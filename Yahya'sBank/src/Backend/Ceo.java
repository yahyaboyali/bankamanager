/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import GUI_wiev.MusteriLoginGUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import veritabani.Veritabani;
import static veritabani.Veritabani.conn;

public class Ceo extends BankaKullanan implements Yetkili {

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

}
