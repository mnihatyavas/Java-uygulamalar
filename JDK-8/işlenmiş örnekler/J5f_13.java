// J5f_13.java: SpringBox (KaynakKutusu) �rne�i.

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

// Gereken dosya: J5f_13x.java=SpringUtilities.java
public class J5f_13 {
    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Kaynak Kutusu");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        Container i�erikPanosu = �er�eve.getContentPane();
        i�erikPanosu.setLayout (new SpringLayout()); // Kaynak Serilimli i�erik panosu...
        i�erikPanosu.add (new JButton ("D��me 1"));
        i�erikPanosu.add (new JButton ("D��me 2"));
        i�erikPanosu.add (new JButton ("D��me 3"));
        i�erikPanosu.add (new JButton ("Uzun-�simli D��me 4"));
        i�erikPanosu.add (new JButton ("5"));
        i�erikPanosu.setBackground (Color.ORANGE);
        J5f_13x.kesifIzgaraYap (
                i�erikPanosu, // ��erik panosu, kab...
                1, // Sat�r say�s�...
                i�erikPanosu.getComponentCount(), // Kolon say�s�...
                6, 6, // (x,y) konumu...
                6, 6); // x yatay ve y dikey tampon aral���...
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_13 s�n�f� sonu...