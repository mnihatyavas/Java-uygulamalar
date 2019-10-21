// J5j_4.java: SwingPaintDemo1 (SwingBoyamaG�sterisi1) �rne�i.

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class J5j_4{
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        System.out.println ("EDT/EventDispatchThread (OlayRaporlamaSicimi) �zerinde GUI/GraphicsUserInterface (GrafikKullan�c�Aray�z�) yarat�ld� m�? "+ SwingUtilities.isEventDispatchThread());
        JFrame �er�eve = new JFrame ("Swing Boyama G�sterisi-1");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.setSize (250,250);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...
} // J5j_4 s�n�f� sonu...