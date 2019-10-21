// J9f_3.java: PrintDialogExample (YazmaDiyalo�u�rne�i)

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
    public int print (Graphics g, PageFormat sayfaBi�imi, int sayfa) throws PrinterException {
        if (sayfa > 0) // S�f�r temelli tek sayfa var...
            return NO_SUCH_PAGE;

        Graphics2D g2d = (Graphics2D)g;
        g2d.translate (sayfaBi�imi.getImageableX(), sayfaBi�imi.getImageableY());
        g.drawString ("Yaz�c� diyalo�unun test edilmesi ba�ar�yla tamamland�!", 100, 100);
        return PAGE_EXISTS;
    } // print(..) haz�r metodu sonu...

    public static void main (String args[]) {
        try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ist) {} // Ald�rma...
        PrinterJob yaz�c�G�revi = PrinterJob.getPrinterJob();
        PrintRequestAttributeSet �zellikK�mesi = new HashPrintRequestAttributeSet();
        PageFormat sayfaBi�imi = yaz�c�G�revi.pageDialog (�zellikK�mesi);
        yaz�c�G�revi.setPrintable (new J9f_3(), sayfaBi�imi); // S�n�f nesneli yaz�c�G�revi...
        boolean yaz�ls�nM� = yaz�c�G�revi.printDialog (�zellikK�mesi);
        if (yaz�ls�nM�) {
            try {yaz�c�G�revi.print (�zellikK�mesi); // print(..) haz�r metodunu �a��r�r...
            }catch (PrinterException ist) {System.err.println ("Yazma i�lemi tamamlanamad�!.."); System.exit (0);}
        } // if karar� sonu...
    } // main(..) metodu sonu...
} // J9f_3 s�n�f� sonu...