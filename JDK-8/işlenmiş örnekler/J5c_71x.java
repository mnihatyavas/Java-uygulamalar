// J5c_71x.java: DocumentSizeFilter (D�k�manEbat�Filitresi) alt-�rne�i.

import java.awt.Toolkit; // Azami karakter say�s�n� a�arsa bipletecek...

import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class J5c_71x extends DocumentFilter {
    int azamiKarakterSay�s�;
    boolean yaz�ls�nM� = true;

    public J5c_71x (int uzunluk) {azamiKarakterSay�s� = uzunluk;} // Kurucu...

    public void insertString (FilterBypass fb, int toplamUzunluk, String yeniGirilecekDizge, AttributeSet nitelikleri)
            throws BadLocationException {
        if (yaz�ls�nM�) System.out.println ("J5c_71x=DocumentSizeFilter s�n�f�n�n insertString metodu i�indeyiz.");

        // Azami uzunlu�u ta�an son giri� reddedilir. Di�er bir y�ntem ise
        // girilece�in sadece ta�an k�sm�n�n k�rp�lmas� olabilir...
        if ((fb.getDocument().getLength() + yeniGirilecekDizge.length()) <= azamiKarakterSay�s�)
            super.insertString (fb, toplamUzunluk, yeniGirilecekDizge, nitelikleri);
        else Toolkit.getDefaultToolkit().beep();
    } // insertString(..) haz�r metodu sonu...

    public void replace (FilterBypass fb, int toplamUzunluk, int length,  String yeniGirilecekDizge, AttributeSet nitelikleri)
            throws BadLocationException {
        if (yaz�ls�nM�) System.out.println ("J5c_71x=DocumentSizeFilter s�n�f�n�n replace metodu i�indeyiz.");

        // Son girilecek azamiyi ta�arsa t�mden reddedilir.
        // Di�er y�ntem ise sadece ta�an k�sm�n k�rp�lmas� olabilir...
        if ((fb.getDocument().getLength() + yeniGirilecekDizge.length() - length) <= azamiKarakterSay�s�)
            super.replace (fb, toplamUzunluk, length, yeniGirilecekDizge, nitelikleri);
        else Toolkit.getDefaultToolkit().beep();
    } // replace(..) haz�r metodu sonu...
} // J5c_71x s�n�f� sonu...