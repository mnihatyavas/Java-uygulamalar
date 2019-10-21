// J3b_13b.java: ObjectStreams (NesneAk��lar�) �rne�i.

import java.io.FileInputStream; // int/byte okur...
import java.io.FileOutputStream; // int/byte yazar...

import java.io.BufferedInputStream; // String sat�r okur...
import java.io.BufferedOutputStream; // String sat�r yazar...

import java.io.ObjectInputStream; // Nesnel tip okur...
import java.io.ObjectOutputStream; // Nesnel tip yazar...

import java.io.IOException;
import java.io.EOFException;

import java.math.BigDecimal;
import java.util.Calendar;

public class J3b_13b {
    static final BigDecimal[] fiyat = {
        new BigDecimal ("19.99"), 
        new BigDecimal ("9.99"),
        new BigDecimal ("15.99"),
        new BigDecimal ("3.99"),
        new BigDecimal ("4.99"),
        new BigDecimal ("7.45")};
    static final int[] adet = {12, 8, 13, 29, 50, 12};
    static final String[] a��klama = {
        "Java T-��rt�",
        "Java Bardak Kupas�",
        "D�k Palya�o Bebe�i",
        "Java Toplu ��nesi",
        "Java Anahtar Zinciri",
        "Java Amblemli Kep"};

    public static void main (String[] args) throws IOException, ClassNotFoundException {
        String faturaDosyas� = args.length > 0? args[0] : "fatura";
 
        ObjectOutputStream yaz = null;
        yaz = new ObjectOutputStream (new BufferedOutputStream (new FileOutputStream (faturaDosyas�)));
        yaz.writeObject (Calendar.getInstance());
        for (int i = 0; i < fiyat.length; i ++) {
            yaz.writeObject (fiyat[i]);
            yaz.writeInt (adet[i]);
            yaz.writeUTF (a��klama[i]);
        } // for d�ng�s� sonu...
        yaz.close();

        ObjectInputStream oku = null;
        oku = new ObjectInputStream (new BufferedInputStream (new FileInputStream (faturaDosyas�)));
        Calendar tarih = null;
        BigDecimal fyt;
        int adt;
        String mal�smi;
        BigDecimal toplam = new BigDecimal (0);

        tarih = (Calendar)oku.readObject();
        System.out.format ("[%tA, %<tB %<te, %<tY; %<tH:%<tm] tarihli m��teri faturas�:%n%n", tarih);
        try {
            while (true) {
                fyt = (BigDecimal)oku.readObject();
                adt = oku.readInt();
                mal�smi = oku.readUTF();
                System.out.format ("[%d] adet [%s], herbiri [%.2f TL]'den sipari� verdiniz.%n", adt, mal�smi, fyt);
                toplam = toplam.add (fyt.multiply (new BigDecimal (adt)));
            } // while d�ng�s� sonu...
        }catch (EOFException ist) {}
        oku.close();

        System.out.format ("==>Toplam fatura tutar�n�z: [%.2f TL]'d�r.==>Tekrar bekleriz!..%n", toplam);
        System.out.println ("\nFatura dosyas� [" + faturaDosyas� + "] sisteme kaydedilmi�tir.");
    } // main(..) metodu sonu...
} // J3b_13b s�n�f� sonu...

/* ��kt�:
**  >java J3b_13b  **
[Pazartesi, May�s 14, 2018; 01:05] tarihli m��teri faturas�:

[12] adet [Java T-��rt�], herbiri [19,99 TL]'den sipari� verdiniz.
[8] adet [Java Bardak Kupas�], herbiri [9,99 TL]'den sipari� verdiniz.
[13] adet [D�k Palya�o Bebe�i], herbiri [15,99 TL]'den sipari� verdiniz.
[29] adet [Java Toplu ��nesi], herbiri [3,99 TL]'den sipari� verdiniz.
[50] adet [Java Anahtar Zinciri], herbiri [4,99 TL]'den sipari� verdiniz.
[12] adet [Java Amblemli Kep], herbiri [7,45 TL]'den sipari� verdiniz.
==>Toplam fatura tutar�n�z: [982,28 TL]'d�r.==>Tekrar bekleriz!..

Fatura dosyas� [fatura] sisteme kaydedilmi�tir.
*/