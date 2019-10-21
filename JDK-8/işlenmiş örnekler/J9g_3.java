// J9g_3.java: HitTestSample2 (Vuru�DenemeNumunesi2) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.font.TextAttribute;
import java.awt.font.FontRenderContext;
import java.awt.font.TextHitInfo;
import java.awt.font.TextLayout;

import java.awt.geom.Point2D;

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Hashtable;

// J9d_5.java=HitTestSample gibidir...
public class J9g_3 extends JApplet {
    private static String dizge;
    private static AttributedString merhabaDizgesi;
    private static final Color KUVVETL�_�MLE�_RENG� = Color.RED;
    private static final Color ZAYIF_�MLE�_RENG� = Color.BLACK;
    private TextLayout metinSerilimi;
    private int vuru�Endeksi;
    private static final FontRenderContext FON_SUNUCU = new FontRenderContext (null, false, false);
    private static final Hashtable k�ymal�TabloListesi = new Hashtable();
    static {k�ymal�TabloListesi.put (TextAttribute.SIZE, new Float (18.0));}
    Dimension tercihiEbat = new Dimension (400, 250);
    Vuru�Paneli vuru�Paneli;

    public Dimension tercihiEbat�Al() {return tercihiEbat;}

    private class FareyleVuru�Dinleyicim extends MouseAdapter {
        public void mouseClicked (MouseEvent olay) {
            Point2D serilimKonumu = serilimKonumunuHesapla();
            float t�klaX = (float) (olay.getX() - serilimKonumu.getX());
            float t�klaY = (float) (olay.getY() - serilimKonumu.getY());
            TextHitInfo akt�elVuru� = metinSerilimi.hitTestChar (t�klaX, t�klaY);
            vuru�Endeksi = akt�elVuru�.getInsertionIndex();
            vuru�Paneli.repaint();
        } // mouseClicked(..) haz�r metodu sonu...
    } // FareyleVuru�Dinleyicim s�n�f� sonu...

    private Point2D serilimKonumunuHesapla() {
        Dimension ebat = tercihiEbat�Al();
        Point2D.Float serilimKonumu = new Point2D.Float();
        serilimKonumu.x = (float)(ebat.width - metinSerilimi.getAdvance()) / 2;
        serilimKonumu.y = (float)(ebat.height - metinSerilimi.getDescent() + metinSerilimi.getAscent()) / 2;
        return serilimKonumu;
    } // serilimKonumunuHesapla() metodu sonu...

    class Vuru�Paneli extends JPanel {
        public void Vuru�Paneli() {} // Varsay�l� s�n�f kurucusu...

        public void paintComponent (Graphics g) {
            super.paintComponent (g);
            setBackground (Color.yellow);
            Graphics2D graphics2D = (Graphics2D)g;
            Point2D serilimKonumu = serilimKonumunuHesapla(); // �lgili metodu �a��r�r...
            graphics2D.translate (serilimKonumu.getX(), serilimKonumu.getY());
            metinSerilimi.draw (graphics2D, 0, 0);
            Shape[] imle�ler = metinSerilimi.getCaretShapes (vuru�Endeksi);
            graphics2D.setColor (KUVVETL�_�MLE�_RENG�);
            graphics2D.draw (imle�ler[0]);
            if (imle�ler[1] != null) {
                graphics2D.setColor (ZAYIF_�MLE�_RENG�);
                graphics2D.draw (imle�ler[1]);
            } // if karar� sonu...
        } // paintComponent(..) haz�r metodu sonu...
    } // Vuru�Paneli s�n�f� sonu...

    public void kurUI (Container kab, AttributedCharacterIterator �zellikliKarakterTaray�c�) {
        metinSerilimi = new TextLayout (�zellikliKarakterTaray�c�, FON_SUNUCU);
        vuru�Endeksi = 0;
        vuru�Paneli = new Vuru�Paneli();
        kab.add (vuru�Paneli, BorderLayout.CENTER);
        vuru�Paneli.addMouseListener (new FareyleVuru�Dinleyicim()); // Metin konumu fare t�klamas�na duyarl�...
    } // kurUI(..) metodu sonu...

    public static void main (String[] args) {
        dizge = args.length > 0? args[0] : "\u05D0\u05E0\u05D9 Hello \u05DC\u05D0 \u05DE\u05D1\u05D9\u05DF "
                + "\u05E2\u05D1\u05E8\u05D9\u05EA Arabic \u0644\u0645\u062C\u0645\u0648\u0639\u0629";
        merhabaDizgesi = new AttributedString (dizge, k�ymal�TabloListesi);

        JFrame �er�eve = new JFrame ("Vuru� Deneme Numunesi 2");
        AttributedCharacterIterator karakterTaray�c� = merhabaDizgesi.getIterator();
        final J9g_3 g�steri = new J9g_3(); // Varsay�l� kuruculu s�n�f nesnesini yarat�r...
        g�steri.kurUI (�er�eve.getContentPane(), karakterTaray�c�); // kurUI() metodunu �a��r�r...
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        �er�eve.setSize (new Dimension (400, 250));
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J9g_3 s�n�f� sonu...