// J5h_2.java: ChooseDropActionDemo (SeçilenSürükleHareketiGösterisi) örneði.

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
    DefaultListModel sürüklemeKaynaðý = new DefaultListModel();
    DefaultListModel kopyalamaHedefi = new DefaultListModel();
    DefaultListModel taþýmaHedefi = new DefaultListModel();
    JList sürüklenecekListe;
    JList kopyalamaListesi;
    JList taþýmaListesi;

    public J5h_2() {// Kurucu...
        super ("Seçilen Sürükle Hareketi Gösterisi");

        for (int i = 15; i >= 0; i--) sürüklemeKaynaðý.add (0, "Kaynak birim " + i);
        for (int i = 2; i >= 0; i--) {
            kopyalamaHedefi.add (0, "Kopyalanacak hedef birimi " + i);
            taþýmaHedefi.add (0, "Taþýnacak hedef birimi " + i);
        } // for döngüsü sonu...

        JPanel panel = new JPanel(); // Sürükleme listesi paneli...
        panel.setLayout (new BoxLayout (panel, BoxLayout.Y_AXIS));
        panel.setBackground (Color.BLACK);
        sürüklenecekListe = new JList (sürüklemeKaynaðý);
        sürüklenecekListe.setTransferHandler (new AktarmaYönetiminden());
        sürüklenecekListe.setPrototypeCellValue ("Liste Birimi WWWWWW");
        sürüklenecekListe.setDragEnabled (true);
        sürüklenecekListe.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        sürüklenecekListe.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        JLabel etiket = new JLabel ("SÜRÜKLEME buradan yapýlacak==>");
        etiket.setAlignmentX (0f);
        panel.add (etiket);
        etiket.setForeground (Color.WHITE);
        JScrollPane kaydýrma = new JScrollPane (sürüklenecekListe);
        kaydýrma.setAlignmentX (0f);
        panel.add (kaydýrma);
        sürüklenecekListe.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        add (panel, BorderLayout.WEST);

        kopyalamaListesi = new JList (kopyalamaHedefi);
        kopyalamaListesi.setTransferHandler (new AktarmaYönetimine (TransferHandler.COPY));
        kopyalamaListesi.setDropMode (DropMode.INSERT);
        kopyalamaListesi.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));

        taþýmaListesi = new JList (taþýmaHedefi);
        taþýmaListesi.setTransferHandler (new AktarmaYönetimine (TransferHandler.MOVE));
        taþýmaListesi.setDropMode (DropMode.INSERT);
        taþýmaListesi.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));

        panel = new JPanel(); // Býrakma listeleri paneli...
        panel.setLayout (new BoxLayout (panel, BoxLayout.Y_AXIS));
        panel.setBackground (Color.BLACK);
        etiket = new JLabel ("==>KOPYALANAN buraya býrakýlacak");
        etiket.setAlignmentX (0f);
        panel.add (etiket);
        etiket.setForeground (Color.WHITE);
        kaydýrma = new JScrollPane (kopyalamaListesi);
        kaydýrma.setAlignmentX (0f);
        panel.add (kaydýrma);
        etiket = new JLabel ("==>TAÞINAN buraya býrakýlacak");
        etiket.setAlignmentX (0f);
        panel.add (etiket);
        etiket.setForeground (Color.WHITE);
        kaydýrma = new JScrollPane (taþýmaListesi);
        kaydýrma.setAlignmentX (0f);
        panel.add (kaydýrma);
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
            if (endeks < 0 || endeks >= sürüklemeKaynaðý.getSize()) return null;
            return new StringSelection ((String)sürüklenecekListe.getSelectedValue());
        } // createTransferable(..) hazýr metodu sonu...

        public void exportDone (JComponent parça, Transferable aktar, int hareket) {
            if (hareket != MOVE) return;
            sürüklemeKaynaðý.removeElementAt (endeks);
        } // exportDone(..) hazýr metodu sonu...
    } // AktarmaYönetiminden sýnýfý sonu...

    class AktarmaYönetimine extends TransferHandler {
        int hareket;

        public AktarmaYönetimine (int hareket) {this.hareket = hareket;} // Kurucu...

        public boolean canImport (TransferHandler.TransferSupport destek) {
            // sadece "býrak" desteklenecek, tampondan "yapýþtýr" deðil...
            if (!destek.isDrop()) return false;

            // Sadece String ithali olabilir...
            if (!destek.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;

            boolean desteklenenHareket = (hareket & destek.getSourceDropActions()) == hareket;
            if (desteklenenHareket) {
                destek.setDropAction (hareket);
                return true;
            } // if kararý sonu...

            return false;
        } // canImport(..) hazýr metodu sonu...

        public boolean importData (TransferHandler.TransferSupport destek) {
            // Ýthal yönetilmiyorsa belirtelim...
            if (!canImport (destek)) return false;

            // Býrakýlacak konumu alalým...
            JList.DropLocation býrakKonumu = (JList.DropLocation)destek.getDropLocation();

            int endeks = býrakKonumu.getIndex();

            // Veriyi al ve baþarýsýzsa belirt...
            String veri;
            try {veri = (String)destek.getTransferable().getTransferData (DataFlavor.stringFlavor);
            }catch (UnsupportedFlavorException ist) {return false;
            }catch (IOException ist) {return false;
            } // try-catch.. bloðu sonu...

            JList liste = (JList)destek.getComponent();
            DefaultListModel model = (DefaultListModel)liste.getModel();
            model.insertElementAt (veri, endeks);

            Rectangle pencere = liste.getCellBounds (endeks, endeks);
            liste.scrollRectToVisible (pencere);
            liste.setSelectedIndex (endeks);
            liste.requestFocusInWindow();

            return true;
        } // importData(..) hazýr metodu sonu...
    } // AktarmaYönetimine sýnýfý sonu...

     private static void yaratVeGösterGUI() {
        J5h_2 gösteri = new J5h_2(); // Kurucuyu çaðýrýr...
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
            } // run() sicim hazýr metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_2 sýnýfý sonu...