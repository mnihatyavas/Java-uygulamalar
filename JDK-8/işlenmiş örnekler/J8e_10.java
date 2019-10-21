// J8e_10.java: StringConverter (DizgeÇevirici) örneği.

import java.io.UnsupportedEncodingException;

// Gerekli dosya: J8e_10x.java=UnicodeFormatter.java
public class J8e_10 {
    public static void bytelarıYaz (byte[] dizi, String ad) {
        for (int k = 0; k < dizi.length; k++)
            System.out.println (ad + "[" + k + "] = " + "0x" + J8e_10x.byteDanHexE (dizi[k]));
    } // bytelarıYaz(..) metodu sonu...

    public static void main (String[] argüman) {
        System.out.println ("Kullanılan kodlama dosyası: " + System.getProperty ("file.encoding"));
        String orijinalDizge = argüman.length > 0? argüman[0] : new String ("A" + "\u00ea" + "\u00f1" + "\u00fc" + "C"); // AêñüC = ASCII:41-136-164-129-43
        System.out.println ("Orijinal dizgemiz = " + orijinalDizge);

        try {
            byte[] varsayılıBytelar = orijinalDizge.getBytes();
            System.out.println ("\nVarsayılı byte'lar [" + new String (varsayılıBytelar) + "]'ın hex kodları:");
            bytelarıYaz (varsayılıBytelar, "varsayılıBytelar");

            byte[] utf8Bytelar = orijinalDizge.getBytes ("UTF8");
            System.out.println ("\nUTF-8 byte'lar [" + new String (utf8Bytelar) + "]'ın hex kodları:");
            bytelarıYaz (utf8Bytelar, "utf8Bytelar");
        }catch (UnsupportedEncodingException ist) {ist.printStackTrace();}
    } // main(..) metodu sonu...
} // J8e_10 sınıfı sonu...

/* Çıktı:
**  >java J8e_10  **
Kullanılan kodlama dosyası: Cp1254
Orijinal dizgemiz = AêñüC

Varsayılı byte'lar [AêñüC]'ın hex kodları:
varsayılıBytelar[0] = 0x41
varsayılıBytelar[1] = 0xea
varsayılıBytelar[2] = 0xf1
varsayılıBytelar[3] = 0xfc
varsayılıBytelar[4] = 0x43

UTF-8 byte'lar [AÃªÃ±Ã¼C]'ın hex kodları:
utf8Bytelar[0] = 0x41
utf8Bytelar[1] = 0xc3
utf8Bytelar[2] = 0xaa
utf8Bytelar[3] = 0xc3
utf8Bytelar[4] = 0xb1
utf8Bytelar[5] = 0xc3
utf8Bytelar[6] = 0xbc
utf8Bytelar[7] = 0x43

**  >java J8e_10 Çarşı  ** TEKRAR
Kullanılan kodlama dosyası: Cp1254
Orijinal dizgemiz = Çarşı

Varsayılı byte'lar [Çarşı]'ın hex kodları:
varsayılıBytelar[0] = 0xc7
varsayılıBytelar[1] = 0x61
varsayılıBytelar[2] = 0x72
varsayılıBytelar[3] = 0xfe
varsayılıBytelar[4] = 0xfd

UTF-8 byte'lar [Ã?arÅ?Ä±]'ın hex kodları:
utf8Bytelar[0] = 0xc3
utf8Bytelar[1] = 0x87
utf8Bytelar[2] = 0x61
utf8Bytelar[3] = 0x72
utf8Bytelar[4] = 0xc5
utf8Bytelar[5] = 0x9f
utf8Bytelar[6] = 0xc4
utf8Bytelar[7] = 0xb1

**  >java J8e_10 "Gül Balı"  ** TEKRAR
Kullanılan kodlama dosyası: Cp1254
Orijinal dizgemiz = Gül Balı

Varsayılı byte'lar [Gül Balı]'ın hex kodları:
varsayılıBytelar[0] = 0x47
varsayılıBytelar[1] = 0xfc
varsayılıBytelar[2] = 0x6c
varsayılıBytelar[3] = 0x20
varsayılıBytelar[4] = 0x42
varsayılıBytelar[5] = 0x61
varsayılıBytelar[6] = 0x6c
varsayılıBytelar[7] = 0xfd

UTF-8 byte'lar [GÃ¼l BalÄ±]'ın hex kodları:
utf8Bytelar[0] = 0x47
utf8Bytelar[1] = 0xc3
utf8Bytelar[2] = 0xbc
utf8Bytelar[3] = 0x6c
utf8Bytelar[4] = 0x20
utf8Bytelar[5] = 0x42
utf8Bytelar[6] = 0x61
utf8Bytelar[7] = 0x6c
utf8Bytelar[8] = 0xc4
utf8Bytelar[9] = 0xb1
*/