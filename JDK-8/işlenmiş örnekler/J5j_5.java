// J5j_5.java: SwingPaintDemo2 (SwingBoyamaGösterisi2) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class J5j_5 {
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        System.out.println ("EDT/EventDispatchThread (OlayRaporlamaSicimi) üzerinde GUI/GraphicsUserInterface (GrafikKullanýcýArayüzü) yaratýldý mý? "+ SwingUtilities.isEventDispatchThread());
        JFrame çerçeve = new JFrame ("Swing Boyama Gösterisi-2");
        çerçeve.setDefaultCloseOperation (3);  // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new Panelim()); // Sýnýf kurucusunu çaðýrýrý...
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...
} // J5j_5 sýnýfý sonu...

class Panelim extends JPanel {
    public Panelim() {// Kurucu...
        setBorder (BorderFactory.createLineBorder (Color.RED));
        setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()) );
    } // Panelim() kurucusu sonu...

    public Dimension getPreferredSize() {return new Dimension (250, 200);}

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        g.setColor (Color.YELLOW);
        g.drawString ("Bu benim özel panelimdir!", 50, 80);
    } // paintComponent(..) hazýr metodu sonu...
} // Penelim sýnýfý sonu...