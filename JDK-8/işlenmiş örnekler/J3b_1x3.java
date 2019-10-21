/* J3b_1x3.java: CheckedDataInput (KontrolluVeriGiriþi) alt-örneði.
 * 
 * Bu sýnýf sadece bir örnektir. Bu sýnýfýn tamamlanmýþ sürümü
 * DataInput arayüzünü implements/yürütmeli ve
 * DataInput'da beyanlý tüm metodlarý tamamlamalýdýr.
 */ 
import java.io.DataInput;
import java.io.IOException;

public class J3b_1x3 {
    private J3b_1x2 kontyek; // J3b_1x2=Chekontyek/Kontrolyekünü...
    private DataInput oku;

    public J3b_1x3 (DataInput oku, J3b_1x2 kontyek) {
        this.kontyek = kontyek;
        this.oku = oku;
    } // J3b_1x3(..) kurucu sonu...

    public byte byteOku() throws IOException {
        byte b = oku.readByte();
        kontyek.güncelle (b);
        return b;
    } // byteOku() metodu sonu...

    public void tamOku (byte[] b) throws IOException {
        oku.readFully (b, 0, b.length);
        kontyek.güncelle (b, 0, b.length);
    } // tamOku(..) metodu sonu...

    public void tamOku (byte[] b, int kapalý, int uzunluk) throws IOException {
        oku.readFully (b, kapalý, uzunluk);
        kontyek.güncelle (b, kapalý, uzunluk);
    } // tamOku(..) metodu sonu...

    public J3b_1x2 kontrolyekünüAl() {return kontyek;}
} // J3b_1x3 sýnýfý sonu...