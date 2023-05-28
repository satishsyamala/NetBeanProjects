/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hes.model;

import com.hes.pojo.Customer;
import com.hes.pojo.CustomerProductMap;
import com.hes.pojo.MySessionFactory;
import com.hes.pojo.Product;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author SATISH SYAMALA
 */
public class CustomerModel {

    public List<Product> getProductList(String productName) {
        List<Product> Products = new ArrayList<Product>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql="From Product cm where cm.status='active'";
            if(productName.trim().length()>0)
                sql+=" and cm.productName='"+productName+"'";
            sql+="  order by cm.productName";
            Products = session.createQuery(sql).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Products;
    }

 public List<Customer> getCustomerList() {
        List<Customer> Customers = new ArrayList<Customer>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            Customers = session.createQuery("From Customer cm where cm.status='active'  order by cm.name").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Customers;
    }

    public String addCustomer(Customer Customer, int status,List<Product> list) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            if (status == 0) {
                List<Customer> Customers = session.createQuery("From Customer cm where cm.name='" + Customer.getName() + "'").list();
                if (Customers.isEmpty()) {
                    Customer.setCreatedOn(new Date());
                    Customer.setStatus("active");
                    Customer.setDiscription("NA");
                    session.save(Customer);
                    for(Product pr:list)
                    {
                        CustomerProductMap cpm=new CustomerProductMap();
                        cpm.setCustomer(Customer);
                        cpm.setProduct(pr);
                        session.save(cpm);
                    }
                    tr.commit();
                    result="success";
                } else {
                    Customer cm=Customers.get(0);
                    if(cm.getStatus().equals("inactive"))
                    {
                        result = "YES"+cm.getId();
                    }else{
                    result = "Customer name already exist !";
                    }
                }
            } else {
                session.createQuery("delete From CustomerProductMap cm where cm.customer.id=" + status).executeUpdate();
                List<Customer> Customers = session.createQuery("From Customer cm where cm.id=" + status).list();
                if (!Customers.isEmpty()) {
                    Customer cm=Customers.get(0);
                    cm.setAddress(Customer.getAddress());
                    cm.setCerNo(Customer.getCerNo());
                    cm.setCommissionerate(Customer.getCommissionerate());
                    cm.setCreatedOn(Customer.getCreatedOn());
                    cm.setCstNumber(Customer.getCstNumber());
                    cm.setDebitEntryNo(Customer.getDebitEntryNo());
                    cm.setDiscription("NA");
                    cm.setDivision(Customer.getDivision());
                    cm.setEccNo(Customer.getEccNo());
                    cm.setLandLine(Customer.getLandLine());
                    cm.setName(Customer.getName());
                    cm.setPhnNumber(Customer.getPhnNumber());
                    cm.setRangeAddress(Customer.getRangeAddress());
                    cm.setStatus("active");
                    cm.setTinNumber(Customer.getTinNumber());
                    cm.setDiscount(Customer.getDiscount());
                    session.update(cm);
                    for(Product pr:list)
                    {
                        CustomerProductMap cpm=new CustomerProductMap();
                        cpm.setCustomer(cm);
                        cpm.setProduct(pr);
                        session.save(cpm);
                    }
                    tr.commit();
                    result="success";
                } else {

                    result = "Customer name already exist !";
                }
            }
        } catch (Exception e) {
            if(tr!=null)
                tr.rollback();
            result = "Error : "+e.getMessage();
        } finally {
            session.close();
        }
        return result;
    }

    public String deleteCustomer(int cmpId)
    {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            session.createQuery("delete From CustomerProductMap cm where cm.customer.id=" + cmpId).executeUpdate();
            List<Customer> Customers = session.createQuery("From Customer cm where cm.id=" + cmpId).list();
                if (!Customers.isEmpty()) {
                    Customer cm=Customers.get(0);
                    cm.setStatus("inactive");
                    session.update(cm);
                    tr.commit();
                    result="success";
                } else {
                    result = "No Customer name  exist !";
                }

        } catch (Exception e) {
            if(tr!=null)
                tr.rollback();
            result = "Error : "+e.getMessage();
        } finally {
            session.close();
        }
        return result;
    }

    public String viewCustomerInfo(Customer cmp)
    {
        String result = "";
        result += "<html><body><center><font size=5 color=blue>Customer Details</font><br/><br/>"
                + "<table width=80%   border=0>";
        result += "<tr  bgcolor=rgb(102,153,255)><td>Customer</td><td>" + cmp.getName() + "</td></tr>";
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

    public List<Product> getCustomerProducts(String customerName)
    {
        List<Product> Products = new ArrayList<Product>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql="From CustomerProductMap cm where cm.customer.name='"+customerName+"' and cm.product.status='active'   order by cm.product.productName";
            List<CustomerProductMap> list=session.createQuery(sql).list();
            for(CustomerProductMap cp:list)
            Products.add(cp.getProduct());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Products;
    }
    public List<Product> getNotCustomerProducts(String customerName)
    {
        List<Product> Products = new ArrayList<Product>();
        Session session = null;
        try {
            List<Product> pro=getProductList("");
            session = MySessionFactory.getSession();
            String sql="From CustomerProductMap cm where cm.customer.name='"+customerName+"'  and cm.product.status='active'   order by cm.product.productName";
            List<CustomerProductMap> list=session.createQuery(sql).list();
            for(Product pr:pro)
            {
              boolean check=true;
            for(CustomerProductMap cp:list)
            {
                if(pr.getProductId()==cp.getProduct().getProductId())
                    check=false;
            }
            if(check)
            Products.add(pr);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Products;
    }
}
