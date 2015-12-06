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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Linh
 */
@ManagedBean (name="userSessionBean", eager = true)
@SessionScoped
public class UserSessionBean { 
    private UserService service = new UserService();
    private String username;
    private String password;
    private String type;
    private Users current;

    public String login() {
        Vector<Criterion> data = new Vector<Criterion>();
        data.add(new Criterion(Constants.USER_USERNAME, username," and "));
        data.add(new Criterion(Constants.USER_PASSWORD, password));
        
        Vector<Users> list = service.getBy(data);
        if (list != null && list.size() > 0)
        {
            current = list.get(0);
        }
        if (current == null ) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Unknown login, try again"));
            return null;
        } else {
            return "/mainPages/home?faces-redirect=true";
        }
    }
    public String loginadmin() {
        Vector<Criterion> data = new Vector<Criterion>();
        data.add(new Criterion(Constants.USER_USERNAME, username," and "));
        data.add(new Criterion(Constants.USER_PASSWORD, password));
        
        Vector<Users> list = service.getBy(data);
        if (list != null && list.size() > 0)
        {
            current = list.get(0);
        }
        if (current == null || !current.getType().equals("admin")) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Unknown login, try again"));
            return null;
        } else {
            return "/adminPages/home?faces-redirect=true";
        }
    }
    public Users getCurrent() {
        return current;
    }

    public UserService getService() {
        return service;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setService(UserService service) {
        this.service = service;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return current != null;
    }
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/mainPages/home?faces-redirect=true";
    }
    public boolean  isAdmin()
    {
        return current.getType().equals("admin");
    }

}
