/* J4a_13.java: DrawingApplet (�izimApleti) �rne�i.
 *
 * <applet code="J4a_13.class" width="300" height="300"></applet>
 */

import java.applet.Applet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.RenderingHints;
import java.awt.BasicStroke;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Arc2D;

import java.awt.image.BufferedImage;

public class J4a_13 extends Applet {
    BufferedImage resim;
    Graphics2D g;

    public void init() {
        int w = getWidth(); // Apletin eni ve  boyu...
        int h = getHeight();
        resim = new BufferedImage (w, h, BufferedImage.TYPE_INT_RGB);
        g = resim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        g.setPaint (Color.CYAN);
        g.fillRect (0, 0, w, h); // Camg�be�i ekran...

        repaint();
    } // init() metodu sonu...

    public void paint (Graphics g) {
        // 1 sn.de bir k���len, rasgele renkte, i�idolu bir dikd�rtgen �izelim...
        for (int i = 0; i < 200; i +=10) {
            try {Thread.sleep (1000);}catch (InterruptedException ist){ist.printStackTrace();}
            g.setColor (new Color ((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
            g.fillRect (0, 0, getWidth() - i, getHeight() - i);
            g.setColor(Color.WHITE); // Beyaz isim yazal�m...
            g.drawString ("M.N�HAT YAVA�", getWidth()/2-i+50, getHeight()-i-20);
        } // for d�ng�s� sonu...
    } // paint(..) metodu sonu...

    // repaint() ile resmi a�a��daki metodla da �ekledebilirsiniz...
    //public void paint (Graphics g) {g.drawImage(resim, 0, 0, this);}

    // A�a��daki metodlarla grafik �izgi kal�nl���, rengi ve yaz� fonunu de�i�tirebilirsiniz...
    public void setLineWidth (float w) {g.setStroke (new BasicStroke(w));}
    public void setColor (int color) {g.setPaint (new Color (color));}
    public void setFont (String fontfamily, int pointsize) {g.setFont (new Font (fontfamily, Font.PLAIN, pointsize));}

    // Bu metodlarla i�idolu  ve ya bo� dikd�rtgen ve yaz� yazabilirsiniz...
    public void fillRect (int x, int y, int w, int h) {g.fillRect (x,y,w,h);}
    public void drawRect (int x, int y, int w, int h) {g.drawRect (x,y,w,h);}
    public void drawString (String s, int x, int y) {g.drawString (s, x, y);}

    // Bu 2 metod �ekil �izer ve i�ini doldurur...
    public void fill (Shape shape) {g.fill (shape);}
    public void draw (Shape shape) {g.draw (shape);}

    // Bu 3 �ekil metodu dikd�rtgen, elips ve kavisli kama �izer...
    public Shape createRectangle (double x, double y, double w, double h) {return new Rectangle2D.Double (x, y, w, h);}
    public Shape createEllipse (double x, double y, double w, double h) {return new Ellipse2D.Double (x, y, w, h);}
    public Shape createWedge (double x, double y, double w, double h,double start, double extent) {return new Arc2D.Double (x, y, w, h, start, extent, Arc2D.PIE);}
} // J4a_13 s�n�f� sonu...