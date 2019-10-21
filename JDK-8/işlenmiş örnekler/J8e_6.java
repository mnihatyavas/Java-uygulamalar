// J8e_6.java: NormSample (NormNumunesi) örneði.

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.Label;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.applet.Applet;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import java.util.HashMap;
import java.util.Iterator;

public class J8e_6 extends Applet {
    JComboBox yerelKelimeler;
    JComboBox normBiçimleri;
    JComponent grafikKomponenti;
    HashMap<String, Normalizer.Form> biçimlemeListesi = new HashMap<String, Normalizer.Form>();
    HashMap<String, String> kelimeListesi = new HashMap<String, String>();

    public void ilkDeðerler() {
        biçimlemeListesi.put ("NFC", Normalizer.Form.NFC); // Norm Biçimleri: C, D, KC ve KD
        biçimlemeListesi.put ("NFD", Normalizer.Form.NFD);
        biçimlemeListesi.put ("NFKC", Normalizer.Form.NFKC);
        biçimlemeListesi.put ("NFKD", Normalizer.Form.NFKD);

        normBiçimleri = new JComboBox();
        for (Iterator tarayýcý = biçimlemeListesi.keySet().iterator(); tarayýcý.hasNext();)
            normBiçimleri.addItem ((String)tarayýcý.next());

        kelimeListesi.put ("acute accent (fransýz aksaný)", "touch" + "\u00e9"); // "\u00e9" = é
        kelimeListesi.put ("turko (türk aksaný)", "\u0131" + "rmak"); // "\u0131" = ý
        kelimeListesi.put ("ligature (italyan aksaný)", "a" + "\ufb03" + "ance"); // "\ufb03" = ffi
        kelimeListesi.put ("cedilla (ispanyol aksaný)", "fa" + "\u00e7" + "ade"); // "\u00e7" = ç
        kelimeListesi.put ("half-width katakana (japon aksaný)", "\uff81\uff6e\uff7a\uff9a\uff70\uff84"); // japonca harfler...

        yerelKelimeler = new JComboBox();
        for (Iterator tarayýcý = kelimeListesi.keySet().iterator(); tarayýcý.hasNext();)
            yerelKelimeler.addItem ((String)tarayýcý.next());

        // Yerel kelimeleri norm biçimlerine uyarlý yansýtacak bir grafik komponenti yaratalým...
        grafikKomponenti = new JComponent() {
            public Dimension getSize() {return new Dimension (550, 200);}
            public Dimension getPreferredSize() {return new Dimension (550, 200);}
            public Dimension getMinimumSize() {return new Dimension (550, 200);}

            public void paint (Graphics g) {
                Graphics2D g2 = (Graphics2D)g;

                g2.setFont (new Font ("Serif", Font.PLAIN, 20));                     
                g2.setColor (Color.BLACK);
                g2.drawString ("Orijinal kelime:", 100, 80);
                g2.drawString ("Norm'a uyarlanan kelime:", 100, 120);
                g2.setFont (new Font ("Serif", Font.BOLD, 24));

                String orijinalKelime = kelimeListesi.get (yerelKelimeler.getSelectedItem());
                g2.drawString (orijinalKelime, 320, 80);

                String normBiçimliKelime;
                Normalizer.Form aktüelBiçim = biçimlemeListesi.get (normBiçimleri.getSelectedItem());
                normBiçimliKelime = Normalizer.normalize (orijinalKelime, aktüelBiçim);
                g2.drawString (normBiçimliKelime, 320, 120);
            } // paint(..) hazýr grafik metodu sonu...
        }; // gra.. ifadesi sonu...

        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
        add (grafikKomponenti);

        JPanel açýlýrKutularPaneli = new JPanel();
        açýlýrKutularPaneli.setLayout (new BoxLayout (açýlýrKutularPaneli, BoxLayout.X_AXIS));
        açýlýrKutularPaneli.add (new Label ("Norm Biçimi: "));
        açýlýrKutularPaneli.add (normBiçimleri);
        açýlýrKutularPaneli.add (new Label ("Yerel Kelime:"));
        açýlýrKutularPaneli.add (yerelKelimeler);
        add (açýlýrKutularPaneli);

        // Açýlýr kutu seçimlerini dinleyiciye duyarlayalým...
        normBiçimleri.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {grafikKomponenti.repaint();}});
        yerelKelimeler.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {grafikKomponenti.repaint();}});
    } // ilkDeðerler() metodu sonu...

    public static void main (String[] args) {
        JFrame çerçeve = new JFrame ("Norm'a Uyarlama");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J8e_6 aplet = new J8e_6(); // Varsayýlý kurucuyu çaðýrýr...
        aplet.ilkDeðerler();
        çerçeve.add ("Center", aplet);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J8e_6 sýnýfý sonu...