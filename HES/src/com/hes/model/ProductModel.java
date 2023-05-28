/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hes.model;

import com.hes.pojo.MySessionFactory;
import com.hes.pojo.Product;
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
public class ProductModel {

 public List<Product> getProductList() {
        List<Product> Products = new ArrayList<Product>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            Products = session.createQuery("From Product cm where cm.status='active'    order by cm.productName").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Products;
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

    public String addProduct(Product Product, int status) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            if (status == 0) {
                List<Product> Products = session.createQuery("From Product cm where cm.productName='" + Product.getProductName() + "'").list();
                if (Products.isEmpty()) {
                    Product.setCreatedOn(new Date());
                    Product.setStatus("active");
                    session.save(Product);
                    tr.commit();
                    result="success";
                } else {
                    Product cm = Products.get(0);
                    if (cm.getStatus().equals("inactive")) {
                        result = "YES" + cm.getProductId();
                    } else {
                        result = "Product name already exist !";
                    }
                    
                }
            } else {
                List<Product> Products = session.createQuery("From Product cm where cm.productId=" + status).list();
                if (!Products.isEmpty()) {
                    Product cm=Products.get(0);
                    cm.setProductName(Product.getProductName());
                    cm.setProductCode(Product.getProductCode());
                    cm.setPurchaseCost(Product.getPurchaseCost());
                    cm.setPackageSize(Product.getPackageSize());
                    cm.setSupplier(Product.getSupplier());
                    cm.setCreatedOn(new Date());
                    cm.setStatus("active");
                    session.update(cm);
                    tr.commit();
                    result="success";
                } else {
                    result = "Product name already exist !";
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

    public String deleteProduct(int cmpId)
    {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            List<Product> Products = session.createQuery("From Product cm where cm.productId=" + cmpId).list();
                if (!Products.isEmpty()) {

                    Product cm=Products.get(0);
                     //session.createQuery("delete From CustomerProductMap cm where cm.product.productId="+cm.getProductId()).executeUpdate();
                    cm.setStatus("inactive");
                    session.update(cm);
                    tr.commit();
                    result="success";
                } else {
                    result = "No Product name  exist !";
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

    public String viewProductInfo(Product cmp)
    {
        String result = "";
        result+="<html><body><center><table border=0>";
        result+="<tr><td>Product</td><td>"+cmp.getProductName()+"</td></tr>";
        result+="<tr><td>Product Code</td><td>"+cmp.getProductCode()+"</td></tr>";
        result+="<tr><td>Package Size</td><td>"+cmp.getPackageSize()+"</td></tr>";
        result+="<tr><td>Purchase Cost.</td><td>"+cmp.getPurchaseCost()+"</td></tr>";
        result+="</table></center></body></html>";
        return result;
    }
}
