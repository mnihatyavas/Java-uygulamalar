// J7bq_3.java: MonthsInYear (YýlýnAylarý) örneði.

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.DateTimeException;

// Girilen yýl argümanýnýn ay çekimlerini döker...
public class J7bq_3 {
    public static void main (String[] argüman) {
        int yýl = 0;

        if (argüman.length < 1) {
            System.err.println (">java J7bq_3 <yýl (yyyy)>");
            throw new IllegalArgumentException(); // java.lang.*
        } // if kararý sonu...

        try {yýl = Integer.parseInt (argüman[0]);
        }catch (NumberFormatException ist) {// java.lang.*
            System.err.printf ("[%s] argümaný 4 basamaklý bir yýl rakamý deðil!%n", argüman[0]);
            throw ist;
        } // try-catch bloðu sonu...

        System.out.printf ("[%d] yýlý'nýn ay çekimleri:%n", yýl);
        for (Month ayAdý : Month.values()) {
            YearMonth yýlýnAyý = YearMonth.of (yýl, ayAdý);
            System.out.printf ("%9s ayý %d gün çeker%n", ayAdý, yýlýnAyý.lengthOfMonth());
        } // for döngüsü sonu...
    } // main(..) metodu sonu...
} // J7bq_3 sýnýfý sonu...

/* Çýktý:
**  >java J7bq_3  **
>java J7bq_3 <yýl (yyyy)>
Exception in thread "main" java.lang.IllegalArgumentException
        at J7bq_3.main(J7bq_3.java:18)

**  >java J7bq_3 201w  ** TEKRAR
[201w] argümaný 4 basamaklý bir yýl rakamý deðil!
Exception in thread "main" java.lang.NumberFormatException: For input string: "201w"
        at java.lang.NumberFormatException.forInputString(Unknown Source)
        at java.lang.Integer.parseInt(Unknown Source)
        at java.lang.Integer.parseInt(Unknown Source)
        at J7bq_3.main(J7bq_3.java:21)

**  >java J7bq_3 2018  ** TEKRAR
[2018] yýlý'nýn ay çekimleri:
  JANUARY ayý 31 gün çeker
 FEBRUARY ayý 28 gün çeker
    MARCH ayý 31 gün çeker
    APRIL ayý 30 gün çeker
      MAY ayý 31 gün çeker
     JUNE ayý 30 gün çeker
     JULY ayý 31 gün çeker
   AUGUST ayý 31 gün çeker
SEPTEMBER ayý 30 gün çeker
  OCTOBER ayý 31 gün çeker
 NOVEMBER ayý 30 gün çeker
 DECEMBER ayý 31 gün çeker

**  >java J7bq_3 2020  ** TEKRAR
[2020] yýlý'nýn ay çekimleri:
  JANUARY ayý 31 gün çeker
 FEBRUARY ayý 29 gün çeker
    MARCH ayý 31 gün çeker
    APRIL ayý 30 gün çeker
      MAY ayý 31 gün çeker
     JUNE ayý 30 gün çeker
     JULY ayý 31 gün çeker
   AUGUST ayý 31 gün çeker
SEPTEMBER ayý 30 gün çeker
  OCTOBER ayý 31 gün çeker
 NOVEMBER ayý 30 gün çeker
 DECEMBER ayý 31 gün çeker
*/