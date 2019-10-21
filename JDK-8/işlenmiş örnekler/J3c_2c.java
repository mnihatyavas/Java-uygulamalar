// J3c_2c.java: Safelock (Çözümlü) örneði.

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class J3c_2c {
    static class Arkadaþ {
        private final String isim;
        private final Lock düðüm = new ReentrantLock();

        public Arkadaþ (String isim) {this.isim = isim;} // Arkadaþ(..) kurucusu...
        public String isimAl() {return this.isim;}

        public boolean geciktirilenSelam (Arkadaþ selamVeren) {
            Boolean düðümüm = false;
            Boolean düðümün = false;
            try {düðümüm = düðüm.tryLock();
                düðümün = selamVeren.düðüm.tryLock();
            } finally {
                if (! (düðümüm && düðümün)) {
                    if (düðümüm) düðüm.unlock(); // Düðümlenme oluþmuþsa çözer...
                    if (düðümün) selamVeren.düðüm.unlock(); // Düðümlenme oluþmuþsa çözer...
                } // if kararý sonu...
            } // try-finally bloðu sonu...
            return düðümüm && düðümün;
        } // geciktirilenSelam(..) metodu sonu...

        public void selam (Arkadaþ selamVeren) {
            if (geciktirilenSelam (selamVeren)) {
                try {System.out.format ("%s: %s beni baþýyla selamladý!%n", this.isim, selamVeren.isimAl());
                    selamVeren.mukabilSelam (this);
                } finally {düðüm.unlock(); // Düðümlenme oluþmuþsa çözer...
                    selamVeren.düðüm.unlock(); // Düðümlenme oluþmuþsa çözer...
                } // try-finally bloðu sonu...
            } else {
                System.out.format ("%s: %s tam beni selamlayacakken farkettim ki ben onu selamlamaktayým!%n",
                    this.isim, selamVeren.isimAl());
            } // else kararý sonu...
        } // selam(..) metodu sonu...

        public void mukabilSelam (Arkadaþ selamVeren) {
            System.out.format ("%s: %s selamýma baþýyla mukabelede bulundu!%n",
                this.isim, selamVeren.isimAl());
        } // mukabilSelam(..) metodu sonu...
    } // Arkadaþ sýnýfý sonu...

    static class SelamDöngüsü implements Runnable {
        private Arkadaþ selamVeren;
        private Arkadaþ selamAlan;

        public SelamDöngüsü (Arkadaþ selamVeren, Arkadaþ selamAlan) {
            this.selamVeren = selamVeren;
            this.selamAlan = selamAlan;
        } // SelamDöngüsü (..) kurucusu sonu...

        public void run() {
            Random rasgele = new Random();
            for (int i = 0; i < 2; i++) {// Sonsuz for(;;) döngüsünü 2 kez selamlaþmayla sýnýrlayalým...
                try {Thread.sleep (rasgele.nextInt (1000)); // [0->1] sn.arasý bekleme...
                }catch (InterruptedException ist) {}
                selamAlan.selam (selamVeren);
            } // for döngüsü sonu...
        } // run() metodu sonu...
    } // SelamDöngüsü sýnýfý sonu...

    public static void main (String[] args) {
        final Arkadaþ nihal = new Arkadaþ ("Nihal");
        final Arkadaþ nihat = new Arkadaþ ("Nihat");

        new Thread (new SelamDöngüsü (nihal, nihat)).start();
        new Thread (new SelamDöngüsü (nihat, nihal)).start();
    } // main(..) metodu sonu...
} // J3c_2c sýnýfý sonu...

/* Çýktý:
**  >java J3c_2c  **
Nihal: Nihat beni baþýyla selamladý!
Nihat: Nihal selamýma baþýyla mukabelede bulundu!
Nihat: Nihal tam beni selamlayacakken farkettim ki ben onu selamlamaktayým!
Nihat: Nihal beni baþýyla selamladý!
Nihal: Nihat selamýma baþýyla mukabelede bulundu!
Nihal: Nihat beni baþýyla selamladý!
Nihat: Nihal selamýma baþýyla mukabelede bulundu!
*/