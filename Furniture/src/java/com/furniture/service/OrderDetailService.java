/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.service;

import com.furniture.dao.BaseDao;
import com.furniture.dao.OrderDetailDao;
import com.furniture.domain.OrderDetail;

/**
 *
 * @author uh606_000
 */
public class OrderDetailService extends BaseService<OrderDetail>{
    private OrderDetailDao orderdetailDao;

    public OrderDetailService() {
        orderdetailDao = new OrderDetailDao();
    }
    	
    public BaseDao<OrderDetail> getDao(){
        return orderdetailDao;
    }
}
