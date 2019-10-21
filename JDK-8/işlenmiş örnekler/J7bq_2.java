// J7bq_2.java: ListMondays (PazartesileriListele) örneði.

import java.time.Month;
import java.time.Year;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

// Girilen ayýn tüm pazartesilerini altalta listeler...
public class J7bq_2 {
    public static void main (String[] argüman) {
        Month ay = null;

        if (argüman.length < 1) {
            System.err.printf (">java J7bq_2 <ay>%n");
            throw new IllegalArgumentException();
        } // if kararý sonu...

        try {ay = Month.valueOf (argüman[0].toUpperCase());
        }catch (IllegalArgumentException ist) {
            System.err.printf ("%s geçerli bir ay adý deðildir.%n", argüman[0]);
            throw ist;
        } // try-catch bloðu sonu...

        System.out.printf ("%s ayýna ait tüm Pazartesi'ler:%n", ay);
        LocalDate tarih = Year.now().atMonth (ay).atDay (1).
              with (TemporalAdjusters.firstInMonth (DayOfWeek.MONDAY));
        Month aylar = tarih.getMonth();
        while (aylar == ay) {
            System.out.printf ("%s%n", tarih);
            tarih = tarih.with (TemporalAdjusters.next (DayOfWeek.MONDAY));
            aylar = tarih.getMonth();
        } // while döngüsü sonu...
    } // main(..) metodu sonu...
} // J7bq_2 sýnýfý sonu...

/* Çýktý:
**  >java J7bq_2
>java J7bq_2 <ay>
Exception in thread "main" java.lang.IllegalArgumentException
        at J7bq_2.main(J7bq_2.java:16)

**  >java J7bq_2 november  ** TEKRAR
NOVEMBER ayýna ait tüm Pazartesi'ler:
2018-11-05
2018-11-12
2018-11-19
2018-11-26
*/