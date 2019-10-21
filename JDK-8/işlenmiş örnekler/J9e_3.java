// J9e_3.java: JumbledImageApplet (KarýþýkResimApleti) örneði.

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.net.MalformedURLException;

import java.util.Random;

// Gereken resim dosyasý: duke_skateboard.jpg
public class J9e_3 extends JApplet {
    static String resimDosyasý = "resim/duke_skateboard.jpg";
    private URL resimYureli;
    private KarýþtýrýlanResim karýþýkResim;

    public static void main (String s[]) {
        JFrame çerçeve = new JFrame ("Karýþýk Resim Apleti");
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        URL resimYureli = null;
        try {resimYureli = ((new File (resimDosyasý)).toURI()).toURL();
        }catch (MalformedURLException ist) {} // Aldýrma...
        J9e_3 karýþtýrýcý = new J9e_3 (resimYureli); // Kurucuyu çaðýrýr...
        karýþtýrýcý.kurUI();
        çerçeve.add ("Center", karýþtýrýcý);
        çerçeve.pack();
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...

    public J9e_3 (URL resimYureli) {this.resimYureli = resimYureli;} // Kurucu...

    public void kurUI() {
        final KarýþtýrýlanResim karýlýResim = new KarýþtýrýlanResim (resimYureli);
        add ("Center", karýlýResim);
        JButton karDüðmesi = new JButton ("Karýþtýr");
        karDüðmesi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                JButton b = (JButton)e.getSource();
                karýlýResim.karýþtýr();
                karýlýResim.repaint();
            }; // actionPerformed(..) hazýr metodu sonu...
        }); // kar.. ifadesi sonu...
        Dimension karýlýEbat = karýlýResim.getPreferredSize();
        resize (karýlýEbat.width, karýlýEbat.height+40);
        add ("South", karDüðmesi);
    } // kurUI() metodu sonu...
} // J9e_3 sýnýfý sonu...

class KarýþtýrýlanResim extends Component {
    private int konumSayýsý = 2;
    private int bölümSayýsý = konumSayýsý*konumSayýsý;
    private int[] bölümler;
    private BufferedImage tamponluResim;
    int en, boy, bölümEn, bölümBoy;
    
    public KarýþtýrýlanResim (URL resimYureli) {// Kurucu...
        try {tamponluResim = ImageIO.read (resimYureli);
            en = tamponluResim.getWidth (null);
            boy = tamponluResim.getHeight (null);
        }catch (IOException ist) {
            System.out.println ("Resim dosyasý okunamadý");
            //System.exit (1);
        } // try-catch bloðu sonu...
        bölümEn = en/konumSayýsý;
        bölümBoy = boy/konumSayýsý;
        bölümler = new int[bölümSayýsý];
        for (int i=0;i<bölümSayýsý;i++) bölümler[i] = i;
    } // KarýþtýrýlanResim(..) kurucusu sonu...

    void karýþtýr() {
        Random rasgele = new Random();
        int ri;
        for (int i=0; i<bölümSayýsý; i++) {
            while ((ri = rasgele.nextInt (konumSayýsý)) == i);
            int geçici = bölümler[i];
            bölümler[i] = bölümler[ri];
            bölümler[ri] = geçici;
        } // for döngüsü sonu...
    } // karýþtýr() metodu sonu...

    public Dimension getPreferredSize() {return new Dimension (en, boy);}

    public void paint (Graphics g) {
        int dx, dy;
        for (int x=0; x<konumSayýsý; x++) {
            int sx = x*bölümEn;
            for (int y=0; y<konumSayýsý; y++) {
                int sy = y*bölümBoy;
                int bölüm = bölümler[x*konumSayýsý+y];
                dx = (bölüm / konumSayýsý) * bölümEn;
                dy = (bölüm % konumSayýsý) * bölümBoy;
                g.drawImage (tamponluResim,
                        dx, dy, dx+bölümEn, dy+bölümBoy,
                        sx, sy, sx+bölümEn, sy+bölümBoy,
                        null);
            } // for-y döngüsü sonu...
        } // for-x döngüsü sonu...
    } // paint(..) hazýr metodu sonu...
} // KarýþtýrýlanResim sýnýfý sonu...