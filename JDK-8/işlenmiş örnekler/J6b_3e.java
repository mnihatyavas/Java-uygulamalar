// J6b_3e.java: FindDups2 (BulAyn�lar�n�2) �rne�i.

import java.util.Set; // K�me listesi...
import java.util.HashSet; // K�ymal� k�me listesi...

public class J6b_3e {
    public static void main (String[] arg�manlar) {
        Set<String> benzersizler = new HashSet<String>();
        Set<String> benzerler = new HashSet<String>();

        // Benzersizlere �nce t�m arg�manlar, benzerlere de sadece ayn�lar eklenir...
        for (String a : arg�manlar)
            if (!benzersizler.add (a)) benzerler.add (a);

        // Sonra da benzersizlerdeki t�m arg�manlar�n benzerlerle �ak��anlar� silinir...
        benzersizler.removeAll (benzerler);

        System.out.println ("Benzersiz kelimelerin listesi: " + benzersizler);
        System.out.println ("Ayn� kelimelerin listesi: " + benzerler);
    } // main(..) metodu sonu...
} // J6b_3e s�n�f� sonu...

/* ��kt�:
**  >java J6b_3e  **
Benzersiz kelimelerin listesi: []
Ayn� kelimelerin listesi: []

**  >java J6b_3e mahmut nihat yava� Yava� mahmut ye�ilyurt malatya TR mahmut ** TEKRAR
Benzersiz kelimelerin listesi: [ye�ilyurt, yava�, malatya, TR, nihat, Yava�]
Ayn� kelimelerin listesi: [mahmut]
*/