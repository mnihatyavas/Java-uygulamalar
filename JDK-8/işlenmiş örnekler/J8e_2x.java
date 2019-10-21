// J8e_2x.java: DocumentSizeFilter (DökümanEbatýFilitresi) alt-örneði.

import java.awt.Toolkit;

import javax.swing.text.DocumentFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;

public class J8e_2x extends DocumentFilter {
    int azamiKarakter;
    boolean göstersinMi = true;

    public J8e_2x (int k) {azamiKarakter = k;} // Kurucu...

    public void insertString (FilterBypass filitreÝzni,
            int metninUzunluðu,
            String yazýlacakMetin,
            AttributeSet özelliði) throws BadLocationException {
        if (göstersinMi) System.out.println ("DocumentSizeFilter sýnýfýnýn insertString(..) hazýr metodu ile yazdýrýlmaktadýr.");
        if ((filitreÝzni.getDocument().getLength() + yazýlacakMetin.length()) <= azamiKarakter)
            super.insertString (filitreÝzni, metninUzunluðu, yazýlacakMetin, özelliði);
        else // Azami'den uzunsa yazmaz, bip ikazý verir
            Toolkit.getDefaultToolkit().beep();
    } // insertString(..) hazýr metodu sonu...

    public void replace (FilterBypass filitreÝzni,
            int metninUzunluðu,
            int uzunluk,
            String yazýlacakMetin,
            AttributeSet özelliði) throws BadLocationException {
        if (göstersinMi) System.out.println ("DocumentSizeFilter sýnýfýnýn insertString(..) hazýr metodu ile yazdýrýlmaktadýr.");
        if ((filitreÝzni.getDocument().getLength() + yazýlacakMetin.length() - uzunluk) <= azamiKarakter)
            super.replace (filitreÝzni, metninUzunluðu, uzunluk, yazýlacakMetin, özelliði);
        else Toolkit.getDefaultToolkit().beep();
    } // replace(..) hazýr metodu sonu...
} // J8e_2x sýnýfý sonu...