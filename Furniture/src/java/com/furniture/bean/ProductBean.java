/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Image;
import com.furniture.domain.Product;
import com.furniture.service.CatService;
import com.furniture.service.ImageService;
import com.furniture.service.ProductService;
import com.furniture.service.TaxService;
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
    private TaxService taxService = new TaxService();
    private Product product;
    private Vector<Product> products;
    private List<SelectItem> catSelectItem;
    private List<SelectItem> catListSelectItem;
    private List<SelectItem> taxSelectItem;
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
    public String getAvatar(int id)
    {   Vector<Criterion> data = new Vector<>();
        data.add(new Criterion(Constants.IMAGE_PRODUCT_ID, id));
        ImageService service = new ImageService();
        Vector<Image> list = service.getBy(data);
        if(list.size() > 0)
            return service.getBy(data).get(0).getName()+"."+service.getBy(data).get(0).getType();
        else
            return "";
    }
    public Integer getCatListId() {
        return catListId;
    }

    public void setCatListId(Integer catListId) {
        currentCatListId = catListId;
        this.catListId = catListId;
    }

    public List<SelectItem> getTaxSelectItem() {
        taxSelectItem = taxService.getAll(ViewUtils.comboboxDisplay(Constants.ID, Constants.TAXES_CODE));
        return taxSelectItem;
    }

    public void setTaxSelectItem(List<SelectItem> taxSelectItem) {
        this.taxSelectItem = taxSelectItem;
    }

    public void productListbyCatId(){
        Vector<Criterion> criterions = new Vector<>();
        criterions.add(new Criterion(Constants.PRODUCT_CAT_ID, currentCatListId));
        products = productService.getBy(criterions);
    }
    public void productListbyAll(){
        products = productService.getAll();
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
    
    public String imageProduct(Product product){
        return "images?faces-redirect=true&prodId=" + product.getId();
    }
    public String getCatName(int catid)
    {
        return catService.getById(catid).getName();
    }
    public String redirectByID(int id)
    {
        currentCatListId = id;
        
        return "productlist?faces-redirect=true&includeViewParams=true\";";
    }
}
