// J9g_6.java: Starry (Y�ld�zl�) �rne�i.

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Shape;
import java.awt.RenderingHints;

import java.awt.geom.AffineTransform;

import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.net.URL;
import java.net.MalformedURLException;

// Gereken resim dosyas�: Y�ld�zlar.gif
public class J9g_6 extends JApplet {
    static String resimDosyas� = "resim/Y�ld�zlar.gif";

    public static void main (String[] args) {
        Image y�ld�zResmi = Toolkit.getDefaultToolkit().getImage (J9g_6.resimDosyas�);
        Y�ld�zPaneli y�ld�zPaneli = new Y�ld�zPaneli (y�ld�zResmi); // Kuruculu s�n�f nesnesi yarat�l�r...

        JFrame �er�eve = new JFrame ("Y�ld�zl�");
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        �er�eve.getContentPane().add (y�ld�zPaneli, BorderLayout.CENTER);
        �er�eve.setSize (new Dimension (1000, 200));
        �er�eve.setLocation (200,50);
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...

    public void init() {
        Image y�ld�zl� = getImage (yureliAl (resimDosyas�));
        Y�ld�zPaneli y�ld�zPaneli = new Y�ld�zPaneli (y�ld�zl�);
        getContentPane().add (y�ld�zPaneli, BorderLayout.CENTER);
    } // init() haz�r aplet metodu sonu...

    protected URL yureliAl (String yol) {
        URL yurel = null;
        try {yurel = new URL (this.getCodeBase(), yol);
        }catch (MalformedURLException ist) {
            System.err.println ("HATA: Resim dosyas� okunamad�!");
            return null;
        } // try-catch blo�u sonu...
        return yurel;
    } // yureliAl(..) metodu sonu...
} // J9g_6 s�n�f� sonu...

class Y�ld�zPaneli extends JPanel {
    Image resim;
    int en, boy;

    public Y�ld�zPaneli (Image resim) {this.resim = resim;} // Kurucu...

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        setBackground (Color.BLACK);
        en = getSize().width;
        boy = getSize().height;
        Graphics2D g2;
        g2 = (Graphics2D)g;

        g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint (RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        FontRenderContext fonSunucu��erik = g2.getFontRenderContext();
        Font yaz�Fonu = new Font ("Helvetica", 1, en/8);
        String dizge = new String ("YILDIZLI GECE");
        TextLayout metinSerilim = new TextLayout (dizge, yaz�Fonu, fonSunucu��erik);
        AffineTransform resimFilitresi = new AffineTransform();
        Shape anaHatlar = metinSerilim.getOutline (null);
        Rectangle kutu = anaHatlar.getBounds();
        resimFilitresi = g2.getTransform();
        resimFilitresi.translate (en/2-(kutu.width/2), boy/2+(kutu.height/2));
        g2.transform (resimFilitresi);
        g2.setColor (Color.BLUE);
        g2.draw (anaHatlar);
        g2.setClip (anaHatlar);
        g2.drawImage (resim, kutu.x, kutu.y, kutu.width, kutu.height, this);
    } // paintComponent(..) haz�r metodu sonu...
} // Y�ld�zPaneli s�n�f� sonu...