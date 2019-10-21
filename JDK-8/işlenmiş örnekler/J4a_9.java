/* J4a_9.java: HelloWorld (MerhabaDünya) örneði.
 *
 * <applet code="J4a_9.class" width="200" height="50"></applet>
 */

import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class J4a_9 extends JApplet {
    public void init() {
        try {
            SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {
                    JLabel etiket = new JLabel ("Merhaba Aplet Dünyasý!");
                    add (etiket);
                } // run() metodu sonu...
            }); // Sw..ifadesi sonu...
        }catch (Exception ist) {System.err.println ("yaratGUI tamamlanmadý!");
        } // try-catch bloðu sonu...
    } // init() metodu sonu...
} // J4a_9 sýnýfý sonu...