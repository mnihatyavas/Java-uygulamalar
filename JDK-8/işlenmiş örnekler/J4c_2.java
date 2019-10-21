/* J4c_2.java: TextEditorApplet (MetinD�zelticiApleti) �rne�i.
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
        }catch (Exception ist) {System.err.println ("HATA: yaratGUI tamamlanamad�!");
        } // try-catch blo�u sonu...
    } // init() metodu sonu...

    private void yaratGUI() {
        J4c_2x1 yeni��erikPanosu = new J4c_2x1(); // J4c_2x1=TextEditor/MetinD�zeltici...
        //yeni��erikPanosu.setOpaque (true);
        add (yeni��erikPanosu); //setContentPane
    } // yaratGUI() metodu sonu...
} // J4c_2 s�n�f� sonu...