/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HomePanel.java
 *
 * Created on Jul 26, 2014, 7:18:18 PM
 */
package com.hes.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author SATISH SYAMALA
 */
public class HomePanel extends javax.swing.JPanel implements ActionListener {

    /** Creates new form HomePanel */
    private MainWindow mainWindow = null;
    Timer time = new Timer(100, this);
    SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    CompanyPanel companyPanel = null;
    SupplierPanel supplierPanel = null;
    ProductPanel productPanel = null;
    CustomerPanel customerPanel = null;
    InvoicePanel invoicePanel = null;
    ReportPanel reportPanel = null;
    SettingPanel settingPanel=null;

    public HomePanel(MainWindow mainWindow) {
        initComponents();
        this.mainWindow = mainWindow;
        time.start();

        companyPanel = new CompanyPanel();
        companyPanel.setBounds(0, 0, 790, 590);
        companyPanel.setVisible(false);
        mainHomePanel.add(companyPanel);

        supplierPanel = new SupplierPanel();
        supplierPanel.setBounds(0, 0, 790, 590);
        supplierPanel.setVisible(false);
        mainHomePanel.add(supplierPanel);

        productPanel = new ProductPanel();
        productPanel.setBounds(0, 0, 790, 590);
        productPanel.setVisible(false);
        mainHomePanel.add(productPanel);

        customerPanel = new CustomerPanel();
        customerPanel.setBounds(0, 0, 790, 590);
        customerPanel.setVisible(false);
        mainHomePanel.add(customerPanel);

        invoicePanel = new InvoicePanel();
        invoicePanel.setBounds(0, 0, 790, 590);
        invoicePanel.setVisible(false);
        mainHomePanel.add(invoicePanel);

        reportPanel = new ReportPanel();
        reportPanel.setBounds(0, 0, 790, 590);
        reportPanel.setVisible(false);
        mainHomePanel.add(reportPanel);

        settingPanel = new SettingPanel();
        settingPanel.setBounds(0, 0, 790, 590);
        settingPanel.setVisible(false);
        mainHomePanel.add(settingPanel);

    }

    public void initail(MainWindow mainWindow) {
        this.setMainWindow(mainWindow);
        homeLb.setText("Home");
        loginLb.setText("Welcome to  " + mainWindow.getUserLogin().getUserName());
        openHomePanel();
    }

    public void openHomePanel() {
        removePanels();
        homeLb.setText("Home");
        welcomePanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openCompanyPanel() {
        removePanels();
        homeLb.setText("Company");
        companyPanel.init(this);
        companyPanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openSupplierPanel() {
        removePanels();
        homeLb.setText("Supplier");
        supplierPanel.init(this);
        supplierPanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openProductPanel() {
        removePanels();
        homeLb.setText("Product");
        productPanel.init(this);
        productPanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openCustomerPanel() {
        removePanels();
        homeLb.setText("Customer");
        customerPanel.init(this);
        customerPanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openInvoicePanel() {
        removePanels();
        homeLb.setText("Invoice");
        invoicePanel.init(this);
        invoicePanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openReportPanel() {
        removePanels();
        homeLb.setText("Report");
        reportPanel.init(this);
        reportPanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openSettingPanel()
    {
        removePanels();
        homeLb.setText("Settings");
        settingPanel.init(this);
        settingPanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void removePanels() {
        welcomePanel.setVisible(false);
        companyPanel.setVisible(false);
        supplierPanel.setVisible(false);
        productPanel.setVisible(false);
        customerPanel.setVisible(false);
        invoicePanel.setVisible(false);
        reportPanel.setVisible(false);
        settingPanel.setVisible(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        InvoiceBtn = new javax.swing.JButton();
        reportsBtn = new javax.swing.JButton();
        companyBtn = new javax.swing.JButton();
        productBtn = new javax.swing.JButton();
        customerBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        supplierBtn = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        timeLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        loginLb = new javax.swing.JLabel();
        homeLb = new javax.swing.JLabel();
        mainHomePanel = new javax.swing.JPanel();
        welcomePanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setLayout(null);

        InvoiceBtn.setFont(new java.awt.Font("Tahoma", 1, 18));
        InvoiceBtn.setForeground(new java.awt.Color(0, 0, 204));
        InvoiceBtn.setText("INVOICE");
        InvoiceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InvoiceBtnActionPerformed(evt);
            }
        });
        add(InvoiceBtn);
        InvoiceBtn.setBounds(30, 120, 150, 35);

        reportsBtn.setFont(new java.awt.Font("Tahoma", 1, 18));
        reportsBtn.setForeground(new java.awt.Color(0, 0, 204));
        reportsBtn.setText("REPORTS");
        reportsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsBtnActionPerformed(evt);
            }
        });
        add(reportsBtn);
        reportsBtn.setBounds(30, 370, 150, 35);

        companyBtn.setFont(new java.awt.Font("Tahoma", 1, 18));
        companyBtn.setForeground(new java.awt.Color(0, 0, 204));
        companyBtn.setText("COMPANY");
        companyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companyBtnActionPerformed(evt);
            }
        });
        add(companyBtn);
        companyBtn.setBounds(30, 170, 150, 35);

        productBtn.setFont(new java.awt.Font("Tahoma", 1, 18));
        productBtn.setForeground(new java.awt.Color(0, 0, 204));
        productBtn.setText("PRODUCT");
        productBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productBtnActionPerformed(evt);
            }
        });
        add(productBtn);
        productBtn.setBounds(30, 270, 150, 35);

        customerBtn.setFont(new java.awt.Font("Tahoma", 1, 18));
        customerBtn.setForeground(new java.awt.Color(0, 0, 204));
        customerBtn.setText("CUSTOMER");
        customerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerBtnActionPerformed(evt);
            }
        });
        add(customerBtn);
        customerBtn.setBounds(30, 320, 150, 35);

        logoutBtn.setFont(new java.awt.Font("Tahoma", 1, 18));
        logoutBtn.setForeground(new java.awt.Color(0, 0, 204));
        logoutBtn.setText("LOGOUT");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        add(logoutBtn);
        logoutBtn.setBounds(30, 470, 150, 35);

        supplierBtn.setFont(new java.awt.Font("Tahoma", 1, 18));
        supplierBtn.setForeground(new java.awt.Color(0, 0, 204));
        supplierBtn.setText("SUPPLIER");
        supplierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierBtnActionPerformed(evt);
            }
        });
        add(supplierBtn);
        supplierBtn.setBounds(30, 220, 150, 35);

        btnSettings.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSettings.setForeground(new java.awt.Color(0, 0, 204));
        btnSettings.setText("SETTINGS");
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });
        add(btnSettings);
        btnSettings.setBounds(30, 420, 150, 35);

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 48));
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("HES");
        add(jLabel3);
        jLabel3.setBounds(50, 40, 110, 70);

        jSeparator1.setAlignmentX(11.0F);
        jSeparator1.setAlignmentY(3.0F);
        add(jSeparator1);
        jSeparator1.setBounds(30, 100, 160, 10);

        timeLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        timeLabel.setForeground(new java.awt.Color(255, 255, 255));
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("25-05-2012 12:10");
        add(timeLabel);
        timeLabel.setBounds(860, 720, 150, 25);
        add(jSeparator2);
        jSeparator2.setBounds(195, 100, 800, 10);

        loginLb.setFont(new java.awt.Font("Tahoma", 3, 16));
        loginLb.setForeground(new java.awt.Color(0, 0, 255));
        loginLb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        loginLb.setText("jLabel4");
        add(loginLb);
        loginLb.setBounds(664, 70, 310, 20);

        homeLb.setFont(new java.awt.Font("Tahoma", 3, 24));
        homeLb.setForeground(new java.awt.Color(255, 0, 0));
        homeLb.setText("jLabel4");
        add(homeLb);
        homeLb.setBounds(210, 49, 200, 50);

        mainHomePanel.setOpaque(false);
        mainHomePanel.setLayout(null);

        welcomePanel.setOpaque(false);
        welcomePanel.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 36));
        jLabel7.setForeground(new java.awt.Color(0, 0, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("TO");
        welcomePanel.add(jLabel7);
        jLabel7.setBounds(290, 190, 160, 70);

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 36));
        jLabel8.setForeground(new java.awt.Color(0, 0, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Hyderabad Electric Syndicate");
        welcomePanel.add(jLabel8);
        jLabel8.setBounds(30, 280, 730, 50);

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 36));
        jLabel9.setForeground(new java.awt.Color(0, 0, 204));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("WELCOME");
        welcomePanel.add(jLabel9);
        jLabel9.setBounds(250, 110, 260, 50);

        mainHomePanel.add(welcomePanel);
        welcomePanel.setBounds(0, 0, 790, 590);

        add(mainHomePanel);
        mainHomePanel.setBounds(200, 110, 790, 590);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hes/images/HES_BG.png"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(-10, 0, 1024, 768);
    }// </editor-fold>//GEN-END:initComponents

    private void productBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productBtnActionPerformed
        openProductPanel();
    }//GEN-LAST:event_productBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        getMainWindow().setUserLogin(null);
        getMainWindow().showLoginPanel();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void companyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companyBtnActionPerformed
        openCompanyPanel();
    }//GEN-LAST:event_companyBtnActionPerformed

    private void supplierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierBtnActionPerformed
        openSupplierPanel();
    }//GEN-LAST:event_supplierBtnActionPerformed

    private void customerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerBtnActionPerformed
        openCustomerPanel();
    }//GEN-LAST:event_customerBtnActionPerformed

    private void InvoiceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InvoiceBtnActionPerformed
        openInvoicePanel();
    }//GEN-LAST:event_InvoiceBtnActionPerformed

    private void reportsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsBtnActionPerformed
       openReportPanel();
    }//GEN-LAST:event_reportsBtnActionPerformed

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed

       openSettingPanel();
    }//GEN-LAST:event_btnSettingsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton InvoiceBtn;
    private javax.swing.JButton btnSettings;
    private javax.swing.JButton companyBtn;
    private javax.swing.JButton customerBtn;
    private javax.swing.JLabel homeLb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel loginLb;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel mainHomePanel;
    private javax.swing.JButton productBtn;
    private javax.swing.JButton reportsBtn;
    private javax.swing.JButton supplierBtn;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JPanel welcomePanel;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == time) {
            timeLabel.setText(sd.format(new Date()));
        }
    }

    /**
     * @return the mainWindow
     */
    public MainWindow getMainWindow() {
        return mainWindow;
    }

    /**
     * @param mainWindow the mainWindow to set
     */
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
}
