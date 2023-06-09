/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i18nconvert;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author celstra31
 */
public class MyWindow extends javax.swing.JFrame {

    public MyWindow() {

        initComponents();
        setLandList();
        jComboBox1.setSelectedItem("Telugu");

    }

    public void setLandList() {
        Map<String, String> m = lanList();
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(setToArray(m.keySet())));
    }

    public String[] setToArray(Set<String> st) {
        List<String> ls = new ArrayList<>();
        for (String s : st) {
            ls.add(s);
        }
        Collections.sort(ls);
        String[] a = new String[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            a[i] = ls.get(i);
        }
        return a;
    }

    public String transletString(String s, String ln) {
        try {
            String urlStr = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=" + ln + "&dt=t&q=" + URLEncoder.encode(s, "UTF-8");
            System.out.println(urlStr);
            URL url = new URL(urlStr);
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            Map<String,List<String>> rp=con.getRequestProperties();
            System.out.println(rp);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONParser j = new JSONParser();
            JSONArray a = (JSONArray) j.parse(response.toString());
            System.out.println(a.toString());
            JSONArray a1 = (JSONArray) a.get(0);
            JSONArray a2 = (JSONArray) a1.get(0);
            return a2.get(0).toString();
        } catch (Exception e) {

        }
        return s;
    }

    public String getKey(String lebel) {
        List<String> rp = new ArrayList<>();
        rp.add(":");
        rp.add(" ");
        rp.add("/");
        rp.add("=");
        rp.add("'");
        for (String s : rp) {
            lebel = lebel.replaceAll(s, "");
        }
        return lebel.toLowerCase();
    }

    public String getLanguage() {
        if (jComboBox1.getSelectedItem() != null) {
            Map<String, String> m = lanList();
            return m.get(jComboBox1.getSelectedItem().toString());
        }
        return "en";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInput = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("I 18 N");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Language");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 210, 210, 40);

        txtInput.setColumns(20);
        txtInput.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtInput.setRows(5);
        txtInput.setToolTipText("");
        jScrollPane1.setViewportView(txtInput);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(40, 100, 590, 106);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Output Text");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 300, 200, 20);

        txtOutput.setColumns(20);
        txtOutput.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtOutput.setRows(5);
        jScrollPane2.setViewportView(txtOutput);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(40, 330, 590, 106);

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(40, 250, 250, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("I18N Converter");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(170, 10, 350, 40);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(490, 230, 130, 70);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("Convert");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(340, 230, 130, 70);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Input Text");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(40, 70, 100, 20);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(40, 50, 590, 10);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (txtInput.getText() != null && txtInput.getText().trim().length() > 0) {
            String s = "";
            if (!getLanguage().equalsIgnoreCase("en")) {
                s = transletString(txtInput.getText().trim(), getLanguage());
            } else {
                s = txtInput.getText().trim();
            }
            txtOutput.setText(getKey(txtInput.getText().trim()) + "=" + s);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txtInput.setText("");
        txtOutput.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
 Dimension screnn = Toolkit.getDefaultToolkit().getScreenSize();
        int hei = screnn.height;
        int wid = screnn.width;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MyWindow m = new MyWindow();
                m.setBounds((wid - 700) / 2, (hei - 550) / 2, 700, 550);
                m.setVisible(true);
            }
        });
    }

    public Map<String, String> lanList() {
        Map<String, String> ll = new HashMap<>();
        ll.put("Afrikaans", "af");
        ll.put("Albanian", "sq");
        ll.put("Amharic", "am");
        ll.put("Arabic", "ar");
        ll.put("Armenian", "hy");
        ll.put("Assamese*", "as");
        ll.put("Aymara*", "ay");
        ll.put("Azerbaijani", "az");
        ll.put("Bambara*", "bm");
        ll.put("Basque", "eu");
        ll.put("Belarusian", "be");
        ll.put("Bengali", "bn");
        ll.put("Bhojpuri*", "bho");
        ll.put("Bosnian", "bs");
        ll.put("Bulgarian", "bg");
        ll.put("Catalan", "ca");
        ll.put("Cebuano", "ceb");
        ll.put("Corsican", "co");
        ll.put("Croatian", "hr");
        ll.put("Czech", "cs");
        ll.put("Danish", "da");
        ll.put("Dhivehi*", "dv");
        ll.put("Dogri*", "doi");
        ll.put("Dutch", "nl");
        ll.put("English", "en");
        ll.put("Esperanto", "eo");
        ll.put("Estonian", "et");
        ll.put("Ewe*", "ee");
        ll.put("Filipino (Tagalog)", "fil");
        ll.put("Finnish", "fi");
        ll.put("French", "fr");
        ll.put("Frisian", "fy");
        ll.put("Galician", "gl");
        ll.put("Georgian", "ka");
        ll.put("German", "de");
        ll.put("Greek", "el");
        ll.put("Guarani*", "gn");
        ll.put("Gujarati", "gu");
        ll.put("Haitian Creole", "ht");
        ll.put("Hausa", "ha");
        ll.put("Hawaiian", "haw");
        ll.put("Hebrew", "he");
        ll.put("Hindi", "hi");
        ll.put("Hmong", "hmn");
        ll.put("Hungarian", "hu");
        ll.put("Icelandic", "is");
        ll.put("Igbo", "ig");
        ll.put("Ilocano*", "ilo");
        ll.put("Indonesian", "id");
        ll.put("Irish", "ga");
        ll.put("Italian", "it");
        ll.put("Japanese", "ja");
        ll.put("Javanese", "jv");
        ll.put("Kannada", "kn");
        ll.put("Kazakh", "kk");
        ll.put("Khmer", "km");
        ll.put("Kinyarwanda", "rw");
        ll.put("Konkani*", "gom");
        ll.put("Korean", "ko");
        ll.put("Krio*", "kri");
        ll.put("Kurdish", "ku");
        ll.put("Kurdish (Sorani)*", "ckb");
        ll.put("Kyrgyz", "ky");
        ll.put("Lao", "lo");
        ll.put("Latin", "la");
        ll.put("Latvian", "lv");
        ll.put("Lingala*", "ln");
        ll.put("Lithuanian", "lt");
        ll.put("Luganda*", "lg");
        ll.put("Luxembourgish", "lb");
        ll.put("Macedonian", "mk");
        ll.put("Maithili*", "mai");
        ll.put("Malagasy", "mg");
        ll.put("Malay", "ms");
        ll.put("Malayalam", "ml");
        ll.put("Maltese", "mt");
        ll.put("Maori", "mi");
        ll.put("Marathi", "mr");
        ll.put("Meiteilon (Manipuri)*", "mni-Mtei");
        ll.put("Mizo*", "lus");
        ll.put("Mongolian", "mn");
        ll.put("Myanmar (Burmese)", "my");
        ll.put("Nepali", "ne");
        ll.put("Norwegian", "no");
        ll.put("Nyanja (Chichewa)", "ny");
        ll.put("Odia (Oriya)", "or");
        ll.put("Oromo*", "om");
        ll.put("Pashto", "ps");
        ll.put("Persian", "fa");
        ll.put("Polish", "pl");
        ll.put("Portuguese (Portugal, Brazil)", "pt");
        ll.put("Punjabi", "pa");
        ll.put("Quechua*", "qu");
        ll.put("Romanian", "ro");
        ll.put("Russian", "ru");
        ll.put("Samoan", "sm");
        ll.put("Sanskrit*", "sa");
        ll.put("Scots Gaelic", "gd");
        ll.put("Sepedi*", "nso");
        ll.put("Serbian", "sr");
        ll.put("Sesotho", "st");
        ll.put("Shona", "sn");
        ll.put("Sindhi", "sd");
        ll.put("Sinhala (Sinhalese)", "si");
        ll.put("Slovak", "sk");
        ll.put("Slovenian", "sl");
        ll.put("Somali", "so");
        ll.put("Spanish", "es");
        ll.put("Sundanese", "su");
        ll.put("Swahili", "sw");
        ll.put("Swedish", "sv");
        ll.put("Tagalog (Filipino)", "tl");
        ll.put("Tajik", "tg");
        ll.put("Tamil", "ta");
        ll.put("Tatar", "tt");
        ll.put("Telugu", "te");
        ll.put("Thai", "th");
        ll.put("Tigrinya*", "ti");
        ll.put("Tsonga*", "ts");
        ll.put("Turkish", "tr");
        ll.put("Turkmen", "tk");
        ll.put("Twi (Akan)*", "ak");
        ll.put("Ukrainian", "uk");
        ll.put("Urdu", "ur");
        ll.put("Uyghur", "ug");
        ll.put("Uzbek", "uz");
        ll.put("Vietnamese", "vi");
        ll.put("Welsh", "cy");
        ll.put("Xhosa", "xh");
        ll.put("Yiddish", "yi");
        ll.put("Yoruba", "yo");
        ll.put("Zulu", "zu");

        return ll;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea txtInput;
    private javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables
}
