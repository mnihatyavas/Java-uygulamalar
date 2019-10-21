// J9c_1.java: ShapesDemo2D (�ekillerG�sterisi2Boyut) �rne�i.

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.awt.BasicStroke;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JApplet;

public class J9c_1 extends JApplet {
    final static int azamiKarakterBoyu = 15;
    final static int asgariFonEbat� = 6;

    final static Color zemin = Color.cyan;
    final static Color yaz� = Color.black;
    final static Color k�rm�z� = Color.red;
    final static Color beyaz = Color.white;

    final static BasicStroke dar�izgi = new BasicStroke (2.0f);
    final static BasicStroke geni��izgi = new BasicStroke (8.0f);

    final static float dash1[] = {10.0f};
    final static BasicStroke tireli�izgi = new BasicStroke (1.0f,
            BasicStroke.CAP_BUTT, 
            BasicStroke.JOIN_MITER, 
            10.0f,
            dash1,
            0.0f);

    Dimension toplamEbat;
    FontMetrics fon�l��leri;

    public void init() {
        // �izimleri (pencere boyutlar�n� de�i�tirdi�inizde tekrar) kurar...
        repaint();
    } // init() haz�r aplet metodu sonu...

    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int en = getWidth(); // Apletin eni ve  boyu...
        int boy = getHeight();
        g2.setPaint (zemin);
        g2.fillRect (0, 0, en, boy); // Camg�be�i ekran...

        Dimension ebat = getSize();
        int �zgaraEni = ebat.width / 6; // Pencereyi (2x6) b�lmeye ayracak...
        int �zgaraBoyu = ebat.height / 2;

        fon�l��leri = yaz��l��leriniAl (g2, "Dolu ve Vurgulu Poligon", �zgaraEni);

        Color yaz�3D = Color.lightGray;

        g2.setPaint (yaz�3D);
        g2.draw3DRect (0, 0, ebat.width - 1, ebat.height - 1, true);
        g2.draw3DRect (3, 3, ebat.width - 7, ebat.height - 7, false);
        g2.setPaint (yaz�);

        int x = 5;
        int y = 7;
        int dikd�rtgenEni = �zgaraEni - 2 * x;
        int dizgeY = �zgaraBoyu - 3 - fon�l��leri.getDescent();
        int dikd�rtgenBoyu = dizgeY - fon�l��leri.getMaxAscent() - y - 2;

        // �LK SATIR
        // (0,0) b�l�me Line2D.Double: 2B �izgi �izimi...
        g2.draw (new Line2D.Double (x, y+dikd�rtgenBoyu-1, x + dikd�rtgenEni, y));
        g2.drawString ("�izgi2B", x, dizgeY);
        x += �zgaraEni;

        // (0,1) b�l�me Rectangle2D.Double: 2B dikd�rtgen �izimi...
        g2.setStroke (dar�izgi);
        g2.draw (new Rectangle2D.Double (x, y, dikd�rtgenEni, dikd�rtgenBoyu));
        g2.drawString ("Dikd�rtgen2B", x, dizgeY);
        x += �zgaraEni;      

        // (0,2) b�l�me RoundRectangle2D.Double: 2B yuvarlak kenarl� tireli dikd�rtgen �izimi...
        g2.setStroke (tireli�izgi);
        g2.draw (new RoundRectangle2D.Double (x, y, dikd�rtgenEni, dikd�rtgenBoyu, 10, 10));
        g2.drawString ("Yuvarlat�l�Dikd�rtgen2B", x, dizgeY);
        x += �zgaraEni;

        // (0,3) b�l�me  Arc2D.Double: 2B kal�n yay �izimi...
        g2.setStroke (geni��izgi);
        g2.draw (new Arc2D.Double (x, y, dikd�rtgenEni, dikd�rtgenBoyu, 90, 135, Arc2D.OPEN));
        g2.drawString ("Yay2B", x, dizgeY);
        x += �zgaraEni;

        // (0,4) b�l�me Ellipse2D.Double: 2B ince elips �izimi
        g2.setStroke (dar�izgi);
        g2.draw (new Ellipse2D.Double (x, y, dikd�rtgenEni, dikd�rtgenBoyu));
        g2.drawString ("Elips2B", x, dizgeY);
        x += �zgaraEni;

        // (0,5) b�l�me GeneralPath (polygon): 2B poligon �izimi...
        int x1Noktalar[] = {x, x+dikd�rtgenEni, x, x+dikd�rtgenEni};
        int y1Noktalar[] = {y, y+dikd�rtgenBoyu, y+dikd�rtgenBoyu, y};
        GeneralPath poligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x1Noktalar.length);
        poligon.moveTo (x1Noktalar[0], y1Noktalar[0]);
        for (int endeks = 1; endeks < x1Noktalar.length; endeks++ )
            poligon.lineTo (x1Noktalar[endeks], y1Noktalar[endeks]);
        poligon.closePath();
        g2.draw (poligon);
        g2.drawString ("Poligon", x, dizgeY);

        // �K�NC� SATIR
        x = 5;
        y += �zgaraBoyu;
        dizgeY += �zgaraBoyu;

        // (1,0) b�l�me GeneralPath (polyline): 2B a��k poligon �izimi...
        int x2Noktalar[] = {x, x+dikd�rtgenEni, x, x+dikd�rtgenEni};
        int y2Noktalar[] = {y, y+dikd�rtgenBoyu, y+dikd�rtgenBoyu, y};
        GeneralPath a��kPoligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x2Noktalar.length);
        a��kPoligon.moveTo (x2Noktalar[0], y2Noktalar[0]);
        for (int endeks = 1; endeks < x2Noktalar.length; endeks++)
            a��kPoligon.lineTo (x2Noktalar[endeks], y2Noktalar[endeks]);
        g2.draw (a��kPoligon);
        g2.drawString ("A��k Poligon", x, dizgeY);
        x += �zgaraEni;

        // (1,1) b�l�me fill Rectangle2D.Double (red): 2B i�i dolu k�rm�z� dikd�rtgen �izimi...
        g2.setPaint (k�rm�z�);
        g2.fill (new Rectangle2D.Double (x, y, dikd�rtgenEni, dikd�rtgenBoyu));
        g2.setPaint (yaz�);
        g2.drawString ("Dolu Dikd�rtgen2D", x, dizgeY);
        x += �zgaraEni;        

        // (1,2) b�l�me fill RoundRectangle2D.Double: 2B i�i kademeli dolu k�rm�z� yuvarlak k��eli dikd�rtgen �izimi...
        GradientPaint k�rm�z�danBeyaza = new GradientPaint (x, y, k�rm�z�, x+dikd�rtgenEni, y, beyaz);
        g2.setPaint (k�rm�z�danBeyaza);
        g2.fill (new RoundRectangle2D.Double (x, y, dikd�rtgenEni, dikd�rtgenBoyu, 10, 10));
        g2.setPaint (yaz�);
        g2.drawString ("Dolu Yuvarlat�l�Dikd�rtgen2B", x, dizgeY);
        x += �zgaraEni;

        // (1,3) b�l�me fill Arc2D: 2B i�i dolu k�rm�z� yay �izimi...
        g2.setPaint (k�rm�z�);
        g2.fill (new Arc2D.Double (x, y, dikd�rtgenEni, dikd�rtgenBoyu, 90, 135, Arc2D.OPEN));
        g2.setPaint (yaz�);
        g2.drawString ("Dolu Yay2B", x, dizgeY);
        x += �zgaraEni;

        // (1,4) b�l�me fill Ellipse2D.Double: 2B i�i kademeli dolu k�rm�z� elips �izimi...
        k�rm�z�danBeyaza = new GradientPaint (x, y, k�rm�z�, x+dikd�rtgenEni, y, beyaz);
        g2.setPaint (k�rm�z�danBeyaza);
        g2.fill (new Ellipse2D.Double (x, y, dikd�rtgenEni, dikd�rtgenBoyu));
        g2.setPaint (yaz�);
        g2.drawString ("Dolu Elips2B", x, dizgeY);
        x += �zgaraEni;

        // (1,5) b�l�me fill dar�izgi GeneralPath: 2B i�i dolu k�rm�z� poligon �izimi...
        int x3Noktalar[] = {x, x+dikd�rtgenEni, x, x+dikd�rtgenEni};
        int y3Noktalar[] = {y, y+dikd�rtgenBoyu, y+dikd�rtgenBoyu, y};
        GeneralPath doluPoligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x3Noktalar.length);
        doluPoligon.moveTo (x3Noktalar[0], y3Noktalar[0]);
        for (int endeks = 1; endeks < x3Noktalar.length; endeks++)
            doluPoligon.lineTo (x3Noktalar[endeks], y3Noktalar[endeks]);
        doluPoligon.closePath();
        g2.setPaint (k�rm�z�);
        g2.fill (doluPoligon);
        g2.setPaint (yaz�);
        g2.draw (doluPoligon);
        g2.drawString ("Dolu Poligon", x, dizgeY);
    } // paint(..) aplet bile�eni kurma haz�r metodu...

    FontMetrics yaz��l��leriniAl (Graphics2D g2, String uzunDizge, int xMesafe) {
        boolean fonUygunMu = false;
        Font fon = g2.getFont();
        FontMetrics fon�l��leri = g2.getFontMetrics();
        int b�y�kl�k = fon.getSize();
        String ad = fon.getName();
        int stil = fon.getStyle();

        while (!fonUygunMu) {
            if ( (fon�l��leri.getHeight() <= azamiKarakterBoyu) && (fon�l��leri.stringWidth (uzunDizge) <= xMesafe) )
                fonUygunMu = true;
            else {
                if ( b�y�kl�k <= asgariFonEbat� ) fonUygunMu = true;
                else {
                    g2.setFont (fon = new Font (ad, stil, --b�y�kl�k));
                    fon�l��leri = g2.getFontMetrics();
                } // i�-else karar� sonu...
            } // d��-else karar� sonu...
        } // while d�ng�s� sonu...

        return fon�l��leri;
    } // yaz��l��leriniAl(..) metodu sonu...

    public static void main (String a[]) {
        JFrame �er�eve = new JFrame ("2-Boyutlu �ekiller G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JApplet aplet = new J9c_1(); // Parametresiz varsay�l� kurucuyla s�n�f nesnesini yarat�r...
        �er�eve.getContentPane().add ("Center", aplet);
        aplet.init(); // Haz�r init() metodunu �a��r�r...
        �er�eve.setLocation (200,100);
        �er�eve.setSize (new Dimension (800,400));
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J9c_1 s�n�f� sonu...