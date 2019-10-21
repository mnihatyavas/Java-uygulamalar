// J3e_2c.java: ReplaceDemo2 (DeğiştirGösterimi2) örneği.

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class J3e_2c {
    private static String REGEX;
    private static String GİRDİ;
    private static String DEĞİŞTİR;

    public static void main (String[] args) {
        GİRDİ = args.length > 0? args[0] : "aabfooaabfooabfoob";
        REGEX = args.length > 1? args[1] : "a*b";
        DEĞİŞTİR = args.length > 2? args[2] : "-";

        System.out.printf ("Girdi: [%s]%nRegex: [%s]%nDeğiştir: [%s]%n", GİRDİ, REGEX, DEĞİŞTİR);

        Pattern kalıp = Pattern.compile (REGEX);

        Matcher eşleşenler = kalıp.matcher (GİRDİ); // Eşleşenlerin nesnesini alalım...
        GİRDİ = eşleşenler.replaceAll (DEĞİŞTİR);
        System.out.println ("Eşleşenleri değişen Girdi: [" + GİRDİ + "]");
    } // main(..) metodu sonu...
} // J3e_2c sınıfı sonu...

/* Çıktı:
**  >java J3e_2c  **
Girdi: [aabfooaabfooabfoob]
Regex: [a*b]
Değiştir: [-]
Eşleşenleri değişen Girdi: [-foo-foo-foo-]

**  >java J3e_2c içtirtifaiiittifakiçten "i*t" "-->"  ** TEKRAR
Girdi: [içtirtifaiiittifakiçten]
Regex: [i*t]
Değiştir: [-->]
Eşleşenleri değişen Girdi: [iç-->ir-->ifa-->-->ifakiç-->en]
*/