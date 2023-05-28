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
import wms1.dao.ReportsDao;
import wms1.pojos.Brands;
import wms1.pojos.Items;
import wms1.pojos.Purchases;
import wms1.pojos.PurchasesItems;
import wms1.pojos.Users;
import wms1.util.ExcelReadAndWrite;

import wms1.util.GeneralUtil;
import wms1.util.ReadCSV;
import wms1.util.ReportUtil;

/**
 *
 * @author SATISH SYAMALA
 */
public class ReportData extends javax.swing.JPanel {

    /**
     * Creates new form ProductPanel
     */
    private HomePanel homePanel;
    private String menuName;
    private int page = 0;
    private int noRec = 0;
    private Map<String, Object> fieldsMap;

    public ReportData() {
        initComponents();

    }

    public void init(HomePanel homePanel, String menuName) {
        this.homePanel = homePanel;
        this.menuName = menuName;
        lbReportName.setText(menuName);
        getFields();
        page = 0;
        noRec = 0;
        showTableData(0, 100);
    }

    public Map<String, Object> getFieldsData() {
        Map<String, Object> filters = new HashMap<>();
        for (String s : fieldsMap.keySet()) {
            if (fieldsMap.get(s) instanceof JTextField) {
                if (((JTextField) fieldsMap.get(s)).getText() != null && ((JTextField) fieldsMap.get(s)).getText().trim().length() > 0) {
                    filters.put(s, ((JTextField) fieldsMap.get(s)).getText());
                }
            }
            if (fieldsMap.get(s) instanceof JComboBox) {
                if (((JComboBox) fieldsMap.get(s)).getSelectedItem() != null && !((JComboBox) fieldsMap.get(s)).getSelectedItem().toString().equalsIgnoreCase("All")) {
                    filters.put(s, ((JComboBox) fieldsMap.get(s)).getSelectedItem());
                }
            }
            if (fieldsMap.get(s) instanceof JXDatePicker) {
                if (((JXDatePicker) fieldsMap.get(s)).getDate() != null) {
                    filters.put(s, ((JXDatePicker) fieldsMap.get(s)).getDate());
                }
            }
        }
        return filters;
    }

    public void showTableData(int pageNo, int size) {

        ReportsDao itd = new ReportsDao();
        List<Object[]> ls = itd.getReportData(menuName, getFieldsData(), pageNo, size);
        noRec = ls.size();

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
        String[] headers = ReportUtil.getReportHeaders(menuName);
        Object[][] data = GeneralUtil.ListToStringA(ls, headers.length);

        tbPurItems.setModel(new javax.swing.table.DefaultTableModel(
                data, headers
        ));
        for (int i = 0; i < headers.length; i++) {
            tbPurItems.getColumnModel().getColumn(i).setPreferredWidth(150);
        }

    }

    public void getFields() {
        fieldsMap = new HashMap<String, Object>();
        pnlFields.removeAll();
        JSONArray fieldArry = GeneralUtil.reportJSONArray(menuName);
        int x = 5;
        int y = 0;
        int index = 1;
        for (int i = 0; i < fieldArry.size(); i++) {
            JSONObject f = (JSONObject) fieldArry.get(i);
            pnlFields.add(GeneralUtil.getJLabel(f.get("name").toString(), x, y, 150, 25));
            if (f.get("type").toString().equalsIgnoreCase("text") || f.get("type").toString().equalsIgnoreCase("number")) {
                JTextField t = GeneralUtil.getJTextField("", x, y + 25, 200, 25, f.get("datatype").toString());
                pnlFields.add(t);
                fieldsMap.put(f.get("name").toString(), t);
            } else if (f.get("type").toString().equalsIgnoreCase("dropdown")) {
                JComboBox t = GeneralUtil.getJComboBox("", x, y + 25, 200, 25, (JSONArray) f.get("values"));
                if (f.get("name").toString().equalsIgnoreCase("brand")) {
                    t.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                            ddBrandItemStateChanged(evt, t);
                        }
                    });
                }

                pnlFields.add(t);
                fieldsMap.put(f.get("name").toString(), t);
            } else if (f.get("type").toString().equalsIgnoreCase("date")) {
                JXDatePicker t = GeneralUtil.getJXDatePicker("", x, y + 25, 200, 25, new Date());
                pnlFields.add(t);
                fieldsMap.put(f.get("name").toString(), t);
            }
            x += 215;
            if (index % 3 == 0) {
                y = y + 50;
                x = 5;
            }
            index++;
        }
        pnlFields.revalidate();
        pnlFields.repaint();
    }

    private void ddBrandItemStateChanged(java.awt.event.ItemEvent evt, JComboBox t) {
        System.out.println("Brand : " + t.getSelectedItem().toString());
        if (fieldsMap.containsKey("Items")) {
            JComboBox item = (JComboBox) fieldsMap.get("Items");
            if (t.getSelectedItem() != null && !t.getSelectedItem().toString().equalsIgnoreCase("all")) {
                item.setModel(new javax.swing.DefaultComboBoxModel(GeneralUtil.getItemeArrayPre("All", t.getSelectedItem().toString())));
            } else {
                item.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"All"}));
            }
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPurItems = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        btnCsv = new javax.swing.JButton();
        btnBack = new javax.swing.JLabel();
        btnCsv1 = new javax.swing.JButton();
        lbReportName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlFields = new javax.swing.JPanel();
        btnSearch1 = new javax.swing.JButton();

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
        btnNext.setBounds(740, 110, 40, 30);
        jPanel2.add(jSeparator2);
        jSeparator2.setBounds(0, 118, 770, 0);

        tbPurItems.setAutoCreateRowSorter(true);
        tbPurItems.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbPurItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Sr,No", "Brand", "Item Code", "Name", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        tbPurItems.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbPurItems.setFocusCycleRoot(true);
        tbPurItems.setOpaque(false);
        tbPurItems.setRowHeight(25);
        tbPurItems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbPurItems.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbPurItems);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 140, 770, 390);

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(204, 51, 0));
        btnSearch.setText("Clear");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch);
        btnSearch.setBounds(680, 10, 100, 30);
        jPanel2.add(jSeparator3);
        jSeparator3.setBounds(10, 540, 770, 20);

        btnCsv.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCsv.setForeground(new java.awt.Color(0, 102, 51));
        btnCsv.setText("CSV");
        btnCsv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCsvActionPerformed(evt);
            }
        });
        jPanel2.add(btnCsv);
        btnCsv.setBounds(150, 550, 100, 30);

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(0, 0, 255));
        btnBack.setText("  <<");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        jPanel2.add(btnBack);
        btnBack.setBounds(10, 110, 40, 30);

        btnCsv1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCsv1.setForeground(new java.awt.Color(153, 0, 153));
        btnCsv1.setText("Excel");
        btnCsv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCsv1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnCsv1);
        btnCsv1.setBounds(20, 550, 110, 30);

        lbReportName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbReportName.setForeground(new java.awt.Color(0, 102, 51));
        lbReportName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbReportName.setText("Report Name");
        jPanel2.add(lbReportName);
        lbReportName.setBounds(60, 110, 670, 30);
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(10, 110, 770, 10);

        pnlFields.setBackground(new java.awt.Color(255, 255, 255));
        pnlFields.setLayout(null);
        jPanel2.add(pnlFields);
        pnlFields.setBounds(10, 0, 660, 110);

        btnSearch1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch1.setForeground(new java.awt.Color(0, 102, 51));
        btnSearch1.setText("Search");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch1);
        btnSearch1.setBounds(680, 50, 100, 50);

        add(jPanel2);
        jPanel2.setBounds(0, 0, 790, 590);
    }// </editor-fold>//GEN-END:initComponents


    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        page = 0;
        showTableData(0, 100);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCsvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCsvActionPerformed
        ReportsDao itd = new ReportsDao();
        List<Object[]> ls = itd.getReportData(menuName, getFieldsData(), 0, 0);
        String[] hd = ReportUtil.getReportHeaders(menuName);
        String headers = "";
        for (String s : hd) {
            headers += s + ",";
        }
        headers = headers.substring(0, headers.length() - 1);
        String msg = ReadCSV.writeCSVfile(ls, headers, menuName, null);
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

    private void btnCsv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCsv1ActionPerformed
        ReportsDao itd = new ReportsDao();
         Map<String, Object> filters=getFieldsData();
        List<Object[]> ls = itd.getReportData(menuName, filters, 0, 0);
        String[] hd = ReportUtil.getReportHeaders(menuName);
        List<String> headers = Arrays.asList(hd);
        String msg = ExcelReadAndWrite.writeXlsx(headers, ls, menuName, null,filters);
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
        }
    }//GEN-LAST:event_btnCsv1ActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        page = 0;
        noRec = 0;
        showTableData(0, 100);
    }//GEN-LAST:event_btnSearch1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JButton btnCsv;
    private javax.swing.JButton btnCsv1;
    private javax.swing.JLabel btnNext;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbReportName;
    private javax.swing.JPanel pnlFields;
    private javax.swing.JTable tbPurItems;
    // End of variables declaration//GEN-END:variables

}
