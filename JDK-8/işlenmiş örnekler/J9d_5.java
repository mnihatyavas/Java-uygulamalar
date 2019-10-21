// J9d_5.java: HitTestSample (Vuru�DenemeNumunesi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextHitInfo;
import java.awt.font.TextLayout;

import java.awt.geom.Point2D;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.util.Hashtable;

public class J9d_5 extends JPanel {
    private static JFrame �er�eve;
    private static String dizgemiz;
    private TextLayout metinSerilimi;
    private int vuru�Endeksi; // Dizgedeki akt�el fare t�klama endeksi...
    private static final Color KUVVETL�_�MLE� = Color.red;
    private static final Color ZAYIF_�MLE� = Color.blue;
    private static final FontRenderContext FON_SUNUCU =
            new FontRenderContext (null, false, false);
    private static final Hashtable<TextAttribute, Object> k�ymal�TabloListesi =
            new Hashtable<TextAttribute, Object>();
    static {
        k�ymal�TabloListesi.put (TextAttribute.FAMILY, "Serif");
        k�ymal�TabloListesi.put (TextAttribute.SIZE, new Float (24.0));
    } // static blok sonu...

    public J9d_5() {// Kurucu...
        AttributedString �zelliklendirilenDizge = new AttributedString (dizgemiz, k�ymal�TabloListesi);
        AttributedCharacterIterator karakterTaray�c� = �zelliklendirilenDizge.getIterator();
        metinSerilimi = new TextLayout (karakterTaray�c�, FON_SUNUCU);
        vuru�Endeksi = 0;
        addMouseListener (new FareyleVuru�Dinleyicim()); // Dizge konumuna fare t�klama duyarl�l���...
    } // J9d_5() kurucusu sonu...

    // �zelliklendirilen dizgemizi i�eren metin serilimi ve varsa akt�el vuru� imle�leri, bile�keyi kurar...
    public void paint (Graphics g) {
        Graphics2D g2B = (Graphics2D)g;
        Point2D serilimKonumu = serilimKonumunuHesapla();
        g2B.translate (serilimKonumu.getX(), serilimKonumu.getY());
        metinSerilimi.draw (g2B, 0, 0);
        Shape[] imle�ler = metinSerilimi.getCaretShapes (vuru�Endeksi);
        // Fare t�klamas�nda kuvvetli imle�ler[0] daima, zay�f imle�ler[1] mevcutsa �izilir...
        g2B.setColor (KUVVETL�_�MLE�);
        g2B.draw (imle�ler[0]);
        if (imle�ler[1] != null) {g2B.setColor (ZAYIF_�MLE�); g2B.draw (imle�ler[1]);}
    } // paint(..) haz�r metodu sonu...

    private Point2D serilimKonumunuHesapla() {
        Dimension ebat = getSize();
        Point2D.Float serilimKonumu = new Point2D.Float();
        serilimKonumu.x = (ebat.width - metinSerilimi.getAdvance()) / 2;
        serilimKonumu.y = (ebat.height - metinSerilimi.getDescent() + metinSerilimi.getAscent()) / 2;
        return serilimKonumu;
    } // serilimKonumunuHesapla() metodu sonu...

    private class FareyleVuru�Dinleyicim extends MouseAdapter {
        public void mouseClicked (MouseEvent olay) {
            Point2D serilimKonumu = serilimKonumunuHesapla();
            // Dizgedeki fare t�klama vuru�u konumunu hesapla...
            float t�klaX = (float) (olay.getX() - serilimKonumu.getX());
            float t�klaY = (float) (olay.getY() - serilimKonumu.getY());
            // Dizgedeki karakter konumunu hesapla...
            TextHitInfo akt�elVuru� = metinSerilimi.hitTestChar (t�klaX, t�klaY);
            vuru�Endeksi = akt�elVuru�.getInsertionIndex();
            // Vuru� konumlu kuvvetli/zay�f imle� g�r�n�m� i�in paint'i g�ncelleyelim...
            �er�eve.repaint();
        } // mouseClicked(..) haz�r metodu sonu...
    } // FareyleVuru�Dinleyicim s�n�f� sonu...

    private static void yaratVeG�sterGUI() {
        J9d_5 g�steri = new J9d_5(); // Kurucuyu �a��r�r...
        �er�eve = new JFrame ("Vuru� Deneme Numunesi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (g�steri);
        �er�eve.setBackground (Color.yellow);
        �er�eve.setLocation (450, 100);
        �er�eve.pack();
        �er�eve.setSize (new Dimension (400, 250));
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        dizgemiz = args.length > 0? args[0] : "\u05D0\u05E0\u05D9 Hello \u05DC\u05D0 \u05DE\u05D1\u05D9\u05DF "
                + "\u05E2\u05D1\u05E8\u05D9\u05EA Arabic \u0644\u0645\u062C\u0645\u0648\u0639\u0629";
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {UIManager.put ("swing.boldMetal", Boolean.FALSE); yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J9d_5 s�n�f� sonu...