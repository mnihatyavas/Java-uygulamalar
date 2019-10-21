// J9f_5.java: ShapesPrint (�ekillerinYaz�m�) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.GradientPaint;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath; // Poligon...

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class J9f_5 extends JPanel implements Printable, ActionListener {
    final static Color zeminRengi = Color.CYAN;
    final static Color �izimRengi = Color.BLACK;
    final static Color k�rm�z� = Color.RED; 
    final static Color beyaz = Color.WHITE;

    final static BasicStroke koyu = new BasicStroke (2.0f);
    final static BasicStroke geni�Koyu = new BasicStroke (8.0f);

    final static float tire1[] = {10.0f};
    final static BasicStroke koyuTireli = new BasicStroke (
            1.0f,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            10.0f,
            tire1,
            0.0f);

    final static JButton yaz�c�D��mesi = new JButton ("YAZDIR");

    public J9f_5() {// Kurucu...
        setBackground (zeminRengi);
        yaz�c�D��mesi.addActionListener (this); // D��me dinleyiciye duyarl�...
    } // J9f_5() kurucusu sonu...

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D) g;
        �ekilleri�iz (g2);
    } // paintComponent(..) haz�r metodu sonu...

   public void actionPerformed (ActionEvent olay) {
        if (olay.getSource() instanceof JButton) {   
            PrinterJob yazd�rma��i = PrinterJob.getPrinterJob();
            yazd�rma��i.setPrintable (this);
            if (yazd�rma��i.printDialog()) {
                try {yazd�rma��i.print(); // print() haz�r metodunu �a��r�r...
                }catch (Exception ist) {ist.printStackTrace();}
            } // if karar� sonu...
       } // d��-if karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    public int print (Graphics g, PageFormat sayfaBi�imi, int sayfa) throws PrinterException {
        if (sayfa >= 1) return Printable.NO_SUCH_PAGE;
        �ekilleri�iz ((Graphics2D) g);
        return Printable.PAGE_EXISTS;
    } // print(..) haz�r metodu sonu...

    public void �ekilleri�iz (Graphics2D g2) {
        Dimension ebat = getSize();
        int �zgaraEni = 400 / 6;
        int �zgaraBoyu = 300 / 2;

        int sat�rAras� = 5;
        int s�tunAras� = 7;
        int kutuEni = �zgaraEni - s�tunAras�;
        int kutuBoyu = �zgaraBoyu - sat�rAras�;

        // A��k gri renkte �zgara �er�evesi �izilecek...
        Color �izimRengi3D = Color.lightGray;
        g2.setPaint (�izimRengi3D);
        g2.drawRect (80, 80, 400 - 1, 310);

        g2.setPaint (�izimRengi);

        // 1.SATIR
        // (1.Sat�r, 1.Kolon): �apraz �izgi; Line2D.Double
        int x = 85;
        int y = 87;
        g2.draw (new Line2D.Double (x, y+kutuBoyu-1, x + kutuEni, y));
        x += �zgaraEni;

        // (1.Sat�r, 2.Kolon): Dik kutu; Rectangle2D.Double
        g2.setStroke (koyu);
        g2.draw (new Rectangle2D.Double (x, y, kutuEni, kutuBoyu));
        x += �zgaraEni;

        // (1.Sat�r, 3.Kolon): Tireli kutu; RoundRectangle2D.Double
        g2.setStroke (koyuTireli);
        g2.draw (new RoundRectangle2D.Double (x, y, kutuEni, kutuBoyu, 10, 10));
        x += �zgaraEni;
       
        // (1.Sat�r, 4.Kolon): Kal�n yay; Arc2D.Double
        g2.setStroke (geni�Koyu);
        g2.draw (new Arc2D.Double (x, y, kutuEni, kutuBoyu, 90, 135, Arc2D.OPEN));
        x += �zgaraEni;

        // (1.Sat�r, 5.Kolon): Dik elips; Ellipse2D.Double
        g2.setStroke (koyu);
        g2.draw (new Ellipse2D.Double (x, y, kutuEni, kutuBoyu));
        x += �zgaraEni;

        // (1.Sat�r, 6.Kolon): Poligon; GeneralPath
        int x1Noktalar[] = {x, x+kutuEni, x, x+kutuEni};
        int y1Noktalar[] = {y, y+kutuBoyu, y+kutuBoyu, y};
        GeneralPath poligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x1Noktalar.length);
        poligon.moveTo (x1Noktalar[0], y1Noktalar[0]);
        for (int endeks = 1; endeks < x1Noktalar.length; endeks++ ) poligon.lineTo (x1Noktalar[endeks], y1Noktalar[endeks]);
        poligon.closePath();
        g2.draw (poligon);
        
        // 2.SATIR
        // (2.Sat�r, 1.Kolon): A��k poligon; GeneralPath
        x = 85;
       y += �zgaraBoyu;
        int x2Noktalar[] = {x, x+kutuEni, x, x+kutuEni};
        int y2Noktalar[] = {y, y+kutuBoyu, y+kutuBoyu, y};
        GeneralPath poli�izgi = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x2Noktalar.length);
        poli�izgi.moveTo (x2Noktalar[0], y2Noktalar[0]);
       for (int endeks = 1; endeks < x2Noktalar.length; endeks++ ) poli�izgi.lineTo (x2Noktalar[endeks], y2Noktalar[endeks]);
        g2.draw (poli�izgi);
        x += �zgaraEni;
        
        // (2.Sat�r, 2.Kolon): Dolu dik kutu; fill Rectangle2D.Double (k�rm�z�)
        g2.setPaint (k�rm�z�);
        g2.fill (new Rectangle2D.Double (x, y, kutuEni, kutuBoyu));
        x += �zgaraEni;

        // (2.Sat�r, 3.Kolon): Dolu kademeli dik kutu; fill RoundRectangle2D.Double (k�zm�z�dan beyaza)
        GradientPaint k�rm�z�danBeyaza = new GradientPaint (x, y, k�rm�z�, x+kutuEni, y, beyaz);
        g2.setPaint (k�rm�z�danBeyaza);
        g2.fill (new RoundRectangle2D.Double (x, y, kutuEni, kutuBoyu, 10, 10));
        x += �zgaraEni;
        
        // (2.Sat�r, 4.Kolon): Dolu yay; fill Arc2D (k�rm�z�)
        g2.setPaint (k�rm�z�);
        g2.fill (new Arc2D.Double (x, y, kutuEni, kutuBoyu, 90, 135, Arc2D.OPEN));
        x += �zgaraEni; 
        
        // (2.Sat�r, 5.Kolon): Dolu elips; fill Ellipse2D.Double (k�rm�z�dan beyaza)
        k�rm�z�danBeyaza = new GradientPaint (x, y, k�rm�z�, x+kutuEni, y, beyaz);
        g2.setPaint (k�rm�z�danBeyaza);
        g2.fill (new Ellipse2D.Double (x, y, kutuEni, kutuBoyu));

         // (2.Sat�r, 6.Kolon): Dolu kenarl� poligon; fill and stroke GeneralPath (k�rm�z�)
        x += �zgaraEni; 
        int x3Noktalar[] = {x, x+kutuEni, x, x+kutuEni};
        int y3Noktalar[] = {y, y+kutuBoyu, y+kutuBoyu, y};
        GeneralPath doluPoligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x3Noktalar.length);
        doluPoligon.moveTo (x3Noktalar[0], y3Noktalar[0]);
        for (int endeks = 1; endeks < x3Noktalar.length; endeks++ ) doluPoligon.lineTo (x3Noktalar[endeks], y3Noktalar[endeks]);
        doluPoligon.closePath();
        g2.setPaint (k�rm�z�);
        g2.fill (doluPoligon);
        g2.setPaint (�izimRengi);
        g2.draw (doluPoligon);
    } // �ekilleri�iz(..) metodu sonu...

   public static void main (String s[]) {
        JFrame �er�eve = new JFrame();
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JPanel panel = new JPanel();
        yaz�c�D��mesi.setForeground (Color.BLUE);
        panel.add (yaz�c�D��mesi);
        �er�eve.getContentPane().add (BorderLayout.SOUTH, panel);
        �er�eve.getContentPane().add (BorderLayout.CENTER, new J9f_5()); // Kurucuyu �a��r�r...
        �er�eve.setSize (580, 500);
        �er�eve.setLocation (350, 50);
        �er�eve.show(); // �er�eve.setVisible (True);
    } // main(..) metodu sonu...
} // J9f_5 s�n�f� sonu...