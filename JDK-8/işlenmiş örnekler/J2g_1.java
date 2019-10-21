// J2g_1.java: BoxDemo (KutuGösterimi) örneði.

// Gereken dosya: J2g_1x.java=Box
public class J2g_1 {
    public static <U> void kutuEkle (U tamsayýNesne, java.util.List<J2g_1x<U>> kutular) {
        J2g_1x<U> kutu = new J2g_1x<>(); // J2g_1x=Box/Kutu...
        kutu.koy (tamsayýNesne);
        kutular.add (kutu);
    } // kutuEkle(..) metodu sonu...

    public static <U> void kutuYazdýr (java.util.List<J2g_1x<U>> kutular) {
        int sayaç = 0;

        System.out.println ("Kutu no ve içerdikleri nesnel tamsayýlar listesi==>");
        for (J2g_1x<U> kutu: kutular) {
            U içerik = kutu.al();
            System.out.println ("Kutu #" + sayaç + " içeriði: [" + içerik.toString() + "]");
            sayaç++;
        } // for döngüsü sonu...
    } // kutuYazdýr(..) metodu sonu...

    public static void main (String[] args) {
        java.util.ArrayList<J2g_1x<Integer>> tamsayýKutularListesi = new java.util.ArrayList<>();

        J2g_1.kutuEkle (Integer.valueOf (30), tamsayýKutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (20), tamsayýKutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (300), tamsayýKutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (-30), tamsayýKutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (3), tamsayýKutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (0), tamsayýKutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (100), tamsayýKutularListesi);

        J2g_1.kutuYazdýr (tamsayýKutularListesi);
    } // main(..) metodu sonu...
} // J2g_1 sýnýfý sonu...

/* Çýktý:
**  >java J2g_1  **
Kutu no ve içerdikleri nesnel tamsayýlar listesi==>
Kutu #0 içeriði: [30]
Kutu #1 içeriði: [20]
Kutu #2 içeriði: [300]
Kutu #3 içeriði: [-30]
Kutu #4 içeriði: [3]
Kutu #5 içeriði: [0]
Kutu #6 içeriði: [100]
*/