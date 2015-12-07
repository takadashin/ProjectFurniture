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
    private int quantity;
    private float total;

    public float getTotal() {
        if(p.getSpecPrice()>0)                
        {
            total = (quantity*p.getSpecPrice());
        }
        else
        {
            total = (quantity*p.getPrice());
        }
        return total;
    }
    
    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
