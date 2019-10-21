// J3b_17d.java: Standard2 (Standart2) �rne�i.

import java.util.Scanner; // Kelime okuyucu...
import java.io.IOException;

public class J3b_17d {
    public static void main (String args[]) throws IOException {
        Scanner oku = new Scanner (System.in);
        String say�Str;
        double toplam = 0;

        System.out.print ("Veri gir [q:��k]: ");
        while ((say�Str = oku.nextLine()) != null) {
            if (say�Str.equals ("q")) break;
            try {System.out.println ("Okunan veri: [" + say�Str + "]");
                toplam += Double.parseDouble (say�Str);
            }catch (NumberFormatException ist) {System.err.println ("Ge�ersiz say�sal veri!");
            } // try-catch blo�u sonu...
            System.out.print ("\nVeri gir: ");
        } // while d�ng�s� sonu...
        //if (oku != null) oku.close();

        System.out.println ("=============================\nToplam: " + toplam);
    } // main(..) metodu sonu...
} // J3b_17d s�n�f� sonu...

/* ��kt�:
**  >java J3b_17c  **
Veri gir [q:��k]: 23
Okunan veri: [23]

Veri gir: 56.98
Okunan veri: [56.98]

Veri gir: nihat
Okunan veri: [nihat]
Ge�ersiz say�sal veri!

Veri gir: 1957 Nihat
Okunan veri: [1957 Nihat]
Ge�ersiz say�sal veri!

Veri gir: 1957
Okunan veri: [1957]

Veri gir: q
=============================
Toplam: 2036.98
*/