// J5e_15.java: TablePrintDemo1 (TabloyuYazd�rmaG�sterisi1) �rne�i.

import java.awt.Component;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.event.TableModelListener;

import java.text.MessageFormat;

/* Gereken resimler:
 *  resim/tablo/ge�ti.png
 *  resim/tablo/kald�.png
 */
public class J5e_15 extends JFrame {
    // Ge�ti/�entik kald�/X i�aretleri...
    private static final ImageIcon ge�ti�konu = new ImageIcon (J5e_15.class.getResource ("resim/tablo/ge�ti.png"));
    private static final ImageIcon kald��konu = new ImageIcon (J5e_15.class.getResource ("resim/tablo/kald�.png"));

    // GUI komponentleri...
    private JPanel i�erikPanosu;
    private JLabel notlarEtiketi;
    private JTable notlarTablosu;
    private JScrollPane kayd�rma;
    private JCheckBox yazDiyalo�unuG�ster;
    private JCheckBox etkile�imKutusu;
    private JCheckBox geni�li�iS��d�rKutusu;
    private JButton yazd�rD��mesi;

    // Alts�n�flarca de�i�tirilebilecek/etkinsizle�tirilebilecek korumal� de�i�kenler...
    protected JCheckBox sayfaBa��Kutusu;
    protected JCheckBox sayfaSonuKutusu;
    protected JTextField sayfaBa��MetinSat�r�;
    protected JTextField sayfaSonuMetinSat�r�;

    public J5e_15() {// Kurucu...
        super ("Tablo Yazd�rma G�sterisi 1");

        notlarEtiketi = new JLabel ("D�nem Sonu Not Ortalamalar� - F�Z�K 101");
        notlarEtiketi.setFont (new Font ("Diyalog", Font.BOLD, 16));

        notlarTablosu = tabloyuYarat (new NotlarModeli());
        notlarTablosu.setFillsViewportHeight (true);
        notlarTablosu.setRowHeight (24);

        // "Durum" kolonuna kendi sunucumuzu kural�m...
        notlarTablosu.getColumn ("Durumu").setCellRenderer (durumSunucusunuYarat());

        kayd�rma = new JScrollPane (notlarTablosu);

        String alet�pucuMetni;

        alet�pucuMetni = "Bir sayfa ba�l��� ekle";
        sayfaBa��Kutusu = new JCheckBox ("Ba�l�k:", true);
        sayfaBa��Kutusu.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                sayfaBa��MetinSat�r�.setEnabled (sayfaBa��Kutusu.isSelected());
            } // actionPerformed(..) haz�r metodu sonu...
        }); // say.. ifadesi sonu...
        sayfaBa��Kutusu.setToolTipText (alet�pucuMetni);

        alet�pucuMetni = "Sayfa Ba�l��� (Sayfa Numaras�n� Dahil Etmek ��in {0} Kullan�n)";
        sayfaBa��MetinSat�r� = new JTextField ("D�nem Sonu Not Ortalamalar� - F�Z�K 101");
        sayfaBa��MetinSat�r�.setToolTipText (alet�pucuMetni);

        alet�pucuMetni = "Bir sayfa sonlu�u ekle";
        sayfaSonuKutusu = new JCheckBox ("Sonluk:", true);
        sayfaSonuKutusu.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                sayfaSonuMetinSat�r�.setEnabled (sayfaSonuKutusu.isSelected());
            } // actionPerformed(..) haz�r metodu sonu...
        }); // say.. ifadesi sonu...
        sayfaSonuKutusu.setToolTipText (alet�pucuMetni);

        alet�pucuMetni = "Sayfa Sonlu�u (Sayfa Numaras�n� Dahil Etmek ��in {0} Kullan�n)";
        sayfaSonuMetinSat�r� = new JTextField ("Sayfa {0}");
        sayfaSonuMetinSat�r�.setToolTipText (alet�pucuMetni);

        alet�pucuMetni = "Yazd�rma �ncesi Yaz Diyalo�unu G�ster";
        yazDiyalo�unuG�ster = new JCheckBox ("Yaz diyalo�unu g�ster", true);
        yazDiyalo�unuG�ster.setToolTipText (alet�pucuMetni);
        yazDiyalo�unuG�ster.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                if (!yazDiyalo�unuG�ster.isSelected()) {
                    JOptionPane.showMessageDialog (
                        J5e_15.this,
                        "E�er Yaz Diyalo�u G�sterilmiyorsa varsay�l� yaz�c� kullan�lacakt�r.",
                        "Mesaj Yaz�l�yor",
                        JOptionPane.INFORMATION_MESSAGE);
                } // if karar� sonu...
            } // actionPerformed(..) haz�r metodu sonu...
        }); // yaz.. ifadesi sonu...

        alet�pucuMetni = "GUI Duyarl� Olacak ve Yazd�rma Esnas�nda bir Stat� Diyalo�u G�sterilecek";
        etkile�imKutusu = new JCheckBox ("Etkile�imli (Stat� diyalo�unu g�ster)", true);
        etkile�imKutusu.setToolTipText (alet�pucuMetni);
        etkile�imKutusu.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                if (!etkile�imKutusu.isSelected()) {
                    JOptionPane.showMessageDialog (
                        J5e_15.this,
                        "Etkile�imli de�ilse, GUI yazd�rma esnas�nda tamamen bloklanacak.",
                        "Mesaj Yazd�r�l�yor",
                        JOptionPane.INFORMATION_MESSAGE);
                } // if karar� sonu...
            } // actionPerformed(..) metodu sonu...
        }); // etk.. ifadesi sonu...

        alet�pucuMetni = "T�m Tablo Geni�li�i Bir Sayfaya S��d�r�lmak ��in Daralt�lacak";
        geni�li�iS��d�rKutusu = new JCheckBox ("Geni�li�i yazd�r�lacak sayfaya uydur", true);
        geni�li�iS��d�rKutusu.setToolTipText (alet�pucuMetni);

        alet�pucuMetni = "Tabloyu Yazd�r";
        yazd�rD��mesi = new JButton ("Yazd�r");
        yazd�rD��mesi.setToolTipText (alet�pucuMetni);
        yazd�rD��mesi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {notlarTablosunuYazd�r();}
        }); // yaz.. ifadesi sonu...

        i�erikPanosu = new JPanel();
        komponentleri��erikPanosunaEkle();
        setContentPane (i�erikPanosu);

        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        setSize (700, 600);
        setLocationRelativeTo (null);
    } // J5e_15() kurucusu sonu...

    protected JTable tabloyuYarat (TableModel model) {return new JTable (model);}
    protected TableCellRenderer durumSunucusunuYarat() {return new DurumKolonuSunucusu();}

    private void notlarTablosunuYazd�r() {
        // GUI komponentlerinden yazd�rma �zelliklerini alal�m...
        MessageFormat ba�l�k = null;
        if (sayfaBa��Kutusu.isSelected()) ba�l�k = new MessageFormat (sayfaBa��MetinSat�r�.getText());

        MessageFormat sonluk = null;
        if (sayfaSonuKutusu.isSelected()) sonluk = new MessageFormat (sayfaSonuMetinSat�r�.getText());

        boolean geni�li�iS��d�rM� = geni�li�iS��d�rKutusu.isSelected();
        boolean yazDiyalo�unuG�sterMi = yazDiyalo�unuG�ster.isSelected();
        boolean etkile�imMi = etkile�imKutusu.isSelected();

        JTable.PrintMode yazd�rmaKipi = geni�li�iS��d�rM� ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;

        try {// Tabloyu yazd�r...
            boolean bittiMi = notlarTablosu.print (
                    yazd�rmaKipi, ba�l�k, sonluk, yazDiyalo�unuG�sterMi, null, etkile�imMi, null);
            if (bittiMi) JOptionPane.showMessageDialog (this,
                    "Yazd�rma Tamamland�", "Yazd�rma Sonucu", JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog (this,
                    "Yazd�rma �ptal Edildi", "Yazd�rma Sonucu", JOptionPane.INFORMATION_MESSAGE);
        }catch (PrinterException ist) {JOptionPane.showMessageDialog (this,
                "Yazd�rma Ba�ar�s�z: " + ist.getMessage(), "Yazd�rma Sonucu", JOptionPane.ERROR_MESSAGE);
        } // try-catch blo�u sonu...
    } // notlarTablosunuYazd�r() metodu sonu...

    private void komponentleri��erikPanosunaEkle() {
        JPanel altPanel = new JPanel();
        altPanel.setBorder (BorderFactory.createTitledBorder ("Yazd�r�yor"));

        GroupLayout altPanelYerle�imi = new GroupLayout (altPanel);
        altPanel.setLayout (altPanelYerle�imi);
        altPanelYerle�imi.setHorizontalGroup (
            altPanelYerle�imi.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup (altPanelYerle�imi.createSequentialGroup()
                .addContainerGap()
                .addGroup (altPanelYerle�imi.createParallelGroup (GroupLayout.Alignment.LEADING)
                    .addComponent (sayfaBa��Kutusu)
                    .addComponent (sayfaSonuKutusu))
                .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                .addGroup (altPanelYerle�imi.createParallelGroup (GroupLayout.Alignment.LEADING, false)
                    .addComponent (sayfaSonuMetinSat�r�)
                    .addComponent (sayfaBa��MetinSat�r�, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addPreferredGap (LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup (altPanelYerle�imi.createParallelGroup (GroupLayout.Alignment.LEADING, false)
                    .addGroup (altPanelYerle�imi.createSequentialGroup()
                        .addComponent (geni�li�iS��d�rKutusu)
                        .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent (yazd�rD��mesi))
                    .addGroup (altPanelYerle�imi.createSequentialGroup()
                        .addComponent (yazDiyalo�unuG�ster)
                        .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent (etkile�imKutusu)))
                .addContainerGap (GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        ); // Yatay grup alt panel yerle�imi tamamland�...
        altPanelYerle�imi.setVerticalGroup (
            altPanelYerle�imi.createParallelGroup (GroupLayout.Alignment.LEADING)
            .addGroup (altPanelYerle�imi.createSequentialGroup()
                .addGroup (altPanelYerle�imi.createParallelGroup (GroupLayout.Alignment.BASELINE)
                    .addComponent (sayfaBa��Kutusu)
                    .addComponent (sayfaBa��MetinSat�r�, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent (etkile�imKutusu)
                    .addComponent (yazDiyalo�unuG�ster))
                .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                .addGroup (altPanelYerle�imi.createParallelGroup (GroupLayout.Alignment.BASELINE)
                    .addComponent (sayfaSonuKutusu)
                    .addComponent (sayfaSonuMetinSat�r�, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent (geni�li�iS��d�rKutusu)
                    .addComponent (yazd�rD��mesi))
                .addContainerGap (GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        ); // Dikey grup alt panel yerle�imi tamamland�...

        GroupLayout i�erikPanosuYerle�imi = new GroupLayout (i�erikPanosu);
        i�erikPanosu.setLayout (i�erikPanosuYerle�imi);
        i�erikPanosuYerle�imi.setHorizontalGroup (
            i�erikPanosuYerle�imi.createParallelGroup (GroupLayout.Alignment.LEADING)
            .addGroup (i�erikPanosuYerle�imi.createSequentialGroup()
                .addContainerGap()
                .addGroup (i�erikPanosuYerle�imi.createParallelGroup (GroupLayout.Alignment.LEADING)
                    .addComponent (kayd�rma, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addComponent (notlarEtiketi)
                    .addComponent (altPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        ); // Yatay grup (ba�l�k, tablo ve alt panelli) i�erik panosu yerle�imi tamamland�...
        i�erikPanosuYerle�imi.setVerticalGroup (
            i�erikPanosuYerle�imi.createParallelGroup (GroupLayout.Alignment.LEADING)
            .addGroup (i�erikPanosuYerle�imi.createSequentialGroup()
                .addContainerGap()
                .addComponent (notlarEtiketi)
                .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                .addComponent (kayd�rma, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                .addComponent (altPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        ); // Dikey grup (ba�l�k, tablo ve alt panelli) i�erik panosu yerle�imi tamamland�...
    } // komponentleri��erikPanosunaEkle() metodu sonu...

    private static class NotlarModeli implements TableModel {
        private final Object[][] NOTLAR = {
            {"Agnew", "Kieran", 80, 74, 75, 93},
            {"Albertson", "Jack", 90, 65, 88, 79},
            {"Anderson", "Mischa", 34, 45, 48, 59},
            {"Andrews", "Danielle", 50, 56, 55, 44},
            {"Baja", "Ron", 32, 23, 55, 67},
            {"Barry", "Douglas", 46, 66, 77, 90},
            {"Davis", "Lacy", 99, 100, 98, 97},
            {"Egelstein", "Harold", 34, 58, 76, 89},
            {"Elens", "Xavier", 35, 66, 49, 47},
            {"Elmer", "Thomas", 50, 32, 51, 60},
            {"Farras", "Elena", 77, 51, 75, 55},
            {"Filison", "Paulo", 88, 87, 77, 52},
            {"Gabon", "Parvati", 9, 15, 35, 86},
            {"Hickey", "Shannon", 95, 89, 95, 100},
            {"Ingles", "Jaime", 75, 65, 55, 95},
            {"Instein", "Payton", 51, 56, 51, 84},
            {"Jackson", "Donald", 94, 78, 97, 13},
            {"Jefferson", "Lynn", 21, 51, 20, 74},
            {"Johnson", "Tanya", 11, 52, 80, 48},
            {"Kimble", "James", 18, 50, 90, 54},
            {"Kolson", "Laura", 98, 88, 97, 99},
            {"Leigh", "Tasha", 85, 78, 48, 100},
            {"Lombardi", "Leonard", 68, 54, 97, 24},
            {"Manning", "Havvy", 59, 54, 9, 18},
            {"McNichol", "Vivian", 88, 99, 58, 87},
            {"Michaels", "Daniel", 97, 95, 54, 99},
            {"Nicholson", "Alex", 24, 100, 55, 100},
            {"Nimble", "Tonya", 41, 33, 33, 81},
            {"Onning", "Wehhoh", 45, 65, 32, 24},
            {"Opals", "Diamond", 98, 59, 12, 11},
            {"Osser", "Michael", 55, 54, 31, 87},
            {"Paton", "Geoff", 68, 22, 31, 80},
            {"Plumber", "Ester", 58, 20, 28, 98},
            {"Robbins", "Noah", 99, 12, 87, 64},
            {"Robinson", "Jenny", 65, 10, 98, 66},
            {"Ronald", "Dmitri", 25, 9, 98, 61},
            {"Sabo", "Polly", 20, 68, 82, 50},
            {"Silverstone", "Dina", 31, 62, 54, 58},
            {"Singleton", "Alyssa", 50, 30, 98, 88},
            {"Stevens", "Cameron", 89, 8, 81, 56},
            {"Talbert", "Will", 34, 80, 81, 84},
            {"Trimble", "Vicky", 58, 65, 98, 54},
            {"Tussle", "Paulo", 55, 55, 88, 55},
            {"Umber", "Gus", 90, 87, 85, 55},
            {"Valhalla", "Yohan", 60, 77, 62, 89},
            {"Viola", "Michel", 31, 84, 62, 68},
            {"Wanderer", "Sean", 24, 51, 85, 95},
            {"West", "North", 88, 23, 81, 87},
            {"Xavier", "Kerry", 91, 99, 24, 84},
            {"Xu", "Richard", 99, 58, 20, 24},
            {"Ying", "Lina", 85, 99, 89, 90},
            {"Yee", "Tong", 95, 65, 74, 64},
        }; // NOTLAR dizisi sonu...

        public void setValueAt (Object de�er, int sat�rEndeksi, int kolonEndeksi) {}
        public void addTableModelListener (TableModelListener dinleyici) {}
        public void removeTableModelListener (TableModelListener dinleyici) {}
        public boolean isCellEditable (int sat�rEndeksi, int kolonEndeksi) {return false;} // M�dahalesiz...
        public int getRowCount() {return NOTLAR.length;}
        public int getColumnCount() {return 8;}

        public Class<?> getColumnClass (int kolon) {
            switch (kolon) {
                case 0:
                case 1: return String.class;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6: return Integer.class;
                case 7: return Boolean.class;
            } // switch-case blo�u sonu...
            throw new AssertionError ("Ge�ersiz kolon");
        } // getColumnClass(..) haz�r metodu sonu...

        public String getColumnName (int kolon) {
            switch (kolon) {
                case 0: return "Soyad�";
                case 1: return "Ad�";
                case 2: return "�dev 1";
                case 3: return "Vize";
                case 4: return "�dev 2";
                case 5: return "Final";
                case 6: return "Ortalama";
                case 7: return "Durumu";
            } // switch-case blo�u sonu...
            throw new AssertionError ("Ge�ersiz kolon");
        } // getColumnName(..) haz�r metodu sonu...

        public Object getValueAt (int row, int kolon) {
            switch (kolon) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5: return NOTLAR[row][kolon];
                case 6:
                case 7:
                    int ortalama = 0;
                    ortalama += (Integer)NOTLAR[row][2];
                    ortalama += (Integer)NOTLAR[row][3];
                    ortalama += (Integer)NOTLAR[row][4];
                    ortalama += (Integer)NOTLAR[row][5];
                    ortalama = Math.round ((ortalama / 4.0f));
                    if (kolon == 6) return ortalama; // ortalama notu d�nd�r...
                    else return ortalama > 50; // True/false (Ge�ti/kald�) d�nd�r...
            } // switch-case blo�u sonu...
            throw new AssertionError ("Ge�ersiz kolon");
        } // getValueAt(..) haz�r metodu sonu...
    } // NotlarModeli s�n�f� sonu...

    protected static class DurumKolonuSunucusu extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent (
                JTable tablo,
                Object de�er,
                boolean se�iliMi,
                boolean odakl�M�,
                int sat�r,
                int kolon) {
            super.getTableCellRendererComponent (tablo, de�er, se�iliMi, odakl�M�, sat�r, kolon);
            setText ("");
            setHorizontalAlignment (SwingConstants.CENTER);

            // De�er boolean arg�man� do�rultusunda ge�ti/passed.png veya kald�/failed.png ikonunu kural�m...
            boolean durumu = (Boolean)de�er;
            setIcon (durumu ? ge�ti�konu : kald��konu);

            return this;
        } // getTableCellRendererComponent(..) haz�r metodu sonu...
    } // DurumKolonuSunucusu s�n�f� sonu...

    public static void main (final String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", false);
                try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
                }catch (Exception ist) {}
                new J5e_15().setVisible (true); // Kurucuyu �a��r�r...
        }}); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_15 s�n�f� sonu...