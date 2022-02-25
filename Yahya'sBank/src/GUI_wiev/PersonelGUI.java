/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_wiev;

import Backend.KrediisteyenMusteri;
import Backend.Kredikartiisteyenmusteri;
import Backend.Log;
import Backend.Musteri;

import Backend.Personel;
import Backend.YeniMusteri;
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
import static veritabani.Veritabani.*;

/**
 *
 * @author yahya
 */
public class PersonelGUI extends javax.swing.JFrame {

    public PersonelGUI() {
        initComponents();
        showlist();
        secilen(jTable1, secilentext, 0);
        secilen(musteritablosu, silinecekmusteritc, 2);
        secilen(krediistektablosu, silinecekbasvurutext, 0);
        secilen(kredikartibasvurutable, kredikartiisteyentctext, 0);
        showlistmusteri();
        showkrediisteklist();
        showlistkredikartiisteyen();
        setLocationRelativeTo(null);

    }

    public PersonelGUI(Personel p) {
        initComponents();
        showlist();
        secilen(jTable1, secilentext, 0);
        secilen(musteritablosu, silinecekmusteritc, 2);
        secilen(krediistektablosu, silinecekbasvurutext, 0);
        secilen(kredikartibasvurutable, kredikartiisteyentctext, 0);
        showlistmusteri();
        showkrediisteklist();
        showlistkredikartiisteyen();
        setLocationRelativeTo(null);

    }

    public ArrayList<YeniMusteri> listele() {
        ArrayList<YeniMusteri> liste = new ArrayList<>();
        Veritabani.baglan();
        String sorgu = "SELECT * FROM musteriistekleri";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sorgu);
            YeniMusteri ym;
            while (rs.next()) {
                ym = new YeniMusteri(rs.getString("tcno"), rs.getString("isim"), rs.getString("soyisim"), rs.getString("unvan"), rs.getString("cinsiyet"), rs.getInt("dogumyili"));
                liste.add(ym);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    public ArrayList<Musteri> listelemusteri() {
        ArrayList<Musteri> liste = new ArrayList<>();
        Veritabani.baglan();
        String sorgu = "SELECT * FROM bireyselmusteri";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sorgu);
            Musteri m;
            while (rs.next()) {
                m = new Musteri(rs.getString("isim"), rs.getString("soyisim"), rs.getString("tc"), rs.getInt("hesapno"));
                liste.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
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
        DefaultTableModel model = (DefaultTableModel) kredikartibasvurutable.getModel();
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

    public void showlistmusteri() {
        ArrayList<Musteri> liste = listelemusteri();
        DefaultTableModel model = (DefaultTableModel) musteritablosu.getModel();
        model.setRowCount(0);
        Object[] satir = new Object[4];
        for (int i = 0; i < liste.size(); i++) {

            satir[0] = liste.get(i).getIsim();
            satir[1] = liste.get(i).getSoyisim();
            satir[2] = liste.get(i).getTc();
            satir[3] = liste.get(i).getHesapno();
            model.addRow(satir);
        }
    }

    public void showlist() {
        ArrayList<YeniMusteri> liste = listele();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        Object[] satir = new Object[6];
        for (int i = 0; i < liste.size(); i++) {
            satir[0] = liste.get(i).getTc();
            satir[1] = liste.get(i).getIsim();
            satir[2] = liste.get(i).getSoyisim();
            satir[3] = liste.get(i).getUnvan();
            satir[4] = liste.get(i).getCinsiyet();
            satir[5] = liste.get(i).getDogumyili();
            model.addRow(satir);

        }
    }

    public void showkrediisteklist() {
        ArrayList<KrediisteyenMusteri> liste = listelekrediistekleri();
        DefaultTableModel model = (DefaultTableModel) krediistektablosu.getModel();
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

    public void secilen(JTable jt, JTextField textf, int x) {
        jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                textf.setText(jt.getValueAt(jt.getSelectedRow(), x).toString());
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        listelebuton = new javax.swing.JButton();
        secilentext = new javax.swing.JTextField();
        onayla = new javax.swing.JButton();
        silmebuton = new javax.swing.JButton();
        personelcıkısbuton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        musteritablosu = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        yenimusteriadtext = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        yenimusterisoyadtext = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        yenimusteritctext = new javax.swing.JTextField();
        yenimustericinsiyetcombobox = new javax.swing.JComboBox<>();
        yenimusteridogumyilicombobox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        yenimusterisifretext = new javax.swing.JPasswordField();
        musterikaydet = new javax.swing.JButton();
        cikisbutonpersonel = new javax.swing.JButton();
        yenimusteriunvancombobox = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        silinecekmusteritc = new javax.swing.JTextField();
        musterisil = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        krediistektablosu = new javax.swing.JTable();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        silinecekbasvurutext = new javax.swing.JTextField();
        silbuton = new javax.swing.JButton();
        cikisbuton = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tcnotext = new javax.swing.JTextField();
        kreditutaricombobox = new javax.swing.JComboBox<>();
        kretiturucombobox = new javax.swing.JComboBox<>();
        taksitsayisicombobox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        aylikgelirtext = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        isimtext = new javax.swing.JTextField();
        basvurueklebuton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        kredikartibasvurutable = new javax.swing.JTable();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        kredikartiisteyentctext = new javax.swing.JTextField();
        kredikartibasvurusil = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        kredikartiisteyenisimtext = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        kredikartiisteyentc = new javax.swing.JTextField();
        kartturucombobox = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        aylikgelirtextbox = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        unvan = new javax.swing.JTextField();
        basvurbuton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        kredikartitctext = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        yenilimittext = new javax.swing.JTextField();
        limitonaylabuton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("personel");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "tcno", "isim", "soy isim", "unvan", "cinsiyet", "dogum yılı"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        listelebuton.setText("yenile");
        listelebuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listelebutonActionPerformed(evt);
            }
        });

        onayla.setText("onayla");
        onayla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onaylaActionPerformed(evt);
            }
        });

        silmebuton.setText("sil");
        silmebuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                silmebutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listelebuton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secilentext, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(onayla, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(silmebuton)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(listelebuton)
                .addGap(41, 41, 41)
                .addComponent(secilentext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(onayla)
                    .addComponent(silmebuton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        personelcıkısbuton.setText("çıkış");
        personelcıkısbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personelcıkısbutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(personelcıkısbuton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(personelcıkısbuton)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("musteri başvuruları", jPanel1);

        musteritablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "isim", "soyisim", "tcno", "hesapno"
            }
        ));
        jScrollPane2.setViewportView(musteritablosu);

        jLabel1.setText("müşteri adı:");

        jLabel2.setText("müşteri soy adı:");

        jLabel3.setText("müşteri tc no:");

        yenimustericinsiyetcombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cinsiyet", "Bayan", "Erkek", "Belirtmek istemiyorum" }));
        yenimustericinsiyetcombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yenimustericinsiyetcomboboxActionPerformed(evt);
            }
        });

        yenimusteridogumyilicombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "doğum yılı", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", " " }));

        jLabel5.setText("şifre: ");

        musterikaydet.setText("kaydet");
        musterikaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musterikaydetActionPerformed(evt);
            }
        });

        cikisbutonpersonel.setText("çıkış");
        cikisbutonpersonel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikisbutonpersonelActionPerformed(evt);
            }
        });

        yenimusteriunvancombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "seçiniz", "öğrenci", "memur", "çiftçi", "doktor", "mühendis", "öğretmen", "iş adamı", "diğer" }));

        jLabel18.setText("ünvan");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel18)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(yenimusterisifretext, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(yenimusteritctext)
                            .addComponent(yenimusteriadtext)
                            .addComponent(yenimusterisoyadtext)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(yenimusteridogumyilicombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 19, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(yenimustericinsiyetcombobox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(cikisbutonpersonel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(musterikaydet))))
                            .addComponent(yenimusteriunvancombobox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yenimusteriadtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yenimusterisoyadtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yenimusteritctext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yenimusteriunvancombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(yenimustericinsiyetcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(yenimusteridogumyilicombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yenimusterisifretext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cikisbutonpersonel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(musterikaydet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("müşteri ekle", jPanel4);

        jLabel6.setText("silinecek müşteri tc no: ");

        musterisil.setText("sil");
        musterisil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musterisilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(silinecekmusteritc))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(musterisil, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(silinecekmusteritc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(musterisil)
                .addContainerGap(310, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("müşteri sil", jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jTabbedPane2))
                .addContainerGap())
        );

        jTabbedPane2.getAccessibleContext().setAccessibleName("");

        jTabbedPane1.addTab("musteri işlemleri", jPanel3);

        krediistektablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "tcno", "miktar", "taksitsayısı", "isim", "aylık gelir", "kredi türü"
            }
        ));
        jScrollPane3.setViewportView(krediistektablosu);

        jLabel10.setText("silinecek başvuru: ");

        silbuton.setText("sil");
        silbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                silbutonActionPerformed(evt);
            }
        });

        cikisbuton.setText("çıkış");
        cikisbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikisbutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 63, Short.MAX_VALUE))
                    .addComponent(silinecekbasvurutext)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(silbuton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cikisbuton, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(silinecekbasvurutext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(silbuton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                .addComponent(cikisbuton)
                .addContainerGap())
        );

        jTabbedPane3.addTab("istek işlemleri", jPanel9);

        jLabel7.setText("tcno:");

        kreditutaricombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kredi tutarı", "3000", "5000", "10000", "50000", "100000", "200000" }));

        kretiturucombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kredi türünü seçiniz", "ihtiyaç kredisi", "konut kredisi", "taşıt kredisi", "ziraa ikredi", " " }));

        taksitsayisicombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "taksit sayısı", "3(%1.05 faiz)", "6(%1.1 faiz)", "12(%1.4 faiz)", "24(%1.6 faiz)", "36(%1.8 faiz)" }));

        jLabel8.setText("aylık sabit geliriniz: ");

        jLabel9.setText("isim: ");

        basvurueklebuton.setText("başvuru ekle");
        basvurueklebuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basvurueklebutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kreditutaricombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(tcnotext))
                    .addComponent(kretiturucombobox, 0, 169, Short.MAX_VALUE)
                    .addComponent(taksitsayisicombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(aylikgelirtext)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(isimtext))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(basvurueklebuton)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(isimtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tcnotext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(kreditutaricombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(kretiturucombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(taksitsayisicombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aylikgelirtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(basvurueklebuton)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("kredi ekle", jPanel7);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jTabbedPane3))
                .addContainerGap())
        );

        jTabbedPane1.addTab("kredi islemleri", jPanel6);

        kredikartibasvurutable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "tc ", "isim", "kart türü", "gelir", "ünvan"
            }
        ));
        jScrollPane4.setViewportView(kredikartibasvurutable);

        jLabel11.setText("isteyen tc:");

        kredikartibasvurusil.setText("sil");
        kredikartibasvurusil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kredikartibasvurusilActionPerformed(evt);
            }
        });

        jButton3.setText("çıkış");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(kredikartiisteyentctext)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 93, Short.MAX_VALUE)
                        .addComponent(kredikartibasvurusil, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kredikartiisteyentctext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(kredikartibasvurusil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                .addComponent(jButton3))
        );

        jTabbedPane4.addTab("istek", jPanel11);

        jLabel12.setText("isim: ");

        jLabel14.setText("tc no:");

        kartturucombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kart türü", "üni kart", "çiftçi kart", "normal kart" }));

        jLabel15.setText("gelir");

        jLabel16.setText("ünvan: ");

        basvurbuton.setText("başvuru ekle");
        basvurbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basvurbutonActionPerformed(evt);
            }
        });

        jButton5.setText("çıkış");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kredikartiisteyentc)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(basvurbuton))
                            .addComponent(jLabel14))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kredikartiisteyenisimtext)
                            .addComponent(kartturucombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(aylikgelirtextbox)
                            .addComponent(unvan)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kredikartiisteyenisimtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kredikartiisteyentc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(kartturucombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(aylikgelirtextbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(unvan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(basvurbuton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("kredi kartı ver", jPanel12);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jTabbedPane4))
                .addContainerGap())
        );

        jTabbedPane1.addTab("kredi kartı başvuruları", jPanel10);

        jLabel13.setText("tc : ");

        jLabel17.setText("yeni limit:");

        limitonaylabuton.setText("onayla");
        limitonaylabuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limitonaylabutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(limitonaylabuton)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel13))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(kredikartitctext, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(yenilimittext))))
                .addContainerGap(320, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(kredikartitctext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(yenilimittext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(limitonaylabuton)
                .addContainerGap(250, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("k kartı limit işlem", jPanel13);

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

        jTabbedPane1.getAccessibleContext().setAccessibleName("islemler");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listelebutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listelebutonActionPerformed
        PersonelGUI pg = new PersonelGUI();
        pg.setVisible(true);
        dispose();
    }//GEN-LAST:event_listelebutonActionPerformed

    private void personelcıkısbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personelcıkısbutonActionPerformed
        MainPageGUI mp = new MainPageGUI();
        mp.setVisible(true);
        dispose();
    }//GEN-LAST:event_personelcıkısbutonActionPerformed

    private void onaylaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onaylaActionPerformed

        Veritabani.baglan();
        String sorgu = "DELETE FROM musteriistekleri WHERE tcno=?";
        String sorgu2 = "INSERT INTO bireyselmusteri(tc,isim,varlik,sifre,hesapno,soyisim,unvan)VALUES (?,?,?,?,?,?,?)";
        String verial = "SELECT * FROM musteriistekleri WHERE tcno=?";
        String isim = "yahya";

        try {
            Musteri m = new Musteri();

            PreparedStatement ps1 = conn.prepareStatement(verial);
            ps1.setString(1, secilentext.getText());
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                m.setTc(rs.getString("tcno"));
                m.setIsim(rs.getString("isim"));
                m.setSoyisim(rs.getString("soyisim"));
                m.setSifre(rs.getString("sifre"));
                m.setUnvan(rs.getString("unvan"));
            }
//            String tablo = "CREATE TABLE "+""+m.getIsim()+"" + "(numara int PRIMARY KEY NOT NULL,log varchar(255)";
//            PreparedStatement ptablo = conn.prepareStatement(tablo);
//            ptablo.executeUpdate();
            Statement st = conn.createStatement();
            ResultSet rsm = st.executeQuery("SELECT * FROM bireyselmusteri"); // hesap numarası belirlemek için
            int hesapno = 0;
            while (rsm.next()) {
                hesapno = rsm.getInt("hesapno");
            }
            //System.out.println(hesapno);
            PreparedStatement ps = conn.prepareStatement(sorgu2);
            ps.setString(1, m.getTc());
            ps.setString(2, m.getIsim());
            ps.setDouble(3, 0.0);
            ps.setString(4, m.getSifre());
            ps.setInt(5, hesapno + 1);
            ps.setString(6, m.getSoyisim());
            ps.setString(7, m.getUnvan());
            ps.executeUpdate();
            PreparedStatement psilme = conn.prepareStatement(sorgu);
            psilme.setString(1, secilentext.getText());
            psilme.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PersonelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        PersonelGUI pg = new PersonelGUI();
        pg.setVisible(true);
        dispose();
    }//GEN-LAST:event_onaylaActionPerformed

    private void silmebutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_silmebutonActionPerformed
        Veritabani.baglan();
        String sorgu = "DELETE FROM musteriistekleri WHERE tcno=?";
        PreparedStatement psilme;
        try {
            psilme = conn.prepareStatement(sorgu);
            psilme.setString(1, secilentext.getText());
            psilme.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_silmebutonActionPerformed

    private void yenimustericinsiyetcomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yenimustericinsiyetcomboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yenimustericinsiyetcomboboxActionPerformed

    private void musterikaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musterikaydetActionPerformed
        String isim, soyisim, tc, unvan, cinsiyet, sifre, sorgu;
        int dogumyili;
        isim = yenimusteriadtext.getText();
        soyisim = yenimusterisoyadtext.getText();
        tc = yenimusteritctext.getText();
        unvan = "diger";
        if (yenimusteriunvancombobox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen geçerli bir üvan seçiniz!");
        } else {
            unvan = (String) yenimusteriunvancombobox.getSelectedItem();
        }

        cinsiyet = (String) yenimustericinsiyetcombobox.getSelectedItem();
        dogumyili = Integer.parseInt((String) yenimusteridogumyilicombobox.getSelectedItem());
        sifre = yenimusterisifretext.getText();
        sorgu = "INSERT INTO bireyselmusteri (tc,isim,varlik,sifre,hesapno,soyisim,unvan) VALUES (?,?,?,?,?,?,?)";
        Musteri m = new Musteri();
        m.yeniekle(isim, soyisim, tc, unvan, cinsiyet, dogumyili, sifre, sorgu, unvan);
        JOptionPane.showMessageDialog(null, "musteri eklendi! ");
        PersonelGUI pg = new PersonelGUI();
        pg.setVisible(true);
        dispose();
    }//GEN-LAST:event_musterikaydetActionPerformed

    private void cikisbutonpersonelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikisbutonpersonelActionPerformed
        MainPageGUI mp = new MainPageGUI();
        mp.setVisible(true);
        dispose();
    }//GEN-LAST:event_cikisbutonpersonelActionPerformed

    private void musterisilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musterisilActionPerformed
        try {
            Veritabani.baglan();
            String sorgu = "DELETE FROM bireyselmusteri WHERE tc=?";
            int res = JOptionPane.showConfirmDialog(null, "silinecek onaylıyor musunuz", "uyarı", JOptionPane.YES_NO_OPTION);
            if (res == 0) {
                PreparedStatement ps = conn.prepareStatement(sorgu);
                ps.setString(1, silinecekmusteritc.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "müşteri silindi");
                PersonelGUI pg = new PersonelGUI();
                pg.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "işlem iptal edildi.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_musterisilActionPerformed

    private void basvurueklebutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basvurueklebutonActionPerformed
        if (kretiturucombobox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen kredi türünü seçiniz");
        } else if (aylikgelirtext.getText().length() == 0 || Integer.parseInt(aylikgelirtext.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "lütfen geçerli bir aylık gelir giriniz");
        } else if (taksitsayisicombobox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen taksit sayısını seçiniz! ");
        } else if (kreditutaricombobox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen kredi tutarını belirleyiniz! ");
        } else {
            int tutar = Integer.parseInt((String) kreditutaricombobox.getSelectedItem());
            int sure = taksitsayisicombobox.getSelectedIndex();
            int odemesuresi = 0;

            double odenecekmiktar = 0;
            switch (sure) {
                case 1:
                    odenecekmiktar = (tutar * 1.05 / 100) + tutar;
                    odemesuresi = 3;
                    break;
                case 2:
                    odenecekmiktar = (tutar * 1.1 / 100) + tutar;
                    odemesuresi = 6;
                    break;
                case 3:
                    odenecekmiktar = (tutar * 1.4 / 100) + tutar;
                    odemesuresi = 12;
                    break;
                case 4:
                    odenecekmiktar = (tutar * 1.6 / 100) + tutar;
                    odemesuresi = 24;
                    break;
                case 5:
                    odenecekmiktar = (tutar * 1.6 / 100) + tutar;
                    odemesuresi = 36;
                    break;
                default:
                    odenecekmiktar = tutar;
            }

            String mesaj = "kreti tutarınız: " + Integer.toString(tutar) + " ödeyeceğiniz miktar: " + Double.toString(odenecekmiktar)
                    + " ödenecek süre: " + Integer.toString(odemesuresi) + " onaylıyor musunuz ?";
            int res = JOptionPane.showConfirmDialog(null, mesaj, "baslik", JOptionPane.YES_NO_OPTION);
            int kreditutari = kreditutaricombobox.getSelectedIndex();
            int kredimiktar = 0;
            switch (kreditutari) {
                case 1:
                    kredimiktar = 3000;
                    break;
                case 2:
                    kredimiktar = 5000;
                    break;
                case 3:
                    kredimiktar = 10000;
                    break;
                case 4:
                    kredimiktar = 50000;
                    break;
                case 5:
                    kredimiktar = 100000;
                    break;
                case 6:
                    kredimiktar = 200000;
                    break;
                default:
                    kredimiktar = 0;
            }

            if (res == 0) {
                Musteri m = new Musteri();
                m.kredibasvurusu(tcnotext.getText(), isimtext.getText(), (String) kretiturucombobox.getSelectedItem(), aylikgelirtext.getText(), odemesuresi, kredimiktar);
                JOptionPane.showMessageDialog(null, "başvurunuz iletilmiştir, ileti durumunu size bildireceğiz");
                PersonelGUI pg = new PersonelGUI();
                pg.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "işleminiz iptal edildi");
            }
        }
    }//GEN-LAST:event_basvurueklebutonActionPerformed

    private void silbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_silbutonActionPerformed
        try {
            Veritabani.baglan();
            String sorgu = "DELETE FROM kredibasvurulari WHERE tc=?";
            int res = JOptionPane.showConfirmDialog(null, "silinecek onaylıyor musunuz", "uyarı", JOptionPane.YES_NO_OPTION);
            if (res == 0) {
                PreparedStatement ps = conn.prepareStatement(sorgu);
                ps.setString(1, silinecekbasvurutext.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "kredi isteği silindi");
                String log = "kredi isteğiniz personel tarafından silindi";
                Log.logekle(silinecekbasvurutext.getText(), log);
                PersonelGUI pg = new PersonelGUI();
                pg.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "işlem iptal edildi.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_silbutonActionPerformed

    private void cikisbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikisbutonActionPerformed
        MainPageGUI mp = new MainPageGUI();
        mp.setVisible(true);
        dispose();
    }//GEN-LAST:event_cikisbutonActionPerformed

    private void basvurbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basvurbutonActionPerformed
        if (kartturucombobox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen geçerli bir kart türü seçiniz ");
        } else if (aylikgelirtextbox.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen aylık gelirinizi belirtiniz");
        } else if (!unvan.getText().equals("ogrenci") && kartturucombobox.getSelectedIndex() == 1) {
            JOptionPane.showMessageDialog(null, "öğrenci kartına sadece öğrenciler başvurabilir!");
        } else if (!unvan.getText().equals("ciftci") && kartturucombobox.getSelectedIndex() == 2) {
            JOptionPane.showMessageDialog(null, "çiftçi kartına sadece çiftçiler başvurabilir!");
        } else {
            int res = JOptionPane.showConfirmDialog(null, "kart başvurunuzu onaylıyor musunuz?", "onayla", JOptionPane.YES_NO_OPTION);
            if (res == 0) {
//                Musteri m = new Musteri(tc, isim, unvan);
                Kredikartiisteyenmusteri m = new Kredikartiisteyenmusteri();
                m.setTc(kredikartiisteyentc.getText());
                m.setUnvan(unvan.getText());
                m.setIsim(kredikartiisteyenisimtext.getText());
                String karttur = (String) kartturucombobox.getSelectedItem();
                String kartturu = (String) kartturucombobox.getSelectedItem();
                double gelir = Double.parseDouble(aylikgelirtextbox.getText());
                m.kredikartibasvur(kredikartiisteyentc.getText(), kredikartiisteyenisimtext.getText(), kartturu, gelir, unvan.getText());
                JOptionPane.showMessageDialog(null, "kart başvurunuz alınmıştır onay süreci biraz zaman alabilir! ");
                PersonelGUI pg = new PersonelGUI();
                pg.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "işleminiz iptal edilmiştir");

            }
    }//GEN-LAST:event_basvurbutonActionPerformed
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void kredikartibasvurusilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kredikartibasvurusilActionPerformed
        try {
            Veritabani.baglan();
            String sorgu = "DELETE FROM kredikartibasvurulari WHERE tc=?";
            int res = JOptionPane.showConfirmDialog(null, "silinecek onaylıyor musunuz", "uyarı", JOptionPane.YES_NO_OPTION);
            if (res == 0) {
                PreparedStatement ps = conn.prepareStatement(sorgu);
                ps.setString(1, kredikartiisteyentctext.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "kredi karti isteği silindi");
                String log = "kredi karti isteğiniz personel tarafından silindi";
                Log.logekle(kredikartiisteyentctext.getText(), log);
                PersonelGUI pg = new PersonelGUI();
                pg.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "işlem iptal edildi.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_kredikartibasvurusilActionPerformed

    private void limitonaylabutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limitonaylabutonActionPerformed
        Personel p = new Personel();
        int res = JOptionPane.showConfirmDialog(null, "yeni limiti onaylıyor musunuz ", "onay", JOptionPane.YES_NO_OPTION);
        if (res == 0) {
            p.limitguncelle(kredikartitctext.getText(), Double.valueOf(yenilimittext.getText()));
            JOptionPane.showMessageDialog(null, "limit güncellendi! ");
            String log = "kredi karti limitiniz güncellendi";
            Log.logekle(kredikartitctext.getText(), log);
            kredikartitctext.setText("");
            yenilimittext.setText("");
        }


    }//GEN-LAST:event_limitonaylabutonActionPerformed

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
            java.util.logging.Logger.getLogger(PersonelGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonelGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonelGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonelGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PersonelGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aylikgelirtext;
    private javax.swing.JTextField aylikgelirtextbox;
    private javax.swing.JButton basvurbuton;
    private javax.swing.JButton basvurueklebuton;
    private javax.swing.JButton cikisbuton;
    private javax.swing.JButton cikisbutonpersonel;
    private javax.swing.JTextField isimtext;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> kartturucombobox;
    private javax.swing.JTable krediistektablosu;
    private javax.swing.JButton kredikartibasvurusil;
    private javax.swing.JTable kredikartibasvurutable;
    private javax.swing.JTextField kredikartiisteyenisimtext;
    private javax.swing.JTextField kredikartiisteyentc;
    private javax.swing.JTextField kredikartiisteyentctext;
    private javax.swing.JTextField kredikartitctext;
    private javax.swing.JComboBox<String> kreditutaricombobox;
    private javax.swing.JComboBox<String> kretiturucombobox;
    private javax.swing.JButton limitonaylabuton;
    private javax.swing.JButton listelebuton;
    private javax.swing.JButton musterikaydet;
    private javax.swing.JButton musterisil;
    private javax.swing.JTable musteritablosu;
    private javax.swing.JButton onayla;
    private javax.swing.JButton personelcıkısbuton;
    private javax.swing.JTextField secilentext;
    private javax.swing.JButton silbuton;
    private javax.swing.JTextField silinecekbasvurutext;
    private javax.swing.JTextField silinecekmusteritc;
    private javax.swing.JButton silmebuton;
    private javax.swing.JComboBox<String> taksitsayisicombobox;
    private javax.swing.JTextField tcnotext;
    private javax.swing.JTextField unvan;
    private javax.swing.JTextField yenilimittext;
    private javax.swing.JTextField yenimusteriadtext;
    private javax.swing.JComboBox<String> yenimustericinsiyetcombobox;
    private javax.swing.JComboBox<String> yenimusteridogumyilicombobox;
    private javax.swing.JPasswordField yenimusterisifretext;
    private javax.swing.JTextField yenimusterisoyadtext;
    private javax.swing.JTextField yenimusteritctext;
    private javax.swing.JComboBox<String> yenimusteriunvancombobox;
    // End of variables declaration//GEN-END:variables
}
