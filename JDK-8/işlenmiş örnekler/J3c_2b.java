// J3c_2b.java: Deadlock2 (Çözümsüz) örneði.

public class J3c_2b {
    static class Arkadaþ {
        private final String isim;

        public Arkadaþ (String isim) {this.isim = isim;} // Arkadaþ kurucusu...
        public String isimAl() {return this.isim;}

        public synchronized void selam (Arkadaþ selamlayan) {
            System.out.format ("%s: %s beni baþýyla selamladý!%n", this.isim, selamlayan.isimAl());

            /* synchronized olunca çözümsüzlük oluþur; ayný anda mukabilSelam(..)
             * çaðrýlýnca týkanýr kalýr...
             */
            selamlayan.mukabilSelam (this);
        } // selam(..) metodu sonu...

        public synchronized void mukabilSelam (Arkadaþ selamlayan) {
            System.out.format ("%s: %s selamýma baþýyla mukabelede bulundu!%n%n", this.isim, selamlayan.isimAl());
        } // mukabilSelam(..) metodu sonu...
    } // Arkadaþ sýnýfý sonu...

    public static void main (String[] args) {
        final Arkadaþ nihal = new Arkadaþ ("Nihal");
        final Arkadaþ nihat = new Arkadaþ ("Nihat");

        new Thread (new Runnable() {public void run() {nihal.selam (nihat);}}).start();
        new Thread (new Runnable() {public void run() {nihat.selam (nihal);}}).start();
    } // main(..) metodu sonu...
} // J3c_2b sýnýfý sonu...

/* Çýktý:
**  >java J3c_2b  **
Nihal: Nihat beni baþýyla selamladý!
Nihat: Nihal beni baþýyla selamladý!
// Týkandý kaldý!..
*/