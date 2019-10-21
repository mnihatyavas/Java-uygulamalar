// J5f_2.java: BorderLayoutDemo (SýnýrSerimlemeGösterisi) örneði.

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.ComponentOrientation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

// Düðme komponentleri çerçeve ebatýyla (sýnýrlara yapýþýk) esner...
public class J5f_2 {
    // "true" yaparak satýr baþýyla sonunun yer deðiþtirdiðini görün...
    public static boolean SAÐDAN_SOLA_MI = false;

    public static void komponentleriPanoyaEkle (Container kab) {
        if (!(kab.getLayout() instanceof BorderLayout)) {// ki varsayýlý serimleme...
            kab.add (new JLabel ("Konteyner kabý BorderLayout/SýnýrSerimleme kullanmamakta!"));
            return;
        } // if kararý sonu...

        if (SAÐDAN_SOLA_MI) kab.setComponentOrientation (ComponentOrientation.RIGHT_TO_LEFT);

        JButton düðme = new JButton ("Uzun Adlý 1.Düðme (PAGE_START/SAYFA_BAÞI)");
        kab.add (düðme, BorderLayout.PAGE_START);

        düðme = new JButton ("2.Düðme (CENTER/MERKEZ)");
        düðme.setPreferredSize (new Dimension (200, 100));
        kab.add (düðme, BorderLayout.CENTER);
        düðme.setBackground (Color.GREEN);
        düðme.setForeground (Color.BLUE);

        düðme = new JButton ("3.Düðme (LINE_START/SATIR_BAÞI)");
        kab.add (düðme, BorderLayout.LINE_START);

        düðme = new JButton ("Uzun-Adlý 4.Düðme (PAGE_END/SAYFA_SONU)");
        kab.add (düðme, BorderLayout.PAGE_END);

        düðme = new JButton ("5.Düðme (LINE_END/SATIR_SONU)");
        kab.add (düðme, BorderLayout.LINE_END);
    } // komponentleriPanoyaEkle(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Sýnýr Serimleme Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        komponentleriPanoyaEkle (çerçeve.getContentPane());
        // çerçeve.setLayout (new BorderLayout()); konteyner kab'ýn varsayýlý serimlemesidir...
        çerçeve.setLocation (450,100);
        çerçeve.pack();
        çerçeve.setVisible(true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch.. bloðu sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5f_2 sýnýfý sonu...