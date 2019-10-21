// J3b_16x1.java: ReverseThread (TersleyenSicim) alt-�rne�i.

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
            try {String sat�r;
                while ((sat�r = oku.readLine()) != null) {
                    yaz.println (ters (sat�r));
                    yaz.flush();
                } // while d�ng�s� sonu..
                yaz.close();
            }catch (IOException ist) {System.err.println ("TersleyenSicim run() metodu hatas�: " + ist);
            } // try-catch blo�u sonu...
        } // if karar� sonu...
    } // run() metodu sonu...

    private String ters (String sat�r) {
        int i, uzunluk = sat�r.length();
        StringBuffer sonu� = new StringBuffer (uzunluk);

        for (i = (uzunluk - 1); i >= 0; i--) sonu�.append (sat�r.charAt (i));
        return sonu�.toString();
    } // ters(..) metodu sonu...
} // J3b_16x1 s�n�f� sonu...