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
public class EndorsementDto {

     private String invoiceNo;
     private Date invoiceDate;
    private String custName;
    private String custAddress;
    private String custTINNumber;
    private String cstNumber;
    private String custPhnNumber;
    private String custandLine;
    private String PONo;
    private Date   PODate;
    private String orginalLorryReceipt;
    private Date lorryReceiptDate;
    private String despatchedFrom;
    private String despatchedTo;
    private String endorsedTo;
    private String beckIndiaInvoiceNo;
    private Date beckIndiaInvoiceDate;
     private String beckIndiaInvoicBy;
    private String payRs;
    private String documentThrough;
    private double custDiscount;
    private boolean Tfce;
     private String supplierName;
    private String supplierAddress;
    private double supplierDiscount;
    private String supplierTINNumber;
    private String supplierPhnNumber;
    private String supplierLandLine;
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
     * @return the PONo
     */
    public String getPONo() {
        return PONo;
    }

    /**
     * @param PONo the PONo to set
     */
    public void setPONo(String PONo) {
        this.PONo = PONo;
    }

    /**
     * @return the PODate
     */
    public Date getPODate() {
        return PODate;
    }

    /**
     * @param PODate the PODate to set
     */
    public void setPODate(Date PODate) {
        this.PODate = PODate;
    }

    /**
     * @return the orginalLorryReceipt
     */
    public String getOrginalLorryReceipt() {
        return orginalLorryReceipt;
    }

    /**
     * @param orginalLorryReceipt the orginalLorryReceipt to set
     */
    public void setOrginalLorryReceipt(String orginalLorryReceipt) {
        this.orginalLorryReceipt = orginalLorryReceipt;
    }

    /**
     * @return the lorryReceiptDate
     */
    public Date getLorryReceiptDate() {
        return lorryReceiptDate;
    }

    /**
     * @param lorryReceiptDate the lorryReceiptDate to set
     */
    public void setLorryReceiptDate(Date lorryReceiptDate) {
        this.lorryReceiptDate = lorryReceiptDate;
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
     * @return the endorsedTo
     */
    public String getEndorsedTo() {
        return endorsedTo;
    }

    /**
     * @param endorsedTo the endorsedTo to set
     */
    public void setEndorsedTo(String endorsedTo) {
        this.endorsedTo = endorsedTo;
    }

    /**
     * @return the beckIndiaInvoiceNo
     */
    public String getBeckIndiaInvoiceNo() {
        return beckIndiaInvoiceNo;
    }

    /**
     * @param beckIndiaInvoiceNo the beckIndiaInvoiceNo to set
     */
    public void setBeckIndiaInvoiceNo(String beckIndiaInvoiceNo) {
        this.beckIndiaInvoiceNo = beckIndiaInvoiceNo;
    }

    /**
     * @return the beckIndiaInvoiceDate
     */
    public Date getBeckIndiaInvoiceDate() {
        return beckIndiaInvoiceDate;
    }

    /**
     * @param beckIndiaInvoiceDate the beckIndiaInvoiceDate to set
     */
    public void setBeckIndiaInvoiceDate(Date beckIndiaInvoiceDate) {
        this.beckIndiaInvoiceDate = beckIndiaInvoiceDate;
    }

    /**
     * @return the beckIndiaInvoicBy
     */
    public String getBeckIndiaInvoicBy() {
        return beckIndiaInvoicBy;
    }

    /**
     * @param beckIndiaInvoicBy the beckIndiaInvoicBy to set
     */
    public void setBeckIndiaInvoicBy(String beckIndiaInvoicBy) {
        this.beckIndiaInvoicBy = beckIndiaInvoicBy;
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

    

}
