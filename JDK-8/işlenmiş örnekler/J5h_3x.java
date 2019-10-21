// J5h_3x.java: ListTransferHandler (ListeAktarmaYöneticisi) alt-örneði.

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
   } // canImport(..) hazýr metodu sonu...

    protected Transferable createTransferable (JComponent parça) {return new StringSelection (dizgeÝhracý (parça));}

    // Ýhraç için seçili liste birimleri tek bir dizge demeti yapýlýr...
    protected String dizgeÝhracý (JComponent parça) {
        JList liste = (JList)parça;
        endeksler = liste.getSelectedIndices();
        Object[] nesneler = liste.getSelectedValues();
        StringBuffer tampon = new StringBuffer();

        for (int i = 0; i < nesneler.length; i++) {
            Object nesne = nesneler[i];
            tampon.append (nesne == null ? "" : nesne.toString());
            if (i != nesneler.length - 1) tampon.append ("\n");
        } // for döngüsü sonu...

        return tampon.toString();
    } // dizgeÝhracý(..) metodu sonu...

    public int getSourceActions (JComponent parça) {return TransferHandler.COPY_OR_MOVE;}

    public boolean importData (TransferHandler.TransferSupport bilgi) {
        if (!bilgi.isDrop()) return false;

        JList liste = (JList)bilgi.getComponent();
        DefaultListModel listeModeli = (DefaultListModel)liste.getModel();
        JList.DropLocation býrakýlanKonum = (JList.DropLocation)bilgi.getDropLocation();
        int endeks = býrakýlanKonum.getIndex();
        boolean arayaMý = býrakýlanKonum.isInsert();

        // Býrakýlan dizgeyi alalým...
        Transferable aktarýlan = bilgi.getTransferable();
        String veri;
        try {veri = (String)aktarýlan.getTransferData (DataFlavor.stringFlavor);
        }catch (Exception ist) {return false;}

        // Araya veya üstüne ithali gerçekleþtirelim...
        if (arayaMý) listeModeli.add (endeks, veri); // Araya ise ekle...
        else listeModeli.set (endeks, veri); // Üstüne ise mevcutla deðiþtir...

        return true;
    } // importData(..) hazýr metodu sonu...

    protected void exportDone (JComponent parça, Transferable veri, int hareket) {
        temizle (parça, hareket == TransferHandler.MOVE);
    } // exportDone(..) hazýr metodu sonu...

    // MOVE/Taþýma doðruysa (=1) seçilen silinmeli, deðilse (kopya) aynen kalmalý...
    protected void temizle (JComponent parça, boolean taþýMý) {
        if (taþýMý && endeksler != null) {
            JList kaynakListe = (JList)parça;
            DefaultListModel model  = (DefaultListModel)kaynakListe.getModel();

            // Eðer taþýma ayný listedeyse yeni enkeksler güncellenmelidir...
            if (toplamEklenen > 0)
                for (int i = 0; i < endeksler.length; i++)
                    if (endeksler[i] > ekleneninEndeksi) endeksler[i] += toplamEklenen;

            for (int i = endeksler.length - 1; i >= 0; i--) model.remove (endeksler[i]);
        } // if kararý sonu...
        endeksler = null;
        toplamEklenen = 0;
        ekleneninEndeksi = -1;
    } // temizle(..) metodu sonu...

    // Ýthal dizgedeki "\n" için listede alt-alta birimlere ayýrma (ve de endeksleme) yapýlmalýdýr...
    protected void importString (JComponent parça, String dizge) {
        JList hedefListe = (JList)parça;
        DefaultListModel listeModeli = (DefaultListModel)hedefListe.getModel();
        int endeks = hedefListe.getSelectedIndex();

        // Kendi üstüne býrakmalar endeks deðiþikliði yapmamalý...
        if (endeksler != null && endeks >= endeksler[0] - 1 && endeks <= endeksler[endeksler.length - 1]) {
            endeksler = null;
            return;
        } // if kararý sonu....

        int azami = listeModeli.getSize();
        if (endeks < 0) endeks = azami;
        else {
            endeks++;
            if (endeks > azami) endeks = azami;
        } // else kararý sonu...

        ekleneninEndeksi = endeks;
        String[] satýrlar = dizge.split ("\n");
        toplamEklenen = satýrlar.length;
        for (int i = 0; i < satýrlar.length; i++) listeModeli.add (endeks++, satýrlar[i]);
    } // importString(..) hazýr metodu sonu...
} // J5h_3x sýnýfý sonu...