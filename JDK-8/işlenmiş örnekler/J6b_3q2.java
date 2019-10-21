// J6b_3q2.java: Ran (Koþtu) örneði.

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class J6b_3q2 {
    public static void main (String[] argümanlar) {
        List<String> argümanListesi = Arrays.asList (argümanlar);
        Collections.shuffle (argümanListesi);

        System.out.println ("\nKarýlan argüman listesi JDK 8 stream() akýþ hazýr metoduyla (yanyana)==>");
        argümanListesi.stream().forEach (element->System.out.format ("%s ", element));

        System.out.println ("\n\nKarýlan argüman listesi for-each yöntemiyle (yanyana)==>");
        for (String argüman: argümanListesi) System.out.format ("%s ", argüman);
        System.out.println();
    } // main(..) metodu sonu...
} // J6b_3q2 sýnýfý sonu...

/* Çýktý:
**  >java J6b_3q2  **
Karýlan argüman listesi JDK 8 stream() akýþ hazýr metoduyla (yanyana)==>
Karýlan argüman listesi for-each yöntemiyle (yanyana)==>

**  >java J6b_3q2 Mahmut NihatYavaþ 17 Nisan 1957 Yeþilyurt Malatya TR  ** TEKRAR

Karýlan argüman listesi JDK 8 stream() akýþ hazýr metoduyla (yanyana)==>
Malatya Mahmut 17 Nihat 1957 Yeþilyurt Yavaþ Nisan TR

Karýlan argüman listesi for-each yöntemiyle (yanyana)==>
Malatya Mahmut 17 Nihat 1957 Yeþilyurt Yavaþ Nisan TR
*/