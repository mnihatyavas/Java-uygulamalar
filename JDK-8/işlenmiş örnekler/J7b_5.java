// J7b_5.java: Parse (�evrim) �rne�i.

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

// "M d yyyy" format�yla girilen ay g�n y�l rakamlar�n� g�n�n tarihi olarak kabul eder...
public class J7b_5 {
    public static void main (String[] arg�manlar) {
        if (arg�manlar.length < 3) {
            System.err.println("3 arg�man (ay g�n y�l) rakamlar� gereklidir!");
            throw new IllegalArgumentException(); // java.lang.*
        } // if karar� sonu...

        String girdi = arg�manlar[0] + ' ' + arg�manlar[1] + ' ' + arg�manlar[2];
        try {DateTimeFormatter bi�imleyici = DateTimeFormatter.ofPattern ("M d yyyy");
            LocalDate tarih = LocalDate.parse (girdi, bi�imleyici);
            System.out.printf ("Girilen tarih: %s%n", tarih);
        }catch (DateTimeParseException ist) {
            System.out.printf ("[%s] tarih'e �evrilemiyor!%n", girdi);
            throw ist;
        } // try-catch blo�u sonu...
    } // main(..) metodu sonu...
} // J7b_5 s�n�f� sonu...

/* ��kt�:
**  >java J7b_5  **
3 arg�man (ay g�n y�l) rakamlar� gereklidir!
Exception in thread "main" java.lang.IllegalArgumentException
        at J7b_5.main(J7b_5.java:12)

**  >java J7b_5 OCT 30 2018  ** TEKRAR
[OCT 30 2018] tarih'e �evrilemiyor!
Exception in thread "main" java.time.format.DateTimeParseException: Text 'OCT 30
 2018' could not be parsed at index 0
        at java.time.format.DateTimeFormatter.parseResolved0(Unknown Source)
        at java.time.format.DateTimeFormatter.parse(Unknown Source)
        at java.time.LocalDate.parse(Unknown Source)
        at J7b_5.main(J7b_5.java:17)

**  >java J7b_5 10 30 2018  ** TEKRAR
Girilen tarih: 2018-10-30
*/