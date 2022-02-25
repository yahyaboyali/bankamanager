
package Backend;

import GUI_wiev.MusteriLoginGUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import veritabani.Veritabani;
import static veritabani.Veritabani.conn;


public class BankaSahibi extends BankaKullanan {
    private Banka banka;

    public Banka getBanka() {
        return banka;
    }

    public void setBanka(Banka banka) {
        this.banka = banka;
    }
    
    @Override
    public boolean girisYap(String tcno,String sifre) {
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

    @Override
    public void sifreDegistir(String yeniSifre, String tcno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
