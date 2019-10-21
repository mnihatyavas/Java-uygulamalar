// J9e_1x.java: ImageDrawingComponent (Resim�izmeBile�eni) alt-�rne�i.

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
        "�st B�y�k �l�ekli",
        "Alt K���k �l�ekli",
        "�st �l�ek: K�bik",
        "Sade: Bu�ulu",
        "Sade : Keskin", 
        "Parlak",
        "Mavi�Negatif",
    };
    int tercihlerEndeksi;
    private BufferedImage tamponlananResim;
    int en, boy;
    public static final float[] KESK�N3x3 = {
        0.f, -1.f,  0.f,
       -1.f,  5.f, -1.f,
        0.f, -1.f,  0.f
    };
    public static final float[] BU�ULU3x3 = {
        0.1f, 0.1f, 0.1f,
        0.1f, 0.2f, 0.1f,
        0.1f, 0.1f, 0.1f
    };

    public J9e_1x (URL resimKayna��) {
        try {
            tamponlananResim = ImageIO.read (resimKayna��);
            en = tamponlananResim.getWidth (null);
            boy = tamponlananResim.getHeight (null);
            if (tamponlananResim.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage tamponlananResim2 = new BufferedImage (en, boy, BufferedImage.TYPE_INT_RGB);
                Graphics tamponlananResimg = tamponlananResim2.getGraphics();
                tamponlananResimg.drawImage (tamponlananResim, 0, 0, null);
                tamponlananResim = tamponlananResim2;
            } // if karar� sonu...
        }catch (IOException ist) {
            System.err.println ("Resim okunamad�");
            //System.exit (1);
        } // try-catch blo�u sonu...
    } // J9e_1x(..) kurucusu sonu...

    public Dimension getPreferredSize() {return new Dimension (en, boy);} // pack() i�in gerekli...
    static String[] getDescriptions() {return tercihler;}
    void setOpIndex (int i) {tercihlerEndeksi = i;}

    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        switch (tercihlerEndeksi) {
        case 0 : // Basit Kopya...
            g.drawImage (tamponlananResim, 0, 0, null);
            break;
        case 1 : // �st B�y�k �l�ekli...
            g.drawImage (tamponlananResim,
                    0, 0, en, boy, // G�r�nt� ebat�...
                    0, 0, en/2, boy/2, // �st b�y�t�len yar�...
                    null);
            break;
        case 2 : // Alt K���k �l�ekli...
            g2.drawImage (tamponlananResim, AffineTransform.getScaleInstance (0.7, 0.7), null);
            break;
        case 3: // K�bik �st �l�ek...
            AffineTransform at = AffineTransform.getScaleInstance (1.5, 1.5);
            AffineTransformOp aop = new AffineTransformOp (at, AffineTransformOp.TYPE_BICUBIC);
            g2.drawImage (tamponlananResim, aop, 0, 0);
            break;
        case 4: // Bu�ulu Sade...
        case 5: // Keskin Sade...
            float[] data = (tercihlerEndeksi == 4) ? BU�ULU3x3 : KESK�N3x3;
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
        } // switch blo�u sonu...
    } // paint(..) haz�r metodu sonu...
} // J9e_1x s�n�f� sonu...