// J3e_4b.java: RegexTestHarness2 (RegexTestKo�umu2) �rne�i.

import java.io.Console; // konsol.readLine: klavye, konsol.format: ekran...
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

public class J3e_4b {
    public static void main (String[] args) {
        String regex = null;
        Pattern kal�p = null;
        Matcher e�le�tirici = null;

        Console konsol = System.console();
        if (konsol == null) {
            System.err.println ("Konsol NAMEVCUT!");
            System.exit (1);
        } // if karar� sonu...

        for (;;) {
            try {regex = konsol.readLine ("%nRegex'inizi girin [s: ��k]: ");
                if (regex.equals ("s")) System.exit (0);
                kal�p = Pattern.compile (regex);
                e�le�tirici = kal�p.matcher (konsol.readLine ("Ara�t�r�lacak dizgenizi girin: "));
            }catch (PatternSyntaxException ist) {
                konsol.format ("Regex/D�zenli�fade'yle ilgili bir problem var!%n");
                konsol.format ("Problemli kal�b�n�z: [%s]%n",ist.getPattern());
                konsol.format ("A��klama: [%s]%n", ist.getDescription());
                konsol.format ("Mesaj: [%s]%n", ist.getMessage());
                konsol.format ("Endeks: [%s]%n", ist.getIndex());
                System.exit (0);
            } // try-catch blo�u sonu...

            boolean bulundu = false;
            while (e�le�tirici.find()) {
                konsol.format ("Bulunan \"%s\" metnin ilk endeksi: %d ve son endeksi ise: %d'dir.%n", e�le�tirici.group(), e�le�tirici.start(), e�le�tirici.end());
                bulundu = true;
            } // while d�ng�s� sonu...

            if (!bulundu) konsol.format ("Hi�bir e�le�me bulunamad�.%n");
        } // for(;;) d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J3e_4b s�n�f� sonu...

/* ��kt�:
**  >java J3e_4b  **
Regex'inizi girin [s: ��k]: "bir"
Ara�t�r�lacak dizgenizi girin: "bir varm�� bir yokmu�"
Hi�bir e�le�me bulunamad�.

Regex'inizi girin [s: ��k]: bir
Ara�t�r�lacak dizgenizi girin: bir yokmu� bir de birli �okmu�
Bulunan "bir" metnin ilk endeksi: 0 ve son endeksi ise: 3'dir.
Bulunan "bir" metnin ilk endeksi: 11 ve son endeksi ise: 14'dir.
Bulunan "bir" metnin ilk endeksi: 18 ve son endeksi ise: 21'dir.

Regex'inizi girin [s: ��k]: \d
Ara�t�r�lacak dizgenizi girin: bir1, iki2, ��3, d�rt4
Bulunan "1" metnin ilk endeksi: 3 ve son endeksi ise: 4'dir.
Bulunan "2" metnin ilk endeksi: 9 ve son endeksi ise: 10'dir.
Bulunan "3" metnin ilk endeksi: 14 ve son endeksi ise: 15'dir.
Bulunan "4" metnin ilk endeksi: 21 ve son endeksi ise: 22'dir.

Regex'inizi girin [s: ��k]: \\d
Ara�t�r�lacak dizgenizi girin: 1234
Hi�bir e�le�me bulunamad�.

Regex'inizi girin [s: ��k]: \�
Ara�t�r�lacak dizgenizi girin: ����
Bulunan "�" metnin ilk endeksi: 0 ve son endeksi ise: 1'dir.
Bulunan "�" metnin ilk endeksi: 1 ve son endeksi ise: 2'dir.
Bulunan "�" metnin ilk endeksi: 2 ve son endeksi ise: 3'dir.
Bulunan "�" metnin ilk endeksi: 3 ve son endeksi ise: 4'dir.

Regex'inizi girin [s: ��k]: **�*
Regex/D�zenli�fade'yle ilgili bir problem var!
Problemli kal�b�n�z: [**�*]
A��klama: [Dangling meta character '*']
Mesaj: [Dangling meta character '*' near index 0 **�*^]
Endeks: [0]
*/