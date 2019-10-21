// J5h_4.java: FillViewportHeightDemo (DoldurGörünenYüksekliðiGösterisi) örneði.

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
    private int sayaç = 0;
    private JCheckBoxMenuItem çentikKutusu;
    private JTable tablo;
    private DefaultTableModel tabloModeli;
    private DefaultListModel listeModeli = new DefaultListModel();

    public J5h_4() {// Kurucu...
        super ("Boþ Tabloya SvB Gösterisi"); // SvB:SürükleVeBýrak (DnD:DragAndDrop)...

        tabloModeli = varsayýlýTabloModeliniAl();
        tablo = new JTable (tabloModeli);
        tablo.setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Ne iþe yarayacak?..
        tablo.setDropMode (DropMode.INSERT_ROWS);
        tablo.setBackground (Color.PINK);

        tablo.setTransferHandler (new TransferHandler() {// Ayrý sýnýf yerine, kurucu içinde...
            public boolean canImport (TransferSupport aktarmaDesteði) {
                // Sadece "Býrak" desteklenecek, "Kes ve Yapýþtýr" deðil...
                if (!aktarmaDesteði.isDrop()) return false;

                // Sadece Strings ithali desteklenecek...
                if (!aktarmaDesteði.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;

                return true;
            } // canImport(..) hazýr metodu sonu...

            public boolean importData (TransferSupport aktarmaDesteði) {
                // Aktarma desteði yönetilmiyorsa, veri ithali gerekmez...
                if (!canImport (aktarmaDesteði)) return false;

                // Býrakma konumunun satýr no'su tespit ediliyor...
                JTable.DropLocation býrakmaKonumu = (JTable.DropLocation)aktarmaDesteði.getDropLocation();
                int satýrNo = býrakmaKonumu.getRow();

                // Aktarýlacak (dizge) veri alýnýyor...
                String aktarýlacakVeri;
                try {aktarýlacakVeri = (String)aktarmaDesteði.getTransferable().getTransferData (DataFlavor.stringFlavor);
                }catch (UnsupportedFlavorException ist) {return false;
                }catch (IOException ist) {return false;
                } // try-catch.. bloðu sonu...

                String[] satýrVerileri = aktarýlacakVeri.split (",");
                tabloModeli.insertRow (satýrNo, satýrVerileri);

                Rectangle görüntülenecekTabloSatýrý = tablo.getCellRect (satýrNo, 0, false);
                if (görüntülenecekTabloSatýrý != null) tablo.scrollRectToVisible (görüntülenecekTabloSatýrý);

                // Varsayýlý liste modeli bir sonraki aktarma için boþaltýlýp, yeni sayaç no'larý bir artýrýlýyor...
                listeModeli.removeAllElements();
                listeModeli.insertElementAt (birsonrakiDizgeyiAl (sayaç++), 0);

                return true;
            } // importData(..) hazýr metodu sonu...
        }); // tab.. ifadesi sonu...

        JList sürüklenecekListeElemaný = new JList (listeModeli);
        sürüklenecekListeElemaný.setFocusable (false); // Müdahalesiz...
        sürüklenecekListeElemaný.setPrototypeCellValue (birsonrakiDizgeyiAl (60));
        listeModeli.insertElementAt (birsonrakiDizgeyiAl (sayaç++), 0);
        sürüklenecekListeElemaný.setDragEnabled (true); // Tek tek sürükleme olanaklý...
        sürüklenecekListeElemaný.setBorder (BorderFactory.createLoweredBevelBorder());

        sürüklenecekListeElemaný.addMouseListener (new MouseAdapter() {
            public void mouseClicked (MouseEvent olay) {
                if (SwingUtilities.isLeftMouseButton (olay) && olay.getClickCount() % 2 == 0) {// Fare sol çift týklama ise...
                    String metin = (String)listeModeli.getElementAt (0);
                    String[] satýrVerileri = metin.split (",");
                    tabloModeli.insertRow (tablo.getRowCount(), satýrVerileri);
                    listeModeli.removeAllElements();
                    listeModeli.insertElementAt (birsonrakiDizgeyiAl (sayaç++), 0);
                } // if kararý sonu...
            } // mouseClicked(..) hazýr metodu sonu...
        }); // sür.. ifadesi sonu...

        JPanel panel = new JPanel();
        panel.setLayout (new BoxLayout (panel, BoxLayout.X_AXIS));
        JPanel etiketliListePaneli = new JPanel();
        JLabel etiket = new JLabel ("Buradan sürükle / çift-týkla:");
        etiket.setForeground (Color.RED);
        etiketliListePaneli.add (etiket);
        etiketliListePaneli.add (sürüklenecekListeElemaný);
        panel.add (Box.createHorizontalStrut (4));
        panel.add (Box.createGlue());
        panel.add (etiketliListePaneli);
        panel.add (Box.createGlue());
        panel.add (Box.createHorizontalStrut (4));
        getContentPane().add (panel, BorderLayout.NORTH);

        JScrollPane tabloKaydýrmasý = new JScrollPane (tablo);
        getContentPane().add (tabloKaydýrmasý, BorderLayout.CENTER);

        JMenuBar menüÇubuðu = new JMenuBar();
        menüÇubuðu.setBackground (Color.BLACK);
        JMenu tercihler = new JMenu ("Tercihleriniz");
        tercihler.setForeground (Color.WHITE);
        menüÇubuðu.add (tercihler);
        setJMenuBar (menüÇubuðu);

        JMenuItem silBaþtan = new JMenuItem ("Yeniden baþlat");
        silBaþtan.addActionListener (this); // Dinleyiciye duyarlý...
        tercihler.add (silBaþtan);

        çentikKutusu = new JCheckBoxMenuItem ("Görüntü Yüksekliði Dolsun"); // Ne iþe yarýyorsa!..
        çentikKutusu.addActionListener (this); // Dinleyiciye duyarlý...
        tercihler.add (çentikKutusu);

        getContentPane().setPreferredSize (new Dimension (270, 180));
    } // J5h_4() kurucusu sonu...

    private static String birsonrakiDizgeyiAl (int sayaç) {
        StringBuffer tampon = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            tampon.append (String.valueOf (sayaç));
            tampon.append (",");
        } // for döngüsü sonu...

        // En sondaki virgülü almayalým...
        tampon.deleteCharAt (tampon.length() - 1);
        return tampon.toString();
    } // birsonrakiDizgeyiAl(..) metodu sonu...

    private static DefaultTableModel varsayýlýTabloModeliniAl() {
        String[] kolonlar = {"Hatice", "Süheyla", "Zeliha", "Songül", "Sevim"};
        return new DefaultTableModel (null, kolonlar);
    } // varsayýlýTabloModeliniAl() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        if (olay.getSource() == çentikKutusu) tablo.setFillsViewportHeight (çentikKutusu.isSelected()); // Ne iþe yarýyorsa?..
        else {// Yeniden baþlat...
            tabloModeli.setRowCount (0);
            sayaç = 0;
            listeModeli.removeAllElements();
            listeModeli.insertElementAt (birsonrakiDizgeyiAl (sayaç++), 0);
        } // else kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        J5h_4 gösteri = new J5h_4(); // Kurucuyu çaðýrýr...
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
            } // run() hazýr sicim metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_4 sýnýfý sonu...