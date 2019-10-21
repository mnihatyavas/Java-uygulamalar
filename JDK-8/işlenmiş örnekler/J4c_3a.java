/* J4c_3a.java: PropertiesArgsDemoApplet (ÖzelliklerArgümanlarýGösterimApleti) örneði.
 *
 * <applet code="J4c_3.class" width="500" height="500"></applet>
 */

import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class J4c_3a extends JApplet {
    public void init() {
        final String javaVersion = System.getProperty("java.runtime.version");
        final String  swing2dNoDrawProperty = System.getProperty("sun.java.launcher");
        final String  jnlpMyProperty = String.valueOf (System.getProperties());         //"jnlp.myProperty"

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    yaratGUI(javaVersion, swing2dNoDrawProperty, jnlpMyProperty);
                }
            });
        } catch (Exception e) {
            System.err.println("HATA: yaratGUI tamamlanamadý!");
        }
    } // init() metodu sonu...

    private void yaratGUI(String javaVersion, String swing2dNoDrawProperty, String jnlpMyProperty) {
        String text = "Properties: java.version = " + javaVersion + 
                ",  sun.java2d.noddraw = " + swing2dNoDrawProperty +
                ",   jnlp.myProperty = " + jnlpMyProperty;
        JLabel lbl = new JLabel(text);
        add(lbl);
    } // yaratGUI(..) metodu sonu...
} // J4c_3a sýnýfý sonu...