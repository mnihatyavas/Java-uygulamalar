// J6b_3a.java: Anagrams2 (Anagramlar2) örneði.

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;

/* Anagram bir kelimenin varyasyonlarýdýr; bu program anagram yapmaz,
 * girilen metin dosyasýnýn önce tüm dökümünü, sonra da belirtilen adet ve
 * fazlasý tekrarlý kelimeleri gruplayarak çoktan-aza sýralamadan dökümünü
 * gerçekleþtirir...
 */
public class J6b_3a {
    public static void main (String[] argümanlar) {
        if (argümanlar.length < 2) {
            System.out.println ("Mutlaka 2 argüman [1:Metin dosyasý; 2:Enküçük aynýlýk grup ebatý] girmelisiniz!");
            System.exit (0);
        } // if kararý sonu...

        int asgariGruplandýrmaSayýsý = 0;
        try {asgariGruplandýrmaSayýsý = Integer.parseInt (argümanlar[1]);
        }catch (Exception ist) {asgariGruplandýrmaSayýsý = 1;}

        if (asgariGruplandýrmaSayýsý < 1) asgariGruplandýrmaSayýsý = 1;
        // Argüman-0 dosyasýný okuyup, çýktýya döküp, kýymalý harita listesine anahtar-deðer çiftli kelime-kelime ekleyecek...
        Map<String, List<String>> kýymalýHaritaListesi = new HashMap<String, List<String>>();

        try {Scanner oku = new Scanner (new File (argümanlar[0]));
            System.out.println ("Dosyanýn baþtan-sona tüm kelimelerinin altalta a-z anagramlý listesi==>");
            int i = 1;
            while (oku.hasNext()) {
                String kelime = oku.next();
                String a_zAnagramý = a_zAnagramlaþtýr (kelime);
                System.out.println (i + ".kelime: [" + kelime + " = " + a_zAnagramý + "]"); i +=1;
                List<String> liste = kýymalýHaritaListesi.get (a_zAnagramý);
                if (liste == null) kýymalýHaritaListesi.put (a_zAnagramý, liste = new ArrayList<String>());
                liste.add (kelime);
            } // while döngüsü sonu...
        } catch (IOException ist) {System.err.println (ist);System.exit (1);
        } // try-catch bloðu sonu...

        System.out.println ("\n" + asgariGruplandýrmaSayýsý + " ve üstü tekrarlý kelimelerin kýymalýharita akýlermez sýralama formüllü listesi==>");
        for (List<String> listeElemaný : kýymalýHaritaListesi.values())
            if (listeElemaný.size() >= asgariGruplandýrmaSayýsý) System.out.println (listeElemaný.size() + ": " + listeElemaný);
    } // main(..) metodu sonu...

    // Kelimeyi a-z gruplayýp sýralar (bir çeþit anagram'laþtýrma)...
    private static String a_zAnagramlaþtýr (String klm) {
        char[] anagram = klm.toCharArray();
        Arrays.sort (anagram);
        return new String (anagram);
    } // a_zAnagramlaþtýr(..) metodu sonu...
} // J6b_3a sýnýfý sonu...

/* Çýktý:
**  >java J6b_3a  **
Mutlaka 2 argüman [1:Metin dosyasý; 2:Enküçük aynýlýk grup ebatý] girmelisiniz!

**  >java J6b_3a html/rehber.txt 2  **
Dosyanýn baþtan-sona tüm kelimelerinin altalta a-z anagramlý listesi==>
1.kelime: [Ýstiyorsanýz = ainorsstyzÝý]
2.kelime: [baþka = aabkþ]
3.kelime: [bir = bir]
4.kelime: [baþlýk = abklýþ]
5.kelime: [ve = ev]
6.kelime: [sonluk = klnosu]
7.kelime: [metinleri = eeiilmnrt]
8.kelime: [girebilirsiniz. = .begiiiiilnrrsz]
9.kelime: [Sayfa = Saafy]
10.kelime: [sonu = nosu]
11.kelime: [numaralandýrmasý = aaaadlmmnnrrsuýý]
12.kelime: [için = iinç]
13.kelime: [{0} = 0{}]
14.kelime: [sembolle = beellmos]
15.kelime: [birlikte = beiiklrt]
16.kelime: [herhangi = aeghhinr]
17.kelime: [bir = bir]
18.kelime: [metni = eimnt]
19.kelime: [kullanabilirsiniz. = .aabiiiiklllnnrsuz]
20.kelime: [Yazdýrma = Yaadmrzý]
21.kelime: [aþamalarýný = aaaalmnrýýþ]
22.kelime: [takip = aikpt]
23.kelime: [etmek = eekmt]
24.kelime: [isterseniz = eeiinrsstz]
25.kelime: ["Yaz = "Yaz]
26.kelime: [Adýmlarý = Aadlmrýý]
27.kelime: [Göster" = "Gerstö]
28.kelime: [kutusu = kstuuu]
29.kelime: [çentiklenmeli, = ,eeeiikllmnntç]
30.kelime: [iptal = ailpt]
31.kelime: [için = iinç]
32.kelime: [ise = eis]
33.kelime: [kutu = ktuu]
34.kelime: [çentiksizlenmelidir. = .deeeiiiikllmnnrstzç]
35.kelime: [Yazdýrma = Yaadmrzý]
36.kelime: [iþlemi = eiilmþ]
37.kelime: [sürerken = eeknrrsü]
38.kelime: [pencereyle = ceeeelnpry]
39.kelime: [etkileþimi = eeiiiklmtþ]
40.kelime: [sürdürmek = dekmrrsüü]
41.kelime: [isterseniz = eeiinrsstz]
42.kelime: ["Arkaplanda = "Aaaadklnpr]
43.kelime: [Yazdýr" = "Yadrzý]
44.kelime: [çentiklenmeli, = ,eeeiikllmnntç]
45.kelime: [yazdýrma = aadmryzý]
46.kelime: [tamamlanýncaya = aaaaaclmmnntyý]
47.kelime: [kadar = aadkr]
48.kelime: [içerikle = eeiiklrç]
49.kelime: [etkileþimi = eeiiiklmtþ]
50.kelime: [dondurmak = addkmnoru]
51.kelime: [için = iinç]
52.kelime: [ise = eis]
53.kelime: [çentiksizlenmelidir. = .deeeiiiikllmnnrstzç]
54.kelime: [Müdahaleli = Maadehillü]
55.kelime: [(düzenleme = (deeelmnzü]
56.kelime: [ve = ev]
57.kelime: [deðiþiklikler = deeiiikkllrðþ]
58.kelime: [mümkün) = )kmmnüü]
59.kelime: [metin = eimnt]
60.kelime: [alaný = aalný]
61.kelime: [içeriklerini = eeiiiiklnrrç]
62.kelime: [son = nos]
63.kelime: [þekliyle = eeikllyþ]
64.kelime: [yazdýrmak = aadkmryzý]
65.kelime: [için = iinç]
66.kelime: [de = de]
67.kelime: [ikonlu = iklnou]
68.kelime: ["Yazdýr!" = !""Yadrzý]
69.kelime: [düðmesini = deiimnsüð]
70.kelime: [týklamanýz = aaklmntzýý]
71.kelime: [yeterlidir. = .deeiilrrty]

2 ve üstü tekrarlý kelimelerin kýymalýharita akýlermez kaotik listesi==>
2: [etkileþimi, etkileþimi]
2: [bir, bir]
2: [metni, metin]
2: [Yazdýrma, Yazdýrma]
2: [isterseniz, isterseniz]
2: [çentiksizlenmelidir., çentiksizlenmelidir.]
2: [ve, ve]
2: [çentiklenmeli,, çentiklenmeli,]
2: [ise, ise]
4: [için, için, için, için]
*/