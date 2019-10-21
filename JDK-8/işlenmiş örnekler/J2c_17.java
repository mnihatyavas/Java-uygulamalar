// J2c_17.java: StackOfInts (Tamsay�larY���n�) �rne�i.

public class J2c_17 {
    private int[] y���n;
    private int sonraki = 0;  // y���n'daki son dolu birimin endeksi...

    // y���n'� i�inde tutacak b�y�kl�kte bir dizin yaratal�m...
    public J2c_17 (int ebat) {y���n = new int[ebat];}

    // y���n dizinine yeni bir birim ekler...
    public void ekle (int de�er) {if (sonraki < y���n.length) y���n[sonraki++] = de�er;}

    // y���n dizinindeki son eleman� ��kar�r...
    public int ��kar() {if (!bo�Mu()) return y���n[--sonraki]; else return 0;}

    // y���n dizininin mevcut ebat�n� (i�erdi�i birim say�s�n�) d�nd�r�r...
    public int y���nEbad�n�Al() {return sonraki;}

    // y���n dizininin bo� oldu�unu (hi� birim i�ermedi�ini) d�nd�r�r...
    public boolean bo�Mu() {return (sonraki == 0);}

    private class Tara { 		
        private int i = 0; // y���n dizinine ilk endeks 0'dan ba�lar...

        // y���n endeksini bir art�r�r...
        public void art�r() {if (i < y���n.length) i++;}

        // y���n�n cari birimini al...
        public int cari() {return y���n[i];}

        // y���n'�n sonuncu birimi mi?..
        public boolean sonuncuMu() {if (i == y���nEbad�n�Al()) return true; else return false;}
    } // Tara s�n�f� sonu...

    public Tara tara() {return new Tara();}

    public static void main (String[] args) {
        // D��s�n�f� y���nBir adl� nesne olarak tiple ve y���n dizinin 15 birim kapasiteli yarat...
        J2c_17 y���nBir = new J2c_17 (15);

        // y���nBir nesnesiyle 15 birimlik y���n dizinin elemanlar�n� (2*endeks)'le doldur...
        for (int j = 0 ; j < 15 ; j++) {y���nBir.ekle (2*j);}

        // ��s�n�f� taray�c� isimli nesne olarak tiple...
        Tara taray�c� = y���nBir.tara();

        // y���nBir nesnesi y���n[i] dizin elemanlar�n�, aralar�nda birer bo�lukla tek sat�ra yazd�r...
        while (!taray�c�.sonuncuMu()) {System.out.print (taray�c�.cari() + " "); taray�c�.art�r();}
    } // main(..) metodu sonu...
} // J2c_17 s�n�f� sonu...

/* ��kt�:
**  >java J2c_17  **
0 2 4 6 8 10 12 14 16 18 20 22 24 26 28
*/