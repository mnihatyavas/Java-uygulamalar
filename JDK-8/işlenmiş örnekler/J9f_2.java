// J9f_2.java: PaginationExample (SayfalamaÖrneði)

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
    int[] sayfaKýrýlmaSatýrNo; // Her sayfa sonundaki kýrýlma satýr nosu...

    String[] metinSatýrlarý;
    private void metinSatýrlarýnýYarat() {
        if (metinSatýrlarý == null) {
            int toplamSatýrSayýsý = 200;
            metinSatýrlarý = new String[toplamSatýrSayýsý];
            for (int i = 0; i < toplamSatýrSayýsý; i++) metinSatýrlarý[i]= "(" + (i+1) + "/200).nci Merhaba Yazýcý Satýrý";
        } // if kararý sonu...
    } // metinSatýrlarýnýYarat() metodu sonu...

    public int print (Graphics g, PageFormat sayfaBiçimi, int sayfaEndeksi) throws PrinterException {
        Font yazýFonu = new Font ("segoe script", Font.PLAIN, 20);
        FontMetrics yazýÖlçekleri = g.getFontMetrics (yazýFonu);
        int satýrYüksekliði = yazýÖlçekleri.getHeight();

        if (sayfaKýrýlmaSatýrNo == null) {
            metinSatýrlarýnýYarat();
            int herSayfadakiSatýrSayýsý = (int)(sayfaBiçimi.getImageableHeight() / satýrYüksekliði);
            int toplamKýrýlmaSayýsý = (metinSatýrlarý.length - 1) / herSayfadakiSatýrSayýsý;
            sayfaKýrýlmaSatýrNo = new int [toplamKýrýlmaSayýsý];
            for (int k = 0; k < toplamKýrýlmaSayýsý; k++) sayfaKýrýlmaSatýrNo[k] = (k+1) * herSayfadakiSatýrSayýsý; 
        } // if kararý sonu...

        if (sayfaEndeksi > sayfaKýrýlmaSatýrNo.length) return NO_SUCH_PAGE;

         // Yazý kýrpýlmasýný önlemek için ilk yazma konumu (0,0) deðil, yazýlabilir (X,Y) konumlarý gözönünde bulundurulmalýdýr...
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate (sayfaBiçimi.getImageableX(), sayfaBiçimi.getImageableY());

        // Her yazýlan satýr sonrasý, y bir artýrýlmalýdýr...
        int y = 0; 
        int ilk = (sayfaEndeksi == 0) ? 0 : sayfaKýrýlmaSatýrNo[sayfaEndeksi-1];
        int son = (sayfaEndeksi == sayfaKýrýlmaSatýrNo.length) ? metinSatýrlarý.length : sayfaKýrýlmaSatýrNo[sayfaEndeksi];
        for (int satýr = ilk; satýr < son; satýr++) {
            y += satýrYüksekliði;
            g.drawString (metinSatýrlarý[satýr], 10, y);
        } // for döngüsü sonu...

        // Dönen deðer yazýlan sayfa dökümaný olsun...
        return PAGE_EXISTS;
    } // print(..) hazýr metodu sonu...

    public void actionPerformed (ActionEvent olay) {
         PrinterJob yazýcýGörevi = PrinterJob.getPrinterJob();
         yazýcýGörevi.setPrintable (this);
         boolean yazýlsýnMý = yazýcýGörevi.printDialog();
         if (yazýlsýnMý) {
             try {yazýcýGörevi.print(); // print() hazýr metodunu çaðýrýr...
             }catch (PrinterException ist) {System.err.println ("Yazma iþlemi tamamlanamadý!..");}
         } // if kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    public static void main (String args[]) {
        try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ist) {}
        JFrame çerçeve = new JFrame ("Çoklu Sayfa Yazdýrma");
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JButton yazýcýDüðmesi = new JButton ("Çoklu Sayfalarý Yazdýr");
        yazýcýDüðmesi.setBackground (Color.BLACK);
        yazýcýDüðmesi.setForeground (Color.BLUE);
        yazýcýDüðmesi.addActionListener (new J9f_2()); // Düðme dinleyiciye kurucusuz sýnýf nesneli duyarlý...
        çerçeve.add ("Center", yazýcýDüðmesi);
        çerçeve.setSize (200, 70); // (40,40) ilk kapalý açýlýþtýr...
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J9f_2 sýnýfý sonu...