// J7b_2x2.java: FamilyBirthdays (AileDoðumgünleri) alt-örneði.

import java.time.Month;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.ChronoField;

public class J7b_2x2 {
    // Parametrik ay/gün'leri sorgular, mevcutsa True döndürür...
    public static Boolean doðumgünüMü (TemporalAccessor tarih) {
        int ay = tarih.get (ChronoField.MONTH_OF_YEAR);
        int gün   = tarih.get (ChronoField.DAY_OF_MONTH);

        // Angie'nin doðumgünü: 3 Nisan...
        if ((ay == Month.APRIL.getValue()) && (gün == 3)) return Boolean.TRUE;

        // Sue'nun doðumgünü: 18 Haziran...
        if ((ay == Month.JUNE.getValue()) && (gün == 18)) return Boolean.TRUE;

        // Joe'nun doðumgünü: 29 Mayýs...
        if ((ay == Month.MAY.getValue()) && (gün == 29)) return Boolean.TRUE;

        // Nihat'ýn doðumgünü: 17 Nisan...
        if ((ay == Month.APRIL.getValue()) && (gün == 17)) return Boolean.TRUE;

        return Boolean.FALSE;
    } // doðumgünüMü(..) metodu sonu...
} // J7b_2x2 sýnýfý sonu...