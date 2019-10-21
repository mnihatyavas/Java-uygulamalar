// J9g_1.java: ClipImage (Yerle�tirilenResim) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.Image;
import java.awt.MediaTracker;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JApplet;
import javax.swing.JFrame;

import java.net.URL;
import java.net.MalformedURLException;

// Gereken resim dosyas�: clouds.jpg
public class J9g_1 extends JApplet implements Runnable {
    private Image resim;
    private final double OINC[] = {5.0, 3.0};
    private final double SINC[] = {5.0, 5.0};
    private double x, y;
    private double ix = OINC[0]; // increment/art��lar...
    private double iy = OINC[1];
    private double iEn = SINC[0];
    private double iBoy = SINC[1];
    private double eEn, eBoy; // elips eni ve boyu...
    private GeneralPath poligon = new GeneralPath();
    private AffineTransform �zellikAktar�m� = new AffineTransform();
    private BasicStroke temelKoyuluk = new BasicStroke(20.0f);
    private Arc2D yay = new Arc2D.Float();
    private Ellipse2D elips = new Ellipse2D.Float();
    private RoundRectangle2D yuvarl�Kutu = new RoundRectangle2D.Float();
    private Rectangle2D kutu = new Rectangle2D.Float();
    private Color k�rm�z�Kar���m = new Color(255, 0, 0, 120);
    private Color ye�ilKar���m = new Color(0, 255, 0, 120);
    private Thread sicim;
    private BufferedImage tamponResmi;
    private int en, boy; // Tuval'in eni ve boyu...
    private boolean yeniTamponResmiMi;

    public void init() {
        try {resim = getImage (yureliAl ("resim/clouds.jpg"));
            MediaTracker izleyici = new MediaTracker (this);
            izleyici.addImage (resim, 0);
            izleyici.waitForID (0);
        }catch (Exception ist) {System.err.println ("HATA: Resim y�kleme ba�ar�s�z!..");}
    } // init() haz�r metodu sonu...

    protected URL yureliAl (String dosyaAd�) {
        URL yurel = null;
        try {yurel = new URL (this.getCodeBase(), dosyaAd�);
        }catch (MalformedURLException ist) {
            System.err.println ("Resim dosyas� okunamad�!..");
            return null;
        } // try-catch blo�u sonu...
        return yurel;
    } // yureliAl(..) metodu sonu...

    public void g�steriyi�iz (Graphics2D g2) {
        if (yeniTamponResmiMi) {
            x = Math.random()*en;
            y = Math.random()*boy;
            eEn = (Math.random()*en)/2;
            eBoy = (Math.random()*boy)/2;
        } // if karar� sonu...
        x += ix;
        y += iy;
        eEn += iEn;
        eBoy += iBoy;
        if (eEn > en/2 ) {
            eEn = en/2;
            iEn = Math.random() * -en/16 - 1;
        } // if karar� sonu...
        if (eEn < en/8 ) {
            eEn = en/8;
            iEn = Math.random() * en/16 + 1;
        } // if karar� sonu...
        if (eBoy > boy/2 ) {
            eBoy = boy/2;
            iBoy = Math.random() * -boy/16 - 1;
        } // if karar� sonu...
        if (eBoy < boy/8 ) {
            eBoy = boy/8;
            iBoy = Math.random() * boy/16 + 1;
        } // if karar� sonu...
        if ((x+eEn) > en) {
            x = (en - eEn)-1;
            ix = Math.random() * -en/32 - 1;
        } // if karar� sonu...
        if (x < 0) {
            x = 2;
            ix = Math.random() * en/32 + 1;
        } // if karar� sonu...
        if ((y+eBoy) > boy ) {
            y = (boy - eBoy)-2;
            iy = Math.random() * -boy/32 - 1;
        } // if karar� sonu...
        if (y < 0) {
            y = 2;
            iy = Math.random() * boy/32 + 1;
        } // if karar� sonu...

        elips.setFrame (x, y, eEn, eBoy);
        g2.setClip (elips);

        kutu.setRect (x+5, y+5, eEn-10, eBoy-10);
        g2.clip (kutu);

        g2.drawImage (resim, 0, 0, en, boy, this);

        poligon.reset();
        poligon.moveTo (-en / 2.0f, -boy / 8.0f);
        poligon.lineTo (+en / 2.0f, -boy / 8.0f);
        poligon.lineTo (-en / 4.0f, +boy / 2.0f);
        poligon.lineTo (+0.0f, -boy / 2.0f);
        poligon.lineTo (+en / 4.0f, +boy / 2.0f);
        poligon.closePath();

        �zellikAktar�m�.setToIdentity();
        �zellikAktar�m�.translate (en*.5f, boy*.5f);
        g2.transform (�zellikAktar�m�);
        g2.setStroke (temelKoyuluk);
        g2.setPaint (k�rm�z�Kar���m);
        g2.draw (poligon);

        �zellikAktar�m�.setToIdentity();
        g2.setTransform (�zellikAktar�m�);

        g2.setPaint (ye�ilKar���m);

        for (int yy = 0; yy < boy; yy += 50) {
            for (int xx = 0, i=0; xx < en; i++, xx += 50 ) {
                switch (i) {
                case 0 : yay.setArc (xx, yy, 25, 25, 45, 270, Arc2D.PIE);
                    g2.fill (yay); break;
                case 1 : elips.setFrame (xx, yy, 25, 25);
                    g2.fill (elips); break;
                case 2 : yuvarl�Kutu.setRoundRect (xx, yy, 25, 25, 4, 4);
                    g2.fill (yuvarl�Kutu); break;
                case 3 : kutu.setRect (xx, yy, 25, 25);
                    g2.fill (kutu);
                    i = -1;
                } // switch-case blo�u sonu...
            } // for-xx d�ng�s� sonu...
        } // for-yy d�ng�s� sonu...
    } // g�steriyi�iz(..) metodu sonu...

    public Graphics2D yaratG�steriGraphics2B (Graphics g) {
        Graphics2D g2 = null;

        if ( tamponResmi == null || tamponResmi.getWidth() !=en || tamponResmi.getHeight() != boy) {
            tamponResmi = (BufferedImage)createImage (en, boy);
            yeniTamponResmiMi = true;
        } // if karar� sonu...

        if (tamponResmi != null) {
            g2 = tamponResmi.createGraphics();
            g2.setBackground (getBackground());
        } // if karar� sonu...

        // Kalite/alias ve sunum/render �zellikleri al�n�r
        g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint (RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Tuval temizlenir...
        g2.clearRect (0,0, en,boy);

        return g2;
    } // yaratG�steriGraphics2B(..) metodu sonu...

    public void paint (Graphics g) {
        en = getWidth(); 
        boy = getHeight(); 

        if (en <= 0 || boy <= 0) return;

        Graphics2D g2 = yaratG�steriGraphics2B (g);
        g2.setPaint (Color.ORANGE);
        g2.fillRect (0, 0, en, boy); // Camg�be�i tuval zemini...

        g�steriyi�iz (g2);
        g2.dispose();

        if (tamponResmi != null && isShowing() ) g.drawImage (tamponResmi, 0, 0, this);

        yeniTamponResmiMi = false;
    } // paint(..) haz�r metodu sonu...

    public void run() {
        Thread ip = Thread.currentThread();
        while (sicim == ip && isShowing() ) {
            Graphics g = getGraphics();
            paint (g); // paint(..) haz�r metodu �al��t�r�l�r...
            g.dispose();
            sicim.yield();
        } // while d�ng�s� sonu...
        sicim = null;
    } // run() sicim haz�r metodu sonu...

    public synchronized void stop() {sicim = null;} // run() duraksat�l�r...
    public void start() {sicim = new Thread (this); sicim.start();} // run() �al��t�r�l�r...

    public static void main (String s[]) {
        final J9g_1 g�steri = new J9g_1(); // Varsay�l� kuruculu s�n�f nesnesi yarat�l�r, init() �al��t�r�l�r...
        WindowListener pencereDinleyicisi = new WindowAdapter() {
            public void windowClosing (WindowEvent olay) {System.exit (0);}
            public void windowDeiconified (WindowEvent olay) {g�steri.start();}
            public void windowIconified (WindowEvent olay) {g�steri.stop();}
        }; // Win.. ifadesi sonu...
        JFrame �er�eve = new JFrame ("Java 2B G�sterisi - Resim Yerle�tirme");
        �er�eve.addWindowListener (pencereDinleyicisi);
        �er�eve.getContentPane().add ("Center", g�steri);
        �er�eve.setSize (new Dimension (400, 300));
        �er�eve.setLocation (450, 50);
        �er�eve.setVisible (true);
        g�steri.start(); // start() haz�r metodu �al��t�r�l�r...
    } // main(..) metodu sonu...
} // J9g_1 s�n�f� sonu...