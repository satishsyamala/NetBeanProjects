/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hes.model;

import com.hes.pojo.Company;
import com.hes.pojo.MySessionFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author SATISH SYAMALA
 */
public class CompanyModel {

    String fontStyle="font:15px arial,sans-serif;color: #0000FF;";

    public List<Company> getCompayList() {
        List<Company> companys = new ArrayList<Company>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            companys = session.createQuery("From Company cm where cm.status='active' order by cm.name").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return companys;
    }

    public String addCompany(Company company, int status) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            if (status == 0) {
                List<Company> companys = session.createQuery("From Company cm where cm.name='" + company.getName() + "'").list();
                if (companys.isEmpty()) {
                    company.setCreatedOn(new Date());
                    company.setStatus("active");
                    company.setDiscription("NA");
                    session.save(company);
                    tr.commit();
                    result = "success";
                } else {
                    Company com = companys.get(0);
                    if (com.getStatus().equals("inactive")) {
                         result = "YES"+com.getId();
                    } else {
                         result = "Company name already exist !";
                    }
                   
                }
            } else {
                List<Company> companys = session.createQuery("From Company cm where cm.id=" + status).list();
                if (!companys.isEmpty()) {
                    Company cm = companys.get(0);
                    cm.setAddress(company.getAddress());
                    cm.setCerNo(company.getCerNo());
                    cm.setCommissionerate(company.getCommissionerate());
                    cm.setCreatedOn(company.getCreatedOn());
                    cm.setCstNumber(company.getCstNumber());
                    cm.setDebitEntryNo(company.getDebitEntryNo());
                    cm.setDiscription("NA");
                    cm.setDivision(company.getDivision());
                    cm.setEccNo(company.getEccNo());
                    cm.setLandLine(company.getLandLine());
                    cm.setName(company.getName());
                    cm.setPhnNumber(company.getPhnNumber());
                    cm.setRangeAddress(company.getRangeAddress());
                    cm.setStatus("active");
                    cm.setTinNumber(company.getTinNumber());
                    session.update(cm);
                    tr.commit();
                    result = "success";
                } else {
                    result = "Company name already exist !";
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

    public String deleteCompany(int cmpId) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            List<Company> companys = session.createQuery("From Company cm where cm.id=" + cmpId).list();
            if (!companys.isEmpty()) {
                Company cm = companys.get(0);
                cm.setStatus("inactive");
                session.update(cm);
                tr.commit();
                result = "success";
            } else {
                result = "No Company name  exist !";
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

    public String viewCompanyInfo(Company cmp) {
        String result = "";
        result += "<html><body><center><font size=5 color=blue>Company Details</font><br/><br/>"
                + "<table width=80%  border=0>";
        result += "<tr  bgcolor=rgb(102,153,255)><td>Company</td><td>" + cmp.getName() + "</td></tr>";
        result += "<tr><td>Address</td><td>" + cmp.getAddress() + "</td></tr>";
        result += "<tr bgcolor=rgb(102,153,255)><td>TIN No.</td><td>" + cmp.getTinNumber() + "</td></tr>";
        result += "<tr><td>Phone No.</td><td>" + cmp.getPhnNumber() + "</td></tr>";
        result += "<tr bgcolor=rgb(102,153,255)><td>Land Line</td><td>" + cmp.getLandLine() + "</td></tr>";
        result += "<tr><td>Division</td><td>" + cmp.getDivision() + "</td></tr>";
        result += "<tr bgcolor=rgb(102,153,255)><td>Commissionerate</td><td>" + cmp.getCommissionerate() + "</td></tr>";
        result += "<tr><td>CST No.</td><td>" + cmp.getCstNumber() + "</td></tr>";
        result += "<tr bgcolor=rgb(102,153,255)><td>GIR No.</td><td>" + cmp.getCerNo() + "</td></tr>";
        result += "<tr><td>Range Address</td><td>" + cmp.getRangeAddress() + "</td></tr>";
         result += "<tr bgcolor=rgb(102,153,255)><td>ECC No.</td><td>" + cmp.getEccNo() + "</td></tr>";

        result += "</table></center></body></html>";
        return result;
    }
}
