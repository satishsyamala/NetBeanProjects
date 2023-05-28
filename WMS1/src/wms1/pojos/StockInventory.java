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

/**
 *
 * @author Aadhya
 */
@Entity
@Table(name = "STOCK_INVENTORY",schema = "WMS")
public class StockInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STOCK_INVENTORY")
    @SequenceGenerator(name = "SEQ_STOCK_INVENTORY", sequenceName = "SEQ_STOCK_INVENTORY", allocationSize = 1)
    @Column(name = "STOCK_INVENTORY_ID")
    private long stockInventoryId;
   @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEMS_ID")
    private Items items;
    @Column(name = "BIN_LOCATION")
    private String binLocation;
    @Column(name = "QUANTITY")
    private int quantity;

    public long getStockInventoryId() {
        return stockInventoryId;
    }

    public void setStockInventoryId(long stockInventoryId) {
        this.stockInventoryId = stockInventoryId;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public String getBinLocation() {
        return binLocation;
    }

    public void setBinLocation(String binLocation) {
        this.binLocation = binLocation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
