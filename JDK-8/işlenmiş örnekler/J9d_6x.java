// J9d_6x.java: LineBreakPanel (SatýrKýrmaPaneli) alt-örneði.

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.swing.JPanel;

import java.util.Hashtable;

class J9d_6x extends JPanel {
    private LineBreakMeasurer satýrKýrmaÖlçücüsü;
    private int paragrafýnÝlkEndeksi;
    private int paragrafýnSonEndeksi;
    private static final Hashtable<TextAttribute, Object> kýymaTabloListesi = new Hashtable<TextAttribute, Object>();
    static {
        kýymaTabloListesi.put (TextAttribute.FAMILY, "Serif");
        kýymaTabloListesi.put (TextAttribute.SIZE, new Float (18.0));
    } // sta.. blok sonu...
    private static AttributedString vanGoghParagrafý = new AttributedString (
        "Many people believe that Vincent van Gogh painted his best works " +
        "during the two-year period he spent in Provence. Here is where he " +
        "painted The Starry Night--which some consider to be his greatest " +
        "work of all. However, as his artistic brilliance reached new " +
        "heights in Provence, his physical and mental health plummeted. ",
        kýymaTabloListesi);

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Tesadüfi koyu renkler...
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor (new Color ( (int)(Math.random()*156+100), (int)(Math.random()*156+100), (int)(Math.random()*156+100) )); // Tesadüfi açýk renkler...

        if (satýrKýrmaÖlçücüsü == null) {
            AttributedCharacterIterator tarayýcý = vanGoghParagrafý.getIterator();
            paragrafýnÝlkEndeksi = tarayýcý.getBeginIndex();
            paragrafýnSonEndeksi = tarayýcý.getEndIndex();
            FontRenderContext fonSunucu = g2d.getFontRenderContext();
            satýrKýrmaÖlçücüsü = new LineBreakMeasurer (tarayýcý, fonSunucu);
        } // if kararý sonu...

        // Satýrýn kýrýlma noktasý olarak bileþke geniþliðini kur...
        float kýrmaEni = (float)getSize().width;
        float çizimKonumuY = 0;
        satýrKýrmaÖlçücüsü.setPosition (paragrafýnÝlkEndeksi);

        // Tarayýcýdaki paragrafýn kýrýlma enli satýrlarý bitinceye kadar döngüde kal...
        while (satýrKýrmaÖlçücüsü.getPosition() < paragrafýnSonEndeksi) {
            TextLayout metinSerilimi = satýrKýrmaÖlçücüsü.nextLayout (kýrmaEni);
            // Serilim soldan-saða'ysa ilkX endeksi=0'dýr...
            float çizimKonumuX = metinSerilimi.isLeftToRight()? 0 : kýrmaEni - metinSerilimi.getAdvance();
            çizimKonumuY += metinSerilimi.getAscent();
            metinSerilimi.draw (g2d, çizimKonumuX, çizimKonumuY);
            çizimKonumuY += metinSerilimi.getDescent() + metinSerilimi.getLeading();
        } // while döngüsü sonu...
    } // paint(..) hazýr metodu sonu...
} // J9d_6x sýnýfý sonu...