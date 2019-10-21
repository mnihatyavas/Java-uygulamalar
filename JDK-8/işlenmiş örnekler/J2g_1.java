// J2g_1.java: BoxDemo (KutuG�sterimi) �rne�i.

// Gereken dosya: J2g_1x.java=Box
public class J2g_1 {
    public static <U> void kutuEkle (U tamsay�Nesne, java.util.List<J2g_1x<U>> kutular) {
        J2g_1x<U> kutu = new J2g_1x<>(); // J2g_1x=Box/Kutu...
        kutu.koy (tamsay�Nesne);
        kutular.add (kutu);
    } // kutuEkle(..) metodu sonu...

    public static <U> void kutuYazd�r (java.util.List<J2g_1x<U>> kutular) {
        int saya� = 0;

        System.out.println ("Kutu no ve i�erdikleri nesnel tamsay�lar listesi==>");
        for (J2g_1x<U> kutu: kutular) {
            U i�erik = kutu.al();
            System.out.println ("Kutu #" + saya� + " i�eri�i: [" + i�erik.toString() + "]");
            saya�++;
        } // for d�ng�s� sonu...
    } // kutuYazd�r(..) metodu sonu...

    public static void main (String[] args) {
        java.util.ArrayList<J2g_1x<Integer>> tamsay�KutularListesi = new java.util.ArrayList<>();

        J2g_1.kutuEkle (Integer.valueOf (30), tamsay�KutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (20), tamsay�KutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (300), tamsay�KutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (-30), tamsay�KutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (3), tamsay�KutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (0), tamsay�KutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (100), tamsay�KutularListesi);

        J2g_1.kutuYazd�r (tamsay�KutularListesi);
    } // main(..) metodu sonu...
} // J2g_1 s�n�f� sonu...

/* ��kt�:
**  >java J2g_1  **
Kutu no ve i�erdikleri nesnel tamsay�lar listesi==>
Kutu #0 i�eri�i: [30]
Kutu #1 i�eri�i: [20]
Kutu #2 i�eri�i: [300]
Kutu #3 i�eri�i: [-30]
Kutu #4 i�eri�i: [3]
Kutu #5 i�eri�i: [0]
Kutu #6 i�eri�i: [100]
*/