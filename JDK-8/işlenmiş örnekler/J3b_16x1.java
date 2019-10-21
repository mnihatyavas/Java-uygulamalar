// J3b_16x1.java: ReverseThread (TersleyenSicim) alt-örneði.

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class J3b_16x1 extends Thread {
    private PrintWriter yaz = null;
    private BufferedReader oku = null;

    public J3b_16x1(PrintWriter yaz, BufferedReader oku) {
        this.yaz = yaz;
        this.oku = oku;
    } // J3b_16x1(..) kurucusu sonu...

    public void run() {
        if (yaz != null && oku != null) {
            try {String satýr;
                while ((satýr = oku.readLine()) != null) {
                    yaz.println (ters (satýr));
                    yaz.flush();
                } // while döngüsü sonu..
                yaz.close();
            }catch (IOException ist) {System.err.println ("TersleyenSicim run() metodu hatasý: " + ist);
            } // try-catch bloðu sonu...
        } // if kararý sonu...
    } // run() metodu sonu...

    private String ters (String satýr) {
        int i, uzunluk = satýr.length();
        StringBuffer sonuç = new StringBuffer (uzunluk);

        for (i = (uzunluk - 1); i >= 0; i--) sonuç.append (satýr.charAt (i));
        return sonuç.toString();
    } // ters(..) metodu sonu...
} // J3b_16x1 sýnýfý sonu...