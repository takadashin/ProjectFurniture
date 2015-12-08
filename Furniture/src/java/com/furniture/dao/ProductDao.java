/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.dao;

import com.furniture.domain.Product;
import com.furniture.utils.Constants;
import com.furniture.utils.Criterion;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linh
 */
public class ProductDao  extends BaseDao<Product>{
    public ProductDao(){
        tableName = Constants.PRODUCT_TB;
    }
    @SuppressWarnings("unchecked")
    public void convertToDomain(ResultSet data, Vector<Product> products)
    {
        try {
            if(data != null){
                while(data.next())
                {
                    Product product =new Product();
                    product.setId(data.getInt(Constants.ID));
                    product.setCatId(data.getInt(Constants.PRODUCT_CAT_ID));
                    product.setTaxId(data.getInt(Constants.PRODUCT_TAX_ID));
                    product.setCode(data.getString(Constants.PRODUCT_CODE));
                    product.setCost(data.getFloat(Constants.PRODUCT_COST));
                    product.setDesc(data.getString(Constants.PRODUCT_DESC));
                    product.setName(data.getString(Constants.PRODUCT_NAME));
                    product.setPrice(data.getFloat(Constants.PRODUCT_PRICE));
                    product.setQuantity(data.getInt(Constants.PRODUCT_QTY));
                    product.setShortDesc(data.getString(Constants.PRODUCT_SHORT_DESC));
                    product.setSpecPrice(data.getFloat(Constants.PRODUCT_SPEC_PRICE));
                    product.setPostDate(data.getDate(Constants.PRODUCT_POST_DATE));
                    
                    products.add(product);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    public Object convertToData(Product product,Vector<Criterion> data)
    {
        Object id =product.getId();
        data.add(new Criterion(Constants.PRODUCT_CAT_ID, product.getCatId()));
        data.add(new Criterion(Constants.PRODUCT_TAX_ID, product.getTaxId()));
        data.add(new Criterion(Constants.PRODUCT_CODE, product.getCode()));
        data.add(new Criterion(Constants.PRODUCT_COST, product.getCost()));
        data.add(new Criterion(Constants.PRODUCT_DESC, product.getDesc()));
        data.add(new Criterion(Constants.PRODUCT_NAME, product.getName()));
        data.add(new Criterion(Constants.PRODUCT_PRICE, product.getPrice()));
        data.add(new Criterion(Constants.PRODUCT_QTY, product.getQuantity()));
        data.add(new Criterion(Constants.PRODUCT_SHORT_DESC, product.getShortDesc()));
        data.add(new Criterion(Constants.PRODUCT_SPEC_PRICE, product.getSpecPrice()));
        if(product.getPostDate() != null){
            data.add(new Criterion(Constants.PRODUCT_POST_DATE, new Date(product.getPostDate().getTime())));
        }

        return id;
    }
}