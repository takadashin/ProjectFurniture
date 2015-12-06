/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.utils;

import com.furniture.bean.UserSessionBean;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author khai
 */
public class LoginFilter implements Filter {
  private FilterConfig config;

  public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain)
          throws IOException, ServletException {
      
    String path = ((HttpServletRequest) req).getRequestURI();
    HttpServletResponse response = (HttpServletResponse) resp;
    HttpSession session = ((HttpServletRequest) req).getSession(false);
    UserSessionBean user = (session != null) ? (UserSessionBean) session.getAttribute("userSessionBean") : null;
    if (path.contains("/faces/mainPages/login.xhtml")) {
        if (user != null && user.isLoggedIn())
        {
            response.sendRedirect(((HttpServletRequest) req).getContextPath() + "/faces/mainPages/user/profile.xhtml"); 
        }
        else
            chain.doFilter(req, resp); // Just continue chain.
    } 
    if (path.contains("/faces/mainPages/adminlogin.xhtml")) {
        if (user != null && user.isLoggedIn() && user.isAdmin())
        {
            response.sendRedirect(((HttpServletRequest) req).getContextPath() + "/faces/adminPages/home.xhtml"); 
        }
        else
            chain.doFilter(req, resp); // Just continue chain.
    } 
  }

  public void init(FilterConfig config) throws ServletException {
    this.config = config;
  }

  public void destroy() {
    config = null;
  }
}
