// J8d_2.java: DateFormatDemo (TarihBi�imlemeG�sterisi) �rne�i.

import java.util.Locale;
import java.util.Date;
import java.text.DateFormat;

public class J8d_2 {
    static int[] stiller = {
        DateFormat.DEFAULT,
        DateFormat.SHORT, 
        DateFormat.MEDIUM,
        DateFormat.LONG,
        DateFormat.FULL
    }; // stiller dizisi sonu...

   static public void tarihiG�ster (Locale akt�elYerel) {
        Date �imdi = new Date();
        DateFormat tarihBi�imleyici = DateFormat.getDateInstance (DateFormat.DEFAULT, akt�elYerel);
        String tarih��kt�s� = tarihBi�imleyici.format (�imdi);
        System.out.println (tarih��kt�s� + " ==>" + akt�elYerel.toString());
    } // tarihiG�ster(..) metodu sonu...

   static public void tarihStilleriniG�ster (Locale akt�elYerel) {
        Date �imdi = new Date();
        DateFormat bi�imleyici;
        String sonu�;

        System.out.println();
        System.out.println ("Yerel: " + akt�elYerel.toString());
        System.out.println ("-------------------------");
        for (int k = 0; k < stiller.length; k++) {
            bi�imleyici = DateFormat.getDateInstance (stiller[k], akt�elYerel);
            sonu� = bi�imleyici.format (�imdi);
            System.out.println (sonu�);
        } // for d�ng�s� sonu...
    } // tarihStilleriniG�ster(..) metodu sonu...

    static public void zamanStilleriniG�ster (Locale akt�elYerel) {
        Date �imdi = new Date();
        DateFormat bi�imleyici;
        String sonu�;

        System.out.println();
        System.out.println ("Yerel: " + akt�elYerel.toString());
        System.out.println ("--------------");
        for (int k = 0; k < stiller.length; k++) {
            bi�imleyici = DateFormat.getTimeInstance (stiller[k], akt�elYerel);
            sonu� = bi�imleyici.format (�imdi);
            System.out.println (sonu�);
        } // for d�ng�s� sonu...
    } // zamanStilleriniG�ster(..) metodu sonu...

    static public void tarihVeZamanStilleriniG�ster (Locale akt�elYerel) {
        Date �imdi = new Date();
        DateFormat bi�imleyici;
        String sonu�;

        System.out.println();
        System.out.println ("Yerel: " + akt�elYerel.toString());
        System.out.println ("----------------------------------------");
        for (int k = 0; k < stiller.length; k++) {
            bi�imleyici = DateFormat.getDateTimeInstance (stiller[k], stiller[k], akt�elYerel);
            sonu� = bi�imleyici.format (�imdi);
            System.out.println (sonu�);
        } // for d�ng�s� sonu...
    } // tarihVeZamanStilleriniG�ster(..) metodu sonu...

    static public void main (String[] args) {
        Locale[] yereller = {
            new Locale ("fr", "FR"),
            new Locale ("de", "DE"),
            new Locale ("en", "US"),
            new Locale ("tr", "TR")
        }; // yereller dizisi sonu...
        System.out.println ("Standart tarih:\n" + "--------------------");
        for (int i = 0; i < yereller.length; i++) tarihiG�ster (yereller[i]);

        System.out.println ("\nTarih stilleri:\n" + "===============");
        tarihStilleriniG�ster (new Locale ("fr", "FR"));
        tarihStilleriniG�ster (new Locale ("de", "DE"));
        tarihStilleriniG�ster (new Locale ("en", "US"));
        tarihStilleriniG�ster (new Locale ("tr", "TR"));

        System.out.println ("\nZaman stilleri:\n" + "===============");
        zamanStilleriniG�ster (new Locale ("fr", "FR"));
        zamanStilleriniG�ster (new Locale ("de", "DE"));
        zamanStilleriniG�ster (new Locale ("en", "US"));
        zamanStilleriniG�ster (new Locale ("tr", "TR"));

        System.out.println ("\nTarih ve zaman stilleri:\n" + "========================"); 
        tarihVeZamanStilleriniG�ster (new Locale ("fr", "FR"));
        tarihVeZamanStilleriniG�ster (new Locale ("de", "DE"));
        tarihVeZamanStilleriniG�ster (new Locale ("en", "US"));
        tarihVeZamanStilleriniG�ster (new Locale ("tr", "TR"));
    } // main(..) metodu sonu...
} // J8d_2 s�n�f� sonu...

/* ��kt�:
**  >java J8d_2  **
Standart tarih:
--------------------
13 nov. 2018 ==>fr_FR
13.11.2018 ==>de_DE
Nov 13, 2018 ==>en_US
13.Kas.2018 ==>tr_TR

Tarih stilleri:
===============

Yerel: fr_FR
-------------------------
13 nov. 2018
13/11/18
13 nov. 2018
13 novembre 2018
mardi 13 novembre 2018

Yerel: de_DE
-------------------------
13.11.2018
13.11.18
13.11.2018
13. November 2018
Dienstag, 13. November 2018

Yerel: en_US
-------------------------
Nov 13, 2018
11/13/18
Nov 13, 2018
November 13, 2018
Tuesday, November 13, 2018

Yerel: tr_TR
-------------------------
13.Kas.2018
13.11.2018
13.Kas.2018
13 Kas�m 2018 Sal�
13 Kas�m 2018 Sal�

Zaman stilleri:
===============

Yerel: fr_FR
--------------
17:48:44
17:48
17:48:44
17:48:44 EET
17 h 48 EET

Yerel: de_DE
--------------
17:48:44
17:48
17:48:44
17:48:44 OEZ
17:48 Uhr OEZ

Yerel: en_US
--------------
5:48:44 PM
5:48 PM
5:48:44 PM
5:48:44 PM EET
5:48:44 PM EET

Yerel: tr_TR
--------------
17:48:44
17:48
17:48:44
17:48:44 EET
17:48:44 EET

Tarih ve zaman stilleri:
========================

Yerel: fr_FR
----------------------------------------
13 nov. 2018 17:48:44
13/11/18 17:48
13 nov. 2018 17:48:44
13 novembre 2018 17:48:44 EET
mardi 13 novembre 2018 17 h 48 EET

Yerel: de_DE
----------------------------------------
13.11.2018 17:48:44
13.11.18 17:48
13.11.2018 17:48:44
13. November 2018 17:48:44 OEZ
Dienstag, 13. November 2018 17:48 Uhr OEZ

Yerel: en_US
----------------------------------------
Nov 13, 2018 5:48:44 PM
11/13/18 5:48 PM
Nov 13, 2018 5:48:44 PM
November 13, 2018 5:48:44 PM EET
Tuesday, November 13, 2018 5:48:44 PM EET

Yerel: tr_TR
----------------------------------------
13.Kas.2018 17:48:44
13.11.2018 17:48
13.Kas.2018 17:48:44
13 Kas�m 2018 Sal� 17:48:44 EET
13 Kas�m 2018 Sal� 17:48:44 EET
*/