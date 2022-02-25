package Backend;

import GUI_wiev.MusteriGUI;
import GUI_wiev.MusteriLoginGUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import veritabani.Veritabani;
import static veritabani.Veritabani.*;

public class Musteri extends BankaKullanan implements Islem {

    private Double varlik;
    private int hesapno;

    public Musteri(String isim, String soyisim, String tc, int hesapno) {
        this.setIsim(isim);
        this.setSoyisim(soyisim);
        this.setTc(tc);
        this.hesapno = hesapno;
    }

    public Musteri(String tc, String isim, String unvan) {
        this.setIsim(isim);
        this.setTc(tc);
        this.setUnvan(unvan);
    }

    public int getHesapno() {
        return hesapno;
    }

    public void setHesapno(int hesapno) {
        this.hesapno = hesapno;
    }

    public Musteri() {
    }

    public Musteri(String tcno, Double varlik) {
        this.varlik = varlik;
        this.setTc(tcno);
    }

    public Musteri(String tcno, String isim, Double varlik) {
        this.setIsim(isim);
        this.setTc(tcno);
        this.varlik = varlik;
    }

    public Double getVarlik() {
        return varlik;
    }

    public void setVarlik(Double varlik) {
        this.varlik = varlik;
    }

    public void yeniekle(String tc, String isim, String soyisim, String unvan, String cinsiyet, String dogumyili, String sifre) throws SQLException, Exception {

        String sql = "INSERT INTO musteriistekleri (tcno,isim,soyisim,unvan,cinsiyet,dogumyili,sifre) VALUES (?,?,?,?,?,?,?)";

        Veritabani.baglan();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tc);
        ps.setString(2, isim);
        ps.setString(3, soyisim);
        ps.setString(4, unvan);
        ps.setString(5, cinsiyet);
        ps.setString(6, dogumyili);
        ps.setString(7, sifre);
        ps.executeUpdate();
    }

    public void yeniekle(String isim, String soyisim, String tc, String unvan, String cinsiyet, int dogumyili, String sifre, String sorgu, String unvann) {
        try {
            Veritabani.baglan();
            Statement st = conn.createStatement();
            ResultSet rsm = st.executeQuery("SELECT * FROM bireyselmusteri"); // hesap numarası belirlemek için
            int hesap = 0;
            while (rsm.next()) {
                hesap = rsm.getInt("hesapno");
            }
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setString(1, tc);
            ps.setString(2, isim);
            ps.setDouble(3, 0.0);
            ps.setString(4, sifre);
            ps.setInt(5, hesap + 1);
            ps.setString(6, soyisim);
            ps.setString(7, unvann);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Musteri.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void kredibasvurusu(String tcnosu, String isim, String kredituru, String gelir, int taksit, int tutar) {
        Veritabani.baglan();
        String sorgu = "INSERT INTO kredibasvurulari(tc,miktar,taksitsayisi,isim,aylikgelir,kredituru) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement ps = conn.prepareCall(sorgu);
            ps.setString(1, tcnosu);
            ps.setInt(2, tutar);
            ps.setInt(3, taksit);
            ps.setString(4, isim);
            ps.setString(5, gelir);
            ps.setString(6, kredituru);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Musteri.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void kredikartibasvur(String tcno, String isim, String kartturu, double gelir, String unvan) {
        Veritabani.baglan();
        String sorgu = "INSERT INTO kredikartibasvurulari (tc,isim,kartturu,gelir,unvan) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setString(1, tcno);
            ps.setString(2, isim);
            ps.setString(3, kartturu);
            ps.setDouble(4, gelir);
            ps.setString(5, unvan);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Musteri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sifreDegistir(String yeniSifre, String tcno) {
        Veritabani.baglan();
        String sorgu = "UPDATE bireyselmusteri SET sifre=? WHERE tc=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setString(1, yeniSifre);
            ps.setString(2, tcno);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Musteri.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void kartiptalet(String tc){
        String kredisil = "DELETE FROM verilenkredilartlari WHERE tc =?";
        Veritabani.baglan();
        try {
            PreparedStatement ps = conn.prepareStatement(kredisil);
            ps.setString(1, tc);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Musteri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void faturaode(String tc, double ilkpara, double cekilenpara) {
        Veritabani.baglan();
        String sorgu = "UPDATE bireyselmusteri SET varlik=? WHERE tc=?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sorgu);
            ps.setDouble(1, (ilkpara - cekilenpara));
            ps.setString(2, tc);
            ps.executeUpdate();
            Kasa.kasadancik(cekilenpara);
        } catch (SQLException ex) {
            Logger.getLogger(Musteri.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean kreditaksidiode(String tc, double taksittutari, double kalantaksit, double mevcutpara) throws SQLException {
        Veritabani.baglan();
        int taksitsayisi = 0;
        double geriodenecekpara = 0;
        String verial = "SELECT * FROM verilenkrediler WHERE tc=?";
        String odeme = "UPDATE bireyselmusteri SET varlik=? WHERE tc=?";
        String krediguncelle = "UPDATE verilenkrediler SET geriodenecektutar=? WHERE tc=?";
        String kreditaksitguncelle = "UPDATE verilenkrediler SET taksitsayisi=? WHERE tc=?";
        String kredisil = "DELETE FROM verilenkrediler WHERE tc =?";
        PreparedStatement verips = conn.prepareStatement(verial);
        verips.setString(1, tc);
        ResultSet rs = verips.executeQuery();
        while (rs.next()) {
            taksitsayisi = rs.getInt("taksitsayisi");
            geriodenecekpara = rs.getDouble("geriodenecektutar");
        }

        if (mevcutpara < taksittutari) {
            JOptionPane.showMessageDialog(null, "yetersiz bakiye");
        } else {
            int res = JOptionPane.showConfirmDialog(null, "fatura ödeme işlemini onaylıyor musunuz? ", "onay", JOptionPane.YES_NO_OPTION);
            if (res == 0) {
                if (kalantaksit == 1) {
                    PreparedStatement podeme = conn.prepareStatement(odeme);
                    podeme.setDouble(1, mevcutpara - taksittutari);
                    podeme.setString(2, tc);
                    podeme.executeUpdate();
                    Kasa.kasayaekle(taksittutari);
                    PreparedStatement silp = conn.prepareStatement(kredisil);
                    silp.setString(1, tc);
                    silp.executeUpdate();
                } else {
                    PreparedStatement podeme = conn.prepareStatement(odeme);
                    podeme.setDouble(1, mevcutpara - taksittutari);
                    podeme.setString(2, tc);
                    podeme.executeUpdate();
                    Kasa.kasayaekle(taksittutari);
                    PreparedStatement pkrediguncelle = conn.prepareStatement(krediguncelle);
                    pkrediguncelle.setDouble(1, geriodenecekpara - taksittutari);
                    pkrediguncelle.setString(2, tc);
                    pkrediguncelle.executeUpdate();
                    PreparedStatement ptaksitguncelle = conn.prepareStatement(kreditaksitguncelle);
                    ptaksitguncelle.setInt(1, taksitsayisi - 1);
                    ptaksitguncelle.setString(2, tc);
                    ptaksitguncelle.executeUpdate();
                    return true;
                }
            } else {
                return false;
            }

        }
        return false;
    }

    @Override
    public boolean paracek(Double ilkpara, Double cekilenpara, String paracekilentc) {
        Veritabani.baglan();
        String sorgu = "UPDATE bireyselmusteri SET varlik=? WHERE tc=?";
        if (cekilenpara > ilkpara) {
            //JOptionPane.showMessageDialog(null, "yetersiz bakiye");
            return false;

        } else {
            PreparedStatement ps;

            try {
                ps = conn.prepareStatement(sorgu);
                ps.setDouble(1, (ilkpara - cekilenpara));
                ps.setString(2, paracekilentc);
                ps.executeUpdate();
                Kasa.kasadancik(cekilenpara);
            } catch (SQLException ex) {
                Logger.getLogger(Musteri.class.getName()).log(Level.SEVERE, null, ex);
            }

            return true;
        }
         
    }

    @Override
    public double parayatir(Double ilkpara, Double yatanpara, String parayatacaktc) {
        try {
            Veritabani.baglan();
            String sorgu = "UPDATE bireyselmusteri SET varlik=? WHERE tc=?";
            
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setDouble(1, (ilkpara + yatanpara));
            ps.setString(2, parayatacaktc);
            ps.executeUpdate();
            Kasa.kasayaekle(yatanpara);
            return (yatanpara +ilkpara );
        } catch (SQLException ex) {
            Logger.getLogger(Musteri.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ilkpara;
    }

    @Override
    public void havale() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean girisYap(String tcno, String sifre) {
        String sorgu = "SELECT * FROM bireyselmusteri WHERE tc=? AND sifre=?";
        Veritabani.baglan();
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setString(1, tcno);
            ps.setString(2, sifre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MusteriLoginGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
