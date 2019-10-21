// J9g_1.java: ClipImage (YerleþtirilenResim) örneði.

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

// Gereken resim dosyasý: clouds.jpg
public class J9g_1 extends JApplet implements Runnable {
    private Image resim;
    private final double OINC[] = {5.0, 3.0};
    private final double SINC[] = {5.0, 5.0};
    private double x, y;
    private double ix = OINC[0]; // increment/artýþlar...
    private double iy = OINC[1];
    private double iEn = SINC[0];
    private double iBoy = SINC[1];
    private double eEn, eBoy; // elips eni ve boyu...
    private GeneralPath poligon = new GeneralPath();
    private AffineTransform özellikAktarýmý = new AffineTransform();
    private BasicStroke temelKoyuluk = new BasicStroke(20.0f);
    private Arc2D yay = new Arc2D.Float();
    private Ellipse2D elips = new Ellipse2D.Float();
    private RoundRectangle2D yuvarlýKutu = new RoundRectangle2D.Float();
    private Rectangle2D kutu = new Rectangle2D.Float();
    private Color kýrmýzýKarýþým = new Color(255, 0, 0, 120);
    private Color yeþilKarýþým = new Color(0, 255, 0, 120);
    private Thread sicim;
    private BufferedImage tamponResmi;
    private int en, boy; // Tuval'in eni ve boyu...
    private boolean yeniTamponResmiMi;

    public void init() {
        try {resim = getImage (yureliAl ("resim/clouds.jpg"));
            MediaTracker izleyici = new MediaTracker (this);
            izleyici.addImage (resim, 0);
            izleyici.waitForID (0);
        }catch (Exception ist) {System.err.println ("HATA: Resim yükleme baþarýsýz!..");}
    } // init() hazýr metodu sonu...

    protected URL yureliAl (String dosyaAdý) {
        URL yurel = null;
        try {yurel = new URL (this.getCodeBase(), dosyaAdý);
        }catch (MalformedURLException ist) {
            System.err.println ("Resim dosyasý okunamadý!..");
            return null;
        } // try-catch bloðu sonu...
        return yurel;
    } // yureliAl(..) metodu sonu...

    public void gösteriyiÇiz (Graphics2D g2) {
        if (yeniTamponResmiMi) {
            x = Math.random()*en;
            y = Math.random()*boy;
            eEn = (Math.random()*en)/2;
            eBoy = (Math.random()*boy)/2;
        } // if kararý sonu...
        x += ix;
        y += iy;
        eEn += iEn;
        eBoy += iBoy;
        if (eEn > en/2 ) {
            eEn = en/2;
            iEn = Math.random() * -en/16 - 1;
        } // if kararý sonu...
        if (eEn < en/8 ) {
            eEn = en/8;
            iEn = Math.random() * en/16 + 1;
        } // if kararý sonu...
        if (eBoy > boy/2 ) {
            eBoy = boy/2;
            iBoy = Math.random() * -boy/16 - 1;
        } // if kararý sonu...
        if (eBoy < boy/8 ) {
            eBoy = boy/8;
            iBoy = Math.random() * boy/16 + 1;
        } // if kararý sonu...
        if ((x+eEn) > en) {
            x = (en - eEn)-1;
            ix = Math.random() * -en/32 - 1;
        } // if kararý sonu...
        if (x < 0) {
            x = 2;
            ix = Math.random() * en/32 + 1;
        } // if kararý sonu...
        if ((y+eBoy) > boy ) {
            y = (boy - eBoy)-2;
            iy = Math.random() * -boy/32 - 1;
        } // if kararý sonu...
        if (y < 0) {
            y = 2;
            iy = Math.random() * boy/32 + 1;
        } // if kararý sonu...

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

        özellikAktarýmý.setToIdentity();
        özellikAktarýmý.translate (en*.5f, boy*.5f);
        g2.transform (özellikAktarýmý);
        g2.setStroke (temelKoyuluk);
        g2.setPaint (kýrmýzýKarýþým);
        g2.draw (poligon);

        özellikAktarýmý.setToIdentity();
        g2.setTransform (özellikAktarýmý);

        g2.setPaint (yeþilKarýþým);

        for (int yy = 0; yy < boy; yy += 50) {
            for (int xx = 0, i=0; xx < en; i++, xx += 50 ) {
                switch (i) {
                case 0 : yay.setArc (xx, yy, 25, 25, 45, 270, Arc2D.PIE);
                    g2.fill (yay); break;
                case 1 : elips.setFrame (xx, yy, 25, 25);
                    g2.fill (elips); break;
                case 2 : yuvarlýKutu.setRoundRect (xx, yy, 25, 25, 4, 4);
                    g2.fill (yuvarlýKutu); break;
                case 3 : kutu.setRect (xx, yy, 25, 25);
                    g2.fill (kutu);
                    i = -1;
                } // switch-case bloðu sonu...
            } // for-xx döngüsü sonu...
        } // for-yy döngüsü sonu...
    } // gösteriyiÇiz(..) metodu sonu...

    public Graphics2D yaratGösteriGraphics2B (Graphics g) {
        Graphics2D g2 = null;

        if ( tamponResmi == null || tamponResmi.getWidth() !=en || tamponResmi.getHeight() != boy) {
            tamponResmi = (BufferedImage)createImage (en, boy);
            yeniTamponResmiMi = true;
        } // if kararý sonu...

        if (tamponResmi != null) {
            g2 = tamponResmi.createGraphics();
            g2.setBackground (getBackground());
        } // if kararý sonu...

        // Kalite/alias ve sunum/render özellikleri alýnýr
        g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint (RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Tuval temizlenir...
        g2.clearRect (0,0, en,boy);

        return g2;
    } // yaratGösteriGraphics2B(..) metodu sonu...

    public void paint (Graphics g) {
        en = getWidth(); 
        boy = getHeight(); 

        if (en <= 0 || boy <= 0) return;

        Graphics2D g2 = yaratGösteriGraphics2B (g);
        g2.setPaint (Color.ORANGE);
        g2.fillRect (0, 0, en, boy); // Camgöbeði tuval zemini...

        gösteriyiÇiz (g2);
        g2.dispose();

        if (tamponResmi != null && isShowing() ) g.drawImage (tamponResmi, 0, 0, this);

        yeniTamponResmiMi = false;
    } // paint(..) hazýr metodu sonu...

    public void run() {
        Thread ip = Thread.currentThread();
        while (sicim == ip && isShowing() ) {
            Graphics g = getGraphics();
            paint (g); // paint(..) hazýr metodu çalýþtýrýlýr...
            g.dispose();
            sicim.yield();
        } // while döngüsü sonu...
        sicim = null;
    } // run() sicim hazýr metodu sonu...

    public synchronized void stop() {sicim = null;} // run() duraksatýlýr...
    public void start() {sicim = new Thread (this); sicim.start();} // run() çalýþtýrýlýr...

    public static void main (String s[]) {
        final J9g_1 gösteri = new J9g_1(); // Varsayýlý kuruculu sýnýf nesnesi yaratýlýr, init() çalýþtýrýlýr...
        WindowListener pencereDinleyicisi = new WindowAdapter() {
            public void windowClosing (WindowEvent olay) {System.exit (0);}
            public void windowDeiconified (WindowEvent olay) {gösteri.start();}
            public void windowIconified (WindowEvent olay) {gösteri.stop();}
        }; // Win.. ifadesi sonu...
        JFrame çerçeve = new JFrame ("Java 2B Gösterisi - Resim Yerleþtirme");
        çerçeve.addWindowListener (pencereDinleyicisi);
        çerçeve.getContentPane().add ("Center", gösteri);
        çerçeve.setSize (new Dimension (400, 300));
        çerçeve.setLocation (450, 50);
        çerçeve.setVisible (true);
        gösteri.start(); // start() hazýr metodu çalýþtýrýlýr...
    } // main(..) metodu sonu...
} // J9g_1 sýnýfý sonu...