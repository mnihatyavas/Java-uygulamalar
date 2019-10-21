// J2fq_4.java: ComputeResult (TahminetSonucu) örneði.

public class J2fq_4 {
    public static void main (String[] args) {
        String dizge1 = args.length > 0? args[0] : "software";
        String dizge2 = args.length > 1? args[1] : "hi";

        StringBuilder sonuç = new StringBuilder (dizge2);
        int endeks = dizge1.indexOf ('a'); // 5...

/*1*/   sonuç.setCharAt (0, dizge1.charAt (0)); // "si"
/*2*/   sonuç.setCharAt (1, dizge1.charAt (dizge1.length() - 1)); // "se"
/*3*/   sonuç.insert (1, dizge1.charAt (4)); // "swe"
/*4*/   sonuç.append (dizge1.substring (1, 4)); // "sweoft"
/*5*/   sonuç.insert (3, (dizge1.substring (endeks, endeks + 2) + " ")); // "swear oft"

        System.out.println ("Girilen dizge: [" + dizge1 + "]");
        System.out.println ("5 iþlem sonucu: [" + sonuç + "]");
    } // main(..) metodu sonu...
} // J2fq_4 sýnýfý sonu...

/* Çýktý:
**  >java J2fq_4  **
Girilen dizge: [software]
5 iþlem sonucu: [swear oft]

**  >java J2fq_4 "M.Nihat Yavaþ" selam  ** TEKRAR
Girilen dizge: [M.Nihat Yavaþ]
5 iþlem sonucu: [Mhþat lam.Ni]
*/