// J5c_61x1.java: ColorRenderer (RenkSunucu) alt-�rne�i.

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class J5c_61x1 extends JLabel implements TableCellRenderer {
    Border se�ilmemi�S�n�r = null;
    Border se�ilmi�S�n�r = null;
    boolean s�n�rl�M� = true;

    public J5c_61x1(boolean s�n�rl�M�) {this.s�n�rl�M� = s�n�rl�M�;setOpaque (true);} // Kurucu...

    public Component getTableCellRendererComponent (JTable tablo,
            Object renk, boolean se�iliMi, boolean odakl�M�, int sat�r, int kolon) {
        Color yeniRenk = (Color)renk;
        setBackground (yeniRenk);
        if (s�n�rl�M�) {
            if (se�iliMi) {
                if (se�ilmi�S�n�r == null) se�ilmi�S�n�r = BorderFactory.createMatteBorder (2,5,2,5, tablo.getSelectionBackground());
                setBorder (se�ilmi�S�n�r);
            }else {
                if (se�ilmemi�S�n�r == null) se�ilmemi�S�n�r = BorderFactory.createMatteBorder (2,5,2,5, tablo.getBackground());
                setBorder (se�ilmemi�S�n�r);
            } // else karar� sonu...
        } // d��-if karar� sonu...
        
        setToolTipText ("RGB de�eri: (" + yeniRenk.getRed() + "," + yeniRenk.getGreen() + "," + yeniRenk.getBlue() + ")");
        return this;
    } // getTableCellRendererComponent(..) haz�r metodu sonu...
} // J5c_61x1 s�n�f� sonu...