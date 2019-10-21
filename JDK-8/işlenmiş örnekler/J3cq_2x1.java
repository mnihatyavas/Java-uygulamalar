// J3cq_2x1.java: Consumer (T�ketici) alt-�rne�i.

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class J3cq_2x1 implements Runnable {
    private BlockingQueue<String> damla;

    public J3cq_2x1(BlockingQueue<String> damla) {this.damla = damla;} // Kurucu...

    public void run() {
        Random rasgele = new Random();
        try {
            for (String mesaj = damla.take(); ! mesaj.equals ("TAMAM"); mesaj = damla.take()) {
                // damla listesinden take/al�r...
                System.out.format ("ALINAN MESAJ: %s%n", mesaj);
                Thread.sleep (rasgele.nextInt (5000)); // [0->5000] ms.bekler...
            } // for d�ng�s� sonu...
        }catch (InterruptedException ist) {}
    } // run() metodu sonu...
} // J3cq_2x1 s�n�f� sonu...