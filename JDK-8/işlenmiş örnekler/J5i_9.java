// J5i_9.java: ListSelectionDemo (ListeSe�imiG�sterisi) �rne�i.

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
    JTextArea d�k�m;
    JList liste;
    String yeniSat�r = "\n";
    ListSelectionModel listeSe�imModeli;

    public J5i_9() {// Kurucu...
        super (new BorderLayout());

        String[] listeVerisi = {"bir", "iki", "��", "d�rt", "be�", "alt�", "yedi", "sekiz", "dokuz", "on"};
        liste = new JList (listeVerisi);
        liste.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        JScrollPane listeKayd�rac� = new JScrollPane (liste);

        listeSe�imModeli = liste.getSelectionModel();
        listeSe�imModeli.addListSelectionListener (new ListeSe�imModelim()); // �zel dinleyiciye Duyarl�...

        JPanel kontrolPaneli = new JPanel();
        String[] se�imKipleri = {"TEKL�_SE��M",
                "TEKL�_ARALIKLI_SE��M",
                "�OKLU_ARALIKLI_SE��M"}; // Asl�nda tablo i�in, listede gereksiz!..

        final JComboBox a��l�rKutu = new JComboBox(se�imKipleri);
        a��l�rKutu.setSelectedIndex (2);
        a��l�rKutu.addActionListener (new ActionListener() {// Dinleyiciye duyarl�...
            public void actionPerformed (ActionEvent olay) {
                String yeniKip = (String)a��l�rKutu.getSelectedItem();
                if (yeniKip.equals ("TEKL�_SE��M")) listeSe�imModeli.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
                else if (yeniKip.equals ("TEKL�_ARALIKLI_SE��M")) listeSe�imModeli.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                else listeSe�imModeli.setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

                d�k�m.append ("----------" + "Se�im Kipi: " + yeniKip + "----------" + yeniSat�r);
            } // actionPerformed(..) haz�r metodu sonu...
        }); // a��.. ifadesi sonu...
        kontrolPaneli.add (new JLabel ("Liste se�im kipi:"));
        kontrolPaneli.add (a��l�rKutu);

        // Kay�t d�k�m� i�in kayd�ra�l� metin alan�n� kural�m...
        d�k�m = new JTextArea (1, 10);
        d�k�m.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.55f));
        d�k�m.setForeground (Color.WHITE);
        d�k�m.setEditable (false); // M�dahalesiz...
        JScrollPane d�k�mKayd�rac� = new JScrollPane (d�k�m,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // ��erik panosu serilimini olu�tural�m...
        JSplitPane paravanl�Pano = new JSplitPane (JSplitPane.VERTICAL_SPLIT);
        add (paravanl�Pano, BorderLayout.CENTER);

        JPanel �stYar�mPaneli = new JPanel();
        �stYar�mPaneli.setLayout (new BoxLayout (�stYar�mPaneli, BoxLayout.LINE_AXIS));
        JPanel listePaneli = new JPanel (new GridLayout (1,1));
        listePaneli.setBorder (BorderFactory.createTitledBorder ("Liste"));
        listePaneli.add (listeKayd�rac�);
        �stYar�mPaneli.setBorder (BorderFactory.createEmptyBorder (5,5,0,5)); // �st-sa�-alt-sol...
        �stYar�mPaneli.add (listePaneli);
        �stYar�mPaneli.setMinimumSize (new Dimension (100, 50));
        �stYar�mPaneli.setPreferredSize (new Dimension (100, 110));
        paravanl�Pano.add (�stYar�mPaneli);

        JPanel altYar�mPaneli = new JPanel (new BorderLayout());
        altYar�mPaneli.add (kontrolPaneli, BorderLayout.PAGE_START);
        altYar�mPaneli.add (d�k�mKayd�rac�, BorderLayout.CENTER);
        // Alt yar�m paneli de �st gibi komple kayd�ra�l� olsayd� alt sat�r konulabilirdi...
        //altYar�mPaneli.setMinimumSize (new Dimension (400, 50));
        altYar�mPaneli.setPreferredSize(new Dimension(450, 135));
        paravanl�Pano.add (altYar�mPaneli);
    } // J5i_9() kurucusu sonu...

    class ListeSe�imModelim implements ListSelectionListener {
        public void valueChanged (ListSelectionEvent olay) { 
            ListSelectionModel lsm = (ListSelectionModel)olay.getSource();

            int ilkEndeks = olay.getFirstIndex();
            int sonEndeks = olay.getLastIndex();
            boolean isAdjusting = olay.getValueIsAdjusting(); 
            d�k�m.append(ilkEndeks + " - " + sonEndeks + " endeksleri i�in olay;"
                    + " ayarlama " + isAdjusting + "'d�r; se�ili endeksler:");
            if (lsm.isSelectionEmpty()) d�k�m.append(" <hi�>");
            else {int enk���kEndeks = lsm.getMinSelectionIndex();
                int enb�y�kEndeks = lsm.getMaxSelectionIndex();
                for (int i = enk���kEndeks; i <= enb�y�kEndeks; i++) {
                    if (lsm.isSelectedIndex (i)) d�k�m.append (" " + i);
                } // for d�ng�s� sonu...
            } // else karar� sonu...
            d�k�m.append (yeniSat�r);
            d�k�m.setCaretPosition (d�k�m.getDocument().getLength());
        } // valueChanged(..) haz�r metodu sonu...
    } // ListeSe�imModelim s�n�f� sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Liste Se�imi G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5i_9 g�steri = new J5i_9(); // Kurucuyu �a��r�r...
        g�steri.setOpaque (true);
        �er�eve.setContentPane (g�steri);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5i_9 s�n�f� sonu...