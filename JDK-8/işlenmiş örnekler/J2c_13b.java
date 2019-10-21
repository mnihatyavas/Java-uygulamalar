// J2c_13b.java: RosterTest (ListeleyiciTesti) �rne�i.

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Comparator;
import java.util.function.Predicate;
import java.lang.Iterable;
import java.time.chrono.IsoChronology;

// Gereken dosya: J2c_13ax.java; Person
public class J2c_13b {
    interface Ki�iKontrolu {
        boolean test (J2c_13ax p); // J2c_13ax.java=Person...
    } // Ki�iKontrolu aray�z� sonu...

/* Ki�iKontrolu soyut aray�z�n�n g�vdesiz test() metodu
 * ve Predicate test() metodlar�nda bir problem var.
 * Hem orijinal programlarda hem de i�lenmi�lerde Yakla��m-3 ve sonras�
 * hata da vermiyor, d�k�m de...
 *
 * Me�erse 18-25 ya�a uyan ERKEK eleman yokmu�; ekledim, d�k�m ald�m.
 */

    // Yakla��m 1: Bir �zelli�iyle uyu�an Ki�i'leri ara�t�ran metodlar yarat...
    public static void denYa�l�lar�Yaz (List<J2c_13ax> listeleyici, int ya�) {
        for (J2c_13ax ki�i : listeleyici) if (ki�i.ya��Al() >= ya�) ki�i.ki�iyiYaz();
    } // denYa�l�lar�Yaz(..) metodu sonu...

    // Yakla��m 2: Daha genel arama metodlar� yarat...
    public static void ikiYa�Aras�Ki�iyiYaz (List<J2c_13ax> listeleyici, int d���k, int y�ksek) {
        for (J2c_13ax ki�i : listeleyici) if (d���k <= ki�i.ya��Al() && ki�i.ya��Al() < y�ksek) ki�i.ki�iyiYaz();
    } // ikiYa�Aras�Ki�iyiYaz(..) metodu sonu...

    // Yakla��m 3: Bir yerel s�n�f i�indeki kritere g�re ara�t�rma belirle...
    // Yakla��m 4: Bir anonim s�n�f i�indeki kritere g�re ara�t�rma belirle...
    // Yakla��m 5: Bir Lambda ifadesi kriterine g�re ara�t�rma belirle...
    public static void ki�ileriYaz (List<J2c_13ax> listeleyici, Ki�iKontrolu testEdici) {
        for (J2c_13ax ki�i : listeleyici) {if (testEdici.test (ki�i)) {ki�i.ki�iyiYaz();}}
    } // ki�ileriYaz(..) metodu sonu...

    // Yakla��m 6: Lambda ifadeli standart fonksiyonel aray�zleri kullan...
    public static void �artl�Ki�ileriYaz (List<J2c_13ax> listeleyici, Predicate<J2c_13ax> testEdici) {
        for (J2c_13ax ki�i : listeleyici) {if (testEdici.test (ki�i)) {ki�i.ki�iyiYaz();}}
    } // �artl�Ki�ileriYaz(..) metodu sonu...

    // Yakla��m 7: Uygulaman boyunca Lambda ifadeleri kullan...
    public static void ki�ileri��le (List<J2c_13ax> listeleyici, Predicate<J2c_13ax> testEdici, Consumer<J2c_13ax> blok) {
        for (J2c_13ax ki�i : listeleyici) {if (testEdici.test (ki�i)) {blok.accept (ki�i);}}
    } // ki�ileri��le(..) metodu sonu...

    // Yakla��m 7, ikinci �rnek...
    public static void fonksiyonelKi�ileri��le (
        List<J2c_13ax> listeleyici,
        Predicate<J2c_13ax> testEdici,
        Function<J2c_13ax, String> haritalay�c�,
        Consumer<String> blok) {
        for (J2c_13ax ki�i : listeleyici) {
            if (testEdici.test (ki�i)) {
                String veri = haritalay�c�.apply (ki�i);
                blok.accept (veri);
            } // if karar� sonu..
        }
    } // fonksiyonelKi�ileri��le(..) metodu sonu...

    // Yakla��m 8: Daha geni� tipler kullan...
    public static <X, Y> void elemanlar�i�le (
        Iterable<X> kaynak,
        Predicate<X> testEdici,
        Function<X, Y> haritalay�c�,
        Consumer<Y> blok) {
            for (X ki�i : kaynak) {
                if (testEdici.test (ki�i)) {
                    Y veri = haritalay�c�.apply (ki�i);
                    blok.accept (veri);
                } // if karar� sonu...
            }
    } // elemanlar�i�le(..) metodu sonu...

    public static void main (String... args) {
        List<J2c_13ax> listeleyici = J2c_13ax.listeyiYarat();

        System.out.println ("Mevcut " + listeleyici.size() + " ki�inin d�k�m�:");
        for (J2c_13ax ki�i : listeleyici) ki�i.ki�iyiYaz();

        // Yakla��m 1:
        System.out.println ("\nYa� 20 �zeri ki�ilerin d�k�m�:");
        denYa�l�lar�Yaz (listeleyici, 20);

        // Yakla��m 2:
        System.out.println ("\n14-30 ya� aras� ki�ilerin d�k�m�:");
        ikiYa�Aras�Ki�iyiYaz (listeleyici, 14, 30);

        // Yakla��m 3:
        System.out.println ("\n�zel servise uygun ki�ilerin d�k�m�:");
        class �zelServiseUygunKi�iKontrolu implements Ki�iKontrolu {
           public boolean test (J2c_13ax ki�i) {
                return ki�i.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                    && ki�i.ya��Al() >= 18
                    && ki�i.ya��Al() <= 25;
            } // test(..) metodu sonu...
        } // �zelServiseUygunKi�iKontrolu s�n�f� sonu...

        ki�ileriYaz (listeleyici, new �zelServiseUygunKi�iKontrolu());

        // Yakla��m 4:
        System.out.println ("\n�zel servise ve anonim s�n�fa uygun ki�ilerin d�k�m�:");
        ki�ileriYaz (
            listeleyici,
            new Ki�iKontrolu() {public boolean test (J2c_13ax ki�i) {
                    return ki�i.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                        && ki�i.ya��Al() >= 18
                        && ki�i.ya��Al() <= 25;
                }
            }
        );

        // Yakla��m 5:
        System.out.println ("\n�zel servise ve Lambda ifadesine uygun ki�ilerin d�k�m�:");
        ki�ileriYaz (
            listeleyici,
            (J2c_13ax ki�i) -> ki�i.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                && ki�i.ya��Al() >= 18
                && ki�i.ya��Al() <= 25
        );

        // Yakla��m 6:
        System.out.println ("\n�zel servise ve �art parametresine uygun ki�ilerin d�k�m�:");
        �artl�Ki�ileriYaz (
            listeleyici,
            ki�i -> ki�i.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                && ki�i.ya��Al() >= 18
                && ki�i.ya��Al() <= 25
        );

        // Yakla��m 7:
        System.out.println ("\n�zel servise, �art ve T�ketici parametrelerine uygun ki�ilerin d�k�m�:");
        ki�ileri��le (
            listeleyici,
            ki�i -> ki�i.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                && ki�i.ya��Al() >= 18
                && ki�i.ya��Al() <= 25,
            ki�i -> ki�i.ki�iyiYaz()
        );

        // Yakla��m 7, ikinci �rnek...
        System.out.println ("\n�zel servise, �art, Fonksiyon ve T�ketici parametrelerine uygun ki�ilerin d�k�m�:");
        fonksiyonelKi�ileri��le (
            listeleyici,
            ki�i -> ki�i.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                && ki�i.ya��Al() >= 18
                && ki�i.ya��Al() <= 25,
            ki�i -> ki�i.epostay�Al(),
            eposta -> System.out.println (eposta) // �arta uyan sadece eposta'lar yazd�r�l�r...
        );

        // Yakla��m 8:
        System.out.println ("\n�zel servise ve daha geni� tip s�r�mlerine uyan ki�ilerin d�k�m�:");
        elemanlar�i�le (
            listeleyici,
            ki�i -> ki�i.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                && ki�i.ya��Al() >= 18
                && ki�i.ya��Al() <= 25,
            ki�i -> ki�i.epostay�Al(),
            eposta -> System.out.println (eposta)
        );

        // Yakla��m 9: Lambda ifadelerini parametre olarak kabul eden hacimli veri i�lemlerini kullan...
        System.out.println ("\n�zel servise ve hacimli veri i�lemlerine uygun ki�ilerin d�k�m�:");
        listeleyici
            .stream()
            .filter (
                ki�i -> ki�i.cinsiAl() == J2c_13ax.Cinsiyet.ERKEK
                    && ki�i.ya��Al() >= 18
                    && ki�i.ya��Al() <= 25)
            .map (ki�i -> ki�i.epostay�Al())
            .forEach (eposta -> System.out.println (eposta));
    } // main(..) metodu sonu...
} // J2c_13b s�n�f� sonu...

/* ��kt�:
**  >java J2c_13b  **
Mevcut 7 ki�inin d�k�m�:
Fred, 37, ERKEK, fred@gmail.com
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

Ya� 20 �zeri ki�ilerin d�k�m�:
Fred, 37, ERKEK, fred@gmail.com
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

14-30 ya� aras� ki�ilerin d�k�m�:
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

�zel servise uygun ki�ilerin d�k�m�:
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

�zel servise ve anonim s�n�fa uygun ki�ilerin d�k�m�:
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

�zel servise ve Lambda ifadesine uygun ki�ilerin d�k�m�:
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

�zel servise ve �art parametresine uygun ki�ilerin d�k�m�:
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

�zel servise, �art ve T�ketici parametrelerine uygun ki�ilerin d�k�m�:
Metin, 19, ERKEK, metin@hotmail.com
Ersin, 21, ERKEK, ersin@hotmail.com

�zel servise, �art, Fonksiyon ve T�ketici parametrelerine uygun ki�ilerin d�k�m�:
metin@hotmail.com
ersin@hotmail.com

�zel servise ve daha geni� tip s�r�mlerine uyan ki�ilerin d�k�m�:
metin@hotmail.com
ersin@hotmail.com

�zel servise ve hacimli veri i�lemlerine uygun ki�ilerin d�k�m�:
metin@hotmail.com
ersin@hotmail.com
*/