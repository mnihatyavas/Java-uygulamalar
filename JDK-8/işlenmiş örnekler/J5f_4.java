// J5f_4.java: BoxLayoutDemo (KutuSerilimiG�sterisi) �rne�i.

import java.awt.Component;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class J5f_4 {
    public static void komponentleriKabaEkle (Container kab) {
        kab.setLayout (new BoxLayout (kab, BoxLayout.Y_AXIS)); // Dikey altalta serilim...

        birD��meEkle ("D��me 1", kab);
        birD��meEkle ("D��me 2", kab);
        birD��meEkle ("D��me 3", kab);
        birD��meEkle ("Uzun-�simli D��me 4", kab);
        birD��meEkle ("5", kab);
    } // public static void komponentleriKabaEkle(..) metodu sonu...

    private static void birD��meEkle (String metin, Container konteyner) {
        JButton d��me = new JButton (metin);
        d��me.setAlignmentX (Component.CENTER_ALIGNMENT); // Yatay ortalanm��...
        konteyner.add (d��me);
    } // birD��meEkle(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Kutu Serilimi G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        komponentleriKabaEkle (�er�eve.getContentPane());
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_4 s�n�f� sonu...