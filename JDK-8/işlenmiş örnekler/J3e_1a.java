// J3e_1a.java: MatcherDemo (E�le�tiriciG�sterimi) �rne�i.

import java.util.regex.Pattern; // Regex kal�p derleyici...
import java.util.regex.Matcher; // E�le�enleri bulucu...

public class J3e_1a {
    private static final String RegexKal�b� = "\\bk�pek\\b"; // b:blank/bo�luk; noktalama hari�...
    private static String dizge;

    public static void main (String[] args) {
        dizge = args.length > 0? args[0] : "K�pek k�pek! k�pek? k�pekcik k�pekk ..k�pek k�pek.";

        Pattern derlenenKal�p = Pattern.compile (RegexKal�b�);

        // Uyanlar�n nesnesini olu�tur...
        Matcher uyanlarNesnesi = derlenenKal�p.matcher (dizge);

        System.out.println ("[" + dizge.length() + "] ebatl�: [" + dizge + "] ifadesindeki: [" + RegexKal�b� + "] kal�p e�le�mesi==>");
        int saya� = 0;
        while (uyanlarNesnesi.find()) {
            saya�++;
            System.out.println ("E�le�me no: [" + saya� + "]");
            System.out.println ("�lk endeks: [" + uyanlarNesnesi.start() + "]");
            System.out.println ("Son endeks: [" + uyanlarNesnesi.end() + "]\n");
        } // while d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J3e_1a s�n�f� sonu...

/* ��kt�:
**  >java J3e_1a  **
[50] ebatl�: [K�pek k�pek! k�pek? k�pekcik k�pekk ..k�pek k�pek.] ifadesindeki: [\bk�pek\b] kal�p e�le�mesi==>
E�le�me no: [1]
�lk endeks: [6]
Son endeks: [11]

E�le�me no: [2]
�lk endeks: [13]
Son endeks: [18]

E�le�me no: [3]
�lk endeks: [38]
Son endeks: [43]

E�le�me no: [4]
�lk endeks: [44]
Son endeks: [49]


**  >java J3e_1a "==k�pek* ?k�pek! //k�pek% (k�pek) [k�pek] {k�pek} ;;k�pek:: ..k�pek.."  ** TEKRAR
[69] ebatl�: [==k�pek* ?k�pek! //k�pek% (k�pek) [k�pek] {k�pek} ;;k�pek:: ..k�pek..] ifadesindeki: [\bk�pek\b] kal�p e�le�mesi==>
E�le�me no: [1]
�lk endeks: [2]
Son endeks: [7]

E�le�me no: [2]
�lk endeks: [10]
Son endeks: [15]

E�le�me no: [3]
�lk endeks: [19]
Son endeks: [24]

E�le�me no: [4]
�lk endeks: [27]
Son endeks: [32]

E�le�me no: [5]
�lk endeks: [35]
Son endeks: [40]

E�le�me no: [6]
�lk endeks: [43]
Son endeks: [48]

E�le�me no: [7]
�lk endeks: [52]
Son endeks: [57]

E�le�me no: [8]
�lk endeks: [62]
Son endeks: [67]
*/