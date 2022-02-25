/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_wiev;

import Backend.Kasa;
import Backend.Log;
import Backend.Musteri;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import veritabani.Veritabani;
import static veritabani.Veritabani.conn;

/**
 *
 * @author yahya
 */
public class ParaTransferGUI extends javax.swing.JFrame {

    String tcno = null;
    int hesapnosu = 0;
    double para = 0;
    double aliciilkpara=0;
    String alicitcno=null;
    public ParaTransferGUI() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public ParaTransferGUI(Musteri m) {
        initComponents();
        setLocationRelativeTo(null);
        tcno = m.getTc();
        para = m.getVarlik();
       // System.out.println(tcno);
        String sorgu = "SELECT * FROM bireyselmusteri WHERE tc=?";
        Veritabani.baglan();
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setString(1, tcno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hesapnosu = rs.getInt("hesapno");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ParaTransferGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean boylebirhesapnovarmi(String s) {
        int hnumara = Integer.parseInt(s);
        Veritabani.baglan();
        Statement st;
        String sorgu="SELECT * FROM bireyselmusteri";
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sorgu);
            int temp=0;
            int kontrol=0;
            while(rs.next()){
                temp=rs.getInt("hesapno");
                //System.out.println(temp);
                if(temp==hnumara){
                    kontrol++;
                    break;
                }
            }
            if(kontrol!=0){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParaTransferGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    public double ilkpara(String s){
       int hnumara = Integer.parseInt(s);
        Veritabani.baglan();
        
        String sorgu="SELECT * FROM bireyselmusteri WHERE hesapno=?"; 
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setInt(1, hnumara);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aliciilkpara=rs.getDouble("varlik");
                alicitcno=rs.getString("tc");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParaTransferGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(aliciilkpara);
        return aliciilkpara;
    }
    public void paraaktar(double gidecekpara,double ilkpara,int hesapno){
        String sorgu = "UPDATE bireyselmusteri SET varlik=? WHERE hesapno=? ";
        Veritabani.baglan();
        
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setDouble(1,(gidecekpara+ilkpara));
            ps.setInt(2, hesapno);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParaTransferGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void paragidenhesapguncelle(double gidenpara){
        String sorgu = "UPDATE bireyselmusteri SET varlik=? WHERE hesapno=? ";
        Veritabani.baglan();
        
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setDouble(1, (para-gidenpara-5.0));
            ps.setInt(2, hesapnosu);
            ps.executeUpdate();
            Kasa.kasayaekle(5.0);
        } catch (SQLException ex) {
            Logger.getLogger(ParaTransferGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        alicihesapnotext = new javax.swing.JTextField();
        aliciadtext = new javax.swing.JTextField();
        alicisoyadtext = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        aciklamatext = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        gonderilecekparatext = new javax.swing.JTextField();
        gonderbuton = new javax.swing.JButton();
        cikisbuton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("para transfer");

        jLabel1.setText("para gönderilecek hesabın: ");

        jLabel2.setText("hesap no: ");

        jLabel3.setText("ad :");

        jLabel4.setText("soyad: ");

        jLabel5.setText("açıklama");

        jLabel6.setText("gönderilecek miktar : ");

        gonderilecekparatext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gonderilecekparatextActionPerformed(evt);
            }
        });

        gonderbuton.setText("gönder");
        gonderbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gonderbutonActionPerformed(evt);
            }
        });

        cikisbuton.setText("çıkış");
        cikisbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikisbutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(alicihesapnotext)
                            .addComponent(aliciadtext)
                            .addComponent(alicisoyadtext)
                            .addComponent(aciklamatext, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 87, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(gonderilecekparatext)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(gonderbuton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cikisbuton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(gonderilecekparatext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(alicihesapnotext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(aliciadtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alicisoyadtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(aciklamatext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cikisbuton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gonderbuton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cikisbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikisbutonActionPerformed
        try {
            Musteri m = new Musteri();
            m.setTc(tcno);
            m.setVarlik(para);
            MusteriGUI mg = new MusteriGUI(m);
            mg.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(ParaTransferGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cikisbutonActionPerformed

    private void gonderilecekparatextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gonderilecekparatextActionPerformed

    }//GEN-LAST:event_gonderilecekparatextActionPerformed

    private void gonderbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gonderbutonActionPerformed
        if (gonderilecekparatext.getText().length() == 0 || alicihesapnotext.getText().length() == 0 || aliciadtext.getText().length() == 0
                || alicisoyadtext.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen gerekli boşlukları doldurunuz");
        } else if (Double.parseDouble(gonderilecekparatext.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "lütfen geçerli bir miktar giriniz");
        } else if (Double.parseDouble(gonderilecekparatext.getText()) > para) {
            JOptionPane.showMessageDialog(null, "girilen miktar kadar paranız yok");
        } else if(!boylebirhesapnovarmi(alicihesapnotext.getText())){
            JOptionPane.showMessageDialog(null, "girilen hesap no ya ait bir hesap yok");
        }
        else{
            int res = JOptionPane.showConfirmDialog(null, "cekilecek olan miktarı onaylıyor musunuz", "onay", JOptionPane.YES_NO_OPTION);
            if(res==0){
                try {
                    double temp = Double.parseDouble(gonderilecekparatext.getText());
                    ilkpara(alicihesapnotext.getText());
                    paraaktar(temp,aliciilkpara,Integer.parseInt(alicihesapnotext.getText()));
                    paragidenhesapguncelle(temp);
                    JOptionPane.showMessageDialog(null, "işleminiz gerçekleşmiştir... bankamız 5.0 lira komisyon almıştır bilginize");
                    String log = "hesabınıza "+String.valueOf(hesapnosu)+" numaralı hesaptan"+String.valueOf(temp) + " lira kadar para "+aciklamatext.getText()+" açıklaması ile aktarıldı";
                    Log.logekle(alicitcno, log);
                    String log2 = String.valueOf(temp) + " kadar para hesabınızdan çıktı ";
                    Log.logekle(tcno, log2);
                    Musteri m = new Musteri();
                    m.setTc(tcno);
                    m.setVarlik(para-temp);
                    MusteriGUI mg = new MusteriGUI(m);
                    mg.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(ParaTransferGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_gonderbutonActionPerformed

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
            java.util.logging.Logger.getLogger(ParaTransferGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParaTransferGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParaTransferGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParaTransferGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParaTransferGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aciklamatext;
    private javax.swing.JTextField aliciadtext;
    private javax.swing.JTextField alicihesapnotext;
    private javax.swing.JTextField alicisoyadtext;
    private javax.swing.JButton cikisbuton;
    private javax.swing.JButton gonderbuton;
    private javax.swing.JTextField gonderilecekparatext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}