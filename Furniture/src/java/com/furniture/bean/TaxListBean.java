/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Tax;
import com.furniture.service.TaxService;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Linh
 */
@ManagedBean(name = "taxListBean", eager = true)
@RequestScoped
public class TaxListBean {

    TaxService taxService = new TaxService();
    private List<TaxBean> taxBeans;

    @PostConstruct
    public void init() {
        Vector<Tax> taxes = taxService.getAll();
        taxBeans = new ArrayList<TaxBean>();
        if (taxes != null) {
            for (int i = 0; i < taxes.size(); i++) {
                Tax tax = taxes.get(i);
                TaxBean taxBean = new TaxBean();
                taxBean.setId(tax.getId());
                taxBean.setTaxCode(tax.getTaxCode());
                taxBean.setTaxName(tax.getTaxName());
                taxBean.setTaxRate(tax.getTaxRate());
                taxBeans.add(taxBean);
            }
        }
    }

    public List<TaxBean> getTaxBeans() {
        return taxBeans;
    }
    
    public void editTax() {
       
    }
    
     public void deleteTax() {
       
    }

}
