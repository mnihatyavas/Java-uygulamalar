// J6b_3f.java: Freq (S�kl�k) �rne�i.

import java.util.Map; // Harita listesi...
import java.util.HashMap; // K�ymal� harita listesi...

public class J6b_3f {
    public static void main (String[] arg�manlar) {
        Map<String, Integer> k�ymal�Harita�iftliListesi = new HashMap<String, Integer>();

        // Komut iletisindeki herbir kelime ve tekrarlanma s�kl���n� dizge+tamsay� �ifti �eklinde listeler...
        for (String a : arg�manlar) {
            Integer s�kl�k = k�ymal�Harita�iftliListesi.get (a);
            k�ymal�Harita�iftliListesi.put (a, (s�kl�k == null) ? 1 : s�kl�k + 1);
        } // for d�ng�s� sonu...

        System.out.println (k�ymal�Harita�iftliListesi.size() + " farkl� kelime; ve herbir kelimenin tekrar say�s�==>");
        System.out.println (k�ymal�Harita�iftliListesi);
    } // main(..) metodu sonu...
} // J6b_3f s�n�f�n�n sonu...

/* ��kt�:
**  >java J6b_3f  **
0 farkl� kelime; ve herbir kelimenin tekrar say�s�==>
{}

**  >java J6b_3f TR mahmut nihat yava� Yava� mahmut ye�ilyurt malatya TR mahmut TR  ** TEKRAR
7 farkl� kelime; ve herbir kelimenin tekrar say�s�==>
{mahmut=3, ye�ilyurt=1, yava�=1, TR=3, malatya=1, nihat=1, Yava�=1}
*/