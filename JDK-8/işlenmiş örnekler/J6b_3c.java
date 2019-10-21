// J6b_3c.java: Deal (Daðýt) örneði.

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class J6b_3c {
    public static void main (String[] argüman) {
        if (argüman.length < 2) {System.err.println ("2 argüman [Oyuncu sayýsý ve daðýtýlacak vanti sayýsý] gerekiyor!"); System.exit (0);}

        int oyuncuSayýsý = 0, herbindekiVantiSayýsý = 0;
        try {oyuncuSayýsý = Math.abs (Integer.parseInt (argüman[0]));}catch (Exception ist){oyuncuSayýsý = 10;}
        try {herbindekiVantiSayýsý = Math.abs (Integer.parseInt (argüman[1]));}catch (Exception ist){herbindekiVantiSayýsý = 5;}
        if (oyuncuSayýsý < 2) oyuncuSayýsý = 2;
        if (herbindekiVantiSayýsý < 1) herbindekiVantiSayýsý = 1;
        if ((herbindekiVantiSayýsý * oyuncuSayýsý) > 52) {System.err.println ("52'lik desteden " + (herbindekiVantiSayýsý * oyuncuSayýsý) + " kart daðýtamazsýnýz!"); System.exit (0);}

        // 52'lik normal bir vanti destesi hazýrlayalým...
        String[] takým = new String[] {"Sinek", "Kupa", "Maça", "Karo"};
        String[] sayý = new String[] {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Vale", "Kýz", "Papaz"};

        List<String> deste = new ArrayList<String>();
        for (int i = 0; i < takým.length; i++) for (int j = 0; j < sayý.length; j++) deste.add (takým[i] + "-" + sayý[j]);

        // Desteyi karýyoruz...
        Collections.shuffle (deste);

        // Kartlarý daðýtýyoruz....
        for (int i=0; i < oyuncuSayýsý; i++) System.out.println ((i+1) + "." + desteyiDaðýt (deste, herbindekiVantiSayýsý));
    } // main(..) metodu sonu...

    public static <E> List<E> desteyiDaðýt (List<E> deste, int n) {
        List<E> altListe = deste.subList (deste.size() - n, deste.size());
        List<E> oyuncununElindekiler = new ArrayList<E> (altListe);
        altListe.clear(); // Alt listeyi temizleyelim...
        return oyuncununElindekiler;
    } // desteyiDaðýt(..) metodu sonu...
} // J6b_3c sýnýfý sonu...

/* Çýktý:
**  >java J6b_3c 13 4  **
1.[Kupa-Papaz, Sinek-4, Kupa-7, Maça-10]
2.[Karo-9, Sinek-Kýz, Karo-5, Karo-3]
3.[Maça-Papaz, Maça-5, Karo-As, Sinek-3]
4.[Sinek-7, Kupa-5, Kupa-10, Kupa-Kýz]
5.[Karo-Papaz, Kupa-3, Maça-9, Sinek-Papaz]
6.[Kupa-9, Karo-2, Sinek-9, Kupa-2]
7.[Sinek-6, Sinek-8, Maça-6, Karo-6]
8.[Maça-2, Sinek-5, Karo-Vale, Maça-Vale]
9.[Kupa-Vale, Sinek-Vale, Maça-7, Karo-4]
10.[Maça-As, Maça-Kýz, Maça-8, Kupa-8]
11.[Sinek-10, Kupa-4, Karo-8, Maça-3]
12.[Kupa-6, Kupa-As, Maça-4, Sinek-2]
13.[Sinek-As, Karo-Kýz, Karo-7, Karo-10]

**  >java J6b_3c -13 5  ** TEKRAR
52'lik desteden 65 kart daðýtamazsýnýz!
*/