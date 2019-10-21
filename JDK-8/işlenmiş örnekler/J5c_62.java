// J5c_62.java: TableFilterDemo (TabloFilitresiGösterisi) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.RowFilter;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

/* Gerekli java dosyasý:
 * J5c_51x2.java=SpringUtilities.java
 */
public class J5c_62 extends JPanel {
    private boolean yazýlsýnMý = true;
    private JTable tablo;
    private JTextField filitreMetinSatýrý;
    private JTextField durumMetinSatýrý;
    private TableRowSorter<Tablom> sýralayýcý;

    public J5c_62() {// Kurucu...
        super();
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));

        // Herbir kolonu artan/azalan sýralayýcýlý bir tablo yaratalým...
        Tablom tabloModelim = new Tablom();
        sýralayýcý = new TableRowSorter<Tablom>(tabloModelim);
        tablo = new JTable (tabloModelim);
        tablo.setRowSorter (sýralayýcý);
        tablo.setPreferredScrollableViewportSize (new Dimension (1100, 80));
        tablo.setFillsViewportHeight (true);
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        tablo.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);

        /* Seçilen satýrýn artan/azalan sýralamalý görüntüdeki aktüel satýr no'sunu
         * ve ilk modeldeki satýr no'sunu durum metin satýrýna birlikte yazalým.*/
        tablo.getSelectionModel().addListSelectionListener (
                new ListSelectionListener() {
                    public void valueChanged (ListSelectionEvent olay) {
                        int seçilenSatýr = tablo.getSelectedRow();
                        if (seçilenSatýr < 0) // Seçilen filitre dýþý...
                            durumMetinSatýrý.setText ("");
                        else {int modelSatýrý = tablo.convertRowIndexToModel (seçilenSatýr);
                            durumMetinSatýrý.setText (String.format ("Güncel sýralý görüntüdeki satýr no: %d. " +
                                    "Orijinal modeldeki satýr no: %d.", seçilenSatýr, modelSatýrý));
                        } // else kararý sonu...
                    } // valueChanged(..) hazýr metodu sonu...
                } // ListSelectionListener() hazýr sýnýfý sonu...
        ); // tab.. ifadesi sonu...

        // Kaydýrma panosunu tabloya giydirip içerik panosuna ekleyelim...
        JScrollPane kaydýrmaPanosu = new JScrollPane (tablo);
        add (kaydýrmaPanosu);

        // Filitre metin satýrý and durum metin satýrý için ayrý bir form paneli kullanalým...
        JPanel formPaneli = new JPanel (new SpringLayout());
        JLabel etiket1 = new JLabel ("Filitre Metni:", SwingConstants.TRAILING); //Saða yaslý...
        filitreMetinSatýrý = new JTextField();
        // Filitre metin satýrý deðiþtiðinde, yeniFilitre metodunu iþletelim...
        filitreMetinSatýrý.getDocument().addDocumentListener (
            new DocumentListener() {
                public void changedUpdate (DocumentEvent olay) {yeniFilitre();}
                public void insertUpdate (DocumentEvent olay) {yeniFilitre();}
                public void removeUpdate (DocumentEvent olay) {yeniFilitre();}
            } // DocumentListener() hazýr sýnýfý sonu...
        ); // fil.. ifadesi sonu...
        etiket1.setLabelFor (filitreMetinSatýrý);
        etiket1.setForeground (Color.WHITE);
        formPaneli.add (etiket1);
        formPaneli.add (filitreMetinSatýrý);

        JLabel etiket2 = new JLabel ("Durum:", SwingConstants.TRAILING);
        durumMetinSatýrý = new JTextField();
        etiket2.setLabelFor (durumMetinSatýrý);
        etiket2.setForeground (Color.WHITE);
        formPaneli.add (etiket2);
        formPaneli.add (durumMetinSatýrý);

        J5c_51x2.kesifIzgaraYap (formPaneli, 2, 2, 6, 6, 6, 6);
        add (formPaneli);
        formPaneli.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
    } // J5c_62() kurucusu sonu...

    /* Filitre metin satýrýndaki regex'in her deðiþimde güncelleyelim.
    * RegEx sadece ikinci sütun (Ad Soyad) için geçerlidir.*/
    private void yeniFilitre() {
        RowFilter<Tablom, Object> satýrFilitresi = null;
        // Eðer aktüel regex çözümlenemezse birþey yapmadan dönelim...
        //if (String.valueOf (filitreMetinSatýrý).compareTo ("A") >= 0) {
        try {satýrFilitresi = RowFilter.regexFilter (filitreMetinSatýrý.getText(), 1);
        }catch (java.util.regex.PatternSyntaxException ist) {return;}
        sýralayýcý.setRowFilter (satýrFilitresi);
        /*}else {
        try {satýrFilitresi = RowFilter.regexFilter (filitreMetinSatýrý.getText(), 0); // TC No'ya göre filitreler...
        }catch (java.util.regex.PatternSyntaxException ist) {return;}
        sýralayýcý.setRowFilter (satýrFilitresi);
        }*/
    } // yeniFilitre() metodu sonu...

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
        JFrame çerçeve = new JFrame ("Tablo Filitresi Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_62 yeniÝçerikPanosu = new J5c_62(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (100,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_62 sýnýfý sonu...

/* Çýktý:
**  >java J5c_62  **
Tablo(8,6)'nýn yeni deðeri:[true]==>(class java.lang.Boolean)'ýn bir tiplemesi

YENÝ DEÐÝÞEN VERÝLER==>
SýraNo TC No'su  Adý ve Soyadý  Ýkamet Þehri  Doðum Tarihi  Vefat Tarihi  2018'de Yaþý  Evli Mi  Mezuniyet
======================================================================================================
1.satýr:  43882313080  Haným Amanat Yavaþ  Yeþilyurt  1 Ekim 1932  4 Mayýs 2014 82  true  Halkeðitim
2.satýr:  43888312872  Memet Yavaþ  Yeþilyurt  1 Mart 1933  30 Mart 2018  85  true  Ýlkokul
3.satýr:  18626504192  Hatice Yavaþ Kaçar  Bursa  1 Mart 1953  Yok  65  true  Ýlkokul
4.satýr:  21290066668  Süheyla Yavaþ Özbay  Yakýnca  10 Mart 1954  Yok  64  true  Ýlkokul
5.satýr:  13619672094  Zeliha Yavaþ Candan  Antalya  7 Aðustos 1954  Yok  64  true  Ýlkokul
6.satýr:  43879313154  Mahmut Nihat Yavaþ  Mersin  17 Nisan 1957  Yok  61  false  Üniversite
7.satýr:  14270300692  Songül Yavaþ Göktürk  Malatya  14 Mayýs 1959  Yok  59  true  Üniversite
8.satýr:  43876313218  Mustafa Nedim Yavaþ  Bursa  27 Nisan 1961  Yok  57  false  Üniversite
9.satýr:  43873313372  Sevim Yavaþ  Bursa  1 Aðustos 1963  Yok  55  true  Üniversite
======================================================================================================
*/