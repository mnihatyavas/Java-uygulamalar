// J8e_1.java: ArabicDigits (ArapçaRakamlar) örneði.

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
    ArapçaRakamlarPaneli arPaneli;

    //public J8e_1() {arPaneli = new ArapçaRakamlarPaneli ("Lucida Sans");} // Parametresiz kurucu...
    public J8e_1 (String fonAdý) {
        arPaneli = new ArapçaRakamlarPaneli (fonAdý);
        arPaneli.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1f) );
        arPaneli.setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.6f) );
        setLayout (new BorderLayout());
        add ("Center", arPaneli);
    } // Parametreli kurucu...

    static class ArapçaRakamlarPaneli extends Panel {
        String yazýFonu;
        TextLayout metinSerilimi;
        private static final String metin = "0 1 2 3 4 5 6 7 8 9";

        void dumpChars (char[] karakterler) {
            for (int i = 0; i < karakterler.length; ++i) {
                char k = karakterler[i];
                if (k < 0x7f) System.out.print (k);
                else {String dizge = Integer.toHexString (k);
                    dizge = "0000".substring (dizge.length()) + dizge;
                    System.out.print ("0x" + dizge);
                } // else kararý sonu...
            } // for döngüsü sonu...
            System.out.println();
        } // dumpChars(..) hazýr metodu sonu...

        ArapçaRakamlarPaneli (String yazýFonu) {// Kurucu...
            HashMap kýymaHarita = new HashMap();
            Font yazýTipi = new Font (yazýFonu, Font.PLAIN, 60);
            kýymaHarita.put (TextAttribute.FONT, yazýTipi);
            kýymaHarita.put (TextAttribute.NUMERIC_SHAPING, NumericShaper.getShaper (NumericShaper.ARABIC));
            FontRenderContext fonSunucuÝçeriði = new FontRenderContext (null, false, false);
            metinSerilimi = new TextLayout (metin, kýymaHarita, fonSunucuÝçeriði);
        } // ArapçaRakamlarPaneli(..) kurucusu sonu...

        public void paint (Graphics g) {// start() etkinleþtirir...
            Graphics2D g2d = (Graphics2D)g;
            metinSerilimi.draw (g2d, 10, 50);
        } // paint(..) hazýr metodu sonu...
    } // ArapçaRakamlarPaneli sýnýfý sonu...

    public static void main (String argüman[]) {
        String fonAdý = argüman.length > 0? argüman[0] : "Lucida Sans";

        J8e_1 gösteri = new J8e_1 (fonAdý); // Parametreli kurucuyu çaðýrýr...
        gösteri.start(); // paint(..)'i etkinleþtirir...

        Frame çerçeve = new Frame ("[9<--0] Arapça Rakamlar");
        çerçeve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});

        çerçeve.add ("Center", gösteri);
        çerçeve.setSize (600,100);
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J8e_1 sýnýfý sonu...