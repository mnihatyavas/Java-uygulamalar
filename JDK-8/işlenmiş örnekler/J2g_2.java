// J2g_2.java: CountVowels (SaySeslileri) �rne�i.

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class J2g_2 {
    public static boolean sesliMi (char krk) {
        return krk == 'a' || krk == 'A' || krk == 'e' || krk == 'E' ||
            krk == '�' || krk == 'I'  || krk == 'i' || krk == '�' ||
            krk == 'o' || krk == 'O'  || krk == '�' || krk == '�' ||
            krk == 'u' || krk == 'U' || krk == '�' || krk == '�';
    } // sesliMi(..) metodu sonu...

    public static void main (String[] args) {
        if (args.length < 1) {
            System.err.println ("Sesli harf kontrolu i�in kelime arg�man� girilmeli!");
            System.exit (1);
        } // if karar� sonu...

        System.out.print ("Girilen dizge: [");
        for (int i = 0; i < args.length; i++) System.out.print (args[i] + " "); System.out.print ("]\n");

        Map<String, Integer> msi = new HashMap<>(); // msi:MapString�nteger...

        int toplamSesli = 0;
        for (String kelime : args) {
            int sesliSay�s� = 0;
            int uzunluk = kelime.length();

            for (int i = 0; i < uzunluk; ++i) // Arg�man kelimenin seslileri say�l�yor...
                if (sesliMi (kelime.charAt (i))) ++sesliSay�s�;

            toplamSesli += sesliSay�s�;
            msi.put (kelime, sesliSay�s�); // Herbir Map eleman� olarak kelime ve sesli say�s� kaydediliyor...
        } // for-String d�ng�s� sonu...

        System.out.println ("\nArg�man kelime ve sesli say�s� HasMap listesi==>");
        Set<String> k�me = msi.keySet();
        int i = 0;
        for (String k : k�me) {
            System.out.println (i + ".arg�man: [" + k + "] ve sesli say�s�: [" + msi.get (k) + "]");
            i++;
        } // for d�ng�s� sonu...
        System.out.println ("\nToplam: " + i + " kelime ve: " + toplamSesli + " sesli harf.");
    } // main(..) metodu sonu...
} // J2g_2 s�n�f� sonu...

/* ��kt�:
**  >java J2g_2  **
Sesli harf kontrolu i�in kelime arg�man� girilmeli!

**  >java J2g_2 Sesli harf kontrolu i�in kelime arg�man� girilmeli! ** TEKRAR
Girilen dizge: [Sesli harf kontrolu i�in kelime arg�man� girilmeli! ]

Arg�man kelime ve sesli say�s� HasMap listesi==>
0.arg�man: [i�in] ve sesli say�s�: [2]
1.arg�man: [arg�man�] ve sesli say�s�: [4]
2.arg�man: [girilmeli!] ve sesli say�s�: [4]
3.arg�man: [kelime] ve sesli say�s�: [3]
4.arg�man: [harf] ve sesli say�s�: [1]
5.arg�man: [kontrolu] ve sesli say�s�: [3]
6.arg�man: [Sesli] ve sesli say�s�: [2]

Toplam: 7 kelime ve: 19 sesli harf.
*/