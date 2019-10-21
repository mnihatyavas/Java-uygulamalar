// J6b_3hx.java: Name (Ad) alt-�rne�i.

public class J6b_3hx implements Comparable<J6b_3hx> {// java.lang.Comparable;
    private final String ad, soyad;

    public J6b_3hx (String ad, String soyad) {// Kurucu...
        if (ad == null || soyad == null) throw new NullPointerException(); // java.lang.*
        this.ad = ad;
        this.soyad = soyad;
    } // J6b_3hx(..) kurucusu sonu...

    public boolean equals (Object nesne1) {
        if (!(nesne1 instanceof J6b_3hx)) return false;
        J6b_3hx nesne2 = (J6b_3hx) nesne1;
        return nesne2.ad.equals (ad) && nesne2.soyad.equals (soyad);
    } // equals(..) haz�r metodu sonu...

    public int hashCode() {return 31*ad.hashCode() + soyad.hashCode();}
    public String toString() {return ad + " " + soyad;}

    public int compareTo (J6b_3hx nesne) {
        int sonK�yas = soyad.compareTo (nesne.soyad);
        return (sonK�yas != 0 ? sonK�yas : ad.compareTo (nesne.ad));
    } // compareTo(..) haz�r metodu sonu...
} // J6b_3hx s�n�f� sonu...