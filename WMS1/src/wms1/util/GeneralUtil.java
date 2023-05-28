/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import wms1.dao.BrandsDao;
import wms1.dao.ItemsDao;
import wms1.pojos.Brands;
import wms1.pojos.ServiceTypes;
import wms1.view.HomePanel;

/**
 *
 * @author Aadhya
 */
public class GeneralUtil {

    public static JSONArray getUserMenus(String userType) {
        JSONArray ar = new JSONArray();
        if (userType.equalsIgnoreCase("admin")) {
            ar.add(menu("brands", "Brands"));
            ar.add(menu("items", "Items"));
            ar.add(menu("servicetype", "Service Types"));
        }
        ar.add(menu("purchases", "Purchases"));
        ar.add(menu("sales", "Sales"));
        ar.add(menu("service", "Services"));
        if (userType.equalsIgnoreCase("admin") || userType.equalsIgnoreCase("audit")) {
            ar.add(menu("stocktake", "Stock Take"));
        }
        if (userType.equalsIgnoreCase("admin")) {
            ar.add(menu("Users", "Users"));
            ar.add(menu("Configuration", "Configuration"));
        }
        ar.add(menu("report", "Reports"));
        return ar;
    }

    public static JSONArray getFormByName(String menu) {
        JSONArray f = new JSONArray();
        if (menu.equalsIgnoreCase("Users")) {
            f.add(fields("text", "User Full Name", "text", "yes"));
            f.add(fields("text", "User Id", "text", "yes"));
            f.add(fields("text", "Password", "text", "yes"));
            f.add(fields("number", "Mobile Number", "number", "no"));
            f.add(fields("dropdown", "User Type", "test", "yes", "Admin,Sales,Audit"));
        } else if (menu.equalsIgnoreCase("Brands")) {
            f.add(fields("text", "Code", "text", "yes"));
            f.add(fields("text", "Name", "text", "yes"));
        } else if (menu.equalsIgnoreCase("Items")) {

            f.add(fields("dropdown", "Brand", "test", "yes", getBrandsArray("Select")));
            f.add(fields("text", "Code", "text", "yes"));
            f.add(fields("text", "Name", "text", "yes"));
            f.add(fields("text", "Bar Code", "text", "no"));
            f.add(fields("text", "Price", "number", "yes"));
        } else if (menu.equalsIgnoreCase("Service Types")) {
            f.add(fields("text", "Type", "text", "yes"));
            f.add(fields("text", "Price", "number", "yes"));
        }
        return f;
    }

    public static String[] getBrandsArray(String value) {
        BrandsDao bd = new BrandsDao();
        List<Brands> b = bd.getBrands(0, null, null);
        String[] bs = new String[b.size() + (value != null ? 1 : 0)];
        if (value != null) {
            bs[0] = value;
        }
        int i = (value != null ? 1 : 0);

        for (Brands a : b) {
            bs[i++] = a.getBrandName();
        }
        return bs;
    }

    public static String[] getServiesTypesArray(String value) {
        BrandsDao bd = new BrandsDao();
        List<ServiceTypes> b = bd.getServiceTypes(0, null);
        String[] bs = new String[b.size() + (value != null ? 1 : 0)];
        if (value != null) {
            bs[0] = value;
        }
        int i = (value != null ? 1 : 0);

        for (ServiceTypes a : b) {
            bs[i++] = a.getServiceType();
        }
        return bs;
    }

    public static String[] getItemeArray(String value, String brand) {
        ItemsDao bd = new ItemsDao();
        List<Object[]> b = bd.getItemsDetails(0, null, null, null, brand);
        String[] bs = new String[b.size() + (value != null ? 1 : 0)];
        if (value != null) {
            bs[0] = value;
        }
        int i = (value != null ? 1 : 0);
        for (Object[] a : b) {
            bs[i++] = a[3].toString() + "-" + a[0].toString();
        }
        return bs;
    }

    public static String[] getItemeArrayPre(String value, String brand) {
        ItemsDao bd = new ItemsDao();
        List<Object[]> b = bd.getItemsDetails(0, null, null, null, brand);
        String[] bs = new String[b.size() + (value != null ? 1 : 0)];
        if (value != null) {
            bs[0] = value;
        }
        int i = (value != null ? 1 : 0);
        for (Object[] a : b) {
            bs[i++] = a[3].toString();
        }
        return bs;
    }

    public static JSONArray reports(String userType) {
        JSONArray ar = new JSONArray();
        if (userType.equalsIgnoreCase("admin")) {
            ar.add(menu("brands", "Brands"));
            ar.add(menu("items", "Items"));
            ar.add(menu("Users", "Users"));
        }
        ar.add(menu("purchases", "Purchases"));
        ar.add(menu("sales", "Sales"));
         ar.add(menu("Day Wise Sales", "Day Wise Sales"));
        if (userType.equalsIgnoreCase("admin") || userType.equalsIgnoreCase("audit")) {
            ar.add(menu("stocktake", "Stock Take"));
        }
        return ar;
    }

    public static JSONArray reportJSONArray(String name) {
        JSONArray fields = new JSONArray();
        if (name.equalsIgnoreCase("Brands")) {
            fields.add(fields("text", "Brand Name", "text", "yes"));
        } else if (name.equalsIgnoreCase("items")) {
            fields.add(fields("dropdown", "Brand", "test", "yes", getBrandsArray("All")));
            fields.add(fields("text", "Item Name", "text", "yes"));
        } else if (name.equalsIgnoreCase("users")) {
            fields.add(fields("dropdown", "User Type", "test", "yes", "Admin,Sales,Audit"));
            fields.add(fields("text", "User Name", "text", "yes"));
        } else if (name.equalsIgnoreCase("Purchases")) {
            fields.add(fields("dropdown", "Brand", "test", "yes", getBrandsArray("All")));
            fields.add(fields("dropdown", "Items", "test", "yes", "All"));
            fields.add(fields("text", "Purchase No", "text", "yes"));
            fields.add(fields("date", "From Date", "number", "yes"));
            fields.add(fields("date", "To Date", "number", "yes"));
        } else if (name.equalsIgnoreCase("Sales")) {
            fields.add(fields("dropdown", "Brand", "test", "yes", getBrandsArray("All")));
            fields.add(fields("dropdown", "Items", "test", "yes", "All"));
            fields.add(fields("text", "Invoice No", "text", "yes"));
            fields.add(fields("date", "From Date", "number", "yes"));
            fields.add(fields("date", "To Date", "number", "yes"));
        }else if (name.equalsIgnoreCase("Day Wise Sales")) {
            fields.add(fields("dropdown", "Brand", "test", "yes", getBrandsArray("All")));
            fields.add(fields("dropdown", "Items", "test", "yes", "All"));
            fields.add(fields("date", "From Date", "number", "yes"));
            fields.add(fields("date", "To Date", "number", "yes"));
        } else if (name.equalsIgnoreCase("Stock Take")) {
            fields.add(fields("dropdown", "Brand", "test", "yes", getBrandsArray("All")));
            fields.add(fields("dropdown", "Items", "test", "yes", "All"));
            fields.add(fields("date", "From Date", "number", "yes"));
            fields.add(fields("date", "To Date", "number", "yes"));
        }
        return fields;
    }

    public static JSONObject menu(String key, String name) {
        JSONObject j = new JSONObject();
        j.put("key", key);
        j.put("name", name);
        return j;
    }

    public static JSONObject fields(String type, String name, String dataType, String mand) {
        JSONObject j = new JSONObject();
        j.put("type", type);
        j.put("name", name);
        j.put("datatype", dataType);
        j.put("mand", mand);
        j.put("value", "");
        return j;
    }

    public static JSONObject fields(String type, String name, String dataType, String mand, List<String> values) {
        JSONObject j = fields(type, name, dataType, mand);
        JSONArray a = new JSONArray();
        if (values != null && !values.isEmpty()) {
            for (String s : values) {
                a.add(s);
            }
        }
        j.put("values", a);
        return j;
    }

    public static JSONObject fields(String type, String name, String dataType, String mand, String[] values) {
        JSONObject j = fields(type, name, dataType, mand);
        JSONArray a = new JSONArray();
        if (values != null && values.length > 0) {
            for (String s : values) {
                a.add(s);
            }
        }
        j.put("values", a);
        return j;
    }

    public static JSONObject fields(String type, String name, String dataType, String mand, String values) {
        JSONObject j = fields(type, name, dataType, mand);
        JSONArray a = new JSONArray();
        if (values != null && values.trim().length() > 0) {
            String vl[] = values.split(",");
            for (String s : vl) {
                a.add(s);
            }
        }
        j.put("values", a);
        return j;
    }

    public static JLabel getJLabel(String name, int x, int y, int width, int height) {
        JLabel lable = new JLabel();
        lable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lable.setText(name);
        lable.setBounds(x, y, width, height);
        return lable;
    }

    public static JTextField getJTextField(String name, int x, int y, int width, int height, String type) {
        JTextField lable = new JTextField();
        lable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lable.setText(name);
        if (type.equalsIgnoreCase("number")) {
            lable.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        }
        lable.setBounds(x, y, width, height);
        return lable;
    }

    public static JComboBox getJComboBox(String name, int x, int y, int width, int height, String[] values) {
        JComboBox lable = new JComboBox();
        lable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lable.setModel(new javax.swing.DefaultComboBoxModel(values));
        lable.setSelectedItem(name);
        lable.setBounds(x, y, width, height);
        return lable;
    }

    public static JComboBox getJComboBox(String name, int x, int y, int width, int height, String values) {
        return getJComboBox(name, x, y, width, height, values.split(","));
    }

    public static JComboBox getJComboBox(String name, int x, int y, int width, int height, JSONArray values) {
        String[] v = new String[values.size()];
        for (int i = 0; i < values.size(); i++) {
            v[i] = values.get(i).toString();
        }
        return getJComboBox(name, x, y, width, height, v);
    }

    public static JXDatePicker getJXDatePicker(String name, int x, int y, int width, int height, Date date) {
        JXDatePicker dateFrom = new JXDatePicker();
        dateFrom.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dateFrom.setFormats("dd-MM-yyyy");
        if (date == null) {
            dateFrom.setDate(new Date());
        } else {
            dateFrom.setDate(date);
        }
        dateFrom.setBounds(x, y, width, height);
        return dateFrom;
    }

    public static String[] getTableheaders(String menu) {
        if (menu.equalsIgnoreCase("Users")) {
            return new String[]{"Id", "User Id", "Full Name", "Mobile No.", "User Type", "Last Login"};
        } else if (menu.equalsIgnoreCase("Brands")) {
            return new String[]{"Id", "Code", "Name"};
        } else if (menu.equalsIgnoreCase("Items")) {
            return new String[]{"Id", "Brand", "Code", "Name", "Bar Code", "Price"};
        } else if (menu.equalsIgnoreCase("Service Types")) {
            return new String[]{"Id", "Type", "Price"};
        }
        return null;
    }

    public static String[][] ListToStringA(List<Object[]> data, int size) {
        String[][] res = new String[data.size()][size];
        for (int i = 0; i < data.size(); i++) {
            Object[] r = data.get(i);
            for (int j = 0; j < size; j++) {
                res[i][j] = r[j] != null ? r[j].toString() : "";
            }
        }
        return res;
    }

    public static Date getCurrentDate() {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
            return sd.parse(sd.format(new Date()));
        } catch (ParseException ex) {
            Logger.getLogger(GeneralUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Date();
    }

    public static void setDailBound(javax.swing.JDialog d, HomePanel hp, int w, int h) {
        int x = (hp.getMainWindow().getWidth() - w) / 2;
        int y = ((hp.getMainWindow().getHeight() - h) / 2);
        d.setBounds(x, y, w, h);
        d.setSize(w, h);
        d.setModal(true);
        d.repaint();
    }

}
