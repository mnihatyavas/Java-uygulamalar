// J3bq_2.java: FindInt (BulTamsayýyý) örneði.

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.channels.SeekableByteChannel;

public class J3bq_2 {
    private Path dosya;

    J3bq_2 (Path dosya) {this.dosya = dosya;} // Kurucu...

    public int ara() throws IOException {
        int sayý = -1;
        try (SeekableByteChannel sbc = Files.newByteChannel (dosya)) {
            ByteBuffer bb = ByteBuffer.allocate (8);

            // Dosyadaki telafiyi konumunu alalým...
            sbc.read (bb);
            long telafi = bb.getLong (0);

            // Telafi konumuna kadar arayalým...
            sbc.position (telafi);
            bb.rewind();

            // "Gizli" deðeri okuyalým...
            sbc.read (bb);
            sayý = bb.getInt (0);
        }catch (IOException ist) {System.err.println (ist);
        } // try-catch bloðu sonu...
        return sayý;
    } // ara() metodu sonu...

    public static void main (String[] args) throws IOException {
        String dosyaAdý = args.length > 0? args[0] : "veridosyasý";
        Path dosya = Paths.get (dosyaAdý);
        int sayý = new J3bq_2 (dosya).ara();
        System.out.println ("[" + dosyaAdý + "] dosyasýndan okunan gizli sayý: " + sayý + "'dür.");
    } // main(..) metodu sonu...
} // J3bq_2 sýnýfý sonu...

/* Çýktý:
**  >java J3bq_2  **
[veridosyasý] dosyasýndan okunan gizli sayý: 123'dür.

**  >java J3bq_2 J3bq_2.java  ** TEKRAR
[J3bq_2.java] dosyasýndan okunan gizli sayý: 791617610'dür.

**  >java J3bq_2 J3bq_1.java  ** TEKRAR
[J3bq_1.java] dosyasýndan okunan gizli sayý: 791617610'dür.
*/