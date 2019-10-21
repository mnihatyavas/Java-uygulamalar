// J8d_4.java: DecimalFormatDemo (Ondal�kBi�imG�sterisi) �rne�i.

import java.util.Locale;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class J8d_4 {
    static public void �zelBi�im (String kal�p, double de�er ) {
        System.out.println ("De�er: " + de�er + ", Kal�p: " + kal�p + ", Kal�pl� de�er: " + new DecimalFormat (kal�p).format (de�er));
    } // �zelBi�im(..) metodu sonu...

    static public void yerelle�enBi�im (String kal�p, double de�er, Locale yerel ) {
        NumberFormat say�Bi�imi = NumberFormat.getNumberInstance (yerel);
        DecimalFormat ondal�kBi�im = (DecimalFormat)say�Bi�imi;
        ondal�kBi�im.applyPattern (kal�p);
        String ��kt�Dizgesi = ondal�kBi�im.format (de�er);
        System.out.println (kal�p + "  " + ��kt�Dizgesi + "  " + yerel.toString());
    } // yerelle�enBi�im(..) metodu sonu...

    static public void main (String[] args) {
        �zelBi�im ("###,###.###", 123456.789);
        �zelBi�im ("###.##", 123456.789);
        �zelBi�im ("000000.000", 123.78);
        �zelBi�im ("$###,###.###", 12345.67);
        �zelBi�im ("\u00a5###,###.###", 12345.67); // hex:165=ascii:190, Japon Yeni...

        Locale akt�elYerel = new Locale ("en", "US");

        DecimalFormatSymbols nadirSemboller = new DecimalFormatSymbols (akt�elYerel);
        nadirSemboller.setDecimalSeparator ('|'); // Ondal�k ayra� sembol� olsun...
        nadirSemboller.setGroupingSeparator ('^'); // Binler ayrac� sembol� olsun...
        String garipDizge = "#,##0.###";
        DecimalFormat acaipBi�im = new DecimalFormat (garipDizge, nadirSemboller);
        acaipBi�im.setGroupingSize (4); // Binler gruplamas� 4'l� olsun...
        String sa�mal�k = acaipBi�im.format (12345.678);
        System.out.println ("\n4 gruplu binler ^ ve | ondal�k ayra�l� tuhaf bi�imli say�: " + sa�mal�k);

        Locale[] yereller = {
            new Locale ("en", "US"),
            new Locale ("de", "DE"),
            new Locale ("fr", "FR"),
            new Locale ("tr", "TR")
        }; // yereller dizisi sonu...

        System.out.println();
        for (int i = 0; i < yereller.length; i++) yerelle�enBi�im ("###,###.###", 123456.789, yereller[i]);
    } // main(..) metodu sonu...
} // J8d_4 s�n�f� sonu...

/* ��kt�:
**  >java J8d_4  **
De�er: 123456.789, Kal�p: ###,###.###, Kal�pl� de�er: 123.456,789
De�er: 123456.789, Kal�p: ###.##, Kal�pl� de�er: 123456,79
De�er: 123.78, Kal�p: 000000.000, Kal�pl� de�er: 000123,780
De�er: 12345.67, Kal�p: $###,###.###, Kal�pl� de�er: $12.345,67
De�er: 12345.67, Kal�p: �###,###.###, Kal�pl� de�er: �12.345,67

4 gruplu binler ^ ve | ondal�k ayra�l� tuhaf bi�imli say�: 1^2345|678

###,###.###  123,456.789  en_US
###,###.###  123.456,789  de_DE
###,###.###  123�456,789  fr_FR
###,###.###  123.456,789  tr_TR
*/