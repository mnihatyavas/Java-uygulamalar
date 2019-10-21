// J8d_4.java: DecimalFormatDemo (OndalýkBiçimGösterisi) örneði.

import java.util.Locale;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class J8d_4 {
    static public void özelBiçim (String kalýp, double deðer ) {
        System.out.println ("Deðer: " + deðer + ", Kalýp: " + kalýp + ", Kalýplý deðer: " + new DecimalFormat (kalýp).format (deðer));
    } // özelBiçim(..) metodu sonu...

    static public void yerelleþenBiçim (String kalýp, double deðer, Locale yerel ) {
        NumberFormat sayýBiçimi = NumberFormat.getNumberInstance (yerel);
        DecimalFormat ondalýkBiçim = (DecimalFormat)sayýBiçimi;
        ondalýkBiçim.applyPattern (kalýp);
        String çýktýDizgesi = ondalýkBiçim.format (deðer);
        System.out.println (kalýp + "  " + çýktýDizgesi + "  " + yerel.toString());
    } // yerelleþenBiçim(..) metodu sonu...

    static public void main (String[] args) {
        özelBiçim ("###,###.###", 123456.789);
        özelBiçim ("###.##", 123456.789);
        özelBiçim ("000000.000", 123.78);
        özelBiçim ("$###,###.###", 12345.67);
        özelBiçim ("\u00a5###,###.###", 12345.67); // hex:165=ascii:190, Japon Yeni...

        Locale aktüelYerel = new Locale ("en", "US");

        DecimalFormatSymbols nadirSemboller = new DecimalFormatSymbols (aktüelYerel);
        nadirSemboller.setDecimalSeparator ('|'); // Ondalýk ayraç sembolü olsun...
        nadirSemboller.setGroupingSeparator ('^'); // Binler ayracý sembolü olsun...
        String garipDizge = "#,##0.###";
        DecimalFormat acaipBiçim = new DecimalFormat (garipDizge, nadirSemboller);
        acaipBiçim.setGroupingSize (4); // Binler gruplamasý 4'lü olsun...
        String saçmalýk = acaipBiçim.format (12345.678);
        System.out.println ("\n4 gruplu binler ^ ve | ondalýk ayraçlý tuhaf biçimli sayý: " + saçmalýk);

        Locale[] yereller = {
            new Locale ("en", "US"),
            new Locale ("de", "DE"),
            new Locale ("fr", "FR"),
            new Locale ("tr", "TR")
        }; // yereller dizisi sonu...

        System.out.println();
        for (int i = 0; i < yereller.length; i++) yerelleþenBiçim ("###,###.###", 123456.789, yereller[i]);
    } // main(..) metodu sonu...
} // J8d_4 sýnýfý sonu...

/* Çýktý:
**  >java J8d_4  **
Deðer: 123456.789, Kalýp: ###,###.###, Kalýplý deðer: 123.456,789
Deðer: 123456.789, Kalýp: ###.##, Kalýplý deðer: 123456,79
Deðer: 123.78, Kalýp: 000000.000, Kalýplý deðer: 000123,780
Deðer: 12345.67, Kalýp: $###,###.###, Kalýplý deðer: $12.345,67
Deðer: 12345.67, Kalýp: ¥###,###.###, Kalýplý deðer: ¥12.345,67

4 gruplu binler ^ ve | ondalýk ayraçlý tuhaf biçimli sayý: 1^2345|678

###,###.###  123,456.789  en_US
###,###.###  123.456,789  de_DE
###,###.###  123 456,789  fr_FR
###,###.###  123.456,789  tr_TR
*/