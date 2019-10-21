// J2c_18x.java: Stack (Yýðýn) alt-örneði.

import java.util.List;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class J2c_18x {
    private List<Object> liste;

    // J2c_18x() kurucusu...
    public J2c_18x (int ebat) {liste = new ArrayList<Object>();}

    public void ekle (Object birim) {liste.add (birim);}

    public Object çýkar() {
        if (liste.size() == 0) throw new EmptyStackException();
        return liste.remove (liste.size() - 1);
    } // çýkar() metodu sonu...

    public boolean boþMu() {return liste.isEmpty();}
} // J2c_18x sýnýfý sonu...