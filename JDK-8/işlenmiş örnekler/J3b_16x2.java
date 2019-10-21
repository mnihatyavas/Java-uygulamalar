// J3b_16x2.java: SortThread (SýralayanSicim) alt-örneði.

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
        int AZAMÝKELÝME = 1000;

        if (yaz != null && oku != null) {
            try {
                String[] kelimelerListesi = new String[AZAMÝKELÝME];
                int kelimeSayýsý = 0;
 
                while ((kelimelerListesi[kelimeSayýsý] = oku.readLine()) != null) kelimeSayýsý++;
                hýzlýSýrala (kelimelerListesi, 0, kelimeSayýsý-1);
                for (int i = 0; i < kelimeSayýsý; i++) yaz.println (kelimelerListesi[i]);
                yaz.close();
            }catch (IOException ist) {System.err.println ("SýralayanSicim run() metodu hatasý: " + ist);
            } // try-catch bloðu sonu...
        } // if kararý sonu...
    } // run() metodu sonu...

    private static void hýzlýSýrala (String[] kelimeler, int alt0, int üst0) {
        int alt = alt0;
        int üst = üst0;

        if (alt >= üst) return;

        String orta = kelimeler[(alt + üst) / 2];
        while (alt < üst) {
            while (alt < üst && kelimeler[alt].compareTo (orta) < 0) alt++;
            while (alt < üst && kelimeler[üst].compareTo (orta) > 0) üst--;
            if (alt < üst) {
                String kelime = kelimeler[alt];
                kelimeler[alt] = kelimeler[üst];
                kelimeler[üst] = kelime;
                alt++;
                üst--;
            } // if kararý sonu...
        } // while döngüsü sonu...

        if (üst < alt) {
            int kelime = üst;
            üst = alt;
            alt = kelime;
        } // if kararý sonu...

        hýzlýSýrala (kelimeler, alt0, alt);
        hýzlýSýrala (kelimeler, alt == alt0? alt+1 : alt, üst0);
    } // hýzlýSýrala(..) metodu sonu...
} // J3b_16x2 sýnýfý sonu...