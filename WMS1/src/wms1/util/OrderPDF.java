/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import wms1.dao.ItemsDao;
import wms1.pojos.Sales;
import wms1.pojos.SalesItems;
import wms1.pojos.ServiceItems;
import wms1.pojos.Services;
import wms1.pojos.Settings;

/**
 *
 * @author USER
 */
public class OrderPDF {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(OrderPDF.class);

    public static File salesPdf(Sales sales) {
        Document document = new Document();
        Settings settings = ReadCSV.getSettings();
        File pdf = new File(ReadCSV.baseFolder("INVOICE") + "/" + sales.getInvoiceNo() + ".pdf");
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf));

            document.open();
            PdfContentByte cb = writer.getDirectContent();
            Rectangle pageSize = new Rectangle(0, 780, 600, 850);
            pageSize.setBackgroundColor(BaseColor.LIGHT_GRAY);
            document.add(pageSize);
            document.add(new Paragraph("   "));
            createContent(cb, 300, 820, settings.getShopName(), Element.ALIGN_CENTER, 15);
            createContentheader(cb, 300, 810, settings.getAddress(), Element.ALIGN_CENTER, 8);
            createContent(cb, 560, 790, "Moble No " + settings.getMobileNo(), Element.ALIGN_RIGHT, 10);
            createContentheader(cb, 300, 760, "Sales Invoice", Element.ALIGN_CENTER, 12);
            createContentheader(cb, 40, 730, "Invoice No", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 140, 730, ": " + sales.getInvoiceNo(), Element.ALIGN_LEFT, 10);
            createContentheader(cb, 560, 730, "Date : " + new SimpleDateFormat("dd-MMM-yyyy").format(sales.getCreatedOn()), Element.ALIGN_RIGHT, 10);
            createContentheader(cb, 40, 710, "Name", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 140, 710, ": " + sales.getCustName(), Element.ALIGN_LEFT, 10);
            createContentheader(cb, 40, 690, "Moblie No", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 140, 690, ": " + sales.getCustMobileNo(), Element.ALIGN_LEFT, 10);
            createContentheader(cb, 40, 670, "Address", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 140, 670, ": " + sales.getCustAddress(), Element.ALIGN_LEFT, 10);
            createContentheader(cb, 20, 60, "_______________________________________________________________________________________________________________________________", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 20, 50, "Inovie downloaded on " + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date()), Element.ALIGN_LEFT, 8);

            addTable(document, sales);

            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdf;
    }

    public static File servicePdf(Services sales) {
        Document document = new Document();
        Settings settings = ReadCSV.getSettings();
        File pdf = new File(ReadCSV.baseFolder("SERVICE_TYPE") + "/" + sales.getServiceNo() + ".pdf");
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf));

            document.open();
            PdfContentByte cb = writer.getDirectContent();
            Rectangle pageSize = new Rectangle(0, 780, 600, 850);
            pageSize.setBackgroundColor(BaseColor.LIGHT_GRAY);
            document.add(pageSize);
            document.add(new Paragraph("   "));
            createContent(cb, 300, 820, settings.getShopName(), Element.ALIGN_CENTER, 15);
            createContentheader(cb, 300, 810, settings.getAddress(), Element.ALIGN_CENTER, 8);
            createContent(cb, 560, 790, "Moble No " + settings.getMobileNo(), Element.ALIGN_RIGHT, 10);
            if (sales.getStatus().equalsIgnoreCase("Pending")) {
                createContentheader(cb, 300, 760, "Service Receipt", Element.ALIGN_CENTER, 12);
            } else {
                createContentheader(cb, 300, 760, "Service Invoice", Element.ALIGN_CENTER, 12);
            }
            createContentheader(cb, 40, 730, "Service No", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 140, 730, ": " + sales.getServiceNo(), Element.ALIGN_LEFT, 10);
            createContentheader(cb, 560, 730, "Date : " + new SimpleDateFormat("dd-MMM-yyyy").format(sales.getCreatedOn()), Element.ALIGN_RIGHT, 10);
            createContentheader(cb, 40, 710, "Name", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 140, 710, ": " + sales.getCustName(), Element.ALIGN_LEFT, 10);
            createContentheader(cb, 40, 690, "Moblie No", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 140, 690, ": " + sales.getCustMobileNo(), Element.ALIGN_LEFT, 10);
            createContentheader(cb, 40, 670, "Address", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 140, 670, ": " + sales.getCustAddress(), Element.ALIGN_LEFT, 10);
            createContentheader(cb, 40, 650, "Product Details", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 140, 650, ": " + sales.getProductName(), Element.ALIGN_LEFT, 10);
           if(sales.getStatus().equalsIgnoreCase("Closed"))
           {
            createContentheader(cb, 20, 60, "_______________________________________________________________________________________________________________________________", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 20, 50, "Inovie downloaded on " + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date()), Element.ALIGN_LEFT, 8);
           }else{
            createContentheader(cb, 20, 200, "_______________________________________________________________________________________________________________________________", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 20, 190, "Inovie downloaded on " + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date()), Element.ALIGN_LEFT, 8);
            createContentheader(cb, 20, 170, "----X---------Service details----------------------------------------------------------------------------------------------------------------------------------------------------", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 40, 150, "Service No", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 140, 150, ": " + sales.getServiceNo(), Element.ALIGN_LEFT, 10);
            createContentheader(cb, 560, 150, "Date : " + new SimpleDateFormat("dd-MMM-yyyy").format(sales.getCreatedOn()), Element.ALIGN_RIGHT, 10);
            createContentheader(cb, 40, 130, "Name", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 140, 130, ": " + sales.getCustName(), Element.ALIGN_LEFT, 10);
            createContentheader(cb, 40, 110, "Moblie No", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 140, 110, ": " + sales.getCustMobileNo(), Element.ALIGN_LEFT, 10);
            createContentheader(cb, 40, 90, "Product Details", Element.ALIGN_LEFT, 10);
            createContentheader(cb, 140, 90, ": " + sales.getProductName(), Element.ALIGN_LEFT, 10);
            String ts="";
            for(ServiceItems s:sales.getServiceItemses())
                ts+=s.getRemarks()+". ";
            createContentheader(cb, 40, 70, "Types : "+ts, Element.ALIGN_LEFT, 8);
           }
            addTable(document, sales);

            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdf;
    }

    public static void addTable(Document document, Services sales) {
        try {
            PdfPTable table = new PdfPTable(5); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(150f); //Space before table
            float[] columnWidths = {1f, 4f, 2f, 2f, 2f};
            table.setWidths(columnWidths);
            table.addCell(getPCell("Sr.No.", Element.ALIGN_LEFT));
            table.addCell(getPCell("Service Type", Element.ALIGN_LEFT));
            table.addCell(getPCell("Price", Element.ALIGN_RIGHT));
            table.addCell(getPCell("Discount", Element.ALIGN_RIGHT));
            table.addCell(getPCell("Amount", Element.ALIGN_RIGHT));
            int i = 1;
            for (ServiceItems s : sales.getServiceItemses()) {
                table.addCell(getPCell((i) + "", Element.ALIGN_RIGHT));
                table.addCell(getPCell(s.getRemarks(), Element.ALIGN_LEFT));
                table.addCell(getPCell(s.getPrice() + "", Element.ALIGN_RIGHT));
                table.addCell(getPCell(s.getDiscount() + "", Element.ALIGN_RIGHT));
                table.addCell(getPCell(s.getTotalAmount() + "", Element.ALIGN_RIGHT));
            }
            document.add(table);
            float[] columnWidths1 = {2.2f, 1.2f};
            PdfPTable table1 = new PdfPTable(2); // 3 columns.
            table1.setWidthPercentage(30); //Width 100%
            table1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table1.setWidths(columnWidths1);
            table1.addCell(getPCell("Amount", Element.ALIGN_LEFT));
            table1.addCell(getPCell(sales.getAmount() + "", Element.ALIGN_RIGHT));
            table1.addCell(getPCell("Discount", Element.ALIGN_LEFT));
            table1.addCell(getPCell(sales.getDiscount() + "", Element.ALIGN_RIGHT));
            table1.addCell(getPCell("Inv. Amt.", Element.ALIGN_LEFT));
            table1.addCell(getPCell(sales.getTotalAmount() + "", Element.ALIGN_RIGHT));
            document.add(table1);

        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    private static void createContent(PdfContentByte cb, float x, float y, String text, int align, int size) {
        cb.beginText();
        cb.setFontAndSize(getBaseFont(), size);
        cb.showTextAligned(align, text.trim(), x, y, 0);
        cb.endText();
    }

    private static void createContentheader(PdfContentByte cb, float x, float y, String text, int align, int size) {
        cb.beginText();
        cb.setFontAndSize(getHeaderBaseFont(), size);
        cb.showTextAligned(align, text.trim(), x, y, 0);
        cb.endText();
    }

    private static BaseFont getHeaderBaseFont() {
        try {
            return BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        } catch (Exception ex) {
            return null;
        }
    }

    private static BaseFont getBaseFont() {
        try {
            return BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        } catch (Exception ex) {
            return null;
        }
    }

    public static void addbarcode(Document document, PdfContentByte cb, String text, int x, int y) {
        try {

            Barcode128 barcode128 = new Barcode128();
            barcode128.setCode(text);
            barcode128.setCodeType(Barcode.CODE128);
            Image code128Image = barcode128.createImageWithBarcode(cb, null, null);
            code128Image.setAbsolutePosition(x, y);
            code128Image.scaleToFit(190, 190);
            document.add(code128Image);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public static void addTable(Document document, Sales sales) {
        try {
            PdfPTable table = new PdfPTable(7); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(140f); //Space before table
            float[] columnWidths = {1f, 2f, 4f, 1f, 1f, 1.2f, 1.2f};
            table.setWidths(columnWidths);
            table.addCell(getPCell("Sr.No.", Element.ALIGN_LEFT));
            table.addCell(getPCell("Brand", Element.ALIGN_LEFT));
            table.addCell(getPCell("Item", Element.ALIGN_LEFT));
            table.addCell(getPCell("Price", Element.ALIGN_LEFT));
            table.addCell(getPCell("Qty.", Element.ALIGN_RIGHT));
            table.addCell(getPCell("Discount", Element.ALIGN_RIGHT));
            table.addCell(getPCell("Amount", Element.ALIGN_RIGHT));
            int i = 1;
            for (SalesItems s : sales.getSalesItems()) {
                table.addCell(getPCell((i) + "", Element.ALIGN_RIGHT));
                table.addCell(getPCell(s.getItems().getBrandId().getBrandName(), Element.ALIGN_LEFT));
                table.addCell(getPCell(s.getItems().getItemName(), Element.ALIGN_LEFT));
                table.addCell(getPCell(s.getPrice() + "", Element.ALIGN_LEFT));
                table.addCell(getPCell(s.getQuantity() + "", Element.ALIGN_RIGHT));
                table.addCell(getPCell(s.getDiscount() + "", Element.ALIGN_RIGHT));
                table.addCell(getPCell(s.getTotalAmount() + "", Element.ALIGN_RIGHT));
            }
            document.add(table);
            float[] columnWidths1 = {2.2f, 1.2f};
            PdfPTable table1 = new PdfPTable(2); // 3 columns.
            table1.setWidthPercentage(30); //Width 100%
            table1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table1.setWidths(columnWidths1);
            table1.addCell(getPCell("Amount", Element.ALIGN_LEFT));
            table1.addCell(getPCell(sales.getAmount() + "", Element.ALIGN_RIGHT));
            table1.addCell(getPCell("Discount", Element.ALIGN_LEFT));
            table1.addCell(getPCell(sales.getDiscount() + "", Element.ALIGN_RIGHT));
            table1.addCell(getPCell("Inv. Amt.", Element.ALIGN_LEFT));
            table1.addCell(getPCell(sales.getTotalAmount() + "", Element.ALIGN_RIGHT));
            document.add(table1);

        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    public static PdfPCell getPCell(String text, int align, int barder) {
        PdfPCell cell3 = new PdfPCell(new Paragraph(text));
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(align);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell3.setBorder(barder);
        return cell3;
    }

    public static PdfPCell getPCell(String text, int align) {
        try {
            Font font = new Font(getHeaderBaseFont(), 9);
            PdfPCell cell3 = new PdfPCell(new Paragraph(text, font));
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(align);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            return cell3;
        } catch (Exception ex) {

        }
        return null;
    }

    public static void main(String[] args) {
        try {
            ItemsDao id = new ItemsDao();
            List<Sales> sales = id.getSalesList(0);
            if (sales != null && !sales.isEmpty()) {
                Sales s = sales.get(0);
                List<SalesItems> ls = id.getSalesItems(s.getSalesId(), null, null);
                s.setSalesItems(ls);
                //salesPdf(s);
            }
            List<Services> serv=id.getServiceLis(0);
            if (serv != null && !serv.isEmpty()) {
                Services s = serv.get(0);
                List<ServiceItems> ls = id.getServiceItems(s.getServiceId(), null);
                s.setServiceItemses(ls);
                servicePdf(s);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

}
