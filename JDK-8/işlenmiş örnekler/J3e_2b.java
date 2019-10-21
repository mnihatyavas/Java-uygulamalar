// J3e_2b.java: RegexDemo (DüzenliifadeGösterimi) örneði.

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class J3e_2b {
    private static String REGEX;
    private static String GÝRDÝ;
    private static String DEÐÝÞTÝR;

    public static void main (String[] args) {
        GÝRDÝ = args.length > 0? args[0] : "aabfooaabfooabfoob";
        REGEX = args.length > 1? args[1] : "a*b";
        DEÐÝÞTÝR = args.length > 2? args[2] : "-";
        System.out.printf ("Girdi: [%s]%nRegex: [%s]%nDeðiþtir: [%s]%n", GÝRDÝ, REGEX, DEÐÝÞTÝR);
        Pattern kalýp = Pattern.compile (REGEX);

        Matcher eþleþenler = kalýp.matcher (GÝRDÝ); // Eþleþenlerin bir nesnesini yaratalým...
        StringBuffer sb = new StringBuffer();
        while (eþleþenler.find()) eþleþenler.appendReplacement (sb, DEÐÝÞTÝR);
        eþleþenler.appendTail (sb); // GÝRDÝ son harfi eklenir...
        System.out.println ("Eþleþenlerin deðiþtirilme sonucu: [" + sb.toString() + "]");
    } // main(..) metodu sonu...
} // J3e_2b sýnýfý sonu...

/* Çýktý:
**  >java J3e_2b  **
Girdi: [aabfooaabfooabfoob]
Regex: [a*b]
Deðiþtir: [-]
Eþleþenlerin deðiþtirilme sonucu: [-foo-foo-foo-]

**  >java J3e_2b itmeiçtirktiþte "i*t" "-->"  ** TEKRAR
Girdi: [itmeiçtirktiþte]
Regex: [i*t]
Deðiþtir: [-->]
Eþleþenlerin deðiþtirilme sonucu: [-->meiç-->irk-->iþ-->e]
*/