// J3d_1.java: Echo (Yank�) �rne�i.

public class J3d_1 {
    public static void main (String[] args) {
        if (args.length < 1) System.err.println ("Arg�man girin l�tfen!");
        else System.out.println ("Girdi�iniz arg�man/lar/�n yank�s�==>");
        for (String element: args) {System.out.println (element);}
    } // main(..) metodu sonu...
} // J3d_1 s�n�f� sonu...

/* ��kt�:
**  >java J3d_1  **
Arg�man girin l�tfen!

**  >java J3d_1 M. Nihat Yava�, Toroslar - Mersin, TR  ** TEKRAR
Girdi�iniz arg�man/lar/�n yank�s�==>
M.
Nihat
Yava�,
Toroslar
-
Mersin,
TR

**  >java J3d_1 "M. Nihat Yava�, Toroslar - Mersin, TR"  ** TEKRAR
Girdi�iniz arg�man/lar/�n yank�s�==>
M. Nihat Yava�, Toroslar - Mersin, TR
*/