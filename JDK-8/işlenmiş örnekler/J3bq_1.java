// J3bq_1.java: CountLetter (SayHarfi) �rne�i.

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class J3bq_1 {
    private char aranan;
    private Path dosya;

    J3bq_1 (char aranan, Path dosya) {
        this.aranan = aranan;
        this.dosya = dosya;
    } // J3bq_1(..) kurucusu sonu...

    public int say() throws IOException {
        int saya� = 0;
        try (InputStream giri� = Files.newInputStream (dosya);
            BufferedReader oku = new BufferedReader (new InputStreamReader (giri�)))
            {String sat�r = null;
            while ((sat�r = oku.readLine()) != null)
                for (int i = 0; i < sat�r.length(); i++) if (aranan == sat�r.charAt (i)) saya�++;
        }catch (IOException ist) {System.err.println (ist);
        } // try-catch blo�u sonu...
        return saya�;
    } // say() metodu sonu...

    static void uyar�() {
        System.out.println ("�kaz: java J3bq_1 <dosya> <harf>");
        System.exit (-1);
    } // uyar�() metodu sonu...

    public static void main (String[] args) throws IOException {
        if (args.length != 2 || args[1].length() != 1) uyar�();

        Path dosya = Paths.get (args[0]);
        char aranan = args[1].charAt (0);

        int saya� = new J3bq_1 (aranan, dosya).say();
        System.out.format ("'%s' dosyas�nda %d adet '%c' harfi say�lm��t�r.%n",
            dosya, saya�, aranan);
    } // main(..) metodu sonu...
} // J3bq_1 s�n�f� sonu...

/* ��kt�:
**  >java J3bq_1  **
�kaz: java J3bq_1 <dosya> <harf>

**  >java J3bq_1 xanadu.txt  ** TEKRAR
�kaz: java J3bq_1 <dosya> <harf>

**  >java J3bq_1 xanadu.txt a  ** TEKRAR
'xanadu.txt' dosyas�nda 13 adet 'a' harfi say�lm��t�r.

**  >java J3bq_1 J3bq_1.java o  ** TEKRAR
'J3bq_1.java' dosyas�nda 49 adet 'o' harfi say�lm��t�r.
*/