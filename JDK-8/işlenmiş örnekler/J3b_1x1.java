// J3b_1x1.java: Adler32 (Adler32) alt-örneði.

// Gereken dosya: J3b_1x2.java=Checksum
public class J3b_1x1 implements J3b_1x2 {// J3b_1x2=Checksum/Kontrolyekünü...
    private int deðer = 1;

    /* TABAN 65536'dan küçük enbüyük temel tamsayýdýr.
     * NBÜYÜK ise enbüyük n'dir, þöyle ki: 255n(n+1)/2 + (n+1)(TABAN-1) <= 2^32-1
     */
    private static final int TABAN = 65521;
    private static final int NBÜYÜK = 5552;

    // Belirtilen byte'la aktüel J3b_1x1/Adler32 kontrolyekünü güncellenecek...
    public void güncelle (int b) {
        int s1 = deðer & 0xffff;
        int s2 = (deðer >> 16) & 0xffff;
        s1 += b & 0xff;
        s2 += s1;
        deðer = ((s2 % TABAN) << 16) | (s1 % TABAN);
    } // güncelle(..) metodu sonu...

    // Belirtilen byte diziniyle aktüel J3b_1x1/Adler32 kontrolyekünü güncellenecek...
    public void güncelle (byte[] b, int kapalý, int uzunluk) {
        int s1 = deðer & 0xffff;
        int s2 = (deðer >> 16) & 0xffff;

        while (uzunluk > 0) {
            int k = uzunluk < NBÜYÜK ? uzunluk : NBÜYÜK;
            uzunluk -= k;
            while (k-- > 0) {
                s1 += b[kapalý++] & 0xff;
                s2 += s1;
            } // while-k döngüsü sonu...
            s1 %= TABAN;
            s2 %= TABAN;
        } // while-uzunluk döngüsü sonu...
        deðer = (s2 << 16) | s1;
    } // güncelle(..) metodu sonu...

    // J3b_1x1/Adler32 kontrolyekünü'ne baþlangýç deðerini atar...
    public void ilkDeðer() {deðer = 1;}

    // Aktüel kontrolyekünü'nü döndürür...
    public long deðerAl() {return (long)deðer & 0xffffffff;}
} // J3b_1x1 sýnýfý sonu...