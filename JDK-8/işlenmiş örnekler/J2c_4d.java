// J2c_4d.java: DisplayDeck2 (G�sterDeste2) �rne�i.

public class J2c_4d {
    public static void main (String[] args) {
        J2c_4dx deste = new J2c_4dx(); // J2c_4dx.java=Deck2...

        System.out.println ("Bir deste vantinin d�k�m�:\n==========================");
        for (int tak�m = J2c_4b.KARO; tak�m <= J2c_4b.S�NEK; tak�m++) {
            for (int say� = J2c_4b.AS; say� <= J2c_4b.PAPAZ; say�++) {
                J2c_4b kart = deste.kartAl (tak�m, say�); // J2c_4b.java=Card2...
                System.out.format ("%d.kart: [%s %s]%n",
                    ((tak�m-1) * 13 + say�),
                    kart.tak�mdanDizgeye (kart.tak�mAl()),
                    kart.say�danDizgeye (kart.say�Al()));
            } // for-say� d�ng�s� sonu...
            System.out.println();
        } // for-tak�m d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J2c_4d s�n�f� sonu...

/* ��kt�:
**  >java J2c_4d  **
Bir deste vantinin d�k�m�:
==========================
1.kart: [Karo As]
2.kart: [Karo �ki]
3.kart: [Karo ��]
4.kart: [Karo D�rt]
5.kart: [Karo Be�]
6.kart: [Karo Alt�]
7.kart: [Karo Yedi]
8.kart: [Karo Sekiz]
9.kart: [Karo Dokuz]
10.kart: [Karo On]
11.kart: [Karo O�lan]
12.kart: [Karo K�z]
13.kart: [Karo Papaz]

14.kart: [Ma�a As]
15.kart: [Ma�a �ki]
16.kart: [Ma�a ��]
17.kart: [Ma�a D�rt]
18.kart: [Ma�a Be�]
19.kart: [Ma�a Alt�]
20.kart: [Ma�a Yedi]
21.kart: [Ma�a Sekiz]
22.kart: [Ma�a Dokuz]
23.kart: [Ma�a On]
24.kart: [Ma�a O�lan]
25.kart: [Ma�a K�z]
26.kart: [Ma�a Papaz]

27.kart: [Kupa As]
28.kart: [Kupa �ki]
29.kart: [Kupa ��]
30.kart: [Kupa D�rt]
31.kart: [Kupa Be�]
32.kart: [Kupa Alt�]
33.kart: [Kupa Yedi]
34.kart: [Kupa Sekiz]
35.kart: [Kupa Dokuz]
36.kart: [Kupa On]
37.kart: [Kupa O�lan]
38.kart: [Kupa K�z]
39.kart: [Kupa Papaz]

40.kart: [Sinek As]
41.kart: [Sinek �ki]
42.kart: [Sinek ��]
43.kart: [Sinek D�rt]
44.kart: [Sinek Be�]
45.kart: [Sinek Alt�]
46.kart: [Sinek Yedi]
47.kart: [Sinek Sekiz]
48.kart: [Sinek Dokuz]
49.kart: [Sinek On]
50.kart: [Sinek O�lan]
51.kart: [Sinek K�z]
52.kart: [Sinek Papaz]
*/