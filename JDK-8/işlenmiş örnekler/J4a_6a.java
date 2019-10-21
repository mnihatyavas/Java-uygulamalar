/* J4a_6a.java: DynamicTreeApplet (DinamikA�a�Apleti) �rne�i.
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
            System.err.println ("yaratGUI ba�ar�yla tamamlanamad�!");
        } // try-catch blo�u sonu...
    } // init() metodu sonu...

    private void yaratGUI() {
        // ��erik panosunu yarat�p kural�m...
        J4a_6x1 yeniPano = new J4a_6x1(); // J4a_6x1/DynamicTreePanel/DinamikA�a�Paneli...
        yeniPano.setOpaque (true);
        setContentPane (yeniPano);        
    } // yaratGUI() metodu sonu...
} // J4a_6a s�n�f� sonu...