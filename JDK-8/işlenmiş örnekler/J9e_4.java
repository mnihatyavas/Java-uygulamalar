// J9e_4.java: LoadImageApp (Y�kleResmiUygulamas�) �rne�i.

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JFrame;

// Gereken resim dosyas�: �ilek.jpg
public class J9e_4 extends Component {
    BufferedImage tResim;

    public void paint (Graphics g) {
        g.setColor (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()) ); // Tesad�fi her renk...
        g.fillRect (0,0, getWidth(), getHeight() );

        g.drawImage (tResim, 50, 50, null);
    } // paint(..) haz�r metodu sonu...

    public J9e_4() {// Kurucu...
       try {tResim = ImageIO.read (new File ("resim/�ilek.jpg"));
       }catch (IOException ist) {}
       //repaint();
    } // J9e_4() kurucusu sonu...

/*
    public Dimension getPreferredSize() {
        if (tResim == null) return new Dimension (100,100);
        else return new Dimension (tResim.getWidth(), tResim.getHeight());
    } // getPreferredSize() --haz�r pack() i�in-- metodu sonu...
*/

    public static void main (String[] args) {
        JFrame �er�eve = new JFrame ("Resim Y�kleme Uygulamas�");
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        �er�eve.add (new J9e_4()); // Kurucuyu �a��r�r...
        �er�eve.setSize (200, 220);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J9e_4 s�n�f� sonu...