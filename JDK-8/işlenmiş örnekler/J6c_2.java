// J6c_2.java: BulkDataOperationsExamples (YýðýlýVeriÝþlemleriÖrnekleri) örneði.

import java.util.List;

// Gereken dosya: J6c_1x2.java=Person/Kiþi.java
public class J6c_2 {
    public static void main (String... argümanlar) {
        // Verili numune kiþi listesini yaratalým...
        List<J6c_1x2> kiþilerListesi = J6c_1x2.listeyiYarat();

        // 1. Listedeki üye isimlerini for-each döngüsüyle altalta dökelim...
        System.out.println ("1. Kiþi adlarýnýn for-each döngüsüyle altalta dökümü==>");
        for (J6c_1x2 kiþi : kiþilerListesi) System.out.println (kiþi.ismiAl());

        // 2. Listedeki üye isimlerini JDK-8 stream().for-each döngüsüyle altalta dökelim...
        System.out.println ("\n2. Kiþi adlarýnýn JDK-8 stream().for-each döngüsüyle altalta dökümü==>");
        kiþilerListesi.stream().forEach (kiþi -> System.out.println (kiþi.ismiAl()));

        // 3. Listedeki erkek üye isimlerini JDK-8 stream().filter(..).for-each döngüsüyle altalta dökelim...
        System.out.println ("\n3. Erkek kiþi adlarýnýn JDK-8 stream().filter(..).for-each döngüsüyle altalta dökümü==>");
        kiþilerListesi.stream().filter (kiþi -> kiþi.cinsiAl() == J6c_1x2.Cinsiyet.ERKEK).forEach (kiþi->System.out.println (kiþi.ismiAl()));

        // 4. Listedeki bayan üye isimlerini if'li for-each döngüsüyle altalta dökelim...
        System.out.println ("\n4. Bayan kiþi adlarýnýn if'li for-each döngüsüyle altalta dökümü==>");
        for (J6c_1x2 kiþi : kiþilerListesi)
            if (kiþi.cinsiAl() == J6c_1x2.Cinsiyet.KADIN) System.out.println (kiþi.ismiAl());

        // 5. Listedeki erkek üye yaþ ortalamasýný JDK-8 stream().filter(..).mapToInt(Lamda).average().getAsDouble() elemesiyle gösterelim...
        double ortalama = kiþilerListesi
                .stream()
                .filter (kiþi -> kiþi.cinsiAl() == J6c_1x2.Cinsiyet.ERKEK)
                .mapToInt (J6c_1x2 :: yaþýAl)
                .average()
                .getAsDouble();
        System.out.format ("\n5. Erkek üye yaþ ortalamasýnýn JDK-8 stream().filter(..).mapToInt(Lamda).average().getAsDouble() elemesiyle bulunmasý: %.2f%n", ortalama);
    } // main(..) metodu sonu...
} // J6c_2 sýnýfý sonu...

/* Çýktý:
**  >java J6c_2  **
1. Kiþi adlarýnýn for-each döngüsüyle altalta dökümü==>
Fred
Hilal
Jane
George
Bob
Nihat
Metin
Belkýs
Ersin

2. Kiþi adlarýnýn JDK-8 stream().for-each döngüsüyle altalta dökümü==>
Fred
Hilal
Jane
George
Bob
Nihat
Metin
Belkýs
Ersin

3. Erkek kiþi adlarýnýn JDK-8 stream().filter(..).for-each döngüsüyle altalta dökümü==>
Fred
George
Bob
Nihat
Metin
Ersin

4. Bayan kiþi adlarýnýn if'li for-each döngüsüyle altalta dökümü==>
Hilal
Jane
Belkýs

5. Erkek üye yaþ ortalamasýnýn JDK-8 stream().filter(..).mapToInt(Lamda).average().getAsDouble() elemesiyle bulunmasý: 30,83
*/