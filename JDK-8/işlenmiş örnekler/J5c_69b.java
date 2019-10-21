// J5c_69b.java: TableSorterAndToolTipsDemo (TabloS�ralay�v�Ve�pu�lar�G�sterisi) �rne�i.
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

/* Bu program da J5c_60.java=TableDemo.java gibidir; ancak
 * burada t�m tablo ba�l�klar� ve baz� kolon elemanlar� i�in
 * (2:ikamet, 6:evliMi ve 7:mezuniyet) ipucu a��klamalar� konulmu�tur.
 *
 * Gereken dosya: J5c_68x.java=TableSorter.java
 */
public class J5c_69b extends JPanel {
    private static boolean yaz�ls�nM� = false;
    protected String[] kolonBa�l�klar��pu�lar� = {
            "Aile bireylerinin TC numaralar� verilmi�tir",
            "�ah�slar�n tam (ilk, orta ve son) adlar� yaz�l�d�r",
            "Bireyin �imdiki zamanda ikamet etti�i yer belirtilmi�tir",
            "�ah�slar�n do�um tarihleri yyyy/aa/gg olarak verilmi�tir",
            "�ah�s m�teveffa ise tarihi, ya��yorsa YOK belirteci konulmu�tur",
            null, // Ya� ipu�lar� belirtilmemi�tir...
            "Akt�el medeni durumlar� (Evliyse �entikli) g�r�lmektedir",
            "Son mezun olunan okul dereceleri verilmi�tir",};

    public J5c_69b() {// Kurucu...
        super (new GridLayout (1,0));

        J5c_68x s�ralay�c� = new J5c_68x (new Tablom());
        JTable tablo = new JTable (s�ralay�c�) {
            // Tablo'nun baz� kolon elemanlar� ipu�lar�n� i�letir...
            public String getToolTipText (MouseEvent olay) {
                String ipucuA��klamas� = null;
                java.awt.Point nokta = olay.getPoint();
                int sat�rEndeksi = rowAtPoint (nokta);
                int kolonEndeksi = columnAtPoint (nokta);
                int ger�ekKolonEndeksi = convertColumnIndexToModel (kolonEndeksi);

                if (ger�ekKolonEndeksi == 2) //�kamet �ehri...
                    ipucuA��klamas� = "Bu aile bireyinin akt�el ikamet �ehri: " + getValueAt (sat�rEndeksi, kolonEndeksi);
                else if (ger�ekKolonEndeksi == 6) {// Evli/bekar...
                    TableModel tabloModeli = getModel();
                    String adSoyad = (String)tabloModeli.getValueAt (sat�rEndeksi, 1);
                    Boolean evliMi = (Boolean)tabloModeli.getValueAt (sat�rEndeksi,6);
                    if (Boolean.TRUE.equals (evliMi)) ipucuA��klamas� = adSoyad + " �u anda evlidir";
                    else ipucuA��klamas� = adSoyad + " �u anda bekard�r";
                }else if (ger�ekKolonEndeksi == 7) // Mezuniyet...
                    ipucuA��klamas� = "Bu aile bireyinin okul mezuniyet durumu: " + getValueAt (sat�rEndeksi, kolonEndeksi);
                else // Kendi ipu�lar�n� sunan takdimci yoksa buray� atlayabilirsiniz...
                    ipucuA��klamas� = super.getToolTipText (olay);

                return ipucuA��klamas�;
            } // getToolTipText(..) haz�r metodu sonu...

            // Tablo kolon ba�l�klar� ipu�lar�n� i�letir...
            protected JTableHeader createDefaultTableHeader() {
                return new JTableHeader (columnModel) {
                    public String getToolTipText (MouseEvent olay) {
                        String ipucuA��klamas� = null;
                        java.awt.Point nokta = olay.getPoint();
                        int endeks = columnModel.getColumnIndexAtX (nokta.x);
                        int ger�ekEndeks = columnModel.getColumn (endeks).getModelIndex();
                        return kolonBa�l�klar��pu�lar�[ger�ekEndeks];
                    } // getToolTipText(..) haz�r metodu sonu...
                }; // ret.. ifadesi sonu...
            } // createDefaultTableHeader() haz�r metodu sonu...
        }; // JTa.. ifadesi sonu...

        s�ralay�c�.setTableHeader (tablo.getTableHeader());
        tablo.setPreferredScrollableViewportSize (new Dimension (1100, 80));
        //tablo.setAutoCreateRowSorter (true); // �ptal...
        tablo.setFillsViewportHeight (true);
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
                
        // Tablolu kayd�rmay� i�erik panosuna ekleyelim...
        JScrollPane kayd�rmaPanosu = new JScrollPane (tablo);
        add (kayd�rmaPanosu);
    } // J5c_69b() kurucusu sonu...

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
            fireTableCellUpdated (sat�r, kolon); // Tablo s�ralay�c� problem ��karabilir...
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
        JFrame �er�eve = new JFrame ("Tablo S�ralay�c� Ve �pu�lar� G�sterisi ");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5c_69b(); // Kurucuyu �a��r�r...
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
} // J5c_69b s�n�f� sonu...