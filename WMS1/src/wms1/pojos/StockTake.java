/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.pojos;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Aadhya
 */
@Entity
@Table(name = "STOCK_TAKE",schema = "WMS")
public class StockTake {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STOCK_TAKE")
    @SequenceGenerator(name = "SEQ_STOCK_TAKE", sequenceName = "SEQ_STOCK_TAKE", allocationSize = 1)
    @Column(name = "STOCK_TAKE_ID")
    private long stockTakeId;
    @Column(name = "STOCK_TAKE_ON")
    private Date stockTakedOn;
    @Column(name = "PREVIOUS_ST_ID")
    private long previousSTId;
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
    @Transient
    private List<StockTakAdj> stockTakAdjs;

    public long getStockTakeId() {
        return stockTakeId;
    }

    public void setStockTakeId(long stockTakeId) {
        this.stockTakeId = stockTakeId;
    }

    public Date getStockTakedOn() {
        return stockTakedOn;
    }

    public void setStockTakedOn(Date stockTakedOn) {
        this.stockTakedOn = stockTakedOn;
    }

    public long getPreviousSTId() {
        return previousSTId;
    }

    public void setPreviousSTId(long previousSTId) {
        this.previousSTId = previousSTId;
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

    public List<StockTakAdj> getStockTakAdjs() {
        return stockTakAdjs;
    }

    public void setStockTakAdjs(List<StockTakAdj> stockTakAdjs) {
        this.stockTakAdjs = stockTakAdjs;
    }
    
    
}
