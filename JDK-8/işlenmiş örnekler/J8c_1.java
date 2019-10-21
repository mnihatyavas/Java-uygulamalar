// J8c_1.java: ListDemo (ListelemeGösterisi) örneði.

import java.util.ResourceBundle; // KaynakBohçasý..
import java.util.Locale;

/* Gereken dosyalar:
 *   J8c_1x_en_CA.java
 *   J8c_1x_ja_JP.java
 *   J8c_1x_fr_FR.java
 *   J8c_1x_tr_TR.java
 */
public class J8c_1 {
    static void deðerleriGöster (Locale aktüelYerel) {
        ResourceBundle deðerler =  ResourceBundle.getBundle ("J8c_1x", aktüelYerel);

        Integer gelir = (Integer)deðerler.getObject ("YýllýkGelir");
        System.out.println ("Kiþi baþý yýllýk gelir = " + gelir.toString());
        Integer nüfus = (Integer)deðerler.getObject ("Nüfus");
        System.out.println ("Ülke nüfusu = " + nüfus.toString());
        Double okuma = (Double)deðerler.getObject ("OkumaYazma");
        System.out.println ("Okuma yazma oraný = " + okuma.toString());
    } // deðerleriGöster

   static public void main (String[] args) {
        Locale[] desteklenenYereller = {
            new Locale ("en","CA"),
            new Locale ("ja","JP"),
            new Locale ("fr","FR"),
            new Locale ("tr","TR")
        }; // desteklenenYereller dizisi sonu...

        for (int i = 0; i < desteklenenYereller.length; i ++) {
            System.out.println ("Ülke = " + desteklenenYereller[i]);
            deðerleriGöster (desteklenenYereller[i]);
            System.out.println();
        } // for döngüsü sonu...
   } // main(..) metodu sonu...
} // J8c_1 sýnýfý sonu...

/* Çýktý:
**  >java J8c_1  **
Ülke = en_CA
Kiþi baþý yýllýk gelir = 24400
Ülke nüfusu = 28802671
Okuma yazma oraný = 0.97

Ülke = ja_JP
Kiþi baþý yýllýk gelir = 21300
Ülke nüfusu = 125449703
Okuma yazma oraný = 0.99

Ülke = fr_FR
Kiþi baþý yýllýk gelir = 20200
Ülke nüfusu = 58317450
Okuma yazma oraný = 0.99

Ülke = tr_TR
Kiþi baþý yýllýk gelir = 3000
Ülke nüfusu = 80802671
Okuma yazma oraný = 0.85
*/