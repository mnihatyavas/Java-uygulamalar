// J5c_44.java: ScrollDemo (Kayd�ra�G�sterisi) �rne�i.

import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
//import javax.swing.border.*;

/* Gereken program ve resim dosyalar�:
 *   J5c_44x1.java=Rule.java
 *   J5c_44x2.java=ScrollablePicture.java
 *   J5c_44x3.java=Corner.java
 *   resim/ar�l��i�ek.jpg
 */
public class J5c_44 extends JPanel implements ItemListener {
    private J5c_44x1 dikeyCetvel;
    private J5c_44x1 yatayCetvel;
    private J5c_44x2 resimNesnesi;
    private JToggleButton �l�ekD��mesi;

    public J5c_44() {
        setLayout (new BoxLayout (this, BoxLayout.LINE_AXIS));

        // Kullan�lacak resmi alal�m...
        ImageIcon ar�l��i�ek = resim�konunuYarat ("resim/ar�l��i�ek.jpg");

        // Yatay ve dikey cetvelleri yaratal�m...
        dikeyCetvel = new J5c_44x1 (J5c_44x1.YATAY, true);
        yatayCetvel = new J5c_44x1 (J5c_44x1.D�KEY, true);

        if (ar�l��i�ek != null) {
            dikeyCetvel.tercihiEniKur (ar�l��i�ek.getIconWidth());
            yatayCetvel.tercihiBoyuKur (ar�l��i�ek.getIconHeight());
        }else {
            dikeyCetvel.tercihiEniKur (250);
            yatayCetvel.tercihiBoyuKur (261);
        } // if-else karar� sonu...

        // Sol �st k��edeki �l�ek d��meli paneli yarat�p kural�m...
        JPanel �l�ekD��mesiPaneli = new JPanel(); // Varsay�l� FlowLayout serilim...
        �l�ekD��mesi = new JToggleButton ("sm", true);
        �l�ekD��mesi.setFont (new Font ("SansSerif", Font.PLAIN, 11));
        �l�ekD��mesi.setMargin (new Insets (2,2,2,2));
        �l�ekD��mesiPaneli.setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) ));
        �l�ekD��mesi.addItemListener (this); // D��me t�klanmas�n� dinleyelim...
        �l�ekD��mesiPaneli.add (�l�ekD��mesi); 

        // Kayd�rma panelini yaratal�m...
        resimNesnesi = new J5c_44x2 (ar�l��i�ek, dikeyCetvel.art���Al());
        JScrollPane kayd�rmaPanosu = new JScrollPane (resimNesnesi);
        kayd�rmaPanosu.setPreferredSize (new Dimension (280, 293));
        kayd�rmaPanosu.setViewportBorder (BorderFactory.createLineBorder (Color.black));

        kayd�rmaPanosu.setColumnHeaderView (dikeyCetvel);
        kayd�rmaPanosu.setRowHeaderView (yatayCetvel);

        // Cetvelleri ve �l�ek d��mesini kenara/k��eye kural�m...
        kayd�rmaPanosu.setCorner (JScrollPane.UPPER_LEFT_CORNER, �l�ekD��mesiPaneli);
        kayd�rmaPanosu.setCorner (JScrollPane.LOWER_LEFT_CORNER, new J5c_44x3());
        kayd�rmaPanosu.setCorner (JScrollPane.UPPER_RIGHT_CORNER, new J5c_44x3());

        // Resimli cetvelli �l�ek d��meli kayd�rma panosunu penceremize ekleyelim...
        add (kayd�rmaPanosu);
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
        setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) ));
    } // J5c_44() kurucusu sonu...

    public void itemStateChanged (ItemEvent olay) {
        if (olay.getStateChange() == ItemEvent.SELECTED) {
            // "sm"ye �evirelim...
            �l�ekD��mesi.setText ("sm");
            yatayCetvel.metri�e�evrilsinMi (true);
            dikeyCetvel.metri�e�evrilsinMi (true);
        }else {
            // "in�"e �evirelim...
            �l�ekD��mesi.setText ("in�");
            yatayCetvel.metri�e�evrilsinMi (false);
            dikeyCetvel.metri�e�evrilsinMi (false);
        } // if-else karar� sonu...
        resimNesnesi.azamiBirimArt���Koy (yatayCetvel.art���Al());
    } // itemStateChanged(..) haz�r metodu sonu...

    protected static ImageIcon resim�konunuYarat (String yol) {
        java.net.URL resimYureli = J5c_44.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konunuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Kayd�ra� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5c_44(); // Kurucuya git...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_44 s�n�f� sonu...