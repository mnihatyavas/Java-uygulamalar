// J2g_3.java: MultiplicationTable (�arp�mTablosu) �rne�i.

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

        SortedMap<Integer, List<Integer>> s�ral�Harita = �arp�mTablosu (ilk, son);

        System.out.println ("(" + ilk + "->" + son + ") aras� �ARPIM TABLOSU\n==============================");
        �arp�mTablosunuYaz (s�ral�Harita);
        System.out.println ("==============================");
    } // main(..) metodu sonu...

    public static SortedMap<Integer, List<Integer>> �arp�mTablosu (int ilk, int son) {
        if (ilk < 0 || son < 0 || ilk > son) return null;

        SortedMap<Integer, List<Integer>> sm = new TreeMap<>();

        for (int i = ilk; i <= son; ++i) {
            List<Integer> liste = new ArrayList<>();
            for (int j = ilk; j <= son; ++j) liste.add (i * j);
            sm.put (i, liste);
        } // for-i d�ng�s� sonu...
        return sm;
    } // �arp�mTablosu(..) metodu sonu...

    public static void �arp�mTablosunuYaz (SortedMap<Integer, List<Integer>> sh) {
        Set<Integer> k�me = sh.keySet();
        Integer[] tamsay�lar = k�me.toArray (new Integer[0]);
        Integer sonSay� = tamsay�lar[tamsay�lar.length - 1];
        int geni�lik = Integer.toString (sonSay� * sonSay�).length() + 1; // enb�y�k (9*9)=81==>geni�lik:3...
        String bi�im = "%" + geni�lik + "d";

        System.out.printf ("%" + geni�lik + "s", "");
        for (Integer k : k�me) System.out.printf (bi�im, k);
        System.out.println();

        for (Integer k : k�me) {
            System.out.printf (bi�im, k);
            List<Integer> liste = sh.get (k);
            for (Integer i : liste) System.out.printf (bi�im, i);
            System.out.println();
        } // for-k d�ng�s� sonu...
    } // �arp�mTablosunuYaz(..) metodu sonu...
} // J2g_3 s�n�f� sonu...

/* ��kt�:
**  >java J2g_3  **
(1->9) aras� �ARPIM TABLOSU
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
(5->15) aras� �ARPIM TABLOSU
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