// J8e_9.java: StreamConverter (AkýþÇevirici) örneði.

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.Writer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;

// Gereken dosya: J8e_9x.java=ShowString.java
public class J8e_9 {
    static void dosyayaYaz (String dizge) {
        try {
            FileOutputStream dosyayaYazdýrmaAkýþý = new FileOutputStream ("test.txt");
            Writer yaz = new OutputStreamWriter (dosyayaYazdýrmaAkýþý, "UTF8");
            yaz.write (dizge);
            yaz.close();
        }catch (IOException ist) {ist.printStackTrace();}
    } // dosyayaYaz(..) metodu sonu...

    static String dosyadanOku() {
        StringBuffer tampon = new StringBuffer();
        try {
            FileInputStream dosyadanOkumaAkýþý = new FileInputStream ("test.txt");
            InputStreamReader akýþOkuyucu = new InputStreamReader (dosyadanOkumaAkýþý, "UTF8");
            Reader oku = new BufferedReader (akýþOkuyucu);
            int krk;
            while ((krk = oku.read()) > -1) tampon.append ((char)krk);
            oku.close();
            return tampon.toString();
        }catch (IOException ist) {ist.printStackTrace(); return null;}
    } // dosyadanOku() metodu sonu...

    public static void main (String[] argüman) {
        String japoncaDizge  = new String ("\u65e5\u672c\u8a9e\u6587\u5b57\u5217");

        String girdiDizgesi = "";
        if (argüman.length > 0) girdiDizgesi = dosyadanOku();
        else {dosyayaYaz (japoncaDizge); girdiDizgesi = dosyadanOku();}

        String çýktýDizgesi = japoncaDizge + " " + girdiDizgesi;
        new J8e_9x (çýktýDizgesi, "UTF8 Çevrim Gösterisi"); // Alt-örnek kurucusunu çaðýrýr...
    } // main(..) metodu sonu...
} // J8e_9 sýnýfý sonu...