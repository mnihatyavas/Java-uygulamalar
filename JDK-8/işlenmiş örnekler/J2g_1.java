// J2g_1.java: BoxDemo (KutuGösterimi) örneği.

// Gereken dosya: J2g_1x.java=Box
public class J2g_1 {
    public static <U> void kutuEkle (U tamsayıNesne, java.util.List<J2g_1x<U>> kutular) {
        J2g_1x<U> kutu = new J2g_1x<>(); // J2g_1x=Box/Kutu...
        kutu.koy (tamsayıNesne);
        kutular.add (kutu);
    } // kutuEkle(..) metodu sonu...

    public static <U> void kutuYazdır (java.util.List<J2g_1x<U>> kutular) {
        int sayaç = 0;

        System.out.println ("Kutu no ve içerdikleri nesnel tamsayılar listesi==>");
        for (J2g_1x<U> kutu: kutular) {
            U içerik = kutu.al();
            System.out.println ("Kutu #" + sayaç + " içeriği: [" + içerik.toString() + "]");
            sayaç++;
        } // for döngüsü sonu...
    } // kutuYazdır(..) metodu sonu...

    public static void main (String[] args) {
        java.util.ArrayList<J2g_1x<Integer>> tamsayıKutularListesi = new java.util.ArrayList<>();

        J2g_1.kutuEkle (Integer.valueOf (30), tamsayıKutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (20), tamsayıKutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (300), tamsayıKutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (-30), tamsayıKutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (3), tamsayıKutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (0), tamsayıKutularListesi);
        J2g_1.kutuEkle (Integer.valueOf (100), tamsayıKutularListesi);

        J2g_1.kutuYazdır (tamsayıKutularListesi);
    } // main(..) metodu sonu...
} // J2g_1 sınıfı sonu...

/* Çıktı:
**  >java J2g_1  **
Kutu no ve içerdikleri nesnel tamsayılar listesi==>
Kutu #0 içeriği: [30]
Kutu #1 içeriği: [20]
Kutu #2 içeriği: [300]
Kutu #3 içeriği: [-30]
Kutu #4 içeriği: [3]
Kutu #5 içeriği: [0]
Kutu #6 içeriği: [100]
*/