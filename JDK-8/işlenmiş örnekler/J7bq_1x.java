// J7bq_1x.java: FridayThirteenQuery (CumaOn��Sorgusu) alt-�rne�i.

import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.ChronoField;

public class J7bq_1x implements TemporalQuery<Boolean> {
    // E�er tarih 13 Cuma ise  "true" d�nd�r�r...
    public Boolean queryFrom (TemporalAccessor tarih) {
        return ((tarih.get (ChronoField.DAY_OF_MONTH) == 13) &&
                (tarih.get (ChronoField.DAY_OF_WEEK) == 5));
    } // queryFrom(..) haz�r metodu sonu...
} // J7bq_1x s�n�f� sonu...