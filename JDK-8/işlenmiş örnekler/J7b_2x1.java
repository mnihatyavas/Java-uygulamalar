// J7b_2x1.java: FamilyVacations (AileTatilleri) alt-�rne�i.

import java.time.Month;

import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.ChronoField;

public class J7b_2x1 implements TemporalQuery<Boolean> {
    // Ay/g�n parametreleriyle, mevcutsa True d�nd�r�r...
    public Boolean queryFrom (TemporalAccessor tarih) {
        int ay = tarih.get(ChronoField.MONTH_OF_YEAR);
        int g�n   = tarih.get(ChronoField.DAY_OF_MONTH);

        // �lk tatil: P�nar Kav�a��'ndaki Disneyland oyunpark� ziyareti...
        if ((ay == Month.APRIL.getValue()) && ((g�n >= 3) && (g�n <= 8))) return Boolean.TRUE;

        // Saugatuck G�l�'nde Narburcu ailesi bulu�mas�...
        if ((ay == Month.AUGUST.getValue()) && ((g�n >= 8) && (g�n <= 14))) return Boolean.TRUE;

        // BangKong'da g�zellik tema�a tavaf�...
        if ((ay == Month.FEBRUARY.getValue()) && ((g�n >= 1) && (g�n <= 10))) return Boolean.TRUE;

        return Boolean.FALSE;
    } // queryFrom (..) haz�r metodu sonu...
} // J7b_2x1 s�n�f� sonu...