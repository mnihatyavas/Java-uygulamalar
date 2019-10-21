// J7bq_1.java: Superstitious (Batýlinanç) örneði.

import java.time.Month;
import java.time.Year;
import java.time.LocalDate;
import java.time.DateTimeException;

// Gerekli dosya: J7bq_1x.java=FridayThirteenQuery/CumaOnüçSorgusu.java
public class J7bq_1 {
    
    public static void main (String[] argümanlar) {
        Month ay = null;
        LocalDate tarih = null;

        if (argümanlar.length < 2) {
            System.err.printf (">java J7bq_1 <ay> <gün>%n");
            throw new IllegalArgumentException(); // java.lang.*
        } // if kararý sonu...

        try {ay = Month.valueOf (argümanlar[0].toUpperCase());
        }catch (IllegalArgumentException ist) {
            System.err.printf ("[%s] geçerli bir ay adý deðildir.%n", argümanlar[0]);
            throw ist;
        } // try-catch bloðu sonu...

        int gün = Integer.parseInt (argümanlar[1]);

        try {tarih = Year.now().atMonth (ay).atDay (gün);
        }catch (DateTimeException ist) {
            System.err.printf ("[%s %s] geçerli bir tarih deðildir.%n", ay, gün);
            throw ist;
        } // try-catch bloðu sonu...

        System.out.println (tarih.query (new J7bq_1x()));
    } // main(..) metodu sonu...
} // J7bq_1 sýnýfý sonu...

/* Çýktý:
**  >java J7bq_1 **
>java J7bq_1 <ay> <gün>
Exception in thread "main" java.lang.IllegalArgumentException
        at J7bq_1.main(J7bq_1.java:17)

**  >java J7bq_1 november 13  ** TEKRAR
false

**  >java J7bq_1 aprýl 13  ** TEKRAR
true
*/