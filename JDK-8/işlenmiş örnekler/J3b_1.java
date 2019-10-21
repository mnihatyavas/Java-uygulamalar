// J3b_1.java: CheckedDIDemo (KontrolluVGGösterimi) örneði.

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

        J3b_1x1 giriþKontrolu = new J3b_1x1(); // J3b_1x1=Adler32...
        J3b_1x1 çýkýþKontrolu = new J3b_1x1();
        J3b_1x3 giriþ = null; // J3b_1x3=CheckedDataInput/KontrolluVeriGiriþi...
        J3b_1x4 çýkýþ = null; // J3b_1x4=CheckedDataOutput/KontrolluVeriÇýkýþý...

        try {giriþ = new J3b_1x3 (new DataInputStream (new FileInputStream (okunanDosya)), giriþKontrolu);
            çýkýþ = new J3b_1x4 (new DataOutputStream (new FileOutputStream (kopyaDosya)), çýkýþKontrolu);
        }catch (FileNotFoundException ist) {System.err.println ("Okunacak dosya bulunamadý: " + ist); System.exit (-1);
        }catch (IOException ist) {System.err.println ("Veri okuma/yazma hatasý: " + ist); System.exit (-1);
        } // try-catch-catch bloðu sonu...

        boolean DosyaSonu = false;
        while (!DosyaSonu) {// byte verileri giriþ dosyasýndan çýkýþ dosyasýna aktarýr...
            try {int k = giriþ.byteOku(); çýkýþ.yaz (k);
            }catch (EOFException ist) {DosyaSonu = true;
            } // try-catch bloðu sonu...
        } // while döngüsü sonu...

        System.out.println ("Veri okuma kontrol yekünü: [" + giriþ.kontrolyekünüAl().deðerAl() + "]");
        System.out.println ("Veri yazma kontrol yekünü: [" + çýkýþ.kontrolyekünüAl().deðerAl() + "]");
    } // main(..) metodu sonu...
} // J3b_1 sýnýfý sonu...

/* Çýktý:
**  >java J3b_1  ** 
Veri okuma kontrol yekünü: [-830172877]
Veri yazma kontrol yekünü: [-830172877]
// kopya.java dosyasý da yaratýldý...

**  >java J3b_1 J3b_1x2.java kopya2.java ** TEKRAR
Veri okuma kontrol yekünü: [-256262460]
Veri yazma kontrol yekünü: [-256262460]
// // kopya2.java dosyasý da yaratýldý...
*/