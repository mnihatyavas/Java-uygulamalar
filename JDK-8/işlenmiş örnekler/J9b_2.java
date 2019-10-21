// J9b_2.java: WeatherWizard (HavaSihirbaz�) �rne�i.

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.applet.Applet;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.imageio.ImageIO;

import java.net.URL;

/* Gereken resim dosyalar�:
 *   hava-g�ne�li.png
 *   hava-bulutlu.png
 *   hava-ya�murlu.png
 *   hava-karl�.png
 */
public class J9b_2 extends JApplet implements ChangeListener {
    HavaRessam� ressam;

    public static void main (String[] args) {
        JFrame �er�eve = new JFrame ("Meteoroloji Sihirbaz�");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JApplet aplet = new J9b_2(); // Parametriz varsay�l� kurucuyu �a��r�r...
        aplet.init();
        aplet.start();
        �er�eve.add ("Center", aplet);
        �er�eve.setLocation (350, 100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...

    public void init() {UIManager.put ("swing.boldMetal", Boolean.FALSE);} // JApplet haz�r fonksiyonu...
    public void start() {s�n�fBile�enleri();} // JApplet haz�r fonksiyonu...

    public void s�n�fBile�enleri() {
        setLayout (new BorderLayout());
        JPanel panel = new JPanel();
        panel.add (new JLabel ("S�cakl�k:"));
        JSlider s�cakl�kS�rg�s� = new JSlider (-10, 40, 20); // �lk, son, akt�el...
        s�cakl�kS�rg�s�.setMinorTickSpacing (2);
        s�cakl�kS�rg�s�.setMajorTickSpacing (10);
        s�cakl�kS�rg�s�.setPaintTicks (true);
        s�cakl�kS�rg�s�.setPaintLabels (true);
        s�cakl�kS�rg�s�.addChangeListener (this); // S�rg� de�i�ikli�i dinleyiciye duyarl�...
        panel.add (s�cakl�kS�rg�s�);
        add ("North", panel);

        ressam = new HavaRessam�(); // Varsay�l� parametresiz kurucuyu �a��r�r...
        ressam.g�ne� = resmiY�kle ("g�ne�li");
        ressam.bulut = resmiY�kle ("bulutlu");
        ressam.ya�mur = resmiY�kle ("ya�murlu");
        ressam.kar = resmiY�kle ("karl�");
        ressam.s�cakl���De�i�tir (20);

        panel.add ("Center", ressam);
    } // s�n�fBile�enleri() metodu sonu...

    private BufferedImage resmiY�kle (String ad) {
        URL yurel = J9b_2.class.getResource ("resim/hava-" + ad + ".png");
        BufferedImage resim = null;
        try {resim = ImageIO.read (yurel);}catch (Exception ist){} // Ald�rma...
        return resim;
    } // resmiY�kle(..) metodu sonu...

    public void stateChanged (ChangeEvent olay) {
        JSlider s�rg� = (JSlider)olay.getSource();
        ressam.s�cakl���De�i�tir (s�rg�.getValue());
    } // stateChanged(..) haz�r metodu sonu...
} // J9b_2 s�n�f� sonu...

class HavaRessam� extends Component {
    int s�cakl�k = 18;
    String[] hava�artlar� = {"Karl�", "Ya�murlu", "Bulutlu", "G�ne�li"};
    BufferedImage kar = null;
    BufferedImage ya�mur = null;
    BufferedImage bulut = null;
    BufferedImage g�ne� = null;
    Color metinRengi = Color.yellow;
    String �artDizgesi = "";
    String hissedilen = "";
    Composite alfa0 = null, alfa1 = null;
    BufferedImage resim0 = null, resim1 = null;

    public Dimension getPreferredSize() {return new Dimension (450, 125);}

    void metniKur (String dizge1, String dizge2) {
        if (s�cakl�k <= 0) {
            metinRengi = Color.blue;
            hissedilen = "Dondurucu";
        }else if (s�cakl�k <= 10) {
            metinRengi = Color.green;
            hissedilen = "So�uk";
        }else if (s�cakl�k <= 20) {
            metinRengi = Color.yellow;
            hissedilen = "Serin";
        }else if (s�cakl�k <= 25) {
            metinRengi = Color.orange;
            hissedilen = "Il�k";
        }else {
            metinRengi = Color.red;
            hissedilen = "S�cak";
        } // if-else.. karar� sonu...

        �artDizgesi = dizge1;
        if (dizge2 != null) �artDizgesi += "/" + dizge2;
    } // metniKur(..) metodu sonu...

    void resmiKur (BufferedImage res0) {// Tek parametreli...
        alfa0 = null;
        alfa1 = null;
        resim0   = res0;
        resim1   = null;
    } // resmiKur(1..) metodu sonu...

    void resmiKur (int d���k, int y�ksek, BufferedImage res0, BufferedImage res1) {// 4 parametreli...
        float alfa = (y�ksek - s�cakl�k) / (float)(y�ksek - d���k);
        alfa0 = AlphaComposite.getInstance (AlphaComposite.SRC_OVER, alfa);
        alfa1 = AlphaComposite.getInstance (AlphaComposite.SRC_OVER, 1 - alfa);
        resim0 = res0;
        resim1 = res1;
    } // resmiKur(4..) metodu sonu...

    void havaRaporunuKur() {
        if (s�cakl�k <= 0) {
            resmiKur (kar);
            metniKur ("Karl�", null);
        }else if (s�cakl�k <= 10) {
            resmiKur (0, 10, kar, ya�mur);
            metniKur ("Karl�", "Ya�murlu");
        }else if (s�cakl�k <= 15) {
            resmiKur (ya�mur);
            metniKur ("Ya�murlu", null);
        }else if (s�cakl�k <= 20) {
            resmiKur (15, 20, ya�mur, bulut);
            metniKur ("Ya�murlu", "Bulutlu");
        }else if (s�cakl�k <= 25) {
            resmiKur (bulut);
            metniKur ("Bulutlu", null);
        } else if (s�cakl�k <= 30) {
            resmiKur (25, 30, bulut, g�ne�);
            metniKur ("Bulutlu", "G�ne�li");
        }else {
            resmiKur (g�ne�);
            metniKur ("G�ne�li", null);
        } // if-else...karar� sonu
    } // havaRaporunuKur() metodu sonu...

    void s�cakl���De�i�tir (int �s�) {
        s�cakl�k = �s�;
        repaint(); // paint()'i �a��r�r...
    } // s�cakl���De�i�tir(..) metodu sonu...

    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Dimension ebat = getSize();
        Composite ilkBile�im;

        havaRaporunuKur();

        ilkBile�im = g2.getComposite();
        if (alfa0 != null) g2.setComposite (alfa0);
        g2.drawImage (resim0,
                0, 0, ebat.width, ebat.height,
                0, 0, resim0.getWidth (null), resim0.getHeight (null),
                null);
        if (resim1 != null) {
            if (alfa1 != null) g2.setComposite (alfa1);
            g2.drawImage (resim1,
                    0, 0, ebat.width, ebat.height,
                    0, 0, resim1.getWidth (null), resim1.getHeight (null),
                    null);
        } // if karar� sonu...
        g2.setComposite (ilkBile�im);

        // Hissedilen: Dondurucu/Freezing, So�uk/Cold, Serin/Cool, Il�k/Warm, S�cakHot,
        // Metin rengi: Mavi/Blue, Ye�il/Green, Sar�/Yellow, Turuncu/Orange, K�rm�z�/Red
        Font yaz�Fonu = new Font ("Serif", Font.PLAIN, 36);
        g.setFont (yaz�Fonu);

        String �s�Dizgesi = hissedilen + " " + s�cakl�k+" C";
        FontRenderContext fonTakdim��eri�i = ((Graphics2D)g).getFontRenderContext();
        Rectangle2D �s�Kutusu = yaz�Fonu.getStringBounds (�s�Dizgesi, fonTakdim��eri�i);
        Rectangle2D �artKutusu = yaz�Fonu.getStringBounds (�artDizgesi, fonTakdim��eri�i);
        int metninEni = Math.max ((int)�s�Kutusu.getWidth(), (int)�artKutusu.getWidth());
        int metninBoyu = (int)�s�Kutusu.getHeight() + (int)�artKutusu.getHeight();
        int kutuX = (ebat.width - metninEni) / 2;
        int kutuY = (ebat.height - metninBoyu) / 2;

        g.setColor (Color.LIGHT_GRAY);
        g2.fillRect (kutuX, kutuY, metninEni, metninBoyu);

        g.setColor (metinRengi);
        int �s�MetniX = kutuX - (int)�s�Kutusu.getX();
        int �s�MetniY = kutuY - (int)�s�Kutusu.getY();
        g.drawString (�s�Dizgesi, �s�MetniX, �s�MetniY);

        int �artMetniX = kutuX - (int)�artKutusu.getX();
        int �artMetniY = kutuY - (int)�artKutusu.getY() + (int)�s�Kutusu.getHeight();
        g.drawString (�artDizgesi, �artMetniX, �artMetniY);
    } // paint(..) haz�r metodu sonu...
} // HavaRessam� s�n�f� sonu...