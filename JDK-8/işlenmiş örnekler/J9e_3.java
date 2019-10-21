// J9e_3.java: JumbledImageApplet (Kar���kResimApleti) �rne�i.

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

// Gereken resim dosyas�: duke_skateboard.jpg
public class J9e_3 extends JApplet {
    static String resimDosyas� = "resim/duke_skateboard.jpg";
    private URL resimYureli;
    private Kar��t�r�lanResim kar���kResim;

    public static void main (String s[]) {
        JFrame �er�eve = new JFrame ("Kar���k Resim Apleti");
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        URL resimYureli = null;
        try {resimYureli = ((new File (resimDosyas�)).toURI()).toURL();
        }catch (MalformedURLException ist) {} // Ald�rma...
        J9e_3 kar��t�r�c� = new J9e_3 (resimYureli); // Kurucuyu �a��r�r...
        kar��t�r�c�.kurUI();
        �er�eve.add ("Center", kar��t�r�c�);
        �er�eve.pack();
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...

    public J9e_3 (URL resimYureli) {this.resimYureli = resimYureli;} // Kurucu...

    public void kurUI() {
        final Kar��t�r�lanResim kar�l�Resim = new Kar��t�r�lanResim (resimYureli);
        add ("Center", kar�l�Resim);
        JButton karD��mesi = new JButton ("Kar��t�r");
        karD��mesi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                JButton b = (JButton)e.getSource();
                kar�l�Resim.kar��t�r();
                kar�l�Resim.repaint();
            }; // actionPerformed(..) haz�r metodu sonu...
        }); // kar.. ifadesi sonu...
        Dimension kar�l�Ebat = kar�l�Resim.getPreferredSize();
        resize (kar�l�Ebat.width, kar�l�Ebat.height+40);
        add ("South", karD��mesi);
    } // kurUI() metodu sonu...
} // J9e_3 s�n�f� sonu...

class Kar��t�r�lanResim extends Component {
    private int konumSay�s� = 2;
    private int b�l�mSay�s� = konumSay�s�*konumSay�s�;
    private int[] b�l�mler;
    private BufferedImage tamponluResim;
    int en, boy, b�l�mEn, b�l�mBoy;
    
    public Kar��t�r�lanResim (URL resimYureli) {// Kurucu...
        try {tamponluResim = ImageIO.read (resimYureli);
            en = tamponluResim.getWidth (null);
            boy = tamponluResim.getHeight (null);
        }catch (IOException ist) {
            System.out.println ("Resim dosyas� okunamad�");
            //System.exit (1);
        } // try-catch blo�u sonu...
        b�l�mEn = en/konumSay�s�;
        b�l�mBoy = boy/konumSay�s�;
        b�l�mler = new int[b�l�mSay�s�];
        for (int i=0;i<b�l�mSay�s�;i++) b�l�mler[i] = i;
    } // Kar��t�r�lanResim(..) kurucusu sonu...

    void kar��t�r() {
        Random rasgele = new Random();
        int ri;
        for (int i=0; i<b�l�mSay�s�; i++) {
            while ((ri = rasgele.nextInt (konumSay�s�)) == i);
            int ge�ici = b�l�mler[i];
            b�l�mler[i] = b�l�mler[ri];
            b�l�mler[ri] = ge�ici;
        } // for d�ng�s� sonu...
    } // kar��t�r() metodu sonu...

    public Dimension getPreferredSize() {return new Dimension (en, boy);}

    public void paint (Graphics g) {
        int dx, dy;
        for (int x=0; x<konumSay�s�; x++) {
            int sx = x*b�l�mEn;
            for (int y=0; y<konumSay�s�; y++) {
                int sy = y*b�l�mBoy;
                int b�l�m = b�l�mler[x*konumSay�s�+y];
                dx = (b�l�m / konumSay�s�) * b�l�mEn;
                dy = (b�l�m % konumSay�s�) * b�l�mBoy;
                g.drawImage (tamponluResim,
                        dx, dy, dx+b�l�mEn, dy+b�l�mBoy,
                        sx, sy, sx+b�l�mEn, sy+b�l�mBoy,
                        null);
            } // for-y d�ng�s� sonu...
        } // for-x d�ng�s� sonu...
    } // paint(..) haz�r metodu sonu...
} // Kar��t�r�lanResim s�n�f� sonu...