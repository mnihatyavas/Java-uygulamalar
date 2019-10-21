// J5i_8.java: ListDataEventDemo (ListeVerisiOlayýGösterisi) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.List;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListDataEvent;

import java.net.URL;

/* Gereken resim dosyalarý:
 *   resim/Üste.gif
 *   resim/Alta.gif
 */
public class J5i_8 extends JPanel implements ListSelectionListener {
    private JList liste;
    private DefaultListModel listeModeli;
    private static final String ekleDizgesi = "Ekle";
    private static final String silDizgesi = "Sil";
    private static final String yukarýDizgesi = "Yukarý";
    private static final String aþaðýDizgesi = "Aþaðý";
    private JButton ekleDüðmesi;
    private JButton silDüðmesi;
    private JButton yukarýDüðmesi;
    private JButton aþaðýDüðmesi;
    private JTextField yerAdý;
    private JTextArea kayýtDökümü;
    static private String yeniSatýr = "\n";

    public J5i_8() {// Kurucu...
        super (new BorderLayout());

        // Liste modelini yaratýp dolduralým...
        listeModeli = new DefaultListModel();
        listeModeli.addElement ("Whistler, Canada");
        listeModeli.addElement ("Jackson Hole, Wyoming");
        listeModeli.addElement ("Squaw Valley, California");
        listeModeli.addElement ("Telluride, Colorado");
        listeModeli.addElement ("Taos, New Mexico");
        listeModeli.addElement ("Snowbird, Utah");
        listeModeli.addElement ("Chamonix, France");
        listeModeli.addElement ("Banff, Canada");
        listeModeli.addElement ("Arapahoe Basin, Colorado");
        listeModeli.addElement ("Kirkwood, California");
        listeModeli.addElement ("Sun Valley, Idaho");
        listeModeli.addElement ("Toroslar, Mersin");
        listeModeli.addElement ("Temenyeri Parký, Bursa");
        listeModeli.addElement ("Banazý Gediði, Malatya");
        listeModeli.addListDataListener (new ListeDinleyicim()); // Liste dinleyicisine duyarlý...

        // Listeyi modelden yaratýp kaydýracýna koyalým...
        liste = new JList (listeModeli);
        liste.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f) );
        liste.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        liste.setSelectedIndex (0);
        liste.addListSelectionListener (this); // Liste Seçiciye duyarlý...
        JScrollPane listeKaydýracý = new JScrollPane (liste);

        // Liste deðiþtirme düðmelerini (ekle-sil-yukarý-aþaðý) yaratalým...
        ekleDüðmesi = new JButton (ekleDizgesi);
        ekleDüðmesi.setActionCommand (ekleDizgesi);
        ekleDüðmesi.addActionListener (new EkleDinleyicim());

        silDüðmesi = new JButton (silDizgesi);
        silDüðmesi.setActionCommand (silDizgesi);
        silDüðmesi.addActionListener (new SilDinleyicim());

        ImageIcon ikon = resimÝkonuYarat ("resim/Üste");
        if (ikon != null) {
            yukarýDüðmesi = new JButton (ikon);
            yukarýDüðmesi.setMargin (new Insets (0,0,0,0));
        }else yukarýDüðmesi = new JButton ("Bir üste taþý");
        yukarýDüðmesi.setToolTipText ("Aktüel seçili liste elemanýný bir üst konuma kaydýrýr.");
        yukarýDüðmesi.setActionCommand (yukarýDizgesi);
        yukarýDüðmesi.addActionListener (new YukarýAþaðýDinleyicim()); //Özel dinleyicime duyarlý...

        ikon = resimÝkonuYarat ("resim/Alta");
        if (ikon != null) {
            aþaðýDüðmesi = new JButton (ikon);
            aþaðýDüðmesi.setMargin (new Insets (0,0,0,0));
        }else aþaðýDüðmesi = new JButton ("Bir alta taþý");
        aþaðýDüðmesi.setToolTipText ("Aktüel seçili liste elemanýný bir alt konuma kaydýrýr.");
        aþaðýDüðmesi.setActionCommand (aþaðýDizgesi);
        aþaðýDüðmesi.addActionListener (new YukarýAþaðýDinleyicim()); //Özel dinleyicime duyarlý...

        JPanel yukarýAþaðýPaneli = new JPanel (new GridLayout (2, 1));
        yukarýAþaðýPaneli.add (yukarýDüðmesi);
        yukarýAþaðýPaneli.add (aþaðýDüðmesi);

        // Yeni yer adlarý /seçebileceðiniz/girebileceðiniz metin satýrýný yaratalým...
        yerAdý = new JTextField (15);
        yerAdý.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f) );
        yerAdý.addActionListener (new EkleDinleyicim()); // Özel dinleyicime duyarlý...
        String isim = listeModeli.getElementAt (liste.getSelectedIndex()).toString();
        yerAdý.setText (isim);

        // Metin satýrlý düðmeleri varsayýlý FlowLayout serilimle düðme paneline ekleyelim...
        JPanel düðmePaneli = new JPanel();
        düðmePaneli.add (yerAdý);
        düðmePaneli.add (ekleDüðmesi);
        düðmePaneli.add (silDüðmesi);
        düðmePaneli.add (yukarýAþaðýPaneli);

        // Kayýt dökümlerini sergileyeceðimiz metin alanlý kaydýracý yaratalým...
        kayýtDökümü = new JTextArea (10, 20);
        kayýtDökümü.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f) );
        kayýtDökümü.setForeground (Color.WHITE);
        JScrollPane dökümKaydýracý = new JScrollPane (kayýtDökümü);

        // Döküm kaydýracý ve liste için bölmeli/paravanlý bir pano yaratalým...
        JSplitPane paravanlýPano = new JSplitPane (
                JSplitPane.VERTICAL_SPLIT, listeKaydýracý, dökümKaydýracý);
        paravanlýPano.setResizeWeight (0.5);

        // Sonuç komponentleri içerik panosuna ekleyelim...
        add (düðmePaneli, BorderLayout.PAGE_START);
        add (paravanlýPano, BorderLayout.CENTER);
    } // J5i_8() kurucusu sonu....

    class ListeDinleyicim implements ListDataListener {
        public void contentsChanged (ListDataEvent olay) {
            kayýtDökümü.append ("Liste elemaný konumu deðiþti: "
                    + olay.getIndex0() + ", " + olay.getIndex1() + yeniSatýr); 
            kayýtDökümü.setCaretPosition (kayýtDökümü.getDocument().getLength());
        } // contentsChanged(..) hazýr metodu sonu...

        public void intervalAdded (ListDataEvent olay) {
            kayýtDökümü.append ("Eklenen eleman konumu: "
                    + olay.getIndex0() + ", " + olay.getIndex1() + yeniSatýr); 
            kayýtDökümü.setCaretPosition (kayýtDökümü.getDocument().getLength());
        } // intervalAdded(..) hazýr metodu sonu...

        public void intervalRemoved (ListDataEvent olay) {
            kayýtDökümü.append ("Silinen eleman-lar aralýðý: "
                    + olay.getIndex0() + ", " + olay.getIndex1() + yeniSatýr); 
            kayýtDökümü.setCaretPosition (kayýtDökümü.getDocument().getLength());
        } // intervalRemoved(..) hazýr metodu sonu...
    } // ListeDinleyicim sýnýfý sonu...

    public void valueChanged (ListSelectionEvent olay) {
        if (olay.getValueIsAdjusting() == false) {// Liste kaydýraç hareketi deðilse...

            if (liste.getSelectedIndex() == -1) {
            // Seçim yok (tüm liste silinmiþ) ise Sil Yukarý Aþaðý düðmeleri aktifsizlenir...
                silDüðmesi.setEnabled (false);
                yukarýDüðmesi.setEnabled (false);
                aþaðýDüðmesi.setEnabled (false);
                yerAdý.setText ("");
            }else if (liste.getSelectedIndices().length > 1) {
            // Çoklu seçim iseYukarý-Aþaðý düðmeleri aktifsizleþir...
                silDüðmesi.setEnabled (true);
                yukarýDüðmesi.setEnabled (false);
                aþaðýDüðmesi.setEnabled (false);
            }else {// Tekli seçim ise tüm düðmeler aktiftir...
                silDüðmesi.setEnabled (true);
                yukarýDüðmesi.setEnabled (true);
                aþaðýDüðmesi.setEnabled (true);
                yerAdý.setText (liste.getSelectedValue().toString());
            } // iç-if-else.. kararý sonu...
        } // Dýþ-if kararý sonu...
    } // valueChanged(..) hazýr metodu sonu...

    class EkleDinleyicim implements ActionListener {
        public void actionPerformed (ActionEvent olay) {
            if (yerAdý.getText().equals("")) {// Metin satýrý boþken Ekle týklanýrsa bipler...
                Toolkit.getDefaultToolkit().beep();
                return;
            } // if kararý sonu...

            int endeks = liste.getSelectedIndex();
            int ebat = listeModeli.getSize();

            // Listeden seçili yoksa veya son eleman seçiliyse, Ekle liste sonuna ekler...
            if (endeks == -1 || (endeks+1 == ebat)) {
                listeModeli.addElement (yerAdý.getText());
                liste.setSelectedIndex (ebat);

            // Deðilse, Ekle seçilenden sonraya ekler ve yeni elemaný seçer...
            }else {
                listeModeli.insertElementAt (yerAdý.getText(), endeks+1);
                liste.setSelectedIndex (endeks+1);
            } // if-else kararý sonu...
        } // actionPerformed(..) hazýr metodu sonu...
    } // EkleDinleyicim sýnýfý sonu...

    class SilDinleyicim implements ActionListener {
        public void actionPerformed (ActionEvent olay) {
            // Listede mevcut ve seçili tekli-çoklu elemanlarý siler...
            ListSelectionModel seçmeModeli = liste.getSelectionModel();
            int ilkSeçilen = seçmeModeli.getMinSelectionIndex();
            int sonSeçilen = seçmeModeli.getMaxSelectionIndex();
            listeModeli.removeRange (ilkSeçilen, sonSeçilen);

            int ebat = listeModeli.size();

            if (ebat == 0) {// Tüm liste silindiyse Sil-Yukarý-Aþaðý düðmeleri etkisizleþtirilir...
                silDüðmesi.setEnabled (false);
                yukarýDüðmesi.setEnabled (false);
                aþaðýDüðmesi.setEnabled (false);
            }else {// Silmeden sonra seçilen ilk (sonuncuysa bir önceki) olmalý...
                if (ilkSeçilen == listeModeli.getSize()) ilkSeçilen--;
                liste.setSelectedIndex (ilkSeçilen);
            } // if-else kararý sonu...
        } // actionPerformed(..) hazýr metodu sonu...
    } // SilDinleyicim sýnýfý sonu...

    protected static ImageIcon resimÝkonuYarat (String resminAdý) {
        String resminYolu = resminAdý + ".gif";
        URL resminYureli = J5i_8.class.getResource (resminYolu);

        if (resminYureli == null) {
            System.err.println (resminYolu + " adlý resim bulunamadý!");
            return null;
        }else return new ImageIcon (resminYureli);
    } // resimÝkonuYarat(..) metodu sonu...

    class YukarýAþaðýDinleyicim implements ActionListener {
        public void actionPerformed (ActionEvent olay) {
            int taþýnacakEndeks = liste.getSelectedIndex();

            if (olay.getActionCommand().equals (yukarýDizgesi)) {// Yukarý taþý...
                if (taþýnacakEndeks != 0) {// Ýlk endeks ise aldýrma...
                    deðiþTokuþ (taþýnacakEndeks, taþýnacakEndeks-1);
                    liste.setSelectedIndex (taþýnacakEndeks-1);
                    liste.ensureIndexIsVisible (taþýnacakEndeks-1);
                } // iç-if kararý sonu...
            }else {// Aþaðý taþý...
                if (taþýnacakEndeks != listeModeli.getSize()-1) {// Son endeks ise aldýrma...
                    deðiþTokuþ (taþýnacakEndeks, taþýnacakEndeks+1);
                    liste.setSelectedIndex (taþýnacakEndeks+1);
                    liste.ensureIndexIsVisible (taþýnacakEndeks+1);
                } // iç-if kararý sonu...
            } // if-else kararý sonu...
        } // actionPerformed(..) hazýr metodu sonu...
    } // YukarýAþaðýDinleyicim sýnýfý sonu...

    private void deðiþTokuþ (int a, int b) {
        Object aNesnesi = listeModeli.getElementAt (a);
        Object bNesnesi = listeModeli.getElementAt (b);
        listeModeli.set (a, bNesnesi);
        listeModeli.set (b, aNesnesi);
    } // deðiþTokuþ(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Liste Verisi Olayý Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5i_8(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        yeniÝçerikPanosu.setMinimumSize (new Dimension (
                yeniÝçerikPanosu.getPreferredSize().width, 100));
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible(true);
    } // yaratVeGösterGUI() netodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..)
} // J5i_8 sýnýfý sonu...