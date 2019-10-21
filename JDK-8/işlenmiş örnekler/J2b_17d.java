// J2b_17d.java: SwitchDemoFallThrough (SwitchG�sterimiBa�ar�s�zl���) �rne�i.

public class J2b_17d {
    public static void main (String[] args) {
        java.util.ArrayList<String> ayListesi = new java.util.ArrayList<String>();

        int ay;
        try {ay = Integer.valueOf (args[0]);} catch (Exception ist) {ay = -1;}

        switch (ay) {
            case 1: ayListesi.add ("Ocak"); // Herbir case sonuna "break" konulmal�...
            case 2: ayListesi.add ("�ubat");
            case 3: ayListesi.add ("Mart");
            case 4: ayListesi.add ("Nisan");
            case 5: ayListesi.add ("May�s");
            case 6: ayListesi.add ("Haziran");
            case 7: ayListesi.add ("Temmuz");
            case 8: ayListesi.add ("A�ustos");
            case 9: ayListesi.add ("Eyl�l");
            case 10: ayListesi.add ("Ekim");
            case 11: ayListesi.add ("Kas�m");
            case 12: ayListesi.add ("Aral�k"); break;
            default: break;
        } // switch-case blo�u sonu...

        if (ayListesi.isEmpty()) System.out.println ("Ge�ersiz ay rakam� giri�i!");
        else for (String ayAd� : ayListesi) System.out.println (ayAd�);
    } // main(..) metodu sonu...
} // J2b_17d s�n�f� sonu...

/* ��kt�:
**  >java J2b_17d  **
Ge�ersiz ay rakam� giri�i!

**  >java J2b_17d 4  ** TEKRAR
Nisan
May�s
Haziran
Temmuz
A�ustos
Eyl�l
Ekim
Kas�m
Aral�k

**  >java J2b_17d 1  ** TEKRAR
Ocak
�ubat
Mart
Nisan
May�s
Haziran
Temmuz
A�ustos
Eyl�l
Ekim
Kas�m
Aral�k

**  >java J2b_17d 12  ** TEKRAR
Aral�k
*/