// J2e_7ax.java: TimeClient (ZamanM��terisi) alt-�rne�i.

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
 
public interface J2e_7ax {
    void zamanKoy (int saat, int dakika, int saniye); // Aray�z�n g�vdesiz soyut metodlar�...
    void tarihKoy (int g�n, int ay, int y�l);
    void tarihVeZamanKoy (int g�n, int ay, int y�l, int saat, int dakika, int saniye);

    ZonedDateTime b�lgeselTarihZamanAl (String b�lgeStr);
    ZoneId b�lgeKimli�iAl (String b�lgeStr);
} // J2e_7ax aray�z� sonu...