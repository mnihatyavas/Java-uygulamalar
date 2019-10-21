// J7b_1.java: Birthday (Doðumgünü) örneði.

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

// Bugünden, gireceðiniz doðumgününe kalan ay-gün ve toplam gün hesaplanýr...
public class J7b_1 {
    public static void main (String[] argüman) {
        LocalDate doðumgünü = LocalDate.of (1957, Month.APRIL, 17);
        if (argüman.length == 3) doðumgünü = LocalDate.of (
            Integer.valueOf (argüman[0]), Month.valueOf (argüman[1].toUpperCase()), Integer.valueOf (argüman[2]));
        else System.out.println ("Doðum gününü 3'lü argüman olarak [1981 aprýl/may 12] þeklinde girebilirdin!");
        LocalDate bugün = LocalDate.now();

        LocalDate gelecekDoðumgünü = doðumgünü.withYear (bugün.getYear());

        // Eðer doðumgünü bu yýl geçmiþse, gelecek doðumgünü yýlý bir artýrýlýr...
        if (gelecekDoðumgünü.isBefore (bugün) || gelecekDoðumgünü.isEqual (bugün))
            gelecekDoðumgünü = gelecekDoðumgünü.plusYears (1);

        Period kalanAyGün = Period.between (bugün, gelecekDoðumgünü);
        long kalanGün = ChronoUnit.DAYS.between (bugün, gelecekDoðumgünü);
        System.out.println ("\nBugünün tarihi: " + bugün + " ve gelecek doðum günü: " + gelecekDoðumgünü);
        System.out.println ("Bir sonraki doðum gününe hala " + kalanAyGün.getMonths() + " ay ve " +
                kalanAyGün.getDays() + " gün kaldý. (Toplam " + kalanGün + " gün)");
    } // main(..) metodu sonu...
} // J7b_1 sýnýfý sonu...

/* Çýktý:
**  >java J7b_1  **
Doðum gününü 3'lü argüman olarak [1981 aprýl/may 12] þeklinde girebilirdin!

Bugünün tarihi: 2018-10-27 ve gelecek doðum günü: 2019-04-17
Bir sonraki doðum gününe hala 5 ay ve 21 gün kaldý. (Toplam 172 gün)

**  >java J7b_1 1960 aprýl 25  ** TEKRAR-1
Bugünün tarihi: 2018-10-27 ve gelecek doðum günü: 2019-04-25
Bir sonraki doðum gününe hala 5 ay ve 29 gün kaldý. (Toplam 180 gün)

**  >java J7b_1 1960 december 1  ** TEKRAR-2
Bugünün tarihi: 2018-10-27 ve gelecek doðum günü: 2018-12-01
Bir sonraki doðum gününe hala 1 ay ve 4 gün kaldý. (Toplam 35 gün)
*/