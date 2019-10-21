// J3bq_1.java: CountLetter (SayHarfi) örneði.

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
        int sayaç = 0;
        try (InputStream giriþ = Files.newInputStream (dosya);
            BufferedReader oku = new BufferedReader (new InputStreamReader (giriþ)))
            {String satýr = null;
            while ((satýr = oku.readLine()) != null)
                for (int i = 0; i < satýr.length(); i++) if (aranan == satýr.charAt (i)) sayaç++;
        }catch (IOException ist) {System.err.println (ist);
        } // try-catch bloðu sonu...
        return sayaç;
    } // say() metodu sonu...

    static void uyarý() {
        System.out.println ("Ýkaz: java J3bq_1 <dosya> <harf>");
        System.exit (-1);
    } // uyarý() metodu sonu...

    public static void main (String[] args) throws IOException {
        if (args.length != 2 || args[1].length() != 1) uyarý();

        Path dosya = Paths.get (args[0]);
        char aranan = args[1].charAt (0);

        int sayaç = new J3bq_1 (aranan, dosya).say();
        System.out.format ("'%s' dosyasýnda %d adet '%c' harfi sayýlmýþtýr.%n",
            dosya, sayaç, aranan);
    } // main(..) metodu sonu...
} // J3bq_1 sýnýfý sonu...

/* Çýktý:
**  >java J3bq_1  **
Ýkaz: java J3bq_1 <dosya> <harf>

**  >java J3bq_1 xanadu.txt  ** TEKRAR
Ýkaz: java J3bq_1 <dosya> <harf>

**  >java J3bq_1 xanadu.txt a  ** TEKRAR
'xanadu.txt' dosyasýnda 13 adet 'a' harfi sayýlmýþtýr.

**  >java J3bq_1 J3bq_1.java o  ** TEKRAR
'J3bq_1.java' dosyasýnda 49 adet 'o' harfi sayýlmýþtýr.
*/