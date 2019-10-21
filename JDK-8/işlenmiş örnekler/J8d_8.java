// J8d_8.java: SimpleDateFormatDemo (BasitTarihBi�imiG�sterisi) �rne�i.

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class J8d_8 {
    static public void yerelTarihiG�ster (Locale akt�elYerel) {
        Date bug�n = new Date();
        String sonu�1, sonu�2;
        SimpleDateFormat bi�imleyici1, bi�imleyici2;

        bi�imleyici1 = new SimpleDateFormat ("EEEE d MMMM yyyy", akt�elYerel);
        sonu�1 = bi�imleyici1.format (bug�n);
        bi�imleyici2 = new SimpleDateFormat ("EEE d MMM yy", akt�elYerel);
        sonu�2 = bi�imleyici2.format (bug�n);

        System.out.println ("Yerel: " + akt�elYerel.toString());
        System.out.println ("Sonu�-1: " + sonu�1);
        System.out.println ("Sonu�-2: " + sonu�2);
    } // yerelTarihiG�ster(..) metodu sonu...

   static public void zamanKal�b�n�G�ster(String pattern, Locale akt�elYerel) {
      Date bug�n;
      SimpleDateFormat bi�imleyici;
      String output;

      bi�imleyici = new SimpleDateFormat(pattern, akt�elYerel);
      bug�n = new Date();
      output = bi�imleyici.format(bug�n);

      System.out.println(pattern + "   " + output);
   }

    static public void main (String[] args) {
        Locale[] yereller = {
                new Locale ("fr","FR"),
                new Locale ("de","DE"),
                new Locale ("en","US"),
                new Locale ("tr","TR")
        }; // yereller dizisi sonu...
        System.out.println ("FRN-ALM-ABD ve TRK yerele �zel tarih bi�imlemeleri:");
        System.out.println ("===================================================");
        for (int i = 0; i < yereller.length; i++) {
            yerelTarihiG�ster (yereller[i]);
            System.out.println();
        } // for d�ng�s� sonu...

        String[] kal�plar = {
                "dd.MM.yy",
                "yyyy.MM.dd G 'at' hh:mm:ss a, z",
                "EEE, MMM d, ''yy",
                "EEEE, MMMM d, yyyy",
                "h:mm a",
                "H:mm",
                "H:mm:ss:SSS",
                "K:mm a,z",
                "yyyy.MMMMM.dd, EEEE GGG hh:mm a"
        }; // kal�plar dizisi sonu...
        System.out.println ("�e�itli genel (en_US) tarih ve zaman bi�imlemeleri:");
        System.out.println ("===================================================");
        for (int k = 0; k < kal�plar.length; k++) zamanKal�b�n�G�ster (kal�plar[k], new Locale ("en","US"));
   } // main(..) metodu sonu...
} // J8d_8 s�n�f� sonu...

/* ��kt�:
**  >java J8d_8  **
FRN-ALM-ABD ve TRK yerele �zel tarih bi�imlemeleri:
===================================================
Yerel: fr_FR
Sonu�-1: vendredi 9 novembre 2018
Sonu�-2: ven. 9 nov. 18

Yerel: de_DE
Sonu�-1: Freitag 9 November 2018
Sonu�-2: Fr 9 Nov 18

Yerel: en_US
Sonu�-1: Friday 9 November 2018
Sonu�-2: Fri 9 Nov 18

Yerel: tr_TR
Sonu�-1: Cuma 9 Kas�m 2018
Sonu�-2: Cum 9 Kas 18

�e�itli genel (en_US) tarih ve zaman bi�imlemeleri:
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