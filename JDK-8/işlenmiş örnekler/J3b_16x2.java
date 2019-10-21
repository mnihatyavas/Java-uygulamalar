// J3b_16x2.java: SortThread (S�ralayanSicim) alt-�rne�i.

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class J3b_16x2 extends Thread {
    private PrintWriter yaz = null;
    private BufferedReader oku = null;

    public J3b_16x2 (PrintWriter yaz, BufferedReader oku) {
        this.yaz = yaz;
        this.oku = oku;
    } // J3b_16x2(..) kurucusu sonu...

    public void run() {
        int AZAM�KEL�ME = 1000;

        if (yaz != null && oku != null) {
            try {
                String[] kelimelerListesi = new String[AZAM�KEL�ME];
                int kelimeSay�s� = 0;
 
                while ((kelimelerListesi[kelimeSay�s�] = oku.readLine()) != null) kelimeSay�s�++;
                h�zl�S�rala (kelimelerListesi, 0, kelimeSay�s�-1);
                for (int i = 0; i < kelimeSay�s�; i++) yaz.println (kelimelerListesi[i]);
                yaz.close();
            }catch (IOException ist) {System.err.println ("S�ralayanSicim run() metodu hatas�: " + ist);
            } // try-catch blo�u sonu...
        } // if karar� sonu...
    } // run() metodu sonu...

    private static void h�zl�S�rala (String[] kelimeler, int alt0, int �st0) {
        int alt = alt0;
        int �st = �st0;

        if (alt >= �st) return;

        String orta = kelimeler[(alt + �st) / 2];
        while (alt < �st) {
            while (alt < �st && kelimeler[alt].compareTo (orta) < 0) alt++;
            while (alt < �st && kelimeler[�st].compareTo (orta) > 0) �st--;
            if (alt < �st) {
                String kelime = kelimeler[alt];
                kelimeler[alt] = kelimeler[�st];
                kelimeler[�st] = kelime;
                alt++;
                �st--;
            } // if karar� sonu...
        } // while d�ng�s� sonu...

        if (�st < alt) {
            int kelime = �st;
            �st = alt;
            alt = kelime;
        } // if karar� sonu...

        h�zl�S�rala (kelimeler, alt0, alt);
        h�zl�S�rala (kelimeler, alt == alt0? alt+1 : alt, �st0);
    } // h�zl�S�rala(..) metodu sonu...
} // J3b_16x2 s�n�f� sonu...