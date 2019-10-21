/* J3b_1x4.java: CheckedDataOutput (KontrolluVeriÇýkýþý) alt-örneði.
 * 
 * Bu sýnýf sadece bir örnektir. Bu sýnýfýn tamamlanmýþ sürümü
 * DataOutput arayüzünü implements/yürütmeli ve
 * DataOutput'da beyanlý tüm metodlarý tamamlamalýdýr.
 */

import java.io.DataOutput;
import java.io.IOException;

public class J3b_1x4 {
    private J3b_1x2 kontyek; // J3b_1x2=Chekontyek/Kontrolyekünü...
    private DataOutput yaz;

    public J3b_1x4 (DataOutput yaz, J3b_1x2 kontyek) {
        this.kontyek = kontyek;
        this.yaz = yaz;
    } // J3b_1x4() kurucu sonu...

    public void yaz (int b) throws IOException {
        yaz.write (b);
        kontyek.güncelle (b);
    } // yaz(..) metodu sonu...

    public void yaz (byte[] b) throws IOException {
        yaz.write (b, 0, b.length);
        kontyek.güncelle (b, 0, b.length);
    } // yaz(..) metodu sonu...

    public void yaz (byte[] b, int kapalý, int uzunluk) throws IOException {
        yaz.write (b, kapalý, uzunluk);
        kontyek.güncelle (b, kapalý, uzunluk);
    } // yaz(..) metodu sonu...

    public J3b_1x2 kontrolyekünüAl() {return kontyek;}
} // J3b_1x4 sýnýfý sonu...