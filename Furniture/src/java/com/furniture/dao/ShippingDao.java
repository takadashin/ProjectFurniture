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
    public void convertToDomain(ResultSet data, Vector<Shipping> shiparray)
    {
        try {
            if(data != null){
                while(data.next())
                {
                    Shipping shipvar =new Shipping();
                    shipvar.setId(data.getInt(Constants.ID));
                    shipvar.setShippingName(data.getString(Constants.SHIPPING_NAME));
                    shipvar.setShippingPrice(data.getFloat(Constants.SHIPPING_PRICE));
                    shipvar.setShippingDescription(data.getString(Constants.SHIPPING_DESCRIPTION));
                    shipvar.setShippingDuration(data.getInt(Constants.SHIPPING_DURATION));  //Added by Huyen
                    shiparray.add(shipvar);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaxDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    public Vector convertToTbData(ResultSet data, int colCount)
    {
        Vector shiparray = new Vector<>();
        try {
            if(data != null){
                while(data.next())
                {
                    Vector record = new Vector();
                    for (int i = 0; i < colCount; i++) {
                        record.add(data.getString(i + 1));
                    }
                    shiparray.add(record);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaxDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return shiparray;
    }
    
    @SuppressWarnings("unchecked")
    public Object convertToData(Shipping shipvar,Vector<Criterion> data)
    {
        Object id =shipvar.getId();
        data.add(new Criterion(Constants.SHIPPING_NAME, shipvar.getShippingName()));
        data.add(new Criterion(Constants.SHIPPING_PRICE, shipvar.getShippingPrice()));
        data.add(new Criterion(Constants.SHIPPING_DESCRIPTION, shipvar.getShippingDescription()));
        data.add(new Criterion(Constants.SHIPPING_DURATION, shipvar.getShippingDuration()));  //Added by Huyen

        return id;
    }
}