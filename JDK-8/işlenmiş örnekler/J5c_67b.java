// J5c_67b.java: TableSelectionAndSortDemo (TabloSeçimiVeSýralamasýGösterisi) örneði.

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class J5c_67b extends JPanel implements ActionListener {
    private static boolean yazýlsýnMý = false;
    private JTable tablo;
    private JCheckBox satýrKontrolu;
    private JCheckBox kolonKontrolu;
    private JCheckBox hücreKontrolu;
    private ButtonGroup butonGrubu;
    private JTextArea raporlamaAlaný;

    public J5c_67b() {// Kurucu...
        super();
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));

        tablo = new JTable (new Tablom());
        tablo.setAutoCreateRowSorter (true);
        tablo.setPreferredScrollableViewportSize (new Dimension (1100, 80));
        tablo.setFillsViewportHeight (true);
        tablo.getSelectionModel().addListSelectionListener (new SatýrDinleyici());
        tablo.getColumnModel().getSelectionModel().addListSelectionListener (new KolonDinleyici());
        add (new JScrollPane (tablo));
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        add (new JLabel ("Tablo Seçim Kipleri"));
        butonGrubu = new ButtonGroup();
        radyoDüðmesiEkle ("Çoklu Aralýklý Seçim").setSelected (true);
        radyoDüðmesiEkle ("Tikel Seçim");
        radyoDüðmesiEkle ("Tikel Aralýklý Seçim");

        add (new JLabel ("Tablo Seçim Tercihleri"));
        satýrKontrolu = çentikKutusuEkle ("Satýr Seçimi");
        satýrKontrolu.setSelected (true);
        kolonKontrolu = çentikKutusuEkle ("Kolon Seçimi");
        hücreKontrolu = çentikKutusuEkle ("Hücre Seçimi");
        hücreKontrolu.setEnabled (false);

        setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...

        raporlamaAlaný = new JTextArea (5, 40);
        raporlamaAlaný.setEditable (false); // Müdahalesiz...
        raporlamaAlaný.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        raporlamaAlaný.setForeground (Color.WHITE);

        add (new JScrollPane (raporlamaAlaný));
    } // J5c_67b() kurucusu sonu...

    private JRadioButton radyoDüðmesiEkle (String dizge) {
        JRadioButton düðme = new JRadioButton (dizge);
        düðme.addActionListener (this); // Düðmeyi dinleyiciye duyarlayalým...
        düðme.setBackground (Color.ORANGE);
        butonGrubu.add (düðme);
        add (düðme);
        return düðme;
    } // radyoDüðmesiEkle(..) metodu sonu...

    private JCheckBox çentikKutusuEkle (String dizge) {
        JCheckBox çentikliKutu = new JCheckBox (dizge);
        çentikliKutu.addActionListener (this); // Dinleyiciye duyarlayalým...
        çentikliKutu.setBackground (Color.MAGENTA);
        add (çentikliKutu);
        return çentikliKutu;
    } // çentikKutusuEkle(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String aksiyonKomutu = olay.getActionCommand();
        // Çoklu Aralýklý Seçim'de Hücre Seçimi etkinsizdir...
        if ("Satýr Seçimi" == aksiyonKomutu) {
            tablo.setRowSelectionAllowed (satýrKontrolu.isSelected());
            // Satýr Seçimi'nde Hücre Seçimi etkinsizse Kolon Seçimi de etkinsizleþtirilmelidir...
            if (!hücreKontrolu.isEnabled()) tablo.setColumnSelectionAllowed (!satýrKontrolu.isSelected());
        }else if ("Kolon Seçimi" == aksiyonKomutu) {
            tablo.setColumnSelectionAllowed (kolonKontrolu.isSelected());
            // Çoklu Aralýklý Seçim'de ya Satýr ya Kolon Seçimi etkindir; ikisi de deðil...
            if (!hücreKontrolu.isEnabled()) tablo.setRowSelectionAllowed (!kolonKontrolu.isSelected());
        }else if ("Hücre Seçimi" == aksiyonKomutu) tablo.setCellSelectionEnabled (hücreKontrolu.isSelected());
        else if ("Çoklu Aralýklý Seçim" == aksiyonKomutu) { 
            tablo.setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            // Bu seçim kipinde Hücre Seçimi daima kapalýdýr...
            if (hücreKontrolu.isSelected()) {
                hücreKontrolu.setSelected (false);
                tablo.setCellSelectionEnabled (false);
            } // iç-if kararý sonu...
            // Ve hücre kontrolu etkinsizdir...
            hücreKontrolu.setEnabled (false);
        }else if ("Tikel Aralýklý Seçim" == aksiyonKomutu) {
            tablo.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            // Bu kipte Hücre Seçimi etkindir...
            hücreKontrolu.setEnabled (true);
        }else if ("Tikel Seçim" == aksiyonKomutu) {
            tablo.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
            // Bu kipte de Hücre Seçimi etkindir...
            hücreKontrolu.setEnabled (true);
        } // if-else karar zinciri sonu...

        // Çentik kutularýný seçim kipleri yan etkilerine karþý güncelleyelim...
        satýrKontrolu.setSelected (tablo.getRowSelectionAllowed());
        kolonKontrolu.setSelected (tablo.getColumnSelectionAllowed());
        if (hücreKontrolu.isEnabled()) hücreKontrolu.setSelected (tablo.getCellSelectionEnabled());
    } // actionPerformed(..) hazýr metodu sonu...

    private class SatýrDinleyici implements ListSelectionListener {
        public void valueChanged (ListSelectionEvent olay) {
            if (olay.getValueIsAdjusting()) return;
            raporlamaAlaný.append ("SATIR SEÇÝMÝ OLAYI==>");
            raporlamaAlanýnaYaz();
        } // valueChanged(..) hazýr metodu sonu...
    } // SatýrDinleyici sýnýfý sonu...

    private class KolonDinleyici implements ListSelectionListener {
        public void valueChanged (ListSelectionEvent olay) {
            if (olay.getValueIsAdjusting()) return;
            raporlamaAlaný.append ("KOLON SEÇÝMÝ OLAYI==>");
            raporlamaAlanýnaYaz();
        } // valueChanged(..) hazýr metodu sonu...
    } // KolonDinleyici sýnýfý sonu...

    private void raporlamaAlanýnaYaz() {
        raporlamaAlaný.append (String.format ("Aktüel Konum: %d, %d; ",
                    tablo.getSelectionModel().getLeadSelectionIndex(),
                    tablo.getColumnModel().getSelectionModel().getLeadSelectionIndex()));
        raporlamaAlaný.append ("Seçilen Satýr(lar):");
        for (int satýr : tablo.getSelectedRows()) raporlamaAlaný.append (String.format (" %d", satýr));
        raporlamaAlaný.append ("; Seçilen Kolon(lar):");
        for (int kolon : tablo.getSelectedColumns()) raporlamaAlaný.append (String.format (" %d", kolon));
        raporlamaAlaný.append (".\n");
    } // raporlamaAlanýnaYaz() metodu sonu...

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
        JFrame çerçeve = new JFrame ("Tablo Seçimi Ve Sýralamasý Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_67b yeniÝçerikPanosu = new J5c_67b(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (100,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        yazýlsýnMý = args.length > 0? true : false;
        javax.swing.UIManager.put ("swing.boldMetal", Boolean.FALSE); 
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_67b sýnýfý sonu...

/* Çýktý:
**  >java J5c_67b yaz  **
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

SATIR SEÇÝMÝ OLAYI==>Aktüel Konum: 8, 6; Seçilen Satýr(lar): 8; Seçilen Kolon(lar): 6.
KOLON SEÇÝMÝ OLAYI==>Aktüel Konum: 8, 6; Seçilen Satýr(lar): 8; Seçilen Kolon(lar): 6.
SATIR SEÇÝMÝ OLAYI==>Aktüel Konum: 4, 1; Seçilen Satýr(lar): 4; Seçilen Kolon(lar): 1.
KOLON SEÇÝMÝ OLAYI==>Aktüel Konum: 4, 1; Seçilen Satýr(lar): 4; Seçilen Kolon(lar): 1.
KOLON SEÇÝMÝ OLAYI==>Aktüel Konum: 4, 2; Seçilen Satýr(lar): 4; Seçilen Kolon(lar): 1 2.
KOLON SEÇÝMÝ OLAYI==>Aktüel Konum: 4, 3; Seçilen Satýr(lar): 4; Seçilen Kolon(lar): 1 2 3.
KOLON SEÇÝMÝ OLAYI==>Aktüel Konum: 4, 4; Seçilen Satýr(lar): 4; Seçilen Kolon(lar): 1 2 3 4.
SATIR SEÇÝMÝ OLAYI==>Aktüel Konum: 5, 4; Seçilen Satýr(lar): 4 5; Seçilen Kolon(lar): 1 2 3 4.
SATIR SEÇÝMÝ OLAYI==>Aktüel Konum: 6, 4; Seçilen Satýr(lar): 4 5 6; Seçilen Kolon(lar): 1 2 3 4.
SATIR SEÇÝMÝ OLAYI==>Aktüel Konum: 7, 4; Seçilen Satýr(lar): 4 5 6 7; Seçilen Kolon(lar): 1 2 3 4.
KOLON SEÇÝMÝ OLAYI==>Aktüel Konum: 7, 5; Seçilen Satýr(lar): 4 5 6 7; Seçilen Kolon(lar): 1 2 3 4 5.
KOLON SEÇÝMÝ OLAYI==>Aktüel Konum: 7, 6; Seçilen Satýr(lar): 4 5 6 7; Seçilen Kolon(lar): 1 2 3 4 5 6.
SATIR SEÇÝMÝ OLAYI==>Aktüel Konum: 8, 6; Seçilen Satýr(lar): 4 5 6 7 8; Seçilen Kolon(lar): 1 2 3 4 5 6.
SATIR SEÇÝMÝ OLAYI==>Aktüel Konum: 4, 6; Seçilen Satýr(lar): 4 5 6 7 8; Seçilen Kolon(lar): 1 2 3 4 5 6.
KOLON SEÇÝMÝ OLAYI==>Aktüel Konum: 4, 7; Seçilen Satýr(lar): 4 5 6 7 8; Seçilen Kolon(lar): 1 2 3 4 5 6.
SATIR SEÇÝMÝ OLAYI==>Aktüel Konum: 4, 7; Seçilen Satýr(lar):; Seçilen Kolon(lar): 1 2 3 4 5 6.
KOLON SEÇÝMÝ OLAYI==>Aktüel Konum: 4, 7; Seçilen Satýr(lar):; Seçilen Kolon(lar):.
SATIR SEÇÝMÝ OLAYI==>Aktüel Konum: 7, 2; Seçilen Satýr(lar): 7; Seçilen Kolon(lar): 2.
KOLON SEÇÝMÝ OLAYI==>Aktüel Konum: 7, 2; Seçilen Satýr(lar): 7; Seçilen Kolon(lar): 2.
KOLON SEÇÝMÝ OLAYI==>Aktüel Konum: 7, 3; Seçilen Satýr(lar): 7; Seçilen Kolon(lar): 3.
SATIR SEÇÝMÝ OLAYI==>Aktüel Konum: 8, 3; Seçilen Satýr(lar): 8; Seçilen Kolon(lar): 3.
*/