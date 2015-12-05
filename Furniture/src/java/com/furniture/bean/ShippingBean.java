/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Shipping;
import com.furniture.domain.Tax;
import com.furniture.service.ShippingService;
import com.furniture.utils.Constants;
import com.furniture.utils.Criterion;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import java.util.List;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Linh
 */
@ManagedBean (name="shippingBean", eager = true)
@RequestScoped
public class ShippingBean extends Shipping{    
	private ShippingService service = new ShippingService();
    private Shipping shipvar;
    private Vector<Shipping> shiparray;

    @PostConstruct
    public void init() {
        shiparray = service.getAll();
        this.shipvar = new Shipping();
    }

    public ShippingService getService() {
        return service;
    }

    public void setService(ShippingService Service) {
        this.service = Service;
    }

    public Shipping getShipvar() {
        return shipvar;
    }

    public void setShipvar(Shipping shipvar) {
        this.shipvar = shipvar;
    }

    public Vector<Shipping> getShiparray() {
        return shiparray;
    }

    public void setShiparray(Vector<Shipping> shiparray) {
        this.shiparray = shiparray;
    }

    
    public String delete(Shipping var) {
        
        service.deleteById(var.getId());
        return  "shipping?faces-redirect=true";
    }
    public String update(Shipping var) {
    service.updatedObject(var);
    return  "shipping?faces-redirect=true";
    }
    public String addAction() {
    service.insertObject(shipvar);
    return  "shipping?faces-redirect=true";
    }
}
