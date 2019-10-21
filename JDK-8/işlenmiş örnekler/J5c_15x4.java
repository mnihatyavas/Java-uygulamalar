// J5c_15x4.java: Utils (Kullanýmlar) alt-örneði.

import javax.swing.ImageIcon;
import java.io.File;

public class J5c_15x4 {
    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";

    public static String uzantýyýAl (File dosya) {
        String uzantý = null;
        String dizge = dosya.getName();
        int i = dizge.lastIndexOf ('.');
        if (i > 0 &&  i < dizge.length() - 1) uzantý = dizge.substring (i+1).toLowerCase();
        return uzantý;
    } // uzantýyýAl(..) metodu sonu...

    protected static ImageIcon resimÝkonuYarat (String yol) {
        java.net.URL resimYureli = J5c_15x4.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasý bulunamadý!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...
} // J5c_15x4 sýnýfý sonu...