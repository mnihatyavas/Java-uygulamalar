// J9f_5.java: ShapesPrint (ÞekillerinYazýmý) örneði.

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
    final static Color çizimRengi = Color.BLACK;
    final static Color kýrmýzý = Color.RED; 
    final static Color beyaz = Color.WHITE;

    final static BasicStroke koyu = new BasicStroke (2.0f);
    final static BasicStroke geniþKoyu = new BasicStroke (8.0f);

    final static float tire1[] = {10.0f};
    final static BasicStroke koyuTireli = new BasicStroke (
            1.0f,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            10.0f,
            tire1,
            0.0f);

    final static JButton yazýcýDüðmesi = new JButton ("YAZDIR");

    public J9f_5() {// Kurucu...
        setBackground (zeminRengi);
        yazýcýDüðmesi.addActionListener (this); // Düðme dinleyiciye duyarlý...
    } // J9f_5() kurucusu sonu...

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D) g;
        þekilleriÇiz (g2);
    } // paintComponent(..) hazýr metodu sonu...

   public void actionPerformed (ActionEvent olay) {
        if (olay.getSource() instanceof JButton) {   
            PrinterJob yazdýrmaÝþi = PrinterJob.getPrinterJob();
            yazdýrmaÝþi.setPrintable (this);
            if (yazdýrmaÝþi.printDialog()) {
                try {yazdýrmaÝþi.print(); // print() hazýr metodunu çaðýrýr...
                }catch (Exception ist) {ist.printStackTrace();}
            } // if kararý sonu...
       } // dýþ-if kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    public int print (Graphics g, PageFormat sayfaBiçimi, int sayfa) throws PrinterException {
        if (sayfa >= 1) return Printable.NO_SUCH_PAGE;
        þekilleriÇiz ((Graphics2D) g);
        return Printable.PAGE_EXISTS;
    } // print(..) hazýr metodu sonu...

    public void þekilleriÇiz (Graphics2D g2) {
        Dimension ebat = getSize();
        int ýzgaraEni = 400 / 6;
        int ýzgaraBoyu = 300 / 2;

        int satýrArasý = 5;
        int sütunArasý = 7;
        int kutuEni = ýzgaraEni - sütunArasý;
        int kutuBoyu = ýzgaraBoyu - satýrArasý;

        // Açýk gri renkte ýzgara çerçevesi çizilecek...
        Color çizimRengi3D = Color.lightGray;
        g2.setPaint (çizimRengi3D);
        g2.drawRect (80, 80, 400 - 1, 310);

        g2.setPaint (çizimRengi);

        // 1.SATIR
        // (1.Satýr, 1.Kolon): Çapraz çizgi; Line2D.Double
        int x = 85;
        int y = 87;
        g2.draw (new Line2D.Double (x, y+kutuBoyu-1, x + kutuEni, y));
        x += ýzgaraEni;

        // (1.Satýr, 2.Kolon): Dik kutu; Rectangle2D.Double
        g2.setStroke (koyu);
        g2.draw (new Rectangle2D.Double (x, y, kutuEni, kutuBoyu));
        x += ýzgaraEni;

        // (1.Satýr, 3.Kolon): Tireli kutu; RoundRectangle2D.Double
        g2.setStroke (koyuTireli);
        g2.draw (new RoundRectangle2D.Double (x, y, kutuEni, kutuBoyu, 10, 10));
        x += ýzgaraEni;
       
        // (1.Satýr, 4.Kolon): Kalýn yay; Arc2D.Double
        g2.setStroke (geniþKoyu);
        g2.draw (new Arc2D.Double (x, y, kutuEni, kutuBoyu, 90, 135, Arc2D.OPEN));
        x += ýzgaraEni;

        // (1.Satýr, 5.Kolon): Dik elips; Ellipse2D.Double
        g2.setStroke (koyu);
        g2.draw (new Ellipse2D.Double (x, y, kutuEni, kutuBoyu));
        x += ýzgaraEni;

        // (1.Satýr, 6.Kolon): Poligon; GeneralPath
        int x1Noktalar[] = {x, x+kutuEni, x, x+kutuEni};
        int y1Noktalar[] = {y, y+kutuBoyu, y+kutuBoyu, y};
        GeneralPath poligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x1Noktalar.length);
        poligon.moveTo (x1Noktalar[0], y1Noktalar[0]);
        for (int endeks = 1; endeks < x1Noktalar.length; endeks++ ) poligon.lineTo (x1Noktalar[endeks], y1Noktalar[endeks]);
        poligon.closePath();
        g2.draw (poligon);
        
        // 2.SATIR
        // (2.Satýr, 1.Kolon): Açýk poligon; GeneralPath
        x = 85;
       y += ýzgaraBoyu;
        int x2Noktalar[] = {x, x+kutuEni, x, x+kutuEni};
        int y2Noktalar[] = {y, y+kutuBoyu, y+kutuBoyu, y};
        GeneralPath poliÇizgi = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x2Noktalar.length);
        poliÇizgi.moveTo (x2Noktalar[0], y2Noktalar[0]);
       for (int endeks = 1; endeks < x2Noktalar.length; endeks++ ) poliÇizgi.lineTo (x2Noktalar[endeks], y2Noktalar[endeks]);
        g2.draw (poliÇizgi);
        x += ýzgaraEni;
        
        // (2.Satýr, 2.Kolon): Dolu dik kutu; fill Rectangle2D.Double (kýrmýzý)
        g2.setPaint (kýrmýzý);
        g2.fill (new Rectangle2D.Double (x, y, kutuEni, kutuBoyu));
        x += ýzgaraEni;

        // (2.Satýr, 3.Kolon): Dolu kademeli dik kutu; fill RoundRectangle2D.Double (kýzmýzýdan beyaza)
        GradientPaint kýrmýzýdanBeyaza = new GradientPaint (x, y, kýrmýzý, x+kutuEni, y, beyaz);
        g2.setPaint (kýrmýzýdanBeyaza);
        g2.fill (new RoundRectangle2D.Double (x, y, kutuEni, kutuBoyu, 10, 10));
        x += ýzgaraEni;
        
        // (2.Satýr, 4.Kolon): Dolu yay; fill Arc2D (kýrmýzý)
        g2.setPaint (kýrmýzý);
        g2.fill (new Arc2D.Double (x, y, kutuEni, kutuBoyu, 90, 135, Arc2D.OPEN));
        x += ýzgaraEni; 
        
        // (2.Satýr, 5.Kolon): Dolu elips; fill Ellipse2D.Double (kýrmýzýdan beyaza)
        kýrmýzýdanBeyaza = new GradientPaint (x, y, kýrmýzý, x+kutuEni, y, beyaz);
        g2.setPaint (kýrmýzýdanBeyaza);
        g2.fill (new Ellipse2D.Double (x, y, kutuEni, kutuBoyu));

         // (2.Satýr, 6.Kolon): Dolu kenarlý poligon; fill and stroke GeneralPath (kýrmýzý)
        x += ýzgaraEni; 
        int x3Noktalar[] = {x, x+kutuEni, x, x+kutuEni};
        int y3Noktalar[] = {y, y+kutuBoyu, y+kutuBoyu, y};
        GeneralPath doluPoligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x3Noktalar.length);
        doluPoligon.moveTo (x3Noktalar[0], y3Noktalar[0]);
        for (int endeks = 1; endeks < x3Noktalar.length; endeks++ ) doluPoligon.lineTo (x3Noktalar[endeks], y3Noktalar[endeks]);
        doluPoligon.closePath();
        g2.setPaint (kýrmýzý);
        g2.fill (doluPoligon);
        g2.setPaint (çizimRengi);
        g2.draw (doluPoligon);
    } // þekilleriÇiz(..) metodu sonu...

   public static void main (String s[]) {
        JFrame çerçeve = new JFrame();
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JPanel panel = new JPanel();
        yazýcýDüðmesi.setForeground (Color.BLUE);
        panel.add (yazýcýDüðmesi);
        çerçeve.getContentPane().add (BorderLayout.SOUTH, panel);
        çerçeve.getContentPane().add (BorderLayout.CENTER, new J9f_5()); // Kurucuyu çaðýrýr...
        çerçeve.setSize (580, 500);
        çerçeve.setLocation (350, 50);
        çerçeve.show(); // çerçeve.setVisible (True);
    } // main(..) metodu sonu...
} // J9f_5 sýnýfý sonu...