// J9b_2.java: WeatherWizard (HavaSihirbazý) örneði.

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

/* Gereken resim dosyalarý:
 *   hava-güneþli.png
 *   hava-bulutlu.png
 *   hava-yaðmurlu.png
 *   hava-karlý.png
 */
public class J9b_2 extends JApplet implements ChangeListener {
    HavaRessamý ressam;

    public static void main (String[] args) {
        JFrame çerçeve = new JFrame ("Meteoroloji Sihirbazý");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JApplet aplet = new J9b_2(); // Parametriz varsayýlý kurucuyu çaðýrýr...
        aplet.init();
        aplet.start();
        çerçeve.add ("Center", aplet);
        çerçeve.setLocation (350, 100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...

    public void init() {UIManager.put ("swing.boldMetal", Boolean.FALSE);} // JApplet hazýr fonksiyonu...
    public void start() {sýnýfBileþenleri();} // JApplet hazýr fonksiyonu...

    public void sýnýfBileþenleri() {
        setLayout (new BorderLayout());
        JPanel panel = new JPanel();
        panel.add (new JLabel ("Sýcaklýk:"));
        JSlider sýcaklýkSürgüsü = new JSlider (-10, 40, 20); // Ýlk, son, aktüel...
        sýcaklýkSürgüsü.setMinorTickSpacing (2);
        sýcaklýkSürgüsü.setMajorTickSpacing (10);
        sýcaklýkSürgüsü.setPaintTicks (true);
        sýcaklýkSürgüsü.setPaintLabels (true);
        sýcaklýkSürgüsü.addChangeListener (this); // Sürgü deðiþikliði dinleyiciye duyarlý...
        panel.add (sýcaklýkSürgüsü);
        add ("North", panel);

        ressam = new HavaRessamý(); // Varsayýlý parametresiz kurucuyu çaðýrýr...
        ressam.güneþ = resmiYükle ("güneþli");
        ressam.bulut = resmiYükle ("bulutlu");
        ressam.yaðmur = resmiYükle ("yaðmurlu");
        ressam.kar = resmiYükle ("karlý");
        ressam.sýcaklýðýDeðiþtir (20);

        panel.add ("Center", ressam);
    } // sýnýfBileþenleri() metodu sonu...

    private BufferedImage resmiYükle (String ad) {
        URL yurel = J9b_2.class.getResource ("resim/hava-" + ad + ".png");
        BufferedImage resim = null;
        try {resim = ImageIO.read (yurel);}catch (Exception ist){} // Aldýrma...
        return resim;
    } // resmiYükle(..) metodu sonu...

    public void stateChanged (ChangeEvent olay) {
        JSlider sürgü = (JSlider)olay.getSource();
        ressam.sýcaklýðýDeðiþtir (sürgü.getValue());
    } // stateChanged(..) hazýr metodu sonu...
} // J9b_2 sýnýfý sonu...

class HavaRessamý extends Component {
    int sýcaklýk = 18;
    String[] havaÞartlarý = {"Karlý", "Yaðmurlu", "Bulutlu", "Güneþli"};
    BufferedImage kar = null;
    BufferedImage yaðmur = null;
    BufferedImage bulut = null;
    BufferedImage güneþ = null;
    Color metinRengi = Color.yellow;
    String þartDizgesi = "";
    String hissedilen = "";
    Composite alfa0 = null, alfa1 = null;
    BufferedImage resim0 = null, resim1 = null;

    public Dimension getPreferredSize() {return new Dimension (450, 125);}

    void metniKur (String dizge1, String dizge2) {
        if (sýcaklýk <= 0) {
            metinRengi = Color.blue;
            hissedilen = "Dondurucu";
        }else if (sýcaklýk <= 10) {
            metinRengi = Color.green;
            hissedilen = "Soðuk";
        }else if (sýcaklýk <= 20) {
            metinRengi = Color.yellow;
            hissedilen = "Serin";
        }else if (sýcaklýk <= 25) {
            metinRengi = Color.orange;
            hissedilen = "Ilýk";
        }else {
            metinRengi = Color.red;
            hissedilen = "Sýcak";
        } // if-else.. kararý sonu...

        þartDizgesi = dizge1;
        if (dizge2 != null) þartDizgesi += "/" + dizge2;
    } // metniKur(..) metodu sonu...

    void resmiKur (BufferedImage res0) {// Tek parametreli...
        alfa0 = null;
        alfa1 = null;
        resim0   = res0;
        resim1   = null;
    } // resmiKur(1..) metodu sonu...

    void resmiKur (int düþük, int yüksek, BufferedImage res0, BufferedImage res1) {// 4 parametreli...
        float alfa = (yüksek - sýcaklýk) / (float)(yüksek - düþük);
        alfa0 = AlphaComposite.getInstance (AlphaComposite.SRC_OVER, alfa);
        alfa1 = AlphaComposite.getInstance (AlphaComposite.SRC_OVER, 1 - alfa);
        resim0 = res0;
        resim1 = res1;
    } // resmiKur(4..) metodu sonu...

    void havaRaporunuKur() {
        if (sýcaklýk <= 0) {
            resmiKur (kar);
            metniKur ("Karlý", null);
        }else if (sýcaklýk <= 10) {
            resmiKur (0, 10, kar, yaðmur);
            metniKur ("Karlý", "Yaðmurlu");
        }else if (sýcaklýk <= 15) {
            resmiKur (yaðmur);
            metniKur ("Yaðmurlu", null);
        }else if (sýcaklýk <= 20) {
            resmiKur (15, 20, yaðmur, bulut);
            metniKur ("Yaðmurlu", "Bulutlu");
        }else if (sýcaklýk <= 25) {
            resmiKur (bulut);
            metniKur ("Bulutlu", null);
        } else if (sýcaklýk <= 30) {
            resmiKur (25, 30, bulut, güneþ);
            metniKur ("Bulutlu", "Güneþli");
        }else {
            resmiKur (güneþ);
            metniKur ("Güneþli", null);
        } // if-else...kararý sonu
    } // havaRaporunuKur() metodu sonu...

    void sýcaklýðýDeðiþtir (int ýsý) {
        sýcaklýk = ýsý;
        repaint(); // paint()'i çaðýrýr...
    } // sýcaklýðýDeðiþtir(..) metodu sonu...

    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Dimension ebat = getSize();
        Composite ilkBileþim;

        havaRaporunuKur();

        ilkBileþim = g2.getComposite();
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
        } // if kararý sonu...
        g2.setComposite (ilkBileþim);

        // Hissedilen: Dondurucu/Freezing, Soðuk/Cold, Serin/Cool, Ilýk/Warm, SýcakHot,
        // Metin rengi: Mavi/Blue, Yeþil/Green, Sarý/Yellow, Turuncu/Orange, Kýrmýzý/Red
        Font yazýFonu = new Font ("Serif", Font.PLAIN, 36);
        g.setFont (yazýFonu);

        String ýsýDizgesi = hissedilen + " " + sýcaklýk+" C";
        FontRenderContext fonTakdimÝçeriði = ((Graphics2D)g).getFontRenderContext();
        Rectangle2D ýsýKutusu = yazýFonu.getStringBounds (ýsýDizgesi, fonTakdimÝçeriði);
        Rectangle2D þartKutusu = yazýFonu.getStringBounds (þartDizgesi, fonTakdimÝçeriði);
        int metninEni = Math.max ((int)ýsýKutusu.getWidth(), (int)þartKutusu.getWidth());
        int metninBoyu = (int)ýsýKutusu.getHeight() + (int)þartKutusu.getHeight();
        int kutuX = (ebat.width - metninEni) / 2;
        int kutuY = (ebat.height - metninBoyu) / 2;

        g.setColor (Color.LIGHT_GRAY);
        g2.fillRect (kutuX, kutuY, metninEni, metninBoyu);

        g.setColor (metinRengi);
        int ýsýMetniX = kutuX - (int)ýsýKutusu.getX();
        int ýsýMetniY = kutuY - (int)ýsýKutusu.getY();
        g.drawString (ýsýDizgesi, ýsýMetniX, ýsýMetniY);

        int þartMetniX = kutuX - (int)þartKutusu.getX();
        int þartMetniY = kutuY - (int)þartKutusu.getY() + (int)ýsýKutusu.getHeight();
        g.drawString (þartDizgesi, þartMetniX, þartMetniY);
    } // paint(..) hazýr metodu sonu...
} // HavaRessamý sýnýfý sonu...