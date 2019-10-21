// J3b_8.java: DiskUsage (DiskinKullan�m�) �rne�i.

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
        long kullan�lan = (depo.getTotalSpace() - depo.getUnallocatedSpace()) / K;
        long bo� = depo.getUsableSpace() / K;
        gt += toplam; gk += kullan�lan; gb += bo�;

        String dizge = depo.toString();
        if (dizge.length() > 20) {
            System.out.println (dizge);
            dizge = "";
        } // if karar� sonu...
        System.out.format ("%-20s %12d %12d %12d\n", dizge, toplam, kullan�lan, bo�);
    } // dosyaDeposunuYaz(..) metodu sonu...

    public static void main (String[] args) throws IOException {
        System.out.format ("%-20s %12s %12s %12s\n", "Dosya Sistemi", "Toplam KB", "Dolu KB", "Bo� KB");
        if (args.length == 0) {
            FileSystem dosyaSistemi = FileSystems.getDefault();
            for (FileStore depo: dosyaSistemi.getFileStores()) dosyaDeposunuYaz (depo);
        }else {
            for (String dosya: args) {
                FileStore depo = Files.getFileStore (Paths.get (dosya));
                dosyaDeposunuYaz (depo);
            } // for-each d�ng�s� sonu...
        } // else karar� sonu...

        System.out.format ("%n%-20s %12d %12d %12d\n", "Toplam de�erler:", gt, gk, gb);
    } // main(..) metodu sonu...
} // J3b_8 s�n�f� sonu...

/* ��kt�:
**  >java J3b_8 c:  **
Dosya Sistemi           Toplam KB      Dolu KB       Bo� KB
(C:)                    153497596     34269108    119228488

Toplam de�erler:        153497596     34269108    119228488

**  >java J3b_8 d:  **
Dosya Sistemi           Toplam KB      Dolu KB       Bo� KB
(D:)                    334784508     41927704    292856804

Toplam de�erler:        334784508     41927704    292856804

**  >java J3b_8  **
Dosya Sistemi           Toplam KB      Dolu KB       Bo� KB
(C:)                    153497596     34269108    119228488
(D:)                    334784508     41927704    292856804

Toplam de�erler:        488282104     76196812    412085292
*/