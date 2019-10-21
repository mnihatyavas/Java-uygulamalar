/* J4a_4.java: WeatherApplet (HavaApleti) �rne�i.
 *
 * <applet code="J4a_4.class" width="300" height="300"></applet>
 */

// Gereken alt-program: J4a_3x.java=WeatherData
import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public class J4a_4 extends JApplet {
public void init() {
        // Bir sicimle bu apletin GUI/GBA'n� yaratacak bir g�rev i�letelim...
        try {
            SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {// J4a_3x=WeatherData/HavaVerileri...
                    J4a_3x yeniPano = J4a_3x.havaVerileriPaneliniAl (false);
                    add (yeniPano);
                }
            });
        } catch (Exception ist) {
            System.err.println ("GUI/GBA yaratma tamamlanamad�!");
        } // try-catch blo�u sonu...
    } // init() metodu sonu...
} // J4a_4 s�n�f� sonu...