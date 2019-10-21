// J7b_3.java: Flight (U�u�) �rne�i.

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

/* San Fransisko->Tokyo aras�, 7:30 pm'de kalkan ve 10 saat 50 dakika s�ren u�u�,
 * zaman dilimi fark� da g�zetilerek hesaplanacakt�r (10:20 pm - 6:20 am = 20 saat dilimi fark�).
 */
public class J7b_3 {
    public static void main(String[] args) {
        DateTimeFormatter bi�imleyici = DateTimeFormatter.ofPattern ("MMMM d yyyy,  hh:mm a");

        // San Fransisko'dan kalk��: October/Ekim 28, 2018, saat 7:30 pm (ak�am)...
        LocalDateTime yerelzamanl���k�� = LocalDateTime.of (2018, Month.OCTOBER, 28, 19, 30);
        ZoneId ��k��Zamandilimi = ZoneId.of ("America/Los_Angeles"); 
        ZonedDateTime zamandilimli��k�� = ZonedDateTime.of (yerelzamanl���k��, ��k��Zamandilimi);

        try {String bi�im1 = zamandilimli��k��.format (bi�imleyici);
            System.out.printf ("AYRILI�:  %s (%s)%n", bi�im1, ��k��Zamandilimi);
        }catch (DateTimeException ist) {
            System.err.printf ("[%s] formatlanamad�!%n", zamandilimli��k��);
            throw ist;
        } // try-catch blo�u sonu...

        // U�u� s�resi 10 saat 50 dakika, yani toplamda 650 dakika...
        ZoneId var��Zamandilimi = ZoneId.of ("Asia/Tokyo"); 
        ZonedDateTime zamandilimliVar�� = zamandilimli��k��
                .withZoneSameInstant (var��Zamandilimi)
                .plusMinutes (10*60+50);

        try {String bi�im2 = zamandilimliVar��.format (bi�imleyici);
            System.out.printf ("VARI�: %s (%s)%n", bi�im2, var��Zamandilimi);
        }catch (DateTimeException ist) {
            System.out.printf ("[%s] formatlanamad�!%n", zamandilimliVar��);
            throw ist;
        } // try-catch blo�u sonu...

        if (var��Zamandilimi.getRules().isDaylightSavings (zamandilimliVar��.toInstant())) 
            System.out.printf ("--->(%s g�n�����ndan tasarruf saat� etkindir.)%n", var��Zamandilimi);
        else System.out.printf ("--->(%s standart saat etkindir.)%n", var��Zamandilimi);
    } // main(..) metodu sonu...
} // J7b_3 s�n�f� sonu...

/* ��kt�:
**  >java J7b_3  **
AYRILI�:  Ekim 28 2018,  07:30 PM (America/Los_Angeles)
VARI�: Ekim 29 2018,  10:20 PM (Asia/Tokyo)
--->(Asia/Tokyo standart saat etkindir.)
*/