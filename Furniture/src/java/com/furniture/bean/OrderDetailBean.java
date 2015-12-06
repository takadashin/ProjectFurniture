/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.OrderDetail;
import com.furniture.service.OrderDetailService;
import com.furniture.utils.ViewUtils;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author uh606_000
 */
@ManagedBean (name="orderdetailBean", eager = true)
@RequestScoped
public class OrderDetailBean {
    private OrderDetailService orderdetailService = new OrderDetailService();
    private OrderDetail orderdetail;
    private Vector<OrderDetail> orderdetails;

    @PostConstruct
    public void init() {
        orderdetails = orderdetailService.getAll();
        this.orderdetail = new OrderDetail();
    }

    public OrderDetailService getOrderdetailService() {
        return orderdetailService;
    }

    public void setOrderdetailService(OrderDetailService orderdetailService) {
        this.orderdetailService = orderdetailService;
    }

    public OrderDetail getOrderdetail() {
        return orderdetail;
    }

    public void setOrderdetail(OrderDetail orderdetail) {
        this.orderdetail = orderdetail;
    }

    public Vector<OrderDetail> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(Vector<OrderDetail> orderdetails) {
        this.orderdetails = orderdetails;
    }

    

    public void editOrderDetail(OrderDetail orderdetail) {
        this.orderdetail = orderdetail;
	ViewUtils.switchAddEditBaseForm("frmEditOrderDetail", true);
    }

    public void deleteOrderDetail(OrderDetail orderdetail) {
        orderdetailService.deleteById(orderdetail.getId());
        orderdetails = orderdetailService.getAll();
    }
    
    public void addAction(){
        orderdetailService.insertObject(orderdetail);
        orderdetails = orderdetailService.getAll();
        this.orderdetail= new OrderDetail();
    }
    
    public void updateAction(){
        orderdetailService.updatedObject(orderdetail);
        orderdetails = orderdetailService.getAll();
        this.orderdetail= new OrderDetail();
        ViewUtils.switchAddEditBaseForm("frmEditOrderDetail", false);       
    }
    
    public void cancelAction(){
        this.orderdetail= new OrderDetail();
	ViewUtils.switchAddEditBaseForm("frmEditOrderDetail", false);
    }
}
