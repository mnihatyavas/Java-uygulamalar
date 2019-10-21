// J5h_7x.java: TextTransferHandler (MetinAktarmaY�netimi) alt-�rne�i.

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.TransferHandler;
import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;
import javax.swing.text.Document;
import javax.swing.text.BadLocationException;

import java.io.IOException;

// Hem s�r�kle-b�rak ve  hem de kes-kopyala-yap��t�r'� destekler...
class J5h_7x extends TransferHandler {
    // MOVE/Ta��'da s�r�klenen metni kaynaktan silmek i�in ilk ve son konumu bilinmelidir...
    Position konum0 = null, konum1 = null;

    public boolean importData (TransferHandler.TransferSupport destek) {
        // �thale destek yoksa d�n...
        if (! canImport (destek)) return false;

        // �zerinde i�lem yap�lmak �zere se�ilen veriyi al...
        String veri;
        try {veri = (String)destek.getTransferable().getTransferData (DataFlavor.stringFlavor);
        }catch (UnsupportedFlavorException ist) {return false;
        }catch (java.io.IOException ist) {return false;
        } // try-catch.. blo�u sonu...

        JTextField par�a = (JTextField)destek.getComponent();      
        par�a.replaceSelection (veri);
        return true;
    } // importData(..) haz�r metodu sonu...

    // Sadece dizge ithaline destek var...
    public boolean canImport (TransferHandler.TransferSupport destek) {
        if (! destek.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;
        return true;
    } // canImport(..) haz�r metodu sonu...

    // Kaynaktan se�ili dizge veri ve ilk-son konumlar� al�n�r...
    protected Transferable createTransferable (JComponent par�a) {
        JTextField kaynak = (JTextField)par�a;
        int ilk = kaynak.getSelectionStart();
        int son = kaynak.getSelectionEnd();
        Document d�k�man = kaynak.getDocument();
        if (ilk == son) return null;
        try {konum0 = d�k�man.createPosition (ilk);
            konum1 = d�k�man.createPosition (son);
        }catch (BadLocationException ist) {System.err.println ("Konumlar yarat�lam�yor - metin kaynaktan silinemiyor.");
        } // try-catch blo�u sonu...
        String veri = kaynak.getSelectedText();
        return new StringSelection (veri);
    } // createTransferable(..) haz�r metodu sonu...

    // Bu metin alanlar�nda kopyala ve ta��'n�n her ikisi de y�netiliyor...
    public int getSourceActions (JComponent par�a) {return COPY_OR_MOVE;}

    // �hra� tamamland���nda, hareket ta�� ise ta��nan metin kaynaktan silinmelidir...
    protected void exportDone (JComponent par�a, Transferable veri, int hareket) {
        if (hareket != MOVE) return;

        if ((konum0 != null) && (konum1 != null) && (konum0.getOffset() != konum1.getOffset())) {
            try {JTextComponent komponent = (JTextComponent)par�a;
                komponent.getDocument().remove (konum0.getOffset(), (konum1.getOffset() - konum0.getOffset()) );
            }catch (BadLocationException ist) {System.err.println ("Metni kaynaktan silemiyorum!");
            } // try-catch blo�u sonu...
        } // if karar� sonu...
    } // exportDone(..) haz�r metodu sonu...
} // J5h_7x s�n�f� sonu...