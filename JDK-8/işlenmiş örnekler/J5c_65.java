// J5c_65.java: TableRenderDemo (TabloSunuGösterisi) örneði.

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

/* Bu program da J5c_60.java=TableDemo.java gibidir; farký
 * bunda kolon geniþliklerine haricen ilkdeðer verilmesi ve Mezuniyet
 * kolonunun deðiþiklik için açýlýr kutu seçenekleri sunmasýdýr.
 */
public class J5c_65 extends JPanel {
    private static boolean yazýlsýnMý = false;

    public J5c_65() {// Kurucu...
        super (new GridLayout (1,0));

        JTable tablo = new JTable (new Tablom());
        tablo.setPreferredScrollableViewportSize (new Dimension (1100, 80));
        tablo.setFillsViewportHeight (true);
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // Tablo kolon geniþliklerine ilk deðer verelim...
        kolonEbatlarýnýÝlkdeðerle (tablo);

        // 7.Mezuniyet kolonu editörünü/düzenlemesini açýlýr kutuya baðlayalým...
        mezuniyetKolonunuKur (tablo, tablo.getColumnModel().getColumn (7));

        // Tablolu kaydýrma panosunu yaratýp içerik panosuna ekleyelim...
        JScrollPane kaydýrmaPanosu = new JScrollPane (tablo);
        add (kaydýrmaPanosu);
    } // J5c_65() kurucusu sonu...

    /* Bu metod mevcut kolon baþlýk geniþliði ve hücre geniþliðinin büyük olanýný
     * kolon geniþliði olarak kabul eder. Tüm kolon baþlýklarý hücre deðerlerinden
     * büyükse, o zaman kýsaca "column.sizeWidthToFit()" yazabilirsiniz.
     */
    private void kolonEbatlarýnýÝlkdeðerle (JTable tablo) {
        Tablom tabloModelim = (Tablom)tablo.getModel();
        TableColumn tabloKolonu = null;
        Component komponent = null;
        int baþlýkGeniþliði = 0;
        int hücreGeniþliði = 0;
        Object[] uzunDeðerler = tabloModelim.uzunDeðerler;
        TableCellRenderer baþlýkSunucusu = tablo.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 8; i++) {
            tabloKolonu = tablo.getColumnModel().getColumn (i);

            komponent = baþlýkSunucusu.getTableCellRendererComponent (
                    null, tabloKolonu.getHeaderValue(), false, false, 0, 0);
            baþlýkGeniþliði = komponent.getPreferredSize().width;

            komponent = tablo.getDefaultRenderer (
                    tabloModelim.getColumnClass (i)).getTableCellRendererComponent (
                            tablo, uzunDeðerler[i], false, false, 0, i);
            hücreGeniþliði = komponent.getPreferredSize().width;

            if (yazýlsýnMý) System.out.println ("Tablo'nun " + i + ".kolon geniþliði ilk deðeri olarak: Büyüðü (Baþlýk Geniþliði = " + baþlýkGeniþliði +
                    ", Hücre Geniþliði = " + hücreGeniþliði + ") alýnacaktýr.");

            tabloKolonu.setPreferredWidth (Math.max (baþlýkGeniþliði, hücreGeniþliði));
        } // for döngüsü sonu...
    } // kolonEbatlarýnýÝlkdeðerle(..) metodu sonu...

    public void mezuniyetKolonunuKur (JTable tablo, TableColumn mezuniyetKolonu) {
        // Mezuniyet hücre içerik deðiþikliði için açýlýr kutulu editörü kuralým...
        JComboBox açýlýrKutu = new JComboBox();
        açýlýrKutu.addItem ("Okuma-Yazma Yok");
        açýlýrKutu.addItem ("Halk Eðitim");
        açýlýrKutu.addItem ("Ýlkokul");
        açýlýrKutu.addItem ("Ortaokul");
        açýlýrKutu.addItem ("Lise");
        açýlýrKutu.addItem ("Üniversite");
        açýlýrKutu.addItem ("Master-Doktora ");
        mezuniyetKolonu.setCellEditor (new DefaultCellEditor (açýlýrKutu));

        // Mezuniyet hücreleri için ipucu verelim...
        DefaultTableCellRenderer sunucu = new DefaultTableCellRenderer();
        sunucu.setToolTipText ("Açýlýr kutu seçenekleri için týklayýn");
        mezuniyetKolonu.setCellRenderer (sunucu);
    } // mezuniyetKolonunuKur(..) metodu sonu...

    class Tablom extends AbstractTableModel {
        private String[] tabloBaþlýklarý = {"TC No'su", "Adý ve Soyadý", "Ýkamet Þehri", "Doðum Tarihi", "Vefat Tarihi", "2018'de Yaþý", "Evli Mi", "Mezuniyet"};
        Object[][] tabloVerileri = {
            {new Long ("43882313080"), "Haným Amanat Yavaþ", "Yeþilyurt", "1 Ekim 1932", "4 Mayýs 2014", new Integer (82), new Boolean (true), "Halkeðitim"},
            {new Long ("43888312872"), "Memet Yavaþ", "Yeþilyurt", "1 Mart 1933", "30 Mart 2018", new Integer (85), new Boolean (true), "Ýlkokul"},
            {new Long ("18626504192"), "Hatice Yavaþ Kaçar", "Bursa", "1 Mart 1953", "Yok", new Integer (65), new Boolean (true), "Ýlkokul"},
            {new Long ("21290066668"), "Süheyla Yavaþ Özbay", "Yakýnca", "10 Mart 1954", "Yok", new Integer (64), new Boolean (true), "Ýlkokul"},
            {new Long ("13619672094"), "Zeliha Yavaþ Candan", "Antalya", "7 Aðustos 1954", "Yok", new Integer (64), new Boolean (true), "Ýlkokul"},
            {new Long ("43879313154"), "Mahmut Nihat Yavaþ", "Mersin", "17 Nisan 1957", "Yok", new Integer (61), new Boolean (false), "Üniversite"},
            {new Long ("14270300692"), "Songül Yavaþ Göktürk", "Malatya", "14 Mayýs 1959", "Yok", new Integer (59), new Boolean (true), "Üniversite"},
            {new Long ("43876313218"), "Mustafa Nedim Yavaþ", "Bursa", "27 Nisan 1961", "Yok", new Integer (57), new Boolean (false), "Üniversite"},
            {new Long ("43873313372"), "Sevim Yavaþ", "Bursa", "1 Aðustos 1963", "Yok", new Integer (55), new Boolean (false), "Üniversite"}
        }; // Obj.. ifadesi sonu...

        public final Object[] uzunDeðerler = {new Long ("43882313080"),
                "Haným Amanat Yavaþ", "Yeþilyurt", "7 Aðustos 1954",
                "30 Mart 2018", new Integer (85), Boolean.TRUE,
                "Okuma-Yazma Yok"};

        public int getColumnCount() {return tabloBaþlýklarý.length;}
        public int getRowCount() {return tabloVerileri.length;}
        public String getColumnName (int col) {return tabloBaþlýklarý[col];}
        public Object getValueAt (int row, int col) {return tabloVerileri[row][col];}

        // Bu metod "Evli Mi" (boolean) verilerini true/false'dan çentikli/çentiksiz kutuya çevirir...
        public Class getColumnClass (int c) {return getValueAt (0, c).getClass();}

        // Tablonun ilk 2 kolondan sonrakiler deðiþtirilebilir ise bu metod iþletilir...
        public boolean isCellEditable (int satýr, int kolon) {
            if (kolon < 2) return false;
            else return true;
        } // isCellEditable(..) hazýr metodu sonu...

        // Tablo verileri deðiþtirilebilir ise bu metod kullanýlabilir..
        public void setValueAt (Object deðer, int satýr, int kolon) {
            if (yazýlsýnMý) System.out.println ("Tablo(" + satýr + "," + kolon + ")" +
                    "'nýn yeni deðeri:[" + deðer +
                    "]==>(" + deðer.getClass() + ")'ýn bir tiplemesi\n");

            tabloVerileri[satýr][kolon] = deðer;
            fireTableCellUpdated (satýr, kolon);
            if (yazýlsýnMý) {System.out.println ("YENÝ DEÐÝÞEN VERÝLER==>");
                tabloVerileriniYaz();
            } // if kararý sonu...
        } // setValueAt(..) hazýr metodu sonu...

        private void tabloVerileriniYaz() {
            int satýrSayýsý = getRowCount();
            int sütunSayýsý = getColumnCount();

            System.out.print ("SýraNo ");
            for (int j=0; j < sütunSayýsý; j++) System.out.print (tabloBaþlýklarý[j] + "  ");
            System.out.println ("\n======================================================================================================");
            for (int i=0; i < satýrSayýsý; i++) {
                System.out.print ((i+1) + ".satýr:");
                for (int j=0; j < sütunSayýsý; j++) System.out.print ("  " + getValueAt (i, j));
                System.out.println();
            } // dýþ-for döngüsü sonu...
            System.out.println ("======================================================================================================");
        } // tabloVerileriniYaz() metodu sonu...
    } // Tablom sýnýfý sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Tablo Takdim Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_65 yeniÝçerikPanosu = new J5c_65(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (100,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        yazýlsýnMý = args.length > 0? true : false;
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_65 sýnýfý sonu...

/* Çýktý:
**  >java J5c_65 yaz  **
Tablo'nun 0.kolon geniþliði ilk deðeri olarak: Büyüðü (Baþlýk Geniþliði = 52, Hücre Geniþliði = 79) alýnacaktýr.
Tablo'nun 1.kolon geniþliði ilk deðeri olarak: Büyüðü (Baþlýk Geniþliði = 77, Hücre Geniþliði = 120) alýnacaktýr.
Tablo'nun 2.kolon geniþliði ilk deðeri olarak: Büyüðü (Baþlýk Geniþliði = 74, Hücre Geniþliði = 48) alýnacaktýr.
Tablo'nun 3.kolon geniþliði ilk deðeri olarak: Büyüðü (Baþlýk Geniþliði = 77, Hücre Geniþliði = 88) alýnacaktýr.
Tablo'nun 4.kolon geniþliði ilk deðeri olarak: Büyüðü (Baþlýk Geniþliði = 67, Hücre Geniþliði = 73) alýnacaktýr.
Tablo'nun 5.kolon geniþliði ilk deðeri olarak: Büyüðü (Baþlýk Geniþliði = 74, Hücre Geniþliði = 16) alýnacaktýr.
Tablo'nun 6.kolon geniþliði ilk deðeri olarak: Büyüðü (Baþlýk Geniþliði = 37, Hücre Geniþliði = 15) alýnacaktýr.
Tablo'nun 7.kolon geniþliði ilk deðeri olarak: Büyüðü (Baþlýk Geniþliði = 59, Hücre Geniþliði = 106) alýnacaktýr.

Tablo(0,7)'nýn yeni deðeri:[Master-Doktora]==>(class java.lang.String)'ýn bir tiplemesi

YENÝ DEÐÝÞEN VERÝLER==>
SýraNo TC No'su  Adý ve Soyadý  Ýkamet Þehri  Doðum Tarihi  Vefat Tarihi  2018'de Yaþý  Evli Mi  Mezuniyet
======================================================================================================
1.satýr:  43882313080  Haným Amanat Yavaþ  Yeþilyurt  1 Ekim 1932  4 Mayýs 2014 82  true  Master-Doktora
2.satýr:  43888312872  Memet Yavaþ  Yeþilyurt  1 Mart 1933  30 Mart 2018  85  true  Ýlkokul
3.satýr:  18626504192  Hatice Yavaþ Kaçar  Bursa  1 Mart 1953  Yok  65  true  Ýlkokul
4.satýr:  21290066668  Süheyla Yavaþ Özbay  Yakýnca  10 Mart 1954  Yok  64  true  Ýlkokul
5.satýr:  13619672094  Zeliha Yavaþ Candan  Antalya  7 Aðustos 1954  Yok  64  true  Ýlkokul
6.satýr:  43879313154  Mahmut Nihat Yavaþ  Mersin  17 Nisan 1957  Yok  61  false  Üniversite
7.satýr:  14270300692  Songül Yavaþ Göktürk  Malatya  14 Mayýs 1959  Yok  59  true  Üniversite
8.satýr:  43876313218  Mustafa Nedim Yavaþ  Bursa  27 Nisan 1961  Yok  57  false  Üniversite
9.satýr:  43873313372  Sevim Yavaþ  Bursa  1 Aðustos 1963  Yok  55  false  Üniversite
======================================================================================================
*/