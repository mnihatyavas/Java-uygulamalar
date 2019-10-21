// J9d_6.java: LineBreakSample (SatýrýKýrmaNumunesi) örneði.

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
        }catch (Exception ist) {} // Aldýrma...
        J9d_6x satýrKýrmaPaneli = new J9d_6x(); // Kurucusuz alt-sýnýf nesnesi yaratýr...
        kab.add (satýrKýrmaPaneli, BorderLayout.CENTER);
    } // kurUI(..) metodu sonu...

    public static void main (String[] args) {
        JFrame çerçeve = new JFrame ("Satýr Kýrma Numunesi");
        çerçeve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        J9d_6 satýrKýrmaNumunesi = new J9d_6(); // Kurucusuz sýnýf nesnesi yaratýr...
        satýrKýrmaNumunesi.kurUI (çerçeve.getContentPane());        
        çerçeve.setSize (new Dimension (400, 250));
        çerçeve.setLocation (400, 50);
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J9d_6 sýnýfý sonu...