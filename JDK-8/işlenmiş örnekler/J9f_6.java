// J9f_6.java: SimpleBook (BasitKitap) �rne�i.

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.GradientPaint;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import java.awt.print.Printable;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.awt.print.PrinterException;
import java.awt.print.Book;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class J9f_6 extends JPanel implements ActionListener{
   final static Color zeminRengi = Color.CYAN;
    final static Color yaz�Rengi = Color.BLACK;
    final static Color k�rm�z� = Color.RED;
    final static Color beyaz = Color.WHITE;

    final static BasicStroke koyu = new BasicStroke (2.0f);
    final static BasicStroke geni�Koyu = new BasicStroke (8.0f);

    final static float tire[] = {10.0f};
    final static BasicStroke tireliKoyu = new BasicStroke (
            1.0f,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            10.0f,
            tire,
            0.0f);
    final static JButton yaz�c�D��mesi = new JButton ("YAZICI");

    public static void main (String[] args) {
        JFrame �er�eve = new JFrame();
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JPanel panel = new JPanel();
        panel.add (yaz�c�D��mesi);
        yaz�c�D��mesi.setForeground (Color.BLUE);
        �er�eve.getContentPane().add (BorderLayout.SOUTH, panel);
        �er�eve.getContentPane().add (BorderLayout.CENTER, new J9f_6()); // Kurucuyu �a��r�r...
        �er�eve.setSize (775, 450);
        �er�eve.setLocation (400, 50);
        �er�eve.show(); // �er�eve.setVisible (True);
    } // main(..) metodu sonu...

    public J9f_6() {// Kurucu...
        setBackground (zeminRengi);
        yaz�c�D��mesi.addActionListener (this); // D��me dinleyiciye duyarl�...
    } // J9f_6() kurucusu sonu...

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D) g;
        �ekilleri�iz (g2);
    } // paintComponent(..) haz�r metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        PrinterJob yaz�c�G�revi = PrinterJob.getPrinterJob();
        PageFormat sayfaBi�imi = yaz�c�G�revi.defaultPage();
        sayfaBi�imi.setOrientation (PageFormat.LANDSCAPE); // �izim yatay...
        // Bir kitap yarat�p, kapa�� ve i�erikleri ekleyelim...
        Book kitap = new Book();
        kitap.append (new Kapa��Kur(), yaz�c�G�revi.defaultPage()); // Normal yazacak...
        kitap.append (new ��eri�iKur(), sayfaBi�imi); // Yatay �izecek...
        // Kitab� PrinterJob'a g�nderelim...
        yaz�c�G�revi.setPageable (kitap);
        // Yazd�rma iptal edilmediyse diyalog kutusunu a�al�m...
        if (yaz�c�G�revi.printDialog()) {
            try {yaz�c�G�revi.print(); // print() haz�r yazd�rma metodu(lar�n�) �a�r�l�r...
            }catch (Exception ist) {System.err.println ("HATA: Yazd�rma i�lemi tamamlanamad�!"); System.exit (1);}
        } // if karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    static void �ekilleri�iz (Graphics2D g2){
        int �zgaraEni = 600 / 6;
        int �zgaraBoyu = 250 / 2;

        int sat�rAras� = 5;
        int s�tunAras� = 7;
        int h�creEni = �zgaraEni - s�tunAras�;
        int h�creBoyu = �zgaraBoyu - sat�rAras�;

        Color yaz�Rengi3D = Color.lightGray;

        // Izgara �er�evesini �izelim...
        g2.setPaint (yaz�Rengi3D);
        g2.drawRect (80,80, 605-1, 265);

        // 1.SATIR
        // (1.sat�r, 1.h�cre) Line2D.Double
        g2.setPaint (yaz�Rengi);
        int x = 85;
        int y = 87;
        g2.draw (new Line2D.Double (x, y+h�creBoyu-1, x + h�creEni, y));

        // (1.sat�r, 2.h�cre) Rectangle2D.Double
        x += �zgaraEni;
        g2.setStroke (koyu);
        g2.draw (new Rectangle2D.Double (x, y, h�creEni, h�creBoyu));

        // (1.sat�r, 3.h�cre) RoundRectangle2D.Double
        x += �zgaraEni;
        g2.setStroke (tireliKoyu);
        g2.draw (new RoundRectangle2D.Double (x, y, h�creEni, h�creBoyu, 10, 10));

        // (1.sat�r, 4.h�cre) Arc2D.Double
        x += �zgaraEni;
        g2.setStroke (geni�Koyu);
        g2.draw (new Arc2D.Double (x, y, h�creEni, h�creBoyu, 90, 135, Arc2D.OPEN));

        // (1.sat�r, 5.h�cre) Ellipse2D.Double
        x += �zgaraEni;
        g2.setStroke(koyu);
        g2.draw (new Ellipse2D.Double (x, y, h�creEni, h�creBoyu));

        // (1.sat�r, 6.h�cre) GeneralPath (poligon)
        x += �zgaraEni;
        int x1Noktalar[] = {x, x+h�creEni, x, x+h�creEni};
        int y1Noktalar[] = {y, y+h�creBoyu, y+h�creBoyu, y};
        GeneralPath poligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x1Noktalar.length);
        poligon.moveTo (x1Noktalar[0], y1Noktalar[0]);
        for (int endeks = 1; endeks < x1Noktalar.length; endeks++) poligon.lineTo (x1Noktalar[endeks], y1Noktalar[endeks]);
        poligon.closePath(); 
        g2.draw (poligon);

        // 2.SATIR
        // (2.sat�r, 1.h�cre) GeneralPath (poli�izgi)
        x = 85;
        y += �zgaraBoyu;
        int x2Noktalar[] = {x, x+h�creEni, x, x+h�creEni};
        int y2Noktalar[] = {y, y+h�creBoyu, y+h�creBoyu, y};
        GeneralPath poli�izgi = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x2Noktalar.length);
        poli�izgi.moveTo (x2Noktalar[0], y2Noktalar[0]);
       for (int endeks = 1; endeks < x2Noktalar.length; endeks++) poli�izgi.lineTo (x2Noktalar[endeks], y2Noktalar[endeks]);
        g2.draw (poli�izgi);

        // (2.sat�r, 2.h�cre) dolgulu Rectangle2D.Double (k�rm�z�)
        x += �zgaraEni;
        g2.setPaint (k�rm�z�);
        g2.fill (new Rectangle2D.Double (x, y, h�creEni, h�creBoyu));

        // (2.sat�r, 3.h�cre) dolgulu RoundRectangle2D.Double
        x += �zgaraEni;
        GradientPaint k�rm�z�danBeyaza = new GradientPaint (x, y, k�rm�z�, x+h�creEni, y, beyaz);
        g2.setPaint (k�rm�z�danBeyaza);
        g2.fill (new RoundRectangle2D.Double (x, y, h�creEni, h�creBoyu, 10, 10));

        // (2.sat�r, 4.h�cre) dolgulu Arc2D
        x += �zgaraEni;
        g2.setPaint (k�rm�z�);
        g2.fill (new Arc2D.Double (x, y, h�creEni, h�creBoyu, 90, 135, Arc2D.OPEN));

        // (2.sat�r, 5.h�cre) dolgulu Ellipse2D.Double
        x += �zgaraEni;
        k�rm�z�danBeyaza = new GradientPaint (x, y, k�rm�z�, x+h�creEni, y, beyaz);
        g2.setPaint (k�rm�z�danBeyaza);
        g2.fill (new Ellipse2D.Double (x, y, h�creEni, h�creBoyu));

        // (2.sat�r, 6.h�cre) dolgulu ve koyu GeneralPath (poligon)
        x += �zgaraEni;
        int x3Noktalar[] = {x, x+h�creEni, x, x+h�creEni};
        int y3Noktalar[] = {y, y+h�creBoyu, y+h�creBoyu, y};
        GeneralPath dolguluPoligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x3Noktalar.length);
        dolguluPoligon.moveTo(x3Noktalar[0], y3Noktalar[0]);
        for (int endeks = 1; endeks < x3Noktalar.length; endeks++) dolguluPoligon.lineTo (x3Noktalar[endeks], y3Noktalar[endeks]);
        dolguluPoligon.closePath();
        g2.setPaint (k�rm�z�);
        g2.fill (dolguluPoligon);
        g2.setPaint (yaz�Rengi);
        g2.draw (dolguluPoligon);
    } // �ekilleri�iz(..) metodu sonu...
} // J9f_6 s�n�f� sonu...

class Kapa��Kur implements Printable {
    Font yaz�Fonu = new Font ("Helvetica-Bold", Font.PLAIN, 50);

    public int print (Graphics g, PageFormat sayfaBi�imi, int sayfaEndeksi) throws PrinterException {
        g.setFont (yaz�Fonu);
        g.setColor (Color.BLACK);
        g.drawString ("Numune �ekiller", 100, 400);
        return Printable.PAGE_EXISTS;
    } // print(..) haz�r metodu sonu...
} // Kapa��Kur s�n�f� sonu...

class ��eri�iKur implements Printable {
    public int print (Graphics g, PageFormat sayfaBi�imi, int sayfaEndeksi) throws PrinterException {
        J9f_6.�ekilleri�iz ((Graphics2D)g);
        return Printable.PAGE_EXISTS;
    } // print(..) haz�r metodu sonu...
} // ��eri�iKur s�n�f� sonu...