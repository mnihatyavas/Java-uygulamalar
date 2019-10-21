// J9f_4.java: PrintUIWindow (YazUI-Kullan�c�Aray�z�-Penceresini) �rne�i.

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
    JFrame yaz�lacakPencere;

    public int print (Graphics g, PageFormat sayfaBi�imi, int sayfa) throws PrinterException {
        if (sayfa > 0) return NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate (sayfaBi�imi.getImageableX(), sayfaBi�imi.getImageableY());
        yaz�lacakPencere.printAll (g); // g.drawString(..) yerine printAll(g) metodlu yazd�rma...
        return PAGE_EXISTS;
    } // print(..) haz�r metodu sonu...

    public void actionPerformed (ActionEvent olay) {
         PrinterJob yaz�m��i = PrinterJob.getPrinterJob();
         yaz�m��i.setPrintable (this);
         boolean yaz�ls�nM� = yaz�m��i.printDialog();
         if (yaz�ls�nM�) {
             try {yaz�m��i.print(); // print() haz�r metodu �a�r�l�r...
             }catch (PrinterException ist) {System.err.println ("HATA: Yazma i�lemi tamamlanamad�!.."); System.exit (1);}
         } // if karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    public J9f_4 (JFrame �er�eve) {yaz�lacakPencere = �er�eve;} // Kurucu...

    public static void main (String args[]) {
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        JFrame pencere = new JFrame ("UI-Kullan�c�Aray�z� Yazd�rma �rne�i");
        pencere.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JTextArea metinAlan� = new JTextArea (50, 20);
        for (int i=1;i<=50;i++) metinAlan�.append ("     Sat�r no: " + i + "\n");
        JScrollPane kayd�rmaPanosu = new JScrollPane (metinAlan�);
        kayd�rmaPanosu.setPreferredSize (new Dimension (300, 400));
        pencere.add ("Center", kayd�rmaPanosu);
        JButton yazd�rmaD��mesi = new JButton ("Bu Pencereyi Aynen Yazd�r");
        yazd�rmaD��mesi.addActionListener (new J9f_4 (pencere)); // S�n�f nesneli, dinleyiciye duyarl� d��me...
        yazd�rmaD��mesi.setBackground (Color.BLACK);
        yazd�rmaD��mesi.setForeground (Color.YELLOW);
        pencere.add ("South", yazd�rmaD��mesi);
        pencere.pack();
        pencere.setLocationRelativeTo (null);
        pencere.setVisible (true);
    } // main(..) metodu sonu...
} // J9f_4 s�n�f� sonu...