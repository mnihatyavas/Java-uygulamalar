// J9c_1.java: ShapesDemo2D (ÞekillerGösterisi2Boyut) örneði.

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
    final static int asgariFonEbatý = 6;

    final static Color zemin = Color.cyan;
    final static Color yazý = Color.black;
    final static Color kýrmýzý = Color.red;
    final static Color beyaz = Color.white;

    final static BasicStroke darÇizgi = new BasicStroke (2.0f);
    final static BasicStroke geniþÇizgi = new BasicStroke (8.0f);

    final static float dash1[] = {10.0f};
    final static BasicStroke tireliÇizgi = new BasicStroke (1.0f,
            BasicStroke.CAP_BUTT, 
            BasicStroke.JOIN_MITER, 
            10.0f,
            dash1,
            0.0f);

    Dimension toplamEbat;
    FontMetrics fonÖlçüleri;

    public void init() {
        // Çizimleri (pencere boyutlarýný deðiþtirdiðinizde tekrar) kurar...
        repaint();
    } // init() hazýr aplet metodu sonu...

    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int en = getWidth(); // Apletin eni ve  boyu...
        int boy = getHeight();
        g2.setPaint (zemin);
        g2.fillRect (0, 0, en, boy); // Camgöbeði ekran...

        Dimension ebat = getSize();
        int ýzgaraEni = ebat.width / 6; // Pencereyi (2x6) bölmeye ayracak...
        int ýzgaraBoyu = ebat.height / 2;

        fonÖlçüleri = yazýÖlçüleriniAl (g2, "Dolu ve Vurgulu Poligon", ýzgaraEni);

        Color yazý3D = Color.lightGray;

        g2.setPaint (yazý3D);
        g2.draw3DRect (0, 0, ebat.width - 1, ebat.height - 1, true);
        g2.draw3DRect (3, 3, ebat.width - 7, ebat.height - 7, false);
        g2.setPaint (yazý);

        int x = 5;
        int y = 7;
        int dikdörtgenEni = ýzgaraEni - 2 * x;
        int dizgeY = ýzgaraBoyu - 3 - fonÖlçüleri.getDescent();
        int dikdörtgenBoyu = dizgeY - fonÖlçüleri.getMaxAscent() - y - 2;

        // ÝLK SATIR
        // (0,0) bölüme Line2D.Double: 2B çizgi çizimi...
        g2.draw (new Line2D.Double (x, y+dikdörtgenBoyu-1, x + dikdörtgenEni, y));
        g2.drawString ("Çizgi2B", x, dizgeY);
        x += ýzgaraEni;

        // (0,1) bölüme Rectangle2D.Double: 2B dikdörtgen çizimi...
        g2.setStroke (darÇizgi);
        g2.draw (new Rectangle2D.Double (x, y, dikdörtgenEni, dikdörtgenBoyu));
        g2.drawString ("Dikdörtgen2B", x, dizgeY);
        x += ýzgaraEni;      

        // (0,2) bölüme RoundRectangle2D.Double: 2B yuvarlak kenarlý tireli dikdörtgen çizimi...
        g2.setStroke (tireliÇizgi);
        g2.draw (new RoundRectangle2D.Double (x, y, dikdörtgenEni, dikdörtgenBoyu, 10, 10));
        g2.drawString ("YuvarlatýlýDikdörtgen2B", x, dizgeY);
        x += ýzgaraEni;

        // (0,3) bölüme  Arc2D.Double: 2B kalýn yay çizimi...
        g2.setStroke (geniþÇizgi);
        g2.draw (new Arc2D.Double (x, y, dikdörtgenEni, dikdörtgenBoyu, 90, 135, Arc2D.OPEN));
        g2.drawString ("Yay2B", x, dizgeY);
        x += ýzgaraEni;

        // (0,4) bölüme Ellipse2D.Double: 2B ince elips çizimi
        g2.setStroke (darÇizgi);
        g2.draw (new Ellipse2D.Double (x, y, dikdörtgenEni, dikdörtgenBoyu));
        g2.drawString ("Elips2B", x, dizgeY);
        x += ýzgaraEni;

        // (0,5) bölüme GeneralPath (polygon): 2B poligon çizimi...
        int x1Noktalar[] = {x, x+dikdörtgenEni, x, x+dikdörtgenEni};
        int y1Noktalar[] = {y, y+dikdörtgenBoyu, y+dikdörtgenBoyu, y};
        GeneralPath poligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x1Noktalar.length);
        poligon.moveTo (x1Noktalar[0], y1Noktalar[0]);
        for (int endeks = 1; endeks < x1Noktalar.length; endeks++ )
            poligon.lineTo (x1Noktalar[endeks], y1Noktalar[endeks]);
        poligon.closePath();
        g2.draw (poligon);
        g2.drawString ("Poligon", x, dizgeY);

        // ÝKÝNCÝ SATIR
        x = 5;
        y += ýzgaraBoyu;
        dizgeY += ýzgaraBoyu;

        // (1,0) bölüme GeneralPath (polyline): 2B açýk poligon çizimi...
        int x2Noktalar[] = {x, x+dikdörtgenEni, x, x+dikdörtgenEni};
        int y2Noktalar[] = {y, y+dikdörtgenBoyu, y+dikdörtgenBoyu, y};
        GeneralPath açýkPoligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x2Noktalar.length);
        açýkPoligon.moveTo (x2Noktalar[0], y2Noktalar[0]);
        for (int endeks = 1; endeks < x2Noktalar.length; endeks++)
            açýkPoligon.lineTo (x2Noktalar[endeks], y2Noktalar[endeks]);
        g2.draw (açýkPoligon);
        g2.drawString ("Açýk Poligon", x, dizgeY);
        x += ýzgaraEni;

        // (1,1) bölüme fill Rectangle2D.Double (red): 2B içi dolu kýrmýzý dikdörtgen çizimi...
        g2.setPaint (kýrmýzý);
        g2.fill (new Rectangle2D.Double (x, y, dikdörtgenEni, dikdörtgenBoyu));
        g2.setPaint (yazý);
        g2.drawString ("Dolu Dikdörtgen2D", x, dizgeY);
        x += ýzgaraEni;        

        // (1,2) bölüme fill RoundRectangle2D.Double: 2B içi kademeli dolu kýrmýzý yuvarlak köþeli dikdörtgen çizimi...
        GradientPaint kýrmýzýdanBeyaza = new GradientPaint (x, y, kýrmýzý, x+dikdörtgenEni, y, beyaz);
        g2.setPaint (kýrmýzýdanBeyaza);
        g2.fill (new RoundRectangle2D.Double (x, y, dikdörtgenEni, dikdörtgenBoyu, 10, 10));
        g2.setPaint (yazý);
        g2.drawString ("Dolu YuvarlatýlýDikdörtgen2B", x, dizgeY);
        x += ýzgaraEni;

        // (1,3) bölüme fill Arc2D: 2B içi dolu kýrmýzý yay çizimi...
        g2.setPaint (kýrmýzý);
        g2.fill (new Arc2D.Double (x, y, dikdörtgenEni, dikdörtgenBoyu, 90, 135, Arc2D.OPEN));
        g2.setPaint (yazý);
        g2.drawString ("Dolu Yay2B", x, dizgeY);
        x += ýzgaraEni;

        // (1,4) bölüme fill Ellipse2D.Double: 2B içi kademeli dolu kýrmýzý elips çizimi...
        kýrmýzýdanBeyaza = new GradientPaint (x, y, kýrmýzý, x+dikdörtgenEni, y, beyaz);
        g2.setPaint (kýrmýzýdanBeyaza);
        g2.fill (new Ellipse2D.Double (x, y, dikdörtgenEni, dikdörtgenBoyu));
        g2.setPaint (yazý);
        g2.drawString ("Dolu Elips2B", x, dizgeY);
        x += ýzgaraEni;

        // (1,5) bölüme fill darÇizgi GeneralPath: 2B içi dolu kýrmýzý poligon çizimi...
        int x3Noktalar[] = {x, x+dikdörtgenEni, x, x+dikdörtgenEni};
        int y3Noktalar[] = {y, y+dikdörtgenBoyu, y+dikdörtgenBoyu, y};
        GeneralPath doluPoligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x3Noktalar.length);
        doluPoligon.moveTo (x3Noktalar[0], y3Noktalar[0]);
        for (int endeks = 1; endeks < x3Noktalar.length; endeks++)
            doluPoligon.lineTo (x3Noktalar[endeks], y3Noktalar[endeks]);
        doluPoligon.closePath();
        g2.setPaint (kýrmýzý);
        g2.fill (doluPoligon);
        g2.setPaint (yazý);
        g2.draw (doluPoligon);
        g2.drawString ("Dolu Poligon", x, dizgeY);
    } // paint(..) aplet bileþeni kurma hazýr metodu...

    FontMetrics yazýÖlçüleriniAl (Graphics2D g2, String uzunDizge, int xMesafe) {
        boolean fonUygunMu = false;
        Font fon = g2.getFont();
        FontMetrics fonÖlçüleri = g2.getFontMetrics();
        int büyüklük = fon.getSize();
        String ad = fon.getName();
        int stil = fon.getStyle();

        while (!fonUygunMu) {
            if ( (fonÖlçüleri.getHeight() <= azamiKarakterBoyu) && (fonÖlçüleri.stringWidth (uzunDizge) <= xMesafe) )
                fonUygunMu = true;
            else {
                if ( büyüklük <= asgariFonEbatý ) fonUygunMu = true;
                else {
                    g2.setFont (fon = new Font (ad, stil, --büyüklük));
                    fonÖlçüleri = g2.getFontMetrics();
                } // iç-else kararý sonu...
            } // dýþ-else kararý sonu...
        } // while döngüsü sonu...

        return fonÖlçüleri;
    } // yazýÖlçüleriniAl(..) metodu sonu...

    public static void main (String a[]) {
        JFrame çerçeve = new JFrame ("2-Boyutlu Þekiller Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JApplet aplet = new J9c_1(); // Parametresiz varsayýlý kurucuyla sýnýf nesnesini yaratýr...
        çerçeve.getContentPane().add ("Center", aplet);
        aplet.init(); // Hazýr init() metodunu çaðýrýr...
        çerçeve.setLocation (200,100);
        çerçeve.setSize (new Dimension (800,400));
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J9c_1 sýnýfý sonu...