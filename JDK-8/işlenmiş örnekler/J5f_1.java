// J5f_1.java: AbsoluteLayoutDemo (MutlakSerimlemeG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Container;
import java.awt.Insets;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// D��melerin konumu sol �st'e mutlak olup, �er�eve ebat�yla de�i�mez...
public class J5f_1 {
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Mutlak Serimleme G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        komponentleriKabaEkle (�er�eve.getContentPane());
        Insets bo�luklar = �er�eve.getInsets();
        �er�eve.setSize (350 + bo�luklar.left + bo�luklar.right, 125 + bo�luklar.top + bo�luklar.bottom);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void komponentleriKabaEkle (Container kab) {
        kab.setLayout (null);
        kab.setBackground (Color.CYAN);

        JButton d��me1 = new JButton ("1.D��me");
        JButton d��me2 = new JButton ("2.D��me");
        JButton d��me3 = new JButton ("3.D��me");

        kab.add (d��me1);
        kab.add (d��me2);
        kab.add (d��me3);

        Insets bo�luklar = kab.getInsets();
        Dimension ebat = d��me1.getPreferredSize();
        d��me1.setBounds (25 + bo�luklar.left, 5 + bo�luklar.top, ebat.width, ebat.height);
        ebat = d��me2.getPreferredSize();
        d��me2.setBounds (55 + bo�luklar.left, 50 + bo�luklar.top, ebat.width, ebat.height);
        ebat = d��me3.getPreferredSize();
        d��me3.setBounds(170 + bo�luklar.left, 15 + bo�luklar.top, ebat.width + 50, ebat.height + 20);
    } // komponentleriKabaEkle(..) metodu sonu...
} // J5f_1 s�n�f� sonu...