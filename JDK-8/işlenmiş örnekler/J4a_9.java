/* J4a_9.java: HelloWorld (MerhabaD�nya) �rne�i.
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
                    JLabel etiket = new JLabel ("Merhaba Aplet D�nyas�!");
                    add (etiket);
                } // run() metodu sonu...
            }); // Sw..ifadesi sonu...
        }catch (Exception ist) {System.err.println ("yaratGUI tamamlanmad�!");
        } // try-catch blo�u sonu...
    } // init() metodu sonu...
} // J4a_9 s�n�f� sonu...