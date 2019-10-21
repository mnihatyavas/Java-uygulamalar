// J3b_3b.java: CardReader (VantiOkuyucu) örneği.

// Gereken dosya: J3a_3x.java=Card3
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class J3b_3b {
    public static void main (String[] args) {
        J3b_3x kart = null; // J3b_3x=Card3/Vanti3...

        try {FileInputStream oku = new FileInputStream ("kart.ser");
            ObjectInputStream ois = new ObjectInputStream (oku);
            kart = (J3b_3x)(ois.readObject()); // Tek kayıt okunmakta...
        }catch (Exception ist) {System.err.println ("Serileştirme hatası: " + ist); System.exit (-1);
        } // try-catch bloğu sonu...

        System.out.println ("[kart.ser] dosyasından okunan veri: [" + kart + "]");
    } // main(..) metodu sonu...
} // J3b_3b sınıfı sonu...

/* Çıktı:
**  >java J3b_3b  **
[kart.ser] dosyasından okunan veri: [Kupa On]
*/