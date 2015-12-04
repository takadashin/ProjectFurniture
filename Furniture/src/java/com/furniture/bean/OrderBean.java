/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Order;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author uh606_000
 */
@ManagedBean (name="orderBean", eager = true)
@RequestScoped
public class OrderBean extends Order{    
	
}
