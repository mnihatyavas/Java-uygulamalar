// J5c_15x2.java: ImageFileView (ResimDosyasýGörüntüleme) alt-örneði.

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;
import java.io.File;

public class J5c_15x2 extends FileView {
    ImageIcon jpgÝkonu = J5c_15x4.resimÝkonuYarat ("resim/jpgIcon.gif");
    ImageIcon gifÝkonu = J5c_15x4.resimÝkonuYarat ("resim/gifIcon.gif");
    ImageIcon tiffÝkonu = J5c_15x4.resimÝkonuYarat ("resim/tiffIcon.gif");
    ImageIcon pngÝkonu = J5c_15x4.resimÝkonuYarat ("resim/pngIcon.png");

    public String getName (File dosya) {return null;}
    public String getDescription (File dosya) {return null;}
    public Boolean isTraversable (File dosya) {return null;}

    public String getTypeDescription (File dosya) {
        String uzantý = J5c_15x4.uzantýyýAl (dosya);
        String tip = null;

        if (uzantý != null) {
            if (uzantý.equals (J5c_15x4.jpeg) || uzantý.equals (J5c_15x4.jpg)) tip = "JPEG Resmi";
            else if (uzantý.equals (J5c_15x4.gif)) tip = "GIF Resmi";
            else if (uzantý.equals (J5c_15x4.tiff) || uzantý.equals (J5c_15x4.tif)) tip = "TIFF Resmi";
            else if (uzantý.equals (J5c_15x4.png)) tip = "PNG Resmi";
        } // dýþ if kararý sonu...

        return tip;
    } // getTypeDescription(..) metodu sonu...

    public Icon getIcon (File dosya) {
        String uzantý = J5c_15x4.uzantýyýAl (dosya);
        Icon ikon = null;

        if (uzantý != null) {
            if (uzantý.equals (J5c_15x4.jpeg) || uzantý.equals (J5c_15x4.jpg)) ikon = jpgÝkonu;
            else if (uzantý.equals (J5c_15x4.gif)) ikon = gifÝkonu;
            else if (uzantý.equals (J5c_15x4.tiff) || uzantý.equals (J5c_15x4.tif)) ikon = tiffÝkonu;
            else if (uzantý.equals (J5c_15x4.png)) ikon = pngÝkonu;
        } // dýþ if kararý sonu...

        return ikon;
    } // getIcon(..) metodu sonu...
} // J5c_15x2 sýnýfý sonu...