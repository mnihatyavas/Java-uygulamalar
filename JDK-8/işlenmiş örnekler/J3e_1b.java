// J3e_1b.java: MatchesLooking (EþleþirGörünen) örneði.

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class J3e_1b {
    private static String regex; // regex: RegularExpression/DüzenliÝfade...
    private static String dizge;
    private static Pattern kalýp; // Derlenmiþ regex...
    private static Matcher eþleþenler;

    public static void main (String[] args) {
        dizge = args.length > 0? args[0] : "fooooooooooooooooo";
        regex = args.length > 1? args[1] : "foo";

        kalýp = Pattern.compile (regex);
        eþleþenler = kalýp.matcher (dizge);

        System.out.println ("Aktüel dizge: [" + dizge + "]");
        System.out.println ("Aktüel Regex kalýbý: [" + regex + "]");
        System.out.println ("Görünen/lookingAt(): [" + eþleþenler.lookingAt() + "]");
        System.out.println ("Uyan/matches(): [" + eþleþenler.matches() + "]");
    } // main(..) metodu sonu...
} // J3e_1b sýnýfý sonu...

/* Çýktý:
**  >java J3e_1b  **
Aktüel dizge: [fooooooooooooooooo]
Aktüel Regex kalýbý: [foo]
Görünen/lookingAt(): [true]
Uyan/matches(): [false]

**  >java J3e_1b "köpek" "\bköpek\b"  ** TEKRAR
Aktüel dizge: [köpek]
Aktüel Regex kalýbý: [\bköpek\b]
Görünen/lookingAt(): [true]
Uyan/matches(): [true]

**  >java J3e_1b "köpekkk" "\bköpek\b"  ** TEKRAR
Aktüel dizge: [köpekkk]
Aktüel Regex kalýbý: [\bköpek\b]
Görünen/lookingAt(): [false]
Uyan/matches(): [false]

**  >java J3e_1b "köpekkk" "köpek"  ** TEKRAR
Aktüel dizge: [köpekkk]
Aktüel Regex kalýbý: [köpek]
Görünen/lookingAt(): [true]
Uyan/matches(): [false]
*/