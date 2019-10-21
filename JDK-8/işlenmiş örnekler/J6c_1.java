// J6c_1.java: ReductionExamples (ÝndirgemeÖrnekleri) örneði.

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/* Gereken dosyalar:
 *    J6c_1x1.java= Averager/Ortalamacý.java
 *    J6c_1x2.java=Person/Kiþi.java
 */
public class J6c_1 {
    public static void main (String... argümanlar) {
        // 1. Kiþiler listesini yaratýp JDK-8 stream forEach'le dökelim...
        List<J6c_1x2> liste = J6c_1x2.listeyiYarat();
        System.out.println ("Kiþiler listesinin stream forEach'le altalta dökümü==>");
        liste.stream().forEach (kiþi -> kiþi.kiþiyiYaz());

        // 2. Erkek üyelerin ortalama yaþý stream average ile...
        double ortalamaYaþ = liste
                .stream()
                .filter (k -> k.cinsiAl() == J6c_1x2.Cinsiyet.ERKEK)
                .mapToInt (J6c_1x2 :: yaþýAl)
                .average()
                .getAsDouble();
        System.out.format ("%nErkek üyelerin yaþ ortalamasý .stream().average() ile: %.2f%n", ortalamaYaþ);

        // 3. Kadýn üyelerin ortalama yaþý collect Ortalayýcý/J6c_1x1 metodlarýyla...
        J6c_1x1 ortYaþ = liste
                .stream()
                .filter (k -> k.cinsiAl() == J6c_1x2.Cinsiyet.KADIN)
                .map (J6c_1x2 :: yaþýAl)
                .collect (J6c_1x1::new, J6c_1x1::accept, J6c_1x1::birleþtir);
        System.out.format ("Kadýn üyelerin yaþ ortalamasý .collect (kullanýcý metodlarý) ile: %.2f%n", ortYaþ.ortala());

        // 4. Tüm liste üyelerinin toplam yaþý, steam sum ile...
        Integer toplamYaþ = liste
                .stream()
                .mapToInt (J6c_1x2 :: yaþýAl)
                .sum();
        System.out.println ("Tüm liste üyelerinin toplam yaþý .stream().sum() ile: " + toplamYaþ);

        // 5. Tüm liste üyelerinin toplam yaþý, stream reduce ile...
        Integer yaþToplamý = liste
                .stream()
                .map(J6c_1x2 :: yaþýAl)
                .reduce (0, (a, b) -> a + b);
        System.out.println ("Tüm liste üyelerinin toplam yaþý .stream().reduce(kimlik,toplayýcý) ile: " + yaþToplamý);

        // 6. Erkek üyelerin altalta isim listesi, stream collect ile...
        System.out.println ("\nErkek üyelerin altalta isimleri .stream().collect(..) ve forEach ile==>");
        List<String> erkekAdListesi = liste
                .stream()
                .filter (k -> k.cinsiAl() == J6c_1x2.Cinsiyet.ERKEK)
                .map (k -> k.ismiAl())
                .collect (Collectors.toList());
        erkekAdListesi.stream().forEach (k -> System.out.println (k));

        // 7. Üye adlarýný cinsiyete göre gruplayýp dökelim...
        System.out.println ("\nÜye adlarýnýn cinsiyetle gruplandýrýlýp altalta dökümü==>");
        Map<J6c_1x2.Cinsiyet, List<J6c_1x2>> cinsiyetGrubuHaritasý =
                liste
                    .stream()
                    .collect (Collectors.groupingBy (J6c_1x2 :: cinsiAl));
        List<Map.Entry<J6c_1x2.Cinsiyet, List<J6c_1x2>>> cinsiyetGrubuListesi =
                new ArrayList<> (cinsiyetGrubuHaritasý.entrySet());
        cinsiyetGrubuListesi
                .stream()
                .forEach (k -> {
                    System.out.println ("Cinsiyet: " + k.getKey());
                    k.getValue()
                        .stream()
                        .map (J6c_1x2 :: ismiAl)
                        .forEach (a -> System.out.println (a));});

        // 8. Farklý bir yaklaþýmla tekrar üye adlarýný cinsiyete göre gruplayýp dökelim...
        System.out.println ("\nÜye adlarýnýn cinsiyetle gruplandýrýlýp altalta dökümü==>");
        Map<J6c_1x2.Cinsiyet, List<String>> cinsiyetGrubuHarita =
            liste
                .stream()
                .collect (Collectors.groupingBy (J6c_1x2 :: cinsiAl,                      
                        Collectors.mapping (J6c_1x2 :: ismiAl, Collectors.toList())));
        List<Map.Entry<J6c_1x2.Cinsiyet, List<String>>> cinsiyetGrubuListe =
                new ArrayList<> (cinsiyetGrubuHarita.entrySet());
        cinsiyetGrubuListe
                .stream()
                .forEach (k -> {
                    System.out.println ("Cinsiyet: " + k.getKey());
                    k.getValue().stream().forEach (a -> System.out.println (a));});

        // 9. Herbir cinsin toplam yaþý collect, sum ve forEach ile...
        System.out.println ("\nCinsiyet gruplarýnýn toplam yaþlarý==>");
        Map<J6c_1x2.Cinsiyet, Integer> cinsiyetGrubuToplamYaþHaritasý =
            liste
                .stream()
                .collect (Collectors.groupingBy (J6c_1x2 :: cinsiAl,                      
                        Collectors.reducing (0, J6c_1x2 :: yaþýAl, Integer::sum)));
        List<Map.Entry<J6c_1x2.Cinsiyet, Integer>> cinsiyetGrubuToplamYaþListesi =
            new ArrayList<> (cinsiyetGrubuToplamYaþHaritasý.entrySet());
        cinsiyetGrubuToplamYaþListesi
            .stream()
            .forEach (k -> System.out.println ("Cinsiyet: " + k.getKey() +", Toplam yaþ: " + k.getValue()));

        // 10. Herbir cinsin ortalama yaþý collect ve average ile...
        System.out.println ("\nCinsiyet gruplarýnýn ortalama yaþlarý==>");
        Map<J6c_1x2.Cinsiyet, Double> cinsiyetGrubuOrtalamaYaþHaritasý =
            liste
                .stream()
                .collect (Collectors.groupingBy (J6c_1x2 :: cinsiAl, Collectors.averagingInt (J6c_1x2::yaþýAl)));
        for (Map.Entry<J6c_1x2.Cinsiyet, Double> k : cinsiyetGrubuOrtalamaYaþHaritasý.entrySet())
            System.out.format ("%s: %.2f%n", k.getKey(), k.getValue());
    } // main(..) metodu sonu...
} // J6c_1 sýnýfý sonu...

/* Çýktý:
**  >java J6c_1  **
Kiþiler listesinin stream forEach'le altalta dökümü==>
Fred, 38, ERKEK, fred@gmail.com
Hilal, 32, KADIN, hilal@hotmail.com
Jane, 28, KADIN, jane@gmail.com
George, 27, ERKEK, george@gmail.com
Bob, 18, ERKEK, bob@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com
Metin, 20, ERKEK, metin@hotmail.com
Belkýs, 34, KADIN, belkis@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

Erkek üyelerin yaþ ortalamasý .stream().average() ile: 30,83
Kadýn üyelerin yaþ ortalamasý .collect (kullanýcý metodlarý) ile: 31,33
Tüm liste üyelerinin toplam yaþý .stream().sum() ile: 279
Tüm liste üyelerinin toplam yaþý .stream().reduce(kimlik,toplayýcý) ile: 279

Erkek üyelerin altalta isimleri .stream().collect(..) ve forEach ile==>
Fred
George
Bob
Nihat
Metin
Ersin

Üye adlarýnýn cinsiyetle gruplandýrýlýp altalta dökümü==>
Cinsiyet: KADIN
Hilal
Jane
Belkýs
Cinsiyet: ERKEK
Fred
George
Bob
Nihat
Metin
Ersin

Üye adlarýnýn cinsiyetle gruplandýrýlýp altalta dökümü==>
Cinsiyet: KADIN
Hilal
Jane
Belkýs
Cinsiyet: ERKEK
Fred
George
Bob
Nihat
Metin
Ersin

Cinsiyet gruplarýnýn toplam yaþlarý==>
Cinsiyet: KADIN, Toplam yaþ: 94
Cinsiyet: ERKEK, Toplam yaþ: 185

Cinsiyet gruplarýnýn ortalama yaþlarý==>
KADIN: 31,33
ERKEK: 30,83
*/