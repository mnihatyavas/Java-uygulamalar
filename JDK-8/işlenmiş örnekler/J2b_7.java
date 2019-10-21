// J2b_7.java: ComparisonDemo (KarþýlaþtýrmaGösterimi) örneði.

class J2b_7 {
    public static void main (String[] args) {
        int deðer1 = 1, deðer2 = 2;

        try {deðer1 = Integer.valueOf (args[0]); }catch (Exception ist) {deðer1 = -1;}
        try {deðer2 = Integer.valueOf (args[1]); }catch (Exception ist) {deðer2 = -2;}

        System.out.println ("deðer1=" + deðer1 + " ve deðer2=" + deðer2);
        if (deðer1 == deðer2) System.out.println ("deðer1 == deðer2");
        if (deðer1 != deðer2) System.out.println ("deðer1 != deðer2");
        if (deðer1 > deðer2) System.out.println ("deðer1 > deðer2");
        if (deðer1 < deðer2) System.out.println ("deðer1 < deðer2");
        if (deðer1 <= deðer2) System.out.println ("deðer1 <= deðer2");
        if (deðer1 >= deðer2) System.out.println ("deðer1 >= deðer2");
    } // main(..) metodu sonu...
} // J2b_7 sýnýfý sonu...

/* Çýktý:
**  >java J2b_7  **
deðer1=-1 ve deðer2=-2
deðer1 != deðer2
deðer1 > deðer2
deðer1 >= deðer2

**  >java J2b_7 -2  ** TEKRAR
deðer1=-2 ve deðer2=-2
deðer1 == deðer2
deðer1 <= deðer2
deðer1 >= deðer2

**  >java J2b_7 3 9  ** TEKRAR
deðer1=3 ve deðer2=9
deðer1 != deðer2
deðer1 < deðer2
deðer1 <= deðer2
*/