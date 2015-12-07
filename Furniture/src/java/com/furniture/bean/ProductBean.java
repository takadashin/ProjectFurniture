/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Product;
import com.furniture.service.CatService;
import com.furniture.service.ProductService;
import com.furniture.utils.Constants;
import com.furniture.utils.Criterion;
import com.furniture.utils.ViewUtils;
import java.util.List;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author Linh
 */
@ManagedBean(name = "productBean", eager = true)
@RequestScoped
public class ProductBean {

    private ProductService productService = new ProductService();
    private CatService catService = new CatService();
    private Product product;
    private Vector<Product> products;
    private List<SelectItem> catSelectItem;
    private List<SelectItem> catListSelectItem;
    private Integer catListId;
    private static Integer currentCatListId;

    @PostConstruct
    public void init() {
        this.product = new Product();
        catListId = currentCatListId;
        productListbyCatId();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Vector<Product> getProducts() {
        return products;
    }

    public void setProducts(Vector<Product> products) {
        this.products = products;
    }

    public List<SelectItem> getCatSelectItem() {
        catSelectItem = catService.getByNull(ViewUtils.comboboxDisplay(Constants.ID, Constants.CATEGORY_NAME), Constants.CATEGORY_PARENTID, false);
        return catSelectItem;
    }

    public void setCatSelectItem(List<SelectItem> catSelectItem) {
        this.catSelectItem = catSelectItem;
    }

    public List<SelectItem> getCatListSelectItem() {
        catListSelectItem = catService.getByNull(ViewUtils.comboboxDisplay(Constants.ID, Constants.CATEGORY_NAME), Constants.CATEGORY_PARENTID, false);
        return catListSelectItem;
    }

    public void setCatListSelectItem(List<SelectItem> catListSelectItem) {
        this.catListSelectItem = catListSelectItem;
    }

    public Integer getCatListId() {
        return catListId;
    }

    public void setCatListId(Integer catListId) {
        currentCatListId = catListId;
        this.catListId = catListId;
    }
    
    public void productListbyCatId(){
        Vector<Criterion> criterions = new Vector<>();
        criterions.add(new Criterion(Constants.PRODUCT_CAT_ID, currentCatListId));
        products = productService.getBy(criterions);
    }

    public void editProduct(Product product) {
        this.product = product;
        ViewUtils.switchAddEditBaseForm("frmEditProduct", true);
    }

    public void deleteProduct(Product product) {
        productService.deleteById(product.getId());
        productListbyCatId();
    }

    public void addAction() {
        productService.insertObject(product);
        productListbyCatId();
        this.product = new Product();
    }

    public void updateAction() {
        productService.updatedObject(product);
        productListbyCatId();
        this.product = new Product();
        ViewUtils.switchAddEditBaseForm("frmEditProduct", false);
    }

    public void cancelAction() {
        this.product = new Product();
        ViewUtils.switchAddEditBaseForm("frmEditProduct", false);
    }
    
    public void changeCat(ValueChangeEvent event){
       currentCatListId = Integer.valueOf(event.getNewValue().toString());
       Vector<Criterion> criterions = new Vector<>();
        criterions.add(new Criterion(Constants.PRODUCT_CAT_ID, currentCatListId));
        products = productService.getBy(criterions);
    }
}
