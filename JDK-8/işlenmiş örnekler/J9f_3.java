// J9f_3.java: PrintDialogExample (YazmaDiyaloðuÖrneði)

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;

import javax.swing.UIManager;

public class J9f_3 implements Printable {
    public int print (Graphics g, PageFormat sayfaBiçimi, int sayfa) throws PrinterException {
        if (sayfa > 0) // Sýfýr temelli tek sayfa var...
            return NO_SUCH_PAGE;

        Graphics2D g2d = (Graphics2D)g;
        g2d.translate (sayfaBiçimi.getImageableX(), sayfaBiçimi.getImageableY());
        g.drawString ("Yazýcý diyaloðunun test edilmesi baþarýyla tamamlandý!", 100, 100);
        return PAGE_EXISTS;
    } // print(..) hazýr metodu sonu...

    public static void main (String args[]) {
        try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ist) {} // Aldýrma...
        PrinterJob yazýcýGörevi = PrinterJob.getPrinterJob();
        PrintRequestAttributeSet özellikKümesi = new HashPrintRequestAttributeSet();
        PageFormat sayfaBiçimi = yazýcýGörevi.pageDialog (özellikKümesi);
        yazýcýGörevi.setPrintable (new J9f_3(), sayfaBiçimi); // Sýnýf nesneli yazýcýGörevi...
        boolean yazýlsýnMý = yazýcýGörevi.printDialog (özellikKümesi);
        if (yazýlsýnMý) {
            try {yazýcýGörevi.print (özellikKümesi); // print(..) hazýr metodunu çaðýrýr...
            }catch (PrinterException ist) {System.err.println ("Yazma iþlemi tamamlanamadý!.."); System.exit (0);}
        } // if kararý sonu...
    } // main(..) metodu sonu...
} // J9f_3 sýnýfý sonu...