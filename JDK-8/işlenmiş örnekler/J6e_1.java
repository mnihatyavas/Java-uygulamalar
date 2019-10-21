// J6e_1.java: Anagrams3 (Anagramlar3) örneği.

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;

// J6b_3a/Anagram2.java benzeridir; ancak burada gruplandırma azalan sıralıdır...
public class J6e_1 {
    public static void main (String[] argümanlar) {
        if (argümanlar.length < 2) {System.err.println ("2 argüman (Metin dosya adı ve enküçük gruplandırma) girilmelidir!"); System.exit (0);}

        int asgariGruplandırmaEbatı = Math.abs (Integer.parseInt (argümanlar[1]));

        // Metin dosya içeriklerini kelime-kelime okuyup kıymalı harita listesine ekleyelim...
        Map<String, List<String>> kıymalıHarita = new HashMap<String, List<String>>();
        try {Scanner dosyayıTümdenOku = new Scanner (new File (argümanlar[0]));
            while (dosyayıTümdenOku.hasNext()) {
                String kelime = dosyayıTümdenOku.next();
                String sıralıKelime = kelimeHarfleriniSırala (kelime);
                List<String> liste = kıymalıHarita.get (sıralıKelime);
                if (liste == null) kıymalıHarita.put (sıralıKelime, liste = new ArrayList<String>());
                liste.add (kelime);
            } // while döngüsü sonu...
        }catch (IOException ist) {System.err.println (ist); System.exit (1);
        } // try-catch bloğu sonu...

        // Asgari gruplama eşiği üstü kelime listelerini ebatlarıyla azalan sıralayıp dökelim...
        List<List<String>> sıralıDökümListesi = new ArrayList<List<String>>();
        for (List<String> lst : kıymalıHarita.values())
            if (lst.size() >= asgariGruplandırmaEbatı) sıralıDökümListesi.add (lst);
        Collections.sort (sıralıDökümListesi, new Comparator<List<String>>() {
            public int compare (List<String> l1, List<String> l2) {return l2.size() - l1.size();}
        }); // Col.. ifadesi sonu...
        for (List<String> lst : sıralıDökümListesi ) System.out.println (lst.size() + ": " + lst);
    } // main(..) metodu sonu...

    private static String kelimeHarfleriniSırala (String dizge) {
        char[] harfDizisi = dizge.toCharArray();
        Arrays.sort (harfDizisi);
        return new String (harfDizisi);
    } // kelimeHarfleriniSırala(..) metodu sonu...
} // J6e_1 sınıfı sonu...

/* Çıktı:
**  >java J6e_1  **
2 argüman (Metin dosya adı ve enküçük gruplandırma) girilmelidir!

**  >java J6e_1 J6e_1.java 4  ** TEKRAR
10: [import, import, import, import, import, import, import, import, import, import]
10: [//, //, //, //, //, //, //, //, //, //]
9: [=, =, =, =, =, =, =, =, =]
6: [sonu..., sonu..., sonu..., sonu..., sonu..., sonu...]
6: [new, new, new, new, new, new]
5: [{, {, {, {, {]
5: [}, }, }, }, }]
4: [String, String, String, String]

**  >java J6e_1 J6e_1.java 11  ** TEKRAR
*/