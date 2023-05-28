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
import javax.swing.JButton;
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
import wms1.pojos.Purchases;
import wms1.pojos.PurchasesItems;
import wms1.pojos.Users;

import wms1.util.GeneralUtil;
import wms1.util.ReadCSV;

/**
 *
 * @author SATISH SYAMALA
 */
public class ReportsPanel extends javax.swing.JPanel {

    /**
     * Creates new form ProductPanel
     */
    private HomePanel homePanel;
    private String menuName;
    private JXDatePicker fromDate;
    private JXDatePicker todateDate;
    private int page = 0;
    private int noRec = 0;

    public ReportsPanel() {
        initComponents();
    }

    public void init(HomePanel homePanel, String menuName) {
        this.homePanel = homePanel;
        this.menuName = menuName;
        JSONArray reports = GeneralUtil.reports(this.homePanel.getMainWindow().getUsers().getUserType());
        int y = 10;
        int x = 10;
        int index = 1;

        for (int i = 0; i < reports.size(); i++) {
            JSONObject json = (JSONObject) reports.get(i);
            JButton menuButtons = new JButton();
            menuButtons.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
            menuButtons.setForeground(new java.awt.Color(0, 153, 51));
            menuButtons.setText(json.get("name").toString());
            int k = i;
            menuButtons.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    onButtonClick(evt, json.get("name").toString());
                }
            });
            add(menuButtons);
            menuButtons.setBounds(x, y, 230, 60);
            x += 260;
            if (index % 3 == 0) {
                y = y + 70;
                x = 10;
            }
            index++;

        }
    }

    public void onButtonClick(java.awt.event.ActionEvent evt, String name) {
        this.homePanel.openReportDataPanel(name);
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

        setOpaque(false);
        setLayout(null);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);
        add(jPanel2);
        jPanel2.setBounds(0, 0, 790, 590);
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}