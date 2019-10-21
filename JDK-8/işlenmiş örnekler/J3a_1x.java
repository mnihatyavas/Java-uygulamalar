// J3a_1x.java: InputFileDeclared (GirdiDosyas�Beyan�) alt-�rne�i.

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class J3a_1x {
    private FileReader fr;

    public J3a_1x (String dosya�smi) throws FileNotFoundException {
        fr = new FileReader (dosya�smi);
    } // J3a_1x(..) kurucu sonu...

    public String kelimeAl() throws IOException {
        int krk;
        StringBuffer sb = new StringBuffer();

        do {krk = fr.read();
            if (Character.isWhitespace ((char)krk))
                return sb.toString();
            else sb.append ((char)krk);
        } while (krk != -1);
        return sb.toString();
    } // kelimeAl() meto sonu...
} // J3a_1x s�n�f� sonu...