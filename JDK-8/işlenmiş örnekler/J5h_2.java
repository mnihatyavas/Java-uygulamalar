// J5h_2.java: ChooseDropActionDemo (SeçilenSürükleHareketiGösterisi) örneği.

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
    DefaultListModel sürüklemeKaynağı = new DefaultListModel();
    DefaultListModel kopyalamaHedefi = new DefaultListModel();
    DefaultListModel taşımaHedefi = new DefaultListModel();
    JList sürüklenecekListe;
    JList kopyalamaListesi;
    JList taşımaListesi;

    public J5h_2() {// Kurucu...
        super ("Seçilen Sürükle Hareketi Gösterisi");

        for (int i = 15; i >= 0; i--) sürüklemeKaynağı.add (0, "Kaynak birim " + i);
        for (int i = 2; i >= 0; i--) {
            kopyalamaHedefi.add (0, "Kopyalanacak hedef birimi " + i);
            taşımaHedefi.add (0, "Taşınacak hedef birimi " + i);
        } // for döngüsü sonu...

        JPanel panel = new JPanel(); // Sürükleme listesi paneli...
        panel.setLayout (new BoxLayout (panel, BoxLayout.Y_AXIS));
        panel.setBackground (Color.BLACK);
        sürüklenecekListe = new JList (sürüklemeKaynağı);
        sürüklenecekListe.setTransferHandler (new AktarmaYönetiminden());
        sürüklenecekListe.setPrototypeCellValue ("Liste Birimi WWWWWW");
        sürüklenecekListe.setDragEnabled (true);
        sürüklenecekListe.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        sürüklenecekListe.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        JLabel etiket = new JLabel ("SÜRÜKLEME buradan yapılacak==>");
        etiket.setAlignmentX (0f);
        panel.add (etiket);
        etiket.setForeground (Color.WHITE);
        JScrollPane kaydırma = new JScrollPane (sürüklenecekListe);
        kaydırma.setAlignmentX (0f);
        panel.add (kaydırma);
        sürüklenecekListe.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        add (panel, BorderLayout.WEST);

        kopyalamaListesi = new JList (kopyalamaHedefi);
        kopyalamaListesi.setTransferHandler (new AktarmaYönetimine (TransferHandler.COPY));
        kopyalamaListesi.setDropMode (DropMode.INSERT);
        kopyalamaListesi.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));

        taşımaListesi = new JList (taşımaHedefi);
        taşımaListesi.setTransferHandler (new AktarmaYönetimine (TransferHandler.MOVE));
        taşımaListesi.setDropMode (DropMode.INSERT);
        taşımaListesi.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));

        panel = new JPanel(); // Bırakma listeleri paneli...
        panel.setLayout (new BoxLayout (panel, BoxLayout.Y_AXIS));
        panel.setBackground (Color.BLACK);
        etiket = new JLabel ("==>KOPYALANAN buraya bırakılacak");
        etiket.setAlignmentX (0f);
        panel.add (etiket);
        etiket.setForeground (Color.WHITE);
        kaydırma = new JScrollPane (kopyalamaListesi);
        kaydırma.setAlignmentX (0f);
        panel.add (kaydırma);
        etiket = new JLabel ("==>TAŞINAN buraya bırakılacak");
        etiket.setAlignmentX (0f);
        panel.add (etiket);
        etiket.setForeground (Color.WHITE);
        kaydırma = new JScrollPane (taşımaListesi);
        kaydırma.setAlignmentX (0f);
        panel.add (kaydırma);
        panel.setBorder (BorderFactory.createEmptyBorder (0, 2, 0, 0));
        add (panel, BorderLayout.CENTER);

        ((JPanel)getContentPane()).setBorder (BorderFactory.createEmptyBorder (1, 1, 1, 1));
        getContentPane().setPreferredSize (new Dimension (410, 315));
    } // J5h_2() kurucusu sonu...

    class AktarmaYönetiminden extends TransferHandler {
        private int endeks = 0;

        public int getSourceActions (JComponent parça) {return COPY_OR_MOVE;}

        public Transferable createTransferable (JComponent parça) {
            endeks = sürüklenecekListe.getSelectedIndex();
            if (endeks < 0 || endeks >= sürüklemeKaynağı.getSize()) return null;
            return new StringSelection ((String)sürüklenecekListe.getSelectedValue());
        } // createTransferable(..) hazır metodu sonu...

        public void exportDone (JComponent parça, Transferable aktar, int hareket) {
            if (hareket != MOVE) return;
            sürüklemeKaynağı.removeElementAt (endeks);
        } // exportDone(..) hazır metodu sonu...
    } // AktarmaYönetiminden sınıfı sonu...

    class AktarmaYönetimine extends TransferHandler {
        int hareket;

        public AktarmaYönetimine (int hareket) {this.hareket = hareket;} // Kurucu...

        public boolean canImport (TransferHandler.TransferSupport destek) {
            // sadece "bırak" desteklenecek, tampondan "yapıştır" değil...
            if (!destek.isDrop()) return false;

            // Sadece String ithali olabilir...
            if (!destek.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;

            boolean desteklenenHareket = (hareket & destek.getSourceDropActions()) == hareket;
            if (desteklenenHareket) {
                destek.setDropAction (hareket);
                return true;
            } // if kararı sonu...

            return false;
        } // canImport(..) hazır metodu sonu...

        public boolean importData (TransferHandler.TransferSupport destek) {
            // İthal yönetilmiyorsa belirtelim...
            if (!canImport (destek)) return false;

            // Bırakılacak konumu alalım...
            JList.DropLocation bırakKonumu = (JList.DropLocation)destek.getDropLocation();

            int endeks = bırakKonumu.getIndex();

            // Veriyi al ve başarısızsa belirt...
            String veri;
            try {veri = (String)destek.getTransferable().getTransferData (DataFlavor.stringFlavor);
            }catch (UnsupportedFlavorException ist) {return false;
            }catch (IOException ist) {return false;
            } // try-catch.. bloğu sonu...

            JList liste = (JList)destek.getComponent();
            DefaultListModel model = (DefaultListModel)liste.getModel();
            model.insertElementAt (veri, endeks);

            Rectangle pencere = liste.getCellBounds (endeks, endeks);
            liste.scrollRectToVisible (pencere);
            liste.setSelectedIndex (endeks);
            liste.requestFocusInWindow();

            return true;
        } // importData(..) hazır metodu sonu...
    } // AktarmaYönetimine sınıfı sonu...

     private static void yaratVeGösterGUI() {
        J5h_2 gösteri = new J5h_2(); // Kurucuyu çağırır...
        gösteri.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        gösteri.setLocation (500,100);
        gösteri.pack();
        gösteri.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

     public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
            } // run() sicim hazır metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_2 sınıfı sonu...