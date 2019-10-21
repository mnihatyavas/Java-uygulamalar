// J6dq_1.java: FileList (DosyaListesi) �rne�i.

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class J6dq_1 {
    public static void main (String[] arg�man) {
        if (arg�man.length < 1) {System.err.println ("Bir metin dosya ad� girmelisiniz!"); System.exit (0);}

        File dosya = new File (arg�man[0]);
        List<String> liste = new ArrayList<String>();
        BufferedReader okuyucu = null;
        int sat�rSay�s� = 0;
        try {okuyucu = new BufferedReader (new FileReader (dosya));
            for (String sat�r = okuyucu.readLine(); sat�r != null; sat�r = okuyucu.readLine()) {
                liste.add (sat�r);
                sat�rSay�s�++;
            } // for d�ng�s� sonu...
        }catch (IOException ist) {
            System.err.format ("Okuma hatal� dosya: %s, ve hata: %s%n", dosya, ist);
            System.exit (1);
        }finally {
            if (okuyucu != null) {
                try {okuyucu.close();
                }catch (IOException ist) {}
            } // if karar� sonu..
        } // try-catch-finally blo�u sonu...

        Random rasgele = new Random();
        int tekrar = (int)(Math.random() * sat�rSay�s�);
        int sat�rNo = 0;
        System.out.println (dosya + " dosyas�n�n " + tekrar + " geli�ig�zel sat�r�n�n altalta d�k�m�==>");
        for (int i = 0; i < tekrar; i++) {
            sat�rNo = rasgele.nextInt (sat�rSay�s�);
          System.out.format ("%d->%d: %s%n", i+1, sat�rNo, liste.get (sat�rNo));
        } // for d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J6dq_1 s�n�f� sonu...

/* ��kt�:
**  >java J6dq_1  **
Bir metin dosya ad� girmelisiniz!

**  >java J6dq_1 J6dq_1.java  ** TEKRAR
J6dq_1.java dosyas�n�n 7 geli�ig�zel sat�r�n�n altalta d�k�m�==>
1->11: public class J6dq_1 {
2->14:
3->28:             if (okuyucu != null) {
4->5:
5->41:         } // for d�ng�s� sonu...
6->25:             System.err.format ("Okuma hatal� dosya: %s, ve hata: %s%n", d
osya, ist);
7->33:
*/