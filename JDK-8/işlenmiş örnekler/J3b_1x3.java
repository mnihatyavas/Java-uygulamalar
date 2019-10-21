/* J3b_1x3.java: CheckedDataInput (KontrolluVeriGiri�i) alt-�rne�i.
 * 
 * Bu s�n�f sadece bir �rnektir. Bu s�n�f�n tamamlanm�� s�r�m�
 * DataInput aray�z�n� implements/y�r�tmeli ve
 * DataInput'da beyanl� t�m metodlar� tamamlamal�d�r.
 */ 
import java.io.DataInput;
import java.io.IOException;

public class J3b_1x3 {
    private J3b_1x2 kontyek; // J3b_1x2=Chekontyek/Kontrolyek�n�...
    private DataInput oku;

    public J3b_1x3 (DataInput oku, J3b_1x2 kontyek) {
        this.kontyek = kontyek;
        this.oku = oku;
    } // J3b_1x3(..) kurucu sonu...

    public byte byteOku() throws IOException {
        byte b = oku.readByte();
        kontyek.g�ncelle (b);
        return b;
    } // byteOku() metodu sonu...

    public void tamOku (byte[] b) throws IOException {
        oku.readFully (b, 0, b.length);
        kontyek.g�ncelle (b, 0, b.length);
    } // tamOku(..) metodu sonu...

    public void tamOku (byte[] b, int kapal�, int uzunluk) throws IOException {
        oku.readFully (b, kapal�, uzunluk);
        kontyek.g�ncelle (b, kapal�, uzunluk);
    } // tamOku(..) metodu sonu...

    public J3b_1x2 kontrolyek�n�Al() {return kontyek;}
} // J3b_1x3 s�n�f� sonu...