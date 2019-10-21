// J8e_4.java: CollatorDemo (K�yaslay�c�G�sterisi) �rne�i.

import java.util.Locale;
import java.text.Collator;

public class J8e_4 {
    public static void k�yaslamaTesti() {
        Collator k�yaslay�c�m = Collator.getInstance (new Locale ("en", "US"));
        System.out.println ("K�yaslay�c� testi de�erleri:\n==>[dizge1>dizge2-->+1; dizge1<dizge2-->-1; dizge1=dizge2-->0]");
        System.out.println ("--------------------------------------------------------------");
        System.out.println ("1. K�yasla('abc','def'):" + k�yaslay�c�m.compare ("abc", "def"));
        System.out.println ("2. K�yasla('rtf','rtf'):" + k�yaslay�c�m.compare ("rtf", "rtf"));
        System.out.println ("3. K�yasla('xyz','abc'):" + k�yaslay�c�m.compare ("xyz", "abc"));
    } // k�yaslamaTesti() metodu sonu...

    public static void dizgeleriArtanS�rala (Collator k�yaslay�c�, String[] kelimeler) {
        String dizge;
        for (int i = 0; i < kelimeler.length; i++) {
            for (int j = i + 1; j < kelimeler.length; j++) {
                // Bubble, ikili baloncuk s�ralama...
                if (k�yaslay�c�.compare (kelimeler[i], kelimeler[j] ) > 0 ) {
                    // Yerde�i�tir:  kelimeler[i]<-->kelimeler[j] 
                    dizge = kelimeler[i];
                    kelimeler[i] = kelimeler[j];
                    kelimeler[j] = dizge;
                } // if karar� sonu...
            } // for-j d�ng�s� sonu...
        } // for-i d�ng�s� sonu...
    } // dizgeleriArtanS�rala(..) metodu sonu...

    public static void dizgeleriYaz (String [] kelimeler) {
        for (int i = 0; i < kelimeler.length; i++) System.out.println (kelimeler[i]);
    } // dizgeleriYaz(..) metodu sonu...

    static public void main (String[] args) {
        k�yaslamaTesti();

        Collator fr_FR_K�yaslay�c� = Collator.getInstance (new Locale ("fr", "FR"));
        Collator en_US_K�yaslay�c� = Collator.getInstance (new Locale ("en", "US"));
        Collator tr_TR_K�yaslay�c� = Collator.getInstance (new Locale ("tr", "TR")); 

        String �apkal�_e = new String ("\u00EA"); // hex:234 = ascii:136 = �
        String �entikli_e = new String ("\u00E9"); // hex:233 = ascii:130 = �
 
        String �eftali_fr = "p" + �entikli_e + "ch" + �entikli_e; // + "=�eftali_fr"
        String g�nah_fr = "p" + �apkal�_e + "che"; // + "=g�nah_fr"
 
        String [] kelimeler = {
            �eftali_fr,
            g�nah_fr,
            "peach", // + "=�eftali_en"
            "sin", // + "=g�nah_en"
            "�ema",
            "�rmak",
            "�ad�r"
        }; // kelimeler dizisi sonu...

        System.out.println ("\n==========Artan s�ralamada yerel farklar==========");
        dizgeleriArtanS�rala (fr_FR_K�yaslay�c�, kelimeler);
        System.out.println ("Yerel: fr_FR");
        dizgeleriYaz (kelimeler);

        dizgeleriArtanS�rala (en_US_K�yaslay�c�, kelimeler);
        System.out.println ("\nYerel: en_US");
        dizgeleriYaz (kelimeler);

        dizgeleriArtanS�rala (tr_TR_K�yaslay�c�, kelimeler);
        System.out.println ("\nYerel: tr_TR");
        dizgeleriYaz (kelimeler);
    } // main(..) metodu sonu...
} // J8e_4 s�n�f� sonu...

/* ��kt�:
**  >java J8e_4  **
K�yaslay�c� testi de�erleri:
==>[dizge1>dizge2-->+1; dizge1<dizge2-->-1; dizge1=dizge2-->0]
--------------------------------------------------------------
1. K�yasla('abc','def'):-1
2. K�yasla('rtf','rtf'):0
3. K�yasla('xyz','abc'):1

==========Artan s�ralamada yerel farklar==========
Yerel: fr_FR
�ad�r
peach
p�che
p�ch�
�ema
sin
�rmak

Yerel: en_US
�ad�r
peach
p�ch�
p�che
�ema
sin
�rmak

Yerel: tr_TR
�ad�r
�rmak
peach
p�ch�
p�che
sin
�ema
*/