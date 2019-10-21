// J8d_6.java: MessageFormatDemo (MesajBi�imlemeG�sterisi) �rne�i.

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Date;

import java.text.MessageFormat;

/* Gereken dosalar:
 *     J86_6x_en_US.properties
 *     J86_6x_de_DE.properties
 *     J86_6x_tr_TR.properties
 */
public class J8d_6 {
    static  void mesaj�G�ster (Locale akt�elYerel) {
        System.out.println ("Akt�el Yerel = " + akt�elYerel.toString());
        System.out.println ("--------------------");

        ResourceBundle mesaj = ResourceBundle.getBundle ("J8d_6x", akt�elYerel);

        Object[] mesajArg�manlar� = {
            mesaj.getString ("gezegen"),
            new Integer(7),
            new Date()
        }; // mesajArg�manlar� dizisi sonu...

        MessageFormat mesajBi�imi = new MessageFormat ("");
        mesajBi�imi.setLocale (akt�elYerel);

        mesajBi�imi.applyPattern (mesaj.getString ("tekrar"));
        String ��kt� = mesajBi�imi.format (mesajArg�manlar�);

        System.out.println (��kt�);
    } // mesaj�G�ster(..) metodu sonu...

    static public void main (String[] args) {
        mesaj�G�ster (new Locale ("en", "US"));
        System.out.println(); mesaj�G�ster (new Locale ("de", "DE"));
        System.out.println(); mesaj�G�ster (new Locale ("tr", "TR"));
    } // main(..) metodu sonu...
} // J8d_6 s�n�f� sonu...

/* ��kt�:
**  >java J8d_6  **
Akt�el Yerel = en_US
--------------------
At 8:12 PM on November 16, 2018, we detected 7 spaceships on the planet Mars.

Akt�el Yerel = de_DE
--------------------
Um 20:12 am 16. November 2018 haben wir 7 Raumschiffe auf dem Planeten Mars entd
eckt.

Akt�el Yerel = tr_TR
--------------------
Saat 20:12 ve tarih 16 Kas�m 2018 Cuma g�n�, biz Mars gezegeni y�zeyinde 7 adet
yabanc� uzaygemisi (UFO) tespit ettik. [� �] [�] [� �] [�] [� �] [� �] [� �] [� �]
*/