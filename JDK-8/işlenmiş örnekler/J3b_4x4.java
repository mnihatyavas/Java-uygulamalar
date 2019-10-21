// J3b_4x4.java: CheckedOutputStream (KontrolluÇýkýþAkýmý) alt-örneði.

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
        kontyek.güncelle (b);
    } // yaz(..) metodu sonu...

    public void yaz (byte[] b) throws IOException {
        out.write (b, 0, b.length);
        kontyek.güncelle (b, 0, b.length);
    } // yaz(..) metodu sonu...

    public void yaz (byte[] b, int kapalý, int uzunluk) throws IOException {
        out.write (b, kapalý, uzunluk);
        kontyek.güncelle (b, kapalý, uzunluk);
    } // yaz(..) metodu sonu...

    public J3b_4x2 kontrolyekünüAl() {return kontyek;}
} // J3b_4x4 sýnýfý sonu...