// J2e_6x4.java: SortByRankThenSuit (Say�yaVeTak�maG�reS�rala) alt-�rne�i.

import java.util.Comparator;

public class J2e_6x4 implements Comparator<J2e_6x1> {// J2e_6x1=Card/Kart...
    public int compare (J2e_6x1 ilkKart, J2e_6x1 ikinciKart) {
        int sonu� = ilkKart.say�Al().de�erAl() - ikinciKart.say�Al().de�erAl();
        if (sonu� != 0) return sonu�;
        else return ilkKart.tak�mAl().de�erAl() - ikinciKart.tak�mAl().de�erAl(); 
    } // compare(..) override metodu sonu...
} // J2e_6x4 s�n�f� sonu...