/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;


public class Kredi {
    private int miktar ;
    private int taksit; 
    private double odenecektutar;
    private double ayliktaksit;
    private String kredituru;

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public int getTaksit() {
        return taksit;
    }

    public void setTaksit(int taksit) {
        this.taksit = taksit;
    }

    public double getOdenecektutar() {
        return odenecektutar;
    }

    public void setOdenecektutar(double odenecektutar) {
        this.odenecektutar = odenecektutar;
    }

    public double getAyliktaksit() {
        return ayliktaksit;
    }

    public void setAyliktaksit(double ayliktaksit) {
        this.ayliktaksit = ayliktaksit;
    }

    public String getKredituru() {
        return kredituru;
    }

    public void setKredituru(String kredituru) {
        this.kredituru = kredituru;
    }

    public Kredi(int miktar, int taksit, double odenecektutar, double ayliktaksit, String kredituru) {
        this.miktar = miktar;
        this.taksit = taksit;
        this.odenecektutar = odenecektutar;
        this.ayliktaksit = ayliktaksit;
        this.kredituru = kredituru;
    }
    
}
