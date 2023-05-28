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
@Table(name = "PURCHASES",schema = "WMS")
public class Purchases {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PURCHASES")
    @SequenceGenerator(name = "SEQ_PURCHASES", sequenceName = "SEQ_PURCHASES", allocationSize = 1)
    @Column(name = "PURCHASE_ID")
    private long purchaseId;
    @Column(name = "PURCHASE_NO")
    private String purchaseNo;
    @Column(name = "REMARKS")
    private String remark;
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
    private List<PurchasesItems> purchaseItems;

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public List<PurchasesItems> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchasesItems> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }
    
    
}
