// J5c_15x2.java: ImageFileView (ResimDosyas�G�r�nt�leme) alt-�rne�i.

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;
import java.io.File;

public class J5c_15x2 extends FileView {
    ImageIcon jpg�konu = J5c_15x4.resim�konuYarat ("resim/jpgIcon.gif");
    ImageIcon gif�konu = J5c_15x4.resim�konuYarat ("resim/gifIcon.gif");
    ImageIcon tiff�konu = J5c_15x4.resim�konuYarat ("resim/tiffIcon.gif");
    ImageIcon png�konu = J5c_15x4.resim�konuYarat ("resim/pngIcon.png");

    public String getName (File dosya) {return null;}
    public String getDescription (File dosya) {return null;}
    public Boolean isTraversable (File dosya) {return null;}

    public String getTypeDescription (File dosya) {
        String uzant� = J5c_15x4.uzant�y�Al (dosya);
        String tip = null;

        if (uzant� != null) {
            if (uzant�.equals (J5c_15x4.jpeg) || uzant�.equals (J5c_15x4.jpg)) tip = "JPEG Resmi";
            else if (uzant�.equals (J5c_15x4.gif)) tip = "GIF Resmi";
            else if (uzant�.equals (J5c_15x4.tiff) || uzant�.equals (J5c_15x4.tif)) tip = "TIFF Resmi";
            else if (uzant�.equals (J5c_15x4.png)) tip = "PNG Resmi";
        } // d�� if karar� sonu...

        return tip;
    } // getTypeDescription(..) metodu sonu...

    public Icon getIcon (File dosya) {
        String uzant� = J5c_15x4.uzant�y�Al (dosya);
        Icon ikon = null;

        if (uzant� != null) {
            if (uzant�.equals (J5c_15x4.jpeg) || uzant�.equals (J5c_15x4.jpg)) ikon = jpg�konu;
            else if (uzant�.equals (J5c_15x4.gif)) ikon = gif�konu;
            else if (uzant�.equals (J5c_15x4.tiff) || uzant�.equals (J5c_15x4.tif)) ikon = tiff�konu;
            else if (uzant�.equals (J5c_15x4.png)) ikon = png�konu;
        } // d�� if karar� sonu...

        return ikon;
    } // getIcon(..) metodu sonu...
} // J5c_15x2 s�n�f� sonu...