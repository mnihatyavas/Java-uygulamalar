// J2g_3.java: MultiplicationTable (ÇarpýmTablosu) örneði.

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class J2g_3 {
    public static void main (String[] args) {
        int ilk, son;

        try {ilk = Integer.valueOf (args[0]).intValue();}catch (Exception ist) {ilk = 1;}
        try {son = Integer.valueOf (args[1]).intValue();}catch (Exception ist) {son = 9;}

        SortedMap<Integer, List<Integer>> sýralýHarita = çarpýmTablosu (ilk, son);

        System.out.println ("(" + ilk + "->" + son + ") arasý ÇARPIM TABLOSU\n==============================");
        çarpýmTablosunuYaz (sýralýHarita);
        System.out.println ("==============================");
    } // main(..) metodu sonu...

    public static SortedMap<Integer, List<Integer>> çarpýmTablosu (int ilk, int son) {
        if (ilk < 0 || son < 0 || ilk > son) return null;

        SortedMap<Integer, List<Integer>> sm = new TreeMap<>();

        for (int i = ilk; i <= son; ++i) {
            List<Integer> liste = new ArrayList<>();
            for (int j = ilk; j <= son; ++j) liste.add (i * j);
            sm.put (i, liste);
        } // for-i döngüsü sonu...
        return sm;
    } // çarpýmTablosu(..) metodu sonu...

    public static void çarpýmTablosunuYaz (SortedMap<Integer, List<Integer>> sh) {
        Set<Integer> küme = sh.keySet();
        Integer[] tamsayýlar = küme.toArray (new Integer[0]);
        Integer sonSayý = tamsayýlar[tamsayýlar.length - 1];
        int geniþlik = Integer.toString (sonSayý * sonSayý).length() + 1; // enbüyük (9*9)=81==>geniþlik:3...
        String biçim = "%" + geniþlik + "d";

        System.out.printf ("%" + geniþlik + "s", "");
        for (Integer k : küme) System.out.printf (biçim, k);
        System.out.println();

        for (Integer k : küme) {
            System.out.printf (biçim, k);
            List<Integer> liste = sh.get (k);
            for (Integer i : liste) System.out.printf (biçim, i);
            System.out.println();
        } // for-k döngüsü sonu...
    } // çarpýmTablosunuYaz(..) metodu sonu...
} // J2g_3 sýnýfý sonu...

/* Çýktý:
**  >java J2g_3  **
(1->9) arasý ÇARPIM TABLOSU
==============================
     1  2  3  4  5  6  7  8  9
  1  1  2  3  4  5  6  7  8  9
  2  2  4  6  8 10 12 14 16 18
  3  3  6  9 12 15 18 21 24 27
  4  4  8 12 16 20 24 28 32 36
  5  5 10 15 20 25 30 35 40 45
  6  6 12 18 24 30 36 42 48 54
  7  7 14 21 28 35 42 49 56 63
  8  8 16 24 32 40 48 56 64 72
  9  9 18 27 36 45 54 63 72 81
==============================

**  >java J2g_3 5 15  ** TEKRAR
(5->15) arasý ÇARPIM TABLOSU
==============================
       5   6   7   8   9  10  11  12  13  14  15
   5  25  30  35  40  45  50  55  60  65  70  75
   6  30  36  42  48  54  60  66  72  78  84  90
   7  35  42  49  56  63  70  77  84  91  98 105
   8  40  48  56  64  72  80  88  96 104 112 120
   9  45  54  63  72  81  90  99 108 117 126 135
  10  50  60  70  80  90 100 110 120 130 140 150
  11  55  66  77  88  99 110 121 132 143 154 165
  12  60  72  84  96 108 120 132 144 156 168 180
  13  65  78  91 104 117 130 143 156 169 182 195
  14  70  84  98 112 126 140 154 168 182 196 210
  15  75  90 105 120 135 150 165 180 195 210 225
==============================
*/