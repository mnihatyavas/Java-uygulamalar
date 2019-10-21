// J9g_7.java: SwingShapeMover (KýpýrtýlýÞekilTaþýyýcý) örneði.

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
    ÞekilPaneli panel;

    public static void main (String s[]) {
        JFrame çerçeve = new JFrame ("Kýpýrtýlý Þekil Taþýyýcý");
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JApplet aplet = new J9g_7(); // Varsayýlý kuruculu sýnýf nesnesini yaratýr...
        çerçeve.getContentPane().add ("Center", aplet);
        aplet.init(); // init() hazýr aplet metodunu çaðýrýr...
        çerçeve.pack();
        çerçeve.setLocation (450, 50);
        çerçeve.setSize (new Dimension (550,250));
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...

    public void init() {
        getContentPane().setLayout (new BorderLayout());
        panel = new ÞekilPaneli(); // Kuruculu sýnýf nesnesi yaratýr...
        getContentPane().add (panel);
        etiket = new JLabel ("Kutuyu alan içinde sürükleyin");
        getContentPane().add ("South", etiket);
        //panel.setBackground (Color.BLUE);
    } // init() hazýr aplet metodu sonu...
} // J9g_7 sýnýfý sonu...

class ÞekilPaneli extends JPanel implements MouseListener, MouseMotionListener{
        Rectangle bileþenAlaný;
        Rectangle kutu = new Rectangle (0,0, 100,50);
        BufferedImage tamponluResim;
        Graphics2D tamponluResimGrafiði;
        TexturePaint dolguPolka, koyuPolka;
        int güncelX, güncelY;
        boolean ilkMi = true;
        boolean dýþardaMý = false;

    public ÞekilPaneli() {// Kurucu...
        //setBackground (Color.BLUE);
        addMouseMotionListener (this);
        addMouseListener (this); // Panel dinleyiciye duyarlý...

        // Kutu dolgusu...
        tamponluResim = new BufferedImage (5,5, BufferedImage.TYPE_INT_RGB);
        tamponluResimGrafiði = tamponluResim.createGraphics();
        tamponluResimGrafiði.setColor (Color.PINK);
        tamponluResimGrafiði.fillRect (0,0, 7,7);
        tamponluResimGrafiði.setColor (Color.CYAN);
        tamponluResimGrafiði.fillOval (0,0, 3,3);
        Rectangle kutucuk = new Rectangle (0,0, 5,5);
        dolguPolka = new TexturePaint (tamponluResim, kutucuk);
        tamponluResimGrafiði.dispose();

        // Kutu çerçeve koyuluðu...
        tamponluResim = new BufferedImage (5,5, BufferedImage.TYPE_INT_RGB);
        tamponluResimGrafiði = tamponluResim.createGraphics();
        tamponluResimGrafiði.setColor (Color.CYAN);
        tamponluResimGrafiði.fillRect (0,0, 7,7);
        tamponluResimGrafiði.setColor (Color.PINK);
        tamponluResimGrafiði.fillOval (0,0, 3,3);
        kutucuk = new Rectangle (0,0, 5,5);
        koyuPolka = new TexturePaint (tamponluResim, kutucuk);
        tamponluResimGrafiði.dispose();
    } // ÞekilPaneli() kurucusu sonu...

    public void mousePressed (MouseEvent olay) {
        güncelX = kutu.x - olay.getX();
        güncelY = kutu.y - olay.getY();
        if ( kutu.contains (olay.getX(), olay.getY()) ) // Kutu içinde mi fareye basýldý?
            konumuGüncelle (olay);
        else {// Kutu dýþýndayýz, ikaz et!..
            J9g_7.etiket.setText ("Evvela imleci kutu içine konumla ve sonra sürükle.");
            dýþardaMý = true;
        } // else kararý sonu...
    } // mousePressed(..) hazýr metodu sonu...

    public void mouseDragged (MouseEvent olay) {
        if (!dýþardaMý) // Kutu içindeyiz...
            konumuGüncelle (olay);
        else // Kutu dýþýndayýz, ikazla!...
            J9g_7.etiket.setText ("Evvela imleci kutu içine konumla ve sonra sürükle.");
    } // mouseDragged(..) hazýr metodu sonu...

    public void mouseReleased (MouseEvent olay) {
        if (kutu.contains (olay.getX(), olay.getY()) ) // Kutu içindeyiz...
            konumuGüncelle (olay);
        else {// Kutu dýþýndayýz, ikaz et!..
            J9g_7.etiket.setText ("Evvela imleci kutu içine konumla ve sonra sürükle.");
            dýþardaMý = false;
        } // else kararý sonu...
    } // mouseReleased(..) hazýr metodu sonu...

     public void mouseMoved (MouseEvent olay){} // Soyut fare esgeçme metodlarý; aldýrma...
     public void mouseClicked (MouseEvent olay){}
     public void mouseExited (MouseEvent olay){}
     public void mouseEntered (MouseEvent olay){}

     public void konumuGüncelle (MouseEvent olay) {
        kutu.setLocation (güncelX + olay.getX(), güncelY + olay.getY());
        if (alanÝçindeMi()) J9g_7.etiket.setText ("Kutunun aktüel konumu: (" + (int)kutu.getX() + ", " + (int)kutu.getY() + ")");
        else J9g_7.etiket.setText ("Lütfen kutuyu alan dýþýna sürüklemeye uðraþmayýn.");
        repaint(); // paintComponent(..) tekrar çaðrýlýr...
     } // konumuGüncelle(..) metodu sonu...

    boolean alanÝçindeMi() {
        if (bileþenAlaný == null) return false;
        if (bileþenAlaný.contains (kutu.x, kutu.y, 100, 50)) // Alan içindeyiz...
            return true;

        // Alan dýþýna sürüklemeye çalýþýyoruz!..
        int yeniX = kutu.x;
        int yeniY = kutu.y;

        if ((kutu.x+100) > bileþenAlaný.getWidth()) yeniX = (int)bileþenAlaný.getWidth() - 99;
        if (kutu.x < 0) yeniX = -1;  
        if ((kutu.y+50) > bileþenAlaný.getHeight()) yeniY = (int)bileþenAlaný.getHeight() - 49;
        if (kutu.y < 0) yeniY = -1;

        kutu.setLocation (yeniX, yeniY);
        return false;
    } // alanÝçindeMi() metodu sonu...

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke (new BasicStroke (8.0f));
        Dimension ebat = getSize();
        int en = (int)ebat.getWidth();
        int boy = (int)ebat.getHeight();

        if (ilkMi) {
            bileþenAlaný = new Rectangle (ebat);
            kutu.setLocation (en/2-50, boy/2-25);
            ilkMi = false;
        } // if kararý sonu...

        g2.setPaint (Color.BLUE); // Zemin rengiyle önceki kutuyu temizler...
        g2.fillRect (0,0, en, boy);

        g2.setPaint (dolguPolka);
        g2.fill (kutu);	
        g2.setPaint (koyuPolka);
        g2.draw (kutu);
    } // paintComponent(..) hazýr metodu sonu...
} // ÞekilPaneli sýnýfý sonu...