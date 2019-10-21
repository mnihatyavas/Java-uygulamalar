// J5c_15x4.java: Utils (Kullan�mlar) alt-�rne�i.

import javax.swing.ImageIcon;
import java.io.File;

public class J5c_15x4 {
    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";

    public static String uzant�y�Al (File dosya) {
        String uzant� = null;
        String dizge = dosya.getName();
        int i = dizge.lastIndexOf ('.');
        if (i > 0 &&  i < dizge.length() - 1) uzant� = dizge.substring (i+1).toLowerCase();
        return uzant�;
    } // uzant�y�Al(..) metodu sonu...

    protected static ImageIcon resim�konuYarat (String yol) {
        java.net.URL resimYureli = J5c_15x4.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas� bulunamad�!"); return null;}
    } // resim�konuYarat(..) metodu sonu...
} // J5c_15x4 s�n�f� sonu...