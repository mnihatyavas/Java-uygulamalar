// J3b_14.java: Password (�ifre) �rne�i.

import java.io.Console;
import java.util.Arrays;
import java.io.IOException;

public class J3b_14 {
    static private String �ifreGiri�i;

    public static void main (String args[]) throws IOException {
        Console klavye = System.console();
        if (klavye == null) {System.err.println ("Klavyeniz yok!"); System.exit (1);}

        �ifreGiri�i = klavye.readLine ("�ifrenizi tu�lay�n�z: ");
        char [] eski�ifre = klavye.readPassword ("�ifrenizi onay i�in tekrar tu�lay�n: ");

        if (onay (�ifreGiri�i, eski�ifre)) {
            System.out.println ("�ifre de�i�ikli�i yap�lacakt�r==>");
            boolean uymad�;
            do {char [] yeni�ifre1 = klavye.readPassword ("Yeni �ifrenizi girin: ");
                char [] yeni�ifre2 = klavye.readPassword ("Yeni �ifrenizi tekrar girin: ");
                uymad� = ! Arrays.equals (yeni�ifre1, yeni�ifre2);

                if (uymad�) klavye.format ("�ifreler ayn� de�il; tekrar deneyin.%n");
                else {de�i�tir (�ifreGiri�i, yeni�ifre1);
                    klavye.format ("[%s] �ifreniz yenisiyle de�i�tirildi.%n", �ifreGiri�i);
                } // else karar� sonu...

                Arrays.fill (yeni�ifre1, ' ');
                Arrays.fill (yeni�ifre2, ' ');
            }while (uymad�);
        } // if karar� sonu...

        Arrays.fill (eski�ifre, ' ');
    } // main(..) metodu sonu...
    
    static boolean onay (String �ifreGiri�i, char[] �ifre) {
        if (�ifreGiri�i.equals (new String (�ifre))) return true;
        return false;
    } // onay(..) metodu sonu...

    static void de�i�tir (String �ifreGiri�i, char[] �ifre) {�ifreGiri�i = new String (�ifre);}
} // J3b_14 s�n�f� sonu...

/* ��kt�:
**  >java J3b_14  **
�ifrenizi tu�lay�n�z: M.Nihat Yava�
�ifrenizi onay i�in tekrar tu�lay�n:
�ifre de�i�ikli�i yap�lacakt�r==>
Yeni �ifrenizi girin:
Yeni �ifrenizi tekrar girin:
�ifreler ayn� de�il; tekrar deneyin.
Yeni �ifrenizi girin:
Yeni �ifrenizi tekrar girin:
[M.Nihat Yava�] �ifreniz yenisiyle de�i�tirildi.
*/