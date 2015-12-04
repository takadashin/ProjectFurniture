/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Order;
import com.furniture.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author uh606_000
 */
@ManagedBean(name = "orderListBean", eager = true)
@RequestScoped
public class OrderListBean {

    OrderService orderService = new OrderService();
    private List<OrderBean> orderBeans;

    @PostConstruct
    public void init() {
        Vector<Order> orders = orderService.getAll();
        orderBeans = new ArrayList<OrderBean>();
        if (orders != null) {
            for (int i = 0; i < orders.size(); i++) {
                
                Order order = orders.get(i);
                
                OrderBean orderBean = new OrderBean();                
                orderBean.setId(order.getId());
                orderBean.setOrderNum(order.getOrderNum());
                orderBean.setOrderDatetime(order.getOrderDatetime());                
                orderBean.setShippingAddress(order.getShippingAddress());
                orderBean.setShippingDatetime(order.getShippingDatetime());
                orderBean.setShippedDatetime(order.getShippedDatetime());
                orderBean.setOrderStatus(order.getOrderStatus());
                orderBean.setUserId(order.getUserId());
                orderBean.setShippingMethodId(order.getShippingMethodId());
                
                orderBeans.add(orderBean);
            }
        }
    }

    public List<OrderBean> getOrderBeans() {
        return orderBeans;
    }
    
    public void editOrder() {
       
    }
    
     public void deleteOrder() {
       
    }

}
