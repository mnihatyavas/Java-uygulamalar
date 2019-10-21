/* J4a_12.java: Simple (Basit) örneði.
 *
 * <applet code="J4a_12.class" width="600" height="600"></applet>
 */

import java.applet.Applet;
import java.awt.Graphics;

public class J4a_12 extends Applet {
    StringBuffer tampon;

    public void init() {
        tampon = new StringBuffer();
        çerçeveEkle ("init() metodundayým... ");
    } // init() metodu sonu...

    public void start() {çerçeveEkle ("start() metodundayým...");}
    public void stop() {çerçeveEkle ("stop() metodundayým...");}
    public void destroy() {çerçeveEkle ("destroy() metodundayým...");}

    private void çerçeveEkle (String yeniKelime) {
        System.out.println (yeniKelime);
        tampon.append (yeniKelime);
        repaint();
    } // çerçeveEkle(..) metodu sonu...

    public void paint (Graphics g) {
        // 1 sn.de bir küçülen bir çerçeve çizelim...
        for (int i = 0; i < 200; i +=10) {
            try {Thread.sleep (1000);} catch (Exception ist){}
            g.drawRect (0, 0, getWidth() - i, getHeight() - i);
            g.drawString (tampon.toString(), 10, 25);
        } // for döngüsü sonu...
        stop(); try {Thread.sleep (1000);} catch (Exception ist){}
        destroy(); try {Thread.sleep (1000);} catch (Exception ist){}
    } // paint(..) metodu sonu...
} // J4a_12 sýnýfý sonu...