/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.pojos;

import java.util.Date;
import java.util.List;
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
@Table(name = "SERVICES",schema = "WMS")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SERVICES")
    @SequenceGenerator(name = "SEQ_SERVICES", sequenceName = "SEQ_SERVICES", allocationSize = 1)
    @Column(name = "SERVICE_ID")
    private long serviceId;
    @Column(name = "SERVICE_NO")
    private String serviceNo;
    @Column(name = "PRODUCT_CODE")
    private String productCode;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "CUST_MOBILE_NO")
    private String custMobileNo;
    @Column(name = "CUST_NAME")
    private String custName;
    @Column(name = "CUST_ADDRESS")
    private String custAddress;
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "EST_AMOUNT")
    private double estAmount;
    @Column(name = "AMOUNT")
    private double amount;
    @Column(name = "DISCOUNT")
    private double discount;
    @Column(name = "TAX")
    private double tax;
    @Column(name = "TOTAL_AMOUNT")
    private double totalAmount;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CREATED_BY")
    private long createdBy;
    @Column(name = "CREATED_ON")
    private Date createdOn;
    @Column(name = "CLOSED_BY")
    private long closedBy;
    @Column(name = "CLOSED_ON")
    private Date closeddOn;
    @Column(name = "UPDATED_BY")
    private long updatedBy;
    @Column(name = "UPDATED_ON")
    private Date updatedOn;
    @Transient
    private List<ServiceItems> serviceItemses;

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustMobileNo() {
        return custMobileNo;
    }

    public void setCustMobileNo(String custMobileNo) {
        this.custMobileNo = custMobileNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getEstAmount() {
        return estAmount;
    }

    public void setEstAmount(double estAmount) {
        this.estAmount = estAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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

    public long getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(long closedBy) {
        this.closedBy = closedBy;
    }

    public Date getCloseddOn() {
        return closeddOn;
    }

    public void setCloseddOn(Date closeddOn) {
        this.closeddOn = closeddOn;
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

    public List<ServiceItems> getServiceItemses() {
        return serviceItemses;
    }

    public void setServiceItemses(List<ServiceItems> serviceItemses) {
        this.serviceItemses = serviceItemses;
    }
    
    

}
