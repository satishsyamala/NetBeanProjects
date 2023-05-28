
package com.hes.view;

import com.hes.model.LoginModel;
import javax.swing.JOptionPane;

/**
 *
 * @author SATISH SYAMALA
 */
public class LoginPanel extends javax.swing.JPanel {

    /** Creates new form LoginPanel */
    MainWindow mainWindow = null;
    LoginModel loginModel = null;

    public LoginPanel(MainWindow mainWindow) {
        initComponents();
        this.mainWindow = mainWindow;
       userIdTxt.setText("admin");
       passwordTxt.setText("1234");
    }

    public void inital() {
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clearBtn = new javax.swing.JButton();
        loginbtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        userIdTxt = new javax.swing.JTextField();
        passwordTxt = new javax.swing.JPasswordField();
        closeBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1024, 768));
        setLayout(null);

        clearBtn.setFont(new java.awt.Font("Tahoma", 1, 18));
        clearBtn.setForeground(new java.awt.Color(0, 0, 255));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        add(clearBtn);
        clearBtn.setBounds(470, 440, 110, 31);

        loginbtn.setFont(new java.awt.Font("Tahoma", 1, 18));
        loginbtn.setForeground(new java.awt.Color(0, 0, 255));
        loginbtn.setText("Login");
        loginbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbtnActionPerformed(evt);
            }
        });
        add(loginbtn);
        loginbtn.setBounds(600, 440, 110, 31);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setText("Password :");
        add(jLabel2);
        jLabel2.setBounds(360, 370, 90, 30);

        jLabel3.setFont(new java.awt.Font("Garamond", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("Login");
        add(jLabel3);
        jLabel3.setBounds(460, 270, 90, 30);

        jLabel4.setFont(new java.awt.Font("Garamond", 3, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("HES");
        add(jLabel4);
        jLabel4.setBounds(350, 250, 110, 50);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel5.setText("User Id     :");
        add(jLabel5);
        jLabel5.setBounds(360, 320, 90, 30);

        userIdTxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(userIdTxt);
        userIdTxt.setBounds(460, 320, 240, 30);

        passwordTxt.setFont(new java.awt.Font("Tahoma", 1, 14));
        add(passwordTxt);
        passwordTxt.setBounds(460, 370, 240, 30);

        closeBtn.setFont(new java.awt.Font("Tahoma", 1, 18));
        closeBtn.setForeground(new java.awt.Color(0, 0, 255));
        closeBtn.setText("Close");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        add(closeBtn);
        closeBtn.setBounds(340, 440, 110, 31);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hes/images/Loading.jpg"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 1060, 768);
    }// </editor-fold>//GEN-END:initComponents

    private void loginbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtnActionPerformed
        if (userIdTxt.getText().trim().length() > 0 && passwordTxt.getText().trim().length() > 0) {
            loginModel = new LoginModel();
            String msg = loginModel.loginValidation(userIdTxt.getText(), passwordTxt.getText());
            if (msg.equals("success")) {
                mainWindow.setUserLogin(loginModel.getUserLogin());
                mainWindow.showHomePanel();
            } else {
                JOptionPane.showMessageDialog(this, msg);
            }
        } else {
            if (userIdTxt.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "User Id required!");
            } else {
                JOptionPane.showMessageDialog(this, "Password required!");
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_loginbtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        userIdTxt.setText("");
        passwordTxt.setText("");

    }//GEN-LAST:event_clearBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
       System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_closeBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton loginbtn;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JTextField userIdTxt;
    // End of variables declaration//GEN-END:variables
}