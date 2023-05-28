/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CustomerPanel.java
 *
 * Created on Jul 27, 2014, 6:40:11 PM
 */
package com.hes.view;

import com.hes.model.CustomerModel;
import com.hes.pojo.Customer;
import com.hes.pojo.Product;
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
public class CustomerPanel extends javax.swing.JPanel {

    /** Creates new form CustomerPanel */
    private HomePanel homePanel;
    private CustomerModel customerModel = new CustomerModel();
    private List<Customer> customers = null;
    SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
    List<Product> productArray = null;
    List<Product> selectedProductArray = null;
    int addstatus = 0;

    public CustomerPanel() {
        initComponents();
    }

    public void init(HomePanel homePanel) {
        this.homePanel = homePanel;
        productArray = new ArrayList<Product>();
        selectedProductArray = new ArrayList<Product>();
        customers = new ArrayList<Customer>();
        addCustomerPanel.setVisible(false);
        customerViewPanel.setVisible(false);
        txtDiscount.setText("0.0");
        addstatus = 0;
        setCompanyDate();
        addProductList();
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
        txtDiscount.setText("0.0");

    }

    public void addProductList() {
        String[] data = new String[productArray.size()];
        for (int i = 0; i < productArray.size(); i++) {
            Product p = productArray.get(i);
            data[i] = p.getProductName();
        }
        String[] data1 = new String[selectedProductArray.size()];
        for (int i = 0; i < selectedProductArray.size(); i++) {
            Product p = selectedProductArray.get(i);
            data1[i] = p.getProductName();

        }
        setProductList(data);
        setSelectedProductList(data1);

    }

    public void setCompanyDate() {
        customers = customerModel.getCustomerList();
        String[][] data = new String[customers.size()][6];
        for (int i = 0; i < customers.size(); i++) {
            Customer cm = customers.get(i);
            data[i][0] = (i + 1) + "";
            data[i][1] = cm.getName();
            data[i][2] = cm.getDivision();
             data[i][3] = cm.getCommissionerate();
            data[i][4] = cm.getDiscount() + "";
            data[i][5] = cm.getTinNumber();
        }
        setTableDate(data);
    }

    public void setTableDate(String[][] data) {
        tbCustomerList.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{
                    "Sr.No", "Name", "Division","Commissionerate", "DisCount", "TIN Number"
                }) {

            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbCustomerList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbCustomerList);
        tbCustomerList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbCustomerList.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbCustomerList.getColumnModel().getColumn(1).setPreferredWidth(250);
        tbCustomerList.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbCustomerList.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbCustomerList.getColumnModel().getColumn(4).setPreferredWidth(100);
         tbCustomerList.getColumnModel().getColumn(5).setPreferredWidth(100);

    }

    public void addCompany() {
        if (txtName.getText().trim().length() > 0 && txtAddress.getText().trim().length() > 0 && txtDiscount.getText().trim().length() > 0) {
            Customer cm = new Customer();
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
            String msg = customerModel.addCustomer(cm, addstatus, selectedProductArray);
            if (msg.equals("success")) {
                jScrollPane1.setVisible(true);
                setCompanyDate();
                addCustomerPanel.setVisible(false);
            } else {
                if (msg.startsWith("YES")) {
                    int check = JOptionPane.showConfirmDialog(addCustomerPanel, "Customer name already exist in Inactive Status. Do you want to activate!");
                    if (check == 0) {
                        addstatus = Integer.parseInt(msg.replaceAll("YES", ""));
                        addCompany();
                    } else {
                        jScrollPane1.setVisible(true);
                        setCompanyDate();
                        addCustomerPanel.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(addCustomerPanel, msg);
                }

            }

        } else {
            JOptionPane.showMessageDialog(addCustomerPanel, "All fields required !");
        }
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addCustomerPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        clearAddBtn = new javax.swing.JButton();
        closeAddwnBtn = new javax.swing.JButton();
        addNewCustBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnAddItems = new javax.swing.JButton();
        btnRemoveItems = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        selectedProducts = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        allProductsList = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtCommissionerate = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        txtDivision = new javax.swing.JTextField();
        txtLandLine = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        txtTinNumber = new javax.swing.JTextField();
        txtCSTNumber = new javax.swing.JTextField();
        txtCERNumber = new javax.swing.JTextField();
        txtECCNumber = new javax.swing.JTextField();
        txtDebitEntryNumber = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtRangeAddress = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        customerViewPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtViewPn = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCustomerList = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        btnCustomerEdit = new javax.swing.JButton();
        btnCustomerView = new javax.swing.JButton();
        btnCustomerDelete = new javax.swing.JButton();
        btnCustomerAdd = new javax.swing.JButton();

        setOpaque(false);
        setLayout(null);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        addCustomerPanel.setBackground(new java.awt.Color(255, 255, 255));
        addCustomerPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        addCustomerPanel.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(0, 0, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("  Add Customer");
        jLabel2.setOpaque(true);
        addCustomerPanel.add(jLabel2);
        jLabel2.setBounds(0, 0, 790, 30);

        clearAddBtn.setFont(new java.awt.Font("Tahoma", 1, 14));
        clearAddBtn.setForeground(new java.awt.Color(0, 0, 204));
        clearAddBtn.setText("CLEAR");
        clearAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAddBtnActionPerformed(evt);
            }
        });
        addCustomerPanel.add(clearAddBtn);
        clearAddBtn.setBounds(520, 460, 100, 30);

        closeAddwnBtn.setFont(new java.awt.Font("Tahoma", 1, 14));
        closeAddwnBtn.setForeground(new java.awt.Color(0, 0, 204));
        closeAddwnBtn.setText("CLOSE");
        closeAddwnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeAddwnBtnActionPerformed(evt);
            }
        });
        addCustomerPanel.add(closeAddwnBtn);
        closeAddwnBtn.setBounds(390, 460, 100, 30);

        addNewCustBtn.setFont(new java.awt.Font("Tahoma", 1, 14));
        addNewCustBtn.setForeground(new java.awt.Color(0, 0, 204));
        addNewCustBtn.setText("ADD");
        addNewCustBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewCustBtnActionPerformed(evt);
            }
        });
        addCustomerPanel.add(addNewCustBtn);
        addNewCustBtn.setBounds(650, 460, 100, 30);

        jLabel11.setBackground(new java.awt.Color(0, 153, 0));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Products");
        jLabel11.setOpaque(true);
        addCustomerPanel.add(jLabel11);
        jLabel11.setBounds(380, 300, 160, 20);

        jLabel10.setBackground(new java.awt.Color(0, 153, 0));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Customer Products");
        jLabel10.setOpaque(true);
        addCustomerPanel.add(jLabel10);
        jLabel10.setBounds(610, 300, 160, 20);

        btnAddItems.setText(">>");
        btnAddItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemsActionPerformed(evt);
            }
        });
        addCustomerPanel.add(btnAddItems);
        btnAddItems.setBounds(550, 350, 50, 23);

        btnRemoveItems.setText("<<");
        btnRemoveItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveItemsActionPerformed(evt);
            }
        });
        addCustomerPanel.add(btnRemoveItems);
        btnRemoveItems.setBounds(550, 390, 50, 23);

        selectedProducts.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        selectedProducts.setVisibleRowCount(20);
        jScrollPane5.setViewportView(selectedProducts);

        addCustomerPanel.add(jScrollPane5);
        jScrollPane5.setBounds(610, 320, 160, 130);

        allProductsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        allProductsList.setVisibleRowCount(20);
        jScrollPane6.setViewportView(allProductsList);

        addCustomerPanel.add(jScrollPane6);
        jScrollPane6.setBounds(380, 320, 160, 130);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("Customer Name");
        addCustomerPanel.add(jLabel8);
        jLabel8.setBounds(30, 40, 130, 25);

        txtName.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCustomerPanel.add(txtName);
        txtName.setBounds(160, 40, 190, 25);

        txtCommissionerate.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCustomerPanel.add(txtCommissionerate);
        txtCommissionerate.setBounds(510, 40, 190, 25);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel12.setText("Range Address");
        addCustomerPanel.add(jLabel12);
        jLabel12.setBounds(380, 80, 130, 25);

        txtAddress.setColumns(20);
        txtAddress.setFont(new java.awt.Font("Monospaced", 1, 12));
        txtAddress.setRows(5);
        jScrollPane2.setViewportView(txtAddress);

        addCustomerPanel.add(jScrollPane2);
        jScrollPane2.setBounds(160, 80, 190, 110);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Address");
        addCustomerPanel.add(jLabel3);
        jLabel3.setBounds(30, 80, 130, 25);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("TIN Number");
        addCustomerPanel.add(jLabel4);
        jLabel4.setBounds(30, 210, 130, 25);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("Phone Number");
        addCustomerPanel.add(jLabel5);
        jLabel5.setBounds(30, 250, 130, 25);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("Land Line No.");
        addCustomerPanel.add(jLabel6);
        jLabel6.setBounds(30, 290, 130, 25);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel17.setText("Division");
        addCustomerPanel.add(jLabel17);
        jLabel17.setBounds(30, 330, 130, 25);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setText("Discount");
        addCustomerPanel.add(jLabel7);
        jLabel7.setBounds(30, 370, 130, 25);

        txtDiscount.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCustomerPanel.add(txtDiscount);
        txtDiscount.setBounds(160, 370, 190, 25);

        txtDivision.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCustomerPanel.add(txtDivision);
        txtDivision.setBounds(160, 330, 190, 25);

        txtLandLine.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCustomerPanel.add(txtLandLine);
        txtLandLine.setBounds(160, 290, 190, 25);

        txtPhoneNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCustomerPanel.add(txtPhoneNumber);
        txtPhoneNumber.setBounds(160, 250, 190, 25);

        txtTinNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCustomerPanel.add(txtTinNumber);
        txtTinNumber.setBounds(160, 210, 190, 25);

        txtCSTNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCustomerPanel.add(txtCSTNumber);
        txtCSTNumber.setBounds(510, 210, 190, 25);

        txtCERNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCustomerPanel.add(txtCERNumber);
        txtCERNumber.setBounds(510, 250, 190, 25);

        txtECCNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCustomerPanel.add(txtECCNumber);
        txtECCNumber.setBounds(160, 410, 190, 25);

        txtDebitEntryNumber.setFont(new java.awt.Font("Tahoma", 3, 12));
        addCustomerPanel.add(txtDebitEntryNumber);
        txtDebitEntryNumber.setBounds(160, 450, 190, 25);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel15.setText("Debit Entry No.");
        addCustomerPanel.add(jLabel15);
        jLabel15.setBounds(30, 450, 130, 25);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel14.setText("E.C.C Number");
        addCustomerPanel.add(jLabel14);
        jLabel14.setBounds(30, 410, 130, 25);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel13.setText("C.E.R Number");
        addCustomerPanel.add(jLabel13);
        jLabel13.setBounds(380, 250, 130, 25);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel16.setText("C.S.T Number");
        addCustomerPanel.add(jLabel16);
        jLabel16.setBounds(380, 210, 130, 25);

        txtRangeAddress.setColumns(20);
        txtRangeAddress.setFont(new java.awt.Font("Monospaced", 1, 12));
        txtRangeAddress.setRows(5);
        jScrollPane4.setViewportView(txtRangeAddress);

        addCustomerPanel.add(jScrollPane4);
        jScrollPane4.setBounds(510, 80, 190, 110);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel18.setText("Commissionerate");
        addCustomerPanel.add(jLabel18);
        jLabel18.setBounds(380, 40, 130, 25);

        jPanel1.add(addCustomerPanel);
        addCustomerPanel.setBounds(0, 20, 790, 510);

        customerViewPanel.setBackground(new java.awt.Color(255, 255, 255));
        customerViewPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        customerViewPanel.setLayout(null);

        jLabel9.setBackground(new java.awt.Color(0, 0, 204));
        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 14));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("  Customer Delails");
        jLabel9.setOpaque(true);
        customerViewPanel.add(jLabel9);
        jLabel9.setBounds(0, 0, 680, 30);

        txtViewPn.setContentType("text/html");
        jScrollPane3.setViewportView(txtViewPn);

        customerViewPanel.add(jScrollPane3);
        jScrollPane3.setBounds(10, 40, 660, 370);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton1.setForeground(new java.awt.Color(0, 0, 255));
        jButton1.setText("CLOSE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        customerViewPanel.add(jButton1);
        jButton1.setBounds(570, 420, 100, 30);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton2.setForeground(new java.awt.Color(0, 0, 255));
        jButton2.setText("PRINT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        customerViewPanel.add(jButton2);
        jButton2.setBounds(40, 420, 100, 30);

        jPanel1.add(customerViewPanel);
        customerViewPanel.setBounds(60, 50, 680, 460);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14));
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText(" Customer List");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 20, 290, 20);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(10, 520, 770, 10);

        tbCustomerList.setAutoCreateRowSorter(true);
        tbCustomerList.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbCustomerList.setModel(new javax.swing.table.DefaultTableModel(
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
        tbCustomerList.setOpaque(false);
        tbCustomerList.setRowHeight(25);
        tbCustomerList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbCustomerList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbCustomerList);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 70, 770, 430);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(10, 50, 770, 10);

        btnCustomerEdit.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnCustomerEdit.setForeground(new java.awt.Color(0, 0, 204));
        btnCustomerEdit.setText("EDIT");
        btnCustomerEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerEditActionPerformed(evt);
            }
        });
        jPanel1.add(btnCustomerEdit);
        btnCustomerEdit.setBounds(430, 540, 100, 30);

        btnCustomerView.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnCustomerView.setForeground(new java.awt.Color(0, 0, 204));
        btnCustomerView.setText("VIEW");
        btnCustomerView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerViewActionPerformed(evt);
            }
        });
        jPanel1.add(btnCustomerView);
        btnCustomerView.setBounds(550, 540, 100, 30);

        btnCustomerDelete.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnCustomerDelete.setForeground(new java.awt.Color(0, 0, 204));
        btnCustomerDelete.setText("DELETE");
        btnCustomerDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnCustomerDelete);
        btnCustomerDelete.setBounds(670, 540, 100, 30);

        btnCustomerAdd.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnCustomerAdd.setForeground(new java.awt.Color(0, 0, 204));
        btnCustomerAdd.setText("ADD");
        btnCustomerAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnCustomerAdd);
        btnCustomerAdd.setBounds(310, 540, 100, 30);

        add(jPanel1);
        jPanel1.setBounds(0, 0, 792, 592);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jScrollPane1.setVisible(true);
        customerViewPanel.setVisible(false);
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            txtViewPn.print();
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(addCustomerPanel, "No Printer Connection");
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
        jScrollPane1.setVisible(true);
        addCustomerPanel.setVisible(false);
}//GEN-LAST:event_closeAddwnBtnActionPerformed

    private void addNewCustBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewCustBtnActionPerformed
        addCompany();
}//GEN-LAST:event_addNewCustBtnActionPerformed

    private void btnCustomerEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerEditActionPerformed
        if (tbCustomerList.getSelectedRowCount() > 0) {
            Customer cm = customers.get(tbCustomerList.getSelectedRow());
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
            txtDiscount.setText(cm.getDiscount() + "");
            addstatus = cm.getId();
            productArray = customerModel.getNotCustomerProducts(cm.getName());
            selectedProductArray = customerModel.getCustomerProducts(cm.getName());
            jScrollPane1.setVisible(false);
            addProductList();
            addCustomerPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(addCustomerPanel, "Please select Supplier !");
        }
}//GEN-LAST:event_btnCustomerEditActionPerformed

    private void btnCustomerViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerViewActionPerformed
        if (tbCustomerList.getSelectedRowCount() > 0) {
            Customer cm = customers.get(tbCustomerList.getSelectedRow());
            String msg = customerModel.viewCustomerInfo(cm);
            jScrollPane1.setVisible(false);
            customerViewPanel.setVisible(true);
            txtViewPn.setText(msg);
        } else {
            JOptionPane.showMessageDialog(addCustomerPanel, "Please select Suppliear !");
        }
}//GEN-LAST:event_btnCustomerViewActionPerformed

    private void btnCustomerDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerDeleteActionPerformed
        if (tbCustomerList.getSelectedRowCount() > 0) {
            int check = JOptionPane.showConfirmDialog(addCustomerPanel, "Do you want to delete !");
            if (check == 0) {
                Customer cm = customers.get(tbCustomerList.getSelectedRow());
                String msg = customerModel.deleteCustomer(cm.getId());
                if (msg.equals("success")) {
                    setCompanyDate();
                } else {
                    JOptionPane.showMessageDialog(addCustomerPanel, msg);
                }
            }
        } else {
            JOptionPane.showMessageDialog(addCustomerPanel, "Please select company !");
        }
}//GEN-LAST:event_btnCustomerDeleteActionPerformed

    private void btnCustomerAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerAddActionPerformed
        productArray = customerModel.getProductList("");
        selectedProductArray = new ArrayList<Product>();
        addProductList();
        addstatus = 0;
        clearFields();
        jScrollPane1.setVisible(false);
        addCustomerPanel.setVisible(true);
}//GEN-LAST:event_btnCustomerAddActionPerformed

    private void btnAddItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemsActionPerformed
        int k = 0;
        for (int i = 0; i < allProductsList.getSelectedIndices().length; i++) {
            int index = (allProductsList.getSelectedIndices()[i] - k);
            selectedProductArray.add(productArray.get(index));
            productArray.remove(index);
            k++;
        }
        addProductList();
    }//GEN-LAST:event_btnAddItemsActionPerformed

    private void btnRemoveItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveItemsActionPerformed
        int k = 0;
        for (int i = 0; i < selectedProducts.getSelectedIndices().length; i++) {
            int index = (selectedProducts.getSelectedIndices()[i] - k);
            productArray.add(selectedProductArray.get(index));
            selectedProductArray.remove(index);
            k++;

        }
        addProductList();
    }//GEN-LAST:event_btnRemoveItemsActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addCustomerPanel;
    private javax.swing.JButton addNewCustBtn;
    private javax.swing.JList allProductsList;
    private javax.swing.JButton btnAddItems;
    private javax.swing.JButton btnCustomerAdd;
    private javax.swing.JButton btnCustomerDelete;
    private javax.swing.JButton btnCustomerEdit;
    private javax.swing.JButton btnCustomerView;
    private javax.swing.JButton btnRemoveItems;
    private javax.swing.JButton clearAddBtn;
    private javax.swing.JButton closeAddwnBtn;
    private javax.swing.JPanel customerViewPanel;
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
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JList selectedProducts;
    private javax.swing.JTable tbCustomerList;
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
