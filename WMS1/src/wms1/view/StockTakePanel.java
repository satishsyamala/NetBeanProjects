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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXDatePicker;
import wms1.dao.ItemsDao;
import wms1.dao.StockTakeDao;
import wms1.pojos.Items;
import wms1.pojos.StockTakAdj;
import wms1.pojos.StockTake;
import wms1.util.ExcelReadAndWrite;

import wms1.util.GeneralUtil;
import wms1.util.ReadCSV;

/**
 *
 * @author SATISH SYAMALA
 */
public class StockTakePanel extends javax.swing.JPanel {

    /**
     * Creates new form ProductPanel
     */
    private HomePanel homePanel;
    private String menuName;
    private Map<String, Object> fieldsMap = new HashMap<String, Object>();
    private List<StockTakAdj> stockTakAdj;
    private int srNo;
    private JXDatePicker fromDate;
    private StockTake preStockTake;
    private Map<Long, Integer> preStockItmes;
    private Map<Long, Integer> allSales;
    private Map<Long, Integer> allPurchases;

    public StockTakePanel() {
        initComponents();
        
    }
    
    public void init(HomePanel homePanel, String menuName) {

        pnlFilechosser.setVisible(false);
        GeneralUtil.setDailBound(pnlFilechosser, homePanel, 560, 400);
        this.homePanel = homePanel;
        this.menuName = menuName;
    
        fromDate = GeneralUtil.getJXDatePicker("From Date", 20, 30, 200, 30, new Date());
        fromDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateChange();
            }
        });
        add(fromDate);
        StockTakeDao sd = new StockTakeDao();
        stockTakAdj=new ArrayList<>();
        preStockTake = sd.lastStockTake(null);
        preStockItmes = new HashMap<>();
        allSales = new HashMap<>();
        allPurchases = new HashMap<>();
        if (preStockTake != null) {
            txtDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(preStockTake.getStockTakedOn()));
        }
       ddBrand.setModel(new javax.swing.DefaultComboBoxModel(GeneralUtil.getBrandsArray("Select")));
    }

    public void dateChange() {
        if (dateValidation()) {
            preStockItmes = new HashMap<>();
            allSales = new HashMap<>();
            allPurchases = new HashMap<>();
            StockTakeDao sd = new StockTakeDao();
            System.err.println("Selected Date " + fromDate.getDate());
            if (preStockTake != null) {
                preStockItmes = getArrTomap(sd.getStockTakeItemsArr(preStockTake.getStockTakeId(), 0));
                allSales = getArrTomap(sd.getSalesByDate(0, preStockTake.getStockTakedOn(), fromDate.getDate()));
                allPurchases = getArrTomap(sd.getPurchasesByDate(0, preStockTake.getStockTakedOn(), fromDate.getDate()));
            } else {
                allSales = getArrTomap(sd.getSalesByDate(0, null, fromDate.getDate()));
                allPurchases = getArrTomap(sd.getPurchasesByDate(0, null, fromDate.getDate()));
            }
            System.out.println("allSales Date " + allSales.size());
            stockTakAdj = new ArrayList<>();
            srNo = 1;
            showTableData();
        } else {
            fromDate.setDate(new Date());
        }
    }

    public Map<Long, Integer> getArrTomap(List<Object[]> input) {
        System.out.println("input input " + input.size());
        Map<Long, Integer> res = new HashMap<>();
        for (Object[] o : input) {
            res.put(Long.parseLong(o[0].toString()), Integer.parseInt(o[1].toString()));
        }
        return res;
    }

    

    public void showTableData() {
        Object[][] data = null;

        if (stockTakAdj != null && stockTakAdj.size() > 0) {
            List<StockTakAdj> tempLs = new ArrayList<>();
            for (StockTakAdj p : stockTakAdj) {
                if (jCheckBox1.isSelected()) {
                    if (p.getAdjQuantity() != 0) {
                        tempLs.add(p);
                    }
                } else {
                    tempLs.add(p);
                }
            }
            data = new Object[tempLs.size()][9];
            int i = 0;
            for (StockTakAdj p : tempLs) {

                data[i][0] = p.getSrNo();
                data[i][1] = p.getItems().getItemName();
                data[i][2] = p.getOpenBal();
                data[i][3] = p.getPurchases();
                data[i][4] = p.getSales();
                data[i][5] = p.getCloseBal();
                data[i][6] = p.getStQuantity();
                data[i][7] = p.getAdjQuantity();
                data[i][8] = "";
                i++;
            }
        } else {
            data = new Object[0][0];
        }
        tbPurItems.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{
                    "Sr,No", "Item", "OB", "Purchases", "Sales", "CB", "ST Qty", "Adj. Qty", "Remarks"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbPurItems.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPurItems.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbPurItems.getColumnModel().getColumn(8).setPreferredWidth(100);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFilechosser = new javax.swing.JDialog();
        lbTemplerHeaders1 = new javax.swing.JLabel();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel2 = new javax.swing.JPanel();
        txtQuantity = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lbDropDownVal = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        ddBrand = new javax.swing.JComboBox();
        btnImport = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPurItems = new javax.swing.JTable();
        lbDropDownVal1 = new javax.swing.JLabel();
        ddItems = new javax.swing.JComboBox();
        lbDropDownVal2 = new javax.swing.JLabel();
        btnSearch1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        btnImport2 = new javax.swing.JButton();
        labdate = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        btnSearch2 = new javax.swing.JButton();
        btnImport3 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        pnlFilechosser.setTitle("Templet Headers (File :  CSV, XLS, XLSX)");
        pnlFilechosser.setModal(true);
        pnlFilechosser.getContentPane().setLayout(null);

        lbTemplerHeaders1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTemplerHeaders1.setForeground(new java.awt.Color(0, 153, 255));
        lbTemplerHeaders1.setText("Item Code,Quantity");
        pnlFilechosser.getContentPane().add(lbTemplerHeaders1);
        lbTemplerHeaders1.setBounds(10, 0, 490, 30);

        jFileChooser1.setSelectedFile(new java.io.File("C:\\Program Files\\NetBeans-12.2\\csv"));
        jFileChooser1.setToolTipText("");
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });
        pnlFilechosser.getContentPane().add(jFileChooser1);
        jFileChooser1.setBounds(10, 30, 540, 330);

        setOpaque(false);
        setLayout(null);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);

        txtQuantity.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtQuantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(txtQuantity);
        txtQuantity.setBounds(540, 420, 140, 30);

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(0, 204, 204));
        btnSearch.setText("Add");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch);
        btnSearch.setBounds(690, 390, 80, 60);

        lbDropDownVal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal.setText("Brand");
        jPanel2.add(lbDropDownVal);
        lbDropDownVal.setBounds(120, 390, 70, 30);
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(10, 470, 770, 10);
        jPanel2.add(jSeparator2);
        jSeparator2.setBounds(10, 100, 770, 0);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Date");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(20, 0, 110, 30);

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
        ddBrand.setBounds(120, 420, 160, 30);

        btnImport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnImport.setForeground(new java.awt.Color(204, 0, 0));
        btnImport.setText("Clear All");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });
        jPanel2.add(btnImport);
        btnImport.setBounds(20, 480, 150, 40);

        tbPurItems.setAutoCreateRowSorter(true);
        tbPurItems.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbPurItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Sr,No", "Item", "OB", "Purchases", "Sales", "CB", "ST Qty", "Adj. Qty", "Remarks"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
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
        jScrollPane1.setBounds(10, 110, 770, 260);

        lbDropDownVal1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal1.setText("Quantity");
        jPanel2.add(lbDropDownVal1);
        lbDropDownVal1.setBounds(540, 390, 70, 30);

        ddItems.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ddItems.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select" }));
        ddItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddItemsActionPerformed(evt);
            }
        });
        jPanel2.add(ddItems);
        ddItems.setBounds(320, 420, 190, 30);

        lbDropDownVal2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal2.setText("Item");
        jPanel2.add(lbDropDownVal2);
        lbDropDownVal2.setBounds(320, 390, 70, 30);

        btnSearch1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch1.setForeground(new java.awt.Color(255, 0, 0));
        btnSearch1.setText("X row");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch1);
        btnSearch1.setBounds(10, 370, 80, 30);
        jPanel2.add(jSeparator3);
        jSeparator3.setBounds(10, 530, 770, 10);

        btnImport2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnImport2.setForeground(new java.awt.Color(255, 153, 0));
        btnImport2.setText("Import");
        btnImport2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImport2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnImport2);
        btnImport2.setBounds(640, 480, 130, 40);

        labdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labdate.setText("Last Stock Take Date");
        jPanel2.add(labdate);
        labdate.setBounds(260, 0, 200, 30);

        txtDate.setEditable(false);
        txtDate.setBackground(new java.awt.Color(255, 255, 255));
        txtDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txtDate);
        txtDate.setBounds(260, 30, 200, 30);

        btnSearch2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch2.setForeground(new java.awt.Color(0, 102, 51));
        btnSearch2.setText("Stock Take List");
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch2);
        btnSearch2.setBounds(20, 540, 180, 30);

        btnImport3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnImport3.setForeground(new java.awt.Color(0, 153, 51));
        btnImport3.setText("Save");
        btnImport3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImport3ActionPerformed(evt);
            }
        });
        jPanel2.add(btnImport3);
        btnImport3.setBounds(610, 10, 140, 80);

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jCheckBox1.setText("Show Only Differeance Qty. Items");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox1);
        jCheckBox1.setBounds(20, 80, 260, 30);

        add(jPanel2);
        jPanel2.setBounds(0, 0, 790, 590);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        addToCart();
    }//GEN-LAST:event_btnSearchActionPerformed
    public void addToCart() {
        boolean check = true;
        int qty = 0;
        if (ddItems.getSelectedItem() == null || ddItems.getSelectedItem().toString().equalsIgnoreCase("select")) {
            check = false;
            JOptionPane.showMessageDialog(this, "Please select Item");
        } else if (txtQuantity.getText() == null || txtQuantity.getText().trim().length() == 0) {
            check = false;
            JOptionPane.showMessageDialog(this, "Quantity Requred");
        } else {
            try {
                qty = Integer.parseInt(txtQuantity.getText().toString());
                if (qty <= 0) {
                    check = false;
                    JOptionPane.showMessageDialog(this, "Quantity should be greater than 0");
                }
            } catch (Exception e) {
                check = false;
                JOptionPane.showMessageDialog(this, "Invalid Quantity");
            }
        }
        if (check) {
            StockTakAdj p = new StockTakAdj();

            String s[] = ddItems.getSelectedItem().toString().split("-");
            long itId = Long.parseLong(s[s.length - 1]);
            StockTakAdj ep = checkExist(itId);
            if (ep != null) {
                p.setItems(ep.getItems());
                p.setSrNo(ep.getSrNo());
                qty += ep.getStQuantity();
                removeItem(ep.getSrNo());
            } else {
                ItemsDao itmd = new ItemsDao();
                p.setItems(itmd.getItems(itId, null, null).get(0));
                p.setSrNo(srNo++);
            }
            p.setStQuantity(qty);
            p.setOpenBal(preStockItmes.containsKey(itId) ? preStockItmes.get(itId) : 0);
            p.setPurchases(allPurchases.containsKey(itId) ? allPurchases.get(itId) : 0);
            p.setSales(allSales.containsKey(itId) ? allSales.get(itId) : 0);
            p.setCloseBal(p.getOpenBal() + p.getPurchases() - p.getSales());
            p.setAdjQuantity(p.getStQuantity() - p.getCloseBal());
            stockTakAdj.add(p);
            txtQuantity.setText("0");
            ddItems.setSelectedItem("Select");
            showTableData();
        }
    }

    public StockTakAdj checkExist(long id) {
        for (StockTakAdj s : stockTakAdj) {
            if (s.getItems().getItemId() == id) {
                return s;
            }
        }
        return null;
    }

    private void ddBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ddBrandActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        clearAll();
    }//GEN-LAST:event_btnImportActionPerformed

    public void clearAll() {
        stockTakAdj = new ArrayList<>();
        srNo = 1;
        showTableData();
    }

    private void ddItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddItemsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ddItemsActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        if (tbPurItems.getSelectedColumnCount() > 0) {
            int sr = Integer.parseInt(tbPurItems.getValueAt(tbPurItems.getSelectedRow(), 0).toString());
            removeItem(sr);
            showTableData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select row for delete");
        }
    }//GEN-LAST:event_btnSearch1ActionPerformed
    public void removeItem(int sr) {
        for (int i = 0; i < stockTakAdj.size(); i++) {
            if (stockTakAdj.get(i).getSrNo() == sr) {
                stockTakAdj.remove(i);
                break;
            }
        }
    }

    private void btnImport2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImport2ActionPerformed
        pnlFilechosser.setVisible(true);
    }//GEN-LAST:event_btnImport2ActionPerformed

    private void ddBrandItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ddBrandItemStateChanged
        if (ddBrand.getSelectedItem() != null && !ddBrand.getSelectedItem().toString().equalsIgnoreCase("select")) {
            ddItems.setModel(new javax.swing.DefaultComboBoxModel(GeneralUtil.getItemeArray("Select", ddBrand.getSelectedItem().toString())));
        } else {
            ddItems.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Select"}));
        }
    }//GEN-LAST:event_ddBrandItemStateChanged

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed
        this.homePanel.openStockTakeList(menuName);
    }//GEN-LAST:event_btnSearch2ActionPerformed

    public void setRearks() {
        for (int i = 0; i < tbPurItems.getRowCount(); i++) {
            for (int j = 0; j < 9; j++) {
                System.out.println("srn : " + tbPurItems.getValueAt(i, j));
            }
        }
        for (int i = 0; i < tbPurItems.getRowCount(); i++) {
            int srn = Integer.parseInt(tbPurItems.getValueAt(i, 0).toString());
            Object rem = tbPurItems.getValueAt(i, 8);
            System.out.println("srn : " + srn);
            for (StockTakAdj s : stockTakAdj) {
                if (s.getSrNo() == srn) {
                    System.out.println("Rem : " + rem);
                    s.setRemarks(rem != null ? rem.toString() : "");
                    break;
                }
            }
        }
    }

    public boolean dateValidation() {
        if (fromDate.getDate().after(GeneralUtil.getCurrentDate())) {
            JOptionPane.showMessageDialog(this, "Date should not be future date");
            return false;
        } else if (preStockTake != null) {
            if (!fromDate.getDate().after(preStockTake.getStockTakedOn())) {
                JOptionPane.showMessageDialog(this, "Date should  be greater than last stock take date");
                return false;
            }
        }
        return true;
    }

    private void btnImport3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImport3ActionPerformed

        if (!stockTakAdj.isEmpty()) {
            if (dateValidation()) {
                setRearks();
                StockTake p = new StockTake();
                p.setPreviousSTId(preStockTake != null ? preStockTake.getStockTakeId() : 0);
                p.setStockTakedOn(fromDate.getDate());
                p.setCreatedBy(homePanel.getMainWindow().getUsers().getUserId());
                p.setStatus("active");
                p.setCreatedOn(new Date());
                p.setStockTakAdjs(stockTakAdj);
                StockTakeDao id = new StockTakeDao();
                String msg = id.saveStockTake(p);
                if (msg.equalsIgnoreCase("success")) {
                   
                    JOptionPane.showMessageDialog(this, "Saved Successfully");
                     this.homePanel.openStockTakeList(menuName);
                    clearAll();
                } else {
                    JOptionPane.showMessageDialog(this, msg);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please add Items");
        }


    }//GEN-LAST:event_btnImport3ActionPerformed

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed

        if (evt.getActionCommand().equalsIgnoreCase("ApproveSelection")) {
            if (jFileChooser1.getSelectedFile() != null) {
                System.err.println("File Path : " + jFileChooser1.getSelectedFile().getAbsolutePath());
                List<List<String>> data = null;
                String path = jFileChooser1.getSelectedFile().getAbsolutePath().toLowerCase();
                if (path.endsWith(".csv")) {
                    data = ReadCSV.readCSVFile(path);
                } else if (path.endsWith(".xls")) {
                    data = ExcelReadAndWrite.readXls(path);
                } else if (path.endsWith(".xlsx")) {
                    data = ExcelReadAndWrite.readXlsx(path);
                }
                if (data != null) {
                    if (!data.isEmpty() && data.size() > 1) {
                        ItemsDao id = new ItemsDao();
                        List<Items> ls = id.getItems(0, null, null);
                        Map<String, Items> itemMap = new HashMap<>();
                        for (Items it : ls) {
                            itemMap.put(it.getItemCode().toLowerCase(), it);
                        }
                        for (int i = 1; i < data.size(); i++) {
                            List<String> row = data.get(i);
                            if (itemMap.containsKey(row.get(0).trim().toLowerCase())) {
                                int qty = (int) Double.parseDouble(row.get(1));
                                StockTakAdj p = new StockTakAdj();
                                p.setItems(itemMap.get(row.get(0).trim().toLowerCase()));
                                long itId = p.getItems().getItemId();
                                StockTakAdj ep = checkExist(itId);
                                if (ep != null) {
                                    p.setSrNo(ep.getSrNo());
                                    qty += ep.getStQuantity();
                                    removeItem(ep.getSrNo());
                                } else {
                                    p.setSrNo(srNo++);
                                }
                                p.setStQuantity(qty);
                                p.setOpenBal(preStockItmes.containsKey(itId) ? preStockItmes.get(itId) : 0);
                                p.setPurchases(allPurchases.containsKey(itId) ? allPurchases.get(itId) : 0);
                                p.setSales(allSales.containsKey(itId) ? allSales.get(itId) : 0);
                                p.setCloseBal(p.getOpenBal() + p.getPurchases() - p.getSales());
                                p.setAdjQuantity(p.getStQuantity() - p.getCloseBal());
                                stockTakAdj.add(p);
                                txtQuantity.setText("0");
                                ddItems.setSelectedItem("Select");
                                showTableData();
                            }

                        }
                        showTableData();
                        pnlFilechosser.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "No Data for import");
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Please chose CSV, Xls or Xlsx file");
                }
            }
        } else {
            pnlFilechosser.setVisible(false);
        }
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        showTableData();
    }//GEN-LAST:event_jCheckBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnImport2;
    private javax.swing.JButton btnImport3;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JComboBox ddBrand;
    private javax.swing.JComboBox ddItems;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labdate;
    private javax.swing.JLabel lbDropDownVal;
    private javax.swing.JLabel lbDropDownVal1;
    private javax.swing.JLabel lbDropDownVal2;
    private javax.swing.JLabel lbTemplerHeaders1;
    private javax.swing.JDialog pnlFilechosser;
    private javax.swing.JTable tbPurItems;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables

}
