// J9d_7.java: SelectionSample (SeçmeNumunesi) örneði.

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
    private static JFrame çerçeve;
    private static String gösteriDizgesi;
    private static final FontRenderContext FON_SUNUCU = new FontRenderContext (null, false, false);
    private static final Hashtable<TextAttribute, Object> htListesi = new Hashtable<TextAttribute, Object>();
    static {
        htListesi.put (TextAttribute.FAMILY, "Serif");
        htListesi.put (TextAttribute.SIZE, new Float (24.0));
    } // sta.. bloðu sonu...
    private static final Color VURGULU_ÝMLEÇ = Color.red;
    private static final Color ZAYIF_ÝMLEÇ = Color.black;
    private static final Color SEÇÝLENÝN_RENGÝ = Color.yellow;
    private static final Color YAZI_RENGÝ = Color.blue;
    private TextLayout metinSerilimi;
    private int çapaBaþý;
    private int aktüelSon;

    public J9d_7() {// Kurucu...
        AttributedString özellikliDizge = new AttributedString (gösteriDizgesi, htListesi);
        AttributedCharacterIterator özellikliTarayýcý = özellikliDizge.getIterator();
        metinSerilimi = new TextLayout (özellikliTarayýcý, FON_SUNUCU);
        çapaBaþý = 0;
        aktüelSon = 0;
        addMouseListener (new FareÝmleçKonumu()); // Fare imleç konumuna duyarlý...
        addMouseMotionListener (new FareSürükleKonumlarý()); // Fare sürükle seçimine duyarlý...
    } // J9d_7() kurucusu sonu...

    private class FareÝmleçKonumu extends MouseAdapter {
        public void mousePressed (MouseEvent olay) {
            Point2D dizgeBaþý = serilimBaþýnýHesapla();
            float týklaX = (float)(olay.getX() - dizgeBaþý.getX());
            float týklaY = (float)(olay.getY() - dizgeBaþý.getY());
            TextHitInfo konum = metinSerilimi.hitTestChar(týklaX, týklaY);
            çapaBaþý = konum.getInsertionIndex();
            aktüelSon = çapaBaþý;
            çerçeve.repaint();
        } // mousePressed(..) hazýr metodu sonu...
    } // FareÝmleçKonumu sýnýfý sonu...

    private Point2D serilimBaþýnýHesapla() {
        Dimension ebat = getSize();
        Point2D.Float dizgeBaþý = new Point2D.Float();
        dizgeBaþý.x = (ebat.width - metinSerilimi.getAdvance()) / 2; // (bileþkeEni - dizgeUzunluðu) / 2
        dizgeBaþý.y = (ebat.height - metinSerilimi.getDescent() + metinSerilimi.getAscent()) / 2; // (bileþkeBoyu - (dizgeÜstü-dizgeAltý)) / 2
        return dizgeBaþý;
    } // serilimBaþýnýHesapla() metodu sonu...

    private class FareSürükleKonumlarý extends MouseMotionAdapter {
        public void mouseDragged (MouseEvent olay) {
            Point2D dizgeBaþý = serilimBaþýnýHesapla();
            float týklaX = (float)(olay.getX() - dizgeBaþý.getX());
            float týklaY = (float)(olay.getY() - dizgeBaþý.getY());
            TextHitInfo konum = metinSerilimi.hitTestChar (týklaX, týklaY);
            int yeniAktüelSon = konum.getInsertionIndex();
            if (aktüelSon != yeniAktüelSon) {
                aktüelSon = yeniAktüelSon;
                çerçeve.repaint();
            } // if kararý sonu...
        } // mouseDragged(..) hazýr metodu sonu...
  } // FareSürükleKonumlarý sýnýfý sonu...

    // Dizgeyle beraber varsa seçili alaný, yoksa imleçleri de çizer...
    public void paint (Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        Point2D dizgeBaþý = serilimBaþýnýHesapla();

        g2D.translate (dizgeBaþý.getX(), dizgeBaþý.getY());
        boolean imleçMi = çapaBaþý == aktüelSon;
        if (!imleçMi) {// Seçili alan varsa...
            Shape seçiliAlan = metinSerilimi.getLogicalHighlightShape (çapaBaþý, aktüelSon);
            g2D.setColor (SEÇÝLENÝN_RENGÝ);
            g2D.fill (seçiliAlan);
        } // if kararý sonu...

        g2D.setColor (YAZI_RENGÝ);
        metinSerilimi.draw (g2D, 0, 0); // Dizgemizi yazalým...

        if (imleçMi) {// Seçili alan yok, imleç(ler) çizilecek...
            Shape[] imleçler = metinSerilimi.getCaretShapes (çapaBaþý);
            g2D.setColor (VURGULU_ÝMLEÇ);
            g2D.draw (imleçler[0]);

            if (imleçler[1] != null) {// Zayýf imleç de varsa (hangi þartlarda oluþuyor, belirtilmiyor!)...
                g2D.setColor (ZAYIF_ÝMLEÇ);
                g2D.draw (imleçler[1]);
            } // if kararý sonu...
        } // if kararý sonu...
    } // paint(..) hazýr metodu sonu...

    private static void yaratVeGöster() {
        J9d_7 gösteri = new J9d_7(); // Kurucuyu çaðýrýr...
        çerçeve = new JFrame ("Seçme Numunesi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (gösteri);
        çerçeve.setBackground (Color.cyan);
        çerçeve.setLocation (500, 100);
        çerçeve.pack();
        çerçeve.setSize (new Dimension (400, 250));
        çerçeve.setVisible (true);
    } // yaratVeGöster() metodu sonu...

    public static void main (String[] args) {
        gösteriDizgesi = args.length > 0? args[0] : "\u05D0\u05E0\u05D9 Hello \u05DC\u05D0 \u05DE\u05D1\u05D9\u05DF "
                + "\u05E2\u05D1\u05E8\u05D9\u05EA Arabic \u0644\u0645\u062C\u0645\u0648\u0639\u0629";
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {UIManager.put ("swing.boldMetal", Boolean.FALSE); yaratVeGöster();}});
    } // main(..) metodu sonu...
} // J9d_7 sýnýfý sonu...