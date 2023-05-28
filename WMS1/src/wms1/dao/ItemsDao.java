/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wms1.pojos.Brands;
import wms1.pojos.Items;
import wms1.pojos.MySessionFactory;
import wms1.pojos.Purchases;
import wms1.pojos.PurchasesItems;
import wms1.pojos.Sales;
import wms1.pojos.SalesItems;
import wms1.pojos.ServiceItems;
import wms1.pojos.Services;
import wms1.pojos.StockInventory;

/**
 *
 * @author Aadhya
 */
public class ItemsDao {

    public List<Items> getItems(long id, String code, String name) {
        List<Items> itemsList = new ArrayList<>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "From Items a where 1=1 ";
            if (id > 0) {
                sql += " and a.itemId=" + id;
            }
            if (code != null && code.trim().length() > 0) {
                sql += " and lower(a.itemCode) = :code ";
            }
            if (name != null && name.trim().length() > 0) {
                sql += " and lower(a.itemName) = :name ";
            }
            sql += " order by lower(a.itemName) ";
            Query q = session.createQuery(sql);
            if (code != null && code.trim().length() > 0) {
                q.setParameter("code", code.trim().toLowerCase());
            }
            if (name != null && name.trim().length() > 0) {
                q.setParameter("name", name.trim().toLowerCase());
            }
            itemsList = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return itemsList;
    }

    public List<Items> getItems(long id, String code, String name, String barCode) {
        List<Items> itemsList = new ArrayList<>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "From Items a where 1=1 ";
            if (id > 0) {
                sql += " and a.itemId=" + id;
            }
            if (code != null && code.trim().length() > 0) {
                sql += " and lower(a.itemCode) = :code ";
            }
            if (name != null && name.trim().length() > 0) {
                sql += " and lower(a.itemName) = :name ";
            }
            if (barCode != null && barCode.trim().length() > 0) {
                sql += " and lower(a.barCode) = :barcode ";
            }
            sql += " order by lower(a.itemName) ";
            Query q = session.createQuery(sql);
            if (code != null && code.trim().length() > 0) {
                q.setParameter("code", code.trim().toLowerCase());
            }
            if (name != null && name.trim().length() > 0) {
                q.setParameter("name", name.trim().toLowerCase());
            }
            if (barCode != null && barCode.trim().length() > 0) {
                q.setParameter("barcode", barCode.trim().toLowerCase());
            }
            itemsList = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return itemsList;
    }

    public List<Object[]> getItemsDetails(int id, String code, String name, String nameOrCode, String type) {
        List<Object[]> ItemsList = new ArrayList<>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "select a.itemId,a.brandId.brandName,a.itemCode,a.itemName,a.barCode,a.price From Items a where 1=1 ";
            if (id > 0) {
                sql += " and a.itemId=" + id;
            }
            if (code != null && code.trim().length() > 0) {
                sql += " and lower(a.itemCode) = :code ";
            }
            if (name != null && name.trim().length() > 0) {
                sql += " and lower(a.itemName) = :name ";
            }
            if (nameOrCode != null && nameOrCode.trim().length() > 0) {
                sql += " and (lower(a.itemCode) like :fullname||'%' or lower(a.itemName) like :fullname||'%') ";
            }
            if (type != null && type.trim().length() > 0 && !type.equalsIgnoreCase("All")) {
                sql += " and a.brandId.brandName = '" + type + "' ";
            }
            sql += " order by lower(a.itemName) ";
            Query q = session.createQuery(sql);
            if (code != null && code.trim().length() > 0) {
                q.setParameter("code", code.trim().toLowerCase());
            }
            if (name != null && name.trim().length() > 0) {
                q.setParameter("name", name.trim().toLowerCase());
            }
            if (nameOrCode != null && nameOrCode.trim().length() > 0) {
                q.setParameter("fullname", nameOrCode.trim().toLowerCase());
            }
            ItemsList = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ItemsList;
    }

    public String saveItems(Items items) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            List<Items> ls = getItems(0, items.getItemCode(), null);
            if (ls.isEmpty()) {
                ls = getItems(0, null, items.getItemName());
                if (ls.isEmpty()) {
                    session = MySessionFactory.getSession();
                    tr = session.beginTransaction();
                    session.save(items);
                    tr.commit();
                    result = "success";
                } else {
                    result = "Item name already exist";
                }
            } else {
                result = "Item code already exist";
            }
        } catch (Exception e) {
            result = "Some thing went wrong";
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public String updateItems(Items items) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            List<Items> ls = getItems(0, items.getItemCode(), null);
            if (ls.isEmpty() || ls.get(0).getItemId() == items.getItemId()) {
                ls = getItems(0, null, items.getItemName());
                if (ls.isEmpty() || ls.get(0).getItemId() == items.getItemId()) {
                    Items br = getItems(items.getItemId(), null, null).get(0);
                    session = MySessionFactory.getSession();
                    tr = session.beginTransaction();
                    br.setItemCode(items.getItemCode());
                    br.setBrandId(items.getBrandId());
                    br.setItemName(items.getItemName());
                    br.setBarCode(items.getBarCode());
                    br.setPrice(items.getPrice());
                    session.update(br);
                    tr.commit();
                    result = "success";
                } else {
                    result = "Brand name already exist";
                }
            } else {
                result = "Item code already exist";
            }
        } catch (Exception e) {
            result = "Some thing went wrong";
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public String savePurchases(Purchases purchases) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            session.save(purchases);
            for (PurchasesItems p : purchases.getPurchaseItems()) {
                p.setPurchases(purchases);
                session.save(p);
                updateInvetory(p.getItems(), p.getQuantity(), session);
            }
            tr.commit();
            result = "success";

        } catch (Exception e) {
            e.printStackTrace();
            result = "Some thing went wrong";
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public String saveSales(Sales sales) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            session.save(sales);
            for (SalesItems p : sales.getSalesItems()) {
                p.setSales(sales);
                session.save(p);
                updateInvetory(p.getItems(), -p.getQuantity(), session);
            }
            tr.commit();
            result = "success";

        } catch (Exception e) {
            e.printStackTrace();
            result = "Some thing went wrong";
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public void updateInvetory(Items items, int qty, Session session) throws Exception {
        String sql = "update wms.stock_inventory set quantity=quantity+" + qty + " where items_id=" + items.getItemId();
        System.err.println("Sql " + sql);
        int cn = session.createSQLQuery(sql).executeUpdate();
        if (cn == 0) {
            StockInventory si = new StockInventory();
            si.setItems(items);
            si.setQuantity(qty);
            session.save(si);
        }
    }

    public List<Object[]> getPurchaseList(String brand, String item, String purNo, Date fromDate, Date todate, int page, int sizw) {
        List<Object[]> result = new ArrayList<>();
        Session session = null;

        try {
            session = MySessionFactory.getSession();
            String sql = "select a.purchaseItemId,to_char(a.purchases.createdOn,'dd-mm-yy HH24:mi'), "
                    + " a.purchases.purchaseNo,a.items.brandId.brandName,a.items.itemName,a.quantity from PurchasesItems a where 1=1 ";
            if (brand != null && brand.trim().length() > 0 && !brand.trim().equalsIgnoreCase("All")) {
                sql += " and a.items.brandId.brandName=:brName ";
            }
            if (item != null && item.trim().length() > 0 && !item.trim().equalsIgnoreCase("All")) {
                sql += " and a.items.itemName=:itName ";
            }
            if (purNo != null && purNo.trim().length() > 0) {
                sql += " and a.purchases.purchaseNo=:purNo ";
            }

            if (fromDate != null && todate != null) {
                sql += " and to_date(to_char(a.purchases.createdOn,'dd-mm-yyyy'),'dd-mm-yyyy') between :fromDate and :todate ";
            }
            sql += "order by a.purchaseItemId";
            System.err.println("Sql " + sql);
            Query q = session.createQuery(sql);
            if (brand != null && brand.trim().length() > 0 && !brand.trim().equalsIgnoreCase("All")) {
                q.setParameter("brName", brand);
            }
            if (item != null && item.trim().length() > 0 && !item.trim().equalsIgnoreCase("All")) {
                q.setParameter("itName", item);
            }
            if (purNo != null && purNo.trim().length() > 0) {
                q.setParameter("purNo", purNo);
            }
            if (fromDate != null && todate != null) {
                q.setParameter("fromDate", fromDate);
                q.setParameter("todate", todate);
            }
            if (sizw > 0) {
                q.setFirstResult(page).setMaxResults(sizw);
            }
            result = q.list();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public String getInvoiceNo() {
        String inv = "";
        Session session = null;

        try {
            session = MySessionFactory.getSession();
            String sql = "SELECT nextval('wms.seq_invoice_no')";
            List<Object> ls = session.createSQLQuery(sql).list();
            String in = ls.get(0).toString();
            String zr = "00000000";
            inv = "INV" + zr.substring(0, 8 - in.length()) + in;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return inv;
    }

    public List<Object[]> getSalesList(String brand, String item, String invNo, Date fromDate, Date todate, String mobile, int page, int sizw) {
        List<Object[]> result = new ArrayList<>();
        Session session = null;

        try {
            session = MySessionFactory.getSession();
            String sql = "select a.salesId,to_char(a.createdOn,'dd-mm-yy HH24:mi'), "
                    + " a.invoiceNo,a.custMobileNo,a.amount,a.discount,a.totalAmount from Sales a where 1=1 ";
            if (brand != null && brand.trim().length() > 0 && !brand.trim().equalsIgnoreCase("All")) {
                sql += " and a.salesId in ( select b.sales.salesId from SalesItems b where "
                        + " b.items.brandId.brandName=:brName and  to_date(to_char(b.sales.createdOn,'dd-mm-yyyy'),'dd-mm-yyyy') between :fromDate and :todate ) ";
            }
            if (item != null && item.trim().length() > 0 && !item.trim().equalsIgnoreCase("All")) {
                sql += " and a.salesId in ( select b.sales.salesId from SalesItems b where "
                        + " b.items.itemName=:itName and  to_date(to_char(b.sales.createdOn,'dd-mm-yyyy'),'dd-mm-yyyy') between :fromDate and :todate ) ";
            }
            if (invNo != null && invNo.trim().length() > 0) {
                sql += " and a.invoiceNo=:purNo ";
            }

            if (mobile != null && mobile.trim().length() > 0) {
                sql += " and a.custMobileNo=:mobleno ";
            }

            if (fromDate != null && todate != null) {
                sql += " and to_date(to_char(a.createdOn,'dd-mm-yyyy'),'dd-mm-yyyy') between :fromDate and :todate ";
            }
            sql += "order by a.salesId";
            System.err.println("Sql " + sql);
            Query q = session.createQuery(sql);
            if (brand != null && brand.trim().length() > 0 && !brand.trim().equalsIgnoreCase("All")) {
                q.setParameter("brName", brand);
            }
            if (item != null && item.trim().length() > 0 && !item.trim().equalsIgnoreCase("All")) {
                q.setParameter("itName", item);
            }
            if (invNo != null && invNo.trim().length() > 0) {
                q.setParameter("purNo", invNo);
            }
            if (mobile != null && mobile.trim().length() > 0) {
                q.setParameter("mobleno", mobile);
            }
            if (fromDate != null && todate != null) {
                q.setParameter("fromDate", fromDate);
                q.setParameter("todate", todate);
            }
            if (sizw > 0) {
                q.setFirstResult(page).setMaxResults(sizw);
            }
            result = q.list();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public Sales getSales(long id) {
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "from Sales a where a.salesId=" + id;
            List<Sales> result = session.createQuery(sql).list();
            if (!result.isEmpty()) {
                return result.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public List<Sales> getSalesList(long id) {
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "from Sales a where 1=1 ";
            if (id > 0) {
                sql += " and a.salesId=" + id;
            }
           return session.createQuery(sql).list();
          
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public List<SalesItems> getSalesItems(long id, String brand, String item) {
        List<SalesItems> result = new ArrayList<>();
        Session session = null;

        try {
            session = MySessionFactory.getSession();
            String sql = "from SalesItems a where 1=1 ";
            if (brand != null && brand.trim().length() > 0 && !brand.trim().equalsIgnoreCase("All")) {
                sql += " and   a.items.brandId.brandName=:brName ";
            }
            if (item != null && item.trim().length() > 0 && !item.trim().equalsIgnoreCase("All")) {
                sql += " and a.items.itemName=:itName  ";
            }
            if (id > 0) {
                sql += " and a.sales.salesId=" + id;
            }
            sql += "order by a.salesItemId ";
            System.err.println("Sql " + sql);
            Query q = session.createQuery(sql);
            if (brand != null && brand.trim().length() > 0 && !brand.trim().equalsIgnoreCase("All")) {
                q.setParameter("brName", brand);
            }
            if (item != null && item.trim().length() > 0 && !item.trim().equalsIgnoreCase("All")) {
                q.setParameter("itName", item);
            }
            result = q.list();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public String saveServices(Services services) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            session.save(services);
            for (ServiceItems p : services.getServiceItemses()) {
                p.setServices(services);
                session.save(p);

            }
            tr.commit();
            result = "success";

        } catch (Exception e) {
            e.printStackTrace();
            result = "Some thing went wrong";
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public String updateServices(Services services) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            session.update(services);
            String sql = "delete from ServiceItems a where a.services.serviceId=" + services.getServiceId();
            session.createQuery(sql).executeUpdate();
            for (ServiceItems p : services.getServiceItemses()) {
                p.setServices(services);
                session.save(p);

            }
            tr.commit();
            result = "success";

        } catch (Exception e) {
            e.printStackTrace();
            result = "Some thing went wrong";
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public String getServiceNo() {
        String inv = "";
        Session session = null;

        try {
            session = MySessionFactory.getSession();
            String sql = "SELECT nextval('wms.seq_service_no')";
            List<Object> ls = session.createSQLQuery(sql).list();
            String in = ls.get(0).toString();
            String zr = "00000000";
            inv = "SER" + zr.substring(0, 8 - in.length()) + in;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return inv;
    }

    public List<Object[]> getServiceList(String item, String invNo, Date fromDate, Date todate, String mobile, int page, int sizw, String status) {
        List<Object[]> result = new ArrayList<>();
        Session session = null;

        try {
            session = MySessionFactory.getSession();
            String sql = "select a.serviceId,a.status,to_char(a.createdOn,'dd-mm-yy HH24:mi'), "
                    + " a.serviceNo,a.productName,a.custMobileNo,a.totalAmount,to_char(a.closeddOn,'dd-mm-yy HH24:mi') from Services a where 1=1 ";

            if (item != null && item.trim().length() > 0 && !item.trim().equalsIgnoreCase("All")) {
                sql += " and a.serviceId in ( select b.services.serviceId from ServiceItems b where "
                        + " b.serviceType.serviceType=:itName and  to_date(to_char(b.services.createdOn,'dd-mm-yyyy'),'dd-mm-yyyy') between :fromDate and :todate ) ";
            }
            if (invNo != null && invNo.trim().length() > 0) {
                sql += " and a.serviceNo=:purNo ";
            }

            if (mobile != null && mobile.trim().length() > 0) {
                sql += " and a.custMobileNo=:mobleno ";
            }

            if (status != null && status.trim().length() > 0 && !status.trim().equalsIgnoreCase("All")) {
                sql += " and a.status=:stat ";
            }

            if (fromDate != null && todate != null) {
                sql += " and to_date(to_char(a.createdOn,'dd-mm-yyyy'),'dd-mm-yyyy') between :fromDate and :todate ";
            }
            sql += "order by a.serviceId";
            System.err.println("Sql " + sql);
            Query q = session.createQuery(sql);

            if (item != null && item.trim().length() > 0 && !item.trim().equalsIgnoreCase("All")) {
                q.setParameter("itName", item);
            }
            if (invNo != null && invNo.trim().length() > 0) {
                q.setParameter("purNo", invNo);
            }
            if (mobile != null && mobile.trim().length() > 0) {
                q.setParameter("mobleno", mobile);
            }
            if (status != null && status.trim().length() > 0 && !status.trim().equalsIgnoreCase("All")) {
                q.setParameter("stat", status);
            }
            if (fromDate != null && todate != null) {
                q.setParameter("fromDate", fromDate);
                q.setParameter("todate", todate);
            }
            if (sizw > 0) {
                q.setFirstResult(page).setMaxResults(sizw);
            }
            result = q.list();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public Services getService(long id) {
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "from Services a where a.serviceId=" + id;
            List<Services> result = session.createQuery(sql).list();
            if (!result.isEmpty()) {
                return result.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    
    public List<Services> getServiceLis(long id) {
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "from Services a where 1=1";
            if(id>0)
            sql+=" and a.serviceId=" + id;
            return  session.createQuery(sql).list();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public List<ServiceItems> getServiceItems(long id, String item) {
        List<ServiceItems> result = new ArrayList<>();
        Session session = null;

        try {
            session = MySessionFactory.getSession();
            String sql = "from ServiceItems a where 1=1 ";

            if (item != null && item.trim().length() > 0 && !item.trim().equalsIgnoreCase("All")) {
                sql += " and a.serviceType.serviceType=:itName  ";
            }
            if (id > 0) {
                sql += " and a.services.serviceId=" + id;
            }
            sql += "order by a.serviceItemId ";
            System.err.println("Sql " + sql);
            Query q = session.createQuery(sql);

            if (item != null && item.trim().length() > 0 && !item.trim().equalsIgnoreCase("All")) {
                q.setParameter("itName", item);
            }
            result = q.list();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

}
