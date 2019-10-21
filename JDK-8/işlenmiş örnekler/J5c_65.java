// J5c_65.java: TableRenderDemo (TabloSunuG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/* Bu program da J5c_60.java=TableDemo.java gibidir; fark�
 * bunda kolon geni�liklerine haricen ilkde�er verilmesi ve Mezuniyet
 * kolonunun de�i�iklik i�in a��l�r kutu se�enekleri sunmas�d�r.
 */
public class J5c_65 extends JPanel {
    private static boolean yaz�ls�nM� = false;

    public J5c_65() {// Kurucu...
        super (new GridLayout (1,0));

        JTable tablo = new JTable (new Tablom());
        tablo.setPreferredScrollableViewportSize (new Dimension (1100, 80));
        tablo.setFillsViewportHeight (true);
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // Tablo kolon geni�liklerine ilk de�er verelim...
        kolonEbatlar�n��lkde�erle (tablo);

        // 7.Mezuniyet kolonu edit�r�n�/d�zenlemesini a��l�r kutuya ba�layal�m...
        mezuniyetKolonunuKur (tablo, tablo.getColumnModel().getColumn (7));

        // Tablolu kayd�rma panosunu yarat�p i�erik panosuna ekleyelim...
        JScrollPane kayd�rmaPanosu = new JScrollPane (tablo);
        add (kayd�rmaPanosu);
    } // J5c_65() kurucusu sonu...

    /* Bu metod mevcut kolon ba�l�k geni�li�i ve h�cre geni�li�inin b�y�k olan�n�
     * kolon geni�li�i olarak kabul eder. T�m kolon ba�l�klar� h�cre de�erlerinden
     * b�y�kse, o zaman k�saca "column.sizeWidthToFit()" yazabilirsiniz.
     */
    private void kolonEbatlar�n��lkde�erle (JTable tablo) {
        Tablom tabloModelim = (Tablom)tablo.getModel();
        TableColumn tabloKolonu = null;
        Component komponent = null;
        int ba�l�kGeni�li�i = 0;
        int h�creGeni�li�i = 0;
        Object[] uzunDe�erler = tabloModelim.uzunDe�erler;
        TableCellRenderer ba�l�kSunucusu = tablo.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 8; i++) {
            tabloKolonu = tablo.getColumnModel().getColumn (i);

            komponent = ba�l�kSunucusu.getTableCellRendererComponent (
                    null, tabloKolonu.getHeaderValue(), false, false, 0, 0);
            ba�l�kGeni�li�i = komponent.getPreferredSize().width;

            komponent = tablo.getDefaultRenderer (
                    tabloModelim.getColumnClass (i)).getTableCellRendererComponent (
                            tablo, uzunDe�erler[i], false, false, 0, i);
            h�creGeni�li�i = komponent.getPreferredSize().width;

            if (yaz�ls�nM�) System.out.println ("Tablo'nun " + i + ".kolon geni�li�i ilk de�eri olarak: B�y��� (Ba�l�k Geni�li�i = " + ba�l�kGeni�li�i +
                    ", H�cre Geni�li�i = " + h�creGeni�li�i + ") al�nacakt�r.");

            tabloKolonu.setPreferredWidth (Math.max (ba�l�kGeni�li�i, h�creGeni�li�i));
        } // for d�ng�s� sonu...
    } // kolonEbatlar�n��lkde�erle(..) metodu sonu...

    public void mezuniyetKolonunuKur (JTable tablo, TableColumn mezuniyetKolonu) {
        // Mezuniyet h�cre i�erik de�i�ikli�i i�in a��l�r kutulu edit�r� kural�m...
        JComboBox a��l�rKutu = new JComboBox();
        a��l�rKutu.addItem ("Okuma-Yazma Yok");
        a��l�rKutu.addItem ("Halk E�itim");
        a��l�rKutu.addItem ("�lkokul");
        a��l�rKutu.addItem ("Ortaokul");
        a��l�rKutu.addItem ("Lise");
        a��l�rKutu.addItem ("�niversite");
        a��l�rKutu.addItem ("Master-Doktora ");
        mezuniyetKolonu.setCellEditor (new DefaultCellEditor (a��l�rKutu));

        // Mezuniyet h�creleri i�in ipucu verelim...
        DefaultTableCellRenderer sunucu = new DefaultTableCellRenderer();
        sunucu.setToolTipText ("A��l�r kutu se�enekleri i�in t�klay�n");
        mezuniyetKolonu.setCellRenderer (sunucu);
    } // mezuniyetKolonunuKur(..) metodu sonu...

    class Tablom extends AbstractTableModel {
        private String[] tabloBa�l�klar� = {"TC No'su", "Ad� ve Soyad�", "�kamet �ehri", "Do�um Tarihi", "Vefat Tarihi", "2018'de Ya��", "Evli Mi", "Mezuniyet"};
        Object[][] tabloVerileri = {
            {new Long ("43882313080"), "Han�m Amanat Yava�", "Ye�ilyurt", "1 Ekim 1932", "4 May�s 2014", new Integer (82), new Boolean (true), "Halke�itim"},
            {new Long ("43888312872"), "Memet Yava�", "Ye�ilyurt", "1 Mart 1933", "30 Mart 2018", new Integer (85), new Boolean (true), "�lkokul"},
            {new Long ("18626504192"), "Hatice Yava� Ka�ar", "Bursa", "1 Mart 1953", "Yok", new Integer (65), new Boolean (true), "�lkokul"},
            {new Long ("21290066668"), "S�heyla Yava� �zbay", "Yak�nca", "10 Mart 1954", "Yok", new Integer (64), new Boolean (true), "�lkokul"},
            {new Long ("13619672094"), "Zeliha Yava� Candan", "Antalya", "7 A�ustos 1954", "Yok", new Integer (64), new Boolean (true), "�lkokul"},
            {new Long ("43879313154"), "Mahmut Nihat Yava�", "Mersin", "17 Nisan 1957", "Yok", new Integer (61), new Boolean (false), "�niversite"},
            {new Long ("14270300692"), "Song�l Yava� G�kt�rk", "Malatya", "14 May�s 1959", "Yok", new Integer (59), new Boolean (true), "�niversite"},
            {new Long ("43876313218"), "Mustafa Nedim Yava�", "Bursa", "27 Nisan 1961", "Yok", new Integer (57), new Boolean (false), "�niversite"},
            {new Long ("43873313372"), "Sevim Yava�", "Bursa", "1 A�ustos 1963", "Yok", new Integer (55), new Boolean (false), "�niversite"}
        }; // Obj.. ifadesi sonu...

        public final Object[] uzunDe�erler = {new Long ("43882313080"),
                "Han�m Amanat Yava�", "Ye�ilyurt", "7 A�ustos 1954",
                "30 Mart 2018", new Integer (85), Boolean.TRUE,
                "Okuma-Yazma Yok"};

        public int getColumnCount() {return tabloBa�l�klar�.length;}
        public int getRowCount() {return tabloVerileri.length;}
        public String getColumnName (int col) {return tabloBa�l�klar�[col];}
        public Object getValueAt (int row, int col) {return tabloVerileri[row][col];}

        // Bu metod "Evli Mi" (boolean) verilerini true/false'dan �entikli/�entiksiz kutuya �evirir...
        public Class getColumnClass (int c) {return getValueAt (0, c).getClass();}

        // Tablonun ilk 2 kolondan sonrakiler de�i�tirilebilir ise bu metod i�letilir...
        public boolean isCellEditable (int sat�r, int kolon) {
            if (kolon < 2) return false;
            else return true;
        } // isCellEditable(..) haz�r metodu sonu...

        // Tablo verileri de�i�tirilebilir ise bu metod kullan�labilir..
        public void setValueAt (Object de�er, int sat�r, int kolon) {
            if (yaz�ls�nM�) System.out.println ("Tablo(" + sat�r + "," + kolon + ")" +
                    "'n�n yeni de�eri:[" + de�er +
                    "]==>(" + de�er.getClass() + ")'�n bir tiplemesi\n");

            tabloVerileri[sat�r][kolon] = de�er;
            fireTableCellUpdated (sat�r, kolon);
            if (yaz�ls�nM�) {System.out.println ("YEN� DE���EN VER�LER==>");
                tabloVerileriniYaz();
            } // if karar� sonu...
        } // setValueAt(..) haz�r metodu sonu...

        private void tabloVerileriniYaz() {
            int sat�rSay�s� = getRowCount();
            int s�tunSay�s� = getColumnCount();

            System.out.print ("S�raNo ");
            for (int j=0; j < s�tunSay�s�; j++) System.out.print (tabloBa�l�klar�[j] + "  ");
            System.out.println ("\n======================================================================================================");
            for (int i=0; i < sat�rSay�s�; i++) {
                System.out.print ((i+1) + ".sat�r:");
                for (int j=0; j < s�tunSay�s�; j++) System.out.print ("  " + getValueAt (i, j));
                System.out.println();
            } // d��-for d�ng�s� sonu...
            System.out.println ("======================================================================================================");
        } // tabloVerileriniYaz() metodu sonu...
    } // Tablom s�n�f� sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Tablo Takdim G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_65 yeni��erikPanosu = new J5c_65(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (100,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        yaz�ls�nM� = args.length > 0? true : false;
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_65 s�n�f� sonu...

/* ��kt�:
**  >java J5c_65 yaz  **
Tablo'nun 0.kolon geni�li�i ilk de�eri olarak: B�y��� (Ba�l�k Geni�li�i = 52, H�cre Geni�li�i = 79) al�nacakt�r.
Tablo'nun 1.kolon geni�li�i ilk de�eri olarak: B�y��� (Ba�l�k Geni�li�i = 77, H�cre Geni�li�i = 120) al�nacakt�r.
Tablo'nun 2.kolon geni�li�i ilk de�eri olarak: B�y��� (Ba�l�k Geni�li�i = 74, H�cre Geni�li�i = 48) al�nacakt�r.
Tablo'nun 3.kolon geni�li�i ilk de�eri olarak: B�y��� (Ba�l�k Geni�li�i = 77, H�cre Geni�li�i = 88) al�nacakt�r.
Tablo'nun 4.kolon geni�li�i ilk de�eri olarak: B�y��� (Ba�l�k Geni�li�i = 67, H�cre Geni�li�i = 73) al�nacakt�r.
Tablo'nun 5.kolon geni�li�i ilk de�eri olarak: B�y��� (Ba�l�k Geni�li�i = 74, H�cre Geni�li�i = 16) al�nacakt�r.
Tablo'nun 6.kolon geni�li�i ilk de�eri olarak: B�y��� (Ba�l�k Geni�li�i = 37, H�cre Geni�li�i = 15) al�nacakt�r.
Tablo'nun 7.kolon geni�li�i ilk de�eri olarak: B�y��� (Ba�l�k Geni�li�i = 59, H�cre Geni�li�i = 106) al�nacakt�r.

Tablo(0,7)'n�n yeni de�eri:[Master-Doktora]==>(class java.lang.String)'�n bir tiplemesi

YEN� DE���EN VER�LER==>
S�raNo TC No'su  Ad� ve Soyad�  �kamet �ehri  Do�um Tarihi  Vefat Tarihi  2018'de Ya��  Evli Mi  Mezuniyet
======================================================================================================
1.sat�r:  43882313080  Han�m Amanat Yava�  Ye�ilyurt  1 Ekim 1932  4 May�s 2014 82  true  Master-Doktora
2.sat�r:  43888312872  Memet Yava�  Ye�ilyurt  1 Mart 1933  30 Mart 2018  85  true  �lkokul
3.sat�r:  18626504192  Hatice Yava� Ka�ar  Bursa  1 Mart 1953  Yok  65  true  �lkokul
4.sat�r:  21290066668  S�heyla Yava� �zbay  Yak�nca  10 Mart 1954  Yok  64  true  �lkokul
5.sat�r:  13619672094  Zeliha Yava� Candan  Antalya  7 A�ustos 1954  Yok  64  true  �lkokul
6.sat�r:  43879313154  Mahmut Nihat Yava�  Mersin  17 Nisan 1957  Yok  61  false  �niversite
7.sat�r:  14270300692  Song�l Yava� G�kt�rk  Malatya  14 May�s 1959  Yok  59  true  �niversite
8.sat�r:  43876313218  Mustafa Nedim Yava�  Bursa  27 Nisan 1961  Yok  57  false  �niversite
9.sat�r:  43873313372  Sevim Yava�  Bursa  1 A�ustos 1963  Yok  55  false  �niversite
======================================================================================================
*/