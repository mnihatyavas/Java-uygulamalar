// J8e_8.java: ShapedDigits (�ekilliRakamlar) �rne�i.

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
    �ekilleyenPanel panel;
    static final String varsay�l�Fon = "Lucida Sans";

    public J8e_8 (String fonAd�) {panel = new �ekilleyenPanel (fonAd�);}

    public void ilkDe�erler() {
        setLayout (new BorderLayout());
        add ("Center", panel);
    } // ilkDe�erler() metodu sonu...

    static class �ekilleyenPanel extends Panel {
        String fonAd�;
        TextLayout[][] metinSerilimleri;
        String[] yerelRakamlarBa�l�klar�;
        private static final String yerelRakamlarMetni =
            "-123 (Latin) 456.00; (Arabic) \u0641\u0642\u0643 -789; (Thai) \u0e01\u0e33 01.23"; // fekal...

        �ekilleyenPanel (String fonAd�) {// Kurucu...
            setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1.0f) );
            setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.55f) );

            Font yerelRakamlarFonu = new Font (fonAd�, Font.PLAIN, 12);
            System.out.println ("Verilen fon: " + fonAd� + ", Uygulanan fon: " + yerelRakamlarFonu.getFontName());
            setFont (yerelRakamlarFonu);

            Font yaz�Tipi = new Font (fonAd�, Font.PLAIN, 18);
            System.out.println ("Verilen fon: " + fonAd� + ", Uygulanan fon: " + yaz�Tipi.getFontName());

            FontRenderContext fonSunucu = new FontRenderContext (null, false, false);

            metinSerilimleri = new TextLayout[5][2];

            HashMap fonListesi = new HashMap();
            fonListesi.put (TextAttribute.FONT, yaz�Tipi);
            metinSerilimleri[0][0] = new TextLayout (yerelRakamlarMetni, fonListesi, fonSunucu);
            AttributedCharacterIterator taray�c� = new AttributedString (yerelRakamlarMetni, fonListesi).getIterator();
            metinSerilimleri[0][1] = new LineBreakMeasurer (taray�c�, fonSunucu).nextLayout (Float.MAX_VALUE);

            NumericShaper arap�a = NumericShaper.getShaper (NumericShaper.ARABIC);
            fonListesi.put (TextAttribute.NUMERIC_SHAPING, arap�a);
            metinSerilimleri[1][0] = new TextLayout (yerelRakamlarMetni, fonListesi, fonSunucu);
            taray�c� = new AttributedString (yerelRakamlarMetni, fonListesi).getIterator();
            metinSerilimleri[1][1] = new LineBreakMeasurer (taray�c�, fonSunucu).nextLayout (Float.MAX_VALUE);

            NumericShaper metinArap�as� = NumericShaper.getContextualShaper (NumericShaper.ARABIC, NumericShaper.ARABIC);
            fonListesi.put (TextAttribute.NUMERIC_SHAPING, metinArap�as�);
            metinSerilimleri[2][0] = new TextLayout (yerelRakamlarMetni, fonListesi, fonSunucu);
            taray�c� = new AttributedString (yerelRakamlarMetni, fonListesi).getIterator();
            metinSerilimleri[2][1] = new LineBreakMeasurer (taray�c�, fonSunucu).nextLayout (Float.MAX_VALUE);

            NumericShaper metinArap�as�ASCII = NumericShaper.getContextualShaper (NumericShaper.ARABIC);
            fonListesi.put (TextAttribute.NUMERIC_SHAPING, metinArap�as�ASCII);
            metinSerilimleri[3][0] = new TextLayout (yerelRakamlarMetni, fonListesi, fonSunucu);
            taray�c� = new AttributedString (yerelRakamlarMetni, fonListesi).getIterator();
            metinSerilimleri[3][1] = new LineBreakMeasurer (taray�c�, fonSunucu).nextLayout (Float.MAX_VALUE);

            NumericShaper t�mMetinler = NumericShaper.getContextualShaper (NumericShaper.ALL_RANGES);
            fonListesi.put (TextAttribute.NUMERIC_SHAPING, t�mMetinler);
            metinSerilimleri[4][0] = new TextLayout (yerelRakamlarMetni, fonListesi, fonSunucu);
            taray�c� = new AttributedString (yerelRakamlarMetni, fonListesi).getIterator();
            metinSerilimleri[4][1] = new LineBreakMeasurer (taray�c�, fonSunucu).nextLayout (Float.MAX_VALUE);

            yerelRakamlarBa�l�klar� = new String[] {
                "Latince - t�m rakamlar Latince (ASCII)",
                "Arap�a - t�m rakamlar Arap�a",
                "Arap�a Metin (Varsay�l� Arap�a) - sadece �ndeki ve Arap�a metin sonras� rakamlar Arap�a",
                "Arap�a Metin (Varsay�l� Latince) - sadece Arap�a metin sonras� rakam Arap�a",
                "T�m Metin (Varsay�l� Latince) - �ndeki rakamlar Latince (ASCII), di�erleri metne uygun"
            }; // yerelRakamlarBa�l�klar�[] dizisi sonu...
        } // �ekilleyenPanel(..) kurucusu sonu...

        public void paint (Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            float x = 5;
            float y = 5;

            for (int i = 0; i < metinSerilimleri.length; ++i) {
                y += 18;
                g2d.drawString (yerelRakamlarBa�l�klar�[i], x, y);
                y += 4;

                for (int j = 0; j < 2; ++j) {// 2.nin 1.den fark�n� g�remedim!..
                    y += metinSerilimleri[i][j].getAscent();
                    metinSerilimleri[i][j].draw (g2d, x, y);
                    y += metinSerilimleri[i][j].getDescent() + metinSerilimleri[i][j].getLeading();
                } // for-j d�ng�s� sonu...
            } // for-i d�ng�s� sonu..
        } // paint(..) haz�r metodu sonu...
    } // �ekilleyenPanel s�n�f� sonu...

    public static void main (String arg�man[]) {
        String fonAd� = arg�man.length > 0? arg�man[0] : varsay�l�Fon;

        J8e_8 �ekilliRakamlar = new J8e_8 (fonAd�); // Kurucuyu �a��r�r...
        �ekilliRakamlar.ilkDe�erler();

        Frame �er�eve = new Frame ("�ekilli Rakamlar");
        �er�eve.addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent olay) {System.exit (0);}
        }); // �er.. ifadesi sonu...

        �er�eve.add ("Center", �ekilliRakamlar);
        �er�eve.setSize (600, 380);
        �er�eve.setLocation (500, 100);
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J8e_8 s�n�f� sonu...

/* ��kt�:
**  >java J8e_8  **
Verilen fon: Lucida Sans, Uygulanan fon: Lucida Sans Regular
Verilen fon: Lucida Sans, Uygulanan fon: Lucida Sans Regular

**  >java J8e_8 "times new roman"  ** TEKRAR
Verilen fon: times new roman, Uygulanan fon: Times New Roman
Verilen fon: times new roman, Uygulanan fon: Times New Roman
*/