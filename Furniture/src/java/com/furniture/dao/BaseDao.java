/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.dao;

import com.furniture.utils.Constants;
import com.furniture.utils.Criterion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public Vector getAll(Vector colNames){
        return getBy(colNames, null, null);
    }
    
    public Vector<T> getAll(){
        Vector<T> domain = new Vector<>();
        getBy(null, null, domain);
        return domain;
    }
    
    public Vector getById(Vector colNames, Object id){
        Vector<Criterion> criterions = new Vector();
        criterions.add(new Criterion(Constants.ID, ""+id));
        return getBy(colNames,criterions,null);
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
    
    public Vector getBy(Vector colNames, Vector<Criterion> criterions,Vector<T> domain){
        Vector data = null;
        String sql = "select * from " + tableName;
        if(criterions != null){
            sql += " where ";
            for (Criterion criterion : criterions) {
                sql += criterion.getColumn() + "= ? " + criterion.getRelation();
            }
        }
        
        sql += " order by " + Constants.ID; 
        ResultSet resultSet = execSQL(sql, criterions);
        
        //Get Domain
        if(domain != null)
            convertToDomain(resultSet,domain);
        //Get data for JTable
        if(colNames != null){
            try {
                ResultSetMetaData rsMetaData = resultSet.getMetaData();
                int colCount = rsMetaData.getColumnCount();
                for(int i=0;i<colCount;i++ ){
                    colNames.add(rsMetaData.getColumnName(i+1));
                }
                data = convertToTbData(resultSet,colCount);
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
                    preStatement.setString(i+1, criterion.getValue());
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
                    preStatement.setString(i+1, criterion.getValue());
                }
                result = preStatement.executeUpdate();
                closeConnection();
                return result;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "It is not able to delete. It have been used","Info", JOptionPane.INFORMATION_MESSAGE);
        }
        return -1;
    }
    
    @SuppressWarnings("unchecked")
    public void convertToDomain(ResultSet data,Vector<T> domain)
    {
     
    }
    public Vector convertToTbData(ResultSet data,int colCout)
    {
        return null;
    }
    
    public Object convertToData(T obj,Vector<Criterion> data){
        return 0;
    }
    
}
