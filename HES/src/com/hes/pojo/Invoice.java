/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hes.pojo;

import java.util.Date;

/**
 *
 * @author SATISH SYAMALA
 */
public class Invoice {

    private int invoiceId;
    private int invoiceType;
    private String invoiceNumber;
    private boolean exciseDuty;
    private Date invoiceDate;
    private int proDiscount;
    private int proOtherCharges;
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
    private double grandTotal;
    private double finalDiscount;
    private double finalexciseDuty;
    private double finaleOtherCharges;
    private double finaleVatOrCst;

    private String invoiceTime;
    private String removalOfGoodsTime;
    private String splrInvoiceTime;

    private String custname;
    private String custaddress;
    private double custdiscount;
    private String custtinNumber;
    private String custphnNumber;
    private String custlandLine;
    private String custdiscription;
    private String custrangeAddress;
    private String custdivision;
    private String custcommissionerate;
    private String custcstNumber;
    private String custcerNo;
    private String custeccNo;
    private String custdebitEntryNo;

    private String splrname;
    private String splraddress;
    private double splrdiscount;
    private String splrtinNumber;
    private String splrphnNumber;
    private String splrlandLine;
    private String splrdiscription;
    private String splrrangeAddress;
    private String splrdivision;
    private String splrcommissionerate;
    private String splrcstNumber;
    private String splrcerNo;
    private String splreccNo;
    private String splrdebitEntryNo;


    private double exciseDutyper;
    private double eductionCess;
    private double SandHEduCess;
    private double cst;
    private double vat;

    /**
     * @return the invoiceId
     */
    public int getInvoiceId() {
        return invoiceId;
    }

    /**
     * @param invoiceId the invoiceId to set
     */
    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

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
     * @return the custname
     */
    public String getCustname() {
        return custname;
    }

    /**
     * @param custname the custname to set
     */
    public void setCustname(String custname) {
        this.custname = custname;
    }

    /**
     * @return the custaddress
     */
    public String getCustaddress() {
        return custaddress;
    }

    /**
     * @param custaddress the custaddress to set
     */
    public void setCustaddress(String custaddress) {
        this.custaddress = custaddress;
    }

    /**
     * @return the custdiscount
     */
    public double getCustdiscount() {
        return custdiscount;
    }

    /**
     * @param custdiscount the custdiscount to set
     */
    public void setCustdiscount(double custdiscount) {
        this.custdiscount = custdiscount;
    }

    /**
     * @return the custtinNumber
     */
    public String getCusttinNumber() {
        return custtinNumber;
    }

    /**
     * @param custtinNumber the custtinNumber to set
     */
    public void setCusttinNumber(String custtinNumber) {
        this.custtinNumber = custtinNumber;
    }

    /**
     * @return the custphnNumber
     */
    public String getCustphnNumber() {
        return custphnNumber;
    }

    /**
     * @param custphnNumber the custphnNumber to set
     */
    public void setCustphnNumber(String custphnNumber) {
        this.custphnNumber = custphnNumber;
    }

    /**
     * @return the custlandLine
     */
    public String getCustlandLine() {
        return custlandLine;
    }

    /**
     * @param custlandLine the custlandLine to set
     */
    public void setCustlandLine(String custlandLine) {
        this.custlandLine = custlandLine;
    }

    /**
     * @return the custdiscription
     */
    public String getCustdiscription() {
        return custdiscription;
    }

    /**
     * @param custdiscription the custdiscription to set
     */
    public void setCustdiscription(String custdiscription) {
        this.custdiscription = custdiscription;
    }

    /**
     * @return the custrangeAddress
     */
    public String getCustrangeAddress() {
        return custrangeAddress;
    }

    /**
     * @param custrangeAddress the custrangeAddress to set
     */
    public void setCustrangeAddress(String custrangeAddress) {
        this.custrangeAddress = custrangeAddress;
    }

    /**
     * @return the custdivision
     */
    public String getCustdivision() {
        return custdivision;
    }

    /**
     * @param custdivision the custdivision to set
     */
    public void setCustdivision(String custdivision) {
        this.custdivision = custdivision;
    }

    /**
     * @return the custcommissionerate
     */
    public String getCustcommissionerate() {
        return custcommissionerate;
    }

    /**
     * @param custcommissionerate the custcommissionerate to set
     */
    public void setCustcommissionerate(String custcommissionerate) {
        this.custcommissionerate = custcommissionerate;
    }

    /**
     * @return the custcstNumber
     */
    public String getCustcstNumber() {
        return custcstNumber;
    }

    /**
     * @param custcstNumber the custcstNumber to set
     */
    public void setCustcstNumber(String custcstNumber) {
        this.custcstNumber = custcstNumber;
    }

    /**
     * @return the custcerNo
     */
    public String getCustcerNo() {
        return custcerNo;
    }

    /**
     * @param custcerNo the custcerNo to set
     */
    public void setCustcerNo(String custcerNo) {
        this.custcerNo = custcerNo;
    }

    /**
     * @return the custeccNo
     */
    public String getCusteccNo() {
        return custeccNo;
    }

    /**
     * @param custeccNo the custeccNo to set
     */
    public void setCusteccNo(String custeccNo) {
        this.custeccNo = custeccNo;
    }

    /**
     * @return the custdebitEntryNo
     */
    public String getCustdebitEntryNo() {
        return custdebitEntryNo;
    }

    /**
     * @param custdebitEntryNo the custdebitEntryNo to set
     */
    public void setCustdebitEntryNo(String custdebitEntryNo) {
        this.custdebitEntryNo = custdebitEntryNo;
    }

    /**
     * @return the splrname
     */
    public String getSplrname() {
        return splrname;
    }

    /**
     * @param splrname the splrname to set
     */
    public void setSplrname(String splrname) {
        this.splrname = splrname;
    }

    /**
     * @return the splraddress
     */
    public String getSplraddress() {
        return splraddress;
    }

    /**
     * @param splraddress the splraddress to set
     */
    public void setSplraddress(String splraddress) {
        this.splraddress = splraddress;
    }

    /**
     * @return the splrdiscount
     */
    public double getSplrdiscount() {
        return splrdiscount;
    }

    /**
     * @param splrdiscount the splrdiscount to set
     */
    public void setSplrdiscount(double splrdiscount) {
        this.splrdiscount = splrdiscount;
    }

    /**
     * @return the splrtinNumber
     */
    public String getSplrtinNumber() {
        return splrtinNumber;
    }

    /**
     * @param splrtinNumber the splrtinNumber to set
     */
    public void setSplrtinNumber(String splrtinNumber) {
        this.splrtinNumber = splrtinNumber;
    }

    /**
     * @return the splrphnNumber
     */
    public String getSplrphnNumber() {
        return splrphnNumber;
    }

    /**
     * @param splrphnNumber the splrphnNumber to set
     */
    public void setSplrphnNumber(String splrphnNumber) {
        this.splrphnNumber = splrphnNumber;
    }

    /**
     * @return the splrlandLine
     */
    public String getSplrlandLine() {
        return splrlandLine;
    }

    /**
     * @param splrlandLine the splrlandLine to set
     */
    public void setSplrlandLine(String splrlandLine) {
        this.splrlandLine = splrlandLine;
    }

    /**
     * @return the splrdiscription
     */
    public String getSplrdiscription() {
        return splrdiscription;
    }

    /**
     * @param splrdiscription the splrdiscription to set
     */
    public void setSplrdiscription(String splrdiscription) {
        this.splrdiscription = splrdiscription;
    }

    /**
     * @return the splrrangeAddress
     */
    public String getSplrrangeAddress() {
        return splrrangeAddress;
    }

    /**
     * @param splrrangeAddress the splrrangeAddress to set
     */
    public void setSplrrangeAddress(String splrrangeAddress) {
        this.splrrangeAddress = splrrangeAddress;
    }

    /**
     * @return the splrdivision
     */
    public String getSplrdivision() {
        return splrdivision;
    }

    /**
     * @param splrdivision the splrdivision to set
     */
    public void setSplrdivision(String splrdivision) {
        this.splrdivision = splrdivision;
    }

    /**
     * @return the splrcommissionerate
     */
    public String getSplrcommissionerate() {
        return splrcommissionerate;
    }

    /**
     * @param splrcommissionerate the splrcommissionerate to set
     */
    public void setSplrcommissionerate(String splrcommissionerate) {
        this.splrcommissionerate = splrcommissionerate;
    }

    /**
     * @return the splrcstNumber
     */
    public String getSplrcstNumber() {
        return splrcstNumber;
    }

    /**
     * @param splrcstNumber the splrcstNumber to set
     */
    public void setSplrcstNumber(String splrcstNumber) {
        this.splrcstNumber = splrcstNumber;
    }

    /**
     * @return the splrcerNo
     */
    public String getSplrcerNo() {
        return splrcerNo;
    }

    /**
     * @param splrcerNo the splrcerNo to set
     */
    public void setSplrcerNo(String splrcerNo) {
        this.splrcerNo = splrcerNo;
    }

    /**
     * @return the splreccNo
     */
    public String getSplreccNo() {
        return splreccNo;
    }

    /**
     * @param splreccNo the splreccNo to set
     */
    public void setSplreccNo(String splreccNo) {
        this.splreccNo = splreccNo;
    }

    /**
     * @return the splrdebitEntryNo
     */
    public String getSplrdebitEntryNo() {
        return splrdebitEntryNo;
    }

    /**
     * @param splrdebitEntryNo the splrdebitEntryNo to set
     */
    public void setSplrdebitEntryNo(String splrdebitEntryNo) {
        this.splrdebitEntryNo = splrdebitEntryNo;
    }

    /**
     * @return the exciseDutyper
     */
    public double getExciseDutyper() {
        return exciseDutyper;
    }

    /**
     * @param exciseDutyper the exciseDutyper to set
     */
    public void setExciseDutyper(double exciseDutyper) {
        this.exciseDutyper = exciseDutyper;
    }

    /**
     * @return the eductionCess
     */
    public double getEductionCess() {
        return eductionCess;
    }

    /**
     * @param eductionCess the eductionCess to set
     */
    public void setEductionCess(double eductionCess) {
        this.eductionCess = eductionCess;
    }

    /**
     * @return the SandHEduCess
     */
    public double getSandHEduCess() {
        return SandHEduCess;
    }

    /**
     * @param SandHEduCess the SandHEduCess to set
     */
    public void setSandHEduCess(double SandHEduCess) {
        this.SandHEduCess = SandHEduCess;
    }

    /**
     * @return the cst
     */
    public double getCst() {
        return cst;
    }

    /**
     * @param cst the cst to set
     */
    public void setCst(double cst) {
        this.cst = cst;
    }

    /**
     * @return the vat
     */
    public double getVat() {
        return vat;
    }

    /**
     * @param vat the vat to set
     */
    public void setVat(double vat) {
        this.vat = vat;
    }

    
    
}
