// J6b_3j.java: Shuffle (Kar��t�r) �rne�i.

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class J6b_3j {
    public static void main (String[] arg�manlar) {
        List<String> liste = Arrays.asList (arg�manlar);
        Collections.shuffle (liste);
        System.out.println ("Girilen arg�manlar�n kar�lan listesi: " + liste);
    } // main(..) metodu sonu...
} // J6b_3j s�n�f� sonu...

/* ��kt�:
**  >java J6b_3j  **
Girilen arg�manlar�n kar�lan listesi: []

**  >java J6b_3j Mahmut Nihat Yava� 17 Nisan 1957 Ye�ilyurt Malatya TR  ** TEKRAR
Girilen arg�manlar�n kar�lan listesi: [1957, TR, Nihat, 17, Nisan, Malatya, Mahmut, Ye�ilyurt, Yava�]

**  >java J6b_3j Mahmut Nihat Yava� 17 Nisan 1957 Ye�ilyurt Malatya TR  ** TEKRAR
Girilen arg�manlar�n kar�lan listesi: [Yava�, Nihat, Malatya, Nisan, 17, 1957, Mahmut, Ye�ilyurt, TR]
*/