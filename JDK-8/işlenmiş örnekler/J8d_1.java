// J8d_1.java: ChoiceFormatDemo (TercihliBi�imlemeG�sterisi) �rne�i.

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
    static void mesajlar�G�ster (Locale akt�elYerel) {
        System.out.println ("�u anki �lke = " + akt�elYerel.toString());
        System.out.println ("----------------------------------------");

        ResourceBundle �lkeBoh�as� = ResourceBundle.getBundle ("J8d_1x", akt�elYerel);

        MessageFormat mesajBi�imi = new MessageFormat ("");
        mesajBi�imi.setLocale (akt�elYerel);

        double[] dosyaS�n�rlar� = {0,1,2};

        String [] dosyaDizgeleri = {
            �lkeBoh�as�.getString("hi�Dosya"),
            �lkeBoh�as�.getString("birDosya"),
            �lkeBoh�as�.getString("�okluDosya")
        }; // dosyaDizgeleri dizisi sonu...

        ChoiceFormat tercihBi�imi = new ChoiceFormat (dosyaS�n�rlar�, dosyaDizgeleri);

        String kal�p = �lkeBoh�as�.getString ("kal�p");
        Format[] bi�imler = {tercihBi�imi, null, NumberFormat.getInstance()};

        mesajBi�imi.applyPattern (kal�p);
        mesajBi�imi.setFormats (bi�imler);

        Object[] mesajArg�manlar� = {null, "C:\\>Disk", null};

        for (int dosyaSay�s� = 0; dosyaSay�s� < 5; dosyaSay�s�++) {
            mesajArg�manlar�[0] = new Integer (dosyaSay�s�);
            mesajArg�manlar�[2] = new Integer (dosyaSay�s�);
            String ��kt� = mesajBi�imi.format (mesajArg�manlar�);
            System.out.println (��kt�);
        } // for d�ng�s� sonu...
    } // mesajlar�G�ster(..) metodu sonu...

    static public void main (String[] args) {
        mesajlar�G�ster (new Locale ("en", "US"));
        System.out.println(); mesajlar�G�ster (new Locale ("fr", "FR"));
        System.out.println(); mesajlar�G�ster (new Locale ("tr", "TR"));
    } // main(..) metodu sonu...
} // J8d_1 s�n�f� sonu...

/* ��kt�:
**  >java J8d_1  **
�u anki �lke = en_US
----------------------------------------
There are no files on C:\>Disk.
There is one file on C:\>Disk.
There are 2 files on C:\>Disk.
There are 3 files on C:\>Disk.
There are 4 files on C:\>Disk.

�u anki �lke = fr_FR
----------------------------------------
Il n'y a pas des fichiers sur C:\>Disk.
Il y a un fichier sur C:\>Disk.
Il y a 2 fichiers sur C:\>Disk.
Il y a 3 fichiers sur C:\>Disk.
Il y a 4 fichiers sur C:\>Disk.

�u anki �lke = tr_TR
----------------------------------------
Hi� bir dosya yok, C:\>Disk �zerinde.
Sadece bir dosya var, C:\>Disk �zerinde.
Dosya say�s�: 2'dir, C:\>Disk �zerinde.
Dosya say�s�: 3'dir, C:\>Disk �zerinde.
Dosya say�s�: 4'dir, C:\>Disk �zerinde.
*/