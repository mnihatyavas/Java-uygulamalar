// J6b_3d.java: FindDups (BulBenzerlerini) örneði.

import java.util.Set;
import java.util.HashSet;

public class J6b_3d {
    public static void main (String[] argümanlar) {
        Set<String> kýymalýKüme = new HashSet<String>(); // (Anlaþýlmaz karýþtýrma formüllü) tikel küme listesi...
        for (String a : argümanlar) kýymalýKüme.add (a);
        System.out.println ("Girdiðiniz " + argümanlar.length + " adet argümanlardan " + kýymalýKüme.size() + " adet benzersizleri==>\n" + kýymalýKüme);
    } // main(..) metodu sonu...
} // J6b_3d sýnýfý sonu...

/* Çýktý:
**  >java J6b_3d nihat yavaþ mahmut yeþilyurt malatya 17 Nisan 1957 1957 yavaþ nisan  **
Girdiðiniz 11 adet argümanlardan 9 adet benzersizleri==>
[mahmut, yeþilyurt, 17, Nisan, yavaþ, nisan, 1957, malatya, nihat]

**  >java J6b_3d  ** TEKRAR
Girdiðiniz 0 adet argümanlardan 0 adet benzersizleri==>
[]
*/