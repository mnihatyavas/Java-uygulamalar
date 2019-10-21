// J9d_2.java: ArrowKeySample (OkTuþuNumunesi) örneði.

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

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import java.util.Hashtable;

public class J9d_2 extends JPanel implements KeyListener {
    private static String dizgemiz;
    private static final FontRenderContext varsayýlýFonSunucuBaðlamý = 
            new FontRenderContext (null, false, false);
    private static JFrame çerçeve;
    private static final Hashtable<TextAttribute, Object> kýymaTabloListesi = new Hashtable<TextAttribute, Object>();
    static {
        kýymaTabloListesi.put (TextAttribute.FAMILY, "Serif");
        kýymaTabloListesi.put (TextAttribute.SIZE, new Float (30.0));
    } // sta.. bloðu sonu....

    // Kuvvetli ve zayýf imleç renkleri...
    private static final Color kuvvetliÝmleçRengi = Color.red;
    private static final Color zayýfÝmleçRengi = Color.black;

    private static TextLayout metinSerilimi;
    private static int imleçEndeksi = 0;

    public J9d_2() {// Kurucu...
        AttributedString özelliklerDizgesi = new AttributedString (dizgemiz, kýymaTabloListesi);
        AttributedCharacterIterator özelliklerTarayýcýsý = özelliklerDizgesi.getIterator();
        metinSerilimi = new TextLayout (özelliklerTarayýcýsý, varsayýlýFonSunucuBaðlamý);
    } // J9d_2() kurucusu sonu...

    public void paint (Graphics g) {// Bileþke yaratýcý...
        Graphics2D graphics2D = (Graphics2D)g;
        Point2D metninBaþlangýcý = serilimBaþlangýcýnýHesapla();

        graphics2D.setColor (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Tesadüfi koyu renkler...
        graphics2D.translate (metninBaþlangýcý.getX(), metninBaþlangýcý.getY());
        metinSerilimi.draw (graphics2D, 0, 0);
        Shape[] imleçler = metinSerilimi.getCaretShapes (imleçEndeksi);
        graphics2D.setColor (kuvvetliÝmleçRengi);
        graphics2D.draw (imleçler[0]);

        if (imleçler[1] != null) {
            graphics2D.setColor (zayýfÝmleçRengi);
            graphics2D.draw (imleçler[1]);
        } // if kararý sonu...
    } // paint(..) hazýr metodu sonu...

    private Point2D serilimBaþlangýcýnýHesapla() {
        Dimension ebat = getSize();
        Point2D.Float metninBaþlangýcý = new Point2D.Float();
        metninBaþlangýcý.x = (ebat.width - metinSerilimi.getAdvance()) / 2;
        metninBaþlangýcý.y = (ebat.height - metinSerilimi.getDescent() + metinSerilimi.getAscent()) / 2;
        return metninBaþlangýcý;
    } // serilimBaþlangýcýnýHesapla() metodu sonu...

    public void keyPressed (KeyEvent olay) {
        int tuþKodu = olay.getKeyCode();
        if (tuþKodu == KeyEvent.VK_LEFT || tuþKodu == KeyEvent.VK_RIGHT)
            okTuþunuYönet (tuþKodu == KeyEvent.VK_RIGHT);
    } // keyPressed(..) hazýr metodu sonu...

    private void okTuþunuYönet (boolean saðOk) {
        TextHitInfo yeniKonum;
        if (saðOk) yeniKonum = metinSerilimi.getNextRightHit (imleçEndeksi);
        else yeniKonum = metinSerilimi.getNextLeftHit (imleçEndeksi);

        if (yeniKonum != null) {
            imleçEndeksi = yeniKonum.getInsertionIndex();
            çerçeve.repaint();
        } // if kararý sonu...
    } // okTuþunuYönet(..) metodu sonu...

    public void keyTyped (KeyEvent olay) {} // Ýþlevsiz...
    public void keyReleased (KeyEvent olay) {} // Ýþlevsiz...

    private static void yaratVeGösterGUI() {
        J9d_2 gösteri = new J9d_2(); // Kurucuyu çaðýrýr...
        çerçeve = new JFrame ("Sola/Saða Ok Tuþlarý Gösterisi");
        çerçeve.addKeyListener (gösteri); // Tuþlar dinleyiciye duyarlý...
        çerçeve.setFocusable (true);
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (gösteri);
        çerçeve.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Tesadüfi açýk renkler...
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setSize (new Dimension (500, 100));
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        // Arapça: li'mecmua(t): Cemaat için/herkese (merhaba)...
        dizgemiz = args.length > 0? args[0] :  "\u05D0\u05E0\u05D9 Hello \u05DC\u05D0 \u05DE\u05D1\u05D9\u05DF "
                + "\u05E2\u05D1\u05E8\u05D9\u05EA Arabic \u0644\u0645\u062C\u0645\u0648\u0639\u0629";
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
        }}); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J9d_2 sýnýfý sonu...