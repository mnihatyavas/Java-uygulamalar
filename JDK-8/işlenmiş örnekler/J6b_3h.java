// J6b_3h.java: NameSort2 (ÝsimSýralamasý2) örneði.

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

// Gereken dosya: J6b_3hx.java
public class J6b_3h {
    public static void main(String[] args) {
        J6b_3hx isimDizisi[] = {// J6b_3hx(..) kurucusunu çaðýrýr...
            new J6b_3hx ("Hatice", "Kaçar"),
            new J6b_3hx ("Nurilhüda", "Küçükbay"),
            new J6b_3hx ("Nihat", "Yavaþ"),
            new J6b_3hx ("Canan", "Candan"),
            new J6b_3hx ("Mahmut", "Yavaþ"),
            new J6b_3hx ("Hatice", "Yavaþ"),
            new J6b_3hx ("Ömer", "Candan"),
            new J6b_3hx ("Atilla", "Göktürk"),
            new J6b_3hx ("M.Nihat", "Yavaþ"),
            new J6b_3hx ("Fatih", "Özbay"),
            new J6b_3hx ("Hatice Yavaþ", "Kaçar")
        };

        List<J6b_3hx> isimListesi = Arrays.asList(isimDizisi);
        Collections.sort (isimListesi);
        System.out.println ("Önce soyad'a sonra da ad'a göre sýralý '" + isimListesi.size() + "' adet yanyana liste dökümü==>\n" + isimListesi);
    } // main(..) metodu sonu...
} // J6b_3h sýnýfý sonu...

/* Çýktý:
**  >java J6b_3h  **
Önce soyad'a sonra da ad'a göre sýralý'11' adet yanyana liste dökümü==>
[Canan Candan, Ömer Candan, Atilla Göktürk, Hatice Kaçar,
Hatice Yavaþ Kaçar, Nurilhüda Küçükbay, Hatice Yavaþ, M.Nihat Yavaþ,
Mahmut Yavaþ, Nihat Yavaþ, Fatih Özbay]
*/