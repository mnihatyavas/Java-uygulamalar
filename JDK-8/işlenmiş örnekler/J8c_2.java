// J8c_2.java: PropertiesDemo (�zelliklerG�sterisi) �rne�i.

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Enumeration;

/* Gereken dosyalar:
 *     J8c_2x_fr_FR.java
 *     J8c_2x_de_GR.java
 *     J8c_2x_en_GB.java
 */
public class J8c_2 {
    static void i�erikleriG�ster (Locale akt�elYerel, String anahtar) {
        ResourceBundle etiket = ResourceBundle.getBundle ("J8c_2x", akt�elYerel);
        String de�er  = etiket.getString (anahtar);
        System.out.println (
           "�lke = " + akt�elYerel.toString() + ", " +
           "anahtar = " + anahtar + ", " +
           "de�er = " + de�er);
    } // i�erikleriG�ster(..) metodu sonu...

    static void anahtarlar�Tara (Locale akt�elYerel) {
        ResourceBundle etiket = ResourceBundle.getBundle ("J8c_2x", akt�elYerel);
        Enumeration boh�aAnahtarlar� = etiket.getKeys();

        while (boh�aAnahtarlar�.hasMoreElements()) {
            String anahtar = (String)boh�aAnahtarlar�.nextElement();
            String de�er  = etiket.getString (anahtar);
            System.out.println ("anahtar = " + anahtar + ", " + "de�er = " + de�er);
        } // while d�ng�s� sonu...
    } // anahtarlar�Tara(..) metodu sonu...

    static public void main (String[] args) {
        Locale[] desteklenenYereller = {
            Locale.FRENCH,
            Locale.GERMAN,
            Locale.ENGLISH
        }; // desteklenenYereller dizisi sonu...

        for (int i = 0; i < desteklenenYereller.length; i ++) i�erikleriG�ster (desteklenenYereller[i], "anh1");
        System.out.println();
        for (int i = 0; i < desteklenenYereller.length; i ++) anahtarlar�Tara (desteklenenYereller[i]);
   } // main(..) metodu sonu...
} // J8c_2 s�n�f� sonu...

/* ��kt�:
**  >java J8c_2  **
�lke = fr, anahtar = anh1, de�er = Je t'em boku!
�lke = de, anahtar = anh1, de�er = Ich liebe dich!
�lke = en, anahtar = anh1, de�er = I love you!

anahtar = anh3, de�er = Le la, en due
anahtar = anh2, de�er = Si vou'ple!
anahtar = anh1, de�er = Je t'em boku!
anahtar = anh3, de�er = Der die das, eins zwei drei...
anahtar = anh2, de�er = Wie heist du?
anahtar = anh1, de�er = Ich liebe dich!
anahtar = anh3, de�er = He she it the, one two tree...
anahtar = anh2, de�er = How do you do?
anahtar = anh1, de�er = I love you!
*/