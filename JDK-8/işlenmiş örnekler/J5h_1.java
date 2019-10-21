// J5h_1.java: BasicDnD (TemelSürükleVeBýrak) örneði.

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
    private static JFrame çerçeve;
    private JTextArea metinAlaný;
    private JTextField metinSatýrý;
    private JList liste;
    private JTable tablo;
    private JTree aðaç;
    private JColorChooser renkSeçici;
    private JCheckBox çentikSvB; // DragAndDropDnD: SürükleVeBýrak_SvB

    public J5h_1() {// Kurucu...
        super (new BorderLayout());
        JPanel solKanatPaneli = dikeyKutuPaneliniYarat();
        JPanel saðKanatPaneli = dikeyKutuPaneliniYarat();

        // Bir tablo modeli aratalým...
        DefaultTableModel tabloModeli = new DefaultTableModel();
        tabloModeli.addColumn ("Kolon 0");
        tabloModeli.addColumn ("Kolon 1");
        tabloModeli.addColumn ("Kolon 2");
        tabloModeli.addColumn ("Kolon 3");
        tabloModeli.addRow (new String[]{"Tablo 00", "Tablo 01", "Tablo 02", "Tablo 03"});
        tabloModeli.addRow (new String[]{"Tablo 10", "Tablo 11", "Tablo 12", "Tablo 13"});
        tabloModeli.addRow (new String[]{"Tablo 20", "Tablo 21", "Tablo 22", "Tablo 23"});
        tabloModeli.addRow (new String[]{"Tablo 30", "Tablo 31", "Tablo 32", "Tablo 33"});

        // Üste tabloyu, alta renk seçiciyi SOL KANAT'a ekleyelim...
        tablo = new JTable (tabloModeli);
        solKanatPaneli.add (parçalýPanelYarat (tablo, "JTable"));

        // Bir renk seçici yaratýp ekleyelim...
        renkSeçici = new JColorChooser();
        solKanatPaneli.add (parçalýPanelYarat (renkSeçici, "JColorChooser"));

        // SAÐ KANAT'a sýrayla metin satýrý, metin alaný, liste ve aðacý yaratýp ekleyelim...
        // Bir metin satýrý yaratýp ekleyelim...
        metinSatýrý = new JTextField (30);
        metinSatýrý.setText ("Favori yiyecekler: Pizza, Musakka, Çömlekte Rosto");
        saðKanatPaneli.add (parçalýPanelYarat (metinSatýrý, "JTextField"));

        // Kaydýrmalý bir metin alaný yaratýp ekleyelim...
        metinAlaný = new JTextArea (5, 30);
        metinAlaný.setText ("Favori þovlar:\nBuffy, Alias, Angel");
        JScrollPane kaydýrmaPanosu = new JScrollPane (metinAlaný);
        saðKanatPaneli.add (parçalýPanelYarat (kaydýrmaPanosu, "JTextArea"));

        // Liste modelli ve kaydýrmalý bir liste yaratýp sað kanata ekleyelim...
        DefaultListModel listeModeli = new DefaultListModel();
        listeModeli.addElement ("Fadime Yavaþ");
        listeModeli.addElement ("Bekir Yavaþ");
        listeModeli.addElement ("Haným Amanat Yavaþ");
        listeModeli.addElement ("Memet Yavaþ");
        listeModeli.addElement ("Hatice Yavaþ Kaçar");
        listeModeli.addElement ("Süheyla Yavaþ Özbay");
        listeModeli.addElement ("Zeliha Yavaþ Candan");
        listeModeli.addElement ("Mahmut Nihat Yavaþ");
        listeModeli.addElement ("Songül Yavaþ Göktürk");
        listeModeli.addElement ("Mustafa Nedim Yavaþ");
        listeModeli.addElement ("Sevim Yavaþ");
        liste = new JList (listeModeli);
        liste.setVisibleRowCount (-1);
        liste.getSelectionModel().setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        liste.setTransferHandler (new TransferHandler() {
            public boolean canImport (TransferHandler.TransferSupport bilgi) {
                // Listeye veri ithal edilince üstüne düþtüðü elemanýn yerine geçer. Ýhracý kopyalamadýr, taþýma deðil...
                if (!bilgi.isDataFlavorSupported (DataFlavor.stringFlavor)) return false;

                JList.DropLocation býrakýlanEndeks = (JList.DropLocation)bilgi.getDropLocation();
                if (býrakýlanEndeks.getIndex() == -1) return false;
                return true;
            } // canImport(..) hazýr metodu sonu...

            public boolean importData (TransferHandler.TransferSupport bilgi) {
                if (!bilgi.isDrop()) return false;

                // Dizge haricini liste sürükle-býrak'ta kabul etmesin...
                if (!bilgi.isDataFlavorSupported (DataFlavor.stringFlavor)) {
                    býrakýlanKonumuGöster ("Liste bu tip bir býrakýyý kabul etmiyor!");
                    return false;
                } // if kararý sonu...

                JList.DropLocation býrakýlanEndeks = (JList.DropLocation)bilgi.getDropLocation();
                DefaultListModel listeModeli = (DefaultListModel)liste.getModel();
                int endeks = býrakýlanEndeks.getIndex();
                boolean sokulsunMu = býrakýlanEndeks.isInsert();
                // Listede býrakýlanýn altýndaki elemaný alalým...
                String listeElemaný = (String)listeModeli.getElementAt (endeks);

                // Býrakýlacak dizgeyi alalým...
                Transferable aktarýlabilir = bilgi.getTransferable();
                String veri;
                try {veri = (String)aktarýlabilir.getTransferData (DataFlavor.stringFlavor);
                }catch (Exception ist) {return false;}

                // Býrakýlan dizgeye ait bir diyalog görüntülensin...
                String býrakýlanDeðer = "\"" + veri + "\" ";
                if (býrakýlanEndeks.isInsert()) {
                    if (býrakýlanEndeks.getIndex() == 0) býrakýlanKonumuGöster (býrakýlanDeðer + "listenin baþýna býrakýldý.");
                    else if (býrakýlanEndeks.getIndex() >= liste.getModel().getSize()) býrakýlanKonumuGöster (býrakýlanDeðer + "listenin sonuna býrakýldý."); // Bu iþlemedi?..
                    else {String listeElemaný1 = (String)liste.getModel().getElementAt (býrakýlanEndeks.getIndex() - 1);
                        String listeElemaný2 = (String)liste.getModel().getElementAt (býrakýlanEndeks.getIndex());
                        býrakýlanKonumuGöster (býrakýlanDeðer + "\"" + listeElemaný1 + "\" ile \"" + listeElemaný2 + "\" arasýna býrakýldý.");
                    } // else kararý sonu...
                }else býrakýlanKonumuGöster (býrakýlanDeðer + "\"" + listeElemaný + "\" üstüne býrakýldý.");

                // Liste dizge býrakýlarýný sokmalýmý/deðiþtirmeþimi kabul edecektir?..
                if (sokulsunMu) listeModeli.add (endeks, veri);
                else listeModeli.set (endeks, veri);

                return true;
            } // importData(..) hazýr metodu sonu...
            
            public int getSourceActions (JComponent parça) {return COPY;} // Listeden taþýnmalar kopya þeklinde olacak...
            
            protected Transferable createTransferable (JComponent parça) {
                JList liste = (JList)parça;
                Object[] listeElemanlarý = liste.getSelectedValues();
                StringBuffer tampon = new StringBuffer();

                for (int i = 0; i < listeElemanlarý.length; i++) {
                    Object eleman = listeElemanlarý[i];
                    tampon.append (eleman == null ? "" : eleman.toString());
                    if (i != listeElemanlarý.length - 1) tampon.append("\n");
                } // for döngüsü sonu...
                return new StringSelection (tampon.toString());
            } // createTransferable(..) hazýr metodu sonu...
        }); // lis.. ifadesi sonu...

        liste.setDropMode (DropMode.ON_OR_INSERT);

        JScrollPane listeKaydýrmasý = new JScrollPane (liste);
        listeKaydýrmasý.setPreferredSize (new Dimension (300, 100));
        saðKanatPaneli.add (parçalýPanelYarat (listeKaydýrmasý, "JList"));

        // Kýsa bir kaydýrmalý secere aðacý yaratýp sað kanata ekleyelim...
        DefaultMutableTreeNode kök = new DefaultMutableTreeNode ("Aile Secerem");
        DefaultMutableTreeNode ebeveyn = new DefaultMutableTreeNode ("Memet ile Haným");
        kök.add (ebeveyn);
        DefaultMutableTreeNode kardeþ1 = new DefaultMutableTreeNode ("Hatice ile Nihat");
        ebeveyn.add (kardeþ1);
        kardeþ1.add (new DefaultMutableTreeNode ("Nurilhüda, Yücel ve Serap"));
        DefaultMutableTreeNode kardeþ2 = new DefaultMutableTreeNode ("Süheyle ile Adil");
        ebeveyn.add (kardeþ2);
        kardeþ2.add (new DefaultMutableTreeNode ("Sema, Selda ve Fatih"));
        DefaultMutableTreeNode kardeþ3 = new DefaultMutableTreeNode ("Zeliha ile Hamza");
        ebeveyn.add (kardeþ3);
        kardeþ3.add (new DefaultMutableTreeNode ("Canan, Zafer ve Belkýs"));
        ebeveyn.add (new DefaultMutableTreeNode ("Nihat"));
        DefaultMutableTreeNode kardeþ4 = new DefaultMutableTreeNode ("Songül ile Sefer");
        ebeveyn.add (kardeþ4);
        kardeþ4.add (new DefaultMutableTreeNode ("Atilla ve Hilal"));
        ebeveyn.add (new DefaultMutableTreeNode ("Nedim"));
        ebeveyn.add (new DefaultMutableTreeNode ("Sevim"));

        DefaultTreeModel model = new DefaultTreeModel (kök);
        aðaç = new JTree (model);
        aðaç.getSelectionModel().setSelectionMode (TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        JScrollPane aðaçKaydýrmasý = new JScrollPane (aðaç);
        aðaçKaydýrmasý.setPreferredSize (new Dimension (300, 100));
        saðKanatPaneli.add (parçalýPanelYarat (aðaçKaydýrmasý, "JTree"));

        // Çentikli kutuyu kurup, dinleyiciye duyarlayýp içerik panosuna ekleyelim...
        çentikSvB = new JCheckBox ("Sürükle ve Býrak'ý Aç");
        çentikSvB.setActionCommand ("çentikSvB");
        çentikSvB.addActionListener (this);

        // Ortadan dikey bölmeli ayýrma panosu yaratýp her iki kanatý da yatay dahil edelim...
        JSplitPane ayýrmaPanosu = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT, solKanatPaneli, saðKanatPaneli);
        ayýrmaPanosu.setOneTouchExpandable (true); // Ayýrma bölmesi týklamalý düðmeli...

        add (ayýrmaPanosu, BorderLayout.CENTER);
        add (çentikSvB, BorderLayout.PAGE_END);
        setBorder (BorderFactory.createEmptyBorder (5,5,5,5));
    } // J5h_1() kurucusu sonu...

    protected JPanel dikeyKutuPaneliniYarat() {
        JPanel panel = new JPanel();
        panel.setLayout (new BoxLayout (panel, BoxLayout.PAGE_AXIS));
        panel.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));
        return panel;
    } // dikeyKutuPaneliniYarat() metodu sonu...

    public JPanel parçalýPanelYarat (JComponent parça, String baþlýk) {
        JPanel panel = new JPanel (new BorderLayout());
        panel.add (parça, BorderLayout.CENTER);
        if (baþlýk != null) panel.setBorder (BorderFactory.createTitledBorder (baþlýk));
        return panel;
    } // parçalýPanelYarat(..) metodu sonu...

    private void býrakýlanKonumuGöster (final String dizge) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {JOptionPane.showMessageDialog (null, dizge);}});
    } // býrakýlanKonumuGöster(..) metodu sonu...

    public void actionPerformed(ActionEvent e) {
        if ("çentikSvB".equals(e.getActionCommand())) {
            boolean toggle = çentikSvB.isSelected();
            metinAlaný.setDragEnabled(toggle);
            metinSatýrý.setDragEnabled(toggle);
            liste.setDragEnabled(toggle);
            tablo.setDragEnabled(toggle);
            aðaç.setDragEnabled(toggle);
            renkSeçici.setDragEnabled(toggle);
        }
    }

    private static void yaratVeGösterGUI() {
        çerçeve = new JFrame ("Temel Sürükle Ve Býrak Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5h_1(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (100,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {UIManager.put ("swing.boldMetal", Boolean.FALSE); yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5h_1 sýnýfý sonu...