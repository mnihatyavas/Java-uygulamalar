// J3b_17a.java: ScanSum (TarayýcýToplamý) örneði.

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;

// Gereken dosya: sayýlar.txt
public class J3b_17a {
    public static void main (String[] args) throws IOException {
        String dosyaAdý = args.length > 0? args[0] : "sayýlar.txt";

        System.out.println ("Orijinal içeriklerin listesi:");
        BufferedReader br = new BufferedReader (new FileReader (dosyaAdý));
        String satýr;
        while ((satýr = br.readLine()) != null) System.out.println (satýr);
        br.close();

        Scanner tarayýcý = null;
        double sayý, toplam = 0;

        tarayýcý = new Scanner (new BufferedReader (new FileReader (dosyaAdý)));
        tarayýcý.useLocale (Locale.US);

        System.out.println ("\nSayýsal verilerin listesi:");
        while (tarayýcý.hasNext()) {
            if (tarayýcý.hasNextDouble()) {
                sayý = tarayýcý.nextDouble();
                toplam += sayý;
                System.out.println (sayý);
            }else tarayýcý.next();
        } // while döngüsü sonu...

        tarayýcý.close();

        System.out.println ("=============================\nToplam: " + toplam);
    } // main(..) metodu sonu...
} // J3b_17a sýnýfý sonu...

/* Çýktý:
**  >java J3b_17a  **
Orijinal içeriklerin listesi:
8.5
32,767
3.14159
1,000,000.1
M.Nihat Yavaþ
Toroslar-Mersin
Doðum tarihi:
17
04
1957
TC No:
43879313154

Sayýsal verilerin listesi:
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
Orijinal içeriklerin listesi:
// J3b_17a.java: ScanSum (TarayýcýToplamý) örneði.

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
Orijinal içeriklerin listesi:
8.5
32,767
3.14159
1,000,000.1
M.Nihat Yavaþ
Toroslar-Mersin
Doðum tarihi:
17
04
1957
TC No:
43879313154

Sayýsal verilerin listesi:
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

Sayýsal verilerin listesi:
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