/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.pojos;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Aadhya
 */
@Entity
@Table(name = "ITEMS", schema = "WMS")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITEMS")
    @SequenceGenerator(name = "SEQ_ITEMS", sequenceName = "SEQ_ITEMS", allocationSize = 1)
    @Column(name = "ITEMS_ID")
    private long itemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRAND_ID")
    private Brands brandId;
    @Column(name = "ITEM_CODE")
    private String itemCode;
    @Column(name = "ITEM_NAME")
    private String itemName;
    @Column(name = "BAR_CODE")
    private String barCode;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "ITEM_TYPE")
    private String itemType;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CREATED_BY")
    private long createdBy;
    @Column(name = "CREATED_ON")
    private Date createdOn;
    @Column(name = "UPDATED_BY")
    private long updatedBy;
    @Column(name = "UPDATED_ON")
    private Date updatedOn;

    public Items() {

    }

    public Items(long itemId) {
        this.itemId = itemId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public Brands getBrandId() {
        return brandId;
    }

    public void setBrandId(Brands brandId) {
        this.brandId = brandId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Map<String, Object> getKeyValues() {

        Map<String, Object> keyValues = new HashMap<>();
        keyValues.put("Brand", this.brandId.getBrandName());
        keyValues.put("Code", this.itemCode);
        keyValues.put("Name", this.itemName);
        keyValues.put("Bar Code", this.barCode);
        keyValues.put("Price", this.price);
        return keyValues;
    }

}
