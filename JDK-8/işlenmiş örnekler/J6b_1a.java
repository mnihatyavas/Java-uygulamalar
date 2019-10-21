// J6b_1a.java: Sort (S�ralama) �rne�i.

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class J6b_1a {
    public static void main (String[] arg�manlar) {
        if (arg�manlar.length < 2) {
            System.out.println ("Enaz s�ralanacak 2 arg�man girmeyi unutmay�n!");
            System.exit (0);
        } // if karar� sonu...

        List<String> liste = Arrays.asList (arg�manlar);
        Collections.sort (liste); // Dizgeye �evirir ve [0->z] s�ralar...
        System.out.println ("Girdi�iniz arg�manlar�n s�ral� (yanyana) listesi: " + liste);
        System.out.println ("\nGirdi�iniz arg�manlar�n s�ral� (altalta) listesi==>");
        for (String eleman : liste) {System.out.println (eleman);}
    } // main(..) metodu sonu...
} // J6b_1a s�n�f� sonu...

/* ��kt�:
**  >java J6b_1a  **
Enaz s�ralanacak 2 arg�man girmeyi unutmay�n!

**  >java J6b_1a Nihat Mahmut Yava� 1957 17 04 Malatya Ye�ilyurt TR  **
Girdi�iniz arg�manlar�n s�ral� (yanyana) listesi: [04, 17, 1957, Mahmut, Malatya, Nihat, TR, Yava�, Ye�ilyurt]

Girdi�iniz arg�manlar�n s�ral� (altalta) listesi==>
04
17
1957
Mahmut
Malatya
Nihat
TR
Yava�
Ye�ilyurt
*/