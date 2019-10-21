// J5c_61x2.java: ColorEditor (RenkYay�mc�s�) alt-�rne�i.

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
    Color �imdikiRenk;
    JButton renkSe�enD��me;
    JColorChooser renkSe�ici;
    JDialog diyalog;
    protected static final String YAYIM = "yay�mla";

    public J5c_61x2() {// Kurucu...
        // Yarat�lan renk d��mesi renk se�ici diyalo�unu getirir...
        renkSe�enD��me = new JButton();
        renkSe�enD��me.setActionCommand (YAYIM);
        renkSe�enD��me.addActionListener (this); // D��meyi dinleyiciye duyarlayal�m...
        renkSe�enD��me.setBorderPainted (true);

        // ColorChooser/RenkSe�ici diyalo�u kural�m...
        renkSe�ici = new JColorChooser();
        diyalog = JColorChooser.createDialog (
                renkSe�enD��me,
                "Bir Renk Se�in",
                true,
                renkSe�ici,
                this, // OK d��mesi y�netimi...
                null); // CANCEL d��mesi y�netimi...
    } // J5c_61x2() kurucusu sonu...

    // Renk se�en d��meye ve diyalo�un OK d��mesine duyarl�d�r...
    public void actionPerformed (ActionEvent olay) {
        if (YAYIM.equals (olay.getActionCommand())) {// Renk (d��mesi) t�kland�...
            renkSe�enD��me.setBackground (�imdikiRenk);
            renkSe�ici.setColor (�imdikiRenk);
            diyalog.setVisible (true);

            // Se�ilen yeni renkle, sunucu tekrar g�r�nt�lensin...
            fireEditingStopped();
        } else // OK d��mesi t�kland�...
            �imdikiRenk = renkSe�ici.getColor();
    } // actionPerformed(..) haz�r metodu sonu...

    public Object getCellEditorValue() {return �imdikiRenk;}

    public Component getTableCellEditorComponent (
            JTable tablo,
            Object renk,
            boolean se�ildiMi,
            int sat�r,
            int kolon) {
        �imdikiRenk = (Color)renk;
        return renkSe�enD��me;
    } // getTableCellEditorComponent(..) haz�r metodu sonu...
} // J5c_61x2 s�n�f� sonu...

/* ��kt�:
**  >java J5c_61  **
Tablo(0,8)'n�n yeni de�eri:[java.awt.Color[r=255,g=255,b=255]]==>(class java.awt.Color)'�n bir tiplemesi

YEN� DE���EN VER�LER==>
S�raNo TC No'su  Ad� ve Soyad�  �kamet �ehri  Do�um Tarihi  Vefat Tarihi  2018'de Ya��  Evli Mi  Mezuniyet  Favori Rengi
======================================================================================================
1.sat�r:  43882313080  Han�m Amanat Yava�  Ye�ilyurt  1 Ekim 1932  4 May�s 2014 82  true  Yok  java.awt.Color[r=255,g=255,b=255]
2.sat�r:  43888312872  Memet Yava�  Ye�ilyurt  1 Mart 1933  30 Mart 2018  85  true  �lkokul  java.awt.Color[r=255,g=0,b=0]
3.sat�r:  18626504192  Hatice Yava� Ka�ar  Bursa  1 Mart 1953  Yok  65  true  �lkokul  java.awt.Color[r=255,g=255,b=0]
4.sat�r:  21290066668  S�heyla Yava� �zbay  Yak�nca  10 Mart 1954  Yok  64  true  �lkokul  java.awt.Color[r=255,g=175,b=175]
5.sat�r:  13619672094  Zeliha Yava� Candan  Antalya  7 A�ustos 1954  Yok  64  true  �lkokul  java.awt.Color[r=255,g=200,b=0]
6.sat�r:  43879313154  Mahmut Nihat Yava�  Mersin  17 Nisan 1957  Yok  61  false  �niversite  java.awt.Color[r=255,g=0,b=255]
7.sat�r:  14270300692  Song�l Yava� G�kt�rk  Malatya  14 May�s 1959  Yok  59  true  �niversite  java.awt.Color[r=0,g=255,b=0]
8.sat�r:  43876313218  Mustafa Nedim Yava�  Bursa  27 Nisan 1961  Yok  57  false  �niversite  java.awt.Color[r=0,g=255,b=255]
9.sat�r:  43873313372  Sevim Yava�  Bursa  1 A�ustos 1963  Yok  55  false  �niversite  java.awt.Color[r=0,g=0,b=255]
======================================================================================================
*/