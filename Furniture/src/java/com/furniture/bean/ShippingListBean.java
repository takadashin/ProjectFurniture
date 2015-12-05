/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Shipping;
import com.furniture.service.ShippingService;
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
@ManagedBean(name = "shippingListBean", eager = true)
@RequestScoped
public class ShippingListBean {

    ShippingService shipService = new ShippingService();
    private List<ShippingBean> shipBeans;

    @PostConstruct
    public void init() {
        Vector<Shipping> ships = shipService.getAll();
        shipBeans = new ArrayList<ShippingBean>();
        if (ships != null) {
            for (int i = 0; i < ships.size(); i++) {
                Shipping shipvar = ships.get(i);
                ShippingBean shipBeanvar = new ShippingBean();
                shipBeanvar.setId(shipvar.getId());
                shipBeanvar.setShippingName(shipvar.getShippingName());
                shipBeanvar.setShippingPrice(shipvar.getShippingPrice());
                shipBeans.add(shipBeanvar);
            }
        }
    }

    public List<ShippingBean> getShippingBeans() {
        return shipBeans;
    }
    
    public void editTax() {
       
    }
    
     public void deleteTax() {
       
    }

}
