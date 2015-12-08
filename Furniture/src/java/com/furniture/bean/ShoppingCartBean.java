/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Item;
import com.furniture.domain.Order;
import com.furniture.domain.OrderDetail;
import com.furniture.domain.Product;
import com.furniture.domain.Shipping;
import com.furniture.domain.Tax;
import com.furniture.service.OrderDetailService;
import com.furniture.service.OrderService;
import com.furniture.service.ShippingService;
import com.furniture.service.TaxService;
import java.text.SimpleDateFormat;
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
    private Date shipDate;
    private Shipping shipM;
    private ShippingService shippingService = new ShippingService();   
    
    private Tax tax;
    private TaxService taxService = new TaxService(); 
    
    private String cartNum;
    private String shipAddr;

    public String getShipAddr() {
        return shipAddr;
    }

    public void setShipAddr(String shipAddr) {
        this.shipAddr = shipAddr;
    }

    public String getCartNum() {        
        return cartNum;
    }

    public void setCartNum(String cartNum) {
        this.cartNum = cartNum;
    }
    
    
    public Date getShipDate() {
        Calendar cal = Calendar.getInstance();

        if(shipid!=0)
        {
            cal.add(Calendar.DATE, shipM.getShippingDuration());
        }
        shipDate = cal.getTime();   
        cartNum = "O" + new SimpleDateFormat("yyMMddhhmmss").format(shipDate);
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }
    
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
//        if(tax!=null)
//        {
//            for (Item item : cart) {
//                
//                subTotal += item.getTotal() *(1+tax.getTaxRate()/100); 
//            }   
//        }
//        else
//        {
//            for (Item item : cart) {
//                subTotal += item.getTotal(); 
//            }
//        }
        
        for (Item item : cart) {            
            if(tax!=null)
            {
                subTotal += item.getTotal() *(1+tax.getTaxRate()/100); 
            }
            else
            {
                subTotal += item.getTotal();
            }
            
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
    
    
    public String addToCart(Product p)
    {
        //Increament quantity if duplicate product
        for (Item item : cart) {
            if(item.getP().getId() == p.getId())
            {
                item.setQuantity(item.getQuantity()+1);
                return "shoppingcart?faces-redirect=true&includeViewParams=true\";";
            }
        }
        
        //Createing a new item in cart
        Item i = new Item();
        i.setQuantity(1);
        i.setP(p);
        cart.add(i);
        
        tax = taxService.getById(p.getTaxId());
        return "shoppingcart?faces-redirect=true&includeViewParams=true\";";        
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
    
   
    private Order order;
    private OrderService orderService = new OrderService();

    public Order getOrder() { 
        
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    
    public void createOrder(int userId){
        order = new Order();
        order.setOrderNum(cartNum);
        order.setShippingAddress(shipAddr);
        order.setShippingDatetime(shipDate);
        order.setOrderStatus("Purchased");
        order.setUserId(userId);
        order.setShippingMethodId(shipid);
        orderService.insertObject(order); 
        
        order = orderService.getAll().lastElement();
              
        for (Item item : cart) {
            orderdetail = new OrderDetail(); 
            orderdetail.setOrderId(order.getId());
            orderdetail.setProductId(item.getP().getId());
            orderdetail.setProductOrderQty(item.getQuantity());
            tax = taxService.getById(item.getP().getTaxId());
            if(tax!=null)
            {
                orderdetail.setProductTaxesRate(tax.getTaxRate());
            }
           
            if(item.getP().getSpecPrice()>0)
            {
                orderdetail.setSalesProductPrice(item.getP().getSpecPrice());
            }
            else
            {
                orderdetail.setSalesProductPrice(item.getP().getPrice());
            }
            
            orderdetailService.insertObject(orderdetail);
            
        }
        
        
        cart.clear();
        
    }
    
    private OrderDetail orderdetail;
    private OrderDetailService orderdetailService = new OrderDetailService();

    public OrderDetail getOrderdetail() {
        return orderdetail;
    }

    public void setOrderdetail(OrderDetail orderdetail) {
        this.orderdetail = orderdetail;
    }

    public OrderDetailService getOrderdetailService() {
        return orderdetailService;
    }

    public void setOrderdetailService(OrderDetailService orderdetailService) {
        this.orderdetailService = orderdetailService;
    }
    
    
}
