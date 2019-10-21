// J9f_4.java: PrintUIWindow (YazUI-KullanýcýArayüzü-Penceresini) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.awt.print.PrinterException;
import java.awt.print.PageFormat;

import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class J9f_4 implements Printable, ActionListener {
    JFrame yazýlacakPencere;

    public int print (Graphics g, PageFormat sayfaBiçimi, int sayfa) throws PrinterException {
        if (sayfa > 0) return NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate (sayfaBiçimi.getImageableX(), sayfaBiçimi.getImageableY());
        yazýlacakPencere.printAll (g); // g.drawString(..) yerine printAll(g) metodlu yazdýrma...
        return PAGE_EXISTS;
    } // print(..) hazýr metodu sonu...

    public void actionPerformed (ActionEvent olay) {
         PrinterJob yazýmÝþi = PrinterJob.getPrinterJob();
         yazýmÝþi.setPrintable (this);
         boolean yazýlsýnMý = yazýmÝþi.printDialog();
         if (yazýlsýnMý) {
             try {yazýmÝþi.print(); // print() hazýr metodu çaðrýlýr...
             }catch (PrinterException ist) {System.err.println ("HATA: Yazma iþlemi tamamlanamadý!.."); System.exit (1);}
         } // if kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    public J9f_4 (JFrame çerçeve) {yazýlacakPencere = çerçeve;} // Kurucu...

    public static void main (String args[]) {
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        JFrame pencere = new JFrame ("UI-KullanýcýArayüzü Yazdýrma Örneði");
        pencere.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JTextArea metinAlaný = new JTextArea (50, 20);
        for (int i=1;i<=50;i++) metinAlaný.append ("     Satýr no: " + i + "\n");
        JScrollPane kaydýrmaPanosu = new JScrollPane (metinAlaný);
        kaydýrmaPanosu.setPreferredSize (new Dimension (300, 400));
        pencere.add ("Center", kaydýrmaPanosu);
        JButton yazdýrmaDüðmesi = new JButton ("Bu Pencereyi Aynen Yazdýr");
        yazdýrmaDüðmesi.addActionListener (new J9f_4 (pencere)); // Sýnýf nesneli, dinleyiciye duyarlý düðme...
        yazdýrmaDüðmesi.setBackground (Color.BLACK);
        yazdýrmaDüðmesi.setForeground (Color.YELLOW);
        pencere.add ("South", yazdýrmaDüðmesi);
        pencere.pack();
        pencere.setLocationRelativeTo (null);
        pencere.setVisible (true);
    } // main(..) metodu sonu...
} // J9f_4 sýnýfý sonu...