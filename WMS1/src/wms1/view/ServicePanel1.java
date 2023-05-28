/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * ProductPanel.java
 *
 * Created on Jul 27, 2014, 11:22:02 AM
 */
package wms1.view;

import java.awt.print.PrinterException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import wms1.dao.BrandsDao;
import wms1.dao.ItemsDao;
import wms1.dao.LoginDao;
import wms1.pojos.Brands;
import wms1.pojos.Items;
import wms1.pojos.Purchases;
import wms1.pojos.PurchasesItems;
import wms1.pojos.Sales;
import wms1.pojos.SalesItems;
import wms1.pojos.ServiceItems;
import wms1.pojos.ServiceTypes;
import wms1.pojos.Services;
import wms1.pojos.Users;

import wms1.util.GeneralUtil;
import wms1.util.OrderPDF;
import wms1.util.ReadCSV;

/**
 *
 * @author SATISH SYAMALA
 */
public class ServicePanel1 extends javax.swing.JPanel {

    /**
     * Creates new form ProductPanel
     */
    private HomePanel homePanel;
    private String menuName;
    private Map<String, Object> fieldsMap = new HashMap<String, Object>();
    private List<ServiceItems> serviceItemses;
    private int srNo;
    private Services services;

    public ServicePanel1() {
        initComponents();
    }

    public void init(HomePanel homePanel, String menuName, Services services) {
        pnlConfirmation.setVisible(false);
        pnlInvoice.setVisible(false);
        this.homePanel = homePanel;
        this.menuName = menuName;
        this.services = services;
         GeneralUtil.setDailBound(pnlInvoice, homePanel, 680, 490);
        GeneralUtil.setDailBound(pnlConfirmation, homePanel, 510, 380);
        ddServiceTypes.setModel(new javax.swing.DefaultComboBoxModel(GeneralUtil.getServiesTypesArray("Select")));
        ddDiscounts.setModel(new javax.swing.DefaultComboBoxModel(ReadCSV.getSettings().getDiscountArray()));
        serviceItemses = new ArrayList<>();
        srNo = 1;
        txtDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        if (services != null) {
            serviceItemses = new ArrayList<>();
            txtDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(services.getCreatedOn()));
            txtCustName.setText(services.getCustName());
            txtMobileNo.setText(services.getCustMobileNo());
            txtAddess.setText(services.getCustAddress());
            txtRemarks.setText(services.getProductName());
            for (ServiceItems s : services.getServiceItemses()) {
                s.setSrNo(srNo);
                serviceItemses.add(s);
                srNo++;
            }
        } else {
            clearHeaderFields();
        }
        clearAddFields();
        showTableData();
    }

    public void showTableData() {
        Object[][] data = null;
        double tm = 0;
        double td = 0;
        double tfa = 0;
        if (serviceItemses != null && serviceItemses.size() > 0) {
            data = new Object[serviceItemses.size()][5];
            int i = 0;
            for (ServiceItems p : serviceItemses) {
                data[i][0] = p.getSrNo();
                data[i][1] = p.getRemarks();
                data[i][2] = p.getAmount();
                data[i][3] = p.getDiscount();
                data[i][4] = p.getTotalAmount();
                tm += p.getAmount();
                td += p.getDiscount();
                tfa += p.getTotalAmount();
                i++;
            }
        } else {
            data = new Object[0][0];
        }
        txtTotalAmt.setText(tm + "");
        txtTotalDisc.setText(td + "");
        txttotalFinalAmount.setText(tfa + "");
        tbPurItems.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{
                    "Sr,No", "Service type", "Amount", "Discount", "Final Amt"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbPurItems.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbPurItems.getColumnModel().getColumn(1).setPreferredWidth(250);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInvoice = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        htmlContent = new javax.swing.JTextPane();
        btnPrint = new javax.swing.JButton();
        btnCSV4 = new javax.swing.JButton();
        pnlConfirmation = new javax.swing.JDialog();
        btnConfirm = new javax.swing.JButton();
        btnConfirmClose = new javax.swing.JButton();
        lbFnTot = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbItemCount = new javax.swing.JLabel();
        lbAmount = new javax.swing.JLabel();
        lbDisc = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabConfirmation = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        lbDropDownVal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ddServiceTypes = new javax.swing.JComboBox();
        btnImport = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPurItems = new javax.swing.JTable();
        ddDiscounts = new javax.swing.JComboBox();
        txtMobileNo = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        labdate = new javax.swing.JLabel();
        txtAddess = new javax.swing.JTextField();
        btnSearch2 = new javax.swing.JButton();
        btnImport3 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        btnSearch3 = new javax.swing.JButton();
        lbDropDownVal7 = new javax.swing.JLabel();
        txtTotalAmt = new javax.swing.JTextField();
        lbDropDownVal8 = new javax.swing.JLabel();
        txtTotalDisc = new javax.swing.JTextField();
        lbDropDownVal9 = new javax.swing.JLabel();
        txttotalFinalAmount = new javax.swing.JTextField();
        txtCustName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        labdate1 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        lbDropDownVal14 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        labdate2 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        lbDropDownVal1 = new javax.swing.JLabel();
        txtOthers = new javax.swing.JTextField();
        lbDropDownVal2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        labdate3 = new javax.swing.JLabel();
        txtRemarks = new javax.swing.JTextField();

        pnlInvoice.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                pnlInvoiceWindowClosed(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                pnlInvoiceWindowDeactivated(evt);
            }
        });
        pnlInvoice.getContentPane().setLayout(null);

        htmlContent.setContentType("text/html"); // NOI18N
        jScrollPane3.setViewportView(htmlContent);

        pnlInvoice.getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(10, 10, 660, 350);

        btnPrint.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(0, 153, 102));
        btnPrint.setText("Prnit");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        pnlInvoice.getContentPane().add(btnPrint);
        btnPrint.setBounds(350, 370, 190, 30);

        btnCSV4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCSV4.setForeground(new java.awt.Color(204, 0, 0));
        btnCSV4.setText("Close");
        btnCSV4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCSV4ActionPerformed(evt);
            }
        });
        pnlInvoice.getContentPane().add(btnCSV4);
        btnCSV4.setBounds(140, 370, 190, 30);

        pnlConfirmation.setTitle("Service Confirmation");
        pnlConfirmation.getContentPane().setLayout(null);

        btnConfirm.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnConfirm.setForeground(new java.awt.Color(0, 153, 0));
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });
        pnlConfirmation.getContentPane().add(btnConfirm);
        btnConfirm.setBounds(260, 280, 130, 40);

        btnConfirmClose.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnConfirmClose.setForeground(new java.awt.Color(255, 0, 0));
        btnConfirmClose.setText("Close");
        btnConfirmClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmCloseActionPerformed(evt);
            }
        });
        pnlConfirmation.getContentPane().add(btnConfirmClose);
        btnConfirmClose.setBounds(110, 280, 130, 40);

        lbFnTot.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lbFnTot.setForeground(new java.awt.Color(0, 153, 0));
        lbFnTot.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbFnTot.setText("0");
        pnlConfirmation.getContentPane().add(lbFnTot);
        lbFnTot.setBounds(260, 230, 110, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Discount        :");
        pnlConfirmation.getContentPane().add(jLabel11);
        jLabel11.setBounds(130, 200, 130, 30);

        lbItemCount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbItemCount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbItemCount.setText("2");
        pnlConfirmation.getContentPane().add(lbItemCount);
        lbItemCount.setBounds(90, 10, 40, 30);

        lbAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbAmount.setText("0");
        pnlConfirmation.getContentPane().add(lbAmount);
        lbAmount.setBounds(260, 170, 110, 30);

        lbDisc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDisc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDisc.setText("0");
        pnlConfirmation.getContentPane().add(lbDisc);
        lbDisc.setBounds(260, 200, 110, 30);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Invoice Amt  :");
        pnlConfirmation.getContentPane().add(jLabel15);
        jLabel15.setBounds(130, 230, 130, 30);

        tabConfirmation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Service Type", "Price", "Discount", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabConfirmation);

        pnlConfirmation.getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 40, 470, 120);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Amount         :");
        pnlConfirmation.getContentPane().add(jLabel16);
        jLabel16.setBounds(130, 170, 130, 30);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Items  :");
        pnlConfirmation.getContentPane().add(jLabel17);
        jLabel17.setBounds(20, 10, 60, 30);

        setOpaque(false);
        setLayout(null);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(0, 204, 204));
        btnSearch.setText("Add");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch);
        btnSearch.setBounds(680, 400, 100, 70);

        lbDropDownVal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal.setText("Price");
        jPanel2.add(lbDropDownVal);
        lbDropDownVal.setBounds(30, 460, 150, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Product Details");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(460, 0, 150, 30);

        ddServiceTypes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ddServiceTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        ddServiceTypes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ddServiceTypesItemStateChanged(evt);
            }
        });
        ddServiceTypes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddServiceTypesActionPerformed(evt);
            }
        });
        jPanel2.add(ddServiceTypes);
        ddServiceTypes.setBounds(30, 420, 220, 30);

        btnImport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnImport.setForeground(new java.awt.Color(204, 0, 0));
        btnImport.setText("Clear All");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });
        jPanel2.add(btnImport);
        btnImport.setBounds(680, 550, 100, 40);

        tbPurItems.setAutoCreateRowSorter(true);
        tbPurItems.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbPurItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Sr,No", "Service Type", "Price", "Discount.", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPurItems.setFocusCycleRoot(true);
        tbPurItems.setOpaque(false);
        tbPurItems.setRowHeight(25);
        tbPurItems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbPurItems.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbPurItems);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 150, 770, 180);

        ddDiscounts.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ddDiscounts.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select" }));
        ddDiscounts.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ddDiscountsItemStateChanged(evt);
            }
        });
        ddDiscounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddDiscountsActionPerformed(evt);
            }
        });
        jPanel2.add(ddDiscounts);
        ddDiscounts.setBounds(220, 490, 160, 30);

        txtMobileNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txtMobileNo);
        txtMobileNo.setBounds(250, 30, 190, 30);

        btnSearch1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch1.setForeground(new java.awt.Color(255, 0, 0));
        btnSearch1.setText("Clear");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch1);
        btnSearch1.setBounds(680, 480, 100, 30);
        jPanel2.add(jSeparator3);
        jSeparator3.setBounds(10, 130, 770, 10);

        labdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labdate.setText("Address");
        jPanel2.add(labdate);
        labdate.setBounds(250, 60, 80, 30);

        txtAddess.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txtAddess);
        txtAddess.setBounds(250, 90, 190, 30);

        btnSearch2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch2.setForeground(new java.awt.Color(0, 102, 51));
        btnSearch2.setText("Service List");
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch2);
        btnSearch2.setBounds(30, 540, 180, 40);

        btnImport3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnImport3.setForeground(new java.awt.Color(0, 153, 51));
        btnImport3.setText("Create");
        btnImport3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImport3ActionPerformed(evt);
            }
        });
        jPanel2.add(btnImport3);
        btnImport3.setBounds(660, 20, 120, 90);
        jPanel2.add(jSeparator4);
        jSeparator4.setBounds(10, 530, 770, 10);

        btnSearch3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch3.setForeground(new java.awt.Color(255, 0, 0));
        btnSearch3.setText("X Row");
        btnSearch3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch3ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch3);
        btnSearch3.setBounds(10, 330, 80, 30);

        lbDropDownVal7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal7.setText("Toatl Amount :");
        jPanel2.add(lbDropDownVal7);
        lbDropDownVal7.setBounds(90, 340, 110, 30);

        txtTotalAmt.setEditable(false);
        txtTotalAmt.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalAmt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalAmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(txtTotalAmt);
        txtTotalAmt.setBounds(200, 340, 100, 30);

        lbDropDownVal8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal8.setText("Total Discount :");
        jPanel2.add(lbDropDownVal8);
        lbDropDownVal8.setBounds(310, 340, 120, 30);

        txtTotalDisc.setEditable(false);
        txtTotalDisc.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalDisc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalDisc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(txtTotalDisc);
        txtTotalDisc.setBounds(430, 340, 100, 30);

        lbDropDownVal9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal9.setText("Inv. Amt :");
        jPanel2.add(lbDropDownVal9);
        lbDropDownVal9.setBounds(540, 340, 80, 30);

        txttotalFinalAmount.setEditable(false);
        txttotalFinalAmount.setBackground(new java.awt.Color(255, 255, 255));
        txttotalFinalAmount.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        txttotalFinalAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(txttotalFinalAmount);
        txttotalFinalAmount.setBounds(620, 330, 160, 50);

        txtCustName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txtCustName);
        txtCustName.setBounds(30, 90, 190, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Name");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(30, 60, 130, 30);

        labdate1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labdate1.setText("Date");
        jPanel2.add(labdate1);
        labdate1.setBounds(30, 0, 80, 30);

        txtDate.setEditable(false);
        txtDate.setBackground(new java.awt.Color(255, 255, 255));
        txtDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txtDate);
        txtDate.setBounds(30, 30, 190, 30);
        jPanel2.add(jSeparator6);
        jSeparator6.setBounds(30, 390, 770, 10);

        lbDropDownVal14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal14.setText("Discount");
        jPanel2.add(lbDropDownVal14);
        lbDropDownVal14.setBounds(220, 460, 150, 30);

        txtPrice.setEditable(false);
        txtPrice.setBackground(new java.awt.Color(255, 255, 255));
        txtPrice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPriceFocusLost(evt);
            }
        });
        jPanel2.add(txtPrice);
        txtPrice.setBounds(30, 490, 170, 30);

        labdate2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labdate2.setText("Discount Amt");
        jPanel2.add(labdate2);
        labdate2.setBounds(390, 460, 110, 30);

        txtAmount.setEditable(false);
        txtAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(txtAmount);
        txtAmount.setBounds(530, 490, 130, 30);

        lbDropDownVal1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal1.setText("Others");
        jPanel2.add(lbDropDownVal1);
        lbDropDownVal1.setBounds(270, 390, 110, 30);

        txtOthers.setEditable(false);
        txtOthers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txtOthers);
        txtOthers.setBounds(270, 420, 370, 30);

        lbDropDownVal2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal2.setText("Service Types");
        jPanel2.add(lbDropDownVal2);
        lbDropDownVal2.setBounds(30, 390, 110, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Mobile No. *");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(250, 0, 120, 30);

        txtDiscount.setEditable(false);
        txtDiscount.setBackground(new java.awt.Color(255, 255, 255));
        txtDiscount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDiscount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(txtDiscount);
        txtDiscount.setBounds(390, 490, 130, 30);

        labdate3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labdate3.setText("Total Amount");
        jPanel2.add(labdate3);
        labdate3.setBounds(530, 460, 110, 30);

        txtRemarks.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txtRemarks);
        txtRemarks.setBounds(460, 30, 190, 30);

        add(jPanel2);
        jPanel2.setBounds(0, 0, 790, 590);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        addToCart();
    }//GEN-LAST:event_btnSearchActionPerformed

    public void addToCart() {
        boolean check = true;
        double price = 0;
        if (ddServiceTypes.getSelectedItem() == null || ddServiceTypes.getSelectedItem().toString().toString().equalsIgnoreCase("select")) {
            check = false;
            JOptionPane.showMessageDialog(this, "Please Select Service Type");
        } else if (ddServiceTypes.getSelectedItem().toString().equalsIgnoreCase("Others")) {
            if (txtOthers.getText() == null || txtOthers.getText().trim().length() == 0) {
                check = false;
                JOptionPane.showMessageDialog(this, "Others is required");
            } else if (txtPrice.getText() == null || txtPrice.getText().trim().length() == 0) {
                check = false;
                JOptionPane.showMessageDialog(this, "Price is required");
            }
        }
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
        } catch (Exception e) {
            check = false;
            JOptionPane.showMessageDialog(this, "Invalid Price");
        }

        if (check) {
            ServiceItems p = new ServiceItems();
            p.setSrNo(srNo++);
            BrandsDao itmd = new BrandsDao();
            List<ServiceTypes> ils = itmd.getServiceTypes(0, ddServiceTypes.getSelectedItem().toString());
            p.setServiceType(ils.get(0));
            p.setQuantity(1);
            p.setPrice(price);
            p.setAmount(price);
            p.setDiscount(calDiscount(p.getAmount()));
            p.setTotalAmount(p.getAmount() - p.getDiscount());
            if (ddServiceTypes.getSelectedItem().toString().equalsIgnoreCase("Others")) {
                p.setRemarks(txtOthers.getText().trim());
            } else {
                p.setRemarks(ddServiceTypes.getSelectedItem().toString());
            }
            serviceItemses.add(p);
            clearAddFields();
            showTableData();
        }
    }

    public double calDiscount(double amount) {
        double dis = 0;
        if (ddDiscounts.getSelectedItem() != null && !ddDiscounts.getSelectedItem().toString().equalsIgnoreCase("Select")) {
            dis = (amount / 100) * (Double.parseDouble(ddDiscounts.getSelectedItem().toString()));
        }
        return dis;
    }

    private void ddServiceTypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddServiceTypesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ddServiceTypesActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        clearAll();
        clearAddFields();
        clearHeaderFields();
        services = new Services();
    }//GEN-LAST:event_btnImportActionPerformed

    public void clearHeaderFields() {
        txtMobileNo.setText("");
        txtCustName.setText("");
        txtAddess.setText("");
        txtRemarks.setText("");
    }

    public void clearAll() {
        serviceItemses = new ArrayList<>();
        srNo = 1;
        showTableData();
    }

    public void clearAddFields() {
        ddServiceTypes.setSelectedItem("Selec");
        ddDiscounts.setSelectedItem("Select");
        txtPrice.setText("0");
        txtOthers.setText("");
        txtAmount.setText("0");

    }

    private void ddDiscountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddDiscountsActionPerformed
        // searchItem();
    }//GEN-LAST:event_ddDiscountsActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        clearAddFields();
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        ItemsDao id = new ItemsDao();
        String msg = "";
        System.out.println("services.getProductCode() : " + services.getProductCode());
        if (services.getProductCode() == null) {
            services.setServiceNo(id.getServiceNo());
            msg = id.saveServices(services);
        } else {
            if (services.getProductCode().equalsIgnoreCase("closed")) {
                services.setStatus("Closed");
                services.setClosedBy(homePanel.getMainWindow().getUsers().getUserId());
                services.setCloseddOn(new Date());
            } else {
                services.setUpdatedBy(homePanel.getMainWindow().getUsers().getUserId());
                services.setUpdatedOn(new Date());
            }
            msg = id.updateServices(services);
        }
        if (msg.equalsIgnoreCase("success")) {
            if (true) {
                pnlInvoice.setTitle(" Service No : " + services.getServiceNo());
                String con = ReadCSV.generateServiceBill(services);
                System.err.println("Con " + con);
                htmlContent.setText(con);
                pnlInvoice.setVisible(true);
            } else {
                File f = OrderPDF.servicePdf(services);
                try {
                    if (f.exists()) {
                        Process p;
                        p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + f.getAbsolutePath());
                        p.waitFor();
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            pnlConfirmation.setVisible(false);
            clearAll();
        } else {
            JOptionPane.showMessageDialog(this, msg);
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void ddServiceTypesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ddServiceTypesItemStateChanged
        if (ddServiceTypes.getSelectedItem() != null && !ddServiceTypes.getSelectedItem().toString().equalsIgnoreCase("select")) {
            if (!ddServiceTypes.getSelectedItem().toString().equalsIgnoreCase("others")) {
                BrandsDao itmd = new BrandsDao();
                List<ServiceTypes> ils = itmd.getServiceTypes(0, ddServiceTypes.getSelectedItem().toString());
                txtPrice.setText(ils.get(0).getPrice() + "");
                txtPrice.setEditable(false);
                txtOthers.setEditable(false);
            } else {
                txtPrice.setText("0");
                txtPrice.setEditable(true);
                txtOthers.setEditable(true);
            }
            calculateAmount();

        } else {
            clearAddFields();
        }
    }//GEN-LAST:event_ddServiceTypesItemStateChanged

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed
        this.homePanel.openServiceList(menuName);
    }//GEN-LAST:event_btnSearch2ActionPerformed

    private void btnImport3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImport3ActionPerformed

        if (txtMobileNo.getText() != null && txtMobileNo.getText().trim().length() > 0) {
            if (txtRemarks.getText() != null && txtRemarks.getText().trim().length() > 0) {
                if (!serviceItemses.isEmpty()) {
                    if (services == null) {
                        services = new Services();
                    }
                    services.setCustMobileNo(txtMobileNo.getText());
                    services.setCustName(txtCustName.getText());
                    services.setCustAddress(txtAddess.getText());
                    services.setProductName(txtRemarks.getText());
                    services.setCreatedBy(homePanel.getMainWindow().getUsers().getUserId());
                    services.setStatus("Pending");
                    services.setAmount(Double.parseDouble(txtTotalAmt.getText()));
                    services.setTotalAmount(Double.parseDouble(txttotalFinalAmount.getText()));
                    services.setDiscount(Double.parseDouble(txtTotalDisc.getText()));
                    services.setCreatedOn(new Date());
                    services.setServiceItemses(serviceItemses);
                    lbItemCount.setText(serviceItemses.size() + "");
                    lbDisc.setText(services.getDiscount() + "");
                    lbFnTot.setText(services.getTotalAmount() + "");
                    lbAmount.setText(services.getAmount() + "");
                    Object[][] cod = new Object[serviceItemses.size()][4];
                    int i = 0;
                    for (ServiceItems s : serviceItemses) {
                        cod[i][0] = s.getRemarks();
                        cod[i][1] = s.getAmount();
                        cod[i][2] = s.getDiscount();
                        cod[i][3] = s.getTotalAmount();
                        i++;
                    }
                    tabConfirmation.setModel(new javax.swing.table.DefaultTableModel(
                            cod,
                            new String[]{
                                "Service Type", "Amount", "Discount", "Amount"
                            }
                    ));
                    pnlConfirmation.setVisible(true);
                    tabConfirmation.getColumnModel().getColumn(0).setPreferredWidth(250);
                } else {
                    JOptionPane.showMessageDialog(this, "Please add Items");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Product Details Required");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Mobile No. Required");
        }
    }//GEN-LAST:event_btnImport3ActionPerformed

    private void btnSearch3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch3ActionPerformed
        if (tbPurItems.getSelectedColumnCount() > 0) {
            int sr = Integer.parseInt(tbPurItems.getValueAt(tbPurItems.getSelectedRow(), 0).toString());
            for (int i = 0; i < serviceItemses.size(); i++) {
                if (serviceItemses.get(i).getSrNo() == sr) {
                    serviceItemses.remove(i);
                    break;
                }
            }
            showTableData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select row for delete");
        }

    }//GEN-LAST:event_btnSearch3ActionPerformed

    private void ddDiscountsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ddDiscountsItemStateChanged
        calculateAmount();


    }//GEN-LAST:event_ddDiscountsItemStateChanged

    private void btnConfirmCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmCloseActionPerformed
        pnlConfirmation.setVisible(false);
    }//GEN-LAST:event_btnConfirmCloseActionPerformed

    private void txtPriceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPriceFocusLost
        calculateAmount();
    }//GEN-LAST:event_txtPriceFocusLost

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try {
            htmlContent.print(); 
             this.homePanel.openServiceList(menuName);// TODO add your handling code here:
        } catch (PrinterException ex) {

        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnCSV4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCSV4ActionPerformed
        pnlInvoice.setVisible(false);
         this.homePanel.openServiceList(menuName);
    }//GEN-LAST:event_btnCSV4ActionPerformed

    private void pnlInvoiceWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_pnlInvoiceWindowClosed
        this.homePanel.openServiceList(menuName);
    }//GEN-LAST:event_pnlInvoiceWindowClosed

    private void pnlInvoiceWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_pnlInvoiceWindowDeactivated
         this.homePanel.openServiceList(menuName);
    }//GEN-LAST:event_pnlInvoiceWindowDeactivated

    public void calculateAmount() {
        try {
            int qty = 1;
            double price = Double.parseDouble(txtPrice.getText());
            double amt = qty * price;
            double dis = calDiscount(amt);
            txtDiscount.setText(dis + "");
            txtAmount.setText((amt - dis) + "");
        } catch (Exception e) {
            txtAmount.setText("0");
            txtDiscount.setText("0");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCSV4;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnConfirmClose;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnImport3;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JButton btnSearch3;
    private javax.swing.JComboBox ddDiscounts;
    private javax.swing.JComboBox ddServiceTypes;
    private javax.swing.JTextPane htmlContent;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel labdate;
    private javax.swing.JLabel labdate1;
    private javax.swing.JLabel labdate2;
    private javax.swing.JLabel labdate3;
    private javax.swing.JLabel lbAmount;
    private javax.swing.JLabel lbDisc;
    private javax.swing.JLabel lbDropDownVal;
    private javax.swing.JLabel lbDropDownVal1;
    private javax.swing.JLabel lbDropDownVal14;
    private javax.swing.JLabel lbDropDownVal2;
    private javax.swing.JLabel lbDropDownVal7;
    private javax.swing.JLabel lbDropDownVal8;
    private javax.swing.JLabel lbDropDownVal9;
    private javax.swing.JLabel lbFnTot;
    private javax.swing.JLabel lbItemCount;
    private javax.swing.JDialog pnlConfirmation;
    private javax.swing.JDialog pnlInvoice;
    private javax.swing.JTable tabConfirmation;
    private javax.swing.JTable tbPurItems;
    private javax.swing.JTextField txtAddess;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtCustName;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtMobileNo;
    private javax.swing.JTextField txtOthers;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtRemarks;
    private javax.swing.JTextField txtTotalAmt;
    private javax.swing.JTextField txtTotalDisc;
    private javax.swing.JTextField txttotalFinalAmount;
    // End of variables declaration//GEN-END:variables

}
