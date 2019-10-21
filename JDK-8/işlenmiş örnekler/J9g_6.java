// J9g_6.java: Starry (Yýldýzlý) örneði.

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

// Gereken resim dosyasý: Yýldýzlar.gif
public class J9g_6 extends JApplet {
    static String resimDosyasý = "resim/Yýldýzlar.gif";

    public static void main (String[] args) {
        Image yýldýzResmi = Toolkit.getDefaultToolkit().getImage (J9g_6.resimDosyasý);
        YýldýzPaneli yýldýzPaneli = new YýldýzPaneli (yýldýzResmi); // Kuruculu sýnýf nesnesi yaratýlýr...

        JFrame çerçeve = new JFrame ("Yýldýzlý");
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        çerçeve.getContentPane().add (yýldýzPaneli, BorderLayout.CENTER);
        çerçeve.setSize (new Dimension (1000, 200));
        çerçeve.setLocation (200,50);
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...

    public void init() {
        Image yýldýzlý = getImage (yureliAl (resimDosyasý));
        YýldýzPaneli yýldýzPaneli = new YýldýzPaneli (yýldýzlý);
        getContentPane().add (yýldýzPaneli, BorderLayout.CENTER);
    } // init() hazýr aplet metodu sonu...

    protected URL yureliAl (String yol) {
        URL yurel = null;
        try {yurel = new URL (this.getCodeBase(), yol);
        }catch (MalformedURLException ist) {
            System.err.println ("HATA: Resim dosyasý okunamadý!");
            return null;
        } // try-catch bloðu sonu...
        return yurel;
    } // yureliAl(..) metodu sonu...
} // J9g_6 sýnýfý sonu...

class YýldýzPaneli extends JPanel {
    Image resim;
    int en, boy;

    public YýldýzPaneli (Image resim) {this.resim = resim;} // Kurucu...

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        setBackground (Color.BLACK);
        en = getSize().width;
        boy = getSize().height;
        Graphics2D g2;
        g2 = (Graphics2D)g;

        g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint (RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        FontRenderContext fonSunucuÝçerik = g2.getFontRenderContext();
        Font yazýFonu = new Font ("Helvetica", 1, en/8);
        String dizge = new String ("YILDIZLI GECE");
        TextLayout metinSerilim = new TextLayout (dizge, yazýFonu, fonSunucuÝçerik);
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
    } // paintComponent(..) hazýr metodu sonu...
} // YýldýzPaneli sýnýfý sonu...