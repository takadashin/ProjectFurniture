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
public class Item {
    private Product p;

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
