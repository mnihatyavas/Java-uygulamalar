// J7b_3.java: Flight (Uçuş) örneği.

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

/* San Fransisko->Tokyo arası, 7:30 pm'de kalkan ve 10 saat 50 dakika süren uçuş,
 * zaman dilimi farkı da gözetilerek hesaplanacaktır (10:20 pm - 6:20 am = 20 saat dilimi farkı).
 */
public class J7b_3 {
    public static void main(String[] args) {
        DateTimeFormatter biçimleyici = DateTimeFormatter.ofPattern ("MMMM d yyyy,  hh:mm a");

        // San Fransisko'dan kalkış: October/Ekim 28, 2018, saat 7:30 pm (akşam)...
        LocalDateTime yerelzamanlıÇıkış = LocalDateTime.of (2018, Month.OCTOBER, 28, 19, 30);
        ZoneId çıkışZamandilimi = ZoneId.of ("America/Los_Angeles"); 
        ZonedDateTime zamandilimliÇıkış = ZonedDateTime.of (yerelzamanlıÇıkış, çıkışZamandilimi);

        try {String biçim1 = zamandilimliÇıkış.format (biçimleyici);
            System.out.printf ("AYRILIŞ:  %s (%s)%n", biçim1, çıkışZamandilimi);
        }catch (DateTimeException ist) {
            System.err.printf ("[%s] formatlanamadı!%n", zamandilimliÇıkış);
            throw ist;
        } // try-catch bloğu sonu...

        // Uçuş süresi 10 saat 50 dakika, yani toplamda 650 dakika...
        ZoneId varışZamandilimi = ZoneId.of ("Asia/Tokyo"); 
        ZonedDateTime zamandilimliVarış = zamandilimliÇıkış
                .withZoneSameInstant (varışZamandilimi)
                .plusMinutes (10*60+50);

        try {String biçim2 = zamandilimliVarış.format (biçimleyici);
            System.out.printf ("VARIŞ: %s (%s)%n", biçim2, varışZamandilimi);
        }catch (DateTimeException ist) {
            System.out.printf ("[%s] formatlanamadı!%n", zamandilimliVarış);
            throw ist;
        } // try-catch bloğu sonu...

        if (varışZamandilimi.getRules().isDaylightSavings (zamandilimliVarış.toInstant())) 
            System.out.printf ("--->(%s günışığından tasarruf saatı etkindir.)%n", varışZamandilimi);
        else System.out.printf ("--->(%s standart saat etkindir.)%n", varışZamandilimi);
    } // main(..) metodu sonu...
} // J7b_3 sınıfı sonu...

/* Çıktı:
**  >java J7b_3  **
AYRILIŞ:  Ekim 28 2018,  07:30 PM (America/Los_Angeles)
VARIŞ: Ekim 29 2018,  10:20 PM (Asia/Tokyo)
--->(Asia/Tokyo standart saat etkindir.)
*/