// J2hq_2.java: Server (Sunucu) örneði.

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

        J2hq_2x2.mesajýYaz ("Sunucu soketini yaratýyor."); // J2hq_2x2=Utilities/Yararlýklar...
        try {sunucuSoketi = new ServerSocket (4444);
        }catch (IOException ist) {
            System.err.println ("Sunucu soketi yaratýlamadý: " + ist);
            System.exit (1);
        } // try-catch bloðu sonu...

        J2hq_2x2.mesajýYaz ("Müþteri baðlantýlarýný kabul ediyor.");
        while (true) {// sonsuz döngü; kýrmak için exit/break gerekir...
            try {Socket müþteriSoketi = sunucuSoketi.accept();
                new J2hq_2x1 (müþteriSoketi).start(); // J2hq_2x1_Client/Müþteri...
            }catch (IOException ist) {
                System.err.println ("Müþteri soket baðlantýsý onaylanmadý: " + ist); 
                System.exit (1);
            } // try-catch bloðu sonu...
        } // while döngüsü sonu...
    } // main(..) metodu sonu...
} // J2hq_2 sýnýfý sonu...

/* Çýktý:
**  >java J2hq_2  **
Sunucu soketini yaratýyor.
Müþteri baðlantýlarýný kabul ediyor.
*/