package com.hes.util;

import com.hes.model.InvoiceModel;
import com.hes.pojo.OtherInvoiceData;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import java.util.List;

public class Endorsement {

    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;
    int rectX = 20;
    int recty = 120;
    int recWdth = 550;
    int rectHt = 350;
    int totalDut = 0;
    double tatalAmount = 0;
    boolean vat = true;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) {

        String pdfFilename = "";
        Endorsement generateInvoice = new Endorsement();
        List<ProductDto> ls = new ArrayList<ProductDto>();
        ProductDto pd = new ProductDto();
        pd.setProductName("T&RED E C G 250-35");
        pd.setPackSize(210);
        pd.setNoOfPackets(5);
        pd.setQuantity(pd.getNoOfPackets() * pd.getPackSize());
        pd.setSalesPrice(165.5);
        pd.setPurchasePrice(177);
        pd.setDiscount(10.0);
        pd.setOtherCharges(2);
        ls.add(pd);
        ls.add(pd);
        ls.add(pd);
        ls.add(pd);
        //  ls.add(pd);

        InvoiceModel cust = new InvoiceModel();

        GenerateBillDto dto = new GenerateBillDto();
        dto.setProductDtos(ls);
        dto.setCustomer(cust.getCustomerList().get(0));
        dto.setInvoiceDate("1-8-2014");
        dto.setInvoiceNumber("4534646");
        dto.setCustDiscount(10.0);
        dto.setPoNo("432156");
        dto.setPoDate("1-8-2014");
        dto.setRrorLrNo("ABC5432");
        dto.setDispachedDate("1-8-2014");
        dto.setDispachedFrom("Mr.ABCD FEGHI");
        dto.setDispachedTo("Chennai");
        dto.setPayByotMS("Usha ConDuctores Pvt Lid.");
        dto.setBeckInvNo("IN1234567");
        dto.setBeckInvDate("1-8-2014");
        dto.setBeckInvAmount(34545);
        dto.setPayRs(2000);
        dto.setDocumentThrough("XYZ");
        dto.setCustDiscount(10);
        dto.setExciseDuty(true);
        //dto.setProDiscount(true);
        dto.setProOtherCharges(1);

        pdfFilename = "d:/" + dto.getInvoiceNumber() + "_CSTorVAT.pdf";

        pdfFilename = "d:/12345_ENDORSEMENT.pdf";
        generateInvoice.createPDF(pdfFilename, dto);
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




    }

    public static GenerateBillDto genBill(GenerateBillDto dto) {

        String pdfFilename = "";
        Endorsement generateInvoice = new Endorsement();
        pdfFilename = "d:/HES_INVOICE/" + dto.getInvoiceNumber() + "/";
        if (!(new File(pdfFilename).exists())) {
            (new File(pdfFilename)).mkdirs();
        }

        pdfFilename += "Endordement.pdf";

        if ((new File(pdfFilename)).exists()) {
            (new File(pdfFilename)).delete();
        }
        dto = generateInvoice.createPDF(pdfFilename, dto);
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

    private GenerateBillDto createPDF(String pdfFilename, GenerateBillDto dto) {

        Document doc = new Document();
        PdfWriter docWriter = null;
        initializeFonts();
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            String path = pdfFilename;
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.addTitle("Invoice");
            doc.setPageSize(PageSize.A4);
            doc.open();
            PdfContentByte cb = docWriter.getDirectContent();
            cb.setLineWidth(1f);
            //generateDetail(doc, cb, "", (recty+5),"Grand Total Amount","","",String.valueOf(tatalAmount));
            boolean beginPage = true;
            int y = 0;

            createHeadings(cb, 20, (recty + rectHt + 170), "INVOICE NO . HES/BK'S/EL-Sales/", 10);
            createHeadings(cb, 190, (recty + rectHt + 170), dto.getInvoiceNumber(), 12);
            createContent(cb, 20, (recty + rectHt + 160), "Sold To", PdfContentByte.ALIGN_LEFT);
            createHeadings(cb, 450, (recty + rectHt + 170), "Date :" + dto.getInvoiceDate(), 12);
            createHeadings(cb, 400, (recty + rectHt + 140), dto.getCustomer().getTinNumber(), 12);
            createContent(cb, 300, (recty + rectHt + 140), "Customer TIN No  : ______________________________________", PdfContentByte.ALIGN_LEFT);
            createHeadings(cb, 350, (recty + rectHt + 120), dto.getCustomer().getCstNumber(), 12);
            createContent(cb, 300, (recty + rectHt + 120), "CST NO  : ______________________________________________", PdfContentByte.ALIGN_LEFT);

            createHeadings(cb, 350, (recty + rectHt + 100), dto.getPoNo(), 12);
            createHeadings(cb, 470, (recty + rectHt + 100), dto.getPoDate(), 12);
            createContent(cb, 300, (recty + rectHt + 100), "P.O. No : _________________  Date:_________________________", PdfContentByte.ALIGN_LEFT);
            //createContent(cb,300,(recty+rectHt+80),"Our D.C. No : ______________ DATE:/_________________________", PdfContentByte.ALIGN_LEFT);

            createHeadings(cb, 250, (recty + rectHt + 70), "ENDORSEMENT", 16);
            createHeadings(cb, 30, (recty + rectHt + 56), "The document of title of the  goods covered by the Lorry receipt is transferred in your favour under section 6(2) of ", 10);

            createHeadings(cb, 460, (recty + rectHt + 42), dto.getRrorLrNo(), 10);

            createHeadings(cb, 20, (recty + rectHt + 42), "the C.S.T.Act 1956,during the cource of movement of goods l.e, Orginal lorry Receipt No.________________________", 10);


            createHeadings(cb, 50, (recty + rectHt + 28), dto.getDispachedDate(), 10);
            createHeadings(cb, 165, (recty + rectHt + 28), dto.getPayByotMS(), 10);
            createHeadings(cb, 510, (recty + rectHt + 28), dto.getDispachedTo(), 10);
            createHeadings(cb, 20, (recty + rectHt + 28), "Date:_______________of M/S___________________________________from Pune/Ankleshwar to Hyd/______________", 10);

            createHeadings(cb, 195, (recty + rectHt + 14), dto.getCustomer().getName(), 10);
            createHeadings(cb, 20, (recty + rectHt + 14), "is duly endorsed in favour of M/S.___________________________________________________for direct collection of", 10);
            createHeadings(cb, 20, (recty + rectHt - 4), "consignment (s) from Transport under  ", 10);
            createHeadings(cb, 220, (recty + rectHt - 4), "\"SALES-IN-TRANSIT\" AGAINST FORM 'C' ", 12);

            createHeadings(cb, 150, (recty + rectHt - 18), dto.getBeckInvNo(), 10);
            createHeadings(cb, 300, (recty + rectHt - 18), dto.getBeckInvDate(), 10);
            createHeadings(cb, 450, (recty + rectHt - 18), dto.getBeckInvAmount() + "", 10);
            createHeadings(cb, 20, (recty + rectHt - 18), "Beck India Invoice No._________________________ Date._______________________ Rs.________________________ ", 10);
            createHeadings(cb, 140, (recty + rectHt - 32), dto.getDocumentThrough(), 10);
            createHeadings(cb, 20, (recty + rectHt - 32), "Documents through._________________________________________________________________________________", 10);

            cb.rectangle(20, (recty + rectHt + 90), 250, 65);
            String addree[] = ("Mrs." + dto.getCustomer().getName() + "," + dto.getCustomer().getAddress()).split(","); //Satish Syamala,Mariyapuram,Gessugonda,Warangal-TS,506330 ";
            int len = 0;
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
                createContent(cb, 27, (recty + rectHt + 140) - (len * 10), ss, PdfContentByte.ALIGN_LEFT);
                len++;
            }

            rectHt = rectHt - 50;
            double purchaseCoat = 0.0;
            List<ProductDto> list = dto.getProductDtos();
            if (!list.isEmpty()) {
                generateLayout(doc, cb, dto.getCustDiscount());
                y = (recty + rectHt - 35);
                for (int i = 0; i < list.size(); i++) {
                    ProductDto pd = list.get(i);
                    if (pd.getCustProID() != null && pd.getCustProID().trim().length() > 0) {
                        createContent(cb, 60, y, pd.getCustProID(), PdfContentByte.ALIGN_LEFT);
                        createContent(cb, 60, y, "__________", PdfContentByte.ALIGN_LEFT);
                        y = y - 10;
                        
                    }
                    String pro = pd.getProductDetails();
                    double qut = pd.getQuantity();
                    double price = pd.getSalesPrice();

                    purchaseCoat += (qut * pd.getPurchasePrice());

                    totalDut += qut;
                    tatalAmount += (qut * price);
                    generateDetail(doc, cb, String.valueOf((i + 1)), y, pro, String.valueOf(qut) + " " + pd.getProductType(), String.valueOf(price), String.valueOf((qut * price)));
                    y = y - 12;
                    if (pd.getOptionalText() != null && pd.getOptionalText().trim().length() > 0) {
                        generateDetail(doc, cb, "", y, pd.getOptionalText(), "", "", "");
                        y = y - 15;

                    }
                }
            }

            int lineAdd = 480;


            if (dto.getProDiscount() > 0) {

                cb.moveTo(rectX + lineAdd, y);
                cb.lineTo(recWdth + 20, y);

                y = y - 10;
                generateDetail(doc, cb, "", y, "", "", "Total", tatalAmount + "");
                //  createContent(cb, 540, y, tatalAmount + "", PdfContentByte.ALIGN_RIGHT);
                y = y - 15;
                createContent(cb, 60, y, "Less Cash Discount", PdfContentByte.ALIGN_LEFT);
                createContent(cb, 60, y, "___________________", PdfContentByte.ALIGN_LEFT);
                double dic = 0.0;
                for (int i = 0; i < list.size(); i++) {
                    ProductDto pd = list.get(i);
                   
                    double disc = 0.0;
                    String lab = "";
                    if (dto.getProDiscount() == 1) {
                        lab = " % per Kg";
                        disc = (pd.getQuantity() * ((pd.getSalesPrice() / 100) * pd.getDiscount()));
                    } else if (dto.getProDiscount() == 3) {
                        lab = " % On Amount";
                        disc = (((pd.getQuantity() * pd.getSalesPrice()) / 100) * pd.getDiscount());
                    } else if (dto.getProDiscount() == 2) {
                        lab = " Rs per Kg";
                        disc = (pd.getQuantity() * pd.getDiscount());
                    }
                    if(disc>0){
                    y = y - 15;
                    createContent(cb, 60, y, pd.getProductName() + "  (" + pd.getDiscount() + "" + lab + ")", PdfContentByte.ALIGN_LEFT);
                    createContent(cb, 345, y, df.format(disc), PdfContentByte.ALIGN_RIGHT);
                    dic += disc;
                    }
                }
                generateDetail(doc, cb, "", y, "", "", "Discount(-)", df.format(dic) + "");
                createContent(cb, 540, y, df.format(dic), PdfContentByte.ALIGN_RIGHT);
                y -= 5;

                tatalAmount -= dic;

                // createContent(cb, 540, y, tatalAmount + "", PdfContentByte.ALIGN_RIGHT);
                dto.setFinalDiscount(dic);
            } else {
                dto.setFinalDiscount(0.0);
            }
            List<OtherInvoiceData> invoiceDatas = dto.getOtherInvoiceData();
            if (!invoiceDatas.isEmpty()) {
                for (int j = 0; j < invoiceDatas.size(); j++) {
                    OtherInvoiceData od = invoiceDatas.get(j);
                    if (od.getOrderBy() == 0) {
                        cb.moveTo(rectX + lineAdd, y);
                        cb.lineTo(recWdth + 20, y);
                        y = y - 10;
                        generateDetail(doc, cb, "", y, "", "", "Total", df.format(tatalAmount) + "");

                        String text = od.getHeadingLine();
                        if (text != null && text.trim().length() > 0) {
                            y = y - 15;
                            createContent(cb, 60, y, text, PdfContentByte.ALIGN_LEFT);
                            createContent(cb, 60, y, "________________", PdfContentByte.ALIGN_LEFT);
                        }
                        boolean check = false;
                        String lines[] = od.getDiscription().trim().split("##");
                        for (int k = 0; k < lines.length; k++) {
                            if (lines[k].trim().length() > 0) {
                                String line[] = lines[k].split("@@");
                                if (line[0].trim().length() > 0) {
                                    check = true;
                                    y = y - 15;
                                    createContent(cb, 60, y, line[0], PdfContentByte.ALIGN_LEFT);
                                    if (line[1].trim().length() > 0) {
                                        createContent(cb, 345, y, line[1], PdfContentByte.ALIGN_RIGHT);
                                    }

                                }

                            }
                        }
                        if (od.getAmount() > 0 && check) {
                            String test = "";
                            if (od.getAmtType() == 0) {
                                test = "(+)";
                                tatalAmount += od.getAmount();
                            } else {
                                test = "(-)";
                                tatalAmount -= od.getAmount();
                            }
                            generateDetail(doc, cb, "", y, "", "", test, df.format(od.getAmount()) + "");
                            y -= 5;
                        }
                    }
                }
            }
            if (dto.isExciseDuty()) {
                cb.moveTo(rectX + lineAdd, y);
                cb.lineTo(recWdth + 20, y);
                y = y - 10;
                generateDetail(doc, cb, "", y, "", "", "Total", df.format(tatalAmount) + "");
                y = y - 15;
                createContent(cb, 60, y, "ADD ACTUALS", PdfContentByte.ALIGN_LEFT);
                createContent(cb, 60, y, "________________", PdfContentByte.ALIGN_LEFT);
                y = y - 15;
                double disc = ((purchaseCoat / 100) * dto.getBasicInformation().getExciseDuty());
                createContent(cb, 60, y, "1 Excise Duty " + dto.getBasicInformation().getExciseDuty() + " %   ", PdfContentByte.ALIGN_LEFT);
                createContent(cb, 345, y, df.format(disc) + "", PdfContentByte.ALIGN_RIGHT);
                y = y - 15;
                double diss = dto.getBasicInformation().getEductionCess() + dto.getBasicInformation().getSandHEduCess();
                double disc1 = ((disc / 100) * diss);
                createContent(cb, 60, y, "2 Education Cess " + diss + " %   ", PdfContentByte.ALIGN_LEFT);
                createContent(cb, 345, y, df.format(disc1) + "", PdfContentByte.ALIGN_RIGHT);
                generateDetail(doc, cb, "", y, "", "", "Exc Duty(+)", df.format((disc + disc1)) + "");
                y -= 5;
                tatalAmount += (disc + disc1);
                
                dto.setFinalexciseDuty((disc + disc1));

            } else {
                dto.setFinalexciseDuty(0.0);
            }
           
            if (dto.getProOtherCharges() > 0) {
                cb.moveTo(rectX + lineAdd, y);
                cb.lineTo(recWdth + 20, y);
                y = y - 10;
                generateDetail(doc, cb, "", y, "", "", "Total", df.format(tatalAmount) + "");
                y = y - 15;
                String text = (dto.getProOtherCharges() == 1 ? "Other Charges" : "P & F Charges");
                createContent(cb, 60, y, text, PdfContentByte.ALIGN_LEFT);
                createContent(cb, 60, y, "________________", PdfContentByte.ALIGN_LEFT);
                double dic = 0.0;
                for (int i = 0; i < list.size(); i++) {
                    ProductDto pd = list.get(i);
                    double disc = (pd.getQuantity() * pd.getOtherCharges());
                    if(disc>0){
                     y = y - 15;
                    createContent(cb, 60, y, pd.getProductName() + "  (" + pd.getOtherCharges() + "Rs per Kg)", PdfContentByte.ALIGN_LEFT);
                    createContent(cb, 345, y, df.format(disc), PdfContentByte.ALIGN_RIGHT);
                    dic += disc;
                    }
                }
                tatalAmount += (dic);
                generateDetail(doc, cb, "", y, "", "", "Other ch(+)", df.format(dic) + "");
                y -= 5;
                cb.moveTo(rectX + lineAdd, y);
                cb.lineTo(recWdth + 20, y);
                dto.setFinaleOtherCharges(dic);

            } else {
                dto.setFinaleOtherCharges(0.0);
            }
             if (!invoiceDatas.isEmpty()) {
                for (int j = 0; j < invoiceDatas.size(); j++) {
                    OtherInvoiceData od = invoiceDatas.get(j);
                    if (od.getOrderBy() == 2) {
                        cb.moveTo(rectX + lineAdd, y);
                        cb.lineTo(recWdth + 20, y);
                        y = y - 10;
                        generateDetail(doc, cb, "", y, "", "", "Total", df.format(tatalAmount) + "");

                        String text = od.getHeadingLine();
                        if (text != null && text.trim().length() > 0) {
                            y = y - 15;
                            createContent(cb, 60, y, text, PdfContentByte.ALIGN_LEFT);
                            createContent(cb, 60, y, "________________", PdfContentByte.ALIGN_LEFT);
                        }
                        boolean check = false;
                        String lines[] = od.getDiscription().trim().split("##");
                        for (int k = 0; k < lines.length; k++) {
                            if (lines[k].trim().length() > 0) {
                                String line[] = lines[k].split("@@");
                                if (line[0].trim().length() > 0) {
                                    check = true;
                                    y = y - 15;
                                    createContent(cb, 60, y, line[0], PdfContentByte.ALIGN_LEFT);
                                    if (line[1].trim().length() > 0) {
                                        createContent(cb, 345, y, line[1], PdfContentByte.ALIGN_RIGHT);
                                    }

                                }

                            }
                        }
                        if (od.getAmount() > 0 && check) {
                            String test = "";
                            if (od.getAmtType() == 0) {
                                test = "(+)";
                                tatalAmount += od.getAmount();
                            } else {
                                test = "(-)";
                                tatalAmount -= od.getAmount();
                            }
                            generateDetail(doc, cb, "", y, "", "", test, df.format(od.getAmount()) + "");
                            y -= 5;
                        }
                    }
                }
            }

            createHeadings(cb, rectX + 34, (recty + 23), "PLEASE ISSUE FORM 'C'", 10);
            createHeadings(cb, rectX + 34, (recty + 3), "E.& O.E", 10);
            if (dto.getCustDiscount() > 0) {
                generateDetail(doc, cb, "", (recty + 45), "", "", "Total", String.valueOf(df.format(tatalAmount)));
                int disc = (int) ((tatalAmount / 100) * dto.getCustDiscount());
                generateDetail(doc, cb, "", (recty + 25), "", "", "Discount " + dto.getCustDiscount() + "%", String.valueOf(df.format(disc)));
                tatalAmount -= disc;
                generateDetail(doc, cb, "", (recty + 5), "", "", "Grand Total Amount", String.valueOf(df.format(tatalAmount)));
                dto.setFinalDiscount(dto.getFinalDiscount() + disc);


            } else {
                generateDetail(doc, cb, "", (recty + 5), "", "", "Grand Total Amount", Math.round(tatalAmount) + ".00");
            }


            createContent(cb, 550, recty - 10, ("(" + NumberToWord.getNuberInWord((int) Math.round(tatalAmount)) + " Rupees Only )").toUpperCase(), PdfContentByte.ALIGN_RIGHT);
            dto.setGrandTotal(tatalAmount);
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

    private void generateLayout(Document doc, PdfContentByte cb, double dis) {

        try {

            // cb.setLineWidth(1f);


            cb.rectangle(rectX, recty, recWdth, rectHt);
            cb.moveTo(rectX, (recty + rectHt - 20));
            cb.lineTo(recWdth + 20, (recty + rectHt - 20));

            cb.moveTo((rectX + 30), recty);
            cb.lineTo((rectX + 30), (recty + rectHt));

            cb.moveTo((rectX + 330), recty);
            cb.lineTo((rectX + 330), (recty + rectHt));



            cb.moveTo((rectX + 480), recty);
            cb.lineTo((rectX + 480), (recty + rectHt));




            if (dis == 0) {
                cb.moveTo((rectX + 410), recty + 20);
                cb.lineTo((rectX + 410), (recty + rectHt));



                cb.moveTo(rectX + 330, (recty + 20));
                cb.lineTo(recWdth + 20, (recty + 20));
            } else {
                cb.moveTo((rectX + 410), recty + 55);
                cb.lineTo((rectX + 410), (recty + rectHt));
                cb.moveTo(rectX + 330, (recty + 55));
                cb.lineTo(recWdth + 20, (recty + 55));
                cb.moveTo(rectX + 330, (recty + 36));
                cb.lineTo(recWdth + 20, (recty + 36));
                cb.moveTo(rectX + 330, (recty + 17));
                cb.lineTo(recWdth + 20, (recty + 17));
            }




            //cb.stroke();

            // Invoice Detail box Text Headings
            createHeadings(cb, (rectX + 2), (recty + rectHt - 15), "Sr.No", 10);
            createHeadings(cb, (rectX + 150), (recty + rectHt - 15), "Product Name", 10);
            createHeadings(cb, (rectX + 350), (recty + rectHt - 15), "Quantity", 10);
            createHeadings(cb, (rectX + 415), (recty + rectHt - 15), "Rate for KG", 10);
            createHeadings(cb, (rectX + 493), (recty + rectHt - 15), "Amount", 10);

            // add the images
            Image companyLogo = Image.getInstance(getClass().getResource("/com/hes/images/Headbar.png"));
            companyLogo.setAbsolutePosition(7, 650);
            companyLogo.scalePercent(92);
            doc.add(companyLogo);
            Image companyLogo1 = Image.getInstance(getClass().getResource("/com/hes/images/FooterImage.png"));
            companyLogo1.setAbsolutePosition(5, 5);
            companyLogo1.scalePercent(71);
            doc.add(companyLogo1);

        } catch (DocumentException dex) {
            dex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateDetail(Document doc, PdfContentByte cb, String index,
            int y, String productName, String qut, String price, String totalAmount) {


        try {

            createContent(cb, (rectX + 15), y, index,
                    PdfContentByte.ALIGN_RIGHT);
            createContent(cb, (rectX + 40), y,
                    productName,
                    PdfContentByte.ALIGN_LEFT);

            createContent(cb, (rectX + 405), y, String.valueOf(qut),
                    PdfContentByte.ALIGN_RIGHT);


            createContent(cb, (rectX + 475), y, price,
                    PdfContentByte.ALIGN_RIGHT);
            createContent(cb, (rectX + 545), y, totalAmount,
                    PdfContentByte.ALIGN_RIGHT);



        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void createHeadings(PdfContentByte cb, float x, float y, String text, int size) {

        cb.beginText();
        cb.setFontAndSize(bfBold, size);
        cb.setTextMatrix(x, y);
        cb.showText(text.trim());
        cb.endText();

    }

    private void printPageNumber(PdfContentByte cb) {

        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. "
                + (pageNumber + 1), 570, 25, 0);
        cb.endText();

        pageNumber++;

    }

    private void createContent(PdfContentByte cb, float x, float y,
            String text, int align) {

        cb.beginText();
        cb.setFontAndSize(bfBold, 9);
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
