// J5c_56.java: SplitPaneDemo2 (B�lmeliPanoG�sterisi2) �rne�i.

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JList;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/* �nceki dikey d��meli b�lmeye �imdi de yatay� eklenmi�tir.
 * Gereken java dosyas�: J5c_55.java=SplitPaneDemo.java
 */
public class J5c_56 extends JFrame implements ListSelectionListener {
    private JLabel etiket;

    public J5c_56() {// Kurucu...
        super ("B�lme Panosu G�sterisi 2");

        // J5c_55/SplitPaneDemo'nun bir tiplemesini yaratal�m...
        J5c_55 b�lmePanosuG�sterisi = new J5c_55();
        JSplitPane �stB�l�m = b�lmePanosuG�sterisi.b�lmePanosunuAl();
        �stB�l�m.setBorder (null);
        // �st b�lme panosuna dinleyiciye duyarl� listeyi kural�m...
        b�lmePanosuG�sterisi.resimListesiniAl().addListSelectionListener (this);

        // Alt b�l�m etiketini yaratal�m...
        etiket = new JLabel ("Listeden bir resim ad� se�iniz", JLabel.CENTER);
        etiket.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // Yeni bir b�lme panosu yarat�p �st panoyu ve etiketi i�ine koyal�m...
        JSplitPane b�lmePanosu = new JSplitPane (
                JSplitPane.VERTICAL_SPLIT, // Yatay d��meli b�lme �ubu�u alt�nda dikey etiket b�lgesi...
                �stB�l�m, // Listeyi ve resmi i�eren �nceki iki b�lmeli pano...
                etiket); // Yeni dikey alt b�lge...
        b�lmePanosu.setOneTouchExpandable (true); // B�lme �ubuklar� d��meli...
        b�lmePanosu.setDividerLocation (180);

        // �st ve alt b�lgelere emk���k ebatlar� tahsis edelim...
        �stB�l�m.setMinimumSize (new Dimension (100, 50));
        etiket.setMinimumSize (new Dimension (100, 30));

        // T�m b�lme panosunu i�erik panolu �er�eveye ekleyelim...
        getContentPane().add (b�lmePanosu);
    } // J5c_56() kurucusu sonu...

    public void valueChanged (ListSelectionEvent olay) {
        if (olay.getValueIsAdjusting()) return;

        JList se�ilenListe = (JList)olay.getSource();
        if (se�ilenListe.isSelectionEmpty()) etiket.setText ("Se�ilen yok!");
        else {int endeks = se�ilenListe.getSelectedIndex();
            etiket.setText ("Se�ilen resmin liste endeksi: " + endeks);
        } // else karar� sonu...
    } // valueChanged(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new J5c_56(); // Kurucuyu �a��r�r...
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_56 s�n�f� sonu...