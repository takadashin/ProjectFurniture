/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.dao;

import com.furniture.domain.Image;
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
public class ImageDao extends BaseDao<Image>{
    public ImageDao(){
        tableName = Constants.IMAGES_TB;
    }
    @SuppressWarnings("unchecked")
    public void convertToDomain(ResultSet data, Vector<Image> images)
    {
        try {
            if(data != null){
                while(data.next())
                {
                    Image image =new Image();
                    image.setId(data.getInt(Constants.ID));
                    image.setName(data.getString(Constants.IMAGE_IMAGE_NAME));
                    image.setType(data.getString(Constants.IMAGE_CONTENT_TYPE));
                    image.setProductId(data.getInt(Constants.IMAGE_PRODUCT_ID));
                    images.add(image);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    public Object convertToData(Image image,Vector<Criterion> data)
    {
        Object id =image.getId();
        data.add(new Criterion(Constants.IMAGE_CONTENT_TYPE, image.getType()));
        data.add(new Criterion(Constants.IMAGE_IMAGE_NAME, image.getName()));
        data.add(new Criterion(Constants.IMAGE_PRODUCT_ID, image.getProductId()));
        return id;
    }
}