// J3c_1x3.java: Producer (�retici) alt-�rne�i.

import java.util.Random;

public class J3c_1x3 implements Runnable {
    private J3c_1x1 mesajla�ma; // J3c_1x1=Drop/Mesajla�ma...

    public J3c_1x3 (J3c_1x1 mesajla�ma) {this.mesajla�ma = mesajla�ma;} // J3c_1x3() kurucusu...

    public void run() {
        String bilgi[] = {
            "K�sraklar yulaf yer",
            "Marallar yulaf yer",
            "K���k kuzular yemlik yer",
            "Bir �ocuk da yemlik yer"
        };

        Random rasgele = new Random();

        for (int i = 0; i < bilgi.length; i++) {
            mesajla�ma.koy (bilgi[i]);
            try {Thread.sleep (rasgele.nextInt (5000));
            }catch (InterruptedException ist) {}
        } // for d�ng�s� sonu...
        mesajla�ma.koy ("TAMAM");
    } // run() metodu sonu...
} // J3c_1x3 s�n�f� sonu...