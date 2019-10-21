// J8e_1.java: ArabicDigits (Arap�aRakamlar) �rne�i.

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.NumericShaper;

import java.applet.Applet;

import java.util.HashMap;

public class J8e_1 extends Applet {
    Arap�aRakamlarPaneli arPaneli;

    //public J8e_1() {arPaneli = new Arap�aRakamlarPaneli ("Lucida Sans");} // Parametresiz kurucu...
    public J8e_1 (String fonAd�) {
        arPaneli = new Arap�aRakamlarPaneli (fonAd�);
        arPaneli.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1f) );
        arPaneli.setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.6f) );
        setLayout (new BorderLayout());
        add ("Center", arPaneli);
    } // Parametreli kurucu...

    static class Arap�aRakamlarPaneli extends Panel {
        String yaz�Fonu;
        TextLayout metinSerilimi;
        private static final String metin = "0 1 2 3 4 5 6 7 8 9";

        void dumpChars (char[] karakterler) {
            for (int i = 0; i < karakterler.length; ++i) {
                char k = karakterler[i];
                if (k < 0x7f) System.out.print (k);
                else {String dizge = Integer.toHexString (k);
                    dizge = "0000".substring (dizge.length()) + dizge;
                    System.out.print ("0x" + dizge);
                } // else karar� sonu...
            } // for d�ng�s� sonu...
            System.out.println();
        } // dumpChars(..) haz�r metodu sonu...

        Arap�aRakamlarPaneli (String yaz�Fonu) {// Kurucu...
            HashMap k�ymaHarita = new HashMap();
            Font yaz�Tipi = new Font (yaz�Fonu, Font.PLAIN, 60);
            k�ymaHarita.put (TextAttribute.FONT, yaz�Tipi);
            k�ymaHarita.put (TextAttribute.NUMERIC_SHAPING, NumericShaper.getShaper (NumericShaper.ARABIC));
            FontRenderContext fonSunucu��eri�i = new FontRenderContext (null, false, false);
            metinSerilimi = new TextLayout (metin, k�ymaHarita, fonSunucu��eri�i);
        } // Arap�aRakamlarPaneli(..) kurucusu sonu...

        public void paint (Graphics g) {// start() etkinle�tirir...
            Graphics2D g2d = (Graphics2D)g;
            metinSerilimi.draw (g2d, 10, 50);
        } // paint(..) haz�r metodu sonu...
    } // Arap�aRakamlarPaneli s�n�f� sonu...

    public static void main (String arg�man[]) {
        String fonAd� = arg�man.length > 0? arg�man[0] : "Lucida Sans";

        J8e_1 g�steri = new J8e_1 (fonAd�); // Parametreli kurucuyu �a��r�r...
        g�steri.start(); // paint(..)'i etkinle�tirir...

        Frame �er�eve = new Frame ("[9<--0] Arap�a Rakamlar");
        �er�eve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});

        �er�eve.add ("Center", g�steri);
        �er�eve.setSize (600,100);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J8e_1 s�n�f� sonu...