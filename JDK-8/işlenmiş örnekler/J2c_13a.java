// J2c_13a.java: MethodReferencesTest (MetodReferansý-ilgi,alaka,bað-Testi) örneði.

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.Set;
import java.util.HashSet;
import java.time.chrono.IsoChronology;

// Gereken dosya: J2c_13ax.java; Person
public class J2c_13a {
    // elemanlarýAktar(..) metodu bir koleksiyon elemanlarýný diðerine kopyalar...
    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>> DEST elemanlarýAktar (SOURCE kaynakKoleksiyon, Supplier<DEST> hedefKoleksiyon) {
        DEST listem = hedefKoleksiyon.get();
        for (T eleman : kaynakKoleksiyon) listem.add (eleman);
        return listem;
    } // elemanlarýAktar(..) metodu sonu...
      
    public static void main (String... args) {
        List<J2c_13ax> liste = J2c_13ax.listeyiYarat();        

        System.out.println ("Orijinal liste:");
        for (J2c_13ax kiþi: liste) kiþi.kiþiyiYaz();

        J2c_13ax[] dizinListe = liste.toArray (new J2c_13ax[liste.size()]);

        class KiþiYaþKarþýlaþtýrýcýsý implements Comparator<J2c_13ax> {
            public int compare (J2c_13ax a, J2c_13ax b) {
                return a.doðumTarihiniAl().compareTo (b.doðumTarihiniAl());
            } // karþýlaþtýr(..) metodu sonu...
        } // KiþiYaþKarþýlaþtýrýcýsý sýnýfý sonu...

        // Metod referans olmaksýzýn sýralama...
        Arrays.sort (dizinListe, new KiþiYaþKarþýlaþtýrýcýsý());
        System.out.println ("\nArrays.sort (dizinListe, new KiþiYaþKarþýlaþtýrýcýsý()): Yaþ'a göre sýralý==>");
        for (J2c_13ax kiþi: dizinListe) kiþi.kiþiyiYaz();


        // Lambda ifadesiyle sýralama...
        Arrays.sort (dizinListe, (J2c_13ax a, J2c_13ax b) -> {return a.doðumTarihiniAl().compareTo (b.doðumTarihiniAl());});
        System.out.println ("\nArrays.sort (dizinListe, (J2c_13ax a, J2c_13ax b) -> {return a.doðumTarihiniAl().compareTo (b.doðumTarihiniAl());}): Yaþ'a göre sýralý==>");
        for (J2c_13ax kiþi: dizinListe) kiþi.kiþiyiYaz();

        // Metod referansla sýralama...
        Arrays.sort (dizinListe, J2c_13ax::doðumTarihineGöreKarþýlaþtýr);
        System.out.println ("\nArrays.sort (dizinListe, J2c_13ax::doðumTarihineGöreKarþýlaþtýr): Yaþ'a göre sýralý==>");
        for (J2c_13ax kiþi: dizinListe) kiþi.kiþiyiYaz();

        // Bir nesnenin tip metoduna referans yapma...
        class Karþýlaþtýrýcý {
            public int ismeGöreKarþýlaþtýr (J2c_13ax a, J2c_13ax b) {return a.ismiAl().compareTo (b.ismiAl());}
            public int doðumTarihineGöreKarþýlaþtýr (J2c_13ax a, J2c_13ax b) {return a.doðumTarihiniAl().compareTo (b.doðumTarihiniAl());}
        } // Karþýlaþtýrýcý sýnýfý sonu...

        Karþýlaþtýrýcý karþýlaþtýrýcým = new Karþýlaþtýrýcý();
        Arrays.sort (dizinListe, karþýlaþtýrýcým::ismeGöreKarþýlaþtýr);
        System.out.println ("\nArrays.sort (dizinListe, karþýlaþtýrýcým::ismeGöreKarþýlaþtýr): Ýsme göre sýralý==>");
        for (J2c_13ax kiþi: dizinListe) kiþi.kiþiyiYaz();

        String[] dizinStr = { "Barbara", "James", "Mary", "John",
            "Patricia", "Robert", "Michael", "Linda", "Nihat" };
        Arrays.sort (dizinStr, String::compareToIgnoreCase);
        System.out.println ("\nArrays.sort (dizinStr, String::compareToIgnoreCase): Ýsme göre sýralý==>");
        for (String kiþi: dizinStr) System.out.println (kiþi);

        Set<J2c_13ax> listeSetLambda = elemanlarýAktar (liste, () -> {return new HashSet<>(); });
        Set<J2c_13ax> listeSet = elemanlarýAktar (liste, HashSet::new);

        System.out.println ("\nListe'den HashSet'e kopyalanan listeSet yazdýrýlýyor==>");
        listeSet.stream().forEach (birey -> birey.kiþiyiYaz());
    } // main(..) metodu sonu...
} // J2c_13a sýnýfý sonu...

/* Çýktý:
**  >java J2c_13a  **
Orijinal liste:
Fred, 37, ERKEK, fred@gmail.com
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com

Arrays.sort (dizinListe, new KiþiYaþKarþýlaþtýrýcýsý()): Yaþ'a göre sýralý==>
Nihat, 61, ERKEK, mnyavas@hotmail.com
Fred, 37, ERKEK, fred@gmail.com
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com

Arrays.sort (dizinListe, (J2c_13ax a, J2c_13ax b) -> {return a.doðumTarihiniAl().compareTo (b.doðumTarihiniAl());}): Yaþ'a göre sýralý==>
Nihat, 61, ERKEK, mnyavas@hotmail.com
Fred, 37, ERKEK, fred@gmail.com
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com

Arrays.sort (dizinListe, J2c_13ax::doðumTarihineGöreKarþýlaþtýr): Yaþ'a göre sýralý==>
Nihat, 61, ERKEK, mnyavas@hotmail.com
Fred, 37, ERKEK, fred@gmail.com
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com

Arrays.sort (dizinListe, karþýlaþtýrýcým::ismeGöreKarþýlaþtýr): Ýsme göre sýralý==>
Bob, 17, ERKEK, bob@gmail.com
Fred, 37, ERKEK, fred@gmail.com
George, 26, ERKEK, george@gmail.com
Jane, 27, KADIN, jane@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com

Arrays.sort (dizinStr, String::compareToIgnoreCase): Ýsme göre sýralý==>
Barbara
James
John
Linda
Mary
Michael
Nihat
Patricia
Robert

Liste'den HashSet'e kopyalanan listeSet yazdýrýlýyor==>
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com
Fred, 37, ERKEK, fred@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com
*/