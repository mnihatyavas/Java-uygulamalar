// J5h_4.java: FillViewportHeightDemo (DoldurG�r�nenY�ksekli�iG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.TransferHandler;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.*;

import java.io.IOException;

public class J5h_4 extends JFrame implements ActionListener {
    private int saya� = 0;
    private JCheckBoxMenuItem �entikKutusu;
    private JTable tablo;
    private DefaultTableModel tabloModeli;
    private DefaultListModel listeModeli = new DefaultListModel();

    public J5h_4() {// Kurucu...
        super ("Bo� Tabloya SvB G�sterisi"); // SvB:S�r�kleVeB�rak (DnD:DragAndDrop)...

        tabloModeli = varsay�l�TabloModeliniAl();
        tablo = new JTable (tabloModeli);
        tablo.setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Ne i�e yarayacak?..
        tablo.setDropMode (DropMode.INSERT_ROWS);
        tablo.setBackground (Color.PINK);

        tablo.setTransferHandler (new TransferHandler() {// Ayr� s�n�f yerine, kurucu i�inde...
            public boolean canImport (TransferSupport aktarmaDeste�i) {
                // Sadece "B�rak" desteklenecek, "Kes ve Yap��t�r" de�il...
                if (!aktarmaDeste�i.isDrop()) return false;

                // Sadece Strings ithali desteklenecek...
                if (!aktarmaDeste�i.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;

                return true;
            } // canImport(..) haz�r metodu sonu...

            public boolean importData (TransferSupport aktarmaDeste�i) {
                // Aktarma deste�i y�netilmiyorsa, veri ithali gerekmez...
                if (!canImport (aktarmaDeste�i)) return false;

                // B�rakma konumunun sat�r no'su tespit ediliyor...
                JTable.DropLocation b�rakmaKonumu = (JTable.DropLocation)aktarmaDeste�i.getDropLocation();
                int sat�rNo = b�rakmaKonumu.getRow();

                // Aktar�lacak (dizge) veri al�n�yor...
                String aktar�lacakVeri;
                try {aktar�lacakVeri = (String)aktarmaDeste�i.getTransferable().getTransferData (DataFlavor.stringFlavor);
                }catch (UnsupportedFlavorException ist) {return false;
                }catch (IOException ist) {return false;
                } // try-catch.. blo�u sonu...

                String[] sat�rVerileri = aktar�lacakVeri.split (",");
                tabloModeli.insertRow (sat�rNo, sat�rVerileri);

                Rectangle g�r�nt�lenecekTabloSat�r� = tablo.getCellRect (sat�rNo, 0, false);
                if (g�r�nt�lenecekTabloSat�r� != null) tablo.scrollRectToVisible (g�r�nt�lenecekTabloSat�r�);

                // Varsay�l� liste modeli bir sonraki aktarma i�in bo�alt�l�p, yeni saya� no'lar� bir art�r�l�yor...
                listeModeli.removeAllElements();
                listeModeli.insertElementAt (birsonrakiDizgeyiAl (saya�++), 0);

                return true;
            } // importData(..) haz�r metodu sonu...
        }); // tab.. ifadesi sonu...

        JList s�r�klenecekListeEleman� = new JList (listeModeli);
        s�r�klenecekListeEleman�.setFocusable (false); // M�dahalesiz...
        s�r�klenecekListeEleman�.setPrototypeCellValue (birsonrakiDizgeyiAl (60));
        listeModeli.insertElementAt (birsonrakiDizgeyiAl (saya�++), 0);
        s�r�klenecekListeEleman�.setDragEnabled (true); // Tek tek s�r�kleme olanakl�...
        s�r�klenecekListeEleman�.setBorder (BorderFactory.createLoweredBevelBorder());

        s�r�klenecekListeEleman�.addMouseListener (new MouseAdapter() {
            public void mouseClicked (MouseEvent olay) {
                if (SwingUtilities.isLeftMouseButton (olay) && olay.getClickCount() % 2 == 0) {// Fare sol �ift t�klama ise...
                    String metin = (String)listeModeli.getElementAt (0);
                    String[] sat�rVerileri = metin.split (",");
                    tabloModeli.insertRow (tablo.getRowCount(), sat�rVerileri);
                    listeModeli.removeAllElements();
                    listeModeli.insertElementAt (birsonrakiDizgeyiAl (saya�++), 0);
                } // if karar� sonu...
            } // mouseClicked(..) haz�r metodu sonu...
        }); // s�r.. ifadesi sonu...

        JPanel panel = new JPanel();
        panel.setLayout (new BoxLayout (panel, BoxLayout.X_AXIS));
        JPanel etiketliListePaneli = new JPanel();
        JLabel etiket = new JLabel ("Buradan s�r�kle / �ift-t�kla:");
        etiket.setForeground (Color.RED);
        etiketliListePaneli.add (etiket);
        etiketliListePaneli.add (s�r�klenecekListeEleman�);
        panel.add (Box.createHorizontalStrut (4));
        panel.add (Box.createGlue());
        panel.add (etiketliListePaneli);
        panel.add (Box.createGlue());
        panel.add (Box.createHorizontalStrut (4));
        getContentPane().add (panel, BorderLayout.NORTH);

        JScrollPane tabloKayd�rmas� = new JScrollPane (tablo);
        getContentPane().add (tabloKayd�rmas�, BorderLayout.CENTER);

        JMenuBar men��ubu�u = new JMenuBar();
        men��ubu�u.setBackground (Color.BLACK);
        JMenu tercihler = new JMenu ("Tercihleriniz");
        tercihler.setForeground (Color.WHITE);
        men��ubu�u.add (tercihler);
        setJMenuBar (men��ubu�u);

        JMenuItem silBa�tan = new JMenuItem ("Yeniden ba�lat");
        silBa�tan.addActionListener (this); // Dinleyiciye duyarl�...
        tercihler.add (silBa�tan);

        �entikKutusu = new JCheckBoxMenuItem ("G�r�nt� Y�ksekli�i Dolsun"); // Ne i�e yar�yorsa!..
        �entikKutusu.addActionListener (this); // Dinleyiciye duyarl�...
        tercihler.add (�entikKutusu);

        getContentPane().setPreferredSize (new Dimension (270, 180));
    } // J5h_4() kurucusu sonu...

    private static String birsonrakiDizgeyiAl (int saya�) {
        StringBuffer tampon = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            tampon.append (String.valueOf (saya�));
            tampon.append (",");
        } // for d�ng�s� sonu...

        // En sondaki virg�l� almayal�m...
        tampon.deleteCharAt (tampon.length() - 1);
        return tampon.toString();
    } // birsonrakiDizgeyiAl(..) metodu sonu...

    private static DefaultTableModel varsay�l�TabloModeliniAl() {
        String[] kolonlar = {"Hatice", "S�heyla", "Zeliha", "Song�l", "Sevim"};
        return new DefaultTableModel (null, kolonlar);
    } // varsay�l�TabloModeliniAl() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        if (olay.getSource() == �entikKutusu) tablo.setFillsViewportHeight (�entikKutusu.isSelected()); // Ne i�e yar�yorsa?..
        else {// Yeniden ba�lat...
            tabloModeli.setRowCount (0);
            saya� = 0;
            listeModeli.removeAllElements();
            listeModeli.insertElementAt (birsonrakiDizgeyiAl (saya�++), 0);
        } // else karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        J5h_4 g�steri = new J5h_4(); // Kurucuyu �a��r�r...
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
            } // run() haz�r sicim metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_4 s�n�f� sonu...