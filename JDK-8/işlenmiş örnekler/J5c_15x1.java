// J5c_15x1.java: ImageFilter (ResimFilitresi) alt-�rne�i.

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class J5c_15x1 extends FileFilter {
    // T�m dizinleri ve gif, jpg, tiff veya png dosyalar�n� kabul edelim...
    public boolean accept (File dosya) {
        if (dosya.isDirectory()) return true;

        String uzant� = J5c_15x4.uzant�y�Al (dosya);
        if (uzant� != null) {
            if (uzant�.equals (J5c_15x4.tiff) ||
                uzant�.equals (J5c_15x4.tif) ||
                uzant�.equals (J5c_15x4.gif) ||
                uzant�.equals (J5c_15x4.jpeg) ||
                uzant�.equals (J5c_15x4.jpg) ||
                uzant�.equals (J5c_15x4.png)) return true;
            else return false;
        } // d�� if karar� sonu...

        return false;
    } // accept(..) metodu sonu...

    // Bu filitrenin a��klamas�...
    public String getDescription() {return "Sadece Resimler";}
} // J5c_15x1 s�n�f� sonu...