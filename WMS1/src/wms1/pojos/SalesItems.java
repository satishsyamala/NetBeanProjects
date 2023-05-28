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
import javax.persistence.FetchType;
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
@Table(name = "SALES_ITEMS", schema = "WMS")
public class SalesItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SALES_ITEMS")
    @SequenceGenerator(name = "SEQ_SALES_ITEMS", sequenceName = "SEQ_SALES_ITEMS", allocationSize = 1)
    @Column(name = "SALES_ITEM_ID")
    private long salesItemId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SALES_ID")
    private Sales sales;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEMS_ID")
    private Items items;
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
    @Column(name = "ITEM_NUMBER")
    private String itemNumber;
    
    @Transient
    private int srNo;

    public long getSalesItemId() {
        return salesItemId;
    }

    public void setSalesItemId(long salesItemId) {
        this.salesItemId = salesItemId;
    }

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
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

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

   

}
