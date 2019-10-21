// J2hq_2x1.java: Client (Müþteri) alt-örneði.

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

public class J2hq_2x1 extends Thread {
    Socket müþteriSoketi = null;

    public J2hq_2x1 (Socket soket) {müþteriSoketi = soket;} // Kurucu...

    public void run() {
        if (müþteriSoketi == null) return;

        PrintStream çýktý = null;

        J2hq_2x2.mesajýYaz ("Sunucu soketinden onay akýþý bekliyor."); // J2hq_2x2= Utilities/Yararlýklar...
        try {çýktý = new PrintStream (müþteriSoketi.getOutputStream());
        }catch (IOException ist) {
            System.err.println ("Çýktýyý sunucu soketine baðlama hatasý: " + ist);
            System.exit (1);
        } // try-catch bloðu sonu...

        J2hq_2x2.mesajýYaz ("Güncel tarih ve saatý yazýyor.");
        Date tarih = new Date();
        çýktý.println (tarih);
        try {çýktý.close();
            müþteriSoketi.close();
        }catch (IOException ist) {}
    } // run() metodu sonu...

    protected void finalize() {
        if (müþteriSoketi != null) {
            try {müþteriSoketi.close();
            }catch (IOException ist) {}

            müþteriSoketi = null;
        } // if kararý sonu...
    } // finalize() metodu sonu...
} // J2hq_2x1 sýnýfý sonu...