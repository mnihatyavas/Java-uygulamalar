// J7bq_3.java: MonthsInYear (Y�l�nAylar�) �rne�i.

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.DateTimeException;

// Girilen y�l arg�man�n�n ay �ekimlerini d�ker...
public class J7bq_3 {
    public static void main (String[] arg�man) {
        int y�l = 0;

        if (arg�man.length < 1) {
            System.err.println (">java J7bq_3 <y�l (yyyy)>");
            throw new IllegalArgumentException(); // java.lang.*
        } // if karar� sonu...

        try {y�l = Integer.parseInt (arg�man[0]);
        }catch (NumberFormatException ist) {// java.lang.*
            System.err.printf ("[%s] arg�man� 4 basamakl� bir y�l rakam� de�il!%n", arg�man[0]);
            throw ist;
        } // try-catch blo�u sonu...

        System.out.printf ("[%d] y�l�'n�n ay �ekimleri:%n", y�l);
        for (Month ayAd� : Month.values()) {
            YearMonth y�l�nAy� = YearMonth.of (y�l, ayAd�);
            System.out.printf ("%9s ay� %d g�n �eker%n", ayAd�, y�l�nAy�.lengthOfMonth());
        } // for d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J7bq_3 s�n�f� sonu...

/* ��kt�:
**  >java J7bq_3  **
>java J7bq_3 <y�l (yyyy)>
Exception in thread "main" java.lang.IllegalArgumentException
        at J7bq_3.main(J7bq_3.java:18)

**  >java J7bq_3 201w  ** TEKRAR
[201w] arg�man� 4 basamakl� bir y�l rakam� de�il!
Exception in thread "main" java.lang.NumberFormatException: For input string: "201w"
        at java.lang.NumberFormatException.forInputString(Unknown Source)
        at java.lang.Integer.parseInt(Unknown Source)
        at java.lang.Integer.parseInt(Unknown Source)
        at J7bq_3.main(J7bq_3.java:21)

**  >java J7bq_3 2018  ** TEKRAR
[2018] y�l�'n�n ay �ekimleri:
  JANUARY ay� 31 g�n �eker
 FEBRUARY ay� 28 g�n �eker
    MARCH ay� 31 g�n �eker
    APRIL ay� 30 g�n �eker
      MAY ay� 31 g�n �eker
     JUNE ay� 30 g�n �eker
     JULY ay� 31 g�n �eker
   AUGUST ay� 31 g�n �eker
SEPTEMBER ay� 30 g�n �eker
  OCTOBER ay� 31 g�n �eker
 NOVEMBER ay� 30 g�n �eker
 DECEMBER ay� 31 g�n �eker

**  >java J7bq_3 2020  ** TEKRAR
[2020] y�l�'n�n ay �ekimleri:
  JANUARY ay� 31 g�n �eker
 FEBRUARY ay� 29 g�n �eker
    MARCH ay� 31 g�n �eker
    APRIL ay� 30 g�n �eker
      MAY ay� 31 g�n �eker
     JUNE ay� 30 g�n �eker
     JULY ay� 31 g�n �eker
   AUGUST ay� 31 g�n �eker
SEPTEMBER ay� 30 g�n �eker
  OCTOBER ay� 31 g�n �eker
 NOVEMBER ay� 30 g�n �eker
 DECEMBER ay� 31 g�n �eker
*/