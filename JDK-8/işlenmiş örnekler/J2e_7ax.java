// J2e_7ax.java: TimeClient (ZamanMüþterisi) alt-örneði.

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
 
public interface J2e_7ax {
    void zamanKoy (int saat, int dakika, int saniye); // Arayüzün gövdesiz soyut metodlarý...
    void tarihKoy (int gün, int ay, int yýl);
    void tarihVeZamanKoy (int gün, int ay, int yýl, int saat, int dakika, int saniye);

    ZonedDateTime bölgeselTarihZamanAl (String bölgeStr);
    ZoneId bölgeKimliðiAl (String bölgeStr);
} // J2e_7ax arayüzü sonu...