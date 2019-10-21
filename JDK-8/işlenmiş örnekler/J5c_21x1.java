// J5c_21x1.java: Person (Þahýs) alt-örneði.

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

    // Bu metod anne, baba ve çocuklarý birbirine baðlar...
    public static void aileyiBaðla (J5c_21x1 ana, J5c_21x1 peder, J5c_21x1[] çocuklar) {
        for (J5c_21x1 çocuk : çocuklar) {
            peder.evlatlar.addElement (çocuk);
            ana.evlatlar.addElement (çocuk);
            çocuk.baba = peder;
            çocuk.anne = ana;
        } // for döngüsü sonu...
    } // aileyiBaðla(..) metodu sonu...

    public String toString() { return isim; }
    public String getName() { return isim; }
    public J5c_21x1 getFather() { return baba; }
    public J5c_21x1 getMother() { return anne; }
    public int getChildCount() { return evlatlar.size(); }
    public J5c_21x1 getChildAt (int i) {return (J5c_21x1)evlatlar.elementAt (i);}
    public int getIndexOfChild (J5c_21x1 çocuk) {return evlatlar.indexOf (çocuk);}
} // J5c_21x1 sýnýfý sonu...