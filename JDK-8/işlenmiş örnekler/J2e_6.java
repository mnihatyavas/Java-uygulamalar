// J2e_6.java: StandardDeck (StandartDeste) �rne�i.

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
    private List<J2e_6x1> t�mDeste;

    public J2e_6 (List<J2e_6x1> mevcutListe) {// J2e_6x1=Card/Kart...
        this.t�mDeste = mevcutListe;
    } // J2e_6(..) arg�manl� s�n�f kurucusu sonu...

    public J2e_6() {
        this.t�mDeste = new ArrayList<>();
        for (J2e_6x1.Tak�m t : J2e_6x1.Tak�m.values())
            for (J2e_6x1.Say� s : J2e_6x1.Say�.values())
               this.t�mDeste.add (new J2e_6x3 (s, t)); // J2e_6x3=PlayingCard/OyunKart�...
    } // J2e_6() arg�mans�z kurucu sonu...

    public J2e_6x2 desteKayna��() {return new J2e_6 (new ArrayList<J2e_6x1>());}
    public int size() {return t�mDeste.size();}
    public List<J2e_6x1> kartlarAl() {return t�mDeste;}
    public void kartEkle (J2e_6x1 kart) {t�mDeste.add (kart);}
    public void kartlarEkle (List<J2e_6x1> kartlar) {t�mDeste.addAll (kartlar);}
    public void desteEkle (J2e_6x2 deste) {List<J2e_6x1> eklenecekListe = deste.kartlarAl(); t�mDeste.addAll (eklenecekListe);}
    public void sort() {Collections.sort (t�mDeste);}
    public void sort (Comparator<J2e_6x1> kar��la�t�r�c�) {Collections.sort (t�mDeste, kar��la�t�r�c�);}    
    public void shuffle() {Collections.shuffle (t�mDeste);}

    public Map<Integer, J2e_6x2> da��t (int oyuncular, int kartAdedi) throws IllegalArgumentException {
        int da��t�lanKartlar = oyuncular * kartAdedi;
        int desteEbad� = t�mDeste.size();
        if (da��t�lanKartlar > desteEbad�) throw new IllegalArgumentException (
                "Oyuncular: " + oyuncular +
                " �arp� da��t�lan kart say�s�: " + kartAdedi + " = " + da��t�lanKartlar +
                " ==> destedeki kart adedinden (" + desteEbad� + ") fazla ��kt�!?");

        Map<Integer, List<J2e_6x1>> da��t�lanDeste = t�mDeste
           .stream()
           .collect(
               Collectors.groupingBy(
                   kart -> {
                       int kartEndeksi = t�mDeste.indexOf (kart);
                       if (kartEndeksi >= da��t�lanKartlar) return (oyuncular + 1);
                       else return (kartEndeksi % oyuncular) + 1;
        })); // Map ifadesi sonu...

        // Map<Integer, List<J2e_6x1>> tekrar Map<Integer, J2e_6x2>'e �evrilecek...
        Map<Integer, J2e_6x2> d�nenHarita = new HashMap<>();
           
        for (int i = 1; i <= (oyuncular + 1); i++) {
            J2e_6x2 mevcutDeste = desteKayna��();
            mevcutDeste.kartlarEkle (da��t�lanDeste.get (i));
            d�nenHarita.put (i, mevcutDeste);
        } // for d�ng�s� sonu...
        return d�nenHarita;
    } // da��t(..) metodu sonu...

    public String destedenDizgeye() {
        return this.t�mDeste
            .stream()
            .map (J2e_6x1 :: toString)
            .collect (Collectors.joining ("\n"));
    } // destedenDizgeye() metodu sonu...

    public static void main (String... args) {
        System.out.println ("Yeni oyun 'destem' nesnesi yarat�l�yor....");
        J2e_6 destem = new J2e_6();

        destem.sort();
        System.out.println ("\nS�ral� deste d�k�m�==>");
        System.out.println (destem.destedenDizgeye());

        destem.shuffle(); // destem geli�ig�zel kar�l�yor...
        destem.sort (new J2e_6x4()); // J2e_6x4=SortByRankThenSuit/Say�yaVeTak�maG�reS�rala...
        System.out.println ("\n�nce say�ya, sonra da tak�ma g�re s�ral� d�k�m==>");
        System.out.println (destem.destedenDizgeye());

        destem.shuffle();
        destem.sort(
            Comparator.comparing (J2e_6x1 :: say�Al)
                .thenComparing (Comparator.comparing (J2e_6x1 :: tak�mAl)));
        System.out.println ("\nStatik ve varsay�l� metodlarla �nce say�ya sonra da tak�ma g�re s�ral� d�k�m==>");
        System.out.println (destem.destedenDizgeye());

        destem.shuffle();
        destem.sort(
            Comparator.comparing (J2e_6x1 :: say�Al)
                .reversed()
                .thenComparing (Comparator.comparing (J2e_6x1 :: tak�mAl)));
        System.out.println ("\nStatik ve varsay�l� metodlarla �nce say�ya tersten sonra da tak�ma g�re s�ral� d�k�m==>");
        System.out.println(destem.destedenDizgeye());

        destem.shuffle();
        destem.sort(
            Comparator.comparing (J2e_6x1 :: say�Al)
                .thenComparing (Comparator.comparing (J2e_6x1 :: tak�mAl))
                .reversed());
        System.out.println ("\nStatik ve varsay�l� metodlarla �nce say�ya  sonra da tak�ma g�re tersten s�ral� d�k�m==>");
        System.out.println(destem.destedenDizgeye());
    } // main(..) metodu sonu...
} // J2e_6 s�n�f� sonu...

/* ��kt�:
**  >java J2e_6  **
Yeni oyun 'destem' nesnesi yarat�l�yor....

S�ral� deste d�k�m�==>
Karo As
Karo �ki
Karo ��
Karo D�rt
Karo Be�
Karo Alt�
Karo Yedi
Karo Sekiz
Karo Dokuz
Karo On
Karo O�lan
Karo K�z
Karo Papaz
Ma�a As
Ma�a �ki
Ma�a ��
Ma�a D�rt
Ma�a Be�
Ma�a Alt�
Ma�a Yedi
Ma�a Sekiz
Ma�a Dokuz
Ma�a On
Ma�a O�lan
Ma�a K�z
Ma�a Papaz
Kupa As
Kupa �ki
Kupa ��
Kupa D�rt
Kupa Be�
Kupa Alt�
Kupa Yedi
Kupa Sekiz
Kupa Dokuz
Kupa On
Kupa O�lan
Kupa K�z
Kupa Papaz
Sinek As
Sinek �ki
Sinek ��
Sinek D�rt
Sinek Be�
Sinek Alt�
Sinek Yedi
Sinek Sekiz
Sinek Dokuz
Sinek On
Sinek O�lan
Sinek K�z
Sinek Papaz

�nce say�ya, sonra da tak�ma g�re s�ral� d�k�m==>
Karo As
Ma�a As
Kupa As
Sinek As
Karo �ki
Ma�a �ki
Kupa �ki
Sinek �ki
Karo ��
Ma�a ��
Kupa ��
Sinek ��
Karo D�rt
Ma�a D�rt
Kupa D�rt
Sinek D�rt
Karo Be�
Ma�a Be�
Kupa Be�
Sinek Be�
Karo Alt�
Ma�a Alt�
Kupa Alt�
Sinek Alt�
Karo Yedi
Ma�a Yedi
Kupa Yedi
Sinek Yedi
Karo Sekiz
Ma�a Sekiz
Kupa Sekiz
Sinek Sekiz
Karo Dokuz
Ma�a Dokuz
Kupa Dokuz
Sinek Dokuz
Karo On
Ma�a On
Kupa On
Sinek On
Karo O�lan
Ma�a O�lan
Kupa O�lan
Sinek O�lan
Karo K�z
Ma�a K�z
Kupa K�z
Sinek K�z
Karo Papaz
Ma�a Papaz
Kupa Papaz
Sinek Papaz

Statik ve varsay�l� metodlarla �nce say�ya sonra da tak�ma g�re s�ral� d�k�m==>
Karo As
Ma�a As
Kupa As
Sinek As
Karo �ki
Ma�a �ki
Kupa �ki
Sinek �ki
Karo ��
Ma�a ��
Kupa ��
Sinek ��
Karo D�rt
Ma�a D�rt
Kupa D�rt
Sinek D�rt
Karo Be�
Ma�a Be�
Kupa Be�
Sinek Be�
Karo Alt�
Ma�a Alt�
Kupa Alt�
Sinek Alt�
Karo Yedi
Ma�a Yedi
Kupa Yedi
Sinek Yedi
Karo Sekiz
Ma�a Sekiz
Kupa Sekiz
Sinek Sekiz
Karo Dokuz
Ma�a Dokuz
Kupa Dokuz
Sinek Dokuz
Karo On
Ma�a On
Kupa On
Sinek On
Karo O�lan
Ma�a O�lan
Kupa O�lan
Sinek O�lan
Karo K�z
Ma�a K�z
Kupa K�z
Sinek K�z
Karo Papaz
Ma�a Papaz
Kupa Papaz
Sinek Papaz

Statik ve varsay�l� metodlarla �nce say�ya tersten sonra da tak�ma g�re s�ral� d�k�m==>
Karo Papaz
Ma�a Papaz
Kupa Papaz
Sinek Papaz
Karo K�z
Ma�a K�z
Kupa K�z
Sinek K�z
Karo O�lan
Ma�a O�lan
Kupa O�lan
Sinek O�lan
Karo On
Ma�a On
Kupa On
Sinek On
Karo Dokuz
Ma�a Dokuz
Kupa Dokuz
Sinek Dokuz
Karo Sekiz
Ma�a Sekiz
Kupa Sekiz
Sinek Sekiz
Karo Yedi
Ma�a Yedi
Kupa Yedi
Sinek Yedi
Karo Alt�
Ma�a Alt�
Kupa Alt�
Sinek Alt�
Karo Be�
Ma�a Be�
Kupa Be�
Sinek Be�
Karo D�rt
Ma�a D�rt
Kupa D�rt
Sinek D�rt
Karo ��
Ma�a ��
Kupa ��
Sinek ��
Karo �ki
Ma�a �ki
Kupa �ki
Sinek �ki
Karo As
Ma�a As
Kupa As
Sinek As

Statik ve varsay�l� metodlarla �nce say�ya  sonra da tak�ma g�re tersten s�ral�d�k�m==>
Sinek Papaz
Kupa Papaz
Ma�a Papaz
Karo Papaz
Sinek K�z
Kupa K�z
Ma�a K�z
Karo K�z
Sinek O�lan
Kupa O�lan
Ma�a O�lan
Karo O�lan
Sinek On
Kupa On
Ma�a On
Karo On
Sinek Dokuz
Kupa Dokuz
Ma�a Dokuz
Karo Dokuz
Sinek Sekiz
Kupa Sekiz
Ma�a Sekiz
Karo Sekiz
Sinek Yedi
Kupa Yedi
Ma�a Yedi
Karo Yedi
Sinek Alt�
Kupa Alt�
Ma�a Alt�
Karo Alt�
Sinek Be�
Kupa Be�
Ma�a Be�
Karo Be�
Sinek D�rt
Kupa D�rt
Ma�a D�rt
Karo D�rt
Sinek ��
Kupa ��
Ma�a ��
Karo ��
Sinek �ki
Kupa �ki
Ma�a �ki
Karo �ki
Sinek As
Kupa As
Ma�a As
Karo As
*/