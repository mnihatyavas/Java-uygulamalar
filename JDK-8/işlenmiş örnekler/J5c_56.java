// J5c_56.java: SplitPaneDemo2 (BölmeliPanoGösterisi2) örneði.

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JList;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/* Önceki dikey düðmeli bölmeye þimdi de yatayý eklenmiþtir.
 * Gereken java dosyasý: J5c_55.java=SplitPaneDemo.java
 */
public class J5c_56 extends JFrame implements ListSelectionListener {
    private JLabel etiket;

    public J5c_56() {// Kurucu...
        super ("Bölme Panosu Gösterisi 2");

        // J5c_55/SplitPaneDemo'nun bir tiplemesini yaratalým...
        J5c_55 bölmePanosuGösterisi = new J5c_55();
        JSplitPane üstBölüm = bölmePanosuGösterisi.bölmePanosunuAl();
        üstBölüm.setBorder (null);
        // Üst bölme panosuna dinleyiciye duyarlý listeyi kuralým...
        bölmePanosuGösterisi.resimListesiniAl().addListSelectionListener (this);

        // Alt bölüm etiketini yaratalým...
        etiket = new JLabel ("Listeden bir resim adý seçiniz", JLabel.CENTER);
        etiket.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // Yeni bir bölme panosu yaratýp üst panoyu ve etiketi içine koyalým...
        JSplitPane bölmePanosu = new JSplitPane (
                JSplitPane.VERTICAL_SPLIT, // Yatay düðmeli bölme çubuðu altýnda dikey etiket bölgesi...
                üstBölüm, // Listeyi ve resmi içeren önceki iki bölmeli pano...
                etiket); // Yeni dikey alt bölge...
        bölmePanosu.setOneTouchExpandable (true); // Bölme çubuklarý düðmeli...
        bölmePanosu.setDividerLocation (180);

        // Üst ve alt bölgelere emküçük ebatlarý tahsis edelim...
        üstBölüm.setMinimumSize (new Dimension (100, 50));
        etiket.setMinimumSize (new Dimension (100, 30));

        // Tüm bölme panosunu içerik panolu çerçeveye ekleyelim...
        getContentPane().add (bölmePanosu);
    } // J5c_56() kurucusu sonu...

    public void valueChanged (ListSelectionEvent olay) {
        if (olay.getValueIsAdjusting()) return;

        JList seçilenListe = (JList)olay.getSource();
        if (seçilenListe.isSelectionEmpty()) etiket.setText ("Seçilen yok!");
        else {int endeks = seçilenListe.getSelectedIndex();
            etiket.setText ("Seçilen resmin liste endeksi: " + endeks);
        } // else kararý sonu...
    } // valueChanged(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new J5c_56(); // Kurucuyu çaðýrýr...
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_56 sýnýfý sonu...