// J3b_17b.java: ScanXan (TaraXan) örneði.

import java.io.FileReader; // Karakter okuyucu...
import java.io.BufferedReader; // Satýr okuyucu...
import java.util.Scanner; // Kelime okuyucu...
import java.io.IOException;

// Gereken dosya: xanadu.txt
public class J3b_17b {
    public static void main (String[] args) throws IOException {
        String dosyaAdý = args.length > 0? args[0] : "xanadu.txt";

        System.out.println ("Satýr okuyucu BufferedReader ile [" + dosyaAdý + "]");
        BufferedReader br = new BufferedReader (new FileReader (dosyaAdý));
        String satýr;
        while ((satýr = br.readLine()) != null) System.out.println (satýr);
        br.close();

        Scanner tarayýcý = null;
        tarayýcý = new Scanner (new BufferedReader (new FileReader (dosyaAdý)));
        System.out.println ("\nKelime okuyucu Scanner ile [" + dosyaAdý + "]");
        while (tarayýcý.hasNext()) System.out.println (tarayýcý.next());
        if (tarayýcý != null) tarayýcý.close();
    } // main(..) metodu sonu...
} // J3b_17b sýnýfý sonu...

/* Çýktý:
**  >java J3b_17b  **
Satýr okuyucu BufferedReader ile [xanadu.txt]
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