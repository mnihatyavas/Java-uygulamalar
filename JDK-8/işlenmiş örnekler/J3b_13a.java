// J3b_13a.java: DataStreams (VeriAkýþlarý) örneði.

import java.io.FileInputStream; // int/byte okur...
import java.io.FileOutputStream; // int/byte yazar...

import java.io.BufferedInputStream; // String satýr okur...
import java.io.BufferedOutputStream; // String satýr yazar...

import java.io.DataInputStream; // Nesnel tip okur...
import java.io.DataOutputStream; // Nesnel tip yazar...

import java.io.IOException;
import java.io.EOFException;

public class J3b_13a {
    static final double[] fiyat = {19.99, 9.99, 15.99, 3.99, 4.99, 7.45};
    static final int[] adet = {12, 8, 13, 29, 50, 12};
    static final String[] açýklama = {
        "Java T-þörtü",
        "Java Bardak Kupasý",
        "Dük Palyaço Bebeði",
        "Java Toplu Ýðnesi",
        "Java Anahtar Zinciri",
        "Java Amblemli Kep"};

    public static void main (String[] args) throws IOException {
        String faturaDosyasý = args.length > 0? args[0] : "fatura";
 
        DataOutputStream yaz = null;
        yaz = new DataOutputStream (new BufferedOutputStream (new FileOutputStream (faturaDosyasý)));
        for (int i = 0; i < fiyat.length; i ++) {
            yaz.writeDouble (fiyat[i]);
            yaz.writeInt (adet[i]);
            yaz.writeUTF (açýklama[i]);
        } // for döngüsü sonu...
        yaz.close();

        DataInputStream oku = null;
        double toplam = 0.0;
        oku = new DataInputStream (new BufferedInputStream (new FileInputStream (faturaDosyasý)));
        double fyt;
        int adt;
        String eþy;

        try {
            while (true) {
                fyt = oku.readDouble();
                adt = oku.readInt();
                eþy = oku.readUTF();
                System.out.format ("[%d] adet [%s] herbiri [%.2f TL]'den sipariþ verdiniz.%n",adt, eþy, fyt);
                toplam += adt * fyt;
            } // while döngüsü sonu...
        }catch (EOFException ist) {}
        oku.close();

        System.out.format ("==>Toplam fatura tutarýnýz: [%.2f TL]'dýr.%n==>Tekrar bekleriz!..%n", toplam);
        System.out.println ("\nFatura dosyasý [" + faturaDosyasý + "] sisteme kaydedilmiþtir.");
    } // main(..) metodu sonu...
} // J3b_13a sýnýfý sonu...

/* Çýktý:
**  >java J3b_13a  **
[12] adet [Java T-þörtü] herbiri [19,99 TL]'den sipariþ verdiniz.
[8] adet [Java Bardak Kupasý] herbiri [9,99 TL]'den sipariþ verdiniz.
[13] adet [Dük Palyaço Bebeði] herbiri [15,99 TL]'den sipariþ verdiniz.
[29] adet [Java Toplu Ýðnesi] herbiri [3,99 TL]'den sipariþ verdiniz.
[50] adet [Java Anahtar Zinciri] herbiri [4,99 TL]'den sipariþ verdiniz.
[12] adet [Java Amblemli Kep] herbiri [7,45 TL]'den sipariþ verdiniz.
==>Toplam fatura tutarýnýz: [982,28 TL]'dýr.
==>Tekrar bekleriz!..

Fatura dosyasý [fatura] sisteme kaydedilmiþtir.

**  >java J3b_13a irsaliye.txt ** TEKRAR
[12] adet [Java T-þörtü] herbiri [19,99 TL]'den sipariþ verdiniz.
[8] adet [Java Bardak Kupasý] herbiri [9,99 TL]'den sipariþ verdiniz.
[13] adet [Dük Palyaço Bebeði] herbiri [15,99 TL]'den sipariþ verdiniz.
[29] adet [Java Toplu Ýðnesi] herbiri [3,99 TL]'den sipariþ verdiniz.
[50] adet [Java Anahtar Zinciri] herbiri [4,99 TL]'den sipariþ verdiniz.
[12] adet [Java Amblemli Kep] herbiri [7,45 TL]'den sipariþ verdiniz.
==>Toplam fatura tutarýnýz: [982,28 TL]'dýr.
==>Tekrar bekleriz!..

Fatura dosyasý [irsaliye.txt] sisteme kaydedilmiþtir.
*/