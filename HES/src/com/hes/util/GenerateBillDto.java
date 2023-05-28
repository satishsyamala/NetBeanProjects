/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hes.util;

import com.hes.pojo.BasicInformation;
import com.hes.pojo.Company;
import com.hes.pojo.Customer;
import com.hes.pojo.OtherInvoiceData;
import com.hes.pojo.Supplier;
import java.util.List;

/**
 *
 * @author SATISH SYAMALA
 */
public class GenerateBillDto {

    private int invoiceType;
    private String invoiceNumber;
    private boolean exciseDuty;
    private String invoiceDate;
    private Customer customer;
    private Supplier supplier;
    private int proDiscount;
    private int proOtherCharges;
    private List<ProductDto> productDtos;
    private double custDiscount;
    private int vatOrCst;
    private String orderNo;
    private String orderDate;
    private String ourDCNo;
    private String ourDCDate;
    private String dispachedFrom;
    private String dispachedTo;
    private String payByotMS;
    private String rrorLrNo;
    private String DispachedDate;
    private double payRs;
    private String documentThrough;
    private String poNo;
    private String poDate;
    private String beckInvNo;
    private String beckInvDate;
    private double beckInvAmount;
    private String exciseInvNo;
    private String dateOfRemovelGoods;
    private String enrtyPageNo;
    private String modeOfTrns;
    private String vehicleRegNo;
    private String suppInvBill;
    private int suppInvAmount;
    private Company company;
    private double grandTotal;
    private double finalDiscount;
    private double finalexciseDuty;
    private double finaleOtherCharges;
    private double finaleVatOrCst;

    private String invoiceTime;
    private String removalOfGoodsTime;
    private String splrInvoiceTime;
    private BasicInformation basicInformation;

    private String splrAddress;

    private List<OtherInvoiceData> otherInvoiceData;



    
    /**
     * @return the invoiceType
     */
    public int getInvoiceType() {
        return invoiceType;
    }

    /**
     * @param invoiceType the invoiceType to set
     */
    public void setInvoiceType(int invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * @return the exciseDuty
     */
    public boolean isExciseDuty() {
        return exciseDuty;
    }

    /**
     * @param exciseDuty the exciseDuty to set
     */
    public void setExciseDuty(boolean exciseDuty) {
        this.exciseDuty = exciseDuty;
    }

    /**
     * @return the invoiceDate
     */
    public String getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * @param invoiceDate the invoiceDate to set
     */
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the proDiscount
     */
    public int getProDiscount() {
        return proDiscount;
    }

    /**
     * @param proDiscount the proDiscount to set
     */
    public void setProDiscount(int proDiscount) {
        this.proDiscount = proDiscount;
    }

    /**
     * @return the proOtherCharges
     */
    public int getProOtherCharges() {
        return proOtherCharges;
    }

    /**
     * @param proOtherCharges the proOtherCharges to set
     */
    public void setProOtherCharges(int proOtherCharges) {
        this.proOtherCharges = proOtherCharges;
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
     * @return the vatOrCst
     */
    public int getVatOrCst() {
        return vatOrCst;
    }

    /**
     * @param vatOrCst the vatOrCst to set
     */
    public void setVatOrCst(int vatOrCst) {
        this.vatOrCst = vatOrCst;
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
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the ourDCNo
     */
    public String getOurDCNo() {
        return ourDCNo;
    }

    /**
     * @param ourDCNo the ourDCNo to set
     */
    public void setOurDCNo(String ourDCNo) {
        this.ourDCNo = ourDCNo;
    }

    /**
     * @return the ourDCDate
     */
    public String getOurDCDate() {
        return ourDCDate;
    }

    /**
     * @param ourDCDate the ourDCDate to set
     */
    public void setOurDCDate(String ourDCDate) {
        this.ourDCDate = ourDCDate;
    }

    /**
     * @return the dispachedFrom
     */
    public String getDispachedFrom() {
        return dispachedFrom;
    }

    /**
     * @param dispachedFrom the dispachedFrom to set
     */
    public void setDispachedFrom(String dispachedFrom) {
        this.dispachedFrom = dispachedFrom;
    }

    /**
     * @return the dispachedTo
     */
    public String getDispachedTo() {
        return dispachedTo;
    }

    /**
     * @param dispachedTo the dispachedTo to set
     */
    public void setDispachedTo(String dispachedTo) {
        this.dispachedTo = dispachedTo;
    }

    /**
     * @return the payByotMS
     */
    public String getPayByotMS() {
        return payByotMS;
    }

    /**
     * @param payByotMS the payByotMS to set
     */
    public void setPayByotMS(String payByotMS) {
        this.payByotMS = payByotMS;
    }

    /**
     * @return the rrorLrNo
     */
    public String getRrorLrNo() {
        return rrorLrNo;
    }

    /**
     * @param rrorLrNo the rrorLrNo to set
     */
    public void setRrorLrNo(String rrorLrNo) {
        this.rrorLrNo = rrorLrNo;
    }

    /**
     * @return the DispachedDate
     */
    public String getDispachedDate() {
        return DispachedDate;
    }

    /**
     * @param DispachedDate the DispachedDate to set
     */
    public void setDispachedDate(String DispachedDate) {
        this.DispachedDate = DispachedDate;
    }

    /**
     * @return the payRs
     */
    public double getPayRs() {
        return payRs;
    }

    /**
     * @param payRs the payRs to set
     */
    public void setPayRs(double payRs) {
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
     * @return the poNo
     */
    public String getPoNo() {
        return poNo;
    }

    /**
     * @param poNo the poNo to set
     */
    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    /**
     * @return the poDate
     */
    public String getPoDate() {
        return poDate;
    }

    /**
     * @param poDate the poDate to set
     */
    public void setPoDate(String poDate) {
        this.poDate = poDate;
    }

    /**
     * @return the beckInvNo
     */
    public String getBeckInvNo() {
        return beckInvNo;
    }

    /**
     * @param beckInvNo the beckInvNo to set
     */
    public void setBeckInvNo(String beckInvNo) {
        this.beckInvNo = beckInvNo;
    }

    /**
     * @return the beckInvDate
     */
    public String getBeckInvDate() {
        return beckInvDate;
    }

    /**
     * @param beckInvDate the beckInvDate to set
     */
    public void setBeckInvDate(String beckInvDate) {
        this.beckInvDate = beckInvDate;
    }

    /**
     * @return the beckInvAmount
     */
    public double getBeckInvAmount() {
        return beckInvAmount;
    }

    /**
     * @param beckInvAmount the beckInvAmount to set
     */
    public void setBeckInvAmount(double beckInvAmount) {
        this.beckInvAmount = beckInvAmount;
    }

    /**
     * @return the exciseInvNo
     */
    public String getExciseInvNo() {
        return exciseInvNo;
    }

    /**
     * @param exciseInvNo the exciseInvNo to set
     */
    public void setExciseInvNo(String exciseInvNo) {
        this.exciseInvNo = exciseInvNo;
    }

    /**
     * @return the dateOfRemovelGoods
     */
    public String getDateOfRemovelGoods() {
        return dateOfRemovelGoods;
    }

    /**
     * @param dateOfRemovelGoods the dateOfRemovelGoods to set
     */
    public void setDateOfRemovelGoods(String dateOfRemovelGoods) {
        this.dateOfRemovelGoods = dateOfRemovelGoods;
    }

    /**
     * @return the enrtyPageNo
     */
    public String getEnrtyPageNo() {
        return enrtyPageNo;
    }

    /**
     * @param enrtyPageNo the enrtyPageNo to set
     */
    public void setEnrtyPageNo(String enrtyPageNo) {
        this.enrtyPageNo = enrtyPageNo;
    }

    /**
     * @return the modeOfTrns
     */
    public String getModeOfTrns() {
        return modeOfTrns;
    }

    /**
     * @param modeOfTrns the modeOfTrns to set
     */
    public void setModeOfTrns(String modeOfTrns) {
        this.modeOfTrns = modeOfTrns;
    }

    /**
     * @return the vehicleRegNo
     */
    public String getVehicleRegNo() {
        return vehicleRegNo;
    }

    /**
     * @param vehicleRegNo the vehicleRegNo to set
     */
    public void setVehicleRegNo(String vehicleRegNo) {
        this.vehicleRegNo = vehicleRegNo;
    }

    /**
     * @return the suppInvBill
     */
    public String getSuppInvBill() {
        return suppInvBill;
    }

    /**
     * @param suppInvBill the suppInvBill to set
     */
    public void setSuppInvBill(String suppInvBill) {
        this.suppInvBill = suppInvBill;
    }

    /**
     * @return the suppInvAmount
     */
    public int getSuppInvAmount() {
        return suppInvAmount;
    }

    /**
     * @param suppInvAmount the suppInvAmount to set
     */
    public void setSuppInvAmount(int suppInvAmount) {
        this.suppInvAmount = suppInvAmount;
    }

    /**
     * @return the invoiceNumber
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * @param invoiceNumber the invoiceNumber to set
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * @return the grandTotal
     */
    public double getGrandTotal() {
        return grandTotal;
    }

    /**
     * @param grandTotal the grandTotal to set
     */
    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    /**
     * @return the finalDiscount
     */
    public double getFinalDiscount() {
        return finalDiscount;
    }

    /**
     * @param finalDiscount the finalDiscount to set
     */
    public void setFinalDiscount(double finalDiscount) {
        this.finalDiscount = finalDiscount;
    }

    /**
     * @return the finalexciseDuty
     */
    public double getFinalexciseDuty() {
        return finalexciseDuty;
    }

    /**
     * @param finalexciseDuty the finalexciseDuty to set
     */
    public void setFinalexciseDuty(double finalexciseDuty) {
        this.finalexciseDuty = finalexciseDuty;
    }

    /**
     * @return the finaleOtherCharges
     */
    public double getFinaleOtherCharges() {
        return finaleOtherCharges;
    }

    /**
     * @param finaleOtherCharges the finaleOtherCharges to set
     */
    public void setFinaleOtherCharges(double finaleOtherCharges) {
        this.finaleOtherCharges = finaleOtherCharges;
    }

    /**
     * @return the finaleVatOrCst
     */
    public double getFinaleVatOrCst() {
        return finaleVatOrCst;
    }

    /**
     * @param finaleVatOrCst the finaleVatOrCst to set
     */
    public void setFinaleVatOrCst(double finaleVatOrCst) {
        this.finaleVatOrCst = finaleVatOrCst;
    }

    /**
     * @return the basicInformation
     */
    public BasicInformation getBasicInformation() {
        return basicInformation;
    }

    /**
     * @param basicInformation the basicInformation to set
     */
    public void setBasicInformation(BasicInformation basicInformation) {
        this.basicInformation = basicInformation;
    }

    /**
     * @return the invoiceTime
     */
    public String getInvoiceTime() {
        return invoiceTime;
    }

    /**
     * @param invoiceTime the invoiceTime to set
     */
    public void setInvoiceTime(String invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    /**
     * @return the removalOfGoodsTime
     */
    public String getRemovalOfGoodsTime() {
        return removalOfGoodsTime;
    }

    /**
     * @param removalOfGoodsTime the removalOfGoodsTime to set
     */
    public void setRemovalOfGoodsTime(String removalOfGoodsTime) {
        this.removalOfGoodsTime = removalOfGoodsTime;
    }

    /**
     * @return the splrInvoiceTime
     */
    public String getSplrInvoiceTime() {
        return splrInvoiceTime;
    }

    /**
     * @param splrInvoiceTime the splrInvoiceTime to set
     */
    public void setSplrInvoiceTime(String splrInvoiceTime) {
        this.splrInvoiceTime = splrInvoiceTime;
    }

    /**
     * @return the splrAddress
     */
    public String getSplrAddress() {
        return splrAddress;
    }

    /**
     * @param splrAddress the splrAddress to set
     */
    public void setSplrAddress(String splrAddress) {
        this.splrAddress = splrAddress;
    }

    /**
     * @return the otherInvoiceData
     */
    public List<OtherInvoiceData> getOtherInvoiceData() {
        return otherInvoiceData;
    }

    /**
     * @param otherInvoiceData the otherInvoiceData to set
     */
    public void setOtherInvoiceData(List<OtherInvoiceData> otherInvoiceData) {
        this.otherInvoiceData = otherInvoiceData;
    }

    

}
