/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Tax;
import com.furniture.service.TaxService;
import java.util.Vector;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Linh
 */
@ManagedBean (name="taxBean", eager = true)
@RequestScoped
public class TaxBean extends Tax{    
	
}
