// J3b_3b.java: CardReader (VantiOkuyucu) �rne�i.

// Gereken dosya: J3a_3x.java=Card3
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class J3b_3b {
    public static void main (String[] args) {
        J3b_3x kart = null; // J3b_3x=Card3/Vanti3...

        try {FileInputStream oku = new FileInputStream ("kart.ser");
            ObjectInputStream ois = new ObjectInputStream (oku);
            kart = (J3b_3x)(ois.readObject()); // Tek kay�t okunmakta...
        }catch (Exception ist) {System.err.println ("Serile�tirme hatas�: " + ist); System.exit (-1);
        } // try-catch blo�u sonu...

        System.out.println ("[kart.ser] dosyas�ndan okunan veri: [" + kart + "]");
    } // main(..) metodu sonu...
} // J3b_3b s�n�f� sonu...

/* ��kt�:
**  >java J3b_3b  **
[kart.ser] dosyas�ndan okunan veri: [Kupa On]
*/