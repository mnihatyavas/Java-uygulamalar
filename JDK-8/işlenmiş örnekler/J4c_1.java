/* J4c_1.java: CookieApplet (ÇerezApleti) örneði.
 *
 * <applet code="J4c_1.class" width="300" height="300"></applet>
 */

import java.applet.Applet;

// Gereken alt-program: J4c_1x.java=CookieAccessor
public class J4c_1 extends Applet {
    public void start() {
        J4c_1x hatýrlatýcýEriþimi = new J4c_1x(); // J4c_1x=CookieAccessor/Hatýrlatýcý(Çerez)Eriþimi
        hatýrlatýcýEriþimi.hatýrlatýcýyaEriþim();
    } // start() metodu sonu...
} // J4c_1 sýnýfý sonu...