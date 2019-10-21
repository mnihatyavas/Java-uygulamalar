// J5e_16.java: TablePrintDemo2 (TabloYazd�rmaG�sterisi2) �rne�i.

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/* Gereken dosyalar:
 *  J5e_15.java=TablePrintDemo1.java
 *  resim/tablo/ge�ti.png
 *  resim/tablo/kald�.png
 *
 *  resim/tablo/ge�ti-SB.png
 *  resim/tablo/kald�-SB.png
 *
 * �nceki programdan fark�, yazd�rma esnas�nda (ekran tablo g�r�nt�s�nde
 * de�il) kendi siyah/beyaz ge�ti/kald� ikonlar�n� kullanmas�d�r.
 */
public class J5e_16 extends J5e_15 {
    private static final ImageIcon ge�ti�konuSB = new ImageIcon (J5e_15.class.getResource ("resim/tablo/ge�ti-SB.png"));
    private static final ImageIcon kald��konuSB = new ImageIcon (J5e_15.class.getResource ("resim/tablo/kald�-SB.png"));

    public J5e_16() {setTitle ("Tablo Yazd�rma G�sterisi 2");} // Kurucu...

    // Kendi SBDurumKolonuSunucusu s�n�f�n� kullanmak �zere �ncekini esge�er...
    protected TableCellRenderer durumSunucusunuYarat() {return new SBDurumKolonuSunucusu();}

    protected static class SBDurumKolonuSunucusu extends DurumKolonuSunucusu {
        public Component getTableCellRendererComponent (
                JTable tablo, Object de�er, boolean se�iliMi, boolean odakl�M�, int sat�r, int kolon) {
            super.getTableCellRendererComponent (tablo, de�er, se�iliMi, odakl�M�, sat�r, kolon);

            // Tablo yazd�r�l�rken (tablo.isPaintingForPrint) kendi siyah/beyaz ikonlar�n� kullanacak...
            if (tablo.isPaintingForPrint()) {boolean durumu = (Boolean)de�er;
                setIcon (durumu ? ge�ti�konuSB : kald��konuSB);
            } // Ekran tablo g�r�nt�s�nde ise �nceki renkli ikonlar� aynen kal�yor...

            return this;
        } // getTableCellRendererComponent(..) haz�r metodu sonu...
    } // SBDurumKolonuSunucusu s�n�f� sonu...

    public static void main (final String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", false);
                try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
                }catch (Exception ist) {}
                new J5e_16().setVisible (true); // Kurucuyu �a��r�r...
            } // run() haz�r metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_16 s�n�f� sonu...