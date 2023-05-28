/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InvoicePanel.java
 *
 * Created on Jul 29, 2014, 5:57:32 AM
 */
package com.hes.view;

import com.hes.model.InvoiceModel;
import com.hes.pojo.Customer;
import com.hes.pojo.OtherInvoiceData;
import com.hes.pojo.Product;
import com.hes.pojo.Supplier;
import com.hes.util.GenerateBillDto;
import com.hes.util.ProductDto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author SATISH SYAMALA
 */
public class InvoicePanel extends javax.swing.JPanel {

    /** Creates new form InvoicePanel */
    private int billType = 0;
    private HomePanel homePanel;
    JXDatePicker dpInvioceTime = new JXDatePicker();
    JXDatePicker dpOrderDate = new JXDatePicker();
    JXDatePicker dpDCDate = new JXDatePicker();
    JXDatePicker dpTransportDate = new JXDatePicker();
    JXDatePicker dpPODate = new JXDatePicker();
    JXDatePicker dpSupInvDate = new JXDatePicker();
    JXDatePicker dpRemovalGoodsDate = new JXDatePicker();
    JXDatePicker dpSplrInvoicDate = new JXDatePicker();
    InvoiceModel invoiceModel = new InvoiceModel();
    private List<Customer> customers = null;
    private Customer selectedCustomer = null;
    private List<Product> customersProducts = null;
    private List<Product> selectedCustomersProducts = null;
    private List<ProductDto> productDtos = null;
    private boolean excieDuty = false;
    private GenerateBillDto generateBillDto = null;
    SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdTime = new SimpleDateFormat("hh:mm a");
    // page 2 comp
    JTextField[] salesPrice;
    JTextField[] packSize;
    JTextField[] custProId;
    JTextField[] proType;
    JTextField[] noOfPacks;
    JTextField[] proDiscount;
    JTextField[] proOtherChar;
    // page 3 comp
    JTextField[] productDetails;
    JTextField[] quantite;
    JTextField[] quantiteType;
    JTextField[] priceUnit;
    JTextField[] proAmount;
    JTextField[] ctProId;
    JTextField[] otherDiscription;
    JTextField[] purchaseCost;;
    List<OtherInvoiceData> otherInvoiceData;
     List<Supplier> list=null;
    int status = 0;
    int productDiscount;
    int proOtherCharge;

    public InvoicePanel() {
        initComponents();
        dpInvioceTime.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dpInvioceTime.setFormats("dd-MM-yyyy");
        dpInvioceTime.setDate(new Date());
        dpInvioceTime.setBounds(400, 110, 150, 25);
        invoicePanel1.add(dpInvioceTime);

        dpOrderDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dpOrderDate.setFormats("dd-MM-yyyy");
        dpOrderDate.setDate(new Date());
        dpOrderDate.setBounds(580, 30, 150, 25);
        cvPanel.add(dpOrderDate);

        dpDCDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dpDCDate.setFormats("dd-MM-yyyy");
        dpDCDate.setDate(new Date());
        dpDCDate.setBounds(470, 70, 250, 25);
        cvPanel.add(dpDCDate);

        dpPODate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dpPODate.setFormats("dd-MM-yyyy");
        dpPODate.setDate(new Date());
        dpPODate.setBounds(370, 30, 130, 25);
        endPanel.add(dpPODate);

        dpSupInvDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dpSupInvDate.setFormats("dd-MM-yyyy");
        dpSupInvDate.setDate(new Date());
        dpSupInvDate.setBounds(370, 70, 130, 25);
        endPanel.add(dpSupInvDate);

        dpTransportDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dpTransportDate.setFormats("dd-MM-yyyy");
        dpTransportDate.setDate(new Date());
        dpTransportDate.setBounds(330, 200, 160, 25);
        invoicePanel2.add(dpTransportDate);

        dpRemovalGoodsDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dpRemovalGoodsDate.setFormats("dd-MM-yyyy");
        dpRemovalGoodsDate.setDate(new Date());
        dpRemovalGoodsDate.setBounds(240, 80, 210, 25);
        invoicePanel5.add(dpRemovalGoodsDate);

        dpSplrInvoicDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dpSplrInvoicDate.setFormats("dd-MM-yyyy");
        dpSplrInvoicDate.setDate(new Date());
        dpSplrInvoicDate.setBounds(240, 250, 210, 25);
        invoicePanel5.add(dpSplrInvoicDate);

        productDiscount = 0;
        proOtherCharge = 0;
        otherInvoiceData = new ArrayList<OtherInvoiceData>();

    }

    public void init(HomePanel homePanel) {
        this.homePanel = homePanel;
        generateBillDto = new GenerateBillDto();
        customers = new ArrayList<Customer>();
        selectedCustomer = null;
        customersProducts = new ArrayList<Product>();
        selectedCustomersProducts = new ArrayList<Product>();
        productDtos = new ArrayList<ProductDto>();
        dpInvioceTime.setDate(new Date());
        dpDCDate.setDate(new Date());
        dpOrderDate.setDate(new Date());
        dpPODate.setDate(new Date());
        dpSupInvDate.setDate(new Date());
        dpTransportDate.setDate(new Date());
        dpRemovalGoodsDate.setDate(new Date());
        customers = invoiceModel.getCustomerList();
        productDiscount = 0;
        proOtherCharge = 0;
        String[] sList = new String[customers.size() + 1];
        sList[0] = "----Select----";
        for (int i = 0; i < customers.size(); i++) {
            Customer sp = customers.get(i);
            sList[i + 1] = sp.getName();
        }
        cmbCustomer.setModel(new javax.swing.DefaultComboBoxModel(sList));
        invoicePanel5.setVisible(false);
        invoicePanel1.setVisible(true);
        invoicePanel2.setVisible(false);
        invoicePanel3.setVisible(false);
        invoicePanel4.setVisible(false);
        otherInvoiceData = new ArrayList<OtherInvoiceData>();
        productDiscount = 0;
        rdNon.setSelected(true);
        rdPerKg.setSelected(false);
        rdpecentageOnKg.setSelected(false);
        rdOnAmount.setSelected(false);
        status = 0;
        txtDiscount.setText("0.0");
        txtInvoiceTime.setText(sdTime.format(new Date()));
        // txtRemovalOfGoodsTime.setText(sdTime.format(new Date()));
        // txtSplrInvoiceTime.setText(sdTime.format(new Date()));
        clearFields();
        // tempDateFields();
        txtInvoiceNo.setText(invoiceModel.getInvoiceNumber());
    }

    public void addProductList() {
        String[] data = new String[customersProducts.size()];
        for (int i = 0; i < customersProducts.size(); i++) {
            Product p = customersProducts.get(i);
            data[i] = p.getProductName();
        }
        String[] data1 = new String[selectedCustomersProducts.size()];
        for (int i = 0; i < selectedCustomersProducts.size(); i++) {
            Product p = selectedCustomersProducts.get(i);
            data1[i] = p.getProductName();

        }
        setProductList(data);
        setSelectedProductList(data1);

    }

    public void setProductList(final String[] product) {
        allProductsList.setSize(150, 100);
        allProductsList.setModel(new javax.swing.AbstractListModel() {

            String[] strings = product;

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        allProductsList.setVisibleRowCount(20);
    }

    public void setSelectedProductList(final String[] product) {
        selectedProducts.setSize(150, 100);
        selectedProducts.setModel(new javax.swing.AbstractListModel() {

            String[] strings = product;

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        selectedProducts.setVisibleRowCount(20);
    }

    public void closeAllPanels() {
        invoicePanel5.setVisible(false);
        invoicePanel1.setVisible(false);
        invoicePanel2.setVisible(false);
        endPanel.setVisible(false);
        cvPanel.setVisible(false);
        invoicePanel4.setVisible(false);
        invoicePanel3.setVisible(false);

    }

    public void clearFields() {
        txtInvoiceNo.setText("");
        txtDiscount.setText("0.0");
        txtDispachedFrom.setText("");
        txtDispachedTo.setText("");
        txtDocumentThrough.setText("");
        txtOrderNumber.setText("");
        txtOurDCNumber.setText("");
        txtPaidBy.setText("");
        txtPayrs.setText("0");
        txtPONumber.setText("");
        txtBeckInvoiceNo.setText("");
        txtExciseInvNo.setText("");
        txtSpInvAmount.setText("0");
        txtSpInvNo.setText("");
        txtModeOfTrn.setText("");
        txtVehicleRegNo.setText("");
        txtInvoiceAmount.setText("0");
        txtRRorLRNumber.setText("");
        txtEntryPageNo.setText("");
        rdCST.setSelected(false);
        rdVAT.setSelected(false);
        productDiscount = 0;
        rdNon.setSelected(true);
        rdPerKg.setSelected(false);
        rdpecentageOnKg.setSelected(false);
        rdOnAmount.setSelected(false);
        chbExciseDuty.setSelected(false);
        endorsement.setSelected(false);
        cstorvat.setSelected(false);
        customersProducts.clear();
        selectedCustomersProducts.clear();
        setProductTableDate();
        addProductList();
    }

    public void tempDateFields() {
        txtInvoiceNo.setText("IN-3434324");
        txtDiscount.setText("0.0");
        txtDispachedFrom.setText("Hyderabad");
        txtDispachedTo.setText("Chennai");
        txtDocumentThrough.setText("Post");
        txtOrderNumber.setText("OR-345678");
        txtOurDCNumber.setText("5368952");
        txtPaidBy.setText("Navatha Transport");
        txtPayrs.setText("3000");
        txtPONumber.setText("6897564");
        txtBeckInvoiceNo.setText("IN-5789876");
        txtExciseInvNo.setText("EX-987654");
        txtSpInvAmount.setText("234567");
        txtSpInvNo.setText("IN-4567896");
        txtModeOfTrn.setText("Road");
        txtVehicleRegNo.setText("AP09-BF 4331");
        txtInvoiceAmount.setText("167890");
        txtRRorLRNumber.setText("LR-234567");
        txtEntryPageNo.setText("435467");
    }

    public void setSelectedPro() {
        productDtos = new ArrayList<ProductDto>();
        int count = selectedCustomersProducts.size();
        for (int i = 0; i < count; i++) {
            Product pd = selectedCustomersProducts.get(i);
            ProductDto pdto = new ProductDto();
            pdto.setNoOfPackets(0);
            pdto.setPackSize(pd.getPackageSize());
            pdto.setProductCode(pd.getProductCode());
            pdto.setProductName(pd.getProductName());
            pdto.setPurchasePrice(pd.getPurchaseCost());
            pdto.setSalesPrice(0);
            pdto.setIdentifyMark(pd.getIdentifMark());
            pdto.setProductId(pd.getProductId());
            pdto.setQuantity(0);
            pdto.setCustProID("");
            pdto.setProductType("Kg");
            pdto.setDiscount(0.0);
            pdto.setOtherCharges(0.0);
            pdto.setOptionalText("");
            productDtos.add(pdto);
        }
    }
    int txtwidth = 25;
    int textSize = 14;

    public void setProductTableDate() {
        int count = productDtos.size();
        salesPrice = new JTextField[count];
        packSize = new JTextField[count];
        noOfPacks = new JTextField[count];
        proDiscount = new JTextField[count];
        proOtherChar = new JTextField[count];
        custProId = new JTextField[count];
        proType = new JTextField[count];
        productPanel.removeAll();
        productPanel.repaint();
        if (!productDtos.isEmpty()) {
            for (int i = 0; i < count; i++) {
                ProductDto pd = productDtos.get(i);

                JLabel proName = new JLabel();
                proName.setFont(new java.awt.Font("Tahoma", 1, 12));
                proName.setText(pd.getProductName());
                productPanel.add(proName);
                proName.setBounds(10, 4 + (i * 30), 200, 20);


                custProId[i] = new javax.swing.JTextField();
                custProId[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
                custProId[i].setText(pd.getCustProID());
                // custProId[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                productPanel.add(custProId[i]);
                custProId[i].setBounds(130, 2 + (i * 30), 90, txtwidth);

                proType[i] = new javax.swing.JTextField();
                proType[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
                proType[i].setText(pd.getProductType());
                // proType[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                productPanel.add(proType[i]);
                proType[i].setBounds(230, 2 + (i * 30), 90, txtwidth);

                salesPrice[i] = new javax.swing.JTextField();
                salesPrice[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
                salesPrice[i].setText(pd.getSalesPrice() + "");
                salesPrice[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                productPanel.add(salesPrice[i]);
                salesPrice[i].setBounds(330, 2 + (i * 30), 70, txtwidth);

                packSize[i] = new javax.swing.JTextField();
                packSize[i].setFont(new java.awt.Font("Tahoma", 1, textSize)); // NOI18N
                packSize[i].setHorizontalAlignment(javax.swing.JTextField.RIGHT);
                packSize[i].setText("" + pd.getPackSize());
                productPanel.add(packSize[i]);
                packSize[i].setBounds(430, 2 + (i * 30), 70, txtwidth);

                noOfPacks[i] = new javax.swing.JTextField();
                noOfPacks[i].setFont(new java.awt.Font("Tahoma", 1, textSize)); // NOI18N
                noOfPacks[i].setHorizontalAlignment(javax.swing.JTextField.RIGHT);
                noOfPacks[i].setText("" + pd.getNoOfPackets());
                productPanel.add(noOfPacks[i]);
                noOfPacks[i].setBounds(520, 2 + (i * 30), 70, txtwidth);

                proDiscount[i] = new javax.swing.JTextField();
                proDiscount[i].setFont(new java.awt.Font("Tahoma", 1, textSize)); // NOI18N
                proDiscount[i].setHorizontalAlignment(javax.swing.JTextField.RIGHT);
                proDiscount[i].setText(pd.getDiscount() + "");
                if (productDiscount > 0) {
                    proDiscount[i].setEnabled(true);
                } else {
                    proDiscount[i].setEnabled(false);
                }
                productPanel.add(proDiscount[i]);
                proDiscount[i].setBounds(600, 2 + (i * 30), 70, txtwidth);

                proOtherChar[i] = new javax.swing.JTextField();
                proOtherChar[i].setFont(new java.awt.Font("Tahoma", 1, textSize)); // NOI18N
                proOtherChar[i].setHorizontalAlignment(javax.swing.JTextField.RIGHT);
                proOtherChar[i].setText("" + pd.getOtherCharges());
                if (proOtherCharge > 0) {
                    proOtherChar[i].setEnabled(true);
                } else {
                    proOtherChar[i].setEnabled(false);
                }
                productPanel.add(proOtherChar[i]);
                proOtherChar[i].setBounds(680, 2 + (i * 30), 60, txtwidth);




            }
        }
    }

    public void setExiceData() {
        int count = productDtos.size();
        productDetails = new JTextField[count];
        quantite = new JTextField[count];
        quantiteType = new JTextField[count];
        priceUnit = new JTextField[count];
        ctProId = new JTextField[count];
        pnlExiceDuty.removeAll();
        pnlExiceDuty.repaint();
        int yaxis = 0;
        for (int i = 0; i < productDtos.size(); i++) {
            ProductDto dto = productDtos.get(i);
            productDetails[i] = new javax.swing.JTextField();
            productDetails[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
            productDetails[i].setText(dto.getProductName());
            //productDetails[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            pnlExiceDuty.add(productDetails[i]);
            productDetails[i].setBounds(10, 2 + (yaxis * 30), 250, txtwidth);

            ctProId[i] = new javax.swing.JTextField();
            ctProId[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
            String ctp = dto.getIdentifyMark();
            ctProId[i].setText(ctp);
            //productDetails[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            pnlExiceDuty.add(ctProId[i]);
            ctProId[i].setBounds(280, 2 + (yaxis * 30), 90, txtwidth);


            quantite[i] = new javax.swing.JTextField();
            quantite[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
            String qut = dto.getQuntyInGrama() + "";
            quantite[i].setText(qut);
            quantite[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            pnlExiceDuty.add(quantite[i]);
            quantite[i].setBounds(390, 2 + (yaxis * 30), 90, txtwidth);

            quantiteType[i] = new javax.swing.JTextField();
            quantiteType[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
            String qutt = dto.getProductCode() + "";
            quantiteType[i].setText(qutt);
            //  quantiteType[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            pnlExiceDuty.add(quantiteType[i]);
            quantiteType[i].setBounds(490, 2 + (yaxis * 30), 120, txtwidth);

            priceUnit[i] = new javax.swing.JTextField();
            priceUnit[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
            String prc = dto.getPurchasePrice() + "";
            priceUnit[i].setText(prc);
            priceUnit[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            pnlExiceDuty.add(priceUnit[i]);
            priceUnit[i].setBounds(620, 2 + (yaxis * 30), 100, txtwidth);

            yaxis++;



        }
    }

    public void confirmation() {
        int count = productDtos.size();
        productDetails = new JTextField[count];
        quantite = new JTextField[count];
        quantiteType = new JTextField[count];
        priceUnit = new JTextField[count];
        proAmount = new JTextField[count];
        otherDiscription = new JTextField[count];
        ctProId = new JTextField[count];
        purchaseCost = new JTextField[count];
        confirmPanel.removeAll();
        confirmPanel.repaint();
        int yaxis = 0;
        for (int i = 0; i < productDtos.size(); i++) {
            ProductDto dto = productDtos.get(i);

            productDetails[i] = new javax.swing.JTextField();
            productDetails[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
            String proName1 = dto.getProductName() + " ( " + dto.getPackSize() + " " + dto.getProductType() + " X " + dto.getNoOfPackets() + " )";
            productDetails[i].setText(dto.getProductDetails());
            //productDetails[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            confirmPanel.add(productDetails[i]);
            productDetails[i].setBounds(0, 2 + (yaxis * 30), 350, txtwidth);

            ctProId[i] = new javax.swing.JTextField();
            ctProId[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
            String ctp = dto.getCustProID();
            ctProId[i].setText(ctp);
            //productDetails[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            confirmPanel.add(ctProId[i]);
            ctProId[i].setBounds(355, 2 + (yaxis * 30), 70, txtwidth);


            quantite[i] = new javax.swing.JTextField();
            quantite[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
            String qut = dto.getQuntyInGrama() + "";
            quantite[i].setText(qut);
            quantite[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            confirmPanel.add(quantite[i]);
            quantite[i].setBounds(430, 2 + (yaxis * 30), 60, txtwidth);

            quantiteType[i] = new javax.swing.JTextField();
            quantiteType[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
            String qutt = dto.getProductType() + "";
            quantiteType[i].setText(qutt);
            //  quantiteType[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            confirmPanel.add(quantiteType[i]);
            quantiteType[i].setBounds(495, 2 + (yaxis * 30), 70, txtwidth);

            priceUnit[i] = new javax.swing.JTextField();
            priceUnit[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
            String prc = dto.getSalesPrice() + "";
            priceUnit[i].setText(prc);
            priceUnit[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            confirmPanel.add(priceUnit[i]);
            priceUnit[i].setBounds(570, 2 + (yaxis * 30), 70, txtwidth);

            proAmount[i] = new javax.swing.JTextField();
            proAmount[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
            String amt = (dto.getQuantity() * dto.getSalesPrice()) + "";
            proAmount[i].setText(amt);
            proAmount[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            confirmPanel.add(proAmount[i]);
            proAmount[i].setBounds(645, 2 + (yaxis * 30), 100, txtwidth);
            yaxis++;

            JLabel proName = new JLabel();
            proName.setFont(new java.awt.Font("Tahoma", 1, textSize));
            proName.setText("Other Info");
            confirmPanel.add(proName);
            proName.setBounds(0, 2 + (yaxis * 30), 90, txtwidth);

            otherDiscription[i] = new javax.swing.JTextField();
            otherDiscription[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
            String opt = dto.getOptionalText();
            otherDiscription[i].setText(opt);
            //  otherDiscription[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            confirmPanel.add(otherDiscription[i]);
            otherDiscription[i].setBounds(95, 2 + (yaxis * 30), 450, txtwidth);


            JLabel proName2 = new JLabel();
            proName2.setFont(new java.awt.Font("Tahoma", 1, textSize));
            proName2.setText("Purchase Cost");
            confirmPanel.add(proName2);
            proName2.setBounds(560, 2 + (yaxis * 30), 110, txtwidth);

            purchaseCost[i] = new javax.swing.JTextField();
            purchaseCost[i].setFont(new java.awt.Font("Tahoma", 1, textSize));
            String opt1 = dto.getPurchasePrice()+"";
            purchaseCost[i].setText(opt1);
            purchaseCost[i].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            confirmPanel.add(purchaseCost[i]);
            purchaseCost[i].setBounds(670, 2 + (yaxis * 30), 80, txtwidth);

            yaxis++;



        }

    }

    public void setOtherInfo() {
        if (!otherInvoiceData.isEmpty()) {
            int yaxis = 0;
            for (int i = 0; i < otherInvoiceData.size(); i++) {
                OtherInvoiceData od = otherInvoiceData.get(i);
                if (od.getHeadingLine().trim().length() > 0) {
                    JLabel proName = new JLabel();
                    proName.setFont(new java.awt.Font("Tahoma", 1, textSize));
                    proName.setText(od.getHeadingLine().trim());
                    proName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
                    pnlOI.add(proName);
                    proName.setBounds(0, 2 + (yaxis * 30), 450, txtwidth);
                    yaxis++;
                }
                boolean check = false;
                String lines[] = od.getDiscription().trim().split("##");
                for (int k = 0; k < lines.length; k++) {
                    if (lines[k].trim().length() > 0) {
                        String line[] = lines[k].split("@@");
                        if (line[0].trim().length() > 0) {
                            check = true;
                            JLabel proName = new JLabel();
                            proName.setFont(new java.awt.Font("Tahoma", 1, textSize));
                            proName.setText(line[0]);
                            //  proName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
                            pnlOI.add(proName);
                            proName.setBounds(10, 2 + (yaxis * 30), 450, txtwidth);
                            if (line[1].trim().length() > 0) {
                                JLabel proName1 = new JLabel();
                                proName1.setFont(new java.awt.Font("Tahoma", 1, textSize));
                                proName1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                                proName1.setText(line[1]);
                                if (lines.length - 1 == k) {
                                    proName1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
                                }
                                pnlOI.add(proName1);
                                proName1.setBounds(460, 2 + (yaxis * 30), 100, txtwidth);

                            }
                            yaxis++;
                        }

                    }
                }

                if (od.getAmount() > 0 && check) {
                    yaxis--;
                    JLabel proName = new JLabel();
                    proName.setFont(new java.awt.Font("Tahoma", 1, textSize));
                    String rr = (od.getAmtType() == 0 ? "(+)" : "(-)") + od.getAmount();
                    proName.setText(rr);
                    proName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    proName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
                    pnlOI.add(proName);
                    proName.setBounds(600, 2 + (yaxis * 30), 100, txtwidth);

                }

                yaxis++;
                yaxis++;

            }
        } else {
            pnlOI.removeAll();
            ;
            pnlOI.repaint();
        }
    }

    public boolean page1Validate() {
        boolean result = false;
        if (!endorsement.isSelected() && !cstorvat.isSelected()) {
            JOptionPane.showMessageDialog(invoicePanel1, "Select Invoice Type ");
        } else if (txtInvoiceNo.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(invoicePanel1, "Invoice Number Required");
        } else if (cmbCustomer.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(invoicePanel1, "Select Customer ");
        } else if (selectedCustomersProducts.isEmpty()) {
            JOptionPane.showMessageDialog(invoicePanel1, "Select Product");
        } else {
            result = true;
        }

        if (result && status == 0) {
            if (invoiceModel.checkInvoice(txtInvoiceNo.getText().trim())) {
                JOptionPane.showMessageDialog(invoicePanel1, "Invoice Number alread exit !");
                result = false;
            }
        }
        if (result) {
            setSelectedPro();
            setProductTableDate();
            generateBillDto.setInvoiceTime(txtInvoiceTime.getText());
            generateBillDto.setInvoiceNumber(txtInvoiceNo.getText());
           
            generateBillDto.setInvoiceDate(dateCheck(dpInvioceTime));
            generateBillDto.setCustomer(selectedCustomer);
            generateBillDto.setCustDiscount(numberCheck(txtDiscount));
            generateBillDto.setExciseDuty(chbExciseDuty.isSelected());
        }


        return result;
    }

    public boolean page2Validate() {
        boolean result = true;
        if (generateBillDto.getInvoiceType() == 1) {
            if (!rdCST.isSelected() && !rdVAT.isSelected()) {
                JOptionPane.showMessageDialog(invoicePanel2, "Select CST or VAT ");
                result = false;
            }
            if (txtOrderNumber.getText().trim().length() == 0) {
                txtOrderNumber.setText(" ");
            }
            if (txtOurDCNumber.getText().trim().length() == 0) {
                txtOurDCNumber.setText(" ");
            }
        } else {
            if (txtPONumber.getText().trim().length() == 0) {
                txtPONumber.setText(" ");
            }
            if (txtBeckInvoiceNo.getText().trim().length() == 0) {
                txtBeckInvoiceNo.setText(" ");
            }
            if (txtInvoiceAmount.getText().trim().length() == 0) {
                txtBeckInvoiceNo.setText(" ");
            }
        }

        if (txtDispachedFrom.getText().trim().length() == 0) {
            txtDispachedFrom.setText(" ");
        }
        if (txtDispachedTo.getText().trim().length() == 0) {
            txtDispachedTo.setText(" ");
        }
        if (txtRRorLRNumber.getText().trim().length() == 0) {
            txtRRorLRNumber.setText(" ");
        }
        if (txtPayrs.getText().trim().length() == 0) {
            txtPayrs.setText(" ");
        }
        if (txtPaidBy.getText().trim().length() == 0) {
            txtPaidBy.setText(" ");
        }
        if (txtDocumentThrough.getText().trim().length() == 0) {
            txtDocumentThrough.setText(" ");
        }


        if (result) {

            int count = selectedCustomersProducts.size();
            if (!selectedCustomersProducts.isEmpty()) {
                for (int i = 0; i < count; i++) {
                    Product pd = selectedCustomersProducts.get(i);
                    if (numberCheck(packSize[i]) == 0) {
                        JOptionPane.showMessageDialog(invoicePanel2, "Pack Size  Required for  " + pd.getProductName());
                        result = false;
                        break;
                    } else if (numberCheck(noOfPacks[i]) == 0) {
                        JOptionPane.showMessageDialog(invoicePanel2, "Number of packs  Required for  " + pd.getProductName());
                        result = false;
                        break;
                    } else if (numberCheck(salesPrice[i]) == 0) {
                        JOptionPane.showMessageDialog(invoicePanel2, "Number of packs  Required for  " + pd.getProductName());
                        result = false;
                        break;
                    }
//                    if (productDiscount>0) {
//                        if (numberCheck(proDiscount[i]) == 0) {
//                            JOptionPane.showMessageDialog(invoicePanel2, "Product discount  Required for  " + pd.getProductName());
//                            result = false;
//                            break;
//                        }
//                    }
//                    if (chOthercharges.isSelected()) {
//                        if (numberCheck(proOtherChar[i]) == 0) {
//                            JOptionPane.showMessageDialog(invoicePanel2, "Other charges Required for  " + pd.getProductName());
//                            result = false;
//                            break;
//                        }
//                    }

                }
            }
        }
        if (result) {
            if (generateBillDto.getInvoiceType() == 1) {
                generateBillDto.setOrderNo(txtOrderNumber.getText());
                if (txtOrderNumber.getText().trim().length() > 0) {
                    generateBillDto.setOrderDate(dateCheck(dpOrderDate));
                } else {
                    generateBillDto.setOrderDate(" ");
                }

                generateBillDto.setOurDCNo(txtOurDCNumber.getText());
                if (txtOurDCNumber.getText().trim().length() > 0) {
                    generateBillDto.setOurDCDate(dateCheck(dpDCDate));
                } else {
                    generateBillDto.setOurDCDate(" ");
                }
                if (rdCST.isSelected()) {
                    generateBillDto.setVatOrCst(2);
                } else {
                    generateBillDto.setVatOrCst(1);
                }

            } else {
                generateBillDto.setPoNo(txtPONumber.getText());
                if (txtPONumber.getText().trim().length() > 0) {
                    generateBillDto.setPoDate(dateCheck(dpPODate));
                } else {
                    generateBillDto.setPoDate(" ");
                }

                generateBillDto.setBeckInvNo(txtBeckInvoiceNo.getText());
                generateBillDto.setBeckInvAmount(numberCheck(txtInvoiceAmount));
                if (txtBeckInvoiceNo.getText().trim().length() > 0) {
                    generateBillDto.setBeckInvDate(dateCheck(dpSupInvDate));
                } else {
                    generateBillDto.setBeckInvDate(" ");
                }
            }
            generateBillDto.setDispachedFrom(txtDispachedFrom.getText());
            generateBillDto.setDispachedTo(txtDispachedTo.getText());
            generateBillDto.setDispachedDate(dateCheck(dpTransportDate));
            generateBillDto.setPayByotMS(txtPaidBy.getText());
            generateBillDto.setPayRs(numberCheck(txtPayrs));
            generateBillDto.setRrorLrNo(txtRRorLRNumber.getText());
            generateBillDto.setDocumentThrough(txtDocumentThrough.getText());
            List<ProductDto> prod = new ArrayList<ProductDto>(productDtos);
            productDtos = new ArrayList<ProductDto>();
            int count = prod.size();
            for (int i = 0; i < count; i++) {
                ProductDto pdto = prod.get(i);
                pdto.setNoOfPackets((int) numberCheck(noOfPacks[i]));
                pdto.setPackSize((int) numberCheck(packSize[i]));
                pdto.setSalesPrice(numberCheck(salesPrice[i]));
                pdto.setQuantity(pdto.getPackSize() * pdto.getNoOfPackets());
                pdto.setCustProID(custProId[i].getText());
                pdto.setProductType(proType[i].getText());
                if (productDiscount > 0) {
                    pdto.setDiscount(numberCheck(proDiscount[i]));
                } else {
                    pdto.setDiscount(0.0);
                }
                if (proOtherCharge > 0) {
                    pdto.setOtherCharges(numberCheck(proOtherChar[i]));
                } else {
                    pdto.setOtherCharges(0.0);
                }
                String proName1 = pdto.getProductName() + " ( " + pdto.getPackSize() + " " + pdto.getProductType() + " X " + pdto.getNoOfPackets() + " )";
                pdto.setProductDetails(proName1);
                String qty = " ( " + pdto.getPackSize() + " " + pdto.getProductType() + " X " + pdto.getNoOfPackets() + " )";
                pdto.setQntyinExice(qty);
                pdto.setQuntyInGrama(pdto.getQuantity() + "");

                productDtos.add(pdto);
            }
            generateBillDto.setProductDtos(productDtos);
            generateBillDto.setProDiscount(productDiscount);
            generateBillDto.setProOtherCharges(proOtherCharge);



        }



        return result;
    }

    public boolean page3Validate() {
        boolean result = true;
        List<ProductDto> prod = new ArrayList<ProductDto>(productDtos);
        productDtos = new ArrayList<ProductDto>();
        int count = prod.size();
        for (int i = 0; i < count; i++) {
            ProductDto pdto = prod.get(i);
            pdto.setProductDetails(productDetails[i].getText());
            pdto.setSalesPrice(numberCheck(priceUnit[i]));
            pdto.setQuantity(numberCheck(quantite[i]));
            pdto.setCustProID(ctProId[i].getText());
            pdto.setProductType(quantiteType[i].getText());
            pdto.setQuntyInGrama(pdto.getQuantity() + "");
            pdto.setOptionalText(otherDiscription[i].getText());
            pdto.setPurchasePrice(numberCheck(purchaseCost[i]));
            productDtos.add(pdto);
        }
        return result;
    }

    public boolean page4Validate() {
        boolean result = true;
        generateBillDto.setOtherInvoiceData(otherInvoiceData);
        return result;
    }

    public boolean page5Validate() {
        boolean result = true;

        if (txtEntryPageNo.getText().trim().length() == 0) {
            txtEntryPageNo.setText(" ");
        }
        if (txtModeOfTrn.getText().trim().length() == 0) {
            txtModeOfTrn.setText(" ");
        }
        if (txtVehicleRegNo.getText().trim().length() == 0) {
            txtVehicleRegNo.setText(" ");
        }
        if (txtSpInvNo.getText().trim().length() == 0) {
            txtSpInvNo.setText(" ");
        }
        if (txtExciseInvNo.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(invoicePanel5, "Excise Invoice number  Required");
            result = false;
        } else if (numberCheck(txtSpInvAmount) == 0) {
            JOptionPane.showMessageDialog(invoicePanel5, "Supplier Invoice Amount Required");
            result = false;
        } else {
            result = true;
        }
        if (result) {
             Supplier sp=list.get(cmbSplrAddree.getSelectedIndex()-1);
            generateBillDto.setExciseInvNo(txtExciseInvNo.getText());
            generateBillDto.setEnrtyPageNo(txtEntryPageNo.getText());
            generateBillDto.setSuppInvBill(txtSpInvNo.getText());
            generateBillDto.setRemovalOfGoodsTime(txtRemovalOfGoodsTime.getText());
            generateBillDto.setSplrInvoiceTime(sd.format(dpSplrInvoicDate.getDate()) + " " + txtSplrInvoiceTime.getText());
            generateBillDto.setSuppInvAmount((int) numberCheck(txtSpInvAmount));
            generateBillDto.setModeOfTrns(txtModeOfTrn.getText());
            generateBillDto.setVehicleRegNo(txtVehicleRegNo.getText());
            generateBillDto.setDateOfRemovelGoods(dateCheck(dpRemovalGoodsDate));
            generateBillDto.setSplrAddress(cmbSplrAddree.getSelectedItem().toString());
            generateBillDto.setSupplier(sp);
            List<ProductDto> prod = new ArrayList<ProductDto>(productDtos);
            productDtos = new ArrayList<ProductDto>();
            int count = prod.size();
            for (int i = 0; i < count; i++) {
                ProductDto pdto = prod.get(i);
                pdto.setProductName(productDetails[i].getText());
                pdto.setPurchasePrice(numberCheck(priceUnit[i]));
                pdto.setQuantity(numberCheck(quantite[i]));
                pdto.setIdentifyMark(ctProId[i].getText());
                pdto.setProductCode(quantiteType[i].getText());
                pdto.setQuntyInGrama(pdto.getQuantity() + "");
                productDtos.add(pdto);
            }
        }

        return result;
    }

    public String dateCheck(JXDatePicker temp) {
        String result = "";
        if (temp.getDate() != null) {
            result = sd.format(temp.getDate());
        }
        return result;

    }

    public double numberCheck(JTextField temp) {
        try {
            return Double.parseDouble(temp.getText());
        } catch (Exception e) {
            return 0.0;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        billtype = new javax.swing.ButtonGroup();
        rdCstOrVat = new javax.swing.ButtonGroup();
        discountType = new javax.swing.ButtonGroup();
        otherType = new javax.swing.ButtonGroup();
        invoicePanel3 = new javax.swing.JPanel();
        btnPage3Back = new javax.swing.JButton();
        btnPage3Next = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        btnCheck = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        confirmPanel = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        invoicePanel4 = new javax.swing.JPanel();
        btnPage4Back = new javax.swing.JButton();
        btnPage4Bill = new javax.swing.JButton();
        btnPage4Next = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txtHeader = new javax.swing.JTextField();
        txtTotalAMt = new javax.swing.JTextField();
        cmbPlusOrMinus = new javax.swing.JComboBox();
        cmbOrderBy = new javax.swing.JComboBox();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtLine3 = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        txtLine1 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        txtAmt1 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        txtLine2 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        btnOIAdd = new javax.swing.JButton();
        txtAmt2 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        txtAmt3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlOI = new javax.swing.JPanel();
        btnOICean = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        invoicePanel5 = new javax.swing.JPanel();
        btnPage5Bill = new javax.swing.JButton();
        btnPage5Back = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtEntryPageNo = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtModeOfTrn = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtVehicleRegNo = new javax.swing.JTextField();
        txtRemovalOfGoodsTime = new javax.swing.JTextField();
        txtSplrInvoiceTime = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtSpInvNo = new javax.swing.JTextField();
        txtSpInvAmount = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtExciseInvNo = new javax.swing.JTextField();
        cmbSplrAddree = new javax.swing.JComboBox();
        jLabel52 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel77 = new javax.swing.JLabel();
        pnlExiceDuty = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        invoicePanel1 = new javax.swing.JPanel();
        endorsement = new javax.swing.JRadioButton();
        cstorvat = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        btnPage1Next = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtInvoiceNo = new javax.swing.JTextField();
        txtInvoiceTime = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbCustomer = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        allProductsList = new javax.swing.JList();
        btnAddItems = new javax.swing.JButton();
        btnRemoveItems = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        selectedProducts = new javax.swing.JList();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        chbExciseDuty = new javax.swing.JCheckBox();
        jLabel48 = new javax.swing.JLabel();
        invoicePanel2 = new javax.swing.JPanel();
        endPanel = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtPONumber = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtBeckInvoiceNo = new javax.swing.JTextField();
        txtInvoiceAmount = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        cvPanel = new javax.swing.JPanel();
        txtOrderNumber = new javax.swing.JTextField();
        rdVAT = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        rdCST = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        txtOurDCNumber = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnPage2Back = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtDispachedTo = new javax.swing.JTextField();
        txtDispachedFrom = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtPayrs = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtRRorLRNumber = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtDocumentThrough = new javax.swing.JTextField();
        txtPaidBy = new javax.swing.JTextField();
        btnPage2Next = new javax.swing.JButton();
        productPanel = new javax.swing.JPanel();
        rdOnAmount = new javax.swing.JRadioButton();
        rdPerKg = new javax.swing.JRadioButton();
        rdNon = new javax.swing.JRadioButton();
        rdpecentageOnKg = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        rdOtherChr = new javax.swing.JRadioButton();
        rdPFChr = new javax.swing.JRadioButton();
        rdNoneOc = new javax.swing.JRadioButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lbDisc = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(null);

        invoicePanel3.setOpaque(false);
        invoicePanel3.setLayout(null);

        btnPage3Back.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnPage3Back.setForeground(new java.awt.Color(0, 0, 204));
        btnPage3Back.setText("<<BACK");
        btnPage3Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPage3BackActionPerformed(evt);
            }
        });
        invoicePanel3.add(btnPage3Back);
        btnPage3Back.setBounds(20, 550, 100, 30);

        btnPage3Next.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnPage3Next.setForeground(new java.awt.Color(0, 0, 204));
        btnPage3Next.setText("NEXT>>");
        btnPage3Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPage3NextActionPerformed(evt);
            }
        });
        invoicePanel3.add(btnPage3Next);
        btnPage3Next.setBounds(660, 550, 100, 30);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel57.setForeground(new java.awt.Color(255, 0, 0));
        jLabel57.setText("Product Name");
        invoicePanel3.add(jLabel57);
        jLabel57.setBounds(20, 30, 110, 25);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel61.setForeground(new java.awt.Color(255, 0, 0));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("Qyt Type");
        invoicePanel3.add(jLabel61);
        jLabel61.setBounds(520, 30, 70, 25);

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel62.setForeground(new java.awt.Color(255, 0, 0));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("Price");
        invoicePanel3.add(jLabel62);
        jLabel62.setBounds(620, 30, 60, 25);

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel63.setForeground(new java.awt.Color(255, 0, 0));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("Ct Pro ID");
        invoicePanel3.add(jLabel63);
        jLabel63.setBounds(370, 30, 70, 25);

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel65.setForeground(new java.awt.Color(255, 0, 0));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel65.setText("Quantity");
        invoicePanel3.add(jLabel65);
        jLabel65.setBounds(440, 30, 60, 25);

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel64.setForeground(new java.awt.Color(255, 0, 0));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("Amount");
        invoicePanel3.add(jLabel64);
        jLabel64.setBounds(680, 30, 80, 25);

        btnCheck.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnCheck.setForeground(new java.awt.Color(0, 0, 204));
        btnCheck.setText("CHECK");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });
        invoicePanel3.add(btnCheck);
        btnCheck.setBounds(330, 490, 100, 30);
        invoicePanel3.add(jSeparator4);
        jSeparator4.setBounds(20, 50, 740, 20);

        confirmPanel.setOpaque(false);
        confirmPanel.setLayout(null);
        invoicePanel3.add(confirmPanel);
        confirmPanel.setBounds(20, 55, 750, 410);

        jLabel55.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Confirm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N
        invoicePanel3.add(jLabel55);
        jLabel55.setBounds(10, 10, 770, 530);

        add(invoicePanel3);
        invoicePanel3.setBounds(0, 0, 790, 590);

        invoicePanel4.setOpaque(false);
        invoicePanel4.setLayout(null);

        btnPage4Back.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnPage4Back.setForeground(new java.awt.Color(0, 0, 204));
        btnPage4Back.setText("<<BACK");
        btnPage4Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPage4BackActionPerformed(evt);
            }
        });
        invoicePanel4.add(btnPage4Back);
        btnPage4Back.setBounds(20, 550, 100, 30);

        btnPage4Bill.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnPage4Bill.setForeground(new java.awt.Color(0, 0, 204));
        btnPage4Bill.setText("BILL");
        btnPage4Bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPage4BillActionPerformed(evt);
            }
        });
        invoicePanel4.add(btnPage4Bill);
        btnPage4Bill.setBounds(660, 550, 100, 30);

        btnPage4Next.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnPage4Next.setForeground(new java.awt.Color(0, 0, 204));
        btnPage4Next.setText("NEXT>>");
        btnPage4Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPage4NextActionPerformed(evt);
            }
        });
        invoicePanel4.add(btnPage4Next);
        btnPage4Next.setBounds(660, 550, 100, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel4.setText("Header");
        invoicePanel4.add(jLabel4);
        jLabel4.setBounds(280, 40, 70, 25);

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel58.setText("Total Amt");
        invoicePanel4.add(jLabel58);
        jLabel58.setBounds(340, 180, 90, 25);

        txtHeader.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel4.add(txtHeader);
        txtHeader.setBounds(340, 40, 420, 25);

        txtTotalAMt.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtTotalAMt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalAMtActionPerformed(evt);
            }
        });
        invoicePanel4.add(txtTotalAMt);
        txtTotalAMt.setBounds(420, 180, 190, 25);

        cmbPlusOrMinus.setFont(new java.awt.Font("Tahoma", 1, 14));
        cmbPlusOrMinus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Plus", "Minus" }));
        invoicePanel4.add(cmbPlusOrMinus);
        cmbPlusOrMinus.setBounds(170, 180, 150, 25);

        cmbOrderBy.setFont(new java.awt.Font("Tahoma", 1, 14));
        cmbOrderBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Before Exice Duty", "Exice Duty", "After Exice Duty" }));
        invoicePanel4.add(cmbOrderBy);
        cmbOrderBy.setBounds(110, 40, 150, 25);

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel59.setText("Oder By");
        invoicePanel4.add(jLabel59);
        jLabel59.setBounds(40, 40, 70, 25);

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel60.setText("Plus Or Minus");
        invoicePanel4.add(jLabel60);
        jLabel60.setBounds(40, 180, 120, 25);

        txtLine3.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel4.add(txtLine3);
        txtLine3.setBounds(90, 140, 490, 25);

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel66.setText("Line 1");
        invoicePanel4.add(jLabel66);
        jLabel66.setBounds(40, 80, 50, 25);

        txtLine1.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel4.add(txtLine1);
        txtLine1.setBounds(90, 80, 490, 25);

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel67.setText("Amt");
        invoicePanel4.add(jLabel67);
        jLabel67.setBounds(600, 80, 40, 25);

        txtAmt1.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel4.add(txtAmt1);
        txtAmt1.setBounds(640, 80, 120, 25);

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel68.setText("Line 1");
        invoicePanel4.add(jLabel68);
        jLabel68.setBounds(40, 110, 50, 25);

        txtLine2.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel4.add(txtLine2);
        txtLine2.setBounds(90, 110, 490, 25);

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel69.setText("Amt");
        invoicePanel4.add(jLabel69);
        jLabel69.setBounds(600, 110, 40, 25);

        btnOIAdd.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnOIAdd.setForeground(new java.awt.Color(0, 0, 204));
        btnOIAdd.setText("ADD");
        btnOIAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOIAddActionPerformed(evt);
            }
        });
        invoicePanel4.add(btnOIAdd);
        btnOIAdd.setBounds(630, 180, 100, 30);

        txtAmt2.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel4.add(txtAmt2);
        txtAmt2.setBounds(640, 110, 120, 25);

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel70.setText("Line 1");
        invoicePanel4.add(jLabel70);
        jLabel70.setBounds(40, 140, 50, 25);

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel71.setText("Amt");
        invoicePanel4.add(jLabel71);
        jLabel71.setBounds(600, 140, 40, 25);

        txtAmt3.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel4.add(txtAmt3);
        txtAmt3.setBounds(640, 140, 120, 25);

        jScrollPane1.setOpaque(false);

        pnlOI.setOpaque(false);
        pnlOI.setLayout(null);
        jScrollPane1.setViewportView(pnlOI);

        invoicePanel4.add(jScrollPane1);
        jScrollPane1.setBounds(20, 220, 750, 300);

        btnOICean.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnOICean.setForeground(new java.awt.Color(0, 0, 204));
        btnOICean.setText("CLEAR");
        btnOICean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOICeanActionPerformed(evt);
            }
        });
        invoicePanel4.add(btnOICean);
        btnOICean.setBounds(340, 550, 100, 30);

        jLabel56.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Other Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N
        invoicePanel4.add(jLabel56);
        jLabel56.setBounds(10, 10, 770, 530);

        add(invoicePanel4);
        invoicePanel4.setBounds(0, 0, 790, 590);

        invoicePanel5.setOpaque(false);
        invoicePanel5.setLayout(null);

        btnPage5Bill.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnPage5Bill.setForeground(new java.awt.Color(0, 0, 204));
        btnPage5Bill.setText("BILL");
        btnPage5Bill.setOpaque(false);
        btnPage5Bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPage5BillActionPerformed(evt);
            }
        });
        invoicePanel5.add(btnPage5Bill);
        btnPage5Bill.setBounds(660, 540, 100, 30);

        btnPage5Back.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnPage5Back.setForeground(new java.awt.Color(0, 0, 204));
        btnPage5Back.setText("<<BACK");
        btnPage5Back.setOpaque(false);
        btnPage5Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPage5BackActionPerformed(evt);
            }
        });
        invoicePanel5.add(btnPage5Back);
        btnPage5Back.setBounds(20, 540, 100, 30);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel41.setText("Time");
        invoicePanel5.add(jLabel41);
        jLabel41.setBounds(480, 80, 40, 25);

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel42.setText("Enrty & Page No. in RG230 Reg.");
        invoicePanel5.add(jLabel42);
        jLabel42.setBounds(50, 120, 230, 25);

        txtEntryPageNo.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel5.add(txtEntryPageNo);
        txtEntryPageNo.setBounds(280, 120, 210, 25);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel43.setText("Mode of Transport");
        invoicePanel5.add(jLabel43);
        jLabel43.setBounds(50, 160, 180, 25);

        txtModeOfTrn.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel5.add(txtModeOfTrn);
        txtModeOfTrn.setBounds(190, 160, 170, 25);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel44.setText("Vehicle Registration No.");
        invoicePanel5.add(jLabel44);
        jLabel44.setBounds(380, 160, 180, 25);

        txtVehicleRegNo.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel5.add(txtVehicleRegNo);
        txtVehicleRegNo.setBounds(550, 160, 170, 25);

        txtRemovalOfGoodsTime.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel5.add(txtRemovalOfGoodsTime);
        txtRemovalOfGoodsTime.setBounds(530, 80, 110, 25);

        txtSplrInvoiceTime.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtSplrInvoiceTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSplrInvoiceTimeActionPerformed(evt);
            }
        });
        invoicePanel5.add(txtSplrInvoiceTime);
        txtSplrInvoiceTime.setBounds(530, 250, 110, 25);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel45.setText("Supplier");
        invoicePanel5.add(jLabel45);
        jLabel45.setBounds(350, 290, 70, 25);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel46.setText("Time");
        invoicePanel5.add(jLabel46);
        jLabel46.setBounds(480, 250, 50, 25);

        txtSpInvNo.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel5.add(txtSpInvNo);
        txtSpInvNo.setBounds(240, 210, 230, 25);

        txtSpInvAmount.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtSpInvAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSpInvAmountActionPerformed(evt);
            }
        });
        invoicePanel5.add(txtSpInvAmount);
        txtSpInvAmount.setBounds(200, 290, 140, 25);

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel47.setText("Excise Invoice No ");
        invoicePanel5.add(jLabel47);
        jLabel47.setBounds(50, 40, 180, 25);

        txtExciseInvNo.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel5.add(txtExciseInvNo);
        txtExciseInvNo.setBounds(200, 40, 200, 25);

        cmbSplrAddree.setFont(new java.awt.Font("Tahoma", 1, 14));
        cmbSplrAddree.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PUNE", "ANKLESHWAR" }));
        cmbSplrAddree.setOpaque(false);
        cmbSplrAddree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSplrAddreeActionPerformed(evt);
            }
        });
        invoicePanel5.add(cmbSplrAddree);
        cmbSplrAddree.setBounds(420, 290, 340, 25);

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel52.setText("Supplier Invoice Amt");
        invoicePanel5.add(jLabel52);
        jLabel52.setBounds(50, 290, 150, 25);

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel49.setText("Date of Removal of Goods");
        invoicePanel5.add(jLabel49);
        jLabel49.setBounds(50, 80, 190, 25);

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel50.setText("Supplier Invoice / Bill No.");
        invoicePanel5.add(jLabel50);
        jLabel50.setBounds(50, 210, 180, 25);

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel51.setText("Supplier Invoice Date");
        invoicePanel5.add(jLabel51);
        jLabel51.setBounds(50, 250, 160, 25);

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel72.setForeground(new java.awt.Color(255, 0, 0));
        jLabel72.setText("Product Name");
        invoicePanel5.add(jLabel72);
        jLabel72.setBounds(60, 340, 110, 25);

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel73.setForeground(new java.awt.Color(255, 0, 0));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("Identy mark");
        invoicePanel5.add(jLabel73);
        jLabel73.setBounds(310, 340, 110, 25);

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel74.setForeground(new java.awt.Color(255, 0, 0));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("Quantity");
        invoicePanel5.add(jLabel74);
        jLabel74.setBounds(430, 340, 90, 25);

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel75.setForeground(new java.awt.Color(255, 0, 0));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel75.setText("Purchase Cost");
        invoicePanel5.add(jLabel75);
        jLabel75.setBounds(660, 340, 90, 25);
        invoicePanel5.add(jSeparator5);
        jSeparator5.setBounds(40, 365, 710, 10);

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel77.setForeground(new java.awt.Color(255, 0, 0));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("Teriff S hd no");
        invoicePanel5.add(jLabel77);
        jLabel77.setBounds(540, 340, 90, 25);

        pnlExiceDuty.setLayout(null);
        invoicePanel5.add(pnlExiceDuty);
        pnlExiceDuty.setBounds(40, 370, 720, 130);

        jLabel76.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Product Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N
        invoicePanel5.add(jLabel76);
        jLabel76.setBounds(20, 320, 750, 190);

        jLabel13.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Excise Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N
        invoicePanel5.add(jLabel13);
        jLabel13.setBounds(10, 10, 770, 520);

        add(invoicePanel5);
        invoicePanel5.setBounds(0, 0, 790, 590);

        invoicePanel1.setOpaque(false);
        invoicePanel1.setLayout(null);

        billtype.add(endorsement);
        endorsement.setFont(new java.awt.Font("Tahoma", 3, 14));
        endorsement.setText("Endorsement");
        endorsement.setOpaque(false);
        invoicePanel1.add(endorsement);
        endorsement.setBounds(230, 70, 130, 30);

        billtype.add(cstorvat);
        cstorvat.setFont(new java.awt.Font("Tahoma", 3, 14));
        cstorvat.setText("CST / VAT");
        cstorvat.setOpaque(false);
        invoicePanel1.add(cstorvat);
        cstorvat.setBounds(120, 70, 110, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Customer & Product Details");
        invoicePanel1.add(jLabel1);
        jLabel1.setBounds(20, 170, 210, 25);

        btnPage1Next.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnPage1Next.setForeground(new java.awt.Color(0, 0, 204));
        btnPage1Next.setText("NEXT>>");
        btnPage1Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPage1NextActionPerformed(evt);
            }
        });
        invoicePanel1.add(btnPage1Next);
        btnPage1Next.setBounds(660, 530, 100, 30);
        invoicePanel1.add(jSeparator1);
        jSeparator1.setBounds(20, 60, 760, 10);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel3.setText("Discount on Amount in (%)");
        invoicePanel1.add(jLabel3);
        jLabel3.setBounds(20, 470, 200, 30);

        txtInvoiceNo.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel1.add(txtInvoiceNo);
        txtInvoiceNo.setBounds(110, 110, 170, 30);

        txtInvoiceTime.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel1.add(txtInvoiceTime);
        txtInvoiceTime.setBounds(610, 110, 100, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel5.setText("Time");
        invoicePanel1.add(jLabel5);
        jLabel5.setBounds(560, 110, 40, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel6.setText("Invoice No.");
        invoicePanel1.add(jLabel6);
        jLabel6.setBounds(20, 110, 100, 30);
        invoicePanel1.add(jSeparator2);
        jSeparator2.setBounds(20, 200, 760, 10);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel7.setForeground(new java.awt.Color(0, 0, 204));
        jLabel7.setText("Invoice Details");
        invoicePanel1.add(jLabel7);
        jLabel7.setBounds(20, 30, 150, 25);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel8.setText("Invoice Type");
        invoicePanel1.add(jLabel8);
        jLabel8.setBounds(20, 70, 100, 30);

        cmbCustomer.setFont(new java.awt.Font("Tahoma", 1, 14));
        cmbCustomer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "satish", "kumar", "reddy", "sham", "Item 3", "Item 4" }));
        cmbCustomer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCustomerItemStateChanged(evt);
            }
        });
        invoicePanel1.add(cmbCustomer);
        cmbCustomer.setBounds(230, 220, 490, 30);

        jLabel11.setBackground(new java.awt.Color(0, 153, 0));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Products");
        jLabel11.setOpaque(true);
        invoicePanel1.add(jLabel11);
        jLabel11.setBounds(230, 290, 210, 20);

        allProductsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        allProductsList.setVisibleRowCount(20);
        jScrollPane6.setViewportView(allProductsList);

        invoicePanel1.add(jScrollPane6);
        jScrollPane6.setBounds(230, 310, 210, 130);

        btnAddItems.setText(">>");
        btnAddItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemsActionPerformed(evt);
            }
        });
        invoicePanel1.add(btnAddItems);
        btnAddItems.setBounds(450, 340, 50, 23);

        btnRemoveItems.setText("<<");
        btnRemoveItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveItemsActionPerformed(evt);
            }
        });
        invoicePanel1.add(btnRemoveItems);
        btnRemoveItems.setBounds(450, 380, 50, 23);

        selectedProducts.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        selectedProducts.setVisibleRowCount(20);
        jScrollPane5.setViewportView(selectedProducts);

        invoicePanel1.add(jScrollPane5);
        jScrollPane5.setBounds(510, 310, 210, 130);

        jLabel10.setBackground(new java.awt.Color(0, 153, 0));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Customer Products");
        jLabel10.setOpaque(true);
        invoicePanel1.add(jLabel10);
        jLabel10.setBounds(510, 290, 210, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel9.setText("Customer");
        invoicePanel1.add(jLabel9);
        jLabel9.setBounds(20, 220, 100, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel12.setText("Select Products");
        invoicePanel1.add(jLabel12);
        jLabel12.setBounds(20, 290, 140, 30);

        txtDiscount.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel1.add(txtDiscount);
        txtDiscount.setBounds(230, 470, 240, 30);

        chbExciseDuty.setFont(new java.awt.Font("Tahoma", 1, 14));
        chbExciseDuty.setText("Excise Duty");
        chbExciseDuty.setOpaque(false);
        invoicePanel1.add(chbExciseDuty);
        chbExciseDuty.setBounds(500, 70, 130, 30);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel48.setText("Invoice Date");
        invoicePanel1.add(jLabel48);
        jLabel48.setBounds(300, 110, 150, 30);

        add(invoicePanel1);
        invoicePanel1.setBounds(0, 0, 790, 590);

        invoicePanel2.setOpaque(false);
        invoicePanel2.setLayout(null);

        endPanel.setOpaque(false);
        endPanel.setLayout(null);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel36.setText("P.O. No.");
        endPanel.add(jLabel36);
        jLabel36.setBounds(20, 30, 90, 25);

        txtPONumber.setFont(new java.awt.Font("Tahoma", 1, 14));
        endPanel.add(txtPONumber);
        txtPONumber.setBounds(100, 30, 170, 25);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel37.setText("Beck Invoice NO");
        endPanel.add(jLabel37);
        jLabel37.setBounds(20, 70, 120, 25);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel38.setText("Inv Amt");
        endPanel.add(jLabel38);
        jLabel38.setBounds(530, 70, 70, 25);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel39.setText("P.O Date");
        endPanel.add(jLabel39);
        jLabel39.setBounds(290, 30, 80, 25);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel40.setText("Inv Date ");
        endPanel.add(jLabel40);
        jLabel40.setBounds(290, 70, 80, 25);

        txtBeckInvoiceNo.setFont(new java.awt.Font("Tahoma", 1, 14));
        endPanel.add(txtBeckInvoiceNo);
        txtBeckInvoiceNo.setBounds(140, 70, 130, 25);

        txtInvoiceAmount.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtInvoiceAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        endPanel.add(txtInvoiceAmount);
        txtInvoiceAmount.setBounds(610, 70, 130, 25);

        jLabel35.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Endorsement Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N
        endPanel.add(jLabel35);
        jLabel35.setBounds(0, 0, 760, 110);

        invoicePanel2.add(endPanel);
        endPanel.setBounds(10, 10, 770, 110);

        cvPanel.setOpaque(false);
        cvPanel.setLayout(null);

        txtOrderNumber.setFont(new java.awt.Font("Tahoma", 1, 14));
        cvPanel.add(txtOrderNumber);
        txtOrderNumber.setBounds(300, 30, 150, 25);

        rdCstOrVat.add(rdVAT);
        rdVAT.setFont(new java.awt.Font("Tahoma", 1, 14));
        rdVAT.setText("VAT");
        rdVAT.setOpaque(false);
        cvPanel.add(rdVAT);
        rdVAT.setBounds(140, 30, 70, 25);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel21.setText("D.C. Date :");
        cvPanel.add(jLabel21);
        jLabel21.setBounds(390, 70, 90, 25);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel14.setText("Our D.C. No");
        cvPanel.add(jLabel14);
        jLabel14.setBounds(30, 70, 90, 25);

        rdCstOrVat.add(rdCST);
        rdCST.setFont(new java.awt.Font("Tahoma", 1, 14));
        rdCST.setText("CST");
        rdCST.setOpaque(false);
        cvPanel.add(rdCST);
        rdCST.setBounds(80, 30, 53, 25);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel15.setText("Type    ");
        cvPanel.add(jLabel15);
        jLabel15.setBounds(30, 30, 100, 25);

        txtOurDCNumber.setFont(new java.awt.Font("Tahoma", 1, 14));
        cvPanel.add(txtOurDCNumber);
        txtOurDCNumber.setBounds(130, 70, 230, 25);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel17.setText("Order No :");
        cvPanel.add(jLabel17);
        jLabel17.setBounds(220, 30, 90, 25);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel18.setText("Order Date :");
        cvPanel.add(jLabel18);
        jLabel18.setBounds(470, 30, 90, 25);

        jLabel26.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "CST / VAT Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N
        cvPanel.add(jLabel26);
        jLabel26.setBounds(0, 0, 760, 110);

        invoicePanel2.add(cvPanel);
        cvPanel.setBounds(10, 10, 770, 110);

        btnPage2Back.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnPage2Back.setForeground(new java.awt.Color(0, 0, 204));
        btnPage2Back.setText("<<BACK");
        btnPage2Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPage2BackActionPerformed(evt);
            }
        });
        invoicePanel2.add(btnPage2Back);
        btnPage2Back.setBounds(20, 550, 100, 30);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel16.setText("TO");
        invoicePanel2.add(jLabel16);
        jLabel16.setBounds(300, 160, 30, 25);

        txtDispachedTo.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel2.add(txtDispachedTo);
        txtDispachedTo.setBounds(330, 160, 160, 25);

        txtDispachedFrom.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel2.add(txtDispachedFrom);
        txtDispachedFrom.setBounds(150, 160, 140, 25);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel22.setText("Freight To Pay Rs");
        invoicePanel2.add(jLabel22);
        jLabel22.setBounds(510, 160, 130, 25);

        txtPayrs.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtPayrs.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        invoicePanel2.add(txtPayrs);
        txtPayrs.setBounds(640, 160, 120, 25);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel23.setText("RR/LR No.");
        invoicePanel2.add(jLabel23);
        jLabel23.setBounds(30, 200, 74, 25);

        txtRRorLRNumber.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel2.add(txtRRorLRNumber);
        txtRRorLRNumber.setBounds(110, 200, 160, 25);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel24.setText("Date");
        invoicePanel2.add(jLabel24);
        jLabel24.setBounds(290, 200, 40, 25);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel25.setText(" By M/S");
        invoicePanel2.add(jLabel25);
        jLabel25.setBounds(510, 200, 70, 25);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel27.setText("Document Through");
        invoicePanel2.add(jLabel27);
        jLabel27.setBounds(30, 240, 140, 25);

        txtDocumentThrough.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel2.add(txtDocumentThrough);
        txtDocumentThrough.setBounds(190, 240, 190, 25);

        txtPaidBy.setFont(new java.awt.Font("Tahoma", 1, 14));
        invoicePanel2.add(txtPaidBy);
        txtPaidBy.setBounds(570, 200, 190, 25);

        btnPage2Next.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnPage2Next.setForeground(new java.awt.Color(0, 0, 204));
        btnPage2Next.setText("NEXT>>");
        btnPage2Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPage2NextActionPerformed(evt);
            }
        });
        invoicePanel2.add(btnPage2Next);
        btnPage2Next.setBounds(660, 550, 100, 30);

        productPanel.setOpaque(false);
        productPanel.setLayout(null);
        invoicePanel2.add(productPanel);
        productPanel.setBounds(20, 370, 740, 160);

        discountType.add(rdOnAmount);
        rdOnAmount.setFont(new java.awt.Font("Tahoma", 1, 14));
        rdOnAmount.setText("On Amount");
        rdOnAmount.setOpaque(false);
        rdOnAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdOnAmountActionPerformed(evt);
            }
        });
        invoicePanel2.add(rdOnAmount);
        rdOnAmount.setBounds(250, 310, 110, 30);

        discountType.add(rdPerKg);
        rdPerKg.setFont(new java.awt.Font("Tahoma", 1, 14));
        rdPerKg.setText("Per Kg");
        rdPerKg.setOpaque(false);
        rdPerKg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPerKgActionPerformed(evt);
            }
        });
        invoicePanel2.add(rdPerKg);
        rdPerKg.setBounds(360, 310, 71, 30);

        discountType.add(rdNon);
        rdNon.setFont(new java.awt.Font("Tahoma", 1, 14));
        rdNon.setText("Non");
        rdNon.setOpaque(false);
        rdNon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNonActionPerformed(evt);
            }
        });
        invoicePanel2.add(rdNon);
        rdNon.setBounds(440, 310, 55, 30);

        discountType.add(rdpecentageOnKg);
        rdpecentageOnKg.setFont(new java.awt.Font("Tahoma", 1, 14));
        rdpecentageOnKg.setText(" % on KG");
        rdpecentageOnKg.setOpaque(false);
        rdpecentageOnKg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdpecentageOnKgActionPerformed(evt);
            }
        });
        invoicePanel2.add(rdpecentageOnKg);
        rdpecentageOnKg.setBounds(140, 310, 100, 30);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel29.setText("Dispached From");
        invoicePanel2.add(jLabel29);
        jLabel29.setBounds(30, 160, 110, 25);

        jLabel28.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Transport Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N
        invoicePanel2.add(jLabel28);
        jLabel28.setBounds(10, 130, 760, 150);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel30.setForeground(new java.awt.Color(255, 0, 0));
        jLabel30.setText("Product Name");
        invoicePanel2.add(jLabel30);
        jLabel30.setBounds(20, 340, 110, 25);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel34.setForeground(new java.awt.Color(255, 0, 0));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Price For Kg");
        invoicePanel2.add(jLabel34);
        jLabel34.setBounds(330, 340, 90, 25);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Package Size");
        invoicePanel2.add(jLabel19);
        jLabel19.setBounds(440, 340, 80, 25);

        otherType.add(rdOtherChr);
        rdOtherChr.setFont(new java.awt.Font("Tahoma", 1, 12));
        rdOtherChr.setText("Oth Chr");
        rdOtherChr.setOpaque(false);
        rdOtherChr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdOtherChrActionPerformed(evt);
            }
        });
        invoicePanel2.add(rdOtherChr);
        rdOtherChr.setBounds(530, 310, 73, 30);

        otherType.add(rdPFChr);
        rdPFChr.setFont(new java.awt.Font("Tahoma", 1, 12));
        rdPFChr.setText("P&F Chr");
        rdPFChr.setOpaque(false);
        rdPFChr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPFChrActionPerformed(evt);
            }
        });
        invoicePanel2.add(rdPFChr);
        rdPFChr.setBounds(610, 310, 80, 30);

        otherType.add(rdNoneOc);
        rdNoneOc.setFont(new java.awt.Font("Tahoma", 1, 12));
        rdNoneOc.setText("None");
        rdNoneOc.setOpaque(false);
        rdNoneOc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoneOcActionPerformed(evt);
            }
        });
        invoicePanel2.add(rdNoneOc);
        rdNoneOc.setBounds(700, 310, 60, 30);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel32.setForeground(new java.awt.Color(255, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("No. Of Packs");
        invoicePanel2.add(jLabel32);
        jLabel32.setBounds(530, 340, 80, 25);

        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        invoicePanel2.add(jLabel2);
        jLabel2.setBounds(530, 310, 230, 30);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel33.setForeground(new java.awt.Color(255, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Discount");
        invoicePanel2.add(jLabel33);
        jLabel33.setBounds(620, 340, 60, 25);

        lbDisc.setFont(new java.awt.Font("Tahoma", 1, 14));
        lbDisc.setText(" DIscount Type");
        lbDisc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        invoicePanel2.add(lbDisc);
        lbDisc.setBounds(20, 310, 500, 30);

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel53.setForeground(new java.awt.Color(255, 0, 0));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel53.setText("pro.type");
        invoicePanel2.add(jLabel53);
        jLabel53.setBounds(220, 340, 90, 25);

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel54.setForeground(new java.awt.Color(255, 0, 0));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel54.setText("Cut.pro.id");
        invoicePanel2.add(jLabel54);
        jLabel54.setBounds(140, 340, 80, 25);
        invoicePanel2.add(jSeparator3);
        jSeparator3.setBounds(20, 365, 740, 10);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel31.setForeground(new java.awt.Color(255, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("Other P&F");
        invoicePanel2.add(jLabel31);
        jLabel31.setBounds(680, 340, 80, 25);

        jLabel20.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Product Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N
        invoicePanel2.add(jLabel20);
        jLabel20.setBounds(10, 290, 760, 250);

        add(invoicePanel2);
        invoicePanel2.setBounds(0, 0, 790, 590);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPage1NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPage1NextActionPerformed
        if (page1Validate()) {
            closeAllPanels();
            System.out.println("cstorvat.isSelected() " + cstorvat.isSelected() + "\tchbExciseDuty.isSelected()  : " + chbExciseDuty.isSelected());
            invoicePanel2.setVisible(true);
            if (cstorvat.isSelected()) {
                billType = 1;
                cvPanel.setVisible(true);
            } else {
                billType = 2;
                endPanel.setVisible(true);
            }
             generateBillDto.setInvoiceType(billType);

        }


    }//GEN-LAST:event_btnPage1NextActionPerformed

    private void btnPage5BillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPage5BillActionPerformed
        if (page5Validate()) {
            invoiceModel.generateInvoic(generateBillDto, status);
            System.out.println("Stats  : " + status);
            status = 1;
        }
    }//GEN-LAST:event_btnPage5BillActionPerformed

    private void btnPage5BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPage5BackActionPerformed
        closeAllPanels();
        setOtherInfo();
        invoicePanel4.setVisible(true);

    }//GEN-LAST:event_btnPage5BackActionPerformed

    private void cmbCustomerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCustomerItemStateChanged
        int customerIndex = cmbCustomer.getSelectedIndex();
        customersProducts.clear();
        selectedCustomersProducts.clear();
        txtDiscount.setText("0.0");
        if (customerIndex > 0) {
            selectedCustomer = customers.get(customerIndex - 1);
            customersProducts = invoiceModel.getCustomerProducts(selectedCustomer.getName());
            txtDiscount.setText(selectedCustomer.getDiscount() + "");
        }
        addProductList();
    }//GEN-LAST:event_cmbCustomerItemStateChanged

    private void btnAddItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemsActionPerformed
        int k = 0;
        for (int i = 0; i < allProductsList.getSelectedIndices().length; i++) {
            int index = (allProductsList.getSelectedIndices()[i] - k);
            selectedCustomersProducts.add(customersProducts.get(index));
            customersProducts.remove(index);
            k++;
        }
        addProductList();
}//GEN-LAST:event_btnAddItemsActionPerformed

    private void btnRemoveItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveItemsActionPerformed
        int k = 0;
        for (int i = 0; i < selectedProducts.getSelectedIndices().length; i++) {
            int index = (selectedProducts.getSelectedIndices()[i] - k);
            customersProducts.add(selectedCustomersProducts.get(index));
            selectedCustomersProducts.remove(index);
            k++;

        }
        addProductList();
}//GEN-LAST:event_btnRemoveItemsActionPerformed

    private void btnPage4BillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPage4BillActionPerformed
        if (page4Validate()) {

            invoiceModel.generateInvoic(generateBillDto, status);
            System.out.println("Stats  : " + status);
            status = 1;
        }
    }//GEN-LAST:event_btnPage4BillActionPerformed

    private void btnPage2BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPage2BackActionPerformed
        closeAllPanels();
        invoicePanel1.setVisible(true);
    }//GEN-LAST:event_btnPage2BackActionPerformed

    private void btnPage2NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPage2NextActionPerformed
        if (page2Validate()) {
            closeAllPanels();
            invoicePanel3.setVisible(true);
            confirmation();
        }
    }//GEN-LAST:event_btnPage2NextActionPerformed

    private void rdOnAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdOnAmountActionPerformed
        if (rdOnAmount.isSelected()) {
            productDiscount = 3;
            int count = selectedCustomersProducts.size();
            for (int i = 0; i < count; i++) {
                proDiscount[i].setEnabled(true);
            }
        }

    }//GEN-LAST:event_rdOnAmountActionPerformed

    private void rdPerKgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPerKgActionPerformed

        if (rdPerKg.isSelected()) {
            productDiscount = 2;
            int count = selectedCustomersProducts.size();
            for (int i = 0; i < count; i++) {
                proDiscount[i].setEnabled(true);
            }
        }
    }//GEN-LAST:event_rdPerKgActionPerformed

    private void rdNonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNonActionPerformed

        if (rdNon.isSelected()) {
            productDiscount = 0;
            int count = selectedCustomersProducts.size();
            for (int i = 0; i < count; i++) {
                proDiscount[i].setEnabled(false);
            }
        }

    }//GEN-LAST:event_rdNonActionPerformed

    private void rdpecentageOnKgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdpecentageOnKgActionPerformed
        if (rdpecentageOnKg.isSelected()) {
            productDiscount = 1;
            int count = selectedCustomersProducts.size();
            for (int i = 0; i < count; i++) {
                proDiscount[i].setEnabled(true);
            }
        }
    }//GEN-LAST:event_rdpecentageOnKgActionPerformed

    private void txtSplrInvoiceTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSplrInvoiceTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSplrInvoiceTimeActionPerformed

    private void cmbSplrAddreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSplrAddreeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSplrAddreeActionPerformed

    private void btnPage4BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPage4BackActionPerformed
        closeAllPanels();
        invoicePanel3.setVisible(true);
        confirmation();

    }//GEN-LAST:event_btnPage4BackActionPerformed

    private void btnPage4NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPage4NextActionPerformed
        closeAllPanels();
        if (page4Validate()) {
             list=invoiceModel.getSupplierList("");
        String[] sList=new String[list.size()+1];
        sList[0]="----Select----";
        for(int i=0;i<list.size();i++)
        {
           Supplier sp=list.get(i);
           sList[i+1]=sp.getName();
        }
             cmbSplrAddree.setModel(new javax.swing.DefaultComboBoxModel(sList));
            setExiceData();
            invoicePanel5.setVisible(true);
        }
    }//GEN-LAST:event_btnPage4NextActionPerformed

    private void rdPFChrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPFChrActionPerformed
        if (rdPFChr.isSelected()) {
            int count = selectedCustomersProducts.size();
            proOtherCharge = 2;
            for (int i = 0; i < count; i++) {
                proOtherChar[i].setEnabled(true);
            }
        }
    }//GEN-LAST:event_rdPFChrActionPerformed

    private void rdOtherChrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdOtherChrActionPerformed
        if (rdOtherChr.isSelected()) {
            int count = selectedCustomersProducts.size();
            proOtherCharge = 1;
            for (int i = 0; i < count; i++) {
                proOtherChar[i].setEnabled(true);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_rdOtherChrActionPerformed

    private void rdNoneOcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoneOcActionPerformed
        if (rdNoneOc.isSelected()) {
            int count = selectedCustomersProducts.size();
            proOtherCharge = 0;
            for (int i = 0; i < count; i++) {
                proOtherChar[i].setEnabled(false);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoneOcActionPerformed

    private void btnPage3BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPage3BackActionPerformed
        closeAllPanels();
        invoicePanel2.setVisible(true);
        if (cstorvat.isSelected()) {
            billType = 1;
            cvPanel.setVisible(true);
        } else {
            billType = 2;
            endPanel.setVisible(true);
        }
        setProductTableDate();
    }//GEN-LAST:event_btnPage3BackActionPerformed

    private void btnPage3NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPage3NextActionPerformed
        if (page3Validate()) {
            closeAllPanels();
            invoicePanel4.setVisible(true);
            if (cstorvat.isSelected()) {
                billType = 1;
                if (chbExciseDuty.isSelected()) {
                    btnPage4Next.setVisible(true);
                    btnPage4Bill.setVisible(false);
                } else {
                    btnPage4Next.setVisible(false);
                    btnPage4Bill.setVisible(true);

                }

            } else {
                btnPage4Next.setVisible(false);
                btnPage4Bill.setVisible(true);
                billType = 2;
            }
        }
    }//GEN-LAST:event_btnPage3NextActionPerformed

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        if (page3Validate()) {
            confirmation();
        }
    }//GEN-LAST:event_btnCheckActionPerformed

    private void txtTotalAMtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalAMtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalAMtActionPerformed

    public void cleanOIFields() {
        cmbOrderBy.setSelectedIndex(0);
        cmbPlusOrMinus.setSelectedIndex(0);
        txtHeader.setText("");
        txtLine1.setText("");
        txtLine2.setText("");
        txtLine3.setText("");
        txtAmt1.setText("");
        txtAmt2.setText("");
        txtAmt3.setText("");
        txtTotalAMt.setText("");
    }

    private void btnOIAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOIAddActionPerformed
        int ordby = cmbOrderBy.getSelectedIndex();
        int pom = cmbPlusOrMinus.getSelectedIndex();
        String header = txtHeader.getText();
        String line1 = txtLine1.getText();
        String line2 = txtLine2.getText();
        String line3 = txtLine3.getText();
        String am1 = txtAmt1.getText();
        String am2 = txtAmt2.getText();
        String am3 = txtAmt3.getText();
        double tami = numberCheck(txtTotalAMt);
        if (line1.trim().length() > 0 || line1.trim().length() > 0 || line1.trim().length() > 0) {
            OtherInvoiceData od = new OtherInvoiceData();
            od.setAmount(tami);
            od.setAmtType(pom);
            od.setHeadingLine(header);
            od.setOrderBy(ordby);
            String des = "";
            if (line1.trim().length() > 0) {
                des += line1 + "@@" + am1 + "##";
            }
            if (line2.trim().length() > 0) {
                des += line2 + "@@" + am2 + "##";
            }
            if (line3.trim().length() > 0) {
                des += line3 + "@@" + am3 + "##";
            }
            des = des.substring(0, des.length() - 2);
            od.setDiscription(des);
            od.setOtherInfo("");
            otherInvoiceData.add(od);
            setOtherInfo();
            cleanOIFields();
        }
    }//GEN-LAST:event_btnOIAddActionPerformed

    private void btnOICeanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOICeanActionPerformed
        otherInvoiceData =new ArrayList<OtherInvoiceData>();
        setOtherInfo();
    }//GEN-LAST:event_btnOICeanActionPerformed

    private void txtSpInvAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSpInvAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSpInvAmountActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList allProductsList;
    private javax.swing.ButtonGroup billtype;
    private javax.swing.JButton btnAddItems;
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnOIAdd;
    private javax.swing.JButton btnOICean;
    private javax.swing.JButton btnPage1Next;
    private javax.swing.JButton btnPage2Back;
    private javax.swing.JButton btnPage2Next;
    private javax.swing.JButton btnPage3Back;
    private javax.swing.JButton btnPage3Next;
    private javax.swing.JButton btnPage4Back;
    private javax.swing.JButton btnPage4Bill;
    private javax.swing.JButton btnPage4Next;
    private javax.swing.JButton btnPage5Back;
    private javax.swing.JButton btnPage5Bill;
    private javax.swing.JButton btnRemoveItems;
    private javax.swing.JCheckBox chbExciseDuty;
    private javax.swing.JComboBox cmbCustomer;
    private javax.swing.JComboBox cmbOrderBy;
    private javax.swing.JComboBox cmbPlusOrMinus;
    private javax.swing.JComboBox cmbSplrAddree;
    private javax.swing.JPanel confirmPanel;
    private javax.swing.JRadioButton cstorvat;
    private javax.swing.JPanel cvPanel;
    private javax.swing.ButtonGroup discountType;
    private javax.swing.JPanel endPanel;
    private javax.swing.JRadioButton endorsement;
    private javax.swing.JPanel invoicePanel1;
    private javax.swing.JPanel invoicePanel2;
    private javax.swing.JPanel invoicePanel3;
    private javax.swing.JPanel invoicePanel4;
    private javax.swing.JPanel invoicePanel5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lbDisc;
    private javax.swing.ButtonGroup otherType;
    private javax.swing.JPanel pnlExiceDuty;
    private javax.swing.JPanel pnlOI;
    private javax.swing.JPanel productPanel;
    private javax.swing.JRadioButton rdCST;
    private javax.swing.ButtonGroup rdCstOrVat;
    private javax.swing.JRadioButton rdNon;
    private javax.swing.JRadioButton rdNoneOc;
    private javax.swing.JRadioButton rdOnAmount;
    private javax.swing.JRadioButton rdOtherChr;
    private javax.swing.JRadioButton rdPFChr;
    private javax.swing.JRadioButton rdPerKg;
    private javax.swing.JRadioButton rdVAT;
    private javax.swing.JRadioButton rdpecentageOnKg;
    private javax.swing.JList selectedProducts;
    private javax.swing.JTextField txtAmt1;
    private javax.swing.JTextField txtAmt2;
    private javax.swing.JTextField txtAmt3;
    private javax.swing.JTextField txtBeckInvoiceNo;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtDispachedFrom;
    private javax.swing.JTextField txtDispachedTo;
    private javax.swing.JTextField txtDocumentThrough;
    private javax.swing.JTextField txtEntryPageNo;
    private javax.swing.JTextField txtExciseInvNo;
    private javax.swing.JTextField txtHeader;
    private javax.swing.JTextField txtInvoiceAmount;
    private javax.swing.JTextField txtInvoiceNo;
    private javax.swing.JTextField txtInvoiceTime;
    private javax.swing.JTextField txtLine1;
    private javax.swing.JTextField txtLine2;
    private javax.swing.JTextField txtLine3;
    private javax.swing.JTextField txtModeOfTrn;
    private javax.swing.JTextField txtOrderNumber;
    private javax.swing.JTextField txtOurDCNumber;
    private javax.swing.JTextField txtPONumber;
    private javax.swing.JTextField txtPaidBy;
    private javax.swing.JTextField txtPayrs;
    private javax.swing.JTextField txtRRorLRNumber;
    private javax.swing.JTextField txtRemovalOfGoodsTime;
    private javax.swing.JTextField txtSpInvAmount;
    private javax.swing.JTextField txtSpInvNo;
    private javax.swing.JTextField txtSplrInvoiceTime;
    private javax.swing.JTextField txtTotalAMt;
    private javax.swing.JTextField txtVehicleRegNo;
    // End of variables declaration//GEN-END:variables
}
