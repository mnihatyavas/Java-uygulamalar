// J2e_5.java: DisplayDeck (DesteyiGöster) örneði.

// Gereken dosya: J2e_5x.java=Deck
public class J2e_5 {
    public static void main (String[] args) {
        J2e_5x deste = new J2e_5x(); // J2e_5x=Deck/Deste...

        System.out.println ("[1,52]'lik destenin dökümü==>");
        for (int takým = J2e_2.KARO; takým <= J2e_2.SÝNEK; takým++) { // J2e_2=Card/Kart...
            for (int sayý = J2e_2.AS; sayý <= J2e_2.PAPAZ; sayý++) {
                J2e_2 kart = deste.kartAl (takým, sayý);
                System.out.format ("%s %s = %2d%n",
                    kart.takýmdanDizgeye (kart.takýmAl()),
                    kart.sayýdanDizgeye (kart.sayýAl()),
                    ((takým - 1) * 13 + sayý));
            } // for-sayý döngüsü sonu...
            System.out.println();
        } // for-takým döngüsü sonu...
    } // main(..) metodu sonu...
} // J2e_5 sýnýfý sonu...

/* Çýktý:
**  >java J2e_5  **
[1,52]'lik destenin dökümü==>
Karo As =  1
Karo Ýki =  2
Karo Üç =  3
Karo Dört =  4
Karo Beþ =  5
Karo Altý =  6
Karo Yedi =  7
Karo Sekiz =  8
Karo Dokuz =  9
Karo On = 10
Karo Oðlan = 11
Karo Kýz = 12
Karo Papaz = 13

Maça As = 14
Maça Ýki = 15
Maça Üç = 16
Maça Dört = 17
Maça Beþ = 18
Maça Altý = 19
Maça Yedi = 20
Maça Sekiz = 21
Maça Dokuz = 22
Maça On = 23
Maça Oðlan = 24
Maça Kýz = 25
Maça Papaz = 26

Kupa As = 27
Kupa Ýki = 28
Kupa Üç = 29
Kupa Dört = 30
Kupa Beþ = 31
Kupa Altý = 32
Kupa Yedi = 33
Kupa Sekiz = 34
Kupa Dokuz = 35
Kupa On = 36
Kupa Oðlan = 37
Kupa Kýz = 38
Kupa Papaz = 39

Sinek As = 40
Sinek Ýki = 41
Sinek Üç = 42
Sinek Dört = 43
Sinek Beþ = 44
Sinek Altý = 45
Sinek Yedi = 46
Sinek Sekiz = 47
Sinek Dokuz = 48
Sinek On = 49
Sinek Oðlan = 50
Sinek Kýz = 51
Sinek Papaz = 52
*/