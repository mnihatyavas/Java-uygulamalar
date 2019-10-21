/* J4a_5.java: AppletTakesParams (ApletleAlýnanParametre) örneði.
 *
 * <applet code="J4a_5" width="500" height="50">
 * <param name="dizge1" value="M.Nihat Yavaþ"/>
 * <param name="dizge2" value="1957"/>
 * <param name="dizge3" value="J4a_5.java"/></applet>
 */

import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class J4a_5 extends JApplet {
    public void init() {
        final String  dizgeStr = getParameter ("dizge1");        
        final int sayýInt = Integer.parseInt (getParameter ("dizge2"));
        final String dosyaFile = getParameter ("dizge3");

        try {
           SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {yaratGUI (dizgeStr, sayýInt, dosyaFile);}
            }); // Sw..ifadesi sonu...
        }catch (Exception ist) {
            System.err.println ("yaratGUI tamamlanamadý!");
        } // try-catch bloðu sonu...
    } // init() metodu sonu...

    private void yaratGUI (String dizge, int sayý, String dosya) {
        String metin = "Aplet parametreleri: dizge: [" + dizge +
            "], tamsayý: [" + sayý + "] ve dosya: [" + dosya + "]";
        JLabel etiket = new JLabel (metin);
        add (etiket);
    } // createGUI(..) metodu sonu...
} // J4a_5 sýnýfý sonu...