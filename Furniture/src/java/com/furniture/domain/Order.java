/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.domain;

import java.util.Date;

/**
 *
 * @author uh606_000
 */
public class Order extends BaseDomain{
    private String orderNum;
    private Date orderDatetime;
    private String shippingAddress;
    private Date shippingDatetime;
    private Date shippedDatetime;    
    private String orderStatus;
    private int userId;
    private int shippingMethodId;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    
    public Date getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(Date orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getShippingDatetime() {
        return shippingDatetime;
    }

    public void setShippingDatetime(Date shippingDatetime) {
        this.shippingDatetime = shippingDatetime;
    }
    
    public Date getShippedDatetime() {
        return shippedDatetime;
    }

    public void setShippedDatetime(Date shippedDatetime) {
        this.shippedDatetime = shippedDatetime;
    }
    
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(int shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }
}
