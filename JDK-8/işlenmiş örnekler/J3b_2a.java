// J3b_2a.java: BufferedCopy (TamponluKopya) örneði.

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class J3b_2a {
    public static void main (String[] args) throws IOException {
        String okunanDosya = args.length > 0? args[0] : "J3b_2a.java";
        String kopyaDosya = args.length > 1? args[1] : "kopya2a.java";

        BufferedReader okumaKanalý = null;
        BufferedWriter yazmaKanalý = null;

        try {okumaKanalý = new BufferedReader (new FileReader (okunanDosya));
            yazmaKanalý = new BufferedWriter (new FileWriter (kopyaDosya));

            int k;
            while ((k = okumaKanalý.read()) != -1) yazmaKanalý.write (k);
        }finally {
            if (okumaKanalý != null) okumaKanalý.close();
            if (yazmaKanalý != null) yazmaKanalý.close();
        } // try-finally bloðu sonu...

        System.out.println ("[" + okunanDosya + "==>" + kopyaDosya + "] dosya kopyalama tamamlandý.");
    } // main(..) metodu sonu...
} // J3b_2a sýnýfý sonu...

/* Çýktý:
**  >java J3b_2a  **
[J3b_2a.java==>kopya2a.java] dosya kopyalama tamamlandý.

**  >java J3b_2a J3b_3.java kopya3.java  ** TEKRAR
[J3b_3.java==>kopya3.java] dosya kopyalama tamamlandý.
*/