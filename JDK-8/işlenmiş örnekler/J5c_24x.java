// J5c_24x.java: MissingIcon (Kay�p�kon) alt-�rne�i.

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

// Y�klenecek resim dosyas� bulunmazsa ikon ve resim yerine (32x32 ebatl�) k�rm�z� X g�sterir...
public class J5c_24x implements Icon {
    private int geni�lik = 32;
    private int y�kseklik = 32;
    private BasicStroke darbe = new BasicStroke (4); // �izim kal�nl���...

    public void paintIcon (Component k, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor (Color.YELLOW);
        g2d.fillRect (x+1, y+1, geni�lik-2, y�kseklik-2);

        g2d.setColor (Color.BLACK);
        g2d.drawRect (x+1, y+1, geni�lik-2, y�kseklik-2);

        g2d.setColor (Color.RED);
        g2d.setStroke (darbe);
        g2d.drawLine (x+10, y+10, x+geni�lik-10, y+y�kseklik-10);
        g2d.drawLine (x+10, y+y�kseklik-10, x+geni�lik-10, y+10);

        g2d.dispose();
    } // paintIcon(..) haz�r metodu sonu...

    public int getIconWidth() {return geni�lik;}
    public int getIconHeight() {return y�kseklik;}
} // J5c_24x s�n�f� sonu...