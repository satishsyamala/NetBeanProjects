/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wms1.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wms1.pojos.MySessionFactory;
import wms1.pojos.SalesItems;
import wms1.pojos.StockTakAdj;
import wms1.pojos.StockTakItems;
import wms1.pojos.StockTake;

/**
 *
 * @author Satish
 */
public class StockTakeDao {

    public StockTake lastStockTake(Date date) {
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "From StockTake a where 1=1 ";
            if (date != null) {
                sql += " and a.stockTakedOn<:on_date";
            }
            sql += " order by a.stockTakedOn desc ";
            Query q = session.createQuery(sql);
            if (date != null) {
                q.setParameter("on_date", date);
            }

            List<StockTake> itemsList = q.setMaxResults(1).list();
            if (!itemsList.isEmpty()) {
                return itemsList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public List<StockTakItems> getStockTakeItems(long id, long itemId) {
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "from StockTakItems a where 1=1";
            if (id > 0) {
                sql += " and a.stockTake.stockTakeId=" + id;
            }
            if (itemId > 0) {
                sql += " and a.items.itemId=" + itemId;
            }
            return session.createQuery(sql).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public List<Object[]> getStockTakeItemsArr(long id, long itemId) {
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "select a.items.itemId, sum(a.quantity) from StockTakItems a where 1=1";
            if (id > 0) {
                sql += " and a.stockTake.stockTakeId=" + id;
            }
            if (itemId > 0) {
                sql += " and a.items.itemId=" + itemId;
            }
            sql += " group by a.items.itemId ";
            return session.createQuery(sql).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public List<Object[]> getPurchasesByDate(long id, Date fromDate, Date todate) {
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "select a.items.itemId,sum(a.quantity) from PurchasesItems a where 1=1";
            if (id > 0) {
                sql += " and a.items.itemId=" + id;
            }
            if (fromDate != null && todate != null) {
                sql += " and to_date(to_char(a.purchases.createdOn,'dd-mm-yyyy'),'dd-mm-yyyy') between :fromDate and :todate ";
            } else if (todate != null) {
                sql += " and to_date(to_char(a.purchases.createdOn,'dd-mm-yyyy'),'dd-mm-yyyy') < :fromDate ";
            }
            sql += " group by a.items.itemId ";
            Query q = session.createQuery(sql);
            if (fromDate != null && todate != null) {
                q.setParameter("fromDate", fromDate).setParameter("todate", todate);
            } else if (todate != null) {
                q.setParameter("fromDate", todate);
            }
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public List<Object[]> getSalesByDate(long id, Date fromDate, Date todate) {
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "select a.items.itemId,sum(a.quantity) from SalesItems a where 1=1";
            if (id > 0) {
                sql += " and a.items.itemId=" + id;
            }
            if (fromDate != null && todate != null) {
                sql += " and to_date(to_char(a.sales.createdOn,'dd-mm-yyyy'),'dd-mm-yyyy') between :fromDate and :todate ";
            } else if (todate != null) {
                sql += " and to_date(to_char(a.sales.createdOn,'dd-mm-yyyy'),'dd-mm-yyyy') < :fromDate ";
            }
            sql += " group by a.items.itemId ";
            Query q = session.createQuery(sql);
            if (fromDate != null && todate != null) {
                q.setParameter("fromDate", fromDate).setParameter("todate", todate);
            } else if (todate != null) {
                q.setParameter("fromDate", todate);
            }
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public String saveStockTake(StockTake st) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            ItemsDao itd = new ItemsDao();
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            session.save(st);
            for (StockTakAdj p : st.getStockTakAdjs()) {
                StockTakItems sti = new StockTakItems();
                sti.setStockTake(st);
                sti.setItems(p.getItems());
                sti.setQuantity(p.getStQuantity());
                session.save(sti);
                p.setStockTake(st);
                session.save(p);
                itd.updateInvetory(p.getItems(), p.getAdjQuantity(), session);
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

    public List<Object[]> getStockList(String brand, String item, Date fromDate, Date todate, int page, int sizw,boolean isDeff) {
        List<Object[]> result = new ArrayList<>();
        Session session = null;

        try {
            session = MySessionFactory.getSession();
            String sql = "select a.stockTakeAdjId,to_char(a.stockTake.stockTakedOn,'dd-mm-yy'),a.items.brandId.brandName, "
                    + "a.items.itemName,a.items.price,a.openBal,a.purchases,a.sales,a.closeBal,a.stQuantity,a.adjQuantity,(a.items.price*a.adjQuantity)||'',a.remarks from StockTakAdj a where 1=1 ";
            if (brand != null && brand.trim().length() > 0 && !brand.trim().equalsIgnoreCase("All")) {
                sql += " and a.items.brandId.brandName=:brName ";
            }
            if (item != null && item.trim().length() > 0 && !item.trim().equalsIgnoreCase("All")) {
                sql += " and a.items.itemName=:itName ";
            }
            if(isDeff)
            {
                sql += " and a.adjQuantity!=0 ";
            }
            if (fromDate != null && todate != null) {
                sql += " and to_date(to_char(a.stockTake.stockTakedOn,'dd-mm-yyyy'),'dd-mm-yyyy') between :fromDate and :todate ";
            }
            sql += "order by a.stockTakeAdjId";
            System.err.println("Sql " + sql);
            Query q = session.createQuery(sql);
            if (brand != null && brand.trim().length() > 0 && !brand.trim().equalsIgnoreCase("All")) {
                q.setParameter("brName", brand);
            }
            if (item != null && item.trim().length() > 0 && !item.trim().equalsIgnoreCase("All")) {
                q.setParameter("itName", item);
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

}
