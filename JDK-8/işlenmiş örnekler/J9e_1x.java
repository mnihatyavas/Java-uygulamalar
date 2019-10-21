// J9e_1x.java: ImageDrawingComponent (ResimÇizmeBileþeni) alt-örneði.

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;

import java.awt.image.AffineTransformOp;
import java.awt.image.ConvolveOp;
import java.awt.image.RescaleOp;
import java.awt.image.LookupOp;
import java.awt.image.ByteLookupTable;
import java.awt.image.BufferedImage;
import java.awt.image.Kernel;

import javax.imageio.ImageIO;

import java.io.IOException;

import java.net.URL;

class J9e_1x extends Component {
    static String tercihler[] = {
        "Basit Kopya",
        "Üst Büyük Ölçekli",
        "Alt Küçük Ölçekli",
        "Üst Ölçek: Kübik",
        "Sade: Buðulu",
        "Sade : Keskin", 
        "Parlak",
        "MaviþNegatif",
    };
    int tercihlerEndeksi;
    private BufferedImage tamponlananResim;
    int en, boy;
    public static final float[] KESKÝN3x3 = {
        0.f, -1.f,  0.f,
       -1.f,  5.f, -1.f,
        0.f, -1.f,  0.f
    };
    public static final float[] BUÐULU3x3 = {
        0.1f, 0.1f, 0.1f,
        0.1f, 0.2f, 0.1f,
        0.1f, 0.1f, 0.1f
    };

    public J9e_1x (URL resimKaynaðý) {
        try {
            tamponlananResim = ImageIO.read (resimKaynaðý);
            en = tamponlananResim.getWidth (null);
            boy = tamponlananResim.getHeight (null);
            if (tamponlananResim.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage tamponlananResim2 = new BufferedImage (en, boy, BufferedImage.TYPE_INT_RGB);
                Graphics tamponlananResimg = tamponlananResim2.getGraphics();
                tamponlananResimg.drawImage (tamponlananResim, 0, 0, null);
                tamponlananResim = tamponlananResim2;
            } // if kararý sonu...
        }catch (IOException ist) {
            System.err.println ("Resim okunamadý");
            //System.exit (1);
        } // try-catch bloðu sonu...
    } // J9e_1x(..) kurucusu sonu...

    public Dimension getPreferredSize() {return new Dimension (en, boy);} // pack() için gerekli...
    static String[] getDescriptions() {return tercihler;}
    void setOpIndex (int i) {tercihlerEndeksi = i;}

    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        switch (tercihlerEndeksi) {
        case 0 : // Basit Kopya...
            g.drawImage (tamponlananResim, 0, 0, null);
            break;
        case 1 : // Üst Büyük Ölçekli...
            g.drawImage (tamponlananResim,
                    0, 0, en, boy, // Görüntü ebatý...
                    0, 0, en/2, boy/2, // Üst büyütülen yarý...
                    null);
            break;
        case 2 : // Alt Küçük Ölçekli...
            g2.drawImage (tamponlananResim, AffineTransform.getScaleInstance (0.7, 0.7), null);
            break;
        case 3: // Kübik Üst Ölçek...
            AffineTransform at = AffineTransform.getScaleInstance (1.5, 1.5);
            AffineTransformOp aop = new AffineTransformOp (at, AffineTransformOp.TYPE_BICUBIC);
            g2.drawImage (tamponlananResim, aop, 0, 0);
            break;
        case 4: // Buðulu Sade...
        case 5: // Keskin Sade...
            float[] data = (tercihlerEndeksi == 4) ? BUÐULU3x3 : KESKÝN3x3;
            ConvolveOp cop = new ConvolveOp (new Kernel (3, 3, data), ConvolveOp.EDGE_NO_OP, null);
            g2.drawImage (tamponlananResim, cop, 0, 0);
            break;
        case 6 : // Parlak...
            RescaleOp rop = new RescaleOp (1.1f, 20.0f, null);
            g2.drawImage (tamponlananResim, rop, 0, 0);
            break;
        case 7 : // Negatif Mavimtrak...
            byte lut[] = new byte[256];
            for (int j=0; j<256; j++) lut[j] = (byte)(256-j); 
            ByteLookupTable blut = new ByteLookupTable (0, lut); 
            LookupOp lop = new LookupOp (blut, null);
            g2.drawImage (tamponlananResim, lop, 0, 0);
            break;
        default :
        } // switch bloðu sonu...
    } // paint(..) hazýr metodu sonu...
} // J9e_1x sýnýfý sonu...