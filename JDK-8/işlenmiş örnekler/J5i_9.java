// J5i_9.java: ListSelectionDemo (ListeSeçimiGösterisi) örneði.

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
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class J5i_9 extends JPanel {
    JTextArea döküm;
    JList liste;
    String yeniSatýr = "\n";
    ListSelectionModel listeSeçimModeli;

    public J5i_9() {// Kurucu...
        super (new BorderLayout());

        String[] listeVerisi = {"bir", "iki", "üç", "dört", "beþ", "altý", "yedi", "sekiz", "dokuz", "on"};
        liste = new JList (listeVerisi);
        liste.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        JScrollPane listeKaydýracý = new JScrollPane (liste);

        listeSeçimModeli = liste.getSelectionModel();
        listeSeçimModeli.addListSelectionListener (new ListeSeçimModelim()); // Özel dinleyiciye Duyarlý...

        JPanel kontrolPaneli = new JPanel();
        String[] seçimKipleri = {"TEKLÝ_SEÇÝM",
                "TEKLÝ_ARALIKLI_SEÇÝM",
                "ÇOKLU_ARALIKLI_SEÇÝM"}; // Aslýnda tablo için, listede gereksiz!..

        final JComboBox açýlýrKutu = new JComboBox(seçimKipleri);
        açýlýrKutu.setSelectedIndex (2);
        açýlýrKutu.addActionListener (new ActionListener() {// Dinleyiciye duyarlý...
            public void actionPerformed (ActionEvent olay) {
                String yeniKip = (String)açýlýrKutu.getSelectedItem();
                if (yeniKip.equals ("TEKLÝ_SEÇÝM")) listeSeçimModeli.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
                else if (yeniKip.equals ("TEKLÝ_ARALIKLI_SEÇÝM")) listeSeçimModeli.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                else listeSeçimModeli.setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

                döküm.append ("----------" + "Seçim Kipi: " + yeniKip + "----------" + yeniSatýr);
            } // actionPerformed(..) hazýr metodu sonu...
        }); // açý.. ifadesi sonu...
        kontrolPaneli.add (new JLabel ("Liste seçim kipi:"));
        kontrolPaneli.add (açýlýrKutu);

        // Kayýt dökümü için kaydýraçlý metin alanýný kuralým...
        döküm = new JTextArea (1, 10);
        döküm.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.55f));
        döküm.setForeground (Color.WHITE);
        döküm.setEditable (false); // Müdahalesiz...
        JScrollPane dökümKaydýracý = new JScrollPane (döküm,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Ýçerik panosu serilimini oluþturalým...
        JSplitPane paravanlýPano = new JSplitPane (JSplitPane.VERTICAL_SPLIT);
        add (paravanlýPano, BorderLayout.CENTER);

        JPanel üstYarýmPaneli = new JPanel();
        üstYarýmPaneli.setLayout (new BoxLayout (üstYarýmPaneli, BoxLayout.LINE_AXIS));
        JPanel listePaneli = new JPanel (new GridLayout (1,1));
        listePaneli.setBorder (BorderFactory.createTitledBorder ("Liste"));
        listePaneli.add (listeKaydýracý);
        üstYarýmPaneli.setBorder (BorderFactory.createEmptyBorder (5,5,0,5)); // Üst-sað-alt-sol...
        üstYarýmPaneli.add (listePaneli);
        üstYarýmPaneli.setMinimumSize (new Dimension (100, 50));
        üstYarýmPaneli.setPreferredSize (new Dimension (100, 110));
        paravanlýPano.add (üstYarýmPaneli);

        JPanel altYarýmPaneli = new JPanel (new BorderLayout());
        altYarýmPaneli.add (kontrolPaneli, BorderLayout.PAGE_START);
        altYarýmPaneli.add (dökümKaydýracý, BorderLayout.CENTER);
        // Alt yarým paneli de üst gibi komple kaydýraçlý olsaydý alt satýr konulabilirdi...
        //altYarýmPaneli.setMinimumSize (new Dimension (400, 50));
        altYarýmPaneli.setPreferredSize(new Dimension(450, 135));
        paravanlýPano.add (altYarýmPaneli);
    } // J5i_9() kurucusu sonu...

    class ListeSeçimModelim implements ListSelectionListener {
        public void valueChanged (ListSelectionEvent olay) { 
            ListSelectionModel lsm = (ListSelectionModel)olay.getSource();

            int ilkEndeks = olay.getFirstIndex();
            int sonEndeks = olay.getLastIndex();
            boolean isAdjusting = olay.getValueIsAdjusting(); 
            döküm.append(ilkEndeks + " - " + sonEndeks + " endeksleri için olay;"
                    + " ayarlama " + isAdjusting + "'dýr; seçili endeksler:");
            if (lsm.isSelectionEmpty()) döküm.append(" <hiç>");
            else {int enküçükEndeks = lsm.getMinSelectionIndex();
                int enbüyükEndeks = lsm.getMaxSelectionIndex();
                for (int i = enküçükEndeks; i <= enbüyükEndeks; i++) {
                    if (lsm.isSelectedIndex (i)) döküm.append (" " + i);
                } // for döngüsü sonu...
            } // else kararý sonu...
            döküm.append (yeniSatýr);
            döküm.setCaretPosition (döküm.getDocument().getLength());
        } // valueChanged(..) hazýr metodu sonu...
    } // ListeSeçimModelim sýnýfý sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Liste Seçimi Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5i_9 gösteri = new J5i_9(); // Kurucuyu çaðýrýr...
        gösteri.setOpaque (true);
        çerçeve.setContentPane (gösteri);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5i_9 sýnýfý sonu...