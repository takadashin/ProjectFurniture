/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.dao;

import com.furniture.utils.Constants;
import com.furniture.utils.Criterion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

/**
 *
 * @author Linh
 */
public class BaseDao<T> {
    private Connection connection;
    protected String tableName;
    
    public Connection getConnection(){
        try{            
            Context ctx = new InitialContext();      
            DataSource ds = (DataSource)ctx.lookup("jdbc/FunitureDatasource"); 
            if(connection == null)
                connection = ds.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException sqlex){
            System.err.println(sqlex.getMessage());
        }
        return connection;
    }
    
    public void closeConnection(){
        try {
            connection.close();
            connection = null;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<SelectItem> getAll(Vector colNames){
        return getBy(colNames, null, null);
    }
    
    public Vector<T> getAll(){
        Vector<T> domain = new Vector<>();
        getBy(null, null, domain);
        return domain;
    }
    
     public T getById(Object id)
    {
        Vector<T> domain = new Vector<>();
        
        Vector<Criterion> criterions = new Vector();
        criterions.add(new Criterion(Constants.ID, ""+id));
        getBy(null,criterions,domain);
        if (domain != null && domain.size() > 0)
        {
            return (T)domain.get(0);
        }
        return null;
    }
    
     public List<SelectItem> getByNull(Vector colNames, String conditionColumn,boolean isNull){
        List<SelectItem> data = new ArrayList<SelectItem>();
        String sql = "select * from " + tableName + " where " + conditionColumn + (isNull ? " is null order by " : " is not null order by ") + Constants.ID;
        ResultSet resultSet = execSQL(sql, null);
        
        if(colNames != null && resultSet != null){
            try {
                while(resultSet.next()){
                    Object value = resultSet.getObject(colNames.get(0).toString()); 
                    String lable = resultSet.getObject(colNames.get(1).toString()).toString(); 
                    data.add(new SelectItem(value, lable));
                }
            } catch (SQLException ex) {
                Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return data;
     }
     
    public List<SelectItem> getBy(Vector colNames, Vector<Criterion> criterions,Vector<T> domain){
        List<SelectItem> data = new ArrayList<SelectItem>();
        String sql = "select * from " + tableName;
        List<Integer> removeitem = new ArrayList<Integer>();
        if(criterions != null){
            sql += " where ";
            int index = 0;
            for (Criterion criterion : criterions) {
                
                if (criterion.getValue() == null)
                {
                    sql += criterion.getColumn()+ " is null " + criterion.getRelation() + " ";
                    removeitem.add(index);
                }
                else
                sql += criterion.getColumn()+ " "+ criterion.getComparison() + " ? " + criterion.getRelation() + " ";
                index++;
            }
        }
        for (int i : removeitem)
        {
            criterions.remove(i);
        }
        
        sql += " order by " + Constants.ID; 
        ResultSet resultSet = execSQL(sql, criterions);
        
        //Get Domain
        if(domain != null)
            convertToDomain(resultSet,domain);
        //Get data for checkbox
        if(colNames != null && resultSet != null){
            try {
                while(resultSet.next()){
                    Object value = resultSet.getObject(colNames.get(0).toString()); 
                    String lable = resultSet.getObject(colNames.get(1).toString()).toString(); 
                    data.add(new SelectItem(value, lable));
                }
            } catch (SQLException ex) {
                Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return data;
    }
    
    public Vector<T> getBy(Vector<Criterion> criterions){
        Vector<T> domain = new Vector<>();
        getBy(null, criterions, domain);
        return domain;
    }
    
    public int insert(Vector<Criterion> criterions)
    {   int id=0;
        String sqlName = "(";
        String sqlValue = "(";

        for (int i = 0; i < criterions.size(); i++ )
        {
            if (i < criterions.size() - 1)
            {
                sqlName += criterions.get(i).getColumn() + ",";
                sqlValue += "?,";
            }
            else
            {
                sqlName += criterions.get(i).getColumn()+ ")";
                sqlValue += "?)";
            }
        }
        String sql = "Insert Into " + tableName + sqlName + " Values" + sqlValue;
        if(execUpdateSQL(sql, criterions)>0){
            try {
                ResultSet resultSet = execSQL("select Max("+ Constants.ID + ") from " + tableName, null);
                if(resultSet.next()){
                    id = resultSet.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }
    
    public int insertObject(T obj)
    {   Vector<Criterion> criterions = new Vector<>();
        convertToData(obj,criterions);
        return insert(criterions);
    }
    
    public int updatedBy(String colName, Object value, Vector<Criterion> data)
    {
        String sql = "Update " + tableName + " Set ";

        for (int i = 0; i < data.size(); i++)
        {
            if (i < data.size() - 1)
                sql = sql + data.get(i).getColumn() + "= ?, ";
            else
                sql = sql + data.get(i).getColumn() + "= ? ";
        }
        sql = sql + " where " + colName + "=? ";
        data.add(new Criterion(colName,value.toString()));
        return execUpdateSQL(sql, data);
    }
     
    public int updatedByID(Vector<Criterion> data, Object id)
    {
        return updatedBy(Constants.ID,id,data);
    }
     
    public int updatedObject(T obj)
    {
        Vector<Criterion> data = new Vector<>();
        Object id = convertToData(obj,data);
        return updatedByID(data,id);
    }

    
    public int deleteById(Object id)
    {   Vector<Criterion> criterions = new Vector<>();
        criterions.add(new Criterion(Constants.ID, id + ""));
        return deleteByCriterion(criterions);
    }

    public int deleteByCriterion(Vector<Criterion> criterions)
    {   getConnection();
        String sql = "Delete From " + tableName + " where ";
        for (Criterion criterion : criterions)
            sql += criterion.getColumn() + "= ? " + criterion.getRelation() + " ";
        return execUpdateSQL(sql, criterions);
    }
    
    public ResultSet execSQL(String sql,Vector<Criterion> critetions ){
        try {
            getConnection();
            ResultSet resultSet;
            if(critetions == null){
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
            }else{
                PreparedStatement preStatement = connection.prepareStatement(sql);
                for (int i = 0; i < critetions.size(); i++) {
                    Criterion criterion = critetions.get(i);
                    preStatement.setObject(i+1, criterion.getValue());
                }
                resultSet = preStatement.executeQuery();
                 
            }
            closeConnection();
            return resultSet;
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int execUpdateSQL(String sql,Vector<Criterion> critetions ){
        try {
            getConnection();
            int result;
            if(critetions == null){
                Statement statement = connection.createStatement();
                 result = statement.executeUpdate(sql);
            }else{
                PreparedStatement preStatement = connection.prepareStatement(sql);
                for (int i = 0; i < critetions.size(); i++) {
                    Criterion criterion = critetions.get(i);
                    preStatement.setObject(i+1, criterion.getValue());
                }
                result = preStatement.executeUpdate();
                closeConnection();
                return result;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "It is not able to update. It have been used","Info", JOptionPane.INFORMATION_MESSAGE);
        }
        return -1;
    }
    
    @SuppressWarnings("unchecked")
    public void convertToDomain(ResultSet data,Vector<T> domain)
    {
     
    }
    
    public Object convertToData(T obj,Vector<Criterion> data){
        return 0;
    }
    
}
