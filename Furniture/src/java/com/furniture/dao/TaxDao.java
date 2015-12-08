/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.dao;

import com.furniture.domain.Tax;
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
public class TaxDao extends BaseDao<Tax>{
    public TaxDao(){
        tableName = Constants.TAXES_TB;
    }
    @SuppressWarnings("unchecked")
    public void convertToDomain(ResultSet data, Vector<Tax> taxes)
    {
        try {
            if(data != null){
                while(data.next())
                {
                    Tax tax =new Tax();
                    tax.setId(data.getInt(Constants.ID));
                    tax.setTaxCode(data.getString(Constants.TAXES_CODE));
                    tax.setTaxName(data.getString(Constants.TAXES_NAME));
                    tax.setTaxRate(data.getFloat(Constants.TAXES_RATE));
                    taxes.add(tax);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaxDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    public Object convertToData(Tax tax,Vector<Criterion> data)
    {
        Object id =tax.getId();
        data.add(new Criterion(Constants.TAXES_CODE, tax.getTaxCode()));
        data.add(new Criterion(Constants.TAXES_NAME, tax.getTaxName()));
        data.add(new Criterion(Constants.TAXES_RATE, tax.getTaxRate()));
        return id;
    }
}