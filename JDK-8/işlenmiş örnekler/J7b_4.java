// J7b_4.java: NextPayg�n (GelecekMaa�g�n�) �rne�i.

import java.time.Month;
import java.time.Year;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

// Gerekli dosya: J7b_4x/Payg�nAdjuster.java
public class J7b_4 {
    public static void main (String[] arg�man) {
        Month ay = null;
        int g�n = 0;
        LocalDate tarih = null;
        DateTimeFormatter tarihBi�imleyici;
        String ��kt� = null;

        if (arg�man.length < 2) {
            System.err.printf ("2 arg�man 1.Ay/apr�l/may, 2.G�n/31 girilmeli!%n");
            throw new IllegalArgumentException();
        } // if karar� sonu...

        try {ay = Month.valueOf (arg�man[0].toUpperCase());
        }catch (IllegalArgumentException ist) {
            System.err.printf ("%s ge�erli ay de�il.%n", arg�man[0]);
            throw ist;
        } // try-catch blo�u sonu...

        try {g�n = Integer.parseInt (arg�man[1]);}catch (Exception ist ) {g�n = 10;}

        try {tarih = Year.now().atMonth (ay).atDay (g�n);
        }catch (DateTimeException ist) {
            System.err.printf ("%s %s ge�erli veri de�iller.%n", ay, g�n);
            throw ist;
        } // try-catch blo�u sonu...

        LocalDate gelecekMaa�g�n� = tarih.with (new J7b_4x()); // Varsay�l� kurucuyu �a��r�r...

        try {
            tarihBi�imleyici = DateTimeFormatter.ofPattern ("yyyy MMMM d");
            ��kt� = tarih.format (tarihBi�imleyici);
            System.out.printf ("Girilen tarih:  %s%n", ��kt�);
            ��kt� = gelecekMaa�g�n�.format (tarihBi�imleyici);
            System.out.printf ("Ger�ek maa� �deme g�n�: %s%n", ��kt�);
        }catch (DateTimeException ist) {
            System.err.printf ("%s tarihi bi�imlenemiyor!%n", ��kt�);
            throw ist;
        } // try-catch blo�u sonu...
    } // main(..) metodu sonu...
} // J7b_4 s�n�f� sonu...

/* ��kt�:
**  >java J7b_4  **
2 arg�man 1.Ay/apr�l/may, 2.G�n/31 girilmeli!
Exception in thread "main" java.lang.IllegalArgumentException
        at J7b_4.main(J7b_4.java:20)

**  >java J7b_4 february 17  ** TEKRAR
Girilen tarih:  2018 �ubat 17
Ger�ek maa� �deme g�n�: 2018 �ubat 28

**  >java J7b_4 apr�l 1  ** TEKRAR
Girilen tarih:  2018 Nisan 1
Ger�ek maa� �deme g�n�: 2018 Nisan 13
*/