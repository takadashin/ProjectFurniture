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
@ManagedBean (name="productBean", eager = true)
@RequestScoped
public class ProductBean {
    private ProductService productService = new ProductService();
    private Product product;
    private Vector<Product> products;

    @PostConstruct
    public void init() {
        products = productService.getAll();
        this.product = new Product();
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Vector<Product> getProducts() {
        return products;
    }

    public void setProductes(Vector<Product> products) {
        this.products = products;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void editProduct(Product product) {
        this.product = product;
	ViewUtils.switchAddEditBaseForm("frmEditProduct", true);
    }

    public void deleteProduct(Product product) {
        productService.deleteById(product.getId());
        products = productService.getAll();
    }
    
    public void addAction(){
        productService.insertObject(product);
        products = productService.getAll();
        this.product= new Product();
    }
    
    public void updateAction(){
        productService.updatedObject(product);
        products = productService.getAll();
        this.product= new Product();
        ViewUtils.switchAddEditBaseForm("frmEditProduct", false);       
    }
    
    public void cancelAction(){
        this.product= new Product();
	ViewUtils.switchAddEditBaseForm("frmEditProduct", false);
    }

}
