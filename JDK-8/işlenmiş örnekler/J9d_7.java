// J9d_7.java: SelectionSample (Se�meNumunesi) �rne�i.

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
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import java.util.Hashtable;

public class J9d_7 extends JPanel {
    private static JFrame �er�eve;
    private static String g�steriDizgesi;
    private static final FontRenderContext FON_SUNUCU = new FontRenderContext (null, false, false);
    private static final Hashtable<TextAttribute, Object> htListesi = new Hashtable<TextAttribute, Object>();
    static {
        htListesi.put (TextAttribute.FAMILY, "Serif");
        htListesi.put (TextAttribute.SIZE, new Float (24.0));
    } // sta.. blo�u sonu...
    private static final Color VURGULU_�MLE� = Color.red;
    private static final Color ZAYIF_�MLE� = Color.black;
    private static final Color SE��LEN�N_RENG� = Color.yellow;
    private static final Color YAZI_RENG� = Color.blue;
    private TextLayout metinSerilimi;
    private int �apaBa��;
    private int akt�elSon;

    public J9d_7() {// Kurucu...
        AttributedString �zellikliDizge = new AttributedString (g�steriDizgesi, htListesi);
        AttributedCharacterIterator �zellikliTaray�c� = �zellikliDizge.getIterator();
        metinSerilimi = new TextLayout (�zellikliTaray�c�, FON_SUNUCU);
        �apaBa�� = 0;
        akt�elSon = 0;
        addMouseListener (new Fare�mle�Konumu()); // Fare imle� konumuna duyarl�...
        addMouseMotionListener (new FareS�r�kleKonumlar�()); // Fare s�r�kle se�imine duyarl�...
    } // J9d_7() kurucusu sonu...

    private class Fare�mle�Konumu extends MouseAdapter {
        public void mousePressed (MouseEvent olay) {
            Point2D dizgeBa�� = serilimBa��n�Hesapla();
            float t�klaX = (float)(olay.getX() - dizgeBa��.getX());
            float t�klaY = (float)(olay.getY() - dizgeBa��.getY());
            TextHitInfo konum = metinSerilimi.hitTestChar(t�klaX, t�klaY);
            �apaBa�� = konum.getInsertionIndex();
            akt�elSon = �apaBa��;
            �er�eve.repaint();
        } // mousePressed(..) haz�r metodu sonu...
    } // Fare�mle�Konumu s�n�f� sonu...

    private Point2D serilimBa��n�Hesapla() {
        Dimension ebat = getSize();
        Point2D.Float dizgeBa�� = new Point2D.Float();
        dizgeBa��.x = (ebat.width - metinSerilimi.getAdvance()) / 2; // (bile�keEni - dizgeUzunlu�u) / 2
        dizgeBa��.y = (ebat.height - metinSerilimi.getDescent() + metinSerilimi.getAscent()) / 2; // (bile�keBoyu - (dizge�st�-dizgeAlt�)) / 2
        return dizgeBa��;
    } // serilimBa��n�Hesapla() metodu sonu...

    private class FareS�r�kleKonumlar� extends MouseMotionAdapter {
        public void mouseDragged (MouseEvent olay) {
            Point2D dizgeBa�� = serilimBa��n�Hesapla();
            float t�klaX = (float)(olay.getX() - dizgeBa��.getX());
            float t�klaY = (float)(olay.getY() - dizgeBa��.getY());
            TextHitInfo konum = metinSerilimi.hitTestChar (t�klaX, t�klaY);
            int yeniAkt�elSon = konum.getInsertionIndex();
            if (akt�elSon != yeniAkt�elSon) {
                akt�elSon = yeniAkt�elSon;
                �er�eve.repaint();
            } // if karar� sonu...
        } // mouseDragged(..) haz�r metodu sonu...
  } // FareS�r�kleKonumlar� s�n�f� sonu...

    // Dizgeyle beraber varsa se�ili alan�, yoksa imle�leri de �izer...
    public void paint (Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        Point2D dizgeBa�� = serilimBa��n�Hesapla();

        g2D.translate (dizgeBa��.getX(), dizgeBa��.getY());
        boolean imle�Mi = �apaBa�� == akt�elSon;
        if (!imle�Mi) {// Se�ili alan varsa...
            Shape se�iliAlan = metinSerilimi.getLogicalHighlightShape (�apaBa��, akt�elSon);
            g2D.setColor (SE��LEN�N_RENG�);
            g2D.fill (se�iliAlan);
        } // if karar� sonu...

        g2D.setColor (YAZI_RENG�);
        metinSerilimi.draw (g2D, 0, 0); // Dizgemizi yazal�m...

        if (imle�Mi) {// Se�ili alan yok, imle�(ler) �izilecek...
            Shape[] imle�ler = metinSerilimi.getCaretShapes (�apaBa��);
            g2D.setColor (VURGULU_�MLE�);
            g2D.draw (imle�ler[0]);

            if (imle�ler[1] != null) {// Zay�f imle� de varsa (hangi �artlarda olu�uyor, belirtilmiyor!)...
                g2D.setColor (ZAYIF_�MLE�);
                g2D.draw (imle�ler[1]);
            } // if karar� sonu...
        } // if karar� sonu...
    } // paint(..) haz�r metodu sonu...

    private static void yaratVeG�ster() {
        J9d_7 g�steri = new J9d_7(); // Kurucuyu �a��r�r...
        �er�eve = new JFrame ("Se�me Numunesi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (g�steri);
        �er�eve.setBackground (Color.cyan);
        �er�eve.setLocation (500, 100);
        �er�eve.pack();
        �er�eve.setSize (new Dimension (400, 250));
        �er�eve.setVisible (true);
    } // yaratVeG�ster() metodu sonu...

    public static void main (String[] args) {
        g�steriDizgesi = args.length > 0? args[0] : "\u05D0\u05E0\u05D9 Hello \u05DC\u05D0 \u05DE\u05D1\u05D9\u05DF "
                + "\u05E2\u05D1\u05E8\u05D9\u05EA Arabic \u0644\u0645\u062C\u0645\u0648\u0639\u0629";
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {UIManager.put ("swing.boldMetal", Boolean.FALSE); yaratVeG�ster();}});
    } // main(..) metodu sonu...
} // J9d_7 s�n�f� sonu...