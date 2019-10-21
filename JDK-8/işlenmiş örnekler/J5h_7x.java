// J5h_7x.java: TextTransferHandler (MetinAktarmaYönetimi) alt-örneði.

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

// Hem sürükle-býrak ve  hem de kes-kopyala-yapýþtýr'ý destekler...
class J5h_7x extends TransferHandler {
    // MOVE/Taþý'da sürüklenen metni kaynaktan silmek için ilk ve son konumu bilinmelidir...
    Position konum0 = null, konum1 = null;

    public boolean importData (TransferHandler.TransferSupport destek) {
        // Ýthale destek yoksa dön...
        if (! canImport (destek)) return false;

        // Üzerinde iþlem yapýlmak üzere seçilen veriyi al...
        String veri;
        try {veri = (String)destek.getTransferable().getTransferData (DataFlavor.stringFlavor);
        }catch (UnsupportedFlavorException ist) {return false;
        }catch (java.io.IOException ist) {return false;
        } // try-catch.. bloðu sonu...

        JTextField parça = (JTextField)destek.getComponent();      
        parça.replaceSelection (veri);
        return true;
    } // importData(..) hazýr metodu sonu...

    // Sadece dizge ithaline destek var...
    public boolean canImport (TransferHandler.TransferSupport destek) {
        if (! destek.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;
        return true;
    } // canImport(..) hazýr metodu sonu...

    // Kaynaktan seçili dizge veri ve ilk-son konumlarý alýnýr...
    protected Transferable createTransferable (JComponent parça) {
        JTextField kaynak = (JTextField)parça;
        int ilk = kaynak.getSelectionStart();
        int son = kaynak.getSelectionEnd();
        Document döküman = kaynak.getDocument();
        if (ilk == son) return null;
        try {konum0 = döküman.createPosition (ilk);
            konum1 = döküman.createPosition (son);
        }catch (BadLocationException ist) {System.err.println ("Konumlar yaratýlamýyor - metin kaynaktan silinemiyor.");
        } // try-catch bloðu sonu...
        String veri = kaynak.getSelectedText();
        return new StringSelection (veri);
    } // createTransferable(..) hazýr metodu sonu...

    // Bu metin alanlarýnda kopyala ve taþý'nýn her ikisi de yönetiliyor...
    public int getSourceActions (JComponent parça) {return COPY_OR_MOVE;}

    // Ýhraç tamamlandýðýnda, hareket taþý ise taþýnan metin kaynaktan silinmelidir...
    protected void exportDone (JComponent parça, Transferable veri, int hareket) {
        if (hareket != MOVE) return;

        if ((konum0 != null) && (konum1 != null) && (konum0.getOffset() != konum1.getOffset())) {
            try {JTextComponent komponent = (JTextComponent)parça;
                komponent.getDocument().remove (konum0.getOffset(), (konum1.getOffset() - konum0.getOffset()) );
            }catch (BadLocationException ist) {System.err.println ("Metni kaynaktan silemiyorum!");
            } // try-catch bloðu sonu...
        } // if kararý sonu...
    } // exportDone(..) hazýr metodu sonu...
} // J5h_7x sýnýfý sonu...