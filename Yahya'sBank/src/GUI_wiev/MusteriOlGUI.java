/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_wiev;

import Backend.Musteri;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import veritabani.Veritabani;
import static veritabani.Veritabani.sifre;

/**
 *
 * @author yahya
 */
public class MusteriOlGUI extends javax.swing.JFrame {

    /**
     * Creates new form MusteriOlGUI
     */
    public MusteriOlGUI() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        yenimusteriisimtext = new javax.swing.JTextField();
        yenimusterisoyisimtext = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        yenimusteritctext = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        yenimusteriunvancombobox = new javax.swing.JComboBox<>();
        musteriolgeributon = new javax.swing.JButton();
        kayitistekbuton = new javax.swing.JButton();
        dogumyilicombobox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        yenimustericinsiyetcombobox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        yenimusterisifre = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("m????teri ol");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("YEN?? M????TER?? OLMA EKRANI");

        yenimusteriisimtext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yenimusteriisimtextActionPerformed(evt);
            }
        });

        jLabel2.setText("isim: ");

        jLabel4.setText("soy isim:");

        jLabel5.setText("tc no:");

        jLabel6.setText("unvan:");

        yenimusteriunvancombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "se??iniz", "????renci", "memur", "??ift??i", "doktor", "m??hendis", "????retmen", "i?? adam??", "di??er" }));

        musteriolgeributon.setText("geri");
        musteriolgeributon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musteriolgeributonActionPerformed(evt);
            }
        });

        kayitistekbuton.setText("kay??t iste??i g??nder");
        kayitistekbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kayitistekbutonActionPerformed(evt);
            }
        });

        dogumyilicombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950" }));
        dogumyilicombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dogumyilicomboboxActionPerformed(evt);
            }
        });

        jLabel8.setText("Do??um y??l??n??z: ");

        yenimustericinsiyetcombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cinsiyet", "erkek", "k??z", "belirtmek istemiyorum" }));

        jLabel9.setText("cinsiyet: ");

        jLabel10.setText("sifre: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(musteriolgeributon, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dogumyilicombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yenimustericinsiyetcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(yenimusteritctext)
                                    .addComponent(yenimusteriisimtext, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addGap(86, 86, 86)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kayitistekbuton)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(yenimusterisoyisimtext)
                                            .addComponent(yenimusteriunvancombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(yenimusterisifre, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(musteriolgeributon)
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yenimusteriisimtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yenimusterisoyisimtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yenimusteritctext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(yenimusteriunvancombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dogumyilicombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(yenimustericinsiyetcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(yenimusterisifre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(kayitistekbuton)
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void musteriolgeributonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musteriolgeributonActionPerformed
        MainPageGUI mp = new MainPageGUI();
        mp.setVisible(true);
        dispose();
    }//GEN-LAST:event_musteriolgeributonActionPerformed

    private void kayitistekbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kayitistekbutonActionPerformed
        if (yenimusteriisimtext.getText().length() == 0 || yenimusterisifre.getText().length() == 0||yenimusterisoyisimtext.getText().length()==0||
              yenimusteritctext.getText().length()==0|| yenimusteriunvancombobox.getSelectedIndex()==0|| yenimustericinsiyetcombobox.getSelectedIndex()==0) {
            JOptionPane.showMessageDialog(null, "l??tfen t??m alanlar?? eksiksiz doldurunuz! ");
        } else {
            Musteri m = new Musteri();
            String tc, isim, soyisim, sifre,unvan,cinsiyet;
            m.setIsim(yenimusteriisimtext.getText());
            m.setSoyisim(yenimusterisoyisimtext.getText());
            m.setTc(yenimusteritctext.getText());
            m.setSifre(yenimusterisifre.getText());
            m.setUnvan((String) yenimusteriunvancombobox.getSelectedItem());
            
            tc = yenimusteritctext.getText();
            isim = yenimusteriisimtext.getText();
            soyisim = yenimusterisoyisimtext.getText();
            sifre = yenimusterisifre.getText();
            if(yenimustericinsiyetcombobox.getSelectedIndex()==3){
                cinsiyet="bilgi yok";
            }
            else{
                cinsiyet = (String) yenimustericinsiyetcombobox.getSelectedItem();
            }
            String dogumyili= (String) dogumyilicombobox.getSelectedItem();
            unvan = m.getUnvan();
            try {
                m.yeniekle(tc, isim, soyisim, unvan,cinsiyet,dogumyili,sifre);
                JOptionPane.showMessageDialog(null, "m????teri olma iste??iniz kaydedilmi??tir onaylan??nca sisteme giri?? yapabilirsiniz te??ekk??r ederiz !");
            } catch (Exception ex) {
                Logger.getLogger(MusteriOlGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_kayitistekbutonActionPerformed

    private void yenimusteriisimtextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yenimusteriisimtextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yenimusteriisimtextActionPerformed

    private void dogumyilicomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dogumyilicomboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dogumyilicomboboxActionPerformed

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
            java.util.logging.Logger.getLogger(MusteriOlGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MusteriOlGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MusteriOlGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MusteriOlGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MusteriOlGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JComboBox<String> dogumyilicombobox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton kayitistekbuton;
    private javax.swing.JButton musteriolgeributon;
    private javax.swing.JComboBox<String> yenimustericinsiyetcombobox;
    private javax.swing.JTextField yenimusteriisimtext;
    private javax.swing.JPasswordField yenimusterisifre;
    private javax.swing.JTextField yenimusterisoyisimtext;
    private javax.swing.JTextField yenimusteritctext;
    private javax.swing.JComboBox<String> yenimusteriunvancombobox;
    // End of variables declaration//GEN-END:variables
}
