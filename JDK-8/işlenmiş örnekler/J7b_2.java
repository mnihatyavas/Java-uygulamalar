// J7b_2.java: CheckDate (TarihKontrolu) �rne�i.

import java.time.Month;
import java.time.Year;
import java.time.LocalDate;
import java.time.DateTimeException;

/* Gereken dosyalar:
 *      J7b_2x1/FamilyVacations.java
 *      J7b_2x2/FamilyBirthg�ns.java
 */
public class J7b_2 {
    public static void main (String[] arg�man) {
        Month ay = null;
        LocalDate tarih = null;
        int g�n = 0;

        if (arg�man.length < 2) {
            System.err.printf ("Komut sat�r�: [J7b_2 <ay/apr�l/may> <g�n>] �eklinde olmal�!%n");
            throw new IllegalArgumentException ("Yanl�� Arg�man �stisnas�");
        } // if karar� sonu...

        try {ay = Month.valueOf (arg�man[0].toUpperCase());
            g�n = Integer.parseInt (arg�man[1]);
            tarih = LocalDate.of (Year.now().getValue(), ay, g�n);
        }catch (IllegalArgumentException ist) {
            System.err.printf ("[%s %s] ge�erli bir ay-g�n de�il!%n", arg�man[0], arg�man[1]);
            throw ist; // System.err.println (ist) �eklinde de olabilirdi...
        }catch (DateTimeException ist) {
            System.err.printf ("[%s %s] ge�erli bir tarih de�il!%n", ay, g�n);
            throw ist; // �rn. g�n = 32...
        } // try-catch-catch blo�u sonu...

        // Lambda's�z sorgulama...
        Boolean tatilg�n�M� = tarih.query (new J7b_2x1());

        // Lambda'l� sorgulama...
        Boolean do�umg�n�M� = tarih.query (J7b_2x2 :: do�umg�n�M�);

        if (tatilg�n�M� || do�umg�n�M�.booleanValue())
            System.out.printf ("[%s] kay�tl� ve �NEML� bir (do�um/tatil-g�n�) tarihtir!%n", tarih);
        else System.out.printf ("[%s] kay�tl� ve �nemli bir (do�um/tatil-g�n�) tarih DE��LD�R!%n", tarih);
    } // main(..) metodu sonu...
} // J7b_2 s�n�f� sonu...

/* ��kt�:
** >java J7b_2  **
Komut sat�r�: [J7b_2 <ay/apr�l/may> <g�n>] �eklinde olmal�!
Exception in thread "main" java.lang.IllegalArgumentException: Yanl�� Arg�man �s
tisnas�
        at J7b_2.main(J7b_2.java:25)

**  >java J7b_2 may 35  ** TEKRAR-1
[MAY 35] ge�erli bir tarih de�il!
Exception in thread "main" java.time.DateTimeException: Invalid value for DayOfM
onth (valid values 1 - 28/31): 35
        at java.time.temporal.ValueRange.checkValidValue(Unknown Source)
        at java.time.temporal.ChronoField.checkValidValue(Unknown Source)
        at java.time.LocalDate.of(Unknown Source)
        at J7b_2.main(J7b_2.java:30)

**  >java J7b_2 may 30  ** TEKRAR-2
[2018-05-30] kay�tl� ve �nemli bir (do�um/tatil-g�n�) tarih DE��LD�R!

**  >java J7b_2 apr�l 3  ** TEKRAR-3
[2018-04-03] kay�tl� ve �NEML� bir (do�um/tatil-g�n�) tarihtir!

**  >java J7b_2 august 10  ** TEKRAR-4
[2018-08-10] kay�tl� ve �NEML� bir (do�um/tatil-g�n�) tarihtir!
*/