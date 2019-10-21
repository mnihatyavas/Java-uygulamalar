/* J3b_5.java: CheckedRAFTest (KontrolluREDDenemesi) �rne�i.
 *
 * RAF: Random  Access File
 * RED: Rasgele Eri�im Dosyas�
 */

/* Gereken dosyalar:
 *   J3b_1x1.java=Adler32
 *   J3b_1x2.java=Checksum
 *   J3b_1x3.java=CheckedDataInput
 *   J3b_1x4.java=CheckedDataOutput
 */
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;

public class J3b_5 {
    public static void main (String[] args) {
        String okunanDosya = args.length > 0? args[0] : "J3b_5.java";
        String kopyaDosya = args.length > 1? args[1] : "kopya.java";
        boolean DosyaSonu = false;

        J3b_1x1 okuKontrolu = new J3b_1x1(); // J3b_1x1=Adler32...
        J3b_1x1 yazKontrolu = new J3b_1x1();
        J3b_1x3 okuyal�m = null; // J3b_1x3=CheckedDataInput/KontrolluVeriGiri�i...
        J3b_J3b_1x4 yazal�m = null; // J3b_J3b_1x4=CheckedDataOutput/KontrolluVeri��k���...

        try {okuyal�m = new J3b_1x3 (new RandomAccessFile (okunanDosya, "r"), okuKontrolu);
            yazal�m = new J3b_J3b_1x4 (new RandomAccessFile (kopyaDosya, "rw"), yazKontrolu);

            while (!DosyaSonu) {int k = okuyal�m.byteOku(); yazal�m.yaz (k);}
        }catch (FileNotFoundException ist) {System.err.println ("Dosya bulunamad� hatas�: " + ist); System.exit (-1);
        }catch (EOFException ist) {DosyaSonu = true;
        }catch (IOException ist) {System.err.println ("Okuma yazma hatas�: " + ist); System.exit (-1);
        } // try-catch-.. blo�u sonu...

       System.out.println ("[" + okunanDosya + "] --> [" + kopyaDosya + "]'ya kopyaland�.");
       System.out.println ("Okunan dosya kontrolyek�n�: [" + okuyal�m.kontrolyek�n�Al().de�erAl() + "]'d�r.");
       System.out.println ("Kopyalanan dosya kontrolyek�n�: [" + yazal�m.kontrolyek�n�Al().de�erAl() + "]'d�r.");
    } // main(..) metodu sonu...
} // J3b_5 s�n�f� sonu...

/* ��kt�:
**  >java J3b_5 J3b_1x1.java kopya2.java  ** TEKRAR
[J3b_1x1.java] --> [kopya2.java]'ya kopyaland�.
Okunan dosya kontrolyek�n�: [246267612]'d�r.
Kopyalanan dosya kontrolyek�n�: [246267612]'d�r.

**  >java J3b_5  **
[J3b_5.java] --> [kopya.java]'ya kopyaland�.
Okunan dosya kontrolyek�n�: [-1093911060]'d�r.
Kopyalanan dosya kontrolyek�n�: [-1093911060]'d�r.
*/