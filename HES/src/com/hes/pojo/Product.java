/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hes.pojo;

import java.util.Date;

/**
 *
 * @author SATISH SYAMALA
 */
public class Product {

    private int productId;
    private Supplier supplier;
    private String productName;
    private double purchaseCost;
    private int packageSize;
    private String productCode;
    private String identifMark;
    private String status;
    private Date createdOn;

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the purchaseCost
     */
    public double getPurchaseCost() {
        return purchaseCost;
    }

    /**
     * @param purchaseCost the purchaseCost to set
     */
    public void setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

   

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the identifMark
     */
    public String getIdentifMark() {
        return identifMark;
    }

    /**
     * @param identifMark the identifMark to set
     */
    public void setIdentifMark(String identifMark) {
        this.identifMark = identifMark;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the createdOn
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn the createdOn to set
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return the packageSize
     */
    public int getPackageSize() {
        return packageSize;
    }

    /**
     * @param packageSize the packageSize to set
     */
    public void setPackageSize(int packageSize) {
        this.packageSize = packageSize;
    }
}
