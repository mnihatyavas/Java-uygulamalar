// J8e_10.java: StringConverter (Dizge�evirici) �rne�i.

import java.io.UnsupportedEncodingException;

// Gerekli dosya: J8e_10x.java=UnicodeFormatter.java
public class J8e_10 {
    public static void bytelar�Yaz (byte[] dizi, String ad) {
        for (int k = 0; k < dizi.length; k++)
            System.out.println (ad + "[" + k + "] = " + "0x" + J8e_10x.byteDanHexE (dizi[k]));
    } // bytelar�Yaz(..) metodu sonu...

    public static void main (String[] arg�man) {
        System.out.println ("Kullan�lan kodlama dosyas�: " + System.getProperty ("file.encoding"));
        String orijinalDizge = arg�man.length > 0? arg�man[0] : new String ("A" + "\u00ea" + "\u00f1" + "\u00fc" + "C"); // A���C = ASCII:41-136-164-129-43
        System.out.println ("Orijinal dizgemiz = " + orijinalDizge);

        try {
            byte[] varsay�l�Bytelar = orijinalDizge.getBytes();
            System.out.println ("\nVarsay�l� byte'lar [" + new String (varsay�l�Bytelar) + "]'�n hex kodlar�:");
            bytelar�Yaz (varsay�l�Bytelar, "varsay�l�Bytelar");

            byte[] utf8Bytelar = orijinalDizge.getBytes ("UTF8");
            System.out.println ("\nUTF-8 byte'lar [" + new String (utf8Bytelar) + "]'�n hex kodlar�:");
            bytelar�Yaz (utf8Bytelar, "utf8Bytelar");
        }catch (UnsupportedEncodingException ist) {ist.printStackTrace();}
    } // main(..) metodu sonu...
} // J8e_10 s�n�f� sonu...

/* ��kt�:
**  >java J8e_10  **
Kullan�lan kodlama dosyas�: Cp1254
Orijinal dizgemiz = A���C

Varsay�l� byte'lar [A���C]'�n hex kodlar�:
varsay�l�Bytelar[0] = 0x41
varsay�l�Bytelar[1] = 0xea
varsay�l�Bytelar[2] = 0xf1
varsay�l�Bytelar[3] = 0xfc
varsay�l�Bytelar[4] = 0x43

UTF-8 byte'lar [AêñüC]'�n hex kodlar�:
utf8Bytelar[0] = 0x41
utf8Bytelar[1] = 0xc3
utf8Bytelar[2] = 0xaa
utf8Bytelar[3] = 0xc3
utf8Bytelar[4] = 0xb1
utf8Bytelar[5] = 0xc3
utf8Bytelar[6] = 0xbc
utf8Bytelar[7] = 0x43

**  >java J8e_10 �ar��  ** TEKRAR
Kullan�lan kodlama dosyas�: Cp1254
Orijinal dizgemiz = �ar��

Varsay�l� byte'lar [�ar��]'�n hex kodlar�:
varsay�l�Bytelar[0] = 0xc7
varsay�l�Bytelar[1] = 0x61
varsay�l�Bytelar[2] = 0x72
varsay�l�Bytelar[3] = 0xfe
varsay�l�Bytelar[4] = 0xfd

UTF-8 byte'lar [�?ar�?ı]'�n hex kodlar�:
utf8Bytelar[0] = 0xc3
utf8Bytelar[1] = 0x87
utf8Bytelar[2] = 0x61
utf8Bytelar[3] = 0x72
utf8Bytelar[4] = 0xc5
utf8Bytelar[5] = 0x9f
utf8Bytelar[6] = 0xc4
utf8Bytelar[7] = 0xb1

**  >java J8e_10 "G�l Bal�"  ** TEKRAR
Kullan�lan kodlama dosyas�: Cp1254
Orijinal dizgemiz = G�l Bal�

Varsay�l� byte'lar [G�l Bal�]'�n hex kodlar�:
varsay�l�Bytelar[0] = 0x47
varsay�l�Bytelar[1] = 0xfc
varsay�l�Bytelar[2] = 0x6c
varsay�l�Bytelar[3] = 0x20
varsay�l�Bytelar[4] = 0x42
varsay�l�Bytelar[5] = 0x61
varsay�l�Bytelar[6] = 0x6c
varsay�l�Bytelar[7] = 0xfd

UTF-8 byte'lar [Gül Balı]'�n hex kodlar�:
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