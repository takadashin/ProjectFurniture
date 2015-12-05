/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.utils;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Linh
 */
public class FacesUtils {
    
    public static ServletContext getServletContext() {
		try {
			return (ServletContext) FacesContext.getCurrentInstance()
					.getExternalContext().getContext();
		} catch (Throwable t) {
			return null;
		}
	}

	/**
	 * Get managed beans based on the beans name.
	 * 
	 * @param beanName
	 *            the beans name
	 * @return the managed beans associated with the beans name
	 */
	public static Object getManagedBean(String beanName) {
		return getValueExpression(getJsfEl(beanName)).getValue(
				getCurrentInstance().getELContext());
	}

	/**
	 * Remove the managed beans based on the beans name.
	 * 
	 * @param beanName
	 *            the beans name of the managed beans to be removed
	 */
	public static void resetManagedBean(String beanName) {
		getValueExpression(getJsfEl(beanName)).setValue(
				getCurrentInstance().getELContext(), null);
	}
        
        public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
	}
        
        public static Application getApplication() {
		return getCurrentInstance().getApplication();
	}
        
        private static ValueExpression getValueExpression(String el) {
		return getApplication().getExpressionFactory().createValueExpression(
				getCurrentInstance().getELContext(), el, Object.class);
	}
        
        private static String getJsfEl(String value) {
		return "#{" + value + "}";
	}
    
}
