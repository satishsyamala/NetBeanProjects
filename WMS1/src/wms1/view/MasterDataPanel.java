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
import wms1.pojos.ServiceTypes;
import wms1.pojos.Users;
import wms1.util.ExcelReadAndWrite;

import wms1.util.GeneralUtil;
import wms1.util.ReadCSV;

/**
 *
 * @author SATISH SYAMALA
 */
public class MasterDataPanel extends javax.swing.JPanel {

    /**
     * Creates new form ProductPanel
     */
    private HomePanel homePanel;
    private String menuName;
    private Map<String, Object> fieldsMap = new HashMap<String, Object>();
    private Map<String, Object> fieldData = new HashMap<String, Object>();
    private JSONArray fieldArry = new JSONArray();
    SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
    int addstatus = 0;
    private long selectedId;

    public MasterDataPanel() {
        initComponents();
    }

    public void init(HomePanel homePanel, String menuName) {
        this.homePanel = homePanel;
        this.menuName = menuName;
        System.out.println(" menuName " + menuName);
        addProductPanel1.setVisible(false);
        pnlFilechosser1.setVisible(false);

        GeneralUtil.setDailBound(pnlFilechosser1, homePanel, 560, 400);
        setAddWindowBounds();
        filterData();
        featchData();

    }

    public void getFields(int action) {
        fieldsMap = new HashMap<String, Object>();
        pnlFields.removeAll();
        for (int i = 0; i < fieldArry.size(); i++) {
            JSONObject f = (JSONObject) fieldArry.get(i);
            pnlFields.add(GeneralUtil.getJLabel(f.get("name").toString(), 20, 20 + (i * 40), 150, 30));
            if (f.get("type").toString().equalsIgnoreCase("text") || f.get("type").toString().equalsIgnoreCase("number")) {
                JTextField t = GeneralUtil.getJTextField(addstatus == 0 ? "" : f.get("value").toString(), 170, 20 + (i * 40), 200, 30, f.get("datatype").toString());
                pnlFields.add(t);
                fieldsMap.put(f.get("name").toString(), t);
            } else if (f.get("type").toString().equalsIgnoreCase("dropdown")) {
                JComboBox t = GeneralUtil.getJComboBox(addstatus == 0 ? "" : f.get("value").toString(), 170, 20 + (i * 40), 200, 30, (JSONArray) f.get("values"));
                pnlFields.add(t);
                fieldsMap.put(f.get("name").toString(), t);
            } else if (f.get("type").toString().equalsIgnoreCase("date")) {
                JXDatePicker t = GeneralUtil.getJXDatePicker(addstatus == 0 ? "" : f.get("value").toString(), 170, 20 + (i * 40), 200, 30, null);
                pnlFields.add(t);
                fieldsMap.put(f.get("name").toString(), t);
            }
        }
        pnlFields.revalidate();
        pnlFields.repaint();
        addProductPanel1.repaint();
        addProductPanel1.setVisible(true);

    }

    public void setAddWindowBounds() {
        JSONArray aa = GeneralUtil.getFormByName(menuName);
        GeneralUtil.setDailBound(addProductPanel1, homePanel, 400, (aa.size() * 45) + 90);
        pnlFields.setBounds(0, 0, addProductPanel1.getWidth(), (aa.size() * 45));
        btnAddWindowClose.setBounds(30, pnlFields.getHeight() + 10, btnAddWindowClose.getWidth(), btnAddWindowClose.getHeight());
        btnAddWindowClear.setBounds(150, pnlFields.getHeight() + 10, btnAddWindowClose.getWidth(), btnAddWindowClose.getHeight());
        btnAddProduct.setBounds(270, pnlFields.getHeight() + 10, btnAddWindowClose.getWidth(), btnAddWindowClose.getHeight());

    }

    public void filterData() {
        ddBrand.setVisible(true);
        lbDropDownVal.setVisible(true);
        txtCodeOrName.setText("");
        ddBrand.setSelectedItem("All");
        String[] values = null;
        ddBrand.setVisible(true);
        jLabel6.setText("Code or Name");
        if (menuName.equalsIgnoreCase("Users")) {
            lbDropDownVal.setText("User Type");
            jLabel6.setText("User Id or Name");
            values = "All,Admin,Sales,Audit".split(",");
        } else if (menuName.equalsIgnoreCase("Brands")) {
            lbDropDownVal.setVisible(false);
            ddBrand.setVisible(false);
        } else if (menuName.equalsIgnoreCase("Items")) {
            lbDropDownVal.setText("Brands");
            ddBrand.setModel(new javax.swing.DefaultComboBoxModel(GeneralUtil.getBrandsArray("All")));
        } else if (menuName.equalsIgnoreCase("Service Type")) {
            lbDropDownVal.setVisible(false);
            ddBrand.setVisible(false);
        }
    }

    public void featchData() {

        if (menuName.equalsIgnoreCase("Users")) {
            lbDropDownVal.setText("User Type");
            LoginDao ld = new LoginDao();
            setTableDate(GeneralUtil.ListToStringA(ld.getUserDetails(0, null, txtCodeOrName.getText(), ddBrand.getSelectedItem().toString()), 6));
        } else if (menuName.equalsIgnoreCase("Brands")) {
            lbDropDownVal.setVisible(false);
            ddBrand.setVisible(false);
            BrandsDao bd = new BrandsDao();
            setTableDate(GeneralUtil.ListToStringA(bd.getBrandsDetails(0, null, null, txtCodeOrName.getText()), 3));
        } else if (menuName.equalsIgnoreCase("Items")) {
            ItemsDao bd = new ItemsDao();
            lbDropDownVal.setText("Brands");
            setTableDate(GeneralUtil.ListToStringA(bd.getItemsDetails(0, null, null, txtCodeOrName.getText(), ddBrand.getSelectedItem().toString()), 6));
        } else if (menuName.equalsIgnoreCase("Service Types")) {
            lbDropDownVal.setVisible(false);
            ddBrand.setVisible(false);
            BrandsDao bd = new BrandsDao();
            setTableDate(GeneralUtil.ListToStringA(bd.getServiceTypes(0, null, txtCodeOrName.getText()), 3));
        }
    }

    public void setTableDate(String[][] data) {
        String[] headers = GeneralUtil.getTableheaders(menuName);
        tbProductList.setModel(new javax.swing.table.DefaultTableModel(
                data, headers) {
        });
        tbProductList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbProductList);
        tbProductList.getColumnModel().getColumn(0).setPreferredWidth(30);
        if (menuName.equalsIgnoreCase("Items")) {
            tbProductList.getColumnModel().getColumn(3).setPreferredWidth(250);
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

        addProductPanel1 = new javax.swing.JDialog();
        pnlFields = new javax.swing.JPanel();
        btnAddProduct = new javax.swing.JButton();
        btnAddWindowClose = new javax.swing.JButton();
        btnAddWindowClear = new javax.swing.JButton();
        pnlFilechosser1 = new javax.swing.JDialog();
        jFileChooser1 = new javax.swing.JFileChooser();
        lbTemplerHeaders1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtCodeOrName = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lbDropDownVal = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProductList = new javax.swing.JTable();
        btnEditProduct = new javax.swing.JButton();
        btnDeleteProduct = new javax.swing.JButton();
        btnProductAdd = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        ddBrand = new javax.swing.JComboBox();
        btnImport = new javax.swing.JButton();
        btnImport1 = new javax.swing.JButton();
        btnImport2 = new javax.swing.JButton();

        addProductPanel1.setTitle(" Add ");
        addProductPanel1.setBackground(new java.awt.Color(255, 255, 255));
        addProductPanel1.setSize(new java.awt.Dimension(500, 500));
        addProductPanel1.setType(java.awt.Window.Type.POPUP);
        addProductPanel1.getContentPane().setLayout(null);

        pnlFields.setBackground(new java.awt.Color(255, 255, 255));
        pnlFields.setLayout(null);
        addProductPanel1.getContentPane().add(pnlFields);
        pnlFields.setBounds(0, 0, 500, 340);

        btnAddProduct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAddProduct.setForeground(new java.awt.Color(0, 153, 0));
        btnAddProduct.setText("ADD");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });
        addProductPanel1.getContentPane().add(btnAddProduct);
        btnAddProduct.setBounds(240, 350, 100, 30);

        btnAddWindowClose.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAddWindowClose.setForeground(new java.awt.Color(204, 0, 0));
        btnAddWindowClose.setText("CLOSE");
        btnAddWindowClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWindowCloseActionPerformed(evt);
            }
        });
        addProductPanel1.getContentPane().add(btnAddWindowClose);
        btnAddWindowClose.setBounds(20, 350, 100, 30);

        btnAddWindowClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAddWindowClear.setForeground(new java.awt.Color(0, 0, 204));
        btnAddWindowClear.setText("CLEAR");
        btnAddWindowClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWindowClearActionPerformed(evt);
            }
        });
        addProductPanel1.getContentPane().add(btnAddWindowClear);
        btnAddWindowClear.setBounds(130, 350, 100, 30);

        addProductPanel1.getAccessibleContext().setAccessibleParent(jPanel2);

        pnlFilechosser1.setTitle("Templet Headers (File :  CSV, XLS, XLSX)");
        pnlFilechosser1.setAutoRequestFocus(false);
        pnlFilechosser1.setSize(new java.awt.Dimension(600, 460));
        pnlFilechosser1.setType(java.awt.Window.Type.POPUP);
        pnlFilechosser1.getContentPane().setLayout(null);

        jFileChooser1.setSelectedFile(new java.io.File("C:\\Program Files\\NetBeans-12.2\\csv"));
        jFileChooser1.setToolTipText("");
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });
        pnlFilechosser1.getContentPane().add(jFileChooser1);
        jFileChooser1.setBounds(0, 30, 550, 320);

        lbTemplerHeaders1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTemplerHeaders1.setForeground(new java.awt.Color(0, 153, 255));
        lbTemplerHeaders1.setText("Templet Headers");
        pnlFilechosser1.getContentPane().add(lbTemplerHeaders1);
        lbTemplerHeaders1.setBounds(20, 0, 490, 30);

        setOpaque(false);
        setLayout(null);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);

        txtCodeOrName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txtCodeOrName);
        txtCodeOrName.setBounds(140, 10, 200, 30);

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(0, 204, 204));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch);
        btnSearch.setBounds(670, 10, 100, 30);

        lbDropDownVal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDropDownVal.setText("Select");
        jPanel2.add(lbDropDownVal);
        lbDropDownVal.setBounds(350, 10, 80, 30);
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(10, 520, 770, 10);

        tbProductList.setAutoCreateRowSorter(true);
        tbProductList.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbProductList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Phone Number", "Establishment Date", "Registration No"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tbProductList.setFocusCycleRoot(true);
        tbProductList.setOpaque(false);
        tbProductList.setRowHeight(25);
        tbProductList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbProductList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbProductList);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 50, 770, 450);

        btnEditProduct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEditProduct.setForeground(new java.awt.Color(51, 51, 255));
        btnEditProduct.setText("EDIT");
        btnEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProductActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditProduct);
        btnEditProduct.setBounds(550, 540, 100, 30);

        btnDeleteProduct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDeleteProduct.setForeground(new java.awt.Color(255, 0, 0));
        btnDeleteProduct.setText("DELETE");
        btnDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProductActionPerformed(evt);
            }
        });
        jPanel2.add(btnDeleteProduct);
        btnDeleteProduct.setBounds(670, 540, 100, 30);

        btnProductAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnProductAdd.setForeground(new java.awt.Color(0, 153, 51));
        btnProductAdd.setText("ADD");
        btnProductAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductAddActionPerformed(evt);
            }
        });
        jPanel2.add(btnProductAdd);
        btnProductAdd.setBounds(430, 540, 100, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Code or Name");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(10, 10, 130, 30);

        ddBrand.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ddBrand.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        ddBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddBrandActionPerformed(evt);
            }
        });
        jPanel2.add(ddBrand);
        ddBrand.setBounds(430, 10, 210, 30);

        btnImport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnImport.setForeground(new java.awt.Color(255, 153, 51));
        btnImport.setText("Import");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });
        jPanel2.add(btnImport);
        btnImport.setBounds(310, 540, 100, 30);

        btnImport1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnImport1.setForeground(new java.awt.Color(0, 102, 0));
        btnImport1.setText("CSV");
        btnImport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImport1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnImport1);
        btnImport1.setBounds(110, 540, 80, 30);

        btnImport2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnImport2.setForeground(new java.awt.Color(153, 0, 153));
        btnImport2.setText("Excel");
        btnImport2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImport2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnImport2);
        btnImport2.setBounds(20, 540, 80, 30);

        add(jPanel2);
        jPanel2.setBounds(0, 0, 790, 590);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddWindowClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWindowClearActionPerformed
        getFields(0);
}//GEN-LAST:event_btnAddWindowClearActionPerformed

    private void btnAddWindowCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWindowCloseActionPerformed
        jScrollPane1.setVisible(true);
        addProductPanel1.setVisible(false);
}//GEN-LAST:event_btnAddWindowCloseActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        readDate();
}//GEN-LAST:event_btnAddProductActionPerformed

    private void btnEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProductActionPerformed
        selectedId = 0;
        if (tbProductList.getSelectedColumnCount() > 0) {
            Map<String, Object> key = null;
            addstatus = 1;
            addProductPanel1.setTitle("  Edit " + menuName);
            if (menuName.equalsIgnoreCase("Users")) {
                LoginDao ld = new LoginDao();
                selectedId = Long.parseLong(tbProductList.getValueAt(tbProductList.getSelectedRow(), 0).toString());
                key = ld.getUser(selectedId, null).get(0).getKeyValues();
            } else if (menuName.equalsIgnoreCase("Brands")) {
                BrandsDao bd = new BrandsDao();
                selectedId = Long.parseLong(tbProductList.getValueAt(tbProductList.getSelectedRow(), 0).toString());
                key = bd.getBrands(selectedId, null, null).get(0).getKeyValues();
            } else if (menuName.equalsIgnoreCase("Items")) {
                ItemsDao bd = new ItemsDao();
                selectedId = Long.parseLong(tbProductList.getValueAt(tbProductList.getSelectedRow(), 0).toString());
                key = bd.getItems(selectedId, null, null).get(0).getKeyValues();
            } else if (menuName.equalsIgnoreCase("Service Types")) {
                BrandsDao bd = new BrandsDao();
                selectedId = Long.parseLong(tbProductList.getValueAt(tbProductList.getSelectedRow(), 0).toString());
                key = bd.getServiceTypes(selectedId, null).get(0).getKeyValues();
            }
            fieldArry = GeneralUtil.getFormByName(menuName);
            if (key != null) {
                for (int i = 0; i < fieldArry.size(); i++) {
                    JSONObject o = (JSONObject) fieldArry.get(i);
                    o.put("value", key.get(o.get("name").toString()));
                }
            }
            getFields(addstatus);
        } else {
            JOptionPane.showMessageDialog(this, "Please select row for edit");
        }
}//GEN-LAST:event_btnEditProductActionPerformed

    private void btnDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductActionPerformed

}//GEN-LAST:event_btnDeleteProductActionPerformed

    private void btnProductAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductAddActionPerformed
        addstatus = 0;
        addProductPanel1.setTitle("  Add " + menuName);
        fieldArry = GeneralUtil.getFormByName(menuName);
        getFields(addstatus);
}//GEN-LAST:event_btnProductAddActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        featchData();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void ddBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ddBrandActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        if (menuName.equalsIgnoreCase("users")) {
            lbTemplerHeaders1.setText("User Full Name, User Id, Password, Mobile Number, User Type");
        } else if (menuName.equalsIgnoreCase("Brands")) {
            lbTemplerHeaders1.setText("Code, Name");
        } else if (menuName.equalsIgnoreCase("Items")) {
            lbTemplerHeaders1.setText("Brand, Code, Name, Bar Code,Price");
        } else if (menuName.equalsIgnoreCase("Service Types")) {
            lbTemplerHeaders1.setText("Type, Price");
        }
        pnlFilechosser1.setVisible(true);
    }//GEN-LAST:event_btnImportActionPerformed

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed

        if (evt.getActionCommand().equalsIgnoreCase("ApproveSelection")) {
            if (jFileChooser1.getSelectedFile() != null) {
                importDate(jFileChooser1.getSelectedFile().getAbsolutePath());
            }
        } else {
            pnlFilechosser1.setVisible(false);
        }
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void btnImport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImport1ActionPerformed
        List<Object[]> ls = null;
        String headers = "";
        String[] headersa = GeneralUtil.getTableheaders(menuName);
        for (String s : headersa) {
            headers += s + ",";
        }
        headers = headers.substring(0, headers.length() - 1);
        if (menuName.equalsIgnoreCase("Users")) {
            LoginDao ld = new LoginDao();
            ls = ld.getUserDetails(0, null, txtCodeOrName.getText(), ddBrand.getSelectedItem().toString());
        } else if (menuName.equalsIgnoreCase("Brands")) {

            BrandsDao bd = new BrandsDao();
            ls = bd.getBrandsDetails(0, null, null, txtCodeOrName.getText());
        } else if (menuName.equalsIgnoreCase("Items")) {

            ItemsDao bd = new ItemsDao();
            ls = bd.getItemsDetails(0, null, null, txtCodeOrName.getText(), ddBrand.getSelectedItem().toString());
        } else if (menuName.equalsIgnoreCase("Service Types")) {

            BrandsDao bd = new BrandsDao();
            ls = bd.getServiceTypes(0, null, txtCodeOrName.getText());
        }
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
    }//GEN-LAST:event_btnImport1ActionPerformed

    private void btnImport2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImport2ActionPerformed

        List<Object[]> ls = null;
        List<String> headers = new ArrayList<>();
        String[] headersa = GeneralUtil.getTableheaders(menuName);
        for (String s : headersa) {
            headers.add(s);
        }
        if (menuName.equalsIgnoreCase("Users")) {
            LoginDao ld = new LoginDao();
            ls = ld.getUserDetails(0, null, txtCodeOrName.getText(), ddBrand.getSelectedItem().toString());
        } else if (menuName.equalsIgnoreCase("Brands")) {

            BrandsDao bd = new BrandsDao();
            ls = bd.getBrandsDetails(0, null, null, txtCodeOrName.getText());
        } else if (menuName.equalsIgnoreCase("Items")) {

            ItemsDao bd = new ItemsDao();
            ls = bd.getItemsDetails(0, null, null, txtCodeOrName.getText(), ddBrand.getSelectedItem().toString());
        } else if (menuName.equalsIgnoreCase("Service Types")) {

            BrandsDao bd = new BrandsDao();
            ls = bd.getServiceTypes(0, null, txtCodeOrName.getText());
        }
        String msg = ExcelReadAndWrite.writeXlsx(headers, ls, menuName, null);
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

    }//GEN-LAST:event_btnImport2ActionPerformed

    public void importDate(String path) {
        List<List<String>> data = null;
        if (path.endsWith(".csv")) {
            data = ReadCSV.readCSVFile(path);
        } else if (path.endsWith(".xls")) {
            data = ExcelReadAndWrite.readXls(path);
        } else if (path.endsWith(".xlsx")) {
            data = ExcelReadAndWrite.readXlsx(path);
        }
        if (data != null) {
            String msg = "";
            if (!data.isEmpty() && data.size() > 1) {
                List<String> headers = data.get(0);
                System.err.println("headers : " + headers);
                if (menuName.equalsIgnoreCase("users")) {
                    LoginDao ld = new LoginDao();
                    for (int i = 1; i < data.size(); i++) {
                        List<String> row = data.get(i);
                        Users u = new Users();
                        u.setFullName(row.get(0));
                        u.setUserName(row.get(1));
                        u.setPassword(row.get(2));
                        u.setMobileNo(row.get(3));
                        u.setUserType(row.get(4).toLowerCase());
                        u.setCreatedBy(homePanel.getMainWindow().getUsers().getUserId());
                        u.setStatus("active");
                        u.setCreatedOn(new Date());
                        msg = ld.saveUsers(u);
                    }
                } else if (menuName.equalsIgnoreCase("Brands")) {
                    BrandsDao bd = new BrandsDao();
                    for (int i = 0; i < data.size(); i++) {
                        List<String> row = data.get(i);
                        System.err.println("row : " + row);
                        Brands b = new Brands();
                        b.setBrandCode(row.get(0));
                        b.setBrandName(row.get(1));
                        b.setCreatedBy(homePanel.getMainWindow().getUsers().getUserId());
                        b.setStatus("active");
                        b.setCreatedOn(new Date());
                        msg = bd.saveBrand(b);
                    }

                } else if (menuName.equalsIgnoreCase("Items")) {
                    ItemsDao id = new ItemsDao();
                    BrandsDao bd = new BrandsDao();
                    for (int i = 0; i < data.size(); i++) {
                        List<String> row = data.get(i);
                        Items b = new Items();
                        String brand = row.get(0);
                        List<Brands> bl = bd.getBrands(0, null, brand);
                        if (!bl.isEmpty()) {
                            Brands brd = bl.get(0);
                            b.setBrandId(brd);
                            b.setItemCode(row.get(1));
                            b.setItemName(row.get(2));
                            b.setBarCode(row.get(3));
                            b.setPrice(Double.parseDouble(row.get(4)));
                            b.setCreatedBy(homePanel.getMainWindow().getUsers().getUserId());
                            b.setStatus("active");
                            b.setCreatedOn(new Date());
                            msg = id.saveItems(b);
                        }
                    }
                } else if (menuName.equalsIgnoreCase("Service Types")) {
                    BrandsDao bd = new BrandsDao();
                    for (int i = 0; i < data.size(); i++) {
                        List<String> row = data.get(i);
                        System.err.println("row : " + row);
                        ServiceTypes b = new ServiceTypes();
                        b.setServiceType(row.get(0));
                        b.setPrice(Double.parseDouble(row.get(1)));
                        b.setCreatedBy(homePanel.getMainWindow().getUsers().getUserId());
                        b.setStatus("active");
                        b.setCreatedOn(new Date());
                        msg = bd.saveServiceTypes(b);
                    }

                }
                if (msg.equalsIgnoreCase("success")) {
                    JOptionPane.showMessageDialog(this, msg);
                    pnlFilechosser1.setVisible(false);

                    featchData();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please chose CSV, Xls or Xlsx file");
            }
        }
    }

    public void readDate() {
        for (String s : fieldsMap.keySet()) {
            if (fieldsMap.get(s) instanceof JTextField) {
                fieldData.put(s, ((JTextField) fieldsMap.get(s)).getText());
            }
            if (fieldsMap.get(s) instanceof JComboBox) {
                fieldData.put(s, ((JComboBox) fieldsMap.get(s)).getSelectedItem());
            }
            if (fieldsMap.get(s) instanceof JXDatePicker) {
                fieldData.put(s, ((JXDatePicker) fieldsMap.get(s)).getDate());
            }
        }
        System.out.println(fieldData);
        String msg = "";
        if (menuName.equalsIgnoreCase("users")) {
            Users u = new Users();
            u.setUserName(fieldData.get("User Id").toString());
            u.setFullName(fieldData.get("User Full Name").toString());
            u.setPassword(fieldData.get("Password").toString());
            u.setMobileNo(fieldData.get("Mobile Number").toString());
            u.setUserType(fieldData.get("User Type").toString());
            u.setCreatedBy(homePanel.getMainWindow().getUsers().getUserId());
            u.setStatus("active");
            u.setCreatedOn(new Date());
            LoginDao ld = new LoginDao();
            if (addstatus == 0) {
                msg = ld.saveUsers(u);
            } else {
                u.setUserId(selectedId);
                u.setUpdatedBy(homePanel.getMainWindow().getUsers().getUserId());
                u.setUpdatedOn(new Date());
                msg = ld.editUsers(u);
            }

        } else if (menuName.equalsIgnoreCase("Brands")) {
            Brands b = new Brands();
            b.setBrandCode(fieldData.get("Code").toString());
            b.setBrandName(fieldData.get("Name").toString());
            b.setCreatedBy(homePanel.getMainWindow().getUsers().getUserId());
            b.setStatus("active");
            b.setCreatedOn(new Date());
            BrandsDao bd = new BrandsDao();
            if (addstatus == 0) {
                msg = bd.saveBrand(b);
            } else {
                b.setBrandId(selectedId);
                b.setUpdatedBy(homePanel.getMainWindow().getUsers().getUserId());
                b.setUpdatedOn(new Date());
                msg = bd.updateBrand(b);
            }

        } else if (menuName.equalsIgnoreCase("Items")) {
            Items b = new Items();
            BrandsDao bd = new BrandsDao();
            String brand = fieldData.get("Brand").toString();
            Brands brd = bd.getBrands(0, null, brand).get(0);
            b.setBrandId(brd);
            b.setItemCode(fieldData.get("Code").toString());
            b.setItemName(fieldData.get("Name").toString());
            b.setBarCode(fieldData.get("Bar Code").toString());
            b.setPrice(Double.parseDouble(fieldData.get("Price").toString()));
            b.setCreatedBy(homePanel.getMainWindow().getUsers().getUserId());
            b.setStatus("active");
            b.setCreatedOn(new Date());
            ItemsDao id = new ItemsDao();
            if (addstatus == 0) {
                msg = id.saveItems(b);
            } else {
                b.setItemId(selectedId);
                b.setUpdatedBy(homePanel.getMainWindow().getUsers().getUserId());
                b.setUpdatedOn(new Date());
                msg = id.updateItems(b);
            }

        } else if (menuName.equalsIgnoreCase("Service Types")) {
            ServiceTypes b = new ServiceTypes();
            b.setServiceType(fieldData.get("Type").toString());
            b.setPrice(Double.parseDouble(fieldData.get("Price").toString()));
            b.setCreatedBy(homePanel.getMainWindow().getUsers().getUserId());
            b.setStatus("active");
            b.setCreatedOn(new Date());
            BrandsDao bd = new BrandsDao();
            if (addstatus == 0) {
                msg = bd.saveServiceTypes(b);
            } else {
                b.setServiceTypeId(selectedId);
                b.setUpdatedBy(homePanel.getMainWindow().getUsers().getUserId());
                b.setUpdatedOn(new Date());
                msg = bd.updateServiceTypes(b);
            }

        }
        if (msg.equalsIgnoreCase("success")) {
            jScrollPane1.setVisible(true);
            addProductPanel1.setVisible(false);
            featchData();
        } else {
            JOptionPane.showMessageDialog(this, msg);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog addProductPanel1;
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnAddWindowClear;
    private javax.swing.JButton btnAddWindowClose;
    private javax.swing.JButton btnDeleteProduct;
    private javax.swing.JButton btnEditProduct;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnImport1;
    private javax.swing.JButton btnImport2;
    private javax.swing.JButton btnProductAdd;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox ddBrand;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbDropDownVal;
    private javax.swing.JLabel lbTemplerHeaders1;
    private javax.swing.JPanel pnlFields;
    private javax.swing.JDialog pnlFilechosser1;
    private javax.swing.JTable tbProductList;
    private javax.swing.JTextField txtCodeOrName;
    // End of variables declaration//GEN-END:variables

}
