/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SupplierPanel.java
 *
 * Created on Jul 27, 2014, 10:18:27 AM
 */
package com.hes.view;

import com.hes.model.SupplierModel;
import com.hes.pojo.Supplier;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author SATISH SYAMALA
 */
public class SupplierPanel extends javax.swing.JPanel {

    private HomePanel homePanel;
    private SupplierModel supplierModel = new SupplierModel();
    private List<Supplier> suppliers = null;
    SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
    int addstatus = 0;

    /** Creates new form SupplierPanel */
    public SupplierPanel() {
        initComponents();
    }

    public void init(HomePanel homePanel) {
        this.homePanel = homePanel;
        txtDiscount.setText("0.0");
        suppliers = new ArrayList<Supplier>();
        addSupplierPanel.setVisible(false);
        supplierViewPanel.setVisible(false);
        addstatus = 0;
        setCompanyDate();
        clearFields();
    }

    public void setCompanyDate() {
        suppliers = supplierModel.getSupplierList();
        String[][] data = new String[suppliers.size()][5];
        for (int i = 0; i < suppliers.size(); i++) {
            Supplier cm = suppliers.get(i);
            data[i][0] = (i + 1) + "";
            data[i][1] = cm.getName();
            data[i][2] = cm.getPhnNumber();
            data[i][3] = cm.getDiscount() + "";
            data[i][4] = cm.getTinNumber();
        }
        setTableDate(data);
    }

    public void setTableDate(String[][] data) {
        tbSupplierList.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{
                    "Sr.No", "Name", "Phone Number", "DisCount", "TIN Number"
                }) {

            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbSupplierList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbSupplierList);
        tbSupplierList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbSupplierList.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbSupplierList.getColumnModel().getColumn(1).setPreferredWidth(350);
        tbSupplierList.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbSupplierList.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbSupplierList.getColumnModel().getColumn(4).setPreferredWidth(100);

    }
    public void clearFields()
    {
        txtName.setText("");
        txtAddress.setText("");
        txtTinNumber.setText("");
        txtDivision.setText("");
        txtPhoneNumber.setText("");
        txtCERNumber.setText("");
        txtCSTNumber.setText("");
        txtCommissionerate.setText("");
        txtDebitEntryNumber.setText("");
        txtECCNumber.setText("");
        txtLandLine.setText("");
        txtRangeAddress.setText("");
        txtDiscount.setText("0.0");

    }

    public void addCompany() {
        if (txtName.getText().trim().length() > 0 && txtAddress.getText().trim().length() > 0) {
            Supplier cm = new Supplier();
            cm.setAddress(txtAddress.getText());
            cm.setCerNo(txtCERNumber.getText());
            cm.setCommissionerate(txtCommissionerate.getText());
            cm.setCreatedOn(new Date());
            cm.setCstNumber(txtCSTNumber.getText());
            cm.setDebitEntryNo(txtDebitEntryNumber.getText());
            cm.setDiscription("");
            cm.setDivision(txtDivision.getText());
            cm.setEccNo(txtECCNumber.getText());
            cm.setLandLine(txtLandLine.getText());
            cm.setName(txtName.getText());
            cm.setPhnNumber(txtPhoneNumber.getText());
            cm.setRangeAddress(txtRangeAddress.getText());
            cm.setStatus("active");
            cm.setTinNumber(txtTinNumber.getText());
            cm.setDiscount(Double.parseDouble(txtDiscount.getText()));
            String msg = supplierModel.addSupplier(cm, addstatus);
            if (msg.equals("success")) {
                jScrollPane1.setVisible(true);
                setCompanyDate();
                addSupplierPanel.setVisible(false);
            } else {
                if (msg.startsWith("YES")) {
                    int check = JOptionPane.showConfirmDialog(addSupplierPanel, "Supplier name already exist in Inactive Status. Do you want to activate!");
                    if (check == 0) {
                        addstatus = Integer.parseInt(msg.replaceAll("YES", ""));
                        addCompany();
                    } else {
                        jScrollPane1.setVisible(true);
                        setCompanyDate();
                        addSupplierPanel.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(addSupplierPanel, msg);
                }

            }


        } else {
            JOptionPane.showMessageDialog(addSupplierPanel, "All fields required !");
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

        jPanel1 = new javax.swing.JPanel();
        addSupplierPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        clearAddBtn = new javax.swing.JButton();
        closeAddwnBtn = new javax.swing.JButton();
        addNewSuppBtn = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtLandLine = new javax.swing.JTextField();
        txtDiscount = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTinNumber = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCommissionerate = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtRangeAddress = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCSTNumber = new javax.swing.JTextField();
        txtCERNumber = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDebitEntryNumber = new javax.swing.JTextField();
        txtECCNumber = new javax.swing.JTextField();
        txtDivision = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        supplierViewPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtViewPn = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSupplierList = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        btnSupplierEdit = new javax.swing.JButton();
        btnSupplierView = new javax.swing.JButton();
        btnSupplierDelete = new javax.swing.JButton();
        btnSupplierAdd = new javax.swing.JButton();

        setOpaque(false);
        setLayout(null);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        addSupplierPanel.setBackground(new java.awt.Color(255, 255, 255));
        addSupplierPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        addSupplierPanel.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(0, 0, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("  Add Supplier");
        jLabel2.setOpaque(true);
        addSupplierPanel.add(jLabel2);
        jLabel2.setBounds(0, 0, 790, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("Supplier Name");
        addSupplierPanel.add(jLabel8);
        jLabel8.setBounds(50, 60, 130, 25);

        clearAddBtn.setFont(new java.awt.Font("Tahoma", 1, 14));
        clearAddBtn.setForeground(new java.awt.Color(0, 0, 204));
        clearAddBtn.setText("CLEAR");
        clearAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAddBtnActionPerformed(evt);
            }
        });
        addSupplierPanel.add(clearAddBtn);
        clearAddBtn.setBounds(350, 460, 100, 30);

        closeAddwnBtn.setFont(new java.awt.Font("Tahoma", 1, 14));
        closeAddwnBtn.setForeground(new java.awt.Color(0, 0, 204));
        closeAddwnBtn.setText("CLOSE");
        closeAddwnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeAddwnBtnActionPerformed(evt);
            }
        });
        addSupplierPanel.add(closeAddwnBtn);
        closeAddwnBtn.setBounds(220, 460, 100, 30);

        addNewSuppBtn.setFont(new java.awt.Font("Tahoma", 1, 14));
        addNewSuppBtn.setForeground(new java.awt.Color(0, 0, 204));
        addNewSuppBtn.setText("ADD");
        addNewSuppBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewSuppBtnActionPerformed(evt);
            }
        });
        addSupplierPanel.add(addNewSuppBtn);
        addNewSuppBtn.setBounds(480, 460, 100, 30);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel17.setText("Division");
        addSupplierPanel.add(jLabel17);
        jLabel17.setBounds(50, 350, 130, 25);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("Land Line No.");
        addSupplierPanel.add(jLabel6);
        jLabel6.setBounds(50, 310, 130, 25);

        txtLandLine.setFont(new java.awt.Font("Tahoma", 3, 12));
        addSupplierPanel.add(txtLandLine);
        txtLandLine.setBounds(180, 310, 190, 25);

        txtDiscount.setFont(new java.awt.Font("Tahoma", 3, 12));
        addSupplierPanel.add(txtDiscount);
        txtDiscount.setBounds(180, 390, 190, 25);

        txtPhoneNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addSupplierPanel.add(txtPhoneNumber);
        txtPhoneNumber.setBounds(180, 270, 190, 25);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("Phone Number");
        addSupplierPanel.add(jLabel5);
        jLabel5.setBounds(50, 270, 130, 25);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("TIN Number");
        addSupplierPanel.add(jLabel4);
        jLabel4.setBounds(50, 230, 130, 25);

        txtTinNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addSupplierPanel.add(txtTinNumber);
        txtTinNumber.setBounds(180, 230, 190, 25);

        txtAddress.setColumns(20);
        txtAddress.setFont(new java.awt.Font("Monospaced", 1, 12));
        txtAddress.setRows(5);
        jScrollPane2.setViewportView(txtAddress);

        addSupplierPanel.add(jScrollPane2);
        jScrollPane2.setBounds(180, 100, 190, 110);

        txtName.setFont(new java.awt.Font("Tahoma", 3, 12));
        addSupplierPanel.add(txtName);
        txtName.setBounds(180, 60, 190, 25);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Address");
        addSupplierPanel.add(jLabel3);
        jLabel3.setBounds(50, 100, 130, 25);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel10.setText("Commissionerate");
        addSupplierPanel.add(jLabel10);
        jLabel10.setBounds(400, 60, 130, 25);

        txtCommissionerate.setFont(new java.awt.Font("Tahoma", 3, 12));
        addSupplierPanel.add(txtCommissionerate);
        txtCommissionerate.setBounds(530, 60, 190, 25);

        txtRangeAddress.setColumns(20);
        txtRangeAddress.setFont(new java.awt.Font("Monospaced", 1, 12));
        txtRangeAddress.setRows(5);
        jScrollPane4.setViewportView(txtRangeAddress);

        addSupplierPanel.add(jScrollPane4);
        jScrollPane4.setBounds(530, 100, 190, 110);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel11.setText("Range Address");
        addSupplierPanel.add(jLabel11);
        jLabel11.setBounds(400, 100, 130, 25);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel12.setText("C.S.T Number");
        addSupplierPanel.add(jLabel12);
        jLabel12.setBounds(400, 230, 130, 25);

        txtCSTNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addSupplierPanel.add(txtCSTNumber);
        txtCSTNumber.setBounds(530, 230, 190, 25);

        txtCERNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addSupplierPanel.add(txtCERNumber);
        txtCERNumber.setBounds(530, 270, 190, 25);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel13.setText("Reg Number");
        addSupplierPanel.add(jLabel13);
        jLabel13.setBounds(400, 270, 130, 25);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel14.setText("E.C.C Number");
        addSupplierPanel.add(jLabel14);
        jLabel14.setBounds(400, 310, 130, 25);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel15.setText("Debit Entry No.");
        addSupplierPanel.add(jLabel15);
        jLabel15.setBounds(400, 350, 130, 25);

        txtDebitEntryNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addSupplierPanel.add(txtDebitEntryNumber);
        txtDebitEntryNumber.setBounds(530, 350, 190, 25);

        txtECCNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addSupplierPanel.add(txtECCNumber);
        txtECCNumber.setBounds(530, 310, 190, 25);

        txtDivision.setFont(new java.awt.Font("Tahoma", 3, 12));
        addSupplierPanel.add(txtDivision);
        txtDivision.setBounds(180, 350, 190, 25);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setText("Discount");
        addSupplierPanel.add(jLabel7);
        jLabel7.setBounds(50, 390, 130, 25);

        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        addSupplierPanel.add(jLabel16);
        jLabel16.setBounds(30, 40, 710, 390);

        jPanel1.add(addSupplierPanel);
        addSupplierPanel.setBounds(0, 10, 780, 520);

        supplierViewPanel.setBackground(new java.awt.Color(255, 255, 255));
        supplierViewPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        supplierViewPanel.setLayout(null);

        jLabel9.setBackground(new java.awt.Color(0, 0, 204));
        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 14));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("  Supplier Delails");
        jLabel9.setOpaque(true);
        supplierViewPanel.add(jLabel9);
        jLabel9.setBounds(0, 0, 680, 30);

        txtViewPn.setContentType("text/html");
        jScrollPane3.setViewportView(txtViewPn);

        supplierViewPanel.add(jScrollPane3);
        jScrollPane3.setBounds(10, 40, 660, 370);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton1.setForeground(new java.awt.Color(0, 0, 255));
        jButton1.setText("CLOSE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        supplierViewPanel.add(jButton1);
        jButton1.setBounds(570, 420, 100, 30);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton2.setForeground(new java.awt.Color(0, 0, 255));
        jButton2.setText("PRINT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        supplierViewPanel.add(jButton2);
        jButton2.setBounds(40, 420, 100, 30);

        jPanel1.add(supplierViewPanel);
        supplierViewPanel.setBounds(60, 50, 680, 460);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14));
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText(" Supplier List");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 20, 290, 20);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(10, 520, 770, 10);

        tbSupplierList.setAutoCreateRowSorter(true);
        tbSupplierList.setFont(new java.awt.Font("Tahoma", 0, 14));
        tbSupplierList.setModel(new javax.swing.table.DefaultTableModel(
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
        tbSupplierList.setOpaque(false);
        tbSupplierList.setRowHeight(25);
        tbSupplierList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbSupplierList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbSupplierList);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 70, 770, 430);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(10, 50, 770, 10);

        btnSupplierEdit.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnSupplierEdit.setForeground(new java.awt.Color(0, 0, 204));
        btnSupplierEdit.setText("EDIT");
        btnSupplierEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierEditActionPerformed(evt);
            }
        });
        jPanel1.add(btnSupplierEdit);
        btnSupplierEdit.setBounds(430, 540, 100, 30);

        btnSupplierView.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnSupplierView.setForeground(new java.awt.Color(0, 0, 204));
        btnSupplierView.setText("VIEW");
        btnSupplierView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierViewActionPerformed(evt);
            }
        });
        jPanel1.add(btnSupplierView);
        btnSupplierView.setBounds(550, 540, 100, 30);

        btnSupplierDelete.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnSupplierDelete.setForeground(new java.awt.Color(0, 0, 204));
        btnSupplierDelete.setText("DELETE");
        btnSupplierDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnSupplierDelete);
        btnSupplierDelete.setBounds(670, 540, 100, 30);

        btnSupplierAdd.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnSupplierAdd.setForeground(new java.awt.Color(0, 0, 204));
        btnSupplierAdd.setText("ADD");
        btnSupplierAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnSupplierAdd);
        btnSupplierAdd.setBounds(310, 540, 100, 30);

        add(jPanel1);
        jPanel1.setBounds(0, 0, 792, 592);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       jScrollPane1.setVisible(true);
        supplierViewPanel.setVisible(false);
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            txtViewPn.print();
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(addSupplierPanel, "No Printer Connection");
        }
}//GEN-LAST:event_jButton2ActionPerformed

    private void clearAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAddBtnActionPerformed
        txtName.setText("");
        txtAddress.setText("");
        txtTinNumber.setText("");
        txtDivision.setText("");
        txtPhoneNumber.setText("");
        txtCERNumber.setText("");
        txtCSTNumber.setText("");
        txtCommissionerate.setText("");
        txtDebitEntryNumber.setText("");
        txtECCNumber.setText("");
        txtLandLine.setText("");
        txtRangeAddress.setText("");
        txtDiscount.setText("0.0");
}//GEN-LAST:event_clearAddBtnActionPerformed

    private void closeAddwnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeAddwnBtnActionPerformed
         clearFields();
       jScrollPane1.setVisible(true);
         addSupplierPanel.setVisible(false);
}//GEN-LAST:event_closeAddwnBtnActionPerformed

    private void addNewSuppBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewSuppBtnActionPerformed
        addCompany();
}//GEN-LAST:event_addNewSuppBtnActionPerformed

    private void btnSupplierEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierEditActionPerformed
        if (tbSupplierList.getSelectedRowCount() > 0) {
            Supplier cm = suppliers.get(tbSupplierList.getSelectedRow());
            txtName.setText(cm.getName());
            txtAddress.setText(cm.getAddress());
            txtTinNumber.setText(cm.getTinNumber());
            txtDivision.setText(cm.getDivision());
            txtPhoneNumber.setText(cm.getPhnNumber());
            txtCERNumber.setText(cm.getCerNo());
            txtCSTNumber.setText(cm.getCstNumber());
            txtCommissionerate.setText(cm.getCommissionerate());
            txtDebitEntryNumber.setText(cm.getDebitEntryNo());
            txtECCNumber.setText(cm.getEccNo());
            txtLandLine.setText(cm.getLandLine());
            txtRangeAddress.setText(cm.getRangeAddress());
            txtDiscount.setText(cm.getDiscount()+"");
            addstatus = cm.getId();
            jScrollPane1.setVisible(false);
            addSupplierPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(addSupplierPanel, "Please select Supplier !");
        }
}//GEN-LAST:event_btnSupplierEditActionPerformed

    private void btnSupplierViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierViewActionPerformed
        if (tbSupplierList.getSelectedRowCount() > 0) {
            Supplier cm = suppliers.get(tbSupplierList.getSelectedRow());
            String msg = supplierModel.viewSupplierInfo(cm);
            jScrollPane1.setVisible(false);
            supplierViewPanel.setVisible(true);
            txtViewPn.setText(msg);
        } else {
            JOptionPane.showMessageDialog(addSupplierPanel, "Please select Suppliear !");
        }
}//GEN-LAST:event_btnSupplierViewActionPerformed

    private void btnSupplierDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierDeleteActionPerformed
        if (tbSupplierList.getSelectedRowCount() > 0) {
             int check=JOptionPane.showConfirmDialog(addSupplierPanel, "Do you want to delete !");
            if(check==0)
            {
            Supplier cm = suppliers.get(tbSupplierList.getSelectedRow());
            String msg = supplierModel.deleteSupplier(cm.getId());
            if (msg.equals("success")) {
                setCompanyDate();
            } else {
                JOptionPane.showMessageDialog(addSupplierPanel, msg);
            }
            }
        } else {
            JOptionPane.showMessageDialog(addSupplierPanel, "Please select company !");
        }
}//GEN-LAST:event_btnSupplierDeleteActionPerformed

    private void btnSupplierAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierAddActionPerformed
        addstatus = 0;
        jScrollPane1.setVisible(false);
        addSupplierPanel.setVisible(true);
}//GEN-LAST:event_btnSupplierAddActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewSuppBtn;
    private javax.swing.JPanel addSupplierPanel;
    private javax.swing.JButton btnSupplierAdd;
    private javax.swing.JButton btnSupplierDelete;
    private javax.swing.JButton btnSupplierEdit;
    private javax.swing.JButton btnSupplierView;
    private javax.swing.JButton clearAddBtn;
    private javax.swing.JButton closeAddwnBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel supplierViewPanel;
    private javax.swing.JTable tbSupplierList;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtCERNumber;
    private javax.swing.JTextField txtCSTNumber;
    private javax.swing.JTextField txtCommissionerate;
    private javax.swing.JTextField txtDebitEntryNumber;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtDivision;
    private javax.swing.JTextField txtECCNumber;
    private javax.swing.JTextField txtLandLine;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextArea txtRangeAddress;
    private javax.swing.JTextField txtTinNumber;
    private javax.swing.JTextPane txtViewPn;
    // End of variables declaration//GEN-END:variables
}
