// J7bq_2.java: ListMondays (PazartesileriListele) �rne�i.

import java.time.Month;
import java.time.Year;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

// Girilen ay�n t�m pazartesilerini altalta listeler...
public class J7bq_2 {
    public static void main (String[] arg�man) {
        Month ay = null;

        if (arg�man.length < 1) {
            System.err.printf (">java J7bq_2 <ay>%n");
            throw new IllegalArgumentException();
        } // if karar� sonu...

        try {ay = Month.valueOf (arg�man[0].toUpperCase());
        }catch (IllegalArgumentException ist) {
            System.err.printf ("%s ge�erli bir ay ad� de�ildir.%n", arg�man[0]);
            throw ist;
        } // try-catch blo�u sonu...

        System.out.printf ("%s ay�na ait t�m Pazartesi'ler:%n", ay);
        LocalDate tarih = Year.now().atMonth (ay).atDay (1).
              with (TemporalAdjusters.firstInMonth (DayOfWeek.MONDAY));
        Month aylar = tarih.getMonth();
        while (aylar == ay) {
            System.out.printf ("%s%n", tarih);
            tarih = tarih.with (TemporalAdjusters.next (DayOfWeek.MONDAY));
            aylar = tarih.getMonth();
        } // while d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J7bq_2 s�n�f� sonu...

/* ��kt�:
**  >java J7bq_2
>java J7bq_2 <ay>
Exception in thread "main" java.lang.IllegalArgumentException
        at J7bq_2.main(J7bq_2.java:16)

**  >java J7bq_2 november  ** TEKRAR
NOVEMBER ay�na ait t�m Pazartesi'ler:
2018-11-05
2018-11-12
2018-11-19
2018-11-26
*/