/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.dao;

import com.furniture.domain.Order;
import com.furniture.utils.Constants;
import com.furniture.utils.Criterion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uh606_000
 */
public class OrderDao extends BaseDao<Order>{
    public OrderDao(){
        tableName = Constants.ORDERS_TB;
    }
    @SuppressWarnings("unchecked")
    public void convertToDomain(ResultSet data, Vector<Order> orders)
    {
        try {
            if(data != null){
                while(data.next())
                {
                    Order order =new Order();
                    
                    order.setId(data.getInt(Constants.ID));
                    order.setOrderNum(data.getString(Constants.ORDERS_NUM));
                    order.setOrderDatetime(data.getDate(Constants.ORDER_DATETIME));
                    order.setShippingAddress(data.getString(Constants.SHIPPING_ADDRESS));
                    order.setShippingDatetime(data.getDate(Constants.SHIPPING_DATETIME));
                    order.setShippedDatetime(data.getDate(Constants.SHIPPED_DATETIME));                    
                    order.setOrderStatus(data.getString(Constants.ORDER_STATUS));
                    order.setUserId(data.getInt(Constants.USER_ID));
                    order.setShippingMethodId(data.getInt(Constants.SHIPPING_METHOD_ID));
                    
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    public Vector convertToTbData(ResultSet data, int colCount)
    {
        Vector orders = new Vector<>();
        try {
            if(data != null){
                while(data.next())
                {
                    Vector record = new Vector();
                    for (int i = 0; i < colCount; i++) {
                        record.add(data.getString(i + 1));
                    }
                    orders.add(record);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
    @SuppressWarnings("unchecked")
    public Object convertToData(Order order,Vector<Criterion> data)
    {
        Object id = order.getId();
        data.add(new Criterion(Constants.ORDERS_NUM, order.getOrderNum()));
        data.add(new Criterion(Constants.ORDER_DATETIME, order.getOrderDatetime()));
        data.add(new Criterion(Constants.SHIPPING_ADDRESS, order.getShippingAddress()));
        data.add(new Criterion(Constants.SHIPPING_DATETIME, order.getShippingDatetime()));
        data.add(new Criterion(Constants.SHIPPED_DATETIME, order.getShippedDatetime()));        
        data.add(new Criterion(Constants.ORDER_STATUS, order.getOrderStatus()));
        data.add(new Criterion(Constants.USER_ID, order.getUserId().toString()));
        data.add(new Criterion(Constants.SHIPPING_METHOD_ID, order.getShippingMethodId().toString()));
        return id;
    }
}
