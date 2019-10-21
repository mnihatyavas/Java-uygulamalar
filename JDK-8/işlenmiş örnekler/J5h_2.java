// J5h_2.java: ChooseDropActionDemo (Se�ilenS�r�kleHareketiG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.TransferHandler;
import javax.swing.DropMode;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.io.IOException;

public class J5h_2 extends JFrame {
    DefaultListModel s�r�klemeKayna�� = new DefaultListModel();
    DefaultListModel kopyalamaHedefi = new DefaultListModel();
    DefaultListModel ta��maHedefi = new DefaultListModel();
    JList s�r�klenecekListe;
    JList kopyalamaListesi;
    JList ta��maListesi;

    public J5h_2() {// Kurucu...
        super ("Se�ilen S�r�kle Hareketi G�sterisi");

        for (int i = 15; i >= 0; i--) s�r�klemeKayna��.add (0, "Kaynak birim " + i);
        for (int i = 2; i >= 0; i--) {
            kopyalamaHedefi.add (0, "Kopyalanacak hedef birimi " + i);
            ta��maHedefi.add (0, "Ta��nacak hedef birimi " + i);
        } // for d�ng�s� sonu...

        JPanel panel = new JPanel(); // S�r�kleme listesi paneli...
        panel.setLayout (new BoxLayout (panel, BoxLayout.Y_AXIS));
        panel.setBackground (Color.BLACK);
        s�r�klenecekListe = new JList (s�r�klemeKayna��);
        s�r�klenecekListe.setTransferHandler (new AktarmaY�netiminden());
        s�r�klenecekListe.setPrototypeCellValue ("Liste Birimi WWWWWW");
        s�r�klenecekListe.setDragEnabled (true);
        s�r�klenecekListe.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        s�r�klenecekListe.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        JLabel etiket = new JLabel ("S�R�KLEME buradan yap�lacak==>");
        etiket.setAlignmentX (0f);
        panel.add (etiket);
        etiket.setForeground (Color.WHITE);
        JScrollPane kayd�rma = new JScrollPane (s�r�klenecekListe);
        kayd�rma.setAlignmentX (0f);
        panel.add (kayd�rma);
        s�r�klenecekListe.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        add (panel, BorderLayout.WEST);

        kopyalamaListesi = new JList (kopyalamaHedefi);
        kopyalamaListesi.setTransferHandler (new AktarmaY�netimine (TransferHandler.COPY));
        kopyalamaListesi.setDropMode (DropMode.INSERT);
        kopyalamaListesi.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));

        ta��maListesi = new JList (ta��maHedefi);
        ta��maListesi.setTransferHandler (new AktarmaY�netimine (TransferHandler.MOVE));
        ta��maListesi.setDropMode (DropMode.INSERT);
        ta��maListesi.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));

        panel = new JPanel(); // B�rakma listeleri paneli...
        panel.setLayout (new BoxLayout (panel, BoxLayout.Y_AXIS));
        panel.setBackground (Color.BLACK);
        etiket = new JLabel ("==>KOPYALANAN buraya b�rak�lacak");
        etiket.setAlignmentX (0f);
        panel.add (etiket);
        etiket.setForeground (Color.WHITE);
        kayd�rma = new JScrollPane (kopyalamaListesi);
        kayd�rma.setAlignmentX (0f);
        panel.add (kayd�rma);
        etiket = new JLabel ("==>TA�INAN buraya b�rak�lacak");
        etiket.setAlignmentX (0f);
        panel.add (etiket);
        etiket.setForeground (Color.WHITE);
        kayd�rma = new JScrollPane (ta��maListesi);
        kayd�rma.setAlignmentX (0f);
        panel.add (kayd�rma);
        panel.setBorder (BorderFactory.createEmptyBorder (0, 2, 0, 0));
        add (panel, BorderLayout.CENTER);

        ((JPanel)getContentPane()).setBorder (BorderFactory.createEmptyBorder (1, 1, 1, 1));
        getContentPane().setPreferredSize (new Dimension (410, 315));
    } // J5h_2() kurucusu sonu...

    class AktarmaY�netiminden extends TransferHandler {
        private int endeks = 0;

        public int getSourceActions (JComponent par�a) {return COPY_OR_MOVE;}

        public Transferable createTransferable (JComponent par�a) {
            endeks = s�r�klenecekListe.getSelectedIndex();
            if (endeks < 0 || endeks >= s�r�klemeKayna��.getSize()) return null;
            return new StringSelection ((String)s�r�klenecekListe.getSelectedValue());
        } // createTransferable(..) haz�r metodu sonu...

        public void exportDone (JComponent par�a, Transferable aktar, int hareket) {
            if (hareket != MOVE) return;
            s�r�klemeKayna��.removeElementAt (endeks);
        } // exportDone(..) haz�r metodu sonu...
    } // AktarmaY�netiminden s�n�f� sonu...

    class AktarmaY�netimine extends TransferHandler {
        int hareket;

        public AktarmaY�netimine (int hareket) {this.hareket = hareket;} // Kurucu...

        public boolean canImport (TransferHandler.TransferSupport destek) {
            // sadece "b�rak" desteklenecek, tampondan "yap��t�r" de�il...
            if (!destek.isDrop()) return false;

            // Sadece String ithali olabilir...
            if (!destek.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;

            boolean desteklenenHareket = (hareket & destek.getSourceDropActions()) == hareket;
            if (desteklenenHareket) {
                destek.setDropAction (hareket);
                return true;
            } // if karar� sonu...

            return false;
        } // canImport(..) haz�r metodu sonu...

        public boolean importData (TransferHandler.TransferSupport destek) {
            // �thal y�netilmiyorsa belirtelim...
            if (!canImport (destek)) return false;

            // B�rak�lacak konumu alal�m...
            JList.DropLocation b�rakKonumu = (JList.DropLocation)destek.getDropLocation();

            int endeks = b�rakKonumu.getIndex();

            // Veriyi al ve ba�ar�s�zsa belirt...
            String veri;
            try {veri = (String)destek.getTransferable().getTransferData (DataFlavor.stringFlavor);
            }catch (UnsupportedFlavorException ist) {return false;
            }catch (IOException ist) {return false;
            } // try-catch.. blo�u sonu...

            JList liste = (JList)destek.getComponent();
            DefaultListModel model = (DefaultListModel)liste.getModel();
            model.insertElementAt (veri, endeks);

            Rectangle pencere = liste.getCellBounds (endeks, endeks);
            liste.scrollRectToVisible (pencere);
            liste.setSelectedIndex (endeks);
            liste.requestFocusInWindow();

            return true;
        } // importData(..) haz�r metodu sonu...
    } // AktarmaY�netimine s�n�f� sonu...

     private static void yaratVeG�sterGUI() {
        J5h_2 g�steri = new J5h_2(); // Kurucuyu �a��r�r...
        g�steri.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        g�steri.setLocation (500,100);
        g�steri.pack();
        g�steri.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

     public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
            } // run() sicim haz�r metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_2 s�n�f� sonu...