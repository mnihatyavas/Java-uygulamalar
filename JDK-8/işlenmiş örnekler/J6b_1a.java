// J6b_1a.java: Sort (Sýralama) örneði.

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class J6b_1a {
    public static void main (String[] argümanlar) {
        if (argümanlar.length < 2) {
            System.out.println ("Enaz sýralanacak 2 argüman girmeyi unutmayýn!");
            System.exit (0);
        } // if kararý sonu...

        List<String> liste = Arrays.asList (argümanlar);
        Collections.sort (liste); // Dizgeye çevirir ve [0->z] sýralar...
        System.out.println ("Girdiðiniz argümanlarýn sýralý (yanyana) listesi: " + liste);
        System.out.println ("\nGirdiðiniz argümanlarýn sýralý (altalta) listesi==>");
        for (String eleman : liste) {System.out.println (eleman);}
    } // main(..) metodu sonu...
} // J6b_1a sýnýfý sonu...

/* Çýktý:
**  >java J6b_1a  **
Enaz sýralanacak 2 argüman girmeyi unutmayýn!

**  >java J6b_1a Nihat Mahmut Yavaþ 1957 17 04 Malatya Yeþilyurt TR  **
Girdiðiniz argümanlarýn sýralý (yanyana) listesi: [04, 17, 1957, Mahmut, Malatya, Nihat, TR, Yavaþ, Yeþilyurt]

Girdiðiniz argümanlarýn sýralý (altalta) listesi==>
04
17
1957
Mahmut
Malatya
Nihat
TR
Yavaþ
Yeþilyurt
*/