// J3e_2b.java: RegexDemo (D�zenliifadeG�sterimi) �rne�i.

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class J3e_2b {
    private static String REGEX;
    private static String G�RD�;
    private static String DE���T�R;

    public static void main (String[] args) {
        G�RD� = args.length > 0? args[0] : "aabfooaabfooabfoob";
        REGEX = args.length > 1? args[1] : "a*b";
        DE���T�R = args.length > 2? args[2] : "-";
        System.out.printf ("Girdi: [%s]%nRegex: [%s]%nDe�i�tir: [%s]%n", G�RD�, REGEX, DE���T�R);
        Pattern kal�p = Pattern.compile (REGEX);

        Matcher e�le�enler = kal�p.matcher (G�RD�); // E�le�enlerin bir nesnesini yaratal�m...
        StringBuffer sb = new StringBuffer();
        while (e�le�enler.find()) e�le�enler.appendReplacement (sb, DE���T�R);
        e�le�enler.appendTail (sb); // G�RD� son harfi eklenir...
        System.out.println ("E�le�enlerin de�i�tirilme sonucu: [" + sb.toString() + "]");
    } // main(..) metodu sonu...
} // J3e_2b s�n�f� sonu...

/* ��kt�:
**  >java J3e_2b  **
Girdi: [aabfooaabfooabfoob]
Regex: [a*b]
De�i�tir: [-]
E�le�enlerin de�i�tirilme sonucu: [-foo-foo-foo-]

**  >java J3e_2b itmei�tirkti�te "i*t" "-->"  ** TEKRAR
Girdi: [itmei�tirkti�te]
Regex: [i*t]
De�i�tir: [-->]
E�le�enlerin de�i�tirilme sonucu: [-->mei�-->irk-->i�-->e]
*/