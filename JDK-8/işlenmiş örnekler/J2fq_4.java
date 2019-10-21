// J2fq_4.java: ComputeResult (TahminetSonucu) �rne�i.

public class J2fq_4 {
    public static void main (String[] args) {
        String dizge1 = args.length > 0? args[0] : "software";
        String dizge2 = args.length > 1? args[1] : "hi";

        StringBuilder sonu� = new StringBuilder (dizge2);
        int endeks = dizge1.indexOf ('a'); // 5...

/*1*/   sonu�.setCharAt (0, dizge1.charAt (0)); // "si"
/*2*/   sonu�.setCharAt (1, dizge1.charAt (dizge1.length() - 1)); // "se"
/*3*/   sonu�.insert (1, dizge1.charAt (4)); // "swe"
/*4*/   sonu�.append (dizge1.substring (1, 4)); // "sweoft"
/*5*/   sonu�.insert (3, (dizge1.substring (endeks, endeks + 2) + " ")); // "swear oft"

        System.out.println ("Girilen dizge: [" + dizge1 + "]");
        System.out.println ("5 i�lem sonucu: [" + sonu� + "]");
    } // main(..) metodu sonu...
} // J2fq_4 s�n�f� sonu...

/* ��kt�:
**  >java J2fq_4  **
Girilen dizge: [software]
5 i�lem sonucu: [swear oft]

**  >java J2fq_4 "M.Nihat Yava�" selam  ** TEKRAR
Girilen dizge: [M.Nihat Yava�]
5 i�lem sonucu: [Mh�at lam.Ni]
*/