// J5c_63.java: TableIntegerEditDemo (TabloTamsay�D�zenlemeG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/* Bu program J5c_60.java=TableDemo.java gibidir. Fark� tamsay� veri
 * giri�ini ge�erlilemek i�in a�a��daki h�cre edit�r�n� kullanmas�d�r:
 * J5c_63x.java=IntegerEditor.java
 */
public class J5c_63 extends JPanel {
    private boolean yaz�ls�nM� = true;

    public J5c_63() {// Kurucu...
        super (new GridLayout (1, 0));

        JTable tablo = new JTable (new Tablom());
        tablo.setPreferredScrollableViewportSize (new Dimension (1100, 80));
        //tablo.setFillsViewportHeight (true);
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // Tablodaki t�m tamsay� kolonlar� tamsay� edit�r�ne ba�layal�m...
        tablo.setDefaultEditor (Integer.class, new J5c_63x (0, 100));

        /* E�er tamsay� edit�r�n� sadece 5.ya� tamsat� kolonuna tahsis etmek istiyorsak:
         * tablo.getColumnModel().getColumn (5).setCellEditor (new J5c_63x (0, 100));
         * kodlamas�n� kullanmal�y�z.*/

        // Tablolu kayd�rma panosunu i�erik panosuna ekleyelim...
        JScrollPane kayd�rmaPanosu = new JScrollPane (tablo);
        add (kayd�rmaPanosu);
    } // J5c_63() kurucusunun sonu...

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
        JFrame �er�eve = new JFrame ("Tablo Tamsay� D�zenleme G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_63 yeni��erikPanosu = new J5c_63(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (100, 100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_63 s�n�f� sonu...

/* ��kt�:
**  >java J5c_63  **
Tablo(3,5)'n�n yeni de�eri:[64]==>(class java.lang.Integer)'�n bir tiplemesi

YEN� DE���EN VER�LER==>
S�raNo TC No'su  Ad� ve Soyad�  �kamet �ehri  Do�um Tarihi  Vefat Tarihi  2018'de Ya��  Evli Mi  Mezuniyet
======================================================================================================
1.sat�r:  43882313080  Han�m Amanat Yava�  Ye�ilyurt  1 Ekim 1932  4 May�s 2014 82  true  Halke�itim
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