// J5c_69b.java: TableSorterAndToolTipsDemo (TabloSýralayývýVeÝpuçlarýGösterisi) örneði.
 
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
 * burada tüm tablo baþlýklarý ve bazý kolon elemanlarý için
 * (2:ikamet, 6:evliMi ve 7:mezuniyet) ipucu açýklamalarý konulmuþtur.
 *
 * Gereken dosya: J5c_68x.java=TableSorter.java
 */
public class J5c_69b extends JPanel {
    private static boolean yazýlsýnMý = false;
    protected String[] kolonBaþlýklarýÝpuçlarý = {
            "Aile bireylerinin TC numaralarý verilmiþtir",
            "Þahýslarýn tam (ilk, orta ve son) adlarý yazýlýdýr",
            "Bireyin þimdiki zamanda ikamet ettiði yer belirtilmiþtir",
            "Þahýslarýn doðum tarihleri yyyy/aa/gg olarak verilmiþtir",
            "Þahýs müteveffa ise tarihi, yaþýyorsa YOK belirteci konulmuþtur",
            null, // Yaþ ipuçlarý belirtilmemiþtir...
            "Aktüel medeni durumlarý (Evliyse çentikli) görülmektedir",
            "Son mezun olunan okul dereceleri verilmiþtir",};

    public J5c_69b() {// Kurucu...
        super (new GridLayout (1,0));

        J5c_68x sýralayýcý = new J5c_68x (new Tablom());
        JTable tablo = new JTable (sýralayýcý) {
            // Tablo'nun bazý kolon elemanlarý ipuçlarýný iþletir...
            public String getToolTipText (MouseEvent olay) {
                String ipucuAçýklamasý = null;
                java.awt.Point nokta = olay.getPoint();
                int satýrEndeksi = rowAtPoint (nokta);
                int kolonEndeksi = columnAtPoint (nokta);
                int gerçekKolonEndeksi = convertColumnIndexToModel (kolonEndeksi);

                if (gerçekKolonEndeksi == 2) //Ýkamet Þehri...
                    ipucuAçýklamasý = "Bu aile bireyinin aktüel ikamet þehri: " + getValueAt (satýrEndeksi, kolonEndeksi);
                else if (gerçekKolonEndeksi == 6) {// Evli/bekar...
                    TableModel tabloModeli = getModel();
                    String adSoyad = (String)tabloModeli.getValueAt (satýrEndeksi, 1);
                    Boolean evliMi = (Boolean)tabloModeli.getValueAt (satýrEndeksi,6);
                    if (Boolean.TRUE.equals (evliMi)) ipucuAçýklamasý = adSoyad + " þu anda evlidir";
                    else ipucuAçýklamasý = adSoyad + " þu anda bekardýr";
                }else if (gerçekKolonEndeksi == 7) // Mezuniyet...
                    ipucuAçýklamasý = "Bu aile bireyinin okul mezuniyet durumu: " + getValueAt (satýrEndeksi, kolonEndeksi);
                else // Kendi ipuçlarýný sunan takdimci yoksa burayý atlayabilirsiniz...
                    ipucuAçýklamasý = super.getToolTipText (olay);

                return ipucuAçýklamasý;
            } // getToolTipText(..) hazýr metodu sonu...

            // Tablo kolon baþlýklarý ipuçlarýný iþletir...
            protected JTableHeader createDefaultTableHeader() {
                return new JTableHeader (columnModel) {
                    public String getToolTipText (MouseEvent olay) {
                        String ipucuAçýklamasý = null;
                        java.awt.Point nokta = olay.getPoint();
                        int endeks = columnModel.getColumnIndexAtX (nokta.x);
                        int gerçekEndeks = columnModel.getColumn (endeks).getModelIndex();
                        return kolonBaþlýklarýÝpuçlarý[gerçekEndeks];
                    } // getToolTipText(..) hazýr metodu sonu...
                }; // ret.. ifadesi sonu...
            } // createDefaultTableHeader() hazýr metodu sonu...
        }; // JTa.. ifadesi sonu...

        sýralayýcý.setTableHeader (tablo.getTableHeader());
        tablo.setPreferredScrollableViewportSize (new Dimension (1100, 80));
        //tablo.setAutoCreateRowSorter (true); // Ýptal...
        tablo.setFillsViewportHeight (true);
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
                
        // Tablolu kaydýrmayý içerik panosuna ekleyelim...
        JScrollPane kaydýrmaPanosu = new JScrollPane (tablo);
        add (kaydýrmaPanosu);
    } // J5c_69b() kurucusu sonu...

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
            fireTableCellUpdated (satýr, kolon); // Tablo sýralayýcý problem çýkarabilir...
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
        JFrame çerçeve = new JFrame ("Tablo Sýralayýcý Ve Ýpuçlarý Gösterisi ");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5c_69b(); // Kurucuyu çaðýrýr...
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
} // J5c_69b sýnýfý sonu...