
package Backend;


public class Banka {
    
    private String name;
    private  Sube sube;
    private  Musteri musteri;
    private  String isim;
    private  Kasa kasa;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sube getSube() {
        return sube;
    }

    public void setSube(Sube sube) {
        this.sube = sube;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public Kasa getKasa() {
        return kasa;
    }

    public void setKasa(Kasa kasa) {
        this.kasa = kasa;
    }
    
}
