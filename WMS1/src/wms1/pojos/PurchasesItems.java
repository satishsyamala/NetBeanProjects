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
@Table(name = "PURCHASE_ITEMS", schema = "WMS")
public class PurchasesItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PURCHASE_ITEMS")
    @SequenceGenerator(name = "SEQ_PURCHASE_ITEMS", sequenceName = "SEQ_PURCHASE_ITEMS", allocationSize = 1)
    @Column(name = "PURCHASE_ITEM_ID")
    private long purchaseItemId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PURCHASE_ID")
    private Purchases purchases;
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
    @Transient
    private int srNo;

    public long getPurchaseItemId() {
        return purchaseItemId;
    }

    public void setPurchaseItemId(long purchaseItemId) {
        this.purchaseItemId = purchaseItemId;
    }

    public Purchases getPurchases() {
        return purchases;
    }

    public void setPurchases(Purchases purchases) {
        this.purchases = purchases;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
