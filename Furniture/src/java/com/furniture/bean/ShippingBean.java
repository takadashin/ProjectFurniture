/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Shipping;
import com.furniture.service.ShippingService;
import com.furniture.utils.Constants;
import com.furniture.utils.Criterion;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import java.util.List;
import java.util.Vector;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Linh
 */
@ManagedBean (name="shippingBean", eager = true)
@RequestScoped
public class ShippingBean extends Shipping{    
	public String delete() {
        ShippingService service = new ShippingService();
        service.deleteById(this.getId());
        return  "shipping?faces-redirect=true";
    }
        public String update() {
        ShippingService service = new ShippingService();
        Vector<Criterion> argument = new Vector<>();
        Criterion object = new Criterion(Constants.SHIPPING_NAME, this.getShippingName());
        Criterion object2 = new Criterion(Constants.SHIPPING_PRICE, this.getShippingPrice().toString());
        argument.add(object);
        argument.add(object2);
        service.updatedByID(argument,this.getId());
        return  "shipping?faces-redirect=true";
    }
}
