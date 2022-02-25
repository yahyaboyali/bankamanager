package Backend;

import GUI_wiev.MusteriLoginGUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import veritabani.Veritabani;
import static veritabani.Veritabani.conn;

public class Personel extends BankaKullanan {

    private double maas;
    private String unvan;

    public Personel() {

    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public double getMaas() {
        return maas;
    }

    public void setMaas(int maas) {
        this.maas = maas;
    }

    public void limitguncelle(String tc,double yenilimit){
        String sorgu="UPDATE verilenkredilartlari SET limit=? WHERE tc=?";

        Veritabani.baglan();

        try {
            
            PreparedStatement ps2 = conn.prepareStatement(sorgu);
            ps2.setDouble(1, yenilimit);
            ps2.setString(2, tc);
            ps2.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Yonetici.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void sifreDegistir(String yeniSifre, String tcno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Personel(String tc, String isim, String soyisim, String unvan, double maas) {
        this.setTc(tc);
        this.setIsim(isim);
        this.setSoyisim(soyisim);
        this.maas = maas;
        this.unvan = unvan;
    }

    @Override
    public boolean girisYap(String tcno, String sifre) {
        String sorgu = "SELECT * FROM personel WHERE tc=? AND sifre=?";
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
