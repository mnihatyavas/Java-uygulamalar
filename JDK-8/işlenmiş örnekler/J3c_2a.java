// J3c_2a.java: Deadlock1 (!��z�ms�z) �rne�i.

public class J3c_2a {
    static class Arkada� {
        private final String isim;

        public Arkada� (String isim) {this.isim = isim;} // Arkada� kurucusu...
        public String isimAl() {return this.isim;}

        public void selam (Arkada� selamlayan) {//synchronized olmay�nca ��z�ms�zl�k olu�maz...
            System.out.format ("%s: %s beni ba��yla selamlad�!%n", this.isim, selamlayan.isimAl());
            selamlayan.mukabilSelam (this);
        } // selam(..) metodu sonu...

        public void mukabilSelam (Arkada� selamlayan) {
            System.out.format ("%s: %s selam�ma ba��yla mukabelede bulundu!%n%n", this.isim, selamlayan.isimAl());
        } // mukabilSelam(..) metodu sonu...
    } // Arkada� s�n�f� sonu...

    public static void main (String[] args) {
        final Arkada� nihal = new Arkada� ("Nihal");
        final Arkada� nihat = new Arkada� ("Nihat");

        new Thread (new Runnable() {public void run() {nihal.selam (nihat);}}).start();
        new Thread (new Runnable() {public void run() {nihat.selam (nihal);}}).start();
    } // main(..) metodu sonu...
} // J3c_2a s�n�f� sonu...

/* ��kt�:
**  >java J3c_2a  **
Nihal: Nihat beni ba��yla selamlad�!
Nihat: Nihal selam�ma ba��yla mukabelede bulundu!

Nihat: Nihal beni ba��yla selamlad�!
Nihal: Nihat selam�ma ba��yla mukabelede bulundu!
*/