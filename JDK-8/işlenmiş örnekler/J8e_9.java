// J8e_9.java: StreamConverter (Ak���evirici) �rne�i.

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
            FileOutputStream dosyayaYazd�rmaAk��� = new FileOutputStream ("test.txt");
            Writer yaz = new OutputStreamWriter (dosyayaYazd�rmaAk���, "UTF8");
            yaz.write (dizge);
            yaz.close();
        }catch (IOException ist) {ist.printStackTrace();}
    } // dosyayaYaz(..) metodu sonu...

    static String dosyadanOku() {
        StringBuffer tampon = new StringBuffer();
        try {
            FileInputStream dosyadanOkumaAk��� = new FileInputStream ("test.txt");
            InputStreamReader ak��Okuyucu = new InputStreamReader (dosyadanOkumaAk���, "UTF8");
            Reader oku = new BufferedReader (ak��Okuyucu);
            int krk;
            while ((krk = oku.read()) > -1) tampon.append ((char)krk);
            oku.close();
            return tampon.toString();
        }catch (IOException ist) {ist.printStackTrace(); return null;}
    } // dosyadanOku() metodu sonu...

    public static void main (String[] arg�man) {
        String japoncaDizge  = new String ("\u65e5\u672c\u8a9e\u6587\u5b57\u5217");

        String girdiDizgesi = "";
        if (arg�man.length > 0) girdiDizgesi = dosyadanOku();
        else {dosyayaYaz (japoncaDizge); girdiDizgesi = dosyadanOku();}

        String ��kt�Dizgesi = japoncaDizge + " " + girdiDizgesi;
        new J8e_9x (��kt�Dizgesi, "UTF8 �evrim G�sterisi"); // Alt-�rnek kurucusunu �a��r�r...
    } // main(..) metodu sonu...
} // J8e_9 s�n�f� sonu...