package Backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import veritabani.Veritabani;
import static veritabani.Veritabani.conn;

public class Kasa {

    private double para;

    public double getPara() {
        return para;
    }

    public void setPara(double para) {
        this.para = para;
    }

    public static void kasayaekle(Double para) {
        String paralar = "SELECT * FROM kasa";
        String sorgu = "UPDATE kasa SET TL=?";
        double ilkpara = 0;
        Veritabani.baglan();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(paralar);
            while (rs.next()) {
                ilkpara = rs.getDouble("tl");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Kasa.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setDouble(1, ilkpara + para);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Kasa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void kasadancik(double para) {
        String paralar = "SELECT * FROM kasa";
        String sorgu = "UPDATE kasa SET TL=?";
        double ilkpara = 0;
        Veritabani.baglan();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(paralar);
            while (rs.next()) {
                ilkpara = rs.getDouble("tl");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Kasa.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement ps = conn.prepareStatement(sorgu);
            ps.setDouble(1, ilkpara - para);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Kasa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
