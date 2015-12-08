/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Image;
import com.furniture.domain.Product;
import com.furniture.service.CatService;
import com.furniture.service.ImageService;
import com.furniture.service.ProductService;
import com.furniture.service.TaxService;
import com.furniture.utils.Constants;
import com.furniture.utils.Criterion;
import com.furniture.utils.ViewUtils;
import java.util.List;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author Linh
 */
@ManagedBean(name = "mainPageBean", eager = true)
@RequestScoped
public class MainPageBean {

    private ProductService productService = new ProductService();
    
    private Product product;
    private Vector<Product> products;
  
    
    
    
    @PostConstruct
    public void init() {
        this.product = new Product();
        products = productService.getAll();
      
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Vector<Product> getProducts() {
        return products;
    }

    public void setProducts(Vector<Product> products) {
        this.products = products;
    }

   
    public String getAvatar(int id)
    {   Vector<Criterion> data = new Vector<>();
        data.add(new Criterion(Constants.IMAGE_PRODUCT_ID, id));
        ImageService service = new ImageService();
        Vector<Image> list = service.getBy(data);
        if(list.size() > 0)
            return service.getBy(data).get(0).getName()+"."+service.getBy(data).get(0).getType();
        else
            return "";
    }
    

    
   
    
    
}
