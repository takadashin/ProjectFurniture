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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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
    private String repassword;

    @PostConstruct
    public void init() {
        userarray = service.getAll();
        this.uservar = new Users();
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
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
    public String addUserAction() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        boolean isvalid = true;
        Vector<Criterion> data = new Vector<Criterion>();
        data.add(new Criterion(Constants.USER_USERNAME, uservar.getUsername()));
        
        Vector<Users> list = service.getBy(data);
        
        if (!uservar.getUserpassword().equals(repassword))
        {
            
            facesContext.addMessage("registerForm", new FacesMessage("Password retype is not the same with password."));
            isvalid = false;
        }
        if (list != null && list.size() > 0)
        {
            
            facesContext.addMessage("registerForm", new FacesMessage("Username is existed. Please choose another name."));
            isvalid = false;
        }
        
        data = new Vector<Criterion>();
        data.add(new Criterion(Constants.USER_EMAIL, uservar.getEmail()));
        
        list = service.getBy(data);
        if (list != null && list.size() > 0)
        {
            
            facesContext.addMessage("registerForm", new FacesMessage("Email is already be used. Please choose another email."));
            isvalid = false;
        }
        if(isvalid)
        {
            uservar.setActive("1");
            uservar.setType("user");
            service.insertObject(uservar);
            return  "/mainPages/login?faces-redirect=true";
        }
        else
            return null;
    }
    
    public String editUserAction(Users userthis) {
        uservar = userthis;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        boolean isvalid = true;
        
        
        if (!uservar.getUserpassword().equals(repassword))
        {
            
            facesContext.addMessage("registerForm", new FacesMessage("Password retype is not the same with password."));
            isvalid = false;
        }
        
        
       
        if(isvalid)
        {
            service.updatedObject(uservar);
            facesContext.addMessage("registerForm", new FacesMessage("Your information is saved."));
            return null;
        }
        else
            return null;
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
