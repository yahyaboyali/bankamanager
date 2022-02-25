
package Backend;

/**
 *
 * @author yahya
 */
public class Kredikartiisteyenmusteri extends Musteri{
    private double gelir;
    private String kartturu;

    public double getGelir() {
        return gelir;
    }

    public void setGelir(double gelir) {
        this.gelir = gelir;
    }

    public String getKartturu() {
        return kartturu;
    }

    public void setKartturu(String kartturu) {
        this.kartturu = kartturu;
    }
    public Kredikartiisteyenmusteri(String tc,String isim,String kartturu,double gelir,String unvan) {
        this.setTc(tc);
        this.setIsim(isim);
        this.setUnvan(unvan);
        this.kartturu=kartturu;
        this.gelir=gelir;
    }

    public Kredikartiisteyenmusteri() {
        
    }
    
    
}
