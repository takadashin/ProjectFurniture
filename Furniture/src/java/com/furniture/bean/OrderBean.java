/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Order;
import com.furniture.service.OrderService;
import com.furniture.utils.ViewUtils;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author uh606_000
 */
@ManagedBean (name="orderBean", eager = true)
@RequestScoped
public class OrderBean{    
    private OrderService orderService = new OrderService();
    private Order order;
    private Vector<Order> orders;

    @PostConstruct
    public void init() {
        orders = orderService.getAll();
        this.order = new Order();
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public Vector<Order> getOrders() {
        return orders;
    }

    public void setOrders(Vector<Order> orders) {
        this.orders = orders;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void editOrder(Order order) {
        this.order = order;
	ViewUtils.switchAddEditBaseForm("frmEditOrder", true);
    }

    public void deleteOrder(Order order) {
        orderService.deleteById(order.getId());
        orders = orderService.getAll();
    }
    
    public void addAction(){
        orderService.insertObject(order);
        orders = orderService.getAll();
        this.order= new Order();
    }
    
    public void updateAction(){
        orderService.updatedObject(order);
        orders = orderService.getAll();
        this.order= new Order();
        ViewUtils.switchAddEditBaseForm("frmEditOrder", false);       
    }
    
    public void cancelAction(){
        this.order= new Order();
	ViewUtils.switchAddEditBaseForm("frmEditOrder", false);
    }
	
}
