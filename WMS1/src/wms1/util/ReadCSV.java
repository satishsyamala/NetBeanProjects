package wms1.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import wms1.dao.LoginDao;
import wms1.pojos.Sales;
import wms1.pojos.SalesItems;
import wms1.pojos.ServiceItems;
import wms1.pojos.Services;
import wms1.pojos.Settings;

/**
 *
 * @author Satish
 */
public class ReadCSV {

    public static List<List<String>> readCSVFile(String path) {
        List<List<String>> data = new ArrayList<>();
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) //returns a Boolean value  
            {
                String[] employee = line.split(splitBy);
                List<String> row = new ArrayList<>();
                for (String s : employee) {
                    row.add(s.trim());
                }
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String writeCSVfile(List<Object[]> data, String header, String type, String fileName) {
        String filePath = null;
        try {
            if (fileName == null) {
                fileName = type.toUpperCase() + "_" + (new SimpleDateFormat("HH_mm_ss").format(new Date()));
            }
            filePath = baseFolder(type) + "/" + fileName + ".csv";
            FileWriter myWriter = new FileWriter(filePath);
            if (header != null) {
                myWriter.write(header);
                myWriter.write("\n");
            }
            for (Object[] a : data) {
                String text = "";
                for (Object b : a) {
                    text += (b != null ? b.toString() : "") + ",";
                }
                text = text.substring(0, text.length() - 1);
                myWriter.write(text);
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            filePath = null;
            e.printStackTrace();
        }
        return filePath;
    }

    public static String baseFolder(String type) {

        String res = "";
        LoginDao ld = new LoginDao();
        Settings a = ld.getSettings();
        if (a != null && a.getFileLocation() != null) {
            res = a.getFileLocation() + "/";
        } else {
            res = "D:/WMSAPP/";
        }
        try {
            if (type.equalsIgnoreCase("import")) {
                res += "IMPORTS";
            } else if (type.equalsIgnoreCase("purchases")) {
                res += "PRUCHASES";
            } else if (type.equalsIgnoreCase("sales")) {
                res += "SALES";
            } else if (type.equalsIgnoreCase("invoice")) {
                res += "INVOICE";
            } else if (type.equalsIgnoreCase("stocktake")) {
                res += "STOCKTAKE";
            } else if (type.equalsIgnoreCase("reports")) {
                res += "REPORTS";
            } else {
                res += type.toUpperCase();
            }
            res += "/" + new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
            File f = new File(res);
            if (!f.isDirectory()) {
                f.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Settings getSettings() {
        LoginDao ld = new LoginDao();
        Settings a = ld.getSettings();
        if (a == null) {
            a = new Settings();
            a.setShopName("Test Shop");
            a.setAddress("Test Shop");
            a.setMobileNo("11111111");
            a.setFileLocation("D:/WMSAPP/");
            a.setDiscounts(null);
        }
        return a;
    }

    public static String generateInvoiceBill(Sales sales) {

        Settings a = getSettings();
        String tableborder = ";font-weight: bold;;border: 2px solid black;border-collapse: collapse";
        String tableborderhead = ";border: 2px solid black;border-collapse: collapse";
        StringBuffer b = new StringBuffer();
        b.append("<html>    <body>         <div> <center><h1  style=\"line-height: 0.1;\"> " + a.getShopName()
                + "</h1><h5  style=\"line-height: 0.1;\">" + a.getAddress() + "</h5></center> ");
        b.append("<h4 style=\"text-align:right;width:100%;line-height: 0.1;\">Mobile No:" + a.getMobileNo() + "</h4> <hr style=\" border-top:  3px solid black\">         </div>");
        b.append("<center><h2>Seles Invoice</h2></center><div><table style=\"width:100%\"><tr><td><h3>Invoice No:" + sales.getInvoiceNo() + "</h3></td>");
        b.append("<td><h3 style=\"text-align:right;width:100%\">Date:" + new SimpleDateFormat("dd-MMM-yyyy").format(sales.getCreatedOn()) + "</h3></td></tr>");
        b.append(" <tr><td><h3>Name : " + sales.getCustName() + "</h3></td></tr>    ");
        b.append("<tr><td> <h3>     Mobile No : " + sales.getCustMobileNo() + "</h3></td></tr> ");
        b.append("<tr><td> <h3>     Address : " + sales.getCustAddress() + "</h3></td></tr> ");
        b.append("</table> ");
        b.append(" <hr style=\" border-top:  3px solid black\">   <table style=\"width:100%;" + tableborder + "\"> <tr style=\"" + tableborder + "\">");

        b.append("<td style=\"text-align:center;width:10%;" + tableborderhead + "\">Sr No</td>");
        b.append("<td style=\"text-align:center;" + tableborderhead + "\">Item</td>");
        b.append("<td style=\"text-align:center;width:15%;" + tableborderhead + "\">Qty.</td>");
        b.append("<td style=\"text-align:center;width:15%;" + tableborderhead + "\">Price</td>");
        b.append("<td style=\"text-align:center;width:15%;" + tableborderhead + "\">Discount</td>");
        b.append("<td style=\"text-align:center;width:15%;" + tableborderhead + "\">Amount</td></tr>");
        int i = 1;
        for (SalesItems s : sales.getSalesItems()) {
            b.append("");
            b.append("<td style=\"text-align:right;width:10%;" + tableborder + "\">" + (i++) + "</td>");
            b.append("<td style=\"text-align:left;" + tableborder + "\">" + s.getItems().getBrandId().getBrandName() + "-" + s.getItems().getItemName() + "</td>");
            b.append("<td style=\"text-align:right;width:15%;" + tableborder + "\">" + s.getQuantity() + "</td>");
            b.append("<td style=\"text-align:right;width:15%;" + tableborder + "\">" + s.getPrice() + "</td>");
            b.append("<td style=\"text-align:right;width:15%;" + tableborder + "\">" + s.getDiscount() + "</td>");
            b.append("<td style=\"text-align:right;width:15%;" + tableborder + "\">" + s.getTotalAmount() + "</td></tr>");
        }
        b.append("</table><br/><hr style=\" border-top:  3px solid black\">  <table align=\"right\" style=\"width:40%;\">  <tr>");
        b.append("<td >Amount</td> <td style=\"text-align:right\">" + sales.getAmount() + "</td> </tr>  <tr >");
        b.append("<td >Discount</td> <td style=\"text-align:right;\">" + sales.getDiscount() + "</td> 	</tr> <tr>");
        b.append("<td >Total Amt.</td> <td style=\"text-align:right;font-size:24px\">" + sales.getTotalAmount() + "</td> </tr> </table>");
        b.append("</div><br/><br/><br/><br/>");
        b.append(" <hr style=\" border-top:  3px solid black\">");
        b.append("<h4>Invoice printed on " + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date()) + "</h4>");
        b.append("</body> </html>");
        return b.toString();
    }

    public static String generateServiceBill(Services services) {
        Settings a = getSettings();
        String tableborder = ";font-weight: bold;;border: 2px solid black;border-collapse: collapse";
        String tableborderhead = ";border: 2px solid black;border-collapse: collapse";
        StringBuffer b = new StringBuffer();
        b.append("<html>       <body>         <div> <center><h1  style=\"line-height: 0.1;\">" + a.getShopName() + "</h1><h5  style=\"line-height: 0.1;\">" + a.getAddress() + "</h5></center> ");
        b.append("<h4 style=\"text-align:right;width:100%;line-height: 0.1;\">Mobile No:" + a.getMobileNo() + "</h4> <hr style=\" border-top:  3px solid black\">         </div>");
        b.append("<center><h2>" + (services.getStatus().equalsIgnoreCase("Pending") ? "Service Receipt" : "Service Invoice") + "</h2></center><div><table style=\"width:100%\"><tr><td><h3>Service No:" + services.getServiceNo() + "</h3></td>");
        b.append("<td><h3 style=\"text-align:right;width:100%\">Date:" + new SimpleDateFormat("dd-MMM-yyyy").format(services.getCreatedOn()) + "</h3></td></tr>");
        b.append(" <tr><td> <h3>Name : " + services.getCustName() + " </td></tr>    ");
        b.append("<tr><td> <h3>    Mobile No : <u>" + services.getCustMobileNo() + "</h3></td></tr>");
        b.append("<tr><td> <h3>    Address : <u>" + services.getCustAddress() + "</h3></td></tr>");
        b.append("<tr><td>  <h3>   Product Details : <u>" + services.getProductName() + "</h3></td></tr>");
        b.append("</table>");
        b.append("   <table style=\"width:100%;" + tableborder + "\"> <tr style=\"" + tableborder + "\">");

        b.append("<td style=\"text-align:center;width:10%;" + tableborderhead + "\">Sr No</td>");
        b.append("<td style=\"text-align:center;" + tableborderhead + "\">Service Type</td>");
        b.append("<td style=\"text-align:center;width:15%;" + tableborderhead + "\">Price</td>");
        b.append("<td style=\"text-align:center;width:15%;" + tableborderhead + "\">Discount</td>");
        b.append("<td style=\"text-align:center;width:15%;" + tableborderhead + "\">Amount</td></tr>");
        int i = 1;
        String stypes="";
        for (ServiceItems s : services.getServiceItemses()) {
            b.append("");
            stypes+=s.getRemarks()+", ";
            b.append("<td style=\"text-align:right;width:10%;" + tableborder + "\">" + (i++) + "</td>");
            b.append("<td style=\"text-align:left;" + tableborder + "\">" + s.getRemarks() + "</td>");
            b.append("<td style=\"text-align:right;width:15%;" + tableborder + "\">" + s.getPrice() + "</td>");
            b.append("<td style=\"text-align:right;width:15%;" + tableborder + "\">" + s.getDiscount() + "</td>");
            b.append("<td style=\"text-align:right;width:15%;" + tableborder + "\">" + s.getTotalAmount() + "</td></tr>");
            
        }
        b.append("</table><br/> <table align=\"right\" style=\"width:40%;\">  <tr>");
        b.append("<td >Amount</td> <td style=\"text-align:right\">" + services.getAmount() + "</td> </tr>  <tr >");
        b.append("<td >Discount</td> <td style=\"text-align:right;\">" + services.getDiscount() + "</td> 	</tr> <tr>");
        b.append("<td >Total Amt.</td> <td style=\"text-align:right;font-size:24px\">" + services.getTotalAmount() + "</td> </tr> </table>");
        b.append("</div> <div style=\"width:100%;bottom:0px;position: absolute;\">");
        b.append(" <hr style=\" border-top:  3px solid black\">");
        b.append("<h4>Invoice printed on " + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date()) + "</h4>");
        if (services.getStatus().equalsIgnoreCase("Pending")) {
            b.append("<br/> <hr style=\" border-top:  3px solid black\">");
            b.append("   <table style=\"width:100%\"><tr><td><h3>Service No:" + services.getServiceNo() + "</h3></td>");
            b.append("<td><h3 style=\"text-align:right;width:100%\">Date:" + new SimpleDateFormat("dd-MMM-yyyy").format(services.getCreatedOn()) + "</h3></td></tr>");
            b.append(" <tr><td> <h3>Name : " + services.getCustName() + " </td>    ");
            b.append("<td> <h3>    Mobile No : <u>" + services.getCustMobileNo() + "</h3></td></tr>");
            b.append("<tr><td> <h3>    Address : <u>" + services.getCustAddress() + "</h3></td>");
            b.append("<td>  <h3>   Product Details : <u>" + services.getProductName() + "</h3></td></tr>");
            b.append("<tr><td>  <h3>   Service Types : <u>" + stypes + "</h3></td></tr>");
            b.append("</table>");
        }

        b.append("</div></body> </html>");
        return b.toString();
    }
}
