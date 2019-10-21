/* J4a_12.java: Simple (Basit) �rne�i.
 *
 * <applet code="J4a_12.class" width="600" height="600"></applet>
 */

import java.applet.Applet;
import java.awt.Graphics;

public class J4a_12 extends Applet {
    StringBuffer tampon;

    public void init() {
        tampon = new StringBuffer();
        �er�eveEkle ("init() metodunday�m... ");
    } // init() metodu sonu...

    public void start() {�er�eveEkle ("start() metodunday�m...");}
    public void stop() {�er�eveEkle ("stop() metodunday�m...");}
    public void destroy() {�er�eveEkle ("destroy() metodunday�m...");}

    private void �er�eveEkle (String yeniKelime) {
        System.out.println (yeniKelime);
        tampon.append (yeniKelime);
        repaint();
    } // �er�eveEkle(..) metodu sonu...

    public void paint (Graphics g) {
        // 1 sn.de bir k���len bir �er�eve �izelim...
        for (int i = 0; i < 200; i +=10) {
            try {Thread.sleep (1000);} catch (Exception ist){}
            g.drawRect (0, 0, getWidth() - i, getHeight() - i);
            g.drawString (tampon.toString(), 10, 25);
        } // for d�ng�s� sonu...
        stop(); try {Thread.sleep (1000);} catch (Exception ist){}
        destroy(); try {Thread.sleep (1000);} catch (Exception ist){}
    } // paint(..) metodu sonu...
} // J4a_12 s�n�f� sonu...