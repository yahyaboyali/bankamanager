/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;


public class YeniMusteri extends Musteri {
    private int dogumyili;
    public int getDogumyili() {
        return dogumyili;
    }
    public void setDogumyili(int dogumyili) {
        this.dogumyili = dogumyili;
    }

    public YeniMusteri() {
        
    }
    
    public YeniMusteri(String tcno,String isim,String soyisim,String unvan,String cinsiyet,int dogumyili){
        this.setTc(tcno);
        this.setIsim(isim);
        this.setSoyisim(soyisim);
        this.setUnvan(unvan);
        this.setCinsiyet(cinsiyet);
        this.dogumyili=dogumyili;
        
    }
}
