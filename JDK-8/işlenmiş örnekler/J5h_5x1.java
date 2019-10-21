// J5h_5x1.java: ListTransferHandler (ListeAktarmaY�netimi) alt-�rne�i.

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

        // E�er destek yoksa uzatma...
        if (! canImport (destek)) return false;

        JList liste = (JList)destek.getComponent();
        DefaultListModel listeModeli = (DefaultListModel)liste.getModel();
        // Dizge veriyi al, alamazsan uzatma...
        try {veri = (String)destek.getTransferable().getTransferData (DataFlavor.stringFlavor);
        }catch (UnsupportedFlavorException ist) {System.err.println ("HATA: Desteksiz veri aktar�m�"); return false;
        }catch (IOException ist) {System.err.println ("HATA: Veri I/O -Okuma/Yazma- istisnas�"); return false;
        } // try-catch.. blo�u sonu...

        if (destek.isDrop()) {// Drop/B�rak i�lemi...
            JList.DropLocation b�rakmaKonumu = (JList.DropLocation)destek.getDropLocation();
            int endeks = b�rakmaKonumu.getIndex();
            if (b�rakmaKonumu.isInsert()) {// Araya b�rak ise...
                listeModeli.add (endeks, veri);
                return true;
            }else {// �st�ne b�rak ise...
                listeModeli.set (endeks, veri);
                return true;
            } // else karar� sonu...
        }else {//Paste/Yap��t�r i�lemi ise...
            int endeks = liste.getSelectedIndex();
            if (endeks >= 0) //Se�ili konum varsa araya ekle...
                listeModeli.add(liste.getSelectedIndex()+1, veri);
            else // Se�ili konum yoksa listenin sonuna ekle...
                listeModeli.addElement(veri);

            return true;
        } // d��-else karar� sonu...
    } // importData(..) haz�r metodu sonu...

    // �hra� edilecek liste verisini dizgeye �evir...
    protected Transferable createTransferable (JComponent par�a) {
        JList liste = (JList)par�a;
        int endeks = liste.getSelectedIndex();
        String de�er = (String)liste.getSelectedValue();
        return new StringSelection (de�er);
    } // createTransferable(..) haz�r metodu sonu...

    // Liste Copy/Kopyala ve Cut/Kes/Move/Ta��'n�n her ikisini de y�netir...
    public int getSourceActions (JComponent par�a) {return COPY_OR_MOVE;} //Haz�r metod...

    // E�er ihra� Cut/Move ise, sonras�nda eski listedeki ta��nan i�erik silinir...
    protected void exportDone (JComponent par�a, Transferable veri, int hareket) {
        if (hareket != MOVE) return;

        JList liste = (JList)par�a;
        DefaultListModel listeModeli = (DefaultListModel)liste.getModel();
        int endeks = liste.getSelectedIndex();
        listeModeli.remove (endeks);
    } // exportDone(..) haz�r metodu sonu...

    // Sadece dizgeler (dizgeye d�n��t�r�len liste i�eri�i) i�in ithal desteklenmektedir...
    public boolean canImport (TransferHandler.TransferSupport destek) {
        if (! destek.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;

        return true;
    } // canImport(..) haz�r metodu sonu...
} // J5h_5x1 s�n�f� sonu...