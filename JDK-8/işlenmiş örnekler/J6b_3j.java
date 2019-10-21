// J6b_3j.java: Shuffle (Karýþtýr) örneði.

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class J6b_3j {
    public static void main (String[] argümanlar) {
        List<String> liste = Arrays.asList (argümanlar);
        Collections.shuffle (liste);
        System.out.println ("Girilen argümanlarýn karýlan listesi: " + liste);
    } // main(..) metodu sonu...
} // J6b_3j sýnýfý sonu...

/* Çýktý:
**  >java J6b_3j  **
Girilen argümanlarýn karýlan listesi: []

**  >java J6b_3j Mahmut Nihat Yavaþ 17 Nisan 1957 Yeþilyurt Malatya TR  ** TEKRAR
Girilen argümanlarýn karýlan listesi: [1957, TR, Nihat, 17, Nisan, Malatya, Mahmut, Yeþilyurt, Yavaþ]

**  >java J6b_3j Mahmut Nihat Yavaþ 17 Nisan 1957 Yeþilyurt Malatya TR  ** TEKRAR
Girilen argümanlarýn karýlan listesi: [Yavaþ, Nihat, Malatya, Nisan, 17, 1957, Mahmut, Yeþilyurt, TR]
*/