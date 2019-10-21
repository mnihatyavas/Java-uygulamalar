// J5c_27.java: LabelDemo (EtiketGösterimi) örneði.

import java.awt.GridLayout;
import java.awt.Color;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

/* Gerekli dosya:
 *   resim/orta.gif
 */
public class J5c_27 extends JPanel {
    public J5c_27() {// Kurucu...
        super (new GridLayout (3,1)); // 3 satýr ve 1 sütunlu yerleþim...
        JLabel etiket1, etiket2, etiket3;

        ImageIcon ikon = resimÝkonuYarat ("resim/orta.gif", "sempatik fakat anlamsýz bir damla saçýmý");

        // Ýlk etiketi yaratalým...
        etiket1 = new JLabel ("Etiket-1: Resim ve Metin", ikon, JLabel.CENTER);
        // Metni ikona göre (alt-ortalanmýþ) konumlandýralým...
        etiket1.setVerticalTextPosition (JLabel.BOTTOM);
        etiket1.setHorizontalTextPosition (JLabel.CENTER);
        etiket1.setForeground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );

        // Diðer 2 etiketi de yaratalým...
        etiket2 = new JLabel ("Etiket-2: Sadece Metin");
        etiket2.setForeground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
        etiket3 = new JLabel (ikon); // Metinsiz ikon...

        // ToolTip'le ipucu açýklamalarýný girelim...
        etiket1.setToolTipText ("Hem metin hem de ikon içeren bir etiket");
        etiket2.setToolTipText ("Sadece metin içeren bir etiket");
        etiket3.setToolTipText ("Sadece ikon içeren bir etiket");

        // Etiketlerimizi penceremize ekleyelim...
        add (etiket1);
        add (etiket2);
        add (etiket3);
        setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
    } // J5c_27() kurucusu sonu...

    protected static ImageIcon resimÝkonuYarat (String yol, String izah) {
        java.net.URL resimYureli = J5c_27.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli, izah);
        else {System.err.println ("[" + yol + "] adlý resim dosyasý bulunamadý!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Etiket Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_27());
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_27 sýnýfý sonu...