// J9e_7.java: SeeThroughImageApplet (G�rResimliApleti) �rne�i.

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.imageio.ImageIO;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JSlider;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.net.URL;
import java.net.MalformedURLException;

import java.io.File;
import java.io.IOException;

// Gereken resim dosyas�: duke_skateboard.jpg
public class J9e_7 extends JApplet {
    static String resimDosyas�Ad� = "resim/duke_skateboard.jpg";
    private URL resimYureli;

    public J9e_7 (URL resimYureli) {this.resimYureli = resimYureli;}

/* // main(..) metodunu iptal edip JApplet init()'le appletviewer'l� da �al��t�r�labilinir...
    public void init() {
        try {resimYureli = new URL (getCodeBase(), resimDosyas�Ad�);
        }catch (MalformedURLException ist) {}
        kurUI();
    } // init() haz�r aplet metodu sonu...
*/
    public void kurUI() {
        final Bile�enleG�r g�r = new Bile�enleG�r (resimYureli);
        add ("Center", g�r);
        JSlider netlikS�rg�s� = new JSlider (0, 100);
        netlikS�rg�s�.addChangeListener (new ChangeListener() {// Dinleyiciye duyarl�...
            public void stateChanged (ChangeEvent olay) {
                JSlider s�rg� = (JSlider)olay.getSource();
                g�r.netli�iAyarla (s�rg�.getValue()/100f);
                g�r.repaint();
            } // stateChanged(..) haz�r metodu sonu...
        }); // net.. ifadesi sonu...
        Dimension bile�keEbat� = g�r.getPreferredSize();
        Dimension s�rg�Ebat� = netlikS�rg�s�.getPreferredSize();
        resize (bile�keEbat�.width, bile�keEbat�.height+s�rg�Ebat�.height);
        add ("South", netlikS�rg�s�);
    } // kurUI() metodu sonu...

    public static void main (String s[]) {
        JFrame �er�eve = new JFrame ("Resmin Netli�ini S�rg�yle G�r");
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        URL resimYureli = null;
        try {resimYureli = new File (resimDosyas�Ad�).toURI().toURL();
        }catch (MalformedURLException ist) {} // Ald�rma...
        J9e_7 resmiG�r = new J9e_7 (resimYureli); // Kurucuyu �a��r�r...
        resmiG�r.kurUI();
        �er�eve.add ("Center", resmiG�r);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J9e_7 s�n�f� sonu...

class Bile�enleG�r extends Component {
    private BufferedImage tamponluResim;
    float[] ana�entikler = { 1f, 1f, 1f, 0.5f };
    float[] ara�entikler = new float[4];
    RescaleOp �l�eklemeTercihi;
    
    public Bile�enleG�r (URL resimYureli) {// Kurucu...
        try {BufferedImage tResim = ImageIO.read (resimYureli);
            int en = tResim.getWidth (null);
            int boy = tResim.getHeight (null);
            tamponluResim = new BufferedImage (en, boy, BufferedImage.TYPE_INT_ARGB);
            Graphics g = tamponluResim.getGraphics();
            g.drawImage (tResim, 0, 0, null);
        }catch (IOException ist) {System.err.println ("Resim okunamad�!..");
            //System.exit(1);
        } // try-catch blo�u sonu...

        netli�iAyarla (0.5f);
    } // Bile�enleG�r(..) kurucusu sonu...

    public Dimension getPreferredSize() {return new Dimension (tamponluResim.getWidth(), tamponluResim.getHeight());}

    public void netli�iAyarla (float netlik) {
        ana�entikler[3] = netlik;
        �l�eklemeTercihi = new RescaleOp (ana�entikler, ara�entikler, null);
    } // netli�iAyarla(..) metodu sonu...

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor (Color.WHITE);
        g2d.fillRect (0,0, getWidth(), getHeight());
        g2d.setColor (Color.BLUE);
        g2d.setFont (new Font ("Dialog", Font.BOLD, 20));
        g2d.drawString ("Java 2B muhte�em!", 10, 80);
        g2d.drawImage (tamponluResim, �l�eklemeTercihi, 0, 0);
    } // paint(..) haz�r metodu sonu...
} // Bile�enleG�r s�n�f� sonu...