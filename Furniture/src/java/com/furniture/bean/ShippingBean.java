/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Shipping;
import com.furniture.service.ShippingService;
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
	
}
