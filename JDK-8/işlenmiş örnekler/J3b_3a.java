// J3b_3a.java: CardWriter (VantiYazýcý) örneði.

// Gereken dosya: J3b_3x.java=Cards3
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class J3b_3a {
    public static void main (String[] args) {
        int takým, sayý;
        try {takým = Integer.valueOf (args[0]).intValue();}catch (Exception ist) {takým = 4;}
        try {sayý = Integer.valueOf (args[1]).intValue();}catch (Exception ist) {sayý = 12;}

        J3b_3x kart = new J3b_3x (sayý, takým); // J3b_3x=Card3/Vanti3...
        System.out.println ("Dosyaya yazýlacak kart: [" + kart + "]");

        try {FileOutputStream yaz = new FileOutputStream ("kart.ser");
            ObjectOutputStream oos = new ObjectOutputStream (yaz);
            oos.writeObject (kart); // Tek kayýt yazýlmakta...
            oos.flush();
        }catch (Exception ist) {System.err.println ("Serileþtirme hatasý: " + ist); System.exit (-1);
        } // try-catch bloðu sonu...
        System.out.println ("[" + kart + "] verisi [kart.ser] dosyasýna kaydedilmiþtir.");
    } // main(..) metodu sonu...
} // J3b_3a sýnýfý sonu...

/* Çýktý:
**  >java J3b_3a  **
Dosyaya yazýlacak kart: [Sinek Kýz]
[Sinek Kýz] verisi [kart.ser] dosyasýna kaydedilmiþtir.

**  >java J3b_3a 3 10  ** TEKRAR
Dosyaya yazýlacak kart: [Kupa On]
[Kupa On] verisi [kart.ser] dosyasýna kaydedilmiþtir.
*/