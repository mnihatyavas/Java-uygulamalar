// J5e_17.java: TablePrintDemo3 (TabloYazd�rmaG�sterisi3) �rne�i.

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
 *     resim/tablo/ge�ti.png
 *     resim/tablo/kald�.png
 *
 *     resim/tablo/ge�ti-SB.png
 *     resim/tablo/kald�-SB.png
 *
 *     resim/tablo/�stOrta.png
 *     resim/tablo/�st.png
 *     resim/tablo/alt.png
 *     resim/tablo/�stSol.png
 *     resim/tablo/�stSa�.png
 *     resim/tablo/altSol.png
 *     resim/tablo/altSa�.png
 *     resim/tablo/sol.png
 *     resim/tablo/sa�.png
 *     resim/tablo/ba�l�k.png
 *
 * Tablo yazd�r�m�nda ilkinden farkl� olarak, ikincinin siyah/beyaz ge�ti/kald�
 * ve ayr�ca bu program�n kenarl�k resimlerini kullanmaktad�r.
 */
public class J5e_17 extends J5e_16 {
    public J5e_17() {// Kurucu...
        setTitle ("Tablo Yazd�rma G�sterisi 3");

        // A�a��daki de�i�ken etkilerini kapat�l�p yerine kendimizinkileri kuraca��z...
        String alet�pucuMetni = "Etkisizle�tirildi - Bu program kendi �zel Sayfaba��/Sayfasonu de�erlerini kullan�yor.";
        sayfaBa��Kutusu.setEnabled (false);
        sayfaBa��Kutusu.setSelected (false);
        sayfaBa��Kutusu.setToolTipText (alet�pucuMetni);
        sayfaBa��MetinSat�r�.setEnabled (false);
        sayfaBa��MetinSat�r�.setText ("<�zel>");
        sayfaBa��MetinSat�r�.setToolTipText (alet�pucuMetni);
        sayfaSonuKutusu.setEnabled (false);
        sayfaSonuKutusu.setSelected (false);
        sayfaSonuKutusu.setToolTipText (alet�pucuMetni);
        sayfaSonuMetinSat�r�.setEnabled (false);
        sayfaSonuMetinSat�r�.setText ("<�zel>");
        sayfaSonuMetinSat�r�.setToolTipText (alet�pucuMetni);
    } // J5e_17() kurucusu sonu...

    // �nceki esge�ilecek...
    protected JTable tabloyuYarat (TableModel model) {return new S�sl�TabloYaz (model);}

    private static class S�sl�TabloYaz extends JTable {
        public S�sl�TabloYaz (TableModel model) {super (model);} // Kurucu...

        // Esge�en metod. Kendi ba�l�k ve sonlu�unu kullan�r: Sayfa no daima sonluk'tad�r...
        public Printable getPrintable (
                PrintMode yazd�rmaKipi,
                MessageFormat ba�l�kBi�imi,
                MessageFormat sonlukBi�imi) {

            MessageFormat sayfaNo = new MessageFormat ("- {0} -");

            // Varsay�l� yazd�r�labiliri alal�m...
            Printable varsay�l�Yazd�rma = super.getPrintable (yazd�rmaKipi, null, sayfaNo);

            // Varsay�l�ya kenarl�k s�slemeleri saracak yeni bir s�sl� yazd�r�labilir d�nd�relim...
            return new S�sl�Yazd�r (varsay�l�Yazd�rma);
        } // getPrintable(..) haz�r metodu sonu...
    } // S�sl�TabloYaz s�n�f� sonu...

    private static class S�sl�Yazd�r implements Printable {
        private Printable varsay�l�Yazd�rma;

        // Tablonun �evresini saracak kenarl�k resim de�i�kenlerini tan�mlayal�m...
        private BufferedImage �stOrta;
        private BufferedImage �st;
        private BufferedImage alt;
        private BufferedImage �stSol;
        private BufferedImage �stSa�;
        private BufferedImage sol;
        private BufferedImage sa�;
        private BufferedImage altSol;
        private BufferedImage altSa�;
        private BufferedImage ba�l�k;        

        boolean resimlerY�klendiMi;

        // Resimleri derli-toplu �zel bir {} blok i�inde ve try-catch kontrollu y�kleyelim...
        {// Resim y�kleme blo�u...
            try {
                �stSol = ImageIO.read (getClass().getResource ("resim/Tablo/�stSol.png"));
                �st = ImageIO.read (getClass().getResource ("resim/Tablo/�st.png"));
                �stOrta = ImageIO.read (getClass().getResource ("resim/Tablo/�stOrta.png"));
                �stSa� = ImageIO.read (getClass().getResource ("resim/Tablo/�stSa�.png"));
                altSol = ImageIO.read (getClass().getResource ("resim/Tablo/altSol.png"));
                alt = ImageIO.read (getClass().getResource ("resim/Tablo/alt.png"));
                altSa� = ImageIO.read (getClass().getResource ("resim/Tablo/altSa�.png"));
                sol = ImageIO.read (getClass().getResource ("resim/Tablo/sol.png"));
                sa� = ImageIO.read (getClass().getResource ("resim/Tablo/sa�.png"));
                ba�l�k = ImageIO.read (getClass().getResource ("resim/Tablo/ba�l�k.png"));

                resimlerY�klendiMi = true;
            }catch (IOException ist) {resimlerY�klendiMi = false;
            } // try-catch blo�u sonu...
        } // Resim y�kleme blo�u sonu...

        public S�sl�Yazd�r (Printable varsay�l�Yazd�rma) {this.varsay�l�Yazd�rma = varsay�l�Yazd�rma;} // Kurucu...

        public int print (Graphics grafik, final PageFormat sayfaBi�imi, int sayfaEndeksi)
                throws PrinterException {

            // Kenarl�k resimleri y�klenemediyse, varsay�l�y� kenarl�ks�z yazd�r�lmak �zere d�nd�relim...
            if (!resimlerY�klendiMi) {return varsay�l�Yazd�rma.print (grafik, sayfaBi�imi, sayfaEndeksi);}

            // Kenarl�k resminin sol �st konumu...
            int ix = (int)sayfaBi�imi.getImageableX();
            int iy = (int)sayfaBi�imi.getImageableY();

            // Kenarl�kl� alan�n eni/boyu...
            int iw = (int)sayfaBi�imi.getImageableWidth();
            int ih = (int)sayfaBi�imi.getImageableHeight();

            // Sol kenarl�k resminin geni�li�i...
            int leftWidth = sol.getWidth();
            
            // Sa� kenarl�k resminin geni�li�i...
            int rightWidth = sa�.getWidth();

            // �st kenarl�k resminin y�ksekli�i...
            int topHeight = �st.getHeight();

            // Alt kenarl�k resminin y�ksekli�i...
            int bottomHeight = alt.getHeight();
            
            // �st not ba�l��� resminin y�ksekli�i...
            int ba�l�kHeight = ba�l�k.getHeight();

            // Sol-sa� kenarl�kla tablo aras� mesafe...
            final int tableX = ix + leftWidth + 10;
            final int tableW = iw - (leftWidth + 10) - (rightWidth + 10);

            // Tablo �st� + 10 + not ba�l��� + 10 + kenarl�k �st resmi...
            final int tableY = iy + topHeight + 10 + ba�l�kHeight + 10;

            // Tablo alt� + 10 + kenarl�k alt�...
            final int tableH = ih - (topHeight + 10) - (ba�l�kHeight + 10) - bottomHeight;

            // Bu hesaplanan kenarl�k mesafeleri d���l�nce bulunan yeni sayfa bi�imi...
            PageFormat darSayfaBi�imi = new PageFormat() {
                public double getImageableX() {return tableX;}
                public double getImageableY() {return tableY;}
                public double getImageableWidth() {return tableW;}
                public double getImageableHeight() {return tableH;}
            }; // Pag.. ifadesi sonu...

            // Tablonun yazd�r�laca�� grafik kopyas�...
            Graphics grafikKopyas� = grafik.create();

            // Tabloyu hesapl� dar yal�n alana yazd�ral�m...
            int d�n��De�eri = varsay�l�Yazd�rma.print (grafikKopyas�, darSayfaBi�imi, sayfaEndeksi);

            // Yazd�r�lacak sayfalar bimi�se d�nelim...
            if (d�n��De�eri == NO_SUCH_PAGE) return d�n��De�eri;

            // Grafik kopyas�yla i�imiz kalmad�...
            grafikKopyas�.dispose();

            // Tablo yazd�r�m� sonras� kenarl�k resimlerini yazd�rma s�ras� geldi...
            int leftEnd = ix + leftWidth;
            int �stOrtaStart = ix + (int)((iw - �stOrta.getWidth()) / 2.0f);
            int �stOrtaEnd = �stOrtaStart + �stOrta.getWidth();
            int rightStart = ix + iw - rightWidth;
            
            // Kenarl�k resimlerini kondural�m...
            grafik.drawImage (�stSol, ix, iy, null);
            grafik.drawImage (�st, leftEnd, iy, �stOrtaStart - leftEnd, topHeight, null);
            grafik.drawImage (�st, �stOrtaEnd, iy, rightStart - �stOrtaEnd, topHeight, null);
            grafik.drawImage (�stSa�, rightStart, iy, null);
            grafik.drawImage (�stOrta, �stOrtaStart, iy, null);
            int ba�l�kStart = ix + (int)((iw - ba�l�k.getWidth()) / 2.0f);
            grafik.drawImage (ba�l�k, ba�l�kStart, iy + topHeight + 10, null);
            int bottomY = iy + ih - bottomHeight;
            grafik.drawImage (altSol, ix, bottomY, null);
            grafik.drawImage (altSa�, rightStart, bottomY, null);
            grafik.drawImage (alt, leftEnd, bottomY, rightStart - leftEnd, bottomHeight, null);
            grafik.drawImage (sol, ix, iy + topHeight, leftWidth, bottomY - iy - topHeight, null);
            grafik.drawImage (sa�, rightStart, iy + topHeight, rightWidth, bottomY - iy - topHeight, null);

            return PAGE_EXISTS;
        } // paint(..) haz�r metodu sonu...
    } // S�sl�Yazd�r s�n�f� sonu...

    public static void main (final String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", false);
                try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
                }catch (Exception ist) {}
                new J5e_17().setVisible (true); // Kurucuyu �a��r�r...
        }}); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_17 s�n�f� sonu...