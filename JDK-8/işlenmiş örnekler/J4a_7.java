/* J4a_7.java: MenuChooserApplet (Men�Se�enAplet) �rne�i.
 *
 * <applet code="J4a_7.class" width="900" height="300"></applet>
 */

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

// Gereken alt-program: J4a_7x1.java=MenuItemChooser
public class J4a_7 extends JApplet {
    J4a_7x1 g�ster = null; // J4a_7x1/MenuItemChooser/Men�BirimiSe�icisi...

    public void init() {
        try {
            SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {
                    g�ster = new J4a_7x1();
                    add (g�ster);
                } // run() metodu sonu...
            }); // Sw..ifadesi sonu...
        }catch (Exception ist) {System.err.println ("GUI yarat�m� tamamlanmad�!");
        } // try-catch blo�u sonu...
    } // init() metodu sonu...

    public boolean isAppletDragStart (MouseEvent olay) {
        if (olay.getID() == MouseEvent.MOUSE_DRAGGED) return true;
        else return false;
    } // isA..(..) metodu sonu...

    public void setAppletCloseListener(ActionListener olay) {g�ster.setCloseListener (olay);}
    public void appletRestored() {g�ster.setCloseListener (null);}
} // J4a_7 s�n�f� sonu...