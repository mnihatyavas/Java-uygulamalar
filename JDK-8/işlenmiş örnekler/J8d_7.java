// J8d_7.java: NumberFormatDemo (Say�Bi�imiG�sterisi) �rne�i.

import java.util.Locale;
import java.util.Currency;
import java.util.ArrayList;

import java.text.NumberFormat;

public class J8d_7 {
    static public void say�y�G�ster (Locale akt�elYerel) {
        Integer tamSay� = new Integer (1234567);
        Double k�s�rl�Say� = new Double (1234567.089);
        NumberFormat say�Bi�imi = NumberFormat.getNumberInstance (akt�elYerel);
        String tamSay�Out;
        String k�s�rl�Say�Out;

        System.out.println (akt�elYerel.toString() + ": " + say�Bi�imi.format (tamSay�));
        System.out.println (akt�elYerel + ": " + say�Bi�imi.format (k�s�rl�Say�));
  } // say�y�G�ster(..) metodu sonu...

    static public void paraBiriminiG�ster (Locale akt�elYerel) {
        Double paraTutar�1 = new Double (1234567);
        Double paraTutar�2 = new Double (1234567.89);
        Currency akt�elParaBirimi = Currency.getInstance (akt�elYerel);
        NumberFormat paraBi�imi = NumberFormat.getCurrencyInstance (akt�elYerel);

        System.out.println (akt�elYerel.getDisplayName() + ", " + akt�elParaBirimi.getDisplayName() + ": " + paraBi�imi.format (paraTutar�1));
        System.out.println (akt�elYerel.getDisplayName() + ", " + akt�elParaBirimi.getDisplayName() + ": " + paraBi�imi.format (paraTutar�2));
    } // paraBiriminiG�ster(..) metodu sonu...

    static public void y�zdeyiG�ster (Locale akt�elYerel) {
        Double y�zde1 = new Double (0.75);
        Double y�zde2 = new Double (0.7568);
        NumberFormat y�zdeBi�imi = NumberFormat.getPercentInstance (akt�elYerel);

        System.out.println (akt�elYerel + ": " + y�zdeBi�imi.format (y�zde1));
        System.out.println (akt�elYerel + ": " + y�zdeBi�imi.format (y�zde2));
    } // y�zdeyiG�ster(..) metodu sonu...

    static public void main (String[] args) {
        ArrayList<Locale> yerellerListesi = new ArrayList<>();
        yerellerListesi.add (0, new Locale.Builder().setLanguage ("fr").setRegion ("FR").build());
        yerellerListesi.add (1, new Locale.Builder().setLanguage ("de").setRegion ("DE").build());
        yerellerListesi.add (2, new Locale.Builder().setLanguage ("en").setRegion ("US").build());
        yerellerListesi.add (3, new Locale.Builder().setLanguage ("tr").setRegion ("TR").build());

        System.out.println ("Yereller listesi: " + yerellerListesi);

        System.out.println ("\nFRN, ALM, ABD ve TRK yerele �zg� SAYISAL bi�imlemeleri:");
        System.out.println ("=======================================================");
        for (int i = 0; i < yerellerListesi.size(); i++) say�y�G�ster (yerellerListesi.get(i));

        System.out.println ("\nFRN, ALM, ABD ve TRK yerele �zg� PARASAL bi�imlemeleri:");
        System.out.println ("=======================================================");
        for (int i = 0; i < yerellerListesi.size(); i++) paraBiriminiG�ster (yerellerListesi.get(i));

        System.out.println ("\nFRN, ALM, ABD ve TRK yerele �zg� Y�ZDESEL bi�imlemeleri:");
        System.out.println ("========================================================");
        for (int i = 0; i < yerellerListesi.size(); i++) y�zdeyiG�ster (yerellerListesi.get(i));
    } // main(..) metodu sonu...
} // J8d_7 s�n�f� sonu...

/* ��kt�:
**  >java J8d_7  **
Yereller listesi: [fr_FR, de_DE, en_US, tr_TR]

FRN, ALM, ABD ve TRK yerele �zg� SAYISAL bi�imlemeleri:
=======================================================
fr_FR: 1�234�567
fr_FR: 1�234�567,089
de_DE: 1.234.567
de_DE: 1.234.567,089
en_US: 1,234,567
en_US: 1,234,567.089
tr_TR: 1.234.567
tr_TR: 1.234.567,089

FRN, ALM, ABD ve TRK yerele �zg� PARASAL bi�imlemeleri:
=======================================================
French (France), Euro: 1�234�567,00 ?
French (France), Euro: 1�234�567,89 ?
German (Germany), Euro: 1.234.567,00 ?
German (Germany), Euro: 1.234.567,89 ?
English (United States), US Dollar: $1,234,567.00
English (United States), US Dollar: $1,234,567.89
T�rk�e (T�rkiye), Turkish Lira: 1.234.567,00 TL
T�rk�e (T�rkiye), Turkish Lira: 1.234.567,89 TL

FRN, ALM, ABD ve TRK yerele �zg� Y�ZDESEL bi�imlemeleri:
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