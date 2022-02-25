
package Backend;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import veritabani.Veritabani;
import static veritabani.Veritabani.conn;


public class Log {
    static String logstring;
    public static void logekle(String tc,String metin){
        String sorgu = "INSERT INTO log (tc,logkaydi) VALUES (?,?)";
        Veritabani.baglan();
        try {
            PreparedStatement ps =conn.prepareStatement(sorgu);
            ps.setString(1, tc);
            ps.setString(2, metin);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
