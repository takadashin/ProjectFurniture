/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.domain;

import java.util.Date;



/**
 *
 * @author Linh
 */
public class Product extends BaseDomain{
    private String code;
    private String name;
    private String shortDesc;
    private String desc;
    private Float cost;
    private Float price;
    private Float specPrice;
    private Integer quantity;
    private Integer catId;
    private Date postDate; 
    private Integer tax_id;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getSpecPrice() {
        return specPrice;
    }

    public void setSpecPrice(Float specPrice) {
        this.specPrice = specPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Integer getTax_id() {
        return tax_id;
    }

    public void setTax_id(Integer tax_id) {
        this.tax_id = tax_id;
    }
    
    
}
