// J3e_1b.java: MatchesLooking (E�le�irG�r�nen) �rne�i.

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class J3e_1b {
    private static String regex; // regex: RegularExpression/D�zenli�fade...
    private static String dizge;
    private static Pattern kal�p; // Derlenmi� regex...
    private static Matcher e�le�enler;

    public static void main (String[] args) {
        dizge = args.length > 0? args[0] : "fooooooooooooooooo";
        regex = args.length > 1? args[1] : "foo";

        kal�p = Pattern.compile (regex);
        e�le�enler = kal�p.matcher (dizge);

        System.out.println ("Akt�el dizge: [" + dizge + "]");
        System.out.println ("Akt�el Regex kal�b�: [" + regex + "]");
        System.out.println ("G�r�nen/lookingAt(): [" + e�le�enler.lookingAt() + "]");
        System.out.println ("Uyan/matches(): [" + e�le�enler.matches() + "]");
    } // main(..) metodu sonu...
} // J3e_1b s�n�f� sonu...

/* ��kt�:
**  >java J3e_1b  **
Akt�el dizge: [fooooooooooooooooo]
Akt�el Regex kal�b�: [foo]
G�r�nen/lookingAt(): [true]
Uyan/matches(): [false]

**  >java J3e_1b "k�pek" "\bk�pek\b"  ** TEKRAR
Akt�el dizge: [k�pek]
Akt�el Regex kal�b�: [\bk�pek\b]
G�r�nen/lookingAt(): [true]
Uyan/matches(): [true]

**  >java J3e_1b "k�pekkk" "\bk�pek\b"  ** TEKRAR
Akt�el dizge: [k�pekkk]
Akt�el Regex kal�b�: [\bk�pek\b]
G�r�nen/lookingAt(): [false]
Uyan/matches(): [false]

**  >java J3e_1b "k�pekkk" "k�pek"  ** TEKRAR
Akt�el dizge: [k�pekkk]
Akt�el Regex kal�b�: [k�pek]
G�r�nen/lookingAt(): [true]
Uyan/matches(): [false]
*/