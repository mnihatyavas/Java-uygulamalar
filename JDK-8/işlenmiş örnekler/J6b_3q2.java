// J6b_3q2.java: Ran (Ko�tu) �rne�i.

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class J6b_3q2 {
    public static void main (String[] arg�manlar) {
        List<String> arg�manListesi = Arrays.asList (arg�manlar);
        Collections.shuffle (arg�manListesi);

        System.out.println ("\nKar�lan arg�man listesi JDK 8 stream() ak�� haz�r metoduyla (yanyana)==>");
        arg�manListesi.stream().forEach (element->System.out.format ("%s ", element));

        System.out.println ("\n\nKar�lan arg�man listesi for-each y�ntemiyle (yanyana)==>");
        for (String arg�man: arg�manListesi) System.out.format ("%s ", arg�man);
        System.out.println();
    } // main(..) metodu sonu...
} // J6b_3q2 s�n�f� sonu...

/* ��kt�:
**  >java J6b_3q2  **
Kar�lan arg�man listesi JDK 8 stream() ak�� haz�r metoduyla (yanyana)==>
Kar�lan arg�man listesi for-each y�ntemiyle (yanyana)==>

**  >java J6b_3q2 Mahmut NihatYava� 17 Nisan 1957 Ye�ilyurt Malatya TR  ** TEKRAR

Kar�lan arg�man listesi JDK 8 stream() ak�� haz�r metoduyla (yanyana)==>
Malatya Mahmut 17 Nihat 1957 Ye�ilyurt Yava� Nisan TR

Kar�lan arg�man listesi for-each y�ntemiyle (yanyana)==>
Malatya Mahmut 17 Nihat 1957 Ye�ilyurt Yava� Nisan TR
*/