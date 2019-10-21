// J8e_10x.java: UnicodeFormatter (TekkodBiçimleyici) alt-örneði.

public class J8e_10x  {
    static public String byteDanHexE (byte b) {
        char hexRakamlar[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] dizi = {hexRakamlar[(b >> 4) & 0x0f], hexRakamlar[b & 0x0f]};
        return new String (dizi);
    } // String byteDanHexE(..) metodu sonu...
} // J8e_10x sýnýfý sonu...