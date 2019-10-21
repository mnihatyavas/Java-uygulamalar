// J5f_1.java: AbsoluteLayoutDemo (MutlakSerimlemeGösterisi) örneði.

import java.awt.Color;
import java.awt.Container;
import java.awt.Insets;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// Düðmelerin konumu sol üst'e mutlak olup, çerçeve ebatýyla deðiþmez...
public class J5f_1 {
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Mutlak Serimleme Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        komponentleriKabaEkle (çerçeve.getContentPane());
        Insets boþluklar = çerçeve.getInsets();
        çerçeve.setSize (350 + boþluklar.left + boþluklar.right, 125 + boþluklar.top + boþluklar.bottom);
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void komponentleriKabaEkle (Container kab) {
        kab.setLayout (null);
        kab.setBackground (Color.CYAN);

        JButton düðme1 = new JButton ("1.Düðme");
        JButton düðme2 = new JButton ("2.Düðme");
        JButton düðme3 = new JButton ("3.Düðme");

        kab.add (düðme1);
        kab.add (düðme2);
        kab.add (düðme3);

        Insets boþluklar = kab.getInsets();
        Dimension ebat = düðme1.getPreferredSize();
        düðme1.setBounds (25 + boþluklar.left, 5 + boþluklar.top, ebat.width, ebat.height);
        ebat = düðme2.getPreferredSize();
        düðme2.setBounds (55 + boþluklar.left, 50 + boþluklar.top, ebat.width, ebat.height);
        ebat = düðme3.getPreferredSize();
        düðme3.setBounds(170 + boþluklar.left, 15 + boþluklar.top, ebat.width + 50, ebat.height + 20);
    } // komponentleriKabaEkle(..) metodu sonu...
} // J5f_1 sýnýfý sonu...