// J8e_6.java: NormSample (NormNumunesi) �rne�i.

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
    JComboBox normBi�imleri;
    JComponent grafikKomponenti;
    HashMap<String, Normalizer.Form> bi�imlemeListesi = new HashMap<String, Normalizer.Form>();
    HashMap<String, String> kelimeListesi = new HashMap<String, String>();

    public void ilkDe�erler() {
        bi�imlemeListesi.put ("NFC", Normalizer.Form.NFC); // Norm Bi�imleri: C, D, KC ve KD
        bi�imlemeListesi.put ("NFD", Normalizer.Form.NFD);
        bi�imlemeListesi.put ("NFKC", Normalizer.Form.NFKC);
        bi�imlemeListesi.put ("NFKD", Normalizer.Form.NFKD);

        normBi�imleri = new JComboBox();
        for (Iterator taray�c� = bi�imlemeListesi.keySet().iterator(); taray�c�.hasNext();)
            normBi�imleri.addItem ((String)taray�c�.next());

        kelimeListesi.put ("acute accent (frans�z aksan�)", "touch" + "\u00e9"); // "\u00e9" = �
        kelimeListesi.put ("turko (t�rk aksan�)", "\u0131" + "rmak"); // "\u0131" = �
        kelimeListesi.put ("ligature (italyan aksan�)", "a" + "\ufb03" + "ance"); // "\ufb03" = ffi
        kelimeListesi.put ("cedilla (ispanyol aksan�)", "fa" + "\u00e7" + "ade"); // "\u00e7" = �
        kelimeListesi.put ("half-width katakana (japon aksan�)", "\uff81\uff6e\uff7a\uff9a\uff70\uff84"); // japonca harfler...

        yerelKelimeler = new JComboBox();
        for (Iterator taray�c� = kelimeListesi.keySet().iterator(); taray�c�.hasNext();)
            yerelKelimeler.addItem ((String)taray�c�.next());

        // Yerel kelimeleri norm bi�imlerine uyarl� yans�tacak bir grafik komponenti yaratal�m...
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

                String normBi�imliKelime;
                Normalizer.Form akt�elBi�im = bi�imlemeListesi.get (normBi�imleri.getSelectedItem());
                normBi�imliKelime = Normalizer.normalize (orijinalKelime, akt�elBi�im);
                g2.drawString (normBi�imliKelime, 320, 120);
            } // paint(..) haz�r grafik metodu sonu...
        }; // gra.. ifadesi sonu...

        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
        add (grafikKomponenti);

        JPanel a��l�rKutularPaneli = new JPanel();
        a��l�rKutularPaneli.setLayout (new BoxLayout (a��l�rKutularPaneli, BoxLayout.X_AXIS));
        a��l�rKutularPaneli.add (new Label ("Norm Bi�imi: "));
        a��l�rKutularPaneli.add (normBi�imleri);
        a��l�rKutularPaneli.add (new Label ("Yerel Kelime:"));
        a��l�rKutularPaneli.add (yerelKelimeler);
        add (a��l�rKutularPaneli);

        // A��l�r kutu se�imlerini dinleyiciye duyarlayal�m...
        normBi�imleri.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {grafikKomponenti.repaint();}});
        yerelKelimeler.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {grafikKomponenti.repaint();}});
    } // ilkDe�erler() metodu sonu...

    public static void main (String[] args) {
        JFrame �er�eve = new JFrame ("Norm'a Uyarlama");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J8e_6 aplet = new J8e_6(); // Varsay�l� kurucuyu �a��r�r...
        aplet.ilkDe�erler();
        �er�eve.add ("Center", aplet);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J8e_6 s�n�f� sonu...