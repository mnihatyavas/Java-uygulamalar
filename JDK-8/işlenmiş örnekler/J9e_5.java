/* J9e_5.java: LoadImageApplet (ResmiYükleApleti) örneði.
* <applet code="J9e_5.class" width="180" height="180"> </applet>
 *>appletviewer J9e_5.java
*/

import java.applet.Applet;

import java.awt.Graphics;
import java.awt.Color;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.IOException;

import java.net.URL;

// Gereken resim dosyasý: çilek.jpg
public class J9e_5 extends Applet {
    private BufferedImage tResmi;

    public void init() {
        try {URL yurel = new URL (getCodeBase(), "resim/çilek.jpg");
            tResmi = ImageIO.read (yurel);
        }catch (IOException ist) {}
    } // init() hazýr metodu sonu...

    public void paint (Graphics g) {
        g.setColor (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()) ); // Tesadüfi her renk...
        g.fillRect (0, 0, getWidth(), getHeight());

        g.drawImage (tResmi, 50, 50, null);
    } // paint(..) hazýr metodu sonu...
} // J9e_5 sýnýfý sonu...