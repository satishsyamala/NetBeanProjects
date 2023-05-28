/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hes.util;

import com.hes.model.CompanyModel;
import com.hes.model.InvoiceModel;
import com.hes.model.SupplierModel;
import com.hes.pojo.OtherInvoiceData;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SATISH SYAMALA
 */
public class TCEInvoice {

    private BaseFont bfBold;
    private BaseFont bf;
    int rectX = 20;
    int recty = 20;
    int recWdth = 550;
    int rectHt = 800;
    int totalDut = 0;
    double tatalAmount = 0;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm a");
    DecimalFormat df = new DecimalFormat("0.0000");

    public static void main(String[] args) {

        String pdfFilename = "";
        TCEInvoice generateInvoice = new TCEInvoice();
        List<ProductDto> ls = new ArrayList<ProductDto>();
        ProductDto pd = new ProductDto();
        pd.setProductName("T&RED E C G 250-35");
        pd.setPackSize(210);
        pd.setNoOfPackets(5);
        pd.setProductCode("1234657");
        pd.setIdentifyMark("Barrel");
        pd.setQuantity(pd.getNoOfPackets() * pd.getPackSize());
        pd.setSalesPrice(166.5);
        pd.setPurchasePrice(158);
        pd.setDiscount(10.0);
        pd.setOtherCharges(2);
        ls.add(pd);


        InvoiceModel cust = new InvoiceModel();
        CompanyModel cm = new CompanyModel();
        SupplierModel sm = new SupplierModel();
        GenerateBillDto dto = new GenerateBillDto();
        dto.setCompany(cm.getCompayList().get(0));
        dto.setProductDtos(ls);
        dto.setCustomer(cust.getCustomerList().get(0));
        dto.setSupplier(sm.getSupplierList().get(0));
        dto.setExciseInvNo("2345678");
        dto.setModeOfTrns("ROAD");
        dto.setVehicleRegNo("AP09-BF4331");
        dto.setSuppInvBill("23435467");
        dto.setSuppInvAmount(12345677);
        dto.setEnrtyPageNo("ADGST8765789");
        dto.setDateOfRemovelGoods("02-06-2014 10:03 AM");
        dto.setInvoiceDate("01-08-2014 10:03 AM");
        dto.setInvoiceNumber("4534646");
        dto.setCustDiscount(10.0);
        dto.setPoNo("432156");
        dto.setPoDate("1-08-2014");
        dto.setRrorLrNo("ABC5432");
        dto.setDispachedDate("1-08-2014");
        dto.setDispachedFrom("Mr.ABCD FEGHI");
        dto.setDispachedTo("Chennai");
        dto.setPayByotMS("Usha ConDuctores Pvt Lid.");
        dto.setBeckInvNo("IN1234567");
        dto.setBeckInvDate("1-08-2014");
        dto.setBeckInvAmount(34545);
        dto.setPayRs(2000);
        dto.setVatOrCst(1);
        dto.setDocumentThrough("XYZ");
        dto.setCustDiscount(0);
        dto.setExciseDuty(true);
        dto.setProDiscount(2);
        dto.setProOtherCharges(1);




        generateInvoice.genBill(dto);


    }

    public static GenerateBillDto genBill(GenerateBillDto dto) {

        String pdfFilename = "";
        TCEInvoice endorsement = new TCEInvoice();
        CompanyModel cm = new CompanyModel();
        dto.setCompany(cm.getCompayList().get(0));
        pdfFilename = "d:/HES_INVOICE/" + dto.getInvoiceNumber() + "/";
        if (!(new File(pdfFilename).exists())) {
            (new File(pdfFilename)).mkdirs();
        }

        pdfFilename += "Invoice.pdf";
        endorsement.createPDF(pdfFilename, dto);
        try {
            if ((new File(pdfFilename)).exists()) {
                Process p;
                p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFilename);
                p.waitFor();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dto;
    }

    @SuppressWarnings("CallToThreadDumpStack")
    private GenerateBillDto createPDF(String pdfFilename, GenerateBillDto dto) {

        Document doc = new Document();
        PdfWriter docWriter = null;
        initializeFonts();
        DecimalFormat df = new DecimalFormat("0.0000");
        try {
            String path = pdfFilename;
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.addTitle("Invoice");
            doc.setPageSize(PageSize.A4);
            doc.open();
            PdfContentByte cb = docWriter.getDirectContent();
            cb.setLineWidth(1f);
            cb.rectangle(rectX, recty, recWdth, rectHt);
            createHeadings(cb, 250, rectHt, "INVOICE", 18, PdfContentByte.ALIGN_LEFT, bfBold);
            // createHeadings(cb, recWdth + 18, rectHt + 10, "PRIPLICATE FOR CENTRAL EXCISE", 8, PdfContentByte.ALIGN_RIGHT, bfBold);
            createHeadings(cb, 190, rectHt - 15, "(Under Rule 11 of Central Excise Rules 2002)", 10, PdfContentByte.ALIGN_LEFT, bf);
            createHeadings(cb, 200, rectHt - 30, "1st Stage Dealer / 2nd Stage Dealer", 10, PdfContentByte.ALIGN_LEFT, bfBold);
            createHeadings(cb, recWdth + 18, rectHt - 30, "INVOICE NO :" + dto.getExciseInvNo(), 10, PdfContentByte.ALIGN_RIGHT, bfBold);

            /*************    Block2  ***************/
            int black1 = 60;
            setBlack(cb, black1);

            /*************    Block2  ***************/
            int black2 = black1 + 62;
            setBlack(cb, black2);
            cb.moveTo(260, (recty + rectHt - black1));
            cb.lineTo(260, (recty + rectHt - black2));
            setBlack2Data(cb, dto);

            /*************    Block3  ***************/
            int black3 = black2 + 90;
            setBlack(cb, black3);
            setBlack3Data(cb, dto);
            cb.moveTo(260, (recty + rectHt - black2));
            cb.lineTo(260, (recty + rectHt - black3));

            cb.moveTo(rectX, (recty + rectHt - black2 - 15));
            cb.lineTo(260, (recty + rectHt - black2 - 15));

            cb.moveTo(rectX, (recty + rectHt - black2 - 75));
            cb.lineTo(260, (recty + rectHt - black2 - 75));

            cb.moveTo(130, (recty + rectHt - black2));
            cb.lineTo(130, (recty + rectHt - black2 - 75));

            /*************    Block4  ***************/
            int black4 = black3 + 130;
            setBlack(cb, black4);
            setBlack(cb, black3 + 35);
            setBlackVer(cb, black3, 50, black4);
            setBlackVer(cb, black3, 200, black4);
            setBlackVer(cb, black3, 240, black4);
            setBlackVer(cb, black3, 280, black4);
            setBlackVer(cb, black3, 320, black4);
            setBlackVer(cb, black3, 360, black4);
            setBlackVer(cb, black3, 400, black4);
            setBlackVer(cb, black3, 440, black4);
            setBlackVer(cb, black3, 480, black4);
            setBlack4Data(cb, dto, rectHt - 200);


            /*************    Block5  ***************/
            int black5 = black4 + 15;
            setBlack(cb, black5);
            createHeadings(cb, 30, rectHt - black4 + 10, "Value In Rs." + tatalAmount + " (" + NumberToWord.getNuberInWord((int) tatalAmount) + " Rupees only)", 10, PdfContentByte.ALIGN_LEFT, bfBold);
            /*************    Block6  ***************/
            int black6 = black5 + 90;
            setBlack(cb, black6);
            setBlackVer(cb, black5, 295, black6);
            setBlack6Data(cb, dto, rectHt - (black3 + 135));
            setBlack5Data(cb, dto, rectHt - (black5 + 95));

            /*************    Block7  ***************/
            int black7 = black6 + 15;
            setBlack(cb, black7);
            createHeadings(cb, 25, (rectHt - black6 + 10), "3 & 4 Column not applicable", 10, PdfContentByte.ALIGN_LEFT, bfBold);

            /*************    Block8  ***************/
            int black8 = black7 + 90;
            setBlack(cb, black8);
            setBlackVer(cb, black7, 295, black8);

            /*************    Block9  ***************/
            int black9 = black8 + 130;
            setBlack(cb, black9);
            setBlack(cb, black8 + 35);
            setBlackVer(cb, black8, 50, black9);
            setBlackVer(cb, black8, 200, black9);
            setBlackVer(cb, black8, 240, black9);
            setBlackVer(cb, black8, 280, black9);
            setBlackVer(cb, black8, 320, black9);
            setBlackVer(cb, black8, 360, black9);
            setBlackVer(cb, black8, 400, black9);
            setBlackVer(cb, black8, 440, black9);
            setBlackVer(cb, black8, 480, black9);
            setBlack4Data(cb, dto, rectHt - black8 + 13);
            /*************    Block10  ***************/
            int black10 = black9 + 15;
            setBlack(cb, black10);
            createHeadings(cb, 30, rectHt - black9 + 10, "Value In Rs." + dto.getSuppInvAmount() + " (" + NumberToWord.getNuberInWord(dto.getSuppInvAmount()) + "  Rupees only)", 10, PdfContentByte.ALIGN_LEFT, bfBold);
            setFinalBack(cb, dto, rectHt - black9 - 10);



            cb.stroke();

        } catch (DocumentException dex) {
            dex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (docWriter != null) {
                docWriter.close();
            }
        }
        return dto;
    }

    private void setBlack2Data(PdfContentByte cb, GenerateBillDto dto) {
        // String addree[] = ("1) Name & Address of Registered Person,M/s " + dto.getCompany().getName() + "," + dto.getCompanyAddress() + ",Phno: " + dto.getCompanyPhno()).split(","); //Satish Syamala,Mariyapuram,Gessugonda,Warangal-TS,506330 ";
        String st = ("1) Name & Address of the Registered Person,M/s " + dto.getCompany().getName() + "," + dto.getCompany().getAddress() + ",Phno: " + dto.getCompany().getLandLine());
        setAdderss(cb, dto, 25, (rectHt - 50), st, 10, bfBold, 35);

        /* int len = 0;
        for (int k = 0; k < addree.length; k++) {
        String ss = addree[k];
        if (k < addree.length - 1) {
        if (ss.length() < 40) {
        k++;
        ss += ", " + addree[k];
        if (ss.length() > 40) {
        k--;
        ss = addree[k];
        }
        }
        }
        if (len == 0) {
        createHeadings(cb, 25, ((rectHt - 50) - (len * 12)), ss, 10, PdfContentByte.ALIGN_LEFT, bfBold);
        } else {
        createHeadings(cb, 30, ((rectHt - 52) - (len * 12)), ss, 10, PdfContentByte.ALIGN_LEFT, bf);
        }
        len++;
        } */

        createHeadings(cb, 265, (rectHt - 55), "E.C.C Reg. No", 10, PdfContentByte.ALIGN_LEFT, bf);
        createHeadings(cb, 265, (rectHt - 70), "Sales Tax No. TIN No : ", 10, PdfContentByte.ALIGN_LEFT, bf);
        createHeadings(cb, 265, (rectHt - 85), "Sales Tax No. CST No", 10, PdfContentByte.ALIGN_LEFT, bf);
        createHeadings(cb, 265, (rectHt - 100), "Income Tax & GIR No", 10, PdfContentByte.ALIGN_LEFT, bf);
        createHeadings(cb, 400, (rectHt - 55), ": " + dto.getCompany().getEccNo(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 400, (rectHt - 70), ": " + dto.getCompany().getTinNumber(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 400, (rectHt - 85), ": " + dto.getCompany().getCstNumber(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 400, (rectHt - 100), ": " + dto.getCompany().getCerNo(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
    }

    private void setBlack3Data(PdfContentByte cb, GenerateBillDto dto) {
        createHeadings(cb, 25, (rectHt - 114), "RANGE (Full Address)", 10, PdfContentByte.ALIGN_LEFT, bf);
        createHeadings(cb, 25, (rectHt - 130), "Nampally,1st Floor,", 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 25, (rectHt - 145), "Posenett Bhavan", 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 25, (rectHt - 160), "Tilak Road, Ramkoti", 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 25, (rectHt - 175), "Hyderabad-500001", 10, PdfContentByte.ALIGN_LEFT, bfBold);

        createHeadings(cb, 135, (rectHt - 114), "DIVISION (Full Address)", 10, PdfContentByte.ALIGN_LEFT, bf);
        createHeadings(cb, 135, (rectHt - 130), dto.getCompany().getDivision(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 135, (rectHt - 130), "_______________", 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 135, (rectHt - 145), "Nampally,1st Floor,", 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 135, (rectHt - 160), "Posenett Bhavan,", 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 135, (rectHt - 175), "Tilak Road, Ramkoti,Hyd-01", 9, PdfContentByte.ALIGN_LEFT, bfBold);

        createHeadings(cb, 25, (rectHt - 189), "Commissionerate  :" + dto.getCompany().getCommissionerate().toUpperCase(), 10, PdfContentByte.ALIGN_LEFT, bf);

        createHeadings(cb, 420, (rectHt - 120), dto.getInvoiceNumber().substring(dto.getInvoiceNumber().lastIndexOf("/") + 1, dto.getInvoiceNumber().length()) + " & " + dto.getInvoiceDate() + " " + dto.getInvoiceTime(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 265, (rectHt - 120), "Date & Time of issue of invoice:____________________________", 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 440, (rectHt - 135), dto.getDateOfRemovelGoods() + " " + dto.getRemovalOfGoodsTime(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 265, (rectHt - 135), "Date & Time of removal of goods:__________________________", 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 440, (rectHt - 150), dto.getEnrtyPageNo(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 265, (rectHt - 150), "Entry & Page No. in RG 23D Reg :__________________________", 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 390, (rectHt - 165), dto.getModeOfTrns(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 265, (rectHt - 165), "Mode of Transport :______________________________________", 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 440, (rectHt - 180), dto.getVehicleRegNo(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 265, (rectHt - 180), "Motor Vehicle Registration No :____________________________", 10, PdfContentByte.ALIGN_LEFT, bfBold);
    }

    private GenerateBillDto setBlack4Data(PdfContentByte cb, GenerateBillDto dto, int y) {
        int x = 10;
        createHeadings(cb, x + 38, y - 10, "Srno", 10, PdfContentByte.ALIGN_RIGHT, bf);

        createHeadings(cb, x + 55, y - 10, "Description of Goods", 10, PdfContentByte.ALIGN_LEFT, bf);
        createHeadings(cb, x + 197, y - 3, "Identify", 8, PdfContentByte.ALIGN_LEFT, bf);
        createHeadings(cb, x + 193, y - 13, "Mark&No.", 8, PdfContentByte.ALIGN_LEFT, bf);
        createHeadings(cb, x + 267, y - 3, "(F)Qty (No", 7, PdfContentByte.ALIGN_RIGHT, bf);
        createHeadings(cb, x + 267, y - 13, "Wt/LT/MT)", 7, PdfContentByte.ALIGN_RIGHT, bf);
        createHeadings(cb, x + 275, y - 3, "Tariff S", 8, PdfContentByte.ALIGN_LEFT, bf);
        createHeadings(cb, x + 270, y - 13, "HeadingNo", 7, PdfContentByte.ALIGN_LEFT, bf);
        createHeadings(cb, x + 347, y - 3, dto.getBasicInformation().getExciseDuty() + "%Basic", 7, PdfContentByte.ALIGN_RIGHT, bf);
        createHeadings(cb, x + 350, y - 13, "Excise Duty", 7, PdfContentByte.ALIGN_RIGHT, bf);
        createHeadings(cb, x + 387, y - 3, +dto.getBasicInformation().getEductionCess() + "%Educ", 8, PdfContentByte.ALIGN_RIGHT, bf);
        createHeadings(cb, x + 380, y - 13, "Cess", 8, PdfContentByte.ALIGN_RIGHT, bf);
        createHeadings(cb, x + 427, y - 3, dto.getBasicInformation().getSandHEduCess() + "% S&H", 8, PdfContentByte.ALIGN_RIGHT, bf);
        createHeadings(cb, x + 420, y - 13, "Cess", 8, PdfContentByte.ALIGN_RIGHT, bf);
        createHeadings(cb, x + 467, y - 3, "Amt of Duty", 7, PdfContentByte.ALIGN_RIGHT, bf);
        createHeadings(cb, x + 460, y - 13, "Per Uint", 7, PdfContentByte.ALIGN_RIGHT, bf);
        createHeadings(cb, x + 560, y - 3, "Amount of Duty involved ", 8, PdfContentByte.ALIGN_RIGHT, bf);
        createHeadings(cb, x + 535, y - 13, "on qty at S.No 1", 8, PdfContentByte.ALIGN_RIGHT, bf);
        createHeadings(cb, x + 535, y - 23, "(F)In Words & fig", 8, PdfContentByte.ALIGN_RIGHT, bf);
        tatalAmount = 0;
        List<ProductDto> ls = dto.getProductDtos();
        double tamont1 = 0.0;
        y = y - 40;
        for (int i = 0; i < ls.size(); i++) {
            ProductDto pd = ls.get(i);
            double qut = pd.getQuantity();
            double price = pd.getSalesPrice();
            totalDut += qut;
            tatalAmount += (qut * price);
            double duty1 = (pd.getPurchasePrice() / 100) * dto.getBasicInformation().getExciseDuty();
            double duty2 = (duty1 / 100) * dto.getBasicInformation().getEductionCess();
            double duty3 = (duty1 / 100) * dto.getBasicInformation().getSandHEduCess();
            duty1 = Double.parseDouble(df.format(duty1));
            duty2 = Double.parseDouble(df.format(duty2));
            duty3 = Double.parseDouble(df.format(duty3));
            double tduty = (duty1 + duty2 + duty3);
            double tamont = (pd.getQuantity() * tduty);
            //System.out.println("tduty : "+tduty);
            //System.out.println("tamont : "+tamont);

            tamont1 += tamont;
            dto.setFinalexciseDuty(tamont);

            setDateInTable(cb, 10, y, "" + (i + 1), pd.getProductName(), pd.getIdentifyMark(), "(" + pd.getPackSize() + "x" + pd.getNoOfPackets() + ")", df.format(duty1), df.format(duty2), df.format(duty3), df.format(tduty), "(" + pd.getQuantity() + "x" + df.format(tduty) + ")", pd.getProductCode(), 8, 5);
            y -= 10;
            setDateInTable(cb, 10, y, "", "", "", "" + pd.getQuantity(), "", "", "", "", "" + Math.round(tamont1), "", 8, 0);
            y -= (15);

        }
        y += (15);
        String result = NumberToWord.getNuberInWord((int) Math.round(tamont1));
        // System.out.println(result.trim());
        String[] ar = ("Rs." + Math.round(tamont1) + " (" + result.trim() + "  Rupees Only )").split(" ");
        String s = "";
        for (int i = 0; i < ar.length; i++) {

            if (s.length() < 18) {
                s += " " + ar[i];
                continue;
            } else {
                i--;
                y -= 10;
                createHeadings(cb, 485, y, s.trim(), 8, PdfContentByte.ALIGN_LEFT, bfBold);

                //  System.out.println(s.trim());
                s = "";
            }

        }
        y -= 10;
        // System.out.println(s.trim());
        createHeadings(cb, 485, y, s.trim(), 8, PdfContentByte.ALIGN_LEFT, bfBold);
        List<ProductDto> list = dto.getProductDtos();
        if (dto.getProDiscount() > 0) {
            double dic = 0.0;
            for (int i = 0; i < list.size(); i++) {
                ProductDto pd = list.get(i);
                double disc = 0.0;
                if (dto.getProDiscount() == 1) {
                    disc = (pd.getQuantity() * ((pd.getSalesPrice() / 100) * pd.getDiscount()));
                } else if (dto.getProDiscount() == 3) {
                    disc = (((pd.getQuantity() * pd.getSalesPrice()) / 100) * pd.getDiscount());
                } else if (dto.getProDiscount() == 2) {
                    disc = (pd.getQuantity() * pd.getDiscount());
                }
                if (disc > 0) {
                    dic += disc;
                }
            }
            tatalAmount -= dic;
            dto.setFinalDiscount(dic);
        } else {
            dto.setFinalDiscount(0.0);
        }

        if (dto.getProOtherCharges() > 0) {
            double dic = 0.0;
            for (int i = 0; i < list.size(); i++) {
                ProductDto pd = list.get(i);
                double disc = (pd.getQuantity() * pd.getOtherCharges());
                if (disc > 0) {
                    dic += disc;
                }
            }
            tatalAmount += (dic);

            dto.setFinaleOtherCharges(dic);

        } else {
            dto.setFinaleOtherCharges(0.0);
        }

        List<OtherInvoiceData> invoiceDatas = dto.getOtherInvoiceData();
        if (!invoiceDatas.isEmpty()) {
            for (int j = 0; j < invoiceDatas.size(); j++) {
                OtherInvoiceData od = invoiceDatas.get(j);
                if (od.getOrderBy() == 0) {
                    if (od.getAmtType() == 0) {
                        tatalAmount += od.getAmount();
                    } else {
                        tatalAmount -= od.getAmount();
                    }
                }
            }
        }
        if (!invoiceDatas.isEmpty()) {
            for (int j = 0; j < invoiceDatas.size(); j++) {
                OtherInvoiceData od = invoiceDatas.get(j);
                if (od.getOrderBy() == 1) {
                    if (od.getAmtType() == 0) {
                        tatalAmount += od.getAmount();
                    } else {
                        tatalAmount -= od.getAmount();
                    }
                }
            }
        }
        if (!invoiceDatas.isEmpty()) {
            for (int j = 0; j < invoiceDatas.size(); j++) {
                OtherInvoiceData od = invoiceDatas.get(j);
                if (od.getOrderBy() == 2) {
                    if (od.getAmtType() == 0) {
                        tatalAmount += od.getAmount();
                    } else {
                        tatalAmount -= od.getAmount();
                    }
                }
            }
        }

        double cstOrVat = (dto.getVatOrCst() == 1) ? dto.getBasicInformation().getVat() : dto.getBasicInformation().getCst();
        if (dto.getCustDiscount() > 0) {

            int disc = (int) Math.round((tatalAmount / 100) * dto.getCustDiscount());
            tatalAmount -= disc;
            int vatamt = (int) Math.round((tatalAmount / 100) * cstOrVat);
            tatalAmount += vatamt;

        } else {
            int vatamt = (int) Math.round((tatalAmount / 100) * cstOrVat);
            tatalAmount += vatamt;
        }

        return dto;
    }

    private void setDateInTable(PdfContentByte cb, int x, int y, String srno, String productName, String identificationMark, String qty, String duty1, String duty2, String duty3, String tduty, String tamount, String productCode, int size, int mn) {
        createHeadings(cb, x + 38, y, srno, size, PdfContentByte.ALIGN_RIGHT, bfBold);
        createHeadings(cb, x + 55, y, productName, size, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, x + 197, y, identificationMark, size, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, x + 258 + mn, y, qty, size, PdfContentByte.ALIGN_RIGHT, bfBold);
        createHeadings(cb, x + 275, y, productCode, 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, x + 347, y, duty1, size, PdfContentByte.ALIGN_RIGHT, bfBold);
        createHeadings(cb, x + 387, y, duty2, size, PdfContentByte.ALIGN_RIGHT, bfBold);
        createHeadings(cb, x + 427, y, duty3, size, PdfContentByte.ALIGN_RIGHT, bfBold);
        createHeadings(cb, x + 467, y, tduty, 8, PdfContentByte.ALIGN_RIGHT, bfBold);
        if (mn > 0) {
            createHeadings(cb, x + 555, y, tamount, 8, PdfContentByte.ALIGN_RIGHT, bfBold);
        } else {
            createHeadings(cb, x + 555, y, tamount, 9, PdfContentByte.ALIGN_RIGHT, bfBold);
        }


    }

    private void setBlack5Data(PdfContentByte cb, GenerateBillDto dto, int y) {
        String sp=dto.getSupplier().getName();
        sp=sp.substring(0, sp.indexOf("-")-1);
        String st = ("5) Name & Address of Manufacturer," + sp + "," + dto.getSupplier().getAddress());
        setAdderss(cb, dto, 25, y, st, 8, bf, 40);


        createHeadings(cb, 60, y - 53, dto.getSupplier().getEccNo(), 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 60, y - 66, dto.getSupplier().getCerNo(), 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 120, y - 79, dto.getSuppInvBill() + " " + dto.getSplrInvoiceTime(), 8, PdfContentByte.ALIGN_LEFT, bfBold);

        createHeadings(cb, 25, y - 53, "E.C.C.____________________________________________", 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 25, y - 66, "CE Reg  no._______________________________________", 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 25, y - 79, "INVOICE / BILL NO ._______________________________", 8, PdfContentByte.ALIGN_LEFT, bfBold);

        String st1 = ("Range full Address," + dto.getSupplier().getRangeAddress());
        setAdderss(cb, dto, 300, y, st1, 8, bf, 40);

        createHeadings(cb, 370, y - 53, dto.getSupplier().getDivision(), 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 400, y - 66, dto.getSupplier().getCommissionerate(), 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 450, y - 79, dto.getSupplier().getDebitEntryNo(), 8, PdfContentByte.ALIGN_LEFT, bfBold);

        createHeadings(cb, 300, y - 53, "DIVISION____________________________________________", 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 300, y - 66, "Commissionarate__________________________________________", 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 300, y - 79, "Debit Entry No. & Date_______________________________", 8, PdfContentByte.ALIGN_LEFT, bfBold);

    }

    private void setBlack6Data(PdfContentByte cb, GenerateBillDto dto, int y) {
        String st = ("2) Name & Full Postal Address of the Consignee," + dto.getCustomer().getName() + "," + dto.getCustomer().getAddress());
        setAdderss(cb, dto, 25, y, st, 8, bf, 40);

        createHeadings(cb, 75, y - 60, dto.getCustomer().getCerNo(), 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 75, y - 76, dto.getCustomer().getEccNo(), 8, PdfContentByte.ALIGN_LEFT, bfBold);

        createHeadings(cb, 25, y - 60, "CER NO.__________________________________________", 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 25, y - 76, "ECC NO._______________________________", 8, PdfContentByte.ALIGN_LEFT, bfBold);

        String st1 = ("Range Full Address ," + dto.getCustomer().getRangeAddress());
        setAdderss(cb, dto, 300, y, st1, 8, bf, 20);

        createHeadings(cb, 400, y - 53, dto.getCustomer().getDivision(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 300, y - 53, "Division :__________________________________________", 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 400, y - 64, dto.getCustomer().getCommissionerate(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 300, y - 64, "Commissionarate :__________________________________________", 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 350, y - 75, dto.getCustomer().getTinNumber(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 480, y - 75, dto.getCustomer().getCstNumber(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 300, y - 75, "TIN NO :____________________ CST NO :______________________", 8, PdfContentByte.ALIGN_LEFT, bfBold);

    }

    private void setAdderss(PdfContentByte cb, GenerateBillDto dto, int x, int y, String address, int size, BaseFont bf1, int length) {
        String addree[] = address.split(",");
        int len = 0;
        for (int k = 0; k < addree.length; k++) {
            String ss = addree[k];
            if (k < addree.length - 1) {
                if (ss.length() < length) {
                    k++;
                    ss += ", " + addree[k];
                    if (ss.length() > length) {
                        k--;
                        ss = addree[k];
                    }
                }
            }
            if (len == 0) {
                createHeadings(cb, (x), (y - (len * 10)), ss, size, PdfContentByte.ALIGN_LEFT, bfBold);
            } else {
                createHeadings(cb, (x + 5), ((y - 2) - (len * 10)), ss, size, PdfContentByte.ALIGN_LEFT, bf1);
            }
            len++;
        }
    }

    private void setFinalBack(PdfContentByte cb, GenerateBillDto dto, int y) {
        createHeadings(cb, 250, y, "CERTIFICATE", 12, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 250, y, "____________", 12, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 30, y - 10, "(A) & (C) Column not applicable", 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 30, y - 23, "B) Received directly from the factory/depot/consignment agent/authorized premisses of ", 8, PdfContentByte.ALIGN_LEFT, bf);
        String sp=dto.getSupplier().getName();
        sp=sp.substring(0, sp.indexOf("-")-1);
        createHeadings(cb, 30, y - 36, "M/s " + sp + "  ( 147,Mumbai-Pune Raod, Pimpri, Pune-411018, Maharastra, India )", 10, PdfContentByte.ALIGN_LEFT, bfBold);
        cb.moveTo(30, y - 40);
        cb.lineTo(recWdth + 5, y - 40);
        createHeadings(cb, 30, y - 55, "Place : " + dto.getCompany().getCommissionerate(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 30, y - 70, "Date : " + dto.getInvoiceDate(), 10, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 30, y - 85, "* Strike out which ever is not applicable", 8, PdfContentByte.ALIGN_LEFT, bf);

        //  createHeadings(cb, 400, y - 50, "for Hyderabad Electric Syndicate", 8, PdfContentByte.ALIGN_LEFT, bfBold);
        //createHeadings(cb, 490, y - 75, "Partner", 8, PdfContentByte.ALIGN_LEFT, bfBold);
        createHeadings(cb, 340, y - 75, "Signature of the Registared Person", 8, PdfContentByte.ALIGN_LEFT, bf);
        createHeadings(cb, 310, y - 85, "(Name & Designation with Co./Firm's Seal/Stamp)", 8, PdfContentByte.ALIGN_LEFT, bf);




    }

    private void setBlack(PdfContentByte cb, int len) {
        cb.moveTo(rectX, (recty + rectHt - len));
        cb.lineTo(recWdth + 20, (recty + rectHt - len));
    }

    private void setBlackVer(PdfContentByte cb, int y, int x, int y1) {
        cb.moveTo(x, (recty + rectHt - y));
        cb.lineTo(x, (recty + rectHt - y1));
    }

    private void generateLayout(Document doc, PdfContentByte cb) {
        try {
            cb.setLineWidth(1f);
            cb.rectangle(rectX, recty, recWdth, rectHt);
            cb.moveTo(rectX, (recty + rectHt - 20));
            cb.lineTo(recWdth + 20, (recty + rectHt - 20));
            cb.moveTo((rectX + 30), recty);
            cb.lineTo((rectX + 30), (recty + rectHt));
            cb.moveTo((rectX + 330), recty);
            cb.lineTo((rectX + 330), (recty + rectHt));
            cb.moveTo((rectX + 380), recty + 20);
            cb.lineTo((rectX + 380), (recty + rectHt));
            cb.moveTo((rectX + 450), recty);
            cb.lineTo((rectX + 450), (recty + rectHt));
            cb.moveTo((rectX + 450), recty);
            cb.lineTo((rectX + 450), (recty + rectHt));
            cb.moveTo(rectX + 330, (recty + 20));
            cb.lineTo(recWdth + 20, (recty + 20));
            cb.stroke();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createHeadings(PdfContentByte cb, float x, float y, String text, int size, int align, BaseFont pbf) {
        cb.beginText();
        cb.setFontAndSize(pbf, size);
        cb.showTextAligned(align, text.trim(), x, y, 0);
        cb.endText();
    }

    private void initializeFonts() {
        try {
            bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD,
                    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252,
                    BaseFont.NOT_EMBEDDED);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
