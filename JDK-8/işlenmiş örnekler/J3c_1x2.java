// J3c_1x2.java: Consumer (T�ketici) alt-�rne�i.

import java.util.Random;

public class J3c_1x2 implements Runnable {
    private J3c_1x1 mesajla�ma; // J3c_1x1=Drop/Mesajla�ma...

    public J3c_1x2 (J3c_1x1 mesajla�ma) {this.mesajla�ma = mesajla�ma;} // J3c_1x2() kurucusu...

    public void run() {
        Random rasgele = new Random();
        for (String mesaj = mesajla�ma.al(); !mesaj.equals ("TAMAM"); mesaj = mesajla�ma.al()) {
            System.out.format ("MESAJ ALINDI: %s%n", mesaj);
            try {Thread.sleep (rasgele.nextInt (5000));
            }catch (InterruptedException ist) {}
        } // for d�ng�s� sonu...
    } // run() metodu sonu...
} // J3c_1x2 s�n�f� sonu...