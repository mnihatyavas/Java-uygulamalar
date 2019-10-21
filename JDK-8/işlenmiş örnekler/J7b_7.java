// J7b_7.java: TimeZoneId (ZamanDilimiKimli�i) �rne�i.

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

// ��kt�ya k�s�ratl� ve k�s�rats�z zaman dilimlerini belirterek, bir .txt dosyas�na da t�m�n� yazar...
public class J7b_7 {
    public static void main (String[] args) {
        // T�m zaman dilimlerini listeye al�p s�ralayal�m...
        List<String> zamanDilimleriListesi = new ArrayList<String> (ZoneId.getAvailableZoneIds());
        Collections.sort (zamanDilimleriListesi);

        LocalDateTime yerelTarih = LocalDateTime.now();

        Path yol = Paths.get ("zamanDilimleri.txt");
        try (BufferedWriter dilimDosyas� = Files.newBufferedWriter (yol, StandardCharsets.US_ASCII)) {
            for (String zamanDilimi : zamanDilimleriListesi) {
                ZoneId dilimKimli�i = ZoneId.of (zamanDilimi);
                ZonedDateTime dilimTarihi = yerelTarih.atZone (dilimKimli�i);
                ZoneOffset saatFark� = dilimTarihi.getOffset();
                int k�s�ratl�SaatFark� = saatFark�.getTotalSeconds() % (60 * 60);
                String ��kt�Dizgesi = String.format ("%35s %7s", dilimKimli�i, saatFark�);

                if (k�s�ratl�SaatFark� != 0) System.out.println (��kt�Dizgesi + " ==>K�S�RATLI saat fark�");
                else System.out.println (��kt�Dizgesi + " ==>TAM saat fark�");

                // T�m zaman dilimleri ve saat farklar�n� tek-tek disk dosyas�na yazal�m...
                dilimDosyas�.write (��kt�Dizgesi + "\n");
            } // for d�ng�s� sonu...
            System.out.println ("\nNOT: Z:Greenwich-�ngiltere s�f�r zaman dilimi, +:Do�udakiler, -:Bat�dakiler");
            dilimDosyas�.write ("\nNOT: Z:Greenwich-Ingiltere '0' zaman dilimi, +:Dogudakiler, -:Batidakiler");
        }catch (IOException ist) {System.err.format ("IOException Okuma/Yazma Hatas�: %s%n", ist);
        } // try-catch blo�u sonu...
    } // main(..) metodu sonu...
} // J7b_7 s�n�f� sonu...

/* ��kt�:
** >java J7b_7  ** (��kt�n�n bir k�sm�d�r. T�m listeyi "zamanDilimleri.txt" dosyas�ndan g�rebilirsiniz.)
                             Turkey  +03:00 ==>TAM saat fark�
                                UCT       Z ==>TAM saat fark�
                          US/Alaska  -08:00 ==>TAM saat fark�
                        US/Aleutian  -09:00 ==>TAM saat fark�
                         US/Arizona  -07:00 ==>TAM saat fark�
                         US/Central  -05:00 ==>TAM saat fark�
                    US/East-Indiana  -04:00 ==>TAM saat fark�
                         US/Eastern  -04:00 ==>TAM saat fark�
                          US/Hawaii  -10:00 ==>TAM saat fark�
                  US/Indiana-Starke  -05:00 ==>TAM saat fark�
                        US/Michigan  -04:00 ==>TAM saat fark�
                        US/Mountain  -06:00 ==>TAM saat fark�
                         US/Pacific  -07:00 ==>TAM saat fark�
                     US/Pacific-New  -07:00 ==>TAM saat fark�
                           US/Samoa  -11:00 ==>TAM saat fark�
                                UTC       Z ==>TAM saat fark�
                          Universal       Z ==>TAM saat fark�
                               W-SU  +03:00 ==>TAM saat fark�
                                WET       Z ==>TAM saat fark�
                               Zulu       Z ==>TAM saat fark�

NOT: Z:Greenwich-�ngiltere s�f�r zaman dilimi, +:Do�udakiler, -:Bat�dakiler
*/