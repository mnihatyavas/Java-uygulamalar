// J3b_8.java: DiskUsage (DiskinKullanýmý) örneði.

import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class J3b_8 {
    static final long K = 1024;
    static long gt = 0, gk = 0, gb = 0;

    static void dosyaDeposunuYaz (FileStore depo) throws IOException {
        long toplam = depo.getTotalSpace() / K;
        long kullanýlan = (depo.getTotalSpace() - depo.getUnallocatedSpace()) / K;
        long boþ = depo.getUsableSpace() / K;
        gt += toplam; gk += kullanýlan; gb += boþ;

        String dizge = depo.toString();
        if (dizge.length() > 20) {
            System.out.println (dizge);
            dizge = "";
        } // if kararý sonu...
        System.out.format ("%-20s %12d %12d %12d\n", dizge, toplam, kullanýlan, boþ);
    } // dosyaDeposunuYaz(..) metodu sonu...

    public static void main (String[] args) throws IOException {
        System.out.format ("%-20s %12s %12s %12s\n", "Dosya Sistemi", "Toplam KB", "Dolu KB", "Boþ KB");
        if (args.length == 0) {
            FileSystem dosyaSistemi = FileSystems.getDefault();
            for (FileStore depo: dosyaSistemi.getFileStores()) dosyaDeposunuYaz (depo);
        }else {
            for (String dosya: args) {
                FileStore depo = Files.getFileStore (Paths.get (dosya));
                dosyaDeposunuYaz (depo);
            } // for-each döngüsü sonu...
        } // else kararý sonu...

        System.out.format ("%n%-20s %12d %12d %12d\n", "Toplam deðerler:", gt, gk, gb);
    } // main(..) metodu sonu...
} // J3b_8 sýnýfý sonu...

/* Çýktý:
**  >java J3b_8 c:  **
Dosya Sistemi           Toplam KB      Dolu KB       Boþ KB
(C:)                    153497596     34269108    119228488

Toplam deðerler:        153497596     34269108    119228488

**  >java J3b_8 d:  **
Dosya Sistemi           Toplam KB      Dolu KB       Boþ KB
(D:)                    334784508     41927704    292856804

Toplam deðerler:        334784508     41927704    292856804

**  >java J3b_8  **
Dosya Sistemi           Toplam KB      Dolu KB       Boþ KB
(C:)                    153497596     34269108    119228488
(D:)                    334784508     41927704    292856804

Toplam deðerler:        488282104     76196812    412085292
*/