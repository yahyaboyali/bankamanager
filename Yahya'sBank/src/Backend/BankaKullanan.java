
package Backend;

import GUI_wiev.MusteriLoginGUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import veritabani.Veritabani;
import static veritabani.Veritabani.conn;


public abstract class BankaKullanan extends Kimlik {
    private String sifre;
    private String unvan;

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }
    public abstract boolean girisYap(String tcno,String sifre);
        
    public abstract void sifreDegistir(String mevcutsifre,String tcno);

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }


}
