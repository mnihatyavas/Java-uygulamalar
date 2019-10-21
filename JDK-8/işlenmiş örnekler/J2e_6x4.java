// J2e_6x4.java: SortByRankThenSuit (SayıyaVeTakımaGöreSırala) alt-örneği.

import java.util.Comparator;

public class J2e_6x4 implements Comparator<J2e_6x1> {// J2e_6x1=Card/Kart...
    public int compare (J2e_6x1 ilkKart, J2e_6x1 ikinciKart) {
        int sonuç = ilkKart.sayıAl().değerAl() - ikinciKart.sayıAl().değerAl();
        if (sonuç != 0) return sonuç;
        else return ilkKart.takımAl().değerAl() - ikinciKart.takımAl().değerAl(); 
    } // compare(..) override metodu sonu...
} // J2e_6x4 sınıfı sonu...