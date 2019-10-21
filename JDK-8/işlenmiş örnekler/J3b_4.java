// J3b_4.java: CheckedIOTest (KontrolluOkumaYazmaDenemesi) örneði.

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

/* Gereken dosyalar:
 *   J3b_1x1.java=Adler32
 *   J3b_1x2.java=Checksum
 *   J3b_4x3.java=CheckedInputStream
 *   J3b_4x4.java=CheckedOutputStream
 */
public class J3b_4 {
    public static void main (String[] args) {
        String okunanDosya = args.length > 0? args[0] : "J3b_4.java";
        String kopyaDosya = args.length > 1? args[1] : "kopya.java";

        J3b_1x1 okuKontrolu = new J3b_1x1(); // J3b_1x1=Adler32...
        J3b_1x1 yazKontrolu = new J3b_1x1();
        J3b_1x3 oku = null; // J3b_1x3=CheckedInputStream/KontrolluGiriþAkýmý...
        J3b_1x4 yaz = null; // J3b_1x4=CheckedOutputStream/KontrolluÇýkýþAkýmý...

        try {oku = new J3b_1x3 (new FileInputStream (okunanDosya), okuKontrolu);
            yaz = new J3b_1x4 (new FileOutputStream (kopyaDosya), yazKontrolu);

            int k;
            while ((k = oku.read()) != -1) yaz.write (k);

            oku.close();
            yaz.close();
        }catch (FileNotFoundException ist) {System.err.println ("Dosya bulunamadý hatasý: " + ist); System.exit (-1);
        }catch (IOException ist) {System.err.println ("Okuma yazma hatasý: " + ist); System.exit (-1);
        } // try-catch-catch bloðu sonu...

       System.out.println ("[" + okunanDosya + "] --> [" + kopyaDosya + "]'ya kopyalandý.");
       System.out.println ("Okunan dosya kontrolyekünü: [" + okuKontrolu.deðerAl() + "] byte'dýr.");
       System.out.println ("Kopyalanan dosya kontrolyekünü: [" + yazKontrolu.deðerAl() + "] byte'dýr.");
    } // main(..) metodu sonu...
} // J3b_4 sýnýfý sonu...

/* Çýktý:
**  >java J3b_4  **
[J3b_4.java] --> [kopya.java]'ya kopyalandý.
Okunan dosya kontrolyekünü: [1] byte'dýr. // Hatalý sonuç?!..
Kopyalanan dosya kontrolyekünü: [1] byte'dýr.

**  >java J3b_4 J3b_1x2.java kontrol2.java  ** TEKRAR
[J3b_1x2.java] --> [kontrol2.java]'ya kopyalandý.
Okunan dosya kontrolyekünü: [1] byte'dýr.
Kopyalanan dosya kontrolyekünü: [1] byte'dýr.
*/