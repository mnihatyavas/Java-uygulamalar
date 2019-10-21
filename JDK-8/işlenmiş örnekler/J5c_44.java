// J5c_44.java: ScrollDemo (KaydýraçGösterisi) örneði.

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

/* Gereken program ve resim dosyalarý:
 *   J5c_44x1.java=Rule.java
 *   J5c_44x2.java=ScrollablePicture.java
 *   J5c_44x3.java=Corner.java
 *   resim/arýlýÇiçek.jpg
 */
public class J5c_44 extends JPanel implements ItemListener {
    private J5c_44x1 dikeyCetvel;
    private J5c_44x1 yatayCetvel;
    private J5c_44x2 resimNesnesi;
    private JToggleButton ölçekDüðmesi;

    public J5c_44() {
        setLayout (new BoxLayout (this, BoxLayout.LINE_AXIS));

        // Kullanýlacak resmi alalým...
        ImageIcon arýlýÇiçek = resimÝkonunuYarat ("resim/arýlýÇiçek.jpg");

        // Yatay ve dikey cetvelleri yaratalým...
        dikeyCetvel = new J5c_44x1 (J5c_44x1.YATAY, true);
        yatayCetvel = new J5c_44x1 (J5c_44x1.DÝKEY, true);

        if (arýlýÇiçek != null) {
            dikeyCetvel.tercihiEniKur (arýlýÇiçek.getIconWidth());
            yatayCetvel.tercihiBoyuKur (arýlýÇiçek.getIconHeight());
        }else {
            dikeyCetvel.tercihiEniKur (250);
            yatayCetvel.tercihiBoyuKur (261);
        } // if-else kararý sonu...

        // Sol üst köþedeki ölçek düðmeli paneli yaratýp kuralým...
        JPanel ölçekDüðmesiPaneli = new JPanel(); // Varsayýlý FlowLayout serilim...
        ölçekDüðmesi = new JToggleButton ("sm", true);
        ölçekDüðmesi.setFont (new Font ("SansSerif", Font.PLAIN, 11));
        ölçekDüðmesi.setMargin (new Insets (2,2,2,2));
        ölçekDüðmesiPaneli.setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) ));
        ölçekDüðmesi.addItemListener (this); // Düðme týklanmasýný dinleyelim...
        ölçekDüðmesiPaneli.add (ölçekDüðmesi); 

        // Kaydýrma panelini yaratalým...
        resimNesnesi = new J5c_44x2 (arýlýÇiçek, dikeyCetvel.artýþýAl());
        JScrollPane kaydýrmaPanosu = new JScrollPane (resimNesnesi);
        kaydýrmaPanosu.setPreferredSize (new Dimension (280, 293));
        kaydýrmaPanosu.setViewportBorder (BorderFactory.createLineBorder (Color.black));

        kaydýrmaPanosu.setColumnHeaderView (dikeyCetvel);
        kaydýrmaPanosu.setRowHeaderView (yatayCetvel);

        // Cetvelleri ve ölçek düðmesini kenara/köþeye kuralým...
        kaydýrmaPanosu.setCorner (JScrollPane.UPPER_LEFT_CORNER, ölçekDüðmesiPaneli);
        kaydýrmaPanosu.setCorner (JScrollPane.LOWER_LEFT_CORNER, new J5c_44x3());
        kaydýrmaPanosu.setCorner (JScrollPane.UPPER_RIGHT_CORNER, new J5c_44x3());

        // Resimli cetvelli ölçek düðmeli kaydýrma panosunu penceremize ekleyelim...
        add (kaydýrmaPanosu);
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
        setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) ));
    } // J5c_44() kurucusu sonu...

    public void itemStateChanged (ItemEvent olay) {
        if (olay.getStateChange() == ItemEvent.SELECTED) {
            // "sm"ye çevirelim...
            ölçekDüðmesi.setText ("sm");
            yatayCetvel.metriðeÇevrilsinMi (true);
            dikeyCetvel.metriðeÇevrilsinMi (true);
        }else {
            // "inç"e çevirelim...
            ölçekDüðmesi.setText ("inç");
            yatayCetvel.metriðeÇevrilsinMi (false);
            dikeyCetvel.metriðeÇevrilsinMi (false);
        } // if-else kararý sonu...
        resimNesnesi.azamiBirimArtýþýKoy (yatayCetvel.artýþýAl());
    } // itemStateChanged(..) hazýr metodu sonu...

    protected static ImageIcon resimÝkonunuYarat (String yol) {
        java.net.URL resimYureli = J5c_44.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonunuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Kaydýraç Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5c_44(); // Kurucuya git...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_44 sýnýfý sonu...