// J8e_2x.java: DocumentSizeFilter (D�k�manEbat�Filitresi) alt-�rne�i.

import java.awt.Toolkit;

import javax.swing.text.DocumentFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;

public class J8e_2x extends DocumentFilter {
    int azamiKarakter;
    boolean g�stersinMi = true;

    public J8e_2x (int k) {azamiKarakter = k;} // Kurucu...

    public void insertString (FilterBypass filitre�zni,
            int metninUzunlu�u,
            String yaz�lacakMetin,
            AttributeSet �zelli�i) throws BadLocationException {
        if (g�stersinMi) System.out.println ("DocumentSizeFilter s�n�f�n�n insertString(..) haz�r metodu ile yazd�r�lmaktad�r.");
        if ((filitre�zni.getDocument().getLength() + yaz�lacakMetin.length()) <= azamiKarakter)
            super.insertString (filitre�zni, metninUzunlu�u, yaz�lacakMetin, �zelli�i);
        else // Azami'den uzunsa yazmaz, bip ikaz� verir
            Toolkit.getDefaultToolkit().beep();
    } // insertString(..) haz�r metodu sonu...

    public void replace (FilterBypass filitre�zni,
            int metninUzunlu�u,
            int uzunluk,
            String yaz�lacakMetin,
            AttributeSet �zelli�i) throws BadLocationException {
        if (g�stersinMi) System.out.println ("DocumentSizeFilter s�n�f�n�n insertString(..) haz�r metodu ile yazd�r�lmaktad�r.");
        if ((filitre�zni.getDocument().getLength() + yaz�lacakMetin.length() - uzunluk) <= azamiKarakter)
            super.replace (filitre�zni, metninUzunlu�u, uzunluk, yaz�lacakMetin, �zelli�i);
        else Toolkit.getDefaultToolkit().beep();
    } // replace(..) haz�r metodu sonu...
} // J8e_2x s�n�f� sonu...