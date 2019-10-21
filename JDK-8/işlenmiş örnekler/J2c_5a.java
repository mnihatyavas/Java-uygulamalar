// J2c_5a.java: Cat (Kedi) �rne�i.

import java.util.Collection;
import java.util.ArrayList;

public class J2c_5a extends J2c_5ax {// extends Animal/Hayvan...
    public J2c_5a() {// S�n�f kurucusu...
        super.renkKoy ("beyaz");
    } // J2c_5a() kurucusu sonu...

    public static void gizle() {System.out.format ("Kedi/J2c_5a s�n�f�n�n gizle() metodu.%n");}
    public void esge�() {System.out.format ("Kedi/J2c_5a s�n�f�n�n esge�() metodu.%n");}

    // Kedi/J2c_5a yavrular�n�n listesini d�nd�r�r...
    public Collection<J2c_5a> yavruAl (int adet) {
        ArrayList<J2c_5a> yavruListesi = new ArrayList<J2c_5a>(adet);

        for (int i = 0; i < adet; i++) yavruListesi.add (i, new J2c_5a());

        return yavruListesi;
    } // yavruAl(..) metodu sonu...

    public static void main (String[] args) {
        J2c_5a kedim = new J2c_5a();
        J2c_5ax hayvan�m = kedim;
        J2c_5ax.gizle(); // Hayvan s�n�f�n�n gizle() metodu...
        hayvan�m.esge�(); // Kedi s�n�f�n�n esge�() metodu...
    } // main(..) metodu sonu...
} // J2c_5a s�n�f� sonu...

/* ��kt�:
**  >java J2c_5a  **
Hayvan/J2c_5ax s�n�f�n�n gizle() metodu.
Kedi/J2c_5a s�n�f�n�n esge�() metodu.
*/