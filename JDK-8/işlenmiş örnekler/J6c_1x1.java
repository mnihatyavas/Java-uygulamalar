// J6c_1x1.java: Averager (Ortalamacý) alt-örneði.

import java.util.function.IntConsumer;

class J6c_1x1 implements IntConsumer {
    private int toplam = 0;
    private int adet = 0;

    public double ortala() {return adet > 0 ? ((double) toplam) / adet : 0;}
    public void accept (int i) {toplam += i; adet++;} // Hazýr metod...

    public void birleþtir (J6c_1x1 diðer) {
        toplam += diðer.toplam;
        adet += diðer.adet;
    } // birleþtir(..) metodu sonu...
} // J6c_1x1 sýnýfý sonu...