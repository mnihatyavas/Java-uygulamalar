// J3e_3.java: SplitDemo (Ay�rG�sterimi) �rne�i.

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class J3e_3 {
    private static String REGEX;
    private static String G�RD�;
    
    public static void main (String[] args) {
        G�RD� = args.length > 0? args[0] : "bir:iki:��:d�rt:be�:alt�:yedi";
        REGEX = args.length > 1? args[1] : ":";
        System.out.printf ("Girdi: [%s]%nRegex: [%s]%n", G�RD�, REGEX);

        Pattern kal�p = Pattern.compile (REGEX);
        String[] birimler = kal�p.split (G�RD�);

        System.out.println ("\nGirdi'nin Regex'le ayr��an d�k�m�:");
        for (String birim : birimler) System.out.println (birim);
    } // main(..) metodu sonu...
} // J3e_3 s�n�f� sonu...

/* ��kt�:
**  >java J3e_3  **
Girdi: [bir:iki:��:d�rt:be�:alt�:yedi]
Regex: [:]

Girdi'nin Regex'le ayr��an d�k�m�:
bir
iki
��
d�rt
be�
alt�
yedi

**  >java J3e_3 "M. Nihat Yava�, Toroslar - Mersin, TR" " "  ** TR
Girdi: [M. Nihat Yava�, Toroslar - Mersin, TR]
Regex: [ ]

Girdi'nin Regex'le ayr��an d�k�m�:
M.
Nihat
Yava�,
Toroslar
-
Mersin,
TR

**  >java J3e_3 bir4iki5��0d�rt2be�8alt�6yedi \d  ** TEKRAR
Girdi: [bir4iki5��0d�rt2be�8alt�6yedi]
Regex: [\d]

Girdi'nin Regex'le ayr��an d�k�m�:
bir
iki
��
d�rt
be�
alt�
yedi
*/