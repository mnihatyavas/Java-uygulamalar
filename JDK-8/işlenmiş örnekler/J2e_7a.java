// J2e_7a.java: SimpleTimeClient (BasitZamanMüþterisi) örneði.

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.DateTimeException;

// Gereken dosya: J2e_7ax.java=TimeClient
public class J2e_7a implements J2e_7ax {
    private LocalDateTime tarihVeZaman;
    
    public J2e_7a() {tarihVeZaman = LocalDateTime.now();} // Kurucu...
    
    public void zamanKoy (int saat, int dakika, int saniye) {
        LocalDate güncelTarih = LocalDate.from (tarihVeZaman);
        LocalTime konacakZaman = LocalTime.of (saat, dakika, saniye);
        tarihVeZaman = LocalDateTime.of (güncelTarih, konacakZaman);
    } // zamanKoy() metodu sonu...

    public void tarihKoy (int gün, int ay, int yýl) {
        LocalDate konacakTarih = LocalDate.of (gün, ay, yýl);
        LocalTime güncelZaman = LocalTime.from (tarihVeZaman);
        tarihVeZaman = LocalDateTime.of (konacakTarih, güncelZaman);
    } // tarihKoy(..) metodu sonu...

    public void tarihVeZamanKoy (int gün, int ay, int yýl, int saat, int dakika, int saniye) {
        LocalDate konacakTarih = LocalDate.of (gün, ay, yýl);
        LocalTime konacakZaman = LocalTime.of (saat, dakika, saniye); 
        tarihVeZaman = LocalDateTime.of (konacakTarih, konacakZaman);
    } // tarihVeZamanKoy(..) metodu sonu...

    public LocalDateTime yerelTarihZamanAl() {return tarihVeZaman;}
    public String toString() {return tarihVeZaman.toString();}

    public ZoneId bölgeKimliðiAl (String bölgeStr) {
        try {return ZoneId.of (bölgeStr);
        } catch (DateTimeException ist) {
            System.err.print ("\nGeçersiz zaman bölgesi: " + bölgeStr +
                "; bu yüzden varsayýlý zaman bölgesi kullanýlacak.");
            return ZoneId.systemDefault();
        } // try-catch bloðu sonu...
    } // bölgeKimliðiAl(..) metodu sonu...

    public ZonedDateTime bölgeselTarihZamanAl (String bölgeStr) {
        return ZonedDateTime.of (yerelTarihZamanAl(), bölgeKimliðiAl (bölgeStr));
    } // bölgeselTarihZamanAl(..) metodu sonu...

    public static void main(String... args) {
        J2e_7ax zamanMüþterim = new J2e_7a(); // J2e_7a() kurucusunu yürütür...
        System.out.println ("Yerel tarih ve zaman: [" + zamanMüþterim.toString() + "]"); // Kurucunun "LocalDateTime.now()"ýný yansýtýr...
    } // main(..) metodu sonu...
} // J2e_7a sýnýfý sonu...

/* Çýktý:
**  >java J2e_7a  **
Yerel tarih ve zaman: [2018-05-02T20:56:37.192]
*/