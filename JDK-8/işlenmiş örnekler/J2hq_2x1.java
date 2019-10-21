// J2hq_2x1.java: Client (M��teri) alt-�rne�i.

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

public class J2hq_2x1 extends Thread {
    Socket m��teriSoketi = null;

    public J2hq_2x1 (Socket soket) {m��teriSoketi = soket;} // Kurucu...

    public void run() {
        if (m��teriSoketi == null) return;

        PrintStream ��kt� = null;

        J2hq_2x2.mesaj�Yaz ("Sunucu soketinden onay ak��� bekliyor."); // J2hq_2x2= Utilities/Yararl�klar...
        try {��kt� = new PrintStream (m��teriSoketi.getOutputStream());
        }catch (IOException ist) {
            System.err.println ("��kt�y� sunucu soketine ba�lama hatas�: " + ist);
            System.exit (1);
        } // try-catch blo�u sonu...

        J2hq_2x2.mesaj�Yaz ("G�ncel tarih ve saat� yaz�yor.");
        Date tarih = new Date();
        ��kt�.println (tarih);
        try {��kt�.close();
            m��teriSoketi.close();
        }catch (IOException ist) {}
    } // run() metodu sonu...

    protected void finalize() {
        if (m��teriSoketi != null) {
            try {m��teriSoketi.close();
            }catch (IOException ist) {}

            m��teriSoketi = null;
        } // if karar� sonu...
    } // finalize() metodu sonu...
} // J2hq_2x1 s�n�f� sonu...