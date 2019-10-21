// J3cq_2.java: ProducerConsumerExample (ÜreticiTüketiciÖrneði).

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/* Gereken dosyalar:
 *   J3cq_2x1.java=Consumer
 *   J3cq_2x2.java=Producer
 */
public class J3cq_2 {
    public static void main (String[] args) {
        BlockingQueue<String> mesajListesi = new SynchronousQueue<String> ();
        new Thread (new J3cq_2x2 (mesajListesi)).start();
        (new Thread (new J3cq_2x1 (mesajListesi))).start();
    } // main(..) metodu sonu...
} // J3cq_2 sýnýfý sonu...

/* Çýktý:
**  >java J3cq_2  **
ALINAN MESAJ: Kýsraklar yulaf yer,
ALINAN MESAJ: Marallar da yulaf yer.
ALINAN MESAJ: Kuzular yemlik yer,
ALINAN MESAJ: Bir çocuk da yemlik yer.
ALINAN MESAJ: Sen ne yersin?
*/