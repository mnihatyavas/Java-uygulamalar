// J3e_2a.java: ReplaceDemo (De�i�tirG�sterimi) �rne�i.

import java.util.regex.Pattern; 
import java.util.regex.Matcher;

public class J3e_2a {
    private static String REGEX;
    private static String G�RD�;
    private static String DE���T�R;
 
    public static void main (String[] args) {
        G�RD� = args.length > 0? args[0] : "The dog says meow. All dogs say meow.";
        REGEX = args.length > 1? args[1] : "dog";
        DE���T�R = args.length > 2? args[2] : "cat";
        System.out.printf ("Girdi: [%s]%nRegex: [%s]%nDe�i�tir: [%s]%n", G�RD�, REGEX, DE���T�R);
        Pattern kal�p = Pattern.compile (REGEX);

        // E�le�enler nesnesini yaratal�m...
        Matcher e�le�enler = kal�p.matcher (G�RD�);
        G�RD� = e�le�enler.replaceAll (DE���T�R); // E�le�en dog'lar cat ile de�i�tirilir...
        System.out.println ("\nE�le�enleri de�i�en Girdi: [" + G�RD� + "]");
    } // main(..) metodu sonu...
} // J3e_2a s�n�f� sonu...

/* ��kt�:
**  >java J3e_2a  **
Girdi: [The dog says meow. All dogs say meow.]
Regex: [dog]
De�i�tir: [cat]

E�le�enleri de�i�en Girdi: [The cat says meow. All cats say meow.]

**  >java J3e_2a "Benim kedi havlar. T�m kediler havlarlar." kedi k�pek  ** TEKRAR
Girdi: [Benim kedi havlar. T�m kediler havlarlar.]
Regex: [kedi]
De�i�tir: [k�pek]

E�le�enleri de�i�en Girdi: [Benim k�pek havlar. T�m k�pekler havlarlar.]
*/