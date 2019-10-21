// J2bq_1.java: NestedIf (ÝçiçeIf) örneði.

class J2bq_1 {
    public static void main (String[] args) {
        int sayý = 0;
        try {sayý = Integer.valueOf (args[0]);
        }catch (Exception ist) {System.out.println ("Yanlýþ sayý giriþi!"); System.exit (0);}

        if (sayý >= 0)
            if (sayý == 0) System.out.println ("Sayý sýfýr'dýr.");
            else System.out.println ("Sayý pozitiftir.");
        else System.out.println ("Sayý negatiftir.");
    } // main(..) metodu sonu...
} // J2bq_1 sýnýfý sonu...

/* Çýktý:
**  >java J2bq_1  **
Yanlýþ sayý giriþi!

**  >java J2bq_1 sayý  ** TEKRAR
Yanlýþ sayý giriþi!

**  >java J2bq_1 -0  ** TEKRAR
Sayý sýfýr'dýr.

**  >java J2bq_1 -29  ** TEKRAR
Sayý negatiftir.

**  >java J2bq_1 9  ** TEKRAR
Sayý pozitiftir.
*/