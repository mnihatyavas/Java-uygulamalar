// J5c_44x2.java: ScrollablePicture (KaydýrýlabilirResim) alt-örneði.

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
    private int azamiBirimArtýþý = 1;
    private boolean resimYokMu = false;

    public J5c_44x2 (ImageIcon resim, int artýþ) {
        super (resim);
        if (resim == null) {
            resimYokMu = true;
            setText ("Resim bulunamadý.");
            setHorizontalAlignment (CENTER);
            setOpaque (true);
            setBackground (Color.cyan);
        } // if kararý sonu...
        azamiBirimArtýþý = artýþ;

        setAutoscrolls (true); // Pencere daralýp-geniþleyince kaydýrma belirsin/kaybolsun...
        addMouseMotionListener (this); // Resim ebat deðiþikliðine duyarlayalým...
    } // J5c_44x2(..) kurucusu sonu...

    public void mouseMoved (MouseEvent duyarsýz) {}

    public void mouseDragged (MouseEvent olay) {
        // kullanýcý resmin ebatlarýný sürüklüyor, kaydýrma yoksa görünsün...
        Rectangle dikdörtgen = new Rectangle (olay.getX(), olay.getY(), 1, 1);
        scrollRectToVisible (dikdörtgen);
    } // mouseDragged(..) hazýr metodu sonu...

    public boolean getScrollableTracksViewportHeight() {return false;}
    public boolean getScrollableTracksViewportWidth() {return false;}
    public int getScrollableBlockIncrement (Rectangle visibleRect, int orientation, int direction) {return 0;}
    public int getScrollableUnitIncrement (Rectangle visibleRect, int orientation, int direction) {return 0;}
    public Dimension getPreferredScrollableViewportSize() {return tercihiEbatýAl();}
    public void azamiBirimArtýþýKoy (int pixels) {azamiBirimArtýþý = pixels;}

    public Dimension tercihiEbatýAl() {
        if (resimYokMu) return new Dimension (280, 293);
        else return super.getPreferredSize();
    } // tercihiEbatýAl() metodu sonu...
} // J5c_44x2 sýnýfý sonu...