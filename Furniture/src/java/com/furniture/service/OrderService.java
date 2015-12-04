/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.service;

import com.furniture.dao.BaseDao;
import com.furniture.dao.OrderDao;
import com.furniture.domain.Order;

/**
 *
 * @author uh606_000
 */
public class OrderService extends BaseService<Order> {
    private OrderDao orderDao;

    public OrderService() {
        orderDao = new OrderDao();
    }
    	
    public BaseDao<Order> getDao(){
        return orderDao;
    }
    
}
