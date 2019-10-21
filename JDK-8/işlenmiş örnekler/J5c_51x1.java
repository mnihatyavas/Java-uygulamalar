// J5c_51x1.java: CyclingSpinnerListModel (DevridaimSayaçListesiModeli) alt-örneği.

import javax.swing.SpinnerModel;
import javax.swing.SpinnerListModel;

/* Devridaim demek, sürekli ileriye ve geriye tekrarlanan demektir.
 * Yani ileriye Aralık'tan sonra durmaz, Ocak'la devam eder;
 * veya geriye Ocak'tan önceye durmaz, Aralık'la devam eder.
 * Ayrıca ay sayacı bir yıl sayacıyla bağlantılanmışsa, 
 * ileriye Aralık'tan Ocak'a geçildiğinde, yıl'ı bir artırır;
 *  veya geriye Ocak'tan Aralık'a geçildiğinde, yıl'ı bir azaltır.
 */
public class J5c_51x1 extends SpinnerListModel {
    Object ilkDeğer, sonDeğer;
    SpinnerModel bağlıSayaçModeli = null;

    public J5c_51x1 (Object[] değerler) {// değerler=(String)ayAdları
        super (değerler);
        ilkDeğer = değerler[0];
        sonDeğer = değerler[değerler.length - 1];
    } // J5c_51x1(..) kurucusu sonu...

    public void setLinkedModel (SpinnerModel bağlıSayaçModeli) {this.bağlıSayaçModeli = bağlıSayaçModeli;}

    public Object getNextValue() {
        Object değer = super.getNextValue();
        if (değer == null) {
            değer = ilkDeğer;
            if (bağlıSayaçModeli != null) bağlıSayaçModeli.setValue (bağlıSayaçModeli.getNextValue());
        } // if kararı sonu...
        return değer;
    } // getNextValue() hazır metodu sonu...

    public Object getPreviousValue() {
        Object değer = super.getPreviousValue();
        if (değer == null) {
            değer = sonDeğer;
            if (bağlıSayaçModeli != null) bağlıSayaçModeli.setValue (bağlıSayaçModeli.getPreviousValue());
        } // if kararı sonu...
        return değer;
    } // getPreviousValue() hazır metodu sonu...
} // J5c_51x1 sınıfı sonu...