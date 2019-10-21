// J5f_4.java: BoxLayoutDemo (KutuSerilimiGösterisi) örneði.

import java.awt.Component;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class J5f_4 {
    public static void komponentleriKabaEkle (Container kab) {
        kab.setLayout (new BoxLayout (kab, BoxLayout.Y_AXIS)); // Dikey altalta serilim...

        birDüðmeEkle ("Düðme 1", kab);
        birDüðmeEkle ("Düðme 2", kab);
        birDüðmeEkle ("Düðme 3", kab);
        birDüðmeEkle ("Uzun-Ýsimli Düðme 4", kab);
        birDüðmeEkle ("5", kab);
    } // public static void komponentleriKabaEkle(..) metodu sonu...

    private static void birDüðmeEkle (String metin, Container konteyner) {
        JButton düðme = new JButton (metin);
        düðme.setAlignmentX (Component.CENTER_ALIGNMENT); // Yatay ortalanmýþ...
        konteyner.add (düðme);
    } // birDüðmeEkle(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Kutu Serilimi Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        komponentleriKabaEkle (çerçeve.getContentPane());
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_4 sýnýfý sonu...