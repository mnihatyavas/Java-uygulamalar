// J5h_6.java: LocationSensitiveDemo (KonumDuyarl�l���G�sterisi) �rne�i.

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
    private int saya� = 0;
    private JTree a�a�;
    private JComboBox a��l�rKutu;
    private DefaultTreeModel a�a�Modeli;
    private TreePath a�a�Yolu;

    public J5h_6() {// Kurucu...
        super ("Konum Duyarl� S�r�kle ve B�rak G�sterisi");

        a�a�Modeli = varsay�l�A�a�ModeliniKur(); // �lgili metodu �a��r�r...
        a�a� = new JTree (a�a�Modeli);
        a�a�.setBorder (BorderFactory.createEmptyBorder (2, 4, 2, 4));
        a�a�.getSelectionModel().setSelectionMode (TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        a�a�.setDropMode (DropMode.ON); // B�rak�l�nca, �st�ndeki a�a� dal�n�n yerine ge�miyor, onun yavru dal� oluyor...
        a�a�Yolu = a�a�.getPathForRow (2); // �simler dal�...
        a�a�.expandRow (2); // �lk a��l��ta renkler ve isimler dal� a��k olacak...
        a�a�.expandRow (1);
        a�a�.setRowHeight (0);
        a�a�.setBackground (Color.CYAN);

        a�a�.setTransferHandler (new TransferHandler() {// Gerekli esge�me haz�r metodlar�n� kural�m...
            public boolean canImport (TransferHandler.TransferSupport destek) {
                // a�a� i�in sadece b�rak desteklenecek, kopya/s�r�kle de�il...
                if (! destek.isDrop()) return false;

                String a��l�rKutuBirimi = (String)a��l�rKutu.getSelectedItem();
                if (a��l�rKutuBirimi.equals ("Daima")) destek.setShowDropLocation (true);
                else if (a��l�rKutuBirimi.equals ("Asla")) destek.setShowDropLocation (false);

                // Sadece JList saya� dizgesi ithal edilebilecek...
                if (! destek.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;

                // B�rak�lacak konumu alal�m...
                JTree.DropLocation b�rak�lacakKonum = (JTree.DropLocation)destek.getDropLocation();
                TreePath yol = b�rak�lacakKonum.getPath();

                // Ge�ersiz yollar veya isim ve alt dal� yollar� desteklenmiyor...
                if (yol == null || a�a�Yolu.isDescendant (yol)) return false;

                return true;
            } // canImport(..) haz�r metodu sonu...

            public boolean importData (TransferHandler.TransferSupport destek) {
                // E�er veri ithali desteklenmiyorsa (�rn.isimler ve alt dallar�) d�n�lecek...
                if (! canImport (destek)) return false;

                // B�rak�lacak a�a� dal� konumu, yolu ve endeksi al�nacak...
                JTree.DropLocation b�rak�lacakKonum = (JTree.DropLocation)destek.getDropLocation();
                TreePath yol = b�rak�lacakKonum.getPath();
                int endeks = b�rak�lacakKonum.getChildIndex();

                // B�rak�lacak dizge veri al�nacak; dizge de�il veya okuma hatal�ysa d�n�lecek...
                String veri;
                try {veri = (String)destek.getTransferable().getTransferData (DataFlavor.stringFlavor);
                }catch (UnsupportedFlavorException ist) {return false;
                }catch (IOException ist) {return false;
                } // try-catch.. blo�u sonu...

                // if child index is -1, the drop was on top of the yol, so we'll
                // E�er endeks -1, yani k�k dal ise, en sona (yiyecekler dal�n�n alt�na) b�rak�lacak...
                if (endeks == -1) endeks = a�a�.getModel().getChildCount (yol.getLastPathComponent());

                // Son liste verisi dizgesi ana dal�n alt�ndaki yeni yarat�lacak yavru dal�n ad� olacak...
                DefaultMutableTreeNode yeniYavruDal = new DefaultMutableTreeNode (veri);
                DefaultMutableTreeNode anaDal = (DefaultMutableTreeNode)yol.getLastPathComponent();
                a�a�Modeli.insertNodeInto (yeniYavruDal, anaDal, endeks);

                // E�er yeni yavru dal pencere alt�nda kal�yorsa g�r�nebilirli�e kayd�r...
                a�a�.makeVisible (yol.pathByAddingChild (yeniYavruDal));
                a�a�.scrollRectToVisible (a�a�.getPathBounds (yol.pathByAddingChild (yeniYavruDal)));

                // �stteki liste dizge sayac�n� bir art�r...
                listeModeli.removeAllElements();
                listeModeli.insertElementAt ("Dizge " + (++saya�), 0);

                return true;
            } // importData(..) haz�r metodu sonu...
        }); // a�a.. ifadesi sonu...

        JList s�r�klenenVeri = new JList (listeModeli);
        s�r�klenenVeri.setFocusable (false); // M�dahalesiz; sadece se�ilebilir...
        s�r�klenenVeri.setPrototypeCellValue ("Dizge 0123");
        listeModeli.insertElementAt ("Dizge " + saya�, 0);
        s�r�klenenVeri.setDragEnabled (true); // S�r�klemek �zere se�ilebilir...
        s�r�klenenVeri.setBorder (BorderFactory.createLoweredBevelBorder());

        JPanel panel = new JPanel();
        panel.setLayout (new BoxLayout (panel, BoxLayout.X_AXIS));
        JPanel etiketli = new JPanel();
        JLabel etiket = new JLabel ("Buradan s�r�kleyiniz:");
        etiket.setForeground (Color.ORANGE);
        etiketli.add (etiket);
        etiketli.add (s�r�klenenVeri);
        panel.add (Box.createHorizontalStrut (4));
        panel.add (Box.createGlue());
        panel.add (etiketli);
        panel.add (Box.createGlue());
        panel.add (Box.createHorizontalStrut (4));
        panel.setBackground (Color.GREEN);
        getContentPane().add (panel, BorderLayout.NORTH);

        getContentPane().add (new JScrollPane (a�a�), BorderLayout.CENTER);

        a��l�rKutu = new JComboBox (new String[] {"Varsay�l�", "Daima", "Asla"});
        a��l�rKutu.setSelectedItem ("INSERT");

        panel = new JPanel();
        panel.setLayout (new BoxLayout (panel, BoxLayout.X_AXIS));
        etiketli = new JPanel();
        etiket = new JLabel ("B�rak�lacak konumu g�ster:");
        etiket.setForeground (Color.ORANGE);
        etiketli.add (etiket);
        etiketli.add (a��l�rKutu);
        panel.add (Box.createHorizontalStrut (4));
        panel.add (Box.createGlue());
        panel.add (etiketli);
        panel.add (Box.createGlue());
        panel.add (Box.createHorizontalStrut (4));
        panel.setBackground (Color.BLUE);
        getContentPane().add (panel, BorderLayout.SOUTH);

        getContentPane().setPreferredSize (new Dimension (400, 450));
    } // J5h_6() kurucusu sonu...

    private static DefaultTreeModel varsay�l�A�a�ModeliniKur() {
        DefaultMutableTreeNode k�k = new DefaultMutableTreeNode ("�eyler");
        DefaultMutableTreeNode ebeveyn;
        DefaultMutableTreeNode altEbeveyn;

        ebeveyn = new DefaultMutableTreeNode ("Renkler");
        k�k.add (ebeveyn);
        ebeveyn.add (new DefaultMutableTreeNode ("K�rm�z�"));
        ebeveyn.add (new DefaultMutableTreeNode ("Sar�"));
        ebeveyn.add (new DefaultMutableTreeNode ("Ye�il"));
        ebeveyn.add (new DefaultMutableTreeNode ("Mavi"));
        ebeveyn.add (new DefaultMutableTreeNode ("Mor"));

        ebeveyn = new DefaultMutableTreeNode ("�simler");
        k�k.add (ebeveyn);
        altEbeveyn = new DefaultMutableTreeNode ("Erkekler");
        altEbeveyn.add (new DefaultMutableTreeNode ("Bekir"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Memet"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Mahmut"));
        altEbeveyn.add (new DefaultMutableTreeNode ("Mustafa"));

        ebeveyn.add (altEbeveyn);
        altEbeveyn = new DefaultMutableTreeNode ("Kad�nlar");
        altEbeveyn.add (new DefaultMutableTreeNode("Fadime"));
        altEbeveyn.add (new DefaultMutableTreeNode("Han�m"));
        altEbeveyn.add (new DefaultMutableTreeNode("Hatice"));
        altEbeveyn.add (new DefaultMutableTreeNode("S�heyla"));
        altEbeveyn.add (new DefaultMutableTreeNode("Zeliha"));
        altEbeveyn.add (new DefaultMutableTreeNode("Song�l"));
        altEbeveyn.add (new DefaultMutableTreeNode("Sevim"));
        ebeveyn.add (altEbeveyn);

        ebeveyn = new DefaultMutableTreeNode ("Sporlar");
        k�k.add (ebeveyn);
        ebeveyn.add (new DefaultMutableTreeNode ("Y�zme"));
        ebeveyn.add (new DefaultMutableTreeNode ("Bisiklet"));
        ebeveyn.add (new DefaultMutableTreeNode ("G�re�"));
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
        k�k.add (ebeveyn);
        ebeveyn.add (new DefaultMutableTreeNode ("Pizza"));
        ebeveyn.add (new DefaultMutableTreeNode ("Pili�"));
        ebeveyn.add (new DefaultMutableTreeNode ("Pasta"));
        altEbeveyn = new DefaultMutableTreeNode ("Meyveler");
        ebeveyn.add (altEbeveyn);
        altEbeveyn.add (new DefaultMutableTreeNode ("Muz"));
        altEbeveyn.add (new DefaultMutableTreeNode ("�eftali"));
        altEbeveyn.add (new DefaultMutableTreeNode ("�z�m"));
        altEbeveyn.add (new DefaultMutableTreeNode ("�ncir"));

        return new DefaultTreeModel (k�k);
    } // varsay�l�A�a�ModeliniKur() metodu sonu...

    private static void yaratVeG�sterGUI() {
        J5h_6 g�steri = new J5h_6(); // Kurucuyu �a��r�r...
        g�steri.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        g�steri.setLocation (500,100);
        g�steri.pack();
        g�steri.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    private static void yaz�B�y�kl���n�Art�r (String tip) {
        Font fon = UIManager.getFont (tip);
        fon = fon.deriveFont (fon.getSize() + 4f);
        UIManager.put (tip, fon);
    } // yaz�B�y�kl���n�Art�r(..) metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {                
                try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
                    yaz�B�y�kl���n�Art�r ("Tree.font");
                    yaz�B�y�kl���n�Art�r ("Label.font");
                    yaz�B�y�kl���n�Art�r ("ComboBox.font");
                    yaz�B�y�kl���n�Art�r ("List.font");
                }catch (Exception ist) {} // Ald�rma...

                // Koyu metal yaz� tipini kapatal�m...
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
            } // run() sicim haz�r metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_6 s�n�f� sonu...