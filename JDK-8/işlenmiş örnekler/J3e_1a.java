// J3e_1a.java: MatcherDemo (EþleþtiriciGösterimi) örneði.

import java.util.regex.Pattern; // Regex kalýp derleyici...
import java.util.regex.Matcher; // Eþleþenleri bulucu...

public class J3e_1a {
    private static final String RegexKalýbý = "\\bköpek\\b"; // b:blank/boþluk; noktalama hariç...
    private static String dizge;

    public static void main (String[] args) {
        dizge = args.length > 0? args[0] : "Köpek köpek! köpek? köpekcik köpekk ..köpek köpek.";

        Pattern derlenenKalýp = Pattern.compile (RegexKalýbý);

        // Uyanlarýn nesnesini oluþtur...
        Matcher uyanlarNesnesi = derlenenKalýp.matcher (dizge);

        System.out.println ("[" + dizge.length() + "] ebatlý: [" + dizge + "] ifadesindeki: [" + RegexKalýbý + "] kalýp eþleþmesi==>");
        int sayaç = 0;
        while (uyanlarNesnesi.find()) {
            sayaç++;
            System.out.println ("Eþleþme no: [" + sayaç + "]");
            System.out.println ("Ýlk endeks: [" + uyanlarNesnesi.start() + "]");
            System.out.println ("Son endeks: [" + uyanlarNesnesi.end() + "]\n");
        } // while döngüsü sonu...
    } // main(..) metodu sonu...
} // J3e_1a sýnýfý sonu...

/* Çýktý:
**  >java J3e_1a  **
[50] ebatlý: [Köpek köpek! köpek? köpekcik köpekk ..köpek köpek.] ifadesindeki: [\bköpek\b] kalýp eþleþmesi==>
Eþleþme no: [1]
Ýlk endeks: [6]
Son endeks: [11]

Eþleþme no: [2]
Ýlk endeks: [13]
Son endeks: [18]

Eþleþme no: [3]
Ýlk endeks: [38]
Son endeks: [43]

Eþleþme no: [4]
Ýlk endeks: [44]
Son endeks: [49]


**  >java J3e_1a "==köpek* ?köpek! //köpek% (köpek) [köpek] {köpek} ;;köpek:: ..köpek.."  ** TEKRAR
[69] ebatlý: [==köpek* ?köpek! //köpek% (köpek) [köpek] {köpek} ;;köpek:: ..köpek..] ifadesindeki: [\bköpek\b] kalýp eþleþmesi==>
Eþleþme no: [1]
Ýlk endeks: [2]
Son endeks: [7]

Eþleþme no: [2]
Ýlk endeks: [10]
Son endeks: [15]

Eþleþme no: [3]
Ýlk endeks: [19]
Son endeks: [24]

Eþleþme no: [4]
Ýlk endeks: [27]
Son endeks: [32]

Eþleþme no: [5]
Ýlk endeks: [35]
Son endeks: [40]

Eþleþme no: [6]
Ýlk endeks: [43]
Son endeks: [48]

Eþleþme no: [7]
Ýlk endeks: [52]
Son endeks: [57]

Eþleþme no: [8]
Ýlk endeks: [62]
Son endeks: [67]
*/