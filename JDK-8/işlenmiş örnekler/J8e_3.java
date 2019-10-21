// J8e_3.java: BreakIteratorDemo (Ay�ranTaray�c�G�sterisi) �rne�i.

import java.util.Locale;

import java.text.BreakIterator;

public class J8e_3  {
    static public void main (String[] args) {
        System.out.println ("=========================Karakter Taray�c�=========================");
        karakter�rnekleri(); System.out.println ("=========================Kelime Taray�c�=========================");
        kelime�rnekleri(); System.out.println ("=========================C�mle Taray�c�=========================");
        c�mle�rnekleri(); System.out.println ("=========================Sat�r Taray�c�=========================");
        sat�r�rnekleri();
    } // main(..) metodu sonu...

    static void karakter�rnekleri() {
        BreakIterator arapcaKarakterTaray�c� = BreakIterator.getCharacterInstance (new Locale ("ar", "SA"));
        // "konut/ev" kelimesinin arap�as�...
        String ev1 = "\u0628" + "\u064e" + "\u064a" + "\u0652" + "\u067a" + "\u064f";
        konumlar�Listele (ev1, arapcaKarakterTaray�c�);

        System.out.println ("--------------------------------------------------");
        BreakIterator t�rk�eKarakterTaray�c� = BreakIterator.getCharacterInstance (new Locale ("tr", "TR"));
        String ev2 = "bar�nak";
        konumlar�Listele (ev2, t�rk�eKarakterTaray�c�);
    } // karakter�rnekleri() metodu sonu...

    static void kelime�rnekleri() {
        BreakIterator ingilizceKelimeTaray�c� = BreakIterator.getWordInstance (new Locale ("en","US"));
        String ingilizceMetin = "She stopped.  " + "She said, \"Hello there,\" and then went on.";
        s�n�rlar���aretle (ingilizceMetin, ingilizceKelimeTaray�c�);
        System.out.println();
        kelimeleriAy�r (ingilizceMetin, ingilizceKelimeTaray�c�);

        System.out.println ("--------------------------------------------------");
        BreakIterator t�rk�eKelimeTaray�c� = BreakIterator.getWordInstance (new Locale ("tr","TR"));
        String t�rk�eMetin = "K�z duraklad�.  " + "\"Hey ordakiler, Merhaba,\" dedi ve sonra da y�r�d� gitti.";
        s�n�rlar���aretle (t�rk�eMetin, t�rk�eKelimeTaray�c�);
        System.out.println();
        kelimeleriAy�r (t�rk�eMetin, t�rk�eKelimeTaray�c�);
    } // kelime�rnekleri() metodu sonu...

    static void c�mle�rnekleri() {
        BreakIterator ingilizceC�mleTaray�c� = BreakIterator.getSentenceInstance (new Locale ("en", "US"));
        String ingilizceMetin1 = "She stopped.  " + "She said, \"Hello there,\" and then went on.";
        s�n�rlar���aretle (ingilizceMetin1, ingilizceC�mleTaray�c�);
        String ingilizceMetin2 = "He's vanished!  " + "What will we do?  It's up to us.";
        s�n�rlar���aretle (ingilizceMetin2, ingilizceC�mleTaray�c�);
        String ingilizceMetin3 = "Please add 1.5 liters to the tank.";
        s�n�rlar���aretle (ingilizceMetin3, ingilizceC�mleTaray�c�);
        String  ingilizceMetin4 = "\"No man is an island... " + "every man...\"";
        s�n�rlar���aretle (ingilizceMetin4, ingilizceC�mleTaray�c�);
        String ingilizceMetin5 = "My friend, Mr.Jones, has a new dog.  " + "The dog's name is Spot.";
        s�n�rlar���aretle (ingilizceMetin5, ingilizceC�mleTaray�c�);

        System.out.println ("--------------------------------------------------");
        BreakIterator t�rk�eC�mleTaray�c� = BreakIterator.getSentenceInstance (new Locale ("tr", "TR"));
        String t�rk�eMetin1 = "K�z duraklad�.  " + "\"Hey ordakiler, Merhaba,\" dedi ve sonra da y�r�d� gitti.";
        s�n�rlar���aretle (t�rk�eMetin1, ingilizceC�mleTaray�c�);
        String t�rk�eMetin2 = "Adam s�v��t�!  " + "�imdi ne yapaca��z?  Ne yapaca��m�z bize kalm��.";
        s�n�rlar���aretle (t�rk�eMetin2, ingilizceC�mleTaray�c�);
        String t�rk�eMetin3 = "L�tfen araban�n ya� deposuna 1.5 litre ilave edin.";
        s�n�rlar���aretle (t�rk�eMetin3, ingilizceC�mleTaray�c�);
        String  t�rk�eMetin4 = "\"Hi� bir insan yaln�z de�ildir... " + "her insan...\"";
        s�n�rlar���aretle (t�rk�eMetin4, ingilizceC�mleTaray�c�);
        String t�rk�eMetin5 = "Arkada��m, M.N.Yava�'�n, bir k�pe�i var.  " + "K�pe�in ad� Mavi�.";
        s�n�rlar���aretle (t�rk�eMetin5, ingilizceC�mleTaray�c�);
    } // c�mle�rnekleri() metodu sonu...

    static void sat�r�rnekleri() {
        Locale akt�elYerel1 = new Locale ("en", "US");
        BreakIterator sat�rTaray�c�1 = BreakIterator.getLineInstance (akt�elYerel1);
        String ingilizceMetin1 = "She stopped.  " + "She said, \"Hello there,\" and then went on.";
        s�n�rlar���aretle (ingilizceMetin1, sat�rTaray�c�1);
        String ayr�lmazTire1 = "There are twenty-four hours in a day.";
        s�n�rlar���aretle (ayr�lmazTire1, sat�rTaray�c�1);
        System.out.println();
        String ingilizceMetin2 = "She said, \"Hello there,\" and then went on down the street. " +
                "When she stopped to look at the fur coats in a shop window, her dog growled.  " +
                "\"Sorry Jake,\" she said. \"I didn't know you would take it personally.\"";
        sat�rlar�Bi�imle (ingilizceMetin2, 30, akt�elYerel1);

        System.out.println ("\n--------------------------------------------------");
        Locale akt�elYerel2 = new Locale ("tr", "TR");
        BreakIterator sat�rTaray�c�2 = BreakIterator.getLineInstance (akt�elYerel2);
        String t�rk�eMetin1 = "K�z duraklad�.  " + "\"Hey ordakiler, Merhaba,\" dedi ve sonra da y�r�d� gitti.";
        s�n�rlar���aretle (t�rk�eMetin1, sat�rTaray�c�2);
        String ayr�lmazTire2 = "Bir y�lda on-iki ay, elli-iki hafta ve ��-y�z-altm��-be� g�n vard�r.";
        s�n�rlar���aretle (ayr�lmazTire2, sat�rTaray�c�2);
        System.out.println();
        String t�rk�eMetin2 = "Kad�n duraklad�.  " + "\"Hey oradakiler, Merhaba,\" dedi ve sonra da caddeden a�a��ya y�r�d� gitti. " +
                "Bir ma�aza vitrini �n�nde, k�rk mantolara bakmak i�in durdu�unda, k�pe�i h�rlad�. " +
                "\"Kusura bakma Boncuk,\" dedi. \"�st�ne al�naca��n� tahmin edemedim.\"";
        sat�rlar�Bi�imle (t�rk�eMetin2, 40, akt�elYerel2);
    } // sat�r�rnekleri() metodu sonu...

    static void konumlar�Listele (String hedef, BreakIterator taray�c�) {
        taray�c�.setText (hedef);
        int s�n�r = taray�c�.first();
        System.out.println (hedef);
        while (s�n�r != BreakIterator.DONE) {
            System.out.println (s�n�r);
            s�n�r = taray�c�.next();
        } // while d�ng�s� sonu...
    } // konumlar�Listele(..) metodu sonu...

    static void s�n�rlar���aretle (String hedef, BreakIterator taray�c�) {
        StringBuffer i�aretler = new StringBuffer();
        i�aretler.setLength (hedef.length() + 1);
        for (int k = 0; k < i�aretler.length(); k++) i�aretler.setCharAt (k,' ');

        taray�c�.setText (hedef);
        int s�n�r = taray�c�.first();

        while (s�n�r != BreakIterator.DONE) {
            i�aretler.setCharAt (s�n�r, '^');
            s�n�r = taray�c�.next();
        } // while d�ng�s� sonu...

        System.out.println (hedef);
        System.out.println (i�aretler);
    } // s�n�rlar���aretle(..) metodu sonu...

    static void kelimeleriAy�r (String hedef, BreakIterator taray�c�) {
        taray�c�.setText (hedef);
        int ilk = taray�c�.first();
        int son = taray�c�.next();

        while (son != BreakIterator.DONE) {
            String kelime = hedef.substring (ilk, son);
            if (Character.isLetterOrDigit (kelime.charAt (0))) System.out.println (kelime);
            ilk = son;
            son = taray�c�.next();
        } // while d�ng�s� sonu...
    } // kelimeleriAy�r(..) metodu sonu...

    static void sat�rlar�Bi�imle (String hedef, int azamiSat�rUzunlu�u, Locale akt�elYerel) {
        BreakIterator s�n�r = BreakIterator.getLineInstance (akt�elYerel);
        s�n�r.setText (hedef);
        int ilk = s�n�r.first();
        int son = s�n�r.next();
        int sat�rUzunlu�u = 0;

        while (son != BreakIterator.DONE) {
            String kelime = hedef.substring (ilk, son);
            sat�rUzunlu�u = sat�rUzunlu�u + kelime.length();
            if (sat�rUzunlu�u >= azamiSat�rUzunlu�u) {
                System.out.println();
                sat�rUzunlu�u = kelime.length();
            } // if karar� sonu...
            System.out.print (kelime);
            ilk = son;
            son = s�n�r.next();
        } // while d�ng�s� sonu...
    } // sat�rlar�Bi�imle(..) metodu sonu...
} // J8e_3 s�n�f� sonu...

/* ��kt�:
**  >java J8e_3  **
=========================Karakter Taray�c�=========================
??????
0
2
4
6
--------------------------------------------------
bar�nak
0
1
2
3
4
5
6
7
=========================Kelime Taray�c�=========================
She stopped.  She said, "Hello there," and then went on.
^  ^^      ^^ ^  ^^   ^^^^    ^^    ^^^^  ^^   ^^   ^^ ^^

She
stopped
She
said
Hello
there
and
then
went
on
--------------------------------------------------
K�z duraklad�.  "Hey ordakiler, Merhaba," dedi ve sonra da y�r�d� gitti.
^  ^^        ^^ ^^  ^^        ^^^      ^^^^   ^^ ^^    ^^ ^^     ^^    ^^

K�z
duraklad�
Hey
ordakiler
Merhaba
dedi
ve
sonra
da
y�r�d�
gitti
=========================C�mle Taray�c�=========================
She stopped.  She said, "Hello there," and then went on.
^             ^                                         ^
He's vanished!  What will we do?  It's up to us.
^               ^                 ^             ^
Please add 1.5 liters to the tank.
^                                 ^
"No man is an island... every man..."
^                                    ^
My friend, Mr.Jones, has a new dog.  The dog's name is Spot.
^                                    ^                      ^
--------------------------------------------------
K�z duraklad�.  "Hey ordakiler, Merhaba," dedi ve sonra da y�r�d� gitti.
^               ^                                                       ^
Adam s�v��t�!  �imdi ne yapaca��z?  Ne yapaca��m�z bize kalm��.
^              ^                    ^                          ^
L�tfen araban�n ya� deposuna 1.5 litre ilave edin.
^                                                 ^
"Hi� bir insan yaln�z de�ildir... her insan..."
^                                              ^
Arkada��m, M.N.Yava�'�n, bir k�pe�i var.  K�pe�in ad� Mavi�.
^                                         ^                 ^
=========================Sat�r Taray�c�=========================
She stopped.  She said, "Hello there," and then went on.
^   ^         ^   ^     ^      ^       ^   ^    ^    ^  ^
There are twenty-four hours in a day.
^     ^   ^      ^    ^     ^  ^ ^   ^

She said, "Hello there," and
then went on down the
street. When she stopped to
look at the fur coats in a
shop window, her dog
growled.  "Sorry Jake," she
said. "I didn't know you
would take it personally."
--------------------------------------------------
K�z duraklad�.  "Hey ordakiler, Merhaba," dedi ve sonra da y�r�d� gitti.
^   ^           ^    ^          ^         ^    ^  ^     ^  ^      ^     ^
Bir y�lda on-iki ay, elli-iki hafta ve ��-y�z-altm��-be� g�n vard�r.
^   ^     ^  ^   ^   ^    ^   ^     ^  ^  ^   ^      ^   ^   ^      ^

Kad�n duraklad�.  "Hey oradakiler,
Merhaba," dedi ve sonra da caddeden
a�a��ya y�r�d� gitti. Bir ma�aza
vitrini �n�nde, k�rk mantolara bakmak
i�in durdu�unda, k�pe�i h�rlad�.
"Kusura bakma Boncuk," dedi. "�st�ne
al�naca��n� tahmin edemedim."
*/