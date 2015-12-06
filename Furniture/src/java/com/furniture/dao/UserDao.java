/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.dao;



import com.furniture.domain.Users;
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
public class UserDao extends BaseDao<Users>{
    public UserDao(){
        tableName = Constants.USER_TB;
    }
    @SuppressWarnings("unchecked")
    public void convertToDomain(ResultSet data, Vector<Users> array)
    {
        try {
            if(data != null){
                while(data.next())
                {
                    Users var =new Users();
                    var.setId(data.getInt(Constants.ID));
                    var.setUsername(data.getString(Constants.USER_USERNAME));
                    var.setUserpassword(data.getString(Constants.USER_PASSWORD));
                    var.setUserfullname(data.getString(Constants.USER_FULLNAME));
                    var.setAddress(data.getString(Constants.USER_ADDRESS));
                    var.setCityname(data.getString(Constants.USER_CITY_NAME));
                    var.setStatename(data.getString(Constants.USER_STATE_NAME));
                    var.setStatecode(data.getString(Constants.USER_STATE_CODE));
                    var.setPhone(data.getString(Constants.USER_PHONE));
                    var.setEmail(data.getString(Constants.USER_EMAIL));
                    var.setType(data.getString(Constants.USER_TYPE));
                    var.setActive(data.getString(Constants.USER_ACTIVE));
                    array.add(var);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    
    @SuppressWarnings("unchecked")
    public Object convertToData(Users var,Vector<Criterion> data)
    {
        Object id =var.getId();
        data.add(new Criterion(Constants.USER_USERNAME, var.getUsername()));
        data.add(new Criterion(Constants.USER_PASSWORD, var.getUserpassword()));
        data.add(new Criterion(Constants.USER_FULLNAME, var.getUserfullname()));
        data.add(new Criterion(Constants.USER_ACTIVE, var.getActive()));
        data.add(new Criterion(Constants.USER_ADDRESS, var.getAddress()));
        data.add(new Criterion(Constants.USER_CITY_NAME, var.getCityname()));
        data.add(new Criterion(Constants.USER_EMAIL, var.getEmail()));
        data.add(new Criterion(Constants.USER_PHONE, var.getPhone()));
        data.add(new Criterion(Constants.USER_STATE_CODE, var.getStatecode()));
        data.add(new Criterion(Constants.USER_STATE_NAME, var.getStatename()));
        data.add(new Criterion(Constants.USER_TYPE, var.getType()));
        return id;
    }
}