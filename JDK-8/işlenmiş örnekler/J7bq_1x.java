// J7bq_1x.java: FridayThirteenQuery (CumaOnüçSorgusu) alt-örneði.

import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.ChronoField;

public class J7bq_1x implements TemporalQuery<Boolean> {
    // Eðer tarih 13 Cuma ise  "true" döndürür...
    public Boolean queryFrom (TemporalAccessor tarih) {
        return ((tarih.get (ChronoField.DAY_OF_MONTH) == 13) &&
                (tarih.get (ChronoField.DAY_OF_WEEK) == 5));
    } // queryFrom(..) hazýr metodu sonu...
} // J7bq_1x sýnýfý sonu...