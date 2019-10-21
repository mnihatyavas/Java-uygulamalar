// J5j_5.java: SwingPaintDemo2 (SwingBoyamaG�sterisi2) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class J5j_5 {
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        System.out.println ("EDT/EventDispatchThread (OlayRaporlamaSicimi) �zerinde GUI/GraphicsUserInterface (GrafikKullan�c�Aray�z�) yarat�ld� m�? "+ SwingUtilities.isEventDispatchThread());
        JFrame �er�eve = new JFrame ("Swing Boyama G�sterisi-2");
        �er�eve.setDefaultCloseOperation (3);  // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new Panelim()); // S�n�f kurucusunu �a��r�r�...
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...
} // J5j_5 s�n�f� sonu...

class Panelim extends JPanel {
    public Panelim() {// Kurucu...
        setBorder (BorderFactory.createLineBorder (Color.RED));
        setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()) );
    } // Panelim() kurucusu sonu...

    public Dimension getPreferredSize() {return new Dimension (250, 200);}

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        g.setColor (Color.YELLOW);
        g.drawString ("Bu benim �zel panelimdir!", 50, 80);
    } // paintComponent(..) haz�r metodu sonu...
} // Penelim s�n�f� sonu...