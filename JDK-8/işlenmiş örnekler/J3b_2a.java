// J3b_2a.java: BufferedCopy (TamponluKopya) �rne�i.

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class J3b_2a {
    public static void main (String[] args) throws IOException {
        String okunanDosya = args.length > 0? args[0] : "J3b_2a.java";
        String kopyaDosya = args.length > 1? args[1] : "kopya2a.java";

        BufferedReader okumaKanal� = null;
        BufferedWriter yazmaKanal� = null;

        try {okumaKanal� = new BufferedReader (new FileReader (okunanDosya));
            yazmaKanal� = new BufferedWriter (new FileWriter (kopyaDosya));

            int k;
            while ((k = okumaKanal�.read()) != -1) yazmaKanal�.write (k);
        }finally {
            if (okumaKanal� != null) okumaKanal�.close();
            if (yazmaKanal� != null) yazmaKanal�.close();
        } // try-finally blo�u sonu...

        System.out.println ("[" + okunanDosya + "==>" + kopyaDosya + "] dosya kopyalama tamamland�.");
    } // main(..) metodu sonu...
} // J3b_2a s�n�f� sonu...

/* ��kt�:
**  >java J3b_2a  **
[J3b_2a.java==>kopya2a.java] dosya kopyalama tamamland�.

**  >java J3b_2a J3b_3.java kopya3.java  ** TEKRAR
[J3b_3.java==>kopya3.java] dosya kopyalama tamamland�.
*/