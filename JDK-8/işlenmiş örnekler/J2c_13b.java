// J2c_13b.java: RosterTest (ListeleyiciTesti) örneði.

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Comparator;
import java.util.function.Predicate;
import java.lang.Iterable;
import java.time.chrono.IsoChronology;

// Gereken dosya: J2c_13ax.java; Person
public class J2c_13b {
    interface KiþiKontrolu {
        boolean test (J2c_13ax p); // J2c_13ax.java=Person...
    } // KiþiKontrolu arayüzü sonu...

/* KiþiKontrolu soyut arayüzünün gövdesiz test() metodu
 * ve Predicate test() metodlarýnda bir problem var.
 * Hem orijinal programlarda hem de iþlenmiþlerde Yaklaþým-3 ve sonrasý
 * hata da vermiyor, döküm de...
 *
 * Meðerse 18-25 yaþa uyan ERKEK eleman yokmuþ; ekledim, döküm aldým.
 */

    // Yaklaþým 1: Bir özelliðiyle uyuþan Kiþi'leri araþtýran metodlar yarat...
    public static void denYaþlýlarýYaz (List<J2c_13ax> listeleyici, int yaþ) {
        for (J2c_13ax kiþi : listeleyici) if (kiþi.yaþýAl() >= yaþ) kiþi.kiþiyiYaz();
    } // denYaþlýlarýYaz(..) metodu sonu...

    // Yaklaþým 2: Daha genel arama metodlarý yarat...
    public static void ikiYaþArasýKiþiyiYaz (List<J2c_13ax> listeleyici, int düþük, int yüksek) {
        for (J2c_13ax kiþi : listeleyici) if (düþük <= kiþi.yaþýAl() && kiþi.yaþýAl() < yüksek) kiþi.kiþiyiYaz();
    } // ikiYaþArasýKiþiyiYaz(..) metodu sonu...

    // Yaklaþým 3: Bir yerel sýnýf içindeki kritere göre araþtýrma belirle...
    // Yaklaþým 4: Bir anonim sýnýf içindeki kritere göre araþtýrma belirle...
    // Yaklaþým 5: Bir Lambda ifadesi kriterine göre araþtýrma belirle...
    public static void kiþileriYaz (List<J2c_13ax> listeleyici, KiþiKontrolu testEdici) {
        for (J2c_13ax kiþi : listeleyici) {if (testEdici.test (kiþi)) {kiþi.kiþiyiYaz();}}
    } // kiþileriYaz(..) metodu sonu...

    // Yaklaþým 6: Lambda ifadeli standart fonksiyonel arayüzleri kullan...
    public static void þartlýKiþileriYaz (List<J2c_13ax> listeleyici, Predicate<J2c_13ax> testEdici) {
        for (J2c_13ax kiþi : listeleyici) {if (testEdici.test (kiþi)) {kiþi.kiþiyiYaz();}}
    } // þartlýKiþileriYaz(..) metodu sonu...

    // Yaklaþým 7: Uygulaman boyunca Lambda ifadeleri kullan...
    public static void kiþileriÝþle (List<J2c_13ax> listeleyici, Predicate<J2c_13ax> testEdici, Consumer<J2c_13ax> blok) {
        for (J2c_13ax kiþi : listeleyici) {if (testEdici.test (kiþi)) {blok.accept (kiþi);}}
    } // kiþileriÝþle(..) metodu sonu...

    // Yaklaþým 7, ikinci örnek...
    public static void fonksiyonelKiþileriÝþle (
        List<J2c_13ax> listeleyici,
        Predicate<J2c_13ax> testEdici,
        Function<J2c_13ax, String> haritalayýcý,
        Consumer<String> blok) {
        for (J2c_13ax kiþi : listeleyici) {
            if (testEdici.test (kiþi)) {
                String veri = haritalayýcý.apply (kiþi);
                blok.accept (veri);
            } // if kararý sonu..
        }
    } // fonksiyonelKiþileriÝþle(..) metodu sonu...

    // Yaklaþým 8: Daha geniþ tipler kullan...
    public static <X, Y> void elemanlarýiþle (
        Iterable<X> kaynak,
        Predicate<X> testEdici,
        Function<X, Y> haritalayýcý,
        Consumer<Y> blok) {
            for (X kiþi : kaynak) {
                if (testEdici.test (kiþi)) {
                    Y veri = haritalayýcý.apply (kiþi);
                    blok.accept (veri);
                } // if kararý sonu...
            }
    } // elemanlarýiþle(..) metodu sonu...

    public static void main (String... args) {
        List<J2c_13ax> listeleyici = J2c_13ax.listeyiYarat();

        System.out.println ("Mevcut " + listeleyici.size() + " kiþinin dökümü:");
        for (J2c_13ax kiþi : listeleyici) kiþi.kiþiyiYaz();

        // Yaklaþým 1:
        System.out.println ("\nYaþ 20 üzeri kiþilerin dökümü:");
        denYaþlýlarýYaz (listeleyici, 20);

        // Yaklaþým 2:
        System.out.println ("\n14-30 yaþ arasý kiþilerin dökümü:");
        ikiYaþArasýKiþiyiYaz (listeleyici, 14, 30);

        // Yaklaþým 3:
        System.out.println ("\nÖzel servise uygun kiþilerin dökümü:");
        class ÖzelServiseUygunKiþiKontrolu implements KiþiKontrolu {
           public boolean test (J2c_13ax kiþi) {
                return kiþi.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                    && kiþi.yaþýAl() >= 18
                    && kiþi.yaþýAl() <= 25;
            } // test(..) metodu sonu...
        } // ÖzelServiseUygunKiþiKontrolu sýnýfý sonu...

        kiþileriYaz (listeleyici, new ÖzelServiseUygunKiþiKontrolu());

        // Yaklaþým 4:
        System.out.println ("\nÖzel servise ve anonim sýnýfa uygun kiþilerin dökümü:");
        kiþileriYaz (
            listeleyici,
            new KiþiKontrolu() {public boolean test (J2c_13ax kiþi) {
                    return kiþi.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                        && kiþi.yaþýAl() >= 18
                        && kiþi.yaþýAl() <= 25;
                }
            }
        );

        // Yaklaþým 5:
        System.out.println ("\nÖzel servise ve Lambda ifadesine uygun kiþilerin dökümü:");
        kiþileriYaz (
            listeleyici,
            (J2c_13ax kiþi) -> kiþi.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                && kiþi.yaþýAl() >= 18
                && kiþi.yaþýAl() <= 25
        );

        // Yaklaþým 6:
        System.out.println ("\nÖzel servise ve Þart parametresine uygun kiþilerin dökümü:");
        þartlýKiþileriYaz (
            listeleyici,
            kiþi -> kiþi.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                && kiþi.yaþýAl() >= 18
                && kiþi.yaþýAl() <= 25
        );

        // Yaklaþým 7:
        System.out.println ("\nÖzel servise, Þart ve Tüketici parametrelerine uygun kiþilerin dökümü:");
        kiþileriÝþle (
            listeleyici,
            kiþi -> kiþi.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                && kiþi.yaþýAl() >= 18
                && kiþi.yaþýAl() <= 25,
            kiþi -> kiþi.kiþiyiYaz()
        );

        // Yaklaþým 7, ikinci örnek...
        System.out.println ("\nÖzel servise, Þart, Fonksiyon ve Tüketici parametrelerine uygun kiþilerin dökümü:");
        fonksiyonelKiþileriÝþle (
            listeleyici,
            kiþi -> kiþi.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                && kiþi.yaþýAl() >= 18
                && kiþi.yaþýAl() <= 25,
            kiþi -> kiþi.epostayýAl(),
            eposta -> System.out.println (eposta) // Þarta uyan sadece eposta'lar yazdýrýlýr...
        );

        // Yaklaþým 8:
        System.out.println ("\nÖzel servise ve daha geniþ tip sürümlerine uyan kiþilerin dökümü:");
        elemanlarýiþle (
            listeleyici,
            kiþi -> kiþi.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                && kiþi.yaþýAl() >= 18
                && kiþi.yaþýAl() <= 25,
            kiþi -> kiþi.epostayýAl(),
            eposta -> System.out.println (eposta)
        );

        // Yaklaþým 9: Lambda ifadelerini parametre olarak kabul eden hacimli veri iþlemlerini kullan...
        System.out.println ("\nÖzel servise ve hacimli veri iþlemlerine uygun kiþilerin dökümü:");
        listeleyici
            .stream()
            .filter (
                kiþi -> kiþi.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                    && kiþi.yaþýAl() >= 18
                    && kiþi.yaþýAl() <= 25)
            .map (kiþi -> kiþi.epostayýAl())
            .forEach (eposta -> System.out.println (eposta));
    } // main(..) metodu sonu...
} // J2c_13b sýnýfý sonu...

/* Çýktý:
**  >java J2c_13b  **
Mevcut 7 kiþinin dökümü:
Fred, 37, ERKEK, fred@gmail.com
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

Yaþ 20 üzeri kiþilerin dökümü:
Fred, 37, ERKEK, fred@gmail.com
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

14-30 yaþ arasý kiþilerin dökümü:
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

Özel servise uygun kiþilerin dökümü:
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

Özel servise ve anonim sýnýfa uygun kiþilerin dökümü:
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

Özel servise ve Lambda ifadesine uygun kiþilerin dökümü:
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

Özel servise ve Þart parametresine uygun kiþilerin dökümü:
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

Özel servise, Þart ve Tüketici parametrelerine uygun kiþilerin dökümü:
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

Özel servise, Þart, Fonksiyon ve Tüketici parametrelerine uygun kiþilerin dökümü:
metin@hotmail.com
ersin@hotmail.com

Özel servise ve daha geniþ tip sürümlerine uyan kiþilerin dökümü:
metin@hotmail.com
ersin@hotmail.com

Özel servise ve hacimli veri iþlemlerine uygun kiþilerin dökümü:
metin@hotmail.com
ersin@hotmail.com
*/