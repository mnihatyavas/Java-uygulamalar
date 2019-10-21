// J6c_3.java: ParallelismExamples (ParalellikÖrnekleri) örneði.

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.concurrent.ConcurrentMap;

// Gerekli dosya: J6c_1x2.java (Person/Kiþi)
public class J6c_3 {
    public static void main (String... argümanlar) {
        // 1. <J6c_1x2/Person/Kiþi> veri tipli listemizi yaratýp,  bilgilerini dökelim...
        List<J6c_1x2> liste = J6c_1x2.listeyiYarat();
        System.out.println ("Toplam " + liste.size() + " kiþilik listenin JDK-8 stream().forEach(..) ile altalta dökümü==>");
        liste.stream().forEach (kiþi -> kiþi.kiþiyiYaz());
        System.out.println();

        // 2. stream() deðil paralelStream()'le erkek üyelerin ortalama yaþlarýnýn bulunmasý...
        double ortalama = liste
                .parallelStream()
                .filter (k -> k.cinsiAl() == J6c_1x2.Cinsiyet.ERKEK)
                .mapToInt (J6c_1x2 :: yaþýAl) // Lamda ile yaþýAl() metodu yaþýAl yalýn yazýlmakta...
                .average()
                .getAsDouble();
        System.out.format ("Paralel listedeki erkek üyelerin ortalama yaþý: %.2f%n", ortalama);

        // stream() deðil paralelStream()'le diþi üyelerin ortalama yaþlarýnýn bulunmasý...
        ortalama = liste
                .stream()
                .filter (k -> k.cinsiAl() == J6c_1x2.Cinsiyet.KADIN)
                .mapToInt (J6c_1x2:: yaþýAl) // Lamda ile yaþýAl() metodu yaþýAl yalýn yazýlmakta...
                .average()
                .getAsDouble();
        System.out.format ("Listedeki kadýn üyelerin ortalama yaþý: %.2f%n", ortalama);

        // 3. Aynýanda listeyi KADIN ve ERKEK üyeler olarak gruplama örneði...
        ConcurrentMap<J6c_1x2.Cinsiyet, List<J6c_1x2>> cinsiyetleParalelGruplama =
                liste.parallelStream().collect (Collectors.groupingByConcurrent (J6c_1x2 :: cinsiAl));
        List<Map.Entry<J6c_1x2.Cinsiyet, List<J6c_1x2>>> cinsiyetleGruplananListe = 
                new ArrayList<> (cinsiyetleParalelGruplama.entrySet());
        System.out.println ("\nCinsiyete göre gruplanan üyelerin altalta adlarý==>");
        cinsiyetleGruplananListe
                .stream()
                .forEach (k -> {
                    System.out.println ("Cinsiyet: " + k.getKey());
                    k.getValue()
                            .stream()
                            .map (J6c_1x2 :: ismiAl)
                            .forEach (ad -> System.out.println (ad));});

        // 4. Sýralama ve paralellik örnekleri...
        System.out.println ("\nSýralama ve paralellik örnekleri:");
        Integer[] tamsayýDizi = {9, 1, 8, 3, 7, 5, 6, 0, 4, 2};
        List<Integer> tamsayýlarListesi = new ArrayList<> (Arrays.asList (tamsayýDizi));
        System.out.println ("Karýþýk tamsayýlar listesi yanyana==>");
        tamsayýlarListesi.stream().forEach (rakam -> System.out.print (rakam + " "));
        System.out.println();

        System.out.println ("\nTers sýralý tamsayýlar listesi yanyana==>");
        Comparator<Integer> normal = Integer :: compare;
        Comparator<Integer> ters = normal.reversed(); 
        Collections.sort (tamsayýlarListesi, ters);             
        tamsayýlarListesi.stream().forEach (r -> System.out.print (r + " "));
        System.out.println();

        System.out.println ("\nParalel akýþtaki tamsayýlar listesi yanyana==>");
        tamsayýlarListesi.parallelStream().forEach (r -> System.out.print (r + " "));
        System.out.println();

        System.out.println ("\nDiðer bir paralel akýþ listesi daha (yanyana)==>");
        tamsayýlarListesi.parallelStream().forEach (r -> System.out.print (r + " "));
        System.out.println();

        System.out.println ("\nforEachOrdered ile paralel akýþ listesi yanyana==>");
        tamsayýlarListesi.parallelStream().forEachOrdered (r -> System.out.print (r + " "));
        System.out.println();

        // 5a. Engelleme örneði...
        try {List<String> dizgelerListesi = new ArrayList<> (Arrays.asList ("bir", "iki", "üç", "dört", "beþ", "altý", "yedi"));
            // .peek(..) iþlemi yeni bir dizgeyi sonlandýrma baþlatýldýktan sonra
            // eklemeye çalýþýrken hata oluþturacaktýr...
            String eklemeliDizge = dizgelerListesi
                    .stream()
                    // Karýþma burada oluþur, ve catch hata yakalar!..
                    .peek (eleman -> dizgelerListesi.add ("sekiz"))
                    .reduce ((a, b) -> a + " " + b)
                    .get();
            System.out.println ("Eklemeli dizge listesi yanyana: " + eklemeliDizge);
        }catch (Exception ist) {System.out.println ("\nYakalanan istisna (Eklemeli dizge listesi hatasý): " + ist.toString());}

        // 5b. Ayný örneði hatasýz dökelim...
        try {List<String> dizgelerListesi = new ArrayList<> (Arrays.asList ("bir", "iki", "üç", "dört", "beþ", "altý", "yedi"));
            String eklemesizDizge = dizgelerListesi
                    .stream()
                    .reduce ((a, b) -> a + " " + b)
                    .get();
            System.out.println ("Eklemesiz dizge listesi yanyana: " + eklemesizDizge);
        }catch (Exception ist) {System.out.println ("Yakalanan istisna: " + ist.toString());}

        // 6a. Seri akýþlý tamsayýlarýn lambdalý dökümü...
        List<Integer> seriDepolama = new ArrayList<>();
        System.out.println ("\nSeri akýþlý tamsayýlar listesi yanyana==>");
        tamsayýlarListesi
                .stream()
                // Bu iþlem þüpheli lambda ifadesi kullanýmýdýr!..
                .map (r -> {seriDepolama.add (r); return r;})
                .forEachOrdered (r -> System.out.print (r + " "));
        System.out.println();
        seriDepolama.stream().forEachOrdered (r -> System.out.print (r + " "));
        System.out.println("");

        // 6b. Paralel akýþlý tamsayýlarýn þüpheli lambdalý dökümü...
        System.out.println ("\nParalel akýþlý tamsayýlar listesi yanyana==>");
        List<Integer> paralelDepolama = Collections.synchronizedList (new ArrayList<>());
        tamsayýlarListesi
                .parallelStream()
                // Bu iþlem þüpheli lambda ifadesi kullanýmýdýr!..
                .map (r -> {paralelDepolama.add (r); return r;})
                .forEachOrdered (r -> System.out.print (r + " "));
        System.out.println();
        paralelDepolama.stream().forEachOrdered (r -> System.out.print (r + " "));
        System.out.println();
    } // main(..) metodu sonu...
} // J6c_3 sýnýfý sonu...

/* Çýktý:
**  >java J6c_3  **
Toplam 9 kiþilik listenin JDK-8 stream().forEach(..) ile altalta dökümü==>
Fred, 38, ERKEK, fred@gmail.com
Hilal, 32, KADIN, hilal@hotmail.com
Jane, 28, KADIN, jane@gmail.com
George, 27, ERKEK, george@gmail.com
Bob, 18, ERKEK, bob@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com
Metin, 20, ERKEK, metin@hotmail.com
Belkýs, 34, KADIN, belkis@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

Paralel listedeki erkek üyelerin ortalama yaþý: 30,83
Listedeki kadýn üyelerin ortalama yaþý: 31,33

Cinsiyete göre gruplanan üyelerin altalta adlarý==>
Cinsiyet: ERKEK
Bob
Nihat
Ersin
Metin
George
Fred
Cinsiyet: KADIN
Belkýs
Jane
Hilal

Sýralama ve paralellik örnekleri:
Karýþýk tamsayýlar listesi yanyana==>
9 1 8 3 7 5 6 0 4 2

Ters sýralý tamsayýlar listesi yanyana==>
9 8 7 6 5 4 3 2 1 0

Paralel akýþtaki tamsayýlar listesi yanyana==>
4 3 1 0 2 9 8 6 5 7

Diðer bir paralel akýþ listesi daha (yanyana)==>
4 3 1 0 2 9 8 6 5 7

forEachOrdered ile paralel akýþ listesi yanyana==>
9 8 7 6 5 4 3 2 1 0

Yakalanan istisna (Eklemeli dizge listesi hatasý): java.util.ConcurrentModificationException
Eklemesiz dizge listesi yanyana: bir iki üç dört beþ altý yedi

Seri akýþlý tamsayýlar listesi yanyana==>
9 8 7 6 5 4 3 2 1 0
9 8 7 6 5 4 3 2 1 0

Paralel akýþlý tamsayýlar listesi yanyana==>
9 8 7 6 5 4 3 2 1 0
4 3 1 0 2 7 6 5 9 8
*/