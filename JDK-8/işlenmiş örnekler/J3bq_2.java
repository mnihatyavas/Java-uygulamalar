// J3bq_2.java: FindInt (BulTamsay�y�) �rne�i.

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
        int say� = -1;
        try (SeekableByteChannel sbc = Files.newByteChannel (dosya)) {
            ByteBuffer bb = ByteBuffer.allocate (8);

            // Dosyadaki telafiyi konumunu alal�m...
            sbc.read (bb);
            long telafi = bb.getLong (0);

            // Telafi konumuna kadar arayal�m...
            sbc.position (telafi);
            bb.rewind();

            // "Gizli" de�eri okuyal�m...
            sbc.read (bb);
            say� = bb.getInt (0);
        }catch (IOException ist) {System.err.println (ist);
        } // try-catch blo�u sonu...
        return say�;
    } // ara() metodu sonu...

    public static void main (String[] args) throws IOException {
        String dosyaAd� = args.length > 0? args[0] : "veridosyas�";
        Path dosya = Paths.get (dosyaAd�);
        int say� = new J3bq_2 (dosya).ara();
        System.out.println ("[" + dosyaAd� + "] dosyas�ndan okunan gizli say�: " + say� + "'d�r.");
    } // main(..) metodu sonu...
} // J3bq_2 s�n�f� sonu...

/* ��kt�:
**  >java J3bq_2  **
[veridosyas�] dosyas�ndan okunan gizli say�: 123'd�r.

**  >java J3bq_2 J3bq_2.java  ** TEKRAR
[J3bq_2.java] dosyas�ndan okunan gizli say�: 791617610'd�r.

**  >java J3bq_2 J3bq_1.java  ** TEKRAR
[J3bq_1.java] dosyas�ndan okunan gizli say�: 791617610'd�r.
*/