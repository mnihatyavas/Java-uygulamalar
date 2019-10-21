// J3b_7c.java: CopyCharacters (KopyalaKarakterleri) �rne�i.

import java.io.FileReader; // FileInputStream ile ayn�...
import java.io.FileWriter; // FileOutputStream ile ayn�...
import java.io.IOException;

public class J3b_7c {
    public static void main (String[] args) throws IOException {
        String okunanDosya = args.length > 0? args[0] : "J3b_7c.java";
        String kopyaDosya = args.length > 1? args[1] : "kopya.java";

        FileReader oku = null;
        FileWriter yaz = null;

        try {oku = new FileReader (okunanDosya);
            yaz = new FileWriter (kopyaDosya);

            char krk;
            while ((krk = (char)oku.read()) != (char)-1) yaz.write (krk);
//          int k; while ((k = oku.read()) != -1) yaz.write (k); //==> B�yle de olur...
        }finally {
            if (oku != null) oku.close();
            if (yaz != null) yaz.close();
        } // try-finally blo�u sonu...

       System.out.println ("[" + okunanDosya + "] --> [" + kopyaDosya + "]'ya kopyaland�.");
    } // main(..) metodu sonu...
} // J3b_7c s�n�f� sonu...

/* ��kt�:
**  >java J3b_7c  **
[J3b_7c.java] --> [kopya.java]'ya kopyaland�.

**  >java J3b_7c J3b_7a.java kopya1.java  ** TEKRAR
[J3b_7a.java] --> [kopya1.java]'ya kopyaland�.
*/