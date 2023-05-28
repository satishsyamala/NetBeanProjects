/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProductPanel.java
 *
 * Created on Jul 27, 2014, 11:22:02 AM
 */

package com.hes.view;

import com.hes.model.ProductModel;
import com.hes.pojo.Product;
import com.hes.pojo.Supplier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author SATISH SYAMALA
 */
public class ProductPanel extends javax.swing.JPanel {

    /** Creates new form ProductPanel */

     private HomePanel homePanel;
     private ProductModel productModel=new ProductModel();
     private List<Product> products = null;
     SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
     int addstatus = 0;
     List<Supplier> list=null;

    public ProductPanel() {
        initComponents();
    }

     public void init(HomePanel homePanel) {
        this.homePanel = homePanel;
        list=productModel.getSupplierList("");
        String[] sList=new String[list.size()+1];
        sList[0]="----Select----";
        for(int i=0;i<list.size();i++)
        {
           Supplier sp=list.get(i);
           sList[i+1]=sp.getName();
        }
        supplierList.setModel(new javax.swing.DefaultComboBoxModel(sList));
        products = new ArrayList<Product>();
        addProductPanel.setVisible(false);
        addstatus = 0;
        setCompanyDate();
        clearFields();
    }
     public void clearFields()
    {
          txtProductName.setText("");
        txtSalesCost.setText("");
        txtProductCode.setText("");
        txtPurchaseCost.setText("");
        supplierList.setSelectedIndex(0);
        txtIdentifyMark.setText("");
     }

    public void setCompanyDate() {
        products = productModel.getProductList();
        String[][] data = new String[products.size()][6];
        for (int i = 0; i < products.size(); i++) {
            Product cm = products.get(i);
            data[i][0] = (i + 1) + "";
            data[i][1] = cm.getProductName();
            data[i][2] = cm.getProductCode();
            data[i][3] = cm.getPurchaseCost() + "";
            data[i][4] = cm.getPackageSize()+"";
             data[i][5] = cm.getIdentifMark();
        }
        setTableDate(data);
    }

    public void setTableDate(String[][] data) {
        tbProductList.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{
                    "Sr.No", "Name", "Tariff S & HeadingNo", "Purchase Cost", "Package Size","Identify Mark&No"
                }) {

            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tbProductList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbProductList);
        tbProductList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbProductList.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbProductList.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbProductList.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbProductList.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbProductList.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbProductList.getColumnModel().getColumn(5).setPreferredWidth(200);

    }

    public void addCompany() {
        if (txtProductName.getText().trim().length() > 0 && txtProductCode.getText().trim().length() > 0 && txtPurchaseCost.getText().trim().length() > 0 && txtSalesCost.getText().trim().length() > 0 && supplierList.getSelectedIndex()>0) {
            Product cm = new Product();
            cm.setProductName(txtProductName.getText());
            cm.setProductCode(txtProductCode.getText());
            cm.setPurchaseCost(Double.parseDouble(txtPurchaseCost.getText()));
            cm.setPackageSize(Integer.parseInt(txtSalesCost.getText()));
            cm.setIdentifMark(txtIdentifyMark.getText());
            //List<Supplier> list=productModel.getSupplierList(supplierList.getSelectedItem().toString());
            Supplier sp=list.get(supplierList.getSelectedIndex()-1);
            if(sp!=null)
            {
                cm.setSupplier(sp);
            String msg = productModel.addProduct(cm, addstatus);
            if (msg.equals("success")) {
                jScrollPane1.setVisible(true);
                setCompanyDate();
                addProductPanel.setVisible(false);
            } else {
                if (msg.startsWith("YES")) {
                    int check = JOptionPane.showConfirmDialog(addProductPanel, "Product name already exist in Inactive Status. Do you want to activate!");
                    if (check == 0) {
                        addstatus = Integer.parseInt(msg.replaceAll("YES", ""));
                        addCompany();
                    } else {
                        jScrollPane1.setVisible(true);
                        setCompanyDate();
                        addProductPanel.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(addProductPanel, msg);
                }

            }
            }

        } else {
            JOptionPane.showMessageDialog(addProductPanel, "All fields required !");
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

        jPanel2 = new javax.swing.JPanel();
        addProductPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        txtProductCode = new javax.swing.JTextField();
        txtSalesCost = new javax.swing.JTextField();
        btnAddWindowClear = new javax.swing.JButton();
        btnAddWindowClose = new javax.swing.JButton();
        btnAddProduct = new javax.swing.JButton();
        txtPurchaseCost = new javax.swing.JTextField();
        supplierList = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtIdentifyMark = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProductList = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        btnEditProduct = new javax.swing.JButton();
        btnDeleteProduct = new javax.swing.JButton();
        btnProductAdd = new javax.swing.JButton();

        setOpaque(false);
        setLayout(null);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);

        addProductPanel.setBackground(new java.awt.Color(255, 255, 255));
        addProductPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        addProductPanel.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(0, 0, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("  Add  Product");
        jLabel2.setOpaque(true);
        addProductPanel.add(jLabel2);
        jLabel2.setBounds(0, 0, 770, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Product Name");
        addProductPanel.add(jLabel4);
        jLabel4.setBounds(190, 130, 130, 25);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Product Code");
        addProductPanel.add(jLabel5);
        jLabel5.setBounds(190, 170, 130, 25);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Purchase Cost");
        addProductPanel.add(jLabel6);
        jLabel6.setBounds(190, 210, 130, 25);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Package Size");
        addProductPanel.add(jLabel7);
        jLabel7.setBounds(190, 250, 130, 25);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Supplier Name");
        addProductPanel.add(jLabel8);
        jLabel8.setBounds(190, 80, 130, 25);

        txtProductName.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        addProductPanel.add(txtProductName);
        txtProductName.setBounds(350, 130, 220, 25);

        txtProductCode.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        addProductPanel.add(txtProductCode);
        txtProductCode.setBounds(350, 170, 220, 25);

        txtSalesCost.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        addProductPanel.add(txtSalesCost);
        txtSalesCost.setBounds(350, 250, 220, 25);

        btnAddWindowClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAddWindowClear.setForeground(new java.awt.Color(0, 0, 204));
        btnAddWindowClear.setText("CLEAR");
        btnAddWindowClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWindowClearActionPerformed(evt);
            }
        });
        addProductPanel.add(btnAddWindowClear);
        btnAddWindowClear.setBounds(330, 340, 100, 30);

        btnAddWindowClose.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAddWindowClose.setForeground(new java.awt.Color(0, 0, 204));
        btnAddWindowClose.setText("CLOSE");
        btnAddWindowClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWindowCloseActionPerformed(evt);
            }
        });
        addProductPanel.add(btnAddWindowClose);
        btnAddWindowClose.setBounds(200, 340, 100, 30);

        btnAddProduct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAddProduct.setForeground(new java.awt.Color(0, 0, 204));
        btnAddProduct.setText("ADD");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });
        addProductPanel.add(btnAddProduct);
        btnAddProduct.setBounds(460, 340, 100, 30);

        txtPurchaseCost.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        addProductPanel.add(txtPurchaseCost);
        txtPurchaseCost.setBounds(350, 210, 220, 25);

        supplierList.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        supplierList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        addProductPanel.add(supplierList);
        supplierList.setBounds(350, 80, 370, 25);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Identify Mark");
        addProductPanel.add(jLabel9);
        jLabel9.setBounds(190, 290, 130, 25);

        txtIdentifyMark.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        addProductPanel.add(txtIdentifyMark);
        txtIdentifyMark.setBounds(350, 290, 220, 25);

        jPanel2.add(addProductPanel);
        addProductPanel.setBounds(10, 40, 770, 460);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("  Product List");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(20, 20, 290, 20);
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
        jScrollPane1.setBounds(10, 70, 770, 430);
        jPanel2.add(jSeparator2);
        jSeparator2.setBounds(10, 50, 770, 10);

        btnEditProduct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEditProduct.setForeground(new java.awt.Color(0, 0, 204));
        btnEditProduct.setText("EDIT");
        btnEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProductActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditProduct);
        btnEditProduct.setBounds(550, 540, 100, 30);

        btnDeleteProduct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDeleteProduct.setForeground(new java.awt.Color(0, 0, 204));
        btnDeleteProduct.setText("DELETE");
        btnDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProductActionPerformed(evt);
            }
        });
        jPanel2.add(btnDeleteProduct);
        btnDeleteProduct.setBounds(670, 540, 100, 30);

        btnProductAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnProductAdd.setForeground(new java.awt.Color(0, 0, 204));
        btnProductAdd.setText("ADD");
        btnProductAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductAddActionPerformed(evt);
            }
        });
        jPanel2.add(btnProductAdd);
        btnProductAdd.setBounds(430, 540, 100, 30);

        add(jPanel2);
        jPanel2.setBounds(0, 0, 790, 590);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddWindowClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWindowClearActionPerformed
        txtProductName.setText("");
        txtSalesCost.setText("");
        txtProductCode.setText("");
        txtPurchaseCost.setText("");
        supplierList.setSelectedIndex(0);
        txtIdentifyMark.setText("");
}//GEN-LAST:event_btnAddWindowClearActionPerformed

    private void btnAddWindowCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWindowCloseActionPerformed
       jScrollPane1.setVisible(true);
        addProductPanel.setVisible(false);
}//GEN-LAST:event_btnAddWindowCloseActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        addCompany();
}//GEN-LAST:event_btnAddProductActionPerformed

    private void btnEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProductActionPerformed
        if (tbProductList.getSelectedRowCount() > 0) {
            Product cm = products.get(tbProductList.getSelectedRow());
            txtProductName.setText(cm.getProductName());
            txtProductCode.setText(cm.getProductCode());
            txtSalesCost.setText(""+cm.getPackageSize());
            txtPurchaseCost.setText(""+cm.getPurchaseCost());
            txtIdentifyMark.setText(cm.getIdentifMark());
            supplierList.setSelectedItem(cm.getSupplier().getName());
            addstatus = cm.getProductId();
            jScrollPane1.setVisible(false);
            addProductPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(addProductPanel, "Please select Product !");
        }
}//GEN-LAST:event_btnEditProductActionPerformed

    private void btnDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductActionPerformed
        if (tbProductList.getSelectedRowCount() > 0) {
             int check=JOptionPane.showConfirmDialog(addProductPanel, "Do you want to delete !");
            System.out.println("check : "+check);
             if(check==0)
            {
            Product cm = products.get(tbProductList.getSelectedRow());
            String msg = productModel.deleteProduct(cm.getProductId());
            if (msg.equals("success")) {
                setCompanyDate();
            } else {
                JOptionPane.showMessageDialog(addProductPanel, msg);
            }
            }
        } else {
            JOptionPane.showMessageDialog(addProductPanel, "Please select Product !");
        }
}//GEN-LAST:event_btnDeleteProductActionPerformed

    private void btnProductAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductAddActionPerformed
        clearFields();
        addstatus = 0;
        jScrollPane1.setVisible(false);
        addProductPanel.setVisible(true);
}//GEN-LAST:event_btnProductAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addProductPanel;
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnAddWindowClear;
    private javax.swing.JButton btnAddWindowClose;
    private javax.swing.JButton btnDeleteProduct;
    private javax.swing.JButton btnEditProduct;
    private javax.swing.JButton btnProductAdd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox supplierList;
    private javax.swing.JTable tbProductList;
    private javax.swing.JTextField txtIdentifyMark;
    private javax.swing.JTextField txtProductCode;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtPurchaseCost;
    private javax.swing.JTextField txtSalesCost;
    // End of variables declaration//GEN-END:variables

}
