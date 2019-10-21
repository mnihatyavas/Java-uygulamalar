// J6c_1.java: ReductionExamples (�ndirgeme�rnekleri) �rne�i.

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/* Gereken dosyalar:
 *    J6c_1x1.java= Averager/Ortalamac�.java
 *    J6c_1x2.java=Person/Ki�i.java
 */
public class J6c_1 {
    public static void main (String... arg�manlar) {
        // 1. Ki�iler listesini yarat�p JDK-8 stream forEach'le d�kelim...
        List<J6c_1x2> liste = J6c_1x2.listeyiYarat();
        System.out.println ("Ki�iler listesinin stream forEach'le altalta d�k�m�==>");
        liste.stream().forEach (ki�i -> ki�i.ki�iyiYaz());

        // 2. Erkek �yelerin ortalama ya�� stream average ile...
        double ortalamaYa� = liste
                .stream()
                .filter (k -> k.cinsiAl() == J6c_1x2.Cinsiyet.ERKEK)
                .mapToInt (J6c_1x2 :: ya��Al)
                .average()
                .getAsDouble();
        System.out.format ("%nErkek �yelerin ya� ortalamas� .stream().average() ile: %.2f%n", ortalamaYa�);

        // 3. Kad�n �yelerin ortalama ya�� collect Ortalay�c�/J6c_1x1 metodlar�yla...
        J6c_1x1 ortYa� = liste
                .stream()
                .filter (k -> k.cinsiAl() == J6c_1x2.Cinsiyet.KADIN)
                .map (J6c_1x2 :: ya��Al)
                .collect (J6c_1x1::new, J6c_1x1::accept, J6c_1x1::birle�tir);
        System.out.format ("Kad�n �yelerin ya� ortalamas� .collect (kullan�c� metodlar�) ile: %.2f%n", ortYa�.ortala());

        // 4. T�m liste �yelerinin toplam ya��, steam sum ile...
        Integer toplamYa� = liste
                .stream()
                .mapToInt (J6c_1x2 :: ya��Al)
                .sum();
        System.out.println ("T�m liste �yelerinin toplam ya�� .stream().sum() ile: " + toplamYa�);

        // 5. T�m liste �yelerinin toplam ya��, stream reduce ile...
        Integer ya�Toplam� = liste
                .stream()
                .map(J6c_1x2 :: ya��Al)
                .reduce (0, (a, b) -> a + b);
        System.out.println ("T�m liste �yelerinin toplam ya�� .stream().reduce(kimlik,toplay�c�) ile: " + ya�Toplam�);

        // 6. Erkek �yelerin altalta isim listesi, stream collect ile...
        System.out.println ("\nErkek �yelerin altalta isimleri .stream().collect(..) ve forEach ile==>");
        List<String> erkekAdListesi = liste
                .stream()
                .filter (k -> k.cinsiAl() == J6c_1x2.Cinsiyet.ERKEK)
                .map (k -> k.ismiAl())
                .collect (Collectors.toList());
        erkekAdListesi.stream().forEach (k -> System.out.println (k));

        // 7. �ye adlar�n� cinsiyete g�re gruplay�p d�kelim...
        System.out.println ("\n�ye adlar�n�n cinsiyetle grupland�r�l�p altalta d�k�m�==>");
        Map<J6c_1x2.Cinsiyet, List<J6c_1x2>> cinsiyetGrubuHaritas� =
                liste
                    .stream()
                    .collect (Collectors.groupingBy (J6c_1x2 :: cinsiAl));
        List<Map.Entry<J6c_1x2.Cinsiyet, List<J6c_1x2>>> cinsiyetGrubuListesi =
                new ArrayList<> (cinsiyetGrubuHaritas�.entrySet());
        cinsiyetGrubuListesi
                .stream()
                .forEach (k -> {
                    System.out.println ("Cinsiyet: " + k.getKey());
                    k.getValue()
                        .stream()
                        .map (J6c_1x2 :: ismiAl)
                        .forEach (a -> System.out.println (a));});

        // 8. Farkl� bir yakla��mla tekrar �ye adlar�n� cinsiyete g�re gruplay�p d�kelim...
        System.out.println ("\n�ye adlar�n�n cinsiyetle grupland�r�l�p altalta d�k�m�==>");
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

        // 9. Herbir cinsin toplam ya�� collect, sum ve forEach ile...
        System.out.println ("\nCinsiyet gruplar�n�n toplam ya�lar�==>");
        Map<J6c_1x2.Cinsiyet, Integer> cinsiyetGrubuToplamYa�Haritas� =
            liste
                .stream()
                .collect (Collectors.groupingBy (J6c_1x2 :: cinsiAl,                      
                        Collectors.reducing (0, J6c_1x2 :: ya��Al, Integer::sum)));
        List<Map.Entry<J6c_1x2.Cinsiyet, Integer>> cinsiyetGrubuToplamYa�Listesi =
            new ArrayList<> (cinsiyetGrubuToplamYa�Haritas�.entrySet());
        cinsiyetGrubuToplamYa�Listesi
            .stream()
            .forEach (k -> System.out.println ("Cinsiyet: " + k.getKey() +", Toplam ya�: " + k.getValue()));

        // 10. Herbir cinsin ortalama ya�� collect ve average ile...
        System.out.println ("\nCinsiyet gruplar�n�n ortalama ya�lar�==>");
        Map<J6c_1x2.Cinsiyet, Double> cinsiyetGrubuOrtalamaYa�Haritas� =
            liste
                .stream()
                .collect (Collectors.groupingBy (J6c_1x2 :: cinsiAl, Collectors.averagingInt (J6c_1x2::ya��Al)));
        for (Map.Entry<J6c_1x2.Cinsiyet, Double> k : cinsiyetGrubuOrtalamaYa�Haritas�.entrySet())
            System.out.format ("%s: %.2f%n", k.getKey(), k.getValue());
    } // main(..) metodu sonu...
} // J6c_1 s�n�f� sonu...

/* ��kt�:
**  >java J6c_1  **
Ki�iler listesinin stream forEach'le altalta d�k�m�==>
Fred, 38, ERKEK, fred@gmail.com
Hilal, 32, KADIN, hilal@hotmail.com
Jane, 28, KADIN, jane@gmail.com
George, 27, ERKEK, george@gmail.com
Bob, 18, ERKEK, bob@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com
Metin, 20, ERKEK, metin@hotmail.com
Belk�s, 34, KADIN, belkis@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

Erkek �yelerin ya� ortalamas� .stream().average() ile: 30,83
Kad�n �yelerin ya� ortalamas� .collect (kullan�c� metodlar�) ile: 31,33
T�m liste �yelerinin toplam ya�� .stream().sum() ile: 279
T�m liste �yelerinin toplam ya�� .stream().reduce(kimlik,toplay�c�) ile: 279

Erkek �yelerin altalta isimleri .stream().collect(..) ve forEach ile==>
Fred
George
Bob
Nihat
Metin
Ersin

�ye adlar�n�n cinsiyetle grupland�r�l�p altalta d�k�m�==>
Cinsiyet: KADIN
Hilal
Jane
Belk�s
Cinsiyet: ERKEK
Fred
George
Bob
Nihat
Metin
Ersin

�ye adlar�n�n cinsiyetle grupland�r�l�p altalta d�k�m�==>
Cinsiyet: KADIN
Hilal
Jane
Belk�s
Cinsiyet: ERKEK
Fred
George
Bob
Nihat
Metin
Ersin

Cinsiyet gruplar�n�n toplam ya�lar�==>
Cinsiyet: KADIN, Toplam ya�: 94
Cinsiyet: ERKEK, Toplam ya�: 185

Cinsiyet gruplar�n�n ortalama ya�lar�==>
KADIN: 31,33
ERKEK: 30,83
*/