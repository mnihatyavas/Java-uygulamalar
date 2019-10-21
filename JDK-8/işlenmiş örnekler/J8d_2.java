// J8d_2.java: DateFormatDemo (TarihBiçimlemeGösterisi) örneði.

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

   static public void tarihiGöster (Locale aktüelYerel) {
        Date þimdi = new Date();
        DateFormat tarihBiçimleyici = DateFormat.getDateInstance (DateFormat.DEFAULT, aktüelYerel);
        String tarihÇýktýsý = tarihBiçimleyici.format (þimdi);
        System.out.println (tarihÇýktýsý + " ==>" + aktüelYerel.toString());
    } // tarihiGöster(..) metodu sonu...

   static public void tarihStilleriniGöster (Locale aktüelYerel) {
        Date þimdi = new Date();
        DateFormat biçimleyici;
        String sonuç;

        System.out.println();
        System.out.println ("Yerel: " + aktüelYerel.toString());
        System.out.println ("-------------------------");
        for (int k = 0; k < stiller.length; k++) {
            biçimleyici = DateFormat.getDateInstance (stiller[k], aktüelYerel);
            sonuç = biçimleyici.format (þimdi);
            System.out.println (sonuç);
        } // for döngüsü sonu...
    } // tarihStilleriniGöster(..) metodu sonu...

    static public void zamanStilleriniGöster (Locale aktüelYerel) {
        Date þimdi = new Date();
        DateFormat biçimleyici;
        String sonuç;

        System.out.println();
        System.out.println ("Yerel: " + aktüelYerel.toString());
        System.out.println ("--------------");
        for (int k = 0; k < stiller.length; k++) {
            biçimleyici = DateFormat.getTimeInstance (stiller[k], aktüelYerel);
            sonuç = biçimleyici.format (þimdi);
            System.out.println (sonuç);
        } // for döngüsü sonu...
    } // zamanStilleriniGöster(..) metodu sonu...

    static public void tarihVeZamanStilleriniGöster (Locale aktüelYerel) {
        Date þimdi = new Date();
        DateFormat biçimleyici;
        String sonuç;

        System.out.println();
        System.out.println ("Yerel: " + aktüelYerel.toString());
        System.out.println ("----------------------------------------");
        for (int k = 0; k < stiller.length; k++) {
            biçimleyici = DateFormat.getDateTimeInstance (stiller[k], stiller[k], aktüelYerel);
            sonuç = biçimleyici.format (þimdi);
            System.out.println (sonuç);
        } // for döngüsü sonu...
    } // tarihVeZamanStilleriniGöster(..) metodu sonu...

    static public void main (String[] args) {
        Locale[] yereller = {
            new Locale ("fr", "FR"),
            new Locale ("de", "DE"),
            new Locale ("en", "US"),
            new Locale ("tr", "TR")
        }; // yereller dizisi sonu...
        System.out.println ("Standart tarih:\n" + "--------------------");
        for (int i = 0; i < yereller.length; i++) tarihiGöster (yereller[i]);

        System.out.println ("\nTarih stilleri:\n" + "===============");
        tarihStilleriniGöster (new Locale ("fr", "FR"));
        tarihStilleriniGöster (new Locale ("de", "DE"));
        tarihStilleriniGöster (new Locale ("en", "US"));
        tarihStilleriniGöster (new Locale ("tr", "TR"));

        System.out.println ("\nZaman stilleri:\n" + "===============");
        zamanStilleriniGöster (new Locale ("fr", "FR"));
        zamanStilleriniGöster (new Locale ("de", "DE"));
        zamanStilleriniGöster (new Locale ("en", "US"));
        zamanStilleriniGöster (new Locale ("tr", "TR"));

        System.out.println ("\nTarih ve zaman stilleri:\n" + "========================"); 
        tarihVeZamanStilleriniGöster (new Locale ("fr", "FR"));
        tarihVeZamanStilleriniGöster (new Locale ("de", "DE"));
        tarihVeZamanStilleriniGöster (new Locale ("en", "US"));
        tarihVeZamanStilleriniGöster (new Locale ("tr", "TR"));
    } // main(..) metodu sonu...
} // J8d_2 sýnýfý sonu...

/* Çýktý:
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
13 Kasým 2018 Salý
13 Kasým 2018 Salý

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
13 Kasým 2018 Salý 17:48:44 EET
13 Kasým 2018 Salý 17:48:44 EET
*/