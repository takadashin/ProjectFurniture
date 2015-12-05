/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Tax;
import com.furniture.service.TaxService;
import com.furniture.utils.ViewUtils;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Linh
 */
@ManagedBean (name="taxBean", eager = true)
@RequestScoped
public class TaxBean{
    
    private TaxService taxService = new TaxService();
    private Tax tax;
    private Vector<Tax> taxes;

    @PostConstruct
    public void init() {
        taxes = taxService.getAll();
        this.tax = new Tax();
    }

    public TaxService getTaxService() {
        return taxService;
    }

    public void setTaxService(TaxService taxService) {
        this.taxService = taxService;
    }

    public Vector<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(Vector<Tax> taxes) {
        this.taxes = taxes;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public void editTax(Tax tax) {
        this.tax = tax;
	ViewUtils.switchAddEditBaseForm("frmEditTax", true);
    }

    public void deleteTax(Tax tax) {
        taxService.deleteById(tax.getId());
        taxes = taxService.getAll();
    }
    
    public void addAction(){
        taxService.insertObject(tax);
        taxes = taxService.getAll();
        this.tax= new Tax();
    }
    
    public void updateAction(){
        taxService.updatedObject(tax);
        taxes = taxService.getAll();
        this.tax= new Tax();
        ViewUtils.switchAddEditBaseForm("frmEditTax", false);       
    }
    
    public void cancelAction(){
        this.tax= new Tax();
	ViewUtils.switchAddEditBaseForm("frmEditTax", false);
    }

	
}
