// J7b_4.java: NextPaygün (GelecekMaaþgünü) örneði.

import java.time.Month;
import java.time.Year;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

// Gerekli dosya: J7b_4x/PaygünAdjuster.java
public class J7b_4 {
    public static void main (String[] argüman) {
        Month ay = null;
        int gün = 0;
        LocalDate tarih = null;
        DateTimeFormatter tarihBiçimleyici;
        String çýktý = null;

        if (argüman.length < 2) {
            System.err.printf ("2 argüman 1.Ay/aprýl/may, 2.Gün/31 girilmeli!%n");
            throw new IllegalArgumentException();
        } // if kararý sonu...

        try {ay = Month.valueOf (argüman[0].toUpperCase());
        }catch (IllegalArgumentException ist) {
            System.err.printf ("%s geçerli ay deðil.%n", argüman[0]);
            throw ist;
        } // try-catch bloðu sonu...

        try {gün = Integer.parseInt (argüman[1]);}catch (Exception ist ) {gün = 10;}

        try {tarih = Year.now().atMonth (ay).atDay (gün);
        }catch (DateTimeException ist) {
            System.err.printf ("%s %s geçerli veri deðiller.%n", ay, gün);
            throw ist;
        } // try-catch bloðu sonu...

        LocalDate gelecekMaaþgünü = tarih.with (new J7b_4x()); // Varsayýlý kurucuyu çaðýrýr...

        try {
            tarihBiçimleyici = DateTimeFormatter.ofPattern ("yyyy MMMM d");
            çýktý = tarih.format (tarihBiçimleyici);
            System.out.printf ("Girilen tarih:  %s%n", çýktý);
            çýktý = gelecekMaaþgünü.format (tarihBiçimleyici);
            System.out.printf ("Gerçek maaþ ödeme günü: %s%n", çýktý);
        }catch (DateTimeException ist) {
            System.err.printf ("%s tarihi biçimlenemiyor!%n", çýktý);
            throw ist;
        } // try-catch bloðu sonu...
    } // main(..) metodu sonu...
} // J7b_4 sýnýfý sonu...

/* Çýktý:
**  >java J7b_4  **
2 argüman 1.Ay/aprýl/may, 2.Gün/31 girilmeli!
Exception in thread "main" java.lang.IllegalArgumentException
        at J7b_4.main(J7b_4.java:20)

**  >java J7b_4 february 17  ** TEKRAR
Girilen tarih:  2018 Þubat 17
Gerçek maaþ ödeme günü: 2018 Þubat 28

**  >java J7b_4 aprýl 1  ** TEKRAR
Girilen tarih:  2018 Nisan 1
Gerçek maaþ ödeme günü: 2018 Nisan 13
*/