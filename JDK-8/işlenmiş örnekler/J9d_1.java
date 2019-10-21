// J9d_1.java: AntialiasedText (KaliteleþtirilenMetin) örneði.

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
    public void init() {repaint();} // Hazýr aplet metodu...

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        String metin = "Merhaba Dünya!..";
        Font yazýFonu = new Font (Font.SERIF, Font.BOLD, 60);
        g2d.setFont (yazýFonu);
        g2d.setColor (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Tesadüfi açýk renkler...
        g2d.fillRect (0, 0, getWidth(), getHeight());

        g2d.setColor (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Tesadüfi koyu renkler...
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
        g2d.drawString ("NOT: Renk cümbüþü için ekran ebatýyla oynayýn...", 20,400);
    } // paint(..) hazýr bileþke metodu sonu...

    public static void main (String[] args) {
        Frame çerçeve = new Frame ("Kaliteli Metin Örneði");
        çerçeve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        çerçeve.add (new J9d_1()); // Varsayýlý parametresiz sýnýf kuruculu nesneyi yaratýr...
        çerçeve.setSize (new Dimension (1250, 450));
        çerçeve.setLocation (50,50);
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J9d_1 sýnýfý sonu...