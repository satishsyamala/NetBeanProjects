/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SettingPanel.java
 *
 * Created on Aug 24, 2014, 10:37:29 AM
 */

package com.hes.view;

import com.hes.model.SettingModel;
import com.hes.pojo.BasicInformation;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author SATISH SYAMALA
 */
public class SettingPanel extends javax.swing.JPanel {

    /** Creates new form SettingPanel */
    HomePanel homePanel=null;
    SettingModel settingModel=new SettingModel();
    public SettingPanel() {
        initComponents();
    }
    
    public void init(HomePanel homePanel){
        this.homePanel=homePanel;
        BasicInformation bi=settingModel.getBasicInformation();
        if(bi!=null)
        {
            txtCst.setText(bi.getCst()+"");
            txtVat.setText(bi.getVat()+"");
            txtExciseDuty.setText(bi.getExciseDuty()+"");
            txtEductionCess.setText(bi.getEductionCess()+"");
            txtSandHEducationCess.setText(bi.getSandHEduCess()+"");
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

        jLabel1 = new javax.swing.JLabel();
        txtExciseDuty = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEductionCess = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSandHEducationCess = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtVat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCst = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setOpaque(false);
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Excise Duty");
        add(jLabel1);
        jLabel1.setBounds(190, 170, 130, 30);

        txtExciseDuty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(txtExciseDuty);
        txtExciseDuty.setBounds(370, 170, 140, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Eduction Cess");
        add(jLabel2);
        jLabel2.setBounds(190, 230, 130, 30);

        txtEductionCess.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(txtEductionCess);
        txtEductionCess.setBounds(370, 230, 140, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("S & H Eduction Cess");
        add(jLabel3);
        jLabel3.setBounds(190, 290, 160, 30);

        txtSandHEducationCess.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(txtSandHEducationCess);
        txtSandHEducationCess.setBounds(370, 290, 140, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("VAT");
        add(jLabel4);
        jLabel4.setBounds(190, 70, 130, 30);

        txtVat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(txtVat);
        txtVat.setBounds(369, 70, 140, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("CST");
        add(jLabel5);
        jLabel5.setBounds(190, 120, 130, 30);

        txtCst.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(txtCst);
        txtCst.setBounds(370, 120, 140, 30);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 204));
        jButton1.setText("SUBMIT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(330, 360, 100, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BasicInformation bi=new BasicInformation();
        bi.setCst(checkDoube(txtCst));
        bi.setEductionCess(checkDoube(txtEductionCess));
        bi.setExciseDuty(checkDoube(txtExciseDuty));
        bi.setSandHEduCess(checkDoube(txtSandHEducationCess));
        bi.setVat(checkDoube(txtVat));
        String msg=settingModel.setSettings(bi);
        JOptionPane.showMessageDialog(this, msg);
    }//GEN-LAST:event_jButton1ActionPerformed

    public double checkDoube(JTextField ts)
    {
        try {
            return Double.parseDouble(ts.getText());
        } catch (Exception e) {
            return 0.0;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtCst;
    private javax.swing.JTextField txtEductionCess;
    private javax.swing.JTextField txtExciseDuty;
    private javax.swing.JTextField txtSandHEducationCess;
    private javax.swing.JTextField txtVat;
    // End of variables declaration//GEN-END:variables

}