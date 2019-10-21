// J7b_6.java: StringConverter (DizgeÇevirici) örneði.

import java.time.LocalDate;
import java.time.Month;
import java.time.DateTimeException;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.chrono.JapaneseChronology;
import java.time.chrono.MinguoChronology;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.chrono.HijrahChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DecimalStyle;
import java.time.temporal.TemporalAccessor;

import java.util.Locale; 

// "2018 Month.OCTOBER 31" tarihini diðer takvimlere, sonra da tekrar Iso/Miladi'ye çevirir...
public class J7b_6 {
    public static String miladiden (LocalDate miladiTarih, Chronology diðerTakvim) {
        if (miladiTarih != null) {
            Locale yerel = Locale.getDefault (Locale.Category.FORMAT);
            ChronoLocalDate takvimTarihi;
            if (diðerTakvim == null) diðerTakvim = IsoChronology.INSTANCE;

            try {takvimTarihi = diðerTakvim.date (miladiTarih);
            }catch (DateTimeException ist) {
                System.err.println (ist);
                diðerTakvim = IsoChronology.INSTANCE;
                takvimTarihi = miladiTarih;
            } // try-catch bloðu sonu...

            String tarihKalýbý = "yyyy/M/d GGGGG";
            DateTimeFormatter tarihBiçimleyici = DateTimeFormatter.ofPattern (tarihKalýbý);
            return tarihBiçimleyici.format (takvimTarihi);
        }else return "";
    } // miladiden(..) metodu sonu...

    public static LocalDate miladiye (String tarihDizgesi, Chronology diðerTakvim) {
        if (tarihDizgesi != null && !tarihDizgesi.isEmpty()) {
            Locale yerel = Locale.getDefault (Locale.Category.FORMAT);
            if (diðerTakvim == null) diðerTakvim = IsoChronology.INSTANCE;

            String tarihKalýbý = "yyyy/M/d GGGGG";
            DateTimeFormatter biçimleyici = new DateTimeFormatterBuilder()
                    .parseLenient()
                    .appendPattern (tarihKalýbý)
                    .toFormatter()
                    .withChronology (diðerTakvim)
                    .withDecimalStyle (DecimalStyle.of (yerel));
            TemporalAccessor zamane = biçimleyici.parse (tarihDizgesi);
            ChronoLocalDate takvimTarihi = diðerTakvim.date (zamane);
            return LocalDate.from (takvimTarihi);
        } // if kararý sonu...
        return null;
    } // miladiye(..) metodu sonu...

    public static void main (String[] argüman) {
        LocalDate tarih = LocalDate.of (2018, Month.OCTOBER, 31); // Miladi Iso/Ýsa takvimi...
        try {if (argüman.length == 3) tarih = LocalDate.of (
                Integer.valueOf (argüman[0]), Month.valueOf (argüman[1].toUpperCase()), Integer.valueOf (argüman[2]));
            else System.out.println ("Günün tarihini 3'lü argüman olarak [2018 october 31] þeklinde girebilirdin!");
        }catch (Exception ist) {tarih = LocalDate.of (2018, Month.OCTOBER, 31);
        } // try-catch bloðu sonu...
        System.out.println ("Verili tarih: [" + tarih + "]");

        System.out.printf ("%n%s: Miladi'den-->Japon takvimine%n", J7b_6.miladiden (tarih, JapaneseChronology.INSTANCE));
        System.out.printf ("%s: Miladi'den-->Moðol takvimine%n", J7b_6.miladiden (tarih, MinguoChronology.INSTANCE));
        System.out.printf ("%s: Miladi'den-->TaiBudist takvimine%n", J7b_6.miladiden (tarih, ThaiBuddhistChronology.INSTANCE));
        System.out.printf ("%s: Miladi'den-->Hicri takvimine%n", J7b_6.miladiden (tarih, HijrahChronology.INSTANCE));

        System.out.printf ("%n%s: Japon'dan-->Miladi takvime%n", J7b_6.miladiye (J7b_6.miladiden (tarih, JapaneseChronology.INSTANCE), JapaneseChronology.INSTANCE));
        System.out.printf ("%s: Moðol'dan-->Miladi takvime%n", J7b_6.miladiye (J7b_6.miladiden (tarih, MinguoChronology.INSTANCE), MinguoChronology.INSTANCE));
        System.out.printf ("%s: TaiBudist'ten-->Miladi takvime%n", J7b_6.miladiye (J7b_6.miladiden (tarih, ThaiBuddhistChronology.INSTANCE), ThaiBuddhistChronology.INSTANCE));
        System.out.printf ("%s: Hicri'den-->Miladi takvime%n", J7b_6.miladiye (J7b_6.miladiden (tarih, HijrahChronology.INSTANCE), HijrahChronology.INSTANCE));
    } // main(..) metodu sonu...
} // J7b_6 sýnýfý sonu...

/* Çýktý:
**  >java J7b_6  **
Günün tarihini 3'lü argüman olarak [2018 october 31] þeklinde girebilirdin!
Verili tarih: [2018-10-31]

0030/10/31 H: Miladi'den-->Japon takvimine
0107/10/31 1: Miladi'den-->Moðol takvimine
2561/10/31 B.E.: Miladi'den-->TaiBudist takvimine
1440/2/22 1: Miladi'den-->Hicri takvimine

2018-10-31: Japon'dan-->Miladi takvime
2018-10-31: Moðol'dan-->Miladi takvime
2018-10-31: TaiBudist'ten-->Miladi takvime
2018-10-31: Hicri'den-->Miladi takvime

**  >java J7b_6 2018 NOVEMBER 1  ** TEKRAR

Verili tarih: [2018-11-01]

0030/11/1 H: Miladi'den-->Japon takvimine
0107/11/1 1: Miladi'den-->Moðol takvimine
2561/11/1 B.E.: Miladi'den-->TaiBudist takvimine
1440/2/23 1: Miladi'den-->Hicri takvimine

2018-11-01: Japon'dan-->Miladi takvime
2018-11-01: Moðol'dan-->Miladi takvime
2018-11-01: TaiBudist'ten-->Miladi takvime
2018-11-01: Hicri'den-->Miladi takvime

**  >java J7b_6 2019 january 1  ** TEKRAR
Verili tarih: [2019-01-01]

0031/1/1 H: Miladi'den-->Japon takvimine
0108/1/1 1: Miladi'den-->Moðol takvimine
2562/1/1 B.E.: Miladi'den-->TaiBudist takvimine
1440/4/25 1: Miladi'den-->Hicri takvimine

2019-01-01: Japon'dan-->Miladi takvime
2019-01-01: Moðol'dan-->Miladi takvime
2019-01-01: TaiBudist'ten-->Miladi takvime
2019-01-01: Hicri'den-->Miladi takvime
*/