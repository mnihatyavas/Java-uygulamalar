// J5c_24x.java: MissingIcon (KayýpÝkon) alt-örneði.

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

// Yüklenecek resim dosyasý bulunmazsa ikon ve resim yerine (32x32 ebatlý) kýrmýzý X gösterir...
public class J5c_24x implements Icon {
    private int geniþlik = 32;
    private int yükseklik = 32;
    private BasicStroke darbe = new BasicStroke (4); // Çizim kalýnlýðý...

    public void paintIcon (Component k, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor (Color.YELLOW);
        g2d.fillRect (x+1, y+1, geniþlik-2, yükseklik-2);

        g2d.setColor (Color.BLACK);
        g2d.drawRect (x+1, y+1, geniþlik-2, yükseklik-2);

        g2d.setColor (Color.RED);
        g2d.setStroke (darbe);
        g2d.drawLine (x+10, y+10, x+geniþlik-10, y+yükseklik-10);
        g2d.drawLine (x+10, y+yükseklik-10, x+geniþlik-10, y+10);

        g2d.dispose();
    } // paintIcon(..) hazýr metodu sonu...

    public int getIconWidth() {return geniþlik;}
    public int getIconHeight() {return yükseklik;}
} // J5c_24x sýnýfý sonu...