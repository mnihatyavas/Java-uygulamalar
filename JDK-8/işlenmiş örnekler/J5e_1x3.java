// J5e_1x3.java: ScrollablePicture (Kayd�r�labilirResim) alt-�rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class J5e_1x3 extends JLabel implements Scrollable {
    private int azamiBirimArt��� = 1;
    private boolean resimYokMu = false;

    public J5e_1x3 (ImageIcon resim, int art��) {
        super (resim);
        if (resim == null) {
            resimYokMu = true;
            setBackground (Color.BLUE);
            setForeground (Color.WHITE);
            setText ("Resim Bulunamad�!");
            setHorizontalAlignment (CENTER);
            setOpaque (true);
        } // if karar� sonu...
        azamiBirimArt��� = art��;
    } // J5e_1x3(..) kurucusu sonu...

    public boolean getScrollableTracksViewportWidth() {return false;}
    public boolean getScrollableTracksViewportHeight() {return false;}
    public void azamiArt���Kur (int pixel) {azamiBirimArt��� = pixel;}
    public Dimension getPreferredScrollableViewportSize() {return tercihiEbat�Al();}

    public Dimension tercihiEbat�Al() {
        if (resimYokMu) return new Dimension (320, 350);
        else return super.getPreferredSize();
    } // tercihiEbat�Al() metodu sonu...

    public int getScrollableUnitIncrement (Rectangle cetvel, int yat�kDik, int y�n) {
        int �imdikiKonum = 0;
        if (yat�kDik == SwingConstants.HORIZONTAL) �imdikiKonum = cetvel.x;
        else �imdikiKonum = cetvel.y;

        if (y�n < 0) {
            int yeniKonum = �imdikiKonum - (�imdikiKonum / azamiBirimArt���) * azamiBirimArt���;
            return (yeniKonum == 0) ? azamiBirimArt��� : yeniKonum;
        } else return ((�imdikiKonum / azamiBirimArt���) + 1) * azamiBirimArt��� - �imdikiKonum;
    } // getScrollableUnitIncrement(..) haz�r metodu sonu...

    public int getScrollableBlockIncrement (Rectangle cetvel, int yat�kDik, int y�n) {
        if (yat�kDik == SwingConstants.HORIZONTAL) return cetvel.width - azamiBirimArt���;
        else return cetvel.height - azamiBirimArt���;
    } // getScrollableBlockIncrement(..) haz�r metodu sonu...
} // J5e_1x3 s�n�f� sonu...