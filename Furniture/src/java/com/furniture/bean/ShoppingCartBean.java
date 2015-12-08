/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Item;
import com.furniture.domain.Product;
import com.furniture.domain.Shipping;
import com.furniture.domain.Tax;
import com.furniture.service.ShippingService;
import com.furniture.service.TaxService;
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
    private int amountOfItem;    
    private int shipid;
    private Shipping shipM;
    private ShippingService shippingService = new ShippingService();    
    
    private Tax tax;
    private TaxService taxService = new TaxService();

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
    
    public TaxService getTaxService() {
        return taxService;
    }

    public void setTaxService(TaxService taxService) {
        this.taxService = taxService;
    }

    public int getShipid() {
        return shipid;
    }

    public void setShipid(int shipid) {
        this.shipid = shipid;
    }

    public Shipping getShipM() {
        shipM = shippingService.getById(shipid);
        return shipM;
    }

    public void setShipM(Shipping shipM) {
        this.shipM = shipM;
    }

    public ShippingService getShippingService() {
        return shippingService;
    }

    public void setShippingService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public int getAmountOfItem() {
        amountOfItem=0;
        for (Item item : cart) {
            amountOfItem += item.getQuantity();            
        }
        return amountOfItem;
    }

    public Date calculateShippingDate()
    {
        Date d;
 
        Calendar cal = Calendar.getInstance();

        if(shipid!=0)
        {
            cal.add(Calendar.DATE, shipM.getShippingDuration());
        }
        d = cal.getTime();
        return d;
    }
    
    public void setAmountOfItem(int amountOfItem) {
        this.amountOfItem = amountOfItem;
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
            subTotal += item.getTotal() *(1+tax.getTaxRate()/100);            
        }    
        if(shipid!=0)
        {
            subTotal += shipM.getShippingPrice();        
        }
        
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }  
    
    
    public void addToCart(Product p)
    {
        //Increament quantity if duplicate product
        for (Item item : cart) {
            if(item.getP().getId() == p.getId())
            {
                item.setQuantity(item.getQuantity()+1);
//                return "shoppingcart";
            }
        }
        
        //Createing a new item in cart
        Item i = new Item();
        i.setQuantity(1);
        i.setP(p);
        cart.add(i);
//        return "shoppingcart";        
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
