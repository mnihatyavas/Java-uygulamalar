// J3c_2c.java: Safelock (��z�ml�) �rne�i.

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class J3c_2c {
    static class Arkada� {
        private final String isim;
        private final Lock d���m = new ReentrantLock();

        public Arkada� (String isim) {this.isim = isim;} // Arkada�(..) kurucusu...
        public String isimAl() {return this.isim;}

        public boolean geciktirilenSelam (Arkada� selamVeren) {
            Boolean d���m�m = false;
            Boolean d���m�n = false;
            try {d���m�m = d���m.tryLock();
                d���m�n = selamVeren.d���m.tryLock();
            } finally {
                if (! (d���m�m && d���m�n)) {
                    if (d���m�m) d���m.unlock(); // D���mlenme olu�mu�sa ��zer...
                    if (d���m�n) selamVeren.d���m.unlock(); // D���mlenme olu�mu�sa ��zer...
                } // if karar� sonu...
            } // try-finally blo�u sonu...
            return d���m�m && d���m�n;
        } // geciktirilenSelam(..) metodu sonu...

        public void selam (Arkada� selamVeren) {
            if (geciktirilenSelam (selamVeren)) {
                try {System.out.format ("%s: %s beni ba��yla selamlad�!%n", this.isim, selamVeren.isimAl());
                    selamVeren.mukabilSelam (this);
                } finally {d���m.unlock(); // D���mlenme olu�mu�sa ��zer...
                    selamVeren.d���m.unlock(); // D���mlenme olu�mu�sa ��zer...
                } // try-finally blo�u sonu...
            } else {
                System.out.format ("%s: %s tam beni selamlayacakken farkettim ki ben onu selamlamaktay�m!%n",
                    this.isim, selamVeren.isimAl());
            } // else karar� sonu...
        } // selam(..) metodu sonu...

        public void mukabilSelam (Arkada� selamVeren) {
            System.out.format ("%s: %s selam�ma ba��yla mukabelede bulundu!%n",
                this.isim, selamVeren.isimAl());
        } // mukabilSelam(..) metodu sonu...
    } // Arkada� s�n�f� sonu...

    static class SelamD�ng�s� implements Runnable {
        private Arkada� selamVeren;
        private Arkada� selamAlan;

        public SelamD�ng�s� (Arkada� selamVeren, Arkada� selamAlan) {
            this.selamVeren = selamVeren;
            this.selamAlan = selamAlan;
        } // SelamD�ng�s� (..) kurucusu sonu...

        public void run() {
            Random rasgele = new Random();
            for (int i = 0; i < 2; i++) {// Sonsuz for(;;) d�ng�s�n� 2 kez selamla�mayla s�n�rlayal�m...
                try {Thread.sleep (rasgele.nextInt (1000)); // [0->1] sn.aras� bekleme...
                }catch (InterruptedException ist) {}
                selamAlan.selam (selamVeren);
            } // for d�ng�s� sonu...
        } // run() metodu sonu...
    } // SelamD�ng�s� s�n�f� sonu...

    public static void main (String[] args) {
        final Arkada� nihal = new Arkada� ("Nihal");
        final Arkada� nihat = new Arkada� ("Nihat");

        new Thread (new SelamD�ng�s� (nihal, nihat)).start();
        new Thread (new SelamD�ng�s� (nihat, nihal)).start();
    } // main(..) metodu sonu...
} // J3c_2c s�n�f� sonu...

/* ��kt�:
**  >java J3c_2c  **
Nihal: Nihat beni ba��yla selamlad�!
Nihat: Nihal selam�ma ba��yla mukabelede bulundu!
Nihat: Nihal tam beni selamlayacakken farkettim ki ben onu selamlamaktay�m!
Nihat: Nihal beni ba��yla selamlad�!
Nihal: Nihat selam�ma ba��yla mukabelede bulundu!
Nihal: Nihat beni ba��yla selamlad�!
Nihat: Nihal selam�ma ba��yla mukabelede bulundu!
*/