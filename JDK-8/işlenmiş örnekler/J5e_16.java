// J5e_16.java: TablePrintDemo2 (TabloYazdýrmaGösterisi2) örneði.

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/* Gereken dosyalar:
 *  J5e_15.java=TablePrintDemo1.java
 *  resim/tablo/geçti.png
 *  resim/tablo/kaldý.png
 *
 *  resim/tablo/geçti-SB.png
 *  resim/tablo/kaldý-SB.png
 *
 * Önceki programdan farký, yazdýrma esnasýnda (ekran tablo görüntüsünde
 * deðil) kendi siyah/beyaz geçti/kaldý ikonlarýný kullanmasýdýr.
 */
public class J5e_16 extends J5e_15 {
    private static final ImageIcon geçtiÝkonuSB = new ImageIcon (J5e_15.class.getResource ("resim/tablo/geçti-SB.png"));
    private static final ImageIcon kaldýÝkonuSB = new ImageIcon (J5e_15.class.getResource ("resim/tablo/kaldý-SB.png"));

    public J5e_16() {setTitle ("Tablo Yazdýrma Gösterisi 2");} // Kurucu...

    // Kendi SBDurumKolonuSunucusu sýnýfýný kullanmak üzere öncekini esgeçer...
    protected TableCellRenderer durumSunucusunuYarat() {return new SBDurumKolonuSunucusu();}

    protected static class SBDurumKolonuSunucusu extends DurumKolonuSunucusu {
        public Component getTableCellRendererComponent (
                JTable tablo, Object deðer, boolean seçiliMi, boolean odaklýMý, int satýr, int kolon) {
            super.getTableCellRendererComponent (tablo, deðer, seçiliMi, odaklýMý, satýr, kolon);

            // Tablo yazdýrýlýrken (tablo.isPaintingForPrint) kendi siyah/beyaz ikonlarýný kullanacak...
            if (tablo.isPaintingForPrint()) {boolean durumu = (Boolean)deðer;
                setIcon (durumu ? geçtiÝkonuSB : kaldýÝkonuSB);
            } // Ekran tablo görüntüsünde ise önceki renkli ikonlarý aynen kalýyor...

            return this;
        } // getTableCellRendererComponent(..) hazýr metodu sonu...
    } // SBDurumKolonuSunucusu sýnýfý sonu...

    public static void main (final String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", false);
                try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
                }catch (Exception ist) {}
                new J5e_16().setVisible (true); // Kurucuyu çaðýrýr...
            } // run() hazýr metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_16 sýnýfý sonu...