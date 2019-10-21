// J7b_2.java: CheckDate (TarihKontrolu) örneði.

import java.time.Month;
import java.time.Year;
import java.time.LocalDate;
import java.time.DateTimeException;

/* Gereken dosyalar:
 *      J7b_2x1/FamilyVacations.java
 *      J7b_2x2/FamilyBirthgüns.java
 */
public class J7b_2 {
    public static void main (String[] argüman) {
        Month ay = null;
        LocalDate tarih = null;
        int gün = 0;

        if (argüman.length < 2) {
            System.err.printf ("Komut satýrý: [J7b_2 <ay/aprýl/may> <gün>] þeklinde olmalý!%n");
            throw new IllegalArgumentException ("Yanlýþ Argüman Ýstisnasý");
        } // if kararý sonu...

        try {ay = Month.valueOf (argüman[0].toUpperCase());
            gün = Integer.parseInt (argüman[1]);
            tarih = LocalDate.of (Year.now().getValue(), ay, gün);
        }catch (IllegalArgumentException ist) {
            System.err.printf ("[%s %s] geçerli bir ay-gün deðil!%n", argüman[0], argüman[1]);
            throw ist; // System.err.println (ist) þeklinde de olabilirdi...
        }catch (DateTimeException ist) {
            System.err.printf ("[%s %s] geçerli bir tarih deðil!%n", ay, gün);
            throw ist; // Örn. gün = 32...
        } // try-catch-catch bloðu sonu...

        // Lambda'sýz sorgulama...
        Boolean tatilgünüMü = tarih.query (new J7b_2x1());

        // Lambda'lý sorgulama...
        Boolean doðumgünüMü = tarih.query (J7b_2x2 :: doðumgünüMü);

        if (tatilgünüMü || doðumgünüMü.booleanValue())
            System.out.printf ("[%s] kayýtlý ve ÖNEMLÝ bir (doðum/tatil-günü) tarihtir!%n", tarih);
        else System.out.printf ("[%s] kayýtlý ve önemli bir (doðum/tatil-günü) tarih DEÐÝLDÝR!%n", tarih);
    } // main(..) metodu sonu...
} // J7b_2 sýnýfý sonu...

/* Çýktý:
** >java J7b_2  **
Komut satýrý: [J7b_2 <ay/aprýl/may> <gün>] þeklinde olmalý!
Exception in thread "main" java.lang.IllegalArgumentException: Yanlýþ Argüman Ýs
tisnasý
        at J7b_2.main(J7b_2.java:25)

**  >java J7b_2 may 35  ** TEKRAR-1
[MAY 35] geçerli bir tarih deðil!
Exception in thread "main" java.time.DateTimeException: Invalid value for DayOfM
onth (valid values 1 - 28/31): 35
        at java.time.temporal.ValueRange.checkValidValue(Unknown Source)
        at java.time.temporal.ChronoField.checkValidValue(Unknown Source)
        at java.time.LocalDate.of(Unknown Source)
        at J7b_2.main(J7b_2.java:30)

**  >java J7b_2 may 30  ** TEKRAR-2
[2018-05-30] kayýtlý ve önemli bir (doðum/tatil-günü) tarih DEÐÝLDÝR!

**  >java J7b_2 aprýl 3  ** TEKRAR-3
[2018-04-03] kayýtlý ve ÖNEMLÝ bir (doðum/tatil-günü) tarihtir!

**  >java J7b_2 august 10  ** TEKRAR-4
[2018-08-10] kayýtlý ve ÖNEMLÝ bir (doðum/tatil-günü) tarihtir!
*/