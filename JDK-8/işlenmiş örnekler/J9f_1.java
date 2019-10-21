// J9f_1.java: HelloWorldPrinter (MerhabaD�nyaYaz�c�s�) �rne�i.

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
    public int print (Graphics g, PageFormat sayfaBi�imi, int sayfa) throws PrinterException {
        if (sayfa > 0) // S�f�r temelli tek sayfam�z var...
            return NO_SUCH_PAGE; // Printable aray�z� haz�r de�i�keni...

        // K�rp�lmay� �nlemek i�in (0,0) konumu de�il, sayfa bi�imi (X,Y) de�erleri al�nmal�...
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate (sayfaBi�imi.getImageableX(), sayfaBi�imi.getImageableY());

        // Yaz�c� sunumu yap�l�r...
        g.drawString ("Merhaba d�nya. Dikkat, bu bir yaz�c� ��kt�s�d�r!..", 10, 50);

        // Sayfan�n yaz�ld��� geri bildirimlenir...
        return PAGE_EXISTS; // Printable aray�z� haz�r de�i�keni...
    } // print(..) haz�r metodu sonu...

    public void actionPerformed (ActionEvent olay) {
         PrinterJob yaz�c�G�revi = PrinterJob.getPrinterJob();
         yaz�c�G�revi.setPrintable (this);
         boolean yaz�ls�nM� = yaz�c�G�revi.printDialog();
         if (yaz�ls�nM�) {
             try {yaz�c�G�revi.print(); // Parametreli haz�r print(..) metodunu parametresiz �a��rd�...
             }catch (PrinterException ist) {System.err.println ("Yazma i�lemi tamamlanamad�!..");}
         } // if karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    public static void main (String args[]) {
         UIManager.put ("swing.boldMetal", Boolean.FALSE);
        JFrame �er�eve = new JFrame ("Merhaba D�nya Yaz�c�s�");
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JButton yaz�c�D��mesi = new JButton ("D�nyaya Selam'� Yazd�r");
        yaz�c�D��mesi.setBackground (Color.BLACK);
        yaz�c�D��mesi.setForeground (Color.YELLOW);
        yaz�c�D��mesi.addActionListener (new J9f_1()); // Dinleyiciye kurucuyu �a��rarak duyarlar...
        �er�eve.add ("Center", yaz�c�D��mesi);
        �er�eve.pack();
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J9f_1 s�n�f� sonu...