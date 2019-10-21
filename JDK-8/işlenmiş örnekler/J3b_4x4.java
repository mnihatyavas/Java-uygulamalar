// J3b_4x4.java: CheckedOutputStream (Kontrollu��k��Ak�m�) alt-�rne�i.

import java.io.OutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

public class J3b_4x4 extends FilterOutputStream {
    private J3b_4x2 kontyek;

    public J3b_4x4 (OutputStream out, J3b_4x2 kontyek) {
        super (out);
        this.kontyek = kontyek;
    } // J3b_4x4(..) kurucusu sonu...

    public void yaz (int b) throws IOException {
        out.write (b);
        kontyek.g�ncelle (b);
    } // yaz(..) metodu sonu...

    public void yaz (byte[] b) throws IOException {
        out.write (b, 0, b.length);
        kontyek.g�ncelle (b, 0, b.length);
    } // yaz(..) metodu sonu...

    public void yaz (byte[] b, int kapal�, int uzunluk) throws IOException {
        out.write (b, kapal�, uzunluk);
        kontyek.g�ncelle (b, kapal�, uzunluk);
    } // yaz(..) metodu sonu...

    public J3b_4x2 kontrolyek�n�Al() {return kontyek;}
} // J3b_4x4 s�n�f� sonu...