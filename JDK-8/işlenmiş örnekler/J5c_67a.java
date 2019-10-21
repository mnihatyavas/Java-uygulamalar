// J5c_67a.java: TableSortDemo (TabloSýralamaGösterisi) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class J5c_67a extends JPanel {
    private static boolean yazýlsýnMý = false;

    public J5c_67a() {// Kurucu...
        super (new GridLayout (1,0));

        JTable tablo = new JTable (new Tablom());
        tablo.setPreferredScrollableViewportSize (new Dimension (1100, 80));
        tablo.setFillsViewportHeight (true);
        tablo.setAutoCreateRowSorter (true); // Tüm tablo kolon artan/azalan sýralamasýný yapar...
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // Kaydýrmalý tabloyu içerik panosuna ekleyelim...
        JScrollPane kaydýrmaPanosu = new JScrollPane (tablo);
        add (kaydýrmaPanosu);
    } // J5c_67a() kurucusu sonu...

    class Tablom extends AbstractTableModel {
        private String[] tabloBaþlýklarý = {"TC No'su", "Adý ve Soyadý", "Ýkamet Þehri", "Doðum Tarihi", "Vefat Tarihi", "2018'de Yaþý", "Evli Mi", "Mezuniyet"};
        Object[][] tabloVerileri = {
            {new Long ("43882313080"), "Haným Amanat Yavaþ", "Yeþilyurt", "1932/10/01", "2014/05/04", new Integer (82), new Boolean (true), "Halkeðitim"},
            {new Long ("43888312872"), "Memet Yavaþ", "Yeþilyurt", "1933/03/01", "2018/03/30", new Integer (85), new Boolean (true), "Ýlkokul"},
            {new Long ("18626504192"), "Hatice Yavaþ Kaçar", "Bursa", "1953/03/01", "Yok", new Integer (65), new Boolean (true), "Ýlkokul"},
            {new Long ("21290066668"), "Süheyla Yavaþ Özbay", "Yakýnca", "1954/03/10", "Yok", new Integer (64), new Boolean (true), "Ýlkokul"},
            {new Long ("13619672094"), "Zeliha Yavaþ Candan", "Antalya", "1954/08/07", "Yok", new Integer (64), new Boolean (true), "Ýlkokul"},
            {new Long ("43879313154"), "Mahmut Nihat Yavaþ", "Mersin", "1957/04/17", "Yok", new Integer (61), new Boolean (false), "Üniversite"},
            {new Long ("14270300692"), "Songül Yavaþ Göktürk", "Malatya", "1959/05/14", "Yok", new Integer (59), new Boolean (true), "Üniversite"},
            {new Long ("43876313218"), "Mustafa Nedim Yavaþ", "Bursa", "1961/04/27", "Yok", new Integer (57), new Boolean (false), "Üniversite"},
            {new Long ("43873313372"), "Sevim Yavaþ", "Bursa", "1963/08/01", "Yok", new Integer (55), new Boolean (false), "Üniversite"}
        }; // Obj.. ifadesi sonu...

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
            //fireTableCellUpdated (satýr, kolon); // Tablo sýralayýcý problem çýkarabilir...
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
        JFrame çerçeve = new JFrame ("Tablo Sýralamasý Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_67a yeniÝçerikPanosu = new J5c_67a(); // Kurucuyu çaðýrýr...
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
} // J5c_67a sýnýfý sonu...

/* Çýktý:
**  >java J5c_67a yaz  **
Tablo(0,7)'nýn yeni deðeri:[Halkeðitim]==>(class java.lang.String)'ýn bir tiplemesi

YENÝ DEÐÝÞEN VERÝLER==>
SýraNo TC No'su  Adý ve Soyadý  Ýkamet Þehri  Doðum Tarihi  Vefat Tarihi  2018'de Yaþý  Evli Mi  Mezuniyet
======================================================================================================
1.satýr:  43882313080  Haným Amanat Yavaþ  Yeþilyurt  1932/10/01  2014/05/04  82  true  Halkeðitim
2.satýr:  43888312872  Memet Yavaþ  Yeþilyurt  1933/03/01  2018/03/30  85  true Ýlkokul
3.satýr:  18626504192  Hatice Yavaþ Kaçar  Bursa  1953/03/01  Yok  65  true  Ýlkokul
4.satýr:  21290066668  Süheyla Yavaþ Özbay  Yakýnca  1954/03/10  Yok  64  trueÝlkokul
5.satýr:  13619672094  Zeliha Yavaþ Candan  Antalya  1954/08/07  Yok  64  trueÝlkokul
6.satýr:  43879313154  Mahmut Nihat Yavaþ  Mersin  1957/04/17  Yok  61  false  Üniversite
7.satýr:  14270300692  Songül Yavaþ Göktürk  Malatya  1959/05/14  Yok  59  true Üniversite
8.satýr:  43876313218  Mustafa Nedim Yavaþ  Bursa  1961/04/27  Yok  57  false  Üniversite
9.satýr:  43873313372  Sevim Yavaþ  Bursa  1963/08/01  Yok  55  false  Üniversite
======================================================================================================
*/