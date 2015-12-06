/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Item;
import com.furniture.domain.Product;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.*;
/**
 *
 * @author uh606_000
 */
@ManagedBean (name="cartBean")
@SessionScoped
public class ShoppingCartBean {
    private List<Item> cart =new ArrayList<Item>();
    private float subTotal;   
    private float shippingprice;
    private int amountOfItem;

    public int getAmountOfItem() {
        amountOfItem=0;
        for (Item item : cart) {
            amountOfItem += item.getQuantity();            
        }
        return amountOfItem;
    }

    public void setAmountOfItem(int amountOfItem) {
        this.amountOfItem = amountOfItem;
    }

    public float getShippingprice() {
        return shippingprice;
    }

    public void setShippingprice(float shippingprice) {
        this.shippingprice = shippingprice;
    }
    
    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }
    
    public float getSubTotal() {
        subTotal = 0;
        for (Item item : cart) {
            subTotal += item.getTotal();            
        }    
        if(shippingprice!=0)
        {
            subTotal += shippingprice;
        }
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }  
    
    
    public String addToCart(Product p)
    {
        //Increament quantity if duplicate product
        for (Item item : cart) {
            if(item.getP().getId() == p.getId())
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
    
    public String payment()
    {
        return "payment";
    }
   
}
