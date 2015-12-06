/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.service;

import com.furniture.dao.BaseDao;
import com.furniture.dao.UserDao;
import com.furniture.domain.Users;

/**
 *
 * @author Linh
 */
public class UserService extends BaseService<Users> {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }
    	
    public BaseDao<Users> getDao(){
        return userDao;
    }
    
}
