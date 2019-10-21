// J8d_7.java: NumberFormatDemo (SayýBiçimiGösterisi) örneði.

import java.util.Locale;
import java.util.Currency;
import java.util.ArrayList;

import java.text.NumberFormat;

public class J8d_7 {
    static public void sayýyýGöster (Locale aktüelYerel) {
        Integer tamSayý = new Integer (1234567);
        Double küsürlüSayý = new Double (1234567.089);
        NumberFormat sayýBiçimi = NumberFormat.getNumberInstance (aktüelYerel);
        String tamSayýOut;
        String küsürlüSayýOut;

        System.out.println (aktüelYerel.toString() + ": " + sayýBiçimi.format (tamSayý));
        System.out.println (aktüelYerel + ": " + sayýBiçimi.format (küsürlüSayý));
  } // sayýyýGöster(..) metodu sonu...

    static public void paraBiriminiGöster (Locale aktüelYerel) {
        Double paraTutarý1 = new Double (1234567);
        Double paraTutarý2 = new Double (1234567.89);
        Currency aktüelParaBirimi = Currency.getInstance (aktüelYerel);
        NumberFormat paraBiçimi = NumberFormat.getCurrencyInstance (aktüelYerel);

        System.out.println (aktüelYerel.getDisplayName() + ", " + aktüelParaBirimi.getDisplayName() + ": " + paraBiçimi.format (paraTutarý1));
        System.out.println (aktüelYerel.getDisplayName() + ", " + aktüelParaBirimi.getDisplayName() + ": " + paraBiçimi.format (paraTutarý2));
    } // paraBiriminiGöster(..) metodu sonu...

    static public void yüzdeyiGöster (Locale aktüelYerel) {
        Double yüzde1 = new Double (0.75);
        Double yüzde2 = new Double (0.7568);
        NumberFormat yüzdeBiçimi = NumberFormat.getPercentInstance (aktüelYerel);

        System.out.println (aktüelYerel + ": " + yüzdeBiçimi.format (yüzde1));
        System.out.println (aktüelYerel + ": " + yüzdeBiçimi.format (yüzde2));
    } // yüzdeyiGöster(..) metodu sonu...

    static public void main (String[] args) {
        ArrayList<Locale> yerellerListesi = new ArrayList<>();
        yerellerListesi.add (0, new Locale.Builder().setLanguage ("fr").setRegion ("FR").build());
        yerellerListesi.add (1, new Locale.Builder().setLanguage ("de").setRegion ("DE").build());
        yerellerListesi.add (2, new Locale.Builder().setLanguage ("en").setRegion ("US").build());
        yerellerListesi.add (3, new Locale.Builder().setLanguage ("tr").setRegion ("TR").build());

        System.out.println ("Yereller listesi: " + yerellerListesi);

        System.out.println ("\nFRN, ALM, ABD ve TRK yerele özgü SAYISAL biçimlemeleri:");
        System.out.println ("=======================================================");
        for (int i = 0; i < yerellerListesi.size(); i++) sayýyýGöster (yerellerListesi.get(i));

        System.out.println ("\nFRN, ALM, ABD ve TRK yerele özgü PARASAL biçimlemeleri:");
        System.out.println ("=======================================================");
        for (int i = 0; i < yerellerListesi.size(); i++) paraBiriminiGöster (yerellerListesi.get(i));

        System.out.println ("\nFRN, ALM, ABD ve TRK yerele özgü YÜZDESEL biçimlemeleri:");
        System.out.println ("========================================================");
        for (int i = 0; i < yerellerListesi.size(); i++) yüzdeyiGöster (yerellerListesi.get(i));
    } // main(..) metodu sonu...
} // J8d_7 sýnýfý sonu...

/* Çýktý:
**  >java J8d_7  **
Yereller listesi: [fr_FR, de_DE, en_US, tr_TR]

FRN, ALM, ABD ve TRK yerele özgü SAYISAL biçimlemeleri:
=======================================================
fr_FR: 1 234 567
fr_FR: 1 234 567,089
de_DE: 1.234.567
de_DE: 1.234.567,089
en_US: 1,234,567
en_US: 1,234,567.089
tr_TR: 1.234.567
tr_TR: 1.234.567,089

FRN, ALM, ABD ve TRK yerele özgü PARASAL biçimlemeleri:
=======================================================
French (France), Euro: 1 234 567,00 ?
French (France), Euro: 1 234 567,89 ?
German (Germany), Euro: 1.234.567,00 ?
German (Germany), Euro: 1.234.567,89 ?
English (United States), US Dollar: $1,234,567.00
English (United States), US Dollar: $1,234,567.89
Türkçe (Türkiye), Turkish Lira: 1.234.567,00 TL
Türkçe (Türkiye), Turkish Lira: 1.234.567,89 TL

FRN, ALM, ABD ve TRK yerele özgü YÜZDESEL biçimlemeleri:
========================================================
fr_FR: 75 %
fr_FR: 76 %
de_DE: 75%
de_DE: 76%
en_US: 75%
en_US: 76%
tr_TR: % 75
tr_TR: % 76
*/