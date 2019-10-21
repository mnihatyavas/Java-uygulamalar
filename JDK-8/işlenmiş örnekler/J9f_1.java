// J9f_1.java: HelloWorldPrinter (MerhabaDünyaYazýcýsý) örneði.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.print.PrinterJob;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PageFormat;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.UIManager;

public class J9f_1 implements Printable, ActionListener {
    public int print (Graphics g, PageFormat sayfaBiçimi, int sayfa) throws PrinterException {
        if (sayfa > 0) // Sýfýr temelli tek sayfamýz var...
            return NO_SUCH_PAGE; // Printable arayüzü hazýr deðiþkeni...

        // Kýrpýlmayý önlemek için (0,0) konumu deðil, sayfa biçimi (X,Y) deðerleri alýnmalý...
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate (sayfaBiçimi.getImageableX(), sayfaBiçimi.getImageableY());

        // Yazýcý sunumu yapýlýr...
        g.drawString ("Merhaba dünya. Dikkat, bu bir yazýcý çýktýsýdýr!..", 10, 50);

        // Sayfanýn yazýldýðý geri bildirimlenir...
        return PAGE_EXISTS; // Printable arayüzü hazýr deðiþkeni...
    } // print(..) hazýr metodu sonu...

    public void actionPerformed (ActionEvent olay) {
         PrinterJob yazýcýGörevi = PrinterJob.getPrinterJob();
         yazýcýGörevi.setPrintable (this);
         boolean yazýlsýnMý = yazýcýGörevi.printDialog();
         if (yazýlsýnMý) {
             try {yazýcýGörevi.print(); // Parametreli hazýr print(..) metodunu parametresiz çaðýrdý...
             }catch (PrinterException ist) {System.err.println ("Yazma iþlemi tamamlanamadý!..");}
         } // if kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    public static void main (String args[]) {
         UIManager.put ("swing.boldMetal", Boolean.FALSE);
        JFrame çerçeve = new JFrame ("Merhaba Dünya Yazýcýsý");
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JButton yazýcýDüðmesi = new JButton ("Dünyaya Selam'ý Yazdýr");
        yazýcýDüðmesi.setBackground (Color.BLACK);
        yazýcýDüðmesi.setForeground (Color.YELLOW);
        yazýcýDüðmesi.addActionListener (new J9f_1()); // Dinleyiciye kurucuyu çaðýrarak duyarlar...
        çerçeve.add ("Center", yazýcýDüðmesi);
        çerçeve.pack();
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J9f_1 sýnýfý sonu...