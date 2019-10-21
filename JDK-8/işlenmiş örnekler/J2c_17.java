// J2c_17.java: StackOfInts (TamsayýlarYýðýný) örneði.

public class J2c_17 {
    private int[] yýðýn;
    private int sonraki = 0;  // yýðýn'daki son dolu birimin endeksi...

    // yýðýn'ý içinde tutacak büyüklükte bir dizin yaratalým...
    public J2c_17 (int ebat) {yýðýn = new int[ebat];}

    // yýðýn dizinine yeni bir birim ekler...
    public void ekle (int deðer) {if (sonraki < yýðýn.length) yýðýn[sonraki++] = deðer;}

    // yýðýn dizinindeki son elemaný çýkarýr...
    public int çýkar() {if (!boþMu()) return yýðýn[--sonraki]; else return 0;}

    // yýðýn dizininin mevcut ebatýný (içerdiði birim sayýsýný) döndürür...
    public int yýðýnEbadýnýAl() {return sonraki;}

    // yýðýn dizininin boþ olduðunu (hiç birim içermediðini) döndürür...
    public boolean boþMu() {return (sonraki == 0);}

    private class Tara { 		
        private int i = 0; // yýðýn dizinine ilk endeks 0'dan baþlar...

        // yýðýn endeksini bir artýrýr...
        public void artýr() {if (i < yýðýn.length) i++;}

        // yýðýnýn cari birimini al...
        public int cari() {return yýðýn[i];}

        // yýðýn'ýn sonuncu birimi mi?..
        public boolean sonuncuMu() {if (i == yýðýnEbadýnýAl()) return true; else return false;}
    } // Tara sýnýfý sonu...

    public Tara tara() {return new Tara();}

    public static void main (String[] args) {
        // Dýþsýnýfý yýðýnBir adlý nesne olarak tiple ve yýðýn dizinin 15 birim kapasiteli yarat...
        J2c_17 yýðýnBir = new J2c_17 (15);

        // yýðýnBir nesnesiyle 15 birimlik yýðýn dizinin elemanlarýný (2*endeks)'le doldur...
        for (int j = 0 ; j < 15 ; j++) {yýðýnBir.ekle (2*j);}

        // Ýçsýnýfý tarayýcý isimli nesne olarak tiple...
        Tara tarayýcý = yýðýnBir.tara();

        // yýðýnBir nesnesi yýðýn[i] dizin elemanlarýný, aralarýnda birer boþlukla tek satýra yazdýr...
        while (!tarayýcý.sonuncuMu()) {System.out.print (tarayýcý.cari() + " "); tarayýcý.artýr();}
    } // main(..) metodu sonu...
} // J2c_17 sýnýfý sonu...

/* Çýktý:
**  >java J2c_17  **
0 2 4 6 8 10 12 14 16 18 20 22 24 26 28
*/