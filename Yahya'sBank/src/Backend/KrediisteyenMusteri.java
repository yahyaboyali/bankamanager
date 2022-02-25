/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author yahya
 */
public class KrediisteyenMusteri extends Musteri {

    //private String tc;
    private int miktar;
    private int taksitsayisi;
    //private String isim;
    private String gelir;
    private String kredituru;

    public KrediisteyenMusteri() {
    }

    public KrediisteyenMusteri(String tc, int miktar, int taksitsayisi, String isim, String gelir, String kredituru) {
        this.setTc(tc);
        this.miktar = miktar;
        this.taksitsayisi = taksitsayisi;
        this.setIsim(isim);
        this.gelir = gelir;
        this.kredituru = kredituru;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public int getTaksitsayisi() {
        return taksitsayisi;
    }

    public void setTaksitsayisi(int taksitsayisi) {
        this.taksitsayisi = taksitsayisi;
    }

    public String getGelir() {
        return gelir;
    }

    public void setGelir(String gelir) {
        this.gelir = gelir;
    }

    public String getKredituru() {
        return kredituru;
    }

    public void setKredituru(String kredituru) {
        this.kredituru = kredituru;
    }
}
