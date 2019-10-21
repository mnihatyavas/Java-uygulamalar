// J6b_3a.java: Anagrams2 (Anagramlar2) �rne�i.

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;

/* Anagram bir kelimenin varyasyonlar�d�r; bu program anagram yapmaz,
 * girilen metin dosyas�n�n �nce t�m d�k�m�n�, sonra da belirtilen adet ve
 * fazlas� tekrarl� kelimeleri gruplayarak �oktan-aza s�ralamadan d�k�m�n�
 * ger�ekle�tirir...
 */
public class J6b_3a {
    public static void main (String[] arg�manlar) {
        if (arg�manlar.length < 2) {
            System.out.println ("Mutlaka 2 arg�man [1:Metin dosyas�; 2:Enk���k ayn�l�k grup ebat�] girmelisiniz!");
            System.exit (0);
        } // if karar� sonu...

        int asgariGrupland�rmaSay�s� = 0;
        try {asgariGrupland�rmaSay�s� = Integer.parseInt (arg�manlar[1]);
        }catch (Exception ist) {asgariGrupland�rmaSay�s� = 1;}

        if (asgariGrupland�rmaSay�s� < 1) asgariGrupland�rmaSay�s� = 1;
        // Arg�man-0 dosyas�n� okuyup, ��kt�ya d�k�p, k�ymal� harita listesine anahtar-de�er �iftli kelime-kelime ekleyecek...
        Map<String, List<String>> k�ymal�HaritaListesi = new HashMap<String, List<String>>();

        try {Scanner oku = new Scanner (new File (arg�manlar[0]));
            System.out.println ("Dosyan�n ba�tan-sona t�m kelimelerinin altalta a-z anagraml� listesi==>");
            int i = 1;
            while (oku.hasNext()) {
                String kelime = oku.next();
                String a_zAnagram� = a_zAnagramla�t�r (kelime);
                System.out.println (i + ".kelime: [" + kelime + " = " + a_zAnagram� + "]"); i +=1;
                List<String> liste = k�ymal�HaritaListesi.get (a_zAnagram�);
                if (liste == null) k�ymal�HaritaListesi.put (a_zAnagram�, liste = new ArrayList<String>());
                liste.add (kelime);
            } // while d�ng�s� sonu...
        } catch (IOException ist) {System.err.println (ist);System.exit (1);
        } // try-catch blo�u sonu...

        System.out.println ("\n" + asgariGrupland�rmaSay�s� + " ve �st� tekrarl� kelimelerin k�ymal�harita ak�lermez s�ralama form�ll� listesi==>");
        for (List<String> listeEleman� : k�ymal�HaritaListesi.values())
            if (listeEleman�.size() >= asgariGrupland�rmaSay�s�) System.out.println (listeEleman�.size() + ": " + listeEleman�);
    } // main(..) metodu sonu...

    // Kelimeyi a-z gruplay�p s�ralar (bir �e�it anagram'la�t�rma)...
    private static String a_zAnagramla�t�r (String klm) {
        char[] anagram = klm.toCharArray();
        Arrays.sort (anagram);
        return new String (anagram);
    } // a_zAnagramla�t�r(..) metodu sonu...
} // J6b_3a s�n�f� sonu...

/* ��kt�:
**  >java J6b_3a  **
Mutlaka 2 arg�man [1:Metin dosyas�; 2:Enk���k ayn�l�k grup ebat�] girmelisiniz!

**  >java J6b_3a html/rehber.txt 2  **
Dosyan�n ba�tan-sona t�m kelimelerinin altalta a-z anagraml� listesi==>
1.kelime: [�stiyorsan�z = ainorsstyz��]
2.kelime: [ba�ka = aabk�]
3.kelime: [bir = bir]
4.kelime: [ba�l�k = abkl��]
5.kelime: [ve = ev]
6.kelime: [sonluk = klnosu]
7.kelime: [metinleri = eeiilmnrt]
8.kelime: [girebilirsiniz. = .begiiiiilnrrsz]
9.kelime: [Sayfa = Saafy]
10.kelime: [sonu = nosu]
11.kelime: [numaraland�rmas� = aaaadlmmnnrrsu��]
12.kelime: [i�in = iin�]
13.kelime: [{0} = 0{}]
14.kelime: [sembolle = beellmos]
15.kelime: [birlikte = beiiklrt]
16.kelime: [herhangi = aeghhinr]
17.kelime: [bir = bir]
18.kelime: [metni = eimnt]
19.kelime: [kullanabilirsiniz. = .aabiiiiklllnnrsuz]
20.kelime: [Yazd�rma = Yaadmrz�]
21.kelime: [a�amalar�n� = aaaalmnr���]
22.kelime: [takip = aikpt]
23.kelime: [etmek = eekmt]
24.kelime: [isterseniz = eeiinrsstz]
25.kelime: ["Yaz = "Yaz]
26.kelime: [Ad�mlar� = Aadlmr��]
27.kelime: [G�ster" = "Gerst�]
28.kelime: [kutusu = kstuuu]
29.kelime: [�entiklenmeli, = ,eeeiikllmnnt�]
30.kelime: [iptal = ailpt]
31.kelime: [i�in = iin�]
32.kelime: [ise = eis]
33.kelime: [kutu = ktuu]
34.kelime: [�entiksizlenmelidir. = .deeeiiiikllmnnrstz�]
35.kelime: [Yazd�rma = Yaadmrz�]
36.kelime: [i�lemi = eiilm�]
37.kelime: [s�rerken = eeknrrs�]
38.kelime: [pencereyle = ceeeelnpry]
39.kelime: [etkile�imi = eeiiiklmt�]
40.kelime: [s�rd�rmek = dekmrrs��]
41.kelime: [isterseniz = eeiinrsstz]
42.kelime: ["Arkaplanda = "Aaaadklnpr]
43.kelime: [Yazd�r" = "Yadrz�]
44.kelime: [�entiklenmeli, = ,eeeiikllmnnt�]
45.kelime: [yazd�rma = aadmryz�]
46.kelime: [tamamlan�ncaya = aaaaaclmmnnty�]
47.kelime: [kadar = aadkr]
48.kelime: [i�erikle = eeiiklr�]
49.kelime: [etkile�imi = eeiiiklmt�]
50.kelime: [dondurmak = addkmnoru]
51.kelime: [i�in = iin�]
52.kelime: [ise = eis]
53.kelime: [�entiksizlenmelidir. = .deeeiiiikllmnnrstz�]
54.kelime: [M�dahaleli = Maadehill�]
55.kelime: [(d�zenleme = (deeelmnz�]
56.kelime: [ve = ev]
57.kelime: [de�i�iklikler = deeiiikkllr��]
58.kelime: [m�mk�n) = )kmmn��]
59.kelime: [metin = eimnt]
60.kelime: [alan� = aaln�]
61.kelime: [i�eriklerini = eeiiiiklnrr�]
62.kelime: [son = nos]
63.kelime: [�ekliyle = eeiklly�]
64.kelime: [yazd�rmak = aadkmryz�]
65.kelime: [i�in = iin�]
66.kelime: [de = de]
67.kelime: [ikonlu = iklnou]
68.kelime: ["Yazd�r!" = !""Yadrz�]
69.kelime: [d��mesini = deiimns��]
70.kelime: [t�klaman�z = aaklmntz��]
71.kelime: [yeterlidir. = .deeiilrrty]

2 ve �st� tekrarl� kelimelerin k�ymal�harita ak�lermez kaotik listesi==>
2: [etkile�imi, etkile�imi]
2: [bir, bir]
2: [metni, metin]
2: [Yazd�rma, Yazd�rma]
2: [isterseniz, isterseniz]
2: [�entiksizlenmelidir., �entiksizlenmelidir.]
2: [ve, ve]
2: [�entiklenmeli,, �entiklenmeli,]
2: [ise, ise]
4: [i�in, i�in, i�in, i�in]
*/