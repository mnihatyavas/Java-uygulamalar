// J5e_15.java: TablePrintDemo1 (TabloyuYazdýrmaGösterisi1) örneði.

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
 *  resim/tablo/geçti.png
 *  resim/tablo/kaldý.png
 */
public class J5e_15 extends JFrame {
    // Geçti/çentik kaldý/X iþaretleri...
    private static final ImageIcon geçtiÝkonu = new ImageIcon (J5e_15.class.getResource ("resim/tablo/geçti.png"));
    private static final ImageIcon kaldýÝkonu = new ImageIcon (J5e_15.class.getResource ("resim/tablo/kaldý.png"));

    // GUI komponentleri...
    private JPanel içerikPanosu;
    private JLabel notlarEtiketi;
    private JTable notlarTablosu;
    private JScrollPane kaydýrma;
    private JCheckBox yazDiyaloðunuGöster;
    private JCheckBox etkileþimKutusu;
    private JCheckBox geniþliðiSýðdýrKutusu;
    private JButton yazdýrDüðmesi;

    // Altsýnýflarca deðiþtirilebilecek/etkinsizleþtirilebilecek korumalý deðiþkenler...
    protected JCheckBox sayfaBaþýKutusu;
    protected JCheckBox sayfaSonuKutusu;
    protected JTextField sayfaBaþýMetinSatýrý;
    protected JTextField sayfaSonuMetinSatýrý;

    public J5e_15() {// Kurucu...
        super ("Tablo Yazdýrma Gösterisi 1");

        notlarEtiketi = new JLabel ("Dönem Sonu Not Ortalamalarý - FÝZÝK 101");
        notlarEtiketi.setFont (new Font ("Diyalog", Font.BOLD, 16));

        notlarTablosu = tabloyuYarat (new NotlarModeli());
        notlarTablosu.setFillsViewportHeight (true);
        notlarTablosu.setRowHeight (24);

        // "Durum" kolonuna kendi sunucumuzu kuralým...
        notlarTablosu.getColumn ("Durumu").setCellRenderer (durumSunucusunuYarat());

        kaydýrma = new JScrollPane (notlarTablosu);

        String aletÝpucuMetni;

        aletÝpucuMetni = "Bir sayfa baþlýðý ekle";
        sayfaBaþýKutusu = new JCheckBox ("Baþlýk:", true);
        sayfaBaþýKutusu.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                sayfaBaþýMetinSatýrý.setEnabled (sayfaBaþýKutusu.isSelected());
            } // actionPerformed(..) hazýr metodu sonu...
        }); // say.. ifadesi sonu...
        sayfaBaþýKutusu.setToolTipText (aletÝpucuMetni);

        aletÝpucuMetni = "Sayfa Baþlýðý (Sayfa Numarasýný Dahil Etmek Ýçin {0} Kullanýn)";
        sayfaBaþýMetinSatýrý = new JTextField ("Dönem Sonu Not Ortalamalarý - FÝZÝK 101");
        sayfaBaþýMetinSatýrý.setToolTipText (aletÝpucuMetni);

        aletÝpucuMetni = "Bir sayfa sonluðu ekle";
        sayfaSonuKutusu = new JCheckBox ("Sonluk:", true);
        sayfaSonuKutusu.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                sayfaSonuMetinSatýrý.setEnabled (sayfaSonuKutusu.isSelected());
            } // actionPerformed(..) hazýr metodu sonu...
        }); // say.. ifadesi sonu...
        sayfaSonuKutusu.setToolTipText (aletÝpucuMetni);

        aletÝpucuMetni = "Sayfa Sonluðu (Sayfa Numarasýný Dahil Etmek Ýçin {0} Kullanýn)";
        sayfaSonuMetinSatýrý = new JTextField ("Sayfa {0}");
        sayfaSonuMetinSatýrý.setToolTipText (aletÝpucuMetni);

        aletÝpucuMetni = "Yazdýrma Öncesi Yaz Diyaloðunu Göster";
        yazDiyaloðunuGöster = new JCheckBox ("Yaz diyaloðunu göster", true);
        yazDiyaloðunuGöster.setToolTipText (aletÝpucuMetni);
        yazDiyaloðunuGöster.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                if (!yazDiyaloðunuGöster.isSelected()) {
                    JOptionPane.showMessageDialog (
                        J5e_15.this,
                        "Eðer Yaz Diyaloðu Gösterilmiyorsa varsayýlý yazýcý kullanýlacaktýr.",
                        "Mesaj Yazýlýyor",
                        JOptionPane.INFORMATION_MESSAGE);
                } // if kararý sonu...
            } // actionPerformed(..) hazýr metodu sonu...
        }); // yaz.. ifadesi sonu...

        aletÝpucuMetni = "GUI Duyarlý Olacak ve Yazdýrma Esnasýnda bir Statü Diyaloðu Gösterilecek";
        etkileþimKutusu = new JCheckBox ("Etkileþimli (Statü diyaloðunu göster)", true);
        etkileþimKutusu.setToolTipText (aletÝpucuMetni);
        etkileþimKutusu.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                if (!etkileþimKutusu.isSelected()) {
                    JOptionPane.showMessageDialog (
                        J5e_15.this,
                        "Etkileþimli deðilse, GUI yazdýrma esnasýnda tamamen bloklanacak.",
                        "Mesaj Yazdýrýlýyor",
                        JOptionPane.INFORMATION_MESSAGE);
                } // if kararý sonu...
            } // actionPerformed(..) metodu sonu...
        }); // etk.. ifadesi sonu...

        aletÝpucuMetni = "Tüm Tablo Geniþliði Bir Sayfaya Sýðdýrýlmak Ýçin Daraltýlacak";
        geniþliðiSýðdýrKutusu = new JCheckBox ("Geniþliði yazdýrýlacak sayfaya uydur", true);
        geniþliðiSýðdýrKutusu.setToolTipText (aletÝpucuMetni);

        aletÝpucuMetni = "Tabloyu Yazdýr";
        yazdýrDüðmesi = new JButton ("Yazdýr");
        yazdýrDüðmesi.setToolTipText (aletÝpucuMetni);
        yazdýrDüðmesi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {notlarTablosunuYazdýr();}
        }); // yaz.. ifadesi sonu...

        içerikPanosu = new JPanel();
        komponentleriÝçerikPanosunaEkle();
        setContentPane (içerikPanosu);

        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        setSize (700, 600);
        setLocationRelativeTo (null);
    } // J5e_15() kurucusu sonu...

    protected JTable tabloyuYarat (TableModel model) {return new JTable (model);}
    protected TableCellRenderer durumSunucusunuYarat() {return new DurumKolonuSunucusu();}

    private void notlarTablosunuYazdýr() {
        // GUI komponentlerinden yazdýrma özelliklerini alalým...
        MessageFormat baþlýk = null;
        if (sayfaBaþýKutusu.isSelected()) baþlýk = new MessageFormat (sayfaBaþýMetinSatýrý.getText());

        MessageFormat sonluk = null;
        if (sayfaSonuKutusu.isSelected()) sonluk = new MessageFormat (sayfaSonuMetinSatýrý.getText());

        boolean geniþliðiSýðdýrMý = geniþliðiSýðdýrKutusu.isSelected();
        boolean yazDiyaloðunuGösterMi = yazDiyaloðunuGöster.isSelected();
        boolean etkileþimMi = etkileþimKutusu.isSelected();

        JTable.PrintMode yazdýrmaKipi = geniþliðiSýðdýrMý ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;

        try {// Tabloyu yazdýr...
            boolean bittiMi = notlarTablosu.print (
                    yazdýrmaKipi, baþlýk, sonluk, yazDiyaloðunuGösterMi, null, etkileþimMi, null);
            if (bittiMi) JOptionPane.showMessageDialog (this,
                    "Yazdýrma Tamamlandý", "Yazdýrma Sonucu", JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog (this,
                    "Yazdýrma Ýptal Edildi", "Yazdýrma Sonucu", JOptionPane.INFORMATION_MESSAGE);
        }catch (PrinterException ist) {JOptionPane.showMessageDialog (this,
                "Yazdýrma Baþarýsýz: " + ist.getMessage(), "Yazdýrma Sonucu", JOptionPane.ERROR_MESSAGE);
        } // try-catch bloðu sonu...
    } // notlarTablosunuYazdýr() metodu sonu...

    private void komponentleriÝçerikPanosunaEkle() {
        JPanel altPanel = new JPanel();
        altPanel.setBorder (BorderFactory.createTitledBorder ("Yazdýrýyor"));

        GroupLayout altPanelYerleþimi = new GroupLayout (altPanel);
        altPanel.setLayout (altPanelYerleþimi);
        altPanelYerleþimi.setHorizontalGroup (
            altPanelYerleþimi.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup (altPanelYerleþimi.createSequentialGroup()
                .addContainerGap()
                .addGroup (altPanelYerleþimi.createParallelGroup (GroupLayout.Alignment.LEADING)
                    .addComponent (sayfaBaþýKutusu)
                    .addComponent (sayfaSonuKutusu))
                .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                .addGroup (altPanelYerleþimi.createParallelGroup (GroupLayout.Alignment.LEADING, false)
                    .addComponent (sayfaSonuMetinSatýrý)
                    .addComponent (sayfaBaþýMetinSatýrý, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addPreferredGap (LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup (altPanelYerleþimi.createParallelGroup (GroupLayout.Alignment.LEADING, false)
                    .addGroup (altPanelYerleþimi.createSequentialGroup()
                        .addComponent (geniþliðiSýðdýrKutusu)
                        .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent (yazdýrDüðmesi))
                    .addGroup (altPanelYerleþimi.createSequentialGroup()
                        .addComponent (yazDiyaloðunuGöster)
                        .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent (etkileþimKutusu)))
                .addContainerGap (GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        ); // Yatay grup alt panel yerleþimi tamamlandý...
        altPanelYerleþimi.setVerticalGroup (
            altPanelYerleþimi.createParallelGroup (GroupLayout.Alignment.LEADING)
            .addGroup (altPanelYerleþimi.createSequentialGroup()
                .addGroup (altPanelYerleþimi.createParallelGroup (GroupLayout.Alignment.BASELINE)
                    .addComponent (sayfaBaþýKutusu)
                    .addComponent (sayfaBaþýMetinSatýrý, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent (etkileþimKutusu)
                    .addComponent (yazDiyaloðunuGöster))
                .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                .addGroup (altPanelYerleþimi.createParallelGroup (GroupLayout.Alignment.BASELINE)
                    .addComponent (sayfaSonuKutusu)
                    .addComponent (sayfaSonuMetinSatýrý, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent (geniþliðiSýðdýrKutusu)
                    .addComponent (yazdýrDüðmesi))
                .addContainerGap (GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        ); // Dikey grup alt panel yerleþimi tamamlandý...

        GroupLayout içerikPanosuYerleþimi = new GroupLayout (içerikPanosu);
        içerikPanosu.setLayout (içerikPanosuYerleþimi);
        içerikPanosuYerleþimi.setHorizontalGroup (
            içerikPanosuYerleþimi.createParallelGroup (GroupLayout.Alignment.LEADING)
            .addGroup (içerikPanosuYerleþimi.createSequentialGroup()
                .addContainerGap()
                .addGroup (içerikPanosuYerleþimi.createParallelGroup (GroupLayout.Alignment.LEADING)
                    .addComponent (kaydýrma, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addComponent (notlarEtiketi)
                    .addComponent (altPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        ); // Yatay grup (baþlýk, tablo ve alt panelli) içerik panosu yerleþimi tamamlandý...
        içerikPanosuYerleþimi.setVerticalGroup (
            içerikPanosuYerleþimi.createParallelGroup (GroupLayout.Alignment.LEADING)
            .addGroup (içerikPanosuYerleþimi.createSequentialGroup()
                .addContainerGap()
                .addComponent (notlarEtiketi)
                .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                .addComponent (kaydýrma, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                .addComponent (altPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        ); // Dikey grup (baþlýk, tablo ve alt panelli) içerik panosu yerleþimi tamamlandý...
    } // komponentleriÝçerikPanosunaEkle() metodu sonu...

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

        public void setValueAt (Object deðer, int satýrEndeksi, int kolonEndeksi) {}
        public void addTableModelListener (TableModelListener dinleyici) {}
        public void removeTableModelListener (TableModelListener dinleyici) {}
        public boolean isCellEditable (int satýrEndeksi, int kolonEndeksi) {return false;} // Müdahalesiz...
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
            } // switch-case bloðu sonu...
            throw new AssertionError ("Geçersiz kolon");
        } // getColumnClass(..) hazýr metodu sonu...

        public String getColumnName (int kolon) {
            switch (kolon) {
                case 0: return "Soyadý";
                case 1: return "Adý";
                case 2: return "Ödev 1";
                case 3: return "Vize";
                case 4: return "Ödev 2";
                case 5: return "Final";
                case 6: return "Ortalama";
                case 7: return "Durumu";
            } // switch-case bloðu sonu...
            throw new AssertionError ("Geçersiz kolon");
        } // getColumnName(..) hazýr metodu sonu...

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
                    if (kolon == 6) return ortalama; // ortalama notu döndür...
                    else return ortalama > 50; // True/false (Geçti/kaldý) döndür...
            } // switch-case bloðu sonu...
            throw new AssertionError ("Geçersiz kolon");
        } // getValueAt(..) hazýr metodu sonu...
    } // NotlarModeli sýnýfý sonu...

    protected static class DurumKolonuSunucusu extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent (
                JTable tablo,
                Object deðer,
                boolean seçiliMi,
                boolean odaklýMý,
                int satýr,
                int kolon) {
            super.getTableCellRendererComponent (tablo, deðer, seçiliMi, odaklýMý, satýr, kolon);
            setText ("");
            setHorizontalAlignment (SwingConstants.CENTER);

            // Deðer boolean argümaný doðrultusunda geçti/passed.png veya kaldý/failed.png ikonunu kuralým...
            boolean durumu = (Boolean)deðer;
            setIcon (durumu ? geçtiÝkonu : kaldýÝkonu);

            return this;
        } // getTableCellRendererComponent(..) hazýr metodu sonu...
    } // DurumKolonuSunucusu sýnýfý sonu...

    public static void main (final String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", false);
                try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
                }catch (Exception ist) {}
                new J5e_15().setVisible (true); // Kurucuyu çaðýrýr...
        }}); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_15 sýnýfý sonu...