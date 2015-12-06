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
    private float total;
    private float subTotal;   

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    public float getTotal() {
        total = 0;
        for (Item item : cart) {
            if(item.getP().getSpecPrice()!=null)                
            {
                total = (item.getQuantity()*item.getP().getSpecPrice());
            }
            else
            {
                total = (item.getQuantity()*item.getP().getPrice());
            }
            
        }
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    public float getSubTotal() {
        subTotal = 0;
        for (Item item : cart) {
            subTotal += total;
            
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
    
    
   
}
