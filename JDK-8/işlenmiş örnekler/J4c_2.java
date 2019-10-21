/* J4c_2.java: TextEditorApplet (MetinDüzelticiApleti) örneði.
 *
 * <applet code="J4c_2.class" width="500" height="300"></applet>
 */

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

// Gereken alt-program: J4c_2x1.java=TextEditor
public class J4c_2 extends JApplet {
    public void init() {
        try {
            SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {yaratGUI();}
            }); // Sw.. ifadesi sonu...
        }catch (Exception ist) {System.err.println ("HATA: yaratGUI tamamlanamadý!");
        } // try-catch bloðu sonu...
    } // init() metodu sonu...

    private void yaratGUI() {
        J4c_2x1 yeniÝçerikPanosu = new J4c_2x1(); // J4c_2x1=TextEditor/MetinDüzeltici...
        //yeniÝçerikPanosu.setOpaque (true);
        add (yeniÝçerikPanosu); //setContentPane
    } // yaratGUI() metodu sonu...
} // J4c_2 sýnýfý sonu...