// J2b_17d.java: SwitchDemoFallThrough (SwitchGösterimiBaþarýsýzlýðý) örneði.

public class J2b_17d {
    public static void main (String[] args) {
        java.util.ArrayList<String> ayListesi = new java.util.ArrayList<String>();

        int ay;
        try {ay = Integer.valueOf (args[0]);} catch (Exception ist) {ay = -1;}

        switch (ay) {
            case 1: ayListesi.add ("Ocak"); // Herbir case sonuna "break" konulmalý...
            case 2: ayListesi.add ("Þubat");
            case 3: ayListesi.add ("Mart");
            case 4: ayListesi.add ("Nisan");
            case 5: ayListesi.add ("Mayýs");
            case 6: ayListesi.add ("Haziran");
            case 7: ayListesi.add ("Temmuz");
            case 8: ayListesi.add ("Aðustos");
            case 9: ayListesi.add ("Eylül");
            case 10: ayListesi.add ("Ekim");
            case 11: ayListesi.add ("Kasým");
            case 12: ayListesi.add ("Aralýk"); break;
            default: break;
        } // switch-case bloðu sonu...

        if (ayListesi.isEmpty()) System.out.println ("Geçersiz ay rakamý giriþi!");
        else for (String ayAdý : ayListesi) System.out.println (ayAdý);
    } // main(..) metodu sonu...
} // J2b_17d sýnýfý sonu...

/* Çýktý:
**  >java J2b_17d  **
Geçersiz ay rakamý giriþi!

**  >java J2b_17d 4  ** TEKRAR
Nisan
Mayýs
Haziran
Temmuz
Aðustos
Eylül
Ekim
Kasým
Aralýk

**  >java J2b_17d 1  ** TEKRAR
Ocak
Þubat
Mart
Nisan
Mayýs
Haziran
Temmuz
Aðustos
Eylül
Ekim
Kasým
Aralýk

**  >java J2b_17d 12  ** TEKRAR
Aralýk
*/