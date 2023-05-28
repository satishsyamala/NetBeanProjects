package com.hes.util;

import com.hes.pojo.OtherInvoiceData;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import java.util.List;

public class CstOrVatInvoice {

    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;
    int rectX = 20;
    int recty = 130;
    int recWdth = 550;
    int rectHt = 340;
    int totalDut = 0;
    double tatalAmount = 0;
    boolean vat = true;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) {

        String pdfFilename = "";
        CstOrVatInvoice generateInvoice = new CstOrVatInvoice();


        List<ProductDto> ls = new ArrayList<ProductDto>();
        ProductDto pd = new ProductDto();
        pd.setProductName("T&RED E C G 250-35");
        pd.setPackSize(210);
        pd.setNoOfPackets(5);
        pd.setQuantity(pd.getNoOfPackets() * pd.getPackSize());
        pd.setSalesPrice(166.50);
        ls.add(pd);
        CstOrVatDto dto = new CstOrVatDto();
        dto.setProductDtos(ls);
        dto.setInvoiceDate(new Date());
        dto.setInvoiceNo("14-15/244");
        dto.setCustTINNumber("36590202452");
        dto.setCstNumber("1234567");
        dto.setOrderNo("432156");
        dto.setOrderDate(new Date());
        dto.setOurDCNo("ABC5432");
        dto.setOutDcDate(new Date());
        dto.setDespatchedFrom("Hyderabad");
        dto.setDespatchedTo("Chennai");
        dto.setPayRs("2000");
        dto.setRRorLRNo("AP36-BF4331");
        dto.setPayDate(new Date());
        dto.setPayTo("Mr.XYZ");
        dto.setDocumentThrough("XYZ");
        dto.setCustDiscount(0);
        dto.setCstOrvat(true);
        dto.setCustName("Usha ConDuctores Pvt Lid.");
        dto.setCustAddress("Door No : 5-25/250 & 251,Prashanthi Nagar,Kukatpelly,Hyderabad-500072");
        pdfFilename = "d:/" + dto.getInvoiceNo().replace("/", "") + "_CSTorVAT.pdf";
        //  generateInvoice.createPDF(pdfFilename, dto);
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

    public static GenerateBillDto genBill(GenerateBillDto gdto) {

        String pdfFilename = "";
        CstOrVatInvoice generateInvoice = new CstOrVatInvoice();

        pdfFilename = "d:/HES_INVOICE/" + gdto.getInvoiceNumber() + "/";
        if (!(new File(pdfFilename).exists())) {
            (new File(pdfFilename)).mkdirs();
        }

        pdfFilename += "CstOrVatTaxInvoice.pdf";
        if ((new File(pdfFilename)).exists()) {
            (new File(pdfFilename)).delete();
        }
        gdto = generateInvoice.createPDF(pdfFilename, gdto);
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
        return gdto;




    }

    private GenerateBillDto createPDF(String pdfFilename, GenerateBillDto gdto) {

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
            createHeadings(cb, 20, (recty + rectHt + 170), "Invoice No. HES/VARNISH/ELECL/  " + gdto.getInvoiceNumber(), 12);
            createContent(cb, 20, (recty + rectHt + 150), "Sold To", PdfContentByte.ALIGN_LEFT);
            createHeadings(cb, 450, (recty + rectHt + 170), "Date :" + gdto.getInvoiceDate(), 12);
            createHeadings(cb, 400, (recty + rectHt + 140), gdto.getCustomer().getTinNumber(), 12);
            createContent(cb, 300, (recty + rectHt + 140), "Customer TIN No  : ______________________________________", PdfContentByte.ALIGN_LEFT);
            createHeadings(cb, 400, (recty + rectHt + 120), gdto.getCustomer().getCstNumber(), 10);
            createContent(cb, 300, (recty + rectHt + 120), "CST NO  : ______________________________________________", PdfContentByte.ALIGN_LEFT);

            createHeadings(cb, 350, (recty + rectHt + 100), gdto.getOrderNo(), 10);
            createHeadings(cb, 480, (recty + rectHt + 100), gdto.getOrderDate(), 10);
            createContent(cb, 300, (recty + rectHt + 100), "Oder No : ______________________  Dt:/____________________", PdfContentByte.ALIGN_LEFT);

            createHeadings(cb, 360, (recty + rectHt + 80), gdto.getOurDCNo(), 10);
            createHeadings(cb, 480, (recty + rectHt + 80), gdto.getOurDCDate(), 10);
            createContent(cb, 300, (recty + rectHt + 80), "Our D.C. No : ___________________ Dt:/____________________", PdfContentByte.ALIGN_LEFT);

            createHeadings(cb, 250, (recty + rectHt + 60), gdto.getDispachedFrom(), 10);
            createHeadings(cb, 460, (recty + rectHt + 60), gdto.getDispachedTo(), 10);
            createContent(cb, 20, (recty + rectHt + 60), "Dispatched per goods / Passanger / Lorry FROM : ________________________________ TO : _______________________________ ", PdfContentByte.ALIGN_LEFT);

            createHeadings(cb, 150, (recty + rectHt + 40), gdto.getPayRs() + "", 10);
            createHeadings(cb, 280, (recty + rectHt + 40), gdto.getRrorLrNo(), 10);
            createHeadings(cb, 390, (recty + rectHt + 40), gdto.getDispachedDate(), 10);
            createHeadings(cb, 470, (recty + rectHt + 40), gdto.getPayByotMS(), 10);
            createContent(cb, 20, (recty + rectHt + 40), "Freight Paid / To Pay Rs.___________________ R.R./LR No.__________________ Dt._______________ By._____________________", PdfContentByte.ALIGN_LEFT);
            createHeadings(cb, 120, (recty + rectHt + 20), gdto.getDocumentThrough(), 10);
            createContent(cb, 20, (recty + rectHt + 20), "Documents through.______________________________________________________________________________________________", PdfContentByte.ALIGN_LEFT);
            cb.rectangle(20, (recty + rectHt + 80), 250, 65);
            String addree[] = ("Mrs." + gdto.getCustomer().getName() + "," + gdto.getCustomer().getAddress()).split(","); //Satish Syamala,Mariyapuram,Gessugonda,Warangal-TS,506330 ";
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
                createContent(cb, 27, (recty + rectHt + 130) - (len * 10), ss, PdfContentByte.ALIGN_LEFT);
                len++;
            }
            createHeadings(cb, rectX + 34, (recty + 3), "E.& O.E", 12);


            List<ProductDto> list = gdto.getProductDtos();
            if (!list.isEmpty()) {
                generateLayout(doc, cb, gdto.getCustDiscount());
                y = (recty + rectHt - 35);
                for (int i = 0; i < list.size(); i++) {
                    ProductDto pd = list.get(i);
                    if (pd.getCustProID() != null && pd.getCustProID().trim().length() > 0) {
                        createContent(cb, 60, y, pd.getCustProID(), PdfContentByte.ALIGN_LEFT);
                        createContent(cb, 60, y, "_________", PdfContentByte.ALIGN_LEFT);
                        y = y - 10;
                    }
                    String pro = pd.getProductDetails();
                    double qut = pd.getQuantity();
                    double price = pd.getSalesPrice();
                    totalDut += qut;
                    tatalAmount += (qut * price);
                    generateDetail(doc, cb, String.valueOf((i + 1)), y, pro, String.valueOf(qut) + " " + pd.getProductType(), String.valueOf(price), String.valueOf((qut * price)));
                    y = y - 12;
                    if (pd.getOptionalText() != null && pd.getOptionalText().trim().length() > 0) {
                        generateDetail(doc, cb, "", y, pd.getOptionalText(), "", "", "");
                        y = y - 15;

                    }
                }
                int lineAdd = 480;
               

                if (gdto.getProDiscount() > 0) {
                    cb.moveTo(rectX + lineAdd, y);
                    cb.lineTo(recWdth + 20, y);

                    y = y - 10;
                    generateDetail(doc, cb, "", y, "", "", "Total", tatalAmount + "");
                    y = y - 15;
                    createContent(cb, 60, y, "Less Cash Discount", PdfContentByte.ALIGN_LEFT);
                    createContent(cb, 60, y, "___________________", PdfContentByte.ALIGN_LEFT);
                    double dic = 0.0;
                    for (int i = 0; i < list.size(); i++) {
                        ProductDto pd = list.get(i);
                       
                        double disc = 0.0;
                        String lab = "";
                        if (gdto.getProDiscount() == 1) {
                            lab = " % per Kg";
                            disc = (pd.getQuantity() * ((pd.getSalesPrice() / 100) * pd.getDiscount()));
                        } else if (gdto.getProDiscount() == 3) {
                            lab = " % On Amount";
                            disc = (((pd.getQuantity() * pd.getSalesPrice()) / 100) * pd.getDiscount());
                        } else if (gdto.getProDiscount() == 2) {
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
                    //  createContent(cb, 540, y, df.format(dic), PdfContentByte.ALIGN_RIGHT);
                    y -= 5;

                    tatalAmount -= dic;

                    // createContent(cb, 540, y, tatalAmount + "", PdfContentByte.ALIGN_RIGHT);
                    gdto.setFinalDiscount(dic);
                } else {
                    gdto.setFinalDiscount(0.0);
                }

                if (gdto.getProOtherCharges() > 0) {
                    cb.moveTo(rectX + lineAdd, y);
                    cb.lineTo(recWdth + 20, y);
                    y = y - 10;
                    generateDetail(doc, cb, "", y, "", "", "Total", df.format(tatalAmount) + "");
                    y = y - 15;
                    String text = (gdto.getProOtherCharges() == 1 ? "Other Charges" : "P & F Charges");
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
                    gdto.setFinaleOtherCharges(dic);

                } else {
                    gdto.setFinaleOtherCharges(0.0);
                }

                List<OtherInvoiceData> invoiceDatas = gdto.getOtherInvoiceData();
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

                    for (int j = 0; j < invoiceDatas.size(); j++) {
                        OtherInvoiceData od = invoiceDatas.get(j);
                        if (od.getOrderBy() == 1) {
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




                double cstOrVat = (gdto.getVatOrCst() == 1) ? gdto.getBasicInformation().getVat() : gdto.getBasicInformation().getCst();
                String cv = (gdto.getVatOrCst() == 1) ? "Vat " + gdto.getBasicInformation().getVat() + "%" : "CST " + gdto.getBasicInformation().getCst() + "%";

                if (gdto.getCustDiscount() > 0) {

                    generateDetail(doc, cb, "", (recty + 65), "", "", "Total Amount", String.valueOf(df.format(tatalAmount)));
                    int disc = (int) ((tatalAmount / 100) * gdto.getCustDiscount());
                    generateDetail(doc, cb, "", (recty + 45), "", "", "Discount " + gdto.getCustDiscount() + "%", String.valueOf(df.format(disc)));
                    gdto.setFinalDiscount(gdto.getFinalDiscount() + disc);
                    tatalAmount -= disc;
                    int vatamt = (int) Math.round((tatalAmount / 100) * cstOrVat);
                    generateDetail(doc, cb, "", (recty + 25), "", "", cv, String.valueOf(df.format(vatamt)));
                    gdto.setFinaleVatOrCst(vatamt);
                    tatalAmount += vatamt;
                    generateDetail(doc, cb, "", (recty + 5), "", "", "Grand Total Amount", String.valueOf(df.format(tatalAmount)));

                } else {
                    generateDetail(doc, cb, "", (recty + 45), "", "", "Total Amount", String.valueOf(df.format(tatalAmount)));
                    int vatamt = (int) Math.round((tatalAmount / 100) * cstOrVat);
                    generateDetail(doc, cb, "", (recty + 25), "", "", cv, String.valueOf(df.format(vatamt)));
                    gdto.setFinaleVatOrCst(vatamt);
                    tatalAmount += vatamt;
                    generateDetail(doc, cb, "", (recty + 5), "", "", "Grand Total Amount", Math.round(tatalAmount) + ".00");
                }
            }
            gdto.setGrandTotal(tatalAmount);
            createContent(cb, 550, recty - 10, ("(" + NumberToWord.getNuberInWord((int) Math.round(tatalAmount)) + " Rupees Only )").toUpperCase(), PdfContentByte.ALIGN_RIGHT);
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
        return gdto;
    }

    private void generateLayout(Document doc, PdfContentByte cb, double dis) {

        try {




            cb.rectangle(rectX, recty, recWdth, rectHt);
            cb.moveTo(rectX, (recty + rectHt - 20));
            cb.lineTo(recWdth + 20, (recty + rectHt - 20));

            cb.moveTo((rectX + 30), recty);
            cb.lineTo((rectX + 30), (recty + rectHt));

            cb.moveTo((rectX + 330), recty);
            cb.lineTo((rectX + 330), (recty + rectHt));



            // cb.moveTo((rectX + 480), recty);
            //cb.lineTo((rectX + 480), (recty + rectHt));

            cb.moveTo((rectX + 480), recty);
            cb.lineTo((rectX + 480), (recty + rectHt));

            if (dis == 0) {
                cb.moveTo((rectX + 410), recty + 60);
                cb.lineTo((rectX + 410), (recty + rectHt));
                cb.moveTo(rectX + 330, (recty + 60));
                cb.lineTo(recWdth + 20, (recty + 60));
                cb.moveTo(rectX + 330, (recty + 40));
                cb.lineTo(recWdth + 20, (recty + 40));
                cb.moveTo(rectX + 330, (recty + 20));
                cb.lineTo(recWdth + 20, (recty + 20));
            } else {
                cb.moveTo((rectX + 410), recty + 80);
                cb.lineTo((rectX + 410), (recty + rectHt));
                cb.moveTo(rectX + 330, (recty + 80));
                cb.lineTo(recWdth + 20, (recty + 80));
                cb.moveTo(rectX + 330, (recty + 60));
                cb.lineTo(recWdth + 20, (recty + 60));
                cb.moveTo(rectX + 330, (recty + 40));
                cb.lineTo(recWdth + 20, (recty + 40));
                cb.moveTo(rectX + 330, (recty + 20));
                cb.lineTo(recWdth + 20, (recty + 20));
            }




            // Invoice Detail box Text Headings
            createHeadings(cb, (rectX + 2), (recty + rectHt - 15), "Sr.No", 10);
            createHeadings(cb, (rectX + 150), (recty + rectHt - 15), "Product Name", 10);
            createHeadings(cb, (rectX + 350), (recty + rectHt - 15), "Quantity", 10);
            createHeadings(cb, (rectX + 415), (recty + rectHt - 15), "Rate for KG", 10);
            createHeadings(cb, (rectX + 493), (recty + rectHt - 15), "Amount", 10);

            // add the images
            Image companyLogo = Image.getInstance(getClass().getResource("/com/hes/images/Header2.png"));
            companyLogo.setAbsolutePosition(7, 650);
            companyLogo.scalePercent(92);
            doc.add(companyLogo);
            Image companyLogo1 = Image.getInstance(getClass().getResource("/com/hes/images/FooterImage1.png"));
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
