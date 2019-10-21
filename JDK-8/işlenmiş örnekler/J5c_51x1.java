// J5c_51x1.java: CyclingSpinnerListModel (DevridaimSaya�ListesiModeli) alt-�rne�i.

import javax.swing.SpinnerModel;
import javax.swing.SpinnerListModel;

/* Devridaim demek, s�rekli ileriye ve geriye tekrarlanan demektir.
 * Yani ileriye Aral�k'tan sonra durmaz, Ocak'la devam eder;
 * veya geriye Ocak'tan �nceye durmaz, Aral�k'la devam eder.
 * Ayr�ca ay sayac� bir y�l sayac�yla ba�lant�lanm��sa, 
 * ileriye Aral�k'tan Ocak'a ge�ildi�inde, y�l'� bir art�r�r;
 *  veya geriye Ocak'tan Aral�k'a ge�ildi�inde, y�l'� bir azalt�r.
 */
public class J5c_51x1 extends SpinnerListModel {
    Object ilkDe�er, sonDe�er;
    SpinnerModel ba�l�Saya�Modeli = null;

    public J5c_51x1 (Object[] de�erler) {// de�erler=(String)ayAdlar�
        super (de�erler);
        ilkDe�er = de�erler[0];
        sonDe�er = de�erler[de�erler.length - 1];
    } // J5c_51x1(..) kurucusu sonu...

    public void setLinkedModel (SpinnerModel ba�l�Saya�Modeli) {this.ba�l�Saya�Modeli = ba�l�Saya�Modeli;}

    public Object getNextValue() {
        Object de�er = super.getNextValue();
        if (de�er == null) {
            de�er = ilkDe�er;
            if (ba�l�Saya�Modeli != null) ba�l�Saya�Modeli.setValue (ba�l�Saya�Modeli.getNextValue());
        } // if karar� sonu...
        return de�er;
    } // getNextValue() haz�r metodu sonu...

    public Object getPreviousValue() {
        Object de�er = super.getPreviousValue();
        if (de�er == null) {
            de�er = sonDe�er;
            if (ba�l�Saya�Modeli != null) ba�l�Saya�Modeli.setValue (ba�l�Saya�Modeli.getPreviousValue());
        } // if karar� sonu...
        return de�er;
    } // getPreviousValue() haz�r metodu sonu...
} // J5c_51x1 s�n�f� sonu...