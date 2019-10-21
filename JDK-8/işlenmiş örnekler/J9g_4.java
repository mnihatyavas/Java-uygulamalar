// J9g_4.java: Pear (Armut) örneði.

import java.applet.Applet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Area;

import javax.swing.JFrame;

public class J9g_4 extends Applet {
    Ellipse2D.Double çember, oval, yaprak, sap;
    Area çemb, ov, yaprak1, yaprak2, sap1, sap2;

    public void init() {
        çember = new Ellipse2D.Double();
        oval = new Ellipse2D.Double();
        yaprak = new Ellipse2D.Double();
        sap = new Ellipse2D.Double();

        çemb = new Area (çember);
        ov = new Area (oval);
        yaprak1 = new Area (yaprak);
        yaprak2 = new Area (yaprak);
        sap1 = new Area (sap);
        sap2 = new Area (sap);

        //setBackground (Color.CYAN); // Zemin rengini de tesadüfi yapalým...
        //repaint(); // Belirtilmese de varsayýlý kullanýyor...
   } // init() hazýr metodu sonu...

    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Dimension ebat = getSize();
        int en = ebat.width; // Bileþen için...
        int boy = ebat.height;
        double eEn = en/2; // Elips için...
        double eBoy = boy/2;

        // Farklý tesadüfi zemin rengi için pencere ebatýyla oynayýn...
        g2.setColor (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random() ) );
        g2.fillRect (0,0, en,boy);

        // 1.yaprak için 2 elip çizimli yapraklar kesiþim alaný tesadüfi boyayla dolgulanýr...
        g2.setColor (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random() ) );
        yaprak.setFrame (eEn-16, eBoy-29, 15.0, 15.0);
        yaprak1 = new Area (yaprak);
        yaprak.setFrame (eEn-14, eBoy-47, 30.0, 30.0);
        yaprak2 = new Area (yaprak); 
        yaprak1.intersect (yaprak2);   
        g2.fill (yaprak1);   

        // 2.yaprak için 2 elips çizimli yapraklar kesiþim alaný tesadüfi boyayla dolgulanýr...
        yaprak.setFrame (eEn+1, eBoy-29, 15.0, 15.0);
        yaprak1 = new Area (yaprak);
        yaprak2.intersect (yaprak1);
        g2.fill (yaprak2);

        // Sap için 2 elips çizimli sap çýkarým alaný tesadüfi boyayla dolgulanýr...
        g2.setColor (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random() ) );
        sap.setFrame (eEn, eBoy-42, 40.0, 40.0);
        sap1 = new Area (sap);
        sap.setFrame (eEn+3, eBoy-47, 50.0, 50.0);
        sap2 = new Area (sap);
        sap1.subtract (sap2);
        g2.fill (sap1);

        // Armut için 2 elips çember ve ocal çizimli birleþim alaný tesadüfi boyayla dolgulanýr...
        g2.setColor (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random() ) );
        çember.setFrame (eEn-25, eBoy, 50.0, 50.0);
        oval.setFrame (eEn-19, eBoy-20, 40.0, 70.0);
        çemb = new Area (çember);
        ov = new Area (oval);
        çemb.add (ov);
        g2.fill (çemb);
    } // paint(..) hazýr metodu sonu...

    public static void main (String s[]) {
        JFrame çerçeve = new JFrame ("Armut");
        Applet aplet = new J9g_4(); // Varsayýlý kuruculu sýnýf nesnesi yaratýlýr...
         
        çerçeve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        çerçeve.getContentPane().add ("Center", aplet);
        aplet.init(); // init() hazýr aplet metodu çaðýrýlýr...
        çerçeve.pack();
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setSize (new Dimension (150, 200));
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J9g_4 sýnýfý sonu...