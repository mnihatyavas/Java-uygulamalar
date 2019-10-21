// J3e_4a.java: RegexTestHarness (RegexTestKo�umu) �rne�i.

import java.io.Console; // Konsol: klavye girdisi ve ekran ��kt�s�...
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class J3e_4a {
    public static void main (String[] args) {
        Console konsol = System.console();
        if (konsol == null) {// Klavye-ekran yoksa...
            System.err.println ("Konsolunuz mevcut DE��L!");
            System.exit (1);
        } // if karar� sonu...

        while (true) {// ^C ile ��k�l�r...
            Pattern kal�p = Pattern.compile (konsol.readLine ("%nRegex kal�b�n� girin: "));
            Matcher e�le�tirici = kal�p.matcher (konsol.readLine ("Ara�t�r�lacak dizgeyi girin: "));

            boolean bulundu = false;
            while (e�le�tirici.find()) {
                konsol.format ("Bulunan \"%s\" metnin ilk endeksi: %d ve son endeksi ise: %d'dir.%n", e�le�tirici.group(), e�le�tirici.start(), e�le�tirici.end());
                bulundu = true;
            } // while d�ng�s� sonu...

            if (!bulundu) konsol.format ("Hi�bir e�le�me bulunamad�.%n");
        } // while-true sonsuz d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J3e_4a s�n�f� sonu...

/* ��kt�:
**  >java J3e_4a  **
Regex kal�b�n� girin: bir
Ara�t�r�lacak dizgeyi girin: bir varm�� bir yokmu�
Bulunan "bir" metnin ilk endeksi: 0 ve son endeksi ise: 3'dir.
Bulunan "bir" metnin ilk endeksi: 11 ve son endeksi ise: 14'dir.

Regex kal�b�n� girin:
Ara�t�r�lacak dizgeyi girin: Bir art� bir eder  iki.
Bulunan " " metnin ilk endeksi: 3 ve son endeksi ise: 4'dir.
Bulunan " " metnin ilk endeksi: 8 ve son endeksi ise: 9'dir.
Bulunan " " metnin ilk endeksi: 12 ve son endeksi ise: 13'dir.
Bulunan " " metnin ilk endeksi: 17 ve son endeksi ise: 18'dir.
Bulunan " " metnin ilk endeksi: 18 ve son endeksi ise: 19'dir.

Regex kal�b�n� girin: \d
Ara�t�r�lacak dizgeyi girin: Bir:1, �ki:2, ��:3 ve D�rt: 4.
Bulunan "1" metnin ilk endeksi: 4 ve son endeksi ise: 5'dir.
Bulunan "2" metnin ilk endeksi: 11 ve son endeksi ise: 12'dir.
Bulunan "3" metnin ilk endeksi: 17 ve son endeksi ise: 18'dir.
Bulunan "4" metnin ilk endeksi: 28 ve son endeksi ise: 29'dir.
Regex kal�b�n� girin: Exception in thread "main"
*/