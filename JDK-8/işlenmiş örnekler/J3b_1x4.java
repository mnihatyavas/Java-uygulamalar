/* J3b_1x4.java: CheckedDataOutput (KontrolluVeri��k���) alt-�rne�i.
 * 
 * Bu s�n�f sadece bir �rnektir. Bu s�n�f�n tamamlanm�� s�r�m�
 * DataOutput aray�z�n� implements/y�r�tmeli ve
 * DataOutput'da beyanl� t�m metodlar� tamamlamal�d�r.
 */

import java.io.DataOutput;
import java.io.IOException;

public class J3b_1x4 {
    private J3b_1x2 kontyek; // J3b_1x2=Chekontyek/Kontrolyek�n�...
    private DataOutput yaz;

    public J3b_1x4 (DataOutput yaz, J3b_1x2 kontyek) {
        this.kontyek = kontyek;
        this.yaz = yaz;
    } // J3b_1x4() kurucu sonu...

    public void yaz (int b) throws IOException {
        yaz.write (b);
        kontyek.g�ncelle (b);
    } // yaz(..) metodu sonu...

    public void yaz (byte[] b) throws IOException {
        yaz.write (b, 0, b.length);
        kontyek.g�ncelle (b, 0, b.length);
    } // yaz(..) metodu sonu...

    public void yaz (byte[] b, int kapal�, int uzunluk) throws IOException {
        yaz.write (b, kapal�, uzunluk);
        kontyek.g�ncelle (b, kapal�, uzunluk);
    } // yaz(..) metodu sonu...

    public J3b_1x2 kontrolyek�n�Al() {return kontyek;}
} // J3b_1x4 s�n�f� sonu...