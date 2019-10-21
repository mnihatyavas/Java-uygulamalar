// J5h_3x.java: ListTransferHandler (ListeAktarmaY�neticisi) alt-�rne�i.

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;

import javax.swing.JList;
import javax.swing.JComponent;
import javax.swing.DefaultListModel;
import javax.swing.TransferHandler;

public class J5h_3x extends TransferHandler {
    private int[] endeksler = null;
    private int ekleneninEndeksi = -1;
    private int toplamEklenen = 0;

    public boolean canImport (TransferHandler.TransferSupport info) {
        // Sadece String dizgeler destekleniyor...
        if (!info.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;
        return true;
   } // canImport(..) haz�r metodu sonu...

    protected Transferable createTransferable (JComponent par�a) {return new StringSelection (dizge�hrac� (par�a));}

    // �hra� i�in se�ili liste birimleri tek bir dizge demeti yap�l�r...
    protected String dizge�hrac� (JComponent par�a) {
        JList liste = (JList)par�a;
        endeksler = liste.getSelectedIndices();
        Object[] nesneler = liste.getSelectedValues();
        StringBuffer tampon = new StringBuffer();

        for (int i = 0; i < nesneler.length; i++) {
            Object nesne = nesneler[i];
            tampon.append (nesne == null ? "" : nesne.toString());
            if (i != nesneler.length - 1) tampon.append ("\n");
        } // for d�ng�s� sonu...

        return tampon.toString();
    } // dizge�hrac�(..) metodu sonu...

    public int getSourceActions (JComponent par�a) {return TransferHandler.COPY_OR_MOVE;}

    public boolean importData (TransferHandler.TransferSupport bilgi) {
        if (!bilgi.isDrop()) return false;

        JList liste = (JList)bilgi.getComponent();
        DefaultListModel listeModeli = (DefaultListModel)liste.getModel();
        JList.DropLocation b�rak�lanKonum = (JList.DropLocation)bilgi.getDropLocation();
        int endeks = b�rak�lanKonum.getIndex();
        boolean arayaM� = b�rak�lanKonum.isInsert();

        // B�rak�lan dizgeyi alal�m...
        Transferable aktar�lan = bilgi.getTransferable();
        String veri;
        try {veri = (String)aktar�lan.getTransferData (DataFlavor.stringFlavor);
        }catch (Exception ist) {return false;}

        // Araya veya �st�ne ithali ger�ekle�tirelim...
        if (arayaM�) listeModeli.add (endeks, veri); // Araya ise ekle...
        else listeModeli.set (endeks, veri); // �st�ne ise mevcutla de�i�tir...

        return true;
    } // importData(..) haz�r metodu sonu...

    protected void exportDone (JComponent par�a, Transferable veri, int hareket) {
        temizle (par�a, hareket == TransferHandler.MOVE);
    } // exportDone(..) haz�r metodu sonu...

    // MOVE/Ta��ma do�ruysa (=1) se�ilen silinmeli, de�ilse (kopya) aynen kalmal�...
    protected void temizle (JComponent par�a, boolean ta��M�) {
        if (ta��M� && endeksler != null) {
            JList kaynakListe = (JList)par�a;
            DefaultListModel model  = (DefaultListModel)kaynakListe.getModel();

            // E�er ta��ma ayn� listedeyse yeni enkeksler g�ncellenmelidir...
            if (toplamEklenen > 0)
                for (int i = 0; i < endeksler.length; i++)
                    if (endeksler[i] > ekleneninEndeksi) endeksler[i] += toplamEklenen;

            for (int i = endeksler.length - 1; i >= 0; i--) model.remove (endeksler[i]);
        } // if karar� sonu...
        endeksler = null;
        toplamEklenen = 0;
        ekleneninEndeksi = -1;
    } // temizle(..) metodu sonu...

    // �thal dizgedeki "\n" i�in listede alt-alta birimlere ay�rma (ve de endeksleme) yap�lmal�d�r...
    protected void importString (JComponent par�a, String dizge) {
        JList hedefListe = (JList)par�a;
        DefaultListModel listeModeli = (DefaultListModel)hedefListe.getModel();
        int endeks = hedefListe.getSelectedIndex();

        // Kendi �st�ne b�rakmalar endeks de�i�ikli�i yapmamal�...
        if (endeksler != null && endeks >= endeksler[0] - 1 && endeks <= endeksler[endeksler.length - 1]) {
            endeksler = null;
            return;
        } // if karar� sonu....

        int azami = listeModeli.getSize();
        if (endeks < 0) endeks = azami;
        else {
            endeks++;
            if (endeks > azami) endeks = azami;
        } // else karar� sonu...

        ekleneninEndeksi = endeks;
        String[] sat�rlar = dizge.split ("\n");
        toplamEklenen = sat�rlar.length;
        for (int i = 0; i < sat�rlar.length; i++) listeModeli.add (endeks++, sat�rlar[i]);
    } // importString(..) haz�r metodu sonu...
} // J5h_3x s�n�f� sonu...