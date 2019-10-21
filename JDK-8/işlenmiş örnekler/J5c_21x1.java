// J5c_21x1.java: Person (�ah�s) alt-�rne�i.

import java.util.Vector;

public class J5c_21x1 {
    J5c_21x1 baba;
    J5c_21x1 anne;
    Vector<J5c_21x1> evlatlar;
    private String isim;

    public J5c_21x1 (String isim) {
        this.isim = isim;
        anne = baba = null;
        evlatlar = new Vector<J5c_21x1>();
    } // J5c_21x1(..) kurucusu sonu...

    // Bu metod anne, baba ve �ocuklar� birbirine ba�lar...
    public static void aileyiBa�la (J5c_21x1 ana, J5c_21x1 peder, J5c_21x1[] �ocuklar) {
        for (J5c_21x1 �ocuk : �ocuklar) {
            peder.evlatlar.addElement (�ocuk);
            ana.evlatlar.addElement (�ocuk);
            �ocuk.baba = peder;
            �ocuk.anne = ana;
        } // for d�ng�s� sonu...
    } // aileyiBa�la(..) metodu sonu...

    public String toString() { return isim; }
    public String getName() { return isim; }
    public J5c_21x1 getFather() { return baba; }
    public J5c_21x1 getMother() { return anne; }
    public int getChildCount() { return evlatlar.size(); }
    public J5c_21x1 getChildAt (int i) {return (J5c_21x1)evlatlar.elementAt (i);}
    public int getIndexOfChild (J5c_21x1 �ocuk) {return evlatlar.indexOf (�ocuk);}
} // J5c_21x1 s�n�f� sonu...