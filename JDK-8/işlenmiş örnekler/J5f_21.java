// J5f_21.java: TabDemo (Fi�G�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

public class J5f_21 {
    final static int fazladanPencereGeni�li�i = 100;

    public void par�alar�PanoyaEkle (Container kab) {
        JTabbedPane fi�liPano = new JTabbedPane();

        // Fi�li panel kartlar�n� yaratal�m...
        JPanel panelKart�1 = new JPanel() {
            // Tek sat�ra s�ralanmas� i�in kart geni�li�ini biraz geni� tutal�m...
            public Dimension getPreferredSize() {
                Dimension ebat = super.getPreferredSize();
                ebat.width += fazladanPencereGeni�li�i;
                return ebat;
            } // getPreferredSize() haz�r metodu sonu...
        }; // JPa.. ifadesi sonu...
        panelKart�1.add (new JButton ("D��me 1"));
        panelKart�1.add (new JButton ("D��me 2"));
        panelKart�1.add (new JButton ("D��me 3"));

        JPanel panelKart�2 = new JPanel();
        panelKart�2.add (new JTextField ("Metin Sat�r�", 20));

        fi�liPano.addTab ("D��meli Panel Fi�i", panelKart�1);
        fi�liPano.addTab ("Metinsat�rl� Panel Fi�i", panelKart�2);

        panelKart�1.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        panelKart�2.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        fi�liPano.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        fi�liPano.setForeground (Color.RED);

        kab.add (fi�liPano, BorderLayout.CENTER);
    } // par�alar�PanoyaEkle(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Fi� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5f_21 g�steri = new J5f_21(); // Varsay�l� kurucuyu �a��r�r...
        g�steri.par�alar�PanoyaEkle (�er�eve.getContentPane());
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ist) {ist.printStackTrace();}
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5c_21 s�n�f� sonu...