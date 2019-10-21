// J8c_3.java: RBControl (KaynakBohçasýKontrolu) örneði.

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Arrays;

/* Gereken dosyalar:
 *     J8c_3x_zh_CN.java // Çince-Çin
 *     J8c_3x_zh_HK.java // Çince-Hongkong
 *     J8c_3x_zh_TW.java // Çince-Tayvan
 *     J8c_3x_en_CA.java // Ýngilizce-Kanada
 */
public class J8c_3 {
    public static void main (String[] args) {
        kontrolEt (Locale.CHINA);
        kontrolEt (new Locale ("zh", "HK"));
        kontrolEt (Locale.TAIWAN);
        kontrolEt (Locale.CANADA);
    } // main(..) metodu sonu...

    private static void kontrolEt (Locale yerel) {
        ResourceBundle kaynakBohçasý = ResourceBundle.getBundle (
            "J8c_3x", yerel, new ResourceBundle.Control() {
                public List<Locale> getCandidateLocales (String temelAd, Locale yerel) {
                    if (temelAd == null) throw new NullPointerException(); // java.lang.*
                    if (yerel.equals (new Locale ("zh", "HK"))) {
                        return Arrays.asList (
                            yerel,
                            Locale.TAIWAN,
                            Locale.ROOT); // Yerel yok, burada lisan Çince...
                    }else if (yerel.equals (Locale.TAIWAN)) {
                        return Arrays.asList (
                            yerel,
                            Locale.ROOT); // Yerel yok, burada da lisan Çince...
                    } // else-if kararý sonu...
                    return super.getCandidateLocales (temelAd, yerel);
                } // getCandidateLocales(..) hazýr metodu sonu...
            } // "J8.. bloðu sonu...
        ); // Res.. ifadesi sonu...
        System.out.print ("Yerel: " + yerel);
        System.out.print ("\tBölge: " + kaynakBohçasý.getString ("Bölge"));
        System.out.print ("\tLisan: " + kaynakBohçasý.getString ("Lisan"));
        System.out.println();
    } // kontrolEt(..) metodu sonu...
} // J8c_3 sýnýfý sonu...

/* Çýktý:
**  >java J8c_3  **
Yerel: zh_CN    Bölge: Çin      	Lisan: Çince
Yerel: zh_HK    Bölge: Hongkong Lisan: Çince
Yerel: zh_TW    Bölge: Tayvan   Lisan: Çince
Yerel: en_CA    Bölge: Kanada   Lisan: Ýngilizce
*/