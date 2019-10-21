// J7b_2x2.java: FamilyBirthdays (AileDo�umg�nleri) alt-�rne�i.

import java.time.Month;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.ChronoField;

public class J7b_2x2 {
    // Parametrik ay/g�n'leri sorgular, mevcutsa True d�nd�r�r...
    public static Boolean do�umg�n�M� (TemporalAccessor tarih) {
        int ay = tarih.get (ChronoField.MONTH_OF_YEAR);
        int g�n   = tarih.get (ChronoField.DAY_OF_MONTH);

        // Angie'nin do�umg�n�: 3 Nisan...
        if ((ay == Month.APRIL.getValue()) && (g�n == 3)) return Boolean.TRUE;

        // Sue'nun do�umg�n�: 18 Haziran...
        if ((ay == Month.JUNE.getValue()) && (g�n == 18)) return Boolean.TRUE;

        // Joe'nun do�umg�n�: 29 May�s...
        if ((ay == Month.MAY.getValue()) && (g�n == 29)) return Boolean.TRUE;

        // Nihat'�n do�umg�n�: 17 Nisan...
        if ((ay == Month.APRIL.getValue()) && (g�n == 17)) return Boolean.TRUE;

        return Boolean.FALSE;
    } // do�umg�n�M�(..) metodu sonu...
} // J7b_2x2 s�n�f� sonu...