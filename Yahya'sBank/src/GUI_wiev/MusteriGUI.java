/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_wiev;

import Backend.Kasa;
import Backend.Kredi;
import Backend.Log;
import Backend.Musteri;
import Backend.Personel;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
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
public class MusteriGUI extends javax.swing.JFrame {

    String tcno = null;
    Double para = null;
    boolean paralar = true;
    String bakiye = null;
    String ad = null;
    String unvan = "diğer";
    String pass = null;
    Boolean x = false;
    int kreditaksitsayisi = 0;
    int krediparasi = 0;
    double kreditutari = 0.0;

    public MusteriGUI() {
        initComponents();
        setLocationRelativeTo(null);
        //bakiyelabel.setText(bakiyegoster(m));
        showlist();
    }

    public MusteriGUI(Musteri m, String kart) throws SQLException {
        initComponents();
        bakiyelabel.setText(bakiyegoster(m));
        setLocationRelativeTo(null);
        kartdurum.setText(kart);
        kartgoster(m.getTc());
        showlist();
        showinfos(tcno);
        loglar();

    }

    public MusteriGUI(Musteri m) throws SQLException {
        initComponents();
        bakiyelabel.setText(bakiyegoster(m));
        secilen(kreditablo, tutartext, 1);
        kartgoster(m.getTc());
        setLocationRelativeTo(null);
        showinfos(tcno);
        showlist();
        loglar();

    }

    public MusteriGUI(String s) {
        initComponents();
        bakiyelabel.setText(s);
        setLocationRelativeTo(null);
        showlist();
        loglar();
    }

    public void secilen(JTable jt, JTextField textf, int x) {
        jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                textf.setText(jt.getValueAt(jt.getSelectedRow(), x).toString());
            }
        });
    }

    public void showinfos(String tc) throws SQLException {
        String sorgu = "SELECT * FROM bireyselmusteri WHERE tc=?";
        Veritabani.baglan();
        PreparedStatement ps = conn.prepareStatement(sorgu);
        ps.setString(1, tc);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            musterisimlabel.setText(rs.getString("isim"));
            musterisoyisimlabel.setText(rs.getString("soyisim"));
            musterihesapnolabel.setText(Integer.toString(rs.getInt("hesapno")));
            musteriunvanlabel.setText(rs.getString("unvan"));
            unvan=rs.getString("unvan");
        }

    }

    public void kartgoster(String tc) throws SQLException {
        String sorgu = "SELECT * FROM verilenkredilartlari WHERE tc=?";
        Veritabani.baglan();
        PreparedStatement ps = conn.prepareStatement(sorgu);
        ps.setString(1, tc);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            ResultSet rs2 = ps.executeQuery();
            while (rs2.next()) {
                kartturulabel.setText(rs2.getString("kartturu"));
                kartlimitilabel.setText(String.valueOf(rs2.getDouble("limit")));
            }
        } else {
            kartturulabel.setText("bilgi yok");
            kartlimitilabel.setText("bilgi yok");
        }
    }

    public String bakiyegoster(Musteri m) {

        Veritabani.baglan();
        String sorgu = "SELECT * FROM bireyselmusteri WHERE tc=? AND sifre=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setString(1, m.getTc());
            ps.setString(2, m.getSifre());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m.setVarlik(rs.getDouble("varlik"));
                m.setIsim(rs.getString("isim"));
                m.setUnvan(rs.getString("unvan"));
                m.setSifre(rs.getString("sifre"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MusteriLoginGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        bakiye = Double.toString(m.getVarlik());

        tcno = m.getTc();
        para = m.getVarlik();
        ad = m.getIsim();
        unvan = m.getUnvan();
        pass = m.getSifre();
        // System.out.println(pass);
        //System.out.println(unvan+" "+ad+" "+ bakiye+" "+para);
        return bakiye;
    }

    public ArrayList<Kredi> listele() {
        ArrayList<Kredi> liste = new ArrayList<>();
        Veritabani.baglan();
        String sorgu = "SELECT * FROM verilenkrediler WHERE tc=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setString(1, tcno);
            ResultSet rs = ps.executeQuery();
            Kredi k;
            int krediparasi = 0;
            while (rs.next()) {
                krediparasi = rs.getInt("miktar");
                kreditaksitsayisi = rs.getInt("taksitsayisi");
                kreditutari = rs.getDouble("geriodenecektutar");
                k = new Kredi(rs.getInt("miktar"), rs.getInt("taksitsayisi"), rs.getDouble("geriodenecektutar"), rs.getDouble("aylikodenecektutar"), rs.getString("kredituru"));
                liste.add(k);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    public void showlist() {
        ArrayList<Kredi> liste = listele();
        DefaultTableModel model = (DefaultTableModel) kreditablo.getModel();
        model.setRowCount(0);
        Object[] satir = new Object[4];
        for (int i = 0; i < liste.size(); i++) {
            satir[0] = liste.get(i).getOdenecektutar();
            satir[1] = liste.get(i).getAyliktaksit();
            satir[2] = liste.get(i).getTaksit();
            satir[3] = liste.get(i).getKredituru();

            model.addRow(satir);

        }
    }
    void loglar(){
        DefaultListModel<String> l = new DefaultListModel<>();
        String sorgu= "SELECT * FROM log WHERE tc=?";
        Veritabani.baglan();
        try {
            PreparedStatement ps=conn.prepareStatement(sorgu);
            ps.setString(1, tcno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                l.addElement(rs.getString("logkaydi"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusteriGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        logkayitlari.setModel(l);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mustericikisbuton = new javax.swing.JButton();
        paracek = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bakiyelabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        kreditalepbuton = new javax.swing.JButton();
        kredikartibasvurbuton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mevcutsifretext = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        yenisifretext = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        yenisifreonaytext = new javax.swing.JPasswordField();
        sifredegistirbuton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        kartdurum = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        kartturulabel = new javax.swing.JLabel();
        kartlimitilabel = new javax.swing.JLabel();
        kredikartiipralbuton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        kreditablo = new javax.swing.JTable();
        tutartext = new javax.swing.JTextField();
        taksitodebuton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        faturaturucombobox = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        odenecektutartext = new javax.swing.JTextField();
        odemebuton = new javax.swing.JButton();
        odenecektutarlabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        musterisimlabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        musterisoyisimlabel = new javax.swing.JLabel();
        musteriunvanlabel = new javax.swing.JLabel();
        musterihesapnolabel = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        logkayitlari = new javax.swing.JList<>();
        jLabel13 = new javax.swing.JLabel();
        parayatirbuton = new javax.swing.JButton();
        paratransferbuton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Musteri");
        setResizable(false);

        mustericikisbuton.setBackground(new java.awt.Color(255, 255, 255));
        mustericikisbuton.setForeground(new java.awt.Color(0, 0, 0));
        mustericikisbuton.setText("ÇIKIŞ");
        mustericikisbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mustericikisbutonActionPerformed(evt);
            }
        });

        paracek.setText("para cek");
        paracek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paracekActionPerformed(evt);
            }
        });

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });

        jLabel1.setText("BAKİYE: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(bakiyelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(314, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bakiyelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(242, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("hesabım", jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        kreditalepbuton.setText("kredi talebi yolla");
        kreditalepbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kreditalepbutonActionPerformed(evt);
            }
        });
        jPanel2.add(kreditalepbuton);

        kredikartibasvurbuton.setText("kredi kartına başvur");
        kredikartibasvurbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kredikartibasvurbutonActionPerformed(evt);
            }
        });
        jPanel2.add(kredikartibasvurbuton);

        jTabbedPane1.addTab("başvurular", jPanel2);

        jLabel2.setText("şifre değiştir: ");

        jLabel3.setText("mevcut şifre:");

        jLabel4.setText("yeni şifre: ");

        jLabel5.setText("şifre tekrar: ");

        sifredegistirbuton.setText("şifre değiştir");
        sifredegistirbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sifredegistirbutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(sifredegistirbuton)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(mevcutsifretext, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addComponent(yenisifretext)
                                .addComponent(yenisifreonaytext)))))
                .addContainerGap(391, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(mevcutsifretext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(yenisifretext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yenisifreonaytext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(sifredegistirbuton)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("işlemler", jPanel3);

        jLabel6.setText("kart bilgileri: ");

        jLabel7.setText("kart türü: ");

        jLabel8.setText("limit: ");

        jPanel8.setBackground(new java.awt.Color(0, 0, 102));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));

        kartturulabel.setBackground(new java.awt.Color(255, 255, 255));
        kartturulabel.setForeground(new java.awt.Color(255, 255, 255));
        kartturulabel.setText("bilgi yok");

        kartlimitilabel.setBackground(new java.awt.Color(255, 255, 255));
        kartlimitilabel.setForeground(new java.awt.Color(255, 255, 255));
        kartlimitilabel.setText("bilgi yok");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kartlimitilabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kartturulabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(kartturulabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(kartlimitilabel)
                .addGap(16, 16, 16))
        );

        kredikartiipralbuton.setText("kart iptal et");
        kredikartiipralbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kredikartiipralbutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel6))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(kredikartiipralbuton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel8))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(kredikartiipralbuton)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kartdurum, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(kartdurum, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("kredi kartlarım", jPanel4);

        kreditablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "kalan borç", "taksit tutarı", "kalan taksit", "kredi turu"
            }
        ));
        jScrollPane1.setViewportView(kreditablo);

        tutartext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutartextActionPerformed(evt);
            }
        });

        taksitodebuton.setText("ödeme yap");
        taksitodebuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taksitodebutonActionPerformed(evt);
            }
        });

        jLabel10.setText("taksit öde");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tutartext)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(0, 65, Short.MAX_VALUE)
                                .addComponent(taksitodebuton)))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(17, 17, 17)
                        .addComponent(tutartext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(taksitodebuton)
                        .addGap(0, 191, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("kredilerim", jPanel5);

        faturaturucombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "fatura öde", "elektrik faturası ", "su faturası", "internet faturası", "telefon faturası" }));
        faturaturucombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                faturaturucomboboxActionPerformed(evt);
            }
        });

        jLabel11.setText("tutar: ");

        odemebuton.setText("öde");
        odemebuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odemebutonActionPerformed(evt);
            }
        });

        odenecektutarlabel.setText("ödenecek tutar : ");

        jLabel12.setText("ödenecek faturayı 2. defa seçerseniz ödeyeceğiniz tutar otomatik gözükecektir.  ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(odemebuton)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(faturaturucombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(18, 18, 18)
                                    .addComponent(odenecektutartext, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(odenecektutarlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(faturaturucombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(11, 11, 11)
                .addComponent(odenecektutarlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(odenecektutartext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(odemebuton)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ödemeler", jPanel6);

        jLabel9.setText("müşteri adı: ");

        musterisimlabel.setText("bilgi yok");

        jLabel14.setText("müşteri soy adı: ");

        jLabel15.setText("müşteri ünvan:");

        jLabel16.setText("hesap no:  ");

        musterisoyisimlabel.setText("bilgi yok");

        musteriunvanlabel.setText("bilgi yok");

        musterihesapnolabel.setText("bilgi yok");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(musterihesapnolabel))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(musteriunvanlabel))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(musterisoyisimlabel))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(51, 51, 51)
                            .addComponent(musterisimlabel))))
                .addContainerGap(414, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(musterisimlabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(musterisoyisimlabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(musteriunvanlabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(musterihesapnolabel))
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("bilgilerim", jPanel9);

        jScrollPane2.setViewportView(logkayitlari);

        jLabel13.setText("hesap hareketlerim");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("hareketler", jPanel10);

        parayatirbuton.setText("para yatır");
        parayatirbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parayatirbutonActionPerformed(evt);
            }
        });

        paratransferbuton.setText("para tranferi");
        paratransferbuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paratransferbutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mustericikisbuton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jTabbedPane1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(parayatirbuton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(paratransferbuton, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(paracek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(mustericikisbuton)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(paracek)
                        .addGap(18, 18, 18)
                        .addComponent(parayatirbuton)
                        .addGap(27, 27, 27)
                        .addComponent(paratransferbuton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mustericikisbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mustericikisbutonActionPerformed
        MainPageGUI mp = new MainPageGUI();
        mp.setVisible(true);
        dispose();
    }//GEN-LAST:event_mustericikisbutonActionPerformed

    private void paracekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paracekActionPerformed
        Musteri m = new Musteri();
        m.setTc(tcno);
        m.setVarlik(para);
        ParaCekGUI pg = new ParaCekGUI(m);
        pg.setVisible(true);
        dispose();
    }//GEN-LAST:event_paracekActionPerformed

    private void parayatirbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parayatirbutonActionPerformed
        Musteri m = new Musteri();
        m.setTc(tcno);
        m.setVarlik(para);
        ParaYatirGUI py = new ParaYatirGUI(m, bakiyegoster(m));
        py.setVisible(true);
        dispose();
    }//GEN-LAST:event_parayatirbutonActionPerformed

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased

    }//GEN-LAST:event_jPanel1MouseReleased

    private void paratransferbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paratransferbutonActionPerformed
        Musteri m = new Musteri();
        m.setTc(tcno);
        m.setVarlik(para);
        //System.out.println(para);
        ParaTransferGUI pt = new ParaTransferGUI(m);
        pt.setVisible(true);
        dispose();
    }//GEN-LAST:event_paratransferbutonActionPerformed

    private void kreditalepbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kreditalepbutonActionPerformed
        Musteri m = new Musteri();
        m.setIsim(ad);
        m.setTc(tcno);
        m.setVarlik(para);
        KreditalebiGUI kt = new KreditalebiGUI(m);
        kt.setVisible(true);
        dispose();
    }//GEN-LAST:event_kreditalepbutonActionPerformed

    private void kredikartibasvurbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kredikartibasvurbutonActionPerformed
        Musteri m = new Musteri();
        m.setIsim(ad);
        m.setTc(tcno);
        m.setUnvan(unvan);
        m.setVarlik(para);
        KrediKartiBasvurGUI kb = new KrediKartiBasvurGUI(m);
        kb.setVisible(true);
        dispose();

    }//GEN-LAST:event_kredikartibasvurbutonActionPerformed

    private void sifredegistirbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sifredegistirbutonActionPerformed
        if (mevcutsifretext.getText().length() == 0 || yenisifretext.getText().length() == 0 || yenisifreonaytext.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen boşlukları dolduralım");
        } else if (!mevcutsifretext.getText().equals(pass)) {
            JOptionPane.showMessageDialog(null, "mevcut şifre yanlış!");
        } else if (!yenisifretext.getText().equals(yenisifreonaytext.getText())) {
            JOptionPane.showMessageDialog(null, "yeni sifre ile tekrarı uyuşmuyor");
        } else {
            try {
                Musteri m = new Musteri();
                m.setTc(tcno);
                m.setIsim(ad);
                m.setSifre(pass);
                m.setVarlik(para);
                m.setUnvan(unvan);
                m.sifreDegistir(yenisifretext.getText(), m.getTc());
                JOptionPane.showMessageDialog(null, "şifreniz değişti!");
                String log = "şifreniz değişti";
                Log.logekle(tcno, log);
                MusteriGUI mg = new MusteriGUI(m);
                mg.setVisible(true);
                dispose();
            } catch (SQLException ex) {
                Logger.getLogger(MusteriGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_sifredegistirbutonActionPerformed

    private void odemebutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odemebutonActionPerformed
        if (faturaturucombobox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen ödenecek bir fatura türü seçiniz!!");
            //Integer.parseInt(odenecektutartext.getText()) <= 0
        } else if (odenecektutartext.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen geçerli bir miktar giriniz");
        } else {

            int res = JOptionPane.showConfirmDialog(null, "fatura ödeme işlemini onaylıyor musunuz? ", "onay", JOptionPane.YES_NO_OPTION);
            if (res == 0) {
                try {
                    Musteri m = new Musteri();
                    m.faturaode(tcno, Double.parseDouble(bakiye), Double.parseDouble(odenecektutartext.getText()));
                    m.setTc(tcno);
                    m.setVarlik(Double.parseDouble(bakiye) - Double.parseDouble(odenecektutartext.getText()));
                    String log = faturaturucombobox.getSelectedItem() + "  ödendi";
                    Log.logekle(tcno, log);
                    MusteriGUI mg = new MusteriGUI(m, kartdurum.getText());
                    mg.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(MusteriGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    JOptionPane.showMessageDialog(null, "işleminiz iptal edilmiştir! ");
                    Musteri m = new Musteri();
                    m.setTc(tcno);
                    m.setVarlik(para);
                    MusteriGUI mg = new MusteriGUI(m, kartdurum.getText());
                    mg.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(MusteriGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }//GEN-LAST:event_odemebutonActionPerformed

    private void faturaturucomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_faturaturucomboboxActionPerformed
        faturaturucombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String miktar = null;
                switch (faturaturucombobox.getSelectedIndex()) {
                    case 1:
                        miktar = "150.0";
                        break;
                    case 2:
                        miktar = "50.0";
                        break;
                    case 3:
                        miktar = "80.0";
                        break;
                    case 4:
                        miktar = "50.0";
                        break;
                    default:
                        miktar = "";
                }
                odenecektutartext.setText(miktar);
            }

        });
    }//GEN-LAST:event_faturaturucomboboxActionPerformed

    private void taksitodebutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taksitodebutonActionPerformed
        if (tutartext.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lütfen ödeme yapacağınız taksit için kredi tablosundan seçim yapınız");

        } else {
            try {
                Veritabani.baglan();
                int taksitsayisi = 0;
                double geriodenecekpara = 0;
                String verial = "SELECT * FROM verilenkrediler WHERE tc=?";
                PreparedStatement verips = conn.prepareStatement(verial);
                verips.setString(1, tcno);
                ResultSet rs = verips.executeQuery();
                while (rs.next()) {
                    taksitsayisi = rs.getInt("taksitsayisi");
                    geriodenecekpara = rs.getDouble("geriodenecektutar");
                }
                double kreditaksiti = Double.parseDouble(tutartext.getText());
                Musteri m = new Musteri();
                m.setTc(tcno);

                if (m.kreditaksidiode(tcno, kreditaksiti, taksitsayisi, para)) {
                    JOptionPane.showMessageDialog(null, "ödeme başarılı!");
                    String log = String.valueOf(kreditaksiti) + " liralık taksit yatırıldı";
                    Log.logekle(tcno, log);
                    m.setVarlik(para - kreditaksiti);
                    MusteriGUI mg = new MusteriGUI(m, kartdurum.getText());
                    mg.setVisible(true);
                    dispose();
                } else {
                    m.setVarlik(para);
                    MusteriGUI mg = new MusteriGUI(m, kartdurum.getText());
                    mg.setVisible(true);
                    dispose();
                }
            } catch (SQLException ex) {
                Logger.getLogger(MusteriGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_taksitodebutonActionPerformed

    private void tutartextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutartextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tutartextActionPerformed

    private void kredikartiipralbutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kredikartiipralbutonActionPerformed
        int res = JOptionPane.showConfirmDialog(null, "kartınızı iptal etmek istediğinize emin misiniz ? ", "onay", JOptionPane.YES_NO_OPTION);
        if (res == 0) {
            Musteri m = new Musteri();
            m.setTc(tcno);
            m.setIsim(ad);
            m.setSifre(pass);
            m.setVarlik(para);
            m.setUnvan(unvan);
            m.kartiptalet(tcno);
            JOptionPane.showMessageDialog(null, "kartınız iptal edildi ");
            String log = " kredi kartı iptal edildi";
            Log.logekle(tcno, log);
            MusteriGUI mg;
            try {
                mg = new MusteriGUI(m);
                mg.setVisible(true);
                dispose();
            } catch (SQLException ex) {
                Logger.getLogger(MusteriGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_kredikartiipralbutonActionPerformed

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
            java.util.logging.Logger.getLogger(MusteriGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MusteriGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MusteriGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MusteriGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MusteriGUI().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bakiyelabel;
    private javax.swing.JComboBox<String> faturaturucombobox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel kartdurum;
    private javax.swing.JLabel kartlimitilabel;
    private javax.swing.JLabel kartturulabel;
    private javax.swing.JButton kredikartibasvurbuton;
    private javax.swing.JButton kredikartiipralbuton;
    private javax.swing.JTable kreditablo;
    private javax.swing.JButton kreditalepbuton;
    private javax.swing.JList<String> logkayitlari;
    private javax.swing.JPasswordField mevcutsifretext;
    private javax.swing.JButton mustericikisbuton;
    private javax.swing.JLabel musterihesapnolabel;
    private javax.swing.JLabel musterisimlabel;
    private javax.swing.JLabel musterisoyisimlabel;
    private javax.swing.JLabel musteriunvanlabel;
    private javax.swing.JButton odemebuton;
    private javax.swing.JLabel odenecektutarlabel;
    private javax.swing.JTextField odenecektutartext;
    private javax.swing.JButton paracek;
    private javax.swing.JButton paratransferbuton;
    private javax.swing.JButton parayatirbuton;
    private javax.swing.JButton sifredegistirbuton;
    private javax.swing.JButton taksitodebuton;
    private javax.swing.JTextField tutartext;
    private javax.swing.JPasswordField yenisifreonaytext;
    private javax.swing.JPasswordField yenisifretext;
    // End of variables declaration//GEN-END:variables
}
