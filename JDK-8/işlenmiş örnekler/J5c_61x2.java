// J5c_61x2.java: ColorEditor (RenkYayýmcýsý) alt-örneði.

import java.awt.Color;
import java.awt.Component;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.AbstractCellEditor;

import javax.swing.table.TableCellEditor;

public class J5c_61x2 extends AbstractCellEditor
            implements TableCellEditor, ActionListener {
    Color þimdikiRenk;
    JButton renkSeçenDüðme;
    JColorChooser renkSeçici;
    JDialog diyalog;
    protected static final String YAYIM = "yayýmla";

    public J5c_61x2() {// Kurucu...
        // Yaratýlan renk düðmesi renk seçici diyaloðunu getirir...
        renkSeçenDüðme = new JButton();
        renkSeçenDüðme.setActionCommand (YAYIM);
        renkSeçenDüðme.addActionListener (this); // Düðmeyi dinleyiciye duyarlayalým...
        renkSeçenDüðme.setBorderPainted (true);

        // ColorChooser/RenkSeçici diyaloðu kuralým...
        renkSeçici = new JColorChooser();
        diyalog = JColorChooser.createDialog (
                renkSeçenDüðme,
                "Bir Renk Seçin",
                true,
                renkSeçici,
                this, // OK düðmesi yönetimi...
                null); // CANCEL düðmesi yönetimi...
    } // J5c_61x2() kurucusu sonu...

    // Renk seçen düðmeye ve diyaloðun OK düðmesine duyarlýdýr...
    public void actionPerformed (ActionEvent olay) {
        if (YAYIM.equals (olay.getActionCommand())) {// Renk (düðmesi) týklandý...
            renkSeçenDüðme.setBackground (þimdikiRenk);
            renkSeçici.setColor (þimdikiRenk);
            diyalog.setVisible (true);

            // Seçilen yeni renkle, sunucu tekrar görüntülensin...
            fireEditingStopped();
        } else // OK düðmesi týklandý...
            þimdikiRenk = renkSeçici.getColor();
    } // actionPerformed(..) hazýr metodu sonu...

    public Object getCellEditorValue() {return þimdikiRenk;}

    public Component getTableCellEditorComponent (
            JTable tablo,
            Object renk,
            boolean seçildiMi,
            int satýr,
            int kolon) {
        þimdikiRenk = (Color)renk;
        return renkSeçenDüðme;
    } // getTableCellEditorComponent(..) hazýr metodu sonu...
} // J5c_61x2 sýnýfý sonu...

/* Çýktý:
**  >java J5c_61  **
Tablo(0,8)'nýn yeni deðeri:[java.awt.Color[r=255,g=255,b=255]]==>(class java.awt.Color)'ýn bir tiplemesi

YENÝ DEÐÝÞEN VERÝLER==>
SýraNo TC No'su  Adý ve Soyadý  Ýkamet Þehri  Doðum Tarihi  Vefat Tarihi  2018'de Yaþý  Evli Mi  Mezuniyet  Favori Rengi
======================================================================================================
1.satýr:  43882313080  Haným Amanat Yavaþ  Yeþilyurt  1 Ekim 1932  4 Mayýs 2014 82  true  Yok  java.awt.Color[r=255,g=255,b=255]
2.satýr:  43888312872  Memet Yavaþ  Yeþilyurt  1 Mart 1933  30 Mart 2018  85  true  Ýlkokul  java.awt.Color[r=255,g=0,b=0]
3.satýr:  18626504192  Hatice Yavaþ Kaçar  Bursa  1 Mart 1953  Yok  65  true  Ýlkokul  java.awt.Color[r=255,g=255,b=0]
4.satýr:  21290066668  Süheyla Yavaþ Özbay  Yakýnca  10 Mart 1954  Yok  64  true  Ýlkokul  java.awt.Color[r=255,g=175,b=175]
5.satýr:  13619672094  Zeliha Yavaþ Candan  Antalya  7 Aðustos 1954  Yok  64  true  Ýlkokul  java.awt.Color[r=255,g=200,b=0]
6.satýr:  43879313154  Mahmut Nihat Yavaþ  Mersin  17 Nisan 1957  Yok  61  false  Üniversite  java.awt.Color[r=255,g=0,b=255]
7.satýr:  14270300692  Songül Yavaþ Göktürk  Malatya  14 Mayýs 1959  Yok  59  true  Üniversite  java.awt.Color[r=0,g=255,b=0]
8.satýr:  43876313218  Mustafa Nedim Yavaþ  Bursa  27 Nisan 1961  Yok  57  false  Üniversite  java.awt.Color[r=0,g=255,b=255]
9.satýr:  43873313372  Sevim Yavaþ  Bursa  1 Aðustos 1963  Yok  55  false  Üniversite  java.awt.Color[r=0,g=0,b=255]
======================================================================================================
*/