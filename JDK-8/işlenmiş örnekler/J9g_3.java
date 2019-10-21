// J9g_3.java: HitTestSample2 (VuruþDenemeNumunesi2) örneði.

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
    private static final Color KUVVETLÝ_ÝMLEÇ_RENGÝ = Color.RED;
    private static final Color ZAYIF_ÝMLEÇ_RENGÝ = Color.BLACK;
    private TextLayout metinSerilimi;
    private int vuruþEndeksi;
    private static final FontRenderContext FON_SUNUCU = new FontRenderContext (null, false, false);
    private static final Hashtable kýymalýTabloListesi = new Hashtable();
    static {kýymalýTabloListesi.put (TextAttribute.SIZE, new Float (18.0));}
    Dimension tercihiEbat = new Dimension (400, 250);
    VuruþPaneli vuruþPaneli;

    public Dimension tercihiEbatýAl() {return tercihiEbat;}

    private class FareyleVuruþDinleyicim extends MouseAdapter {
        public void mouseClicked (MouseEvent olay) {
            Point2D serilimKonumu = serilimKonumunuHesapla();
            float týklaX = (float) (olay.getX() - serilimKonumu.getX());
            float týklaY = (float) (olay.getY() - serilimKonumu.getY());
            TextHitInfo aktüelVuruþ = metinSerilimi.hitTestChar (týklaX, týklaY);
            vuruþEndeksi = aktüelVuruþ.getInsertionIndex();
            vuruþPaneli.repaint();
        } // mouseClicked(..) hazýr metodu sonu...
    } // FareyleVuruþDinleyicim sýnýfý sonu...

    private Point2D serilimKonumunuHesapla() {
        Dimension ebat = tercihiEbatýAl();
        Point2D.Float serilimKonumu = new Point2D.Float();
        serilimKonumu.x = (float)(ebat.width - metinSerilimi.getAdvance()) / 2;
        serilimKonumu.y = (float)(ebat.height - metinSerilimi.getDescent() + metinSerilimi.getAscent()) / 2;
        return serilimKonumu;
    } // serilimKonumunuHesapla() metodu sonu...

    class VuruþPaneli extends JPanel {
        public void VuruþPaneli() {} // Varsayýlý sýnýf kurucusu...

        public void paintComponent (Graphics g) {
            super.paintComponent (g);
            setBackground (Color.yellow);
            Graphics2D graphics2D = (Graphics2D)g;
            Point2D serilimKonumu = serilimKonumunuHesapla(); // Ýlgili metodu çaðýrýr...
            graphics2D.translate (serilimKonumu.getX(), serilimKonumu.getY());
            metinSerilimi.draw (graphics2D, 0, 0);
            Shape[] imleçler = metinSerilimi.getCaretShapes (vuruþEndeksi);
            graphics2D.setColor (KUVVETLÝ_ÝMLEÇ_RENGÝ);
            graphics2D.draw (imleçler[0]);
            if (imleçler[1] != null) {
                graphics2D.setColor (ZAYIF_ÝMLEÇ_RENGÝ);
                graphics2D.draw (imleçler[1]);
            } // if kararý sonu...
        } // paintComponent(..) hazýr metodu sonu...
    } // VuruþPaneli sýnýfý sonu...

    public void kurUI (Container kab, AttributedCharacterIterator özellikliKarakterTarayýcý) {
        metinSerilimi = new TextLayout (özellikliKarakterTarayýcý, FON_SUNUCU);
        vuruþEndeksi = 0;
        vuruþPaneli = new VuruþPaneli();
        kab.add (vuruþPaneli, BorderLayout.CENTER);
        vuruþPaneli.addMouseListener (new FareyleVuruþDinleyicim()); // Metin konumu fare týklamasýna duyarlý...
    } // kurUI(..) metodu sonu...

    public static void main (String[] args) {
        dizge = args.length > 0? args[0] : "\u05D0\u05E0\u05D9 Hello \u05DC\u05D0 \u05DE\u05D1\u05D9\u05DF "
                + "\u05E2\u05D1\u05E8\u05D9\u05EA Arabic \u0644\u0645\u062C\u0645\u0648\u0639\u0629";
        merhabaDizgesi = new AttributedString (dizge, kýymalýTabloListesi);

        JFrame çerçeve = new JFrame ("Vuruþ Deneme Numunesi 2");
        AttributedCharacterIterator karakterTarayýcý = merhabaDizgesi.getIterator();
        final J9g_3 gösteri = new J9g_3(); // Varsayýlý kuruculu sýnýf nesnesini yaratýr...
        gösteri.kurUI (çerçeve.getContentPane(), karakterTarayýcý); // kurUI() metodunu çaðýrýr...
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        çerçeve.setSize (new Dimension (400, 250));
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J9g_3 sýnýfý sonu...