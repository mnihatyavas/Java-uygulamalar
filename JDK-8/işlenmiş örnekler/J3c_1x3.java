// J3c_1x3.java: Producer (Üretici) alt-örneði.

import java.util.Random;

public class J3c_1x3 implements Runnable {
    private J3c_1x1 mesajlaþma; // J3c_1x1=Drop/Mesajlaþma...

    public J3c_1x3 (J3c_1x1 mesajlaþma) {this.mesajlaþma = mesajlaþma;} // J3c_1x3() kurucusu...

    public void run() {
        String bilgi[] = {
            "Kýsraklar yulaf yer",
            "Marallar yulaf yer",
            "Küçük kuzular yemlik yer",
            "Bir çocuk da yemlik yer"
        };

        Random rasgele = new Random();

        for (int i = 0; i < bilgi.length; i++) {
            mesajlaþma.koy (bilgi[i]);
            try {Thread.sleep (rasgele.nextInt (5000));
            }catch (InterruptedException ist) {}
        } // for döngüsü sonu...
        mesajlaþma.koy ("TAMAM");
    } // run() metodu sonu...
} // J3c_1x3 sýnýfý sonu...