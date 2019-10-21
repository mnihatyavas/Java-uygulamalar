// J9g_4.java: Pear (Armut) �rne�i.

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
    Ellipse2D.Double �ember, oval, yaprak, sap;
    Area �emb, ov, yaprak1, yaprak2, sap1, sap2;

    public void init() {
        �ember = new Ellipse2D.Double();
        oval = new Ellipse2D.Double();
        yaprak = new Ellipse2D.Double();
        sap = new Ellipse2D.Double();

        �emb = new Area (�ember);
        ov = new Area (oval);
        yaprak1 = new Area (yaprak);
        yaprak2 = new Area (yaprak);
        sap1 = new Area (sap);
        sap2 = new Area (sap);

        //setBackground (Color.CYAN); // Zemin rengini de tesad�fi yapal�m...
        //repaint(); // Belirtilmese de varsay�l� kullan�yor...
   } // init() haz�r metodu sonu...

    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Dimension ebat = getSize();
        int en = ebat.width; // Bile�en i�in...
        int boy = ebat.height;
        double eEn = en/2; // Elips i�in...
        double eBoy = boy/2;

        // Farkl� tesad�fi zemin rengi i�in pencere ebat�yla oynay�n...
        g2.setColor (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random() ) );
        g2.fillRect (0,0, en,boy);

        // 1.yaprak i�in 2 elip �izimli yapraklar kesi�im alan� tesad�fi boyayla dolgulan�r...
        g2.setColor (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random() ) );
        yaprak.setFrame (eEn-16, eBoy-29, 15.0, 15.0);
        yaprak1 = new Area (yaprak);
        yaprak.setFrame (eEn-14, eBoy-47, 30.0, 30.0);
        yaprak2 = new Area (yaprak); 
        yaprak1.intersect (yaprak2);   
        g2.fill (yaprak1);   

        // 2.yaprak i�in 2 elips �izimli yapraklar kesi�im alan� tesad�fi boyayla dolgulan�r...
        yaprak.setFrame (eEn+1, eBoy-29, 15.0, 15.0);
        yaprak1 = new Area (yaprak);
        yaprak2.intersect (yaprak1);
        g2.fill (yaprak2);

        // Sap i�in 2 elips �izimli sap ��kar�m alan� tesad�fi boyayla dolgulan�r...
        g2.setColor (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random() ) );
        sap.setFrame (eEn, eBoy-42, 40.0, 40.0);
        sap1 = new Area (sap);
        sap.setFrame (eEn+3, eBoy-47, 50.0, 50.0);
        sap2 = new Area (sap);
        sap1.subtract (sap2);
        g2.fill (sap1);

        // Armut i�in 2 elips �ember ve ocal �izimli birle�im alan� tesad�fi boyayla dolgulan�r...
        g2.setColor (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random() ) );
        �ember.setFrame (eEn-25, eBoy, 50.0, 50.0);
        oval.setFrame (eEn-19, eBoy-20, 40.0, 70.0);
        �emb = new Area (�ember);
        ov = new Area (oval);
        �emb.add (ov);
        g2.fill (�emb);
    } // paint(..) haz�r metodu sonu...

    public static void main (String s[]) {
        JFrame �er�eve = new JFrame ("Armut");
        Applet aplet = new J9g_4(); // Varsay�l� kuruculu s�n�f nesnesi yarat�l�r...
         
        �er�eve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        �er�eve.getContentPane().add ("Center", aplet);
        aplet.init(); // init() haz�r aplet metodu �a��r�l�r...
        �er�eve.pack();
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setSize (new Dimension (150, 200));
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J9g_4 s�n�f� sonu...