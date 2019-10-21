// J5c_71x.java: DocumentSizeFilter (DökümanEbatýFilitresi) alt-örneði.

import java.awt.Toolkit; // Azami karakter sayýsýný aþarsa bipletecek...

import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class J5c_71x extends DocumentFilter {
    int azamiKarakterSayýsý;
    boolean yazýlsýnMý = true;

    public J5c_71x (int uzunluk) {azamiKarakterSayýsý = uzunluk;} // Kurucu...

    public void insertString (FilterBypass fb, int toplamUzunluk, String yeniGirilecekDizge, AttributeSet nitelikleri)
            throws BadLocationException {
        if (yazýlsýnMý) System.out.println ("J5c_71x=DocumentSizeFilter sýnýfýnýn insertString metodu içindeyiz.");

        // Azami uzunluðu taþan son giriþ reddedilir. Diðer bir yöntem ise
        // girileceðin sadece taþan kýsmýnýn kýrpýlmasý olabilir...
        if ((fb.getDocument().getLength() + yeniGirilecekDizge.length()) <= azamiKarakterSayýsý)
            super.insertString (fb, toplamUzunluk, yeniGirilecekDizge, nitelikleri);
        else Toolkit.getDefaultToolkit().beep();
    } // insertString(..) hazýr metodu sonu...

    public void replace (FilterBypass fb, int toplamUzunluk, int length,  String yeniGirilecekDizge, AttributeSet nitelikleri)
            throws BadLocationException {
        if (yazýlsýnMý) System.out.println ("J5c_71x=DocumentSizeFilter sýnýfýnýn replace metodu içindeyiz.");

        // Son girilecek azamiyi taþarsa tümden reddedilir.
        // Diðer yöntem ise sadece taþan kýsmýn kýrpýlmasý olabilir...
        if ((fb.getDocument().getLength() + yeniGirilecekDizge.length() - length) <= azamiKarakterSayýsý)
            super.replace (fb, toplamUzunluk, length, yeniGirilecekDizge, nitelikleri);
        else Toolkit.getDefaultToolkit().beep();
    } // replace(..) hazýr metodu sonu...
} // J5c_71x sýnýfý sonu...