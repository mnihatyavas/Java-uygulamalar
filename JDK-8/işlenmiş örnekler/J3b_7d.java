// J3b_7d.java: CopyLines (KopyalaSatýrlarý) örneði.

import java.io.FileReader; // int/char okur...
import java.io.FileWriter; // int/char yazar...

import java.io.BufferedReader; // String satýr okur...
import java.io.PrintWriter; // String satýr yazar...

import java.io.IOException;

public class J3b_7d {
    public static void main (String[] args) throws IOException {
        String okunanDosya = args.length > 0? args[0] : "J3b_7d.java";
        String kopyaDosya = args.length > 1? args[1] : "kopya.java";

        BufferedReader oku = null;
        PrintWriter yaz = null;

        oku = new BufferedReader (new FileReader (okunanDosya));
        yaz = new PrintWriter (new FileWriter (kopyaDosya));

        String satýr;
        while ((satýr = oku.readLine()) != null) yaz.println (satýr);
        if (oku != null) oku.close();
        if (yaz != null) yaz.close();

       System.out.println ("[" + okunanDosya + "] --> [" + kopyaDosya + "]'ya kopyalandý.");
    } // main(..) metodu sonu...
} // J3b_7d sýnýfý sonu...

/* Çýktý:
**  >java J3b_7d  **
[J3b_7d.java] --> [kopya.java]'ya kopyalandý.

**  >java J3b_7d J3b_7a.java kopya1.java  ** TEKRAR
[J3b_7a.java] --> [kopya1.java]'ya kopyalandý.
*/