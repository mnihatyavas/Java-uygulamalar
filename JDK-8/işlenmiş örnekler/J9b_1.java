// J9b_1.java: ImageLabel (ResimliArkaplan) �rne�i.

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FontMetrics;

import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

// Gerekli resim dosyas�: boyama.gif
public class J9b_1 extends Component {
    BufferedImage resim;
    String metin;
    Font yaz�Tipi;

    public static void main (String args[]) {
        JFrame �er�eve = new JFrame ("Resimli Arkaplan");
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_CLOSE
        BufferedImage resim = null;
        try {resim = ImageIO.read (new File ("resim/boyama.gif"));
        }catch (IOException ist) {} // Ald�rma...
        �er�eve.add ("Center", new J9b_1 (resim, "Java 2D Graphics!")); // Kurucuyu �a��r�r...
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...

    public J9b_1 (BufferedImage r, String m) {// paint()'i i�letir, tip de�i�kenlerine de�er atar...
        this.resim = r;
        this.metin = m;
        yaz�Tipi = new Font ("Serif", Font.PLAIN, 36);
    } // J9b_1(..) kurucusu sonu...

    public Dimension getPreferredSize() {
        FontMetrics �l��ler = resim.getGraphics().getFontMetrics (yaz�Tipi);
        int en = �l��ler.stringWidth (metin) * 2;
        int boy = �l��ler.getHeight() * 2;
        return new Dimension (en, boy);
    } // getPreferredSize() haz�r metodu (pack() i�in) sonu...

    public void paint (Graphics g) {
        Dimension ebat = getSize();
        g.drawImage (resim,
                0, 0, ebat.width, ebat.height,
                0, 0, resim.getWidth (null), resim.getHeight (null),
                null);

        // Resim i�i metni yuvarlak kenarl� kutuya kurar...
        g.setFont (yaz�Tipi);
        FontRenderContext fonSunucu��eri�i = ((Graphics2D)g).getFontRenderContext();
        Rectangle2D metinKutusuS�n�rlar� = yaz�Tipi.getStringBounds (metin, fonSunucu��eri�i);

        int metninEni = (int)metinKutusuS�n�rlar�.getWidth();
        int metninBoyu = (int)metinKutusuS�n�rlar�.getHeight();

        int kutuX = (ebat.width - metninEni) / 2;
        int kutuY = (ebat.height - metninBoyu) / 2;
        g.setColor (Color.yellow);
        g.fillRoundRect (kutuX, kutuY, metninEni, metninBoyu, metninBoyu / 2, metninBoyu / 2);

        int xMetin = kutuX-(int)metinKutusuS�n�rlar�.getX();
        int yMetin = kutuY-(int)metinKutusuS�n�rlar�.getY();
        g.setColor (Color.blue);
        g.setFont (yaz�Tipi);
        g.drawString (metin, xMetin, yMetin);
    } // paint(..) haz�r metodu sonu...
} // J9b_1 s�n�f� sonu...