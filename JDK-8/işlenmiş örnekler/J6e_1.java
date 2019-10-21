// J6e_1.java: Anagrams3 (Anagramlar3) �rne�i.

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

// J6b_3a/Anagram2.java benzeridir; ancak burada grupland�rma azalan s�ral�d�r...
public class J6e_1 {
    public static void main (String[] arg�manlar) {
        if (arg�manlar.length < 2) {System.err.println ("2 arg�man (Metin dosya ad� ve enk���k grupland�rma) girilmelidir!"); System.exit (0);}

        int asgariGrupland�rmaEbat� = Math.abs (Integer.parseInt (arg�manlar[1]));

        // Metin dosya i�eriklerini kelime-kelime okuyup k�ymal� harita listesine ekleyelim...
        Map<String, List<String>> k�ymal�Harita = new HashMap<String, List<String>>();
        try {Scanner dosyay�T�mdenOku = new Scanner (new File (arg�manlar[0]));
            while (dosyay�T�mdenOku.hasNext()) {
                String kelime = dosyay�T�mdenOku.next();
                String s�ral�Kelime = kelimeHarfleriniS�rala (kelime);
                List<String> liste = k�ymal�Harita.get (s�ral�Kelime);
                if (liste == null) k�ymal�Harita.put (s�ral�Kelime, liste = new ArrayList<String>());
                liste.add (kelime);
            } // while d�ng�s� sonu...
        }catch (IOException ist) {System.err.println (ist); System.exit (1);
        } // try-catch blo�u sonu...

        // Asgari gruplama e�i�i �st� kelime listelerini ebatlar�yla azalan s�ralay�p d�kelim...
        List<List<String>> s�ral�D�k�mListesi = new ArrayList<List<String>>();
        for (List<String> lst : k�ymal�Harita.values())
            if (lst.size() >= asgariGrupland�rmaEbat�) s�ral�D�k�mListesi.add (lst);
        Collections.sort (s�ral�D�k�mListesi, new Comparator<List<String>>() {
            public int compare (List<String> l1, List<String> l2) {return l2.size() - l1.size();}
        }); // Col.. ifadesi sonu...
        for (List<String> lst : s�ral�D�k�mListesi ) System.out.println (lst.size() + ": " + lst);
    } // main(..) metodu sonu...

    private static String kelimeHarfleriniS�rala (String dizge) {
        char[] harfDizisi = dizge.toCharArray();
        Arrays.sort (harfDizisi);
        return new String (harfDizisi);
    } // kelimeHarfleriniS�rala(..) metodu sonu...
} // J6e_1 s�n�f� sonu...

/* ��kt�:
**  >java J6e_1  **
2 arg�man (Metin dosya ad� ve enk���k grupland�rma) girilmelidir!

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