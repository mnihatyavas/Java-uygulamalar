// J5e_1x3.java: ScrollablePicture (KaydýrýlabilirResim) alt-örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class J5e_1x3 extends JLabel implements Scrollable {
    private int azamiBirimArtýþý = 1;
    private boolean resimYokMu = false;

    public J5e_1x3 (ImageIcon resim, int artýþ) {
        super (resim);
        if (resim == null) {
            resimYokMu = true;
            setBackground (Color.BLUE);
            setForeground (Color.WHITE);
            setText ("Resim Bulunamadý!");
            setHorizontalAlignment (CENTER);
            setOpaque (true);
        } // if kararý sonu...
        azamiBirimArtýþý = artýþ;
    } // J5e_1x3(..) kurucusu sonu...

    public boolean getScrollableTracksViewportWidth() {return false;}
    public boolean getScrollableTracksViewportHeight() {return false;}
    public void azamiArtýþýKur (int pixel) {azamiBirimArtýþý = pixel;}
    public Dimension getPreferredScrollableViewportSize() {return tercihiEbatýAl();}

    public Dimension tercihiEbatýAl() {
        if (resimYokMu) return new Dimension (320, 350);
        else return super.getPreferredSize();
    } // tercihiEbatýAl() metodu sonu...

    public int getScrollableUnitIncrement (Rectangle cetvel, int yatýkDik, int yön) {
        int þimdikiKonum = 0;
        if (yatýkDik == SwingConstants.HORIZONTAL) þimdikiKonum = cetvel.x;
        else þimdikiKonum = cetvel.y;

        if (yön < 0) {
            int yeniKonum = þimdikiKonum - (þimdikiKonum / azamiBirimArtýþý) * azamiBirimArtýþý;
            return (yeniKonum == 0) ? azamiBirimArtýþý : yeniKonum;
        } else return ((þimdikiKonum / azamiBirimArtýþý) + 1) * azamiBirimArtýþý - þimdikiKonum;
    } // getScrollableUnitIncrement(..) hazýr metodu sonu...

    public int getScrollableBlockIncrement (Rectangle cetvel, int yatýkDik, int yön) {
        if (yatýkDik == SwingConstants.HORIZONTAL) return cetvel.width - azamiBirimArtýþý;
        else return cetvel.height - azamiBirimArtýþý;
    } // getScrollableBlockIncrement(..) hazýr metodu sonu...
} // J5e_1x3 sýnýfý sonu...