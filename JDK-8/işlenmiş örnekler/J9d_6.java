// J9d_6.java: LineBreakSample (Sat�r�K�rmaNumunesi) �rne�i.

import java.awt.Dimension;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class J9d_6 extends JApplet {
    public void kurUI (Container kab) {
        try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ist) {} // Ald�rma...
        J9d_6x sat�rK�rmaPaneli = new J9d_6x(); // Kurucusuz alt-s�n�f nesnesi yarat�r...
        kab.add (sat�rK�rmaPaneli, BorderLayout.CENTER);
    } // kurUI(..) metodu sonu...

    public static void main (String[] args) {
        JFrame �er�eve = new JFrame ("Sat�r K�rma Numunesi");
        �er�eve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        J9d_6 sat�rK�rmaNumunesi = new J9d_6(); // Kurucusuz s�n�f nesnesi yarat�r...
        sat�rK�rmaNumunesi.kurUI (�er�eve.getContentPane());        
        �er�eve.setSize (new Dimension (400, 250));
        �er�eve.setLocation (400, 50);
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J9d_6 s�n�f� sonu...