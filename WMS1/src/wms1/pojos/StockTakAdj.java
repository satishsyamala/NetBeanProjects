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

@Entity
@Table(name = "STOCK_TAKE_ADJ", schema = "WMS")
public class StockTakAdj {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STOCK_TAKE_ADJ")
    @SequenceGenerator(name = "SEQ_STOCK_TAKE_ADJ", sequenceName = "SEQ_STOCK_TAKE_ADJ", allocationSize = 1)
    @Column(name = "STOCK_TAKE_Adj_ID")
    private long stockTakeAdjId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STOCK_TAKE_ID")
    private StockTake stockTake;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEMS_ID")
    private Items items;
    @Column(name = "OPEN_BAL")
    private int openBal;
    @Column(name = "PURCHASES")
    private int purchases;
    @Column(name = "SALES")
    private int sales;
    @Column(name = "CLOSE_BAL")
    private int closeBal;
    @Column(name = "ST_QUANTITY")
    private int stQuantity;
    @Column(name = "ADJ_QUANTITY")
    private int adjQuantity;
    @Column(name = "REMARKS")
    private String remarks;
    
    @Transient
    private int srNo;

    public long getStockTakeAdjId() {
        return stockTakeAdjId;
    }

    public void setStockTakeAdjId(long stockTakeAdjId) {
        this.stockTakeAdjId = stockTakeAdjId;
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

    public int getOpenBal() {
        return openBal;
    }

    public void setOpenBal(int openBal) {
        this.openBal = openBal;
    }

    

    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getCloseBal() {
        return closeBal;
    }

    public void setCloseBal(int closeBal) {
        this.closeBal = closeBal;
    }

    public int getStQuantity() {
        return stQuantity;
    }

    public void setStQuantity(int stQuantity) {
        this.stQuantity = stQuantity;
    }

  

    public int getAdjQuantity() {
        return adjQuantity;
    }

    public void setAdjQuantity(int adjQuantity) {
        this.adjQuantity = adjQuantity;
    }

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    

}
