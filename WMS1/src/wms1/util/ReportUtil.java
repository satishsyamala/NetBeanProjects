/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Aadhya
 */
public class ReportUtil {

    public static String[] getReportHeaders(String menu) {
        if (menu.equalsIgnoreCase("Users")) {
            return new String[]{"Id", "User Id", "Full Name", "Mobile No.", "User Type", "Last Login"};
        } else if (menu.equalsIgnoreCase("Brands")) {
            return new String[]{"Id", "Code", "Name"};
        } else if (menu.equalsIgnoreCase("Items")) {
            return new String[]{"Id", "Brand", "Code", "Name", "Bar Code", "Price"};
        } else if (menu.equalsIgnoreCase("Service Types")) {
            return new String[]{"Id", "Type", "Price"};
        } else if (menu.equalsIgnoreCase("Purchases")) {
            return new String[]{"id", "Date", "No.", "Brand", "Item", "Quantity"};
        } else if (menu.equalsIgnoreCase("Sales")) {
            return new String[]{"id", "Date", "Invoice No.", "Mobile No", "Brand", "Item", "Qty.", "Price", "Amount", "Discount", "Total Amt."};
        } else if (menu.equalsIgnoreCase("Day Wise Sales")) {
            return new String[]{"Date", "Brand", "Item", "Qty.","Discount", "Total Amt."};
        }else if (menu.equalsIgnoreCase("Stock Take")) {
            return new String[]{"Id", "Date", "Brand", "Item", "Price", "OB", "Purchases", "Sales", "CB", "ST Qty.", "Adj. Qty", "Adj. Amount", "Remarks"};
        }
        return null;
    }

    public static JSONObject getReportQueries(String name) {
        System.out.println("name : " + name);
        if (name.equalsIgnoreCase("brands")) {
            return brandQuery();
        } else if (name.equalsIgnoreCase("Items")) {
            return itemQuery();
        } else if (name.equalsIgnoreCase("Sales")) {
            return salesQuery();
        } else if (name.equalsIgnoreCase("Day Wise Sales")) {
            return dayWiseSalesQuery();
        } else {
            return null;
        }
    }
     public static JSONObject dayWiseSalesQuery() {
        JSONObject o = new JSONObject();
        o.put("main", "select to_char(a.sales.createdOn,'dd-mon-yyyy'),a.items.brandId.brandName,a.items.itemName,sum(a.quantity),sum(a.discount),sum(a.totalAmount) From SalesItems a where 1=1");
        o.put("brand", " and a.items.brandId.brandName = :brand_ft");
        o.put("items", " and a.items.itemName = :items_ft");
        o.put("fromdate", " and " + toDate("a.sales.createdOn") + " >= :fromdate_ft");
        o.put("todate", " and " + toDate("a.sales.createdOn") + " <= :todate_ft");
        o.put("order", " group by to_char(a.sales.createdOn,'dd-mon-yyyy'),a.items.brandId.brandName,a.items.itemName order by to_char(a.sales.createdOn,'dd-mon-yyyy'),a.items.brandId.brandName,a.items.itemName ");
        JSONArray low = new JSONArray();
        o.put("lower", low);
        return o;
    }
    
    public static JSONObject salesQuery() {
        JSONObject o = new JSONObject();
        o.put("main", "select a.salesItemId,to_char(a.sales.createdOn,'dd-MM-yy HH24:mi'),a.sales.invoiceNo,a.sales.custMobileNo,a.items.brandId.brandName,a.items.itemName,a.quantity,a.price,a.amount,a.discount,a.totalAmount From SalesItems a where 1=1");
        o.put("brand", " and a.items.brandId.brandName = :brand_ft");
        o.put("items", " and a.items.itemName = :items_ft");
        o.put("invoiceno", " and a.sales.invoiceNo = :invoiceno_ft");
        o.put("fromdate", " and " + toDate("a.sales.createdOn") + " >= :fromdate_ft");
        o.put("todate", " and " + toDate("a.sales.createdOn") + " <= :todate_ft");
        o.put("order", " order by a.salesItemId ");
        JSONArray low = new JSONArray();

        o.put("lower", low);
        return o;
    }

   

    public static JSONObject itemQuery() {
        JSONObject o = new JSONObject();
        o.put("main", "select a.itemId,a.brandId.brandName,a.itemCode,a.itemName,a.barCode,a.price From Items a where 1=1");
        o.put("brand", " and a.brandId.brandName = :brand_ft");
        o.put("itemname", " and lower(a.itemName) like :itemname_ft||'%'");
        o.put("order", " order by lower(a.itemName) ");
        JSONArray low = new JSONArray();
        low.add("ttemname");
        o.put("lower", low);
        return o;
    }

    public static JSONObject brandQuery() {
        JSONObject o = new JSONObject();
        o.put("main", "select a.brandId,a.brandCode,a.brandName From Brands a where 1=1 ");
        o.put("brandname", " and lower(a.brandName) like :brandname_ft||'%'");
        o.put("order", " order by lower(a.brandName) ");
        JSONArray low = new JSONArray();
        low.add("brandname");
        o.put("lower", low);
        return o;
    }

    public static String conKey(String key) {
        return key.replaceAll(" ", "").toLowerCase();
    }

    public static String toDate(String d) {
        return "to_date(to_char(" + d + ",'dd-mm-yyyy'),'dd-mm-yyyy')";
    }

}
