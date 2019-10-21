// J5e_17.java: TablePrintDemo3 (TabloYazdýrmaGösterisi3) örneði.

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PageFormat;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableModel;

import javax.imageio.ImageIO;

import java.text.MessageFormat;

import java.io.IOException;

/* Gereken dosyalar:
 *  J5e_15.java=TablePrintDemo1.java
 *  J5e_16.java=TablePrintDemo2.java
 *
 *     resim/tablo/geçti.png
 *     resim/tablo/kaldý.png
 *
 *     resim/tablo/geçti-SB.png
 *     resim/tablo/kaldý-SB.png
 *
 *     resim/tablo/üstOrta.png
 *     resim/tablo/üst.png
 *     resim/tablo/alt.png
 *     resim/tablo/üstSol.png
 *     resim/tablo/üstSað.png
 *     resim/tablo/altSol.png
 *     resim/tablo/altSað.png
 *     resim/tablo/sol.png
 *     resim/tablo/sað.png
 *     resim/tablo/baþlýk.png
 *
 * Tablo yazdýrýmýnda ilkinden farklý olarak, ikincinin siyah/beyaz geçti/kaldý
 * ve ayrýca bu programýn kenarlýk resimlerini kullanmaktadýr.
 */
public class J5e_17 extends J5e_16 {
    public J5e_17() {// Kurucu...
        setTitle ("Tablo Yazdýrma Gösterisi 3");

        // Aþaðýdaki deðiþken etkilerini kapatýlýp yerine kendimizinkileri kuracaðýz...
        String aletÝpucuMetni = "Etkisizleþtirildi - Bu program kendi özel Sayfabaþý/Sayfasonu deðerlerini kullanýyor.";
        sayfaBaþýKutusu.setEnabled (false);
        sayfaBaþýKutusu.setSelected (false);
        sayfaBaþýKutusu.setToolTipText (aletÝpucuMetni);
        sayfaBaþýMetinSatýrý.setEnabled (false);
        sayfaBaþýMetinSatýrý.setText ("<Özel>");
        sayfaBaþýMetinSatýrý.setToolTipText (aletÝpucuMetni);
        sayfaSonuKutusu.setEnabled (false);
        sayfaSonuKutusu.setSelected (false);
        sayfaSonuKutusu.setToolTipText (aletÝpucuMetni);
        sayfaSonuMetinSatýrý.setEnabled (false);
        sayfaSonuMetinSatýrý.setText ("<Özel>");
        sayfaSonuMetinSatýrý.setToolTipText (aletÝpucuMetni);
    } // J5e_17() kurucusu sonu...

    // Önceki esgeçilecek...
    protected JTable tabloyuYarat (TableModel model) {return new SüslüTabloYaz (model);}

    private static class SüslüTabloYaz extends JTable {
        public SüslüTabloYaz (TableModel model) {super (model);} // Kurucu...

        // Esgeçen metod. Kendi baþlýk ve sonluðunu kullanýr: Sayfa no daima sonluk'tadýr...
        public Printable getPrintable (
                PrintMode yazdýrmaKipi,
                MessageFormat baþlýkBiçimi,
                MessageFormat sonlukBiçimi) {

            MessageFormat sayfaNo = new MessageFormat ("- {0} -");

            // Varsayýlý yazdýrýlabiliri alalým...
            Printable varsayýlýYazdýrma = super.getPrintable (yazdýrmaKipi, null, sayfaNo);

            // Varsayýlýya kenarlýk süslemeleri saracak yeni bir süslü yazdýrýlabilir döndürelim...
            return new SüslüYazdýr (varsayýlýYazdýrma);
        } // getPrintable(..) hazýr metodu sonu...
    } // SüslüTabloYaz sýnýfý sonu...

    private static class SüslüYazdýr implements Printable {
        private Printable varsayýlýYazdýrma;

        // Tablonun çevresini saracak kenarlýk resim deðiþkenlerini tanýmlayalým...
        private BufferedImage üstOrta;
        private BufferedImage üst;
        private BufferedImage alt;
        private BufferedImage üstSol;
        private BufferedImage üstSað;
        private BufferedImage sol;
        private BufferedImage sað;
        private BufferedImage altSol;
        private BufferedImage altSað;
        private BufferedImage baþlýk;        

        boolean resimlerYüklendiMi;

        // Resimleri derli-toplu özel bir {} blok içinde ve try-catch kontrollu yükleyelim...
        {// Resim yükleme bloðu...
            try {
                üstSol = ImageIO.read (getClass().getResource ("resim/Tablo/üstSol.png"));
                üst = ImageIO.read (getClass().getResource ("resim/Tablo/üst.png"));
                üstOrta = ImageIO.read (getClass().getResource ("resim/Tablo/üstOrta.png"));
                üstSað = ImageIO.read (getClass().getResource ("resim/Tablo/üstSað.png"));
                altSol = ImageIO.read (getClass().getResource ("resim/Tablo/altSol.png"));
                alt = ImageIO.read (getClass().getResource ("resim/Tablo/alt.png"));
                altSað = ImageIO.read (getClass().getResource ("resim/Tablo/altSað.png"));
                sol = ImageIO.read (getClass().getResource ("resim/Tablo/sol.png"));
                sað = ImageIO.read (getClass().getResource ("resim/Tablo/sað.png"));
                baþlýk = ImageIO.read (getClass().getResource ("resim/Tablo/baþlýk.png"));

                resimlerYüklendiMi = true;
            }catch (IOException ist) {resimlerYüklendiMi = false;
            } // try-catch bloðu sonu...
        } // Resim yükleme bloðu sonu...

        public SüslüYazdýr (Printable varsayýlýYazdýrma) {this.varsayýlýYazdýrma = varsayýlýYazdýrma;} // Kurucu...

        public int print (Graphics grafik, final PageFormat sayfaBiçimi, int sayfaEndeksi)
                throws PrinterException {

            // Kenarlýk resimleri yüklenemediyse, varsayýlýyý kenarlýksýz yazdýrýlmak üzere döndürelim...
            if (!resimlerYüklendiMi) {return varsayýlýYazdýrma.print (grafik, sayfaBiçimi, sayfaEndeksi);}

            // Kenarlýk resminin sol üst konumu...
            int ix = (int)sayfaBiçimi.getImageableX();
            int iy = (int)sayfaBiçimi.getImageableY();

            // Kenarlýklý alanýn eni/boyu...
            int iw = (int)sayfaBiçimi.getImageableWidth();
            int ih = (int)sayfaBiçimi.getImageableHeight();

            // Sol kenarlýk resminin geniþliði...
            int leftWidth = sol.getWidth();
            
            // Sað kenarlýk resminin geniþliði...
            int rightWidth = sað.getWidth();

            // Üst kenarlýk resminin yüksekliði...
            int topHeight = üst.getHeight();

            // Alt kenarlýk resminin yüksekliði...
            int bottomHeight = alt.getHeight();
            
            // Üst not baþlýðý resminin yüksekliði...
            int baþlýkHeight = baþlýk.getHeight();

            // Sol-sað kenarlýkla tablo arasý mesafe...
            final int tableX = ix + leftWidth + 10;
            final int tableW = iw - (leftWidth + 10) - (rightWidth + 10);

            // Tablo üstü + 10 + not baþlýðý + 10 + kenarlýk üst resmi...
            final int tableY = iy + topHeight + 10 + baþlýkHeight + 10;

            // Tablo altý + 10 + kenarlýk altý...
            final int tableH = ih - (topHeight + 10) - (baþlýkHeight + 10) - bottomHeight;

            // Bu hesaplanan kenarlýk mesafeleri düþülünce bulunan yeni sayfa biçimi...
            PageFormat darSayfaBiçimi = new PageFormat() {
                public double getImageableX() {return tableX;}
                public double getImageableY() {return tableY;}
                public double getImageableWidth() {return tableW;}
                public double getImageableHeight() {return tableH;}
            }; // Pag.. ifadesi sonu...

            // Tablonun yazdýrýlacaðý grafik kopyasý...
            Graphics grafikKopyasý = grafik.create();

            // Tabloyu hesaplý dar yalýn alana yazdýralým...
            int dönüþDeðeri = varsayýlýYazdýrma.print (grafikKopyasý, darSayfaBiçimi, sayfaEndeksi);

            // Yazdýrýlacak sayfalar bimiþse dönelim...
            if (dönüþDeðeri == NO_SUCH_PAGE) return dönüþDeðeri;

            // Grafik kopyasýyla iþimiz kalmadý...
            grafikKopyasý.dispose();

            // Tablo yazdýrýmý sonrasý kenarlýk resimlerini yazdýrma sýrasý geldi...
            int leftEnd = ix + leftWidth;
            int üstOrtaStart = ix + (int)((iw - üstOrta.getWidth()) / 2.0f);
            int üstOrtaEnd = üstOrtaStart + üstOrta.getWidth();
            int rightStart = ix + iw - rightWidth;
            
            // Kenarlýk resimlerini konduralým...
            grafik.drawImage (üstSol, ix, iy, null);
            grafik.drawImage (üst, leftEnd, iy, üstOrtaStart - leftEnd, topHeight, null);
            grafik.drawImage (üst, üstOrtaEnd, iy, rightStart - üstOrtaEnd, topHeight, null);
            grafik.drawImage (üstSað, rightStart, iy, null);
            grafik.drawImage (üstOrta, üstOrtaStart, iy, null);
            int baþlýkStart = ix + (int)((iw - baþlýk.getWidth()) / 2.0f);
            grafik.drawImage (baþlýk, baþlýkStart, iy + topHeight + 10, null);
            int bottomY = iy + ih - bottomHeight;
            grafik.drawImage (altSol, ix, bottomY, null);
            grafik.drawImage (altSað, rightStart, bottomY, null);
            grafik.drawImage (alt, leftEnd, bottomY, rightStart - leftEnd, bottomHeight, null);
            grafik.drawImage (sol, ix, iy + topHeight, leftWidth, bottomY - iy - topHeight, null);
            grafik.drawImage (sað, rightStart, iy + topHeight, rightWidth, bottomY - iy - topHeight, null);

            return PAGE_EXISTS;
        } // paint(..) hazýr metodu sonu...
    } // SüslüYazdýr sýnýfý sonu...

    public static void main (final String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", false);
                try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
                }catch (Exception ist) {}
                new J5e_17().setVisible (true); // Kurucuyu çaðýrýr...
        }}); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_17 sýnýfý sonu...