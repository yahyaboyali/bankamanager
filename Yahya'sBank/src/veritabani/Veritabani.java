/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veritabani;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yahya
 */
public class Veritabani {

    public static Connection conn = null;
    public static String url = "jdbc:derby://localhost:1527/Bankadb";
    public static String kullaniciadi = "root";
    public static String sifre = "root";
   // public static String sorgu;

    public static Connection baglan() {
        try {
            conn = DriverManager.getConnection(url, kullaniciadi, sifre);
        } catch (Exception e) {
            System.out.println("hata");
        }
        return conn;
    }


    
}
