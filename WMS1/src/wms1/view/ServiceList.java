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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLEditorKit;
import org.jdesktop.swingx.JXDatePicker;
import wms1.dao.ItemsDao;
import wms1.pojos.Sales;
import wms1.pojos.SalesItems;
import wms1.pojos.ServiceItems;
import wms1.pojos.Services;

import wms1.util.GeneralUtil;
import wms1.util.OrderPDF;
import wms1.util.ReadCSV;

/**
 *
 * @author SATISH SYAMALA
 */
public class ServiceList extends javax.swing.JPanel {

    /**
     * Creates new form ProductPanel
     */
    private HomePanel homePanel;
    private String menuName;
    private JXDatePicker fromDate;
    private JXDatePicker todateDate;
    private int page = 0;
    private int noRec = 0;

    public ServiceList() {
        initComponents();
      
       
    }

    public void init(HomePanel homePanel, String menuName) {
        this.homePanel = homePanel;
        this.menuName = menuName;
        pnlDetails.setVisible(false);
        pnlInvoice.setVisible(false);
        GeneralUtil.setDailBound(pnlInvoice, homePanel, 680, 500);
        GeneralUtil.setDailBound(pnlDetails, homePanel, 660, 450);
        fromDate = GeneralUtil.getJXDatePicker("From Date", 150, 70, 150, 30, new Date());
        todateDate = GeneralUtil.getJXDatePicker("From Date", 330, 70, 150, 30, new Date());
        add(fromDate);
        add(todateDate);
        htmlContent.setEditorKit(new HTMLEditorKit());
        ddServiceTypes.setModel(new javax.swing.DefaultComboBoxModel(GeneralUtil.getServiesTypesArray("All")));
        showTableData(0, 100);
    }

    public void showTableData(int pageNo, int size) {

        ItemsDao itd = new ItemsDao();
        List<Object[]> ls = itd.getServiceList(ddServiceTypes.getSelectedItem().toString(), txtInvoiceNo.getText(),
                fromDate.getDate(), todateDate.getDate(), txtMoblieNo.getText(), pageNo, size, ddStatus.getSelectedItem().toString());
        noRec = ls.size();
        System.err.println("pageNo : " + pageNo);
        if (ls.size() < 100) {
            btnNext.setVisible(false);
        } else {
            btnNext.setVisible(true);
        }
        if (pageNo == 0) {
            btnBack.setVisible(false);
        } else {
            btnBack.setVisible(true);
        }
        Object[][] data = GeneralUtil.ListToStringA(ls, 8);
        tbPurItems.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{
                    "Id", "Status", "Date", "Service No", "Prod. Details", "Moblie No", "Amount", "Closed Date"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbPurItems.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPurItems.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbPurItems.getColumnModel().getColumn(2).setPreferredWidth(130);
        tbPurItems.getColumnModel().getColumn(3).setPreferredWidth(130);
        tbPurItems.getColumnModel().getColumn(4).setPreferredWidth(250);
        tbPurItems.getColumnModel().getColumn(5).setPreferredWidth(130);
        tbPurItems.getColumnModel().getColumn(7).setPreferredWidth(150);

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
        btnCSV4 = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        pnlDetails = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbItems = new javax.swing.JTable();
        btnCSV2 = new javax.swing.JButton();
        libInvoiceNo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbDate = new javax.swing.JLabel();
        lbMobileNo = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbCustomerName = new javax.swing.JLabel();
        lbAddress = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbFinalAmt = new javax.swing.JLabel();
        lbDiscount = new javax.swing.JLabel();
        lbAmount = new javax.swing.JLabel();
        lbProductDetails = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbClosedDate = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnNext = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPurItems = new javax.swing.JTable();
        ddServiceTypes = new javax.swing.JComboBox();
        lbDropDownVal2 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        txtInvoiceNo = new javax.swing.JTextField();
        lbDropDownVal4 = new javax.swing.JLabel();
        btnCSV = new javax.swing.JButton();
        lbDropDownVal1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMoblieNo = new javax.swing.JTextField();
        btnView = new javax.swing.JButton();
        btnInvoicePrint = new javax.swing.JButton();
        btnSearch1 = new javax.swing.JButton();
        ddStatus = new javax.swing.JComboBox();
        lbDropDownVal5 = new javax.swing.JLabel();
        btnView1 = new javax.swing.JButton();
        btnView2 = new javax.swing.JButton();
        btnInvoicePrint1 = new javax.swing.JButton();

        pnlInvoice.getContentPane().setLayout(null);

        htmlContent.setContentType("text/html"); // NOI18N
        jScrollPane3.setViewportView(htmlContent);

        pnlInvoice.getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(10, 10, 660, 340);

        btnCSV4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCSV4.setForeground(new java.awt.Color(204, 0, 0));
        btnCSV4.setText("Close");
        btnCSV4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCSV4ActionPerformed(evt);
            }
        });
        pnlInvoice.getContentPane().add(btnCSV4);
        btnCSV4.setBounds(140, 390, 190, 30);

        btnPrint.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(0, 153, 102));
        btnPrint.setText("Prnit");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        pnlInvoice.getContentPane().add(btnPrint);
        btnPrint.setBounds(350, 390, 190, 30);

        pnlDetails.getContentPane().setLayout(null);

        tbItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Service Type", "Price", "Discount", "Total Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
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
        jScrollPane2.setViewportView(tbItems);

        pnlDetails.getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 170, 620, 110);

        btnCSV2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCSV2.setForeground(new java.awt.Color(204, 0, 0));
        btnCSV2.setText("Close");
        btnCSV2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCSV2ActionPerformed(evt);
            }
        });
        pnlDetails.getContentPane().add(btnCSV2);
        btnCSV2.setBounds(220, 370, 190, 30);

        libInvoiceNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        libInvoiceNo.setText("Invoice No");
        pnlDetails.getContentPane().add(libInvoiceNo);
        libInvoiceNo.setBounds(160, 10, 110, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Closed Date");
        pnlDetails.getContentPane().add(jLabel9);
        jLabel9.setBounds(20, 100, 110, 20);

        lbDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDate.setText("Invoice No");
        pnlDetails.getContentPane().add(lbDate);
        lbDate.setBounds(160, 40, 140, 20);

        lbMobileNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbMobileNo.setText("Invoice No");
        pnlDetails.getContentPane().add(lbMobileNo);
        lbMobileNo.setBounds(160, 70, 110, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Service No");
        pnlDetails.getContentPane().add(jLabel12);
        jLabel12.setBounds(20, 10, 110, 20);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Mobile No.");
        pnlDetails.getContentPane().add(jLabel13);
        jLabel13.setBounds(20, 70, 110, 20);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Customer Name");
        pnlDetails.getContentPane().add(jLabel14);
        jLabel14.setBounds(310, 10, 130, 20);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Product Details");
        pnlDetails.getContentPane().add(jLabel15);
        jLabel15.setBounds(20, 130, 130, 20);

        lbCustomerName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbCustomerName.setText("Invoice No");
        pnlDetails.getContentPane().add(lbCustomerName);
        lbCustomerName.setBounds(440, 10, 110, 20);

        lbAddress.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbAddress.setText("Address");
        pnlDetails.getContentPane().add(lbAddress);
        lbAddress.setBounds(440, 40, 110, 20);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Amount            :");
        pnlDetails.getContentPane().add(jLabel20);
        jLabel20.setBounds(380, 280, 110, 20);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Discount          :");
        pnlDetails.getContentPane().add(jLabel21);
        jLabel21.setBounds(380, 310, 110, 20);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Invoice Amt.    :");
        pnlDetails.getContentPane().add(jLabel22);
        jLabel22.setBounds(380, 340, 130, 20);

        lbFinalAmt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbFinalAmt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbFinalAmt.setText("0");
        pnlDetails.getContentPane().add(lbFinalAmt);
        lbFinalAmt.setBounds(500, 340, 110, 20);

        lbDiscount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDiscount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDiscount.setText("0");
        pnlDetails.getContentPane().add(lbDiscount);
        lbDiscount.setBounds(500, 310, 110, 20);

        lbAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbAmount.setText("0");
        pnlDetails.getContentPane().add(lbAmount);
        lbAmount.setBounds(500, 280, 110, 20);

        lbProductDetails.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbProductDetails.setText("Invoice No");
        pnlDetails.getContentPane().add(lbProductDetails);
        lbProductDetails.setBounds(160, 130, 470, 20);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Status");
        pnlDetails.getContentPane().add(jLabel16);
        jLabel16.setBounds(310, 70, 130, 20);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Address");
        pnlDetails.getContentPane().add(jLabel17);
        jLabel17.setBounds(310, 40, 130, 20);

        lbStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbStatus.setText("Invoice No");
        pnlDetails.getContentPane().add(lbStatus);
        lbStatus.setBounds(440, 70, 110, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Date");
        pnlDetails.getContentPane().add(jLabel10);
        jLabel10.setBounds(20, 40, 110, 20);

        lbClosedDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbClosedDate.setText("Invoice No");
        pnlDetails.getContentPane().add(lbClosedDate);
        lbClosedDate.setBounds(160, 100, 150, 20);

        setOpaque(false);
        setLayout(null);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);

        btnNext.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNext.setForeground(new java.awt.Color(51, 51, 255));
        btnNext.setText("  >>");
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextMouseClicked(evt);
            }
        });
        jPanel2.add(btnNext);
        btnNext.setBounds(720, 100, 40, 30);
        jPanel2.add(jSeparator2);
        jSeparator2.setBounds(0, 100, 770, 10);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Service No");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(150, 0, 110, 20);

        tbPurItems.setAutoCreateRowSorter(true);
        tbPurItems.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbPurItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Sr,No", "Date", "Service No", "Moblie No", "Decription", "Amount", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPurItems.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbPurItems.setFocusCycleRoot(true);
        tbPurItems.setOpaque(false);
        tbPurItems.setRowHeight(25);
        tbPurItems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbPurItems.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbPurItems);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 130, 770, 360);

        ddServiceTypes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ddServiceTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        ddServiceTypes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddServiceTypesActionPerformed(evt);
            }
        });
        jPanel2.add(ddServiceTypes);
        ddServiceTypes.setBounds(330, 20, 210, 30);

        lbDropDownVal2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal2.setText("Service Types");
        jPanel2.add(lbDropDownVal2);
        lbDropDownVal2.setBounds(330, 0, 60, 20);

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(0, 102, 51));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch);
        btnSearch.setBounds(680, 60, 100, 40);
        jPanel2.add(jSeparator3);
        jSeparator3.setBounds(10, 540, 770, 10);

        txtInvoiceNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txtInvoiceNo);
        txtInvoiceNo.setBounds(150, 20, 150, 30);

        lbDropDownVal4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal4.setText("Status");
        jPanel2.add(lbDropDownVal4);
        lbDropDownVal4.setBounds(510, 50, 70, 20);

        btnCSV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCSV.setForeground(new java.awt.Color(0, 153, 51));
        btnCSV.setText("Download CSV");
        btnCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCSVActionPerformed(evt);
            }
        });
        jPanel2.add(btnCSV);
        btnCSV.setBounds(20, 550, 190, 30);

        lbDropDownVal1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal1.setText("From Date");
        jPanel2.add(lbDropDownVal1);
        lbDropDownVal1.setBounds(150, 50, 110, 20);

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(0, 0, 255));
        btnBack.setText("  <<");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        jPanel2.add(btnBack);
        btnBack.setBounds(20, 100, 40, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Mobile No");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(600, 0, 120, 20);

        txtMoblieNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txtMoblieNo);
        txtMoblieNo.setBounds(600, 20, 170, 30);

        btnView.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnView.setForeground(new java.awt.Color(0, 153, 153));
        btnView.setText("View");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });
        jPanel2.add(btnView);
        btnView.setBounds(450, 550, 90, 30);

        btnInvoicePrint.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInvoicePrint.setForeground(new java.awt.Color(0, 102, 51));
        btnInvoicePrint.setText("Download");
        btnInvoicePrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvoicePrintActionPerformed(evt);
            }
        });
        jPanel2.add(btnInvoicePrint);
        btnInvoicePrint.setBounds(550, 550, 110, 30);

        btnSearch1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch1.setForeground(new java.awt.Color(0, 102, 51));
        btnSearch1.setText("NEW");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch1);
        btnSearch1.setBounds(10, 10, 130, 80);

        ddStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ddStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Pending", "Closed" }));
        ddStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddStatusActionPerformed(evt);
            }
        });
        jPanel2.add(ddStatus);
        ddStatus.setBounds(510, 70, 150, 30);

        lbDropDownVal5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal5.setText("To Date");
        jPanel2.add(lbDropDownVal5);
        lbDropDownVal5.setBounds(330, 50, 70, 20);

        btnView1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnView1.setForeground(new java.awt.Color(204, 0, 0));
        btnView1.setText("Close Service");
        btnView1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnView1);
        btnView1.setBounds(490, 500, 160, 30);

        btnView2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnView2.setForeground(new java.awt.Color(0, 153, 153));
        btnView2.setText("Edit");
        btnView2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnView2);
        btnView2.setBounds(680, 500, 100, 30);

        btnInvoicePrint1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInvoicePrint1.setForeground(new java.awt.Color(102, 102, 0));
        btnInvoicePrint1.setText("Print");
        btnInvoicePrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvoicePrint1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnInvoicePrint1);
        btnInvoicePrint1.setBounds(670, 550, 110, 30);

        add(jPanel2);
        jPanel2.setBounds(0, 0, 790, 590);
    }// </editor-fold>//GEN-END:initComponents


    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        page = 0;
        showTableData(0, 100);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void ddServiceTypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddServiceTypesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ddServiceTypesActionPerformed

    private void btnCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCSVActionPerformed
        ItemsDao itd = new ItemsDao();
        List<Object[]> ls = itd.getServiceList(ddServiceTypes.getSelectedItem().toString(), txtInvoiceNo.getText(),
                fromDate.getDate(), todateDate.getDate(), txtMoblieNo.getText(), 0, 0, ddStatus.getSelectedItem().toString());

        String headers = "Id,Status,Date,Service No,Prod. Details,Moblie No,Amount,Closed Date";
        String msg = ReadCSV.writeCSVfile(ls, headers, "purchases", null);
        if (msg != null) {
            try {
                if ((new File(msg)).exists()) {
                    Process p;
                    p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + msg);
                    p.waitFor();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {

        }


    }//GEN-LAST:event_btnCSVActionPerformed

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        page--;
        showTableData(page * 100, 100);
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseClicked
        page++;
        showTableData(page * 100, 100);
    }//GEN-LAST:event_btnNextMouseClicked

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        if (tbPurItems.getSelectedColumnCount() > 0) {
            long id = Long.parseLong(tbPurItems.getValueAt(tbPurItems.getSelectedRow(), 0).toString());
            ItemsDao itid = new ItemsDao();
            Services s = itid.getService(id);
            if (s != null) {
                pnlInvoice.setTitle(" Service : " + s.getServiceNo());
                libInvoiceNo.setText(s.getServiceNo());
                lbDate.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(s.getCreatedOn()));
                lbMobileNo.setText(s.getCustMobileNo());
                lbCustomerName.setText(s.getCustName());
                lbAddress.setText(s.getCustAddress());
                lbAmount.setText(s.getAmount() + "");
                lbDiscount.setText(s.getDiscount() + "");
                lbFinalAmt.setText(s.getTotalAmount() + "");
                lbProductDetails.setText(s.getProductName());
                lbStatus.setText(s.getStatus());
                lbClosedDate.setText("");
                if (s.getCloseddOn() != null) {
                    lbClosedDate.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(s.getCloseddOn()));
                }
                List<ServiceItems> ls = itid.getServiceItems(id, null);
                Object[][] data = new Object[ls.size()][6];
                int i = 0;
                for (ServiceItems p : ls) {
                    data[i][0] = (i + 1);
                    data[i][1] = p.getRemarks();
                    data[i][2] = p.getPrice();
                    data[i][3] = p.getQuantity();
                    data[i][4] = p.getPrice();
                    i++;
                }
                tbItems.setModel(new javax.swing.table.DefaultTableModel(
                        data,
                        new String[]{
                            "Id", "Servic Type", "Price", "Discount", "Total Amt"
                        }
                ) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
                tbItems.getColumnModel().getColumn(0).setPreferredWidth(30);
                tbItems.getColumnModel().getColumn(1).setPreferredWidth(200);
            }
            pnlDetails.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select row to view");
        }
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnCSV2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCSV2ActionPerformed
        pnlDetails.setVisible(false);
    }//GEN-LAST:event_btnCSV2ActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try {
            htmlContent.print();        // TODO add your handling code here:
        } catch (PrinterException ex) {
            Logger.getLogger(ServiceList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnCSV4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCSV4ActionPerformed
        pnlInvoice.setVisible(false);
    }//GEN-LAST:event_btnCSV4ActionPerformed

    private void btnInvoicePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvoicePrintActionPerformed
        if (tbPurItems.getSelectedColumnCount() > 0) {
            long id = Long.parseLong(tbPurItems.getValueAt(tbPurItems.getSelectedRow(), 0).toString());
            ItemsDao itid = new ItemsDao();
            Services s = itid.getService(id);
            List<ServiceItems> ls = itid.getServiceItems(id, null);
            s.setServiceItemses(ls);
            File f = OrderPDF.servicePdf(s);
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

        } else {
            JOptionPane.showMessageDialog(this, "Please select row to view");
        }
    }//GEN-LAST:event_btnInvoicePrintActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        this.homePanel.openServicePanel1(menuName);
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void ddStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ddStatusActionPerformed

    private void btnView1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView1ActionPerformed
        if (tbPurItems.getSelectedColumnCount() > 0) {
            long id = Long.parseLong(tbPurItems.getValueAt(tbPurItems.getSelectedRow(), 0).toString());
            ItemsDao itid = new ItemsDao();
            Services s = itid.getService(id);
            if (s.getStatus().equalsIgnoreCase("Pending")) {
                List<ServiceItems> ls = itid.getServiceItems(id, null);
                s.setServiceItemses(ls);
                s.setProductCode("closed");
                this.homePanel.openServicePanel1(menuName, s);
            } else {
                JOptionPane.showMessageDialog(this, "Service closed");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select row to view");
        }

    }//GEN-LAST:event_btnView1ActionPerformed

    private void btnView2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView2ActionPerformed
        if (tbPurItems.getSelectedColumnCount() > 0) {
            long id = Long.parseLong(tbPurItems.getValueAt(tbPurItems.getSelectedRow(), 0).toString());
            ItemsDao itid = new ItemsDao();
            Services s = itid.getService(id);
            if (s.getStatus().equalsIgnoreCase("Pending")) {
                List<ServiceItems> ls = itid.getServiceItems(id, null);
                s.setServiceItemses(ls);
                s.setProductCode("edit");
                this.homePanel.openServicePanel1(menuName, s);
            } else {
                JOptionPane.showMessageDialog(this, "Service closed");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select row to view");
        }
    }//GEN-LAST:event_btnView2ActionPerformed

    private void btnInvoicePrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvoicePrint1ActionPerformed
        if (tbPurItems.getSelectedColumnCount() > 0) {
            long id = Long.parseLong(tbPurItems.getValueAt(tbPurItems.getSelectedRow(), 0).toString());
            ItemsDao itid = new ItemsDao();
            Services s = itid.getService(id);
            List<ServiceItems> ls = itid.getServiceItems(id, null);
            s.setServiceItemses(ls);
            pnlDetails.setTitle(" Service : " + s.getServiceNo());
            String con = ReadCSV.generateServiceBill(s);
            System.err.println("Con " + con);
            htmlContent.setText(con);
            pnlInvoice.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Please select row to view");
        }
    }//GEN-LAST:event_btnInvoicePrint1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JButton btnCSV;
    private javax.swing.JButton btnCSV2;
    private javax.swing.JButton btnCSV4;
    private javax.swing.JButton btnInvoicePrint;
    private javax.swing.JButton btnInvoicePrint1;
    private javax.swing.JLabel btnNext;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnView;
    private javax.swing.JButton btnView1;
    private javax.swing.JButton btnView2;
    private javax.swing.JComboBox ddServiceTypes;
    private javax.swing.JComboBox ddStatus;
    private javax.swing.JTextPane htmlContent;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbAmount;
    private javax.swing.JLabel lbClosedDate;
    private javax.swing.JLabel lbCustomerName;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbDiscount;
    private javax.swing.JLabel lbDropDownVal1;
    private javax.swing.JLabel lbDropDownVal2;
    private javax.swing.JLabel lbDropDownVal4;
    private javax.swing.JLabel lbDropDownVal5;
    private javax.swing.JLabel lbFinalAmt;
    private javax.swing.JLabel lbMobileNo;
    private javax.swing.JLabel lbProductDetails;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel libInvoiceNo;
    private javax.swing.JDialog pnlDetails;
    private javax.swing.JDialog pnlInvoice;
    private javax.swing.JTable tbItems;
    private javax.swing.JTable tbPurItems;
    private javax.swing.JTextField txtInvoiceNo;
    private javax.swing.JTextField txtMoblieNo;
    // End of variables declaration//GEN-END:variables

}
