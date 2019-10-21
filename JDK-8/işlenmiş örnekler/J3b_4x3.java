// J3b_4x3.java: CheckedInputStream (KontrolluGiriþAkýmý) alt-örneði.

import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;

public class J3b_4x3 extends FilterInputStream {
    private J3b_4x2 kontyek;

    public J3b_4x3 (InputStream in, J3b_4x2 kontyek) {
        super (in);
        this.kontyek = kontyek;
    } // J3b_4x3(..) kurucusu sonu...

    public int oku() throws IOException {
        int b = in.read();
        if (b != -1) kontyek.güncelle (b);
        return b;
    } // oku() metodu sonu...

    public int oku (byte[] b) throws IOException {
        int uzunluk;
        uzunluk = in.read (b, 0, b.length);
        if (uzunluk != -1) kontyek.güncelle (b, 0, uzunluk);
        return uzunluk;
    } // oku(..) metodu sonu...

    public int oku (byte[] b, int kapalý, int uzunluk) throws IOException {
        uzunluk = in.read (b, kapalý, uzunluk);
        if (uzunluk != -1) kontyek.güncelle (b, kapalý, uzunluk);
        return uzunluk;
    } // oku(..) metodu sonu...

    public J3b_4x2 kontrolyekünüAl() {return kontyek;}
} // J3b_4x3 sýnýfý sonu...