/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.utils;

import java.util.List;
import java.util.Vector;
import javax.faces.component.UICommand;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectBoolean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

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
        UICommand btnImage= (UICommand) context.getViewRoot().findComponent(
                formName + ":btnImage");

        btnAdd.setRendered(!isEdit);
        btnUpdate.setRendered(isEdit);
        btnCancel.setRendered(isEdit);
        
        if(btnImage != null){
            btnImage.setRendered(isEdit);
        }
        context.renderResponse();
    }
   
    public static void switchAddImageIntoForm(String formName,String fileName) {

        FacesContext context = FacesContext.getCurrentInstance();

        UIInput txtImageName= (UIInput) context.getViewRoot().findComponent(
                formName + ":txtImageName");
        txtImageName.setValue(fileName);
        txtImageName.setSubmittedValue(fileName);
        context.renderResponse();
    }
    
    public static Vector comboboxDisplay(String valuecolumn, String lableColumn){
        Vector show = new Vector();
        show.add(valuecolumn);
        show.add(lableColumn);
        return show;
    }
    
}
