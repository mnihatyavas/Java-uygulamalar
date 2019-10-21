// J9d_1.java: AntialiasedText (Kalitele�tirilenMetin) �rne�i.

import java.applet.Applet;

import java.awt.Frame;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class J9d_1 extends Applet {
    public void init() {repaint();} // Haz�r aplet metodu...

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        String metin = "Merhaba D�nya!..";
        Font yaz�Fonu = new Font (Font.SERIF, Font.BOLD, 60);
        g2d.setFont (yaz�Fonu);
        g2d.setColor (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Tesad�fi a��k renkler...
        g2d.fillRect (0, 0, getWidth(), getHeight());

        g2d.setColor (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Tesad�fi koyu renkler...
        g2d.setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        g2d.drawString ("ANTIALIAS_OFF: "+metin, 20,50);

        g2d.setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.drawString ("ANTIALIAS_ON: "+metin, 20,150);

        g2d.setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g2d.drawString ("ANTIALIAS_GASP: "+metin, 20,250);

        g2d.setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2d.drawString ("ANTIALIAS_LCD_HRGB: "+metin, 20,350);

        g2d.setFont (new Font (Font.SANS_SERIF, Font.ITALIC, 12));
        g2d.drawString ("NOT: Renk c�mb��� i�in ekran ebat�yla oynay�n...", 20,400);
    } // paint(..) haz�r bile�ke metodu sonu...

    public static void main (String[] args) {
        Frame �er�eve = new Frame ("Kaliteli Metin �rne�i");
        �er�eve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        �er�eve.add (new J9d_1()); // Varsay�l� parametresiz s�n�f kuruculu nesneyi yarat�r...
        �er�eve.setSize (new Dimension (1250, 450));
        �er�eve.setLocation (50,50);
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J9d_1 s�n�f� sonu...