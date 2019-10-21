// J6dq_1.java: FileList (DosyaListesi) örneði.

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class J6dq_1 {
    public static void main (String[] argüman) {
        if (argüman.length < 1) {System.err.println ("Bir metin dosya adý girmelisiniz!"); System.exit (0);}

        File dosya = new File (argüman[0]);
        List<String> liste = new ArrayList<String>();
        BufferedReader okuyucu = null;
        int satýrSayýsý = 0;
        try {okuyucu = new BufferedReader (new FileReader (dosya));
            for (String satýr = okuyucu.readLine(); satýr != null; satýr = okuyucu.readLine()) {
                liste.add (satýr);
                satýrSayýsý++;
            } // for döngüsü sonu...
        }catch (IOException ist) {
            System.err.format ("Okuma hatalý dosya: %s, ve hata: %s%n", dosya, ist);
            System.exit (1);
        }finally {
            if (okuyucu != null) {
                try {okuyucu.close();
                }catch (IOException ist) {}
            } // if kararý sonu..
        } // try-catch-finally bloðu sonu...

        Random rasgele = new Random();
        int tekrar = (int)(Math.random() * satýrSayýsý);
        int satýrNo = 0;
        System.out.println (dosya + " dosyasýnýn " + tekrar + " geliþigüzel satýrýnýn altalta dökümü==>");
        for (int i = 0; i < tekrar; i++) {
            satýrNo = rasgele.nextInt (satýrSayýsý);
          System.out.format ("%d->%d: %s%n", i+1, satýrNo, liste.get (satýrNo));
        } // for döngüsü sonu...
    } // main(..) metodu sonu...
} // J6dq_1 sýnýfý sonu...

/* Çýktý:
**  >java J6dq_1  **
Bir metin dosya adý girmelisiniz!

**  >java J6dq_1 J6dq_1.java  ** TEKRAR
J6dq_1.java dosyasýnýn 7 geliþigüzel satýrýnýn altalta dökümü==>
1->11: public class J6dq_1 {
2->14:
3->28:             if (okuyucu != null) {
4->5:
5->41:         } // for döngüsü sonu...
6->25:             System.err.format ("Okuma hatalý dosya: %s, ve hata: %s%n", d
osya, ist);
7->33:
*/