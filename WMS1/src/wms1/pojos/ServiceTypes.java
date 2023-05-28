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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Aadhya
 */
@Entity
@Table(name = "SERVICE_TYPES",schema = "WMS")
public class ServiceTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SERVICE_TYPES")
    @SequenceGenerator(name = "SEQ_SERVICE_TYPES", sequenceName = "SEQ_SERVICE_TYPES", allocationSize = 1)
    @Column(name = "SERVICE_TYPE_ID")
    private long serviceTypeId;
    @Column(name = "SERVICE_CODE")
    private String serviceCode;
    @Column(name = "SERVICE_TYPE")
    private String serviceType;
    @Column(name = "PRICE")
    private double price;
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

    public long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
    
      @Transient
    public Map<String, Object> getKeyValues() {
        Map<String, Object> keyValues=new HashMap<>();
        keyValues.put("Type",this.serviceType);
        keyValues.put("Price",this.price);
        return keyValues;
    }

}
