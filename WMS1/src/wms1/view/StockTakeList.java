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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import wms1.dao.StockTakeDao;
import wms1.pojos.Brands;
import wms1.pojos.Items;
import wms1.pojos.Purchases;
import wms1.pojos.PurchasesItems;
import wms1.pojos.StockTake;
import wms1.pojos.Users;
import wms1.util.ExcelReadAndWrite;

import wms1.util.GeneralUtil;
import wms1.util.ReadCSV;

/**
 *
 * @author SATISH SYAMALA
 */
public class StockTakeList extends javax.swing.JPanel {

    /**
     * Creates new form ProductPanel
     */
    private HomePanel homePanel;
    private String menuName;
    private JXDatePicker fromDate;
    private JXDatePicker todateDate;
    private int page = 0;
    private int noRec = 0;

    public StockTakeList() {
        initComponents();
        fromDate = GeneralUtil.getJXDatePicker("From Date", 150, 70, 200, 30, new Date());
        todateDate = GeneralUtil.getJXDatePicker("From Date", 370, 70, 210, 30, new Date());
        add(fromDate);
        add(todateDate);
    }

    public void init(HomePanel homePanel, String menuName) {
        this.homePanel = homePanel;
        this.menuName = menuName;
        StockTakeDao sd = new StockTakeDao();
        StockTake preSt = sd.lastStockTake(null);
        if (preSt != null) {
            fromDate.setDate(preSt.getStockTakedOn());
            todateDate.setDate(preSt.getStockTakedOn());
        } else {
            fromDate.setDate(new Date());
            todateDate.setDate(new Date());
        }
        ddBrand.setModel(new javax.swing.DefaultComboBoxModel(GeneralUtil.getBrandsArray("All")));
        showTableData(0, 100);
    }

    public void showTableData(int pageNo, int size) {

        StockTakeDao itd = new StockTakeDao();
        List<Object[]> ls = itd.getStockList(ddBrand.getSelectedItem().toString(),
                ddItems.getSelectedItem().toString(),
                fromDate.getDate(), todateDate.getDate(), pageNo, size, jCheckBox1.isSelected());

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
        Object[][] data = GeneralUtil.ListToStringA(ls, 13);
        tbPurItems.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{
                    "Id", "Date", "Brand", "Item", "Price", "OB", "Purchases", "Sales", "CB", "ST Qty.", "Adj. Qty", "Adj. Amount", "Remarks"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false
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
        tbPurItems.getColumnModel().getColumn(2).setPreferredWidth(150);
        tbPurItems.getColumnModel().getColumn(3).setPreferredWidth(150);
        tbPurItems.getColumnModel().getColumn(4).setPreferredWidth(150);
        tbPurItems.getColumnModel().getColumn(11).setPreferredWidth(100);
        tbPurItems.getColumnModel().getColumn(12).setPreferredWidth(200);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnNext = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        ddBrand = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPurItems = new javax.swing.JTable();
        ddItems = new javax.swing.JComboBox();
        lbDropDownVal2 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        lbDropDownVal3 = new javax.swing.JLabel();
        lbDropDownVal4 = new javax.swing.JLabel();
        btnCsv = new javax.swing.JButton();
        lbDropDownVal1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnSearch1 = new javax.swing.JButton();
        btnCsv1 = new javax.swing.JButton();

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
        jLabel6.setText("Only Deffirence Items ");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(620, 0, 160, 20);

        ddBrand.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ddBrand.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        ddBrand.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ddBrandItemStateChanged(evt);
            }
        });
        ddBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddBrandActionPerformed(evt);
            }
        });
        jPanel2.add(ddBrand);
        ddBrand.setBounds(150, 20, 200, 30);

        tbPurItems.setAutoCreateRowSorter(true);
        tbPurItems.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbPurItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Date", "Brand", "Item", "Price", "OB", "Purchases", "Sales", "CB", "ST Qty.", "Adj. Qty", "Adj. Amt", "Remarks"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
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
        jScrollPane1.setBounds(10, 130, 770, 400);

        ddItems.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ddItems.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        ddItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddItemsActionPerformed(evt);
            }
        });
        jPanel2.add(ddItems);
        ddItems.setBounds(370, 20, 210, 30);

        lbDropDownVal2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal2.setText("Item");
        jPanel2.add(lbDropDownVal2);
        lbDropDownVal2.setBounds(370, 0, 70, 20);

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(0, 102, 51));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch);
        btnSearch.setBounds(620, 70, 160, 30);
        jPanel2.add(jSeparator3);
        jSeparator3.setBounds(10, 540, 770, 10);

        lbDropDownVal3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal3.setText("Brand");
        jPanel2.add(lbDropDownVal3);
        lbDropDownVal3.setBounds(150, 0, 70, 20);

        lbDropDownVal4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal4.setText("To Date");
        jPanel2.add(lbDropDownVal4);
        lbDropDownVal4.setBounds(370, 50, 70, 20);

        btnCsv.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCsv.setForeground(new java.awt.Color(0, 102, 51));
        btnCsv.setText("CSV");
        btnCsv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCsvActionPerformed(evt);
            }
        });
        jPanel2.add(btnCsv);
        btnCsv.setBounds(130, 550, 90, 30);

        lbDropDownVal1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal1.setText("From Date");
        jPanel2.add(lbDropDownVal1);
        lbDropDownVal1.setBounds(150, 50, 120, 20);

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

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jCheckBox1);
        jCheckBox1.setBounds(620, 20, 21, 30);
        jCheckBox1.getAccessibleContext().setAccessibleDescription("");

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

        btnCsv1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCsv1.setForeground(new java.awt.Color(153, 0, 153));
        btnCsv1.setText("Excel");
        btnCsv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCsv1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnCsv1);
        btnCsv1.setBounds(10, 550, 110, 30);

        add(jPanel2);
        jPanel2.setBounds(0, 0, 790, 590);
    }// </editor-fold>//GEN-END:initComponents


    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        page = 0;
        showTableData(0, 100);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void ddItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddItemsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ddItemsActionPerformed

    private void ddBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddBrandActionPerformed

    }//GEN-LAST:event_ddBrandActionPerformed

    private void ddBrandItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ddBrandItemStateChanged

        if (ddBrand.getSelectedItem() != null && !ddBrand.getSelectedItem().toString().equalsIgnoreCase("select")) {
            ddItems.setModel(new javax.swing.DefaultComboBoxModel(GeneralUtil.getItemeArrayPre("All", ddBrand.getSelectedItem().toString())));
        } else {
            ddItems.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"All"}));
        }
    }//GEN-LAST:event_ddBrandItemStateChanged

    private void btnCsvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCsvActionPerformed
        StockTakeDao itd = new StockTakeDao();
        List<Object[]> ls = itd.getStockList(ddBrand.getSelectedItem().toString(),
                ddItems.getSelectedItem().toString(),
                fromDate.getDate(), todateDate.getDate(), 0, 0, jCheckBox1.isSelected());
        String headers = "Id,Date,Brand,Item,Price,OB,Purchases,Sales,CB,ST Qty.,Adj. Qty,Adj. Amt,Remarks";
        String msg = ReadCSV.writeCSVfile(ls, headers, "Stock Take", null);
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


    }//GEN-LAST:event_btnCsvActionPerformed

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        page--;
        showTableData(page * 100, 100);
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseClicked
        page++;
        showTableData(page * 100, 100);
    }//GEN-LAST:event_btnNextMouseClicked

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        this.homePanel.openStockTakePanel(menuName);
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void btnCsv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCsv1ActionPerformed
        StockTakeDao itd = new StockTakeDao();
        List<Object[]> ls = itd.getStockList(ddBrand.getSelectedItem().toString(),
                ddItems.getSelectedItem().toString(),
                fromDate.getDate(), todateDate.getDate(), 0, 0, jCheckBox1.isSelected());
        String hd = "Id,Date,Brand,Item,Price,OB,Purchases,Sales,CB,ST Qty.,Adj. Qty,Adj. Amt,Remarks";
        List<String> headers = Arrays.asList(hd.split(","));
        String msg = ExcelReadAndWrite.writeXlsx(headers, ls, "Stock Take", null);

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
    }//GEN-LAST:event_btnCsv1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JButton btnCsv;
    private javax.swing.JButton btnCsv1;
    private javax.swing.JLabel btnNext;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JComboBox ddBrand;
    private javax.swing.JComboBox ddItems;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbDropDownVal1;
    private javax.swing.JLabel lbDropDownVal2;
    private javax.swing.JLabel lbDropDownVal3;
    private javax.swing.JLabel lbDropDownVal4;
    private javax.swing.JTable tbPurItems;
    // End of variables declaration//GEN-END:variables

}
