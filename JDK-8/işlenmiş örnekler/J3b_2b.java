// J3b_2b.java: BufferlessCopy (TamponsuzKopya) örneði.

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class J3b_2b {
    public static void main (String[] args) throws IOException {
        String okunanDosya = args.length > 0? args[0] : "J3b_2a.java";
        String kopyaDosya = args.length > 1? args[1] : "kopya2a.java";

        FileReader okuma = new FileReader (okunanDosya);
        FileWriter yazma = new FileWriter (kopyaDosya);

        int k;
        while ((k = okuma.read()) != -1) yazma.write (k);

        okuma.close();
        yazma.close();

        System.out.println ("[" + okunanDosya + "==>" + kopyaDosya + "] dosya kopyalama tamamlandý.");
    } // main(..) metodu sonu...
} // J3b_2b sýnýfý sonu...

/* Çýktý:
**  >java J3b_2b  **
[J3b_2a.java==>kopya2a.java] dosya kopyalama tamamlandý.

**  >java J3b_2b J3b_3.java kopya3.java  ** TEKRAR
[J3b_3.java==>kopya3.java] dosya kopyalama tamamlandý.
*/