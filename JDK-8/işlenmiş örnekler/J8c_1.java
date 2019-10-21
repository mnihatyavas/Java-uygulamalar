// J8c_1.java: ListDemo (ListelemeG�sterisi) �rne�i.

import java.util.ResourceBundle; // KaynakBoh�as�..
import java.util.Locale;

/* Gereken dosyalar:
 *   J8c_1x_en_CA.java
 *   J8c_1x_ja_JP.java
 *   J8c_1x_fr_FR.java
 *   J8c_1x_tr_TR.java
 */
public class J8c_1 {
    static void de�erleriG�ster (Locale akt�elYerel) {
        ResourceBundle de�erler =  ResourceBundle.getBundle ("J8c_1x", akt�elYerel);

        Integer gelir = (Integer)de�erler.getObject ("Y�ll�kGelir");
        System.out.println ("Ki�i ba�� y�ll�k gelir = " + gelir.toString());
        Integer n�fus = (Integer)de�erler.getObject ("N�fus");
        System.out.println ("�lke n�fusu = " + n�fus.toString());
        Double okuma = (Double)de�erler.getObject ("OkumaYazma");
        System.out.println ("Okuma yazma oran� = " + okuma.toString());
    } // de�erleriG�ster

   static public void main (String[] args) {
        Locale[] desteklenenYereller = {
            new Locale ("en","CA"),
            new Locale ("ja","JP"),
            new Locale ("fr","FR"),
            new Locale ("tr","TR")
        }; // desteklenenYereller dizisi sonu...

        for (int i = 0; i < desteklenenYereller.length; i ++) {
            System.out.println ("�lke = " + desteklenenYereller[i]);
            de�erleriG�ster (desteklenenYereller[i]);
            System.out.println();
        } // for d�ng�s� sonu...
   } // main(..) metodu sonu...
} // J8c_1 s�n�f� sonu...

/* ��kt�:
**  >java J8c_1  **
�lke = en_CA
Ki�i ba�� y�ll�k gelir = 24400
�lke n�fusu = 28802671
Okuma yazma oran� = 0.97

�lke = ja_JP
Ki�i ba�� y�ll�k gelir = 21300
�lke n�fusu = 125449703
Okuma yazma oran� = 0.99

�lke = fr_FR
Ki�i ba�� y�ll�k gelir = 20200
�lke n�fusu = 58317450
Okuma yazma oran� = 0.99

�lke = tr_TR
Ki�i ba�� y�ll�k gelir = 3000
�lke n�fusu = 80802671
Okuma yazma oran� = 0.85
*/