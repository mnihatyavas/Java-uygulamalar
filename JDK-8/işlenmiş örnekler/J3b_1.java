// J3b_1.java: CheckedDIDemo (KontrolluVGG�sterimi) �rne�i.

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.EOFException;

/* Gereken dosyalar:
 *    J3b_1x1.java=Adler32
 *    J3b_1x2.java=Checksum
 *    J3b_1x3.java=CheckedDataInput
 *    J3b_1x4.java=CheckedDataOutput
 */
public class J3b_1 {
    public static void main (String[] args) throws IOException {
        String okunanDosya = args.length > 0? args[0] : "J3b_1.java";
        String kopyaDosya = args.length > 1? args[1] : "kopya.java";

        J3b_1x1 giri�Kontrolu = new J3b_1x1(); // J3b_1x1=Adler32...
        J3b_1x1 ��k��Kontrolu = new J3b_1x1();
        J3b_1x3 giri� = null; // J3b_1x3=CheckedDataInput/KontrolluVeriGiri�i...
        J3b_1x4 ��k�� = null; // J3b_1x4=CheckedDataOutput/KontrolluVeri��k���...

        try {giri� = new J3b_1x3 (new DataInputStream (new FileInputStream (okunanDosya)), giri�Kontrolu);
            ��k�� = new J3b_1x4 (new DataOutputStream (new FileOutputStream (kopyaDosya)), ��k��Kontrolu);
        }catch (FileNotFoundException ist) {System.err.println ("Okunacak dosya bulunamad�: " + ist); System.exit (-1);
        }catch (IOException ist) {System.err.println ("Veri okuma/yazma hatas�: " + ist); System.exit (-1);
        } // try-catch-catch blo�u sonu...

        boolean DosyaSonu = false;
        while (!DosyaSonu) {// byte verileri giri� dosyas�ndan ��k�� dosyas�na aktar�r...
            try {int k = giri�.byteOku(); ��k��.yaz (k);
            }catch (EOFException ist) {DosyaSonu = true;
            } // try-catch blo�u sonu...
        } // while d�ng�s� sonu...

        System.out.println ("Veri okuma kontrol yek�n�: [" + giri�.kontrolyek�n�Al().de�erAl() + "]");
        System.out.println ("Veri yazma kontrol yek�n�: [" + ��k��.kontrolyek�n�Al().de�erAl() + "]");
    } // main(..) metodu sonu...
} // J3b_1 s�n�f� sonu...

/* ��kt�:
**  >java J3b_1  ** 
Veri okuma kontrol yek�n�: [-830172877]
Veri yazma kontrol yek�n�: [-830172877]
// kopya.java dosyas� da yarat�ld�...

**  >java J3b_1 J3b_1x2.java kopya2.java ** TEKRAR
Veri okuma kontrol yek�n�: [-256262460]
Veri yazma kontrol yek�n�: [-256262460]
// // kopya2.java dosyas� da yarat�ld�...
*/