/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_wiev;

import Backend.KrediisteyenMusteri;
import Backend.Kredikartiisteyenmusteri;
import Backend.Log;
import Backend.Personel;
import Backend.YeniMusteri;
import Backend.Yonetici;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import veritabani.Veritabani;
import static veritabani.Veritabani.conn;

/**
 *
 * @author yahya
 */
public class YoneticiGUI extends javax.swing.JFrame {

    String tcno = null;
    String sifre = null;

    public YoneticiGUI() {
        initComponents();
        setLocationRelativeTo(null);
        showlist();
        showkrediisteklist();
        showlistkredikartiisteyen();
    }

    public YoneticiGUI(Yonetici y) {
        initComponents();
        setLocationRelativeTo(null);
        showlist();
        showkrediisteklist();
        showlistkredikartiisteyen();
        secilen(personeltablo, secilentext, 2);
        secilen(krediistekleritablo, islenecektc, 0);
        secilen(kredikartiistektablo, secilentckredikarti, 0);
        tcno = y.getTc();
        sifre = y.getSifre();
    }

    public void secilen(JTable jt, JTextField textf, int x) {
        jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                textf.setText(jt.getValueAt(jt.getSelectedRow(), x).toString());
            }
        });
    }

    public ArrayList<Personel> listele() {
        ArrayList<Personel> liste = new ArrayList<>();
        Veritabani.baglan();
        String sorgu = "SELECT * FROM personel";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sorgu);
            Personel p;
            while (rs.next()) {
                p = new Personel(rs.getString("tc"), rs.getString("isim"), rs.getString("soyisim"), rs.getString("unvan"), rs.getDouble("maas"));
                liste.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    public void showlist() {
        ArrayList<Personel> liste = listele();
        DefaultTableModel model = (DefaultTableModel) personeltablo.getModel();
        model.setRowCount(0);
        Object[] satir = new Object[5];
        for (int i = 0; i < liste.size(); i++) {
            satir[0] = liste.get(i).getIsim();
            satir[1] = liste.get(i).getSoyisim();
            satir[2] = liste.get(i).getTc();
            satir[3] = liste.get(i).getUnvan();
            satir[4] = liste.get(i).getMaas();

            model.addRow(satir);

        }
    }

    public ArrayList<KrediisteyenMusteri> listelekrediistekleri() {
        ArrayList<KrediisteyenMusteri> list = new ArrayList<>();
        Veritabani.baglan();
        String sorgu = "SELECT * FROM kredibasvurulari";
        KrediisteyenMusteri m;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sorgu);
            while (rs.next()) {
                m = new KrediisteyenMusteri(rs.getString("tc"), rs.getInt("miktar"), rs.getInt("taksitsayisi"), rs.getString("isim"), rs.getString("aylikgelir"), rs.getString("kredituru"));
                list.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void showkrediisteklist() {
        ArrayList<KrediisteyenMusteri> liste = listelekrediistekleri();
        DefaultTableModel model = (DefaultTableModel) krediistekleritablo.getModel();
        model.setRowCount(0);
        Object[] satir = new Object[6];
        for (int i = 0; i < liste.size(); i++) {
            satir[0] = liste.get(i).getTc();
            satir[1] = liste.get(i).getMiktar();
            satir[2] = liste.get(i).getTaksitsayisi();
            satir[3] = liste.get(i).getIsim();
            satir[4] = liste.get(i).getGelir();
            satir[5] = liste.get(i).getKredituru();
            model.addRow(satir);

        }
    }

    public ArrayList<Kredikartiisteyenmusteri> lsitelekredikartiistekleri() {
        ArrayList<Kredikartiisteyenmusteri> liste = new ArrayList<>();
        Veritabani.baglan();
        String sorgu = "SELECT * FROM kredikartibasvurulari";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sorgu);
            Kredikartiisteyenmusteri m;
            while (rs.next()) {
                m = new Kredikartiisteyenmusteri(rs.getString("tc"), rs.getString("isim"), rs.getString("kartturu"), rs.getDouble("gelir"), rs.getString("unvan"));
                liste.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    public void showlistkredikartiisteyen() {
        ArrayList<Kredikartiisteyenmusteri> liste = lsitelekredikartiistekleri();
        DefaultTableModel model = (DefaultTableModel) kredikartiistektablo.getModel();
        model.setRowCount(0);
        Object[] satir = new Object[5];
        for (int i = 0; i < liste.size(); i++) {
            satir[0] = liste.get(i).getTc();
            satir[1] = liste.get(i).getIsim();
            satir[2] = liste.get(i).getKartturu();
            satir[3] = liste.get(i).getGelir();
            satir[4] = liste.get(i).getUnvan();
            model.addRow(satir);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        personeltablo = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        secilentext = new javax.swing.JTextField();
        zambuton = new javax.swing.JButton();
        terfibuton = new javax.swing.JButton();
        kovbuton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        personeladtext = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        personelsoyadtext = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        personeltctext = new javax.swing.JTextField();
        personelunvancombobox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        personelmaastext = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        personelsifretext = new javax.swing.JPasswordField();
        eklebuton = new javax.swing.JButton();
        cikisbuton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        krediistekleritablo = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        islenecektc = new javax.swing.JTextField();
        silbuton = new javax.swing.JButton();
        onaylabuton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        kredikartiistektablo = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        secilentckredikarti = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        kredikartionay = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        mevcutsifretext = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        yenisifretext = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        yenisifreonaytext = new javax.swing.JPasswordField();
        sifredegistirbuton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("yönetici ");

        personeltablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "isim", "soy isim", "tc", "maas", "unvan"
            }
        ));
        jScrollPane1.setViewportView(personeltablo);

        jLabel1.setText("seçilen: ");

        zambuton.setText("zam yap");
        zambuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zambutonActionPerformed(evt);
            }
        });

        terfibuton.setText("terfi et");
        terfibuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terfibutonActionPerformed(evt);
            }
        });

        kovbuton.setText("işten çıkar");

        jLabel2.setText("Personel ekleme ");

        jLabel3.setText("adı: ");

        jLabel4.setText("soy ad:");

        jLabel5.setText("tcno:");

        personelunvancombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ünvanı", "personel", "müdür" }));

        jLabel6.setText("maaş:");

        jLabel7.setText("geçici şifre:");

        eklebuton.setText("ekle");
        eklebuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eklebutonActionPerformed(evt);
            }
        });

        cikisbuton.setText("çıkış");
        cikisbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikisbutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(secilentext))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(zambuton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(kovbuton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(terfibuton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(personeladtext)
                                    .addComponent(personelsoyadtext)
                                    .addComponent(personeltctext)
                                    .addComponent(personelunvancombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(personelmaastext)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(personelsifretext))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cikisbuton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eklebuton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(secilentext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zambuton)
                    .addComponent(terfibuton)
                    .addComponent(kovbuton))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(personeladtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(personelsoyadtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(personeltctext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(personelunvancombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(personelmaastext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(personelsifretext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eklebuton)
                    .addComponent(cikisbuton))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("personel işlemleri", jPanel1);

        krediistekleritablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "tc", "miktar", "taksit sayısı", "isim", "aylık gelir", "kredi türü"
            }
        ));
        jScrollPane2.setViewportView(krediistekleritablo);

        jLabel8.setText("işlenecek tc: ");

        silbuton.setText("sil");
        silbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                silbutonActionPerformed(evt);
            }
        });

        onaylabuton.setText("onayla");
        onaylabuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onaylabutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(islenecektc)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(silbuton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addComponent(onaylabuton)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(islenecektc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(silbuton)
                    .addComponent(onaylabuton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("kredi istekleri", jPanel2);

        kredikartiistektablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "tc", "isim", "kart türü", "gelir", "unvan"
            }
        ));
        jScrollPane3.setViewportView(kredikartiistektablo);

        jLabel9.setText("işlem görecek tc: ");

        jButton3.setText("sil");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        kredikartionay.setText("onayla");
        kredikartionay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kredikartionayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(secilentckredikarti))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                                .addComponent(kredikartionay)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(secilentckredikarti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(kredikartionay))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("kredi kartı işlemleri", jPanel3);

        jLabel10.setText("şifre değiştir: ");

        jLabel11.setText("mevcut şifre:");

        jLabel12.setText("yeni şifre: ");

        jLabel13.setText("şifre tekrar: ");

        sifredegistirbuton.setText("şifre değiştir");
        sifredegistirbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sifredegistirbutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(sifredegistirbuton)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(mevcutsifretext, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addComponent(yenisifretext)
                                .addComponent(yenisifreonaytext)))))
                .addContainerGap(494, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(mevcutsifretext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(yenisifretext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yenisifreonaytext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(sifredegistirbuton)
                .addContainerGap(245, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("kişisel işlemler", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eklebutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eklebutonActionPerformed
        if (personeladtext.getText().length() == 0 || personelsoyadtext.getText().length() == 0 || personeltctext.getText().length() == 0
                || personelunvancombobox.getSelectedIndex() == 0 || personelmaastext.getText().length() == 0 || personelsifretext.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen tüm alanları eksiksiz doldurunuz...!");
        } else {
            String sorgu = "INSERT INTO personel (tc,isim,soyisim,unvan,maas,sifre) VALUES (?,?,?,?,?,?)";
            Veritabani.baglan();
            try {
                PreparedStatement ps = conn.prepareStatement(sorgu);
                ps.setString(1, personeltctext.getText());
                ps.setString(2, personeladtext.getText());
                ps.setString(3, personelsoyadtext.getText());
                ps.setString(4, (String) personelunvancombobox.getSelectedItem());
                ps.setDouble(5, Double.parseDouble(personelmaastext.getText()));
                ps.setString(6, personelsifretext.getText());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(YoneticiGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "personel eklendi!");
            Yonetici y = new Yonetici();
            y.setTc(tcno);
            YoneticiGUI yg = new YoneticiGUI(y);
            yg.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_eklebutonActionPerformed

    private void cikisbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikisbutonActionPerformed
        new MainPageGUI().setVisible(true);
        dispose();
    }//GEN-LAST:event_cikisbutonActionPerformed

    private void onaylabutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onaylabutonActionPerformed
        Yonetici y = new Yonetici();
        y.setTc(tcno);
        double para = 0;
        String sorgu = "SELECT * FROM bireyselmusteri WHERE tc=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setString(1, islenecektc.getText());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                para = rs.getDouble("varlik");
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MusteriLoginGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (y.kredionayla(islenecektc.getText(), para)) {
            JOptionPane.showMessageDialog(null, "kredi onaylandı! ");
            String log = "kredi talebiniz onaylandı";
            Log.logekle(islenecektc.getText(), log);
//            String log2 = "hesabınıza krediden " + String.valueOf(para) + " kadar para aktarılmıştır";
//            Log.logekle(islenecektc.getText(), log2);
            YoneticiGUI yg = new YoneticiGUI(y);
            yg.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "işlem iptal edildi! ");
            YoneticiGUI yg = new YoneticiGUI(y);
            yg.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_onaylabutonActionPerformed

    private void kredikartionayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kredikartionayActionPerformed
        Yonetici y = new Yonetici();
        y.setTc(tcno);
        int res = JOptionPane.showConfirmDialog(null, "işlemi onaylıyor musunuz?", "onay", JOptionPane.YES_NO_OPTION);
        if (res == 0) {
            try {
                if (!y.kredikartionayla(secilentckredikarti.getText())) {
                    JOptionPane.showMessageDialog(null, "işlem gerçekleşemedi!!");
                } else {
                    JOptionPane.showMessageDialog(null, "işleminiz gerçekleşti!!");
                    String log = "kredi kartı talebiniz onaylandı";
                    Log.logekle(secilentckredikarti.getText(), log);
                    YoneticiGUI yg = new YoneticiGUI(y);
                    yg.setVisible(true);
                    dispose();
                }
            } catch (SQLException ex) {
                Logger.getLogger(YoneticiGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "işlem gerçekleşemedi!!");
            YoneticiGUI yg = new YoneticiGUI(y);
            yg.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_kredikartionayActionPerformed

    private void zambutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zambutonActionPerformed
        String x = JOptionPane.showInputDialog(null, "miktar");
        Yonetici y = new Yonetici();
        int res = JOptionPane.showConfirmDialog(null, "işlemi onaylıyor musunuz?", "onay", JOptionPane.YES_NO_OPTION);
        if (res == 0) {
            y.zamYap(secilentext.getText(), Integer.parseInt(x));
            JOptionPane.showMessageDialog(null, "işleminiz gerçekleşti!!");
            YoneticiGUI yg = new YoneticiGUI(y);
            yg.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "işleminiz iptal edildi!!");
            YoneticiGUI yg = new YoneticiGUI(y);
            yg.setVisible(true);
            dispose();
        }


    }//GEN-LAST:event_zambutonActionPerformed

    private void sifredegistirbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sifredegistirbutonActionPerformed
        if (mevcutsifretext.getText().length() == 0 || yenisifretext.getText().length() == 0 || yenisifreonaytext.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen boşlukları dolduralım");
        } else if (!mevcutsifretext.getText().equals(sifre)) {
            JOptionPane.showMessageDialog(null, "mevcut şifre yanlış!");
        } else if (!yenisifretext.getText().equals(yenisifreonaytext.getText())) {
            JOptionPane.showMessageDialog(null, "yeni sifre ile tekrarı uyuşmuyor");
        } else {
            Yonetici y = new Yonetici();
            y.setTc(tcno);

            y.sifreDegistir(yenisifretext.getText(), y.getTc());
            JOptionPane.showMessageDialog(null, "şifreniz değişti!");
            y.setSifre(yenisifretext.getText());
            YoneticiGUI yg = new YoneticiGUI(y);
            yg.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_sifredegistirbutonActionPerformed

    private void terfibutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terfibutonActionPerformed
        Yonetici y = new Yonetici();
        Personel p = new Personel();
        p.setTc(secilentext.getText());
        String yeniyetki;
        yeniyetki = JOptionPane.showInputDialog(null, "yeni yetki ( mudur)");
        int res = JOptionPane.showConfirmDialog(null, "işlemi onaylıyor musunuz?", "onay", JOptionPane.YES_NO_OPTION);
        if (res == 0) {
            y.yetkiVer(p, yeniyetki);
            JOptionPane.showMessageDialog(null, "işleminiz gerçekleşti!!");
            YoneticiGUI yg = new YoneticiGUI(y);
            yg.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "işleminiz iptal edildi!!");
            YoneticiGUI yg = new YoneticiGUI(y);
            yg.setVisible(true);
            dispose();
        }

    }//GEN-LAST:event_terfibutonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            Veritabani.baglan();
            String sorgu = "DELETE FROM kredikartibasvurulari WHERE tc=?";
            int res = JOptionPane.showConfirmDialog(null, "silinecek onaylıyor musunuz", "uyarı", JOptionPane.YES_NO_OPTION);
            if (res == 0) {
                PreparedStatement ps = conn.prepareStatement(sorgu);
                ps.setString(1, secilentckredikarti.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "kredi karti isteği silindi");
                String log = "kredi karti isteğiniz personel tarafından silindi";
                Log.logekle(secilentckredikarti.getText(), log);
                Yonetici y = new Yonetici();
                YoneticiGUI yg = new YoneticiGUI(y);
                yg.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "işlem iptal edildi.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void silbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_silbutonActionPerformed
        try {
            Veritabani.baglan();
            String sorgu = "DELETE FROM kredibasvurulari WHERE tc=?";
            int res = JOptionPane.showConfirmDialog(null, "silinecek onaylıyor musunuz", "uyarı", JOptionPane.YES_NO_OPTION);
            if (res == 0) {
                PreparedStatement ps = conn.prepareStatement(sorgu);
                ps.setString(1, islenecektc.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "kredi isteği silindi");
                String log = "kredi isteğiniz personel tarafından silindi";
                Log.logekle(islenecektc.getText(), log);
                Yonetici y = new Yonetici();
                YoneticiGUI yg = new YoneticiGUI(y);
                yg.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "işlem iptal edildi.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_silbutonActionPerformed

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
            java.util.logging.Logger.getLogger(YoneticiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(YoneticiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(YoneticiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(YoneticiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new YoneticiGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cikisbuton;
    private javax.swing.JButton eklebuton;
    private javax.swing.JTextField islenecektc;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton kovbuton;
    private javax.swing.JTable krediistekleritablo;
    private javax.swing.JTable kredikartiistektablo;
    private javax.swing.JButton kredikartionay;
    private javax.swing.JPasswordField mevcutsifretext;
    private javax.swing.JButton onaylabuton;
    private javax.swing.JTextField personeladtext;
    private javax.swing.JTextField personelmaastext;
    private javax.swing.JPasswordField personelsifretext;
    private javax.swing.JTextField personelsoyadtext;
    private javax.swing.JTable personeltablo;
    private javax.swing.JTextField personeltctext;
    private javax.swing.JComboBox<String> personelunvancombobox;
    private javax.swing.JTextField secilentckredikarti;
    private javax.swing.JTextField secilentext;
    private javax.swing.JButton sifredegistirbuton;
    private javax.swing.JButton silbuton;
    private javax.swing.JButton terfibuton;
    private javax.swing.JPasswordField yenisifreonaytext;
    private javax.swing.JPasswordField yenisifretext;
    private javax.swing.JButton zambuton;
    // End of variables declaration//GEN-END:variables
}
