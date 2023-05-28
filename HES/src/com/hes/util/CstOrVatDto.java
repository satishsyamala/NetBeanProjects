/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hes.util;

import java.util.Date;
import java.util.List;

/**
 *
 * @author SATISH SYAMALA
 */
public class CstOrVatDto {

    private String invoiceNo;
    private Date invoiceDate;
    private String custName;
    private String custAddress;
    private String custTINNumber;
    private String cstNumber;
    private String custPhnNumber;
    private String custandLine;
    private String orderNo;
    private Date orderDate;
    private String OurDCNo;
    private Date outDcDate;
    private String despatchedFrom;
    private String despatchedTo;
    private String PayTo;
    private String RRorLRNo;
    private Date payDate;
    private String payRs;
    private String documentThrough;
    private double custDiscount;
    private boolean Tfce;
    private boolean cstOrvat;
    private String supplierName;
    private String supplierAddress;
    private double supplierDiscount;
    private String supplierTINNumber;
    private String supplierPhnNumber;
    private String supplierLandLine;
    private String companyName;
    private String companyAddress;
    private String companyPhno;
    private String companyLandine;
    private String custrangeFullAddress;
    private String custDivision;
    private String commissionerate;
    private String custCERno;
    private String custECCNo;
    private String supprangeFullAddress;
    private String suppDivision;
    private String suppCERno;
    private String appECCNo;
    private String supcommissionerate;
    private List<ProductDto> productDtos;

    /**
     * @return the invoiceNo
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * @param invoiceNo the invoiceNo to set
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * @return the invoiceDate
     */
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * @param invoiceDate the invoiceDate to set
     */
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * @return the custName
     */
    public String getCustName() {
        return custName;
    }

    /**
     * @param custName the custName to set
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * @return the custAddress
     */
    public String getCustAddress() {
        return custAddress;
    }

    /**
     * @param custAddress the custAddress to set
     */
    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    /**
     * @return the custTINNumber
     */
    public String getCustTINNumber() {
        return custTINNumber;
    }

    /**
     * @param custTINNumber the custTINNumber to set
     */
    public void setCustTINNumber(String custTINNumber) {
        this.custTINNumber = custTINNumber;
    }

    /**
     * @return the cstNumber
     */
    public String getCstNumber() {
        return cstNumber;
    }

    /**
     * @param cstNumber the cstNumber to set
     */
    public void setCstNumber(String cstNumber) {
        this.cstNumber = cstNumber;
    }

    /**
     * @return the custPhnNumber
     */
    public String getCustPhnNumber() {
        return custPhnNumber;
    }

    /**
     * @param custPhnNumber the custPhnNumber to set
     */
    public void setCustPhnNumber(String custPhnNumber) {
        this.custPhnNumber = custPhnNumber;
    }

    /**
     * @return the custandLine
     */
    public String getCustandLine() {
        return custandLine;
    }

    /**
     * @param custandLine the custandLine to set
     */
    public void setCustandLine(String custandLine) {
        this.custandLine = custandLine;
    }

    /**
     * @return the orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo the orderNo to set
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the OurDCNo
     */
    public String getOurDCNo() {
        return OurDCNo;
    }

    /**
     * @param OurDCNo the OurDCNo to set
     */
    public void setOurDCNo(String OurDCNo) {
        this.OurDCNo = OurDCNo;
    }

    /**
     * @return the outDcDate
     */
    public Date getOutDcDate() {
        return outDcDate;
    }

    /**
     * @param outDcDate the outDcDate to set
     */
    public void setOutDcDate(Date outDcDate) {
        this.outDcDate = outDcDate;
    }

    /**
     * @return the despatchedFrom
     */
    public String getDespatchedFrom() {
        return despatchedFrom;
    }

    /**
     * @param despatchedFrom the despatchedFrom to set
     */
    public void setDespatchedFrom(String despatchedFrom) {
        this.despatchedFrom = despatchedFrom;
    }

    /**
     * @return the despatchedTo
     */
    public String getDespatchedTo() {
        return despatchedTo;
    }

    /**
     * @param despatchedTo the despatchedTo to set
     */
    public void setDespatchedTo(String despatchedTo) {
        this.despatchedTo = despatchedTo;
    }

    /**
     * @return the PayTo
     */
    public String getPayTo() {
        return PayTo;
    }

    /**
     * @param PayTo the PayTo to set
     */
    public void setPayTo(String PayTo) {
        this.PayTo = PayTo;
    }

    /**
     * @return the RRorLRNo
     */
    public String getRRorLRNo() {
        return RRorLRNo;
    }

    /**
     * @param RRorLRNo the RRorLRNo to set
     */
    public void setRRorLRNo(String RRorLRNo) {
        this.RRorLRNo = RRorLRNo;
    }

    /**
     * @return the payDate
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * @param payDate the payDate to set
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * @return the payRs
     */
    public String getPayRs() {
        return payRs;
    }

    /**
     * @param payRs the payRs to set
     */
    public void setPayRs(String payRs) {
        this.payRs = payRs;
    }

    /**
     * @return the documentThrough
     */
    public String getDocumentThrough() {
        return documentThrough;
    }

    /**
     * @param documentThrough the documentThrough to set
     */
    public void setDocumentThrough(String documentThrough) {
        this.documentThrough = documentThrough;
    }

    /**
     * @return the custDiscount
     */
    public double getCustDiscount() {
        return custDiscount;
    }

    /**
     * @param custDiscount the custDiscount to set
     */
    public void setCustDiscount(double custDiscount) {
        this.custDiscount = custDiscount;
    }

    /**
     * @return the Tfce
     */
    public boolean isTfce() {
        return Tfce;
    }

    /**
     * @param Tfce the Tfce to set
     */
    public void setTfce(boolean Tfce) {
        this.Tfce = Tfce;
    }

    /**
     * @return the cstOrvat
     */
    public boolean isCstOrvat() {
        return cstOrvat;
    }

    /**
     * @param cstOrvat the cstOrvat to set
     */
    public void setCstOrvat(boolean cstOrvat) {
        this.cstOrvat = cstOrvat;
    }

    /**
     * @return the supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @return the supplierAddress
     */
    public String getSupplierAddress() {
        return supplierAddress;
    }

    /**
     * @param supplierAddress the supplierAddress to set
     */
    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    /**
     * @return the supplierDiscount
     */
    public double getSupplierDiscount() {
        return supplierDiscount;
    }

    /**
     * @param supplierDiscount the supplierDiscount to set
     */
    public void setSupplierDiscount(double supplierDiscount) {
        this.supplierDiscount = supplierDiscount;
    }

    /**
     * @return the supplierTINNumber
     */
    public String getSupplierTINNumber() {
        return supplierTINNumber;
    }

    /**
     * @param supplierTINNumber the supplierTINNumber to set
     */
    public void setSupplierTINNumber(String supplierTINNumber) {
        this.supplierTINNumber = supplierTINNumber;
    }

    /**
     * @return the supplierPhnNumber
     */
    public String getSupplierPhnNumber() {
        return supplierPhnNumber;
    }

    /**
     * @param supplierPhnNumber the supplierPhnNumber to set
     */
    public void setSupplierPhnNumber(String supplierPhnNumber) {
        this.supplierPhnNumber = supplierPhnNumber;
    }

    /**
     * @return the supplierLandLine
     */
    public String getSupplierLandLine() {
        return supplierLandLine;
    }

    /**
     * @param supplierLandLine the supplierLandLine to set
     */
    public void setSupplierLandLine(String supplierLandLine) {
        this.supplierLandLine = supplierLandLine;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the companyAddress
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * @param companyAddress the companyAddress to set
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    /**
     * @return the companyPhno
     */
    public String getCompanyPhno() {
        return companyPhno;
    }

    /**
     * @param companyPhno the companyPhno to set
     */
    public void setCompanyPhno(String companyPhno) {
        this.companyPhno = companyPhno;
    }

    /**
     * @return the companyLandine
     */
    public String getCompanyLandine() {
        return companyLandine;
    }

    /**
     * @param companyLandine the companyLandine to set
     */
    public void setCompanyLandine(String companyLandine) {
        this.companyLandine = companyLandine;
    }

    /**
     * @return the custrangeFullAddress
     */
    public String getCustrangeFullAddress() {
        return custrangeFullAddress;
    }

    /**
     * @param custrangeFullAddress the custrangeFullAddress to set
     */
    public void setCustrangeFullAddress(String custrangeFullAddress) {
        this.custrangeFullAddress = custrangeFullAddress;
    }

    /**
     * @return the custDivision
     */
    public String getCustDivision() {
        return custDivision;
    }

    /**
     * @param custDivision the custDivision to set
     */
    public void setCustDivision(String custDivision) {
        this.custDivision = custDivision;
    }

    /**
     * @return the commissionerate
     */
    public String getCommissionerate() {
        return commissionerate;
    }

    /**
     * @param commissionerate the commissionerate to set
     */
    public void setCommissionerate(String commissionerate) {
        this.commissionerate = commissionerate;
    }

    /**
     * @return the custCERno
     */
    public String getCustCERno() {
        return custCERno;
    }

    /**
     * @param custCERno the custCERno to set
     */
    public void setCustCERno(String custCERno) {
        this.custCERno = custCERno;
    }

    /**
     * @return the custECCNo
     */
    public String getCustECCNo() {
        return custECCNo;
    }

    /**
     * @param custECCNo the custECCNo to set
     */
    public void setCustECCNo(String custECCNo) {
        this.custECCNo = custECCNo;
    }

    /**
     * @return the supprangeFullAddress
     */
    public String getSupprangeFullAddress() {
        return supprangeFullAddress;
    }

    /**
     * @param supprangeFullAddress the supprangeFullAddress to set
     */
    public void setSupprangeFullAddress(String supprangeFullAddress) {
        this.supprangeFullAddress = supprangeFullAddress;
    }

    /**
     * @return the suppDivision
     */
    public String getSuppDivision() {
        return suppDivision;
    }

    /**
     * @param suppDivision the suppDivision to set
     */
    public void setSuppDivision(String suppDivision) {
        this.suppDivision = suppDivision;
    }

    /**
     * @return the suppCERno
     */
    public String getSuppCERno() {
        return suppCERno;
    }

    /**
     * @param suppCERno the suppCERno to set
     */
    public void setSuppCERno(String suppCERno) {
        this.suppCERno = suppCERno;
    }

    /**
     * @return the appECCNo
     */
    public String getAppECCNo() {
        return appECCNo;
    }

    /**
     * @param appECCNo the appECCNo to set
     */
    public void setAppECCNo(String appECCNo) {
        this.appECCNo = appECCNo;
    }

    /**
     * @return the supcommissionerate
     */
    public String getSupcommissionerate() {
        return supcommissionerate;
    }

    /**
     * @param supcommissionerate the supcommissionerate to set
     */
    public void setSupcommissionerate(String supcommissionerate) {
        this.supcommissionerate = supcommissionerate;
    }

    /**
     * @return the productDtos
     */
    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    /**
     * @param productDtos the productDtos to set
     */
    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }

    
}
