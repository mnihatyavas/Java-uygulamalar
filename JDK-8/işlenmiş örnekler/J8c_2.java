// J8c_2.java: PropertiesDemo (ÖzelliklerGösterisi) örneði.

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Enumeration;

/* Gereken dosyalar:
 *     J8c_2x_fr_FR.java
 *     J8c_2x_de_GR.java
 *     J8c_2x_en_GB.java
 */
public class J8c_2 {
    static void içerikleriGöster (Locale aktüelYerel, String anahtar) {
        ResourceBundle etiket = ResourceBundle.getBundle ("J8c_2x", aktüelYerel);
        String deðer  = etiket.getString (anahtar);
        System.out.println (
           "Ülke = " + aktüelYerel.toString() + ", " +
           "anahtar = " + anahtar + ", " +
           "deðer = " + deðer);
    } // içerikleriGöster(..) metodu sonu...

    static void anahtarlarýTara (Locale aktüelYerel) {
        ResourceBundle etiket = ResourceBundle.getBundle ("J8c_2x", aktüelYerel);
        Enumeration bohçaAnahtarlarý = etiket.getKeys();

        while (bohçaAnahtarlarý.hasMoreElements()) {
            String anahtar = (String)bohçaAnahtarlarý.nextElement();
            String deðer  = etiket.getString (anahtar);
            System.out.println ("anahtar = " + anahtar + ", " + "deðer = " + deðer);
        } // while döngüsü sonu...
    } // anahtarlarýTara(..) metodu sonu...

    static public void main (String[] args) {
        Locale[] desteklenenYereller = {
            Locale.FRENCH,
            Locale.GERMAN,
            Locale.ENGLISH
        }; // desteklenenYereller dizisi sonu...

        for (int i = 0; i < desteklenenYereller.length; i ++) içerikleriGöster (desteklenenYereller[i], "anh1");
        System.out.println();
        for (int i = 0; i < desteklenenYereller.length; i ++) anahtarlarýTara (desteklenenYereller[i]);
   } // main(..) metodu sonu...
} // J8c_2 sýnýfý sonu...

/* Çýktý:
**  >java J8c_2  **
Ülke = fr, anahtar = anh1, deðer = Je t'em boku!
Ülke = de, anahtar = anh1, deðer = Ich liebe dich!
Ülke = en, anahtar = anh1, deðer = I love you!

anahtar = anh3, deðer = Le la, en due
anahtar = anh2, deðer = Si vou'ple!
anahtar = anh1, deðer = Je t'em boku!
anahtar = anh3, deðer = Der die das, eins zwei drei...
anahtar = anh2, deðer = Wie heist du?
anahtar = anh1, deðer = Ich liebe dich!
anahtar = anh3, deðer = He she it the, one two tree...
anahtar = anh2, deðer = How do you do?
anahtar = anh1, deðer = I love you!
*/