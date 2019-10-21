// J9g_5.java: ShapeMover (ÞekilTaþýyýcý) örneði.

import java.applet.Applet;

import java.awt.Frame;
import java.awt.Label;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.BasicStroke;
import java.awt.TexturePaint;
import java.awt.Canvas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;

public class J9g_5 extends Applet {
    static protected Label etiket;

    public static void main (String s[]) {
        Frame çerçeve = new Frame ("Þekil Taþýyýcý");
        çerçeve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        Applet aplet = new J9g_5(); // Varsayýlý kuruculu sýnýf nesnesini yaratýr...
        çerçeve.add ("Center", aplet);
        aplet.init(); // init() aplet hazýr metodunu çaðýrýr...
        çerçeve.pack();
        çerçeve.setLocation (400, 50);
        çerçeve.setSize (new Dimension (550, 250));
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...

    public void init() {
        setLayout (new BorderLayout());
        add (new Tuvalim()); // Tuvalim() kuruculu sýnýf nesnesini yaratýr...
        etiket = new Label ("Percere alaný dahilinde kutuyu sürükleyin");
        add ("South", etiket);
    } // init() hazýr aplet metodu sonu...
} // J9g_5 sýnýfý sonu...

class Tuvalim extends Canvas implements MouseListener, MouseMotionListener {
    Rectangle kutu = new Rectangle (0,0, 100,50);
    BufferedImage tamponResmi;
    Graphics2D trGrafik;
    int sonX, sonY;
    boolean ilkMi = true;
    TexturePaint dolguPolka, koyuPolka;
    Rectangle kutuAlaný;
    boolean býrakýldýMý = false;

    public Tuvalim() {// Kurucu...
        setBackground (Color.CYAN);
        addMouseMotionListener (this); // Fare sürüklemesine duyarlý...
        addMouseListener (this); // Fare týklamalarýna duyarlý...

        // Dolgu kalýbýný yaratýr...
        tamponResmi = new BufferedImage (5, 5, BufferedImage.TYPE_INT_RGB);
        trGrafik = tamponResmi.createGraphics();
        trGrafik.setColor (Color.PINK);
        trGrafik.fillRect (0,0, 7,7);
        trGrafik.setColor (Color.BLUE);
        trGrafik.fillOval(0,0, 3,3);
        Rectangle kut = new Rectangle (0,0, 5,5);
        dolguPolka = new TexturePaint (tamponResmi, kut);
        trGrafik.dispose();

        // Koyuluk kalýbýný yaratýr...
        tamponResmi = new BufferedImage (5, 5, BufferedImage.TYPE_INT_RGB);
        trGrafik = tamponResmi.createGraphics();
        trGrafik.setColor (Color.BLUE);
        trGrafik.fillRect (0,0, 7,7);
        trGrafik.setColor (Color.PINK);
        trGrafik.fillOval (0,0, 3,3);
        kut = new Rectangle (0,0, 5,5);
        koyuPolka = new TexturePaint (tamponResmi, kut);
        trGrafik.dispose();
  } // Tuvalim() kurucusu sonu...

    public void mousePressed (MouseEvent olay) {// Týklama baskýsýný yönetir...
        sonX = kutu.x - olay.getX();
        sonY = kutu.y - olay.getY();

        if (kutu.contains (olay.getX(), olay.getY())) {// Sürükleme-týklama dahilde...
            býrakýldýMý = false;
            konumuGüncelle (olay);
        }else {// Sürükleme-týklama çerçeve alanýný taþmaya çalýþýyor...
            J9g_5.etiket.setText ("Evvela imleci kutu üzerine getirin ve sonra sürükleyin.");
            býrakýldýMý = true;
        } // if-else kararý sonu...
    } // mousePressed(..) hazýr metodu sonu...

    public void mouseDragged (MouseEvent olay) {// Sürüklemeyi yönetir...
        if (!býrakýldýMý) konumuGüncelle (olay);
        else J9g_5.etiket.setText ("Evvela imleci kutu üzerine getirin ve sonra sürükleyin.");
    } // mouseDragged(..) hazýr metodu sonu...

    public void mouseReleased (MouseEvent olay) {// Týklama býrakýlmasýný yönetir...
        if (kutu.contains (olay.getX(), olay.getY())) {// Týklayýp býrakma kutu üzerindeyse...
            if (!býrakýldýMý) konumuGüncelle (olay);
        }else J9g_5.etiket.setText ("Evvela imleci kutu üzerine getirin ve sonra sürükleyin.");
    } // mouseReleased(..) hazýr metodu sonu...

    public void mouseMoved (MouseEvent olay) {} // Aldýrma, sadece soyut dinleyici arayüz esgeçmesi gereði...
    public void mouseClicked (MouseEvent olay) {}
    public void mouseExited (MouseEvent olay) {}
    public void mouseEntered (MouseEvent olay) {}

    public void konumuGüncelle (MouseEvent olay) {
        kutu.setLocation (sonX + olay.getX(), sonY + olay.getY());
        if (kutuKontrolu()) // Kutu dahildeyse, konumunu gösterelim...
            J9g_5.etiket.setText ("Kutunun aktüel konumu: ("+ (int)kutu.getX() + ", "+ (int)kutu.getY() + ")");
        else // Taþmaya çalýþýyorsa ikaz edelim...
            J9g_5.etiket.setText ("Lütfen kutuyu çerçeve dýþýna sürüklemeye uðraþmayýn.");
        repaint(); // paint(..) hazýr metodunu yineler...
    } // konumuGüncelle(..) metodu sonu...

    boolean kutuKontrolu() {
        if (kutuAlaný == null) return false; // Aslýnda sözkonusu deðil...
        if (kutuAlaný.contains (kutu.x,kutu.y, 100,50)) // Kutu pencere dahlindeyse True döndür...
            return true;

        int aktüelX = kutu.x;
        int aktüelY = kutu.y;

       if ((kutu.x + 100) > kutuAlaný.getWidth()) // Kutu pencere enini taþmamalý...
            aktüelX = (int) kutuAlaný.getWidth() - 99;
        if (kutu.x < 0) aktüelX = -1;
        if ((kutu.y + 50) > kutuAlaný.getHeight()) // Kutu pencere boyunu taþmamalý...
            aktüelY = (int) kutuAlaný.getHeight() - 49;
        if (kutu.y < 0) aktüelY = -1;

        kutu.setLocation (aktüelX, aktüelY);
        return false;
    } // kutuKontrolu() metodu sonu...

    public void paint (Graphics g) {güncelle(g);}

    public void güncelle (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Dimension ebat = getSize();
        int en = (int)ebat.getWidth();
        int boy = (int)ebat.getHeight();
        g2.setStroke (new BasicStroke (8.0f));

        if (ilkMi) {
            kutuAlaný = new Rectangle (ebat);
            kutu.setLocation (en / 2 - 50, boy / 2 - 25); // Kutuyu pencerede ortala...
            ilkMi = false;
        } // if kararý sonu...

        g2.setPaint (Color.CYAN);
        g2.fillRect (0,0, en,boy); // Önceki kutuyu zeminle boyayýp silelim...

        // Yeni konumlu kutuyu çizip dolgulayalým...
        g2.setPaint (koyuPolka);
        g2.draw (kutu);
        g2.setPaint (dolguPolka);
        g2.fill (kutu);
    } // güncelle(..) metodu sonu...
} // Tuvalim sýnýfý sonu...