// J7b_2x1.java: FamilyVacations (AileTatilleri) alt-örneði.

import java.time.Month;

import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.ChronoField;

public class J7b_2x1 implements TemporalQuery<Boolean> {
    // Ay/gün parametreleriyle, mevcutsa True döndürür...
    public Boolean queryFrom (TemporalAccessor tarih) {
        int ay = tarih.get(ChronoField.MONTH_OF_YEAR);
        int gün   = tarih.get(ChronoField.DAY_OF_MONTH);

        // Ýlk tatil: Pýnar Kavþaðý'ndaki Disneyland oyunparký ziyareti...
        if ((ay == Month.APRIL.getValue()) && ((gün >= 3) && (gün <= 8))) return Boolean.TRUE;

        // Saugatuck Gölü'nde Narburcu ailesi buluþmasý...
        if ((ay == Month.AUGUST.getValue()) && ((gün >= 8) && (gün <= 14))) return Boolean.TRUE;

        // BangKong'da güzellik temaþa tavafý...
        if ((ay == Month.FEBRUARY.getValue()) && ((gün >= 1) && (gün <= 10))) return Boolean.TRUE;

        return Boolean.FALSE;
    } // queryFrom (..) hazýr metodu sonu...
} // J7b_2x1 sýnýfý sonu...