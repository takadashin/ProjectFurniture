/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.dao;

import com.furniture.domain.Category;
import com.furniture.domain.Shipping;
import com.furniture.utils.Constants;
import com.furniture.utils.Criterion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linh
 */
public class CatDao extends BaseDao<Category>{
    public CatDao(){
        tableName = Constants.CATEGORY_TB;
    }
    @SuppressWarnings("unchecked")
    public void convertToDomain(ResultSet data, Vector<Category> array)
    {
        try {
            if(data != null){
                while(data.next())
                {
                    Category var =new Category();
                    var.setId(data.getInt(Constants.ID));
                    var.setName(data.getString(Constants.CATEGORY_NAME));
                    var.setDescription(data.getString(Constants.CATEGORY_DESCRIPTION));
                    var.setParentid(data.getInt(Constants.CATEGORY_PARENTID));
                    array.add(var);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CatDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    public Vector convertToTbData(ResultSet data, int colCount)
    {
        Vector array = new Vector<>();
        try {
            if(data != null){
                while(data.next())
                {
                    Vector record = new Vector();
                    for (int i = 0; i < colCount; i++) {
                        record.add(data.getString(i + 1));
                    }
                    array.add(record);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CatDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    
    @SuppressWarnings("unchecked")
    public Object convertToData(Category var,Vector<Criterion> data)
    {
        Object id =var.getId();
        data.add(new Criterion(Constants.CATEGORY_NAME, var.getName()));
        data.add(new Criterion(Constants.CATEGORY_PARENTID, var.getParentid()!= 0?var.getParentid():null));
        data.add(new Criterion(Constants.CATEGORY_DESCRIPTION, var.getDescription()));

        return id;
    }
}