/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hes.model;

import com.hes.pojo.BasicInformation;
import com.hes.pojo.Customer;
import com.hes.pojo.CustomerProductMap;
import com.hes.pojo.Invoice;
import com.hes.pojo.InvoiceProductMap;
import com.hes.pojo.MySessionFactory;
import com.hes.pojo.OtherInvoiceData;
import com.hes.pojo.Product;
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
public class InvoiceModel {

    public String getInvoiceNumber() {
        String result = "14-15/";
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            List ls = session.createQuery("select max(inv.invoiceId) from Invoice inv").list();
            if (ls.isEmpty()) {
                result += "1";
            } else {
                if (ls.get(0) != null) {
                    result += ls.get(0).toString();
                } else {
                    result += "1";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

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

    public List<Product> getCustomerProducts(String customerName) {
        List<Product> Products = new ArrayList<Product>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "From CustomerProductMap cm where cm.customer.name='" + customerName + "' and cm.product.status='active'   order by cm.product.productName";
            List<CustomerProductMap> list = session.createQuery(sql).list();
            for (CustomerProductMap cp : list) {
                Products.add(cp.getProduct());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Products;
    }

    public String generateInvoic(GenerateBillDto dto, int status) {
        String result = "";
        if (status == 1) {
            deleteInvoice(dto);
        }
        System.out.println("dto.getInvoiceType()   "+dto.getInvoiceType());
        dto.setBasicInformation(getBasicInformation());
        if (dto.getInvoiceType() == 1) {
            dto = CstOrVatInvoice.genBill(dto);
            if (dto.isExciseDuty()) {
                dto = TCEInvoice.genBill(dto);
            }
        } else {
            dto = Endorsement.genBill(dto);
        }
        addInvoice(dto);
        return result;

    }

     public List<Supplier> getSupplierList(String supplierId) {
        List<Supplier> Suppliers = new ArrayList<Supplier>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql="From Supplier cm where cm.status='active'";
            if(supplierId.trim().length()>0)
                sql+=" and cm.name='" + supplierId + "'";
            sql+="   order by cm.name";
            Suppliers = session.createQuery(sql).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Suppliers;
    }

    public void addInvoice(GenerateBillDto dto) {
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            Invoice in = new Invoice();
            in.setBeckInvAmount(dto.getBeckInvAmount());
            in.setBeckInvDate((dto.getBeckInvDate() != null) ? dto.getBeckInvDate() : "NA");
            in.setBeckInvNo((dto.getBeckInvNo() != null) ? dto.getBeckInvNo() : "NA");
            in.setCustDiscount(dto.getCustDiscount());

            in.setCustaddress(dto.getCustomer().getAddress());
            in.setCustcerNo(dto.getCustomer().getCerNo());
            in.setCustcommissionerate(dto.getCustomer().getCommissionerate());
            in.setCustcstNumber(dto.getCustomer().getCstNumber());
            in.setCustdebitEntryNo(dto.getCustomer().getDebitEntryNo());
            in.setCustdiscount(dto.getCustomer().getDiscount());
            in.setCustdiscription(dto.getCustomer().getDiscription());
            in.setCustdivision(dto.getCustomer().getDivision());
            in.setCusteccNo(dto.getCustomer().getEccNo());
            in.setCustlandLine(dto.getCustomer().getLandLine());
            in.setCustname(dto.getCustomer().getName());
            in.setCustphnNumber(dto.getCustomer().getPhnNumber());
            in.setCustrangeAddress(dto.getCustomer().getRangeAddress());
            in.setCusttinNumber(dto.getCustomer().getTinNumber());


            in.setDateOfRemovelGoods((dto.getDateOfRemovelGoods() != null) ? dto.getDateOfRemovelGoods() : "NA");
            in.setDispachedDate((dto.getDispachedDate() != null) ? dto.getDispachedDate() : "NA");
            in.setDispachedFrom((dto.getDispachedFrom() != null) ? dto.getDispachedFrom() : "NA");
            in.setDispachedTo((dto.getDispachedTo() != null) ? dto.getDispachedTo() : "NA");
            in.setDocumentThrough((dto.getDocumentThrough() != null) ? dto.getDocumentThrough() : "NA");
            in.setEnrtyPageNo((dto.getEnrtyPageNo() != null) ? dto.getEnrtyPageNo() : "NA");
            in.setExciseDuty(dto.isExciseDuty());
            in.setExciseInvNo((dto.getExciseInvNo() != null) ? dto.getExciseInvNo() : "NA");
            in.setInvoiceDate(getDate(dto.getInvoiceDate()));
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
            in.setSplraddress(dto.getSupplier().getAddress());
            in.setSplrcerNo(dto.getSupplier().getCerNo());
            in.setSplrcommissionerate(dto.getSupplier().getCommissionerate());
            in.setSplrcstNumber(dto.getSupplier().getCstNumber());
            in.setSplrdebitEntryNo(dto.getSupplier().getDebitEntryNo());
            in.setSplrdiscount(dto.getSupplier().getDiscount());
            in.setSplrdiscription(dto.getSupplier().getDiscription());
            in.setSplrdivision(dto.getSupplier().getDivision());
            in.setSplreccNo(dto.getSupplier().getEccNo());
            in.setSplrlandLine(dto.getSupplier().getLandLine());
            in.setSplrname(dto.getSupplier().getName());
            in.setSplrphnNumber(dto.getSupplier().getPhnNumber());
            in.setSplrrangeAddress(dto.getSupplier().getRangeAddress());
            in.setSplrtinNumber(dto.getSupplier().getTinNumber());

            in.setVat(dto.getBasicInformation().getVat());
            in.setCst(dto.getBasicInformation().getCst());
            in.setExciseDutyper(dto.getBasicInformation().getExciseDuty());
            in.setEductionCess(dto.getBasicInformation().getEductionCess());
            in.setSandHEduCess(dto.getBasicInformation().getSandHEduCess());

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
            session.save(in);
            List<ProductDto> ls = dto.getProductDtos();
            for (int i = 0; i < ls.size(); i++) {
                ProductDto pd = ls.get(i);
                InvoiceProductMap ipm = new InvoiceProductMap();
                ipm.setDiscount(pd.getDiscount());
                ipm.setIdentifyMark((pd.getIdentifyMark() != null) ? pd.getIdentifyMark() : "");
                ipm.setInvoice(in);
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
                session.save(ipm);
            }
            
            List<OtherInvoiceData> invoiceDatas=dto.getOtherInvoiceData();
            for(OtherInvoiceData otd:invoiceDatas){
                otd.setInvoice(in);
                session.save(otd);
            }
            tr.commit();



        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();

            }
        } finally {
            session.close();
        }

    }

    public Date getDate(String st) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {

            Date date = formatter.parse(st);
            return date;

        } catch (Exception e) {
            return new Date();
        }
    }

    public void deleteInvoice(GenerateBillDto dto) {
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
        } catch (Exception e) {
            e.printStackTrace();
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    public boolean checkInvoice(String invoice) {
        boolean result = false;
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            List<Invoice> ls = session.createQuery("from Invoice inv where inv.invoiceNumber='" + invoice + "'").list();
            if (!ls.isEmpty()) {
                result = true;
            }
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return result;
    }

    public BasicInformation getBasicInformation() {
        BasicInformation basicInformation = null;
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            List<BasicInformation> ls = session.createQuery("From BasicInformation cm ").list();
            if (!ls.isEmpty()) {
                basicInformation = ls.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return basicInformation;
    }
}
