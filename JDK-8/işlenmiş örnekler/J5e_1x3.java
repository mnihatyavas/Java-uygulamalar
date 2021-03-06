// J5e_1x3.java: ScrollablePicture (KaydırılabilirResim) alt-örneği.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class J5e_1x3 extends JLabel implements Scrollable {
    private int azamiBirimArtışı = 1;
    private boolean resimYokMu = false;

    public J5e_1x3 (ImageIcon resim, int artış) {
        super (resim);
        if (resim == null) {
            resimYokMu = true;
            setBackground (Color.BLUE);
            setForeground (Color.WHITE);
            setText ("Resim Bulunamadı!");
            setHorizontalAlignment (CENTER);
            setOpaque (true);
        } // if kararı sonu...
        azamiBirimArtışı = artış;
    } // J5e_1x3(..) kurucusu sonu...

    public boolean getScrollableTracksViewportWidth() {return false;}
    public boolean getScrollableTracksViewportHeight() {return false;}
    public void azamiArtışıKur (int pixel) {azamiBirimArtışı = pixel;}
    public Dimension getPreferredScrollableViewportSize() {return tercihiEbatıAl();}

    public Dimension tercihiEbatıAl() {
        if (resimYokMu) return new Dimension (320, 350);
        else return super.getPreferredSize();
    } // tercihiEbatıAl() metodu sonu...

    public int getScrollableUnitIncrement (Rectangle cetvel, int yatıkDik, int yön) {
        int şimdikiKonum = 0;
        if (yatıkDik == SwingConstants.HORIZONTAL) şimdikiKonum = cetvel.x;
        else şimdikiKonum = cetvel.y;

        if (yön < 0) {
            int yeniKonum = şimdikiKonum - (şimdikiKonum / azamiBirimArtışı) * azamiBirimArtışı;
            return (yeniKonum == 0) ? azamiBirimArtışı : yeniKonum;
        } else return ((şimdikiKonum / azamiBirimArtışı) + 1) * azamiBirimArtışı - şimdikiKonum;
    } // getScrollableUnitIncrement(..) hazır metodu sonu...

    public int getScrollableBlockIncrement (Rectangle cetvel, int yatıkDik, int yön) {
        if (yatıkDik == SwingConstants.HORIZONTAL) return cetvel.width - azamiBirimArtışı;
        else return cetvel.height - azamiBirimArtışı;
    } // getScrollableBlockIncrement(..) hazır metodu sonu...
} // J5e_1x3 sınıfı sonu...