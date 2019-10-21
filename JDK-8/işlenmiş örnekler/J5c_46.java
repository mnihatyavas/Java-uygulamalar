// J5c_46.java: SharedModelDemo (PaylaþýlanModelGösterisi) örneði.

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListCellRenderer;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelListener;

import javax.swing.table.TableModel;
import javax.swing.table.AbstractTableModel;

public class J5c_46 extends JPanel {
    JTextArea metinAlaný;
    JList liste;
    JTable tablo;
    String yeniSatýr = "\n";
    ListSelectionModel listeSeçimModeli;

    public J5c_46() {// Kurucu...
        super (new BorderLayout());

        String[] veriBaþlýk = {"Türkçe", "Fransýzca", "Ýspanyolca", "Ýtalyanca", "Ýngilizce", "Almanca"};
        String[] veriBir = {"bir", "un", "uno", "uno", "one", "eins"};
        String[] veriÝki = {"iki", "deux", "dos", "due", "two", "zwei"};
        String[] veriÜç = {"üç", "trois", "tres", "tre", "tree", "drei"};
        String[] veriDört = {"dört", "quatre", "cuatro", "quattro", "four", "fier"};
        String[] veriBeþ = {"beþ", "cinq", "cinco", "cinque", "five", "fünf"};
        String[] veriAltý = {"altý", "six", "seis", "sei", "six", "sechs"};
        String[] veriYedi = {"yedi", "sept", "siete", "sette", "seven", "sieben"};
        String[] veriSekiz = {"sekiz", "huit", "siete", "sette", "seven", "acht"};
        String[] veriDokuz = {"dokuz", "neuf", "siete", "sette", "seven", "neun"};
        String[] veriOn = {"on", "dix", "siete", "sette", "seven", "zehn"};

        // Modelimizi kuralým...
        PaylaþýlanVeriModeli veriModeli = new PaylaþýlanVeriModeli (veriBaþlýk);
        veriModeli.addElement (veriBir);
        veriModeli.addElement (veriÝki);
        veriModeli.addElement (veriÜç);
        veriModeli.addElement (veriDört);
        veriModeli.addElement (veriBeþ);
        veriModeli.addElement (veriAltý);
        veriModeli.addElement (veriYedi);
        veriModeli.addElement (veriSekiz);
        veriModeli.addElement (veriDokuz);
        veriModeli.addElement (veriOn);

        liste = new JList (veriModeli);
        liste.setCellRenderer (new DefaultListCellRenderer() {
            public Component getListCellRendererComponent (JList liste, Object deðer, int i, boolean eh1, boolean eh2) {
                String[] dizi = (String[]) deðer;
                return super.getListCellRendererComponent (liste, dizi[0], i, eh1, eh2);}
        }); // lis.. ifadesi sonu...

        listeSeçimModeli = liste.getSelectionModel();
        listeSeçimModeli.addListSelectionListener (new PaylaþýlanListeSeçimYöneticisi());
        JScrollPane listeKaydýrmaPanosu = new JScrollPane (liste);

        tablo = new JTable (veriModeli);
        tablo.setSelectionModel (listeSeçimModeli);
        JScrollPane tabloKaydýrmaPanosu = new JScrollPane (tablo);

        // Varsayýlý FlowLayout'u kullanarak seçim kipleri açýlýr kutusunu içerecek kontrol panelini kuralým...
        JPanel kontrolPaneli = new JPanel();
        String[] seçimKipleri = {"TÝKEL_SEÇÝM", "TÝKEL_ARALIK_SEÇÝM", "ÇOKLU_ARALIK_SEÇÝM" };

        final JComboBox açýlýrKutu = new JComboBox (seçimKipleri);
        açýlýrKutu.setSelectedIndex (2); // Ýlk anda çoklu aralýk...
        açýlýrKutu.addActionListener (new ActionListener() {// Aksiyon dinleyicisine duyarlayalým...
            public void actionPerformed (ActionEvent olay) {
                String yeniKip = (String)açýlýrKutu.getSelectedItem();
                if (yeniKip.equals ("TÝKEL_SEÇÝM")) listeSeçimModeli.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
                else if (yeniKip.equals ("TÝKEL_ARALIK_SEÇÝM")) listeSeçimModeli.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                else listeSeçimModeli.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                metinAlaný.append ("----------Seçim kipi: " + yeniKip + "----------" + yeniSatýr);
            } // actionPerformed(..) hazýr metodu sonu...
        }); // açý.. ifadesi sonu...
        kontrolPaneli.add (new JLabel ("Seçim kipi:"));
        kontrolPaneli.add (açýlýrKutu);

        // Metin alanýný kuralým...
        metinAlaný = new JTextArea (10, 40);
        metinAlaný.append ("DÝKKAT: Liste'de týklayýp seçim, Tablo'da ise çift týklayýp deðiþiklik yapabilirsiniz." + yeniSatýr);
        metinAlaný.setEditable (false); // Müdahalesiz...
        JScrollPane metinAlanýPanosu = new JScrollPane (metinAlaný,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Üst yarýya listeyi ve tabloyu, alt yarýya ise seçim kipini ve metin alanýný serimleyelim...
        JSplitPane bölmePanosu = new JSplitPane (JSplitPane.VERTICAL_SPLIT);
        add (bölmePanosu, BorderLayout.CENTER);

        JPanel üstYarýPaneli = new JPanel();
        üstYarýPaneli.setLayout (new BoxLayout (üstYarýPaneli, BoxLayout.X_AXIS));
        JPanel listeKabý = new JPanel (new GridLayout (1,1));
        listeKabý.setBorder (BorderFactory.createTitledBorder ("Liste"));
        listeKabý.add (listeKaydýrmaPanosu);
        JPanel tabloKabý = new JPanel (new GridLayout (1,1));
        tabloKabý.setBorder (BorderFactory.createTitledBorder ("Tablo"));
        tabloKabý.add (tabloKaydýrmaPanosu);
        tabloKaydýrmaPanosu.setPreferredSize (new Dimension (400, 100));
        üstYarýPaneli.setBorder (BorderFactory.createEmptyBorder (5,5,0,5));
        üstYarýPaneli.add (listeKabý);
        üstYarýPaneli.add (tabloKabý);

        üstYarýPaneli.setMinimumSize (new Dimension (500, 50));
        üstYarýPaneli.setPreferredSize (new Dimension (500, 110));
        bölmePanosu.add (üstYarýPaneli);

        JPanel altYarýPaneli = new JPanel (new BorderLayout());
        altYarýPaneli.add (kontrolPaneli, BorderLayout.NORTH);
        altYarýPaneli.add (metinAlanýPanosu, BorderLayout.CENTER);
        //Eðer tüm alt yarý paneli kaydýrmalý olacaksa asgari boy konulabilir...
        //altYarýPaneli.setMinimumSize (new Dimension (500, 50));
        altYarýPaneli.setPreferredSize (new Dimension (500, 135));
        bölmePanosu.add (altYarýPaneli);
    } // J5c_46() kurucusu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Paylaþýlan Model Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5c_46();
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifdesi sonu...
    } // main(..) metodu sonu...

    class PaylaþýlanListeSeçimYöneticisi implements ListSelectionListener {
        public void valueChanged (ListSelectionEvent olay) { 
            ListSelectionModel lsm = (ListSelectionModel)olay.getSource();

            int ilkEndeks = olay.getFirstIndex();
            int sonEndeks = olay.getLastIndex();
            boolean deðiþtiMi = olay.getValueIsAdjusting(); 
            metinAlaný.append (ilkEndeks + " - " + sonEndeks + " endeksleri için olay;" +
                    " deðiþti mi: " + deðiþtiMi + "; seçili endeksler:");

            if (lsm.isSelectionEmpty()) metinAlaný.append (" <seçili yok>");
            else {// Hangi endekslerin seçili olduðunu bulalým...
                int küçükEndeks = lsm.getMinSelectionIndex();
                int büyükEndeks = lsm.getMaxSelectionIndex();
                for (int i = küçükEndeks; i <= büyükEndeks; i++) 
                    if (lsm.isSelectedIndex (i)) metinAlaný.append (" " + i);
            } // else kararý sonu...
            metinAlaný.append (yeniSatýr);
        } // valueChanged(..) hazýr metodu sonu...
    } // PaylaþýlanListeSeçimYöneticisi sýnýfý sonu...

    class PaylaþýlanVeriModeli extends DefaultListModel implements TableModel {
        public String[] sütunAdlarý;

        public PaylaþýlanVeriModeli (String[] sütunAdlarý) {
            super();
            this.sütunAdlarý = sütunAdlarý;
        } // PaylaþýlanVeriModeli(..) kurucusu sonu...

        public void rowChanged (int satýr) {fireContentsChanged (this, satýr, satýr);}

        private TableModel tabloModeli = new AbstractTableModel() {
            public String getColumnName (int kolon) {return sütunAdlarý[kolon];}
            public int getRowCount() {return size();}
            public int getColumnCount() {return sütunAdlarý.length;}
            public boolean isCellEditable(int satýr, int sütun) {return true;}

            public Object getValueAt (int satýr, int sütun) {
                String[] satýrVerisi = (String [])elementAt (satýr);
                return satýrVerisi[sütun];
            } // getValueAt(..) hazýr metodu sonu...

            public void setValueAt (Object deðer, int satýr, int kolon) {
                String yeniDeðer = (String)deðer;
                String[] satýrVerisi = (String [])elementAt (satýr);
                satýrVerisi[kolon] = yeniDeðer;
                fireTableCellUpdated (satýr, kolon); // Tablo olayý...
                rowChanged (satýr); // Liste olayý...
            } // setValueAt(..) hazýr metodu sonu...
        }; // pri.. ifadesi sonu...

        // TableModel arayüzünü yürütür...
        public int getRowCount() {return tabloModeli.getRowCount();}
        public int getColumnCount() {return tabloModeli.getColumnCount();}
        public String getColumnName (int kolonEndeksi) {return tabloModeli.getColumnName (kolonEndeksi);}
        public Class getColumnClass (int kolonEndeksi) {return tabloModeli.getColumnClass (kolonEndeksi);}
        public boolean isCellEditable (int satýrEndeksi, int kolonEndeksi) {return tabloModeli.isCellEditable (satýrEndeksi, kolonEndeksi);}
        public Object getValueAt (int satýrEndeksi, int kolonEndeksi) {return tabloModeli.getValueAt (satýrEndeksi, kolonEndeksi);}
        public void setValueAt (Object deðer, int satýrEndeksi, int kolonEndeksi) {tabloModeli.setValueAt (deðer, satýrEndeksi, kolonEndeksi);}
        public void addTableModelListener (TableModelListener dinleyici) {tabloModeli.addTableModelListener (dinleyici);}
        public void removeTableModelListener (TableModelListener dinleyici) {tabloModeli.removeTableModelListener (dinleyici);}
    } // PaylaþýlanVeriModeli sýnýfý sonu...
} // J5c_46 sýnýfý sonu...