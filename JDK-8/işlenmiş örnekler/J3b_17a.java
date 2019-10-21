// J3b_17a.java: ScanSum (Taray�c�Toplam�) �rne�i.

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;

// Gereken dosya: say�lar.txt
public class J3b_17a {
    public static void main (String[] args) throws IOException {
        String dosyaAd� = args.length > 0? args[0] : "say�lar.txt";

        System.out.println ("Orijinal i�eriklerin listesi:");
        BufferedReader br = new BufferedReader (new FileReader (dosyaAd�));
        String sat�r;
        while ((sat�r = br.readLine()) != null) System.out.println (sat�r);
        br.close();

        Scanner taray�c� = null;
        double say�, toplam = 0;

        taray�c� = new Scanner (new BufferedReader (new FileReader (dosyaAd�)));
        taray�c�.useLocale (Locale.US);

        System.out.println ("\nSay�sal verilerin listesi:");
        while (taray�c�.hasNext()) {
            if (taray�c�.hasNextDouble()) {
                say� = taray�c�.nextDouble();
                toplam += say�;
                System.out.println (say�);
            }else taray�c�.next();
        } // while d�ng�s� sonu...

        taray�c�.close();

        System.out.println ("=============================\nToplam: " + toplam);
    } // main(..) metodu sonu...
} // J3b_17a s�n�f� sonu...

/* ��kt�:
**  >java J3b_17a  **
Orijinal i�eriklerin listesi:
8.5
32,767
3.14159
1,000,000.1
M.Nihat Yava�
Toroslar-Mersin
Do�um tarihi:
17
04
1957
TC No:
43879313154

Say�sal verilerin listesi:
8.5
32767.0
3.14159
1000000.1
17.0
4.0
1957.0
4.3879313154E10
============================
Toplam: 4.388034791074159E10

**  >java J3b_17a J3b_17a.java  ** TEKRAR
Orijinal i�eriklerin listesi:
// J3b_17a.java: ScanSum (Taray�c�Toplam�) �rne�i.

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;

public class J3b_17a {
    public static void main (String[] args) throws IOException {
...........
...........
...........
Orijinal i�eriklerin listesi:
8.5
32,767
3.14159
1,000,000.1
M.Nihat Yava�
Toroslar-Mersin
Do�um tarihi:
17
04
1957
TC No:
43879313154

Say�sal verilerin listesi:
8.5
32767.0
3.14159
1000000.1
17.0
4.0
1957.0
4.3879313154E10
============================
Toplam: 4.388034791074159E10
*/

Say�sal verilerin listesi:
8.5
32767.0
3.14159
1000000.1
17.0
4.0
1957.0
4.3879313154E10
8.5
32767.0
3.14159
1000000.1
17.0
4.0
1957.0
4.3879313154E10
4.388034791074159E10
============================
Toplam: 1.3164104373222478E11
*/