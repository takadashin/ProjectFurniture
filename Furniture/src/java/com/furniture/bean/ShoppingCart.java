/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Item;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.*;
/**
 *
 * @author uh606_000
 */
@ManagedBean (name="cartBean")
@SessionScoped
public class ShoppingCart {
    private List<Item> cart =new ArrayList<Item>();
    private float total;

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    public float getTotal() {
        total=0;
        for (Item item : cart) {
            total=total + (item.getQuantity()*item.getP().getPrice().floatValue());
        }
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    public String addToCart(Product p)
    {
        //Increament quantity if duplicate product
        for (Item item : cart) {
            if(item.getP().getId().equals(p.getId()))
            {
                item.setQuantity(item.getQuantity()+1);
                return "shoppingcart";
            }
        }
        
        //Createing a new item in cart
        Item i = new Item();
        i.setQuantity(1);
        i.setP(p);
        cart.add(i);
        return "shoppingcart";        
    }   
    
    public void update()
    {
        
    }
    
    public void remove(Item i)
    {
        for (Item item : cart) 
        {
            if(item.equals((i)))
            {
                cart.remove(i);
                break;
            }
        }
    }
    
    
   
}
