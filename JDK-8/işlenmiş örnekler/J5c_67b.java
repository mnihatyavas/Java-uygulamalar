// J5c_67b.java: TableSelectionAndSortDemo (TabloSe�imiVeS�ralamas�G�sterisi) �rne�i.

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
    private static boolean yaz�ls�nM� = false;
    private JTable tablo;
    private JCheckBox sat�rKontrolu;
    private JCheckBox kolonKontrolu;
    private JCheckBox h�creKontrolu;
    private ButtonGroup butonGrubu;
    private JTextArea raporlamaAlan�;

    public J5c_67b() {// Kurucu...
        super();
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));

        tablo = new JTable (new Tablom());
        tablo.setAutoCreateRowSorter (true);
        tablo.setPreferredScrollableViewportSize (new Dimension (1100, 80));
        tablo.setFillsViewportHeight (true);
        tablo.getSelectionModel().addListSelectionListener (new Sat�rDinleyici());
        tablo.getColumnModel().getSelectionModel().addListSelectionListener (new KolonDinleyici());
        add (new JScrollPane (tablo));
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        add (new JLabel ("Tablo Se�im Kipleri"));
        butonGrubu = new ButtonGroup();
        radyoD��mesiEkle ("�oklu Aral�kl� Se�im").setSelected (true);
        radyoD��mesiEkle ("Tikel Se�im");
        radyoD��mesiEkle ("Tikel Aral�kl� Se�im");

        add (new JLabel ("Tablo Se�im Tercihleri"));
        sat�rKontrolu = �entikKutusuEkle ("Sat�r Se�imi");
        sat�rKontrolu.setSelected (true);
        kolonKontrolu = �entikKutusuEkle ("Kolon Se�imi");
        h�creKontrolu = �entikKutusuEkle ("H�cre Se�imi");
        h�creKontrolu.setEnabled (false);

        setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...

        raporlamaAlan� = new JTextArea (5, 40);
        raporlamaAlan�.setEditable (false); // M�dahalesiz...
        raporlamaAlan�.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        raporlamaAlan�.setForeground (Color.WHITE);

        add (new JScrollPane (raporlamaAlan�));
    } // J5c_67b() kurucusu sonu...

    private JRadioButton radyoD��mesiEkle (String dizge) {
        JRadioButton d��me = new JRadioButton (dizge);
        d��me.addActionListener (this); // D��meyi dinleyiciye duyarlayal�m...
        d��me.setBackground (Color.ORANGE);
        butonGrubu.add (d��me);
        add (d��me);
        return d��me;
    } // radyoD��mesiEkle(..) metodu sonu...

    private JCheckBox �entikKutusuEkle (String dizge) {
        JCheckBox �entikliKutu = new JCheckBox (dizge);
        �entikliKutu.addActionListener (this); // Dinleyiciye duyarlayal�m...
        �entikliKutu.setBackground (Color.MAGENTA);
        add (�entikliKutu);
        return �entikliKutu;
    } // �entikKutusuEkle(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String aksiyonKomutu = olay.getActionCommand();
        // �oklu Aral�kl� Se�im'de H�cre Se�imi etkinsizdir...
        if ("Sat�r Se�imi" == aksiyonKomutu) {
            tablo.setRowSelectionAllowed (sat�rKontrolu.isSelected());
            // Sat�r Se�imi'nde H�cre Se�imi etkinsizse Kolon Se�imi de etkinsizle�tirilmelidir...
            if (!h�creKontrolu.isEnabled()) tablo.setColumnSelectionAllowed (!sat�rKontrolu.isSelected());
        }else if ("Kolon Se�imi" == aksiyonKomutu) {
            tablo.setColumnSelectionAllowed (kolonKontrolu.isSelected());
            // �oklu Aral�kl� Se�im'de ya Sat�r ya Kolon Se�imi etkindir; ikisi de de�il...
            if (!h�creKontrolu.isEnabled()) tablo.setRowSelectionAllowed (!kolonKontrolu.isSelected());
        }else if ("H�cre Se�imi" == aksiyonKomutu) tablo.setCellSelectionEnabled (h�creKontrolu.isSelected());
        else if ("�oklu Aral�kl� Se�im" == aksiyonKomutu) { 
            tablo.setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            // Bu se�im kipinde H�cre Se�imi daima kapal�d�r...
            if (h�creKontrolu.isSelected()) {
                h�creKontrolu.setSelected (false);
                tablo.setCellSelectionEnabled (false);
            } // i�-if karar� sonu...
            // Ve h�cre kontrolu etkinsizdir...
            h�creKontrolu.setEnabled (false);
        }else if ("Tikel Aral�kl� Se�im" == aksiyonKomutu) {
            tablo.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            // Bu kipte H�cre Se�imi etkindir...
            h�creKontrolu.setEnabled (true);
        }else if ("Tikel Se�im" == aksiyonKomutu) {
            tablo.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
            // Bu kipte de H�cre Se�imi etkindir...
            h�creKontrolu.setEnabled (true);
        } // if-else karar zinciri sonu...

        // �entik kutular�n� se�im kipleri yan etkilerine kar�� g�ncelleyelim...
        sat�rKontrolu.setSelected (tablo.getRowSelectionAllowed());
        kolonKontrolu.setSelected (tablo.getColumnSelectionAllowed());
        if (h�creKontrolu.isEnabled()) h�creKontrolu.setSelected (tablo.getCellSelectionEnabled());
    } // actionPerformed(..) haz�r metodu sonu...

    private class Sat�rDinleyici implements ListSelectionListener {
        public void valueChanged (ListSelectionEvent olay) {
            if (olay.getValueIsAdjusting()) return;
            raporlamaAlan�.append ("SATIR SE��M� OLAYI==>");
            raporlamaAlan�naYaz();
        } // valueChanged(..) haz�r metodu sonu...
    } // Sat�rDinleyici s�n�f� sonu...

    private class KolonDinleyici implements ListSelectionListener {
        public void valueChanged (ListSelectionEvent olay) {
            if (olay.getValueIsAdjusting()) return;
            raporlamaAlan�.append ("KOLON SE��M� OLAYI==>");
            raporlamaAlan�naYaz();
        } // valueChanged(..) haz�r metodu sonu...
    } // KolonDinleyici s�n�f� sonu...

    private void raporlamaAlan�naYaz() {
        raporlamaAlan�.append (String.format ("Akt�el Konum: %d, %d; ",
                    tablo.getSelectionModel().getLeadSelectionIndex(),
                    tablo.getColumnModel().getSelectionModel().getLeadSelectionIndex()));
        raporlamaAlan�.append ("Se�ilen Sat�r(lar):");
        for (int sat�r : tablo.getSelectedRows()) raporlamaAlan�.append (String.format (" %d", sat�r));
        raporlamaAlan�.append ("; Se�ilen Kolon(lar):");
        for (int kolon : tablo.getSelectedColumns()) raporlamaAlan�.append (String.format (" %d", kolon));
        raporlamaAlan�.append (".\n");
    } // raporlamaAlan�naYaz() metodu sonu...

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
        JFrame �er�eve = new JFrame ("Tablo Se�imi Ve S�ralamas� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_67b yeni��erikPanosu = new J5c_67b(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (100,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        yaz�ls�nM� = args.length > 0? true : false;
        javax.swing.UIManager.put ("swing.boldMetal", Boolean.FALSE); 
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_67b s�n�f� sonu...

/* ��kt�:
**  >java J5c_67b yaz  **
Tablo(8,6)'n�n yeni de�eri:[true]==>(class java.lang.Boolean)'�n bir tiplemesi

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
9.sat�r:  43873313372  Sevim Yava�  Bursa  1 A�ustos 1963  Yok  55  true  �niversite
======================================================================================================

SATIR SE��M� OLAYI==>Akt�el Konum: 8, 6; Se�ilen Sat�r(lar): 8; Se�ilen Kolon(lar): 6.
KOLON SE��M� OLAYI==>Akt�el Konum: 8, 6; Se�ilen Sat�r(lar): 8; Se�ilen Kolon(lar): 6.
SATIR SE��M� OLAYI==>Akt�el Konum: 4, 1; Se�ilen Sat�r(lar): 4; Se�ilen Kolon(lar): 1.
KOLON SE��M� OLAYI==>Akt�el Konum: 4, 1; Se�ilen Sat�r(lar): 4; Se�ilen Kolon(lar): 1.
KOLON SE��M� OLAYI==>Akt�el Konum: 4, 2; Se�ilen Sat�r(lar): 4; Se�ilen Kolon(lar): 1 2.
KOLON SE��M� OLAYI==>Akt�el Konum: 4, 3; Se�ilen Sat�r(lar): 4; Se�ilen Kolon(lar): 1 2 3.
KOLON SE��M� OLAYI==>Akt�el Konum: 4, 4; Se�ilen Sat�r(lar): 4; Se�ilen Kolon(lar): 1 2 3 4.
SATIR SE��M� OLAYI==>Akt�el Konum: 5, 4; Se�ilen Sat�r(lar): 4 5; Se�ilen Kolon(lar): 1 2 3 4.
SATIR SE��M� OLAYI==>Akt�el Konum: 6, 4; Se�ilen Sat�r(lar): 4 5 6; Se�ilen Kolon(lar): 1 2 3 4.
SATIR SE��M� OLAYI==>Akt�el Konum: 7, 4; Se�ilen Sat�r(lar): 4 5 6 7; Se�ilen Kolon(lar): 1 2 3 4.
KOLON SE��M� OLAYI==>Akt�el Konum: 7, 5; Se�ilen Sat�r(lar): 4 5 6 7; Se�ilen Kolon(lar): 1 2 3 4 5.
KOLON SE��M� OLAYI==>Akt�el Konum: 7, 6; Se�ilen Sat�r(lar): 4 5 6 7; Se�ilen Kolon(lar): 1 2 3 4 5 6.
SATIR SE��M� OLAYI==>Akt�el Konum: 8, 6; Se�ilen Sat�r(lar): 4 5 6 7 8; Se�ilen Kolon(lar): 1 2 3 4 5 6.
SATIR SE��M� OLAYI==>Akt�el Konum: 4, 6; Se�ilen Sat�r(lar): 4 5 6 7 8; Se�ilen Kolon(lar): 1 2 3 4 5 6.
KOLON SE��M� OLAYI==>Akt�el Konum: 4, 7; Se�ilen Sat�r(lar): 4 5 6 7 8; Se�ilen Kolon(lar): 1 2 3 4 5 6.
SATIR SE��M� OLAYI==>Akt�el Konum: 4, 7; Se�ilen Sat�r(lar):; Se�ilen Kolon(lar): 1 2 3 4 5 6.
KOLON SE��M� OLAYI==>Akt�el Konum: 4, 7; Se�ilen Sat�r(lar):; Se�ilen Kolon(lar):.
SATIR SE��M� OLAYI==>Akt�el Konum: 7, 2; Se�ilen Sat�r(lar): 7; Se�ilen Kolon(lar): 2.
KOLON SE��M� OLAYI==>Akt�el Konum: 7, 2; Se�ilen Sat�r(lar): 7; Se�ilen Kolon(lar): 2.
KOLON SE��M� OLAYI==>Akt�el Konum: 7, 3; Se�ilen Sat�r(lar): 7; Se�ilen Kolon(lar): 3.
SATIR SE��M� OLAYI==>Akt�el Konum: 8, 3; Se�ilen Sat�r(lar): 8; Se�ilen Kolon(lar): 3.
*/