// J2e_7b.java: TestSimpleTimeClient (DeneBasitZamanMüþterisini) örneði.

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class J2e_7b extends J2e_7a {// J2e_7a=SimpleTimeClient/BasitZamanMüþterisi...
    public static void main (String... args) {
        J2e_7ax zamanMüþterim = new J2e_7b();
        System.out.println ("Aktüel yerel tarih ve zaman (metodla): [" + zamanMüþterim.toString() + "]");

        System.out.println ("California'daki (veya varsayýlý) bölgesel tarih ve zaman: [" +
            zamanMüþterim.bölgeselTarihZamanAl ("Kem küm").toString() + "]");
        System.out.println ("\nLos Angeles'daki (veya varsayýlý) bölgesel tarih ve zaman: [" +
            zamanMüþterim.bölgeselTarihZamanAl ("America/Los_Angeles").toString() + "]");
        System.out.println ("Turkey'deki (veya varsayýlý) bölgesel tarih ve zaman: [" +
            zamanMüþterim.bölgeselTarihZamanAl ("Turkey").toString() + "]");
        System.out.println ("Ýstanbul'daki (veya varsayýlý) bölgesel tarih ve zaman: [" +
            zamanMüþterim.bölgeselTarihZamanAl ("Europe/Istanbul").toString() + "]");
        System.out.println ("Kaliningrad'daki (veya varsayýlý) bölgesel tarih ve zaman: [" +
            zamanMüþterim.bölgeselTarihZamanAl ("Europe/Kaliningrad").toString() + "]");

        System.out.println ("\nAktüel yerel->bölgesel tarih ve zaman (doðrudan)==>");
        System.out.println ("Rusya Kaliningrad'da tarih ve zaman: [" + LocalDateTime.now (ZoneId.of ("Europe/Kaliningrad")) + "]");
        System.out.println ("Hindistan'da tarih ve zaman: [" + LocalDateTime.now (ZoneId.of ("Asia/Kolkata")) + "]");
        System.out.println ("Japonya'da tarih ve zaman: [" + LocalDateTime.now (ZoneId.of ("Asia/Tokyo")) + "]");
        System.out.println ("Los Angeles'da tarih ve zaman: [" + LocalDateTime.now (ZoneId.of ("America/Los_Angeles")) + "]");
        System.out.println ("Türkiye'de tarih ve zaman: [" + LocalDateTime.now (ZoneId.of ("Turkey")) + "]");
        System.out.println ("Ýstanbul'da tarih ve zaman: [" + LocalDateTime.now (ZoneId.of ("Europe/Istanbul")) + "]");
    } // main(..) metodu sonu...
} // J2e_7b sýnýfý sonu...

/* Çýktý:
**  >java J2e_7b  **
Aktüel yerel tarih ve zaman (metodla): [2018-05-02T22:36:21.950]

Geçersiz zaman bölgesi: Kem küm; bu yüzden varsayýlý zaman bölgesi kullanýlacak.
California'daki (veya varsayýlý) bölgesel tarih ve zaman: [2018-05-02T22:36:21.950+02:00[Europe/Kaliningrad]]

Los Angeles'daki (veya varsayýlý) bölgesel tarih ve zaman: [2018-05-02T22:36:21.950-07:00[America/Los_Angeles]]
Turkey'deki (veya varsayýlý) bölgesel tarih ve zaman: [2018-05-02T22:36:21.950+03:00[Turkey]]
Ýstanbul'daki (veya varsayýlý) bölgesel tarih ve zaman: [2018-05-02T22:36:21.950+03:00[Europe/Istanbul]]
Kaliningrad'daki (veya varsayýlý) bölgesel tarih ve zaman: [2018-05-02T22:36:21.950+02:00[Europe/Kaliningrad]]

Aktüel yerel->bölgesel tarih ve zaman (doðrudan)==>
Rusya Kaliningrad'da tarih ve zaman: [2018-05-02T22:36:22.018]
Hindistan'da tarih ve zaman: [2018-05-03T02:06:22.018]
Japonya'da tarih ve zaman: [2018-05-03T05:36:22.018]
Los Angeles'da tarih ve zaman: [2018-05-02T13:36:22.018]
Türkiye'de tarih ve zaman: [2018-05-02T23:36:22.018]
Ýstanbul'da tarih ve zaman: [2018-05-02T23:36:22.028]
*/