// J9f_2.java: PaginationExample (Sayfalama�rne�i)

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JButton;

public class J9f_2 implements Printable, ActionListener {
    int[] sayfaK�r�lmaSat�rNo; // Her sayfa sonundaki k�r�lma sat�r nosu...

    String[] metinSat�rlar�;
    private void metinSat�rlar�n�Yarat() {
        if (metinSat�rlar� == null) {
            int toplamSat�rSay�s� = 200;
            metinSat�rlar� = new String[toplamSat�rSay�s�];
            for (int i = 0; i < toplamSat�rSay�s�; i++) metinSat�rlar�[i]= "(" + (i+1) + "/200).nci Merhaba Yaz�c� Sat�r�";
        } // if karar� sonu...
    } // metinSat�rlar�n�Yarat() metodu sonu...

    public int print (Graphics g, PageFormat sayfaBi�imi, int sayfaEndeksi) throws PrinterException {
        Font yaz�Fonu = new Font ("segoe script", Font.PLAIN, 20);
        FontMetrics yaz��l�ekleri = g.getFontMetrics (yaz�Fonu);
        int sat�rY�ksekli�i = yaz��l�ekleri.getHeight();

        if (sayfaK�r�lmaSat�rNo == null) {
            metinSat�rlar�n�Yarat();
            int herSayfadakiSat�rSay�s� = (int)(sayfaBi�imi.getImageableHeight() / sat�rY�ksekli�i);
            int toplamK�r�lmaSay�s� = (metinSat�rlar�.length - 1) / herSayfadakiSat�rSay�s�;
            sayfaK�r�lmaSat�rNo = new int [toplamK�r�lmaSay�s�];
            for (int k = 0; k < toplamK�r�lmaSay�s�; k++) sayfaK�r�lmaSat�rNo[k] = (k+1) * herSayfadakiSat�rSay�s�; 
        } // if karar� sonu...

        if (sayfaEndeksi > sayfaK�r�lmaSat�rNo.length) return NO_SUCH_PAGE;

         // Yaz� k�rp�lmas�n� �nlemek i�in ilk yazma konumu (0,0) de�il, yaz�labilir (X,Y) konumlar� g�z�n�nde bulundurulmal�d�r...
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate (sayfaBi�imi.getImageableX(), sayfaBi�imi.getImageableY());

        // Her yaz�lan sat�r sonras�, y bir art�r�lmal�d�r...
        int y = 0; 
        int ilk = (sayfaEndeksi == 0) ? 0 : sayfaK�r�lmaSat�rNo[sayfaEndeksi-1];
        int son = (sayfaEndeksi == sayfaK�r�lmaSat�rNo.length) ? metinSat�rlar�.length : sayfaK�r�lmaSat�rNo[sayfaEndeksi];
        for (int sat�r = ilk; sat�r < son; sat�r++) {
            y += sat�rY�ksekli�i;
            g.drawString (metinSat�rlar�[sat�r], 10, y);
        } // for d�ng�s� sonu...

        // D�nen de�er yaz�lan sayfa d�k�man� olsun...
        return PAGE_EXISTS;
    } // print(..) haz�r metodu sonu...

    public void actionPerformed (ActionEvent olay) {
         PrinterJob yaz�c�G�revi = PrinterJob.getPrinterJob();
         yaz�c�G�revi.setPrintable (this);
         boolean yaz�ls�nM� = yaz�c�G�revi.printDialog();
         if (yaz�ls�nM�) {
             try {yaz�c�G�revi.print(); // print() haz�r metodunu �a��r�r...
             }catch (PrinterException ist) {System.err.println ("Yazma i�lemi tamamlanamad�!..");}
         } // if karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    public static void main (String args[]) {
        try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ist) {}
        JFrame �er�eve = new JFrame ("�oklu Sayfa Yazd�rma");
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JButton yaz�c�D��mesi = new JButton ("�oklu Sayfalar� Yazd�r");
        yaz�c�D��mesi.setBackground (Color.BLACK);
        yaz�c�D��mesi.setForeground (Color.BLUE);
        yaz�c�D��mesi.addActionListener (new J9f_2()); // D��me dinleyiciye kurucusuz s�n�f nesneli duyarl�...
        �er�eve.add ("Center", yaz�c�D��mesi);
        �er�eve.setSize (200, 70); // (40,40) ilk kapal� a��l��t�r...
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J9f_2 s�n�f� sonu...