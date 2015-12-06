/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.service;

import com.furniture.dao.BaseDao;
import com.furniture.dao.ProductDao;
import com.furniture.domain.Product;

/**
 *
 * @author Linh
 */
public class ProductService extends BaseService<Product> {
    private ProductDao productDao;

    public ProductService() {
        productDao = new ProductDao();
    }
    	
    public BaseDao<Product> getDao(){
        return productDao;
    }
}
