// J2hq_2.java: Server (Sunucu) �rne�i.

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/* Gereken dosyalar:
 *   J2hq-2x1.java=Client
 *   J2hq-2x2.java=Utilities
 */
public class J2hq_2 {
    public static void main (String args[]) {
        ServerSocket sunucuSoketi = null;

        J2hq_2x2.mesaj�Yaz ("Sunucu soketini yarat�yor."); // J2hq_2x2=Utilities/Yararl�klar...
        try {sunucuSoketi = new ServerSocket (4444);
        }catch (IOException ist) {
            System.err.println ("Sunucu soketi yarat�lamad�: " + ist);
            System.exit (1);
        } // try-catch blo�u sonu...

        J2hq_2x2.mesaj�Yaz ("M��teri ba�lant�lar�n� kabul ediyor.");
        while (true) {// sonsuz d�ng�; k�rmak i�in exit/break gerekir...
            try {Socket m��teriSoketi = sunucuSoketi.accept();
                new J2hq_2x1 (m��teriSoketi).start(); // J2hq_2x1_Client/M��teri...
            }catch (IOException ist) {
                System.err.println ("M��teri soket ba�lant�s� onaylanmad�: " + ist); 
                System.exit (1);
            } // try-catch blo�u sonu...
        } // while d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J2hq_2 s�n�f� sonu...

/* ��kt�:
**  >java J2hq_2  **
Sunucu soketini yarat�yor.
M��teri ba�lant�lar�n� kabul ediyor.
*/