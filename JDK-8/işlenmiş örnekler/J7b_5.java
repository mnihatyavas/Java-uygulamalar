// J7b_5.java: Parse (Çevrim) örneði.

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

// "M d yyyy" formatýyla girilen ay gün yýl rakamlarýný günün tarihi olarak kabul eder...
public class J7b_5 {
    public static void main (String[] argümanlar) {
        if (argümanlar.length < 3) {
            System.err.println("3 argüman (ay gün yýl) rakamlarý gereklidir!");
            throw new IllegalArgumentException(); // java.lang.*
        } // if kararý sonu...

        String girdi = argümanlar[0] + ' ' + argümanlar[1] + ' ' + argümanlar[2];
        try {DateTimeFormatter biçimleyici = DateTimeFormatter.ofPattern ("M d yyyy");
            LocalDate tarih = LocalDate.parse (girdi, biçimleyici);
            System.out.printf ("Girilen tarih: %s%n", tarih);
        }catch (DateTimeParseException ist) {
            System.out.printf ("[%s] tarih'e çevrilemiyor!%n", girdi);
            throw ist;
        } // try-catch bloðu sonu...
    } // main(..) metodu sonu...
} // J7b_5 sýnýfý sonu...

/* Çýktý:
**  >java J7b_5  **
3 argüman (ay gün yýl) rakamlarý gereklidir!
Exception in thread "main" java.lang.IllegalArgumentException
        at J7b_5.main(J7b_5.java:12)

**  >java J7b_5 OCT 30 2018  ** TEKRAR
[OCT 30 2018] tarih'e çevrilemiyor!
Exception in thread "main" java.time.format.DateTimeParseException: Text 'OCT 30
 2018' could not be parsed at index 0
        at java.time.format.DateTimeFormatter.parseResolved0(Unknown Source)
        at java.time.format.DateTimeFormatter.parse(Unknown Source)
        at java.time.LocalDate.parse(Unknown Source)
        at J7b_5.main(J7b_5.java:17)

**  >java J7b_5 10 30 2018  ** TEKRAR
Girilen tarih: 2018-10-30
*/