// J2e_7b.java: TestSimpleTimeClient (DeneBasitZamanM��terisini) �rne�i.

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class J2e_7b extends J2e_7a {// J2e_7a=SimpleTimeClient/BasitZamanM��terisi...
    public static void main (String... args) {
        J2e_7ax zamanM��terim = new J2e_7b();
        System.out.println ("Akt�el yerel tarih ve zaman (metodla): [" + zamanM��terim.toString() + "]");

        System.out.println ("California'daki (veya varsay�l�) b�lgesel tarih ve zaman: [" +
            zamanM��terim.b�lgeselTarihZamanAl ("Kem k�m").toString() + "]");
        System.out.println ("\nLos Angeles'daki (veya varsay�l�) b�lgesel tarih ve zaman: [" +
            zamanM��terim.b�lgeselTarihZamanAl ("America/Los_Angeles").toString() + "]");
        System.out.println ("Turkey'deki (veya varsay�l�) b�lgesel tarih ve zaman: [" +
            zamanM��terim.b�lgeselTarihZamanAl ("Turkey").toString() + "]");
        System.out.println ("�stanbul'daki (veya varsay�l�) b�lgesel tarih ve zaman: [" +
            zamanM��terim.b�lgeselTarihZamanAl ("Europe/Istanbul").toString() + "]");
        System.out.println ("Kaliningrad'daki (veya varsay�l�) b�lgesel tarih ve zaman: [" +
            zamanM��terim.b�lgeselTarihZamanAl ("Europe/Kaliningrad").toString() + "]");

        System.out.println ("\nAkt�el yerel->b�lgesel tarih ve zaman (do�rudan)==>");
        System.out.println ("Rusya Kaliningrad'da tarih ve zaman: [" + LocalDateTime.now (ZoneId.of ("Europe/Kaliningrad")) + "]");
        System.out.println ("Hindistan'da tarih ve zaman: [" + LocalDateTime.now (ZoneId.of ("Asia/Kolkata")) + "]");
        System.out.println ("Japonya'da tarih ve zaman: [" + LocalDateTime.now (ZoneId.of ("Asia/Tokyo")) + "]");
        System.out.println ("Los Angeles'da tarih ve zaman: [" + LocalDateTime.now (ZoneId.of ("America/Los_Angeles")) + "]");
        System.out.println ("T�rkiye'de tarih ve zaman: [" + LocalDateTime.now (ZoneId.of ("Turkey")) + "]");
        System.out.println ("�stanbul'da tarih ve zaman: [" + LocalDateTime.now (ZoneId.of ("Europe/Istanbul")) + "]");
    } // main(..) metodu sonu...
} // J2e_7b s�n�f� sonu...

/* ��kt�:
**  >java J2e_7b  **
Akt�el yerel tarih ve zaman (metodla): [2018-05-02T22:36:21.950]

Ge�ersiz zaman b�lgesi: Kem k�m; bu y�zden varsay�l� zaman b�lgesi kullan�lacak.
California'daki (veya varsay�l�) b�lgesel tarih ve zaman: [2018-05-02T22:36:21.950+02:00[Europe/Kaliningrad]]

Los Angeles'daki (veya varsay�l�) b�lgesel tarih ve zaman: [2018-05-02T22:36:21.950-07:00[America/Los_Angeles]]
Turkey'deki (veya varsay�l�) b�lgesel tarih ve zaman: [2018-05-02T22:36:21.950+03:00[Turkey]]
�stanbul'daki (veya varsay�l�) b�lgesel tarih ve zaman: [2018-05-02T22:36:21.950+03:00[Europe/Istanbul]]
Kaliningrad'daki (veya varsay�l�) b�lgesel tarih ve zaman: [2018-05-02T22:36:21.950+02:00[Europe/Kaliningrad]]

Akt�el yerel->b�lgesel tarih ve zaman (do�rudan)==>
Rusya Kaliningrad'da tarih ve zaman: [2018-05-02T22:36:22.018]
Hindistan'da tarih ve zaman: [2018-05-03T02:06:22.018]
Japonya'da tarih ve zaman: [2018-05-03T05:36:22.018]
Los Angeles'da tarih ve zaman: [2018-05-02T13:36:22.018]
T�rkiye'de tarih ve zaman: [2018-05-02T23:36:22.018]
�stanbul'da tarih ve zaman: [2018-05-02T23:36:22.028]
*/