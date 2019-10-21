// J3e_4b.java: RegexTestHarness2 (RegexTestKoþumu2) örneði.

import java.io.Console; // konsol.readLine: klavye, konsol.format: ekran...
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

public class J3e_4b {
    public static void main (String[] args) {
        String regex = null;
        Pattern kalýp = null;
        Matcher eþleþtirici = null;

        Console konsol = System.console();
        if (konsol == null) {
            System.err.println ("Konsol NAMEVCUT!");
            System.exit (1);
        } // if kararý sonu...

        for (;;) {
            try {regex = konsol.readLine ("%nRegex'inizi girin [s: Çýk]: ");
                if (regex.equals ("s")) System.exit (0);
                kalýp = Pattern.compile (regex);
                eþleþtirici = kalýp.matcher (konsol.readLine ("Araþtýrýlacak dizgenizi girin: "));
            }catch (PatternSyntaxException ist) {
                konsol.format ("Regex/DüzenliÝfade'yle ilgili bir problem var!%n");
                konsol.format ("Problemli kalýbýnýz: [%s]%n",ist.getPattern());
                konsol.format ("Açýklama: [%s]%n", ist.getDescription());
                konsol.format ("Mesaj: [%s]%n", ist.getMessage());
                konsol.format ("Endeks: [%s]%n", ist.getIndex());
                System.exit (0);
            } // try-catch bloðu sonu...

            boolean bulundu = false;
            while (eþleþtirici.find()) {
                konsol.format ("Bulunan \"%s\" metnin ilk endeksi: %d ve son endeksi ise: %d'dir.%n", eþleþtirici.group(), eþleþtirici.start(), eþleþtirici.end());
                bulundu = true;
            } // while döngüsü sonu...

            if (!bulundu) konsol.format ("Hiçbir eþleþme bulunamadý.%n");
        } // for(;;) döngüsü sonu...
    } // main(..) metodu sonu...
} // J3e_4b sýnýfý sonu...

/* Çýktý:
**  >java J3e_4b  **
Regex'inizi girin [s: Çýk]: "bir"
Araþtýrýlacak dizgenizi girin: "bir varmýþ bir yokmuþ"
Hiçbir eþleþme bulunamadý.

Regex'inizi girin [s: Çýk]: bir
Araþtýrýlacak dizgenizi girin: bir yokmuþ bir de birli çokmuþ
Bulunan "bir" metnin ilk endeksi: 0 ve son endeksi ise: 3'dir.
Bulunan "bir" metnin ilk endeksi: 11 ve son endeksi ise: 14'dir.
Bulunan "bir" metnin ilk endeksi: 18 ve son endeksi ise: 21'dir.

Regex'inizi girin [s: Çýk]: \d
Araþtýrýlacak dizgenizi girin: bir1, iki2, üç3, dört4
Bulunan "1" metnin ilk endeksi: 3 ve son endeksi ise: 4'dir.
Bulunan "2" metnin ilk endeksi: 9 ve son endeksi ise: 10'dir.
Bulunan "3" metnin ilk endeksi: 14 ve son endeksi ise: 15'dir.
Bulunan "4" metnin ilk endeksi: 21 ve son endeksi ise: 22'dir.

Regex'inizi girin [s: Çýk]: \\d
Araþtýrýlacak dizgenizi girin: 1234
Hiçbir eþleþme bulunamadý.

Regex'inizi girin [s: Çýk]: \ç
Araþtýrýlacak dizgenizi girin: çççç
Bulunan "ç" metnin ilk endeksi: 0 ve son endeksi ise: 1'dir.
Bulunan "ç" metnin ilk endeksi: 1 ve son endeksi ise: 2'dir.
Bulunan "ç" metnin ilk endeksi: 2 ve son endeksi ise: 3'dir.
Bulunan "ç" metnin ilk endeksi: 3 ve son endeksi ise: 4'dir.

Regex'inizi girin [s: Çýk]: **ç*
Regex/DüzenliÝfade'yle ilgili bir problem var!
Problemli kalýbýnýz: [**ç*]
Açýklama: [Dangling meta character '*']
Mesaj: [Dangling meta character '*' near index 0 **ç*^]
Endeks: [0]
*/