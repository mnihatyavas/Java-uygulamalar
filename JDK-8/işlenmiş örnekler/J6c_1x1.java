// J6c_1x1.java: Averager (Ortalamac�) alt-�rne�i.

import java.util.function.IntConsumer;

class J6c_1x1 implements IntConsumer {
    private int toplam = 0;
    private int adet = 0;

    public double ortala() {return adet > 0 ? ((double) toplam) / adet : 0;}
    public void accept (int i) {toplam += i; adet++;} // Haz�r metod...

    public void birle�tir (J6c_1x1 di�er) {
        toplam += di�er.toplam;
        adet += di�er.adet;
    } // birle�tir(..) metodu sonu...
} // J6c_1x1 s�n�f� sonu...