// J5c_11ax.java: ArrowIcon (OkÝkonu) alt-örneði.

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.SwingConstants;

public class J5c_11ax implements Icon, SwingConstants {
    private int geniþlik = 9;
    private int yükseklik = 18;

    private int[] xNoktalar = new int[4];
    private int[] yNoktalar = new int[4];

    public J5c_11ax (int yön) {// Kurucu...
        if (yön == LEADING) {
            xNoktalar[0] = geniþlik;
            yNoktalar[0] = -1;
            xNoktalar[1] = geniþlik;
            yNoktalar[1] = yükseklik;
            xNoktalar[2] = 0;
            yNoktalar[2] = yükseklik / 2;
            xNoktalar[3] = 0;
            yNoktalar[3] = yükseklik / 2 - 1;
        }else /* yön == TRAILING */ {
            xNoktalar[0] = 0;
            yNoktalar[0] = -1;
            xNoktalar[1] = 0;
            yNoktalar[1] = yükseklik;
            xNoktalar[2] = geniþlik;
            yNoktalar[2] = yükseklik / 2;
            xNoktalar[3] = geniþlik;
            yNoktalar[3] = yükseklik / 2 - 1;
        } // if-else kararý sonu...
    } // J5c_11ax() kurucusu sonu...

    public int getIconHeight() {return yükseklik;}
    public int getIconWidth() {return geniþlik;}

    public void paintIcon (Component kom, Graphics gr, int x, int y) {
        if (kom.isEnabled()) gr.setColor (kom.getForeground());
        else gr.setColor (Color.gray);

        gr.translate (x, y);
        gr.fillPolygon (xNoktalar, yNoktalar, xNoktalar.length);
        gr.translate (-x, -y); // Grafik nesnelerini geri yükler...
    } // paintIcon(..) metodu sonu...
} // J5c_11ax sýnýfý sonu...