// J5f_7.java: CustomLayoutDemo (�zelSerilimG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// Gereken dosya: J5f_7x.java=DiagonalLayout.java
public class J5f_7 {
    public static void komponentleriKabaEkle (Container kab) {
        kab.setLayout (new J5f_7x()); // Di�er program kurucusunu �a��r�r...
        kab.setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // T�m renkler...

        kab.add (new JButton ("D��me 1"));
        kab.add (new JButton ("D��me 2"));
        kab.add (new JButton ("D��me 3"));
        kab.add (new JButton ("D��me 4"));
        kab.add (new JButton ("D��me 5"));
    } // komponentleriKabaEkle(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("�zel Serilim G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        komponentleriKabaEkle (�er�eve.getContentPane());
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_7 s�n�f� sonu...