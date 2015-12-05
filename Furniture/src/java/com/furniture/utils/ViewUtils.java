/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.utils;

import javax.faces.component.UICommand;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectBoolean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Linh
 */
public class ViewUtils {
    
    public static void switchAddEditBaseForm(String formName, boolean isEdit) {

        FacesContext context = FacesContext.getCurrentInstance();

        UICommand btnAdd = (UICommand) context.getViewRoot().findComponent(
                formName + ":btnAdd");
        UICommand btnUpdate = (UICommand) context.getViewRoot().findComponent(
                formName + ":btnUpdate");
        UICommand btnCancel = (UICommand) context.getViewRoot().findComponent(
                formName + ":btnCancel");

        btnAdd.setRendered(!isEdit);
        btnUpdate.setRendered(isEdit);
        btnCancel.setRendered(isEdit);
        context.renderResponse();
    }
    public static void switchAddEditCancelBaseForm(String formName, boolean isEdit) {

        FacesContext context = FacesContext.getCurrentInstance();

        UICommand btnAdd = (UICommand) context.getViewRoot().findComponent(
                formName + ":btnAdd");
        UICommand btnUpdate = (UICommand) context.getViewRoot().findComponent(
                formName + ":btnUpdate");
        UICommand btnCancel = (UICommand) context.getViewRoot().findComponent(
                formName + ":btnCancel");
        
        btnAdd.setRendered(!isEdit);
        btnUpdate.setRendered(isEdit);
        btnCancel.setRendered(isEdit);        
        context.renderResponse();
    }
    
}
