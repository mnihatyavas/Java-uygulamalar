// J9d_2.java: ArrowKeySample (OkTu�uNumunesi) �rne�i.

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
    private static final FontRenderContext varsay�l�FonSunucuBa�lam� = 
            new FontRenderContext (null, false, false);
    private static JFrame �er�eve;
    private static final Hashtable<TextAttribute, Object> k�ymaTabloListesi = new Hashtable<TextAttribute, Object>();
    static {
        k�ymaTabloListesi.put (TextAttribute.FAMILY, "Serif");
        k�ymaTabloListesi.put (TextAttribute.SIZE, new Float (30.0));
    } // sta.. blo�u sonu....

    // Kuvvetli ve zay�f imle� renkleri...
    private static final Color kuvvetli�mle�Rengi = Color.red;
    private static final Color zay�f�mle�Rengi = Color.black;

    private static TextLayout metinSerilimi;
    private static int imle�Endeksi = 0;

    public J9d_2() {// Kurucu...
        AttributedString �zelliklerDizgesi = new AttributedString (dizgemiz, k�ymaTabloListesi);
        AttributedCharacterIterator �zelliklerTaray�c�s� = �zelliklerDizgesi.getIterator();
        metinSerilimi = new TextLayout (�zelliklerTaray�c�s�, varsay�l�FonSunucuBa�lam�);
    } // J9d_2() kurucusu sonu...

    public void paint (Graphics g) {// Bile�ke yarat�c�...
        Graphics2D graphics2D = (Graphics2D)g;
        Point2D metninBa�lang�c� = serilimBa�lang�c�n�Hesapla();

        graphics2D.setColor (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Tesad�fi koyu renkler...
        graphics2D.translate (metninBa�lang�c�.getX(), metninBa�lang�c�.getY());
        metinSerilimi.draw (graphics2D, 0, 0);
        Shape[] imle�ler = metinSerilimi.getCaretShapes (imle�Endeksi);
        graphics2D.setColor (kuvvetli�mle�Rengi);
        graphics2D.draw (imle�ler[0]);

        if (imle�ler[1] != null) {
            graphics2D.setColor (zay�f�mle�Rengi);
            graphics2D.draw (imle�ler[1]);
        } // if karar� sonu...
    } // paint(..) haz�r metodu sonu...

    private Point2D serilimBa�lang�c�n�Hesapla() {
        Dimension ebat = getSize();
        Point2D.Float metninBa�lang�c� = new Point2D.Float();
        metninBa�lang�c�.x = (ebat.width - metinSerilimi.getAdvance()) / 2;
        metninBa�lang�c�.y = (ebat.height - metinSerilimi.getDescent() + metinSerilimi.getAscent()) / 2;
        return metninBa�lang�c�;
    } // serilimBa�lang�c�n�Hesapla() metodu sonu...

    public void keyPressed (KeyEvent olay) {
        int tu�Kodu = olay.getKeyCode();
        if (tu�Kodu == KeyEvent.VK_LEFT || tu�Kodu == KeyEvent.VK_RIGHT)
            okTu�unuY�net (tu�Kodu == KeyEvent.VK_RIGHT);
    } // keyPressed(..) haz�r metodu sonu...

    private void okTu�unuY�net (boolean sa�Ok) {
        TextHitInfo yeniKonum;
        if (sa�Ok) yeniKonum = metinSerilimi.getNextRightHit (imle�Endeksi);
        else yeniKonum = metinSerilimi.getNextLeftHit (imle�Endeksi);

        if (yeniKonum != null) {
            imle�Endeksi = yeniKonum.getInsertionIndex();
            �er�eve.repaint();
        } // if karar� sonu...
    } // okTu�unuY�net(..) metodu sonu...

    public void keyTyped (KeyEvent olay) {} // ��levsiz...
    public void keyReleased (KeyEvent olay) {} // ��levsiz...

    private static void yaratVeG�sterGUI() {
        J9d_2 g�steri = new J9d_2(); // Kurucuyu �a��r�r...
        �er�eve = new JFrame ("Sola/Sa�a Ok Tu�lar� G�sterisi");
        �er�eve.addKeyListener (g�steri); // Tu�lar dinleyiciye duyarl�...
        �er�eve.setFocusable (true);
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (g�steri);
        �er�eve.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Tesad�fi a��k renkler...
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setSize (new Dimension (500, 100));
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        // Arap�a: li'mecmua(t): Cemaat i�in/herkese (merhaba)...
        dizgemiz = args.length > 0? args[0] :  "\u05D0\u05E0\u05D9 Hello \u05DC\u05D0 \u05DE\u05D1\u05D9\u05DF "
                + "\u05E2\u05D1\u05E8\u05D9\u05EA Arabic \u0644\u0645\u062C\u0645\u0648\u0639\u0629";
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
        }}); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J9d_2 s�n�f� sonu...