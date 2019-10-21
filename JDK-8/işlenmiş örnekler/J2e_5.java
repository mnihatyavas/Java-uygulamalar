// J2e_5.java: DisplayDeck (DesteyiG�ster) �rne�i.

// Gereken dosya: J2e_5x.java=Deck
public class J2e_5 {
    public static void main (String[] args) {
        J2e_5x deste = new J2e_5x(); // J2e_5x=Deck/Deste...

        System.out.println ("[1,52]'lik destenin d�k�m�==>");
        for (int tak�m = J2e_2.KARO; tak�m <= J2e_2.S�NEK; tak�m++) { // J2e_2=Card/Kart...
            for (int say� = J2e_2.AS; say� <= J2e_2.PAPAZ; say�++) {
                J2e_2 kart = deste.kartAl (tak�m, say�);
                System.out.format ("%s %s = %2d%n",
                    kart.tak�mdanDizgeye (kart.tak�mAl()),
                    kart.say�danDizgeye (kart.say�Al()),
                    ((tak�m - 1) * 13 + say�));
            } // for-say� d�ng�s� sonu...
            System.out.println();
        } // for-tak�m d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J2e_5 s�n�f� sonu...

/* ��kt�:
**  >java J2e_5  **
[1,52]'lik destenin d�k�m�==>
Karo As =  1
Karo �ki =  2
Karo �� =  3
Karo D�rt =  4
Karo Be� =  5
Karo Alt� =  6
Karo Yedi =  7
Karo Sekiz =  8
Karo Dokuz =  9
Karo On = 10
Karo O�lan = 11
Karo K�z = 12
Karo Papaz = 13

Ma�a As = 14
Ma�a �ki = 15
Ma�a �� = 16
Ma�a D�rt = 17
Ma�a Be� = 18
Ma�a Alt� = 19
Ma�a Yedi = 20
Ma�a Sekiz = 21
Ma�a Dokuz = 22
Ma�a On = 23
Ma�a O�lan = 24
Ma�a K�z = 25
Ma�a Papaz = 26

Kupa As = 27
Kupa �ki = 28
Kupa �� = 29
Kupa D�rt = 30
Kupa Be� = 31
Kupa Alt� = 32
Kupa Yedi = 33
Kupa Sekiz = 34
Kupa Dokuz = 35
Kupa On = 36
Kupa O�lan = 37
Kupa K�z = 38
Kupa Papaz = 39

Sinek As = 40
Sinek �ki = 41
Sinek �� = 42
Sinek D�rt = 43
Sinek Be� = 44
Sinek Alt� = 45
Sinek Yedi = 46
Sinek Sekiz = 47
Sinek Dokuz = 48
Sinek On = 49
Sinek O�lan = 50
Sinek K�z = 51
Sinek Papaz = 52
*/