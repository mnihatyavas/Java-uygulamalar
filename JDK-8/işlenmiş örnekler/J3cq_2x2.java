// J3cq_2x2.java: Producer (Üretici) alt-örneði.

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class J3cq_2x2 implements Runnable {
    private BlockingQueue<String> damla;

    public J3cq_2x2 (BlockingQueue<String> damla) {this.damla = damla;} // Kurucu...

    public void run() {
        String bilgi[] = {
            "Kýsraklar yulaf yer,",
            "Marallar da yulaf yer.",
            "Kuzular yemlik yer,",
            "Bir çocuk da yemlik yer.",
            "Sen ne yersin?"
        };

        Random rasgele = new Random();
        try {
            for (int i = 0; i < bilgi.length; i++) {
                damla.put (bilgi[i]); // damla listesine put/koyar...
                Thread.sleep (rasgele.nextInt (5000)); // [0->5000] ms.bekler...
            } // for döngüsü sonu...
            damla.put ("TAMAM"); // damla listesine put/koyar...
        }catch (InterruptedException ist) {}
    } // run() metodu sonu...
} // J3cq_2x2 sýnýfý sonu...