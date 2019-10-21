// J5c_46.java: SharedModelDemo (Payla��lanModelG�sterisi) �rne�i.

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
    JTextArea metinAlan�;
    JList liste;
    JTable tablo;
    String yeniSat�r = "\n";
    ListSelectionModel listeSe�imModeli;

    public J5c_46() {// Kurucu...
        super (new BorderLayout());

        String[] veriBa�l�k = {"T�rk�e", "Frans�zca", "�spanyolca", "�talyanca", "�ngilizce", "Almanca"};
        String[] veriBir = {"bir", "un", "uno", "uno", "one", "eins"};
        String[] veri�ki = {"iki", "deux", "dos", "due", "two", "zwei"};
        String[] veri�� = {"��", "trois", "tres", "tre", "tree", "drei"};
        String[] veriD�rt = {"d�rt", "quatre", "cuatro", "quattro", "four", "fier"};
        String[] veriBe� = {"be�", "cinq", "cinco", "cinque", "five", "f�nf"};
        String[] veriAlt� = {"alt�", "six", "seis", "sei", "six", "sechs"};
        String[] veriYedi = {"yedi", "sept", "siete", "sette", "seven", "sieben"};
        String[] veriSekiz = {"sekiz", "huit", "siete", "sette", "seven", "acht"};
        String[] veriDokuz = {"dokuz", "neuf", "siete", "sette", "seven", "neun"};
        String[] veriOn = {"on", "dix", "siete", "sette", "seven", "zehn"};

        // Modelimizi kural�m...
        Payla��lanVeriModeli veriModeli = new Payla��lanVeriModeli (veriBa�l�k);
        veriModeli.addElement (veriBir);
        veriModeli.addElement (veri�ki);
        veriModeli.addElement (veri��);
        veriModeli.addElement (veriD�rt);
        veriModeli.addElement (veriBe�);
        veriModeli.addElement (veriAlt�);
        veriModeli.addElement (veriYedi);
        veriModeli.addElement (veriSekiz);
        veriModeli.addElement (veriDokuz);
        veriModeli.addElement (veriOn);

        liste = new JList (veriModeli);
        liste.setCellRenderer (new DefaultListCellRenderer() {
            public Component getListCellRendererComponent (JList liste, Object de�er, int i, boolean eh1, boolean eh2) {
                String[] dizi = (String[]) de�er;
                return super.getListCellRendererComponent (liste, dizi[0], i, eh1, eh2);}
        }); // lis.. ifadesi sonu...

        listeSe�imModeli = liste.getSelectionModel();
        listeSe�imModeli.addListSelectionListener (new Payla��lanListeSe�imY�neticisi());
        JScrollPane listeKayd�rmaPanosu = new JScrollPane (liste);

        tablo = new JTable (veriModeli);
        tablo.setSelectionModel (listeSe�imModeli);
        JScrollPane tabloKayd�rmaPanosu = new JScrollPane (tablo);

        // Varsay�l� FlowLayout'u kullanarak se�im kipleri a��l�r kutusunu i�erecek kontrol panelini kural�m...
        JPanel kontrolPaneli = new JPanel();
        String[] se�imKipleri = {"T�KEL_SE��M", "T�KEL_ARALIK_SE��M", "�OKLU_ARALIK_SE��M" };

        final JComboBox a��l�rKutu = new JComboBox (se�imKipleri);
        a��l�rKutu.setSelectedIndex (2); // �lk anda �oklu aral�k...
        a��l�rKutu.addActionListener (new ActionListener() {// Aksiyon dinleyicisine duyarlayal�m...
            public void actionPerformed (ActionEvent olay) {
                String yeniKip = (String)a��l�rKutu.getSelectedItem();
                if (yeniKip.equals ("T�KEL_SE��M")) listeSe�imModeli.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
                else if (yeniKip.equals ("T�KEL_ARALIK_SE��M")) listeSe�imModeli.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                else listeSe�imModeli.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                metinAlan�.append ("----------Se�im kipi: " + yeniKip + "----------" + yeniSat�r);
            } // actionPerformed(..) haz�r metodu sonu...
        }); // a��.. ifadesi sonu...
        kontrolPaneli.add (new JLabel ("Se�im kipi:"));
        kontrolPaneli.add (a��l�rKutu);

        // Metin alan�n� kural�m...
        metinAlan� = new JTextArea (10, 40);
        metinAlan�.append ("D�KKAT: Liste'de t�klay�p se�im, Tablo'da ise �ift t�klay�p de�i�iklik yapabilirsiniz." + yeniSat�r);
        metinAlan�.setEditable (false); // M�dahalesiz...
        JScrollPane metinAlan�Panosu = new JScrollPane (metinAlan�,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        // �st yar�ya listeyi ve tabloyu, alt yar�ya ise se�im kipini ve metin alan�n� serimleyelim...
        JSplitPane b�lmePanosu = new JSplitPane (JSplitPane.VERTICAL_SPLIT);
        add (b�lmePanosu, BorderLayout.CENTER);

        JPanel �stYar�Paneli = new JPanel();
        �stYar�Paneli.setLayout (new BoxLayout (�stYar�Paneli, BoxLayout.X_AXIS));
        JPanel listeKab� = new JPanel (new GridLayout (1,1));
        listeKab�.setBorder (BorderFactory.createTitledBorder ("Liste"));
        listeKab�.add (listeKayd�rmaPanosu);
        JPanel tabloKab� = new JPanel (new GridLayout (1,1));
        tabloKab�.setBorder (BorderFactory.createTitledBorder ("Tablo"));
        tabloKab�.add (tabloKayd�rmaPanosu);
        tabloKayd�rmaPanosu.setPreferredSize (new Dimension (400, 100));
        �stYar�Paneli.setBorder (BorderFactory.createEmptyBorder (5,5,0,5));
        �stYar�Paneli.add (listeKab�);
        �stYar�Paneli.add (tabloKab�);

        �stYar�Paneli.setMinimumSize (new Dimension (500, 50));
        �stYar�Paneli.setPreferredSize (new Dimension (500, 110));
        b�lmePanosu.add (�stYar�Paneli);

        JPanel altYar�Paneli = new JPanel (new BorderLayout());
        altYar�Paneli.add (kontrolPaneli, BorderLayout.NORTH);
        altYar�Paneli.add (metinAlan�Panosu, BorderLayout.CENTER);
        //E�er t�m alt yar� paneli kayd�rmal� olacaksa asgari boy konulabilir...
        //altYar�Paneli.setMinimumSize (new Dimension (500, 50));
        altYar�Paneli.setPreferredSize (new Dimension (500, 135));
        b�lmePanosu.add (altYar�Paneli);
    } // J5c_46() kurucusu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Payla��lan Model G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5c_46();
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifdesi sonu...
    } // main(..) metodu sonu...

    class Payla��lanListeSe�imY�neticisi implements ListSelectionListener {
        public void valueChanged (ListSelectionEvent olay) { 
            ListSelectionModel lsm = (ListSelectionModel)olay.getSource();

            int ilkEndeks = olay.getFirstIndex();
            int sonEndeks = olay.getLastIndex();
            boolean de�i�tiMi = olay.getValueIsAdjusting(); 
            metinAlan�.append (ilkEndeks + " - " + sonEndeks + " endeksleri i�in olay;" +
                    " de�i�ti mi: " + de�i�tiMi + "; se�ili endeksler:");

            if (lsm.isSelectionEmpty()) metinAlan�.append (" <se�ili yok>");
            else {// Hangi endekslerin se�ili oldu�unu bulal�m...
                int k���kEndeks = lsm.getMinSelectionIndex();
                int b�y�kEndeks = lsm.getMaxSelectionIndex();
                for (int i = k���kEndeks; i <= b�y�kEndeks; i++) 
                    if (lsm.isSelectedIndex (i)) metinAlan�.append (" " + i);
            } // else karar� sonu...
            metinAlan�.append (yeniSat�r);
        } // valueChanged(..) haz�r metodu sonu...
    } // Payla��lanListeSe�imY�neticisi s�n�f� sonu...

    class Payla��lanVeriModeli extends DefaultListModel implements TableModel {
        public String[] s�tunAdlar�;

        public Payla��lanVeriModeli (String[] s�tunAdlar�) {
            super();
            this.s�tunAdlar� = s�tunAdlar�;
        } // Payla��lanVeriModeli(..) kurucusu sonu...

        public void rowChanged (int sat�r) {fireContentsChanged (this, sat�r, sat�r);}

        private TableModel tabloModeli = new AbstractTableModel() {
            public String getColumnName (int kolon) {return s�tunAdlar�[kolon];}
            public int getRowCount() {return size();}
            public int getColumnCount() {return s�tunAdlar�.length;}
            public boolean isCellEditable(int sat�r, int s�tun) {return true;}

            public Object getValueAt (int sat�r, int s�tun) {
                String[] sat�rVerisi = (String [])elementAt (sat�r);
                return sat�rVerisi[s�tun];
            } // getValueAt(..) haz�r metodu sonu...

            public void setValueAt (Object de�er, int sat�r, int kolon) {
                String yeniDe�er = (String)de�er;
                String[] sat�rVerisi = (String [])elementAt (sat�r);
                sat�rVerisi[kolon] = yeniDe�er;
                fireTableCellUpdated (sat�r, kolon); // Tablo olay�...
                rowChanged (sat�r); // Liste olay�...
            } // setValueAt(..) haz�r metodu sonu...
        }; // pri.. ifadesi sonu...

        // TableModel aray�z�n� y�r�t�r...
        public int getRowCount() {return tabloModeli.getRowCount();}
        public int getColumnCount() {return tabloModeli.getColumnCount();}
        public String getColumnName (int kolonEndeksi) {return tabloModeli.getColumnName (kolonEndeksi);}
        public Class getColumnClass (int kolonEndeksi) {return tabloModeli.getColumnClass (kolonEndeksi);}
        public boolean isCellEditable (int sat�rEndeksi, int kolonEndeksi) {return tabloModeli.isCellEditable (sat�rEndeksi, kolonEndeksi);}
        public Object getValueAt (int sat�rEndeksi, int kolonEndeksi) {return tabloModeli.getValueAt (sat�rEndeksi, kolonEndeksi);}
        public void setValueAt (Object de�er, int sat�rEndeksi, int kolonEndeksi) {tabloModeli.setValueAt (de�er, sat�rEndeksi, kolonEndeksi);}
        public void addTableModelListener (TableModelListener dinleyici) {tabloModeli.addTableModelListener (dinleyici);}
        public void removeTableModelListener (TableModelListener dinleyici) {tabloModeli.removeTableModelListener (dinleyici);}
    } // Payla��lanVeriModeli s�n�f� sonu...
} // J5c_46 s�n�f� sonu...