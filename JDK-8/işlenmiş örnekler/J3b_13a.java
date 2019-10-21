// J3b_13a.java: DataStreams (VeriAk��lar�) �rne�i.

import java.io.FileInputStream; // int/byte okur...
import java.io.FileOutputStream; // int/byte yazar...

import java.io.BufferedInputStream; // String sat�r okur...
import java.io.BufferedOutputStream; // String sat�r yazar...

import java.io.DataInputStream; // Nesnel tip okur...
import java.io.DataOutputStream; // Nesnel tip yazar...

import java.io.IOException;
import java.io.EOFException;

public class J3b_13a {
    static final double[] fiyat = {19.99, 9.99, 15.99, 3.99, 4.99, 7.45};
    static final int[] adet = {12, 8, 13, 29, 50, 12};
    static final String[] a��klama = {
        "Java T-��rt�",
        "Java Bardak Kupas�",
        "D�k Palya�o Bebe�i",
        "Java Toplu ��nesi",
        "Java Anahtar Zinciri",
        "Java Amblemli Kep"};

    public static void main (String[] args) throws IOException {
        String faturaDosyas� = args.length > 0? args[0] : "fatura";
 
        DataOutputStream yaz = null;
        yaz = new DataOutputStream (new BufferedOutputStream (new FileOutputStream (faturaDosyas�)));
        for (int i = 0; i < fiyat.length; i ++) {
            yaz.writeDouble (fiyat[i]);
            yaz.writeInt (adet[i]);
            yaz.writeUTF (a��klama[i]);
        } // for d�ng�s� sonu...
        yaz.close();

        DataInputStream oku = null;
        double toplam = 0.0;
        oku = new DataInputStream (new BufferedInputStream (new FileInputStream (faturaDosyas�)));
        double fyt;
        int adt;
        String e�y;

        try {
            while (true) {
                fyt = oku.readDouble();
                adt = oku.readInt();
                e�y = oku.readUTF();
                System.out.format ("[%d] adet [%s] herbiri [%.2f TL]'den sipari� verdiniz.%n",adt, e�y, fyt);
                toplam += adt * fyt;
            } // while d�ng�s� sonu...
        }catch (EOFException ist) {}
        oku.close();

        System.out.format ("==>Toplam fatura tutar�n�z: [%.2f TL]'d�r.%n==>Tekrar bekleriz!..%n", toplam);
        System.out.println ("\nFatura dosyas� [" + faturaDosyas� + "] sisteme kaydedilmi�tir.");
    } // main(..) metodu sonu...
} // J3b_13a s�n�f� sonu...

/* ��kt�:
**  >java J3b_13a  **
[12] adet [Java T-��rt�] herbiri [19,99 TL]'den sipari� verdiniz.
[8] adet [Java Bardak Kupas�] herbiri [9,99 TL]'den sipari� verdiniz.
[13] adet [D�k Palya�o Bebe�i] herbiri [15,99 TL]'den sipari� verdiniz.
[29] adet [Java Toplu ��nesi] herbiri [3,99 TL]'den sipari� verdiniz.
[50] adet [Java Anahtar Zinciri] herbiri [4,99 TL]'den sipari� verdiniz.
[12] adet [Java Amblemli Kep] herbiri [7,45 TL]'den sipari� verdiniz.
==>Toplam fatura tutar�n�z: [982,28 TL]'d�r.
==>Tekrar bekleriz!..

Fatura dosyas� [fatura] sisteme kaydedilmi�tir.

**  >java J3b_13a irsaliye.txt ** TEKRAR
[12] adet [Java T-��rt�] herbiri [19,99 TL]'den sipari� verdiniz.
[8] adet [Java Bardak Kupas�] herbiri [9,99 TL]'den sipari� verdiniz.
[13] adet [D�k Palya�o Bebe�i] herbiri [15,99 TL]'den sipari� verdiniz.
[29] adet [Java Toplu ��nesi] herbiri [3,99 TL]'den sipari� verdiniz.
[50] adet [Java Anahtar Zinciri] herbiri [4,99 TL]'den sipari� verdiniz.
[12] adet [Java Amblemli Kep] herbiri [7,45 TL]'den sipari� verdiniz.
==>Toplam fatura tutar�n�z: [982,28 TL]'d�r.
==>Tekrar bekleriz!..

Fatura dosyas� [irsaliye.txt] sisteme kaydedilmi�tir.
*/