/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Category;
import com.furniture.domain.Shipping;
import com.furniture.domain.Tax;
import com.furniture.service.CatService;
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
@ManagedBean (name="catBean", eager = true)
@RequestScoped
public class CatBean extends Category{    
	private CatService service = new CatService();
    private Category catvar;
    private Vector<Category> catarray;

    @PostConstruct
    public void init() {
        catarray = service.getAll();
        this.catvar = new Category();
    }
    
    public Vector<Category> CatByID(int id)
    {
         Vector<Criterion> data = new Vector<Criterion>();
        data.add(new Criterion(Constants.CATEGORY_PARENTID, id == 0?null:id," and "," is "));

        
        Vector<Category> list = service.getBy(data);
        return list;
    }
    
    public String delete(Category var) {
        
        service.deleteById(var.getId());
        return  "category?faces-redirect=true";
    }
    public String update(Category var) {
   
    service.updatedObject(var);
    return  "category?faces-redirect=true";
    }
    public String addAction() {
    service.insertObject(catvar);
    return  "category?faces-redirect=true";
    }

    public CatService getService() {
        return service;
    }

    public void setService(CatService service) {
        this.service = service;
    }

    public Category getCatvar() {
        return catvar;
    }

    public void setCatvar(Category catvar) {
        this.catvar = catvar;
    }

    public Vector<Category> getCatarray() {
        return catarray;
    }

    public void setCatarray(Vector<Category> catarray) {
        this.catarray = catarray;
    }
}
