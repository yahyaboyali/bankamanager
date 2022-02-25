/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

public interface Yetkili {
    void yetkiVer(Personel p,String yetki);
    void zamYap(String tc,int yuzde);
}
