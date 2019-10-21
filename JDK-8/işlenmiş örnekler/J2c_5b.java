// J2c_5b.java: Cats (Kediler) �rne�i.

import java.util.Collection;

public class J2c_5b {
    public static void main (String[] args) {
        J2c_5a kedim = new J2c_5a();

        Collection<J2c_5a> enikListesi = kedim.yavruAl (5);
        for (J2c_5a yavru : enikListesi) System.out.format ("%s%n", yavru.renkAl()); // 5 adet beyaz renkli yavru...
    } // main(..) metodu sonu...
} // J2c_5b s�n�f� sonu...

/* ��kt�:
**  >java J2c_5b  **
beyaz
beyaz
beyaz
beyaz
beyaz
*/