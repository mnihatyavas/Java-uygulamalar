// J6c_2.java: BulkDataOperationsExamples (Y���l�Veri��lemleri�rnekleri) �rne�i.

import java.util.List;

// Gereken dosya: J6c_1x2.java=Person/Ki�i.java
public class J6c_2 {
    public static void main (String... arg�manlar) {
        // Verili numune ki�i listesini yaratal�m...
        List<J6c_1x2> ki�ilerListesi = J6c_1x2.listeyiYarat();

        // 1. Listedeki �ye isimlerini for-each d�ng�s�yle altalta d�kelim...
        System.out.println ("1. Ki�i adlar�n�n for-each d�ng�s�yle altalta d�k�m�==>");
        for (J6c_1x2 ki�i : ki�ilerListesi) System.out.println (ki�i.ismiAl());

        // 2. Listedeki �ye isimlerini JDK-8 stream().for-each d�ng�s�yle altalta d�kelim...
        System.out.println ("\n2. Ki�i adlar�n�n JDK-8 stream().for-each d�ng�s�yle altalta d�k�m�==>");
        ki�ilerListesi.stream().forEach (ki�i -> System.out.println (ki�i.ismiAl()));

        // 3. Listedeki erkek �ye isimlerini JDK-8 stream().filter(..).for-each d�ng�s�yle altalta d�kelim...
        System.out.println ("\n3. Erkek ki�i adlar�n�n JDK-8 stream().filter(..).for-each d�ng�s�yle altalta d�k�m�==>");
        ki�ilerListesi.stream().filter (ki�i -> ki�i.cinsiAl() == J6c_1x2.Cinsiyet.ERKEK).forEach (ki�i->System.out.println (ki�i.ismiAl()));

        // 4. Listedeki bayan �ye isimlerini if'li for-each d�ng�s�yle altalta d�kelim...
        System.out.println ("\n4. Bayan ki�i adlar�n�n if'li for-each d�ng�s�yle altalta d�k�m�==>");
        for (J6c_1x2 ki�i : ki�ilerListesi)
            if (ki�i.cinsiAl() == J6c_1x2.Cinsiyet.KADIN) System.out.println (ki�i.ismiAl());

        // 5. Listedeki erkek �ye ya� ortalamas�n� JDK-8 stream().filter(..).mapToInt(Lamda).average().getAsDouble() elemesiyle g�sterelim...
        double ortalama = ki�ilerListesi
                .stream()
                .filter (ki�i -> ki�i.cinsiAl() == J6c_1x2.Cinsiyet.ERKEK)
                .mapToInt (J6c_1x2 :: ya��Al)
                .average()
                .getAsDouble();
        System.out.format ("\n5. Erkek �ye ya� ortalamas�n�n JDK-8 stream().filter(..).mapToInt(Lamda).average().getAsDouble() elemesiyle bulunmas�: %.2f%n", ortalama);
    } // main(..) metodu sonu...
} // J6c_2 s�n�f� sonu...

/* ��kt�:
**  >java J6c_2  **
1. Ki�i adlar�n�n for-each d�ng�s�yle altalta d�k�m�==>
Fred
Hilal
Jane
George
Bob
Nihat
Metin
Belk�s
Ersin

2. Ki�i adlar�n�n JDK-8 stream().for-each d�ng�s�yle altalta d�k�m�==>
Fred
Hilal
Jane
George
Bob
Nihat
Metin
Belk�s
Ersin

3. Erkek ki�i adlar�n�n JDK-8 stream().filter(..).for-each d�ng�s�yle altalta d�k�m�==>
Fred
George
Bob
Nihat
Metin
Ersin

4. Bayan ki�i adlar�n�n if'li for-each d�ng�s�yle altalta d�k�m�==>
Hilal
Jane
Belk�s

5. Erkek �ye ya� ortalamas�n�n JDK-8 stream().filter(..).mapToInt(Lamda).average().getAsDouble() elemesiyle bulunmas�: 30,83
*/