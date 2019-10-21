/* J4a_7.java: MenuChooserApplet (MenüSeçenAplet) örneði.
 *
 * <applet code="J4a_7.class" width="900" height="300"></applet>
 */

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

// Gereken alt-program: J4a_7x1.java=MenuItemChooser
public class J4a_7 extends JApplet {
    J4a_7x1 göster = null; // J4a_7x1/MenuItemChooser/MenüBirimiSeçicisi...

    public void init() {
        try {
            SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {
                    göster = new J4a_7x1();
                    add (göster);
                } // run() metodu sonu...
            }); // Sw..ifadesi sonu...
        }catch (Exception ist) {System.err.println ("GUI yaratýmý tamamlanmadý!");
        } // try-catch bloðu sonu...
    } // init() metodu sonu...

    public boolean isAppletDragStart (MouseEvent olay) {
        if (olay.getID() == MouseEvent.MOUSE_DRAGGED) return true;
        else return false;
    } // isA..(..) metodu sonu...

    public void setAppletCloseListener(ActionListener olay) {göster.setCloseListener (olay);}
    public void appletRestored() {göster.setCloseListener (null);}
} // J4a_7 sýnýfý sonu...