// J5h_6.java: LocationSensitiveDemo (KonumDuyarlýlýðýGösterisi) örneði.

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DropMode;
import javax.swing.BorderFactory;
import javax.swing.TransferHandler;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import java.io.IOException;

public class J5h_6 extends JFrame {
    private DefaultListModel listeModeli = new DefaultListModel();
    private int sayaç = 0;
    private JTree aðaç;
    private JComboBox açýlýrKutu;
    private DefaultTreeModel aðaçModeli;
    private TreePath aðaçYolu;

    public J5h_6() {// Kurucu...
        super ("Konum Duyarlý Sürükle ve Býrak Gösterisi");

        aðaçModeli = varsayýlýAðaçModeliniKur(); // Ýlgili metodu çaðýrýr...
        aðaç = new JTree (aðaçModeli);
        aðaç.setBorder (BorderFactory.createEmptyBorder (2, 4, 2, 4));
        aðaç.getSelectionModel().setSelectionMode (TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        aðaç.setDropMode (DropMode.ON); // Býrakýlýnca, üstündeki aðaç dalýnýn yerine geçmiyor, onun yavru dalý oluyor...
        aðaçYolu = aðaç.getPathForRow (2); // Ýsimler dalý...
        aðaç.expandRow (2); // Ýlk açýlýþta renkler ve isimler dalý açýk olacak...
        aðaç.expandRow (1);
        aðaç.setRowHeight (0);
        aðaç.setBackground (Color.CYAN);

        aðaç.setTransferHandler (new TransferHandler() {// Gerekli esgeçme hazýr metodlarýný kuralým...
            public boolean canImport (TransferHandler.TransferSupport destek) {
                // aðaç için sadece býrak desteklenecek, kopya/sürükle deðil...
                if (! destek.isDrop()) return false;

                String açýlýrKutuBirimi = (String)açýlýrKutu.getSelectedItem();
                if (açýlýrKutuBirimi.equals ("Daima")) destek.setShowDropLocation (true);
                else if (açýlýrKutuBirimi.equals ("Asla")) destek.setShowDropLocation (false);

                // Sadece JList sayaç dizgesi ithal edilebilecek...
                if (! destek.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;

                // Býrakýlacak konumu alalým...
                JTree.DropLocation býrakýlacakKonum = (JTree.DropLocation)destek.getDropLocation();
                TreePath yol = býrakýlacakKonum.getPath();

                // Geçersiz yollar veya isim ve alt dalý yollarý desteklenmiyor...
                if (yol == null || aðaçYolu.isDescendant (yol)) return false;

                return true;
            } // canImport(..) hazýr metodu sonu...

            public boolean importData (TransferHandler.TransferSupport destek) {
                // Eðer veri ithali desteklenmiyorsa (örn.isimler ve alt dallarý) dönülecek...
                if (! canImport (destek)) return false;

                // Býrakýlacak aðaç dalý konumu, yolu ve endeksi alýnacak...
                JTree.DropLocation býrakýlacakKonum = (JTree.DropLocation)destek.getDropLocation();
                TreePath yol = býrakýlacakKonum.getPath();
                int endeks = býrakýlacakKonum.getChildIndex();

                // Býrakýlacak dizge veri alýnacak; dizge deðil veya okuma hatalýysa dönülecek...
                String veri;
                try {veri = (String)destek.getTransferable().getTransferData (DataFlavor.stringFlavor);
                }catch (UnsupportedFlavorException ist) {return false;
                }catch (IOException ist) {return false;
                } // try-catch.. bloðu sonu...

                // if child index is -1, the drop was on top of the yol, so we'll
                // Eðer endeks -1, yani kök dal ise, en sona (yiyecekler dalýnýn altýna) býrakýlacak...
                if (endeks == -1) endeks = aðaç.getModel().getChildCount (yol.getLastPathComponent());

                // Son liste verisi dizgesi ana dalýn altýndaki yeni yaratýlacak yavru dalýn adý olacak...
                DefaultMutableTreeNode yeniYavruDal = new DefaultMutableTreeNode (veri);
                DefaultMutableTreeNode anaDal = (DefaultMutableTreeNode)yol.getLastPathComponent();
                aðaçModeli.insertNodeInto (yeniYavruDal, anaDal, endeks);

                // Eðer yeni yavru dal pencere altýnda kalýyorsa görünebilirliðe kaydýr...
                aðaç.makeVisible (yol.pathByAddingChild (yeniYavruDal));
                aðaç.scrollRectToVisible (aðaç.getPathBounds (yol.pathByAddingChild (yeniYavruDal)));

                // Üstteki liste dizge sayacýný bir artýr...
                listeModeli.removeAllElements();
                listeModeli.insertElementAt ("Dizge " + (++sayaç), 0);

                return true;
            } // importData(..) hazýr metodu sonu...
        }); // aða.. ifadesi sonu...

        JList sürüklenenVeri = new JList (listeModeli);
        sürüklenenVeri.setFocusable (false); // Müdahalesiz; sadece seçilebilir...
        sürüklenenVeri.setPrototypeCellValue ("Dizge 0123");
        listeModeli.insertElementAt ("Dizge " + sayaç, 0);
        sürüklenenVeri.setDragEnabled (true); // Sürüklemek üzere seçilebilir...
        sürüklenenVeri.setBorder (BorderFactory.createLoweredBevelBorder());

        JPanel panel = new JPanel();
        panel.setLayout (new BoxLayout (panel, BoxLayout.X_AXIS));
        JPanel etiketli = new JPanel();
        JLabel etiket = new JLabel ("Buradan sürükleyiniz:");
        etiket.setForeground (Color.ORANGE);
        etiketli.add (etiket);
        etiketli.add (sürüklenenVeri);
        panel.add (Box.createHorizontalStrut (4));
        panel.add (Box.createGlue());
        panel.add (etiketli);
        panel.add (Box.createGlue());
        panel.add (Box.createHorizontalStrut (4));
        panel.setBackground (Color.GREEN);
        getContentPane().add (panel, BorderLayout.NORTH);

        getContentPane().add (new JScrollPane (aðaç), BorderLayout.CENTER);

        açýlýrKutu = new JComboBox (new String[] {"Varsayýlý", "Daima", "Asla"});
        açýlýrKutu.setSelectedItem ("INSERT");

        panel = new JPanel();
        panel.setLayout (new BoxLayout (panel, BoxLayout.X_AXIS));
        etiketli = new JPanel();
        etiket = new JLabel ("Býrakýlacak konumu göster:");
        etiket.setForeground (Color.ORANGE);
        etiketli.add (etiket);
        etiketli.add (açýlýrKutu);
        panel.add (Box.createHorizontalStrut (4));
        panel.add (Box.createGlue());
        panel.add (etiketli);
        panel.add (Box.createGlue());
        panel.add (Box.createHorizontalStrut (4));
        panel.setBackground (Color.BLUE);
        getContentPane().add (panel, BorderLayout.SOUTH);

        getContentPane().setPreferredSize (new Dimension (400, 450));
    } // J5h_6() kurucusu sonu...

    private static DefaultTreeModel varsayýlýAðaçModeliniKur() {
        DefaultMutableTreeNode kök = new DefaultMutableTreeNode ("Þeyler");
        DefaultMutableTreeNode ebeveyn;
        DefaultMutableTreeNode altEbeveyn;

        ebeveyn = new DefaultMutableTreeNode ("Renkler");
        kök.add (ebeveyn);
        ebeveyn.add (new DefaultMutableTreeNode ("Kýrmýzý"));
        ebeveyn.add (new DefaultMutableTreeNode ("Sarý"));
        ebeveyn.add (new DefaultMutableTreeNode ("Yeþil"));
        ebeveyn.add (new DefaultMutableTreeNode ("Mavi"));
        ebeveyn.add (new DefaultMutableTreeNode ("Mor"));

        ebeveyn = new DefaultMutableTreeNode ("Ýsimler");
        kök.add (ebeveyn);
        altEbeveyn = new DefaultMutableTreeNode ("Erkekler");
        altEbeveyn.add (new DefaultMutableTreeNode ("Bekir"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Memet"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Mahmut"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Mustafa"));

        ebeveyn.add (altEbeveyn);
        altEbeveyn = new DefaultMutableTreeNode ("Kadýnlar");
        altEbeveyn.add (new DefaultMutableTreeNode("Fadime"));
        altEbeveyn.add (new DefaultMutableTreeNode("Haným"));
        altEbeveyn.add (new DefaultMutableTreeNode("Hatice"));
        altEbeveyn.add (new DefaultMutableTreeNode("Süheyla"));
        altEbeveyn.add (new DefaultMutableTreeNode("Zeliha"));
        altEbeveyn.add (new DefaultMutableTreeNode("Songül"));
        altEbeveyn.add (new DefaultMutableTreeNode("Sevim"));
        ebeveyn.add (altEbeveyn);

        ebeveyn = new DefaultMutableTreeNode ("Sporlar");
        kök.add (ebeveyn);
        ebeveyn.add (new DefaultMutableTreeNode ("Yüzme"));
        ebeveyn.add (new DefaultMutableTreeNode ("Bisiklet"));
        ebeveyn.add (new DefaultMutableTreeNode ("Güreþ"));
        ebeveyn.add (new DefaultMutableTreeNode ("KickBox"));

        altEbeveyn = new DefaultMutableTreeNode ("Ayaklarla");
        ebeveyn.add (altEbeveyn);
        altEbeveyn.add (new DefaultMutableTreeNode ("Walking"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Trekking"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Hiking"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Jogging"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Cross"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Marathon"));

        ebeveyn = new DefaultMutableTreeNode ("Yiyecek");
        kök.add (ebeveyn);
        ebeveyn.add (new DefaultMutableTreeNode ("Pizza"));
        ebeveyn.add (new DefaultMutableTreeNode ("Piliç"));
        ebeveyn.add (new DefaultMutableTreeNode ("Pasta"));
        altEbeveyn = new DefaultMutableTreeNode ("Meyveler");
        ebeveyn.add (altEbeveyn);
        altEbeveyn.add (new DefaultMutableTreeNode ("Muz"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Þeftali"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Üzüm"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Ýncir"));

        return new DefaultTreeModel (kök);
    } // varsayýlýAðaçModeliniKur() metodu sonu...

    private static void yaratVeGösterGUI() {
        J5h_6 gösteri = new J5h_6(); // Kurucuyu çaðýrýr...
        gösteri.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        gösteri.setLocation (500,100);
        gösteri.pack();
        gösteri.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    private static void yazýBüyüklüðünüArtýr (String tip) {
        Font fon = UIManager.getFont (tip);
        fon = fon.deriveFont (fon.getSize() + 4f);
        UIManager.put (tip, fon);
    } // yazýBüyüklüðünüArtýr(..) metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {                
                try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
                    yazýBüyüklüðünüArtýr ("Tree.font");
                    yazýBüyüklüðünüArtýr ("Label.font");
                    yazýBüyüklüðünüArtýr ("ComboBox.font");
                    yazýBüyüklüðünüArtýr ("List.font");
                }catch (Exception ist) {} // Aldýrma...

                // Koyu metal yazý tipini kapatalým...
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
            } // run() sicim hazýr metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_6 sýnýfý sonu...