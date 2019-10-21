// J7bq_1.java: Superstitious (Bat�linan�) �rne�i.

import java.time.Month;
import java.time.Year;
import java.time.LocalDate;
import java.time.DateTimeException;

// Gerekli dosya: J7bq_1x.java=FridayThirteenQuery/CumaOn��Sorgusu.java
public class J7bq_1 {
    
    public static void main (String[] arg�manlar) {
        Month ay = null;
        LocalDate tarih = null;

        if (arg�manlar.length < 2) {
            System.err.printf (">java J7bq_1 <ay> <g�n>%n");
            throw new IllegalArgumentException(); // java.lang.*
        } // if karar� sonu...

        try {ay = Month.valueOf (arg�manlar[0].toUpperCase());
        }catch (IllegalArgumentException ist) {
            System.err.printf ("[%s] ge�erli bir ay ad� de�ildir.%n", arg�manlar[0]);
            throw ist;
        } // try-catch blo�u sonu...

        int g�n = Integer.parseInt (arg�manlar[1]);

        try {tarih = Year.now().atMonth (ay).atDay (g�n);
        }catch (DateTimeException ist) {
            System.err.printf ("[%s %s] ge�erli bir tarih de�ildir.%n", ay, g�n);
            throw ist;
        } // try-catch blo�u sonu...

        System.out.println (tarih.query (new J7bq_1x()));
    } // main(..) metodu sonu...
} // J7bq_1 s�n�f� sonu...

/* ��kt�:
**  >java J7bq_1 **
>java J7bq_1 <ay> <g�n>
Exception in thread "main" java.lang.IllegalArgumentException
        at J7bq_1.main(J7bq_1.java:17)

**  >java J7bq_1 november 13  ** TEKRAR
false

**  >java J7bq_1 apr�l 13  ** TEKRAR
true
*/