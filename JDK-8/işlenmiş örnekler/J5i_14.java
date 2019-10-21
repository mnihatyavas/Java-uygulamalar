// J5i_14.java: TableListSelectionDemo (Tablo'danListeSe�imiG�sterisi) �rne�i.

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
    JTextArea ��kt�;
    JTable tablo;
    String yeniSat�r = "\n";
    ListSelectionModel listeSe�imModeli;

    public J5i_14() {// Kurucu...
        super (new BorderLayout());

        String[] kolonAdlar� = {"French", "Spanish", "Italian" , "�ngilizce", "T�rk�e"};
        String[][] tabloVerisi = {{"un", "uno", "uno", "One", "Bir"},
                                {"deux", "dos", "due", "Two", "�ki"},
                                {"trois", "tres", "tre", "Tree", "��"},
                                {"quatre", "cuatro",  "quattro", "Four", "D�rt"},
                                {"cinq", "cinco", "cinque", "Five", "Be�"},
                                {"six", "seis", "sei", "Six", "Alt�"},
                                {"sept", "siete", "sette", "Seven", "Yedi"}};

        tablo = new JTable (tabloVerisi, kolonAdlar�);
        tablo.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1.00f) );
        listeSe�imModeli = tablo.getSelectionModel();
        // Liste se�im modeli �zelle�tirilen se�ili kay�t ��kt�l� dinleyiciye duyarl�...
        listeSe�imModeli.addListSelectionListener (new SharedListSelectionHandler());
        tablo.setSelectionModel (listeSe�imModeli);
        JScrollPane tabloKayd�rac� = new JScrollPane (tablo);

        // Varsay�l� FlowLayout serilim'le kontrol panelini kural�m...
        JPanel kontrolPaneli = new JPanel();
        String[] listeSe�imKipleri = { "TEKL�_SE��M",
                "TEKL�_ARALIK_SE��M�",
                "�OKLU_ARALIK_SE��M�" };

        final JComboBox a��l�rKutu = new JComboBox (listeSe�imKipleri);
        a��l�rKutu.setSelectedIndex (2);
        // A��l�r kutu se�im kipi ��kt�l� dinleyiciye duyarl�...
        a��l�rKutu.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                String yeniKip = (String)a��l�rKutu.getSelectedItem();
                if (yeniKip.equals ("TEKL�_SE��M")) listeSe�imModeli.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
                else if (yeniKip.equals ("TEKL�_ARALIK_SE��M�")) listeSe�imModeli.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                else listeSe�imModeli.setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

                ��kt�.append ("----------" + "Se�im Kipi: " + yeniKip + "----------" + yeniSat�r);
            } // actionPerformed(..) haz�r metodu sonu...
        }); // a��.. ifadesi sonu...

        kontrolPaneli.add (new JLabel ("Liste se�im kipi:"));
        kontrolPaneli.add (a��l�rKutu);

        // ��kt� metin alan�n� kural�m...
        ��kt� = new JTextArea (1, 10);
        ��kt�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.55f) );
        ��kt�.setForeground (Color.YELLOW);
        ��kt�.setEditable (false); // M�dahalesiz...
        JScrollPane ��kt�Kayd�rac� = new JScrollPane (��kt�,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Paravanl� pano'yla i�erik panosu serilimini yapal�m...
        JSplitPane paravanl�Pano = new JSplitPane (JSplitPane.VERTICAL_SPLIT);
        add (paravanl�Pano, BorderLayout.CENTER);

        JPanel �stYar�Paneli = new JPanel();
        �stYar�Paneli.setLayout (new BoxLayout (�stYar�Paneli, BoxLayout.LINE_AXIS));
        JPanel tabloKab� = new JPanel (new GridLayout (1,1));
        tabloKab�.setBorder (BorderFactory.createTitledBorder ("Tablo"));
        tabloKab�.add (tabloKayd�rac�);
        tabloKayd�rac�.setPreferredSize (new Dimension (420, 130));
        �stYar�Paneli.setBorder (BorderFactory.createEmptyBorder (5,5,0,5));
        �stYar�Paneli.add (tabloKab�);
        �stYar�Paneli.setMinimumSize (new Dimension (250, 50));
        �stYar�Paneli.setPreferredSize (new Dimension (200, 110));
        paravanl�Pano.add (�stYar�Paneli);

        JPanel altYar�Paneli = new JPanel (new BorderLayout());
        altYar�Paneli.add (kontrolPaneli, BorderLayout.PAGE_START);
        altYar�Paneli.add (��kt�Kayd�rac�, BorderLayout.CENTER);
        altYar�Paneli.setPreferredSize (new Dimension (450, 110));
        paravanl�Pano.add (altYar�Paneli);
    } // J5i_14() kurucusu sonu...

    class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged (ListSelectionEvent olay) { 
            ListSelectionModel lsm = (ListSelectionModel)olay.getSource();

            int ilkEndeks = olay.getFirstIndex();
            int sonEndeks = olay.getLastIndex();
            boolean ayarl�yorMu = olay.getValueIsAdjusting(); 
            ��kt�.append (ilkEndeks + " - " + sonEndeks + " endeksler i�in olay"
                    + "; ayar yap�ld��� " + ayarl�yorMu
                    + "; se�ili endeksler:");

            if (lsm.isSelectionEmpty()) ��kt�.append (" <yok>");
            else {// Hangi endekslerin se�ili oldu�unu bulal�m...
                int enk���kEndeks = lsm.getMinSelectionIndex();
                int enb�y�kEndeks = lsm.getMaxSelectionIndex();
                for (int i = enk���kEndeks; i <= enb�y�kEndeks; i++) {
                    if (lsm.isSelectedIndex (i)) ��kt�.append (" " + i);
                } // for d�ng�s� sonu...
            } // else karar� sonu...
            ��kt�.append (yeniSat�r);
            ��kt�.setCaretPosition (��kt�.getDocument().getLength());
        } // valueChanged(..) haz�r metodu sonu...
    } // ListSelectionListener haz�r s�n�f� sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Tablo'dan Liste Se�imi G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5i_14 g�steri = new J5i_14(); // Kurucuyu �a��r�r...
        g�steri.setOpaque (true);
        �er�eve.setContentPane (g�steri);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5i_14 s�n�f� sonu...