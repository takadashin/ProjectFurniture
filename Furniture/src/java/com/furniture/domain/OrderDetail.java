/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.domain;

/**
 *
 * @author uh606_000
 */
public class OrderDetail extends BaseDomain{
    private Integer productId;
    private Integer orderId;
    private Integer productOrderQty;
    private Float salesProductPrice;
    private Float productTaxesRate;   

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductOrderQty() {
        return productOrderQty;
    }

    public void setProductOrderQty(Integer productOrderQty) {
        this.productOrderQty = productOrderQty;
    }

    public Float getSalesProductPrice() {
        return salesProductPrice;
    }

    public void setSalesProductPrice(Float salesProductPrice) {
        this.salesProductPrice = salesProductPrice;
    }

    public Float getProductTaxesRate() {
        return productTaxesRate;
    }

    public void setProductTaxesRate(Float productTaxesRate) {
        this.productTaxesRate = productTaxesRate;
    }
    
}
