// J6b_3d.java: FindDups (BulBenzerlerini) �rne�i.

import java.util.Set;
import java.util.HashSet;

public class J6b_3d {
    public static void main (String[] arg�manlar) {
        Set<String> k�ymal�K�me = new HashSet<String>(); // (Anla��lmaz kar��t�rma form�ll�) tikel k�me listesi...
        for (String a : arg�manlar) k�ymal�K�me.add (a);
        System.out.println ("Girdi�iniz " + arg�manlar.length + " adet arg�manlardan " + k�ymal�K�me.size() + " adet benzersizleri==>\n" + k�ymal�K�me);
    } // main(..) metodu sonu...
} // J6b_3d s�n�f� sonu...

/* ��kt�:
**  >java J6b_3d nihat yava� mahmut ye�ilyurt malatya 17 Nisan 1957 1957 yava� nisan  **
Girdi�iniz 11 adet arg�manlardan 9 adet benzersizleri==>
[mahmut, ye�ilyurt, 17, Nisan, yava�, nisan, 1957, malatya, nihat]

**  >java J6b_3d  ** TEKRAR
Girdi�iniz 0 adet arg�manlardan 0 adet benzersizleri==>
[]
*/