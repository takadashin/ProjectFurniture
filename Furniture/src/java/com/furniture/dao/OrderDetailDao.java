/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.dao;

import com.furniture.domain.OrderDetail;
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
public class OrderDetailDao extends BaseDao<OrderDetail>{
    public OrderDetailDao(){
        tableName = Constants.ORDERDETAILS_TB;
    }
    @SuppressWarnings("unchecked")
    public void convertToDomain(ResultSet data, Vector<OrderDetail> orderdetails)
    {
        try {
            if(data != null){
                while(data.next())
                {
                    OrderDetail orderdetail =new OrderDetail();
                    
                    orderdetail.setId(data.getInt(Constants.ID));
                    orderdetail.setProductId(data.getInt(Constants.PRODUCT_ID));
                    orderdetail.setOrderId(data.getInt(Constants.ORDER_ID));
                    orderdetail.setProductOrderQty(data.getInt(Constants.PRODUCT_ORDER_QTY));
                    orderdetail.setSalesProductPrice(data.getFloat(Constants.SALES_PRODUCT_PRICE));
                    orderdetail.setProductTaxesRate(data.getFloat(Constants.PRODUCT_TAXES_RATE));
                    
                    orderdetails.add(orderdetail);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    public Vector convertToTbData(ResultSet data, int colCount)
    {
        Vector orderdetails = new Vector<>();
        try {
            if(data != null){
                while(data.next())
                {
                    Vector record = new Vector();
                    for (int i = 0; i < colCount; i++) {
                        record.add(data.getString(i + 1));
                    }
                    orderdetails.add(record);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderdetails;
    }
    
    @SuppressWarnings("unchecked")
    public Object convertToData(OrderDetail orderdetail,Vector<Criterion> data)
    {
        Object id = orderdetail.getId();
        data.add(new Criterion(Constants.PRODUCT_ID, orderdetail.getProductId()));
        data.add(new Criterion(Constants.ORDER_ID, orderdetail.getOrderId()));
        data.add(new Criterion(Constants.PRODUCT_ORDER_QTY, orderdetail.getProductOrderQty()));
        data.add(new Criterion(Constants.SALES_PRODUCT_PRICE, orderdetail.getSalesProductPrice()));
        data.add(new Criterion(Constants.PRODUCT_TAXES_RATE, orderdetail.getProductTaxesRate()));

        return id;
    }
    
}
