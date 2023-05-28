/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.pojos;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
@Table(name = "USERS",schema = "WMS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERS")
    @SequenceGenerator(name = "SEQ_USERS", sequenceName = "SEQ_USERS", allocationSize = 1)
    @Column(name = "USER_ID")
    private long userId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "MOBILE_NO")
    private String mobileNo;
    @Column(name = "USER_TYPE")
    private String userType;
    @Column(name = "LAST_LOGIN_TIME")
    private Date lastLoginTime;
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
    private Map<String,Object> keyValues;
    @Transient
    private Date tempLastLoginTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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

    public Map<String, Object> getKeyValues() {
     
        keyValues=new HashMap<>();
        keyValues.put("User Full Name",this.fullName);
        keyValues.put("User Id",this.userName);
        keyValues.put("Password",this.password);
        keyValues.put("Mobile Number",this.mobileNo);
        keyValues.put("User Type",this.userType);
         return keyValues;
    }

    public void setKeyValues(Map<String, Object> keyValues) {
        this.keyValues = keyValues;
    }

    public Date getTempLastLoginTime() {
        return tempLastLoginTime;
    }

    public void setTempLastLoginTime(Date tempLastLoginTime) {
        this.tempLastLoginTime = tempLastLoginTime;
    }
    
    

}
