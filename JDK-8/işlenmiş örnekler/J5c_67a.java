// J5c_67a.java: TableSortDemo (TabloS�ralamaG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class J5c_67a extends JPanel {
    private static boolean yaz�ls�nM� = false;

    public J5c_67a() {// Kurucu...
        super (new GridLayout (1,0));

        JTable tablo = new JTable (new Tablom());
        tablo.setPreferredScrollableViewportSize (new Dimension (1100, 80));
        tablo.setFillsViewportHeight (true);
        tablo.setAutoCreateRowSorter (true); // T�m tablo kolon artan/azalan s�ralamas�n� yapar...
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // Kayd�rmal� tabloyu i�erik panosuna ekleyelim...
        JScrollPane kayd�rmaPanosu = new JScrollPane (tablo);
        add (kayd�rmaPanosu);
    } // J5c_67a() kurucusu sonu...

    class Tablom extends AbstractTableModel {
        private String[] tabloBa�l�klar� = {"TC No'su", "Ad� ve Soyad�", "�kamet �ehri", "Do�um Tarihi", "Vefat Tarihi", "2018'de Ya��", "Evli Mi", "Mezuniyet"};
        Object[][] tabloVerileri = {
            {new Long ("43882313080"), "Han�m Amanat Yava�", "Ye�ilyurt", "1932/10/01", "2014/05/04", new Integer (82), new Boolean (true), "Halke�itim"},
            {new Long ("43888312872"), "Memet Yava�", "Ye�ilyurt", "1933/03/01", "2018/03/30", new Integer (85), new Boolean (true), "�lkokul"},
            {new Long ("18626504192"), "Hatice Yava� Ka�ar", "Bursa", "1953/03/01", "Yok", new Integer (65), new Boolean (true), "�lkokul"},
            {new Long ("21290066668"), "S�heyla Yava� �zbay", "Yak�nca", "1954/03/10", "Yok", new Integer (64), new Boolean (true), "�lkokul"},
            {new Long ("13619672094"), "Zeliha Yava� Candan", "Antalya", "1954/08/07", "Yok", new Integer (64), new Boolean (true), "�lkokul"},
            {new Long ("43879313154"), "Mahmut Nihat Yava�", "Mersin", "1957/04/17", "Yok", new Integer (61), new Boolean (false), "�niversite"},
            {new Long ("14270300692"), "Song�l Yava� G�kt�rk", "Malatya", "1959/05/14", "Yok", new Integer (59), new Boolean (true), "�niversite"},
            {new Long ("43876313218"), "Mustafa Nedim Yava�", "Bursa", "1961/04/27", "Yok", new Integer (57), new Boolean (false), "�niversite"},
            {new Long ("43873313372"), "Sevim Yava�", "Bursa", "1963/08/01", "Yok", new Integer (55), new Boolean (false), "�niversite"}
        }; // Obj.. ifadesi sonu...

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
            //fireTableCellUpdated (sat�r, kolon); // Tablo s�ralay�c� problem ��karabilir...
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
        JFrame �er�eve = new JFrame ("Tablo S�ralamas� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_67a yeni��erikPanosu = new J5c_67a(); // Kurucuyu �a��r�r...
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
} // J5c_67a s�n�f� sonu...

/* ��kt�:
**  >java J5c_67a yaz  **
Tablo(0,7)'n�n yeni de�eri:[Halke�itim]==>(class java.lang.String)'�n bir tiplemesi

YEN� DE���EN VER�LER==>
S�raNo TC No'su  Ad� ve Soyad�  �kamet �ehri  Do�um Tarihi  Vefat Tarihi  2018'de Ya��  Evli Mi  Mezuniyet
======================================================================================================
1.sat�r:  43882313080  Han�m Amanat Yava�  Ye�ilyurt  1932/10/01  2014/05/04  82  true  Halke�itim
2.sat�r:  43888312872  Memet Yava�  Ye�ilyurt  1933/03/01  2018/03/30  85  true �lkokul
3.sat�r:  18626504192  Hatice Yava� Ka�ar  Bursa  1953/03/01  Yok  65  true  �lkokul
4.sat�r:  21290066668  S�heyla Yava� �zbay  Yak�nca  1954/03/10  Yok  64  true�lkokul
5.sat�r:  13619672094  Zeliha Yava� Candan  Antalya  1954/08/07  Yok  64  true�lkokul
6.sat�r:  43879313154  Mahmut Nihat Yava�  Mersin  1957/04/17  Yok  61  false  �niversite
7.sat�r:  14270300692  Song�l Yava� G�kt�rk  Malatya  1959/05/14  Yok  59  true �niversite
8.sat�r:  43876313218  Mustafa Nedim Yava�  Bursa  1961/04/27  Yok  57  false  �niversite
9.sat�r:  43873313372  Sevim Yava�  Bursa  1963/08/01  Yok  55  false  �niversite
======================================================================================================
*/