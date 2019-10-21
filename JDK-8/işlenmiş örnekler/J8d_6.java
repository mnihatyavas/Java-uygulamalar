// J8d_6.java: MessageFormatDemo (MesajBiçimlemeGösterisi) örneði.

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
    static  void mesajýGöster (Locale aktüelYerel) {
        System.out.println ("Aktüel Yerel = " + aktüelYerel.toString());
        System.out.println ("--------------------");

        ResourceBundle mesaj = ResourceBundle.getBundle ("J8d_6x", aktüelYerel);

        Object[] mesajArgümanlarý = {
            mesaj.getString ("gezegen"),
            new Integer(7),
            new Date()
        }; // mesajArgümanlarý dizisi sonu...

        MessageFormat mesajBiçimi = new MessageFormat ("");
        mesajBiçimi.setLocale (aktüelYerel);

        mesajBiçimi.applyPattern (mesaj.getString ("tekrar"));
        String çýktý = mesajBiçimi.format (mesajArgümanlarý);

        System.out.println (çýktý);
    } // mesajýGöster(..) metodu sonu...

    static public void main (String[] args) {
        mesajýGöster (new Locale ("en", "US"));
        System.out.println(); mesajýGöster (new Locale ("de", "DE"));
        System.out.println(); mesajýGöster (new Locale ("tr", "TR"));
    } // main(..) metodu sonu...
} // J8d_6 sýnýfý sonu...

/* Çýktý:
**  >java J8d_6  **
Aktüel Yerel = en_US
--------------------
At 8:12 PM on November 16, 2018, we detected 7 spaceships on the planet Mars.

Aktüel Yerel = de_DE
--------------------
Um 20:12 am 16. November 2018 haben wir 7 Raumschiffe auf dem Planeten Mars entd
eckt.

Aktüel Yerel = tr_TR
--------------------
Saat 20:12 ve tarih 16 Kasým 2018 Cuma günü, biz Mars gezegeni yüzeyinde 7 adet
yabancý uzaygemisi (UFO) tespit ettik. [£ ¥] [Ç] [Ö Ü] [ç] [ö ü] [Ð ð] [Ý ý] [Þ þ]
*/