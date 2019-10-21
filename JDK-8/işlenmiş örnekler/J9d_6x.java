// J9d_6x.java: LineBreakPanel (Sat�rK�rmaPaneli) alt-�rne�i.

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
    private LineBreakMeasurer sat�rK�rma�l��c�s�;
    private int paragraf�n�lkEndeksi;
    private int paragraf�nSonEndeksi;
    private static final Hashtable<TextAttribute, Object> k�ymaTabloListesi = new Hashtable<TextAttribute, Object>();
    static {
        k�ymaTabloListesi.put (TextAttribute.FAMILY, "Serif");
        k�ymaTabloListesi.put (TextAttribute.SIZE, new Float (18.0));
    } // sta.. blok sonu...
    private static AttributedString vanGoghParagraf� = new AttributedString (
        "Many people believe that Vincent van Gogh painted his best works " +
        "during the two-year period he spent in Provence. Here is where he " +
        "painted The Starry Night--which some consider to be his greatest " +
        "work of all. However, as his artistic brilliance reached new " +
        "heights in Provence, his physical and mental health plummeted. ",
        k�ymaTabloListesi);

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Tesad�fi koyu renkler...
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor (new Color ( (int)(Math.random()*156+100), (int)(Math.random()*156+100), (int)(Math.random()*156+100) )); // Tesad�fi a��k renkler...

        if (sat�rK�rma�l��c�s� == null) {
            AttributedCharacterIterator taray�c� = vanGoghParagraf�.getIterator();
            paragraf�n�lkEndeksi = taray�c�.getBeginIndex();
            paragraf�nSonEndeksi = taray�c�.getEndIndex();
            FontRenderContext fonSunucu = g2d.getFontRenderContext();
            sat�rK�rma�l��c�s� = new LineBreakMeasurer (taray�c�, fonSunucu);
        } // if karar� sonu...

        // Sat�r�n k�r�lma noktas� olarak bile�ke geni�li�ini kur...
        float k�rmaEni = (float)getSize().width;
        float �izimKonumuY = 0;
        sat�rK�rma�l��c�s�.setPosition (paragraf�n�lkEndeksi);

        // Taray�c�daki paragraf�n k�r�lma enli sat�rlar� bitinceye kadar d�ng�de kal...
        while (sat�rK�rma�l��c�s�.getPosition() < paragraf�nSonEndeksi) {
            TextLayout metinSerilimi = sat�rK�rma�l��c�s�.nextLayout (k�rmaEni);
            // Serilim soldan-sa�a'ysa ilkX endeksi=0'd�r...
            float �izimKonumuX = metinSerilimi.isLeftToRight()? 0 : k�rmaEni - metinSerilimi.getAdvance();
            �izimKonumuY += metinSerilimi.getAscent();
            metinSerilimi.draw (g2d, �izimKonumuX, �izimKonumuY);
            �izimKonumuY += metinSerilimi.getDescent() + metinSerilimi.getLeading();
        } // while d�ng�s� sonu...
    } // paint(..) haz�r metodu sonu...
} // J9d_6x s�n�f� sonu...