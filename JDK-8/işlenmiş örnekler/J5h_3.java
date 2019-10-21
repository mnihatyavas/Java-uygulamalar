// J5h_3.java: DropDemo (BýrakGösterisi) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
//import java.awt.datatransfer.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.DropMode;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

// Gereken dosya: J5h_3x.java=ListTransferHandler.java
public class J5h_3 extends JPanel implements ActionListener {
    private JComboBox açýlýrKutu;
    private JList liste;
    private JPanel panel;

    public J5h_3() {
        super (new GridLayout (2,1)); // Tek kolonda 2 alt-alta parçalý serilim...
        add (metinAlanýnýYarat()); // Üstteki (metin alanlý) ilk panelli parça...
        add (listeyiYarat()); // Alttaki (listeli ve açýlýr kutulu) ikinci panelli parça...
        setBackground (Color.CYAN);
    } // J5h_3() kurucusu sonu...

    private JPanel metinAlanýnýYarat() {
        String metin = "Bu alan-dan/a seçiliyi sürükle veya býrak.\nVarsayýlý hareket MOVE/Taþý'dýr;\nCOPY/Kopya'lama için ise Ctrl tuþunu basýlý tutun.";
        
        JTextArea metinAlaný = new JTextArea();
        metinAlaný.setText (metin);
        metinAlaný.setDragEnabled (true);
        metinAlaný.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        JScrollPane kaydýrmaPanosu = new JScrollPane (metinAlaný);
        kaydýrmaPanosu.setPreferredSize (new Dimension (400, 100));
        panel = new JPanel (new BorderLayout());
        panel.add (kaydýrmaPanosu, BorderLayout.CENTER);
        panel.setBorder (BorderFactory.createTitledBorder ("JText Area"));
        panel.setBackground (Color.CYAN);
        return panel;
    } // metinAlanýnýYarat() metodu sonu...

    private JPanel listeyiYarat() {
        DefaultListModel listeModeli = new DefaultListModel();
        listeModeli.addElement ("Bu liste-den/ye seçiliyi sürükle veya býrak.");            
        for (int i = 1; i <= 10; i++) listeModeli.addElement ("Liste Birimi " + i);
        // Veya==> for (int i = 10; i >= 0; i--) listeModeli.addElement (0, "Liste Birimi " + i);
        liste = new JList (listeModeli);
        liste.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION); // Tekli ama icabýnda aralýklý çoklu...
        liste.setDragEnabled (true);
        liste.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.75f));
        liste.setTransferHandler (new J5h_3x());
        JScrollPane kaydýrmaPanosu = new JScrollPane (liste);
        kaydýrmaPanosu.setPreferredSize (new Dimension (400, 100));

        açýlýrKutu = new JComboBox (new String[] {"SEÇÝLÝYÝ_KULLAN", "ÜSTÜNE", "ARAYA", "ÜSTÜNE_VEYA_ARAYA"});
        açýlýrKutu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.85f));
        açýlýrKutu.addActionListener (this); // Dinleyiciye duyarlayalým...
        panel = new JPanel();
        panel.add (new JLabel ("Listeye Býrakma Kipi:"));
        panel.add (açýlýrKutu);
        panel.setBackground (Color.ORANGE);

        JPanel panel1 = new JPanel (new BorderLayout());
        panel1.add (kaydýrmaPanosu, BorderLayout.CENTER);
        panel1.add (panel, BorderLayout.SOUTH);
        panel1.setBorder (BorderFactory.createTitledBorder ("JList"));
        panel1.setBackground (Color.CYAN);
        return panel1;
    } // listeyiYarat() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        Object nesne = açýlýrKutu.getSelectedItem(); // import java.lang.Object;
        if (nesne == "SEÇÝLÝYÝ_KULLAN") liste.setDropMode (DropMode.USE_SELECTION); // ÜSTÜNE gibidir...
        else if (nesne == "ÜSTÜNE") liste.setDropMode (DropMode.ON);
        else if (nesne == "ARAYA") liste.setDropMode (DropMode.INSERT);
        else if (nesne == "ÜSTÜNE_VEYA_ARAYA") liste.setDropMode (DropMode.ON_OR_INSERT);
    } // actionPerformed(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Býrak Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5h_3(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
            } // run() sicim hazýr metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_3 sýnýfý sonu...