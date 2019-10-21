/* J3b_5.java: CheckedRAFTest (KontrolluREDDenemesi) örneði.
 *
 * RAF: Random  Access File
 * RED: Rasgele Eriþim Dosyasý
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
        J3b_1x3 okuyalým = null; // J3b_1x3=CheckedDataInput/KontrolluVeriGiriþi...
        J3b_J3b_1x4 yazalým = null; // J3b_J3b_1x4=CheckedDataOutput/KontrolluVeriÇýkýþý...

        try {okuyalým = new J3b_1x3 (new RandomAccessFile (okunanDosya, "r"), okuKontrolu);
            yazalým = new J3b_J3b_1x4 (new RandomAccessFile (kopyaDosya, "rw"), yazKontrolu);

            while (!DosyaSonu) {int k = okuyalým.byteOku(); yazalým.yaz (k);}
        }catch (FileNotFoundException ist) {System.err.println ("Dosya bulunamadý hatasý: " + ist); System.exit (-1);
        }catch (EOFException ist) {DosyaSonu = true;
        }catch (IOException ist) {System.err.println ("Okuma yazma hatasý: " + ist); System.exit (-1);
        } // try-catch-.. bloðu sonu...

       System.out.println ("[" + okunanDosya + "] --> [" + kopyaDosya + "]'ya kopyalandý.");
       System.out.println ("Okunan dosya kontrolyekünü: [" + okuyalým.kontrolyekünüAl().deðerAl() + "]'dýr.");
       System.out.println ("Kopyalanan dosya kontrolyekünü: [" + yazalým.kontrolyekünüAl().deðerAl() + "]'dýr.");
    } // main(..) metodu sonu...
} // J3b_5 sýnýfý sonu...

/* Çýktý:
**  >java J3b_5 J3b_1x1.java kopya2.java  ** TEKRAR
[J3b_1x1.java] --> [kopya2.java]'ya kopyalandý.
Okunan dosya kontrolyekünü: [246267612]'dýr.
Kopyalanan dosya kontrolyekünü: [246267612]'dýr.

**  >java J3b_5  **
[J3b_5.java] --> [kopya.java]'ya kopyalandý.
Okunan dosya kontrolyekünü: [-1093911060]'dýr.
Kopyalanan dosya kontrolyekünü: [-1093911060]'dýr.
*/