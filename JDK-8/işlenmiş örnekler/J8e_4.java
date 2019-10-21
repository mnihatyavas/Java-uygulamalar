// J8e_4.java: CollatorDemo (KýyaslayýcýGösterisi) örneði.

import java.util.Locale;
import java.text.Collator;

public class J8e_4 {
    public static void kýyaslamaTesti() {
        Collator kýyaslayýcým = Collator.getInstance (new Locale ("en", "US"));
        System.out.println ("Kýyaslayýcý testi deðerleri:\n==>[dizge1>dizge2-->+1; dizge1<dizge2-->-1; dizge1=dizge2-->0]");
        System.out.println ("--------------------------------------------------------------");
        System.out.println ("1. Kýyasla('abc','def'):" + kýyaslayýcým.compare ("abc", "def"));
        System.out.println ("2. Kýyasla('rtf','rtf'):" + kýyaslayýcým.compare ("rtf", "rtf"));
        System.out.println ("3. Kýyasla('xyz','abc'):" + kýyaslayýcým.compare ("xyz", "abc"));
    } // kýyaslamaTesti() metodu sonu...

    public static void dizgeleriArtanSýrala (Collator kýyaslayýcý, String[] kelimeler) {
        String dizge;
        for (int i = 0; i < kelimeler.length; i++) {
            for (int j = i + 1; j < kelimeler.length; j++) {
                // Bubble, ikili baloncuk sýralama...
                if (kýyaslayýcý.compare (kelimeler[i], kelimeler[j] ) > 0 ) {
                    // Yerdeðiþtir:  kelimeler[i]<-->kelimeler[j] 
                    dizge = kelimeler[i];
                    kelimeler[i] = kelimeler[j];
                    kelimeler[j] = dizge;
                } // if kararý sonu...
            } // for-j döngüsü sonu...
        } // for-i döngüsü sonu...
    } // dizgeleriArtanSýrala(..) metodu sonu...

    public static void dizgeleriYaz (String [] kelimeler) {
        for (int i = 0; i < kelimeler.length; i++) System.out.println (kelimeler[i]);
    } // dizgeleriYaz(..) metodu sonu...

    static public void main (String[] args) {
        kýyaslamaTesti();

        Collator fr_FR_Kýyaslayýcý = Collator.getInstance (new Locale ("fr", "FR"));
        Collator en_US_Kýyaslayýcý = Collator.getInstance (new Locale ("en", "US"));
        Collator tr_TR_Kýyaslayýcý = Collator.getInstance (new Locale ("tr", "TR")); 

        String þapkalý_e = new String ("\u00EA"); // hex:234 = ascii:136 = ê
        String çentikli_e = new String ("\u00E9"); // hex:233 = ascii:130 = é
 
        String þeftali_fr = "p" + çentikli_e + "ch" + çentikli_e; // + "=þeftali_fr"
        String günah_fr = "p" + þapkalý_e + "che"; // + "=günah_fr"
 
        String [] kelimeler = {
            þeftali_fr,
            günah_fr,
            "peach", // + "=þeftali_en"
            "sin", // + "=günah_en"
            "þema",
            "ýrmak",
            "çadýr"
        }; // kelimeler dizisi sonu...

        System.out.println ("\n==========Artan sýralamada yerel farklar==========");
        dizgeleriArtanSýrala (fr_FR_Kýyaslayýcý, kelimeler);
        System.out.println ("Yerel: fr_FR");
        dizgeleriYaz (kelimeler);

        dizgeleriArtanSýrala (en_US_Kýyaslayýcý, kelimeler);
        System.out.println ("\nYerel: en_US");
        dizgeleriYaz (kelimeler);

        dizgeleriArtanSýrala (tr_TR_Kýyaslayýcý, kelimeler);
        System.out.println ("\nYerel: tr_TR");
        dizgeleriYaz (kelimeler);
    } // main(..) metodu sonu...
} // J8e_4 sýnýfý sonu...

/* Çýktý:
**  >java J8e_4  **
Kýyaslayýcý testi deðerleri:
==>[dizge1>dizge2-->+1; dizge1<dizge2-->-1; dizge1=dizge2-->0]
--------------------------------------------------------------
1. Kýyasla('abc','def'):-1
2. Kýyasla('rtf','rtf'):0
3. Kýyasla('xyz','abc'):1

==========Artan sýralamada yerel farklar==========
Yerel: fr_FR
çadýr
peach
pêche
péché
þema
sin
ýrmak

Yerel: en_US
çadýr
peach
péché
pêche
þema
sin
ýrmak

Yerel: tr_TR
çadýr
ýrmak
peach
péché
pêche
sin
þema
*/