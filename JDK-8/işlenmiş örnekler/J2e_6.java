// J2e_6.java: StandardDeck (StandartDeste) örneði.

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.stream.Collectors;

/* Gereken dosyalar:
 *   J2e_6x1.java=Card
 *   J2e_6x2.java=Deck
 *   J2e_6x3.java=PlayingCard
 *   J2e_6x4.java=SortByRankThenSuit
 */
public class J2e_6 implements J2e_6x2 {// J2e_6x2=Deck/Deste...
    private List<J2e_6x1> tümDeste;

    public J2e_6 (List<J2e_6x1> mevcutListe) {// J2e_6x1=Card/Kart...
        this.tümDeste = mevcutListe;
    } // J2e_6(..) argümanlý sýnýf kurucusu sonu...

    public J2e_6() {
        this.tümDeste = new ArrayList<>();
        for (J2e_6x1.Takým t : J2e_6x1.Takým.values())
            for (J2e_6x1.Sayý s : J2e_6x1.Sayý.values())
               this.tümDeste.add (new J2e_6x3 (s, t)); // J2e_6x3=PlayingCard/OyunKartý...
    } // J2e_6() argümansýz kurucu sonu...

    public J2e_6x2 desteKaynaðý() {return new J2e_6 (new ArrayList<J2e_6x1>());}
    public int size() {return tümDeste.size();}
    public List<J2e_6x1> kartlarAl() {return tümDeste;}
    public void kartEkle (J2e_6x1 kart) {tümDeste.add (kart);}
    public void kartlarEkle (List<J2e_6x1> kartlar) {tümDeste.addAll (kartlar);}
    public void desteEkle (J2e_6x2 deste) {List<J2e_6x1> eklenecekListe = deste.kartlarAl(); tümDeste.addAll (eklenecekListe);}
    public void sort() {Collections.sort (tümDeste);}
    public void sort (Comparator<J2e_6x1> karþýlaþtýrýcý) {Collections.sort (tümDeste, karþýlaþtýrýcý);}    
    public void shuffle() {Collections.shuffle (tümDeste);}

    public Map<Integer, J2e_6x2> daðýt (int oyuncular, int kartAdedi) throws IllegalArgumentException {
        int daðýtýlanKartlar = oyuncular * kartAdedi;
        int desteEbadý = tümDeste.size();
        if (daðýtýlanKartlar > desteEbadý) throw new IllegalArgumentException (
                "Oyuncular: " + oyuncular +
                " çarpý daðýtýlan kart sayýsý: " + kartAdedi + " = " + daðýtýlanKartlar +
                " ==> destedeki kart adedinden (" + desteEbadý + ") fazla çýktý!?");

        Map<Integer, List<J2e_6x1>> daðýtýlanDeste = tümDeste
           .stream()
           .collect(
               Collectors.groupingBy(
                   kart -> {
                       int kartEndeksi = tümDeste.indexOf (kart);
                       if (kartEndeksi >= daðýtýlanKartlar) return (oyuncular + 1);
                       else return (kartEndeksi % oyuncular) + 1;
        })); // Map ifadesi sonu...

        // Map<Integer, List<J2e_6x1>> tekrar Map<Integer, J2e_6x2>'e çevrilecek...
        Map<Integer, J2e_6x2> dönenHarita = new HashMap<>();
           
        for (int i = 1; i <= (oyuncular + 1); i++) {
            J2e_6x2 mevcutDeste = desteKaynaðý();
            mevcutDeste.kartlarEkle (daðýtýlanDeste.get (i));
            dönenHarita.put (i, mevcutDeste);
        } // for döngüsü sonu...
        return dönenHarita;
    } // daðýt(..) metodu sonu...

    public String destedenDizgeye() {
        return this.tümDeste
            .stream()
            .map (J2e_6x1 :: toString)
            .collect (Collectors.joining ("\n"));
    } // destedenDizgeye() metodu sonu...

    public static void main (String... args) {
        System.out.println ("Yeni oyun 'destem' nesnesi yaratýlýyor....");
        J2e_6 destem = new J2e_6();

        destem.sort();
        System.out.println ("\nSýralý deste dökümü==>");
        System.out.println (destem.destedenDizgeye());

        destem.shuffle(); // destem geliþigüzel karýlýyor...
        destem.sort (new J2e_6x4()); // J2e_6x4=SortByRankThenSuit/SayýyaVeTakýmaGöreSýrala...
        System.out.println ("\nÖnce sayýya, sonra da takýma göre sýralý döküm==>");
        System.out.println (destem.destedenDizgeye());

        destem.shuffle();
        destem.sort(
            Comparator.comparing (J2e_6x1 :: sayýAl)
                .thenComparing (Comparator.comparing (J2e_6x1 :: takýmAl)));
        System.out.println ("\nStatik ve varsayýlý metodlarla önce sayýya sonra da takýma göre sýralý döküm==>");
        System.out.println (destem.destedenDizgeye());

        destem.shuffle();
        destem.sort(
            Comparator.comparing (J2e_6x1 :: sayýAl)
                .reversed()
                .thenComparing (Comparator.comparing (J2e_6x1 :: takýmAl)));
        System.out.println ("\nStatik ve varsayýlý metodlarla önce sayýya tersten sonra da takýma göre sýralý döküm==>");
        System.out.println(destem.destedenDizgeye());

        destem.shuffle();
        destem.sort(
            Comparator.comparing (J2e_6x1 :: sayýAl)
                .thenComparing (Comparator.comparing (J2e_6x1 :: takýmAl))
                .reversed());
        System.out.println ("\nStatik ve varsayýlý metodlarla önce sayýya  sonra da takýma göre tersten sýralý döküm==>");
        System.out.println(destem.destedenDizgeye());
    } // main(..) metodu sonu...
} // J2e_6 sýnýfý sonu...

/* Çýktý:
**  >java J2e_6  **
Yeni oyun 'destem' nesnesi yaratýlýyor....

Sýralý deste dökümü==>
Karo As
Karo Ýki
Karo Üç
Karo Dört
Karo Beþ
Karo Altý
Karo Yedi
Karo Sekiz
Karo Dokuz
Karo On
Karo Oðlan
Karo Kýz
Karo Papaz
Maça As
Maça Ýki
Maça Üç
Maça Dört
Maça Beþ
Maça Altý
Maça Yedi
Maça Sekiz
Maça Dokuz
Maça On
Maça Oðlan
Maça Kýz
Maça Papaz
Kupa As
Kupa Ýki
Kupa Üç
Kupa Dört
Kupa Beþ
Kupa Altý
Kupa Yedi
Kupa Sekiz
Kupa Dokuz
Kupa On
Kupa Oðlan
Kupa Kýz
Kupa Papaz
Sinek As
Sinek Ýki
Sinek Üç
Sinek Dört
Sinek Beþ
Sinek Altý
Sinek Yedi
Sinek Sekiz
Sinek Dokuz
Sinek On
Sinek Oðlan
Sinek Kýz
Sinek Papaz

Önce sayýya, sonra da takýma göre sýralý döküm==>
Karo As
Maça As
Kupa As
Sinek As
Karo Ýki
Maça Ýki
Kupa Ýki
Sinek Ýki
Karo Üç
Maça Üç
Kupa Üç
Sinek Üç
Karo Dört
Maça Dört
Kupa Dört
Sinek Dört
Karo Beþ
Maça Beþ
Kupa Beþ
Sinek Beþ
Karo Altý
Maça Altý
Kupa Altý
Sinek Altý
Karo Yedi
Maça Yedi
Kupa Yedi
Sinek Yedi
Karo Sekiz
Maça Sekiz
Kupa Sekiz
Sinek Sekiz
Karo Dokuz
Maça Dokuz
Kupa Dokuz
Sinek Dokuz
Karo On
Maça On
Kupa On
Sinek On
Karo Oðlan
Maça Oðlan
Kupa Oðlan
Sinek Oðlan
Karo Kýz
Maça Kýz
Kupa Kýz
Sinek Kýz
Karo Papaz
Maça Papaz
Kupa Papaz
Sinek Papaz

Statik ve varsayýlý metodlarla önce sayýya sonra da takýma göre sýralý döküm==>
Karo As
Maça As
Kupa As
Sinek As
Karo Ýki
Maça Ýki
Kupa Ýki
Sinek Ýki
Karo Üç
Maça Üç
Kupa Üç
Sinek Üç
Karo Dört
Maça Dört
Kupa Dört
Sinek Dört
Karo Beþ
Maça Beþ
Kupa Beþ
Sinek Beþ
Karo Altý
Maça Altý
Kupa Altý
Sinek Altý
Karo Yedi
Maça Yedi
Kupa Yedi
Sinek Yedi
Karo Sekiz
Maça Sekiz
Kupa Sekiz
Sinek Sekiz
Karo Dokuz
Maça Dokuz
Kupa Dokuz
Sinek Dokuz
Karo On
Maça On
Kupa On
Sinek On
Karo Oðlan
Maça Oðlan
Kupa Oðlan
Sinek Oðlan
Karo Kýz
Maça Kýz
Kupa Kýz
Sinek Kýz
Karo Papaz
Maça Papaz
Kupa Papaz
Sinek Papaz

Statik ve varsayýlý metodlarla önce sayýya tersten sonra da takýma göre sýralý döküm==>
Karo Papaz
Maça Papaz
Kupa Papaz
Sinek Papaz
Karo Kýz
Maça Kýz
Kupa Kýz
Sinek Kýz
Karo Oðlan
Maça Oðlan
Kupa Oðlan
Sinek Oðlan
Karo On
Maça On
Kupa On
Sinek On
Karo Dokuz
Maça Dokuz
Kupa Dokuz
Sinek Dokuz
Karo Sekiz
Maça Sekiz
Kupa Sekiz
Sinek Sekiz
Karo Yedi
Maça Yedi
Kupa Yedi
Sinek Yedi
Karo Altý
Maça Altý
Kupa Altý
Sinek Altý
Karo Beþ
Maça Beþ
Kupa Beþ
Sinek Beþ
Karo Dört
Maça Dört
Kupa Dört
Sinek Dört
Karo Üç
Maça Üç
Kupa Üç
Sinek Üç
Karo Ýki
Maça Ýki
Kupa Ýki
Sinek Ýki
Karo As
Maça As
Kupa As
Sinek As

Statik ve varsayýlý metodlarla önce sayýya  sonra da takýma göre tersten sýralýdöküm==>
Sinek Papaz
Kupa Papaz
Maça Papaz
Karo Papaz
Sinek Kýz
Kupa Kýz
Maça Kýz
Karo Kýz
Sinek Oðlan
Kupa Oðlan
Maça Oðlan
Karo Oðlan
Sinek On
Kupa On
Maça On
Karo On
Sinek Dokuz
Kupa Dokuz
Maça Dokuz
Karo Dokuz
Sinek Sekiz
Kupa Sekiz
Maça Sekiz
Karo Sekiz
Sinek Yedi
Kupa Yedi
Maça Yedi
Karo Yedi
Sinek Altý
Kupa Altý
Maça Altý
Karo Altý
Sinek Beþ
Kupa Beþ
Maça Beþ
Karo Beþ
Sinek Dört
Kupa Dört
Maça Dört
Karo Dört
Sinek Üç
Kupa Üç
Maça Üç
Karo Üç
Sinek Ýki
Kupa Ýki
Maça Ýki
Karo Ýki
Sinek As
Kupa As
Maça As
Karo As
*/