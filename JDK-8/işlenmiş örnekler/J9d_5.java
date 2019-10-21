// J9d_5.java: HitTestSample (VuruþDenemeNumunesi) örneði.

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
    private static JFrame çerçeve;
    private static String dizgemiz;
    private TextLayout metinSerilimi;
    private int vuruþEndeksi; // Dizgedeki aktüel fare týklama endeksi...
    private static final Color KUVVETLÝ_ÝMLEÇ = Color.red;
    private static final Color ZAYIF_ÝMLEÇ = Color.blue;
    private static final FontRenderContext FON_SUNUCU =
            new FontRenderContext (null, false, false);
    private static final Hashtable<TextAttribute, Object> kýymalýTabloListesi =
            new Hashtable<TextAttribute, Object>();
    static {
        kýymalýTabloListesi.put (TextAttribute.FAMILY, "Serif");
        kýymalýTabloListesi.put (TextAttribute.SIZE, new Float (24.0));
    } // static blok sonu...

    public J9d_5() {// Kurucu...
        AttributedString özelliklendirilenDizge = new AttributedString (dizgemiz, kýymalýTabloListesi);
        AttributedCharacterIterator karakterTarayýcý = özelliklendirilenDizge.getIterator();
        metinSerilimi = new TextLayout (karakterTarayýcý, FON_SUNUCU);
        vuruþEndeksi = 0;
        addMouseListener (new FareyleVuruþDinleyicim()); // Dizge konumuna fare týklama duyarlýlýðý...
    } // J9d_5() kurucusu sonu...

    // Özelliklendirilen dizgemizi içeren metin serilimi ve varsa aktüel vuruþ imleçleri, bileþkeyi kurar...
    public void paint (Graphics g) {
        Graphics2D g2B = (Graphics2D)g;
        Point2D serilimKonumu = serilimKonumunuHesapla();
        g2B.translate (serilimKonumu.getX(), serilimKonumu.getY());
        metinSerilimi.draw (g2B, 0, 0);
        Shape[] imleçler = metinSerilimi.getCaretShapes (vuruþEndeksi);
        // Fare týklamasýnda kuvvetli imleçler[0] daima, zayýf imleçler[1] mevcutsa çizilir...
        g2B.setColor (KUVVETLÝ_ÝMLEÇ);
        g2B.draw (imleçler[0]);
        if (imleçler[1] != null) {g2B.setColor (ZAYIF_ÝMLEÇ); g2B.draw (imleçler[1]);}
    } // paint(..) hazýr metodu sonu...

    private Point2D serilimKonumunuHesapla() {
        Dimension ebat = getSize();
        Point2D.Float serilimKonumu = new Point2D.Float();
        serilimKonumu.x = (ebat.width - metinSerilimi.getAdvance()) / 2;
        serilimKonumu.y = (ebat.height - metinSerilimi.getDescent() + metinSerilimi.getAscent()) / 2;
        return serilimKonumu;
    } // serilimKonumunuHesapla() metodu sonu...

    private class FareyleVuruþDinleyicim extends MouseAdapter {
        public void mouseClicked (MouseEvent olay) {
            Point2D serilimKonumu = serilimKonumunuHesapla();
            // Dizgedeki fare týklama vuruþu konumunu hesapla...
            float týklaX = (float) (olay.getX() - serilimKonumu.getX());
            float týklaY = (float) (olay.getY() - serilimKonumu.getY());
            // Dizgedeki karakter konumunu hesapla...
            TextHitInfo aktüelVuruþ = metinSerilimi.hitTestChar (týklaX, týklaY);
            vuruþEndeksi = aktüelVuruþ.getInsertionIndex();
            // Vuruþ konumlu kuvvetli/zayýf imleç görünümü için paint'i güncelleyelim...
            çerçeve.repaint();
        } // mouseClicked(..) hazýr metodu sonu...
    } // FareyleVuruþDinleyicim sýnýfý sonu...

    private static void yaratVeGösterGUI() {
        J9d_5 gösteri = new J9d_5(); // Kurucuyu çaðýrýr...
        çerçeve = new JFrame ("Vuruþ Deneme Numunesi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (gösteri);
        çerçeve.setBackground (Color.yellow);
        çerçeve.setLocation (450, 100);
        çerçeve.pack();
        çerçeve.setSize (new Dimension (400, 250));
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        dizgemiz = args.length > 0? args[0] : "\u05D0\u05E0\u05D9 Hello \u05DC\u05D0 \u05DE\u05D1\u05D9\u05DF "
                + "\u05E2\u05D1\u05E8\u05D9\u05EA Arabic \u0644\u0645\u062C\u0645\u0648\u0639\u0629";
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {UIManager.put ("swing.boldMetal", Boolean.FALSE); yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J9d_5 sýnýfý sonu...