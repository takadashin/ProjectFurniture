/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;




import com.furniture.domain.Users;
import com.furniture.service.UserService;
import com.furniture.utils.Constants;
import com.furniture.utils.Criterion;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import java.util.List;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Linh
 */
@ManagedBean (name="userBean", eager = true)
@RequestScoped
public class UserBean extends Users{    
	private UserService service = new UserService();
    private Users uservar;
    private Vector<Users> userarray;

    @PostConstruct
    public void init() {
        userarray = service.getAll();
        this.uservar = new Users();
    }
    
    
    
    public String delete(Users var) {
        
        service.deleteById(var.getId());
        return  "user?faces-redirect=true";
    }
    public String update(Users var) {
   
    service.updatedObject(var);
    return  "user?faces-redirect=true";
    }
    public String addAction() {
    service.insertObject(uservar);
    return  "user?faces-redirect=true";
    }

    public UserService getService() {
        return service;
    }

    public void setService(UserService service) {
        this.service = service;
    }

    public Users getUservar() {
        return uservar;
    }

    public void setUservar(Users uservar) {
        this.uservar = uservar;
    }

    public Vector<Users> getUserarray() {
        return userarray;
    }

    public void setUserarray(Vector<Users> userarray) {
        this.userarray = userarray;
    }


}