/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.pojos;

import java.util.Date;
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
@Table(name = "SERVICE_ITEMS",schema = "WMS")
public class ServiceItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SERVICE_ITEMS")
    @SequenceGenerator(name = "SEQ_SERVICE_ITEMS", sequenceName = "SEQ_SERVICE_ITEMS", allocationSize = 1)
    @Column(name = "SERVICE_ITEM_ID")
    private long serviceItemId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SERVICE_ID")
    private Services services;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SERVICE_TYPE_ID")
    private ServiceTypes serviceType;
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "AMOUNT")
    private double amount;
    @Column(name = "DISCOUNT")
    private double discount;
    @Column(name = "TAX")
    private double tax;
    @Column(name = "TOTAL_AMOUNT")
    private double totalAmount;
    
    @Transient
    private int srNo;

    public long getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(long serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public ServiceTypes getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceTypes serviceType) {
        this.serviceType = serviceType;
    }

  

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }
    
    
}
