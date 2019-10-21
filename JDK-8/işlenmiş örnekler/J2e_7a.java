// J2e_7a.java: SimpleTimeClient (BasitZamanM��terisi) �rne�i.

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
        LocalDate g�ncelTarih = LocalDate.from (tarihVeZaman);
        LocalTime konacakZaman = LocalTime.of (saat, dakika, saniye);
        tarihVeZaman = LocalDateTime.of (g�ncelTarih, konacakZaman);
    } // zamanKoy() metodu sonu...

    public void tarihKoy (int g�n, int ay, int y�l) {
        LocalDate konacakTarih = LocalDate.of (g�n, ay, y�l);
        LocalTime g�ncelZaman = LocalTime.from (tarihVeZaman);
        tarihVeZaman = LocalDateTime.of (konacakTarih, g�ncelZaman);
    } // tarihKoy(..) metodu sonu...

    public void tarihVeZamanKoy (int g�n, int ay, int y�l, int saat, int dakika, int saniye) {
        LocalDate konacakTarih = LocalDate.of (g�n, ay, y�l);
        LocalTime konacakZaman = LocalTime.of (saat, dakika, saniye); 
        tarihVeZaman = LocalDateTime.of (konacakTarih, konacakZaman);
    } // tarihVeZamanKoy(..) metodu sonu...

    public LocalDateTime yerelTarihZamanAl() {return tarihVeZaman;}
    public String toString() {return tarihVeZaman.toString();}

    public ZoneId b�lgeKimli�iAl (String b�lgeStr) {
        try {return ZoneId.of (b�lgeStr);
        } catch (DateTimeException ist) {
            System.err.print ("\nGe�ersiz zaman b�lgesi: " + b�lgeStr +
                "; bu y�zden varsay�l� zaman b�lgesi kullan�lacak.");
            return ZoneId.systemDefault();
        } // try-catch blo�u sonu...
    } // b�lgeKimli�iAl(..) metodu sonu...

    public ZonedDateTime b�lgeselTarihZamanAl (String b�lgeStr) {
        return ZonedDateTime.of (yerelTarihZamanAl(), b�lgeKimli�iAl (b�lgeStr));
    } // b�lgeselTarihZamanAl(..) metodu sonu...

    public static void main(String... args) {
        J2e_7ax zamanM��terim = new J2e_7a(); // J2e_7a() kurucusunu y�r�t�r...
        System.out.println ("Yerel tarih ve zaman: [" + zamanM��terim.toString() + "]"); // Kurucunun "LocalDateTime.now()"�n� yans�t�r...
    } // main(..) metodu sonu...
} // J2e_7a s�n�f� sonu...

/* ��kt�:
**  >java J2e_7a  **
Yerel tarih ve zaman: [2018-05-02T20:56:37.192]
*/