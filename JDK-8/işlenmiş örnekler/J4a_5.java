/* J4a_5.java: AppletTakesParams (ApletleAl�nanParametre) �rne�i.
 *
 * <applet code="J4a_5" width="500" height="50">
 * <param name="dizge1" value="M.Nihat Yava�"/>
 * <param name="dizge2" value="1957"/>
 * <param name="dizge3" value="J4a_5.java"/></applet>
 */

import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class J4a_5 extends JApplet {
    public void init() {
        final String  dizgeStr = getParameter ("dizge1");        
        final int say�Int = Integer.parseInt (getParameter ("dizge2"));
        final String dosyaFile = getParameter ("dizge3");

        try {
           SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {yaratGUI (dizgeStr, say�Int, dosyaFile);}
            }); // Sw..ifadesi sonu...
        }catch (Exception ist) {
            System.err.println ("yaratGUI tamamlanamad�!");
        } // try-catch blo�u sonu...
    } // init() metodu sonu...

    private void yaratGUI (String dizge, int say�, String dosya) {
        String metin = "Aplet parametreleri: dizge: [" + dizge +
            "], tamsay�: [" + say� + "] ve dosya: [" + dosya + "]";
        JLabel etiket = new JLabel (metin);
        add (etiket);
    } // createGUI(..) metodu sonu...
} // J4a_5 s�n�f� sonu...