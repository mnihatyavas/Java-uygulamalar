// J5f_15.java: SpringDemo1 (KaynakG�sterisi1) �rne�i.

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class J5f_15 {
    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Kaynak G�sterisi 1");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        Container i�erikPanosu = �er�eve.getContentPane();
        i�erikPanosu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        SpringLayout kaynakSerilim = new SpringLayout();
        i�erikPanosu.setLayout (kaynakSerilim);
        i�erikPanosu.add (new JLabel ("Etiket: "));
        i�erikPanosu.add (new JTextField ("Metin sat�r�", 15));
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_15 s�n�f� sonu...