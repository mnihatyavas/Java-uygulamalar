// J6b_3g.java: NameSort (AdS�ralama) �rne�i.

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class J6b_3g {
    public static void main (String[] args) {
        List<String> isimListesi = new ArrayList<String>();

        isimListesi.add ("Hatice Ka�ar");
        isimListesi.add ("Nurilh�da K���kbay");
        isimListesi.add ("Nihat Yava�");
        isimListesi.add ("Canan Candan");
        isimListesi.add ("Mahmut Yava�");
        isimListesi.add ("Hatice Yava�");
        isimListesi.add ("�mer Candan");
        isimListesi.add ("Atilla G�kt�rk");
        isimListesi.add ("M.Nihat Yava�");
        isimListesi.add ("Fatih �zbay");
        isimListesi.add ("Hatice Yava� Ka�ar");

        Collections.sort (isimListesi);
        System.out.println ("Ad'a g�re artan s�ral� '" + isimListesi.size() + "' adet isim listesi yanyana==>\n" + isimListesi);
    } // main(..) metodu sonu...
} // J6b_3g s�n�f� sonu...

/* ��kt�:
**  >java J6b_3g  **
Ad'a g�re artan s�ral� '11' adet isim listesi yanyana==>
[Atilla G�kt�rk, Canan Candan, Fatih �zbay, Hatice Ka�ar, Hatice Yava�,
Hatice Yava� Ka�ar, M.Nihat Yava�, Mahmut Yava�, Nihat Yava�,
Nurilh�da K���kbay, �mer Candan]
*/