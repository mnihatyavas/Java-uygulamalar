// J6b_3f.java: Freq (Sıklık) örneği.

import java.util.Map; // Harita listesi...
import java.util.HashMap; // Kıymalı harita listesi...

public class J6b_3f {
    public static void main (String[] argümanlar) {
        Map<String, Integer> kıymalıHaritaÇiftliListesi = new HashMap<String, Integer>();

        // Komut iletisindeki herbir kelime ve tekrarlanma sıklığını dizge+tamsayı çifti şeklinde listeler...
        for (String a : argümanlar) {
            Integer sıklık = kıymalıHaritaÇiftliListesi.get (a);
            kıymalıHaritaÇiftliListesi.put (a, (sıklık == null) ? 1 : sıklık + 1);
        } // for döngüsü sonu...

        System.out.println (kıymalıHaritaÇiftliListesi.size() + " farklı kelime; ve herbir kelimenin tekrar sayısı==>");
        System.out.println (kıymalıHaritaÇiftliListesi);
    } // main(..) metodu sonu...
} // J6b_3f sınıfının sonu...

/* Çıktı:
**  >java J6b_3f  **
0 farklı kelime; ve herbir kelimenin tekrar sayısı==>
{}

**  >java J6b_3f TR mahmut nihat yavaş YavaŞ mahmut yeşilyurt malatya TR mahmut TR  ** TEKRAR
7 farklı kelime; ve herbir kelimenin tekrar sayısı==>
{mahmut=3, yeşilyurt=1, yavaş=1, TR=3, malatya=1, nihat=1, YavaŞ=1}
*/