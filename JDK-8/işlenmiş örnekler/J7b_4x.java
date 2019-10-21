// J7b_4x.java: PaydayAdjuster (Maa�g�n�Ayarlay�c�s�) �rne�i.

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

// Maa� g�nleri ay-ortas� ve sonudur. Tatile denk gelirse bir �nceki cuma'y� farzeder...
public class J7b_4x implements TemporalAdjuster {
    // Verili tarihi al�r ve ayarl� tarihi d�nd�r�r...
    public Temporal adjustInto (Temporal girdi) {
        LocalDate tarih = LocalDate.from (girdi);
        int g�n;
        if (tarih.getDayOfMonth() < 15) g�n = 15;
        else g�n = tarih.with (TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        tarih = tarih.withDayOfMonth (g�n);
        if (tarih.getDayOfWeek() == DayOfWeek.SATURDAY || tarih.getDayOfWeek() == DayOfWeek.SUNDAY)
            tarih = tarih.with (TemporalAdjusters.previous (DayOfWeek.FRIDAY));

        return girdi.with (tarih);
    } // adjustInto(..) haz�r metodu sonu...
} // J7b_4x s�n�f� sonu...