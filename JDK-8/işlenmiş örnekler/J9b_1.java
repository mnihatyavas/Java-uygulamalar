// J9b_1.java: ImageLabel (ResimliArkaplan) örneði.

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

// Gerekli resim dosyasý: boyama.gif
public class J9b_1 extends Component {
    BufferedImage resim;
    String metin;
    Font yazýTipi;

    public static void main (String args[]) {
        JFrame çerçeve = new JFrame ("Resimli Arkaplan");
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_CLOSE
        BufferedImage resim = null;
        try {resim = ImageIO.read (new File ("resim/boyama.gif"));
        }catch (IOException ist) {} // Aldýrma...
        çerçeve.add ("Center", new J9b_1 (resim, "Java 2D Graphics!")); // Kurucuyu çaðýrýr...
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...

    public J9b_1 (BufferedImage r, String m) {// paint()'i iþletir, tip deðiþkenlerine deðer atar...
        this.resim = r;
        this.metin = m;
        yazýTipi = new Font ("Serif", Font.PLAIN, 36);
    } // J9b_1(..) kurucusu sonu...

    public Dimension getPreferredSize() {
        FontMetrics ölçüler = resim.getGraphics().getFontMetrics (yazýTipi);
        int en = ölçüler.stringWidth (metin) * 2;
        int boy = ölçüler.getHeight() * 2;
        return new Dimension (en, boy);
    } // getPreferredSize() hazýr metodu (pack() için) sonu...

    public void paint (Graphics g) {
        Dimension ebat = getSize();
        g.drawImage (resim,
                0, 0, ebat.width, ebat.height,
                0, 0, resim.getWidth (null), resim.getHeight (null),
                null);

        // Resim içi metni yuvarlak kenarlý kutuya kurar...
        g.setFont (yazýTipi);
        FontRenderContext fonSunucuÝçeriði = ((Graphics2D)g).getFontRenderContext();
        Rectangle2D metinKutusuSýnýrlarý = yazýTipi.getStringBounds (metin, fonSunucuÝçeriði);

        int metninEni = (int)metinKutusuSýnýrlarý.getWidth();
        int metninBoyu = (int)metinKutusuSýnýrlarý.getHeight();

        int kutuX = (ebat.width - metninEni) / 2;
        int kutuY = (ebat.height - metninBoyu) / 2;
        g.setColor (Color.yellow);
        g.fillRoundRect (kutuX, kutuY, metninEni, metninBoyu, metninBoyu / 2, metninBoyu / 2);

        int xMetin = kutuX-(int)metinKutusuSýnýrlarý.getX();
        int yMetin = kutuY-(int)metinKutusuSýnýrlarý.getY();
        g.setColor (Color.blue);
        g.setFont (yazýTipi);
        g.drawString (metin, xMetin, yMetin);
    } // paint(..) hazýr metodu sonu...
} // J9b_1 sýnýfý sonu...