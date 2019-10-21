// J9f_6.java: SimpleBook (BasitKitap) örneði.

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
    final static Color yazýRengi = Color.BLACK;
    final static Color kýrmýzý = Color.RED;
    final static Color beyaz = Color.WHITE;

    final static BasicStroke koyu = new BasicStroke (2.0f);
    final static BasicStroke geniþKoyu = new BasicStroke (8.0f);

    final static float tire[] = {10.0f};
    final static BasicStroke tireliKoyu = new BasicStroke (
            1.0f,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            10.0f,
            tire,
            0.0f);
    final static JButton yazýcýDüðmesi = new JButton ("YAZICI");

    public static void main (String[] args) {
        JFrame çerçeve = new JFrame();
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JPanel panel = new JPanel();
        panel.add (yazýcýDüðmesi);
        yazýcýDüðmesi.setForeground (Color.BLUE);
        çerçeve.getContentPane().add (BorderLayout.SOUTH, panel);
        çerçeve.getContentPane().add (BorderLayout.CENTER, new J9f_6()); // Kurucuyu çaðýrýr...
        çerçeve.setSize (775, 450);
        çerçeve.setLocation (400, 50);
        çerçeve.show(); // çerçeve.setVisible (True);
    } // main(..) metodu sonu...

    public J9f_6() {// Kurucu...
        setBackground (zeminRengi);
        yazýcýDüðmesi.addActionListener (this); // Düðme dinleyiciye duyarlý...
    } // J9f_6() kurucusu sonu...

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D) g;
        þekilleriÇiz (g2);
    } // paintComponent(..) hazýr metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        PrinterJob yazýcýGörevi = PrinterJob.getPrinterJob();
        PageFormat sayfaBiçimi = yazýcýGörevi.defaultPage();
        sayfaBiçimi.setOrientation (PageFormat.LANDSCAPE); // Çizim yatay...
        // Bir kitap yaratýp, kapaðý ve içerikleri ekleyelim...
        Book kitap = new Book();
        kitap.append (new KapaðýKur(), yazýcýGörevi.defaultPage()); // Normal yazacak...
        kitap.append (new ÝçeriðiKur(), sayfaBiçimi); // Yatay çizecek...
        // Kitabý PrinterJob'a gönderelim...
        yazýcýGörevi.setPageable (kitap);
        // Yazdýrma iptal edilmediyse diyalog kutusunu açalým...
        if (yazýcýGörevi.printDialog()) {
            try {yazýcýGörevi.print(); // print() hazýr yazdýrma metodu(larýný) çaðrýlýr...
            }catch (Exception ist) {System.err.println ("HATA: Yazdýrma iþlemi tamamlanamadý!"); System.exit (1);}
        } // if kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    static void þekilleriÇiz (Graphics2D g2){
        int ýzgaraEni = 600 / 6;
        int ýzgaraBoyu = 250 / 2;

        int satýrArasý = 5;
        int sütunArasý = 7;
        int hücreEni = ýzgaraEni - sütunArasý;
        int hücreBoyu = ýzgaraBoyu - satýrArasý;

        Color yazýRengi3D = Color.lightGray;

        // Izgara çerçevesini çizelim...
        g2.setPaint (yazýRengi3D);
        g2.drawRect (80,80, 605-1, 265);

        // 1.SATIR
        // (1.satýr, 1.hücre) Line2D.Double
        g2.setPaint (yazýRengi);
        int x = 85;
        int y = 87;
        g2.draw (new Line2D.Double (x, y+hücreBoyu-1, x + hücreEni, y));

        // (1.satýr, 2.hücre) Rectangle2D.Double
        x += ýzgaraEni;
        g2.setStroke (koyu);
        g2.draw (new Rectangle2D.Double (x, y, hücreEni, hücreBoyu));

        // (1.satýr, 3.hücre) RoundRectangle2D.Double
        x += ýzgaraEni;
        g2.setStroke (tireliKoyu);
        g2.draw (new RoundRectangle2D.Double (x, y, hücreEni, hücreBoyu, 10, 10));

        // (1.satýr, 4.hücre) Arc2D.Double
        x += ýzgaraEni;
        g2.setStroke (geniþKoyu);
        g2.draw (new Arc2D.Double (x, y, hücreEni, hücreBoyu, 90, 135, Arc2D.OPEN));

        // (1.satýr, 5.hücre) Ellipse2D.Double
        x += ýzgaraEni;
        g2.setStroke(koyu);
        g2.draw (new Ellipse2D.Double (x, y, hücreEni, hücreBoyu));

        // (1.satýr, 6.hücre) GeneralPath (poligon)
        x += ýzgaraEni;
        int x1Noktalar[] = {x, x+hücreEni, x, x+hücreEni};
        int y1Noktalar[] = {y, y+hücreBoyu, y+hücreBoyu, y};
        GeneralPath poligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x1Noktalar.length);
        poligon.moveTo (x1Noktalar[0], y1Noktalar[0]);
        for (int endeks = 1; endeks < x1Noktalar.length; endeks++) poligon.lineTo (x1Noktalar[endeks], y1Noktalar[endeks]);
        poligon.closePath(); 
        g2.draw (poligon);

        // 2.SATIR
        // (2.satýr, 1.hücre) GeneralPath (poliçizgi)
        x = 85;
        y += ýzgaraBoyu;
        int x2Noktalar[] = {x, x+hücreEni, x, x+hücreEni};
        int y2Noktalar[] = {y, y+hücreBoyu, y+hücreBoyu, y};
        GeneralPath poliçizgi = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x2Noktalar.length);
        poliçizgi.moveTo (x2Noktalar[0], y2Noktalar[0]);
       for (int endeks = 1; endeks < x2Noktalar.length; endeks++) poliçizgi.lineTo (x2Noktalar[endeks], y2Noktalar[endeks]);
        g2.draw (poliçizgi);

        // (2.satýr, 2.hücre) dolgulu Rectangle2D.Double (kýrmýzý)
        x += ýzgaraEni;
        g2.setPaint (kýrmýzý);
        g2.fill (new Rectangle2D.Double (x, y, hücreEni, hücreBoyu));

        // (2.satýr, 3.hücre) dolgulu RoundRectangle2D.Double
        x += ýzgaraEni;
        GradientPaint kýrmýzýdanBeyaza = new GradientPaint (x, y, kýrmýzý, x+hücreEni, y, beyaz);
        g2.setPaint (kýrmýzýdanBeyaza);
        g2.fill (new RoundRectangle2D.Double (x, y, hücreEni, hücreBoyu, 10, 10));

        // (2.satýr, 4.hücre) dolgulu Arc2D
        x += ýzgaraEni;
        g2.setPaint (kýrmýzý);
        g2.fill (new Arc2D.Double (x, y, hücreEni, hücreBoyu, 90, 135, Arc2D.OPEN));

        // (2.satýr, 5.hücre) dolgulu Ellipse2D.Double
        x += ýzgaraEni;
        kýrmýzýdanBeyaza = new GradientPaint (x, y, kýrmýzý, x+hücreEni, y, beyaz);
        g2.setPaint (kýrmýzýdanBeyaza);
        g2.fill (new Ellipse2D.Double (x, y, hücreEni, hücreBoyu));

        // (2.satýr, 6.hücre) dolgulu ve koyu GeneralPath (poligon)
        x += ýzgaraEni;
        int x3Noktalar[] = {x, x+hücreEni, x, x+hücreEni};
        int y3Noktalar[] = {y, y+hücreBoyu, y+hücreBoyu, y};
        GeneralPath dolguluPoligon = new GeneralPath (GeneralPath.WIND_EVEN_ODD, x3Noktalar.length);
        dolguluPoligon.moveTo(x3Noktalar[0], y3Noktalar[0]);
        for (int endeks = 1; endeks < x3Noktalar.length; endeks++) dolguluPoligon.lineTo (x3Noktalar[endeks], y3Noktalar[endeks]);
        dolguluPoligon.closePath();
        g2.setPaint (kýrmýzý);
        g2.fill (dolguluPoligon);
        g2.setPaint (yazýRengi);
        g2.draw (dolguluPoligon);
    } // þekilleriÇiz(..) metodu sonu...
} // J9f_6 sýnýfý sonu...

class KapaðýKur implements Printable {
    Font yazýFonu = new Font ("Helvetica-Bold", Font.PLAIN, 50);

    public int print (Graphics g, PageFormat sayfaBiçimi, int sayfaEndeksi) throws PrinterException {
        g.setFont (yazýFonu);
        g.setColor (Color.BLACK);
        g.drawString ("Numune Þekiller", 100, 400);
        return Printable.PAGE_EXISTS;
    } // print(..) hazýr metodu sonu...
} // KapaðýKur sýnýfý sonu...

class ÝçeriðiKur implements Printable {
    public int print (Graphics g, PageFormat sayfaBiçimi, int sayfaEndeksi) throws PrinterException {
        J9f_6.þekilleriÇiz ((Graphics2D)g);
        return Printable.PAGE_EXISTS;
    } // print(..) hazýr metodu sonu...
} // ÝçeriðiKur sýnýfý sonu...