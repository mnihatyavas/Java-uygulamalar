// J3b_17d.java: Standard2 (Standart2) örneði.

import java.util.Scanner; // Kelime okuyucu...
import java.io.IOException;

public class J3b_17d {
    public static void main (String args[]) throws IOException {
        Scanner oku = new Scanner (System.in);
        String sayýStr;
        double toplam = 0;

        System.out.print ("Veri gir [q:çýk]: ");
        while ((sayýStr = oku.nextLine()) != null) {
            if (sayýStr.equals ("q")) break;
            try {System.out.println ("Okunan veri: [" + sayýStr + "]");
                toplam += Double.parseDouble (sayýStr);
            }catch (NumberFormatException ist) {System.err.println ("Geçersiz sayýsal veri!");
            } // try-catch bloðu sonu...
            System.out.print ("\nVeri gir: ");
        } // while döngüsü sonu...
        //if (oku != null) oku.close();

        System.out.println ("=============================\nToplam: " + toplam);
    } // main(..) metodu sonu...
} // J3b_17d sýnýfý sonu...

/* Çýktý:
**  >java J3b_17c  **
Veri gir [q:çýk]: 23
Okunan veri: [23]

Veri gir: 56.98
Okunan veri: [56.98]

Veri gir: nihat
Okunan veri: [nihat]
Geçersiz sayýsal veri!

Veri gir: 1957 Nihat
Okunan veri: [1957 Nihat]
Geçersiz sayýsal veri!

Veri gir: 1957
Okunan veri: [1957]

Veri gir: q
=============================
Toplam: 2036.98
*/