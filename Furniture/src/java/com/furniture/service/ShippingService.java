/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.service;

import com.furniture.dao.BaseDao;
import com.furniture.dao.ShippingDao;
import com.furniture.domain.Shipping;

/**
 *
 * @author Linh
 */
public class ShippingService extends BaseService<Shipping> {
    private ShippingDao shipDao;

    public ShippingService() {
        shipDao = new ShippingDao();
    }
    	
    public BaseDao<Shipping> getDao(){
        return shipDao;
    }
    
}
