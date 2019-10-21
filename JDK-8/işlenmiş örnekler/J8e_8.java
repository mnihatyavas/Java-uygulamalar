// J8e_8.java: ShapedDigits (ÞekilliRakamlar) örneði.

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.font.NumericShaper;
import java.awt.font.LineBreakMeasurer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.applet.Applet;

import java.text.AttributedString;
import java.text.AttributedCharacterIterator;

import java.util.HashMap;

public class J8e_8 extends Applet {
    ÞekilleyenPanel panel;
    static final String varsayýlýFon = "Lucida Sans";

    public J8e_8 (String fonAdý) {panel = new ÞekilleyenPanel (fonAdý);}

    public void ilkDeðerler() {
        setLayout (new BorderLayout());
        add ("Center", panel);
    } // ilkDeðerler() metodu sonu...

    static class ÞekilleyenPanel extends Panel {
        String fonAdý;
        TextLayout[][] metinSerilimleri;
        String[] yerelRakamlarBaþlýklarý;
        private static final String yerelRakamlarMetni =
            "-123 (Latin) 456.00; (Arabic) \u0641\u0642\u0643 -789; (Thai) \u0e01\u0e33 01.23"; // fekal...

        ÞekilleyenPanel (String fonAdý) {// Kurucu...
            setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1.0f) );
            setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.55f) );

            Font yerelRakamlarFonu = new Font (fonAdý, Font.PLAIN, 12);
            System.out.println ("Verilen fon: " + fonAdý + ", Uygulanan fon: " + yerelRakamlarFonu.getFontName());
            setFont (yerelRakamlarFonu);

            Font yazýTipi = new Font (fonAdý, Font.PLAIN, 18);
            System.out.println ("Verilen fon: " + fonAdý + ", Uygulanan fon: " + yazýTipi.getFontName());

            FontRenderContext fonSunucu = new FontRenderContext (null, false, false);

            metinSerilimleri = new TextLayout[5][2];

            HashMap fonListesi = new HashMap();
            fonListesi.put (TextAttribute.FONT, yazýTipi);
            metinSerilimleri[0][0] = new TextLayout (yerelRakamlarMetni, fonListesi, fonSunucu);
            AttributedCharacterIterator tarayýcý = new AttributedString (yerelRakamlarMetni, fonListesi).getIterator();
            metinSerilimleri[0][1] = new LineBreakMeasurer (tarayýcý, fonSunucu).nextLayout (Float.MAX_VALUE);

            NumericShaper arapça = NumericShaper.getShaper (NumericShaper.ARABIC);
            fonListesi.put (TextAttribute.NUMERIC_SHAPING, arapça);
            metinSerilimleri[1][0] = new TextLayout (yerelRakamlarMetni, fonListesi, fonSunucu);
            tarayýcý = new AttributedString (yerelRakamlarMetni, fonListesi).getIterator();
            metinSerilimleri[1][1] = new LineBreakMeasurer (tarayýcý, fonSunucu).nextLayout (Float.MAX_VALUE);

            NumericShaper metinArapçasý = NumericShaper.getContextualShaper (NumericShaper.ARABIC, NumericShaper.ARABIC);
            fonListesi.put (TextAttribute.NUMERIC_SHAPING, metinArapçasý);
            metinSerilimleri[2][0] = new TextLayout (yerelRakamlarMetni, fonListesi, fonSunucu);
            tarayýcý = new AttributedString (yerelRakamlarMetni, fonListesi).getIterator();
            metinSerilimleri[2][1] = new LineBreakMeasurer (tarayýcý, fonSunucu).nextLayout (Float.MAX_VALUE);

            NumericShaper metinArapçasýASCII = NumericShaper.getContextualShaper (NumericShaper.ARABIC);
            fonListesi.put (TextAttribute.NUMERIC_SHAPING, metinArapçasýASCII);
            metinSerilimleri[3][0] = new TextLayout (yerelRakamlarMetni, fonListesi, fonSunucu);
            tarayýcý = new AttributedString (yerelRakamlarMetni, fonListesi).getIterator();
            metinSerilimleri[3][1] = new LineBreakMeasurer (tarayýcý, fonSunucu).nextLayout (Float.MAX_VALUE);

            NumericShaper tümMetinler = NumericShaper.getContextualShaper (NumericShaper.ALL_RANGES);
            fonListesi.put (TextAttribute.NUMERIC_SHAPING, tümMetinler);
            metinSerilimleri[4][0] = new TextLayout (yerelRakamlarMetni, fonListesi, fonSunucu);
            tarayýcý = new AttributedString (yerelRakamlarMetni, fonListesi).getIterator();
            metinSerilimleri[4][1] = new LineBreakMeasurer (tarayýcý, fonSunucu).nextLayout (Float.MAX_VALUE);

            yerelRakamlarBaþlýklarý = new String[] {
                "Latince - tüm rakamlar Latince (ASCII)",
                "Arapça - tüm rakamlar Arapça",
                "Arapça Metin (Varsayýlý Arapça) - sadece öndeki ve Arapça metin sonrasý rakamlar Arapça",
                "Arapça Metin (Varsayýlý Latince) - sadece Arapça metin sonrasý rakam Arapça",
                "Tüm Metin (Varsayýlý Latince) - öndeki rakamlar Latince (ASCII), diðerleri metne uygun"
            }; // yerelRakamlarBaþlýklarý[] dizisi sonu...
        } // ÞekilleyenPanel(..) kurucusu sonu...

        public void paint (Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            float x = 5;
            float y = 5;

            for (int i = 0; i < metinSerilimleri.length; ++i) {
                y += 18;
                g2d.drawString (yerelRakamlarBaþlýklarý[i], x, y);
                y += 4;

                for (int j = 0; j < 2; ++j) {// 2.nin 1.den farkýný göremedim!..
                    y += metinSerilimleri[i][j].getAscent();
                    metinSerilimleri[i][j].draw (g2d, x, y);
                    y += metinSerilimleri[i][j].getDescent() + metinSerilimleri[i][j].getLeading();
                } // for-j döngüsü sonu...
            } // for-i döngüsü sonu..
        } // paint(..) hazýr metodu sonu...
    } // ÞekilleyenPanel sýnýfý sonu...

    public static void main (String argüman[]) {
        String fonAdý = argüman.length > 0? argüman[0] : varsayýlýFon;

        J8e_8 þekilliRakamlar = new J8e_8 (fonAdý); // Kurucuyu çaðýrýr...
        þekilliRakamlar.ilkDeðerler();

        Frame çerçeve = new Frame ("Þekilli Rakamlar");
        çerçeve.addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent olay) {System.exit (0);}
        }); // çer.. ifadesi sonu...

        çerçeve.add ("Center", þekilliRakamlar);
        çerçeve.setSize (600, 380);
        çerçeve.setLocation (500, 100);
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J8e_8 sýnýfý sonu...

/* Çýktý:
**  >java J8e_8  **
Verilen fon: Lucida Sans, Uygulanan fon: Lucida Sans Regular
Verilen fon: Lucida Sans, Uygulanan fon: Lucida Sans Regular

**  >java J8e_8 "times new roman"  ** TEKRAR
Verilen fon: times new roman, Uygulanan fon: Times New Roman
Verilen fon: times new roman, Uygulanan fon: Times New Roman
*/