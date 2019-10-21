// J8d_1.java: ChoiceFormatDemo (TercihliBiçimlemeGösterisi) örneði.

import java.util.ResourceBundle;
import java.util.Locale;

import java.text.Format;
import java.text.NumberFormat;
import java.text.MessageFormat;
import java.text.ChoiceFormat;

/* Gereken dosyalar:
 *     J8d_1x_en_US.properties
 *     J8d_1x_fr_FR.properties
 *     J8d_1x_tr.properties
 */
public class J8d_1 {
    static void mesajlarýGöster (Locale aktüelYerel) {
        System.out.println ("Þu anki ülke = " + aktüelYerel.toString());
        System.out.println ("----------------------------------------");

        ResourceBundle ülkeBohçasý = ResourceBundle.getBundle ("J8d_1x", aktüelYerel);

        MessageFormat mesajBiçimi = new MessageFormat ("");
        mesajBiçimi.setLocale (aktüelYerel);

        double[] dosyaSýnýrlarý = {0,1,2};

        String [] dosyaDizgeleri = {
            ülkeBohçasý.getString("hiçDosya"),
            ülkeBohçasý.getString("birDosya"),
            ülkeBohçasý.getString("çokluDosya")
        }; // dosyaDizgeleri dizisi sonu...

        ChoiceFormat tercihBiçimi = new ChoiceFormat (dosyaSýnýrlarý, dosyaDizgeleri);

        String kalýp = ülkeBohçasý.getString ("kalýp");
        Format[] biçimler = {tercihBiçimi, null, NumberFormat.getInstance()};

        mesajBiçimi.applyPattern (kalýp);
        mesajBiçimi.setFormats (biçimler);

        Object[] mesajArgümanlarý = {null, "C:\\>Disk", null};

        for (int dosyaSayýsý = 0; dosyaSayýsý < 5; dosyaSayýsý++) {
            mesajArgümanlarý[0] = new Integer (dosyaSayýsý);
            mesajArgümanlarý[2] = new Integer (dosyaSayýsý);
            String çýktý = mesajBiçimi.format (mesajArgümanlarý);
            System.out.println (çýktý);
        } // for döngüsü sonu...
    } // mesajlarýGöster(..) metodu sonu...

    static public void main (String[] args) {
        mesajlarýGöster (new Locale ("en", "US"));
        System.out.println(); mesajlarýGöster (new Locale ("fr", "FR"));
        System.out.println(); mesajlarýGöster (new Locale ("tr", "TR"));
    } // main(..) metodu sonu...
} // J8d_1 sýnýfý sonu...

/* Çýktý:
**  >java J8d_1  **
Þu anki ülke = en_US
----------------------------------------
There are no files on C:\>Disk.
There is one file on C:\>Disk.
There are 2 files on C:\>Disk.
There are 3 files on C:\>Disk.
There are 4 files on C:\>Disk.

Þu anki ülke = fr_FR
----------------------------------------
Il n'y a pas des fichiers sur C:\>Disk.
Il y a un fichier sur C:\>Disk.
Il y a 2 fichiers sur C:\>Disk.
Il y a 3 fichiers sur C:\>Disk.
Il y a 4 fichiers sur C:\>Disk.

Þu anki ülke = tr_TR
----------------------------------------
Hiç bir dosya yok, C:\>Disk üzerinde.
Sadece bir dosya var, C:\>Disk üzerinde.
Dosya sayýsý: 2'dir, C:\>Disk üzerinde.
Dosya sayýsý: 3'dir, C:\>Disk üzerinde.
Dosya sayýsý: 4'dir, C:\>Disk üzerinde.
*/