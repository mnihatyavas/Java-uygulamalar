// J3c_1.java: ProducerConsumerExample (ÜreticiTüketiciÖrneði).

/* Gereken dosyalar:
 *    j3c_1x1.java=Drop
 *    j3c_1x2.java=Consumer
 *    j3c_1x3.java=Producer
 */
public class J3c_1 {
    public static void main (String[] args) {
        J3c_1x1 mesajlaþma = new J3c_1x1(); // J3c_1x1=Drop/Mesajlaþma...
        (new Thread (new J3c_1x3 (mesajlaþma))).start(); // J3c_1x3=Producer/Üretici...
        (new Thread (new J3c_1x2 (mesajlaþma))).start(); // J3c_1x2=Consumer/Tüketici...
    } // main(..) metodu sonu...
} // J3c_1 sýnýfý sonu...

/* Çýktý:
**  >java J3c_1  **
MESAJ ALINDI: Kýsraklar yulaf yer
MESAJ ALINDI: Marallar yulaf yer
MESAJ ALINDI: Küçük kuzular yemlik yer
MESAJ ALINDI: Bir çocuk da yemlik yer
*/