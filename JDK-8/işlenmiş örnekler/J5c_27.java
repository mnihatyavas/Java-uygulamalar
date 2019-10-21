// J5c_27.java: LabelDemo (EtiketG�sterimi) �rne�i.

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
        super (new GridLayout (3,1)); // 3 sat�r ve 1 s�tunlu yerle�im...
        JLabel etiket1, etiket2, etiket3;

        ImageIcon ikon = resim�konuYarat ("resim/orta.gif", "sempatik fakat anlams�z bir damla sa��m�");

        // �lk etiketi yaratal�m...
        etiket1 = new JLabel ("Etiket-1: Resim ve Metin", ikon, JLabel.CENTER);
        // Metni ikona g�re (alt-ortalanm��) konumland�ral�m...
        etiket1.setVerticalTextPosition (JLabel.BOTTOM);
        etiket1.setHorizontalTextPosition (JLabel.CENTER);
        etiket1.setForeground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );

        // Di�er 2 etiketi de yaratal�m...
        etiket2 = new JLabel ("Etiket-2: Sadece Metin");
        etiket2.setForeground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
        etiket3 = new JLabel (ikon); // Metinsiz ikon...

        // ToolTip'le ipucu a��klamalar�n� girelim...
        etiket1.setToolTipText ("Hem metin hem de ikon i�eren bir etiket");
        etiket2.setToolTipText ("Sadece metin i�eren bir etiket");
        etiket3.setToolTipText ("Sadece ikon i�eren bir etiket");

        // Etiketlerimizi penceremize ekleyelim...
        add (etiket1);
        add (etiket2);
        add (etiket3);
        setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
    } // J5c_27() kurucusu sonu...

    protected static ImageIcon resim�konuYarat (String yol, String izah) {
        java.net.URL resimYureli = J5c_27.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli, izah);
        else {System.err.println ("[" + yol + "] adl� resim dosyas� bulunamad�!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Etiket G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_27());
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_27 s�n�f� sonu...