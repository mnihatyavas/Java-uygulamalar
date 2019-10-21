// J2c_5a.java: Cat (Kedi) örneði.

import java.util.Collection;
import java.util.ArrayList;

public class J2c_5a extends J2c_5ax {// extends Animal/Hayvan...
    public J2c_5a() {// Sýnýf kurucusu...
        super.renkKoy ("beyaz");
    } // J2c_5a() kurucusu sonu...

    public static void gizle() {System.out.format ("Kedi/J2c_5a sýnýfýnýn gizle() metodu.%n");}
    public void esgeç() {System.out.format ("Kedi/J2c_5a sýnýfýnýn esgeç() metodu.%n");}

    // Kedi/J2c_5a yavrularýnýn listesini döndürür...
    public Collection<J2c_5a> yavruAl (int adet) {
        ArrayList<J2c_5a> yavruListesi = new ArrayList<J2c_5a>(adet);

        for (int i = 0; i < adet; i++) yavruListesi.add (i, new J2c_5a());

        return yavruListesi;
    } // yavruAl(..) metodu sonu...

    public static void main (String[] args) {
        J2c_5a kedim = new J2c_5a();
        J2c_5ax hayvaným = kedim;
        J2c_5ax.gizle(); // Hayvan sýnýfýnýn gizle() metodu...
        hayvaným.esgeç(); // Kedi sýnýfýnýn esgeç() metodu...
    } // main(..) metodu sonu...
} // J2c_5a sýnýfý sonu...

/* Çýktý:
**  >java J2c_5a  **
Hayvan/J2c_5ax sýnýfýnýn gizle() metodu.
Kedi/J2c_5a sýnýfýnýn esgeç() metodu.
*/