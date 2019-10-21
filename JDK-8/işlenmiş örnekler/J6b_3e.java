// J6b_3e.java: FindDups2 (BulAynýlarýný2) örneði.

import java.util.Set; // Küme listesi...
import java.util.HashSet; // Kýymalý küme listesi...

public class J6b_3e {
    public static void main (String[] argümanlar) {
        Set<String> benzersizler = new HashSet<String>();
        Set<String> benzerler = new HashSet<String>();

        // Benzersizlere önce tüm argümanlar, benzerlere de sadece aynýlar eklenir...
        for (String a : argümanlar)
            if (!benzersizler.add (a)) benzerler.add (a);

        // Sonra da benzersizlerdeki tüm argümanlarýn benzerlerle çakýþanlarý silinir...
        benzersizler.removeAll (benzerler);

        System.out.println ("Benzersiz kelimelerin listesi: " + benzersizler);
        System.out.println ("Ayný kelimelerin listesi: " + benzerler);
    } // main(..) metodu sonu...
} // J6b_3e sýnýfý sonu...

/* Çýktý:
**  >java J6b_3e  **
Benzersiz kelimelerin listesi: []
Ayný kelimelerin listesi: []

**  >java J6b_3e mahmut nihat yavaþ YavaÞ mahmut yeþilyurt malatya TR mahmut ** TEKRAR
Benzersiz kelimelerin listesi: [yeþilyurt, yavaþ, malatya, TR, nihat, YavaÞ]
Ayný kelimelerin listesi: [mahmut]
*/