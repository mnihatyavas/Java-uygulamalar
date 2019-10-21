// J5f_13.java: SpringBox (KaynakKutusu) örneði.

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

// Gereken dosya: J5f_13x.java=SpringUtilities.java
public class J5f_13 {
    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Kaynak Kutusu");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        Container içerikPanosu = çerçeve.getContentPane();
        içerikPanosu.setLayout (new SpringLayout()); // Kaynak Serilimli içerik panosu...
        içerikPanosu.add (new JButton ("Düðme 1"));
        içerikPanosu.add (new JButton ("Düðme 2"));
        içerikPanosu.add (new JButton ("Düðme 3"));
        içerikPanosu.add (new JButton ("Uzun-Ýsimli Düðme 4"));
        içerikPanosu.add (new JButton ("5"));
        içerikPanosu.setBackground (Color.ORANGE);
        J5f_13x.kesifIzgaraYap (
                içerikPanosu, // Ýçerik panosu, kab...
                1, // Satýr sayýsý...
                içerikPanosu.getComponentCount(), // Kolon sayýsý...
                6, 6, // (x,y) konumu...
                6, 6); // x yatay ve y dikey tampon aralýðý...
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_13 sýnýfý sonu...