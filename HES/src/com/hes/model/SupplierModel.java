/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hes.model;

import com.hes.pojo.MySessionFactory;
import com.hes.pojo.Supplier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author SATISH SYAMALA
 */
public class SupplierModel {

    public List<Supplier> getSupplierList() {
        List<Supplier> Suppliers = new ArrayList<Supplier>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            Suppliers = session.createQuery("From Supplier cm where cm.status='active' order by cm.name").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Suppliers;
    }

    public Supplier getSupplier(String eccNo) {
        Supplier Suppliers = null;
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            List<Supplier> ls = session.createQuery("From Supplier cm where cm.eccNo='"+eccNo+"'").list();
            if(!ls.isEmpty())
                Suppliers=ls.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Suppliers;
    }


    public String addSupplier(Supplier Supplier, int status) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            if (status == 0) {
                List<Supplier> Suppliers = session.createQuery("From Supplier cm where cm.name='" + Supplier.getName() + "'").list();
                if (Suppliers.isEmpty()) {
                    Supplier.setCreatedOn(new Date());
                    Supplier.setStatus("active");
                    Supplier.setDiscription("NA");
                    session.save(Supplier);
                    tr.commit();
                    result = "success";
                } else {
                    Supplier cm = Suppliers.get(0);
                    if (cm.getStatus().equals("inactive")) {
                        result = "YES" + cm.getId();
                    } else {
                        result = "Supplier name already exist !";
                    }
                    
                }
            } else {
                List<Supplier> Suppliers = session.createQuery("From Supplier cm where cm.id=" + status).list();
                if (!Suppliers.isEmpty()) {
                    Supplier cm = Suppliers.get(0);
                    cm.setAddress(Supplier.getAddress());
                    cm.setCerNo(Supplier.getCerNo());
                    cm.setCommissionerate(Supplier.getCommissionerate());
                    cm.setCreatedOn(Supplier.getCreatedOn());
                    cm.setCstNumber(Supplier.getCstNumber());
                    cm.setDebitEntryNo(Supplier.getDebitEntryNo());
                    cm.setDiscription("NA");
                    cm.setDivision(Supplier.getDivision());
                    cm.setEccNo(Supplier.getEccNo());
                    cm.setLandLine(Supplier.getLandLine());
                    cm.setName(Supplier.getName());
                    cm.setPhnNumber(Supplier.getPhnNumber());
                    cm.setRangeAddress(Supplier.getRangeAddress());
                    cm.setStatus("active");
                    cm.setTinNumber(Supplier.getTinNumber());
                    cm.setDiscount(Supplier.getDiscount());
                    session.update(cm);
                    tr.commit();
                    result = "success";
                } else {
                    result = "Supplier name already exist !";
                }
            }
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            result = "Error : " + e.getMessage();
        } finally {
            session.close();
        }
        return result;
    }

    public String deleteSupplier(int cmpId) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            List<Supplier> Suppliers = session.createQuery("From Supplier cm where cm.id=" + cmpId).list();
            if (!Suppliers.isEmpty()) {
                Supplier cm = Suppliers.get(0);
                session.delete(cm);
                tr.commit();
                result = "success";
            } else {
                result = "No Supplier name  exist !";
            }

        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            result = "Error : " + e.getMessage();
        } finally {
            session.close();
        }
        return result;
    }

    public String viewSupplierInfo(Supplier cmp) {
         String result = "";
        result += "<html><body><center><font size=5 color=blue>Supplier Details</font><br/><br/>"
                + "<table width=80%   border=0>";
        result += "<tr  bgcolor=rgb(102,153,255)><td>Supplier</td><td>" + cmp.getName() + "</td></tr>";
        result += "<tr><td>Address</td><td>" + cmp.getAddress() + "</td></tr>";
        result += "<tr bgcolor=rgb(102,153,255)><td>TIN No.</td><td>" + cmp.getTinNumber() + "</td></tr>";
        result += "<tr><td>Phone No.</td><td>" + cmp.getPhnNumber() + "</td></tr>";
        result += "<tr bgcolor=rgb(102,153,255)><td>Land Line</td><td>" + cmp.getLandLine() + "</td></tr>";
        result += "<tr><td>Division</td><td>" + cmp.getDivision() + "</td></tr>";
        result += "<tr bgcolor=rgb(102,153,255)><td>Commissionerate</td><td>" + cmp.getCommissionerate() + "</td></tr>";
        result += "<tr><td>CST No.</td><td>" + cmp.getCstNumber() + "</td></tr>";
        result += "<tr bgcolor=rgb(102,153,255)><td>CER No.</td><td>" + cmp.getCerNo() + "</td></tr>";
        result += "<tr><td>Range Address</td><td>" + cmp.getRangeAddress() + "</td></tr>";
        result += "<tr bgcolor=rgb(102,153,255)><td>ECC No.</td><td>" + cmp.getEccNo() + "</td></tr>";
        result += "</table></center></body></html>";
        return result;
    }
}
