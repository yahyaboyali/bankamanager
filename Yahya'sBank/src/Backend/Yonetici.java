package Backend;

import GUI_wiev.MusteriLoginGUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import veritabani.Veritabani;
import static veritabani.Veritabani.conn;

public class Yonetici extends BankaKullanan implements Yetkili{

    @Override
    public void sifreDegistir(String yeniSifre, String tcno) {
       Veritabani.baglan();
        String sorgu = "UPDATE yetkili SET sifre=? WHERE tcno=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setString(1, yeniSifre);
            ps.setString(2, tcno);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Musteri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean kredionayla(String tc, double para) {
        String sorgu = "SELECT * FROM kredibasvurulari WHERE tc=?";
        Veritabani.baglan();
        KrediisteyenMusteri ki = new KrediisteyenMusteri();
        ki.setTc(tc);
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setString(1, tc);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ki.setMiktar(rs.getInt("miktar"));
                ki.setKredituru(rs.getString("kredituru"));
                ki.setTaksitsayisi(rs.getInt("taksitsayisi"));
            }
            double odenecekmiktar = 0;
            int sure = ki.getTaksitsayisi();
            int odemesuresi = 0;
            int tutar = ki.getMiktar();
            switch (sure) {
                case 3:
                    odenecekmiktar = (tutar * 1.05 / 100) + tutar;
                    odemesuresi = 3;
                    break;
                case 6:
                    odenecekmiktar = (tutar * 1.1 / 100) + tutar;
                    odemesuresi = 6;
                    break;
                case 12:
                    odenecekmiktar = (tutar * 1.4 / 100) + tutar;
                    odemesuresi = 12;
                    break;
                case 24:
                    odenecekmiktar = (tutar * 1.6 / 100) + tutar;
                    odemesuresi = 24;
                    break;
                case 36:
                    odenecekmiktar = (tutar * 1.6 / 100) + tutar;
                    odemesuresi = 36;
                    break;
                default:
                    odenecekmiktar = tutar;
            }
            int res = JOptionPane.showConfirmDialog(null, "onaylıyor musunuz ? ", "onay", JOptionPane.YES_NO_OPTION);
            if (res == 0) {
                String kredieklesorgu = "INSERT INTO verilenkrediler (tc,miktar,taksitsayisi,geriodenecektutar,aylikodenecektutar,kredituru) VALUES(?,?,?,?,?,?)";
                PreparedStatement ponay = conn.prepareStatement(kredieklesorgu);
                ponay.setString(1, ki.getTc());
                ponay.setInt(2, ki.getMiktar());
                ponay.setDouble(3, ki.getTaksitsayisi());
                ponay.setDouble(4, odenecekmiktar);
                ponay.setDouble(5, odenecekmiktar / sure);
                ponay.setString(6, ki.getKredituru());
                ponay.executeUpdate();
                String paraekle = "UPDATE bireyselmusteri SET varlik=? WHERE tc=?";
                PreparedStatement ppara = conn.prepareStatement(paraekle);
                ppara.setDouble(1, ki.getMiktar() + para);
                ppara.setString(2, ki.getTc());
                ppara.executeUpdate();
                String silsorgu = "DELETE FROM kredibasvurulari WHERE tc=?";
                PreparedStatement psil = conn.prepareStatement(silsorgu);
                psil.setString(1, ki.getTc());
                psil.executeUpdate();
                Kasa.kasadancik(ki.getMiktar());
                return true;

            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Yonetici.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean girisYap(String tcno, String sifre) {
        Veritabani.baglan();
        String sorgu = "SELECT * FROM yetkili WHERE tcno=? AND sifre=?";
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

    public boolean kredikartionayla(String tc) throws SQLException {
        String sorgu = "SELECT * FROM kredikartibasvurulari WHERE tc =?";
        String sorgu2 = "INSERT INTO verilenkredilartlari(tc,isim,kartturu,limit) VALUES (?,?,?,?)";
        String isim = null;
        String kartturu = null;
        Double gelir = null;
        String unvan;
        Veritabani.baglan();
        int kontrol = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);

            ps.setString(1, tc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                isim = rs.getString("isim");
                kartturu = rs.getString("kartturu");
                gelir = rs.getDouble("gelir");
                unvan = rs.getString("unvan");
                kontrol++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MusteriLoginGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (kontrol > 0) {
            PreparedStatement pekle = conn.prepareStatement(sorgu2);
            pekle.setString(1, tc);
            pekle.setString(2, isim);
            pekle.setString(3, kartturu);
            pekle.setDouble(4, gelir);
            pekle.executeUpdate();
            String silsorgu = "DELETE FROM kredikartibasvurulari WHERE tc=?";
            PreparedStatement psil = conn.prepareStatement(silsorgu);
            psil.setString(1, tc);
            psil.executeUpdate();
            return true;
        }

        return false;
    }

    @Override
    public void yetkiVer(Personel p, String yetki) {
        String sorgu="UPDATE personel SET unvan=? WHERE tc=?";
         Veritabani.baglan();
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setString(1, yetki);
            ps.setString(2, p.getTc());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Musteri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void zamYap(String tc, int yuzde) {
        String sorgu="UPDATE personel SET maas=? WHERE tc=?";
        String sorgu2 = "SELECT * FROM personel WHERE tc = ?";
        Veritabani.baglan();
        double ilkmaas =0;
        try {
            
            PreparedStatement ps = conn.prepareStatement(sorgu2);
            ps.setString(1, tc);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ilkmaas=rs.getDouble("maas");
            }
            PreparedStatement ps2 = conn.prepareStatement(sorgu);
            ps2.setDouble(1, ilkmaas+ilkmaas*yuzde/100);
            ps2.setString(2, tc);
            ps2.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Yonetici.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
