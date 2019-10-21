// J2g_2.java: CountVowels (SaySeslileri) örneði.

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class J2g_2 {
    public static boolean sesliMi (char krk) {
        return krk == 'a' || krk == 'A' || krk == 'e' || krk == 'E' ||
            krk == 'ý' || krk == 'I'  || krk == 'i' || krk == 'Ý' ||
            krk == 'o' || krk == 'O'  || krk == 'ö' || krk == 'Ö' ||
            krk == 'u' || krk == 'U' || krk == 'ü' || krk == 'Ü';
    } // sesliMi(..) metodu sonu...

    public static void main (String[] args) {
        if (args.length < 1) {
            System.err.println ("Sesli harf kontrolu için kelime argümaný girilmeli!");
            System.exit (1);
        } // if kararý sonu...

        System.out.print ("Girilen dizge: [");
        for (int i = 0; i < args.length; i++) System.out.print (args[i] + " "); System.out.print ("]\n");

        Map<String, Integer> msi = new HashMap<>(); // msi:MapStringÝnteger...

        int toplamSesli = 0;
        for (String kelime : args) {
            int sesliSayýsý = 0;
            int uzunluk = kelime.length();

            for (int i = 0; i < uzunluk; ++i) // Argüman kelimenin seslileri sayýlýyor...
                if (sesliMi (kelime.charAt (i))) ++sesliSayýsý;

            toplamSesli += sesliSayýsý;
            msi.put (kelime, sesliSayýsý); // Herbir Map elemaný olarak kelime ve sesli sayýsý kaydediliyor...
        } // for-String döngüsü sonu...

        System.out.println ("\nArgüman kelime ve sesli sayýsý HasMap listesi==>");
        Set<String> küme = msi.keySet();
        int i = 0;
        for (String k : küme) {
            System.out.println (i + ".argüman: [" + k + "] ve sesli sayýsý: [" + msi.get (k) + "]");
            i++;
        } // for döngüsü sonu...
        System.out.println ("\nToplam: " + i + " kelime ve: " + toplamSesli + " sesli harf.");
    } // main(..) metodu sonu...
} // J2g_2 sýnýfý sonu...

/* Çýktý:
**  >java J2g_2  **
Sesli harf kontrolu için kelime argümaný girilmeli!

**  >java J2g_2 Sesli harf kontrolu için kelime argümaný girilmeli! ** TEKRAR
Girilen dizge: [Sesli harf kontrolu için kelime argümaný girilmeli! ]

Argüman kelime ve sesli sayýsý HasMap listesi==>
0.argüman: [için] ve sesli sayýsý: [2]
1.argüman: [argümaný] ve sesli sayýsý: [4]
2.argüman: [girilmeli!] ve sesli sayýsý: [4]
3.argüman: [kelime] ve sesli sayýsý: [3]
4.argüman: [harf] ve sesli sayýsý: [1]
5.argüman: [kontrolu] ve sesli sayýsý: [3]
6.argüman: [Sesli] ve sesli sayýsý: [2]

Toplam: 7 kelime ve: 19 sesli harf.
*/