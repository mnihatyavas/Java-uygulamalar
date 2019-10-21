// J3d_1.java: Echo (Yanký) örneði.

public class J3d_1 {
    public static void main (String[] args) {
        if (args.length < 1) System.err.println ("Argüman girin lütfen!");
        else System.out.println ("Girdiðiniz argüman/lar/ýn yankýsý==>");
        for (String element: args) {System.out.println (element);}
    } // main(..) metodu sonu...
} // J3d_1 sýnýfý sonu...

/* Çýktý:
**  >java J3d_1  **
Argüman girin lütfen!

**  >java J3d_1 M. Nihat Yavaþ, Toroslar - Mersin, TR  ** TEKRAR
Girdiðiniz argüman/lar/ýn yankýsý==>
M.
Nihat
Yavaþ,
Toroslar
-
Mersin,
TR

**  >java J3d_1 "M. Nihat Yavaþ, Toroslar - Mersin, TR"  ** TEKRAR
Girdiðiniz argüman/lar/ýn yankýsý==>
M. Nihat Yavaþ, Toroslar - Mersin, TR
*/