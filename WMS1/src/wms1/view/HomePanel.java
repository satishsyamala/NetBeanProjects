/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * HomePanel.java
 *
 * Created on Jul 26, 2014, 7:18:18 PM
 */
package wms1.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import wms1.pojos.PurchasesItems;
import wms1.pojos.Services;
import wms1.util.GeneralUtil;
import wms1.util.ReadCSV;

/**
 *
 * @author SATISH SYAMALA
 */
public class HomePanel extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form HomePanel
     */
    private MainWindow mainWindow = null;
    Timer time = new Timer(100, this);
    SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private MasterDataPanel masterDataPanel = null;
    private PurchasesPanel purchasesPanel = null;
    private PurchasesList purchasesList = null;
    private SalesPanel1 salesPanel1 = null;
    private SalesList salesList = null;
    private ReportsPanel reportsPanel = null;
    private StockTakePanel stockTakePanel = null;
    private StockTakeList stockTakeList = null;
    private ServiceList serviceList = null;
    private ServicePanel1 servicePanel1 = null;
    private ConfigurationPanel configurationPanel = null;
    private ReportData reportData=null;

    public HomePanel(MainWindow mainWindow) {
        initComponents();
        this.mainWindow = mainWindow;
        time.start();
        
         reportData = new ReportData();
        reportData.setBounds(0, 0, 790, 590);
        reportData.setVisible(false);
        mainHomePanel.add(reportData);

        configurationPanel = new ConfigurationPanel();
        configurationPanel.setBounds(0, 0, 790, 590);
        configurationPanel.setVisible(false);
        mainHomePanel.add(configurationPanel);

        servicePanel1 = new ServicePanel1();
        servicePanel1.setBounds(0, 0, 790, 590);
        servicePanel1.setVisible(false);
        mainHomePanel.add(servicePanel1);

        serviceList = new ServiceList();
        serviceList.setBounds(0, 0, 790, 590);
        serviceList.setVisible(false);
        mainHomePanel.add(serviceList);

        stockTakeList = new StockTakeList();
        stockTakeList.setBounds(0, 0, 790, 590);
        stockTakeList.setVisible(false);
        mainHomePanel.add(stockTakeList);

        stockTakePanel = new StockTakePanel();
        stockTakePanel.setBounds(0, 0, 790, 590);
        stockTakePanel.setVisible(false);
        mainHomePanel.add(stockTakePanel);

        reportsPanel = new ReportsPanel();
        reportsPanel.setBounds(0, 0, 790, 590);
        reportsPanel.setVisible(false);
        mainHomePanel.add(reportsPanel);

        salesList = new SalesList();
        salesList.setBounds(0, 0, 790, 590);
        salesList.setVisible(false);
        mainHomePanel.add(salesList);

        masterDataPanel = new MasterDataPanel();
        masterDataPanel.setBounds(0, 0, 790, 590);
        masterDataPanel.setVisible(false);
        mainHomePanel.add(masterDataPanel);

        purchasesPanel = new PurchasesPanel();
        purchasesPanel.setBounds(0, 0, 790, 590);
        purchasesPanel.setVisible(false);
        mainHomePanel.add(purchasesPanel);

        purchasesList = new PurchasesList();
        purchasesList.setBounds(0, 0, 790, 590);
        purchasesList.setVisible(false);
        mainHomePanel.add(purchasesList);

        salesPanel1 = new SalesPanel1();
        salesPanel1.setBounds(0, 0, 790, 590);
        salesPanel1.setVisible(false);
        mainHomePanel.add(salesPanel1);
    }

    public void removePanels() {
        reportData.setVisible(false);
        configurationPanel.setVisible(false);
        serviceList.setVisible(false);
        stockTakeList.setVisible(false);
        welcomePanel.setVisible(false);
        masterDataPanel.setVisible(false);
        purchasesPanel.setVisible(false);
        purchasesList.setVisible(false);
        salesPanel1.setVisible(false);
        salesList.setVisible(false);
        reportsPanel.setVisible(false);
        stockTakePanel.setVisible(false);
        servicePanel1.setVisible(false);
        
        
    }
    
     public void openReportDataPanel(String manuName) {
        removePanels();
        reportData.init(this, manuName);
        reportData.setVisible(true);
        mainHomePanel.repaint();
    }
    
     public void openConfigurationPanel(String manuName) {
        removePanels();
        configurationPanel.init(this, manuName);
        configurationPanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openServicePanel1(String manuName) {
        removePanels();
        servicePanel1.init(this, manuName, null);
        servicePanel1.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openServicePanel1(String manuName, Services services) {
        removePanels();
        servicePanel1.init(this, manuName, services);
        servicePanel1.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openServiceList(String manuName) {
        removePanels();
        serviceList.init(this, manuName);
        serviceList.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openStockTakeList(String manuName) {
        removePanels();
        stockTakeList.init(this, manuName);
        stockTakeList.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openStockTakePanel(String manuName) {
        removePanels();
        stockTakePanel.init(this, manuName);
        stockTakePanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openReportsPanel(String manuName) {
        removePanels();
        reportsPanel.init(this, manuName);
        reportsPanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openSalesList(String manuName) {
        removePanels();
        salesList.init(this, manuName);
        salesList.setVisible(true);
        mainHomePanel.repaint();
    }

    public void initail(MainWindow mainWindow) {
        this.setMainWindow(mainWindow);
        pnlMenuItems.removeAll();
        JSONArray menus = GeneralUtil.getUserMenus(this.mainWindow.getUsers().getUserType());
        for (int i = 0; i < menus.size(); i++) {
            JSONObject j = (JSONObject) menus.get(i);
            JButton menuButtons = new JButton();
            menuButtons.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
            menuButtons.setForeground(new java.awt.Color(0, 0, 204));
            menuButtons.setText(j.get("name").toString());
            menuButtons.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    onButtonClick(evt, j.get("name").toString());
                }
            });
            pnlMenuItems.add(menuButtons);
            menuButtons.setBounds(10, 10 + (i * 45), 155, 35);
        }
        pnlMenuItems.revalidate();
        pnlMenuItems.repaint();
        this.revalidate();
        this.repaint();
        homeLb.setText("Home");
        loginLb.setText(this.mainWindow.getUsers().getFullName());
        if(this.mainWindow.getUsers().getTempLastLoginTime()!=null)
        loginLb1.setText(new SimpleDateFormat("dd-MMM-yy HH:mm").format(this.mainWindow.getUsers().getTempLastLoginTime()));
        jLabel8.setText(ReadCSV.getSettings().getShopName());
        openHomePanel();
    }

    public void openHomePanel() {
        removePanels();
        homeLb.setText("Home");
        welcomePanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void onButtonClick(java.awt.event.ActionEvent evt, String name) {
        //  JOptionPane.showMessageDialog(this, name);
        homeLb.setText(name);
        if (name.equalsIgnoreCase("Users") || name.equalsIgnoreCase("Brands") || name.equalsIgnoreCase("Items") || name.equalsIgnoreCase("Service Types")) {
            openMasterPanel(name);
        } else if (name.equalsIgnoreCase("Purchases")) {
            openPurcaseList(name);
        } else if (name.equalsIgnoreCase("Sales")) {
            openSalesList(name);
        } else if (name.equalsIgnoreCase("Reports")) {
            openReportsPanel(name);
        } else if (name.equalsIgnoreCase("Stock Take")) {
            openStockTakeList(name);
        } else if (name.equalsIgnoreCase("Services")) {
            openServiceList(name);
        }else if (name.equalsIgnoreCase("Configuration")) {
            openConfigurationPanel(name);
        } else {
            openHomePanel();
        }

    }

    public void openMasterPanel(String manuName) {
        removePanels();
        masterDataPanel.init(this, manuName);
        masterDataPanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openPurcasePanel(String manuName) {
        removePanels();
        purchasesPanel.init(this, manuName);
        purchasesPanel.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openPurcaseList(String manuName) {
        removePanels();
        purchasesList.init(this, manuName);
        purchasesList.setVisible(true);
        mainHomePanel.repaint();
    }

    public void openSalesPanel1(String manuName) {
        removePanels();
        salesPanel1.init(this, manuName);
        salesPanel1.setVisible(true);
        mainHomePanel.repaint();
    }

    public void logout() {
        getMainWindow().setUsers(null);
        getMainWindow().showLoginPanel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        loginLb = new javax.swing.JLabel();
        loginLb1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        homeLb = new javax.swing.JLabel();
        pnlMenuItems = new javax.swing.JPanel();
        mainHomePanel = new javax.swing.JPanel();
        welcomePanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setLayout(null);

        timeLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(255, 255, 255));
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("25-05-2012 12:10");
        add(timeLabel);
        timeLabel.setBounds(860, 720, 150, 25);
        add(jSeparator2);
        jSeparator2.setBounds(20, 110, 980, 10);

        loginLb.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        loginLb.setForeground(new java.awt.Color(0, 102, 102));
        loginLb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        loginLb.setText("jLabel4");
        add(loginLb);
        loginLb.setBounds(750, 60, 190, 20);

        loginLb1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loginLb1.setForeground(new java.awt.Color(0, 102, 102));
        loginLb1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        loginLb1.setText("jLabel4");
        add(loginLb1);
        loginLb1.setBounds(750, 80, 190, 20);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wms1/images/LO30X30.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        add(jLabel4);
        jLabel4.setBounds(950, 60, 30, 40);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wms1/images/home30x30.png"))); // NOI18N
        jLabel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 204, 204), null, new java.awt.Color(204, 204, 204)));
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        add(jLabel5);
        jLabel5.setBounds(30, 60, 36, 40);

        homeLb.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        homeLb.setForeground(new java.awt.Color(0, 153, 153));
        homeLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeLb.setText("Home");
        add(homeLb);
        homeLb.setBounds(330, 50, 440, 60);

        pnlMenuItems.setOpaque(false);
        pnlMenuItems.setLayout(null);
        add(pnlMenuItems);
        pnlMenuItems.setBounds(10, 110, 180, 590);

        mainHomePanel.setOpaque(false);
        mainHomePanel.setLayout(null);

        welcomePanel.setOpaque(false);
        welcomePanel.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Warehouse Managment System");
        welcomePanel.add(jLabel8);
        jLabel8.setBounds(10, 10, 460, 40);

        jSeparator3.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        welcomePanel.add(jSeparator3);
        jSeparator3.setBounds(10, 62, 770, 20);

        mainHomePanel.add(welcomePanel);
        welcomePanel.setBounds(0, 0, 790, 590);

        add(mainHomePanel);
        mainHomePanel.setBounds(200, 110, 790, 590);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wms1/images/HES_BG.png"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(-10, 0, 1024, 768);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        logout();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
       openHomePanel();
    }//GEN-LAST:event_jLabel5MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel homeLb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel loginLb;
    private javax.swing.JLabel loginLb1;
    private javax.swing.JPanel mainHomePanel;
    private javax.swing.JPanel pnlMenuItems;
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
