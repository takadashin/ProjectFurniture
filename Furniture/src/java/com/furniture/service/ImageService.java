/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.service;

import com.furniture.dao.BaseDao;
import com.furniture.dao.ImageDao;
import com.furniture.domain.Image;


/**
 *
 * @author Linh
 */
public class ImageService extends BaseService<Image> {
    private ImageDao imageDao;

    public ImageService() {
        imageDao = new ImageDao();
    }
    	
    public BaseDao<Image> getDao(){
        return imageDao;
    }
    
}