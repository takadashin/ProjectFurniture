/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.service;

import com.furniture.dao.BaseDao;
import com.furniture.utils.Criterion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Linh
 */
public abstract class BaseService<T> {
    public abstract BaseDao<T> getDao();
    
    public Vector getAll(Vector colNames){
        return getDao().getAll(colNames);
    }
    
    public Vector<T> getAll(){
        return getDao().getAll();
    }
    
    public Vector getById(Vector colNames, Object id){
        return getDao().getById(colNames, id);
    }
    
     public T getById(Object id)
    {
        return getDao().getById(id);
    }
    
    public Vector getBy(Vector colNames, Vector<Criterion> criterions,Vector<T> domain){
       return getDao().getBy(colNames, criterions, domain);
    }
    
    public Vector<T> getBy(Vector<Criterion> criterions){
        return getDao().getBy(criterions);
    }
    
    public int updatedBy(String colName, Object value, Vector<Criterion> data)
    {
        return getDao().updatedBy(colName, value, data);
    }
     
    public int updatedByID(Vector<Criterion> data, Object id)
    {
        return getDao().updatedByID(data, id);
    }
     
    public int updatedObject(T obj)
    {
        return getDao().updatedObject(obj);
    }
    
    public int insert(Vector<Criterion> criterions)
    {
        return getDao().insert(criterions);
    }
    
    public int insertObject(T obj)
    {
        return getDao().insertObject(obj);
    }
    
    public int deleteById(Object id)
    {   
        return getDao().deleteById(id);
    }

    public int deleteByCriterion(Vector<Criterion> criterions)
    {
        return getDao().deleteByCriterion(criterions);
    }
    
    public  void convertToDomain(ResultSet data, Vector<T> domain)
    {
        getDao().convertToDomain(data,domain);
    }
    
    public Vector convertToTbData(ResultSet data,int colCout)
    {
        return getDao().convertToTbData(data, colCout);
    }
    
    public Object convertToData(T obj,Vector<Criterion> data){
        return getDao().convertToData(obj,data);
    }
}
