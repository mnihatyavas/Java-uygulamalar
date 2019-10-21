// J5f_7.java: CustomLayoutDemo (ÖzelSerilimGösterisi) örneði.

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// Gereken dosya: J5f_7x.java=DiagonalLayout.java
public class J5f_7 {
    public static void komponentleriKabaEkle (Container kab) {
        kab.setLayout (new J5f_7x()); // Diðer program kurucusunu çaðýrýr...
        kab.setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // Tüm renkler...

        kab.add (new JButton ("Düðme 1"));
        kab.add (new JButton ("Düðme 2"));
        kab.add (new JButton ("Düðme 3"));
        kab.add (new JButton ("Düðme 4"));
        kab.add (new JButton ("Düðme 5"));
    } // komponentleriKabaEkle(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Özel Serilim Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        komponentleriKabaEkle (çerçeve.getContentPane());
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_7 sýnýfý sonu...