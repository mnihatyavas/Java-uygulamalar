// J8e_7.java: RulesDemo (-Alfabetik-KurallarG�sterisi) �rne�i.

import java.text.Collator;
import java.text.RuleBasedCollator;
import java.text.ParseException;

public class J8e_7 {
    public static void kelimeleriS�rala (Collator k�yaslay�c�, String[] kelimeler) {
        String arac�;
        for (int i = 0; i < kelimeler.length; i++) {
            for (int j = i + 1; j < kelimeler.length; j++) {
                if (k�yaslay�c�.compare (kelimeler[i], kelimeler[j]) > 0 ) {
                    // �kili ard���k artan bubble/baloncuk s�ralama....
                    arac� = kelimeler[i];
                    kelimeler[i] = kelimeler[j];
                    kelimeler[j] = arac�;
                } // if karar� sonu...
            } // for-j d�ng�s� sonu...
        } // for-i d�ng�s� sonu...
    } // kelimeleriS�rala(..) metodu sonu...

    public static void kelimeleriYaz (String [] kelimeler) {
       for (int i = 0; i < kelimeler.length; i++) System.out.println (kelimeler[i]);
    } // kelimeleriYaz(..) metodu sonu...

    static public void main (String[] args) {
        String ingilizAlfabesi = ("< a,A < b,B < c,C < d,D < e,E < f,F < g,G < h,H < i,I < j,J < k,K < l,L < m,M < n,N < o,O < p,P < q,Q < r,R < s,S < t,T < u,U < v,V < w,W < x,X < y,Y < z,Z");
        String t�rkAlfabesi = ("< a,A < b,B < c,C < �,� < d,D < e,E < f,F < g,G < �,� < h,H < �,I < i,� < j,J < k,K < l,L < m,M < n,N < o,O < �,� < p,P < r,R < s,S < �,� < t,T < u,U <�,� < v,V < y,Y < z,Z");
  
        String ny  = new String ("\u00F1");
        String NY = new String ("\u00D1");
        String ispanyolAlfabesi = "< a,A < b,B < c,C < ch, cH, Ch, CH < d,D < e,E < f,F < g,G < h,H < i,I < j,J < k,K < l,L < ll, lL, Ll, LL < m,M < n,N < " + ny + "," + NY + " < o,O < p,P < q,Q < r,R < s,S < t,T < u,U < v,V < w,W < x,X < y,Y < z,Z";

        String [] kelimeler = {
            "luz",
            "ma" + ny + "ana",
            "�rmak",
            "z�rafa",
            "�ama��r",
            "�aka��",
            "�k�z",
            "curioso",
            "llama",
            "chalina"
        }; // kelimeler dizisi sonu...

        try {
            RuleBasedCollator ingilizK�yaslay�c� = new RuleBasedCollator (ingilizAlfabesi);
            RuleBasedCollator ispanyolK�yaslay�c� = new RuleBasedCollator (ispanyolAlfabesi);
            RuleBasedCollator t�rkK�yaslay�c� = new RuleBasedCollator (t�rkAlfabesi);

            System.out.println ("�ngiliz alfabesiyle s�ralama:\n-----------------------------");
            kelimeleriS�rala (ingilizK�yaslay�c�, kelimeler);
            kelimeleriYaz (kelimeler);

           System.out.println ("\n�spanyol alfabesiyle s�ralama:\n------------------------------");
           kelimeleriS�rala (ispanyolK�yaslay�c�, kelimeler);
           kelimeleriYaz (kelimeler);

           System.out.println ("\nT�rk alfabesiyle s�ralama:\n--------------------------");
           kelimeleriS�rala (t�rkK�yaslay�c�, kelimeler);
           kelimeleriYaz (kelimeler);
       }catch (ParseException ist) {System.err.println ("Alfabetik k�yaslamada �evirme hatas�");}
    } // main(..) metodu sonu...
} // J8e_7 s�n�f� sonu...

/* ��kt�:
**  >java J8e_7  **
�ngiliz alfabesiyle s�ralama:
-----------------------------
chalina
curioso
�ama��r
llama
luz
ma�ana
�k�z
�aka��
z�rafa
�rmak

�spanyol alfabesiyle s�ralama:
------------------------------
curioso
�ama��r
chalina
luz
llama
ma�ana
�k�z
�aka��
z�rafa
�rmak

T�rk alfabesiyle s�ralama:
--------------------------
chalina
curioso
�ama��r
�rmak
llama
luz
ma�ana
�k�z
�aka��
z�rafa
*/