/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Image;
import com.furniture.domain.Product;
import com.furniture.service.ImageService;
import com.furniture.service.ProductService;
import com.furniture.utils.Constants;
import com.furniture.utils.Criterion;
import com.sun.xml.ws.tx.at.v10.types.Vote;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Linh
 */
@ManagedBean(name = "productDetailBean", eager = true)
@RequestScoped
public class ProductDetailBean {
    
   
    private ImageService imageService =new ImageService();
    private ProductService ProductService = new ProductService();
    
    private Product product;
    private Vector<Image> images;
    
    @ManagedProperty(value="#{param.id}")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
     @PostConstruct
    public void init() {
        product = ProductService.getById(id); 
        imageList();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String firstImageName() {
        if (images != null && images.size()>0)
            return images.get(0).getName();
        else
            return "";
    }
    

    public Vector<Image> getImages() {
        imageList();
        return images;
    }

    public void setImages(Vector<Image> images) {
        this.images = images;
    }
    
    
    
    public void imageList(){
        Vector<Criterion> criterions = new Vector<>();
        criterions.add(new Criterion(Constants.IMAGE_PRODUCT_ID, id));
        images = imageService.getBy(criterions);
        if(images == null){
            images = new Vector<Image>();
        }
    }
}
