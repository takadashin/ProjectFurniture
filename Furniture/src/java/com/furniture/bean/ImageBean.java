/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.domain.Image;
import com.furniture.domain.Image;
import com.furniture.domain.Product;
import com.furniture.service.ImageService;
import com.furniture.service.ProductService;
import com.furniture.utils.Constants;
import com.furniture.utils.Criterion;
import com.furniture.utils.FacesUtils;
import com.furniture.utils.ViewUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Linh
 */
@ManagedBean(name = "imageBean", eager = true)
@RequestScoped
public class ImageBean{

    private UploadedFile file;
    ImageService imageService = new ImageService();
    ProductService productService = new ProductService();
    private Vector<Image> images;
    private Image image;
    
    private static Integer curProductid;
    private static String currentFileName;
    
    @ManagedProperty(value="#{param.prodId}")
    private static Integer prodId;

    @PostConstruct
    public void init() {
        image = new Image();
        if(curProductid == null || ( prodId != null && curProductid != prodId) )
            curProductid = prodId;
       imageList();
    }
    
    public void imageList(){
        Vector<Criterion> criterions = new Vector<>();
        criterions.add(new Criterion(Constants.IMAGE_PRODUCT_ID, curProductid));
        images = imageService.getBy(criterions);
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Vector<Image> getImages() {
        return images;
    }

    public void setImages(Vector<Image> images) {
        this.images = images;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public void editImage(Image image) {
        this.image = image;
        ViewUtils.switchAddEditBaseForm("frmEditImage", true);
    }

    public void deleteImage(Image image) {
        imageService.deleteById(image.getId());
        imageList();
    }

    public void addAction() {
        image.setProductId(curProductid);
        imageService.insertObject(image);
        imageList();
        this.image = new Image();
    }

    public void updateAction() {
        imageService.updatedObject(image);
        imageList();
        this.image = new Image();
        ViewUtils.switchAddEditBaseForm("frmEditImage", false);
    }

    public void cancelAction() {
        this.image = new Image();
        ViewUtils.switchAddEditBaseForm("frmEditImage", false);
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        try {
            
            Product product = productService.getById(curProductid);
            currentFileName=event.getFile().getFileName();
            if (product != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date();
                currentFileName = "image"+product.getCatId() + "_" + curProductid + "_" + dateFormat.format(date) + currentFileName.substring(currentFileName.length()-4);
            }
            copyFile(currentFileName, event.getFile().getInputstream());
          
            image.setName(currentFileName);
            image.setType(event.getFile().getContentType());
            image.setProductId(curProductid);
            imageService.insertObject(image);
            image = new Image();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void copyFile(String fileName, InputStream in) {
        try {
            String path = FacesUtils.getServletContext().getRealPath("/") + Constants.IMAGE_PRODUCT_PATH;
            
            OutputStream out = new FileOutputStream(new File(path + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
