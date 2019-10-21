// J3c_1x2.java: Consumer (Tüketici) alt-örneði.

import java.util.Random;

public class J3c_1x2 implements Runnable {
    private J3c_1x1 mesajlaþma; // J3c_1x1=Drop/Mesajlaþma...

    public J3c_1x2 (J3c_1x1 mesajlaþma) {this.mesajlaþma = mesajlaþma;} // J3c_1x2() kurucusu...

    public void run() {
        Random rasgele = new Random();
        for (String mesaj = mesajlaþma.al(); !mesaj.equals ("TAMAM"); mesaj = mesajlaþma.al()) {
            System.out.format ("MESAJ ALINDI: %s%n", mesaj);
            try {Thread.sleep (rasgele.nextInt (5000));
            }catch (InterruptedException ist) {}
        } // for döngüsü sonu...
    } // run() metodu sonu...
} // J3c_1x2 sýnýfý sonu...