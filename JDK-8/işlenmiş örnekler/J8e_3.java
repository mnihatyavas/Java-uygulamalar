// J8e_3.java: BreakIteratorDemo (AyýranTarayýcýGösterisi) örneði.

import java.util.Locale;

import java.text.BreakIterator;

public class J8e_3  {
    static public void main (String[] args) {
        System.out.println ("=========================Karakter Tarayýcý=========================");
        karakterÖrnekleri(); System.out.println ("=========================Kelime Tarayýcý=========================");
        kelimeÖrnekleri(); System.out.println ("=========================Cümle Tarayýcý=========================");
        cümleÖrnekleri(); System.out.println ("=========================Satýr Tarayýcý=========================");
        satýrÖrnekleri();
    } // main(..) metodu sonu...

    static void karakterÖrnekleri() {
        BreakIterator arapcaKarakterTarayýcý = BreakIterator.getCharacterInstance (new Locale ("ar", "SA"));
        // "konut/ev" kelimesinin arapçasý...
        String ev1 = "\u0628" + "\u064e" + "\u064a" + "\u0652" + "\u067a" + "\u064f";
        konumlarýListele (ev1, arapcaKarakterTarayýcý);

        System.out.println ("--------------------------------------------------");
        BreakIterator türkçeKarakterTarayýcý = BreakIterator.getCharacterInstance (new Locale ("tr", "TR"));
        String ev2 = "barýnak";
        konumlarýListele (ev2, türkçeKarakterTarayýcý);
    } // karakterÖrnekleri() metodu sonu...

    static void kelimeÖrnekleri() {
        BreakIterator ingilizceKelimeTarayýcý = BreakIterator.getWordInstance (new Locale ("en","US"));
        String ingilizceMetin = "She stopped.  " + "She said, \"Hello there,\" and then went on.";
        sýnýrlarýÝþaretle (ingilizceMetin, ingilizceKelimeTarayýcý);
        System.out.println();
        kelimeleriAyýr (ingilizceMetin, ingilizceKelimeTarayýcý);

        System.out.println ("--------------------------------------------------");
        BreakIterator türkçeKelimeTarayýcý = BreakIterator.getWordInstance (new Locale ("tr","TR"));
        String türkçeMetin = "Kýz durakladý.  " + "\"Hey ordakiler, Merhaba,\" dedi ve sonra da yürüdü gitti.";
        sýnýrlarýÝþaretle (türkçeMetin, türkçeKelimeTarayýcý);
        System.out.println();
        kelimeleriAyýr (türkçeMetin, türkçeKelimeTarayýcý);
    } // kelimeÖrnekleri() metodu sonu...

    static void cümleÖrnekleri() {
        BreakIterator ingilizceCümleTarayýcý = BreakIterator.getSentenceInstance (new Locale ("en", "US"));
        String ingilizceMetin1 = "She stopped.  " + "She said, \"Hello there,\" and then went on.";
        sýnýrlarýÝþaretle (ingilizceMetin1, ingilizceCümleTarayýcý);
        String ingilizceMetin2 = "He's vanished!  " + "What will we do?  It's up to us.";
        sýnýrlarýÝþaretle (ingilizceMetin2, ingilizceCümleTarayýcý);
        String ingilizceMetin3 = "Please add 1.5 liters to the tank.";
        sýnýrlarýÝþaretle (ingilizceMetin3, ingilizceCümleTarayýcý);
        String  ingilizceMetin4 = "\"No man is an island... " + "every man...\"";
        sýnýrlarýÝþaretle (ingilizceMetin4, ingilizceCümleTarayýcý);
        String ingilizceMetin5 = "My friend, Mr.Jones, has a new dog.  " + "The dog's name is Spot.";
        sýnýrlarýÝþaretle (ingilizceMetin5, ingilizceCümleTarayýcý);

        System.out.println ("--------------------------------------------------");
        BreakIterator türkçeCümleTarayýcý = BreakIterator.getSentenceInstance (new Locale ("tr", "TR"));
        String türkçeMetin1 = "Kýz durakladý.  " + "\"Hey ordakiler, Merhaba,\" dedi ve sonra da yürüdü gitti.";
        sýnýrlarýÝþaretle (türkçeMetin1, ingilizceCümleTarayýcý);
        String türkçeMetin2 = "Adam sývýþtý!  " + "Þimdi ne yapacaðýz?  Ne yapacaðýmýz bize kalmýþ.";
        sýnýrlarýÝþaretle (türkçeMetin2, ingilizceCümleTarayýcý);
        String türkçeMetin3 = "Lütfen arabanýn yað deposuna 1.5 litre ilave edin.";
        sýnýrlarýÝþaretle (türkçeMetin3, ingilizceCümleTarayýcý);
        String  türkçeMetin4 = "\"Hiç bir insan yalnýz deðildir... " + "her insan...\"";
        sýnýrlarýÝþaretle (türkçeMetin4, ingilizceCümleTarayýcý);
        String türkçeMetin5 = "Arkadaþým, M.N.Yavaþ'ýn, bir köpeði var.  " + "Köpeðin adý Maviþ.";
        sýnýrlarýÝþaretle (türkçeMetin5, ingilizceCümleTarayýcý);
    } // cümleÖrnekleri() metodu sonu...

    static void satýrÖrnekleri() {
        Locale aktüelYerel1 = new Locale ("en", "US");
        BreakIterator satýrTarayýcý1 = BreakIterator.getLineInstance (aktüelYerel1);
        String ingilizceMetin1 = "She stopped.  " + "She said, \"Hello there,\" and then went on.";
        sýnýrlarýÝþaretle (ingilizceMetin1, satýrTarayýcý1);
        String ayrýlmazTire1 = "There are twenty-four hours in a day.";
        sýnýrlarýÝþaretle (ayrýlmazTire1, satýrTarayýcý1);
        System.out.println();
        String ingilizceMetin2 = "She said, \"Hello there,\" and then went on down the street. " +
                "When she stopped to look at the fur coats in a shop window, her dog growled.  " +
                "\"Sorry Jake,\" she said. \"I didn't know you would take it personally.\"";
        satýrlarýBiçimle (ingilizceMetin2, 30, aktüelYerel1);

        System.out.println ("\n--------------------------------------------------");
        Locale aktüelYerel2 = new Locale ("tr", "TR");
        BreakIterator satýrTarayýcý2 = BreakIterator.getLineInstance (aktüelYerel2);
        String türkçeMetin1 = "Kýz durakladý.  " + "\"Hey ordakiler, Merhaba,\" dedi ve sonra da yürüdü gitti.";
        sýnýrlarýÝþaretle (türkçeMetin1, satýrTarayýcý2);
        String ayrýlmazTire2 = "Bir yýlda on-iki ay, elli-iki hafta ve üç-yüz-altmýþ-beþ gün vardýr.";
        sýnýrlarýÝþaretle (ayrýlmazTire2, satýrTarayýcý2);
        System.out.println();
        String türkçeMetin2 = "Kadýn durakladý.  " + "\"Hey oradakiler, Merhaba,\" dedi ve sonra da caddeden aþaðýya yürüdü gitti. " +
                "Bir maðaza vitrini önünde, kürk mantolara bakmak için durduðunda, köpeði hýrladý. " +
                "\"Kusura bakma Boncuk,\" dedi. \"Üstüne alýnacaðýný tahmin edemedim.\"";
        satýrlarýBiçimle (türkçeMetin2, 40, aktüelYerel2);
    } // satýrÖrnekleri() metodu sonu...

    static void konumlarýListele (String hedef, BreakIterator tarayýcý) {
        tarayýcý.setText (hedef);
        int sýnýr = tarayýcý.first();
        System.out.println (hedef);
        while (sýnýr != BreakIterator.DONE) {
            System.out.println (sýnýr);
            sýnýr = tarayýcý.next();
        } // while döngüsü sonu...
    } // konumlarýListele(..) metodu sonu...

    static void sýnýrlarýÝþaretle (String hedef, BreakIterator tarayýcý) {
        StringBuffer iþaretler = new StringBuffer();
        iþaretler.setLength (hedef.length() + 1);
        for (int k = 0; k < iþaretler.length(); k++) iþaretler.setCharAt (k,' ');

        tarayýcý.setText (hedef);
        int sýnýr = tarayýcý.first();

        while (sýnýr != BreakIterator.DONE) {
            iþaretler.setCharAt (sýnýr, '^');
            sýnýr = tarayýcý.next();
        } // while döngüsü sonu...

        System.out.println (hedef);
        System.out.println (iþaretler);
    } // sýnýrlarýÝþaretle(..) metodu sonu...

    static void kelimeleriAyýr (String hedef, BreakIterator tarayýcý) {
        tarayýcý.setText (hedef);
        int ilk = tarayýcý.first();
        int son = tarayýcý.next();

        while (son != BreakIterator.DONE) {
            String kelime = hedef.substring (ilk, son);
            if (Character.isLetterOrDigit (kelime.charAt (0))) System.out.println (kelime);
            ilk = son;
            son = tarayýcý.next();
        } // while döngüsü sonu...
    } // kelimeleriAyýr(..) metodu sonu...

    static void satýrlarýBiçimle (String hedef, int azamiSatýrUzunluðu, Locale aktüelYerel) {
        BreakIterator sýnýr = BreakIterator.getLineInstance (aktüelYerel);
        sýnýr.setText (hedef);
        int ilk = sýnýr.first();
        int son = sýnýr.next();
        int satýrUzunluðu = 0;

        while (son != BreakIterator.DONE) {
            String kelime = hedef.substring (ilk, son);
            satýrUzunluðu = satýrUzunluðu + kelime.length();
            if (satýrUzunluðu >= azamiSatýrUzunluðu) {
                System.out.println();
                satýrUzunluðu = kelime.length();
            } // if kararý sonu...
            System.out.print (kelime);
            ilk = son;
            son = sýnýr.next();
        } // while döngüsü sonu...
    } // satýrlarýBiçimle(..) metodu sonu...
} // J8e_3 sýnýfý sonu...

/* Çýktý:
**  >java J8e_3  **
=========================Karakter Tarayýcý=========================
??????
0
2
4
6
--------------------------------------------------
barýnak
0
1
2
3
4
5
6
7
=========================Kelime Tarayýcý=========================
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
Kýz durakladý.  "Hey ordakiler, Merhaba," dedi ve sonra da yürüdü gitti.
^  ^^        ^^ ^^  ^^        ^^^      ^^^^   ^^ ^^    ^^ ^^     ^^    ^^

Kýz
durakladý
Hey
ordakiler
Merhaba
dedi
ve
sonra
da
yürüdü
gitti
=========================Cümle Tarayýcý=========================
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
Kýz durakladý.  "Hey ordakiler, Merhaba," dedi ve sonra da yürüdü gitti.
^               ^                                                       ^
Adam sývýþtý!  Þimdi ne yapacaðýz?  Ne yapacaðýmýz bize kalmýþ.
^              ^                    ^                          ^
Lütfen arabanýn yað deposuna 1.5 litre ilave edin.
^                                                 ^
"Hiç bir insan yalnýz deðildir... her insan..."
^                                              ^
Arkadaþým, M.N.Yavaþ'ýn, bir köpeði var.  Köpeðin adý Maviþ.
^                                         ^                 ^
=========================Satýr Tarayýcý=========================
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
Kýz durakladý.  "Hey ordakiler, Merhaba," dedi ve sonra da yürüdü gitti.
^   ^           ^    ^          ^         ^    ^  ^     ^  ^      ^     ^
Bir yýlda on-iki ay, elli-iki hafta ve üç-yüz-altmýþ-beþ gün vardýr.
^   ^     ^  ^   ^   ^    ^   ^     ^  ^  ^   ^      ^   ^   ^      ^

Kadýn durakladý.  "Hey oradakiler,
Merhaba," dedi ve sonra da caddeden
aþaðýya yürüdü gitti. Bir maðaza
vitrini önünde, kürk mantolara bakmak
için durduðunda, köpeði hýrladý.
"Kusura bakma Boncuk," dedi. "Üstüne
alýnacaðýný tahmin edemedim."
*/