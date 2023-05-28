/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CompanyPanel.java
 *
 * Created on Jul 27, 2014, 6:47:47 AM
 */
package com.hes.view;

import com.hes.model.CompanyModel;
import com.hes.pojo.Company;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author SATISH SYAMALA
 */
public class CompanyPanel extends javax.swing.JPanel {

    /** Creates new form CompanyPanel */
    private HomePanel homePanel;
    private CompanyModel companyModel = new CompanyModel();
    private List<Company> companys = null;
    JXDatePicker estDate = new JXDatePicker();
    SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
    int addstatus = 0;

    public CompanyPanel() {
        initComponents();
        estDate.setFormats("dd-MM-yyyy HH:mm");
        estDate.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        estDate.setBounds(180, 390, 220, 20);
        addCompanyPanel.add(estDate);

    }

    public void init(HomePanel homePanel) {
        this.homePanel = homePanel;
        estDate.setDate(new Date());
        companys = new ArrayList<Company>();
        addCompanyPanel.setVisible(false);
        cmpnyViewPanel.setVisible(false);
        addstatus = 0;
        setCompanyDate();
        clearFields();
    }

    public void clearFields() {
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
        estDate.setDate(new Date());
    }

    public void setCompanyDate() {
        companys = companyModel.getCompayList();
        String[][] data = new String[companys.size()][5];
        for (int i = 0; i < companys.size(); i++) {
            Company cm = companys.get(i);
            data[i][0] = (i + 1) + "";
            data[i][1] = cm.getName();
            data[i][2] = cm.getPhnNumber();
            data[i][3] = cm.getTinNumber();
            data[i][4] = cm.getCstNumber();
        }
        setTableDate(data);
    }

    public void setTableDate(String[][] data) {
        tbCmpnyList.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{
                    "Sr.No", "Name", "Phone Number", "TIN Numebr", "C.S.T Number"
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
        tbCmpnyList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbCmpnyList);
        tbCmpnyList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbCmpnyList.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbCmpnyList.getColumnModel().getColumn(1).setPreferredWidth(350);
        tbCmpnyList.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbCmpnyList.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbCmpnyList.getColumnModel().getColumn(4).setPreferredWidth(100);

    }

    public void addCompany() {
        if (txtName.getText().trim().length() > 0 && txtAddress.getText().trim().length() > 0 && txtTinNumber.getText().trim().length() > 0 && txtPhoneNumber.getText().trim().length() > 0 && txtDivision.getText().trim().length() > 0) {
            Company cm = new Company();
            cm.setAddress(txtAddress.getText());
            cm.setCerNo(txtCERNumber.getText());
            cm.setCommissionerate(txtCommissionerate.getText());
            cm.setCreatedOn(estDate.getDate());
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
            String msg = companyModel.addCompany(cm, addstatus);
            if (msg.equals("success")) {
                jScrollPane1.setVisible(true);
                setCompanyDate();
                addCompanyPanel.setVisible(false);
            } else {
                if (msg.startsWith("YES")) {
                    int check = JOptionPane.showConfirmDialog(addCompanyPanel, "Company name already exist in Inactive Status. Do you want to activate!");
                    if (check == 0) {
                        addstatus = Integer.parseInt(msg.replaceAll("YES", ""));
                        addCompany();
                    } else {
                        jScrollPane1.setVisible(true);
                        setCompanyDate();
                        addCompanyPanel.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(addCompanyPanel, msg);
                }

            }


        } else {
            JOptionPane.showMessageDialog(addCompanyPanel, "All fields required !");
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

        jButton4 = new javax.swing.JButton();
        addCompanyPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTinNumber = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtDivision = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        clearAddBtn = new javax.swing.JButton();
        closeAddwnBtn = new javax.swing.JButton();
        addNewCmpBtn = new javax.swing.JButton();
        txtLandLine = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCommissionerate = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtRangeAddress = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCSTNumber = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCERNumber = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtECCNumber = new javax.swing.JTextField();
        txtDebitEntryNumber = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cmpnyViewPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtViewPn = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCmpnyList = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        cmpEditBtn = new javax.swing.JButton();
        cmpViewBtn = new javax.swing.JButton();
        cmpDeleteBtn = new javax.swing.JButton();
        cmpAddBtn = new javax.swing.JButton();

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton4.setForeground(new java.awt.Color(0, 0, 204));
        jButton4.setText("EDIT");

        setOpaque(false);
        setLayout(null);

        addCompanyPanel.setBackground(new java.awt.Color(255, 255, 255));
        addCompanyPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        addCompanyPanel.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(0, 0, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("  Add Company");
        jLabel2.setOpaque(true);
        addCompanyPanel.add(jLabel2);
        jLabel2.setBounds(0, 0, 770, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Address");
        addCompanyPanel.add(jLabel3);
        jLabel3.setBounds(50, 100, 130, 25);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("TIN Number");
        addCompanyPanel.add(jLabel4);
        jLabel4.setBounds(50, 230, 130, 25);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("Phone Number");
        addCompanyPanel.add(jLabel5);
        jLabel5.setBounds(50, 270, 130, 25);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("Land Line No.");
        addCompanyPanel.add(jLabel6);
        jLabel6.setBounds(50, 310, 130, 25);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setText("Established Date");
        addCompanyPanel.add(jLabel7);
        jLabel7.setBounds(50, 390, 130, 25);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("Company Name");
        addCompanyPanel.add(jLabel8);
        jLabel8.setBounds(50, 60, 130, 25);

        txtTinNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCompanyPanel.add(txtTinNumber);
        txtTinNumber.setBounds(180, 230, 190, 25);

        txtPhoneNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCompanyPanel.add(txtPhoneNumber);
        txtPhoneNumber.setBounds(180, 270, 190, 25);

        txtName.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCompanyPanel.add(txtName);
        txtName.setBounds(180, 60, 190, 25);

        txtDivision.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCompanyPanel.add(txtDivision);
        txtDivision.setBounds(180, 350, 190, 25);

        txtAddress.setColumns(20);
        txtAddress.setFont(new java.awt.Font("Monospaced", 1, 12));
        txtAddress.setRows(5);
        jScrollPane2.setViewportView(txtAddress);

        addCompanyPanel.add(jScrollPane2);
        jScrollPane2.setBounds(180, 100, 190, 110);

        clearAddBtn.setFont(new java.awt.Font("Tahoma", 1, 14));
        clearAddBtn.setForeground(new java.awt.Color(0, 0, 204));
        clearAddBtn.setText("CLEAR");
        clearAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAddBtnActionPerformed(evt);
            }
        });
        addCompanyPanel.add(clearAddBtn);
        clearAddBtn.setBounds(310, 440, 100, 30);

        closeAddwnBtn.setFont(new java.awt.Font("Tahoma", 1, 14));
        closeAddwnBtn.setForeground(new java.awt.Color(0, 0, 204));
        closeAddwnBtn.setText("CLOSE");
        closeAddwnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeAddwnBtnActionPerformed(evt);
            }
        });
        addCompanyPanel.add(closeAddwnBtn);
        closeAddwnBtn.setBounds(180, 440, 100, 30);

        addNewCmpBtn.setFont(new java.awt.Font("Tahoma", 1, 14));
        addNewCmpBtn.setForeground(new java.awt.Color(0, 0, 204));
        addNewCmpBtn.setText("ADD");
        addNewCmpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewCmpBtnActionPerformed(evt);
            }
        });
        addCompanyPanel.add(addNewCmpBtn);
        addNewCmpBtn.setBounds(440, 440, 100, 30);

        txtLandLine.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCompanyPanel.add(txtLandLine);
        txtLandLine.setBounds(180, 310, 190, 25);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel10.setText("Commissionerate");
        addCompanyPanel.add(jLabel10);
        jLabel10.setBounds(400, 60, 130, 25);

        txtCommissionerate.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCompanyPanel.add(txtCommissionerate);
        txtCommissionerate.setBounds(530, 60, 190, 25);

        txtRangeAddress.setColumns(20);
        txtRangeAddress.setFont(new java.awt.Font("Monospaced", 1, 12));
        txtRangeAddress.setRows(5);
        jScrollPane4.setViewportView(txtRangeAddress);

        addCompanyPanel.add(jScrollPane4);
        jScrollPane4.setBounds(530, 100, 190, 110);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel11.setText("Range Address");
        addCompanyPanel.add(jLabel11);
        jLabel11.setBounds(400, 100, 130, 25);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel12.setText("C.S.T Number");
        addCompanyPanel.add(jLabel12);
        jLabel12.setBounds(400, 230, 130, 25);

        txtCSTNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCompanyPanel.add(txtCSTNumber);
        txtCSTNumber.setBounds(530, 230, 190, 25);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel13.setText("G.I.R Number");
        addCompanyPanel.add(jLabel13);
        jLabel13.setBounds(400, 270, 130, 25);

        txtCERNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCompanyPanel.add(txtCERNumber);
        txtCERNumber.setBounds(530, 270, 190, 25);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel14.setText("E.C.C Number");
        addCompanyPanel.add(jLabel14);
        jLabel14.setBounds(400, 310, 130, 25);

        txtECCNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCompanyPanel.add(txtECCNumber);
        txtECCNumber.setBounds(530, 310, 190, 25);

        txtDebitEntryNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCompanyPanel.add(txtDebitEntryNumber);
        txtDebitEntryNumber.setBounds(530, 350, 190, 25);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel15.setText("Debit Entry No.");
        addCompanyPanel.add(jLabel15);
        jLabel15.setBounds(400, 350, 130, 25);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel17.setText("Division");
        addCompanyPanel.add(jLabel17);
        jLabel17.setBounds(50, 350, 130, 25);

        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        addCompanyPanel.add(jLabel16);
        jLabel16.setBounds(30, 40, 710, 390);

        add(addCompanyPanel);
        addCompanyPanel.setBounds(10, 40, 770, 490);

        cmpnyViewPanel.setBackground(new java.awt.Color(255, 255, 255));
        cmpnyViewPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        cmpnyViewPanel.setLayout(null);

        jLabel9.setBackground(new java.awt.Color(0, 0, 204));
        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 14));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("  Company Delails");
        jLabel9.setOpaque(true);
        cmpnyViewPanel.add(jLabel9);
        jLabel9.setBounds(0, 0, 680, 30);

        txtViewPn.setContentType("text/html");
        jScrollPane3.setViewportView(txtViewPn);

        cmpnyViewPanel.add(jScrollPane3);
        jScrollPane3.setBounds(10, 40, 660, 370);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton1.setForeground(new java.awt.Color(0, 0, 255));
        jButton1.setText("CLOSE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        cmpnyViewPanel.add(jButton1);
        jButton1.setBounds(570, 420, 100, 30);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton2.setForeground(new java.awt.Color(0, 0, 255));
        jButton2.setText("PRINT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        cmpnyViewPanel.add(jButton2);
        jButton2.setBounds(40, 420, 100, 30);

        add(cmpnyViewPanel);
        cmpnyViewPanel.setBounds(60, 50, 680, 460);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14));
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("Company List");
        add(jLabel1);
        jLabel1.setBounds(20, 20, 290, 20);
        add(jSeparator1);
        jSeparator1.setBounds(10, 520, 770, 10);

        tbCmpnyList.setAutoCreateRowSorter(true);
        tbCmpnyList.setFont(new java.awt.Font("Tahoma", 0, 14));
        tbCmpnyList.setModel(new javax.swing.table.DefaultTableModel(
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
        tbCmpnyList.setOpaque(false);
        tbCmpnyList.setRowHeight(25);
        tbCmpnyList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbCmpnyList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbCmpnyList);
        tbCmpnyList.getColumnModel().getColumn(0).setPreferredWidth(5);
        tbCmpnyList.getColumnModel().getColumn(1).setPreferredWidth(350);
        tbCmpnyList.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbCmpnyList.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbCmpnyList.getColumnModel().getColumn(4).setPreferredWidth(100);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 70, 770, 430);
        add(jSeparator2);
        jSeparator2.setBounds(10, 50, 770, 10);

        cmpEditBtn.setFont(new java.awt.Font("Tahoma", 1, 14));
        cmpEditBtn.setForeground(new java.awt.Color(0, 0, 204));
        cmpEditBtn.setText("EDIT");
        cmpEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmpEditBtnActionPerformed(evt);
            }
        });
        add(cmpEditBtn);
        cmpEditBtn.setBounds(430, 540, 100, 30);

        cmpViewBtn.setFont(new java.awt.Font("Tahoma", 1, 14));
        cmpViewBtn.setForeground(new java.awt.Color(0, 0, 204));
        cmpViewBtn.setText("VIEW");
        cmpViewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmpViewBtnActionPerformed(evt);
            }
        });
        add(cmpViewBtn);
        cmpViewBtn.setBounds(550, 540, 100, 30);

        cmpDeleteBtn.setFont(new java.awt.Font("Tahoma", 1, 14));
        cmpDeleteBtn.setForeground(new java.awt.Color(0, 0, 204));
        cmpDeleteBtn.setText("DELETE");
        cmpDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmpDeleteBtnActionPerformed(evt);
            }
        });
        add(cmpDeleteBtn);
        cmpDeleteBtn.setBounds(670, 540, 100, 30);

        cmpAddBtn.setFont(new java.awt.Font("Tahoma", 1, 14));
        cmpAddBtn.setForeground(new java.awt.Color(0, 0, 204));
        cmpAddBtn.setText("ADD");
        cmpAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmpAddBtnActionPerformed(evt);
            }
        });
        add(cmpAddBtn);
        cmpAddBtn.setBounds(310, 540, 100, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void cmpViewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmpViewBtnActionPerformed
        if (tbCmpnyList.getSelectedRowCount() > 0) {
            Company cm = companys.get(tbCmpnyList.getSelectedRow());
            String msg = companyModel.viewCompanyInfo(cm);
            jScrollPane1.setVisible(false);
            cmpnyViewPanel.setVisible(true);
            txtViewPn.setText(msg);
        } else {
            JOptionPane.showMessageDialog(addCompanyPanel, "Please select company !");
        }
    }//GEN-LAST:event_cmpViewBtnActionPerformed

    private void cmpDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmpDeleteBtnActionPerformed
        if (tbCmpnyList.getSelectedRowCount() > 0) {
            int check = JOptionPane.showConfirmDialog(addCompanyPanel, "Do you want to delete !");
            if (check == 0) {
                Company cm = companys.get(tbCmpnyList.getSelectedRow());
                String msg = companyModel.deleteCompany(cm.getId());
                if (msg.equals("success")) {
                    setCompanyDate();
                } else {
                    JOptionPane.showMessageDialog(addCompanyPanel, msg);
                }
            }
        } else {
            JOptionPane.showMessageDialog(addCompanyPanel, "Please select company !");
        }
    }//GEN-LAST:event_cmpDeleteBtnActionPerformed

    private void cmpAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmpAddBtnActionPerformed
        addstatus = 0;
        clearFields();
        jScrollPane1.setVisible(false);
        addCompanyPanel.setVisible(true);
    }//GEN-LAST:event_cmpAddBtnActionPerformed

    private void closeAddwnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeAddwnBtnActionPerformed
        jScrollPane1.setVisible(true);
        addCompanyPanel.setVisible(false);
    }//GEN-LAST:event_closeAddwnBtnActionPerformed

    private void addNewCmpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewCmpBtnActionPerformed
        addCompany();
    }//GEN-LAST:event_addNewCmpBtnActionPerformed

    private void clearAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAddBtnActionPerformed
        clearFields();
    }//GEN-LAST:event_clearAddBtnActionPerformed

    private void cmpEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmpEditBtnActionPerformed
        if (tbCmpnyList.getSelectedRowCount() > 0) {
            Company cm = companys.get(tbCmpnyList.getSelectedRow());
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
            estDate.setDate(cm.getCreatedOn());
            addstatus = cm.getId();
            jScrollPane1.setVisible(false);
            addCompanyPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(addCompanyPanel, "Please select company !");
        }
    }//GEN-LAST:event_cmpEditBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jScrollPane1.setVisible(true);
        cmpnyViewPanel.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            txtViewPn.print();
            
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(addCompanyPanel, "No Printer Connection");
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addCompanyPanel;
    private javax.swing.JButton addNewCmpBtn;
    private javax.swing.JButton clearAddBtn;
    private javax.swing.JButton closeAddwnBtn;
    private javax.swing.JButton cmpAddBtn;
    private javax.swing.JButton cmpDeleteBtn;
    private javax.swing.JButton cmpEditBtn;
    private javax.swing.JButton cmpViewBtn;
    private javax.swing.JPanel cmpnyViewPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tbCmpnyList;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtCERNumber;
    private javax.swing.JTextField txtCSTNumber;
    private javax.swing.JTextField txtCommissionerate;
    private javax.swing.JTextField txtDebitEntryNumber;
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
