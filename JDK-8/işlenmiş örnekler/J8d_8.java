// J8d_8.java: SimpleDateFormatDemo (BasitTarihBiçimiGösterisi) örneði.

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class J8d_8 {
    static public void yerelTarihiGöster (Locale aktüelYerel) {
        Date bugün = new Date();
        String sonuç1, sonuç2;
        SimpleDateFormat biçimleyici1, biçimleyici2;

        biçimleyici1 = new SimpleDateFormat ("EEEE d MMMM yyyy", aktüelYerel);
        sonuç1 = biçimleyici1.format (bugün);
        biçimleyici2 = new SimpleDateFormat ("EEE d MMM yy", aktüelYerel);
        sonuç2 = biçimleyici2.format (bugün);

        System.out.println ("Yerel: " + aktüelYerel.toString());
        System.out.println ("Sonuç-1: " + sonuç1);
        System.out.println ("Sonuç-2: " + sonuç2);
    } // yerelTarihiGöster(..) metodu sonu...

   static public void zamanKalýbýnýGöster(String pattern, Locale aktüelYerel) {
      Date bugün;
      SimpleDateFormat biçimleyici;
      String output;

      biçimleyici = new SimpleDateFormat(pattern, aktüelYerel);
      bugün = new Date();
      output = biçimleyici.format(bugün);

      System.out.println(pattern + "   " + output);
   }

    static public void main (String[] args) {
        Locale[] yereller = {
                new Locale ("fr","FR"),
                new Locale ("de","DE"),
                new Locale ("en","US"),
                new Locale ("tr","TR")
        }; // yereller dizisi sonu...
        System.out.println ("FRN-ALM-ABD ve TRK yerele özel tarih biçimlemeleri:");
        System.out.println ("===================================================");
        for (int i = 0; i < yereller.length; i++) {
            yerelTarihiGöster (yereller[i]);
            System.out.println();
        } // for döngüsü sonu...

        String[] kalýplar = {
                "dd.MM.yy",
                "yyyy.MM.dd G 'at' hh:mm:ss a, z",
                "EEE, MMM d, ''yy",
                "EEEE, MMMM d, yyyy",
                "h:mm a",
                "H:mm",
                "H:mm:ss:SSS",
                "K:mm a,z",
                "yyyy.MMMMM.dd, EEEE GGG hh:mm a"
        }; // kalýplar dizisi sonu...
        System.out.println ("Çeþitli genel (en_US) tarih ve zaman biçimlemeleri:");
        System.out.println ("===================================================");
        for (int k = 0; k < kalýplar.length; k++) zamanKalýbýnýGöster (kalýplar[k], new Locale ("en","US"));
   } // main(..) metodu sonu...
} // J8d_8 sýnýfý sonu...

/* Çýktý:
**  >java J8d_8  **
FRN-ALM-ABD ve TRK yerele özel tarih biçimlemeleri:
===================================================
Yerel: fr_FR
Sonuç-1: vendredi 9 novembre 2018
Sonuç-2: ven. 9 nov. 18

Yerel: de_DE
Sonuç-1: Freitag 9 November 2018
Sonuç-2: Fr 9 Nov 18

Yerel: en_US
Sonuç-1: Friday 9 November 2018
Sonuç-2: Fri 9 Nov 18

Yerel: tr_TR
Sonuç-1: Cuma 9 Kasým 2018
Sonuç-2: Cum 9 Kas 18

Çeþitli genel (en_US) tarih ve zaman biçimlemeleri:
===================================================
dd.MM.yy   09.11.18
yyyy.MM.dd G 'at' hh:mm:ss a, z   2018.11.09 AD at 10:05:07 PM, EET
EEE, MMM d, ''yy   Fri, Nov 9, '18
EEEE, MMMM d, yyyy   Friday, November 9, 2018
h:mm a   10:05 PM
H:mm   22:05
H:mm:ss:SSS   22:05:07:754
K:mm a,z   10:05 PM,EET
yyyy.MMMMM.dd, EEEE GGG hh:mm a   2018.November.09, Friday AD 10:05 PM
*/