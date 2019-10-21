// J8e_9x.java: ShowString (GösterilenDizge) alt-örneði.

import java.awt.Color;
import java.awt.Frame;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class J8e_9x extends Frame {
    FontMetrics yazýTipiÖlçeði;
    String çýktýDizgesi;
    
    J8e_9x (String hedef, String baþlýk) {// Kurucu...
        setTitle (baþlýk);
        çýktýDizgesi = hedef;

        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent olay) {System.exit (0);}
        }); // add.. ifadesi sonu...

        setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1.0f) );
        setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.75f) );

        Font yazýTipi = new Font ("Monospaced", Font.PLAIN, 36);
        yazýTipiÖlçeði = getFontMetrics (yazýTipi);
        setFont (yazýTipi);

        int en = 0;
        for (int i = 0; i < çýktýDizgesi.length(); i++) en += yazýTipiÖlçeði.charWidth (çýktýDizgesi.charAt (i));
        en += 24;
        setSize (en, yazýTipiÖlçeði.getHeight() + 60);
        setLocation (getSize().width/2, getSize().height/2);
        setVisible (true);
    } // J8e_9x(..) kurucusu sonu...

    public void paint (Graphics g) {
        Insets boþluk = getInsets();
        int x = boþluk.left;
        int y = boþluk.top;
        g.drawString (çýktýDizgesi, x + 6, y + yazýTipiÖlçeði.getAscent() + 14);
    } // paint(..) hazýr metodu sonu...
} // J8e_9x sýnýfý sonu...