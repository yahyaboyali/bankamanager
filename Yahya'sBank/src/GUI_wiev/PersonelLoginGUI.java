/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_wiev;

import Backend.Personel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import veritabani.Veritabani;
import static veritabani.Veritabani.conn;

/**
 *
 * @author yahya
 */
public class PersonelLoginGUI extends javax.swing.JFrame {

    /**
     * Creates new form PersonelLoginGUI
     */
    public PersonelLoginGUI() {
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

        personeltctext = new javax.swing.JTextField();
        personelgirisbuton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        personelsifretext = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        personellogincıkısbuton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Personel Login");
        setPreferredSize(new java.awt.Dimension(300, 300));
        setResizable(false);

        personeltctext.setBackground(new java.awt.Color(255, 255, 255));
        personeltctext.setForeground(new java.awt.Color(0, 0, 0));
        personeltctext.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        personelgirisbuton.setText("giriş");
        personelgirisbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personelgirisbutonActionPerformed(evt);
            }
        });

        jLabel1.setText("T.C no:");

        jLabel2.setText("sifre: ");

        personelsifretext.setBackground(new java.awt.Color(255, 255, 255));
        personelsifretext.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setText("personel girişi");

        personellogincıkısbuton.setText("çıkış");
        personellogincıkısbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personellogincıkısbutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(personelgirisbuton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(personeltctext)
                                        .addComponent(personelsifretext, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 47, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(personellogincıkısbuton)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(personellogincıkısbuton)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(personeltctext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(personelsifretext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(personelgirisbuton)
                .addGap(67, 67, 67))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void personellogincıkısbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personellogincıkısbutonActionPerformed
        MainPageGUI mp = new MainPageGUI();
        mp.setVisible(true);
        dispose();
    }//GEN-LAST:event_personellogincıkısbutonActionPerformed

    private void personelgirisbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personelgirisbutonActionPerformed
        if (personeltctext.getText().length() == 0 || personelsifretext.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen gerekli alanları doldurun!!");
        }else if(personeltctext.getText().length() >11){
             JOptionPane.showMessageDialog(null, "lütfen geçerli bir tc  giriniz!!");
        }
        else if(personelsifretext.getText().length() > 30){
            JOptionPane.showMessageDialog(null, "lütfen geçerli şifre  giriniz!!");
        }
        else {
            //String sorgu = "SELECT * FROM personel WHERE tc=? AND sifre=?";
            Personel p = new Personel();
            p.setSifre(personelsifretext.getText());
            p.setTc(personeltctext.getText());
            
            if (!p.girisYap(personeltctext.getText(), personelsifretext.getText())) {
                JOptionPane.showMessageDialog(null, "tc veya şifre yanlış kontrol ediniz");

            } else {
                JOptionPane.showMessageDialog(null, "bilgiler doğru giriş yapılıyor");
                PersonelGUI pg = new PersonelGUI(p);
                pg.setVisible(true);
                dispose();
            }
        }


    }//GEN-LAST:event_personelgirisbutonActionPerformed

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
            java.util.logging.Logger.getLogger(PersonelLoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonelLoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonelLoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonelLoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PersonelLoginGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton personelgirisbuton;
    private javax.swing.JButton personellogincıkısbuton;
    private javax.swing.JPasswordField personelsifretext;
    private javax.swing.JTextField personeltctext;
    // End of variables declaration//GEN-END:variables
}
