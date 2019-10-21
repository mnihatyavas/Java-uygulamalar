// J5j_4.java: SwingPaintDemo1 (SwingBoyamaGösterisi1) örneði.

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class J5j_4{
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        System.out.println ("EDT/EventDispatchThread (OlayRaporlamaSicimi) üzerinde GUI/GraphicsUserInterface (GrafikKullanýcýArayüzü) yaratýldý mý? "+ SwingUtilities.isEventDispatchThread());
        JFrame çerçeve = new JFrame ("Swing Boyama Gösterisi-1");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.setSize (250,250);
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...
} // J5j_4 sýnýfý sonu...