// J3b_13b.java: ObjectStreams (NesneAkýþlarý) örneði.

import java.io.FileInputStream; // int/byte okur...
import java.io.FileOutputStream; // int/byte yazar...

import java.io.BufferedInputStream; // String satýr okur...
import java.io.BufferedOutputStream; // String satýr yazar...

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
    static final String[] açýklama = {
        "Java T-þörtü",
        "Java Bardak Kupasý",
        "Dük Palyaço Bebeði",
        "Java Toplu Ýðnesi",
        "Java Anahtar Zinciri",
        "Java Amblemli Kep"};

    public static void main (String[] args) throws IOException, ClassNotFoundException {
        String faturaDosyasý = args.length > 0? args[0] : "fatura";
 
        ObjectOutputStream yaz = null;
        yaz = new ObjectOutputStream (new BufferedOutputStream (new FileOutputStream (faturaDosyasý)));
        yaz.writeObject (Calendar.getInstance());
        for (int i = 0; i < fiyat.length; i ++) {
            yaz.writeObject (fiyat[i]);
            yaz.writeInt (adet[i]);
            yaz.writeUTF (açýklama[i]);
        } // for döngüsü sonu...
        yaz.close();

        ObjectInputStream oku = null;
        oku = new ObjectInputStream (new BufferedInputStream (new FileInputStream (faturaDosyasý)));
        Calendar tarih = null;
        BigDecimal fyt;
        int adt;
        String malÝsmi;
        BigDecimal toplam = new BigDecimal (0);

        tarih = (Calendar)oku.readObject();
        System.out.format ("[%tA, %<tB %<te, %<tY; %<tH:%<tm] tarihli müþteri faturasý:%n%n", tarih);
        try {
            while (true) {
                fyt = (BigDecimal)oku.readObject();
                adt = oku.readInt();
                malÝsmi = oku.readUTF();
                System.out.format ("[%d] adet [%s], herbiri [%.2f TL]'den sipariþ verdiniz.%n", adt, malÝsmi, fyt);
                toplam = toplam.add (fyt.multiply (new BigDecimal (adt)));
            } // while döngüsü sonu...
        }catch (EOFException ist) {}
        oku.close();

        System.out.format ("==>Toplam fatura tutarýnýz: [%.2f TL]'dýr.==>Tekrar bekleriz!..%n", toplam);
        System.out.println ("\nFatura dosyasý [" + faturaDosyasý + "] sisteme kaydedilmiþtir.");
    } // main(..) metodu sonu...
} // J3b_13b sýnýfý sonu...

/* Çýktý:
**  >java J3b_13b  **
[Pazartesi, Mayýs 14, 2018; 01:05] tarihli müþteri faturasý:

[12] adet [Java T-þörtü], herbiri [19,99 TL]'den sipariþ verdiniz.
[8] adet [Java Bardak Kupasý], herbiri [9,99 TL]'den sipariþ verdiniz.
[13] adet [Dük Palyaço Bebeði], herbiri [15,99 TL]'den sipariþ verdiniz.
[29] adet [Java Toplu Ýðnesi], herbiri [3,99 TL]'den sipariþ verdiniz.
[50] adet [Java Anahtar Zinciri], herbiri [4,99 TL]'den sipariþ verdiniz.
[12] adet [Java Amblemli Kep], herbiri [7,45 TL]'den sipariþ verdiniz.
==>Toplam fatura tutarýnýz: [982,28 TL]'dýr.==>Tekrar bekleriz!..

Fatura dosyasý [fatura] sisteme kaydedilmiþtir.
*/