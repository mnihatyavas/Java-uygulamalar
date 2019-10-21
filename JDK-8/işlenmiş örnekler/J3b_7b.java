// J3b_7b.java: CopyBytes (KopyalaByte'lar�) �rne�i.

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class J3b_7b {
    public static void main (String[] args) throws IOException {
        String okunanDosya = args.length > 0? args[0] : "J3b_7b.java";
        String kopyaDosya = args.length > 1? args[1] : "kopya.java";

        FileInputStream oku = null;
        FileOutputStream yaz = null;

        try {oku = new FileInputStream (okunanDosya);
            yaz = new FileOutputStream (kopyaDosya);

            int k;
            while ((k = oku.read()) != -1) yaz.write (k);
        }finally {
            if (oku != null) oku.close();
            if (yaz != null) yaz.close();
        } // try-finally blo�u sonu...

       System.out.println ("[" + okunanDosya + "] --> [" + kopyaDosya + "]'ya kopyaland�.");
    } // main(..) metodu sonu...
} // J3b_7b s�n�f� sonu...

/* ��kt�:
**  >java J3b_7b  **
[J3b_7b.java] --> [kopya.java]'ya kopyaland�.

**  >java J3b_7b J3b_6.java kopya1.java  ** TEKRAR
[J3b_6.java] --> [kopya1.java]'ya kopyaland�.
*/