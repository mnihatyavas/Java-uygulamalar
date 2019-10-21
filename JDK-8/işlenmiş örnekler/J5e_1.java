/* J5e_1.java: AccessibleScrollDemo (EriþilebilirKaydýrmaGösterisi) örneði.
 *
 * J5c_44.java ile ayný...*/

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Insets;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;

import java.net.URL;

/* Gerekli dosyalar:
 *    Rule.java=J5e_1x1.java
 *    Corner.java=J5e_1x2.java
 *    ScrollablePicture.java=J5e_1x3.java
 *
 *    resim/arýlýÇiçek.jpg
 */
public class J5e_1 extends JPanel implements ItemListener {
    private J5e_1x1 yatayCetvel;
    private J5e_1x1 dikeyCetvel;

    private JToggleButton cetvelBirimiDüðmesi;
    private J5e_1x3 resim;

    public J5e_1() {// Kurucu...
        // Kullanýlacak resmi alalým...
        ImageIcon arýlýResim = resimÝkonunuYarat ("resim/arýlýÇiçek.jpg", "Arýlý bir çiçek fotoðrafý");

        // Resmin üst-yatay ve sol-dikey cetvellerini yaratalým...
        yatayCetvel = new J5e_1x1 (J5e_1x1.YATAY, true);
        if (arýlýResim != null) yatayCetvel.tercihiGeniþliðiKur (arýlýResim.getIconWidth());
        else yatayCetvel.tercihiGeniþliðiKur (320);
        yatayCetvel.getAccessibleContext().setAccessibleName ("Kolon Baþlýðý");
        yatayCetvel.getAccessibleContext().setAccessibleDescription ("Yatay kaydýrma çubuðuna baðlý olarak yatay cetveli gösterir.");

        dikeyCetvel = new J5e_1x1 (J5e_1x1.DÝKEY, true);
        if (arýlýResim != null) dikeyCetvel.tercihiYüksekliðiKur (arýlýResim.getIconHeight());
        else dikeyCetvel.tercihiYüksekliðiKur (350);
        dikeyCetvel.getAccessibleContext().setAccessibleName ("Satýr Baþlýðý");
        dikeyCetvel.getAccessibleContext().setAccessibleDescription ("Dikey kaydýrma çubuðuna baðlý olarak dikey cetveli gösterir.");

        // Köþeleri yaratalým...
        JPanel üstSolKöþe = new JPanel();
        cetvelBirimiDüðmesi = new JToggleButton ("ÖB", true); // ÖB: Ölçü Birimi...
        cetvelBirimiDüðmesi.setFont (new Font ("SansSerif", Font.PLAIN, 11));
        cetvelBirimiDüðmesi.setMargin (new Insets (2,2,2,2));
        cetvelBirimiDüðmesi.addItemListener (this); // sm/in düðmesini dinleyiciye duyarlayalým...
        cetvelBirimiDüðmesi.setToolTipText ("Cetvelin ölçüm birimini inç ile santim arasýnda deðiþtirir");
        üstSolKöþe.add (cetvelBirimiDüðmesi); //Varsayýlý FlowLayout serilimi kullanýlýr...
        üstSolKöþe.getAccessibleContext().setAccessibleName ("Üst Sol Köþe");
        üstSolKöþe.getAccessibleContext().setAccessibleDescription ("Kaydýrma panosu sol üst köþesini ölçek düðmesiyle doldurur.");

        J5e_1x2 üstSaðKöþe = new J5e_1x2();
        üstSaðKöþe.getAccessibleContext().setAccessibleName ("Üst Sað Köþe");
        üstSaðKöþe.getAccessibleContext().setAccessibleDescription ("Kaydýrma panosu köþesini cetcel uzantýsýyla doldurur.");

        J5e_1x2 altSolKöþe = new J5e_1x2();
        altSolKöþe.getAccessibleContext().setAccessibleName ("Alt Sol Köþe");
        altSolKöþe.getAccessibleContext().setAccessibleDescription ("Kaydýrma panosu köþesini cetcel uzantýsýyla doldurur.");
        
        // Kaydýrma panosunu kuralým...
        resim = new J5e_1x3 (arýlýResim, yatayCetvel.artýþýAl());
        try{resim.setToolTipText (arýlýResim.getDescription());}catch(NullPointerException ist){}
        resim.getAccessibleContext().setAccessibleName ("Resmin Kaydýrma Panosu");
        resim.getAccessibleContext().setAccessibleDescription ("Resmi kaydýrma panosuna koyar; resim yoksa uyarý yazar.");

        JScrollPane resimKaydýrmaPanosu = new JScrollPane (resim);
        resimKaydýrmaPanosu.setPreferredSize (new Dimension (300, 250));
        resimKaydýrmaPanosu.setViewportBorder (BorderFactory.createLineBorder (Color.black));

        resimKaydýrmaPanosu.setColumnHeaderView (yatayCetvel);
        resimKaydýrmaPanosu.setRowHeaderView (dikeyCetvel);

        resimKaydýrmaPanosu.setCorner (JScrollPane.UPPER_LEFT_CORNER, üstSolKöþe);
        resimKaydýrmaPanosu.setCorner(JScrollPane.UPPER_RIGHT_CORNER, üstSaðKöþe);
        resimKaydýrmaPanosu.setCorner(JScrollPane.LOWER_LEFT_CORNER, altSolKöþe);

        // Ýçerik panosuna BoxLayout serilimini kurup, kaydýrmayý ekleyip, çepeçevre 20px boþluk býrakalým...
        setLayout (new BoxLayout (this, BoxLayout.X_AXIS));
        add (resimKaydýrmaPanosu);
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
    } // J5e_1() kurucusu sonu...

    protected static ImageIcon resimÝkonunuYarat (String yol, String izah) {
        URL resimYureli = J5e_1.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli, izah);
        else {System.err.println ("[" + yol + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonunuYarat(..) metodu sonu...

    public void itemStateChanged (ItemEvent olay) {
        if (olay.getStateChange() == ItemEvent.SELECTED) {
            // Metriðe çevirelim...
            dikeyCetvel.cetveliKur (true);
            yatayCetvel.cetveliKur (true);
        } else {// Ýnçe çevirelim...
            dikeyCetvel.cetveliKur (false);
            yatayCetvel.cetveliKur (false);
        } // if-else kararý sonu...
        resim.azamiArtýþýKur (dikeyCetvel.artýþýAl());
    } // itemStateChanged(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Eriþilebilir Kaydýrma Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5e_1(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_1 sýnýfý sonu...