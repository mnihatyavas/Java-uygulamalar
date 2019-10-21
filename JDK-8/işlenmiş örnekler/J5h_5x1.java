// J5h_5x1.java: ListTransferHandler (ListeAktarmaYönetimi) alt-örneði.

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.JList;
import javax.swing.JComponent;
import javax.swing.DefaultListModel;
import javax.swing.TransferHandler;

import java.io.IOException;

class J5h_5x1 extends TransferHandler {
    public boolean importData (TransferHandler.TransferSupport destek) {
        String veri = null;

        // Eðer destek yoksa uzatma...
        if (! canImport (destek)) return false;

        JList liste = (JList)destek.getComponent();
        DefaultListModel listeModeli = (DefaultListModel)liste.getModel();
        // Dizge veriyi al, alamazsan uzatma...
        try {veri = (String)destek.getTransferable().getTransferData (DataFlavor.stringFlavor);
        }catch (UnsupportedFlavorException ist) {System.err.println ("HATA: Desteksiz veri aktarýmý"); return false;
        }catch (IOException ist) {System.err.println ("HATA: Veri I/O -Okuma/Yazma- istisnasý"); return false;
        } // try-catch.. bloðu sonu...

        if (destek.isDrop()) {// Drop/Býrak iþlemi...
            JList.DropLocation býrakmaKonumu = (JList.DropLocation)destek.getDropLocation();
            int endeks = býrakmaKonumu.getIndex();
            if (býrakmaKonumu.isInsert()) {// Araya býrak ise...
                listeModeli.add (endeks, veri);
                return true;
            }else {// Üstüne býrak ise...
                listeModeli.set (endeks, veri);
                return true;
            } // else kararý sonu...
        }else {//Paste/Yapýþtýr iþlemi ise...
            int endeks = liste.getSelectedIndex();
            if (endeks >= 0) //Seçili konum varsa araya ekle...
                listeModeli.add(liste.getSelectedIndex()+1, veri);
            else // Seçili konum yoksa listenin sonuna ekle...
                listeModeli.addElement(veri);

            return true;
        } // dýþ-else kararý sonu...
    } // importData(..) hazýr metodu sonu...

    // Ýhraç edilecek liste verisini dizgeye çevir...
    protected Transferable createTransferable (JComponent parça) {
        JList liste = (JList)parça;
        int endeks = liste.getSelectedIndex();
        String deðer = (String)liste.getSelectedValue();
        return new StringSelection (deðer);
    } // createTransferable(..) hazýr metodu sonu...

    // Liste Copy/Kopyala ve Cut/Kes/Move/Taþý'nýn her ikisini de yönetir...
    public int getSourceActions (JComponent parça) {return COPY_OR_MOVE;} //Hazýr metod...

    // Eðer ihraç Cut/Move ise, sonrasýnda eski listedeki taþýnan içerik silinir...
    protected void exportDone (JComponent parça, Transferable veri, int hareket) {
        if (hareket != MOVE) return;

        JList liste = (JList)parça;
        DefaultListModel listeModeli = (DefaultListModel)liste.getModel();
        int endeks = liste.getSelectedIndex();
        listeModeli.remove (endeks);
    } // exportDone(..) hazýr metodu sonu...

    // Sadece dizgeler (dizgeye dönüþtürülen liste içeriði) için ithal desteklenmektedir...
    public boolean canImport (TransferHandler.TransferSupport destek) {
        if (! destek.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;

        return true;
    } // canImport(..) hazýr metodu sonu...
} // J5h_5x1 sýnýfý sonu...