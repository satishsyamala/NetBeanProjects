/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hes.pojo;

/**
 *
 * @author SATISH SYAMALA
 */
public class OtherInvoiceData {

    private int otherInvoiceDataId;
    private Invoice invoice;
    private String discription;
    private String headingLine;
    private double amount;
    private int amtType;
    private String otherInfo;
    private int orderBy;

    /**
     * @return the otherInvoiceDataId
     */
    public int getOtherInvoiceDataId() {
        return otherInvoiceDataId;
    }

    /**
     * @param otherInvoiceDataId the otherInvoiceDataId to set
     */
    public void setOtherInvoiceDataId(int otherInvoiceDataId) {
        this.otherInvoiceDataId = otherInvoiceDataId;
    }

    /**
     * @return the invoice
     */
    public Invoice getInvoice() {
        return invoice;
    }

    /**
     * @param invoice the invoice to set
     */
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    /**
     * @return the discription
     */
    public String getDiscription() {
        return discription;
    }

    /**
     * @param discription the discription to set
     */
    public void setDiscription(String discription) {
        this.discription = discription;
    }

    /**
     * @return the headingLine
     */
    public String getHeadingLine() {
        return headingLine;
    }

    /**
     * @param headingLine the headingLine to set
     */
    public void setHeadingLine(String headingLine) {
        this.headingLine = headingLine;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the amtType
     */
    public int getAmtType() {
        return amtType;
    }

    /**
     * @param amtType the amtType to set
     */
    public void setAmtType(int amtType) {
        this.amtType = amtType;
    }

    /**
     * @return the otherInfo
     */
    public String getOtherInfo() {
        return otherInfo;
    }

    /**
     * @param otherInfo the otherInfo to set
     */
    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    /**
     * @return the orderBy
     */
    public int getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    




}
