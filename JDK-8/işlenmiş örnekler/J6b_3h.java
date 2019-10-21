// J6b_3h.java: NameSort2 (�simS�ralamas�2) �rne�i.

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

// Gereken dosya: J6b_3hx.java
public class J6b_3h {
    public static void main(String[] args) {
        J6b_3hx isimDizisi[] = {// J6b_3hx(..) kurucusunu �a��r�r...
            new J6b_3hx ("Hatice", "Ka�ar"),
            new J6b_3hx ("Nurilh�da", "K���kbay"),
            new J6b_3hx ("Nihat", "Yava�"),
            new J6b_3hx ("Canan", "Candan"),
            new J6b_3hx ("Mahmut", "Yava�"),
            new J6b_3hx ("Hatice", "Yava�"),
            new J6b_3hx ("�mer", "Candan"),
            new J6b_3hx ("Atilla", "G�kt�rk"),
            new J6b_3hx ("M.Nihat", "Yava�"),
            new J6b_3hx ("Fatih", "�zbay"),
            new J6b_3hx ("Hatice Yava�", "Ka�ar")
        };

        List<J6b_3hx> isimListesi = Arrays.asList(isimDizisi);
        Collections.sort (isimListesi);
        System.out.println ("�nce soyad'a sonra da ad'a g�re s�ral� '" + isimListesi.size() + "' adet yanyana liste d�k�m�==>\n" + isimListesi);
    } // main(..) metodu sonu...
} // J6b_3h s�n�f� sonu...

/* ��kt�:
**  >java J6b_3h  **
�nce soyad'a sonra da ad'a g�re s�ral�'11' adet yanyana liste d�k�m�==>
[Canan Candan, �mer Candan, Atilla G�kt�rk, Hatice Ka�ar,
Hatice Yava� Ka�ar, Nurilh�da K���kbay, Hatice Yava�, M.Nihat Yava�,
Mahmut Yava�, Nihat Yava�, Fatih �zbay]
*/