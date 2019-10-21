// J7b_4x.java: PaydayAdjuster (MaaþgünüAyarlayýcýsý) örneði.

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

// Maaþ günleri ay-ortasý ve sonudur. Tatile denk gelirse bir önceki cuma'yý farzeder...
public class J7b_4x implements TemporalAdjuster {
    // Verili tarihi alýr ve ayarlý tarihi döndürür...
    public Temporal adjustInto (Temporal girdi) {
        LocalDate tarih = LocalDate.from (girdi);
        int gün;
        if (tarih.getDayOfMonth() < 15) gün = 15;
        else gün = tarih.with (TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        tarih = tarih.withDayOfMonth (gün);
        if (tarih.getDayOfWeek() == DayOfWeek.SATURDAY || tarih.getDayOfWeek() == DayOfWeek.SUNDAY)
            tarih = tarih.with (TemporalAdjusters.previous (DayOfWeek.FRIDAY));

        return girdi.with (tarih);
    } // adjustInto(..) hazýr metodu sonu...
} // J7b_4x sýnýfý sonu...