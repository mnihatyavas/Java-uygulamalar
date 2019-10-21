// J3b_10.java: FileType (DosyaTipi) �rne�i.

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class J3b_10 {
    public static void main (String[] args) throws IOException {
        if (args.length == 0) {System.err.println ("Tipi belirlenecek yol ve/veya dosya(lar) girilmelidir!.."); System.exit (-1);}

        for (String arg�man: args) {
            Path dosya = Paths.get (arg�man);
            String tip;

            if (Files.isDirectory (dosya)) tip = "directory/dizin";
            else {tip = Files.probeContentType (dosya);
                if (tip == null) tip = "<tan�ms�z>";
            } // else karar� sonu...

            System.out.format ("[%s]\t[%s]%n", dosya, tip);
        } // for-each d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J3b_10 s�n�f� sonu...

/* ��kt�:
**  >java J3b_10  **
Tipi belirlenecek yol ve/veya dosya(lar) girilmelidir!..

**  >java J3b_10 "C:\Users\pc\Desktop\MyFiles\4. Dersler\JDK-8\uygulama" ** TEKRAR
[C:\Users\pc\Desktop\MyFiles\4. Dersler\JDK-8\uygulama] [directory/dizin]

**  >java J3b_10 J3b_10.java J3b_10.class  ** TEKRAR
[J3b_10.java]   [<tan�ms�z>]
[J3b_10.class]  [<tan�ms�z>]

**  >java J3b_10 *.*  ** TEKRAR
[1.Not Defteri.lnk]     [<tan�ms�z>]
[2.cmd.exe]     [application/x-msdownload]
[J3bq_1.java]   [<tan�ms�z>]
[J3bq_2.java]   [<tan�ms�z>]
[J3b_10.class]  [<tan�ms�z>]
[J3b_10.java]   [<tan�ms�z>]
[J3b_9.class]   [<tan�ms�z>]
[J3b_9.java]    [<tan�ms�z>]
[__JDK-8 �rnekleri Kodlamas�.HAM.txt]   [text/plain]
*/