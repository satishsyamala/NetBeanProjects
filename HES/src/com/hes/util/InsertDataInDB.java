/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hes.util;

import com.hes.pojo.Customer;
import com.hes.pojo.CustomerProductMap;
import com.hes.pojo.MySessionFactory;
import com.hes.pojo.Product;
import com.hes.pojo.Supplier;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author SATISH SYAMALA
 */
public class InsertDataInDB {

    public static void main(String[] arg) {
       insertIntoCustomer();
       insertIntoProducts();
       insertIntoProductAndCustomerMap();
    }

    public static void insertIntoCustomer() {
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            File filr = new File("D:/customer.txt");
            FileReader in = new FileReader(filr);
            BufferedReader br = new BufferedReader(in);
            String line = br.readLine();
            int count=1;
            while (line != null) {
                String[] ar = line.trim().split("@@");
                List<Customer> Customers = session.createQuery("From Customer cm where cm.name='" + ar[0].trim() + "'").list();
                if (Customers.isEmpty()) {
                    Customer cust = new Customer();
                    System.out.println(count+") "+ar[0]);
                    count++;
                    cust.setName(ar[0].trim());
                    cust.setAddress(ar[1]);
                    cust.setRangeAddress(ar[2].equals("N/A")?" ":ar[2]);
                    cust.setTinNumber(ar[3].equals("N/A")?" ":ar[3]);
                    cust.setCstNumber(ar[4].equals("N/A")?" ":ar[4]);
                    cust.setDivision(ar[5].equals("N/A")?" ":ar[5]);
                    cust.setCommissionerate(ar[6].equals("N/A")?" ":ar[6]);
                    cust.setCerNo(ar[7].equals("N/A")?" ":ar[7]);
                    cust.setEccNo(ar[8].equals("N/A")?" ":ar[8]);
                    cust.setLandLine(" ");
                    cust.setPhnNumber(" ");
                    cust.setCreatedOn(new Date());
                    cust.setStatus("active");
                    cust.setDebitEntryNo(" ");
                    cust.setDiscount(0.0);
                    cust.setDiscription(" ");
                    session.save(cust);
                    
                }
                line = br.readLine();
            }
            tr.commit();
        } catch (Exception e) {
            if(tr!=null)
                tr.rollback();
            e.printStackTrace();
        }
        finally{
            MySessionFactory.closeSession();
        }
    }

    public static void insertIntoProducts() {
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            List<Supplier> ls = session.createQuery("From Supplier cm where cm.status='active' order by cm.name").list();
            Supplier sp = ls.get(0);
            File filr = new File("D:/products.txt");
            FileReader in = new FileReader(filr);
            BufferedReader br = new BufferedReader(in);
            String line = br.readLine();
             int count=1;
            while (line != null) {
                String[] ar = line.trim().split("@@");
                List<Product> Products = session.createQuery("From Product cm where cm.productName='" + ar[0].trim() + "'").list();
                if (Products.isEmpty()) {
                    Product pro = new Product();
                    System.out.println(count+") "+ar[0]);
                    count++;
                    pro.setProductName(ar[0].trim());
                    pro.setCreatedOn(new Date());
                    pro.setPackageSize(Integer.parseInt(ar[1].trim()));
                    pro.setPurchaseCost(Double.parseDouble(ar[2].trim()));
                    pro.setIdentifMark("Barrel");
                    pro.setStatus("active");
                    pro.setProductCode("32069041");
                    pro.setSupplier(sp);
                    session.save(pro);
                   
                }
                 line = br.readLine();
            }
            tr.commit();
        } catch (Exception e) {
            if(tr!=null)
                tr.rollback();
            e.printStackTrace();
        }
        finally{
            MySessionFactory.closeSession();
        }
    }

    public static void insertIntoProductAndCustomerMap() {
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            File filr = new File("D:/productAndCustomer.txt");
            FileReader in = new FileReader(filr);
            BufferedReader br = new BufferedReader(in);
            String line = br.readLine();
            int count=1;
            while (line != null) {
                String[] ar = line.trim().split("@@");
                List<CustomerProductMap> ls=session.createQuery("From CustomerProductMap cm where cm.customer.name='" + ar[0].trim() + "' and cm.product.productName='" + ar[1].trim() + "'").list();
                if(ls.isEmpty())
                {
                CustomerProductMap cm = new CustomerProductMap();
                List<Customer> Customers = session.createQuery("From Customer cm where cm.name='" + ar[0].trim() + "'").list();
                List<Product> Products = session.createQuery("From Product cm where cm.productName='" + ar[1].trim() + "'").list();
                if (!Customers.isEmpty() && !Products.isEmpty()) {
                    System.out.println(count+") "+ar[0]);
                    count++;
                    cm.setCustomer(Customers.get(0));
                    cm.setProduct(Products.get(0));
                    session.save(cm);
                }
                }
                line = br.readLine();



            }
            tr.commit();
        } catch (Exception e) {
            if(tr!=null)
                tr.rollback();
            e.printStackTrace();
        }
        finally{
            MySessionFactory.closeSession();
        }
    }
}
