// J5c_11ax.java: ArrowIcon (Ok�konu) alt-�rne�i.

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.SwingConstants;

public class J5c_11ax implements Icon, SwingConstants {
    private int geni�lik = 9;
    private int y�kseklik = 18;

    private int[] xNoktalar = new int[4];
    private int[] yNoktalar = new int[4];

    public J5c_11ax (int y�n) {// Kurucu...
        if (y�n == LEADING) {
            xNoktalar[0] = geni�lik;
            yNoktalar[0] = -1;
            xNoktalar[1] = geni�lik;
            yNoktalar[1] = y�kseklik;
            xNoktalar[2] = 0;
            yNoktalar[2] = y�kseklik / 2;
            xNoktalar[3] = 0;
            yNoktalar[3] = y�kseklik / 2 - 1;
        }else /* y�n == TRAILING */ {
            xNoktalar[0] = 0;
            yNoktalar[0] = -1;
            xNoktalar[1] = 0;
            yNoktalar[1] = y�kseklik;
            xNoktalar[2] = geni�lik;
            yNoktalar[2] = y�kseklik / 2;
            xNoktalar[3] = geni�lik;
            yNoktalar[3] = y�kseklik / 2 - 1;
        } // if-else karar� sonu...
    } // J5c_11ax() kurucusu sonu...

    public int getIconHeight() {return y�kseklik;}
    public int getIconWidth() {return geni�lik;}

    public void paintIcon (Component kom, Graphics gr, int x, int y) {
        if (kom.isEnabled()) gr.setColor (kom.getForeground());
        else gr.setColor (Color.gray);

        gr.translate (x, y);
        gr.fillPolygon (xNoktalar, yNoktalar, xNoktalar.length);
        gr.translate (-x, -y); // Grafik nesnelerini geri y�kler...
    } // paintIcon(..) metodu sonu...
} // J5c_11ax s�n�f� sonu...