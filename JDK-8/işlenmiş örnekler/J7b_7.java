// J7b_7.java: TimeZoneId (ZamanDilimiKimliði) örneði.

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;

import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

// Çýktýya küsüratlý ve küsüratsýz zaman dilimlerini belirterek, bir .txt dosyasýna da tümünü yazar...
public class J7b_7 {
    public static void main (String[] args) {
        // Tüm zaman dilimlerini listeye alýp sýralayalým...
        List<String> zamanDilimleriListesi = new ArrayList<String> (ZoneId.getAvailableZoneIds());
        Collections.sort (zamanDilimleriListesi);

        LocalDateTime yerelTarih = LocalDateTime.now();

        Path yol = Paths.get ("zamanDilimleri.txt");
        try (BufferedWriter dilimDosyasý = Files.newBufferedWriter (yol, StandardCharsets.US_ASCII)) {
            for (String zamanDilimi : zamanDilimleriListesi) {
                ZoneId dilimKimliði = ZoneId.of (zamanDilimi);
                ZonedDateTime dilimTarihi = yerelTarih.atZone (dilimKimliði);
                ZoneOffset saatFarký = dilimTarihi.getOffset();
                int küsüratlýSaatFarký = saatFarký.getTotalSeconds() % (60 * 60);
                String çýktýDizgesi = String.format ("%35s %7s", dilimKimliði, saatFarký);

                if (küsüratlýSaatFarký != 0) System.out.println (çýktýDizgesi + " ==>KÜSÜRATLI saat farký");
                else System.out.println (çýktýDizgesi + " ==>TAM saat farký");

                // Tüm zaman dilimleri ve saat farklarýný tek-tek disk dosyasýna yazalým...
                dilimDosyasý.write (çýktýDizgesi + "\n");
            } // for döngüsü sonu...
            System.out.println ("\nNOT: Z:Greenwich-Ýngiltere sýfýr zaman dilimi, +:Doðudakiler, -:Batýdakiler");
            dilimDosyasý.write ("\nNOT: Z:Greenwich-Ingiltere '0' zaman dilimi, +:Dogudakiler, -:Batidakiler");
        }catch (IOException ist) {System.err.format ("IOException Okuma/Yazma Hatasý: %s%n", ist);
        } // try-catch bloðu sonu...
    } // main(..) metodu sonu...
} // J7b_7 sýnýfý sonu...

/* Çýktý:
** >java J7b_7  ** (Çýktýnýn bir kýsmýdýr. Tüm listeyi "zamanDilimleri.txt" dosyasýndan görebilirsiniz.)
                             Turkey  +03:00 ==>TAM saat farký
                                UCT       Z ==>TAM saat farký
                          US/Alaska  -08:00 ==>TAM saat farký
                        US/Aleutian  -09:00 ==>TAM saat farký
                         US/Arizona  -07:00 ==>TAM saat farký
                         US/Central  -05:00 ==>TAM saat farký
                    US/East-Indiana  -04:00 ==>TAM saat farký
                         US/Eastern  -04:00 ==>TAM saat farký
                          US/Hawaii  -10:00 ==>TAM saat farký
                  US/Indiana-Starke  -05:00 ==>TAM saat farký
                        US/Michigan  -04:00 ==>TAM saat farký
                        US/Mountain  -06:00 ==>TAM saat farký
                         US/Pacific  -07:00 ==>TAM saat farký
                     US/Pacific-New  -07:00 ==>TAM saat farký
                           US/Samoa  -11:00 ==>TAM saat farký
                                UTC       Z ==>TAM saat farký
                          Universal       Z ==>TAM saat farký
                               W-SU  +03:00 ==>TAM saat farký
                                WET       Z ==>TAM saat farký
                               Zulu       Z ==>TAM saat farký

NOT: Z:Greenwich-Ýngiltere sýfýr zaman dilimi, +:Doðudakiler, -:Batýdakiler
*/