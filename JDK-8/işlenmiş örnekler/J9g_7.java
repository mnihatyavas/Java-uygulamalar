// J9g_7.java: SwingShapeMover (K�p�rt�l��ekilTa��y�c�) �rne�i.

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.BasicStroke;
import java.awt.TexturePaint;
import java.awt.BorderLayout;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class J9g_7 extends JApplet {
    static protected JLabel etiket;
    �ekilPaneli panel;

    public static void main (String s[]) {
        JFrame �er�eve = new JFrame ("K�p�rt�l� �ekil Ta��y�c�");
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JApplet aplet = new J9g_7(); // Varsay�l� kuruculu s�n�f nesnesini yarat�r...
        �er�eve.getContentPane().add ("Center", aplet);
        aplet.init(); // init() haz�r aplet metodunu �a��r�r...
        �er�eve.pack();
        �er�eve.setLocation (450, 50);
        �er�eve.setSize (new Dimension (550,250));
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...

    public void init() {
        getContentPane().setLayout (new BorderLayout());
        panel = new �ekilPaneli(); // Kuruculu s�n�f nesnesi yarat�r...
        getContentPane().add (panel);
        etiket = new JLabel ("Kutuyu alan i�inde s�r�kleyin");
        getContentPane().add ("South", etiket);
        //panel.setBackground (Color.BLUE);
    } // init() haz�r aplet metodu sonu...
} // J9g_7 s�n�f� sonu...

class �ekilPaneli extends JPanel implements MouseListener, MouseMotionListener{
        Rectangle bile�enAlan�;
        Rectangle kutu = new Rectangle (0,0, 100,50);
        BufferedImage tamponluResim;
        Graphics2D tamponluResimGrafi�i;
        TexturePaint dolguPolka, koyuPolka;
        int g�ncelX, g�ncelY;
        boolean ilkMi = true;
        boolean d��ardaM� = false;

    public �ekilPaneli() {// Kurucu...
        //setBackground (Color.BLUE);
        addMouseMotionListener (this);
        addMouseListener (this); // Panel dinleyiciye duyarl�...

        // Kutu dolgusu...
        tamponluResim = new BufferedImage (5,5, BufferedImage.TYPE_INT_RGB);
        tamponluResimGrafi�i = tamponluResim.createGraphics();
        tamponluResimGrafi�i.setColor (Color.PINK);
        tamponluResimGrafi�i.fillRect (0,0, 7,7);
        tamponluResimGrafi�i.setColor (Color.CYAN);
        tamponluResimGrafi�i.fillOval (0,0, 3,3);
        Rectangle kutucuk = new Rectangle (0,0, 5,5);
        dolguPolka = new TexturePaint (tamponluResim, kutucuk);
        tamponluResimGrafi�i.dispose();

        // Kutu �er�eve koyulu�u...
        tamponluResim = new BufferedImage (5,5, BufferedImage.TYPE_INT_RGB);
        tamponluResimGrafi�i = tamponluResim.createGraphics();
        tamponluResimGrafi�i.setColor (Color.CYAN);
        tamponluResimGrafi�i.fillRect (0,0, 7,7);
        tamponluResimGrafi�i.setColor (Color.PINK);
        tamponluResimGrafi�i.fillOval (0,0, 3,3);
        kutucuk = new Rectangle (0,0, 5,5);
        koyuPolka = new TexturePaint (tamponluResim, kutucuk);
        tamponluResimGrafi�i.dispose();
    } // �ekilPaneli() kurucusu sonu...

    public void mousePressed (MouseEvent olay) {
        g�ncelX = kutu.x - olay.getX();
        g�ncelY = kutu.y - olay.getY();
        if ( kutu.contains (olay.getX(), olay.getY()) ) // Kutu i�inde mi fareye bas�ld�?
            konumuG�ncelle (olay);
        else {// Kutu d���nday�z, ikaz et!..
            J9g_7.etiket.setText ("Evvela imleci kutu i�ine konumla ve sonra s�r�kle.");
            d��ardaM� = true;
        } // else karar� sonu...
    } // mousePressed(..) haz�r metodu sonu...

    public void mouseDragged (MouseEvent olay) {
        if (!d��ardaM�) // Kutu i�indeyiz...
            konumuG�ncelle (olay);
        else // Kutu d���nday�z, ikazla!...
            J9g_7.etiket.setText ("Evvela imleci kutu i�ine konumla ve sonra s�r�kle.");
    } // mouseDragged(..) haz�r metodu sonu...

    public void mouseReleased (MouseEvent olay) {
        if (kutu.contains (olay.getX(), olay.getY()) ) // Kutu i�indeyiz...
            konumuG�ncelle (olay);
        else {// Kutu d���nday�z, ikaz et!..
            J9g_7.etiket.setText ("Evvela imleci kutu i�ine konumla ve sonra s�r�kle.");
            d��ardaM� = false;
        } // else karar� sonu...
    } // mouseReleased(..) haz�r metodu sonu...

     public void mouseMoved (MouseEvent olay){} // Soyut fare esge�me metodlar�; ald�rma...
     public void mouseClicked (MouseEvent olay){}
     public void mouseExited (MouseEvent olay){}
     public void mouseEntered (MouseEvent olay){}

     public void konumuG�ncelle (MouseEvent olay) {
        kutu.setLocation (g�ncelX + olay.getX(), g�ncelY + olay.getY());
        if (alan��indeMi()) J9g_7.etiket.setText ("Kutunun akt�el konumu: (" + (int)kutu.getX() + ", " + (int)kutu.getY() + ")");
        else J9g_7.etiket.setText ("L�tfen kutuyu alan d���na s�r�klemeye u�ra�may�n.");
        repaint(); // paintComponent(..) tekrar �a�r�l�r...
     } // konumuG�ncelle(..) metodu sonu...

    boolean alan��indeMi() {
        if (bile�enAlan� == null) return false;
        if (bile�enAlan�.contains (kutu.x, kutu.y, 100, 50)) // Alan i�indeyiz...
            return true;

        // Alan d���na s�r�klemeye �al���yoruz!..
        int yeniX = kutu.x;
        int yeniY = kutu.y;

        if ((kutu.x+100) > bile�enAlan�.getWidth()) yeniX = (int)bile�enAlan�.getWidth() - 99;
        if (kutu.x < 0) yeniX = -1;  
        if ((kutu.y+50) > bile�enAlan�.getHeight()) yeniY = (int)bile�enAlan�.getHeight() - 49;
        if (kutu.y < 0) yeniY = -1;

        kutu.setLocation (yeniX, yeniY);
        return false;
    } // alan��indeMi() metodu sonu...

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke (new BasicStroke (8.0f));
        Dimension ebat = getSize();
        int en = (int)ebat.getWidth();
        int boy = (int)ebat.getHeight();

        if (ilkMi) {
            bile�enAlan� = new Rectangle (ebat);
            kutu.setLocation (en/2-50, boy/2-25);
            ilkMi = false;
        } // if karar� sonu...

        g2.setPaint (Color.BLUE); // Zemin rengiyle �nceki kutuyu temizler...
        g2.fillRect (0,0, en, boy);

        g2.setPaint (dolguPolka);
        g2.fill (kutu);	
        g2.setPaint (koyuPolka);
        g2.draw (kutu);
    } // paintComponent(..) haz�r metodu sonu...
} // �ekilPaneli s�n�f� sonu...