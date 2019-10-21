// J9g_5.java: ShapeMover (�ekilTa��y�c�) �rne�i.

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
        Frame �er�eve = new Frame ("�ekil Ta��y�c�");
        �er�eve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        Applet aplet = new J9g_5(); // Varsay�l� kuruculu s�n�f nesnesini yarat�r...
        �er�eve.add ("Center", aplet);
        aplet.init(); // init() aplet haz�r metodunu �a��r�r...
        �er�eve.pack();
        �er�eve.setLocation (400, 50);
        �er�eve.setSize (new Dimension (550, 250));
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...

    public void init() {
        setLayout (new BorderLayout());
        add (new Tuvalim()); // Tuvalim() kuruculu s�n�f nesnesini yarat�r...
        etiket = new Label ("Percere alan� dahilinde kutuyu s�r�kleyin");
        add ("South", etiket);
    } // init() haz�r aplet metodu sonu...
} // J9g_5 s�n�f� sonu...

class Tuvalim extends Canvas implements MouseListener, MouseMotionListener {
    Rectangle kutu = new Rectangle (0,0, 100,50);
    BufferedImage tamponResmi;
    Graphics2D trGrafik;
    int sonX, sonY;
    boolean ilkMi = true;
    TexturePaint dolguPolka, koyuPolka;
    Rectangle kutuAlan�;
    boolean b�rak�ld�M� = false;

    public Tuvalim() {// Kurucu...
        setBackground (Color.CYAN);
        addMouseMotionListener (this); // Fare s�r�klemesine duyarl�...
        addMouseListener (this); // Fare t�klamalar�na duyarl�...

        // Dolgu kal�b�n� yarat�r...
        tamponResmi = new BufferedImage (5, 5, BufferedImage.TYPE_INT_RGB);
        trGrafik = tamponResmi.createGraphics();
        trGrafik.setColor (Color.PINK);
        trGrafik.fillRect (0,0, 7,7);
        trGrafik.setColor (Color.BLUE);
        trGrafik.fillOval(0,0, 3,3);
        Rectangle kut = new Rectangle (0,0, 5,5);
        dolguPolka = new TexturePaint (tamponResmi, kut);
        trGrafik.dispose();

        // Koyuluk kal�b�n� yarat�r...
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

    public void mousePressed (MouseEvent olay) {// T�klama bask�s�n� y�netir...
        sonX = kutu.x - olay.getX();
        sonY = kutu.y - olay.getY();

        if (kutu.contains (olay.getX(), olay.getY())) {// S�r�kleme-t�klama dahilde...
            b�rak�ld�M� = false;
            konumuG�ncelle (olay);
        }else {// S�r�kleme-t�klama �er�eve alan�n� ta�maya �al���yor...
            J9g_5.etiket.setText ("Evvela imleci kutu �zerine getirin ve sonra s�r�kleyin.");
            b�rak�ld�M� = true;
        } // if-else karar� sonu...
    } // mousePressed(..) haz�r metodu sonu...

    public void mouseDragged (MouseEvent olay) {// S�r�klemeyi y�netir...
        if (!b�rak�ld�M�) konumuG�ncelle (olay);
        else J9g_5.etiket.setText ("Evvela imleci kutu �zerine getirin ve sonra s�r�kleyin.");
    } // mouseDragged(..) haz�r metodu sonu...

    public void mouseReleased (MouseEvent olay) {// T�klama b�rak�lmas�n� y�netir...
        if (kutu.contains (olay.getX(), olay.getY())) {// T�klay�p b�rakma kutu �zerindeyse...
            if (!b�rak�ld�M�) konumuG�ncelle (olay);
        }else J9g_5.etiket.setText ("Evvela imleci kutu �zerine getirin ve sonra s�r�kleyin.");
    } // mouseReleased(..) haz�r metodu sonu...

    public void mouseMoved (MouseEvent olay) {} // Ald�rma, sadece soyut dinleyici aray�z esge�mesi gere�i...
    public void mouseClicked (MouseEvent olay) {}
    public void mouseExited (MouseEvent olay) {}
    public void mouseEntered (MouseEvent olay) {}

    public void konumuG�ncelle (MouseEvent olay) {
        kutu.setLocation (sonX + olay.getX(), sonY + olay.getY());
        if (kutuKontrolu()) // Kutu dahildeyse, konumunu g�sterelim...
            J9g_5.etiket.setText ("Kutunun akt�el konumu: ("+ (int)kutu.getX() + ", "+ (int)kutu.getY() + ")");
        else // Ta�maya �al���yorsa ikaz edelim...
            J9g_5.etiket.setText ("L�tfen kutuyu �er�eve d���na s�r�klemeye u�ra�may�n.");
        repaint(); // paint(..) haz�r metodunu yineler...
    } // konumuG�ncelle(..) metodu sonu...

    boolean kutuKontrolu() {
        if (kutuAlan� == null) return false; // Asl�nda s�zkonusu de�il...
        if (kutuAlan�.contains (kutu.x,kutu.y, 100,50)) // Kutu pencere dahlindeyse True d�nd�r...
            return true;

        int akt�elX = kutu.x;
        int akt�elY = kutu.y;

       if ((kutu.x + 100) > kutuAlan�.getWidth()) // Kutu pencere enini ta�mamal�...
            akt�elX = (int) kutuAlan�.getWidth() - 99;
        if (kutu.x < 0) akt�elX = -1;
        if ((kutu.y + 50) > kutuAlan�.getHeight()) // Kutu pencere boyunu ta�mamal�...
            akt�elY = (int) kutuAlan�.getHeight() - 49;
        if (kutu.y < 0) akt�elY = -1;

        kutu.setLocation (akt�elX, akt�elY);
        return false;
    } // kutuKontrolu() metodu sonu...

    public void paint (Graphics g) {g�ncelle(g);}

    public void g�ncelle (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Dimension ebat = getSize();
        int en = (int)ebat.getWidth();
        int boy = (int)ebat.getHeight();
        g2.setStroke (new BasicStroke (8.0f));

        if (ilkMi) {
            kutuAlan� = new Rectangle (ebat);
            kutu.setLocation (en / 2 - 50, boy / 2 - 25); // Kutuyu pencerede ortala...
            ilkMi = false;
        } // if karar� sonu...

        g2.setPaint (Color.CYAN);
        g2.fillRect (0,0, en,boy); // �nceki kutuyu zeminle boyay�p silelim...

        // Yeni konumlu kutuyu �izip dolgulayal�m...
        g2.setPaint (koyuPolka);
        g2.draw (kutu);
        g2.setPaint (dolguPolka);
        g2.fill (kutu);
    } // g�ncelle(..) metodu sonu...
} // Tuvalim s�n�f� sonu...