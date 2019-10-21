// J5j_3.java: SelectionDemo (Se�meG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;

import java.net.URL;

// Gerekli resim dosyas�: resim/y�ld�zl�Uzay.gif
public class J5j_3 {
    JLabel etiket;
    static String resimDosyas� = "resim/y�ld�zl�Uzay.gif";

    private void komponentleriEkle (Container i�erikKab�, ImageIcon resim�konu) {
        i�erikKab�.setLayout  (new BoxLayout (i�erikKab�, BoxLayout.PAGE_AXIS));
        Se�meliUzayAlan� uzayAlan� = new Se�meliUzayAlan� (resim�konu, this); // S�n�f kurucusunu �a��r�r...
        i�erikKab�.add (uzayAlan�);
        etiket = new JLabel ("Uzay bo�lu�unda s�r�kleyip bir alan se�in.");
        etiket.setLabelFor (uzayAlan�);
        i�erikKab�.add (etiket);
        // Par�alar� sola (ancak boyutu resim ikonu boyutuna sabit) hizalayal�m...
        uzayAlan�.setAlignmentX (Component.LEFT_ALIGNMENT);
        etiket.setAlignmentX (Component.LEFT_ALIGNMENT);
    } // komponentleriEkle(..) metodu sonu...

    private class Se�meliUzayAlan� extends JLabel {
        Rectangle akt�elDikd�rtgen = null;
        Rectangle �izilecekDikd�rtgen = null;
        Rectangle �nceki�izilenDikd�rtgen = new Rectangle();
        J5j_3 s�n�fNesnesi;
    
        public Se�meliUzayAlan� (ImageIcon resim�konu, J5j_3 s�n�fNesnesi) {// Kurucu...
            super (resim�konu);
            this.s�n�fNesnesi = s�n�fNesnesi;
            setOpaque (true);
            setMinimumSize (new Dimension (10,10)); // Uzay� pek b�y�kce kirletmeyelim...
            FareDinleyicim dinleyicim = new FareDinleyicim();
            addMouseListener (dinleyicim); // Fare s�r�klemesine duyarl�...
            addMouseMotionListener (dinleyicim);
        } // Se�meliUzayAlan� s�n�f� kurucusu sonu...

        private class FareDinleyicim extends MouseInputAdapter {
            public void mousePressed (MouseEvent olay) {
                int x = olay.getX();
                int y = olay.getY();
                akt�elDikd�rtgen = new Rectangle (x, y, 0, 0);
                �izilebilirDikd�rtgeniG�ncelle (getWidth(), getHeight());
                repaint();
            } // mousePressed(..) haz�r metodu sonu...

            public void mouseDragged (MouseEvent olay) {boyutuG�ncelle (olay);}
            public void mouseReleased (MouseEvent olay) {boyutuG�ncelle (olay);}

            void boyutuG�ncelle (MouseEvent olay) {
                int x = olay.getX();
                int y = olay.getY();
                akt�elDikd�rtgen.setSize (x - akt�elDikd�rtgen.x, y - akt�elDikd�rtgen.y);
                �izilebilirDikd�rtgeniG�ncelle (getWidth(), getHeight());
                Rectangle toplamBoyanan = �izilecekDikd�rtgen.union (�nceki�izilenDikd�rtgen);
                repaint (toplamBoyanan.x, toplamBoyanan.y, toplamBoyanan.width, toplamBoyanan.height);
            } // boyutuG�ncelle(..) metodu sonu...
        } // FareDinleyicim s�n�f� sonu...

        private void �izilebilirDikd�rtgeniG�ncelle (int par�an�nGeni�li�i, int par�an�nY�ksekli�i) {
            int x = akt�elDikd�rtgen.x;
            int y = akt�elDikd�rtgen.y;
            int en = akt�elDikd�rtgen.width;
            int boy = akt�elDikd�rtgen.height;

            if (en < 0) {
                en = 0 - en;
                x = x - en + 1; 
                if (x < 0) {
                    en += x; 
                    x = 0;
                } // i�-if karar� sonu...
            } // d�� if karar� sonu...
            if (boy < 0) {
                boy = 0 - boy;
                y = y - boy + 1; 
                if (y < 0) {
                    boy += y; 
                    y = 0;
                } // i�-if karar� sonu...
            } // d��-if karar� sonu...

            // Se�ili dikd�rtgen boyutu uzay alan�n� ta�amaz...
            if ((x + en) > par�an�nGeni�li�i) en = par�an�nGeni�li�i - x;
            if ((y + boy) > par�an�nY�ksekli�i) boy = par�an�nY�ksekli�i - y;
            if (�izilecekDikd�rtgen != null) {�nceki�izilenDikd�rtgen.setBounds (
                    �izilecekDikd�rtgen.x, �izilecekDikd�rtgen.y, 
                    �izilecekDikd�rtgen.width, �izilecekDikd�rtgen.height);
                �izilecekDikd�rtgen.setBounds (x, y, en, boy);
            } else �izilecekDikd�rtgen = new Rectangle (x, y, en, boy);
        } // �izilebilirDikd�rtgeniG�ncelle(..) metodu sonu...

        protected void paintComponent (Graphics g) {
            super.paintComponent (g);
            if (akt�elDikd�rtgen != null) {
                g.setXORMode (Color.white);
                g.drawRect (�izilecekDikd�rtgen.x, �izilecekDikd�rtgen.y, �izilecekDikd�rtgen.width - 1, �izilecekDikd�rtgen.height - 1);
                s�n�fNesnesi.etiketiG�ncelle (�izilecekDikd�rtgen);
            } // if karar� sonu...
        } // paintComponent(..) haz�r metodu sonu...
    } // Se�meliUzayAlan� s�n�f� sonu...

    public void etiketiG�ncelle (Rectangle dikd�rtgen) {
        int en = dikd�rtgen.width;
        int boy = dikd�rtgen.height;

        if (en == 0) en = 1;
        if (boy == 0) boy = 1;

        etiket.setText ("Dikd�rtgenin ilk ve son konumlar�: ("
                + dikd�rtgen.x + "," + dikd�rtgen.y + ") ve ("
                + (dikd�rtgen.x + en - 1) + "," + (dikd�rtgen.y + boy - 1) + ").");
    } // etiketiG�ncelle(..) metodu sonu...

    protected static ImageIcon resim�konunuYarat (String yol) {
        URL resimYureli = J5j_3.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konunuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Se�me G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5j_3 s�n�fNesnesi = new J5j_3(); // Namevcut varsay�l� kurucuyu �a��r�r...
        s�n�fNesnesi.komponentleriEkle (�er�eve.getContentPane(), resim�konunuYarat (resimDosyas�));
        �er�eve.setLocation (200,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main(String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI(); }});
    } // main(..) metodu sonu...
} // J5j_3 s�n�f� sonu...