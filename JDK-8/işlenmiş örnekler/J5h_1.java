// J5h_1.java: BasicDnD (TemelS�r�kleVeB�rak) �rne�i.

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.TransferHandler;
import javax.swing.DropMode;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.table.DefaultTableModel;

public class J5h_1 extends JPanel implements ActionListener {
    private static JFrame �er�eve;
    private JTextArea metinAlan�;
    private JTextField metinSat�r�;
    private JList liste;
    private JTable tablo;
    private JTree a�a�;
    private JColorChooser renkSe�ici;
    private JCheckBox �entikSvB; // DragAndDropDnD: S�r�kleVeB�rak_SvB

    public J5h_1() {// Kurucu...
        super (new BorderLayout());
        JPanel solKanatPaneli = dikeyKutuPaneliniYarat();
        JPanel sa�KanatPaneli = dikeyKutuPaneliniYarat();

        // Bir tablo modeli aratal�m...
        DefaultTableModel tabloModeli = new DefaultTableModel();
        tabloModeli.addColumn ("Kolon 0");
        tabloModeli.addColumn ("Kolon 1");
        tabloModeli.addColumn ("Kolon 2");
        tabloModeli.addColumn ("Kolon 3");
        tabloModeli.addRow (new String[]{"Tablo 00", "Tablo 01", "Tablo 02", "Tablo 03"});
        tabloModeli.addRow (new String[]{"Tablo 10", "Tablo 11", "Tablo 12", "Tablo 13"});
        tabloModeli.addRow (new String[]{"Tablo 20", "Tablo 21", "Tablo 22", "Tablo 23"});
        tabloModeli.addRow (new String[]{"Tablo 30", "Tablo 31", "Tablo 32", "Tablo 33"});

        // �ste tabloyu, alta renk se�iciyi SOL KANAT'a ekleyelim...
        tablo = new JTable (tabloModeli);
        solKanatPaneli.add (par�al�PanelYarat (tablo, "JTable"));

        // Bir renk se�ici yarat�p ekleyelim...
        renkSe�ici = new JColorChooser();
        solKanatPaneli.add (par�al�PanelYarat (renkSe�ici, "JColorChooser"));

        // SA� KANAT'a s�rayla metin sat�r�, metin alan�, liste ve a�ac� yarat�p ekleyelim...
        // Bir metin sat�r� yarat�p ekleyelim...
        metinSat�r� = new JTextField (30);
        metinSat�r�.setText ("Favori yiyecekler: Pizza, Musakka, ��mlekte Rosto");
        sa�KanatPaneli.add (par�al�PanelYarat (metinSat�r�, "JTextField"));

        // Kayd�rmal� bir metin alan� yarat�p ekleyelim...
        metinAlan� = new JTextArea (5, 30);
        metinAlan�.setText ("Favori �ovlar:\nBuffy, Alias, Angel");
        JScrollPane kayd�rmaPanosu = new JScrollPane (metinAlan�);
        sa�KanatPaneli.add (par�al�PanelYarat (kayd�rmaPanosu, "JTextArea"));

        // Liste modelli ve kayd�rmal� bir liste yarat�p sa� kanata ekleyelim...
        DefaultListModel listeModeli = new DefaultListModel();
        listeModeli.addElement ("Fadime Yava�");
        listeModeli.addElement ("Bekir Yava�");
        listeModeli.addElement ("Han�m Amanat Yava�");
        listeModeli.addElement ("Memet Yava�");
        listeModeli.addElement ("Hatice Yava� Ka�ar");
        listeModeli.addElement ("S�heyla Yava� �zbay");
        listeModeli.addElement ("Zeliha Yava� Candan");
        listeModeli.addElement ("Mahmut Nihat Yava�");
        listeModeli.addElement ("Song�l Yava� G�kt�rk");
        listeModeli.addElement ("Mustafa Nedim Yava�");
        listeModeli.addElement ("Sevim Yava�");
        liste = new JList (listeModeli);
        liste.setVisibleRowCount (-1);
        liste.getSelectionModel().setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        liste.setTransferHandler (new TransferHandler() {
            public boolean canImport (TransferHandler.TransferSupport bilgi) {
                // Listeye veri ithal edilince �st�ne d��t��� eleman�n yerine ge�er. �hrac� kopyalamad�r, ta��ma de�il...
                if (!bilgi.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;

                JList.DropLocation b�rak�lanEndeks = (JList.DropLocation)bilgi.getDropLocation();
                if (b�rak�lanEndeks.getIndex() == -1) return false;
                return true;
            } // canImport(..) haz�r metodu sonu...

            public boolean importData (TransferHandler.TransferSupport bilgi) {
                if (!bilgi.isDrop()) return false;

                // Dizge haricini liste s�r�kle-b�rak'ta kabul etmesin...
                if (!bilgi.isDataFlavorSupported (DataFlavor.stringFlavor)) {
                    b�rak�lanKonumuG�ster ("Liste bu tip bir b�rak�y� kabul etmiyor!");
                    return false;
                } // if karar� sonu...

                JList.DropLocation b�rak�lanEndeks = (JList.DropLocation)bilgi.getDropLocation();
                DefaultListModel listeModeli = (DefaultListModel)liste.getModel();
                int endeks = b�rak�lanEndeks.getIndex();
                boolean sokulsunMu = b�rak�lanEndeks.isInsert();
                // Listede b�rak�lan�n alt�ndaki eleman� alal�m...
                String listeEleman� = (String)listeModeli.getElementAt (endeks);

                // B�rak�lacak dizgeyi alal�m...
                Transferable aktar�labilir = bilgi.getTransferable();
                String veri;
                try {veri = (String)aktar�labilir.getTransferData (DataFlavor.stringFlavor);
                }catch (Exception ist) {return false;}

                // B�rak�lan dizgeye ait bir diyalog g�r�nt�lensin...
                String b�rak�lanDe�er = "\"" + veri + "\" ";
                if (b�rak�lanEndeks.isInsert()) {
                    if (b�rak�lanEndeks.getIndex() == 0) b�rak�lanKonumuG�ster (b�rak�lanDe�er + "listenin ba��na b�rak�ld�.");
                    else if (b�rak�lanEndeks.getIndex() >= liste.getModel().getSize()) b�rak�lanKonumuG�ster (b�rak�lanDe�er + "listenin sonuna b�rak�ld�."); // Bu i�lemedi?..
                    else {String listeEleman�1 = (String)liste.getModel().getElementAt (b�rak�lanEndeks.getIndex() - 1);
                        String listeEleman�2 = (String)liste.getModel().getElementAt (b�rak�lanEndeks.getIndex());
                        b�rak�lanKonumuG�ster (b�rak�lanDe�er + "\"" + listeEleman�1 + "\" ile \"" + listeEleman�2 + "\" aras�na b�rak�ld�.");
                    } // else karar� sonu...
                }else b�rak�lanKonumuG�ster (b�rak�lanDe�er + "\"" + listeEleman� + "\" �st�ne b�rak�ld�.");

                // Liste dizge b�rak�lar�n� sokmal�m�/de�i�tirme�imi kabul edecektir?..
                if (sokulsunMu) listeModeli.add (endeks, veri);
                else listeModeli.set (endeks, veri);

                return true;
            } // importData(..) haz�r metodu sonu...
            
            public int getSourceActions (JComponent par�a) {return COPY;} // Listeden ta��nmalar kopya �eklinde olacak...
            
            protected Transferable createTransferable (JComponent par�a) {
                JList liste = (JList)par�a;
                Object[] listeElemanlar� = liste.getSelectedValues();
                StringBuffer tampon = new StringBuffer();

                for (int i = 0; i < listeElemanlar�.length; i++) {
                    Object eleman = listeElemanlar�[i];
                    tampon.append (eleman == null ? "" : eleman.toString());
                    if (i != listeElemanlar�.length - 1) tampon.append("\n");
                } // for d�ng�s� sonu...
                return new StringSelection (tampon.toString());
            } // createTransferable(..) haz�r metodu sonu...
        }); // lis.. ifadesi sonu...

        liste.setDropMode (DropMode.ON_OR_INSERT);

        JScrollPane listeKayd�rmas� = new JScrollPane (liste);
        listeKayd�rmas�.setPreferredSize (new Dimension (300, 100));
        sa�KanatPaneli.add (par�al�PanelYarat (listeKayd�rmas�, "JList"));

        // K�sa bir kayd�rmal� secere a�ac� yarat�p sa� kanata ekleyelim...
        DefaultMutableTreeNode k�k = new DefaultMutableTreeNode ("Aile Secerem");
        DefaultMutableTreeNode ebeveyn = new DefaultMutableTreeNode ("Memet ile Han�m");
        k�k.add (ebeveyn);
        DefaultMutableTreeNode karde�1 = new DefaultMutableTreeNode ("Hatice ile Nihat");
        ebeveyn.add (karde�1);
        karde�1.add (new DefaultMutableTreeNode ("Nurilh�da, Y�cel ve Serap"));
        DefaultMutableTreeNode karde�2 = new DefaultMutableTreeNode ("S�heyle ile Adil");
        ebeveyn.add (karde�2);
        karde�2.add (new DefaultMutableTreeNode ("Sema, Selda ve Fatih"));
        DefaultMutableTreeNode karde�3 = new DefaultMutableTreeNode ("Zeliha ile Hamza");
        ebeveyn.add (karde�3);
        karde�3.add (new DefaultMutableTreeNode ("Canan, Zafer ve Belk�s"));
        ebeveyn.add (new DefaultMutableTreeNode ("Nihat"));
        DefaultMutableTreeNode karde�4 = new DefaultMutableTreeNode ("Song�l ile Sefer");
        ebeveyn.add (karde�4);
        karde�4.add (new DefaultMutableTreeNode ("Atilla ve Hilal"));
        ebeveyn.add (new DefaultMutableTreeNode ("Nedim"));
        ebeveyn.add (new DefaultMutableTreeNode ("Sevim"));

        DefaultTreeModel model = new DefaultTreeModel (k�k);
        a�a� = new JTree (model);
        a�a�.getSelectionModel().setSelectionMode (TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        JScrollPane a�a�Kayd�rmas� = new JScrollPane (a�a�);
        a�a�Kayd�rmas�.setPreferredSize (new Dimension (300, 100));
        sa�KanatPaneli.add (par�al�PanelYarat (a�a�Kayd�rmas�, "JTree"));

        // �entikli kutuyu kurup, dinleyiciye duyarlay�p i�erik panosuna ekleyelim...
        �entikSvB = new JCheckBox ("S�r�kle ve B�rak'� A�");
        �entikSvB.setActionCommand ("�entikSvB");
        �entikSvB.addActionListener (this);

        // Ortadan dikey b�lmeli ay�rma panosu yarat�p her iki kanat� da yatay dahil edelim...
        JSplitPane ay�rmaPanosu = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT, solKanatPaneli, sa�KanatPaneli);
        ay�rmaPanosu.setOneTouchExpandable (true); // Ay�rma b�lmesi t�klamal� d��meli...

        add (ay�rmaPanosu, BorderLayout.CENTER);
        add (�entikSvB, BorderLayout.PAGE_END);
        setBorder (BorderFactory.createEmptyBorder (5,5,5,5));
    } // J5h_1() kurucusu sonu...

    protected JPanel dikeyKutuPaneliniYarat() {
        JPanel panel = new JPanel();
        panel.setLayout (new BoxLayout (panel, BoxLayout.PAGE_AXIS));
        panel.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));
        return panel;
    } // dikeyKutuPaneliniYarat() metodu sonu...

    public JPanel par�al�PanelYarat (JComponent par�a, String ba�l�k) {
        JPanel panel = new JPanel (new BorderLayout());
        panel.add (par�a, BorderLayout.CENTER);
        if (ba�l�k != null) panel.setBorder (BorderFactory.createTitledBorder (ba�l�k));
        return panel;
    } // par�al�PanelYarat(..) metodu sonu...

    private void b�rak�lanKonumuG�ster (final String dizge) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {JOptionPane.showMessageDialog (null, dizge);}});
    } // b�rak�lanKonumuG�ster(..) metodu sonu...

    public void actionPerformed(ActionEvent e) {
        if ("�entikSvB".equals(e.getActionCommand())) {
            boolean toggle = �entikSvB.isSelected();
            metinAlan�.setDragEnabled(toggle);
            metinSat�r�.setDragEnabled(toggle);
            liste.setDragEnabled(toggle);
            tablo.setDragEnabled(toggle);
            a�a�.setDragEnabled(toggle);
            renkSe�ici.setDragEnabled(toggle);
        }
    }

    private static void yaratVeG�sterGUI() {
        �er�eve = new JFrame ("Temel S�r�kle Ve B�rak G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5h_1(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (100,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {UIManager.put ("swing.boldMetal", Boolean.FALSE); yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5h_1 s�n�f� sonu...