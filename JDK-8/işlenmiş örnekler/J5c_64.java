// J5c_64.java: TablePrintDemo (TabloyuYazd�rmaG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import javax.swing.table.AbstractTableModel;

import java.text.MessageFormat;

public class J5c_64 extends JPanel implements java.awt.event.ActionListener {
    private boolean yaz�ls�nM� = true;
    private JTable tablo;

    public J5c_64() {// Kurucu...
        super();
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));

        tablo = new JTable (new Tablom());
        tablo.setPreferredScrollableViewportSize (new Dimension (1100, 80));
        //tablo.setFillsViewportHeight (true);
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // Tablolu kayd�rma panosunu yarat�p i�erik panosuna ekleyelim...
        JScrollPane kayd�rmaPanosu = new JScrollPane (tablo);
        add (kayd�rmaPanosu);

        // Tablonun alt�na, tabloyu yaz�c�dan alma butonu ekleyelim...
        JButton yazD��mesi = new JButton ("Yaz");
        yazD��mesi.setAlignmentX (Component.CENTER_ALIGNMENT);
        yazD��mesi.addActionListener (this);
        yazD��mesi.setBackground (Color.BLACK);
        yazD��mesi.setForeground (Color.WHITE);
        add (yazD��mesi);
        setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // T�m renkler...
    } // J5c_64() kurucusu sonu...

    public void actionPerformed (java.awt.event.ActionEvent gerekmez) {
        MessageFormat ba�l�k = new MessageFormat ("Page {0, number, integer}");
        try {tablo.print (JTable.PrintMode.FIT_WIDTH, ba�l�k, null);
        }catch (java.awt.print.PrinterException ist) {System.err.format ("Yazd�r�lam�yor: [%s]%n", ist.getMessage());}
    } // actionPerformed(..) haz�r metodu sonu...

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
        JFrame �er�eve = new JFrame ("Tabloyu Yazd�rma G�sterisi");
        �er�eve.setDefaultCloseOperation (3);
        J5c_64 yeni��erikPanosu = new J5c_64(); // Kurucuyu �a��r�r...
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
} // J5c_64 s�n�f� sonu...