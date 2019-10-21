// J3e_4a.java: RegexTestHarness (RegexTestKoþumu) örneði.

import java.io.Console; // Konsol: klavye girdisi ve ekran çýktýsý...
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class J3e_4a {
    public static void main (String[] args) {
        Console konsol = System.console();
        if (konsol == null) {// Klavye-ekran yoksa...
            System.err.println ("Konsolunuz mevcut DEÐÝL!");
            System.exit (1);
        } // if kararý sonu...

        while (true) {// ^C ile çýkýlýr...
            Pattern kalýp = Pattern.compile (konsol.readLine ("%nRegex kalýbýný girin: "));
            Matcher eþleþtirici = kalýp.matcher (konsol.readLine ("Araþtýrýlacak dizgeyi girin: "));

            boolean bulundu = false;
            while (eþleþtirici.find()) {
                konsol.format ("Bulunan \"%s\" metnin ilk endeksi: %d ve son endeksi ise: %d'dir.%n", eþleþtirici.group(), eþleþtirici.start(), eþleþtirici.end());
                bulundu = true;
            } // while döngüsü sonu...

            if (!bulundu) konsol.format ("Hiçbir eþleþme bulunamadý.%n");
        } // while-true sonsuz döngüsü sonu...
    } // main(..) metodu sonu...
} // J3e_4a sýnýfý sonu...

/* Çýktý:
**  >java J3e_4a  **
Regex kalýbýný girin: bir
Araþtýrýlacak dizgeyi girin: bir varmýþ bir yokmuþ
Bulunan "bir" metnin ilk endeksi: 0 ve son endeksi ise: 3'dir.
Bulunan "bir" metnin ilk endeksi: 11 ve son endeksi ise: 14'dir.

Regex kalýbýný girin:
Araþtýrýlacak dizgeyi girin: Bir artý bir eder  iki.
Bulunan " " metnin ilk endeksi: 3 ve son endeksi ise: 4'dir.
Bulunan " " metnin ilk endeksi: 8 ve son endeksi ise: 9'dir.
Bulunan " " metnin ilk endeksi: 12 ve son endeksi ise: 13'dir.
Bulunan " " metnin ilk endeksi: 17 ve son endeksi ise: 18'dir.
Bulunan " " metnin ilk endeksi: 18 ve son endeksi ise: 19'dir.

Regex kalýbýný girin: \d
Araþtýrýlacak dizgeyi girin: Bir:1, Ýki:2, Üç:3 ve Dört: 4.
Bulunan "1" metnin ilk endeksi: 4 ve son endeksi ise: 5'dir.
Bulunan "2" metnin ilk endeksi: 11 ve son endeksi ise: 12'dir.
Bulunan "3" metnin ilk endeksi: 17 ve son endeksi ise: 18'dir.
Bulunan "4" metnin ilk endeksi: 28 ve son endeksi ise: 29'dir.
Regex kalýbýný girin: Exception in thread "main"
*/