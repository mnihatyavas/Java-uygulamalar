// J5f_2.java: BorderLayoutDemo (S�n�rSerimlemeG�sterisi) �rne�i.

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

// D��me komponentleri �er�eve ebat�yla (s�n�rlara yap���k) esner...
public class J5f_2 {
    // "true" yaparak sat�r ba��yla sonunun yer de�i�tirdi�ini g�r�n...
    public static boolean SA�DAN_SOLA_MI = false;

    public static void komponentleriPanoyaEkle (Container kab) {
        if (!(kab.getLayout() instanceof BorderLayout)) {// ki varsay�l� serimleme...
            kab.add (new JLabel ("Konteyner kab� BorderLayout/S�n�rSerimleme kullanmamakta!"));
            return;
        } // if karar� sonu...

        if (SA�DAN_SOLA_MI) kab.setComponentOrientation (ComponentOrientation.RIGHT_TO_LEFT);

        JButton d��me = new JButton ("Uzun Adl� 1.D��me (PAGE_START/SAYFA_BA�I)");
        kab.add (d��me, BorderLayout.PAGE_START);

        d��me = new JButton ("2.D��me (CENTER/MERKEZ)");
        d��me.setPreferredSize (new Dimension (200, 100));
        kab.add (d��me, BorderLayout.CENTER);
        d��me.setBackground (Color.GREEN);
        d��me.setForeground (Color.BLUE);

        d��me = new JButton ("3.D��me (LINE_START/SATIR_BA�I)");
        kab.add (d��me, BorderLayout.LINE_START);

        d��me = new JButton ("Uzun-Adl� 4.D��me (PAGE_END/SAYFA_SONU)");
        kab.add (d��me, BorderLayout.PAGE_END);

        d��me = new JButton ("5.D��me (LINE_END/SATIR_SONU)");
        kab.add (d��me, BorderLayout.LINE_END);
    } // komponentleriPanoyaEkle(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("S�n�r Serimleme G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        komponentleriPanoyaEkle (�er�eve.getContentPane());
        // �er�eve.setLayout (new BorderLayout()); konteyner kab'�n varsay�l� serimlemesidir...
        �er�eve.setLocation (450,100);
        �er�eve.pack();
        �er�eve.setVisible(true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch.. blo�u sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5f_2 s�n�f� sonu...