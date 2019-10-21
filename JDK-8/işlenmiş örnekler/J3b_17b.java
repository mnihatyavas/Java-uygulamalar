// J3b_17b.java: ScanXan (TaraXan) �rne�i.

import java.io.FileReader; // Karakter okuyucu...
import java.io.BufferedReader; // Sat�r okuyucu...
import java.util.Scanner; // Kelime okuyucu...
import java.io.IOException;

// Gereken dosya: xanadu.txt
public class J3b_17b {
    public static void main (String[] args) throws IOException {
        String dosyaAd� = args.length > 0? args[0] : "xanadu.txt";

        System.out.println ("Sat�r okuyucu BufferedReader ile [" + dosyaAd� + "]");
        BufferedReader br = new BufferedReader (new FileReader (dosyaAd�));
        String sat�r;
        while ((sat�r = br.readLine()) != null) System.out.println (sat�r);
        br.close();

        Scanner taray�c� = null;
        taray�c� = new Scanner (new BufferedReader (new FileReader (dosyaAd�)));
        System.out.println ("\nKelime okuyucu Scanner ile [" + dosyaAd� + "]");
        while (taray�c�.hasNext()) System.out.println (taray�c�.next());
        if (taray�c� != null) taray�c�.close();
    } // main(..) metodu sonu...
} // J3b_17b s�n�f� sonu...

/* ��kt�:
**  >java J3b_17b  **
Sat�r okuyucu BufferedReader ile [xanadu.txt]
In Xanadu did Kubla Khan
A stately pleasure-dome decree:
Where Alph, the sacred river, ran
Through caverns measureless to man
Down to a sunless sea.

Kelime okuyucu Scanner ile [xanadu.txt]
In
Xanadu
did
Kubla
Khan
A
stately
pleasure-dome
decree:
Where
Alph,
the
sacred
river,
ran
Through
caverns
measureless
to
man
Down
to
a
sunless
sea.
*/