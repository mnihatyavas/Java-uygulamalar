// J8g_1.java: RBCPTest (KaynakBoh�as�KontrolTedarikcisiTesti) �rne�i.

import java.util.ResourceBundle;
import java.util.Locale;

// RBCP: ResourceBundleControlProvider (KaynakBoh�as�KontrolTedarikcisi)
public class J8g_1 {
    public static void main (String[] args) {
        ResourceBundle kaynakBoh�as� = ResourceBundle.getBundle ("J8g_1x1" , Locale.ROOT);
        String tip = kaynakBoh�as�.getString ("tip");
        System.out.println ("K�k yereli, tip: " + tip);

        kaynakBoh�as� = ResourceBundle.getBundle ("J8g_1x1", Locale.JAPAN);
        tip = kaynakBoh�as�.getString ("tip");
        System.out.println ("Japon yereli, tip: " + tip);

        kaynakBoh�as� = ResourceBundle.getBundle ("J8g_1x1", new Locale ("tr", "TR") );
        tip = kaynakBoh�as�.getString ("tip");
        System.out.println ("T�rkiye yereli, tip: " + tip);
        System.out.println ("-------------------------------------------------------------------------");

        kontrol (Locale.ROOT);
        kontrol (Locale.CHINA);
        kontrol (new Locale ("zh", "HK"));
        kontrol (Locale.TAIWAN);
        kontrol (Locale.CANADA);
    } // main(..) metodu sonu...

    private static void kontrol (Locale yerel) {
        ResourceBundle kaynakBoh�as� = ResourceBundle.getBundle ("J8g_1x2", yerel);
        System.out.println ("Yerel: " + yerel);
        System.out.println ("    �lke: " + kaynakBoh�as�.getString ("�lke"));
        System.out.println ("    lisan: " + kaynakBoh�as�.getString ("lisan"));
        System.out.println();
    } // kontrol(..) metodu sonu...
} // J8g_1 s�n�f� sonu...

/* ��kt�:
**  >java J8g_1 **
K�k yereli, tip: XML dosyas�n� okuyamad���ndan, properties'den bir de�er.
Japon yereli, tip: Japonya yerelinden bir de�er.
T�rkiye yereli, tip: [� �] [�] [� �] [�] [� �] [� �] [� �] [� �]
-------------------------------------------------------------------------
Yerel:
    �lke: Uluslararas�
    lisan: �ngilizce

Yerel: zh_CN
    �lke: �in
    lisan: Basitle�tirilmi� �ince

Yerel: zh_HK
    �lke: Hong Kong
    lisan: Basitle�tirilmi� �ince

Yerel: zh_TW
    �lke: Tayvan
    lisan: Geleneksel �ince

Yerel: en_CA
    �lke: Kanada
    lisan: �ngilizce
*/