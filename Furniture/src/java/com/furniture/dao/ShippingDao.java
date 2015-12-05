/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.dao;

import com.furniture.domain.Shipping;
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
public class ShippingDao extends BaseDao<Shipping>{
    public ShippingDao(){
        tableName = Constants.SHIPPING_TB;
    }
    @SuppressWarnings("unchecked")
    public void convertToDomain(ResultSet data, Vector<Shipping> shippings)
    {
        try {
            if(data != null){
                while(data.next())
                {
                    Shipping shippingvar =new Shipping();
                    shippingvar.setId(data.getInt(Constants.ID));
                    shippingvar.setShippingName(data.getString(Constants.SHIPPING_NAME));
                    shippingvar.setShippingPrice(data.getFloat(Constants.SHIPPING_PRICE));
                    shippings.add(shippingvar);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShippingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    public Vector convertToTbData(ResultSet data, int colCount)
    {
        Vector taxes = new Vector<>();
        try {
            if(data != null){
                while(data.next())
                {
                    Vector record = new Vector();
                    for (int i = 0; i < colCount; i++) {
                        record.add(data.getString(i + 1));
                    }
                    taxes.add(record);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShippingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taxes;
    }
    
    @SuppressWarnings("unchecked")
    public Object convertToData(Shipping shippingvar,Vector<Criterion> data)
    {
        Object id =shippingvar.getId();
        data.add(new Criterion(Constants.SHIPPING_NAME, shippingvar.getShippingName()));
        data.add(new Criterion(Constants.SHIPPING_PRICE, shippingvar.getShippingPrice().toString()));
        return id;
    }
}