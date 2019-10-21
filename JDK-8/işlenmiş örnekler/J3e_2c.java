// J3e_2c.java: ReplaceDemo2 (De�i�tirG�sterimi2) �rne�i.

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class J3e_2c {
    private static String REGEX;
    private static String G�RD�;
    private static String DE���T�R;

    public static void main (String[] args) {
        G�RD� = args.length > 0? args[0] : "aabfooaabfooabfoob";
        REGEX = args.length > 1? args[1] : "a*b";
        DE���T�R = args.length > 2? args[2] : "-";

        System.out.printf ("Girdi: [%s]%nRegex: [%s]%nDe�i�tir: [%s]%n", G�RD�, REGEX, DE���T�R);

        Pattern kal�p = Pattern.compile (REGEX);

        Matcher e�le�enler = kal�p.matcher (G�RD�); // E�le�enlerin nesnesini alal�m...
        G�RD� = e�le�enler.replaceAll (DE���T�R);
        System.out.println ("E�le�enleri de�i�en Girdi: [" + G�RD� + "]");
    } // main(..) metodu sonu...
} // J3e_2c s�n�f� sonu...

/* ��kt�:
**  >java J3e_2c  **
Girdi: [aabfooaabfooabfoob]
Regex: [a*b]
De�i�tir: [-]
E�le�enleri de�i�en Girdi: [-foo-foo-foo-]

**  >java J3e_2c i�tirtifaiiittifaki�ten "i*t" "-->"  ** TEKRAR
Girdi: [i�tirtifaiiittifaki�ten]
Regex: [i*t]
De�i�tir: [-->]
E�le�enleri de�i�en Girdi: [i�-->ir-->ifa-->-->ifaki�-->en]
*/