// J5c_44x2.java: ScrollablePicture (Kayd�r�labilirResim) alt-�rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.Scrollable;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class J5c_44x2 extends JLabel implements Scrollable, MouseMotionListener {
    private int azamiBirimArt��� = 1;
    private boolean resimYokMu = false;

    public J5c_44x2 (ImageIcon resim, int art��) {
        super (resim);
        if (resim == null) {
            resimYokMu = true;
            setText ("Resim bulunamad�.");
            setHorizontalAlignment (CENTER);
            setOpaque (true);
            setBackground (Color.cyan);
        } // if karar� sonu...
        azamiBirimArt��� = art��;

        setAutoscrolls (true); // Pencere daral�p-geni�leyince kayd�rma belirsin/kaybolsun...
        addMouseMotionListener (this); // Resim ebat de�i�ikli�ine duyarlayal�m...
    } // J5c_44x2(..) kurucusu sonu...

    public void mouseMoved (MouseEvent duyars�z) {}

    public void mouseDragged (MouseEvent olay) {
        // kullan�c� resmin ebatlar�n� s�r�kl�yor, kayd�rma yoksa g�r�ns�n...
        Rectangle dikd�rtgen = new Rectangle (olay.getX(), olay.getY(), 1, 1);
        scrollRectToVisible (dikd�rtgen);
    } // mouseDragged(..) haz�r metodu sonu...

    public boolean getScrollableTracksViewportHeight() {return false;}
    public boolean getScrollableTracksViewportWidth() {return false;}
    public int getScrollableBlockIncrement (Rectangle visibleRect, int orientation, int direction) {return 0;}
    public int getScrollableUnitIncrement (Rectangle visibleRect, int orientation, int direction) {return 0;}
    public Dimension getPreferredScrollableViewportSize() {return tercihiEbat�Al();}
    public void azamiBirimArt���Koy (int pixels) {azamiBirimArt��� = pixels;}

    public Dimension tercihiEbat�Al() {
        if (resimYokMu) return new Dimension (280, 293);
        else return super.getPreferredSize();
    } // tercihiEbat�Al() metodu sonu...
} // J5c_44x2 s�n�f� sonu...