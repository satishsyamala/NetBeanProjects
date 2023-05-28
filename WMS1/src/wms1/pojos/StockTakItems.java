/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.pojos;

/**
 *
 * @author Aadhya
 */
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

@Entity
@Table(name = "STOCK_TAKE_ITEMS", schema = "WMS")
public class StockTakItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STOCK_TAKE_ITEMS")
    @SequenceGenerator(name = "SEQ_STOCK_TAKE_ITEMS", sequenceName = "SEQ_STOCK_TAKE_ITEMS", allocationSize = 1)
    @Column(name = "STOCK_TAKE_ITEMS_ID")
    private long stockTakeItemId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STOCK_TAKE_ID")
    private StockTake stockTake;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEMS_ID")
    private Items items;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "INV_QUANTITY")
    private int invQuantity;
    @Column(name = "ADJ_QUANTITY")
    private int adjQuantity;

    public long getStockTakeItemId() {
        return stockTakeItemId;
    }

    public void setStockTakeItemId(long stockTakeItemId) {
        this.stockTakeItemId = stockTakeItemId;
    }

    public StockTake getStockTake() {
        return stockTake;
    }

    public void setStockTake(StockTake stockTake) {
        this.stockTake = stockTake;
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

    public int getInvQuantity() {
        return invQuantity;
    }

    public void setInvQuantity(int invQuantity) {
        this.invQuantity = invQuantity;
    }

    public int getAdjQuantity() {
        return adjQuantity;
    }

    public void setAdjQuantity(int adjQuantity) {
        this.adjQuantity = adjQuantity;
    }

}
