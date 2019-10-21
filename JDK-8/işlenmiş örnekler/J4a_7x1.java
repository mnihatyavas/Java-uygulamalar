// J4a_7x1.java: MenuItemChooser (MenüBirimiSeçicisi) alt-örneği.

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

// Tüm GUI'yi yaratan ana sınıf...
// Gereken altalt-program: J4a_7x2.java=MenuItem
public class J4a_7x1 extends JPanel implements ActionListener, FocusListener {
    J4a_7x2 [] birimler = null; // J4a_7x2=MenuItem/MenüBirimi...
    int şimdikiMenüBirimi = 0;

    JLabel birimAdı = null;
    JLabel birimTipi = null;
    JLabel fiyat = null;
    JLabel resim = null;
    JLabel izah = null;
    JLabel toplamEtiketi = null;

    JTextField miktar = null;

    JButton sonraki = null;
    JButton önceki = null;
    JButton sipariş = null;
    JButton iptal = null;
    JButton kapat = null;

    int toplam = 0;

    ActionListener kapatListener = null;

    public J4a_7x1() {
        birimler = J4a_7x2.menüyüBaşlat();
        başlatGUI();
    } // J4a_7x1() kurucusu sonu...

    private void başlatGUI() {
        JPanel birimDetaylarıPaneli = new JPanel();
        birimDetaylarıPaneli.setLayout (new GridLayout (4, 2));
        birimDetaylarıPaneli.setBackground (Color.ORANGE);

        JLabel etiket = new JLabel ("Birim: ");
        birimDetaylarıPaneli.add (etiket);

        birimAdı = new JLabel ("");
        birimDetaylarıPaneli.add (birimAdı);

        etiket = new JLabel ("Tipi: ");
        birimDetaylarıPaneli.add (etiket);

        birimTipi = new JLabel ("");
        birimDetaylarıPaneli.add (birimTipi);

        etiket = new JLabel ("Fiyatı: TL ");
        birimDetaylarıPaneli.add (etiket);

        fiyat = new JLabel ("");
        birimDetaylarıPaneli.add (fiyat);

        etiket = new JLabel ("Miktar: ");
        birimDetaylarıPaneli.add (etiket);

        miktar = new JTextField ("0", 1);
        miktar.setBackground (new Color (20,200,200));
        miktar.addActionListener (this);
        miktar.addFocusListener (this);
        birimDetaylarıPaneli.add (miktar);

        JPanel resimİzahPaneli = new JPanel (new BorderLayout());
        resimİzahPaneli.setBackground (new Color (220,180,20));
        resim = new JLabel ("");
        resim.setHorizontalAlignment (SwingConstants.CENTER);
        resim.setVerticalAlignment (SwingConstants.CENTER);

        resimİzahPaneli.add (resim, BorderLayout.CENTER);

        izah = new JLabel ("izah");
        resimİzahPaneli.add (izah, BorderLayout.SOUTH);

        JPanel butonPaneli = new JPanel();
        butonPaneli.setBackground (Color.MAGENTA);
        sonraki = new JButton ("Sonraki");
        sonraki.addActionListener (this);
        butonPaneli.add (sonraki);

        önceki = new JButton ("Önceki");
        önceki.addActionListener (this);
        butonPaneli.add (önceki);

        sipariş = new JButton ("Sipariş");
        sipariş.addActionListener (this);
        butonPaneli.add (sipariş);

        iptal = new JButton ("İptal / Yeni");
        iptal.addActionListener (this);
        butonPaneli.add (iptal);

        kapat = new JButton ("Kapat");
        kapat.addActionListener (this);
        kapat.setEnabled (false);
        butonPaneli.add (kapat);

        toplamEtiketi = new JLabel ("");
        toplamEtiketi.setFont (new Font ("Serif", Font.BOLD, 14));
        toplamEtiketi.setForeground ((Color.GREEN).darker());
        butonPaneli.add (toplamEtiketi);

        BorderLayout bl = new BorderLayout();
        bl.setHgap (30);
        bl.setVgap (20);
        setLayout (bl);
        setBackground (Color.CYAN);
        add (birimDetaylarıPaneli, BorderLayout.WEST);
        add (resimİzahPaneli, BorderLayout.EAST);
        add (butonPaneli, BorderLayout.SOUTH);
        TitledBorder başlık = BorderFactory.createTitledBorder ("Menü Birimini Seçin ve Siparişinizi Verin");
        setBorder (başlık);

        menüBiriminiYükle();
    } // başlatGUI() metodu sonu...

    private void menüBiriminiYükle() {
        birimAdı.setText (birimler[şimdikiMenüBirimi].ad);
        birimTipi.setText (birimler[şimdikiMenüBirimi].tip);
        fiyat.setText (new Integer (birimler[şimdikiMenüBirimi].fiyat).toString());
        miktar.setText (new Integer (birimler[şimdikiMenüBirimi].siparişMiktarı).toString());

        ClassLoader cl = this.getClass().getClassLoader();
        Icon menüBirimiİkonu  = new ImageIcon (cl.getResource (birimler[şimdikiMenüBirimi].resminYolu));
        resim.setIcon (menüBirimiİkonu);
        izah.setText (birimler[şimdikiMenüBirimi].izah);
    } // menüBiriminiYükle() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        if (olay.getSource() == önceki) öncekiMenüBiriminiGöster();
        else if (olay.getSource() == sonraki) sonrakiMenüBiriminiGöster();
        else if (olay.getSource() == sipariş) siparişVer();
        else if (olay.getSource() == iptal) siparişİptal();
        else if (olay.getSource() == kapat) kapat();
        else if (olay.getSource() == miktar) siparişMiktarınıGüncelle();
    } // actionPerformed(..) metodu sonu...

    public void focusLost (FocusEvent olay) {
        if (olay.getSource() == miktar) siparişMiktarınıGüncelle();
    } // focusLost(..) metodu sonu...

    public void focusGained (FocusEvent olay) {}

    private void öncekiMenüBiriminiGöster() {
        if (şimdikiMenüBirimi > 0) şimdikiMenüBirimi--;
        menüBiriminiYükle();
    } // öncekiMenüBiriminiGöster() metodu sonu...

    private void sonrakiMenüBiriminiGöster() {
        if (şimdikiMenüBirimi < 3) şimdikiMenüBirimi++;
        menüBiriminiYükle();
    } // sonrakiMenüBiriminiGöster() metodu sonu...

    private void siparişVer() {
         for (int i = 0; i < 4; i++) toplam = toplam + birimler[i].fiyat * birimler[i].siparişMiktarı;
        toplamEtiketi.setText ("Verdiğiniz Sipariş Toplamı: [" + toplam + " TL]");
        toplam = 0;
    } // siparişVer() metodu sonu...

    private void siparişİptal() {
        for (int i = 0; i < 4; i++) birimler[i].siparişMiktarı = 0;
        toplam = 0;
        toplamEtiketi.setText ("");
        şimdikiMenüBirimi = 0;
        menüBiriminiYükle();
    } // siparişİptal() metodu sonu...

    private void kapat() {kapatListener.actionPerformed (null);}

    private void siparişMiktarınıGüncelle() {
        try {
            String değer = miktar.getText();
            birimler[şimdikiMenüBirimi].siparişMiktarı = (new Integer (değer)).intValue();
        }catch (NumberFormatException ist) {ist.printStackTrace();
        } // try-catch bloğu sonu...
    } // siparişMiktarınıGüncelle() metodu sonu...

    public void setCloseListener (ActionListener olay) {
        kapat.setEnabled (true);
    } // setCloseListener(..) metodu sonu...
} // J4a_7x1 sınıfı sonu...