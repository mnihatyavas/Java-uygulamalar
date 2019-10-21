/* J4a_6a.java: DynamicTreeApplet (DinamikAðaçApleti) örneði.
 *
 * <applet code="J4a_6a" width="300" height="300"></applet>
 */

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

// Gereken alt_program: J4a_6x1.java=DynamicTreePanel
public class J4a_6a extends JApplet {
    public void init() {
        try {
            SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {yaratGUI();}
            }); // Sw..ifadesi sonu...
        }catch (Exception ist) { 
            System.err.println ("yaratGUI baþarýyla tamamlanamadý!");
        } // try-catch bloðu sonu...
    } // init() metodu sonu...

    private void yaratGUI() {
        // Ýçerik panosunu yaratýp kuralým...
        J4a_6x1 yeniPano = new J4a_6x1(); // J4a_6x1/DynamicTreePanel/DinamikAðaçPaneli...
        yeniPano.setOpaque (true);
        setContentPane (yeniPano);        
    } // yaratGUI() metodu sonu...
} // J4a_6a sýnýfý sonu...