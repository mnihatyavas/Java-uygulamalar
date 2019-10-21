// J5c_61x1.java: ColorRenderer (RenkSunucu) alt-örneği.

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class J5c_61x1 extends JLabel implements TableCellRenderer {
    Border seçilmemişSınır = null;
    Border seçilmişSınır = null;
    boolean sınırlıMı = true;

    public J5c_61x1(boolean sınırlıMı) {this.sınırlıMı = sınırlıMı;setOpaque (true);} // Kurucu...

    public Component getTableCellRendererComponent (JTable tablo,
            Object renk, boolean seçiliMi, boolean odaklıMı, int satır, int kolon) {
        Color yeniRenk = (Color)renk;
        setBackground (yeniRenk);
        if (sınırlıMı) {
            if (seçiliMi) {
                if (seçilmişSınır == null) seçilmişSınır = BorderFactory.createMatteBorder (2,5,2,5, tablo.getSelectionBackground());
                setBorder (seçilmişSınır);
            }else {
                if (seçilmemişSınır == null) seçilmemişSınır = BorderFactory.createMatteBorder (2,5,2,5, tablo.getBackground());
                setBorder (seçilmemişSınır);
            } // else kararı sonu...
        } // dış-if kararı sonu...
        
        setToolTipText ("RGB değeri: (" + yeniRenk.getRed() + "," + yeniRenk.getGreen() + "," + yeniRenk.getBlue() + ")");
        return this;
    } // getTableCellRendererComponent(..) hazır metodu sonu...
} // J5c_61x1 sınıfı sonu...