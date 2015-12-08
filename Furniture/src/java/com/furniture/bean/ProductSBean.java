/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Product;
import com.furniture.service.ProductService;
import com.furniture.utils.ViewUtils;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Linh
 */
@ManagedBean (name="productSBean", eager = true)
@RequestScoped
public class ProductSBean{
    
    private ProductService productSService = new ProductService();
    private Product productS;
    private Vector<Product> productSes;

    @PostConstruct
    public void init() {
        productSes = productSService.getAll();
        this.productS = new Product();
    }

    public ProductService getProductSService() {
        return productSService;
    }

    public void setProductSService(ProductService productSService) {
        this.productSService = productSService;
    }

    public Product getProductS() {
        return productS;
    }

    public void setProductS(Product productS) {
        this.productS = productS;
    }

    public Vector<Product> getProductSes() {
        return productSes;
    }

    public void setProductSes(Vector<Product> productSes) {
        this.productSes = productSes;
    }

   

    public void editproductS(Product productS) {
        this.productS = productS;
	ViewUtils.switchAddEditBaseForm("frmEditProduct", true);
    }

    public void deleteproductS(Product productS) {
        productSService.deleteById(productS.getId());
        productSes = productSService.getAll();
    }
    
    public void addAction(){
        productSService.insertObject(productS);
        productSes = productSService.getAll();
        this.productS= new Product();
    }
    
    public void updateAction(){
        productSService.updatedObject(productS);
        productSes = productSService.getAll();
        this.productS= new Product();
        ViewUtils.switchAddEditBaseForm("frmEditProduct", false);       
    }
    
    public void cancelAction(){
        this.productS= new Product();
	ViewUtils.switchAddEditBaseForm("frmEditproduct", false);
    }

	
}
