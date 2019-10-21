// J3e_3.java: SplitDemo (AyýrGösterimi) örneði.

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class J3e_3 {
    private static String REGEX;
    private static String GÝRDÝ;
    
    public static void main (String[] args) {
        GÝRDÝ = args.length > 0? args[0] : "bir:iki:üç:dört:beþ:altý:yedi";
        REGEX = args.length > 1? args[1] : ":";
        System.out.printf ("Girdi: [%s]%nRegex: [%s]%n", GÝRDÝ, REGEX);

        Pattern kalýp = Pattern.compile (REGEX);
        String[] birimler = kalýp.split (GÝRDÝ);

        System.out.println ("\nGirdi'nin Regex'le ayrýþan dökümü:");
        for (String birim : birimler) System.out.println (birim);
    } // main(..) metodu sonu...
} // J3e_3 sýnýfý sonu...

/* Çýktý:
**  >java J3e_3  **
Girdi: [bir:iki:üç:dört:beþ:altý:yedi]
Regex: [:]

Girdi'nin Regex'le ayrýþan dökümü:
bir
iki
üç
dört
beþ
altý
yedi

**  >java J3e_3 "M. Nihat Yavaþ, Toroslar - Mersin, TR" " "  ** TR
Girdi: [M. Nihat Yavaþ, Toroslar - Mersin, TR]
Regex: [ ]

Girdi'nin Regex'le ayrýþan dökümü:
M.
Nihat
Yavaþ,
Toroslar
-
Mersin,
TR

**  >java J3e_3 bir4iki5üç0dört2beþ8altý6yedi \d  ** TEKRAR
Girdi: [bir4iki5üç0dört2beþ8altý6yedi]
Regex: [\d]

Girdi'nin Regex'le ayrýþan dökümü:
bir
iki
üç
dört
beþ
altý
yedi
*/