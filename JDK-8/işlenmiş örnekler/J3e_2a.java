// J3e_2a.java: ReplaceDemo (DeðiþtirGösterimi) örneði.

import java.util.regex.Pattern; 
import java.util.regex.Matcher;

public class J3e_2a {
    private static String REGEX;
    private static String GÝRDÝ;
    private static String DEÐÝÞTÝR;
 
    public static void main (String[] args) {
        GÝRDÝ = args.length > 0? args[0] : "The dog says meow. All dogs say meow.";
        REGEX = args.length > 1? args[1] : "dog";
        DEÐÝÞTÝR = args.length > 2? args[2] : "cat";
        System.out.printf ("Girdi: [%s]%nRegex: [%s]%nDeðiþtir: [%s]%n", GÝRDÝ, REGEX, DEÐÝÞTÝR);
        Pattern kalýp = Pattern.compile (REGEX);

        // Eþleþenler nesnesini yaratalým...
        Matcher eþleþenler = kalýp.matcher (GÝRDÝ);
        GÝRDÝ = eþleþenler.replaceAll (DEÐÝÞTÝR); // Eþleþen dog'lar cat ile deðiþtirilir...
        System.out.println ("\nEþleþenleri deðiþen Girdi: [" + GÝRDÝ + "]");
    } // main(..) metodu sonu...
} // J3e_2a sýnýfý sonu...

/* Çýktý:
**  >java J3e_2a  **
Girdi: [The dog says meow. All dogs say meow.]
Regex: [dog]
Deðiþtir: [cat]

Eþleþenleri deðiþen Girdi: [The cat says meow. All cats say meow.]

**  >java J3e_2a "Benim kedi havlar. Tüm kediler havlarlar." kedi köpek  ** TEKRAR
Girdi: [Benim kedi havlar. Tüm kediler havlarlar.]
Regex: [kedi]
Deðiþtir: [köpek]

Eþleþenleri deðiþen Girdi: [Benim köpek havlar. Tüm köpekler havlarlar.]
*/