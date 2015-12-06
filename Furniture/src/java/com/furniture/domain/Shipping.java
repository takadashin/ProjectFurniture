/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.domain;

/**
 *
 * @author khai
 */
public class Shipping extends BaseDomain{
    private String shippingName;
    private Float shippingPrice;
    private String shippingDescription;
    private Integer shippingDuration;

    //Added by Huyen
    public Integer getShippingDuration() {
        return shippingDuration;
    }

    public void setShippingDuration(Integer shippingDuration) {
        this.shippingDuration = shippingDuration;
    }
    //End

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public Float getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(Float shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public String getShippingDescription() {
        return shippingDescription;
    }

    public void setShippingDescription(String shippingDescription) {
        this.shippingDescription = shippingDescription;
    }
    
}
