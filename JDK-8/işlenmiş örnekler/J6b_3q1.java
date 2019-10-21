// J6b_3q1.java: ListTrim (ListeKırpma) örneği.

import java.util.List;
import java.util.ListIterator;
import java.util.Arrays;

public class J6b_3q1 {
    static void listeBoşluklarınıKırp (List<String> lst) {
        for (ListIterator<String> listeTarayıcısı = lst.listIterator(); listeTarayıcısı.hasNext();)
            listeTarayıcısı.set (listeTarayıcısı.next().trim()); // trim() hazır metoduyla ön-arka boşlukları kırpma...
    } // listeBoşluklarınıKırp(..) metodu sonu...

    public static void main(String[] args) {
        List<String> liste = Arrays.asList ("  Kırmızı   ", " Beyaz    ", "  Mavi ", "Yeşil", "Sarı ", "     Siyah     ");
        System.out.println ("Listenin orijinali (yanyana): " + liste);

        listeBoşluklarınıKırp (liste);
        System.out.println ("\nListenin boşluklarından arındırılmışı (yanyana): " + liste);
        System.out.println ("\nListenin boşluklarından arındırılmışı (\" arası ve altalta)==>");
        for (String dizge : liste) System.out.format ("\"%s\"%n", dizge);
    } // main(..) metodu sonu...
} // J6b_3q1 sınıfı sonu...

/* Çıktı:
**  >java J6b_3q1  **
Listenin orijinali (yanyana): [  Kırmızı   ,  Beyaz    ,   Mavi , Yeşil, Sarı ,     Siyah     ]

Listenin boşluklarından arındırılmışı (yanyana): [Kırmızı, Beyaz, Mavi, Yeşil, Sarı, Siyah]

Listenin boşluklarından arındırılmışı (" arası ve altalta)==>
"Kırmızı"
"Beyaz"
"Mavi"
"Yeşil"
"Sarı"
"Siyah"
*/