// J3c_1.java: ProducerConsumerExample (�reticiT�ketici�rne�i).

/* Gereken dosyalar:
 *    j3c_1x1.java=Drop
 *    j3c_1x2.java=Consumer
 *    j3c_1x3.java=Producer
 */
public class J3c_1 {
    public static void main (String[] args) {
        J3c_1x1 mesajla�ma = new J3c_1x1(); // J3c_1x1=Drop/Mesajla�ma...
        (new Thread (new J3c_1x3 (mesajla�ma))).start(); // J3c_1x3=Producer/�retici...
        (new Thread (new J3c_1x2 (mesajla�ma))).start(); // J3c_1x2=Consumer/T�ketici...
    } // main(..) metodu sonu...
} // J3c_1 s�n�f� sonu...

/* ��kt�:
**  >java J3c_1  **
MESAJ ALINDI: K�sraklar yulaf yer
MESAJ ALINDI: Marallar yulaf yer
MESAJ ALINDI: K���k kuzular yemlik yer
MESAJ ALINDI: Bir �ocuk da yemlik yer
*/