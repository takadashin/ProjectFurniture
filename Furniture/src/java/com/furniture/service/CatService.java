/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.service;

import com.furniture.dao.BaseDao;
import com.furniture.dao.CatDao;
import com.furniture.domain.Category;

/**
 *
 * @author Linh
 */
public class CatService extends BaseService<Category> {
    private CatDao catDao;

    public CatService() {
        catDao = new CatDao();
    }
    	
    public BaseDao<Category> getDao(){
        return catDao;
    }
    
}
