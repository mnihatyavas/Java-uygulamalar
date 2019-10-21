// J8e_7.java: RulesDemo (-Alfabetik-KurallarGösterisi) örneði.

import java.text.Collator;
import java.text.RuleBasedCollator;
import java.text.ParseException;

public class J8e_7 {
    public static void kelimeleriSýrala (Collator kýyaslayýcý, String[] kelimeler) {
        String aracý;
        for (int i = 0; i < kelimeler.length; i++) {
            for (int j = i + 1; j < kelimeler.length; j++) {
                if (kýyaslayýcý.compare (kelimeler[i], kelimeler[j]) > 0 ) {
                    // Ýkili ardýþýk artan bubble/baloncuk sýralama....
                    aracý = kelimeler[i];
                    kelimeler[i] = kelimeler[j];
                    kelimeler[j] = aracý;
                } // if kararý sonu...
            } // for-j döngüsü sonu...
        } // for-i döngüsü sonu...
    } // kelimeleriSýrala(..) metodu sonu...

    public static void kelimeleriYaz (String [] kelimeler) {
       for (int i = 0; i < kelimeler.length; i++) System.out.println (kelimeler[i]);
    } // kelimeleriYaz(..) metodu sonu...

    static public void main (String[] args) {
        String ingilizAlfabesi = ("< a,A < b,B < c,C < d,D < e,E < f,F < g,G < h,H < i,I < j,J < k,K < l,L < m,M < n,N < o,O < p,P < q,Q < r,R < s,S < t,T < u,U < v,V < w,W < x,X < y,Y < z,Z");
        String türkAlfabesi = ("< a,A < b,B < c,C < ç,Ç < d,D < e,E < f,F < g,G < ð,Ð < h,H < ý,I < i,Ý < j,J < k,K < l,L < m,M < n,N < o,O < ö,Ö < p,P < r,R < s,S < þ,Þ < t,T < u,U <ü,Ü < v,V < y,Y < z,Z");
  
        String ny  = new String ("\u00F1");
        String NY = new String ("\u00D1");
        String ispanyolAlfabesi = "< a,A < b,B < c,C < ch, cH, Ch, CH < d,D < e,E < f,F < g,G < h,H < i,I < j,J < k,K < l,L < ll, lL, Ll, LL < m,M < n,N < " + ny + "," + NY + " < o,O < p,P < q,Q < r,R < s,S < t,T < u,U < v,V < w,W < x,X < y,Y < z,Z";

        String [] kelimeler = {
            "luz",
            "ma" + ny + "ana",
            "ýrmak",
            "zürafa",
            "çamaþýr",
            "þakaðý",
            "öküz",
            "curioso",
            "llama",
            "chalina"
        }; // kelimeler dizisi sonu...

        try {
            RuleBasedCollator ingilizKýyaslayýcý = new RuleBasedCollator (ingilizAlfabesi);
            RuleBasedCollator ispanyolKýyaslayýcý = new RuleBasedCollator (ispanyolAlfabesi);
            RuleBasedCollator türkKýyaslayýcý = new RuleBasedCollator (türkAlfabesi);

            System.out.println ("Ýngiliz alfabesiyle sýralama:\n-----------------------------");
            kelimeleriSýrala (ingilizKýyaslayýcý, kelimeler);
            kelimeleriYaz (kelimeler);

           System.out.println ("\nÝspanyol alfabesiyle sýralama:\n------------------------------");
           kelimeleriSýrala (ispanyolKýyaslayýcý, kelimeler);
           kelimeleriYaz (kelimeler);

           System.out.println ("\nTürk alfabesiyle sýralama:\n--------------------------");
           kelimeleriSýrala (türkKýyaslayýcý, kelimeler);
           kelimeleriYaz (kelimeler);
       }catch (ParseException ist) {System.err.println ("Alfabetik kýyaslamada çevirme hatasý");}
    } // main(..) metodu sonu...
} // J8e_7 sýnýfý sonu...

/* Çýktý:
**  >java J8e_7  **
Ýngiliz alfabesiyle sýralama:
-----------------------------
chalina
curioso
çamaþýr
llama
luz
mañana
öküz
þakaðý
zürafa
ýrmak

Ýspanyol alfabesiyle sýralama:
------------------------------
curioso
çamaþýr
chalina
luz
llama
mañana
öküz
þakaðý
zürafa
ýrmak

Türk alfabesiyle sýralama:
--------------------------
chalina
curioso
çamaþýr
ýrmak
llama
luz
mañana
öküz
þakaðý
zürafa
*/