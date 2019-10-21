// J6b_3c.java: Deal (Da��t) �rne�i.

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class J6b_3c {
    public static void main (String[] arg�man) {
        if (arg�man.length < 2) {System.err.println ("2 arg�man [Oyuncu say�s� ve da��t�lacak vanti say�s�] gerekiyor!"); System.exit (0);}

        int oyuncuSay�s� = 0, herbindekiVantiSay�s� = 0;
        try {oyuncuSay�s� = Math.abs (Integer.parseInt (arg�man[0]));}catch (Exception ist){oyuncuSay�s� = 10;}
        try {herbindekiVantiSay�s� = Math.abs (Integer.parseInt (arg�man[1]));}catch (Exception ist){herbindekiVantiSay�s� = 5;}
        if (oyuncuSay�s� < 2) oyuncuSay�s� = 2;
        if (herbindekiVantiSay�s� < 1) herbindekiVantiSay�s� = 1;
        if ((herbindekiVantiSay�s� * oyuncuSay�s�) > 52) {System.err.println ("52'lik desteden " + (herbindekiVantiSay�s� * oyuncuSay�s�) + " kart da��tamazs�n�z!"); System.exit (0);}

        // 52'lik normal bir vanti destesi haz�rlayal�m...
        String[] tak�m = new String[] {"Sinek", "Kupa", "Ma�a", "Karo"};
        String[] say� = new String[] {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Vale", "K�z", "Papaz"};

        List<String> deste = new ArrayList<String>();
        for (int i = 0; i < tak�m.length; i++) for (int j = 0; j < say�.length; j++) deste.add (tak�m[i] + "-" + say�[j]);

        // Desteyi kar�yoruz...
        Collections.shuffle (deste);

        // Kartlar� da��t�yoruz....
        for (int i=0; i < oyuncuSay�s�; i++) System.out.println ((i+1) + "." + desteyiDa��t (deste, herbindekiVantiSay�s�));
    } // main(..) metodu sonu...

    public static <E> List<E> desteyiDa��t (List<E> deste, int n) {
        List<E> altListe = deste.subList (deste.size() - n, deste.size());
        List<E> oyuncununElindekiler = new ArrayList<E> (altListe);
        altListe.clear(); // Alt listeyi temizleyelim...
        return oyuncununElindekiler;
    } // desteyiDa��t(..) metodu sonu...
} // J6b_3c s�n�f� sonu...

/* ��kt�:
**  >java J6b_3c 13 4  **
1.[Kupa-Papaz, Sinek-4, Kupa-7, Ma�a-10]
2.[Karo-9, Sinek-K�z, Karo-5, Karo-3]
3.[Ma�a-Papaz, Ma�a-5, Karo-As, Sinek-3]
4.[Sinek-7, Kupa-5, Kupa-10, Kupa-K�z]
5.[Karo-Papaz, Kupa-3, Ma�a-9, Sinek-Papaz]
6.[Kupa-9, Karo-2, Sinek-9, Kupa-2]
7.[Sinek-6, Sinek-8, Ma�a-6, Karo-6]
8.[Ma�a-2, Sinek-5, Karo-Vale, Ma�a-Vale]
9.[Kupa-Vale, Sinek-Vale, Ma�a-7, Karo-4]
10.[Ma�a-As, Ma�a-K�z, Ma�a-8, Kupa-8]
11.[Sinek-10, Kupa-4, Karo-8, Ma�a-3]
12.[Kupa-6, Kupa-As, Ma�a-4, Sinek-2]
13.[Sinek-As, Karo-K�z, Karo-7, Karo-10]

**  >java J6b_3c -13 5  ** TEKRAR
52'lik desteden 65 kart da��tamazs�n�z!
*/