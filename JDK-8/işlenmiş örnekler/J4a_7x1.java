// J4a_7x1.java: MenuItemChooser (Men�BirimiSe�icisi) alt-�rne�i.

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

// T�m GUI'yi yaratan ana s�n�f...
// Gereken altalt-program: J4a_7x2.java=MenuItem
public class J4a_7x1 extends JPanel implements ActionListener, FocusListener {
    J4a_7x2 [] birimler = null; // J4a_7x2=MenuItem/Men�Birimi...
    int �imdikiMen�Birimi = 0;

    JLabel birimAd� = null;
    JLabel birimTipi = null;
    JLabel fiyat = null;
    JLabel resim = null;
    JLabel izah = null;
    JLabel toplamEtiketi = null;

    JTextField miktar = null;

    JButton sonraki = null;
    JButton �nceki = null;
    JButton sipari� = null;
    JButton iptal = null;
    JButton kapat = null;

    int toplam = 0;

    ActionListener kapatListener = null;

    public J4a_7x1() {
        birimler = J4a_7x2.men�y�Ba�lat();
        ba�latGUI();
    } // J4a_7x1() kurucusu sonu...

    private void ba�latGUI() {
        JPanel birimDetaylar�Paneli = new JPanel();
        birimDetaylar�Paneli.setLayout (new GridLayout (4, 2));
        birimDetaylar�Paneli.setBackground (Color.ORANGE);

        JLabel etiket = new JLabel ("Birim: ");
        birimDetaylar�Paneli.add (etiket);

        birimAd� = new JLabel ("");
        birimDetaylar�Paneli.add (birimAd�);

        etiket = new JLabel ("Tipi: ");
        birimDetaylar�Paneli.add (etiket);

        birimTipi = new JLabel ("");
        birimDetaylar�Paneli.add (birimTipi);

        etiket = new JLabel ("Fiyat�: TL ");
        birimDetaylar�Paneli.add (etiket);

        fiyat = new JLabel ("");
        birimDetaylar�Paneli.add (fiyat);

        etiket = new JLabel ("Miktar: ");
        birimDetaylar�Paneli.add (etiket);

        miktar = new JTextField ("0", 1);
        miktar.setBackground (new Color (20,200,200));
        miktar.addActionListener (this);
        miktar.addFocusListener (this);
        birimDetaylar�Paneli.add (miktar);

        JPanel resim�zahPaneli = new JPanel (new BorderLayout());
        resim�zahPaneli.setBackground (new Color (220,180,20));
        resim = new JLabel ("");
        resim.setHorizontalAlignment (SwingConstants.CENTER);
        resim.setVerticalAlignment (SwingConstants.CENTER);

        resim�zahPaneli.add (resim, BorderLayout.CENTER);

        izah = new JLabel ("izah");
        resim�zahPaneli.add (izah, BorderLayout.SOUTH);

        JPanel butonPaneli = new JPanel();
        butonPaneli.setBackground (Color.MAGENTA);
        sonraki = new JButton ("Sonraki");
        sonraki.addActionListener (this);
        butonPaneli.add (sonraki);

        �nceki = new JButton ("�nceki");
        �nceki.addActionListener (this);
        butonPaneli.add (�nceki);

        sipari� = new JButton ("Sipari�");
        sipari�.addActionListener (this);
        butonPaneli.add (sipari�);

        iptal = new JButton ("�ptal / Yeni");
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
        add (birimDetaylar�Paneli, BorderLayout.WEST);
        add (resim�zahPaneli, BorderLayout.EAST);
        add (butonPaneli, BorderLayout.SOUTH);
        TitledBorder ba�l�k = BorderFactory.createTitledBorder ("Men� Birimini Se�in ve Sipari�inizi Verin");
        setBorder (ba�l�k);

        men�BiriminiY�kle();
    } // ba�latGUI() metodu sonu...

    private void men�BiriminiY�kle() {
        birimAd�.setText (birimler[�imdikiMen�Birimi].ad);
        birimTipi.setText (birimler[�imdikiMen�Birimi].tip);
        fiyat.setText (new Integer (birimler[�imdikiMen�Birimi].fiyat).toString());
        miktar.setText (new Integer (birimler[�imdikiMen�Birimi].sipari�Miktar�).toString());

        ClassLoader cl = this.getClass().getClassLoader();
        Icon men�Birimi�konu  = new ImageIcon (cl.getResource (birimler[�imdikiMen�Birimi].resminYolu));
        resim.setIcon (men�Birimi�konu);
        izah.setText (birimler[�imdikiMen�Birimi].izah);
    } // men�BiriminiY�kle() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        if (olay.getSource() == �nceki) �ncekiMen�BiriminiG�ster();
        else if (olay.getSource() == sonraki) sonrakiMen�BiriminiG�ster();
        else if (olay.getSource() == sipari�) sipari�Ver();
        else if (olay.getSource() == iptal) sipari��ptal();
        else if (olay.getSource() == kapat) kapat();
        else if (olay.getSource() == miktar) sipari�Miktar�n�G�ncelle();
    } // actionPerformed(..) metodu sonu...

    public void focusLost (FocusEvent olay) {
        if (olay.getSource() == miktar) sipari�Miktar�n�G�ncelle();
    } // focusLost(..) metodu sonu...

    public void focusGained (FocusEvent olay) {}

    private void �ncekiMen�BiriminiG�ster() {
        if (�imdikiMen�Birimi > 0) �imdikiMen�Birimi--;
        men�BiriminiY�kle();
    } // �ncekiMen�BiriminiG�ster() metodu sonu...

    private void sonrakiMen�BiriminiG�ster() {
        if (�imdikiMen�Birimi < 3) �imdikiMen�Birimi++;
        men�BiriminiY�kle();
    } // sonrakiMen�BiriminiG�ster() metodu sonu...

    private void sipari�Ver() {
         for (int i = 0; i < 4; i++) toplam = toplam + birimler[i].fiyat * birimler[i].sipari�Miktar�;
        toplamEtiketi.setText ("Verdi�iniz Sipari� Toplam�: [" + toplam + " TL]");
        toplam = 0;
    } // sipari�Ver() metodu sonu...

    private void sipari��ptal() {
        for (int i = 0; i < 4; i++) birimler[i].sipari�Miktar� = 0;
        toplam = 0;
        toplamEtiketi.setText ("");
        �imdikiMen�Birimi = 0;
        men�BiriminiY�kle();
    } // sipari��ptal() metodu sonu...

    private void kapat() {kapatListener.actionPerformed (null);}

    private void sipari�Miktar�n�G�ncelle() {
        try {
            String de�er = miktar.getText();
            birimler[�imdikiMen�Birimi].sipari�Miktar� = (new Integer (de�er)).intValue();
        }catch (NumberFormatException ist) {ist.printStackTrace();
        } // try-catch blo�u sonu...
    } // sipari�Miktar�n�G�ncelle() metodu sonu...

    public void setCloseListener (ActionListener olay) {
        kapat.setEnabled (true);
    } // setCloseListener(..) metodu sonu...
} // J4a_7x1 s�n�f� sonu...