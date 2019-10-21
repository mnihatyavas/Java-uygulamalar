// J8g_1.java: RBCPTest (KaynakBohçasýKontrolTedarikcisiTesti) örneði.

import java.util.ResourceBundle;
import java.util.Locale;

// RBCP: ResourceBundleControlProvider (KaynakBohçasýKontrolTedarikcisi)
public class J8g_1 {
    public static void main (String[] args) {
        ResourceBundle kaynakBohçasý = ResourceBundle.getBundle ("J8g_1x1" , Locale.ROOT);
        String tip = kaynakBohçasý.getString ("tip");
        System.out.println ("Kök yereli, tip: " + tip);

        kaynakBohçasý = ResourceBundle.getBundle ("J8g_1x1", Locale.JAPAN);
        tip = kaynakBohçasý.getString ("tip");
        System.out.println ("Japon yereli, tip: " + tip);

        kaynakBohçasý = ResourceBundle.getBundle ("J8g_1x1", new Locale ("tr", "TR") );
        tip = kaynakBohçasý.getString ("tip");
        System.out.println ("Türkiye yereli, tip: " + tip);
        System.out.println ("-------------------------------------------------------------------------");

        kontrol (Locale.ROOT);
        kontrol (Locale.CHINA);
        kontrol (new Locale ("zh", "HK"));
        kontrol (Locale.TAIWAN);
        kontrol (Locale.CANADA);
    } // main(..) metodu sonu...

    private static void kontrol (Locale yerel) {
        ResourceBundle kaynakBohçasý = ResourceBundle.getBundle ("J8g_1x2", yerel);
        System.out.println ("Yerel: " + yerel);
        System.out.println ("    ülke: " + kaynakBohçasý.getString ("ülke"));
        System.out.println ("    lisan: " + kaynakBohçasý.getString ("lisan"));
        System.out.println();
    } // kontrol(..) metodu sonu...
} // J8g_1 sýnýfý sonu...

/* Çýktý:
**  >java J8g_1 **
Kök yereli, tip: XML dosyasýný okuyamadýðýndan, properties'den bir deðer.
Japon yereli, tip: Japonya yerelinden bir deðer.
Türkiye yereli, tip: [£ ¥] [Ç] [Ö Ü] [ç] [ö ü] [Ð ð] [Ý ý] [Þ þ]
-------------------------------------------------------------------------
Yerel:
    ülke: Uluslararasý
    lisan: Ýngilizce

Yerel: zh_CN
    ülke: Çin
    lisan: Basitleþtirilmiþ Çince

Yerel: zh_HK
    ülke: Hong Kong
    lisan: Basitleþtirilmiþ Çince

Yerel: zh_TW
    ülke: Tayvan
    lisan: Geleneksel Çince

Yerel: en_CA
    ülke: Kanada
    lisan: Ýngilizce
*/