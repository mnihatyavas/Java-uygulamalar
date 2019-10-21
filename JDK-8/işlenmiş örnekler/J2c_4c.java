// J2c_4c.java: DisplayDeck (GösterDesteyi) örneði.

// Gereken dosyalar: J2c_4a.java=Card; J2c_4cx=Deck
public class J2c_4c {
    public static void main (String[] args) {
        J2c_4cx deste = new J2c_4cx();

        System.out.println ("Bir deste vanti kart dökümü==>\n===========================");
        for (int takým = J2c_4a.KARO; takým <= J2c_4a.SÝNEK; takým++) {
            for (int sayý = J2c_4a.AS; sayý <= J2c_4a.PAPAZ; sayý++) {
                J2c_4a kart = deste.kartAl (takým, sayý);
                System.out.format ("%d.kart: [%s %s]%n",
                    ((takým - 1) * 13 + sayý),
                    kart.takýmdanDizgeye (kart.takýmAl()),
                    kart.sayýdanDizgeye (kart.sayýAl()));
            } // for-sayý döngüsü sonu...
            System.out.println();
        } // for-takým döngüsü sonu...
    } // main(..) metodu sonu...
} // J2c_4c sýnýfý sonu...

/* Çýktý:
**  >java J2c_4c  **
Bir deste vanti kart dökümü==>
===========================
1.kart: [Karo As]
2.kart: [Karo Ýki]
3.kart: [Karo Üç]
4.kart: [Karo Dört]
5.kart: [Karo Beþ]
6.kart: [Karo Altý]
7.kart: [Karo Yedi]
8.kart: [Karo Sekiz]
9.kart: [Karo Dokuz]
10.kart: [Karo On]
11.kart: [Karo Oðlan]
12.kart: [Karo Kýz]
13.kart: [Karo Papaz]

14.kart: [Maça As]
15.kart: [Maça Ýki]
16.kart: [Maça Üç]
17.kart: [Maça Dört]
18.kart: [Maça Beþ]
19.kart: [Maça Altý]
20.kart: [Maça Yedi]
21.kart: [Maça Sekiz]
22.kart: [Maça Dokuz]
23.kart: [Maça On]
24.kart: [Maça Oðlan]
25.kart: [Maça Kýz]
26.kart: [Maça Papaz]

27.kart: [Kupa As]
28.kart: [Kupa Ýki]
29.kart: [Kupa Üç]
30.kart: [Kupa Dört]
31.kart: [Kupa Beþ]
32.kart: [Kupa Altý]
33.kart: [Kupa Yedi]
34.kart: [Kupa Sekiz]
35.kart: [Kupa Dokuz]
36.kart: [Kupa On]
37.kart: [Kupa Oðlan]
38.kart: [Kupa Kýz]
39.kart: [Kupa Papaz]

40.kart: [Sinek As]
41.kart: [Sinek Ýki]
42.kart: [Sinek Üç]
43.kart: [Sinek Dört]
44.kart: [Sinek Beþ]
45.kart: [Sinek Altý]
46.kart: [Sinek Yedi]
47.kart: [Sinek Sekiz]
48.kart: [Sinek Dokuz]
49.kart: [Sinek On]
50.kart: [Sinek Oðlan]
51.kart: [Sinek Kýz]
52.kart: [Sinek Papaz]
*/