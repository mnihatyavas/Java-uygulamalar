// J8c_3.java: RBControl (KaynakBoh�as�Kontrolu) �rne�i.

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Arrays;

/* Gereken dosyalar:
 *     J8c_3x_zh_CN.java // �ince-�in
 *     J8c_3x_zh_HK.java // �ince-Hongkong
 *     J8c_3x_zh_TW.java // �ince-Tayvan
 *     J8c_3x_en_CA.java // �ngilizce-Kanada
 */
public class J8c_3 {
    public static void main (String[] args) {
        kontrolEt (Locale.CHINA);
        kontrolEt (new Locale ("zh", "HK"));
        kontrolEt (Locale.TAIWAN);
        kontrolEt (Locale.CANADA);
    } // main(..) metodu sonu...

    private static void kontrolEt (Locale yerel) {
        ResourceBundle kaynakBoh�as� = ResourceBundle.getBundle (
            "J8c_3x", yerel, new ResourceBundle.Control() {
                public List<Locale> getCandidateLocales (String temelAd, Locale yerel) {
                    if (temelAd == null) throw new NullPointerException(); // java.lang.*
                    if (yerel.equals (new Locale ("zh", "HK"))) {
                        return Arrays.asList (
                            yerel,
                            Locale.TAIWAN,
                            Locale.ROOT); // Yerel yok, burada lisan �ince...
                    }else if (yerel.equals (Locale.TAIWAN)) {
                        return Arrays.asList (
                            yerel,
                            Locale.ROOT); // Yerel yok, burada da lisan �ince...
                    } // else-if karar� sonu...
                    return super.getCandidateLocales (temelAd, yerel);
                } // getCandidateLocales(..) haz�r metodu sonu...
            } // "J8.. blo�u sonu...
        ); // Res.. ifadesi sonu...
        System.out.print ("Yerel: " + yerel);
        System.out.print ("\tB�lge: " + kaynakBoh�as�.getString ("B�lge"));
        System.out.print ("\tLisan: " + kaynakBoh�as�.getString ("Lisan"));
        System.out.println();
    } // kontrolEt(..) metodu sonu...
} // J8c_3 s�n�f� sonu...

/* ��kt�:
**  >java J8c_3  **
Yerel: zh_CN    B�lge: �in      	Lisan: �ince
Yerel: zh_HK    B�lge: Hongkong Lisan: �ince
Yerel: zh_TW    B�lge: Tayvan   Lisan: �ince
Yerel: en_CA    B�lge: Kanada   Lisan: �ngilizce
*/