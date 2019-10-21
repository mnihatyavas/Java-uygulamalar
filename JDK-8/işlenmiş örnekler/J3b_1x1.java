// J3b_1x1.java: Adler32 (Adler32) alt-�rne�i.

// Gereken dosya: J3b_1x2.java=Checksum
public class J3b_1x1 implements J3b_1x2 {// J3b_1x2=Checksum/Kontrolyek�n�...
    private int de�er = 1;

    /* TABAN 65536'dan k���k enb�y�k temel tamsay�d�r.
     * NB�Y�K ise enb�y�k n'dir, ��yle ki: 255n(n+1)/2 + (n+1)(TABAN-1) <= 2^32-1
     */
    private static final int TABAN = 65521;
    private static final int NB�Y�K = 5552;

    // Belirtilen byte'la akt�el J3b_1x1/Adler32 kontrolyek�n� g�ncellenecek...
    public void g�ncelle (int b) {
        int s1 = de�er & 0xffff;
        int s2 = (de�er >> 16) & 0xffff;
        s1 += b & 0xff;
        s2 += s1;
        de�er = ((s2 % TABAN) << 16) | (s1 % TABAN);
    } // g�ncelle(..) metodu sonu...

    // Belirtilen byte diziniyle akt�el J3b_1x1/Adler32 kontrolyek�n� g�ncellenecek...
    public void g�ncelle (byte[] b, int kapal�, int uzunluk) {
        int s1 = de�er & 0xffff;
        int s2 = (de�er >> 16) & 0xffff;

        while (uzunluk > 0) {
            int k = uzunluk < NB�Y�K ? uzunluk : NB�Y�K;
            uzunluk -= k;
            while (k-- > 0) {
                s1 += b[kapal�++] & 0xff;
                s2 += s1;
            } // while-k d�ng�s� sonu...
            s1 %= TABAN;
            s2 %= TABAN;
        } // while-uzunluk d�ng�s� sonu...
        de�er = (s2 << 16) | s1;
    } // g�ncelle(..) metodu sonu...

    // J3b_1x1/Adler32 kontrolyek�n�'ne ba�lang�� de�erini atar...
    public void ilkDe�er() {de�er = 1;}

    // Akt�el kontrolyek�n�'n� d�nd�r�r...
    public long de�erAl() {return (long)de�er & 0xffffffff;}
} // J3b_1x1 s�n�f� sonu...