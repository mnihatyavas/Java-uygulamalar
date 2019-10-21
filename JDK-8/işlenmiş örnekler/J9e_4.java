// J9e_4.java: LoadImageApp (YükleResmiUygulamasý) örneði.

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JFrame;

// Gereken resim dosyasý: çilek.jpg
public class J9e_4 extends Component {
    BufferedImage tResim;

    public void paint (Graphics g) {
        g.setColor (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()) ); // Tesadüfi her renk...
        g.fillRect (0,0, getWidth(), getHeight() );

        g.drawImage (tResim, 50, 50, null);
    } // paint(..) hazýr metodu sonu...

    public J9e_4() {// Kurucu...
       try {tResim = ImageIO.read (new File ("resim/çilek.jpg"));
       }catch (IOException ist) {}
       //repaint();
    } // J9e_4() kurucusu sonu...

/*
    public Dimension getPreferredSize() {
        if (tResim == null) return new Dimension (100,100);
        else return new Dimension (tResim.getWidth(), tResim.getHeight());
    } // getPreferredSize() --hazýr pack() için-- metodu sonu...
*/

    public static void main (String[] args) {
        JFrame çerçeve = new JFrame ("Resim Yükleme Uygulamasý");
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        çerçeve.add (new J9e_4()); // Kurucuyu çaðýrýr...
        çerçeve.setSize (200, 220);
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J9e_4 sýnýfý sonu...