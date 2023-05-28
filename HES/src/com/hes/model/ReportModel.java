/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hes.model;

import com.hes.pojo.BasicInformation;
import com.hes.pojo.Customer;
import com.hes.pojo.Invoice;
import com.hes.pojo.InvoiceProductMap;
import com.hes.pojo.MySessionFactory;
import com.hes.pojo.OtherInvoiceData;
import com.hes.pojo.Supplier;
import com.hes.util.CstOrVatInvoice;
import com.hes.util.Endorsement;
import com.hes.util.GenerateBillDto;
import com.hes.util.ProductDto;
import com.hes.util.TCEInvoice;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author SATISH SYAMALA
 */
public class ReportModel {

    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sd1 = new SimpleDateFormat("dd-MM-yyyy");

    public List<Customer> getCustomerList() {
        List<Customer> Customers = new ArrayList<Customer>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            Customers = session.createQuery("From Customer cm where cm.status='active'    order by cm.name").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Customers;
    }

    public List<Invoice> getInvoiceDetails(String custId, Date fromDate, Date toDate) {
        List<Invoice> invoices = new ArrayList<Invoice>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "from Invoice inv where inv.invoiceDate between '" + sd.format(fromDate) + "' and '" + sd.format(toDate) + "' ";
            if (custId.trim().length() > 0) {
                sql += " and inv.custname='" + custId + "'";
            }
            sql += "    order by inv.invoiceDate desc";
            invoices = session.createQuery(sql).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return invoices;

    }



    public String deleteInvoice(Invoice dto) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            List<Invoice> ls = session.createQuery("from Invoice inv where inv.invoiceNumber='" + dto.getInvoiceNumber() + "'").list();
            for (Invoice inv : ls) {
                List<InvoiceProductMap> ls1 = session.createQuery("from InvoiceProductMap imp where imp.invoice.invoiceNumber='" + dto.getInvoiceNumber() + "'").list();
                for (InvoiceProductMap ipm : ls1) {
                    session.delete(ipm);
                }
                List<OtherInvoiceData> invoiceDatas=session.createQuery("From OtherInvoiceData od where od.invoice.invoiceNumber='" + dto.getInvoiceNumber() + "'").list();
                for(OtherInvoiceData od:invoiceDatas){
                     session.delete(od);
                }
                session.delete(inv);
            }
            tr.commit();
            result = "ok";
        } catch (Exception e) {
            e.printStackTrace();
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public void generateBills(Invoice inv) {
        GenerateBillDto dto = addInvoice(inv);
        if (dto.getInvoiceType() == 1) {
            dto = CstOrVatInvoice.genBill(dto);
            if (dto.isExciseDuty()) {
                dto = TCEInvoice.genBill(dto);
            }
        } else {
            dto = Endorsement.genBill(dto);
        }
    }

    public GenerateBillDto addInvoice(Invoice dto) {
        Session session = null;
        GenerateBillDto in = null;
        try {
            session = MySessionFactory.getSession();
            in = new GenerateBillDto();
            in.setBeckInvAmount(dto.getBeckInvAmount());
            in.setBeckInvDate((dto.getBeckInvDate() != null) ? dto.getBeckInvDate() : "NA");
            in.setBeckInvNo((dto.getBeckInvNo() != null) ? dto.getBeckInvNo() : "NA");
            in.setCustDiscount(dto.getCustDiscount());

            Customer cst = new Customer();
            cst.setAddress(dto.getCustaddress());
            cst.setCerNo(dto.getCustcerNo());
            cst.setCommissionerate(dto.getCustcommissionerate());
            cst.setCstNumber(dto.getCustcstNumber());
            cst.setDebitEntryNo(dto.getCustdebitEntryNo());
            cst.setDiscount(dto.getCustdiscount());
            cst.setDiscription(dto.getCustdiscription());
            cst.setDivision(dto.getCustdivision());
            cst.setEccNo(dto.getCusteccNo());
            cst.setLandLine(dto.getCustlandLine());
            cst.setName(dto.getCustname());
            cst.setPhnNumber(dto.getCustphnNumber());
            cst.setRangeAddress(dto.getCustrangeAddress());
            cst.setTinNumber(dto.getCusttinNumber());
            in.setCustomer(cst);

            BasicInformation bi = new BasicInformation();
            bi.setCst(dto.getCst());
            bi.setVat(dto.getVat());
            bi.setEductionCess(dto.getEductionCess());
            bi.setExciseDuty(dto.getExciseDutyper());
            bi.setSandHEduCess(dto.getSandHEduCess());
            in.setBasicInformation(bi);

            in.setDateOfRemovelGoods((dto.getDateOfRemovelGoods() != null) ? dto.getDateOfRemovelGoods() : "NA");
            in.setDispachedDate((dto.getDispachedDate() != null) ? dto.getDispachedDate() : "NA");
            in.setDispachedFrom((dto.getDispachedFrom() != null) ? dto.getDispachedFrom() : "NA");
            in.setDispachedTo((dto.getDispachedTo() != null) ? dto.getDispachedTo() : "NA");
            in.setDocumentThrough((dto.getDocumentThrough() != null) ? dto.getDocumentThrough() : "NA");
            in.setEnrtyPageNo((dto.getEnrtyPageNo() != null) ? dto.getEnrtyPageNo() : "NA");
            in.setExciseDuty(dto.isExciseDuty());
            in.setExciseInvNo((dto.getExciseInvNo() != null) ? dto.getExciseInvNo() : "NA");
            in.setInvoiceDate(sd1.format(dto.getInvoiceDate()));
            in.setInvoiceNumber((dto.getInvoiceNumber() != null) ? dto.getInvoiceNumber() : "NA");
            in.setInvoiceType(dto.getInvoiceType());
            in.setModeOfTrns((dto.getModeOfTrns() != null) ? dto.getModeOfTrns() : "NA");
            in.setOrderDate((dto.getOrderDate() != null) ? dto.getOrderDate() : "NA");
            in.setOrderNo((dto.getOrderNo() != null) ? dto.getOrderNo() : "NA");
            in.setOurDCDate((dto.getOurDCDate() != null) ? dto.getOurDCDate() : "NA");
            in.setOurDCNo((dto.getOurDCNo() != null) ? dto.getOurDCNo() : "NA");
            in.setPayByotMS((dto.getPayByotMS() != null) ? dto.getPayByotMS() : "NA");
            in.setPayRs(dto.getPayRs());
            in.setPoDate((dto.getPoDate() != null) ? dto.getPoDate() : "NA");
            in.setPoNo((dto.getPoNo() != null) ? dto.getPoNo() : "NA");
            in.setProDiscount(dto.getProDiscount());
            in.setProOtherCharges(dto.getProOtherCharges());
            in.setRrorLrNo((dto.getRrorLrNo() != null) ? dto.getRrorLrNo() : "NA");
            in.setSuppInvAmount(dto.getSuppInvAmount());
            in.setSuppInvBill((dto.getSuppInvBill() != null) ? dto.getSuppInvBill() : "NA");

            Supplier splr = new Supplier();
            splr.setAddress(dto.getSplraddress());
            splr.setCerNo(dto.getSplrcerNo());
            splr.setCommissionerate(dto.getSplrcommissionerate());
            splr.setCstNumber(dto.getSplrcstNumber());
            splr.setDebitEntryNo(dto.getSplrdebitEntryNo());
            splr.setDiscount(dto.getSplrdiscount());
            splr.setDiscription(dto.getSplrdiscription());
            splr.setDivision(dto.getSplrdivision());
            splr.setEccNo(dto.getSplreccNo());
            splr.setLandLine(dto.getSplrlandLine());
            splr.setName(dto.getSplrname());
            splr.setPhnNumber(dto.getSplrphnNumber());
            splr.setRangeAddress(dto.getSplrrangeAddress());
            splr.setTinNumber(dto.getSplrtinNumber());

            in.setSupplier(splr);

            in.setInvoiceTime(dto.getInvoiceTime());
            in.setRemovalOfGoodsTime(dto.getRemovalOfGoodsTime());
            in.setSplrInvoiceTime(dto.getSplrInvoiceTime());

            in.setVatOrCst(dto.getVatOrCst());
            in.setVehicleRegNo((dto.getVehicleRegNo() != null) ? dto.getVehicleRegNo() : "NA");
            in.setFinalDiscount(dto.getFinalDiscount());
            in.setFinaleOtherCharges(dto.getFinaleOtherCharges());
            in.setFinalexciseDuty(dto.getFinalexciseDuty());
            in.setGrandTotal(dto.getGrandTotal());
            in.setFinaleVatOrCst(dto.getFinaleVatOrCst());
            List<InvoiceProductMap> ls1 = session.createQuery("from InvoiceProductMap imp where imp.invoice.invoiceNumber='" + dto.getInvoiceNumber() + "'").list();
            List<ProductDto> ls = new ArrayList<ProductDto>();
            for (int i = 0; i < ls1.size(); i++) {
                InvoiceProductMap pd = ls1.get(i);
                ProductDto ipm = new ProductDto();
                ipm.setDiscount(pd.getDiscount());
                ipm.setIdentifyMark((pd.getIdentifyMark() != null) ? pd.getIdentifyMark() : "");
                ipm.setNoOfPackets(pd.getNoOfPackets());
                ipm.setOtherCharges(pd.getOtherCharges());
                ipm.setPackSize(pd.getPackSize());
                ipm.setProductCode(pd.getProductCode());
                ipm.setProductId(pd.getProductId());
                ipm.setProductName(pd.getProductName());
                ipm.setPurchasePrice(pd.getPurchasePrice());
                ipm.setQuantity(pd.getQuantity());
                ipm.setSalesPrice(pd.getSalesPrice());
                ipm.setIdentifyMark(pd.getIdentifyMark());
                ipm.setCustProID(pd.getCustProID());
                ipm.setProductDetails(pd.getProductDetails());
                ipm.setProductType(pd.getProductType());
                ipm.setOptionalText(pd.getOptionalText());
                ipm.setQntyinExice(pd.getQntyinExice());
                ipm.setQuntyInGrama(pd.getQuntyInGrama());
                ls.add(ipm);
            }
             List<OtherInvoiceData> invoiceDatas=session.createQuery("From OtherInvoiceData od where od.invoice.invoiceNumber='" + dto.getInvoiceNumber() + "'").list();
             in.setOtherInvoiceData(invoiceDatas);
             in.setProductDtos(ls);


        } catch (Exception e) {
        } finally {
            session.close();
        }
        return in;

    }
}
