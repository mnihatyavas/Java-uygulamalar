// J6c_3.java: ParallelismExamples (Paralellik�rnekleri) �rne�i.

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.concurrent.ConcurrentMap;

// Gerekli dosya: J6c_1x2.java (Person/Ki�i)
public class J6c_3 {
    public static void main (String... arg�manlar) {
        // 1. <J6c_1x2/Person/Ki�i> veri tipli listemizi yarat�p,  bilgilerini d�kelim...
        List<J6c_1x2> liste = J6c_1x2.listeyiYarat();
        System.out.println ("Toplam " + liste.size() + " ki�ilik listenin JDK-8 stream().forEach(..) ile altalta d�k�m�==>");
        liste.stream().forEach (ki�i -> ki�i.ki�iyiYaz());
        System.out.println();

        // 2. stream() de�il paralelStream()'le erkek �yelerin ortalama ya�lar�n�n bulunmas�...
        double ortalama = liste
                .parallelStream()
                .filter (k -> k.cinsiAl() == J6c_1x2.Cinsiyet.ERKEK)
                .mapToInt (J6c_1x2 :: ya��Al) // Lamda ile ya��Al() metodu ya��Al yal�n yaz�lmakta...
                .average()
                .getAsDouble();
        System.out.format ("Paralel listedeki erkek �yelerin ortalama ya��: %.2f%n", ortalama);

        // stream() de�il paralelStream()'le di�i �yelerin ortalama ya�lar�n�n bulunmas�...
        ortalama = liste
                .stream()
                .filter (k -> k.cinsiAl() == J6c_1x2.Cinsiyet.KADIN)
                .mapToInt (J6c_1x2:: ya��Al) // Lamda ile ya��Al() metodu ya��Al yal�n yaz�lmakta...
                .average()
                .getAsDouble();
        System.out.format ("Listedeki kad�n �yelerin ortalama ya��: %.2f%n", ortalama);

        // 3. Ayn�anda listeyi KADIN ve ERKEK �yeler olarak gruplama �rne�i...
        ConcurrentMap<J6c_1x2.Cinsiyet, List<J6c_1x2>> cinsiyetleParalelGruplama =
                liste.parallelStream().collect (Collectors.groupingByConcurrent (J6c_1x2 :: cinsiAl));
        List<Map.Entry<J6c_1x2.Cinsiyet, List<J6c_1x2>>> cinsiyetleGruplananListe = 
                new ArrayList<> (cinsiyetleParalelGruplama.entrySet());
        System.out.println ("\nCinsiyete g�re gruplanan �yelerin altalta adlar�==>");
        cinsiyetleGruplananListe
                .stream()
                .forEach (k -> {
                    System.out.println ("Cinsiyet: " + k.getKey());
                    k.getValue()
                            .stream()
                            .map (J6c_1x2 :: ismiAl)
                            .forEach (ad -> System.out.println (ad));});

        // 4. S�ralama ve paralellik �rnekleri...
        System.out.println ("\nS�ralama ve paralellik �rnekleri:");
        Integer[] tamsay�Dizi = {9, 1, 8, 3, 7, 5, 6, 0, 4, 2};
        List<Integer> tamsay�larListesi = new ArrayList<> (Arrays.asList (tamsay�Dizi));
        System.out.println ("Kar���k tamsay�lar listesi yanyana==>");
        tamsay�larListesi.stream().forEach (rakam -> System.out.print (rakam + " "));
        System.out.println();

        System.out.println ("\nTers s�ral� tamsay�lar listesi yanyana==>");
        Comparator<Integer> normal = Integer :: compare;
        Comparator<Integer> ters = normal.reversed(); 
        Collections.sort (tamsay�larListesi, ters);             
        tamsay�larListesi.stream().forEach (r -> System.out.print (r + " "));
        System.out.println();

        System.out.println ("\nParalel ak��taki tamsay�lar listesi yanyana==>");
        tamsay�larListesi.parallelStream().forEach (r -> System.out.print (r + " "));
        System.out.println();

        System.out.println ("\nDi�er bir paralel ak�� listesi daha (yanyana)==>");
        tamsay�larListesi.parallelStream().forEach (r -> System.out.print (r + " "));
        System.out.println();

        System.out.println ("\nforEachOrdered ile paralel ak�� listesi yanyana==>");
        tamsay�larListesi.parallelStream().forEachOrdered (r -> System.out.print (r + " "));
        System.out.println();

        // 5a. Engelleme �rne�i...
        try {List<String> dizgelerListesi = new ArrayList<> (Arrays.asList ("bir", "iki", "��", "d�rt", "be�", "alt�", "yedi"));
            // .peek(..) i�lemi yeni bir dizgeyi sonland�rma ba�lat�ld�ktan sonra
            // eklemeye �al���rken hata olu�turacakt�r...
            String eklemeliDizge = dizgelerListesi
                    .stream()
                    // Kar��ma burada olu�ur, ve catch hata yakalar!..
                    .peek (eleman -> dizgelerListesi.add ("sekiz"))
                    .reduce ((a, b) -> a + " " + b)
                    .get();
            System.out.println ("Eklemeli dizge listesi yanyana: " + eklemeliDizge);
        }catch (Exception ist) {System.out.println ("\nYakalanan istisna (Eklemeli dizge listesi hatas�): " + ist.toString());}

        // 5b. Ayn� �rne�i hatas�z d�kelim...
        try {List<String> dizgelerListesi = new ArrayList<> (Arrays.asList ("bir", "iki", "��", "d�rt", "be�", "alt�", "yedi"));
            String eklemesizDizge = dizgelerListesi
                    .stream()
                    .reduce ((a, b) -> a + " " + b)
                    .get();
            System.out.println ("Eklemesiz dizge listesi yanyana: " + eklemesizDizge);
        }catch (Exception ist) {System.out.println ("Yakalanan istisna: " + ist.toString());}

        // 6a. Seri ak��l� tamsay�lar�n lambdal� d�k�m�...
        List<Integer> seriDepolama = new ArrayList<>();
        System.out.println ("\nSeri ak��l� tamsay�lar listesi yanyana==>");
        tamsay�larListesi
                .stream()
                // Bu i�lem ��pheli lambda ifadesi kullan�m�d�r!..
                .map (r -> {seriDepolama.add (r); return r;})
                .forEachOrdered (r -> System.out.print (r + " "));
        System.out.println();
        seriDepolama.stream().forEachOrdered (r -> System.out.print (r + " "));
        System.out.println("");

        // 6b. Paralel ak��l� tamsay�lar�n ��pheli lambdal� d�k�m�...
        System.out.println ("\nParalel ak��l� tamsay�lar listesi yanyana==>");
        List<Integer> paralelDepolama = Collections.synchronizedList (new ArrayList<>());
        tamsay�larListesi
                .parallelStream()
                // Bu i�lem ��pheli lambda ifadesi kullan�m�d�r!..
                .map (r -> {paralelDepolama.add (r); return r;})
                .forEachOrdered (r -> System.out.print (r + " "));
        System.out.println();
        paralelDepolama.stream().forEachOrdered (r -> System.out.print (r + " "));
        System.out.println();
    } // main(..) metodu sonu...
} // J6c_3 s�n�f� sonu...

/* ��kt�:
**  >java J6c_3  **
Toplam 9 ki�ilik listenin JDK-8 stream().forEach(..) ile altalta d�k�m�==>
Fred, 38, ERKEK, fred@gmail.com
Hilal, 32, KADIN, hilal@hotmail.com
Jane, 28, KADIN, jane@gmail.com
George, 27, ERKEK, george@gmail.com
Bob, 18, ERKEK, bob@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com
Metin, 20, ERKEK, metin@hotmail.com
Belk�s, 34, KADIN, belkis@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

Paralel listedeki erkek �yelerin ortalama ya��: 30,83
Listedeki kad�n �yelerin ortalama ya��: 31,33

Cinsiyete g�re gruplanan �yelerin altalta adlar�==>
Cinsiyet: ERKEK
Bob
Nihat
Ersin
Metin
George
Fred
Cinsiyet: KADIN
Belk�s
Jane
Hilal

S�ralama ve paralellik �rnekleri:
Kar���k tamsay�lar listesi yanyana==>
9 1 8 3 7 5 6 0 4 2

Ters s�ral� tamsay�lar listesi yanyana==>
9 8 7 6 5 4 3 2 1 0

Paralel ak��taki tamsay�lar listesi yanyana==>
4 3 1 0 2 9 8 6 5 7

Di�er bir paralel ak�� listesi daha (yanyana)==>
4 3 1 0 2 9 8 6 5 7

forEachOrdered ile paralel ak�� listesi yanyana==>
9 8 7 6 5 4 3 2 1 0

Yakalanan istisna (Eklemeli dizge listesi hatas�): java.util.ConcurrentModificationException
Eklemesiz dizge listesi yanyana: bir iki �� d�rt be� alt� yedi

Seri ak��l� tamsay�lar listesi yanyana==>
9 8 7 6 5 4 3 2 1 0
9 8 7 6 5 4 3 2 1 0

Paralel ak��l� tamsay�lar listesi yanyana==>
9 8 7 6 5 4 3 2 1 0
4 3 1 0 2 7 6 5 9 8
*/