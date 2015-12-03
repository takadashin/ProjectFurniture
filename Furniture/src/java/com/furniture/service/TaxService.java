/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.service;

import com.furniture.dao.BaseDao;
import com.furniture.dao.TaxDao;
import com.furniture.domain.Tax;

/**
 *
 * @author Linh
 */
public class TaxService extends BaseService<Tax> {
    private TaxDao taxDao;

    public TaxService() {
        taxDao = new TaxDao();
    }
    	
    public BaseDao<Tax> getDao(){
        return taxDao;
    }
    
}
