// J5f_10.java: GraphPaperTest (GrafikKa��d�Denemesi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

// Gereken dosya: J5f_10x.java/GraphPaperLayout.java
public class J5f_10 extends JPanel {
    public J5f_10() {// Kurucu...
        // (5x5) �l�ekli bir grafik ka��d�na kutucuk d��meleri koyal�m...
        setLayout (new J5f_10x (new Dimension (5,5))); // J5f_10x(..) kurucusunu �a��r�r...
        setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // T�m renkler...

        add (new JButton ("1"), new Rectangle (0,0, 1,1)); // (0,0) konumuna, (1,1) ebatl�...
        add (new JButton ("2"), new Rectangle (2,0, 2,1)); // (2,0) konumuna, (2,1) ebatl�...
        add (new JButton ("3"), new Rectangle (1,1, 1,2)); // (1,1) konumuna, (1,2) ebatl�...
        add (new JButton ("4"), new Rectangle (4,1, 1,1)); // (4,1) konumuna, (1,1) ebatl�...
        add (new JButton ("5"), new Rectangle (3,2, 2,2)); // (3,2) konumuna, (2,2) ebatl�...
        add (new JButton ("6"), new Rectangle (0,4, 1,1)); // (0,4) konumuna, (1,1) ebatl�...
        add (new JButton ("7"), new Rectangle (2,3, 1,2)); // (2,3) konumuna, (1,2) ebatl�...
        add (new JButton ("8"), new Rectangle (4,4, 1,1)); // (4,4) konumuna, (1,1) ebatl�...
    } // J5f_10() kurucusu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Grafik Ka��d� Denemesi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5f_10(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_10 s�n�f� sonu...