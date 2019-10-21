// J5c_62.java: TableFilterDemo (TabloFilitresiG�sterisi) �rne�i.

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

/* Gerekli java dosyas�:
 * J5c_51x2.java=SpringUtilities.java
 */
public class J5c_62 extends JPanel {
    private boolean yaz�ls�nM� = true;
    private JTable tablo;
    private JTextField filitreMetinSat�r�;
    private JTextField durumMetinSat�r�;
    private TableRowSorter<Tablom> s�ralay�c�;

    public J5c_62() {// Kurucu...
        super();
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));

        // Herbir kolonu artan/azalan s�ralay�c�l� bir tablo yaratal�m...
        Tablom tabloModelim = new Tablom();
        s�ralay�c� = new TableRowSorter<Tablom>(tabloModelim);
        tablo = new JTable (tabloModelim);
        tablo.setRowSorter (s�ralay�c�);
        tablo.setPreferredScrollableViewportSize (new Dimension (1100, 80));
        tablo.setFillsViewportHeight (true);
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        tablo.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);

        /* Se�ilen sat�r�n artan/azalan s�ralamal� g�r�nt�deki akt�el sat�r no'sunu
         * ve ilk modeldeki sat�r no'sunu durum metin sat�r�na birlikte yazal�m.*/
        tablo.getSelectionModel().addListSelectionListener (
                new ListSelectionListener() {
                    public void valueChanged (ListSelectionEvent olay) {
                        int se�ilenSat�r = tablo.getSelectedRow();
                        if (se�ilenSat�r < 0) // Se�ilen filitre d���...
                            durumMetinSat�r�.setText ("");
                        else {int modelSat�r� = tablo.convertRowIndexToModel (se�ilenSat�r);
                            durumMetinSat�r�.setText (String.format ("G�ncel s�ral� g�r�nt�deki sat�r no: %d. " +
                                    "Orijinal modeldeki sat�r no: %d.", se�ilenSat�r, modelSat�r�));
                        } // else karar� sonu...
                    } // valueChanged(..) haz�r metodu sonu...
                } // ListSelectionListener() haz�r s�n�f� sonu...
        ); // tab.. ifadesi sonu...

        // Kayd�rma panosunu tabloya giydirip i�erik panosuna ekleyelim...
        JScrollPane kayd�rmaPanosu = new JScrollPane (tablo);
        add (kayd�rmaPanosu);

        // Filitre metin sat�r� and durum metin sat�r� i�in ayr� bir form paneli kullanal�m...
        JPanel formPaneli = new JPanel (new SpringLayout());
        JLabel etiket1 = new JLabel ("Filitre Metni:", SwingConstants.TRAILING); //Sa�a yasl�...
        filitreMetinSat�r� = new JTextField();
        // Filitre metin sat�r� de�i�ti�inde, yeniFilitre metodunu i�letelim...
        filitreMetinSat�r�.getDocument().addDocumentListener (
            new DocumentListener() {
                public void changedUpdate (DocumentEvent olay) {yeniFilitre();}
                public void insertUpdate (DocumentEvent olay) {yeniFilitre();}
                public void removeUpdate (DocumentEvent olay) {yeniFilitre();}
            } // DocumentListener() haz�r s�n�f� sonu...
        ); // fil.. ifadesi sonu...
        etiket1.setLabelFor (filitreMetinSat�r�);
        etiket1.setForeground (Color.WHITE);
        formPaneli.add (etiket1);
        formPaneli.add (filitreMetinSat�r�);

        JLabel etiket2 = new JLabel ("Durum:", SwingConstants.TRAILING);
        durumMetinSat�r� = new JTextField();
        etiket2.setLabelFor (durumMetinSat�r�);
        etiket2.setForeground (Color.WHITE);
        formPaneli.add (etiket2);
        formPaneli.add (durumMetinSat�r�);

        J5c_51x2.kesifIzgaraYap (formPaneli, 2, 2, 6, 6, 6, 6);
        add (formPaneli);
        formPaneli.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
    } // J5c_62() kurucusu sonu...

    /* Filitre metin sat�r�ndaki regex'in her de�i�imde g�ncelleyelim.
    * RegEx sadece ikinci s�tun (Ad Soyad) i�in ge�erlidir.*/
    private void yeniFilitre() {
        RowFilter<Tablom, Object> sat�rFilitresi = null;
        // E�er akt�el regex ��z�mlenemezse bir�ey yapmadan d�nelim...
        //if (String.valueOf (filitreMetinSat�r�).compareTo ("A") >= 0) {
        try {sat�rFilitresi = RowFilter.regexFilter (filitreMetinSat�r�.getText(), 1);
        }catch (java.util.regex.PatternSyntaxException ist) {return;}
        s�ralay�c�.setRowFilter (sat�rFilitresi);
        /*}else {
        try {sat�rFilitresi = RowFilter.regexFilter (filitreMetinSat�r�.getText(), 0); // TC No'ya g�re filitreler...
        }catch (java.util.regex.PatternSyntaxException ist) {return;}
        s�ralay�c�.setRowFilter (sat�rFilitresi);
        }*/
    } // yeniFilitre() metodu sonu...

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
        JFrame �er�eve = new JFrame ("Tablo Filitresi G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_62 yeni��erikPanosu = new J5c_62(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (100,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_62 s�n�f� sonu...

/* ��kt�:
**  >java J5c_62  **
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
*/