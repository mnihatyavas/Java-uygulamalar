// J3b_4x3.java: CheckedInputStream (KontrolluGiri�Ak�m�) alt-�rne�i.

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
        if (b != -1) kontyek.g�ncelle (b);
        return b;
    } // oku() metodu sonu...

    public int oku (byte[] b) throws IOException {
        int uzunluk;
        uzunluk = in.read (b, 0, b.length);
        if (uzunluk != -1) kontyek.g�ncelle (b, 0, uzunluk);
        return uzunluk;
    } // oku(..) metodu sonu...

    public int oku (byte[] b, int kapal�, int uzunluk) throws IOException {
        uzunluk = in.read (b, kapal�, uzunluk);
        if (uzunluk != -1) kontyek.g�ncelle (b, kapal�, uzunluk);
        return uzunluk;
    } // oku(..) metodu sonu...

    public J3b_4x2 kontrolyek�n�Al() {return kontyek;}
} // J3b_4x3 s�n�f� sonu...