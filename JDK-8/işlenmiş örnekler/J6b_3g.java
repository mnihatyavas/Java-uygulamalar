// J6b_3g.java: NameSort (AdSýralama) örneði.

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class J6b_3g {
    public static void main (String[] args) {
        List<String> isimListesi = new ArrayList<String>();

        isimListesi.add ("Hatice Kaçar");
        isimListesi.add ("Nurilhüda Küçükbay");
        isimListesi.add ("Nihat Yavaþ");
        isimListesi.add ("Canan Candan");
        isimListesi.add ("Mahmut Yavaþ");
        isimListesi.add ("Hatice Yavaþ");
        isimListesi.add ("Ömer Candan");
        isimListesi.add ("Atilla Göktürk");
        isimListesi.add ("M.Nihat Yavaþ");
        isimListesi.add ("Fatih Özbay");
        isimListesi.add ("Hatice Yavaþ Kaçar");

        Collections.sort (isimListesi);
        System.out.println ("Ad'a göre artan sýralý '" + isimListesi.size() + "' adet isim listesi yanyana==>\n" + isimListesi);
    } // main(..) metodu sonu...
} // J6b_3g sýnýfý sonu...

/* Çýktý:
**  >java J6b_3g  **
Ad'a göre artan sýralý '11' adet isim listesi yanyana==>
[Atilla Göktürk, Canan Candan, Fatih Özbay, Hatice Kaçar, Hatice Yavaþ,
Hatice Yavaþ Kaçar, M.Nihat Yavaþ, Mahmut Yavaþ, Nihat Yavaþ,
Nurilhüda Küçükbay, Ömer Candan]
*/