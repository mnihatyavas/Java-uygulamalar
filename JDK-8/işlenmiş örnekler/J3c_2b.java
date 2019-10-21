// J3c_2b.java: Deadlock2 (��z�ms�z) �rne�i.

public class J3c_2b {
    static class Arkada� {
        private final String isim;

        public Arkada� (String isim) {this.isim = isim;} // Arkada� kurucusu...
        public String isimAl() {return this.isim;}

        public synchronized void selam (Arkada� selamlayan) {
            System.out.format ("%s: %s beni ba��yla selamlad�!%n", this.isim, selamlayan.isimAl());

            /* synchronized olunca ��z�ms�zl�k olu�ur; ayn� anda mukabilSelam(..)
             * �a�r�l�nca t�kan�r kal�r...
             */
            selamlayan.mukabilSelam (this);
        } // selam(..) metodu sonu...

        public synchronized void mukabilSelam (Arkada� selamlayan) {
            System.out.format ("%s: %s selam�ma ba��yla mukabelede bulundu!%n%n", this.isim, selamlayan.isimAl());
        } // mukabilSelam(..) metodu sonu...
    } // Arkada� s�n�f� sonu...

    public static void main (String[] args) {
        final Arkada� nihal = new Arkada� ("Nihal");
        final Arkada� nihat = new Arkada� ("Nihat");

        new Thread (new Runnable() {public void run() {nihal.selam (nihat);}}).start();
        new Thread (new Runnable() {public void run() {nihat.selam (nihal);}}).start();
    } // main(..) metodu sonu...
} // J3c_2b s�n�f� sonu...

/* ��kt�:
**  >java J3c_2b  **
Nihal: Nihat beni ba��yla selamlad�!
Nihat: Nihal beni ba��yla selamlad�!
// T�kand� kald�!..
*/