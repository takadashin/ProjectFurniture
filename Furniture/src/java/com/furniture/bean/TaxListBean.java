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

    private TaxService taxService = new TaxService();
    private Tax tax;
    private Vector<Tax> taxes;

    @PostConstruct
    public void init() {
        taxes = taxService.getAll();
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

    }

    public void deleteTax(Tax tax) {
        taxService.deleteById(tax.getId());
        taxes = taxService.getAll();
    }

}
