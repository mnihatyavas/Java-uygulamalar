// J3b_4.java: CheckedIOTest (KontrolluOkumaYazmaDenemesi) �rne�i.

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
        J3b_1x3 oku = null; // J3b_1x3=CheckedInputStream/KontrolluGiri�Ak�m�...
        J3b_1x4 yaz = null; // J3b_1x4=CheckedOutputStream/Kontrollu��k��Ak�m�...

        try {oku = new J3b_1x3 (new FileInputStream (okunanDosya), okuKontrolu);
            yaz = new J3b_1x4 (new FileOutputStream (kopyaDosya), yazKontrolu);

            int k;
            while ((k = oku.read()) != -1) yaz.write (k);

            oku.close();
            yaz.close();
        }catch (FileNotFoundException ist) {System.err.println ("Dosya bulunamad� hatas�: " + ist); System.exit (-1);
        }catch (IOException ist) {System.err.println ("Okuma yazma hatas�: " + ist); System.exit (-1);
        } // try-catch-catch blo�u sonu...

       System.out.println ("[" + okunanDosya + "] --> [" + kopyaDosya + "]'ya kopyaland�.");
       System.out.println ("Okunan dosya kontrolyek�n�: [" + okuKontrolu.de�erAl() + "] byte'd�r.");
       System.out.println ("Kopyalanan dosya kontrolyek�n�: [" + yazKontrolu.de�erAl() + "] byte'd�r.");
    } // main(..) metodu sonu...
} // J3b_4 s�n�f� sonu...

/* ��kt�:
**  >java J3b_4  **
[J3b_4.java] --> [kopya.java]'ya kopyaland�.
Okunan dosya kontrolyek�n�: [1] byte'd�r. // Hatal� sonu�?!..
Kopyalanan dosya kontrolyek�n�: [1] byte'd�r.

**  >java J3b_4 J3b_1x2.java kontrol2.java  ** TEKRAR
[J3b_1x2.java] --> [kontrol2.java]'ya kopyaland�.
Okunan dosya kontrolyek�n�: [1] byte'd�r.
Kopyalanan dosya kontrolyek�n�: [1] byte'd�r.
*/