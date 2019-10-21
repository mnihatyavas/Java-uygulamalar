// J9e_7.java: SeeThroughImageApplet (GörResimliApleti) örneði.

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

// Gereken resim dosyasý: duke_skateboard.jpg
public class J9e_7 extends JApplet {
    static String resimDosyasýAdý = "resim/duke_skateboard.jpg";
    private URL resimYureli;

    public J9e_7 (URL resimYureli) {this.resimYureli = resimYureli;}

/* // main(..) metodunu iptal edip JApplet init()'le appletviewer'lý da çalýþtýrýlabilinir...
    public void init() {
        try {resimYureli = new URL (getCodeBase(), resimDosyasýAdý);
        }catch (MalformedURLException ist) {}
        kurUI();
    } // init() hazýr aplet metodu sonu...
*/
    public void kurUI() {
        final BileþenleGör gör = new BileþenleGör (resimYureli);
        add ("Center", gör);
        JSlider netlikSürgüsü = new JSlider (0, 100);
        netlikSürgüsü.addChangeListener (new ChangeListener() {// Dinleyiciye duyarlý...
            public void stateChanged (ChangeEvent olay) {
                JSlider sürgü = (JSlider)olay.getSource();
                gör.netliðiAyarla (sürgü.getValue()/100f);
                gör.repaint();
            } // stateChanged(..) hazýr metodu sonu...
        }); // net.. ifadesi sonu...
        Dimension bileþkeEbatý = gör.getPreferredSize();
        Dimension sürgüEbatý = netlikSürgüsü.getPreferredSize();
        resize (bileþkeEbatý.width, bileþkeEbatý.height+sürgüEbatý.height);
        add ("South", netlikSürgüsü);
    } // kurUI() metodu sonu...

    public static void main (String s[]) {
        JFrame çerçeve = new JFrame ("Resmin Netliðini Sürgüyle Gör");
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        URL resimYureli = null;
        try {resimYureli = new File (resimDosyasýAdý).toURI().toURL();
        }catch (MalformedURLException ist) {} // Aldýrma...
        J9e_7 resmiGör = new J9e_7 (resimYureli); // Kurucuyu çaðýrýr...
        resmiGör.kurUI();
        çerçeve.add ("Center", resmiGör);
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J9e_7 sýnýfý sonu...

class BileþenleGör extends Component {
    private BufferedImage tamponluResim;
    float[] anaÇentikler = { 1f, 1f, 1f, 0.5f };
    float[] araÇentikler = new float[4];
    RescaleOp ölçeklemeTercihi;
    
    public BileþenleGör (URL resimYureli) {// Kurucu...
        try {BufferedImage tResim = ImageIO.read (resimYureli);
            int en = tResim.getWidth (null);
            int boy = tResim.getHeight (null);
            tamponluResim = new BufferedImage (en, boy, BufferedImage.TYPE_INT_ARGB);
            Graphics g = tamponluResim.getGraphics();
            g.drawImage (tResim, 0, 0, null);
        }catch (IOException ist) {System.err.println ("Resim okunamadý!..");
            //System.exit(1);
        } // try-catch bloðu sonu...

        netliðiAyarla (0.5f);
    } // BileþenleGör(..) kurucusu sonu...

    public Dimension getPreferredSize() {return new Dimension (tamponluResim.getWidth(), tamponluResim.getHeight());}

    public void netliðiAyarla (float netlik) {
        anaÇentikler[3] = netlik;
        ölçeklemeTercihi = new RescaleOp (anaÇentikler, araÇentikler, null);
    } // netliðiAyarla(..) metodu sonu...

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor (Color.WHITE);
        g2d.fillRect (0,0, getWidth(), getHeight());
        g2d.setColor (Color.BLUE);
        g2d.setFont (new Font ("Dialog", Font.BOLD, 20));
        g2d.drawString ("Java 2B muhteþem!", 10, 80);
        g2d.drawImage (tamponluResim, ölçeklemeTercihi, 0, 0);
    } // paint(..) hazýr metodu sonu...
} // BileþenleGör sýnýfý sonu...