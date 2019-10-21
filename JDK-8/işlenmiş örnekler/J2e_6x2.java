// J2e_6x2.java: Deck (Deste) arayüzü alt-örneði.

import java.util.List;
import java.util.Map;
import java.util.Comparator;
 
public interface J2e_6x2 {
    List<J2e_6x1> kartlarAl(); // J2e_6x1=Card/Kart
    J2e_6x2 desteKaynaðý(); // arayüzdeki gövdesiz soyut metodlar...
    int size();
    void kartEkle (J2e_6x1 kart);
    void kartlarEkle (List<J2e_6x1> kartlar);
    void desteEkle (J2e_6x2 deste);
    void shuffle();
    void sort();
    void sort (Comparator<J2e_6x1> c);
    String destedenDizgeye();

    Map<Integer, J2e_6x2> daðýt (int oyuncular, int kartAdedi) throws IllegalArgumentException;
} // J2e_6x2 arayüzü sonu...