/* J5e_1.java: AccessibleScrollDemo (Eri�ilebilirKayd�rmaG�sterisi) �rne�i.
 *
 * J5c_44.java ile ayn�...*/

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
 *    resim/ar�l��i�ek.jpg
 */
public class J5e_1 extends JPanel implements ItemListener {
    private J5e_1x1 yatayCetvel;
    private J5e_1x1 dikeyCetvel;

    private JToggleButton cetvelBirimiD��mesi;
    private J5e_1x3 resim;

    public J5e_1() {// Kurucu...
        // Kullan�lacak resmi alal�m...
        ImageIcon ar�l�Resim = resim�konunuYarat ("resim/ar�l��i�ek.jpg", "Ar�l� bir �i�ek foto�raf�");

        // Resmin �st-yatay ve sol-dikey cetvellerini yaratal�m...
        yatayCetvel = new J5e_1x1 (J5e_1x1.YATAY, true);
        if (ar�l�Resim != null) yatayCetvel.tercihiGeni�li�iKur (ar�l�Resim.getIconWidth());
        else yatayCetvel.tercihiGeni�li�iKur (320);
        yatayCetvel.getAccessibleContext().setAccessibleName ("Kolon Ba�l���");
        yatayCetvel.getAccessibleContext().setAccessibleDescription ("Yatay kayd�rma �ubu�una ba�l� olarak yatay cetveli g�sterir.");

        dikeyCetvel = new J5e_1x1 (J5e_1x1.D�KEY, true);
        if (ar�l�Resim != null) dikeyCetvel.tercihiY�ksekli�iKur (ar�l�Resim.getIconHeight());
        else dikeyCetvel.tercihiY�ksekli�iKur (350);
        dikeyCetvel.getAccessibleContext().setAccessibleName ("Sat�r Ba�l���");
        dikeyCetvel.getAccessibleContext().setAccessibleDescription ("Dikey kayd�rma �ubu�una ba�l� olarak dikey cetveli g�sterir.");

        // K��eleri yaratal�m...
        JPanel �stSolK��e = new JPanel();
        cetvelBirimiD��mesi = new JToggleButton ("�B", true); // �B: �l�� Birimi...
        cetvelBirimiD��mesi.setFont (new Font ("SansSerif", Font.PLAIN, 11));
        cetvelBirimiD��mesi.setMargin (new Insets (2,2,2,2));
        cetvelBirimiD��mesi.addItemListener (this); // sm/in d��mesini dinleyiciye duyarlayal�m...
        cetvelBirimiD��mesi.setToolTipText ("Cetvelin �l��m birimini in� ile santim aras�nda de�i�tirir");
        �stSolK��e.add (cetvelBirimiD��mesi); //Varsay�l� FlowLayout serilimi kullan�l�r...
        �stSolK��e.getAccessibleContext().setAccessibleName ("�st Sol K��e");
        �stSolK��e.getAccessibleContext().setAccessibleDescription ("Kayd�rma panosu sol �st k��esini �l�ek d��mesiyle doldurur.");

        J5e_1x2 �stSa�K��e = new J5e_1x2();
        �stSa�K��e.getAccessibleContext().setAccessibleName ("�st Sa� K��e");
        �stSa�K��e.getAccessibleContext().setAccessibleDescription ("Kayd�rma panosu k��esini cetcel uzant�s�yla doldurur.");

        J5e_1x2 altSolK��e = new J5e_1x2();
        altSolK��e.getAccessibleContext().setAccessibleName ("Alt Sol K��e");
        altSolK��e.getAccessibleContext().setAccessibleDescription ("Kayd�rma panosu k��esini cetcel uzant�s�yla doldurur.");
        
        // Kayd�rma panosunu kural�m...
        resim = new J5e_1x3 (ar�l�Resim, yatayCetvel.art���Al());
        try{resim.setToolTipText (ar�l�Resim.getDescription());}catch(NullPointerException ist){}
        resim.getAccessibleContext().setAccessibleName ("Resmin Kayd�rma Panosu");
        resim.getAccessibleContext().setAccessibleDescription ("Resmi kayd�rma panosuna koyar; resim yoksa uyar� yazar.");

        JScrollPane resimKayd�rmaPanosu = new JScrollPane (resim);
        resimKayd�rmaPanosu.setPreferredSize (new Dimension (300, 250));
        resimKayd�rmaPanosu.setViewportBorder (BorderFactory.createLineBorder (Color.black));

        resimKayd�rmaPanosu.setColumnHeaderView (yatayCetvel);
        resimKayd�rmaPanosu.setRowHeaderView (dikeyCetvel);

        resimKayd�rmaPanosu.setCorner (JScrollPane.UPPER_LEFT_CORNER, �stSolK��e);
        resimKayd�rmaPanosu.setCorner(JScrollPane.UPPER_RIGHT_CORNER, �stSa�K��e);
        resimKayd�rmaPanosu.setCorner(JScrollPane.LOWER_LEFT_CORNER, altSolK��e);

        // ��erik panosuna BoxLayout serilimini kurup, kayd�rmay� ekleyip, �epe�evre 20px bo�luk b�rakal�m...
        setLayout (new BoxLayout (this, BoxLayout.X_AXIS));
        add (resimKayd�rmaPanosu);
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
    } // J5e_1() kurucusu sonu...

    protected static ImageIcon resim�konunuYarat (String yol, String izah) {
        URL resimYureli = J5e_1.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli, izah);
        else {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konunuYarat(..) metodu sonu...

    public void itemStateChanged (ItemEvent olay) {
        if (olay.getStateChange() == ItemEvent.SELECTED) {
            // Metri�e �evirelim...
            dikeyCetvel.cetveliKur (true);
            yatayCetvel.cetveliKur (true);
        } else {// �n�e �evirelim...
            dikeyCetvel.cetveliKur (false);
            yatayCetvel.cetveliKur (false);
        } // if-else karar� sonu...
        resim.azamiArt���Kur (dikeyCetvel.art���Al());
    } // itemStateChanged(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Eri�ilebilir Kayd�rma G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5e_1(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_1 s�n�f� sonu...