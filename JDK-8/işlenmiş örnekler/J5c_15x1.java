// J5c_15x1.java: ImageFilter (ResimFilitresi) alt-örneði.

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class J5c_15x1 extends FileFilter {
    // Tüm dizinleri ve gif, jpg, tiff veya png dosyalarýný kabul edelim...
    public boolean accept (File dosya) {
        if (dosya.isDirectory()) return true;

        String uzantý = J5c_15x4.uzantýyýAl (dosya);
        if (uzantý != null) {
            if (uzantý.equals (J5c_15x4.tiff) ||
                uzantý.equals (J5c_15x4.tif) ||
                uzantý.equals (J5c_15x4.gif) ||
                uzantý.equals (J5c_15x4.jpeg) ||
                uzantý.equals (J5c_15x4.jpg) ||
                uzantý.equals (J5c_15x4.png)) return true;
            else return false;
        } // dýþ if kararý sonu...

        return false;
    } // accept(..) metodu sonu...

    // Bu filitrenin açýklamasý...
    public String getDescription() {return "Sadece Resimler";}
} // J5c_15x1 sýnýfý sonu...