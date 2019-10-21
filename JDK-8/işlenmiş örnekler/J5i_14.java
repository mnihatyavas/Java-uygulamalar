// J5i_14.java: TableListSelectionDemo (Tablo'danListeSeçimiGösterisi) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class J5i_14 extends JPanel {
    JTextArea çýktý;
    JTable tablo;
    String yeniSatýr = "\n";
    ListSelectionModel listeSeçimModeli;

    public J5i_14() {// Kurucu...
        super (new BorderLayout());

        String[] kolonAdlarý = {"French", "Spanish", "Italian" , "Ýngilizce", "Türkçe"};
        String[][] tabloVerisi = {{"un", "uno", "uno", "One", "Bir"},
                                {"deux", "dos", "due", "Two", "Ýki"},
                                {"trois", "tres", "tre", "Tree", "Üç"},
                                {"quatre", "cuatro",  "quattro", "Four", "Dört"},
                                {"cinq", "cinco", "cinque", "Five", "Beþ"},
                                {"six", "seis", "sei", "Six", "Altý"},
                                {"sept", "siete", "sette", "Seven", "Yedi"}};

        tablo = new JTable (tabloVerisi, kolonAdlarý);
        tablo.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1.00f) );
        listeSeçimModeli = tablo.getSelectionModel();
        // Liste seçim modeli özelleþtirilen seçili kayýt çýktýlý dinleyiciye duyarlý...
        listeSeçimModeli.addListSelectionListener (new SharedListSelectionHandler());
        tablo.setSelectionModel (listeSeçimModeli);
        JScrollPane tabloKaydýracý = new JScrollPane (tablo);

        // Varsayýlý FlowLayout serilim'le kontrol panelini kuralým...
        JPanel kontrolPaneli = new JPanel();
        String[] listeSeçimKipleri = { "TEKLÝ_SEÇÝM",
                "TEKLÝ_ARALIK_SEÇÝMÝ",
                "ÇOKLU_ARALIK_SEÇÝMÝ" };

        final JComboBox açýlýrKutu = new JComboBox (listeSeçimKipleri);
        açýlýrKutu.setSelectedIndex (2);
        // Açýlýr kutu seçim kipi çýktýlý dinleyiciye duyarlý...
        açýlýrKutu.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                String yeniKip = (String)açýlýrKutu.getSelectedItem();
                if (yeniKip.equals ("TEKLÝ_SEÇÝM")) listeSeçimModeli.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
                else if (yeniKip.equals ("TEKLÝ_ARALIK_SEÇÝMÝ")) listeSeçimModeli.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                else listeSeçimModeli.setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

                çýktý.append ("----------" + "Seçim Kipi: " + yeniKip + "----------" + yeniSatýr);
            } // actionPerformed(..) hazýr metodu sonu...
        }); // açý.. ifadesi sonu...

        kontrolPaneli.add (new JLabel ("Liste seçim kipi:"));
        kontrolPaneli.add (açýlýrKutu);

        // Çýktý metin alanýný kuralým...
        çýktý = new JTextArea (1, 10);
        çýktý.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.55f) );
        çýktý.setForeground (Color.YELLOW);
        çýktý.setEditable (false); // Müdahalesiz...
        JScrollPane çýktýKaydýracý = new JScrollPane (çýktý,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Paravanlý pano'yla içerik panosu serilimini yapalým...
        JSplitPane paravanlýPano = new JSplitPane (JSplitPane.VERTICAL_SPLIT);
        add (paravanlýPano, BorderLayout.CENTER);

        JPanel üstYarýPaneli = new JPanel();
        üstYarýPaneli.setLayout (new BoxLayout (üstYarýPaneli, BoxLayout.LINE_AXIS));
        JPanel tabloKabý = new JPanel (new GridLayout (1,1));
        tabloKabý.setBorder (BorderFactory.createTitledBorder ("Tablo"));
        tabloKabý.add (tabloKaydýracý);
        tabloKaydýracý.setPreferredSize (new Dimension (420, 130));
        üstYarýPaneli.setBorder (BorderFactory.createEmptyBorder (5,5,0,5));
        üstYarýPaneli.add (tabloKabý);
        üstYarýPaneli.setMinimumSize (new Dimension (250, 50));
        üstYarýPaneli.setPreferredSize (new Dimension (200, 110));
        paravanlýPano.add (üstYarýPaneli);

        JPanel altYarýPaneli = new JPanel (new BorderLayout());
        altYarýPaneli.add (kontrolPaneli, BorderLayout.PAGE_START);
        altYarýPaneli.add (çýktýKaydýracý, BorderLayout.CENTER);
        altYarýPaneli.setPreferredSize (new Dimension (450, 110));
        paravanlýPano.add (altYarýPaneli);
    } // J5i_14() kurucusu sonu...

    class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged (ListSelectionEvent olay) { 
            ListSelectionModel lsm = (ListSelectionModel)olay.getSource();

            int ilkEndeks = olay.getFirstIndex();
            int sonEndeks = olay.getLastIndex();
            boolean ayarlýyorMu = olay.getValueIsAdjusting(); 
            çýktý.append (ilkEndeks + " - " + sonEndeks + " endeksler için olay"
                    + "; ayar yapýldýðý " + ayarlýyorMu
                    + "; seçili endeksler:");

            if (lsm.isSelectionEmpty()) çýktý.append (" <yok>");
            else {// Hangi endekslerin seçili olduðunu bulalým...
                int enküçükEndeks = lsm.getMinSelectionIndex();
                int enbüyükEndeks = lsm.getMaxSelectionIndex();
                for (int i = enküçükEndeks; i <= enbüyükEndeks; i++) {
                    if (lsm.isSelectedIndex (i)) çýktý.append (" " + i);
                } // for döngüsü sonu...
            } // else kararý sonu...
            çýktý.append (yeniSatýr);
            çýktý.setCaretPosition (çýktý.getDocument().getLength());
        } // valueChanged(..) hazýr metodu sonu...
    } // ListSelectionListener hazýr sýnýfý sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Tablo'dan Liste Seçimi Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5i_14 gösteri = new J5i_14(); // Kurucuyu çaðýrýr...
        gösteri.setOpaque (true);
        çerçeve.setContentPane (gösteri);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5i_14 sýnýfý sonu...